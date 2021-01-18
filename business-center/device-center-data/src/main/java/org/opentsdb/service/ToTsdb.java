package org.opentsdb.service;


import com.tianbo.security.IotCipher;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.opentsdb.builder.MetricBuilder;
import org.opentsdb.model.MyData;

import java.util.*;

public class ToTsdb {


    private static final String INDEX = "index";

    public static void toOpenTsdb(byte[] msg, MetricBuilder builder,String topicName){
        String end = "3346383431334235334145363032414245374546303635364441363444324538";
        if (end.equals(ByteUtils.toHexString(msg))){
            System.out.println("结束字符");
            return;
        }
        byte[] password = new byte[]{0x2b, 0x7e, 0x15, 0x16, 0x28, (byte) 0xae, (byte)0xd2, (byte)0xa6, (byte)0xab, (byte)0xf7, 0x15, (byte)0x88, 0x09, (byte)0xcf, 0x4f, 0x3c};
        byte[] bytes2 = IotCipher.decodeData(msg, password);
        String string = ByteUtils.toHexString(bytes2);
        //数据长度
        String len = string.substring(32, 36);
        int lens = Integer.parseInt(reverseHex(len), 16);
        String string1 = string.substring(0, lens);
        //设备编号
        String substring = string1.substring(0, 24);
        String host = convertHexToString(substring);
        //时间戳
        String substring1 = string1.substring(24, 32);
        //时间间隔
        String substrin = string1.substring(36, 44);
        long interval = Integer.parseInt(reverseHex(substrin), 16);
        //电池电压
        String substring2 = string1.substring(44, 46);
        //信号强度
        String substring3 = string1.substring(46, 48);
        //温度
        String substring4 = string1.substring(48, 50);
        //湿度
        String substring5 = string1.substring(50, 52);
        //类型
        String substring6 = string1.substring(52, 54);
        //数据
        String substring7 = string1.substring(54);
        List<Integer> list = new ArrayList<>();
        double ceil = Math.ceil(substring7.length() / 4.0);
        for (int index = 0; index < ceil ; index++) {
            String childStr = substring(substring7, index * 4,
                    (index + 1) * 4);
            Integer data = Integer.parseInt(childStr, 16);
            list.add(data);
        }
        String s = reverseHex(substring1);
        long timestap = Integer.parseInt(s,16) - 28800;
        HashMap<String, Double> map = new HashMap<>();
        Double vol = Integer.parseInt(substring2,16) * 0.1;
        Integer rssi = Integer.parseInt(substring3,16);
        Double at = Integer.parseInt(substring4,16) * 0.5 - 40;
        Integer ah = Integer.parseInt(substring5,16);
        Integer type = Integer.parseInt(substring6,16);
        map.put("vol",vol);
        map.put("rssi",rssi.doubleValue());
        map.put("at",at);
        map.put("ah",ah.doubleValue());
        map.put("type",type.doubleValue());

        MyData myData = new MyData();
        myData.setHost(host);
        myData.setInterval(interval);
        myData.setAh(ah.doubleValue());
        myData.setAt(ah.doubleValue());
        myData.setRssi(rssi.doubleValue());
        myData.setType(type.doubleValue());
        myData.setVol(vol);
        myData.setTimestap(timestap);
        myData.setList(list);
        myData.setMap(map);
        System.out.println(myData.toString());
        addMetrics(builder, "mete.", timestap, topicName, map,list,interval);
    }

    private static void addMetrics(MetricBuilder builder, String metric_name, long timestap, String host, Map<String, Double> map, List<Integer> list,long interval) {
        if (timestap==0){
            timestap = Calendar.getInstance().getTimeInMillis();
        }
        Iterator<Map.Entry<String, Double>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            builder.addMetric(metric_name + "rtu").setDataPoint(timestap, entry.getValue())
                    .addTag("host", host).addTag(INDEX, entry.getKey());
        }
        for (Integer value : list) {
                builder.addMetric(metric_name + "rtu").setDataPoint(timestap, value)
                        .addTag("host", host).addTag(INDEX, "rtu");
            timestap=timestap+interval;
        }
    }



    public static String reverseHex(String hex) {
        char[] charArray = hex.toCharArray();
        int length = charArray.length;
        int times = length / 2;
        for (int c1i = 0; c1i < times; c1i += 2) {
            int c2i = c1i + 1;
            char c1 = charArray[c1i];
            char c2 = charArray[c2i];
            int c3i = length - c1i - 2;
            int c4i = length - c1i - 1;
            charArray[c1i] = charArray[c3i];
            charArray[c2i] = charArray[c4i];
            charArray[c3i] = c1;
            charArray[c4i] = c2;
        }
        return new String(charArray);
    }
    public static byte[] hexStringToString(String hexstring) {

        byte[] destByte = new byte[hexstring.length()/2];
        int j=0;
        for(int i=0;i<destByte.length;i++) {
            byte high = (byte) (Character.digit(hexstring.charAt(j), 16) & 0xff);
            byte low = (byte) (Character.digit(hexstring.charAt(j + 1), 16) & 0xff);
            destByte[i] = (byte) (high << 4 | low);
            j+=2;
        }
        return destByte;
    }

    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

