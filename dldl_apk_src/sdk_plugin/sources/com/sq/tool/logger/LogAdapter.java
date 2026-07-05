package com.sq.tool.logger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface LogAdapter {
    boolean isLoggable(int priority, String tag);

    void log(int priority, String tag, String message);
}
