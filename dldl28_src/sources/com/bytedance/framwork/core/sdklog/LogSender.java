package com.bytedance.framwork.core.sdklog;

import android.content.Context;
import android.database.sqlite.SQLiteFullException;
import android.text.TextUtils;
import com.bytedance.framwork.core.sdklog.LogHandler;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
final class LogSender extends Thread {
    private static final long CLEAN_LOG_INTERVAL = 600000;
    private static final long DEFAULT_SCAN_LOG_INTERVAL = 120000;
    static final String KEY_MAGIC_TAG = "magic_tag";
    static final String KEY_MESSAGE = "message";
    static final String MAGIC_TAG = "log_queue";
    private static final long MAX_EXPIRE_TIME = 864000000;
    static final String STATUS_OK = "success";
    private static final String TAG = "LogSender";
    private final Context mContext;
    private final LogQueueManager mDbHelper;
    private long mLastCleanTime;
    private final Object mLock;
    private LogQueue mLogQueue;
    private long mMinLog;
    private final LinkedList<LogItem> mPendingQueue;
    private long mScanLogInterval;
    private final AtomicBoolean mStopFlag;

    LogSender(Context context, LogQueue logQueue, LinkedList<LogItem> linkedList, AtomicBoolean atomicBoolean) {
        super(TAG);
        this.mLock = new Object();
        this.mMinLog = -1L;
        this.mLastCleanTime = 0L;
        this.mScanLogInterval = DEFAULT_SCAN_LOG_INTERVAL;
        this.mLogQueue = logQueue;
        this.mContext = context;
        this.mPendingQueue = linkedList;
        this.mStopFlag = atomicBoolean;
        this.mDbHelper = LogQueueManager.getInstance(context);
    }

    private void cleanLog() {
        LogHandler.IConfig config;
        if (isStop()) {
            return;
        }
        Map<String, LogHandler> allLogHandler = this.mLogQueue.getAllLogHandler();
        if (allLogHandler != null && !allLogHandler.isEmpty()) {
            for (String str : allLogHandler.keySet()) {
                if (isStop()) {
                    break;
                }
                LogHandler logHandler = allLogHandler.get(str);
                if (logHandler != null && (config = logHandler.getConfig()) != null) {
                    this.mDbHelper.cleanExpireLog(str, config.getMaxRetryCount(), config.getLogExpireTime());
                }
            }
        }
        this.mDbHelper.cleanExpireLog(null, -1, MAX_EXPIRE_TIME);
    }

    private boolean isStop() {
        return this.mStopFlag.get();
    }

