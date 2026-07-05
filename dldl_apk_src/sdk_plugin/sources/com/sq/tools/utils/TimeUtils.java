package com.sq.tools.utils;

import android.os.SystemClock;
import com.sqwan.bugless.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class TimeUtils {
    private static long baseClientTime;
    private static long baseServerTime;
    private static volatile boolean isFetched;

    public static void fetchServerTime(String str) {
    }

    private static Long getLongSystemElapsedTime() {
        return Long.valueOf(SystemClock.elapsedRealtime() / 1000);
    }

    private static String getSystemElapsedTime() {
        return Long.toString(getLongSystemElapsedTime().longValue());
    }

    public static String getServerTimeInSeconds() {
        return Long.toString(getLongServerTimeInSeconds());
    }

    public static long getLongServerTimeInSeconds() {
        return isFetched ? (baseServerTime + getLongSystemElapsedTime().longValue()) - baseClientTime : Calendar.getInstance().getTimeInMillis() / 1000;
    }

    public static String getServerTimeInMills() {
        return Long.toString(getLongServerTimeInMills());
    }

    public static long getLongServerTimeInMills() {
        return isFetched ? ((baseServerTime + getLongSystemElapsedTime().longValue()) - baseClientTime) * 1000 : Calendar.getInstance().getTimeInMillis();
    }

    public static long getStartDayTime(long j) {
        return getSpecificHourTime(j, 0);
    }

    public static long getEndDayTime(long j) {
        return getStartDayTime(j) + 86400;
    }

    public static long getSpecificHourTime(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestampInMills(j));
        calendar.set(11, i);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    public static int getHour(long j) {
        return Integer.parseInt(format(j, "HH"));
    }

    public static long getMonthEndTime(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestampInMills(j));
        calendar.set(calendar.get(1), calendar.get(2), calendar.getActualMaximum(5), 24, 0, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    public static String format(long j) {
        return format(j, DateUtil.DEFAULT_DATE_TIME_FORMAT);
    }

    public static String format(long j, String str) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(timestampInMills(j)));
    }

    private static long timestampInMills(long j) {
        return String.valueOf(j).length() < 13 ? j * ((long) Math.pow(10.0d, 13 - r0)) : j;
    }
}
