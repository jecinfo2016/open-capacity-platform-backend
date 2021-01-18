package com.open.capacity.developer.water.entity;

import java.util.Date;

/**
 * 水利模型-管线信息实体类
 * @author DUJIN
 */
public class EpnaetPipes {
    private Integer id;

    /**
     * 模型ID
     */
    private Integer modelId;
    /**
     * 管道ID
     */
    private Integer pipesId;
    /**
     * 起始点ID
     */
    private Integer node1Id;
    /**
     * 结束点ID
     */
    private Integer node2Id;
    /**
     * 管线长度
     */
    private Double length;
    /**
     * 直径
     */
    private Double diameter;
    /**
     * 粗糙系数
     */
    private Double roughness;
    /**
     * 损失系数
     */
    private Double minorLoss;
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getPipesId() {
        return pipesId;
    }

    public void setPipesId(Integer pipesId) {
        this.pipesId = pipesId;
    }

    public Integer getNode1Id() {
        return node1Id;
    }

    public void setNode1Id(Integer node1Id) {
        this.node1Id = node1Id;
    }

    public Integer getNode2Id() {
        return node2Id;
    }

    public void setNode2Id(Integer node2Id) {
        this.node2Id = node2Id;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public Double getRoughness() {
        return roughness;
    }

    public void setRoughness(Double roughness) {
        this.roughness = roughness;
    }

    public Double getMinorLoss() {
        return minorLoss;
    }

    public void setMinorLoss(Double minorLoss) {
        this.minorLoss = minorLoss;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "EpnaetPipes{" +
                "id=" + id +
                ", modelId=" + modelId +
                ", pipesId=" + pipesId +
                ", node1Id=" + node1Id +
                ", node2Id=" + node2Id +
                ", length=" + length +
                ", diameter=" + diameter +
                ", roughness=" + roughness +
                ", minorLoss=" + minorLoss +
                ", createTime=" + createTime +
                '}';
    }
}
