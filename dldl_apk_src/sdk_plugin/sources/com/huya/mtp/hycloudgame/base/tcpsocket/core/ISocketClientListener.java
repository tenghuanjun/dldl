package com.huya.mtp.hycloudgame.base.tcpsocket.core;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ISocketClientListener {
    void onClose(int i, String str, boolean z);

    void onError(Throwable th);

    void onMessage(ByteBuffer byteBuffer);

    void onOpen();
}