    private boolean processPendingQueue() {
        if (isStop()) {
            return false;
        }
        synchronized (this.mPendingQueue) {
            if (isStop()) {
                return false;
            }
            LogItem logItemPoll = !this.mPendingQueue.isEmpty() ? this.mPendingQueue.poll() : null;
            boolean z = !this.mPendingQueue.isEmpty();
            if (logItemPoll != null) {
                try {
                    if (this.mDbHelper.insertLog(logItemPoll.type, logItemPoll.value) >= Long.MAX_VALUE) {
                        this.mDbHelper.recreateTableQueue();
                    }
                } catch (SQLiteFullException unused) {
                    this.mDbHelper.recreateTableQueue();
                }
            }
            return z;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.bytedance.framwork.core.sdklog.LogHandler] */
    /* JADX WARN: Type inference failed for: r10v2, types: [com.bytedance.framwork.core.sdklog.LogQueueManager] */
    /* JADX WARN: Type inference failed for: r13v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.String] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private boolean scanAndSendLog() {
        String lastSuccessChannel;
        LogHandler.IConfig iConfig;
        boolean z;
        ?? r3;
        boolean zSendLog;
        LogHandler logHandler;
        ?? r32;
        int i;
        boolean zSendLog2;
        Object obj;
        String str;
        ?? r33;
        boolean z2;
        int i2;
        int i3;
        if (isStop()) {
            return false;
        }
        if (this.mMinLog < 0 && System.currentTimeMillis() - this.mLastCleanTime > 600000) {
            this.mMinLog = 0L;
            cleanLog();
            this.mLastCleanTime = System.currentTimeMillis();
        }
        if (!LogLib.isNetworkAvailable(this.mContext)) {
            this.mScanLogInterval = DEFAULT_SCAN_LOG_INTERVAL;
            return false;
        }
        LogItem log = this.mDbHelper.getLog(this.mMinLog);
        ?? r0 = 0;
        if (log == null) {
            if (this.mMinLog == 0 && this.mDbHelper.getEventCount(null) == 0) {
                this.mScanLogInterval = 0L;
                return false;
            }
            if (this.mMinLog == -1) {
                this.mScanLogInterval = DEFAULT_SCAN_LOG_INTERVAL;
            }
            this.mMinLog = -1L;
            return false;
        }
        long j = this.mMinLog;
        long j2 = log.id;
        if (j < j2) {
            this.mMinLog = j2;
        } else {
            this.mMinLog = j + 1;
        }
        byte[] bArr = log.value;
        if (bArr == null || bArr.length <= 0) {
            lastSuccessChannel = null;
            iConfig = null;
            z = true;
            r3 = 0;
        } else {
            LogHandler logHandler2 = this.mLogQueue.getLogHandler(log.type);
            if (logHandler2 == null) {
                return true;
            }
            LogHandler.IConfig config = logHandler2.getConfig();
            LogHandler.IResponseConfig responseConfig = logHandler2.getResponseConfig();
            long jCurrentTimeMillis = System.currentTimeMillis();
            long retryInterval = config.getRetryInterval();
            ?? r34 = "send log exception: ";
            if (responseConfig == null) {
                iConfig = config;
                if (retryInterval > 0 && (i = log.retryCount) > 0 && jCurrentTimeMillis - log.retryTime < retryInterval * ((long) i)) {
                    return true;
                }
                lastSuccessChannel = logHandler2.getLastSuccessChannel();
                List<String> channels = iConfig.getChannels();
                if (channels == null) {
                    return true;
                }
                try {
                    zSendLog = !TextUtils.isEmpty(lastSuccessChannel) ? sendLog(logHandler2, lastSuccessChannel, log.value) : false;
                } catch (Throwable th) {
                    th = th;
                    zSendLog = false;
                }
                if (zSendLog) {
                    logHandler = logHandler2;
                    r32 = zSendLog;
                } else {
                    try {
                        for (String str2 : channels) {
                            if (isStop()) {
                                return true;
                            }
                            if (!TextUtils.isEmpty(str2) && !str2.equals(lastSuccessChannel) && (zSendLog = sendLog(logHandler2, str2, log.value))) {
                                logHandler = logHandler2;
                                r32 = zSendLog;
                                lastSuccessChannel = str2;
                                break;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        LogQueue.log(TAG, "send log exception: " + th);
                    }
                    logHandler = logHandler2;
                    r32 = zSendLog;
                }
            } else if (responseConfig.getRemoveSwitch()) {
                lastSuccessChannel = null;
                r0 = logHandler2;
                iConfig = config;
                z = false;
                r3 = 1;
            } else {
                long stopInterval = responseConfig.getStopInterval();
                long stopMoreChannelInterval = logHandler2.getStopMoreChannelInterval();
                if ((stopInterval > 0 && jCurrentTimeMillis - logHandler2.getLastStopTime() < stopInterval) || (stopMoreChannelInterval > 0 && jCurrentTimeMillis - logHandler2.getLastStopTime() < stopMoreChannelInterval)) {
                    return true;
                }
                iConfig = config;
                logHandler2.setLastStopTime(System.currentTimeMillis());
                if (retryInterval > 0 && (i3 = log.retryCount) > 0 && jCurrentTimeMillis - log.retryTime < retryInterval * ((long) i3)) {
                    return true;
                }
                String lastSuccessChannel2 = logHandler2.getLastSuccessChannel();
                List<String> channels2 = iConfig.getChannels();
                if (channels2 == null) {
                    return true;
                }
                try {
                    if (TextUtils.isEmpty(lastSuccessChannel2)) {
                        z2 = false;
                        zSendLog2 = false;
                    } else {
                        zSendLog2 = sendLog(logHandler2, lastSuccessChannel2, log.value);
                        z2 = true;
                    }
                    if (zSendLog2) {
                        str = lastSuccessChannel2;
                    } else {
                        try {
                            i2 = 0;
                        } catch (Throwable th3) {
                            th = th3;
                            obj = th;
                            str = lastSuccessChannel2;
                            r33 = r34;
                            LogQueue.log(TAG, ((String) r33) + obj);
                            lastSuccessChannel = str;
                            logHandler = logHandler2;
                            r32 = zSendLog2;
                        }
                        try {
                            for (String str3 : channels2) {
                                if (!responseConfig.getMoreChannelSwitch() && z2) {
                                    break;
                                }
                                if (isStop()) {
                                    return true;
                                }
                                if (!TextUtils.isEmpty(str3) && !str3.equals(lastSuccessChannel2)) {
                                    boolean zSendLog3 = sendLog(logHandler2, str3, log.value);
                                    zSendLog2 = zSendLog3;
                                    if (!zSendLog3) {
                                        z2 = true;
                                    }
                                    break;
                                }
                                i2++;
                            }
                            break;
                            if (i2 == channels2.size() && channels2.size() > 1) {
                                logHandler2.setStopMoreChannelInterval(responseConfig.getStopMoreChannelInterval());
                            }
                            logHandler = logHandler2;
                            r34 = zSendLog2;
                            lastSuccessChannel = str3;
                            r32 = r34;
                        } catch (Throwable th4) {
                            obj = th4;
                            str = str3;
                            r33 = r34;
                            LogQueue.log(TAG, ((String) r33) + obj);
                            lastSuccessChannel = str;
                            logHandler = logHandler2;
                            r32 = zSendLog2;
                        }
                        str3 = lastSuccessChannel2;
                    }
                    logHandler2.setStopMoreChannelInterval(0L);
                    logHandler = logHandler2;
                    r34 = zSendLog2;
                    lastSuccessChannel = str3;
                    r32 = r34;
                } catch (Throwable th5) {
                    th = th5;
                    zSendLog2 = false;
                }
            }
            z = false;
            r0 = logHandler;
            r3 = r32;
        }
        if (isStop()) {
            return true;
        }
        if (z) {
            this.mDbHelper.onLogSent(log.id, true, 0L, 0);
            return true;
        }
        if (r3 != 0) {
            r0.setLastSuccessChannel(lastSuccessChannel);
        }
        if (this.mDbHelper.onLogSent(log.id, r3, iConfig.getLogExpireTime(), iConfig.getMaxRetryCount())) {
            long retryInterval2 = iConfig.getRetryInterval() * ((long) (log.retryCount + 1));
            if (retryInterval2 > 0) {
                this.mScanLogInterval = retryInterval2;
            }
            this.mScanLogInterval = Math.min(DEFAULT_SCAN_LOG_INTERVAL, this.mScanLogInterval);
        } else {
            this.mScanLogInterval = DEFAULT_SCAN_LOG_INTERVAL;
        }
        r0.onLogSent(log.value, r3);
        return true;
    }

    private boolean sendLog(LogHandler logHandler, String str, byte[] bArr) {
        if (bArr == null || bArr.length <= 0 || logHandler == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return logHandler.send(str, bArr);
    }

    void awaken() {
        synchronized (this.mLock) {
            this.mLock.notify();
        }
    }

    void quit() {
        awaken();
        this.mDbHelper.closeDatabase();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        LogQueue.log(TAG, "LogSender start");
        while (!isStop()) {
            boolean zProcessPendingQueue = processPendingQueue();
            if (isStop()) {
                break;
            }
            boolean z = scanAndSendLog() || zProcessPendingQueue;
            if (isStop()) {
                break;
            }
            if (!z) {
                synchronized (this.mLock) {
                    try {
                        long j = this.mScanLogInterval;
                        if (j == 0) {
                            this.mLock.wait();
                        } else {
                            this.mLock.wait(j);
                        }
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
        LogQueue.log(TAG, "LogSender quit");
    }
}
