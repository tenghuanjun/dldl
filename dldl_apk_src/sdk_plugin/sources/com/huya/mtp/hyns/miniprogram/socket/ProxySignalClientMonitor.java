package com.huya.mtp.hyns.miniprogram.socket;

import android.os.Handler;
import android.os.Message;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hycloudgame.base.listener.ISocketClient;
import com.huya.mtp.hycloudgame.base.websocket.ISocketStateMonitor;
import com.huya.mtp.hyns.miniprogram.data.ProxySignalMonitorInfo;
import com.huya.mtp.hyns.miniprogram.utils.ProxySignalSocketHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ProxySignalClientMonitor extends ISocketStateMonitor {
    private static final int MSG_HEARTBEAT = 1;
    public static final String TAG = "NetService-ClientMonitor";
    private MonitorHandler mHandler;
    private ProxySignalMonitorInfo mMonitorInfo;
    private ISocketClient mWebSocketClient;
    private boolean mIsStart = false;
    private boolean mDoLogin = false;

    public ProxySignalClientMonitor(ProxySignalMonitorInfo proxySignalMonitorInfo, ISocketClient iSocketClient) {
        if (iSocketClient == null) {
            MTPApi.LOGGER.error(TAG, "bug bug WebSocketClient is null");
            return;
        }
        this.mMonitorInfo = proxySignalMonitorInfo;
        this.mHandler = new MonitorHandler(this.mMonitorInfo, iSocketClient);
        this.mWebSocketClient = iSocketClient;
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.ISocketStateMonitor
    public void start() {
        if (this.mIsStart) {
            MTPApi.LOGGER.error(TAG, "is started.");
            return;
        }
        this.mIsStart = true;
        this.mDoLogin = false;
        MonitorHandler monitorHandler = this.mHandler;
        if (monitorHandler == null || monitorHandler.isStarted()) {
            return;
        }
        this.mHandler.setIsStarted(true);
        this.mHandler.sendEmptyMessage(1);
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.ISocketStateMonitor
    public void stop() {
        if (this.mIsStart) {
            this.mIsStart = false;
            MonitorHandler monitorHandler = this.mHandler;
            if (monitorHandler == null || !monitorHandler.isStarted()) {
                return;
            }
            this.mHandler.setIsStarted(false);
            this.mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.ISocketStateMonitor
    public void startHeartBeat() {
        MTPApi.LOGGER.info(TAG, "初始化成功，开始心跳");
        this.mHandler.sendEmptyMessageDelayed(1, this.mMonitorInfo.getHeartBeatInterval());
    }

    private static final class MonitorHandler extends Handler {
        private boolean mIsStarted;
        private ProxySignalMonitorInfo mMonitorInfo;
        private ISocketClient mWebSocketClient;

        public MonitorHandler(ProxySignalMonitorInfo proxySignalMonitorInfo, ISocketClient iSocketClient) {
            this.mWebSocketClient = iSocketClient;
            this.mMonitorInfo = proxySignalMonitorInfo;
        }

        public void setIsStarted(boolean z) {
            this.mIsStarted = z;
        }

        public boolean isStarted() {
            return this.mIsStarted;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.mIsStarted && message.what == 1) {
                MTPApi.LOGGER.info(ProxySignalClientMonitor.TAG, "mini program connected");
                this.mWebSocketClient.sendTubeRequest(ProxySignalSocketHelper.getCommonPacket(null, 1));
                sendEmptyMessageDelayed(1, this.mMonitorInfo.getHeartBeatInterval());
            }
        }
    }
}
