package com.volcengine.androidcloud.common.log;

import com.volcengine.androidcloud.common.log.AcLog;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: loaded from: classes3.dex */
public class AcLogInfo {
    private static final Queue<AcLogInfo> sObjectPool = new LinkedBlockingDeque();
    private AcLog.Level mLevel;
    private String mMsg;
    private String mTag;
    private Throwable mThrowable;

    protected AcLogInfo(AcLog.Level level, String str, String str2, Throwable th) {
        this.mLevel = level;
        this.mTag = str;
        this.mMsg = str2;
        this.mThrowable = th;
    }

    public static void recycle(AcLogInfo acLogInfo) {
        Queue<AcLogInfo> queue = sObjectPool;
        synchronized (queue) {
            if (acLogInfo != null) {
                acLogInfo.reset();
                queue.offer(acLogInfo);
            }
        }
    }

    public static AcLogInfo wrap(AcLog.Level level, String str, String str2) {
        Queue<AcLogInfo> queue = sObjectPool;
        synchronized (queue) {
            if (queue.isEmpty()) {
                return new AcLogInfo(level, str, str2, null);
            }
            AcLogInfo acLogInfoPoll = queue.poll();
            if (acLogInfoPoll != null) {
                acLogInfoPoll.set(level, str, str2, null);
                return acLogInfoPoll;
            }
            return new AcLogInfo(level, str, str2, null);
        }
    }

    public static AcLogInfo wrap(AcLog.Level level, String str, String str2, Throwable th) {
        Queue<AcLogInfo> queue = sObjectPool;
        synchronized (queue) {
            if (queue.isEmpty()) {
                return new AcLogInfo(level, str, str2, th);
            }
            AcLogInfo acLogInfoPoll = queue.poll();
            if (acLogInfoPoll != null) {
                acLogInfoPoll.set(level, str, str2, th);
                return acLogInfoPoll;
            }
            return new AcLogInfo(level, str, str2, th);
        }
    }

    public AcLog.Level level() {
        return this.mLevel;
    }

    public String message() {
        return this.mMsg;
    }

    public void reset() {
        this.mLevel = AcLog.Level.VERBOSE;
        this.mTag = null;
        this.mMsg = null;
        this.mThrowable = null;
    }

    public void set(AcLog.Level level, String str, String str2, Throwable th) {
        this.mLevel = level;
        this.mTag = str;
        this.mMsg = str2;
        this.mThrowable = th;
    }

    public String tag() {
        return this.mTag;
    }

    public Throwable throwable() {
        return this.mThrowable;
    }
}
