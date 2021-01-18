package com.open.device.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 中兴克拉-业务数据上报接口回调接收类
 */
@Data
public class Devstatepush {

    //状 态 更 新 时 间 戳 ， yyyy-MM-dd HH:mm:ss 格式字符串。
    private String rpttime;

    //设备标识 devEUI
    private String deveui;

    //0--在线；1--离线；2--异常；3--未知；
    private Integer state;
}
