package com.open.capacity.video.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 每一帧的数据, 注意这里不能使用lombok设置getter/setter, 不然会导致spark-streaming启动报错
 *
 * @author donglh
 * @date 2020/7/15
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoFrameData implements Serializable {
    /**
     * 任务id
     */
    private String taskId;
    /**
     * 当前帧的毫秒数
     */
    private double millSec;
    /**
     * 行数
     */
    private int rows;
    /**
     * 列数
     */
    private int cols;
    /**
     * 类型
     */
    private int type;
    /**
     * base64 数据
     */
    private String data;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public double getMillSec() {
        return millSec;
    }

    public void setMillSec(double millSec) {
        this.millSec = millSec;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
