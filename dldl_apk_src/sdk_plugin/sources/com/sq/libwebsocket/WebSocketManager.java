package com.sq.libwebsocket;

import android.text.TextUtils;
import com.sq.libwebsocket.ReconnectManager;
import com.sq.libwebsocket.dispatcher.MainThreadResponseDelivery;
import com.sq.libwebsocket.dispatcher.ResponseDelivery;
import com.sq.libwebsocket.dispatcher.ResponseProcessEngine;
import com.sq.libwebsocket.request.Request;
import com.sq.libwebsocket.request.RequestFactory;
import com.sq.libwebsocket.response.ErrorResponse;
import com.sq.libwebsocket.response.Response;
import com.sq.libwebsocket.response.ResponseFactory;
import com.sq.libwebsocket.util.LogUtil;
import com.sq.sywebsocket.framing.Framedata;
import com.sq.sywebsocket.framing.PingFrame;
import java.nio.ByteBuffer;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebSocketManager {
    private static final String TAG = "WebSocketManager";
    private volatile boolean destroyed = false;
    private volatile boolean disconnect = false;
    private volatile ResponseDelivery mDelivery;
    private volatile ReconnectManager mReconnectManager;
    private volatile ResponseProcessEngine mResponseProcessEngine;
    private WebSocketSetting mSetting;
    private SocketWrapperListener mSocketWrapperListener;
    private WebSocketWrapper mWebSocket;
    private volatile WebSocketEngine mWebSocketEngine;

    WebSocketManager(WebSocketSetting webSocketSetting, WebSocketEngine webSocketEngine, ResponseProcessEngine responseProcessEngine) {
        this.mSetting = webSocketSetting;
        this.mWebSocketEngine = webSocketEngine;
        this.mResponseProcessEngine = responseProcessEngine;
        this.mDelivery = this.mSetting.getResponseDelivery();
        if (this.mDelivery == null) {
            this.mDelivery = new MainThreadResponseDelivery();
        }
        SocketWrapperListener socketWrapperListener = getSocketWrapperListener();
        this.mSocketWrapperListener = socketWrapperListener;
        if (this.mWebSocket == null) {
            this.mWebSocket = new WebSocketWrapper(this.mSetting, socketWrapperListener);
        }
        start();
    }

    public WebSocketManager start() {
        if (this.mWebSocket == null) {
            this.mWebSocket = new WebSocketWrapper(this.mSetting, this.mSocketWrapperListener);
        }
        if (this.mWebSocket.getConnectState() == WebSocketWrapper.CONNECTSTATUS_UNCONNECT) {
            reconnect();
        }
        return this;
    }

    public boolean isConnect() {
        WebSocketWrapper webSocketWrapper = this.mWebSocket;
        return webSocketWrapper != null && webSocketWrapper.getConnectState() == WebSocketWrapper.CONNECTSTATUS_CONNECTED;
    }

    public void setReconnectManager(ReconnectManager reconnectManager) {
        this.mReconnectManager = reconnectManager;
    }

    public WebSocketManager reconnect() {
        this.disconnect = false;
        if (this.mReconnectManager == null) {
            this.mReconnectManager = getDefaultReconnectManager();
        }
        if (!this.mReconnectManager.reconnecting()) {
            this.mReconnectManager.startReconnect();
        }
        return this;
    }

    public WebSocketManager reconnect(WebSocketSetting webSocketSetting) {
        this.disconnect = false;
        if (this.destroyed) {
            LogUtil.e(TAG, "This WebSocketManager is destroyed!");
            return this;
        }
        this.mSetting = webSocketSetting;
        WebSocketWrapper webSocketWrapper = this.mWebSocket;
        if (webSocketWrapper != null) {
            webSocketWrapper.destroy();
            this.mWebSocket = null;
        }
        start();
        return this;
    }

    public WebSocketManager disConnect() {
        this.disconnect = true;
        if (this.destroyed) {
            LogUtil.e(TAG, "This WebSocketManager is destroyed!");
            return this;
        }
        if (this.mWebSocket.getConnectState() != 0) {
            this.mWebSocketEngine.disConnect(this.mWebSocket, this.mSocketWrapperListener);
        }
        return this;
    }

    public void send(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Request<String> requestCreateStringRequest = RequestFactory.createStringRequest();
        requestCreateStringRequest.setRequestData(str);
        sendRequest(requestCreateStringRequest);
    }

    public void send(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        Request<byte[]> requestCreateByteArrayRequest = RequestFactory.createByteArrayRequest();
        requestCreateByteArrayRequest.setRequestData(bArr);
        sendRequest(requestCreateByteArrayRequest);
    }

    public void send(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return;
        }
        Request<ByteBuffer> requestCreateByteBufferRequest = RequestFactory.createByteBufferRequest();
        requestCreateByteBufferRequest.setRequestData(byteBuffer);
        sendRequest(requestCreateByteBufferRequest);
    }

    public void sendPing() {
        sendRequest(RequestFactory.createPingRequest());
    }

    public void sendPong() {
        sendRequest(RequestFactory.createPongRequest());
    }

    public void sendPong(PingFrame pingFrame) {
        if (pingFrame == null) {
            return;
        }
        Request<PingFrame> requestCreatePongRequest = RequestFactory.createPongRequest();
        requestCreatePongRequest.setRequestData(pingFrame);
        sendRequest(requestCreatePongRequest);
    }

    public void sendFrame(Framedata framedata) {
        if (framedata == null) {
            return;
        }
        Request<Framedata> requestCreateFrameDataRequest = RequestFactory.createFrameDataRequest();
        requestCreateFrameDataRequest.setRequestData(framedata);
        sendRequest(requestCreateFrameDataRequest);
    }

    public void sendFrame(Collection<Framedata> collection) {
        if (collection == null) {
            return;
        }
        Request<Collection<Framedata>> requestCreateCollectionFrameRequest = RequestFactory.createCollectionFrameRequest();
        requestCreateCollectionFrameRequest.setRequestData(collection);
        sendRequest(requestCreateCollectionFrameRequest);
    }

    public WebSocketManager addListener(SocketListener socketListener) {
        this.mDelivery.addListener(socketListener);
        return this;
    }

    public WebSocketManager removeListener(SocketListener socketListener) {
        this.mDelivery.removeListener(socketListener);
        return this;
    }

    public WebSocketSetting getSetting() {
        return this.mSetting;
    }

    public void destroy() {
        this.destroyed = true;
        if (this.mWebSocket != null) {
            if (this.mWebSocketEngine != null) {
                this.mWebSocketEngine.destroyWebSocket(this.mWebSocket);
                this.mWebSocketEngine = null;
            }
            this.mWebSocket = null;
        }
        if (this.mDelivery != null) {
            if (!this.mDelivery.isEmpty()) {
                this.mDelivery.clear();
            }
            this.mDelivery.destroy();
        }
        if (this.mReconnectManager != null) {
            if (this.mReconnectManager.reconnecting()) {
                this.mReconnectManager.stopReconnect();
            }
            this.mReconnectManager = null;
        }
    }

    void reconnectOnce() {
        if (this.destroyed) {
            LogUtil.e(TAG, "This WebSocketManager is destroyed!");
        } else {
            if (this.mWebSocket.getConnectState() == WebSocketWrapper.CONNECTSTATUS_UNCONNECT) {
                this.mWebSocketEngine.connect(this.mWebSocket, this.mSocketWrapperListener);
                return;
            }
            if (this.mReconnectManager != null) {
                this.mReconnectManager.onConnected();
            }
            LogUtil.e(TAG, "WebSocket 已连接，请勿重试。");
        }
    }

    private void sendRequest(Request request) {
        if (this.destroyed) {
            LogUtil.e(TAG, "This WebSocketManager is destroyed!");
        } else {
            this.mWebSocketEngine.sendRequest(this.mWebSocket, request, this.mSocketWrapperListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ReconnectManager getDefaultReconnectManager() {
        return new DefaultReconnectManager(this, new ReconnectManager.OnConnectListener() { // from class: com.sq.libwebsocket.WebSocketManager.1
            @Override // com.sq.libwebsocket.ReconnectManager.OnConnectListener
            public void onConnected() {
                LogUtil.i(WebSocketManager.TAG, "重连成功");
            }

            @Override // com.sq.libwebsocket.ReconnectManager.OnConnectListener
            public void onDisconnect() {
                LogUtil.i(WebSocketManager.TAG, "重连失败");
                WebSocketManager.this.mSetting.getResponseDispatcher().onDisconnect(WebSocketManager.this.mDelivery);
            }
        });
    }

    private SocketWrapperListener getSocketWrapperListener() {
        return new SocketWrapperListener() { // from class: com.sq.libwebsocket.WebSocketManager.2
            @Override // com.sq.libwebsocket.SocketWrapperListener
            public void onConnected() {
                if (WebSocketManager.this.mReconnectManager != null) {
                    WebSocketManager.this.mReconnectManager.onConnected();
                }
                WebSocketManager.this.mSetting.getResponseDispatcher().onConnected(WebSocketManager.this.mDelivery);
            }

            @Override // com.sq.libwebsocket.SocketWrapperListener
            public void onConnectFailed(Throwable th) {
                if (WebSocketManager.this.mReconnectManager != null && WebSocketManager.this.mReconnectManager.reconnecting()) {
                    WebSocketManager.this.mReconnectManager.onConnectError(th);
                }
                WebSocketManager.this.mSetting.getResponseDispatcher().onConnectFailed(th, WebSocketManager.this.mDelivery);
            }

            @Override // com.sq.libwebsocket.SocketWrapperListener
            public void onDisconnect() {
                WebSocketManager.this.mSetting.getResponseDispatcher().onDisconnect(WebSocketManager.this.mDelivery);
                if (WebSocketManager.this.mReconnectManager == null || !WebSocketManager.this.mReconnectManager.reconnecting()) {
                    if (WebSocketManager.this.disconnect) {
                        return;
                    }
                    if (WebSocketManager.this.mReconnectManager == null) {
                        WebSocketManager webSocketManager = WebSocketManager.this;
                        webSocketManager.mReconnectManager = webSocketManager.getDefaultReconnectManager();
                    }
                    WebSocketManager.this.mReconnectManager.onConnectError(null);
                    WebSocketManager.this.mReconnectManager.startReconnect();
                    return;
                }
                if (WebSocketManager.this.disconnect) {
                    WebSocketManager.this.mSetting.getResponseDispatcher().onDisconnect(WebSocketManager.this.mDelivery);
                } else {
                    WebSocketManager.this.mReconnectManager.onConnectError(null);
                }
            }

            @Override // com.sq.libwebsocket.SocketWrapperListener
            public void onSendDataError(Request request, int i, Throwable th) {
                ErrorResponse errorResponseCreateErrorResponse = ResponseFactory.createErrorResponse();
                errorResponseCreateErrorResponse.init(request, i, th);
                if (!WebSocketManager.this.mSetting.processDataOnBackground()) {
                    WebSocketManager.this.mSetting.getResponseDispatcher().onSendDataError(errorResponseCreateErrorResponse, WebSocketManager.this.mDelivery);
                } else {
                    WebSocketManager.this.mResponseProcessEngine.onSendDataError(errorResponseCreateErrorResponse, WebSocketManager.this.mSetting.getResponseDispatcher(), WebSocketManager.this.mDelivery);
                }
                if (WebSocketManager.this.disconnect || i != 0) {
                    return;
                }
                LogUtil.e(WebSocketManager.TAG, "数据发送失败，网络未连接，开始重连。。。");
                WebSocketManager.this.reconnect();
            }

            @Override // com.sq.libwebsocket.SocketWrapperListener
            public void onMessage(Response response) {
                LogUtil.i(WebSocketManager.TAG, "onMessage");
                if (!WebSocketManager.this.mSetting.processDataOnBackground()) {
                    response.onResponse(WebSocketManager.this.mSetting.getResponseDispatcher(), WebSocketManager.this.mDelivery);
                } else {
                    WebSocketManager.this.mResponseProcessEngine.onMessageReceive(response, WebSocketManager.this.mSetting.getResponseDispatcher(), WebSocketManager.this.mDelivery);
                }
            }
        };
    }
}
