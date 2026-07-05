package com.sq.libwebsocket;

import com.sq.libwebsocket.ReconnectManager;
import com.sq.libwebsocket.util.LogUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DefaultReconnectManager implements ReconnectManager {
    private static final String TAG = "DefaultReconnectManager";
    private ReconnectManager.OnConnectListener mOnDisconnectListener;
    private WebSocketManager mWebSocketManager;
    private final Object BLOCK = new Object();
    private volatile boolean needStopReconnect = false;
    private volatile boolean connected = false;
    private final ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
    private AtomicInteger reconnectCount = new AtomicInteger(1);
    private AtomicInteger finishCount = new AtomicInteger(1);
    private volatile boolean reconnecting = false;
    private volatile boolean destroyed = false;

    public DefaultReconnectManager(WebSocketManager webSocketManager, ReconnectManager.OnConnectListener onConnectListener) {
        this.mWebSocketManager = webSocketManager;
        this.mOnDisconnectListener = onConnectListener;
    }

    @Override // com.sq.libwebsocket.ReconnectManager
    public boolean reconnecting() {
        return this.reconnecting;
    }

    @Override // com.sq.libwebsocket.ReconnectManager
    public void startReconnect() {
        if (this.reconnecting) {
            LogUtil.i(TAG, "Reconnecting, do not call again.");
            return;
        }
        if (this.destroyed) {
            LogUtil.e(TAG, "ReconnectManager is destroyed!!!");
            return;
        }
        this.needStopReconnect = false;
        this.reconnecting = true;
        try {
            this.singleThreadPool.execute(getReconnectRunnable());
        } catch (RejectedExecutionException e) {
            LogUtil.e(TAG, "线程队列已满，无法执行此次任务。", e);
            this.reconnecting = false;
        }
    }

    private Runnable getReconnectRunnable() {
        return new Runnable() { // from class: com.sq.libwebsocket.DefaultReconnectManager.1
            /* JADX WARN: Code restructure failed: missing block: B:24:0x00f1, code lost:
            
                java.lang.Thread.sleep(3000);
             */
            /* JADX WARN: Code restructure failed: missing block: B:26:0x00f6, code lost:
            
                r4 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:27:0x00f7, code lost:
            
                r4.printStackTrace();
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 389
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sq.libwebsocket.DefaultReconnectManager.AnonymousClass1.run():void");
            }
        };
    }

    @Override // com.sq.libwebsocket.ReconnectManager
    public void stopReconnect() {
        this.needStopReconnect = true;
        ExecutorService executorService = this.singleThreadPool;
        if (executorService != null) {
            executorService.shutdownNow();
        }
    }

    @Override // com.sq.libwebsocket.ReconnectManager
    public void onConnected() {
        this.connected = true;
        synchronized (this.BLOCK) {
            LogUtil.i(TAG, "onConnected()->BLOCK.notifyAll()");
            this.BLOCK.notifyAll();
        }
    }

    @Override // com.sq.libwebsocket.ReconnectManager
    public void onConnectError(Throwable th) {
        this.connected = false;
        synchronized (this.BLOCK) {
            LogUtil.i(TAG, "onConnectError(Throwable)->BLOCK.notifyAll()");
            this.BLOCK.notifyAll();
        }
    }

    @Override // com.sq.libwebsocket.ReconnectManager
    public void destroy() {
        this.destroyed = true;
        stopReconnect();
        this.mWebSocketManager = null;
    }
}
