package com.bytedance.framwork.core.sdklog;

import android.content.Context;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class LogQueue {
    static final boolean DEBUG = false;
    private static final int MAX_QUEUE_SIZE = 2000;
    static final String TAG = "LogQueue";
    private static LogQueue sLogQueue;
    private final Context mContext;
    private final Map<String, LogHandler> mLogHandlers;
    private final LogSender mLogSender;
    private final LinkedList<LogItem> mPendingQueue;
    private final AtomicBoolean mStopFlag;

    private LogQueue(Context context) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.mStopFlag = atomicBoolean;
        LinkedList<LogItem> linkedList = new LinkedList<>();
        this.mPendingQueue = linkedList;
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mLogHandlers = new ConcurrentHashMap();
        LogSender logSender = new LogSender(applicationContext, this, linkedList, atomicBoolean);
        this.mLogSender = logSender;
        logSender.start();
    }

    public static LogQueue getInstance(Context context) {
        if (sLogQueue == null) {
            synchronized (LogQueue.class) {
                if (sLogQueue == null) {
                    sLogQueue = new LogQueue(context);
                }
            }
        }
        return sLogQueue;
    }

    static void log(String str) {
    }

    static void log(String str, String str2) {
    }

    public static void quit() {
        synchronized (LogQueue.class) {
            LogQueue logQueue = sLogQueue;
            if (logQueue != null) {
                logQueue.stop();
            }
        }
    }

    boolean enqueue(String str, byte[] bArr) {
        if (isStop() || bArr == null || bArr.length <= 0 || getLogHandler(str) == null) {
            return false;
        }
        synchronized (this.mPendingQueue) {
            if (this.mStopFlag.get()) {
                return false;
            }
            if (this.mPendingQueue.size() >= 2000) {
                this.mPendingQueue.poll();
            }
            boolean zAdd = this.mPendingQueue.add(new LogItem(str, bArr));
            this.mLogSender.awaken();
            return zAdd;
        }
    }

    Map<String, LogHandler> getAllLogHandler() {
        return this.mLogHandlers;
    }

    LogHandler getLogHandler(String str) {
        return this.mLogHandlers.get(str);
    }

    boolean isStop() {
        return this.mStopFlag.get();
    }

    public void registerLogHandler(String str, LogHandler logHandler) {
        if (isStop() || logHandler == null) {
            return;
        }
        this.mLogHandlers.put(str, logHandler);
    }

    void stop() {
        synchronized (this.mPendingQueue) {
            this.mPendingQueue.clear();
        }
        this.mStopFlag.set(true);
        this.mLogSender.quit();
    }

    public void unregisterLogHandler(LogHandler logHandler) {
        if (isStop() || logHandler == null) {
            return;
        }
        this.mLogHandlers.remove(logHandler.getType());
    }
}
