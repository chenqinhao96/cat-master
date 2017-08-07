package com.chenqinhao.cat.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenqinhao on 2017/7/15.
 */
public class TimeUtils {

    private final static long minute = 60 * 1000;
    private final static long hour  = 60 * minute;
    private final static long day = 24 * hour;
    private final static long month = 31 * day;
    private final static long year = 12 * month;


    public static final SimpleDateFormat DATE_FORMAT_DATE_D = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE_M = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_DATE_S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private TimeUtils() {
        throw new AssertionError();
    }

    public static final String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_DATE_S);
    }

    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r =  (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

}
