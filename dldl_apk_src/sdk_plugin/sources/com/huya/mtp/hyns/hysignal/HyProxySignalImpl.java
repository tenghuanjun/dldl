package com.huya.mtp.hyns.hysignal;

import android.content.Context;
import com.huya.mtp.hycloudgame.base.listener.ISocketStateListener;
import com.huya.mtp.hyns.api.IProxySignalJceMsgListener;
import com.huya.mtp.hyns.api.IProxySignalListener;
import com.huya.mtp.hyns.api.NSConnectConfig;
import com.huya.mtp.hyns.api.NSProxySignalApi;
import com.huya.mtp.hyns.miniprogram.socket.ProxySignalJceMsgListener;
import com.huya.mtp.hyns.miniprogram.socket.ProxySignalSocketInitParam;
import com.huya.mtp.hyns.miniprogram.socket.ProxySignalSocketManager;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyProxySignalImpl implements NSProxySignalApi {
    @Override // com.huya.mtp.hyns.api.NSProxySignalApi
    public NSProxySignalApi.IProxySignalManager newProxySignalItem(NSProxySignalApi.NSProxySignalInitParam nSProxySignalInitParam, Context context) {
        return new HyProxySignalItem(new ProxySignalSocketInitParam.Builder().build(), context);
    }

    public static class HyProxySignalItem implements NSProxySignalApi.IProxySignalManager {
        ConcurrentHashMap<IProxySignalJceMsgListener, ProxySignalJceMsgListener> mProxySignalJceLMap = new ConcurrentHashMap<>();
        private ProxySignalSocketManager proxySignalManager;

        public HyProxySignalItem(ProxySignalSocketInitParam proxySignalSocketInitParam, Context context) {
            this.proxySignalManager = new ProxySignalSocketManager(proxySignalSocketInitParam, context);
        }

        @Override // com.huya.mtp.hyns.api.NSProxySignalApi.IProxySignalManager
        public void connect(NSConnectConfig nSConnectConfig, final IProxySignalListener iProxySignalListener) {
            this.proxySignalManager.connectForDns(nSConnectConfig.getTargetUrl(), nSConnectConfig.getUserId(), nSConnectConfig.getBizId(), nSConnectConfig.isTestEnv(), new ISocketStateListener() { // from class: com.huya.mtp.hyns.hysignal.HyProxySignalImpl.HyProxySignalItem.1
                @Override // com.huya.mtp.hycloudgame.base.listener.ISocketStateListener
                public void onSocketInitCompleted() {
                }

                @Override // com.huya.mtp.hycloudgame.base.listener.ISocketStateListener
                public void onSocketConnected() {
                    IProxySignalListener iProxySignalListener2 = iProxySignalListener;
                    if (iProxySignalListener2 != null) {
                        iProxySignalListener2.onSocketConnected();
                    }
                }

                @Override // com.huya.mtp.hycloudgame.base.listener.ISocketStateListener
                public void onSocketDisconnected() {
                    IProxySignalListener iProxySignalListener2 = iProxySignalListener;
                    if (iProxySignalListener2 != null) {
                        iProxySignalListener2.onSocketDisconnected();
                    }
                }

                @Override // com.huya.mtp.hycloudgame.base.listener.ISocketStateListener
                public void onSocketError(int i, Throwable th) {
                    IProxySignalListener iProxySignalListener2 = iProxySignalListener;
                    if (iProxySignalListener2 != null) {
                        iProxySignalListener2.onSocketError(i, th);
                    }
                }
            });
        }

        @Override // com.huya.mtp.hyns.api.NSProxySignalApi.IProxySignalManager
        public void reConnectSocket() {
            this.proxySignalManager.reConnectSocket();
        }

        @Override // com.huya.mtp.hyns.api.NSProxySignalApi.IProxySignalManager
        public void disconnect() {
            this.proxySignalManager.disconnect();
        }

        @Override // com.huya.mtp.hyns.api.NSProxySignalApi.IProxySignalManager
        public void addJceRspListener(final IProxySignalJceMsgListener iProxySignalJceMsgListener) {
            if (iProxySignalJceMsgListener != null) {
                ProxySignalJceMsgListener proxySignalJceMsgListener = new ProxySignalJceMsgListener() { // from class: com.huya.mtp.hyns.hysignal.HyProxySignalImpl.HyProxySignalItem.2
                    @Override // com.huya.mtp.hyns.miniprogram.socket.ProxySignalJceMsgListener
                    public void onResponse(int i, byte[] bArr) {
                        iProxySignalJceMsgListener.onResponse(i, bArr);
                    }
                };
                this.mProxySignalJceLMap.put(iProxySignalJceMsgListener, proxySignalJceMsgListener);
                this.proxySignalManager.addJceRspListener(proxySignalJceMsgListener);
            }
        }

        @Override // com.huya.mtp.hyns.api.NSProxySignalApi.IProxySignalManager
        public void removeJceRspListener(IProxySignalJceMsgListener iProxySignalJceMsgListener) {
            ProxySignalJceMsgListener proxySignalJceMsgListenerRemove;
            if (iProxySignalJceMsgListener == null || (proxySignalJceMsgListenerRemove = this.mProxySignalJceLMap.remove(iProxySignalJceMsgListener)) == null) {
                return;
            }
            this.proxySignalManager.removeJceRspListener(proxySignalJceMsgListenerRemove);
        }

        @Override // com.huya.mtp.hyns.api.NSProxySignalApi.IProxySignalManager
        public void sendWebSocketPacket(byte[] bArr, int i) {
            this.proxySignalManager.sendWebSocketPacket(bArr, i);
        }

        @Override // com.huya.mtp.hyns.api.NSProxySignalApi.IProxySignalManager
        public boolean isConnected() {
            return this.proxySignalManager.isHasConnected();
        }

        @Override // com.huya.mtp.hyns.api.NSProxySignalApi.IProxySignalManager
        public void destroy() {
            this.mProxySignalJceLMap.clear();
            this.proxySignalManager.onDestroy();
        }
    }
}
