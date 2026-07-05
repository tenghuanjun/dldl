package com.huya.mtp.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface EnvVarApi {
    String getChannelName();

    int getHotFixVersionCode();

    int getVersionCode();

    String getVersionName();

    boolean isDebuggable();

    boolean isSnapshot();

    boolean isTestEnv();
}
