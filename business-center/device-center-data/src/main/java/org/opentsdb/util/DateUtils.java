package org.opentsdb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类
 * @author xiaoleilu
 */
public class DateUtils {
    private static Logger log = LoggerFactory.getLogger(DateUtils.class);

    /** 毫秒 */
    public final static long MS = 1;
    /** 每秒钟的毫秒数 */
    public final static long SECOND_MS = MS * 1000;
    /** 每分钟的毫秒数 */
    public final static long MINUTE_MS = SECOND_MS * 60;
    /** 每小时的毫秒数 */
    public final static long HOUR_MS = MINUTE_MS * 60;
    /** 每天的毫秒数 */
    public final static long DAY_MS = HOUR_MS * 24;

    /** 标准日期格式 */
    public final static String NORM_DATE_PATTERN = "yyyy-MM-dd";
    /** 标准时间格式 */
    public final static String NORM_TIME_PATTERN = "HH:mm:ss";
    /** 标准日期时间格式 */
    public final static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** HTTP头中日期时间格式 */
    public final static String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

    public final static  String DATE_KEY_STR = "HHmmddMMyy";

    /** 标准日期（不含时间）格式化器 */
    private final static SimpleDateFormat NORM_DATE_FORMAT = new SimpleDateFormat(NORM_DATE_PATTERN);
    /** 标准时间格式化器 */
    private final static SimpleDateFormat NORM_TIME_FORMAT = new SimpleDateFormat(NORM_TIME_PATTERN);
    /** 标准日期时间格式化器 */
    private final static SimpleDateFormat NORM_DATETIME_FORMAT = new SimpleDateFormat(NORM_DATETIME_PATTERN);
    /** HTTP日期时间格式化器 */
    private final static SimpleDateFormat HTTP_DATETIME_FORMAT = new SimpleDateFormat(HTTP_DATETIME_PATTERN, Locale.US);




    /**
     * 将指定的日期转换成Unix时间戳
     * @param  date 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_KEY_STR).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 获得当前时间10分钟的整数时间点
     * @return
     */
    public static long getFiveMinutePoint(){
        Calendar rightNow = Calendar.getInstance();
        int minute = rightNow.get(Calendar.MINUTE);

        minute = Math.round(minute/5*5);//计算10的整数分钟

        rightNow.set(Calendar.MINUTE, minute);
        rightNow.set(Calendar.SECOND, 0);
        long time =rightNow.getTimeInMillis();
        return time;
    }

    public static String getStrFiveMinutePoint(){
        Calendar rightNow = Calendar.getInstance();
        int minute = rightNow.get(Calendar.MINUTE);

        minute = Math.round(minute/5*5);//计算10的整数分钟

        rightNow.set(Calendar.MINUTE, minute);
        rightNow.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(rightNow.getTime());


    }

    /**
     * 将指定的日期转换成Unix时间戳
     * @param  date 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long timeToUnixTimestamp(String date) {
        long timestamp = 0;
        int hour = 0;
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        try {
            Date d = new Date();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(NORM_DATE_PATTERN);
            String dateNowStr = sdf.format(d);

            d = new SimpleDateFormat(NORM_DATETIME_PATTERN).parse(dateNowStr+" "+date);
            // 对 calendar 设置为 date 所定的日期
            calendar.setTime(d);
            hour = calendar.get(Calendar.HOUR_OF_DAY)+8;
            if(hour>23)
                hour = hour - 24;
            calendar.set(Calendar.HOUR_OF_DAY,hour);
            timestamp = calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 当前时间，格式 yyyy-MM-dd HH:mm:ss
     * @return 当前时间的标准形式字符串
     */
    public static String now() {
        return formatDateTime(new Date());
    }

    /**
     * 当前日期，格式 yyyy-MM-dd
     * @return 当前日期的标准形式字符串
     */
    public static String today() {
        return formatDate(new Date());
    }

    // ------------------------------------ Format start ----------------------------------------------
    /**
     * 根据特定格式格式化日期
     * @param date 被格式化的日期
     * @param format 格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     * @param date 被格式化的日期
     * @return 格式化后的日期
     */
    public static String formatDateTime(Date date) {
        return NORM_DATETIME_FORMAT.format(date);
    }

    /**
     * 格式化为Http的标准日期格式
     * @param date 被格式化的日期
     * @return HTTP标准形式日期字符串
     */
    public static String formatHttpDate(Date date) {
        return HTTP_DATETIME_FORMAT.format(date);
    }

