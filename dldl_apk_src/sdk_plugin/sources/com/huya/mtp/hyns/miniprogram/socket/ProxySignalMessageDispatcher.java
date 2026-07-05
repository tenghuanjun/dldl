package com.huya.mtp.hyns.miniprogram.socket;

import com.duowan.taf.jce.JceInputStream;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hycloudgame.base.listener.IMessageDispatcher;
import com.huya.mtp.hycloudgame.base.websocket.WupResponseListener;
import com.huya.mtp.hyns.miniprogram.jce.WsProxyPacket;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ProxySignalMessageDispatcher implements IMessageDispatcher {
    private static final String TAG = "NetService-MessageDispatcher";
    private ProxySignalJceMsgListener mJceRspListener;
    private final Object mDispatchLock = new Object();
    private ConcurrentHashMap<Integer, WupResponseListener> mWupResponseListenerMap = new ConcurrentHashMap<>();

    public void setJceRspListener(ProxySignalJceMsgListener proxySignalJceMsgListener) {
        synchronized (this.mDispatchLock) {
            this.mJceRspListener = proxySignalJceMsgListener;
        }
    }

    public void removeJceRspListener() {
        synchronized (this.mDispatchLock) {
            this.mJceRspListener = null;
        }
    }

    public void clear() {
        synchronized (this.mDispatchLock) {
            this.mJceRspListener = null;
            this.mWupResponseListenerMap.clear();
        }
    }

    public void setWupMessageListener(int i, WupResponseListener wupResponseListener) {
        this.mWupResponseListenerMap.put(Integer.valueOf(i), wupResponseListener);
    }

    public void clearMessage(int i) {
        this.mWupResponseListenerMap.remove(Integer.valueOf(i));
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.IMessageDispatcher
    public void dispatchMessage(byte[] bArr) {
        JceInputStream jceInputStream = new JceInputStream(bArr);
        WsProxyPacket wsProxyPacket = new WsProxyPacket();
        wsProxyPacket.readFrom(jceInputStream);
        MTPApi.LOGGER.info(TAG, "MiniPg Received data length:%d", Integer.valueOf(bArr.length));
        MTPApi.LOGGER.info(TAG, "MiniPg Received command:%d, vData:%d.", Integer.valueOf(wsProxyPacket.iCommand), Integer.valueOf(wsProxyPacket.vData.length));
        synchronized (this.mDispatchLock) {
            if (this.mJceRspListener != null) {
                this.mJceRspListener.onResponse(wsProxyPacket.iCommand, wsProxyPacket.vData);
            }
        }
    }
}
