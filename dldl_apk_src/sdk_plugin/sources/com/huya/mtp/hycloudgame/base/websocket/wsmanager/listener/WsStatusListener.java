package com.huya.mtp.hycloudgame.base.websocket.wsmanager.listener;

import okhttp3.Response;
import okio.ByteString;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class WsStatusListener {
    public void onClosed(int i, String str) {
    }

    public void onClosing(int i, String str) {
    }

    public void onFailure(Throwable th, Response response) {
    }

    public void onMessage(String str) {
    }

    public void onMessage(ByteString byteString) {
    }

    public void onOpen(Response response) {
    }

    public void onReconnect() {
    }
}
