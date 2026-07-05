package com.huya.hysignal.wrapper.listener;

import java.util.Vector;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface P2pPushListener {
    void onDatas(String str, long j, Vector<byte[]> vector, int i);

    void onSignalStreamReqStatus(String str, long j, int i);
}