    /**
     * 格式 yyyy-MM-dd
     * @param date 被格式化的日期
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date) {
        return NORM_DATE_FORMAT.format(date);
    }
    // ------------------------------------ Format end ----------------------------------------------

    // ------------------------------------ Parse start ----------------------------------------------
    /**
     * 将特定格式的日期转换为Date对象
     * @param dateString 特定格式的日期
     * @param format 格式，例如yyyy-MM-dd
     * @return 日期对象
     */
    public static Date parse(String dateString, String format){
        try {
            return (new SimpleDateFormat(format)).parse(dateString);
        } catch (ParseException e) {
            log.error("Parse " + dateString + " with format " + format + " error!", e);
        }
        return null;
    }

    /**
     * 格式yyyy-MM-dd HH:mm:ss
     * @param dateString 标准形式的时间字符串
     * @return 日期对象
     */
    public static Date parseDateTime(String dateString){
        try {
            return NORM_DATETIME_FORMAT.parse(dateString);
        } catch (ParseException e) {
            log.error("Parse " + dateString + " with format " + NORM_DATETIME_FORMAT.toPattern() + " error!", e);
        }
        return null;
    }

    /**
     * 格式yyyy-MM-dd
     * @param dateString 标准形式的日期字符串
     * @return 日期对象
     */
    public static Date parseDate(String dateString){
        try {
            return NORM_DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            log.error("Parse " + dateString + " with format " + NORM_DATE_PATTERN + " error!", e);
        }
        return null;
    }

    /**
     * 格式HH:mm:ss
     * @return 日期对象
     */
    public static Date parseTime(String timeString){
        try {
            return NORM_TIME_FORMAT.parse(timeString);
        } catch (ParseException e) {
            log.error("Parse " + timeString + " with format " + NORM_TIME_PATTERN + " error!", e);
        }
        return null;
    }

    /**
     * 格式：<br>
     * 1、yyyy-MM-dd HH:mm:ss<br>
     * 2、yyyy-MM-dd<br>
     * 3、HH:mm:ss>
     * @param dateStr 日期字符串
     * @return 日期
     */
    public static Date parse(String dateStr) {
        int length = dateStr.length();
        try {
            if(length == DateUtils.NORM_DATETIME_PATTERN.length()) {
                return parseDateTime(dateStr);
            }else if(length == DateUtils.NORM_DATE_PATTERN.length()) {
                return parseDate(dateStr);
            }else if(length == DateUtils.NORM_TIME_PATTERN.length()){
                return parseTime(dateStr);
            }
        }catch(Exception e) {
            log.error("Parse " + dateStr + " with format normal error!", e);
        }
        return null;
    }
    // ------------------------------------ Parse end ----------------------------------------------

    // ------------------------------------ Offset start ----------------------------------------------

    /**
     * 昨天
     * @return 昨天
     */
    public static Date yesterday() {
        return offsiteDate(new Date(), Calendar.DAY_OF_YEAR, -1);
    }

    /**
     * 上周
     * @return 上周
     */
    public static Date lastWeek() {
        return offsiteDate(new Date(), Calendar.WEEK_OF_YEAR, -1);
    }

    /**
     * 上个月
     * @return 上个月
     */
    public static Date lastMouth() {
        return offsiteDate(new Date(), Calendar.MONTH, -1);
    }

    /**
     * 获取指定日期偏移指定时间后的时间
     * @param date 基准日期
     * @param calendarField 偏移的粒度大小（小时、天、月等）使用Calendar中的常数
     * @param offsite 偏移量，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     */
    public static Date offsiteDate(Date date, int calendarField, int offsite){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendarField, offsite);
        return cal.getTime();
    }
    // ------------------------------------ Offset end ----------------------------------------------

    /**
     * 判断两个日期相差的时长<br/>
     * 返回 minuend - subtrahend 的差
     * @param subtrahend 减数日期
     * @param minuend 被减数日期
     * @param diffField 相差的选项：相差的天、小时
     * @return 日期差
     */
    public static long diff(Date subtrahend, Date minuend, long diffField){
        long diff = minuend.getTime() - subtrahend.getTime();
        return diff/diffField;
    }

    /**
     * 计时，常用于记录某段代码的执行时间，单位：纳秒
     * @param preTime 之前记录的时间
     * @return 时间差，纳秒
     */
    public static long spendNt(long preTime) {
        return System.nanoTime() - preTime;
    }

    /**
     * 计时，常用于记录某段代码的执行时间，单位：毫秒
     * @param preTime 之前记录的时间
     * @return 时间差，毫秒
     */

}