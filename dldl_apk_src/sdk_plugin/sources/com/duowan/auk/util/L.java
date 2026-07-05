package com.duowan.auk.util;

import android.os.Environment;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class L {
    public static int LOG_LEVEL = 4;
    public static final String LOG_NAME = "logs";
    public static final String NATIVE_BAK_LOG_NAME = "native_crash.bak";
    public static final String NATIVE_LOG_NAME = "native_crash.txt";
    public static String TAG = "auk";
    public static final String UE_LOG_NAME = "uncaught_exception.txt";
    public static String sLogPath = "";
    public static File sRootDir;
    public static boolean isStoreExist = "mounted".equalsIgnoreCase(Environment.getExternalStorageState());
    public static boolean useLocalUncaughtException = true;
    private static ILogger sLogger = null;

    public static void setLogger(ILogger iLogger) {
        sLogger = iLogger;
    }

    public static void setWrapperCount(int i) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.setWrapperCount(i);
        }
    }

    public static void useLocalUncaughtException(boolean z) {
        useLocalUncaughtException = z;
    }

    public static void setSysLogEnabled(boolean z) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.setSysLogEnabled(z);
        }
    }

    public static void setLogLevel(int i) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.setLogLevel(i);
        }
    }

    public static boolean isLogEnable() {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            return iLogger.isLogEnable();
        }
        return true;
    }

    public static void setLogEnable(boolean z) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.setLogEnable(z);
        }
    }

    public static void setMaxFileCount(int i) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.setMaxFileCount(i);
        }
    }

    public static void setMaxFileSize(int i) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.setMaxFileSize(i);
        }
    }

    public static void verbose(Object obj, String str, Object... objArr) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.verbose(obj, str, objArr);
        }
    }

    public static void verbose(Object obj, String str) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.verbose(obj, str);
        }
    }

    public static void verbose(String str) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.verbose(str);
        }
    }

    public static void debug(Object obj, String str, Object... objArr) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.debug(obj, str, objArr);
        }
    }

    public static void debug(Object obj, String str) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.debug(obj, str);
        }
    }

    public static void debug(Object obj, Throwable th) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.debug(obj, th);
        }
    }

    public static void debug(String str) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.debug(str);
        }
    }

    public static void debug(String str, Throwable th) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.debug(str, th);
        }
    }

    public static void info(Object obj, String str) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.info(obj, str);
        }
    }

    public static void info(Object obj, String str, Object... objArr) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.info(obj, str, objArr);
        }
    }

    public static void info(String str) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.info(str);
        }
    }

    public static void warn(Object obj, String str, Object... objArr) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.warn(obj, str, objArr);
        }
    }

    public static void warn(Object obj, String str) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.warn(obj, str);
        }
    }

    public static void warn(String str) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.warn("WARN", str);
        }
    }

    public static void error(Object obj, String str, Object... objArr) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.error(obj, str, objArr);
        }
    }

    public static void error(Object obj, String str) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.error(obj, str);
        }
    }

    public static void error(Object obj, Throwable th) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.error(obj, th);
        }
    }

    public static void error(String str) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.error(str);
        }
    }

    public static void error(String str, Throwable th) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.error(str, th);
        }
    }

    public static void uncaughtException(Throwable th) {
        ILogger iLogger;
        if (!useLocalUncaughtException || (iLogger = sLogger) == null) {
            return;
        }
        iLogger.uncaughtException(th);
    }

    public static boolean isLogLevelEnabled(int i) {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            return iLogger.isLogLevelEnabled(i);
        }
        return true;
    }

    public static void flushToDisk() {
        ILogger iLogger = sLogger;
        if (iLogger != null) {
            iLogger.flushToDisk();
        }
    }
}
