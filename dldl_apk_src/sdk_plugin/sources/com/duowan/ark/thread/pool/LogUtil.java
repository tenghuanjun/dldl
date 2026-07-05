package com.duowan.ark.thread.pool;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LogUtil {
    private static Logger mLog = new Logger() { // from class: com.duowan.ark.thread.pool.LogUtil.1
        @Override // com.duowan.ark.thread.pool.LogUtil.Logger
        public void info(String str, String str2) {
            Log.i(str, str2);
        }

        @Override // com.duowan.ark.thread.pool.LogUtil.Logger
        public void info(String str, Throwable th) {
            Log.i(str, th.toString());
        }
    };

    public interface Logger {
        void info(String str, String str2);

        void info(String str, Throwable th);
    }

    public static void info(String str, String str2) {
        mLog.info(str, str2);
    }

    public static void info(String str, Throwable th) {
        mLog.info(str, th);
    }

    public static void setLog(Logger logger) {
        if (logger == null) {
            throw new NullPointerException("Logger can not be null");
        }
        mLog = logger;
    }
}
