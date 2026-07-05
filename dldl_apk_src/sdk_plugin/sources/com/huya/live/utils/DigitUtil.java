package com.huya.live.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.sqwan.bugless.util.DateUtil;
import com.sqwan.bugless.util.FileUtil;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DigitUtil {
    private static final long MAX_MAX_NUM = 999999999999L;
    private static final long MAX_NUM = 999999999;
    private static final long MAX_NUM2 = 99999999;
    private static final long MID_NUM = 9999999;
    private static final long MIN_NUM = 9999;
    private static String[] weekDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    private static String[] lastWeekDays = {"上周一", "上周二", "上周三", "上周四", "上周五", "上周六", "上周日"};

    public static int range(int i, int i2, int i3) {
        return i2 < i ? i : i2 > i3 ? i3 : i2;
    }

    public static String longFormat(long j) {
        String str;
        if (j > MAX_NUM) {
            long j2 = j / 10000000;
            long j3 = j2 % 10;
            Locale locale = Locale.CHINA;
            Object[] objArr = new Object[2];
            objArr[0] = Long.valueOf(j2 / 10);
            if (j3 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j3);
            } else {
                str = "";
            }
            objArr[1] = str;
            return String.format(locale, "%d%s亿", objArr);
        }
        return numFromat(j);
    }

    public static String shortFormat2(long j) {
        String str = "";
        if (j > MAX_MAX_NUM) {
            long j2 = j / 100000000000L;
            long j3 = j2 % 10;
            Locale locale = Locale.CHINA;
            Object[] objArr = new Object[2];
            objArr[0] = Long.valueOf(j2 / 10);
            if (j3 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j3);
            }
            objArr[1] = str;
            return String.format(locale, "%d%s万亿", objArr);
        }
        if (j > MAX_NUM2) {
            long j4 = j / 10000000;
            long j5 = j4 % 10;
            Locale locale2 = Locale.CHINA;
            Object[] objArr2 = new Object[2];
            objArr2[0] = Long.valueOf(j4 / 10);
            if (j5 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j5);
            }
            objArr2[1] = str;
            return String.format(locale2, "%d%s亿", objArr2);
        }
        if (j > MIN_NUM) {
            long j6 = j / 1000;
            long j7 = j6 % 10;
            Locale locale3 = Locale.CHINA;
            Object[] objArr3 = new Object[2];
            objArr3[0] = Long.valueOf(j6 / 10);
            if (j7 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j7);
            }
            objArr3[1] = str;
            return String.format(locale3, "%d%s万", objArr3);
        }
        return numFromat(j);
    }

    public static String shortFormat(long j) {
        String str = "";
        if (j > MAX_NUM) {
            long j2 = j / 10000000;
            long j3 = j2 % 10;
            Locale locale = Locale.CHINA;
            Object[] objArr = new Object[2];
            objArr[0] = Long.valueOf(j2 / 10);
            if (j3 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j3);
            }
            objArr[1] = str;
            return String.format(locale, "%d%s亿", objArr);
        }
        if (j > MID_NUM) {
            long j4 = j / 1000;
            long j5 = j4 % 10;
            Locale locale2 = Locale.CHINA;
            Object[] objArr2 = new Object[2];
            objArr2[0] = Long.valueOf(j4 / 10);
            if (j5 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j5);
            }
            objArr2[1] = str;
            return String.format(locale2, "%d%s万", objArr2);
        }
        return numFromat(j);
    }

    public static String formatToW(long j) {
        String str;
        if (j < 10000) {
            return String.valueOf(j);
        }
        long j2 = j / 1000;
        long j3 = j2 % 10;
        Locale locale = Locale.CHINA;
        Object[] objArr = new Object[2];
        objArr[0] = Long.valueOf(j2 / 10);
        if (j3 > 0) {
            str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j3);
        } else {
            str = "";
        }
        objArr[1] = str;
        return String.format(locale, "%d%sw", objArr);
    }

    public static String shortFormat1(long j) {
        String str = "";
        if (j > MAX_NUM) {
            long j2 = j / 10000000;
            long j3 = j2 % 10;
            Locale locale = Locale.CHINA;
            Object[] objArr = new Object[2];
            objArr[0] = Long.valueOf(j2 / 10);
            if (j3 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j3);
            }
            objArr[1] = str;
            return String.format(locale, "%d%s亿", objArr);
        }
        if (j > 10000) {
            long j4 = j / 1000;
            long j5 = j4 % 10;
            Locale locale2 = Locale.CHINA;
            Object[] objArr2 = new Object[2];
            objArr2[0] = Long.valueOf(j4 / 10);
            if (j5 > 0) {
                str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(j5);
            }
            objArr2[1] = str;
            return String.format(locale2, "%d%s万", objArr2);
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
        if (d == 0.0d) {
            return "0";
        }
        if (d > 9.99999999E8d) {
            return new DecimalFormat("#.0").format(d / 1.0E7d) + "亿";
        }
        if (d > 9999999.0d) {
            DecimalFormat decimalFormat = new DecimalFormat("#.0");
            decimalFormat.setRoundingMode(RoundingMode.FLOOR);
            return decimalFormat.format(d / 10000.0d) + "万";
        }
        return String.valueOf(d);
    }

    public static String speedFormat(float f) {
        return new DecimalFormat("###0.0").format(f);
    }

    public static String dayTimeFormat1(long j) {
        return new SimpleDateFormat("MM月dd日 HH:mm").format(Long.valueOf(j));
    }

    public static String dayTimeFormat2(long j) {
        return new SimpleDateFormat("(MM月dd日)").format(Long.valueOf(j));
    }

    public static String yearMDHM(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(Long.valueOf(j * 1000));
    }

    public static String yearMD(long j) {
        return new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE, Locale.CHINA).format(Long.valueOf(j * 1000));
    }

    public static long getTime(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).parse(str).getTime() / 1000;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return 0L;
        }
    }

    public static String numFromat(long j) {
        if (j > 999999) {
            return new DecimalFormat("###,###,###").format(j);
        }
        if (j > 999) {
            return new DecimalFormat("###,###").format(j);
        }
        return String.valueOf(j);
    }

    public static String USFormat(int i) {
        return new DecimalFormat(",###").format(i);
    }

    public static String USFormat(long j) {
        return new DecimalFormat(",###").format(j);
    }

    public static long getLastDayMorning(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static String formatLongToCommaStr(long j) {
        if (j < 1000) {
            return String.valueOf(j);
        }
        return new DecimalFormat("###,###,###,###").format(j);
    }

    public static ArrayList<String> findCommaNumberString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = Pattern.compile("((\\d{0,3},\\d{0,3},\\d{3})|(\\d{0,3},\\d{3})|(\\d{3,4}))").matcher(str);
        ArrayList<String> arrayList = new ArrayList<>();
        while (matcher.find()) {
            arrayList.add(matcher.group());
        }
        return arrayList;
    }

    public static String yearMDHMssSSS(long j) {
        return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss.SSS", Locale.CHINA).format(Long.valueOf(j));
    }

    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    public static int parseInt(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public static long parseLong(String str) {
        return parseLong(str, 0L);
    }

    public static long parseLong(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    public static float parseFloat(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public static double parseDouble(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
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
}
