package com.huya.mtp.hycloudgame.base.listener;

import com.huya.mtp.hycloudgame.base.websocket.ISocketStateMonitor;
import com.huya.mtp.hycloudgame.base.websocket.WebSocketConfig;
import com.huya.mtp.hycloudgame.base.websocket.client.WebSocketHandler;
import com.huya.mtp.hyns.api.ISocketPacket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ISocketClient {
    void checkNotifyInitCompleted();

    boolean connect(String str, ISocketStateMonitor iSocketStateMonitor);

    boolean connect(String str, WebSocketConfig webSocketConfig, ISocketStateMonitor iSocketStateMonitor);

    boolean connect(String str, String str2, ISocketStateMonitor iSocketStateMonitor);

    void destroy();

    void disconnect();

    void reConnect();

    void sendTubeRequest(ISocketPacket iSocketPacket);

    void setMessageDispatcher(IMessageDispatcher iMessageDispatcher);

    void setMessageParseStrategy(WebSocketHandler.OnMessageDispatchListener onMessageDispatchListener);

    void setSocketStateListener(ISocketStateListener iSocketStateListener);
}
