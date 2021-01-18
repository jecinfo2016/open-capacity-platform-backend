package com.open.capacity.uaa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.*;

public class SignUtil {

    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /**
     * 字符编码
     */
    private final static String INPUT_CHARSET = "UTF-8";
    /**
     * 超时时间
     */
    private final static int TIME_OUT = 30 * 60 * 1000;
    /**
     * 随机字符串 8位
     */
    private final static String noncestr = UUID.randomUUID().toString().substring(0, UUID.randomUUID().toString().indexOf("-"));

    public static String getSignature(String... args) {
        return getSignature(noncestr, args);
    }

    /**
     * 签名算法
     */
    private static String getSignature(String key, String... args) {
        try {
            args = Arrays.copyOf(args, args.length + 1);
            args[args.length - 1] = key;
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(args);
            for (int i = 0; i < args.length; i++) {
                sb.append(args[i]);
            }
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(sb.toString().getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            logger.info("参数错误");
            throw new IllegalArgumentException("参数错误");
        }
    }

    /**
     * 验证签名
     */
    public static boolean verify(Map<String, String> params) {

        String sign = "";
        if (params.get("sign") != null) {
            sign = params.get("sign");
        } else {
            logger.info("sign is null");
            return false;
        }
        String timestamp = "";
        if (params.get("timestamp") != null) {
            timestamp = params.get("timestamp");
        } else {
            return false;
        }

        long curr = System.currentTimeMillis();
        if ((curr - Long.valueOf(timestamp)) > TIME_OUT) {
            logger.info("签名超时");
            return false;
        }
        String appId = params.get("appId");
        String appSecret = params.get("appSecret");
        String mysign = getSignature(noncestr, new String[]{appId, timestamp, appSecret});
        if (!checkpw(mysign,sign)) {
            logger.info("签名错误");
            return false;
        }
        return true;
    }

    public static boolean checkpw(String a,String b){
        char[] caa = a.toCharArray();
        char[] cab = b.toCharArray();

        if (caa.length != cab.length) {
            return false;
        }

        byte ret = 0;
        for (int i = 0; i < caa.length; i++) {
            ret |= caa[i] ^ cab[i];
        }
        return ret == 0;
    }

    public static void main(String[] args) {
//        String appId = "mobile";
//        String timestamp = String.valueOf(new Date().getTime());
//        System.out.println(timestamp);
//        String appSecret = "$2a$10$ULxRssv/4NWOc388lZFbyus3IFfsbcpG/BZOq4TRxDhsx5HHIR7Jm";
//        String sign = getSignature(noncestr, new String[]{appId, timestamp, appSecret});
//        System.out.println(sign);
//        Map<String, String> params = new HashMap<>();
//        params.put("appId", appId);
//        params.put("timestamp", timestamp);
//        params.put("appSecret", appSecret);
//        params.put("sign", sign);
//        System.out.println("验证签名：" + verify(params));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        Date date = cal.getTime();
        System.out.println(date);

    }
}
