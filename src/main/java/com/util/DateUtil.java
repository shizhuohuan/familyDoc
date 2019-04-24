package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
    public static String Date2String(Date date){
        return sdf.format(date);
    }
    public static String NowDate2String(){
        return sdf.format(new Date());
    }
}
