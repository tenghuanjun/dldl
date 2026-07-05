package com.huya.mtp.hycloudgame.base;

import android.content.Context;
import com.huya.mtp.hycloudgame.base.listener.ISocketClient;
import com.huya.mtp.hycloudgame.base.websocket.client.WebSocketClient;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SocketFactory {
    public static ISocketClient createSocket(int i, boolean z, Context context) {
        return new WebSocketClient(context, z);
    }
}
