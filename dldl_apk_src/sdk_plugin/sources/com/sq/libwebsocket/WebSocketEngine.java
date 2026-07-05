package com.sq.libwebsocket;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sq.libwebsocket.request.Request;
import com.sq.libwebsocket.util.LogUtil;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebSocketEngine {
    private static final String TAG = "WSWebSocketEngine";
    private OptionThread mOptionThread;

    WebSocketEngine() {
        OptionThread optionThread = new OptionThread();
        this.mOptionThread = optionThread;
        optionThread.start();
    }

    void sendRequest(WebSocketWrapper webSocketWrapper, Request request, SocketWrapperListener socketWrapperListener) {
        if (this.mOptionThread.mHandler == null) {
            socketWrapperListener.onSendDataError(request, 2, null);
            return;
        }
        ReRunnable reRunnableObtain = ReRunnable.obtain();
        reRunnableObtain.type = 0;
        reRunnableObtain.request = request;
        reRunnableObtain.webSocketWrapper = webSocketWrapper;
        this.mOptionThread.mHandler.post(reRunnableObtain);
    }

    void connect(WebSocketWrapper webSocketWrapper, SocketWrapperListener socketWrapperListener) {
        if (this.mOptionThread.mHandler == null) {
            socketWrapperListener.onConnectFailed(new Exception("WebSocketEngine not start!"));
            return;
        }
        ReRunnable reRunnableObtain = ReRunnable.obtain();
        reRunnableObtain.type = 1;
        reRunnableObtain.webSocketWrapper = webSocketWrapper;
        this.mOptionThread.mHandler.post(reRunnableObtain);
    }

    void disConnect(WebSocketWrapper webSocketWrapper, SocketWrapperListener socketWrapperListener) {
        if (this.mOptionThread.mHandler != null) {
            ReRunnable reRunnableObtain = ReRunnable.obtain();
            reRunnableObtain.type = 2;
            reRunnableObtain.webSocketWrapper = webSocketWrapper;
            this.mOptionThread.mHandler.post(reRunnableObtain);
            return;
        }
        LogUtil.e(TAG, "WebSocketEngine not start!");
    }

    void destroyWebSocket(WebSocketWrapper webSocketWrapper) {
        if (this.mOptionThread.mHandler != null) {
            ReRunnable reRunnableObtain = ReRunnable.obtain();
            reRunnableObtain.type = 3;
            reRunnableObtain.webSocketWrapper = webSocketWrapper;
            this.mOptionThread.mHandler.post(reRunnableObtain);
            return;
        }
        LogUtil.e(TAG, "WebSocketEngine not start!");
    }

    public void destroy() {
        OptionThread optionThread = this.mOptionThread;
        if (optionThread == null || optionThread.mHandler == null) {
            return;
        }
        this.mOptionThread.mHandler.sendEmptyMessage(1);
    }

    private class OptionThread extends Thread {
        private OptionHandler mHandler;

        private OptionThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            Looper.prepare();
            this.mHandler = new OptionHandler();
            Looper.loop();
        }
    }

    private static class OptionHandler extends Handler {
        private static final int QUIT = 1;

        private OptionHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    private static class ReRunnable implements Runnable {
        private static Queue<ReRunnable> POOL = new ArrayDeque(10);
        public static final int type_connect = 1;
        public static final int type_destroy = 3;
        public static final int type_disconnect = 2;
        public static final int type_send = 0;
        private Request request;
        private int type;
        private WebSocketWrapper webSocketWrapper;

        private ReRunnable() {
        }

        static ReRunnable obtain() {
            ReRunnable reRunnablePoll = POOL.poll();
            return reRunnablePoll == null ? new ReRunnable() : reRunnablePoll;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.webSocketWrapper != null && (this.type != 0 || this.request != null)) {
                    if (this.type == 0) {
                        this.webSocketWrapper.send(this.request);
                    } else if (this.type == 1) {
                        this.webSocketWrapper.reconnect();
                    } else if (this.type == 2) {
                        this.webSocketWrapper.disConnect();
                    } else if (this.type == 3) {
                        this.webSocketWrapper.destroy();
                    }
                }
            } finally {
                this.webSocketWrapper = null;
                this.request = null;
                release();
            }
        }

        void release() {
            POOL.offer(this);
        }
    }
}
