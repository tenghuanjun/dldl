package com.sq.websocket_engine;

import com.sq.websocket_engine.ReqWrapperHandler;
import com.sq.websocket_engine.RequestQueueHandler;
import com.sq.websocket_engine.WebSocketCenter;
import com.sq.websocket_engine.parse.ResponseDataParse;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.task.TaskSubThread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebSocketEngine implements WebSocketCenter.WebSocketCenterListener, RequestQueueHandler.IReciveInf {
    private static final String TAG = "WebSocketEngine";
    private static final WebSocketEngine ourInstance = new WebSocketEngine();
    private static AtomicInteger authReqErrorTime = new AtomicInteger(0);
    private final int authReqErrorTimeMax = 5;
    private TaskSubThread heartBeatTask = TaskSubThread.create();
    private long heartBeatDuration = 15000;
    private List<ARecInfMsgBaseFactory> aRecInfMsgBaseFactories = new ArrayList();
    private List<WebSocketEngineCallback> webSocketEngineCallbacks = new ArrayList();
    private BaseRequestManager baseRequestManager = new BaseRequestManager();

    public interface WebSocketEngineCallback {
        void onAuth();

        void onReceiveInf(ARecInfMsg aRecInfMsg);
    }

    public static WebSocketEngine getInstance() {
        return ourInstance;
    }

    private WebSocketEngine() {
    }

    @Override // com.sq.websocket_engine.RequestQueueHandler.IReciveInf
    public void onRecive(ResponseDataParse responseDataParse) {
        Object objConvert;
        for (ARecInfMsgBaseFactory aRecInfMsgBaseFactory : this.aRecInfMsgBaseFactories) {
            if (aRecInfMsgBaseFactory != null && (objConvert = aRecInfMsgBaseFactory.convert(responseDataParse)) != null && (objConvert instanceof ARecInfMsg)) {
                ARecInfMsg aRecInfMsg = (ARecInfMsg) objConvert;
                if (aRecInfMsg.inf != 0) {
                    for (WebSocketEngineCallback webSocketEngineCallback : this.webSocketEngineCallbacks) {
                        if (webSocketEngineCallback != null) {
                            webSocketEngineCallback.onReceiveInf(aRecInfMsg);
                        }
                    }
                }
            }
        }
    }

    public class WebSocketEngineCallbackAdapter implements WebSocketEngineCallback {
        @Override // com.sq.websocket_engine.WebSocketEngine.WebSocketEngineCallback
        public void onAuth() {
        }

        @Override // com.sq.websocket_engine.WebSocketEngine.WebSocketEngineCallback
        public void onReceiveInf(ARecInfMsg aRecInfMsg) {
        }

        public WebSocketEngineCallbackAdapter() {
        }
    }

    public void registerWebSocketEngineCallback(WebSocketEngineCallback webSocketEngineCallback) {
        this.webSocketEngineCallbacks.add(webSocketEngineCallback);
    }

    public void unregisterWebSocketEngineCallback(WebSocketEngineCallback webSocketEngineCallback) {
        this.webSocketEngineCallbacks.remove(webSocketEngineCallback);
    }

    public void open() {
        LogUtil.i(TAG, "open start");
        WebSocketCenter.getInstance().initWebSocket(this);
        authReqErrorTime.set(0);
        RequestQueueHandler.getInstance().registerReciveInf(this);
        LogUtil.i(TAG, "open end");
    }

    public void close() {
        LogUtil.i(TAG, "close start");
        this.webSocketEngineCallbacks.clear();
        this.heartBeatTask.stop();
        RequestQueueHandler.getInstance().unRegisterReciveInf(this);
        RequestQueueHandler.getInstance().release();
        WebSocketCenter.getInstance().destroy();
        LogUtil.i(TAG, "close end");
    }

    @Override // com.sq.websocket_engine.WebSocketCenter.WebSocketCenterListener
    public void onNetworkChange(boolean z) {
        LogUtil.i(TAG, "onNetworkChange isConnected:" + z);
        if (!z) {
            this.heartBeatTask.stop();
        } else {
            authReqErrorTime.set(0);
        }
    }

    @Override // com.sq.websocket_engine.WebSocketCenter.WebSocketCenterListener
    public void onConnected() {
        LogUtil.i(TAG, "onConnected");
        if (authReqErrorTime.get() == 5) {
            LogUtil.i(TAG, "authReqErrorTimeMax");
        } else {
            LogUtil.i(TAG, "reqAuth");
            this.baseRequestManager.reqAuth(new ReqWrapperHandler.FinishListener<BodyData>() { // from class: com.sq.websocket_engine.WebSocketEngine.1
                @Override // com.sq.websocket_engine.ReqWrapperHandler.FinishListener
                public void on(BodyData bodyData) {
                    if (bodyData == null) {
                        WebSocketEngine.authReqErrorTime.incrementAndGet();
                        return;
                    }
                    LogUtil.i(WebSocketEngine.TAG, "reqAuth true");
                    if (bodyData.hb_time != 0) {
                        WebSocketEngine.this.heartBeatDuration = ((long) bodyData.hb_time) * 1000;
                    }
                    WebSocketEngine.authReqErrorTime.set(0);
                    WebSocketEngine.this.heartBeatTask.repeat(WebSocketEngine.this.heartBeatDuration, new TaskSubThread.TaskFunc() { // from class: com.sq.websocket_engine.WebSocketEngine.1.1
                        @Override // com.sqwan.common.util.task.TaskSubThread.TaskFunc
                        public TaskSubThread.Result exec() {
                            WebSocketEngine.this.baseRequestManager.reqHeartBeat(new ReqWrapperHandler.FinishListener<Boolean>() { // from class: com.sq.websocket_engine.WebSocketEngine.1.1.1
                                @Override // com.sq.websocket_engine.ReqWrapperHandler.FinishListener
                                public void on(Boolean bool) {
                                    LogUtil.i(WebSocketEngine.TAG, "reqHeartBeat result:" + bool);
                                }
                            });
                            return null;
                        }
                    });
                    for (WebSocketEngineCallback webSocketEngineCallback : WebSocketEngine.this.webSocketEngineCallbacks) {
                        if (webSocketEngineCallback != null) {
                            webSocketEngineCallback.onAuth();
                        }
                    }
                }
            });
        }
    }

    public void addARecInfMsgBaseFactory(ARecInfMsgBaseFactory aRecInfMsgBaseFactory) {
        this.aRecInfMsgBaseFactories.add(aRecInfMsgBaseFactory);
    }

    public void removeARecInfMsgBaseFactory(ARecInfMsgBaseFactory aRecInfMsgBaseFactory) {
        this.aRecInfMsgBaseFactories.remove(aRecInfMsgBaseFactory);
    }
}
