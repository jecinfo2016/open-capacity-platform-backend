package com.open.capacity.video.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.opencv.core.Point;

import java.io.Serializable;

/**
 * 行人属性类
 *
 * @author donglh
 * @date 2020/7/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Pedestrian implements Serializable {
    /**
     * 坐标起点
     */
    private Point start;
    /**
     * 坐标终点
     */
    private Point end;
    /*
     * todo 行人属性等
     */



}
