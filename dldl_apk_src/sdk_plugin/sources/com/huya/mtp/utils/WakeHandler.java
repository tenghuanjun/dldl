package com.huya.mtp.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.Vector;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class WakeHandler {
    private boolean mKeepWake;
    private Looper mLooper;
    private HandlerThread mThread;

    public WakeHandler() {
        init(null, true);
    }

    public WakeHandler(String str) {
        this(str, true);
    }

    public WakeHandler(String str, boolean z) {
        HandlerThread handlerThread = new HandlerThread(str);
        this.mThread = handlerThread;
        handlerThread.start();
        init(this.mThread.getLooper(), z);
    }

    public WakeHandler(Looper looper) {
        init(looper, true);
    }

    private void init(Looper looper, boolean z) {
        this.mLooper = looper;
        this.mKeepWake = z;
    }

    public HandlerAdapter getAdapter() {
        return getAdapter((Handler.Callback) null);
    }

    public HandlerAdapter getAdapter(boolean z) {
        return getAdapter(null, z);
    }

    public HandlerAdapter getAdapter(Handler.Callback callback) {
        return getAdapter(callback, this.mKeepWake);
    }

    public HandlerAdapter getAdapter(Handler.Callback callback, boolean z) {
        return new HandlerAdapter(getLooper(), callback, z);
    }

    public Looper getLooper() {
        Looper looper = this.mLooper;
        return looper == null ? Looper.myLooper() : looper;
    }

    public String getThreadName() {
        return getLooper().getThread().getName();
    }

    public static class HandlerAdapter {
        private MsgHandler mHandler;

        public HandlerAdapter(Looper looper, Handler.Callback callback, boolean z) {
            this.mHandler = new MsgHandler(looper, callback, z);
        }

        public void removeMessages(int i) {
            this.mHandler.removeMessageForWakeLock(this.mHandler.obtainMessage(i));
            this.mHandler.removeMessages(i);
        }

        public void removeMessages(int i, Object obj) {
            this.mHandler.removeMessageForWakeLock(this.mHandler.obtainMessage(i, obj));
            this.mHandler.removeMessages(i, obj);
        }

        public void removeCallbacksAndMessages(Object obj) {
            this.mHandler.removeMessagesForWakeLock(obj);
            this.mHandler.removeCallbacksAndMessages(obj);
        }

        public void post(Runnable runnable) {
            this.mHandler.post(runnable);
        }

        public boolean postAtTime(Runnable runnable, Object obj, long j) {
            return this.mHandler.postAtTime(runnable, obj, j);
        }

        public boolean hasMessages(int i) {
            return this.mHandler.hasMessages(i);
        }

        public Message obtainMessage(int i) {
            return this.mHandler.obtainMessage(i);
        }

        public Message obtainMessage(int i, Object obj) {
            return this.mHandler.obtainMessage(i, obj);
        }

        public void sendMessage(Message message) {
            this.mHandler.sendMessage(message);
        }

        public void sendEmptyMessage(int i) {
            this.mHandler.sendEmptyMessage(i);
        }
    }

    private static class MsgHandler extends Handler {
        private boolean mKeepWakeLock;
        private Vector<Message> msgQueue;

        public MsgHandler(Looper looper, Handler.Callback callback, boolean z) {
            super(looper, callback);
            this.msgQueue = new Vector<>();
            this.mKeepWakeLock = z;
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j) {
            addMessageForWakeLock(message);
            return super.sendMessageAtTime(message, j);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            removeMessageForWakeLock(message);
        }

        public synchronized void addMessageForWakeLock(Message message) {
            if (this.mKeepWakeLock) {
                this.msgQueue.add(message);
                WakeLockHelper.getInstance().acquireWakeLock();
            }
        }

        public synchronized void removeMessageForWakeLock(Message message) {
            if (this.mKeepWakeLock) {
                if (this.msgQueue.remove(message)) {
                    WakeLockHelper.getInstance().releaseWakeLock();
                }
            }
        }

        public synchronized void removeMessagesForWakeLock(Object obj) {
            if (this.mKeepWakeLock) {
                int i = 0;
                while (i < this.msgQueue.size()) {
                    Message message = this.msgQueue.get(i);
                    if (obj == null || obj.equals(message.obj)) {
                        removeMessageForWakeLock(message);
                        i--;
                    }
                    i++;
                }
            }
        }
    }
}
