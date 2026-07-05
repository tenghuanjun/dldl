package com.duowan.live.common;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TimeDayUtil {
    public static final long MILLIS_IN_DAY = 86400000;
    public static final int SECONDS_IN_DAY = 86400;

    public static boolean isSameDayOfMillis(long j, long j2) {
        long j3 = j - j2;
        return j3 < 86400000 && j3 > -86400000 && toDay(j) == toDay(j2);
    }

    private static long toDay(long j) {
        return (j + ((long) TimeZone.getDefault().getOffset(j))) / 86400000;
    }

    public static boolean isInTime(Date date, Date date2, Date date3) {
        int hours = (date.getHours() * 60) + date.getMinutes();
        int hours2 = (date2.getHours() * 60) + date2.getMinutes();
        int hours3 = (date3.getHours() * 60) + date3.getMinutes();
        if (hours2 > hours3) {
            int hours4 = (24 - date2.getHours()) * 60;
            hours2 = (hours2 + hours4) % 1440;
            hours3 = (hours3 + hours4) % 1440;
            hours = (hours + hours4) % 1440;
        }
        return hours >= hours2 && hours <= hours3;
    }

    public static boolean isSameWeekWithToday(long j, long j2) {
        Date date = new Date(j);
        Date date2 = new Date(j2);
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.setTime(date2);
        return calendar.get(3) == calendar2.get(3);
    }
}
