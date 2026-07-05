package com.huya.mtp.utils;

import com.sqwan.bugless.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TimeUtil {
    public static final SimpleDateFormat DATE_FORMAT_DEFAULT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HMS = new SimpleDateFormat(DateUtil.DEFAULT_DATE_TIME_FORMAT);
    public static final SimpleDateFormat DATE_FORMAT_THIS_YEAR = new SimpleDateFormat("MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_YMD = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE);
    public static final SimpleDateFormat DATE_FORMAT_MS = new SimpleDateFormat("mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_HMS = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_TIME);
    public static final SimpleDateFormat DATE_FORMAT_TIME_DETAIL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat();

    public static String parseTimeCurrentMillis(long j) {
        Date date = new Date();
        date.setTime(j);
        return DATE_FORMAT_HMS.format(date);
    }

    public static String parseTimeWithGMT(long j, SimpleDateFormat simpleDateFormat) {
        long offset = j - ((long) TimeZone.getDefault().getOffset(j));
        Date date = new Date();
        date.setTime(offset);
        return simpleDateFormat.format(date);
    }

    public static String parseTimeHMSWithGMT(long j) {
        return parseTimeWithGMT(j, DATE_FORMAT_HMS);
    }

    public static String parseTimeMSWithGMT(long j) {
        return parseTimeWithGMT(j, DATE_FORMAT_MS);
    }

    public static String parseTimeYMD(long j) {
        return parseTimeWithGMT(j, DATE_FORMAT_YMD);
    }

    public static String parseTimeAsShortAsPossibleWithGMT(long j) {
        if (((j / 60) / 60) / 1000 > 0) {
            return parseTimeHMSWithGMT(j);
        }
        return parseTimeMSWithGMT(j);
    }

    public static long getSeconds(long j) {
        return (j - (toMinutes(j) * 60000)) / 1000;
    }

    private static long toMinutes(long j) {
        return j / 60000;
    }

    public static long toHours(long j) {
        return j / 3600000;
    }

    public static long getMinutes(long j) {
        return (j - (((toHours(j) * 1000) * 60) * 60)) / 60000;
    }

    public static long getHours(long j) {
        return j / 3600000;
    }

    public static String getMSFormatTime(long j) {
        return String.format("%02d:%02d", Long.valueOf(getMinutes(j)), Long.valueOf(getSeconds(j)));
    }

    public static String getPropHMSFormatTime(long j) {
        long hours = getHours(j);
        return hours == 0 ? getMSFormatTime(j) : String.format("%02d:%02d:%02d", Long.valueOf(hours), Long.valueOf(getMinutes(j)), Long.valueOf(getSeconds(j)));
    }

    public static String getFullHMSFormatTime(long j) {
        return String.format("%02d:%02d:%02d", Long.valueOf(getHours(j)), Long.valueOf(getMinutes(j)), Long.valueOf(getSeconds(j)));
    }

    public static String getFormattedTime(String str, long j) {
        CALENDAR.setTimeInMillis(j);
        DATE_FORMAT.applyLocalizedPattern(str);
        return DATE_FORMAT.format(CALENDAR.getTime());
    }

    public static boolean isToyear(long j) {
        CALENDAR.setTimeInMillis(j);
        int i = CALENDAR.get(1);
        CALENDAR.setTimeInMillis(System.currentTimeMillis());
        return i == CALENDAR.get(1);
    }
}
