package com.huya.berry.gamesdk.crash;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ICrashService {
    void init();

    void postCatchedException(Throwable th);
}
