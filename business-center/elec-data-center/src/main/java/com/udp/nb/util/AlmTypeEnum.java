package com.udp.nb.util;

import com.udp.nb.entity.AlmResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paul
 * @description
 * @date 2019/5/15
 */
public enum AlmTypeEnum {
    TYPE_7(7, "漏电保护功能正常"),
    TYPE_15(15, "电流预警"),
    TYPE_23(23, "相序 ACB"),
    TYPE_31(31, "开关在线状态"),
    TYPE_6(6, "过压报警"),
    TYPE_14(14, "漏电预警"),
    TYPE_22(22, "不平衡报警"),
    TYPE_30(30, "开关开合状态"),
    TYPE_5(5, "过流报警"),
    TYPE_13(13, "欠压预警"),
    TYPE_21(21, "保留"),
    TYPE_29(29, "保留"),
    TYPE_4(4, "漏电报警"),
    TYPE_12(12, "过压预警"),
    TYPE_20(20, "保留"),
    TYPE_28(28, "保留"),
    TYPE_3(3, "温度报警"),
    TYPE_11(11, "欠压报警"),
    TYPE_19(19, "禁止相序保护"),
    TYPE_27(27, "保留"),
    TYPE_2(2, "过载报警"),
    TYPE_10(10, "打火报警"),
    TYPE_18(18, "禁止不平衡保护"),
    TYPE_26(26, "保留"),
    TYPE_1(1, "浪涌报警"),
    TYPE_9(9, "输入缺相(仅380)"),
    TYPE_17(17, "禁止缺相保护"),
    TYPE_25(25, "保留"),
    TYPE_0(0, "短路报警"),
    TYPE_8(8, "漏电保护自检未完成"),
    TYPE_16(16, "远程合闸禁止"),
    TYPE_24(24, "保留");

    //开关开合状态 由开到关
    public static final int TYPE_301 = 301;
    //开关开合状态 由关到开
    public static final int TYPE_302 = 302;

    //远程合闸禁止 由开到关
    public static final int TYPE_161 = 161;
    //远程合闸禁止 由关到开
    public static final int TYPE_162 = 162;

    //开关在线状态 由开到关
    public static final int TYPE_311 = 311;
    //开关在线状态 由关到开
    public static final int TYPE_312 = 312;

    //电器运行状态:off
    public static final int TYPE_321 = 321;
    //电器运行状态:idle
    public static final int TYPE_322 = 322;
    //电器运行状态:work
    public static final int TYPE_323 = 323;
    //网络掉线告警
    public static final int TYPE_33 = 33;


    AlmTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public final int type;
    public final String desc;

    public static List<AlmResult> contentList(long old, long now) {
        String string = new StringBuilder(Long.toBinaryString(old ^ now)).reverse().toString();
        //String string =new StringBuilder(Long.toBinaryString(now)).reverse().toString();
        List<AlmResult> almResults = new ArrayList<AlmResult>();
        for (int i = string.length() - 1; i >= 0; i--) {
            //for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '1') {
                AlmTypeEnum almTypeEnum = AlmTypeEnum.valueOf("TYPE_" + i);
                if (almTypeEnum != null) {

                    AlmResult almResult = new AlmResult();
                    almResult.setType(i);
                    almResult.setPlatformType(i);
                    almResult.setAlmTypeEnum(almTypeEnum);
                    String desc = almTypeEnum.desc;
                    if (almTypeEnum == TYPE_30) {
                        boolean oldcontent = content(old, almTypeEnum.type);
                        desc = desc + (oldcontent ? ":由开到关" : ":由关到开");
                        almResult.setStatus(!oldcontent);
                        almResult.setAlmTypeEnum(TYPE_30);
                        if (oldcontent) {
                            almResult.setPlatformType(TYPE_301);
                        } else {
                            almResult.setPlatformType(TYPE_302);
                        }
                    } else if (almTypeEnum == TYPE_16) {
                        boolean oldcontent = content(old, almTypeEnum.type);
                        desc = desc + (oldcontent ? ":由开到关" : ":由关到开");
                        almResult.setStatus(!oldcontent);
                        almResult.setAlmTypeEnum(TYPE_16);
                        if (oldcontent) {
                            almResult.setPlatformType(TYPE_161);
                        } else {
                            almResult.setPlatformType(TYPE_162);
                        }
                    } else if (almTypeEnum == TYPE_31) {
                        boolean oldcontent = content(old, almTypeEnum.type);
                        desc = desc + (oldcontent ? ":由开到关" : ":由关到开");
                        almResult.setStatus(!oldcontent);
                        almResult.setAlmTypeEnum(TYPE_31);
                        if (oldcontent) {
                            almResult.setPlatformType(TYPE_311);
                        } else {
                            almResult.setPlatformType(TYPE_312);
                        }
                    }
                    almResult.setDesc(desc);
                    almResults.add(almResult);
                }
            }
        }
        return almResults;
    }


    public static boolean content(long alm, int location) {
        String string = new StringBuilder(Long.toBinaryString(alm)).reverse().toString();
      //  System.out.println(string);
        if (string.length() > location) {
            char c = string.charAt(location);
            if (c == '1') {
                return true;
            }
        }
        return false;
    }


}

