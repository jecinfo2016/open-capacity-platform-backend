package com.open.capacity.epanetgis.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 模型点位坐标实体类
 */
@Setter
@Getter
public class Coordinates {
    private Integer nodeId; //模型点ID
    private Double xCoord;  //模型点横坐标
    private Double yCoord;  //模型点纵坐标
}
