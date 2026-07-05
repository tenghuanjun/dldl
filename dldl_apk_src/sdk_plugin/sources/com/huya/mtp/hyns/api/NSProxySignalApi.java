package com.huya.mtp.hyns.api;

import android.content.Context;
import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.protocol.NSProxySignalProtocol;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(NSProxySignalProtocol.class)
public interface NSProxySignalApi {

    public interface IProxySignalManager {
        void addJceRspListener(IProxySignalJceMsgListener iProxySignalJceMsgListener);

        void connect(NSConnectConfig nSConnectConfig, IProxySignalListener iProxySignalListener);

        void destroy();

        void disconnect();

        boolean isConnected();

        void reConnectSocket();

        void removeJceRspListener(IProxySignalJceMsgListener iProxySignalJceMsgListener);

        void sendWebSocketPacket(byte[] bArr, int i);
    }

    public static class NSProxySignalInitParam {
    }

    IProxySignalManager newProxySignalItem(NSProxySignalInitParam nSProxySignalInitParam, Context context);
}
