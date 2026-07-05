package com.huya.hysignal.wrapper;

import com.huya.hysignal.wrapper.listener.P2pPushListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface P2pPushDelegate {
    void start(String str, long j, P2pPushListener p2pPushListener);

    void stop(String str);
}
