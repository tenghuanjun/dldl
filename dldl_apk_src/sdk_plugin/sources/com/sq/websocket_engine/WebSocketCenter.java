package com.sq.websocket_engine;

import com.sq.libwebsocket.SimpleListener;
import com.sq.libwebsocket.SocketListener;
import com.sq.libwebsocket.WebSocketHandler;
import com.sq.libwebsocket.WebSocketManager;
import com.sq.libwebsocket.WebSocketSetting;
import com.sq.libwebsocket.response.ErrorResponse;
import com.sqwan.common.annotation.UrlUpdate;
import com.sqwan.common.util.CheckNetwork;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.task.Task;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebSocketCenter {
    public static final String KEY_WEBSOCKET = "websocket";
    private static final String TAG = "WebSocketCenter";
    private static final boolean isTest = false;
    private static String websocket_url = null;
    private static final String websocket_url_test = "ws://push-comet-test.37.com.cn/sub";
    private WebSocketManager manager;
    private WebSocketCenterListener webSocketCenterListener;
    private static final WebSocketCenter ourInstance = new WebSocketCenter();

    @UrlUpdate(KEY_WEBSOCKET)
    private static String websocket_url_release = "ws://push-comet.37.com.cn/sub";
    private volatile boolean isLocalConnected = true;
    private SocketListener socketListener = new SimpleListener() { // from class: com.sq.websocket_engine.WebSocketCenter.3
        @Override // com.sq.libwebsocket.SimpleListener, com.sq.libwebsocket.SocketListener
        public void onConnected() {
            WebSocketCenter.this.logMsg("onConnected");
            if (WebSocketCenter.this.webSocketCenterListener != null) {
                WebSocketCenter.this.webSocketCenterListener.onConnected();
            }
        }

        @Override // com.sq.libwebsocket.SimpleListener, com.sq.libwebsocket.SocketListener
        public void onConnectFailed(Throwable th) {
            if (th == null) {
                WebSocketCenter.this.logMsg("onConnectFailed:null");
                return;
            }
            WebSocketCenter.this.logMsg("onConnectFailed:" + th.toString());
        }

        @Override // com.sq.libwebsocket.SimpleListener, com.sq.libwebsocket.SocketListener
        public void onDisconnect() {
            WebSocketCenter.this.logMsg("onDisconnect");
        }

        @Override // com.sq.libwebsocket.SimpleListener, com.sq.libwebsocket.SocketListener
        public void onSendDataError(ErrorResponse errorResponse) {
            WebSocketCenter.this.logMsg(errorResponse.getDescription());
            errorResponse.release();
        }

        @Override // com.sq.libwebsocket.SimpleListener, com.sq.libwebsocket.SocketListener
        public <T> void onMessage(ByteBuffer byteBuffer, T t) {
            LogUtil.i(WebSocketCenter.TAG, "onMessage");
            RequestQueueHandler.getInstance().receiveMessageData(byteBuffer);
        }
    };

    public interface WebSocketCenterListener {
        void onConnected();

        void onNetworkChange(boolean z);
    }

    static {
        websocket_url = websocket_url_test;
        websocket_url = "ws://push-comet.37.com.cn/sub";
    }

    public static WebSocketCenter getInstance() {
        return ourInstance;
    }

    private WebSocketCenter() {
    }

    public boolean isLocalConnected() {
        return this.isLocalConnected;
    }

    public void setLocalConnected(boolean z) {
        this.isLocalConnected = z;
    }

    private WebSocketSetting initWebSocketSetting() {
        WebSocketSetting webSocketSetting = new WebSocketSetting();
        webSocketSetting.setConnectUrl(websocket_url);
        webSocketSetting.setConnectTimeout(15000);
        webSocketSetting.setConnectionLostTimeout(30);
        webSocketSetting.setReconnectFrequency(5);
        webSocketSetting.setResponseProcessDispatcher(new AppResponseDispatcher());
        webSocketSetting.setProcessDataOnBackground(true);
        webSocketSetting.setResponseDelivery(new SubThreadResponseDelivery());
        return webSocketSetting;
    }

    public void initWebSocket(final WebSocketCenterListener webSocketCenterListener) {
        if (this.manager == null) {
            this.webSocketCenterListener = webSocketCenterListener;
            CheckNetwork.getInstance().checkNetworkListener = new CheckNetwork.CheckNetworkListener() { // from class: com.sq.websocket_engine.WebSocketCenter.1
                @Override // com.sqwan.common.util.CheckNetwork.CheckNetworkListener
                public void onNetworkChange(boolean z) {
                    LogUtil.i(WebSocketCenter.TAG, "onNetworkChange isConnected:" + z);
                    WebSocketCenterListener webSocketCenterListener2 = webSocketCenterListener;
                    if (webSocketCenterListener2 != null) {
                        webSocketCenterListener2.onNetworkChange(z);
                    }
                    RequestQueueHandler.getInstance().setLocalConnected(z);
                    WebSocketCenter.this.setLocalConnected(z);
                    if (WebSocketHandler.getDefault() == null || !WebSocketHandler.getDefault().getSetting().reconnectWithNetworkChanged()) {
                        return;
                    }
                    WebSocketHandler.getDefault().reconnect();
                }
            };
            this.manager = WebSocketHandler.init(initWebSocketSetting());
            WebSocketHandler.getDefault().addListener(this.socketListener);
            Task.postDelay(50L, new Runnable() { // from class: com.sq.websocket_engine.WebSocketCenter.2
                @Override // java.lang.Runnable
                public void run() {
                    WebSocketCenter.this.manager.start();
                    CheckNetwork.getInstance().register();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logMsg(String str) {
        LogUtil.i(TAG, "logMsg:" + str);
    }

    public void destroy() {
        if (this.manager != null) {
            CheckNetwork.getInstance().unregister();
            this.manager.removeListener(this.socketListener);
            this.manager.destroy();
            WebSocketHandler.uninit();
            this.manager = null;
        }
    }

    public WebSocketManager getManager() {
        return this.manager;
    }
}
