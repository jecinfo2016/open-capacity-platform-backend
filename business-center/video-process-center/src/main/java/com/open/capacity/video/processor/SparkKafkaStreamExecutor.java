package com.open.capacity.video.processor;

import com.open.capacity.video.entity.VideoFrameData;
import com.open.capacity.video.service.RecognizeResultService;
import com.open.capacity.video.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.MapGroupsWithStateFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * spark stream kafka 处理器
 *
 * @author donglh
 * @date 2020/7/15
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SparkKafkaStreamExecutor implements Serializable, Runnable {

    @Value("${spark.master.url}")
    private String sparkMasterUrl;
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServers;
    @Value("${spring.kafka.producer.batch-size}")
    private String batchSize;
    @Value("${spring.kafka.consumer.max-poll-records}")
    private String maxPollRecords;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String startingOffsets;
    @Value("${spring.kafka.topic}")
    private String topic;

    @Override
    @SneakyThrows
    public void run() {
        log.info("spark master url ==== {}", sparkMasterUrl);
        // spark 配置
        SparkConf sparkConf = new SparkConf();
        // sparkConf.set("spark.driver.bindAddress", "192.168.3.61");
        sparkConf.setAppName("video stream processor");
//        sparkConf.setMaster(sparkMasterUrl);
        // spark Session
        SparkSession spark = SparkSession.builder()
                .config(sparkConf)
                .getOrCreate();

        // 将kafka的中的json数据转换为结构体
        StructType schema = DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField("taskId", DataTypes.StringType, true),
                DataTypes.createStructField("millSec", DataTypes.DoubleType, true),
                DataTypes.createStructField("rows", DataTypes.IntegerType, true),
                DataTypes.createStructField("cols", DataTypes.IntegerType, true),
                DataTypes.createStructField("type", DataTypes.IntegerType, true),
                DataTypes.createStructField("data", DataTypes.StringType, true)
        });


        // 从kafka数据流中穿件dataSet
        Dataset<VideoFrameData> ds = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", kafkaServers)
                .option("subscribe", topic)
                .option("kafka.max.partition.fetch.bytes", batchSize)
                .option("kafka.max.poll.records", maxPollRecords)
                .option("startingOffsets", startingOffsets)
                .load()
                .selectExpr("CAST(value AS STRING) as message")
                .select(functions.from_json(functions.col("message"), schema).as("json"))
                .select("json.*")
                .as(Encoders.bean(VideoFrameData.class));

        // key value taskId
        KeyValueGroupedDataset<String, VideoFrameData> kvDataset =
                ds.groupByKey((MapFunction<VideoFrameData, String>) value -> value.getTaskId(), Encoders.STRING());


        Dataset<VideoFrameData> processedDataset =
                kvDataset.mapGroupsWithState((MapGroupsWithStateFunction<String, VideoFrameData, VideoFrameData, VideoFrameData>) (key, values, state) -> {
                    log.info("taskId = " + key + " PartitionId=" + TaskContext.getPartitionId());
                    VideoFrameData existing = null;
                    //check previous state
                    if (state.exists()) {
                        existing = state.get();
                    }
                    // detect motions
                    VideoFrameData processed = PedestrianDetector.pedestrian(key, values, existing);
                    // update last processed
                    if (processed != null) {
                        state.update(processed);
                    }
                    return processed;
                }, Encoders.bean(VideoFrameData.class), Encoders.bean(VideoFrameData.class));

        //开始
        StreamingQuery query = processedDataset.writeStream()
                .outputMode("update")
                .format("console")
                .start();

        // 将进程堵塞
        query.awaitTermination();
    }

}
