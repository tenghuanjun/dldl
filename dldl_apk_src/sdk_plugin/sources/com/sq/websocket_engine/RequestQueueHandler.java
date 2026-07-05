package com.sq.websocket_engine;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.huya.berry.gamesdk.module.ICommonConstants;
import com.sq.websocket_engine.parse.RequestDataParse;
import com.sq.websocket_engine.parse.ResponseDataParse;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.task.Task;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RequestQueueHandler {
    private static final String TAG = "RequestQueueHandler";
    private static final RequestQueueHandler ourInstance = new RequestQueueHandler();
    private Handler handler;
    private int MSG_REQUEST_SEND = 0;
    private int MSG_REQUEST_RECEIVE = 1;
    private final byte[] lock = new byte[0];
    private ConcurrentHashMap<RequestPriority, Vector<RequestDataParse>> requestQueueMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, RequestDataParse> requestSendingMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, Task> requestSendingTimerMap = new ConcurrentHashMap<>();
    private final List<IReciveInf> iReciveInfs = new ArrayList();
    private volatile boolean isLocalConnected = false;

    public interface IReciveInf {
        void onRecive(ResponseDataParse responseDataParse);
    }

    public interface IRequestCallback<T> {
        void onFalid(T t);

        void onSuccess(T t);
    }

    public enum RequestPriority {
        DEFAULT,
        SUPER,
        HIGH,
        LOW
    }

    public static RequestQueueHandler getInstance() {
        return ourInstance;
    }

    private RequestQueueHandler() {
        initHandler();
    }

    public boolean isLocalConnected() {
        return this.isLocalConnected;
    }

    public void setLocalConnected(boolean z) {
        this.isLocalConnected = z;
    }

    public void registerReciveInf(IReciveInf iReciveInf) {
        LogUtil.i(TAG, "registerReciveInf");
        this.iReciveInfs.add(iReciveInf);
    }

    public void unRegisterReciveInf(IReciveInf iReciveInf) {
        LogUtil.i(TAG, "unRegisterReciveInf");
        this.iReciveInfs.remove(iReciveInf);
    }

    private void handleInf(ResponseDataParse responseDataParse) {
        LogUtil.i(TAG, "iReciveInfs size:" + this.iReciveInfs.size());
        for (IReciveInf iReciveInf : this.iReciveInfs) {
            if (iReciveInf != null) {
                if (responseDataParse == null) {
                    LogUtil.i(TAG, "responseDataParse==null return");
                    return;
                } else {
                    if (responseDataParse.body == null) {
                        LogUtil.i(TAG, "responseDataParse.body==null return");
                        return;
                    }
                    iReciveInf.onRecive(responseDataParse);
                }
            }
        }
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread("Conn_Send_Thread");
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper()) { // from class: com.sq.websocket_engine.RequestQueueHandler.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i != RequestQueueHandler.this.MSG_REQUEST_SEND) {
                    if (i == RequestQueueHandler.this.MSG_REQUEST_RECEIVE) {
                        RequestQueueHandler.this.handleMessageData((ByteBuffer) message.obj);
                        return;
                    }
                    return;
                }
                RequestDataParse requestDataParse = (RequestDataParse) message.obj;
                requestDataParse.sendTime = System.currentTimeMillis();
                final int i2 = requestDataParse.seq;
                int i3 = requestDataParse.timeout;
                Task taskCreate = Task.create();
                taskCreate.oneShot(i3, new Runnable() { // from class: com.sq.websocket_engine.RequestQueueHandler.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RequestQueueHandler.this.handleRequestTimeout(i2);
                    }
                });
                RequestQueueHandler.this.requestSendingTimerMap.put(Integer.valueOf(i2), taskCreate);
                if (WebSocketCenter.getInstance().getManager() != null) {
                    WebSocketCenter.getInstance().getManager().send(requestDataParse.pack());
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRequestTimeout(int i) {
        if (this.requestSendingTimerMap.get(Integer.valueOf(i)) != null) {
            this.requestSendingTimerMap.remove(Integer.valueOf(i));
            if (!this.requestSendingMap.containsKey(Integer.valueOf(i))) {
                return;
            }
            RequestDataParse requestDataParse = this.requestSendingMap.get(Integer.valueOf(i));
            this.requestSendingMap.remove(Integer.valueOf(i));
            if (requestDataParse.requestCallback != null) {
                requestDataParse.requestCallback.onFalid(null);
            }
        }
        trySendNextRequest();
    }

    private void trySendNextRequest() {
        RequestDataParse nextRequest = getNextRequest();
        if (nextRequest == null) {
            LogUtil.i(TAG, "trySendNextRequest nextRequest null");
        } else {
            this.requestSendingMap.put(Integer.valueOf(nextRequest.seq), nextRequest);
            sendSocketMessage(nextRequest);
        }
    }

    private void sendSocketMessage(RequestDataParse requestDataParse) {
        Message messageObtain = Message.obtain();
        messageObtain.what = this.MSG_REQUEST_SEND;
        messageObtain.obj = requestDataParse;
        this.handler.sendMessage(messageObtain);
    }

    private void addRequest(RequestDataParse requestDataParse) {
        RequestPriority requestPriority = requestDataParse.requestPriority;
        Vector<RequestDataParse> vector = this.requestQueueMap.get(requestPriority);
        if (vector == null) {
            vector = new Vector<>();
        }
        vector.add(requestDataParse);
        this.requestQueueMap.put(requestPriority, vector);
    }

    private synchronized RequestDataParse getNextRequest() {
        RequestDataParse nextRequestWithPriority;
        nextRequestWithPriority = getNextRequestWithPriority(RequestPriority.SUPER);
        if (nextRequestWithPriority == null) {
            nextRequestWithPriority = getNextRequestWithPriority(RequestPriority.HIGH);
        }
        if (nextRequestWithPriority == null) {
            nextRequestWithPriority = getNextRequestWithPriority(RequestPriority.DEFAULT);
        }
        if (nextRequestWithPriority == null) {
            nextRequestWithPriority = getNextRequestWithPriority(RequestPriority.LOW);
        }
        return nextRequestWithPriority;
    }

    private RequestDataParse getNextRequestWithPriority(RequestPriority requestPriority) {
        Vector<RequestDataParse> vector = this.requestQueueMap.get(requestPriority);
        if (vector == null) {
            vector = new Vector<>();
        }
        if (vector.size() == 0) {
            return null;
        }
        return vector.remove(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMessageData(ByteBuffer byteBuffer) {
        LogUtil.i(TAG, "handleMessageData 1");
        ResponseDataParse responseDataParse = new ResponseDataParse().parse(byteBuffer);
        if (responseDataParse != null) {
            handleResponseData(responseDataParse);
        }
    }

    private void handleResponseData(ResponseDataParse responseDataParse) {
        LogUtil.i(TAG, "handleMessageData 2");
        int i = responseDataParse.seq;
        int i2 = responseDataParse.op;
        String str = responseDataParse.body.ev;
        responseDataParse.isSuccess();
        LogUtil.i(TAG, "responseDataParse:" + responseDataParse.toString());
        RequestDataParse requestDataParse = this.requestSendingMap.get(Integer.valueOf(i));
        if (requestDataParse == null) {
            handleInf(responseDataParse);
            return;
        }
        this.requestSendingMap.remove(Integer.valueOf(i));
        if (requestDataParse.requestCallback != null) {
            try {
                if (responseDataParse.body.isSuccess()) {
                    LogUtil.e(TAG, "onSuccess");
                    LogUtil.e(TAG, "responseDataParse:" + responseDataParse);
                    requestDataParse.requestCallback.onSuccess(responseDataParse);
                } else {
                    LogUtil.e(TAG, "onFalid");
                    requestDataParse.requestCallback.onFalid(responseDataParse);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e(TAG, "onFalid");
                requestDataParse.requestCallback.onFalid(responseDataParse);
            }
        }
        trySendNextRequest();
    }

    public void receiveMessageData(ByteBuffer byteBuffer) {
        LogUtil.i(TAG, "receiveMessageData");
        Message messageObtain = Message.obtain();
        messageObtain.obj = byteBuffer;
        messageObtain.what = this.MSG_REQUEST_RECEIVE;
        this.handler.sendMessage(messageObtain);
    }

    public void sendMessage(MsgBaseReq msgBaseReq, IRequestCallback<ResponseDataParse> iRequestCallback) {
        synchronized (this.lock) {
            LogUtil.i(TAG, ICommonConstants.FuncName.SEND_MESSAGE);
            RequestDataParse requestDataParse = new RequestDataParse(msgBaseReq);
            int i = requestDataParse.op;
            LogUtil.i(TAG, "sendMessage:" + requestDataParse.toString());
            requestDataParse.requestCallback = iRequestCallback;
            if (!isLocalConnected()) {
                iRequestCallback.onFalid(new ResponseDataParse());
            } else {
                addRequest(requestDataParse);
                trySendNextRequest();
            }
        }
    }

    public void release() {
        synchronized (this.lock) {
            Iterator<Task> it = this.requestSendingTimerMap.values().iterator();
            while (it.hasNext()) {
                it.next().stop();
            }
            this.requestSendingTimerMap.clear();
            Iterator<Vector<RequestDataParse>> it2 = this.requestQueueMap.values().iterator();
            while (it2.hasNext()) {
                it2.next().clear();
            }
            this.requestQueueMap.clear();
            this.requestSendingMap.clear();
            this.iReciveInfs.clear();
        }
    }
}
