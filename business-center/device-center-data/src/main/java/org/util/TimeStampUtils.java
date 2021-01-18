package org.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间戳工具类
 * @author zx
 * @Date 2018年2月26日 下午5:07:32
 * @Class TimeStamp.java
 */
public class TimeStampUtils {

    /**
     * （int）时间戳转Date
     * @author zx
     * @date 2018年2月26日 下午5:10:40
     * @param timestamp
     * @return
     */
    public static Date stampForDate(Integer timestamp){
        return new Date((long) timestamp*1000);
    }

    /**
     * （long）时间戳转Date
     * @author zx
     * @date 2018年2月26日 下午5:16:46
     * @param timestamp
     * @return
     */
    public static Date longStampForDate(long timestamp){
        return new Date(timestamp);
    }

    /**
     * date转String
     * @author zx
     * @date 2018年2月26日 下午5:11:51
     * @param date
     * @return
     */
    public static String dateForString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间的格式
        return sdf.format(date);
    }

    /**
     * String转Date
     * @author zx
     * @date 2018年2月26日 下午5:14:36
     * @param time
     * @return
     */
    public static Date stringForDate(String time){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间的格式
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date转时间戳
     * @author zx
     * @date 2018年2月26日 下午5:14:54
     * @param data
     * @return
     */
    public static long dateForStamp(Date data){
        return data.getTime();
    }

    /**
     * Date转时间戳
     * @author guj
     * @date 2019年8月30日 下午15:19:54
     * @param time
     * @return
     */
    public static long dateToTimestamp(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(time);
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.HOUR_OF_DAY, 8);
            return ca.getTimeInMillis();
        } catch (ParseException e) {
            return 0;
        }
    }


}
