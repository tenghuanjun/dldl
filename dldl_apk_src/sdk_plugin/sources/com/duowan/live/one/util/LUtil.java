package com.duowan.live.one.util;

import com.duowan.auk.asignal.Property;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LUtil {
    private static final String MarkEanbleLog = "enableLog";
    public static final Property<Boolean> enableLog = new Property<>(false, MarkEanbleLog);

    public static void verbose(Object obj, String str, Object... objArr) {
        if (enableLog.get().booleanValue()) {
            L.verbose(obj, str, objArr);
        }
    }

    public static void verbose(Object obj, String str) {
        if (enableLog.get().booleanValue()) {
            L.verbose(obj, str);
        }
    }

    public static void verbose(String str) {
        if (enableLog.get().booleanValue()) {
            L.verbose(str);
        }
    }

    public static void debug(Object obj, String str, Object... objArr) {
        if (enableLog.get().booleanValue()) {
            L.debug(obj, str, objArr);
        }
    }

    public static void debug(Object obj, String str) {
        if (enableLog.get().booleanValue()) {
            L.debug(obj, str);
        }
    }

    public static void debug(Object obj, Throwable th) {
        if (enableLog.get().booleanValue()) {
            L.debug(obj, th);
        }
    }

    public static void debug(String str) {
        if (enableLog.get().booleanValue()) {
            L.debug(str);
        }
    }

    public static void debug(String str, Throwable th) {
        if (enableLog.get().booleanValue()) {
            L.debug(str, th);
        }
    }

    public static void info(String str) {
        if (enableLog.get().booleanValue()) {
            L.info(str);
        }
    }

    public static void info(Object obj, String str) {
        if (enableLog.get().booleanValue()) {
            L.info(obj, str);
        }
    }

    public static void info(Object obj, String str, Object... objArr) {
        if (enableLog.get().booleanValue()) {
            L.info(obj, str, objArr);
        }
    }

    public static void warn(Object obj, String str, Object... objArr) {
        if (enableLog.get().booleanValue()) {
            L.warn(obj, str, objArr);
        }
    }

    public static void warn(Object obj, String str) {
        if (enableLog.get().booleanValue()) {
            L.warn(obj, str);
        }
    }

    public static void warn(String str) {
        if (enableLog.get().booleanValue()) {
            L.warn(str);
        }
    }

    public static void error(Object obj, String str, Object... objArr) {
        if (enableLog.get().booleanValue()) {
            L.error(obj, str, objArr);
        }
    }

    public static void error(Object obj, String str) {
        if (enableLog.get().booleanValue()) {
            L.error(obj, str);
        }
    }

    public static void error(Object obj, Throwable th) {
        if (enableLog.get().booleanValue()) {
            L.error(obj, th);
        }
    }

    public static void error(String str) {
        if (enableLog.get().booleanValue()) {
            L.error(str);
        }
    }

    public static void error(String str, Throwable th) {
        if (enableLog.get().booleanValue()) {
            L.error(str, th);
        }
    }
}
