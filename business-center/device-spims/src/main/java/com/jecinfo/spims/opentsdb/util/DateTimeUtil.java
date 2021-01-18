package com.jecinfo.spims.opentsdb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static Date parse(String date,String fm){
        Date res=null;
        try {
            SimpleDateFormat sft=new SimpleDateFormat(fm);
            res=sft.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

}