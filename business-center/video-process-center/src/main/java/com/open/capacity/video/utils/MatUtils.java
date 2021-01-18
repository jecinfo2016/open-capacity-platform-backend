package com.open.capacity.video.utils;

import com.open.capacity.video.entity.VideoFrameData;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.Base64;
import java.util.UUID;

/**
 * Mat 工具类
 *
 * @author donglh
 * @date 2020/7/15
 */
@Slf4j
public class MatUtils {

    private MatUtils() {

    }

    // 将opencv mat 保存下来
    public static String saveImage(Mat mat, String outputDir) {
        String imagePath = outputDir + UUID.randomUUID() + ".jpg";
        log.warn("Saving images to " + imagePath);
        boolean result = Imgcodecs.imwrite(imagePath, mat);
        if (!result) {
            log.error("Couldn't save images to path " + outputDir + ".Please check if this path exists. This is configured in processed.output.dir key of property file.");
            return null;
        }
        return imagePath;
    }

    // Get Mat from byte[]
    public static Mat getMat(VideoFrameData ed) {
        log.info("video frame data 当前豪秒数 ==========> {}", ed.getMillSec());
        Mat mat = new Mat(ed.getRows(), ed.getCols(), ed.getType());
        mat.put(0, 0, Base64.getDecoder().decode(ed.getData()));
        return mat;
    }
}
