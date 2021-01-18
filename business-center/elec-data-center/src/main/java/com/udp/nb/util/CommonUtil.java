package com.udp.nb.util;

import java.util.List;

/**
 * commonutil类
 * @author zhouxing
 * @version 1.0
 * @date 2016年9月5日
 */
public class CommonUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isStrEmpty(String str) {
        if (null != str && !str.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean stringIsExists(String subject, String search) {
        if(isStrEmpty(subject) || isStrEmpty(search)) {
            return false;
        }
        if(subject.indexOf(search) != -1) {
            return true;
        }
        return false;
    }
    public  static  int getIndex(int k){
        int length=32;
        return  length-k-1;
    }

    /**
     * 16进制转2进制后获取每位的值(0或1)
     *  空开报警类型：1、漏电报警->4；2、漏电预警->14；3、温度报警->3；4、短路报警->0；5、打火报警->10；
     *  6、三项报警；7、窃电报警
     * @param k
     * @param i 使用0x表示
     * @return
     */
    public  static String binaryValue(int k,String i){
        if(i.length()!=8){
            return "";
        }
        Long hex=Long.parseLong(i,16);
        String binary=Long.toBinaryString(hex);
        if (binary.length() != 32) {
            for (int j = binary.length(); j < 32; j++) {
                binary = "0" + binary;
            }
        }
        return   binary.substring(getIndex(k),getIndex(k)+1);
    }
}
