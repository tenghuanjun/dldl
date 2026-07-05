package com.sq.tools.network.httpdns.log;

import android.util.Log;
import com.sq.tool.logger.Printer;
import com.sq.tool.logger.SQLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsLog {
    public static boolean DEBUG = Log.isLoggable("sysdk.debug.log.dns", 3);
    public static boolean VERBOSE = Log.isLoggable("sysdk.debug.log.dns", 2);
    private static Printer sLogger = SQLog.m("【Dns】");

    public static void setLogger(Printer logger) {
        sLogger = logger;
    }

    public static void e(String msg) {
        sLogger.e(msg);
    }

    public static void w(String msg) {
        sLogger.w(msg);
    }

    public static void i(String msg) {
        if (DEBUG) {
            sLogger.i(msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            sLogger.d(msg);
        }
    }

    public static void v(String msg) {
        if (VERBOSE) {
            sLogger.v(msg);
        }
    }

    public static void e(String tag, String msg) {
        sLogger.e(tag, msg);
    }

    public static void w(String tag, String msg) {
        sLogger.w(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            sLogger.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            sLogger.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (VERBOSE) {
            sLogger.v(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable e) {
        sLogger.w(tag, msg, e);
    }

    public static void e(String tag, String msg, Throwable e) {
        sLogger.e(tag, msg, e);
    }

    public static void w(String msg, Throwable e) {
        sLogger.w(msg, e);
    }

    public static void e(String msg, Throwable e) {
        sLogger.e(msg, e);
    }
}
