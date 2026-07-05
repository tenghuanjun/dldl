package com.huya.mtp.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface LogApi {
    void debug(Object obj, String str);

    void debug(Object obj, String str, Throwable th);

    void debug(Object obj, String str, Object... objArr);

    void debug(Object obj, Throwable th);

    void debug(String str);

    void error(Object obj, String str);

    void error(Object obj, String str, Throwable th);

    void error(Object obj, String str, Object... objArr);

    void error(Object obj, Throwable th);

    void error(String str);

    void fatal(Object obj, String str);

    void fatal(Object obj, String str, Throwable th);

    void fatal(Object obj, String str, Object... objArr);

    void fatal(Object obj, Throwable th);

    void fatal(String str);

    void flushToDisk();

    void info(Object obj, String str);

    void info(Object obj, String str, Throwable th);

    void info(Object obj, String str, Object... objArr);

    void info(Object obj, Throwable th);

    void info(String str);

    boolean isLogLevelEnabled(int i);

    void uncaughtException(Throwable th);

    void verbose(Object obj, String str);

    void verbose(Object obj, String str, Throwable th);

    void verbose(Object obj, String str, Object... objArr);

    void verbose(Object obj, Throwable th);

    void verbose(String str);

    void warn(Object obj, String str);

    void warn(Object obj, String str, Throwable th);

    void warn(Object obj, String str, Object... objArr);

    void warn(Object obj, Throwable th);

    void warn(String str);
}
