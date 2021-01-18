package com.open.device.model;

public class InfiComboUrl {
    //IP u:claazc p:claazc  ;lyxf
    private static String infiComboIp = "http://47.98.179.40:8093/openapi/v2";

    //用户认证接口
    public static String smLoginUrl = infiComboIp + "/sm/login";

    //设备类型查询
    public static String devtypesUrl = infiComboIp + "/dev/devtypes";

    //实配类型查询
    public static String projectdevtypesUrl = infiComboIp + "/dev/projectdevtypes";

    //设备列表查询
    public static String devsUrl = infiComboIp + "/dev/devs";

    //数据模型查询
    public static String datamodelUrl = infiComboIp + "/model/datamodel";

    //指令模型查询
    public static String controlmodelUrl = infiComboIp + "/model/controlmodel";

    //告警模型查询
    public static String alarmmodelUrl = infiComboIp + "/model/alarmmodel";

    //业务数据查询
    public static String devDataUrl = infiComboIp + "/data/devdata";

    //业务数据批量查询
    public static String batchdevdataUrl = infiComboIp + "/data/batchdevdata";

    //最新业务数据查询
    public static String latestdevdataUrl = infiComboIp + "/data/latestdevdata";

    //设备运行状态查询
    public static String devstatedataUrl = infiComboIp + "/data/devstatedata";

    //设备当前告警查询
    public static String devcuralarmUrl = infiComboIp + "/alarm/devcuralarm";

    //最新告警列表查询
    public static String getlatestalarmsUrl = infiComboIp + "/alarm/getlatestalarms";

    //设备历史告警查询
    public static String devhisalarmUrl = infiComboIp + "/alarm/devhisalarm";

    //历史告警批量查询
    public static String hisalarmlistUrl = infiComboIp + "/alarm/hisalarmlist";

    //设备控制指令下发
    public static String cmdUrl = infiComboIp + "/devcontrol/cmd";

    //设备组播指令下发
    public static String broadcastcmdUrl = infiComboIp + "/devcontrol/broadcastcmd";

    //上报接口注册（业务数据）
    public static String dataRegisteUrl = infiComboIp + "/datapush/registe";

    //上报接口注销
    public static String dataUnregisteUrl = infiComboIp + "/datapush/unregiste";

    //上报接口注册查询
    public static String dataRegistelistUrl = infiComboIp + "/datapush/registelist";

    //上报接口注册(告警数据)
    public static String alarmRegisteUrl = infiComboIp + "/alarmpush/registe";

    //上报接口注销
    public static String alarmUnregisteUrl = infiComboIp + "/alarmpush/unregiste";

    //上报接口注册查询
    public static String alarmRegistelistUrl = infiComboIp + "/alarmpush/registelist";

    //上报接口注册(设备状态)
    public static String devRegisteUrl = infiComboIp + "/devstatepush/registe";

    //上报接口注销
    public static String devUnregisteUrl = infiComboIp + "/devstatepush/unregiste";

    //上报接口注册查询
    public static String devRegistelistUrl = infiComboIp + "/devstatepush/registelist";
}
