package com.sq.tools;

import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class Logger {
    private static String globalTag = "sqad";
    private static int logLevel = 2;

    public static Tag tag(String str) {
        return new Tag(str);
    }

    public static void setLogLevel(int i) {
        logLevel = i;
    }

    public static void setTag(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        globalTag = str;
    }

    public static void verbose(String str, Object... objArr) {
        doLog(2, null, str, objArr);
    }

    public static void verbose(Tag tag, String str, Object... objArr) {
        doLog(2, tag.tag, str, objArr);
    }

    public static void debug(String str, Object... objArr) {
        doLog(3, null, str, objArr);
    }

    public static void debug(Tag tag, String str, Object... objArr) {
        doLog(3, tag.tag, str, objArr);
    }

    public static void info(String str, Object... objArr) {
        doLog(4, null, str, objArr);
    }

    public static void info(Tag tag, String str, Object... objArr) {
        doLog(4, tag.tag, str, objArr);
    }

    public static void warning(String str, Object... objArr) {
        doLog(5, null, str, objArr);
    }

    public static void warning(Tag tag, String str, Object... objArr) {
        doLog(5, tag.tag, str, objArr);
    }

    public static void error(String str, Object... objArr) {
        doLog(6, null, str, objArr);
    }

    public static void error(Tag tag, String str, Object... objArr) {
        doLog(6, tag.tag, str, objArr);
    }

    private static void doLog(int i, String str, String str2, Object[] objArr) {
        try {
            Throwable thExtractThrowable = extractThrowable(objArr);
            if (thExtractThrowable != null) {
                objArr = trimParams(objArr);
            }
            if (str2 != null && objArr != null) {
                str2 = String.format(str2, objArr);
            } else if (str2 == null) {
                str2 = "";
            }
            String trace = formatTrace(str2);
            if (str == null) {
                str = globalTag;
            }
            String tag = formatTag(str);
            if (thExtractThrowable == null) {
                androidLog(i, tag, trace);
                return;
            }
            if (thExtractThrowable instanceof UnknownHostException) {
                trace = trace + "\nException: " + thExtractThrowable.toString();
            }
            androidLog(i, tag, trace, thExtractThrowable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void androidLog(int i, String str, String str2) {
        switch (i) {
            case 2:
                SQLog.vt(str, str2);
                break;
            case 3:
                SQLog.dt(str, str2);
                break;
            case 4:
                SQLog.it(str, str2);
                break;
            case 5:
                SQLog.wt(str, str2);
                break;
            case 6:
            case 7:
                SQLog.et(str, str2);
                break;
        }
    }

    private static void androidLog(int i, String str, String str2, Throwable th) {
        switch (i) {
            case 2:
                SQLog.vt(str, str2, th);
                break;
            case 3:
                SQLog.dt(str, str2, th);
                break;
            case 4:
                SQLog.it(str, str2, th);
                break;
            case 5:
                SQLog.wt(str, str2, th);
                break;
            case 6:
            case 7:
                SQLog.et(str, str2, th);
                break;
        }
    }

    private static Throwable extractThrowable(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        Object obj = objArr[objArr.length - 1];
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        return null;
    }

    private static Object[] trimParams(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            throw new IllegalArgumentException("params is null or empty");
        }
        int length = objArr.length - 1;
        Object[] objArr2 = new Object[length];
        System.arraycopy(objArr, 0, objArr2, 0, length);
        return objArr2;
    }

    private static String formatTrace(String str) {
        return formatTrace(str, 4);
    }

    private static String formatTrace(String str, int i) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[Math.min(i, r0.getStackTrace().length - 1)];
        return "[(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + "): " + stackTraceElement.getMethodName() + "()]: " + str;
    }

    private static String formatTag(String str) {
        return str + ": " + Thread.currentThread().getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static class Tag {
        public String tag;

        public Tag(String str) {
            this.tag = str;
        }
    }
}
