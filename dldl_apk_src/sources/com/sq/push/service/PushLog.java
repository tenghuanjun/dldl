package com.sq.push.service;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class PushLog {
    private static final String SUB_TAG = "【Push】";
    public static boolean DEBUG = Log.isLoggable("sysdk.debug.log.push", 3);
    public static boolean VERBOSE = Log.isLoggable("sysdk.debug.log.push", 2);
    private static String sTAG = "NO_TAG";
    private static Logger sLogger = new MyLogger();

    public interface Logger {
        public static final int ASSERT = 7;
        public static final int DEBUG = 3;
        public static final int ERROR = 6;
        public static final int INFO = 4;
        public static final int VERBOSE = 2;
        public static final int WARN = 5;

        void log(int priority, String tag, String message, Throwable throwable);
    }

    public static void setLogger(Logger logger) {
        sLogger = logger;
    }

    public static void setTag(String tag) {
        if (tag != null) {
            sTAG = tag;
        }
    }

    public static void e(String msg) {
        sLogger.log(6, sTAG, SUB_TAG + msg, null);
    }

    public static void w(String msg) {
        if (DEBUG) {
            sLogger.log(5, sTAG, SUB_TAG + msg, null);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            sLogger.log(4, sTAG, SUB_TAG + msg, null);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            sLogger.log(3, sTAG, SUB_TAG + msg, null);
        }
    }

    public static void v(String msg) {
        if (VERBOSE) {
            sLogger.log(2, sTAG, SUB_TAG + msg, null);
        }
    }

    public static void e(String tag, String msg) {
        sLogger.log(6, sTAG, "【Push】<" + tag + "> " + msg, null);
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            sLogger.log(5, sTAG, "【Push】<" + tag + "> " + msg, null);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            sLogger.log(4, sTAG, "【Push】<" + tag + "> " + msg, null);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            sLogger.log(3, sTAG, "【Push】<" + tag + "> " + msg, null);
        }
    }

    public static void v(String tag, String msg) {
        if (VERBOSE) {
            sLogger.log(2, sTAG, "【Push】<" + tag + "> " + msg, null);
        }
    }

    public static void w(String tag, String msg, Throwable e) {
        if (DEBUG) {
            sLogger.log(5, sTAG, "【Push】<" + tag + "> " + msg, e);
        }
    }

    public static void e(String tag, String msg, Throwable e) {
        sLogger.log(6, sTAG, "【Push】<" + tag + "> " + msg, e);
    }

    public static void w(String msg, Throwable e) {
        if (DEBUG) {
            sLogger.log(5, sTAG, SUB_TAG + msg, e);
        }
    }

    public static void e(String msg, Throwable e) {
        sLogger.log(6, sTAG, SUB_TAG + msg, e);
    }

    private static class MyLogger implements Logger {
        private MyLogger() {
        }

        @Override // com.sq.push.service.PushLog.Logger
        public void log(int priority, String tag, String message, Throwable throwable) {
            if (throwable != null) {
                Log.println(priority, tag, message + '\n' + Log.getStackTraceString(throwable));
                return;
            }
            Log.println(priority, tag, message);
        }
    }
}
