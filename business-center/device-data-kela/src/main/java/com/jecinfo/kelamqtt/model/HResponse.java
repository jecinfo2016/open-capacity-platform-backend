package com.jecinfo.kelamqtt.model;

public class HResponse {

    /**
     * 请求结果的状态
     */
    private int status;
    private String description;
    private Object data;
    public HResponse() {
    }
    public HResponse(int status, String description, Object data) {
        this.status = status;
        this.description = description;
        this.data = data;
    }
    public static HResponse success() {
        return new HResponse(200, "操作成功", null);
    }
    public static HResponse success(String description) {
        return new HResponse(200, description, null);
    }
    public static HResponse success(Object data) {
        return new HResponse(200, "success", data);
    }
    public static HResponse success(String description, Object data) {
        return new HResponse(200, description, data);
    }
    public static HResponse error(String description) {
        return new HResponse(400, description, null);
    }
    public static HResponse error(String description, Object data) {
        return new HResponse(400, description, data);
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
