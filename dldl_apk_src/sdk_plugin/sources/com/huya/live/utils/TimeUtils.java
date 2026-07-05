package com.huya.live.utils;

import com.sqwan.bugless.util.DateUtil;
import com.sqwan.bugless.util.FileUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TimeUtils {
    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat(DateUtil.DEFAULT_DATE_TIME_FORMAT, Locale.getDefault());
    private static final int KHOUR = 3600000;
    private static final int KMINUTE = 60000;
    private static final int KSECOND = 1000;
    public static final long MILLIS_OF_SEC = 1000;
    public static final long SEC_OF_MIN = 60;

    public static class MINUTES {
        public static long toMillis(int i) {
            return ((long) i) * 60 * 1000;
        }

        public static long toSec(int i) {
            return ((long) i) * 60;
        }
    }

    public static class SECONDS {
        public static long toMillis(long j) {
            return j * 1000;
        }

        public static int toSec(int i) {
            return i;
        }
    }

    public static String parseTime(long j) {
        if (j <= 0) {
            return "00:00:00";
        }
        long j2 = (j / 1000) % 60;
        long j3 = j / 60;
        StringBuilder sb = new StringBuilder();
        align((j3 / 60) / 1000, sb);
        sb.append(":");
        align((j3 / 1000) % 60, sb);
        sb.append(":");
        align(j2, sb);
        return sb.toString();
    }

    public static String parseTimeChinese(long j) {
        if (j <= 0) {
            return "";
        }
        long j2 = (j / 1000) % 60;
        long j3 = j / 60;
        long j4 = (j3 / 1000) % 60;
        long j5 = (j3 / 60) / 1000;
        StringBuilder sb = new StringBuilder();
        if (j5 > 0) {
            sb.append(j5 + "小时");
        }
        if (j4 > 0) {
            sb.append(j4 + "分");
        }
        sb.append(j2 + "秒");
        return sb.toString();
    }

    public static String shortParseTime(long j) {
        if (j <= 0) {
            return "00:00";
        }
        long j2 = (j / 1000) % 60;
        long j3 = j / 60;
        long j4 = (j3 / 1000) % 60;
        long j5 = (j3 / 60) / 1000;
        StringBuilder sb = new StringBuilder();
        if (j5 > 0) {
            align(j5, sb);
            sb.append(":");
        }
        align(j4, sb);
        sb.append(":");
        align(j2, sb);
        return sb.toString();
    }

    public static String shortWidthMsParseTime(long j) {
        if (j <= 0) {
            return "00:00.000";
        }
        long j2 = j % 1000;
        StringBuilder sb = new StringBuilder();
        align((j / 60) / 1000, sb);
        sb.append(":");
        align((j / 1000) % 60, sb);
        if (j2 > 0) {
            sb.append(FileUtil.FILE_EXTENSION_SEPARATOR);
            align3(j2, sb);
        }
        return sb.toString();
    }

    private static void align(long j, StringBuilder sb) {
        if (j >= 10) {
            sb.append(j);
        } else {
            sb.append(0);
            sb.append(j);
        }
    }

    private static void align3(long j, StringBuilder sb) {
        if (j >= 100) {
            sb.append(j);
            return;
        }
        if (j >= 10) {
            sb.append(0);
            sb.append(j);
        } else {
            sb.append(0);
            sb.append(0);
            sb.append(j);
        }
    }

    public static int secondToMinute(int i) {
        return i / 60;
    }

    public static boolean isAfter24(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE, Locale.getDefault());
        StringBuilder sb = new StringBuilder();
        sb.append(simpleDateFormat.format(Long.valueOf(j)));
        sb.append(" 23:59:59");
        try {
            return jCurrentTimeMillis >= DEFAULT_FORMAT.parse(sb.toString()).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getTickCount() {
        return (int) (System.nanoTime() / 1000000);
    }

    public static long getTickCountLong() {
        return System.nanoTime() / 1000000;
    }

    public static String toDataFormat(long j) {
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
}
