package com.huya.berry.gamesdk.utils;

import android.content.Context;
import android.provider.Settings;
import com.sqwan.bugless.util.FileUtil;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DigitUtil {
    private static final int KHOUR = 3600000;
    private static final int KMINUTE = 60000;
    private static final int KSECOND = 1000;
    private static String[] weekDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    private static String[] lastWeekDays = {"上周一", "上周二", "上周三", "上周四", "上周五", "上周六", "上周日"};

    public static String longFormat(long j) {
        String str;
        if (j > 999999999) {
            long j2 = j / 10000000;
            long j3 = j2 % 10;
            Object[] objArr = new Object[2];
            objArr[0] = Long.valueOf(j2 / 10);
            if (j3 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j3);
            } else {
                str = "";
            }
            objArr[1] = str;
            return String.format("%d%s亿", objArr);
        }
        return numFromat(j);
    }

    public static String shortFormat(long j) {
        String str = "";
        if (j > 999999999) {
            long j2 = j / 10000000;
            long j3 = j2 % 10;
            Object[] objArr = new Object[2];
            objArr[0] = Long.valueOf(j2 / 10);
            if (j3 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j3);
            }
            objArr[1] = str;
            return String.format("%d%s亿", objArr);
        }
        if (j > 9999999) {
            long j4 = j / 1000;
            long j5 = j4 % 10;
            Object[] objArr2 = new Object[2];
            objArr2[0] = Long.valueOf(j4 / 10);
            if (j5 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j5);
            }
            objArr2[1] = str;
            return String.format("%d%s万", objArr2);
        }
        return numFromat(j);
    }

    public static String shortFormat1(long j) {
        String str = "";
        if (j > 999999999) {
            long j2 = j / 10000000;
            long j3 = j2 % 10;
            Object[] objArr = new Object[2];
            objArr[0] = Long.valueOf(j2 / 10);
            if (j3 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j3);
            }
            objArr[1] = str;
            return String.format("%d%s亿", objArr);
        }
        if (j > 10000) {
            long j4 = j / 1000;
            long j5 = j4 % 10;
            Object[] objArr2 = new Object[2];
            objArr2[0] = Long.valueOf(j4 / 10);
            if (j5 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j5);
            }
            objArr2[1] = str;
            return String.format("%d%s万", objArr2);
        }
        return String.valueOf(j);
    }

    public static String longFormat(double d) {
        if (d > 9.99999999E8d) {
            return new DecimalFormat("#.0").format(d / 1.0E7d) + "亿";
        }
        return String.valueOf(d);
    }

    public static String shortFormat(double d) {
        if (d > 9.99999999E8d) {
            return new DecimalFormat("#.0").format(d / 1.0E7d) + "亿";
        }
        if (d > 9999999.0d) {
            return new DecimalFormat("#.0").format(d / 1000.0d) + "万";
        }
        return String.valueOf(d);
    }

    public static String speedFormat(float f) {
        return new DecimalFormat("###0.0").format(f);
    }

    public static String dayTimeFormat1(long j) {
        return new SimpleDateFormat("MM月dd日 HH:mm").format(Long.valueOf(j));
    }

    public static String numFromat(long j) {
        return j > 999 ? String.format("%.1fk", Float.valueOf((j * 1.0f) / 1000.0f)) : String.valueOf(j);
    }

    public static String toDataFormat(long j) {
        if (j == 0) {
            return "00:00:00";
        }
        long j2 = j / 3600000;
        long j3 = j - (3600000 * j2);
        long j4 = j3 / 60000;
        long j5 = (j3 - (60000 * j4)) / 1000;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(j2 < 10 ? "0%d" : "%d", Long.valueOf(j2)));
        sb.append(AbstractJsonLexerKt.COLON);
        sb.append(String.format(j4 < 10 ? "0%d" : "%d", Long.valueOf(j4)));
        sb.append(AbstractJsonLexerKt.COLON);
        sb.append(String.format(j5 >= 10 ? "%d" : "0%d", Long.valueOf(j5)));
        return sb.toString();
    }

    public static String USFormat(int i) {
        return new DecimalFormat(",###").format(i);
    }

    private static String getWeekDay(int i, String[] strArr) {
        return (i < 1 || i > strArr.length) ? "" : strArr[i - 1];
    }

    private static int getDayOfWeek(Calendar calendar) {
        int i = calendar.get(7) - 1;
        if (i == 0) {
            return 7;
        }
        return i;
    }

    public static long getlastDayMorning(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        calendar.add(5, -i);
        return calendar.getTimeInMillis();
    }

    public static String getMessageTime(Context context, long j) {
        String str;
        if (j < 0) {
            return "";
        }
        String string = Settings.System.getString(context.getContentResolver(), "time_12_24");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        long jCurrentTimeMillis = System.currentTimeMillis();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(jCurrentTimeMillis);
        calendar.setFirstDayOfWeek(2);
        calendar2.setFirstDayOfWeek(2);
        int dayOfWeek = getDayOfWeek(calendar);
        int dayOfWeek2 = getDayOfWeek(calendar2);
        int i = calendar2.get(3);
        int i2 = calendar.get(3);
        if (getlastDayMorning(7) > j || jCurrentTimeMillis < j) {
            return new SimpleDateFormat("yyyy/MM/dd", Locale.US).format(Long.valueOf(j));
        }
        if (i != i2) {
            return getWeekDay(dayOfWeek, lastWeekDays);
        }
        if (dayOfWeek != dayOfWeek2) {
            return dayOfWeek2 + (-1) == dayOfWeek ? "昨天" : getWeekDay(dayOfWeek, weekDays);
        }
        if (string != null && string.equals("24")) {
            return new SimpleDateFormat("HH:mm", Locale.US).format(Long.valueOf(j));
        }
        int i3 = calendar.get(10);
        if (calendar.get(9) == 0) {
            str = "上午 " + i3 + ":";
        } else {
            str = "下午 " + i3 + ":";
        }
        return str + new SimpleDateFormat("mm", Locale.US).format(Long.valueOf(j));
    }

    public static String intFormat(int i) {
        if (i < 0) {
            return "0";
        }
        if (i < 10000) {
            return String.valueOf(i);
        }
        return i < 100000000 ? String.format("%d万", Integer.valueOf(i / 10000)) : String.format("%d亿", Integer.valueOf(i / 100000000));
    }
}
