package com.huya.statistics.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StatisticsThread {
    private static final String THREAD_NAME = "StatisticsThread-";
    private static final HandlerThreadWrapper mMakeThread = new HandlerThreadWrapper("Make");
    private static final HandlerThreadWrapper mWorkThread = new HandlerThreadWrapper("work");
    private static final HandlerThreadWrapper timerThread = new HandlerThreadWrapper("heart");

    private static class HandlerThreadWrapper {
        private Handler mHandler;
        private HandlerThread mThread;

        public HandlerThreadWrapper(String str) {
            this(str, null);
        }

        public HandlerThreadWrapper(String str, Handler.Callback callback) {
            HandlerThread handlerThread = new HandlerThread(StatisticsThread.THREAD_NAME + str);
            handlerThread.setPriority(10);
            this.mThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper(), callback);
        }

        public Handler getHandler() {
            return this.mHandler;
        }

        public Thread getThread() {
            return this.mThread;
        }
    }

    public static void executorMakeTask(Runnable runnable) {
        mMakeThread.getHandler().post(runnable);
    }

    public static void executorMakeTask(Runnable runnable, long j) {
        mMakeThread.getHandler().postDelayed(runnable, j);
    }

    public static void removeMakeTask(Runnable runnable) {
        mMakeThread.getHandler().removeCallbacks(runnable);
    }

    public static void executorWorkTask(Runnable runnable) {
        mWorkThread.getHandler().post(runnable);
    }

    public static void executorWorkTask(Runnable runnable, long j) {
        mWorkThread.getHandler().postDelayed(runnable, j);
    }

    public static void removeWorkTask(Runnable runnable) {
        mWorkThread.getHandler().removeCallbacks(runnable);
    }

    public static Handler getTimerHandler() {
        return timerThread.getHandler();
    }

    public static void asyncRun(Runnable runnable) {
        if (mMakeThread.mThread.getThreadId() != Process.myTid()) {
            mMakeThread.mHandler.post(runnable);
        } else {
            runnable.run();
        }
    }
}
