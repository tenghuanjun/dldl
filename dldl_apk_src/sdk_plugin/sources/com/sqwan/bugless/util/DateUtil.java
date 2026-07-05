package com.sqwan.bugless.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class DateUtil {
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";
    public static final String DEFAULT_FORMAT_TIME = "HH:mm:ss";
    public static final ThreadLocal<SimpleDateFormat> defaultDateTimeFormat = new ThreadLocal<SimpleDateFormat>() { // from class: com.sqwan.bugless.util.DateUtil.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DateUtil.DEFAULT_DATE_TIME_FORMAT);
        }
    };
    public static final ThreadLocal<SimpleDateFormat> defaultDateFormat = new ThreadLocal<SimpleDateFormat>() { // from class: com.sqwan.bugless.util.DateUtil.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE);
        }
    };
    public static final ThreadLocal<SimpleDateFormat> defaultTimeFormat = new ThreadLocal<SimpleDateFormat>() { // from class: com.sqwan.bugless.util.DateUtil.3
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_TIME);
        }
    };

    private DateUtil() {
        throw new RuntimeException("‾ 3‾");
    }

    public static String getDateTimeFromMillis(long timeInMillis) {
        return getDateTimeFormat(new Date(timeInMillis));
    }

    public static String getDateFromMillis(long timeInMillis) {
        return getDateFormat(new Date(timeInMillis));
    }

    public static String getDateTimeFormat(Date date) {
        return dateSimpleFormat(date, defaultDateTimeFormat.get());
    }

    public static String getDateFormat(int year, int month, int day) {
        return getDateFormat(getDate(year, month, day));
    }

    public static String getDateFormat(Date date) {
        return dateSimpleFormat(date, defaultDateFormat.get());
    }

    public static String getTimeFormat(Date date) {
        return dateSimpleFormat(date, defaultTimeFormat.get());
    }

    public static String dateFormat(String sdate, String format) {
        return dateSimpleFormat(java.sql.Date.valueOf(sdate), new SimpleDateFormat(format));
    }

    public static String dateFormat(Date date, String format) {
        return dateSimpleFormat(date, new SimpleDateFormat(format));
    }

    public static String dateSimpleFormat(Date date, SimpleDateFormat format) {
        if (format == null) {
            format = defaultDateTimeFormat.get();
        }
        return date == null ? "" : format.format(date);
    }

    public static Date getDateByDateTimeFormat(String strDate) {
        return getDateByFormat(strDate, defaultDateTimeFormat.get());
    }

    public static Date getDateByDateFormat(String strDate) {
        return getDateByFormat(strDate, defaultDateFormat.get());
    }

    public static Date getDateByFormat(String strDate, String format) {
        return getDateByFormat(strDate, new SimpleDateFormat(format));
    }

    private static Date getDateByFormat(String strDate, SimpleDateFormat format) {
        if (format == null) {
            format = defaultDateTimeFormat.get();
        }
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    public static long getIntervalDays(String strat, String end) {
        return (java.sql.Date.valueOf(end).getTime() - java.sql.Date.valueOf(strat).getTime()) / 86400000;
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(1);
    }

    public static int getCurrentMonth() {
        return Calendar.getInstance().get(2) + 1;
    }

    public static int getDayOfMonth() {
        return Calendar.getInstance().get(5);
    }

    public static String getToday() {
        return getDateFormat(Calendar.getInstance().getTime());
    }

    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -1);
        return getDateFormat(calendar.getTime());
    }

    public static String getBeforeYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -2);
        return getDateFormat(calendar.getTime());
    }

    public static String getOtherDay(int diff) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, diff);
        return getDateFormat(calendar.getTime());
    }

    public static String getCalcDateFormat(String sDate, int amount) {
        return getDateFormat(getCalcDate(getDateByDateFormat(sDate), amount));
    }

    public static Date getCalcDate(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, amount);
        return calendar.getTime();
    }

    public static Date getCalcTime(Date date, int hOffset, int mOffset, int sOffset) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(11, hOffset);
        calendar.add(12, mOffset);
        calendar.add(13, sOffset);
        return calendar.getTime();
    }

    public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hourOfDay, minute, second);
        return calendar.getTime();
    }

    public static int[] getYearMonthAndDayFrom(String sDate) {
        return getYearMonthAndDayFromDate(getDateByDateFormat(sDate));
    }

    public static int[] getYearMonthAndDayFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new int[]{calendar.get(1), calendar.get(2), calendar.get(5)};
    }
}
