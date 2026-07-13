package com.volcengine.androidcloud.common.log;

import com.volcengine.androidcloud.common.log.AcLog;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class AcLogCache implements AcLog.ILogger, Runnable {
    private static final int MAX_WAIT_TIMEOUT = 1;
    private final Callback mCallback;
    private boolean mIsLooping = false;
    private final ArrayBlockingQueue<AcLogInfo> mLogQueue;
    private final int mMaxQueueSize;

    public interface Callback {
        void onLog(AcLogInfo acLogInfo);
    }

    public AcLogCache(int i, Callback callback) {
        this.mCallback = callback;
        this.mMaxQueueSize = i;
        this.mLogQueue = new ArrayBlockingQueue<>(i);
    }

    private void addQueue(AcLogInfo acLogInfo) {
        synchronized (this.mLogQueue) {
            if (this.mLogQueue.size() >= this.mMaxQueueSize) {
                AcLogInfo.recycle(this.mLogQueue.take());
            }
            this.mLogQueue.offer(acLogInfo, 1L, TimeUnit.SECONDS);
            this.mLogQueue.notify();
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onDebug(String str, String str2) {
        try {
            addQueue(AcLogInfo.wrap(AcLog.Level.DEBUG, str, str2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onError(String str, String str2) {
        try {
            addQueue(AcLogInfo.wrap(AcLog.Level.ERROR, str, str2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onError(String str, String str2, Throwable th) {
        try {
            addQueue(AcLogInfo.wrap(AcLog.Level.ERROR, str, str2, th));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onInfo(String str, String str2) {
        try {
            addQueue(AcLogInfo.wrap(AcLog.Level.INFO, str, str2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onVerbose(String str, String str2) {
        try {
            addQueue(AcLogInfo.wrap(AcLog.Level.VERBOSE, str, str2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onWarn(String str, String str2) {
        try {
            addQueue(AcLogInfo.wrap(AcLog.Level.WARN, str, str2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        Callback callback;
        while (this.mIsLooping) {
            try {
                synchronized (this.mLogQueue) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    while (!this.mLogQueue.isEmpty()) {
                        AcLogInfo acLogInfoPoll = this.mLogQueue.poll(1L, TimeUnit.SECONDS);
                        if (acLogInfoPoll != null && (callback = this.mCallback) != null) {
                            callback.onLog(acLogInfoPoll);
                        }
                        AcLogInfo.recycle(acLogInfoPoll);
                        if (System.currentTimeMillis() - jCurrentTimeMillis <= 1000) {
                        }
                    }
                    try {
                        this.mLogQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
    }

    public int size() {
        return this.mLogQueue.size();
    }

    public void startLooper() {
        this.mIsLooping = true;
        new Thread(this).start();
    }

    public void stopLooper() {
        this.mIsLooping = false;
    }
}
