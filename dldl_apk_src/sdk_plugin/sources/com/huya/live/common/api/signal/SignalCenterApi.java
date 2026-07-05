package com.huya.live.common.api.signal;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface SignalCenterApi {
    <T> void register(T t);

    <T> void send(T t);

    <T> void send(T t, int i);

    <T> void send(T t, int i, String str);

    <T> void send(T t, Object obj);

    <T> void unregister(T t);
}