//    public static void main(String[] args) {
//
//        String str = "7334ABDA3F3E1A8A038F62A051ACD4939CDE7C065A0BA77F8B0D72ABEFF22899";
//        byte[] msg = hexStringToString(str);
//        byte[] password = new byte[]{0x2b, 0x7e, 0x15, 0x16, 0x28, (byte) 0xae, (byte)0xd2, (byte)0xa6, (byte)0xab, (byte)0xf7, 0x15, (byte)0x88, 0x09, (byte)0xcf, 0x4f, 0x3c};
//        byte[] bytes2 = IotCipher.decodeData(msg, password);
//        String string = ByteUtils.toHexString(bytes2);
//        System.out.println(string);
//        String len = string.substring(32, 36);
//        int lens = Integer.parseInt(reverseHex(len), 16);
//        String string1 = string.substring(0, lens);
//        //设备编号
//        String substring = string1.substring(0, 24);
//        String host = convertHexToString(substring);
//        //时间戳
//        String substring1 = string1.substring(24, 32);
//        System.out.println(substring1);
//        //时间间隔
//        String substrin = string1.substring(36, 44);
//        long interval = Integer.parseInt(reverseHex(substrin), 16);
//        //电池电压
//        String substring2 = string1.substring(44, 46);
//        //信号强度
//        String substring3 = string1.substring(46, 48);
//        //温度
//        String substring4 = string1.substring(48, 50);
//        //湿度
//        String substring5 = string1.substring(50, 52);
//        //类型
//        String substring6 = string1.substring(52, 54);
//        //数据
//        String substring7 = string1.substring(54);
//        List<Integer> list = new ArrayList<>();
//        double ceil = Math.ceil(substring7.length() / 4.0);
//        for (int index = 0; index < ceil ; index++) {
//            String childStr = substring(substring7, index * 4,
//                    (index + 1) * 4);
//            Integer data = Integer.parseInt(childStr, 16);
//            list.add(data);
//        }
//        String s = reverseHex(substring1);
//        System.out.println(s);
//        long timestap = Integer.parseInt(s,16) - 28800;
//        HashMap<String, Double> map = new HashMap<>();
//        Double vol = Integer.parseInt(substring2,16) * 0.1;
//        Integer rssi = Integer.parseInt(substring3,16);
//        Double at = Integer.parseInt(substring4,16) * 0.5 - 40;
//        Integer ah = Integer.parseInt(substring5,16);
//        Integer type = Integer.parseInt(substring6,16);
//
//        System.out.println(host);
//        System.out.println(lens);
//        System.out.println(timestap);
//        System.out.println(interval);
//        System.out.println(vol);
//        System.out.println(rssi);
//        System.out.println(at);
//        System.out.println(ah);
//        System.out.println(type);
//        System.out.println("---------------");
//        list.forEach(System.out::println);
//        System.out.println("---------------");
//        for (Map.Entry<String, Double> entry : map.entrySet()) {
//            System.out.println(entry.getValue());
//        }
//        System.out.println("---------------");
//        MyData myData = new MyData();
//        myData.setHost(host);
//        myData.setInterval(interval);
//        myData.setAh(ah.doubleValue());
//        myData.setAt(ah.doubleValue());
//        myData.setRssi(rssi.doubleValue());
//        myData.setType(type.doubleValue());
//        myData.setVol(vol);
//        myData.setTimestap(timestap);
//        myData.setList(list);
//        myData.setMap(map);
//        System.out.println(myData.toString());
//    }


    public static String substring(String str, int f, int t) {
        if (f > str.length()) {
            return null;
        }
        if (t > str.length()) {
            return str.substring(f);
        } else {
            return str.substring(f, t);
        }
    }
}

