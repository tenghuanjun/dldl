package com.huya.mtp.hycloudgame.base.websocket.wsmanager;

import okhttp3.WebSocket;
import okio.ByteString;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
interface IWsManager {
    int getCurrentStatus();

    WebSocket getWebSocket();

    boolean isWsConnected();

    boolean sendMessage(String str);

    boolean sendMessage(ByteString byteString);

    void startConnect();

    void stopConnect();
}
