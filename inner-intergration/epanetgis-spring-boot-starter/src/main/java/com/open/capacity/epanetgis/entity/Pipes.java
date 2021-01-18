package com.open.capacity.epanetgis.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 模型管线实体类
 */
@Setter
@Getter
public class Pipes {
    private Integer pipesId; //管道ID
    private Integer node1Id; //起始点ID
    private Integer node2Id; //结束点ID
    private Double length;  //管线长度
    private Double diameter;  //直径
    private Double roughness;  //粗糙系数
    private Double minorLoss;  //损失系数
}
