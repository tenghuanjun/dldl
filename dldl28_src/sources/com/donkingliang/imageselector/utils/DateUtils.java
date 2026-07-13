package com.donkingliang.imageselector.utils;

import android.content.Context;
import com.donkingliang.imageselector.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public class DateUtils {
    public static String getImageTime(Context context, long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j);
        if (sameDay(calendar, calendar2)) {
            return context.getString(R.string.selector_this_today);
        }
        if (sameWeek(calendar, calendar2)) {
            return context.getString(R.string.selector_this_week);
        }
        if (sameMonth(calendar, calendar2)) {
            return context.getString(R.string.selector_this_month);
        }
        return new SimpleDateFormat("yyyy/MM").format(new Date(j));
    }

    public static boolean sameDay(Calendar calendar, Calendar calendar2) {
        return calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static boolean sameWeek(Calendar calendar, Calendar calendar2) {
        return calendar.get(1) == calendar2.get(1) && calendar.get(3) == calendar2.get(3);
    }

    public static boolean sameMonth(Calendar calendar, Calendar calendar2) {
        return calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2);
    }
}
