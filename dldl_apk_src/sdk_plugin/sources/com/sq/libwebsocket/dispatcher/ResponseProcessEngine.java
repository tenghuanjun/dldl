package com.sq.libwebsocket.dispatcher;

import com.sq.libwebsocket.response.ErrorResponse;
import com.sq.libwebsocket.response.Response;
import com.sq.libwebsocket.util.LogUtil;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResponseProcessEngine {
    private static final String TAG = "ResponseProcessEngine";
    private EngineThread mThread;

    public ResponseProcessEngine() {
        EngineThread engineThread = new EngineThread();
        this.mThread = engineThread;
        engineThread.start();
    }

    public void onMessageReceive(Response response, IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery) {
        LogUtil.i(TAG, "onMessageReceive 1");
        if (response == null || iResponseDispatcher == null || responseDelivery == null) {
            return;
        }
        LogUtil.i(TAG, "onMessageReceive 2");
        EngineEntity engineEntityObtain = EngineEntity.obtain();
        engineEntityObtain.dispatcher = iResponseDispatcher;
        engineEntityObtain.delivery = responseDelivery;
        engineEntityObtain.isError = false;
        engineEntityObtain.response = response;
        engineEntityObtain.errorResponse = null;
        this.mThread.add(engineEntityObtain);
    }

    public void onSendDataError(ErrorResponse errorResponse, IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery) {
        if (errorResponse == null || iResponseDispatcher == null || responseDelivery == null) {
            return;
        }
        EngineEntity engineEntityObtain = EngineEntity.obtain();
        engineEntityObtain.dispatcher = iResponseDispatcher;
        engineEntityObtain.delivery = responseDelivery;
        engineEntityObtain.isError = true;
        engineEntityObtain.errorResponse = errorResponse;
        engineEntityObtain.response = null;
        this.mThread.add(engineEntityObtain);
    }

    static class EngineEntity {
        private static Queue<EngineEntity> ENTITY_POOL = new ArrayDeque(10);
        ResponseDelivery delivery;
        IResponseDispatcher dispatcher;
        ErrorResponse errorResponse;
        boolean isError;
        Response response;

        EngineEntity() {
        }

        static EngineEntity obtain() {
            EngineEntity engineEntityPoll = ENTITY_POOL.poll();
            return engineEntityPoll == null ? new EngineEntity() : engineEntityPoll;
        }

        static void release(EngineEntity engineEntity) {
            ENTITY_POOL.offer(engineEntity);
        }
    }
}
