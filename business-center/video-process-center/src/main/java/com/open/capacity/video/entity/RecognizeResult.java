package com.open.capacity.video.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 对于每一帧的分析结果, 识别结果
 *
 * @author donglh
 * @date 2020/7/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RecognizeResult implements Serializable {
    /**
     * id 主键
     */
    private String id;

    /**
     * 所属任务
     */
    private String taskId;
    /**
     * 行人识别列表 json数据
     */
    private String pedestriansJson;
    /**
     * 标记帧结果, base64形式
     */
    private String base64;
    /**
     * 当前帧数在视频的毫秒数
     */
    private Double millSec;
    /**
     * 人数统计
     */
    private Integer cnt;
}
