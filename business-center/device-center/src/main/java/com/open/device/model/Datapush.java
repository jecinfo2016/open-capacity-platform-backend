package com.open.device.model;

import lombok.Data;

import java.util.List;

/**
 * 中兴克拉-业务数据上报接口回调接收类
 */
@Data
public class Datapush {

    //业务数据对应的设备类型 ID，方便解析
    private String devtype;

    private List<BusinessData> datarows;

    @Data
    public class BusinessData{
        //采集时间  yyyy-MM-dd HH:mm:ss 格式
        private String collecttime;
        //设备标识
        private String deveui;
        //数据模型字段
        private Double tempature;
        private Double humdity;
        private Double pressure;
    }
}
