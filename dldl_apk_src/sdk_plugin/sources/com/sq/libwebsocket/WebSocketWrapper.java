package com.sq.libwebsocket;

import android.text.TextUtils;
import com.sq.libwebsocket.request.Request;
import com.sq.libwebsocket.response.Response;
import com.sq.libwebsocket.response.ResponseFactory;
import com.sq.libwebsocket.util.LogUtil;
import com.sq.sywebsocket.WebSocket;
import com.sq.sywebsocket.client.WebSocketClient;
import com.sq.sywebsocket.drafts.Draft;
import com.sq.sywebsocket.drafts.Draft_6455;
import com.sq.sywebsocket.exceptions.WebsocketNotConnectedException;
import com.sq.sywebsocket.framing.Framedata;
import com.sq.sywebsocket.handshake.ServerHandshake;
import com.sqwan.liveshow.huya.SqR;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebSocketWrapper {
    public static int CONNECTSTATUS_CONNECTED = 2;
    public static int CONNECTSTATUS_CONNECTING = 1;
    public static int CONNECTSTATUS_UNCONNECT = 0;
    private static final String TAG = "WebSocketWrapper";
    private WebSocketSetting mSetting;
    private SocketWrapperListener mSocketListener;
    private WebSocketClient mWebSocket;
    private volatile int connectStatus = CONNECTSTATUS_UNCONNECT;
    private volatile boolean needClose = false;
    private volatile boolean destroyed = false;

    WebSocketWrapper(WebSocketSetting webSocketSetting, SocketWrapperListener socketWrapperListener) {
        this.mSetting = webSocketSetting;
        this.mSocketListener = socketWrapperListener;
    }

    void connect() {
        if (this.destroyed) {
            return;
        }
        this.needClose = false;
        if (this.connectStatus == CONNECTSTATUS_UNCONNECT) {
            this.connectStatus = CONNECTSTATUS_CONNECTING;
            try {
                if (this.mWebSocket == null) {
                    if (TextUtils.isEmpty(this.mSetting.getConnectUrl())) {
                        throw new RuntimeException("WebSocket connect url is empty!");
                    }
                    Draft draft = this.mSetting.getDraft();
                    if (draft == null) {
                        draft = new Draft_6455();
                    }
                    Draft draft2 = draft;
                    int connectTimeout = this.mSetting.getConnectTimeout();
                    this.mWebSocket = new MyWebSocketClient(new URI(this.mSetting.getConnectUrl()), draft2, this.mSetting.getHttpHeaders(), connectTimeout <= 0 ? 0 : connectTimeout);
                    LogUtil.i(TAG, "WebSocket start connect...");
                    if (this.mSetting.getProxy() != null) {
                        this.mWebSocket.setProxy(this.mSetting.getProxy());
                    }
                    this.mWebSocket.connect();
                    this.mWebSocket.setConnectionLostTimeout(this.mSetting.getConnectionLostTimeout());
                    if (this.needClose) {
                        disConnect();
                    }
                    checkDestroy();
                    return;
                }
                LogUtil.i(TAG, "WebSocket reconnecting...");
                this.mWebSocket.reconnect();
                if (this.needClose) {
                    disConnect();
                }
                checkDestroy();
            } catch (Throwable th) {
                this.connectStatus = CONNECTSTATUS_UNCONNECT;
                LogUtil.e(TAG, "WebSocket connect failed:", th);
                SocketWrapperListener socketWrapperListener = this.mSocketListener;
                if (socketWrapperListener != null) {
                    socketWrapperListener.onConnectFailed(th);
                }
            }
        }
    }

    void reconnect() {
        this.needClose = false;
        if (this.connectStatus == CONNECTSTATUS_UNCONNECT) {
            connect();
        }
    }

    void disConnect() {
        this.needClose = true;
        if (this.connectStatus == CONNECTSTATUS_CONNECTED) {
            LogUtil.i(TAG, "WebSocket disconnecting...");
            WebSocketClient webSocketClient = this.mWebSocket;
            if (webSocketClient != null) {
                webSocketClient.close();
            }
            LogUtil.i(TAG, "WebSocket disconnected");
        }
    }

    void send(Request request) {
        if (this.mWebSocket == null) {
            return;
        }
        if (request == null) {
            LogUtil.e(TAG, "send data is null!");
            return;
        }
        try {
            if (this.connectStatus == CONNECTSTATUS_CONNECTED) {
                try {
                    try {
                        request.send(this.mWebSocket);
                    } catch (WebsocketNotConnectedException e) {
                        this.connectStatus = CONNECTSTATUS_UNCONNECT;
                        LogUtil.e(TAG, "ws is disconnected, send failed:" + request.toString(), e);
                        if (this.mSocketListener != null) {
                            this.mSocketListener.onSendDataError(request, 0, e);
                            this.mSocketListener.onDisconnect();
                        }
                    }
                } finally {
                }
                request.release();
                return;
            }
            SocketWrapperListener socketWrapperListener = this.mSocketListener;
            if (socketWrapperListener != null) {
                socketWrapperListener.onSendDataError(request, 0, null);
            }
        } catch (Throwable th) {
            request.release();
        }
    }

    int getConnectState() {
        return this.connectStatus;
    }

    void destroy() {
        this.destroyed = true;
        disConnect();
        if (this.connectStatus == CONNECTSTATUS_UNCONNECT) {
            this.mWebSocket = null;
        }
        releaseResource();
    }

    private void checkDestroy() {
        if (this.destroyed) {
            try {
                if (this.mWebSocket != null && !this.mWebSocket.isClosed()) {
                    this.mWebSocket.close();
                }
                releaseResource();
                this.connectStatus = CONNECTSTATUS_UNCONNECT;
            } catch (Throwable th) {
                LogUtil.e(TAG, "checkDestroy(WebSocketClient)", th);
            }
        }
    }

    private void releaseResource() {
        if (this.mSocketListener != null) {
            this.mSocketListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackOpen(ServerHandshake serverHandshake) {
        if (this.destroyed) {
            checkDestroy();
            return;
        }
        this.connectStatus = CONNECTSTATUS_CONNECTED;
        LogUtil.i(TAG, "WebSocket connect success");
        if (this.needClose) {
            disConnect();
            return;
        }
        SocketWrapperListener socketWrapperListener = this.mSocketListener;
        if (socketWrapperListener != null) {
            socketWrapperListener.onConnected();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackMessage(String str) {
        if (this.destroyed) {
            checkDestroy();
            return;
        }
        this.connectStatus = CONNECTSTATUS_CONNECTED;
        if (this.mSocketListener != null) {
            Response<String> responseCreateTextResponse = ResponseFactory.createTextResponse();
            responseCreateTextResponse.setResponseData(str);
            LogUtil.i(TAG, "WebSocket received message:" + responseCreateTextResponse.toString());
            this.mSocketListener.onMessage(responseCreateTextResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackMessage(ByteBuffer byteBuffer) {
        if (this.destroyed) {
            checkDestroy();
            return;
        }
        this.connectStatus = CONNECTSTATUS_CONNECTED;
        if (this.mSocketListener != null) {
            Response<ByteBuffer> responseCreateByteBufferResponse = ResponseFactory.createByteBufferResponse();
            responseCreateByteBufferResponse.setResponseData(byteBuffer);
            LogUtil.i(TAG, "WebSocket received message:" + responseCreateByteBufferResponse.toString());
            this.mSocketListener.onMessage(responseCreateByteBufferResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackWebsocketPing(Framedata framedata) {
        if (this.destroyed) {
            checkDestroy();
            return;
        }
        this.connectStatus = CONNECTSTATUS_CONNECTED;
        if (this.mSocketListener != null) {
            Response<Framedata> responseCreatePingResponse = ResponseFactory.createPingResponse();
            responseCreatePingResponse.setResponseData(framedata);
            LogUtil.i(TAG, "WebSocket received ping:" + responseCreatePingResponse.toString());
            this.mSocketListener.onMessage(responseCreatePingResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackWebsocketPong(Framedata framedata) {
        if (this.destroyed) {
            checkDestroy();
            return;
        }
        this.connectStatus = CONNECTSTATUS_CONNECTED;
        if (this.mSocketListener != null) {
            Response<Framedata> responseCreatePongResponse = ResponseFactory.createPongResponse();
            responseCreatePongResponse.setResponseData(framedata);
            LogUtil.i(TAG, "WebSocket received pong:" + responseCreatePongResponse.toString());
            this.mSocketListener.onMessage(responseCreatePongResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackClose(int i, String str, boolean z) {
        this.connectStatus = CONNECTSTATUS_UNCONNECT;
        LogUtil.d(TAG, String.format("WebSocket closed!code=%s,reason:%s,remote:%s", Integer.valueOf(i), str, Boolean.valueOf(z)));
        SocketWrapperListener socketWrapperListener = this.mSocketListener;
        if (socketWrapperListener != null) {
            socketWrapperListener.onDisconnect();
        }
        checkDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackError(Exception exc) {
        if (this.destroyed) {
            checkDestroy();
        } else {
            LogUtil.e(TAG, "WebSocketClient#onError(Exception)", exc);
        }
    }

    private class MyWebSocketClient extends WebSocketClient {
        public MyWebSocketClient(URI uri) {
            super(uri);
        }

        public MyWebSocketClient(URI uri, Draft draft) {
            super(uri, draft);
        }

        public MyWebSocketClient(URI uri, Map<String, String> map) {
            super(uri, map);
        }

        public MyWebSocketClient(URI uri, Draft draft, Map<String, String> map) {
            super(uri, draft, map);
        }

        public MyWebSocketClient(URI uri, Draft draft, Map<String, String> map, int i) {
            super(uri, draft, map, i);
        }

        @Override // com.sq.sywebsocket.client.WebSocketClient
        public void onOpen(ServerHandshake serverHandshake) {
            WebSocketWrapper.this.onWSCallbackOpen(serverHandshake);
        }

        @Override // com.sq.sywebsocket.client.WebSocketClient
        public void onMessage(String str) {
            WebSocketWrapper.this.onWSCallbackMessage(str);
        }

        @Override // com.sq.sywebsocket.client.WebSocketClient
        public void onMessage(ByteBuffer byteBuffer) {
            LogUtil.i(WebSocketWrapper.TAG, "onMessage");
            WebSocketWrapper.this.onWSCallbackMessage(byteBuffer);
        }

        @Override // com.sq.sywebsocket.WebSocketAdapter, com.sq.sywebsocket.WebSocketListener
        public void onWebsocketPing(WebSocket webSocket, Framedata framedata) {
            super.onWebsocketPing(webSocket, framedata);
            WebSocketWrapper.this.onWSCallbackWebsocketPing(framedata);
        }

        @Override // com.sq.sywebsocket.WebSocketAdapter, com.sq.sywebsocket.WebSocketListener
        public void onWebsocketPong(WebSocket webSocket, Framedata framedata) {
            super.onWebsocketPong(webSocket, framedata);
            WebSocketWrapper.this.onWSCallbackWebsocketPong(framedata);
        }

        @Override // com.sq.sywebsocket.client.WebSocketClient
        public void onClose(int i, String str, boolean z) {
            WebSocketWrapper.this.onWSCallbackClose(i, str, z);
        }

        @Override // com.sq.sywebsocket.client.WebSocketClient
        public void onError(Exception exc) {
            WebSocketWrapper.this.onWSCallbackError(exc);
        }

        @Override // com.sq.sywebsocket.client.WebSocketClient, com.sq.sywebsocket.WebSocket
        public void send(byte[] bArr) {
            super.send(bArr);
            LogUtil.i(WebSocketWrapper.TAG, SqR.string.send);
        }
    }
}
