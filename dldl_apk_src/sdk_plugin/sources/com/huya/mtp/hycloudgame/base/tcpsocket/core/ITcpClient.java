package com.huya.mtp.hycloudgame.base.tcpsocket.core;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ITcpClient {
    void connect(String str, int i);

    void disconnect();

    boolean isConnected();

    boolean send(byte[] bArr);
}
