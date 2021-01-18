package com.open.device.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String formt(String s) throws ParseException {
        //日期转换
        SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (s!=null&&!s.equals("")){
            //CreateTime日期转换
             s = sdf2.format(sdf1.parse(s));
        }else {
            Date date = new Date();
             s = sdf2.format(date);
            return s;
        }
        return s;
    }
}
