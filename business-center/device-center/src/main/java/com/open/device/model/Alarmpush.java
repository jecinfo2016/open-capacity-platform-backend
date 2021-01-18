package com.open.device.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 中兴克拉-业务数据上报接口回调接收类
 */
@Data
public class Alarmpush {

    //告警流水号
    private String alarmid;

    //设备类型
    private String devtype;

    //设备标识
    private String deveui;

    //告警码
    private String alarmcode;

    //类型标记 0: 告警恢复； 1: 告警发生； 2: 通知消息
    private Integer typeflag;

    //告警标题
    private String title;

    //告警级别，固定为 1
    private Integer alarmlevel;

    //告 警 上 报 时 间 戳 ， yyyy-MM-dd HH:mm:ss 格式字符串。
    private String alarmtime;

    //告警详细描述
    private String descp;

    //告 警 恢 复 时 间 ， yyyy-MM-dd HH:mm:ss 格式字符串，可为 null。
    private String cleartime;

    //告警确认状态: 0-未确认， 1-已确认；
    private Integer confirmstate;

    //告 警 确 认 时 间 ， yyyy-MM-dd HH:mm:ss 格式字符串，可为 null。
    private String confirmtime;

    //告警确认人姓名，可为 null
    private String confirmer;

    //告警备注信息
    private String remark;
}
