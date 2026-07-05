package com.duowan.auk.util;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ILogger {
    void debug(Object obj, String str);

    void debug(Object obj, String str, Object... objArr);

    void debug(Object obj, Throwable th);

    void debug(String str);

    void debug(String str, Throwable th);

    void error(Object obj, String str);

    void error(Object obj, String str, Object... objArr);

    void error(Object obj, Throwable th);

    void error(String str);

    void error(String str, Throwable th);

    void flushToDisk();

    void info(Object obj, String str);

    void info(Object obj, String str, Object... objArr);

    void info(String str);

    void init(Context context, String str, boolean z);

    boolean isLogEnable();

    boolean isLogLevelEnabled(int i);

    void setLogEnable(boolean z);

    void setLogLevel(int i);

    void setMaxFileCount(int i);

    void setMaxFileSize(int i);

    void setSysLogEnabled(boolean z);

    void setWrapperCount(int i);

    void uncaughtException(Throwable th);

    void verbose(Object obj, String str);

    void verbose(Object obj, String str, Object... objArr);

    void verbose(String str);

    void warn(Object obj, String str);

    void warn(Object obj, String str, Object... objArr);

    void warn(String str);
}
