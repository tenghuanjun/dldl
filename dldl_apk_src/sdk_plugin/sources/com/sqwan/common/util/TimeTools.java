package com.sqwan.common.util;

import android.text.format.Time;
import com.sqwan.bugless.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class TimeTools {
    public static boolean isCurrentInTimeScope(long j, int i, int i2, int i3, int i4) {
        StringBuilder sb = new StringBuilder();
        sb.append("isCurrentInTimeScope currentTimeMillis = ");
        sb.append(j);
        sb.append(" 时间 = ");
        sb.append(stampToDate(j + ""));
        LogUtil.i(sb.toString());
        LogUtil.i("isCurrentInTimeScope beginHour = " + i + " beginMin = " + i2 + " endHour= " + i3 + " endMin= " + i4);
        Time time = new Time();
        time.set(j);
        Time time2 = new Time();
        time2.set(j);
        time2.hour = i;
        time2.minute = i2;
        boolean z = false;
        time2.second = 0;
        Time time3 = new Time();
        time3.set(j);
        time3.hour = i3;
        time3.minute = i4;
        time3.second = 0;
        if (!time2.before(time3)) {
            time2.set(time2.toMillis(true) - 86400000);
            if (!time.before(time2) && !time.after(time3)) {
                z = true;
            }
            Time time4 = new Time();
            time4.set(time2.toMillis(true) + 86400000);
            if (!time.before(time4)) {
                return true;
            }
        } else if (!time.before(time2) && !time.after(time3)) {
            z = true;
        }
        return z;
    }

    public static String stampToDate(String str) {
        return new SimpleDateFormat(DateUtil.DEFAULT_DATE_TIME_FORMAT).format(new Date(Long.valueOf(str).longValue()));
    }
}
