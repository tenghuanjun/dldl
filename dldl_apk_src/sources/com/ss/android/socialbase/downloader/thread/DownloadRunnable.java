package com.ss.android.socialbase.downloader.thread;

import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.ss.android.socialbase.downloader.constants.ByteInvalidRetryStatus;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.constants.RunStatus;
import com.ss.android.socialbase.downloader.depend.AbsDownloadForbiddenCallback;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadForbiddenHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadInterceptor;
import com.ss.android.socialbase.downloader.depend.IDownloadMonitorDepend;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler;
import com.ss.android.socialbase.downloader.downloader.DownloadStatusHandler;
import com.ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator;
import com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.exception.DownloadFileExistException;
import com.ss.android.socialbase.downloader.exception.DownloadHttpException;
import com.ss.android.socialbase.downloader.exception.DownloadOnlyWifiException;
import com.ss.android.socialbase.downloader.exception.DownloadOutOfSpaceException;
import com.ss.android.socialbase.downloader.exception.DownloadPauseReserveWifiException;
import com.ss.android.socialbase.downloader.exception.DownloadRetryNeedlessException;
import com.ss.android.socialbase.downloader.exception.RetryCheckStatus;
import com.ss.android.socialbase.downloader.exception.RetryThrowable;
import com.ss.android.socialbase.downloader.impls.AbsDownloadEngine;
import com.ss.android.socialbase.downloader.impls.DefaultDownloadEngine;
import com.ss.android.socialbase.downloader.impls.RetryDelayTimeParamCalculator;
import com.ss.android.socialbase.downloader.impls.RetryScheduler;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadChunk;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.model.HttpResponse;
import com.ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.ss.android.socialbase.downloader.network.AbsDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.DeviceBandwidthSampler;
import com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.connectionpool.DownloadConnectionPool;
import com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHeadHttpConnection;
import com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection;
import com.ss.android.socialbase.downloader.segment.Segment;
import com.ss.android.socialbase.downloader.segment.SegmentDispatcher;
import com.ss.android.socialbase.downloader.segment.SegmentStrategy;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadHttpUtils;
import com.ss.android.socialbase.downloader.utils.DownloadSettingsUtils;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLHandshakeException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadRunnable implements IDownloadRunnableCallback, Runnable {
    private static final int MAX_RESET_RETAIN_RETRY_TIMES_COUNT = 3;
    private static final String TAG = DownloadRunnable.class.getSimpleName();
    private boolean acceptPartial;
    private boolean canResumeFromCache;
    private long curBytesNeedCheckSpaceOverFlow;
    private IDownloadDiskSpaceHandler diskSpaceHandler;
    private final IDownloadCache downloadCache;
    private DownloadInfo downloadInfo;
    private final DownloadTask downloadTask;
    private volatile BaseException errorException;
    private String existTargetFileName;
    private IDownloadHttpConnection firstGetConnection;
    private IDownloadHeadHttpConnection firstHeadConnection;
    private volatile DownloadResponseHandler firstHttpResponseHandler;
    private IDownloadForbiddenHandler forbiddenHandler;
    private final IChunkAdjustCalculator globalChunkAdjustCalculator;
    private final IChunkCntCalculator globalChunkCalculator;
    private final AtomicBoolean isAlive;
    private boolean isChunked;
    private boolean isResponseFromBegin;
    private boolean isSingleChunk;
    private Future mFuture;
    private long prepareDownloadTime;
    private AtomicInteger retainRetryTimes;
    private IRetryDelayTimeCalculator retryDelayTimeCalculator;
    private final DownloadSetting setting;
    private final DownloadStatusHandler statusHandler;
    private IChunkAdjustCalculator taskChunkAdjustCalculator;
    private IChunkCntCalculator taskChunkCalculator;
    private volatile boolean isTriedFixRangeNotSatisfiable = false;
    private final ArrayList<DownloadChunkRunnable> downloadChunkRunnableList = new ArrayList<>();
    private volatile RunStatus runStatus = RunStatus.RUN_STATUS_NONE;
    private volatile int bytesRetryCount = 5;
    private boolean needJumpToStart = false;
    private boolean firstHeadConnectionFailed = false;
    private boolean needCheckIfModified = false;
    private int resetRetainRetryTimesCount = 0;
    private volatile SegmentDispatcher segmentDispatcher = null;

    private boolean checkNeedRetryDelay() {
        return false;
    }

    public DownloadRunnable(DownloadTask downloadTask, Handler handler) {
        this.downloadTask = downloadTask;
        if (downloadTask != null) {
            this.downloadInfo = downloadTask.getDownloadInfo();
            this.taskChunkCalculator = downloadTask.getChunkStrategy();
            this.taskChunkAdjustCalculator = downloadTask.getChunkAdjustCalculator();
            this.forbiddenHandler = downloadTask.getForbiddenHandler();
            this.diskSpaceHandler = downloadTask.getDiskSpaceHandler();
            this.retryDelayTimeCalculator = getRetryDelayTimeCalculator(downloadTask);
            this.setting = DownloadSetting.obtain(this.downloadInfo.getId());
        } else {
            this.setting = DownloadSetting.obtainGlobal();
        }
        updateRetainRetryTimes();
        this.downloadCache = DownloadComponentManager.getDownloadCache();
        this.globalChunkCalculator = DownloadComponentManager.getChunkCntCalculator();
        this.globalChunkAdjustCalculator = DownloadComponentManager.getChunkAdjustCalculator();
        this.statusHandler = new DownloadStatusHandler(downloadTask, handler);
        this.isAlive = new AtomicBoolean(true);
    }

    private void updateRetainRetryTimes() {
        DownloadInfo downloadInfo = this.downloadInfo;
        if (downloadInfo == null) {
            return;
        }
        int retryCount = downloadInfo.getRetryCount() - this.downloadInfo.getCurRetryTime();
        if (retryCount < 0) {
            retryCount = 0;
        }
        AtomicInteger atomicInteger = this.retainRetryTimes;
        if (atomicInteger == null) {
            this.retainRetryTimes = new AtomicInteger(retryCount);
        } else {
            atomicInteger.set(retryCount);
        }
    }

    public void pause() {
        this.runStatus = RunStatus.RUN_STATUS_PAUSE;
        if (this.segmentDispatcher != null) {
            this.segmentDispatcher.pause();
        }
        if (this.firstHttpResponseHandler != null) {
            this.firstHttpResponseHandler.pause();
        }
        if (this.segmentDispatcher == null && this.firstHttpResponseHandler == null) {
            closeConnection();
            this.runStatus = RunStatus.RUN_STATUS_PAUSE;
            endDownloadRunnable();
        }
        try {
            for (DownloadChunkRunnable downloadChunkRunnable : (ArrayList) this.downloadChunkRunnableList.clone()) {
                if (downloadChunkRunnable != null) {
                    downloadChunkRunnable.pause();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void cancel() {
        this.runStatus = RunStatus.RUN_STATUS_CANCELED;
        if (this.segmentDispatcher != null) {
            this.segmentDispatcher.cancel();
        }
        if (this.firstHttpResponseHandler != null) {
            this.firstHttpResponseHandler.cancel();
        }
        if (this.segmentDispatcher == null && this.firstHttpResponseHandler == null) {
            closeConnection();
            this.runStatus = RunStatus.RUN_STATUS_CANCELED;
            endDownloadRunnable();
        }
        cancelAllChunkRunnable();
    }

    public DownloadTask getDownloadTask() {
        return this.downloadTask;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int calculateChunkCount(long r7, java.util.List<com.ss.android.socialbase.downloader.model.DownloadChunk> r9) {
        /*
            r6 = this;
            boolean r0 = r6.isMultiChunkDownloadAvailable()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L5e
            boolean r0 = r6.canResumeFromCache
            if (r0 == 0) goto L1a
            if (r9 == 0) goto L13
            int r9 = r9.size()
            goto L5c
        L13:
            com.ss.android.socialbase.downloader.model.DownloadInfo r9 = r6.downloadInfo
            int r9 = r9.getChunkCount()
            goto L5c
        L1a:
            com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator r9 = r6.taskChunkCalculator
            if (r9 == 0) goto L23
            int r9 = r9.calculateChunkCount(r7)
            goto L29
        L23:
            com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator r9 = r6.globalChunkCalculator
            int r9 = r9.calculateChunkCount(r7)
        L29:
            com.ss.android.socialbase.downloader.network.NetTrafficManager r0 = com.ss.android.socialbase.downloader.network.NetTrafficManager.getInstance()
            com.ss.android.socialbase.downloader.network.NetworkQuality r0 = r0.getCurrentNetworkQuality()
            java.lang.String r3 = com.ss.android.socialbase.downloader.thread.DownloadRunnable.TAG
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r5 = r0.name()
            r4[r1] = r5
            java.lang.String r5 = "NetworkQuality is : %s"
            java.lang.String r4 = java.lang.String.format(r5, r4)
            com.ss.android.socialbase.downloader.logger.Logger.d(r3, r4)
            com.ss.android.socialbase.downloader.model.DownloadInfo r3 = r6.downloadInfo
            java.lang.String r4 = r0.name()
            r3.setNetworkQuality(r4)
            com.ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator r3 = r6.taskChunkAdjustCalculator
            if (r3 == 0) goto L56
            int r9 = r3.calculateChunkCount(r9, r0)
            goto L5c
        L56:
            com.ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator r3 = r6.globalChunkAdjustCalculator
            int r9 = r3.calculateChunkCount(r9, r0)
        L5c:
            if (r9 > 0) goto L5f
        L5e:
            r9 = 1
        L5f:
            boolean r0 = com.ss.android.socialbase.downloader.logger.Logger.debug()
            if (r0 == 0) goto L88
            java.lang.String r0 = com.ss.android.socialbase.downloader.thread.DownloadRunnable.TAG
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r4 = java.lang.String.valueOf(r9)
            r3[r1] = r4
            com.ss.android.socialbase.downloader.model.DownloadInfo r1 = r6.downloadInfo
            java.lang.String r1 = r1.getName()
            r3[r2] = r1
            r1 = 2
            java.lang.String r7 = java.lang.String.valueOf(r7)
            r3[r1] = r7
            java.lang.String r7 = "chunk count : %s for %s contentLen:%s"
            java.lang.String r7 = java.lang.String.format(r7, r3)
            com.ss.android.socialbase.downloader.logger.Logger.d(r0, r7)
        L88:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadRunnable.calculateChunkCount(long, java.util.List):int");
    }

    private boolean checkTaskStatusValid() {
        int status = this.downloadInfo.getStatus();
        if (status == 1 || this.downloadInfo.canSkipStatusHandler()) {
            return true;
        }
        if (status == -2 || status == -4) {
            return false;
        }
        onError(new BaseException(1000, "The download Task can't start, because its status is not prepare:" + status));
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab A[Catch: all -> 0x00ea, TryCatch #7 {all -> 0x00ea, blocks: (B:56:0x00a7, B:58:0x00ab, B:60:0x00af, B:73:0x00e9), top: B:86:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f7 A[Catch: SQLiteException -> 0x00ff, TRY_LEAVE, TryCatch #6 {SQLiteException -> 0x00ff, blocks: (B:77:0x00ef, B:79:0x00f7), top: B:84:0x00ef }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void checkTaskCache() throws com.ss.android.socialbase.downloader.exception.DownloadFileExistException {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadRunnable.checkTaskCache():void");
    }

    @Override // java.lang.Runnable
    public void run() {
        DownloadComponentManager.onDownloadTaskStart(this.downloadTask, 3);
        try {
            DeviceBandwidthSampler.getInstance().startSampling();
            runInner();
            DeviceBandwidthSampler.getInstance().stopSampling();
            DownloadComponentManager.onDownloadTaskFinish(this.downloadTask, 3);
        } catch (Throwable th) {
            DeviceBandwidthSampler.getInstance().stopSampling();
            throw th;
        }
    }

    private void runInner() {
        Process.setThreadPriority(10);
        try {
            if (this.downloadInfo != null && this.prepareDownloadTime > 0) {
                this.downloadInfo.increaseDownloadPrepareTime(System.currentTimeMillis() - this.prepareDownloadTime);
            }
        } catch (Throwable unused) {
        }
        try {
            IDownloadInterceptor interceptor = this.downloadTask.getInterceptor();
            if (interceptor != null) {
                if (interceptor.intercepte()) {
                    this.statusHandler.onIntercept();
                    return;
                }
            }
        } finally {
        }
        if (!checkTaskStatusValid()) {
            IDownloadMonitorDepend monitorDepend = this.downloadTask.getMonitorDepend();
            DownloadInfo downloadInfo = this.downloadInfo;
            BaseException baseException = new BaseException(1003, "task status is invalid");
            DownloadInfo downloadInfo2 = this.downloadInfo;
            DownloadMonitorHelper.monitorSendWithTaskMonitor(monitorDepend, downloadInfo, baseException, downloadInfo2 != null ? downloadInfo2.getStatus() : 0);
            return;
        }
        while (true) {
            downloadInner();
            if (!this.needJumpToStart) {
                return;
            }
            if (this.bytesRetryCount > 0) {
                this.bytesRetryCount--;
            } else {
                if (this.downloadInfo.getCurBytes() != this.downloadInfo.getTotalBytes()) {
                    Logger.d(TAG, this.downloadInfo.getErrorBytesLog());
                    this.statusHandler.onError(new DownloadRetryNeedlessException(1027, "current bytes is not equals to total bytes, bytes invalid retry status is : " + this.downloadInfo.getByteInvalidRetryStatus()));
                    return;
                }
                if (this.downloadInfo.getCurBytes() <= 0) {
                    Logger.d(TAG, this.downloadInfo.getErrorBytesLog());
                    this.statusHandler.onError(new DownloadRetryNeedlessException(DownloadErrorCode.ERROR_CUR_BYTES_ZERO, "curBytes is 0, bytes invalid retry status is : " + this.downloadInfo.getByteInvalidRetryStatus()));
                    return;
                }
                if (this.downloadInfo.getTotalBytes() <= 0) {
                    Logger.d(TAG, this.downloadInfo.getErrorBytesLog());
                    this.statusHandler.onError(new DownloadRetryNeedlessException(DownloadErrorCode.ERROR_TOTAL_BYTES_ZERO, "TotalBytes is 0, bytes invalid retry status is : " + this.downloadInfo.getByteInvalidRetryStatus()));
                    return;
                }
            }
        }
    }

    private void downloadInner() {
        boolean z;
        List<DownloadChunk> downloadChunk;
        try {
            this.runStatus = RunStatus.RUN_STATUS_NONE;
            this.downloadInfo.updateStartDownloadTime();
            this.downloadInfo.resetRealStartDownloadTime();
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.downloadInfo.setFirstSpeedTime(-1L);
            try {
                checkTaskCache();
                z = false;
            } catch (DownloadFileExistException e) {
                Logger.d(TAG, "file exist " + e.getExistTargetFileName());
                this.existTargetFileName = e.getExistTargetFileName();
                z = true;
            }
            if (!this.needJumpToStart) {
                this.statusHandler.onStart();
            }
            this.needJumpToStart = false;
            if (checkIsStoppedByUser()) {
                return;
            }
            if (!TextUtils.isEmpty(this.existTargetFileName) && z) {
                if (this.downloadInfo.isExpiredRedownload()) {
                    this.needCheckIfModified = DownloadUtils.cacheExpired(this.downloadInfo);
                }
                if (!this.needCheckIfModified) {
                    finishWithFileExist();
                    return;
                }
            }
            while (!checkIsStoppedByUser()) {
                try {
                    try {
                        try {
                            checkSavePathValid();
                            checkHasAnotherSameTask();
                            checkWifiTaskValid();
                            downloadChunk = this.downloadCache.getDownloadChunk(this.downloadInfo.getId());
                            checkTaskCanResume();
                        } catch (BaseException e2) {
                            Logger.w(TAG, "downloadInner: baseException = " + e2);
                            if (this.runStatus != RunStatus.RUN_STATUS_PAUSE) {
                                if (e2.getErrorCode() != 1025 && e2.getErrorCode() != 1009) {
                                    if (canRetry(e2)) {
                                        if (DownloadUtils.isHttpDataDirtyError(e2)) {
                                            clearCurrentDownloadData();
                                        }
                                        if (onRetry(e2, 0L) == RetryCheckStatus.RETURN) {
                                            closeConnection();
                                            return;
                                        }
                                        closeConnection();
                                    } else {
                                        onError(e2);
                                    }
                                }
                                this.runStatus = RunStatus.RUN_STATUS_END_RIGHT_NOW;
                                closeConnection();
                                return;
                            }
                        }
                    } catch (RetryThrowable e3) {
                        try {
                            Logger.w(TAG, "downloadInner: retry throwable for " + e3.getErrorMsg());
                            if (this.runStatus != RunStatus.RUN_STATUS_PAUSE) {
                                if (this.retainRetryTimes != null && this.retainRetryTimes.get() > 0) {
                                    this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.decrementAndGet());
                                    this.downloadInfo.setStatus(5);
                                } else if (this.retainRetryTimes == null) {
                                    onError(new BaseException(DownloadErrorCode.ERROR_RETAIN_RETRY_TIME_IS_NULL, "retry for Throwable, but retain retry time is NULL, last error is" + e3.getErrorMsg()));
                                } else if (this.downloadInfo.trySwitchToNextBackupUrl()) {
                                    this.downloadInfo.setStatus(5);
                                    this.retainRetryTimes.set(this.downloadInfo.getRetryCount());
                                    this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.get());
                                } else {
                                    onError(new BaseException(1018, String.format("retry for Throwable, but retry Time %s all used, last error is %s", String.valueOf(this.downloadInfo.getRetryCount()), e3.getErrorMsg())));
                                }
                                closeConnection();
                            }
                        } catch (Throwable th) {
                            closeConnection();
                            throw th;
                        }
                    }
                } catch (DownloadFileExistException unused) {
                    finishWithFileExist();
                } catch (Throwable th2) {
                    Logger.w(TAG, "downloadInner: throwable =  " + th2);
                    if (this.runStatus != RunStatus.RUN_STATUS_PAUSE) {
                        onError(new BaseException(DownloadErrorCode.ERROR_DOWNLOAD_RUNNABLE_UNKNOWN, th2));
                    }
                }
                if (downloadSegments()) {
                    Logger.i(TAG, "downloadSegments return");
                    closeConnection();
                    return;
                }
                String connectionUrl = this.downloadInfo.getConnectionUrl();
                if (checkIsStoppedByUser()) {
                    closeConnection();
                    return;
                }
                long firstOffset = this.canResumeFromCache ? DownloadUtils.getFirstOffset(this.downloadInfo) : 0L;
                DownloadChunk downloadChunkCreateFirstDownloadChunk = createFirstDownloadChunk(this.downloadInfo, firstOffset);
                List<HttpHeader> extraHeaders = getExtraHeaders(downloadChunkCreateFirstDownloadChunk);
                DownloadUtils.addThrottleNetSpeed(extraHeaders, this.downloadInfo);
                DownloadUtils.addTTNetProtectTimeout(extraHeaders, this.downloadInfo);
                this.downloadInfo.setPreconnectLevel(0);
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                try {
                    doFirstConnect(connectionUrl, extraHeaders, firstOffset);
                    this.downloadInfo.increaseAllConnectTime(System.currentTimeMillis() - jCurrentTimeMillis2);
                    if (checkIsStoppedByUser()) {
                        closeConnection();
                        return;
                    }
                    long totalBytes = this.downloadInfo.getTotalBytes();
                    checkSpaceOverflow(totalBytes);
                    int iCalculateChunkCount = calculateChunkCount(totalBytes, downloadChunk);
                    if (checkIsStoppedByUser()) {
                        closeConnection();
                        return;
                    }
                    if (iCalculateChunkCount <= 0) {
                        throw new BaseException(DownloadErrorCode.ERROR_CHUNK_COUNT_ERROR, "chunkCount is 0");
                    }
                    boolean z2 = iCalculateChunkCount == 1;
                    this.isSingleChunk = z2;
                    if (z2) {
                        if (this.firstGetConnection == null) {
                            try {
                                jCurrentTimeMillis2 = System.currentTimeMillis();
                                createFirstGetConnection(connectionUrl, extraHeaders);
                                this.downloadInfo.increaseAllConnectTime(System.currentTimeMillis() - jCurrentTimeMillis2);
                            } finally {
                            }
                        }
                        if (checkIsStoppedByUser()) {
                            closeConnection();
                            return;
                        } else {
                            this.downloadInfo.setFirstSpeedTime(System.currentTimeMillis() - jCurrentTimeMillis);
                            resetRetainRetryTimes();
                            handleResponseWithSingleChunk(downloadChunkCreateFirstDownloadChunk, connectionUrl, this.firstGetConnection);
                        }
                    } else {
                        if (!this.downloadInfo.isNeedReuseFirstConnection()) {
                            closeFirstConnection();
                        }
                        if (checkIsStoppedByUser()) {
                            closeConnection();
                            return;
                        }
                        resetRetainRetryTimes();
                        this.downloadInfo.setFirstSpeedTime(System.currentTimeMillis() - jCurrentTimeMillis);
                        if (this.canResumeFromCache) {
                            handleResponseWithMultiChunkFromResume(iCalculateChunkCount, downloadChunk);
                        } else {
                            handleResponseMultiChunkFromBegin(totalBytes, iCalculateChunkCount);
                        }
                    }
                    closeConnection();
                    return;
                } finally {
                }
            }
        } finally {
            endDownloadRunnable();
        }
    }

    private void finishWithFileExist() {
        Logger.d(TAG, "finishWithFileExist");
        if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_END_FOR_FILE_EXIST_ERROR, true)) {
            if (this.existTargetFileName.equals(this.downloadInfo.getName())) {
                this.runStatus = RunStatus.RUN_STATUS_END_RIGHT_NOW;
                return;
            } else {
                this.runStatus = RunStatus.RUN_STATUS_END_FOR_FILE_EXIST;
                return;
            }
        }
        if (this.existTargetFileName.equals(this.downloadInfo.getTargetFilePath())) {
            this.runStatus = RunStatus.RUN_STATUS_END_RIGHT_NOW;
        } else {
            this.runStatus = RunStatus.RUN_STATUS_END_FOR_FILE_EXIST;
        }
    }

    private boolean downloadSegments() throws InterruptedException, BaseException {
        if (this.downloadInfo.isExpiredRedownload() || this.downloadInfo.getChunkCount() != 1 || this.downloadInfo.getThrottleNetSpeed() > 0) {
            return false;
        }
        JSONObject jSONObjectOptJSONObject = DownloadSetting.obtain(this.downloadInfo.getId()).optJSONObject(DownloadSettingKeys.SEGMENT_CONFIG);
        List<Segment> segments = this.downloadCache.getSegments(this.downloadInfo.getId());
        if (this.downloadInfo.getCurBytes() > 0) {
            if (segments == null || segments.isEmpty()) {
                return false;
            }
            if (jSONObjectOptJSONObject == null) {
                jSONObjectOptJSONObject = new JSONObject();
            }
        }
        if (jSONObjectOptJSONObject == null) {
            return false;
        }
        this.segmentDispatcher = new SegmentDispatcher(this.downloadInfo, SegmentStrategy.from(jSONObjectOptJSONObject), this);
        if (checkIsStoppedByUser()) {
            Logger.i(TAG, "downloadSegments: is stopped by user");
            if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                this.segmentDispatcher.cancel();
            } else {
                this.segmentDispatcher.pause();
            }
            return true;
        }
        return this.segmentDispatcher.downloadSegments(segments);
    }

    private void resetRetainRetryTimes() {
        if (DownloadSetting.obtain(this.downloadInfo.getId()).optInt(DownloadSettingKeys.RESET_RETAIN_RETRY_TIMES, 0) != 1 || this.resetRetainRetryTimesCount >= 3) {
            return;
        }
        this.retainRetryTimes.set(this.downloadInfo.isBackUpUrlUsed() ? this.downloadInfo.getBackUpUrlRetryCount() : this.downloadInfo.getRetryCount());
        this.resetRetainRetryTimesCount++;
    }

    private void doFirstConnect(String str, List<HttpHeader> list, long j) throws RetryThrowable, BaseException {
        createFirstHeadConnection(str, list, j);
        IDownloadHeadHttpConnection iDownloadHeadHttpConnection = this.firstHeadConnection;
        if (iDownloadHeadHttpConnection != null) {
            try {
                handleFirstConnection(str, iDownloadHeadHttpConnection, j);
            } catch (Throwable unused) {
                this.firstHeadConnectionFailed = true;
            }
        }
        if (this.firstHeadConnection == null || this.firstHeadConnectionFailed) {
            createFirstGetConnection(str, list);
            handleFirstConnection(str, this.firstGetConnection, j);
        }
    }

    private void endDownloadRunnable() {
        boolean zDoTaskStatusHandle;
        boolean z;
        Logger.d(TAG, "endDownloadRunnable::runStatus=" + this.runStatus);
        boolean z2 = (this.runStatus == RunStatus.RUN_STATUS_PAUSE || this.runStatus == RunStatus.RUN_STATUS_CANCELED) ? false : true;
        try {
            zDoTaskStatusHandle = doTaskStatusHandle();
            z = false;
        } catch (Exception e) {
            if (e instanceof BaseException) {
                this.statusHandler.onError((BaseException) e);
            } else {
                this.statusHandler.onError(new BaseException(DownloadErrorCode.ERROR_DOWNLOAD_FINALLY_UNKNOWN, e));
            }
            zDoTaskStatusHandle = true;
            z = true;
        }
        if (zDoTaskStatusHandle || z) {
            this.isAlive.set(false);
            if (z2) {
                try {
                    AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
                    if (downloadEngine != null) {
                        downloadEngine.removeDownloadRunnable(this);
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    IDownloadMonitorDepend monitorDepend = this.downloadTask.getMonitorDepend();
                    DownloadInfo downloadInfo = this.downloadInfo;
                    BaseException baseException = new BaseException(1014, DownloadUtils.getErrorMsgWithTagPrefix(th, "removeDownloadRunnable"));
                    DownloadInfo downloadInfo2 = this.downloadInfo;
                    DownloadMonitorHelper.monitorSendWithTaskMonitor(monitorDepend, downloadInfo, baseException, downloadInfo2 != null ? downloadInfo2.getStatus() : 0);
                    return;
                }
            }
            return;
        }
        this.needJumpToStart = true;
        Logger.d(TAG, "jump to restart");
    }

    private void createFirstHeadConnection(String str, List<HttpHeader> list, long j) throws RetryThrowable, BaseException {
        FakeDownloadHeadHttpConnection cachedHeadConnection;
        boolean z = true;
        if (this.downloadInfo.getChunkCount() == 1 && (cachedHeadConnection = DownloadConnectionPool.getInstance().getCachedHeadConnection(str, list)) != null) {
            this.firstHeadConnection = cachedHeadConnection;
            this.downloadInfo.setPreconnectLevel(1);
        }
        if (this.firstHeadConnection == null && !this.firstHeadConnectionFailed && this.downloadInfo.isHeadConnectionAvailable()) {
            try {
                int iOptInt = this.setting.optInt(DownloadSettingKeys.NET_LIB_STRATEGY);
                if (this.setting.optInt(DownloadSettingKeys.MONITOR_DOWNLOAD_CONNECT, 0) <= 0) {
                    z = false;
                }
                this.firstHeadConnection = DownloadComponentManager.downloadWithHeadConnection(str, list, iOptInt, z, this.downloadInfo);
            } catch (Throwable th) {
                this.downloadInfo.setHeadConnectionException(DownloadUtils.getThrowableMsg(th));
            }
        }
    }

    private void createFirstGetConnection(String str, List<HttpHeader> list) throws RetryThrowable, BaseException {
        IDownloadHttpConnection iDownloadHttpConnectionDownloadWithConnection;
        if (this.firstGetConnection != null) {
            return;
        }
        FakeDownloadHttpConnection cachedDownloadConnection = this.downloadInfo.getChunkCount() == 1 ? DownloadConnectionPool.getInstance().getCachedDownloadConnection(str, list) : null;
        try {
            if (cachedDownloadConnection != null) {
                setHttpResponseStatus(this.firstGetConnection);
                this.downloadInfo.setPreconnectLevel(2);
                this.firstGetConnection = cachedDownloadConnection;
            } else {
                try {
                    iDownloadHttpConnectionDownloadWithConnection = DownloadComponentManager.downloadWithConnection(this.downloadInfo.isNeedDefaultHttpServiceBackUp(), this.downloadInfo.getMaxBytes(), str, null, list, this.setting.optInt(DownloadSettingKeys.NET_LIB_STRATEGY), this.setting.optInt(DownloadSettingKeys.MONITOR_DOWNLOAD_CONNECT, 0) > 0, this.downloadInfo);
                    this.firstGetConnection = iDownloadHttpConnectionDownloadWithConnection;
                } catch (BaseException e) {
                    throw e;
                } catch (Throwable th) {
                    if (this.downloadInfo.isExpiredRedownload() && DownloadUtils.isResponseCode304Error(th) && DownloadUtils.hasDownloadCacheHeader(list)) {
                        Logger.d(TAG, "dcache=execepiton responseCode=304 lastModified not changed, use local file.. old cacheControl=" + this.downloadInfo.getCacheControl());
                        long jOptInt = DownloadUtils.parserMaxAge(this.downloadInfo.getCacheControl());
                        if (jOptInt <= 0) {
                            jOptInt = DownloadSetting.obtain(this.downloadInfo.getId()).optInt(DownloadSettingKeys.DEFAULT_304_MAX_AGE, 300);
                        }
                        this.downloadInfo.setCacheExpiredTime(System.currentTimeMillis() + (jOptInt * 1000));
                        throw new DownloadFileExistException(this.existTargetFileName);
                    }
                    if (DownloadUtils.isResponseCode416Error(th)) {
                        handleResponseCodeError("", "http code 416");
                    } else if (DownloadUtils.isResponseCode412Error(th)) {
                        handleResponseCodeError("", "http code 412");
                    } else {
                        DownloadUtils.parseException(th, "CreateFirstConnection");
                    }
                    iDownloadHttpConnectionDownloadWithConnection = this.firstGetConnection;
                }
                setHttpResponseStatus(iDownloadHttpConnectionDownloadWithConnection);
            }
            if (this.firstGetConnection == null) {
                throw new BaseException(DownloadErrorCode.ERROR_NO_CONNECTION, new IOException("download can't continue, firstConnection is null"));
            }
        } catch (Throwable th2) {
            setHttpResponseStatus(this.firstGetConnection);
            throw th2;
        }
    }

    private void closeFirstHeadConnection() {
        IDownloadHeadHttpConnection iDownloadHeadHttpConnection = this.firstHeadConnection;
        if (iDownloadHeadHttpConnection != null) {
            iDownloadHeadHttpConnection.cancel();
            this.firstHeadConnection = null;
        }
    }

    private void closeFirstConnection() {
        IDownloadHttpConnection iDownloadHttpConnection = this.firstGetConnection;
        if (iDownloadHttpConnection != null) {
            iDownloadHttpConnection.end();
            this.firstGetConnection = null;
        }
    }

    private void closeConnection() {
        closeFirstHeadConnection();
        closeFirstConnection();
    }

    public static DownloadChunk createFirstDownloadChunk(DownloadInfo downloadInfo, long j) {
        return new DownloadChunk.Builder(downloadInfo.getId()).chunkIndex(-1).startOffset(0L).oldOffset(j).currentOffset(j).endOffset(0L).contentLength(downloadInfo.getTotalBytes() - j).build();
    }

    private List<HttpHeader> getExtraHeaders(DownloadChunk downloadChunk) {
        List<HttpHeader> listAddRangeHeader = DownloadUtils.addRangeHeader(this.downloadInfo.getExtraHeaders(), this.downloadInfo.geteTag(), downloadChunk);
        if (this.downloadInfo.isExpiredRedownload() && this.needCheckIfModified && this.downloadInfo.getLastModified() != null) {
            listAddRangeHeader.add(new HttpHeader(DownloadUtils.IF_MODIFIED_SINCE, this.downloadInfo.getLastModified()));
            listAddRangeHeader.add(new HttpHeader(DownloadUtils.HEADER_TAG_DOWNLOAD_CACHE, DownloadUtils.HEADER_TAG_DOWNLOAD_CACHE));
            Logger.d(TAG, "dcache::add head IF_MODIFIED_SINCE=" + this.downloadInfo.getLastModified());
        }
        return listAddRangeHeader;
    }

    private void handleFirstResponse() throws BaseException {
        if (this.firstHttpResponseHandler != null) {
            if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                this.downloadInfo.setStatus(-4);
                this.firstHttpResponseHandler.cancel();
            } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
                this.downloadInfo.setStatus(-2);
                this.firstHttpResponseHandler.pause();
            } else {
                this.firstHttpResponseHandler.handleResponse();
            }
        }
    }

    private boolean isStoppedStatus() {
        return this.runStatus == RunStatus.RUN_STATUS_CANCELED || this.runStatus == RunStatus.RUN_STATUS_PAUSE;
    }

    private boolean checkIsStoppedByUser() {
        if (!isStoppedStatus() && this.downloadInfo.getStatus() != -2) {
            return false;
        }
        if (isStoppedStatus()) {
            return true;
        }
        if (this.downloadInfo.getStatus() == -2) {
            this.runStatus = RunStatus.RUN_STATUS_PAUSE;
            return true;
        }
        if (this.downloadInfo.getStatus() != -4) {
            return true;
        }
        this.runStatus = RunStatus.RUN_STATUS_CANCELED;
        return true;
    }

    private boolean doTaskStatusHandle() {
        if (this.runStatus == RunStatus.RUN_STATUS_ERROR) {
            this.statusHandler.onError(this.errorException);
        } else if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
            this.statusHandler.onCancel();
        } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
            this.statusHandler.onPause();
        } else if (this.runStatus == RunStatus.RUN_STATUS_END_RIGHT_NOW) {
            try {
                this.statusHandler.onCompleteForFileExist();
            } catch (BaseException e) {
                this.statusHandler.onError(e);
            }
        } else if (this.runStatus == RunStatus.RUN_STATUS_END_FOR_FILE_EXIST) {
            try {
                this.statusHandler.onCompleteForFileExist(this.existTargetFileName);
            } catch (BaseException e2) {
                this.statusHandler.onError(e2);
            }
        } else {
            if (this.runStatus == RunStatus.RUN_STATUS_ALL_CHUNK_RETRY_WITH_RESET) {
                this.statusHandler.onRetry(this.errorException, false);
                return false;
            }
            if (this.runStatus == RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER) {
                return true;
            }
            if (this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY && !isAllChunkDownloadComplete()) {
                Logger.d(TAG, "doTaskStatusHandle retryDelay");
                startRetryDelayAlarm();
                return this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY;
            }
            try {
                if (!checkCompletedByteValid()) {
                    return false;
                }
                this.statusHandler.onCompleted();
                RetryScheduler.getInstance().scheduleRetryWhenHasTaskSucceed();
            } catch (Throwable th) {
                onError(new BaseException(1008, DownloadUtils.getErrorMsgWithTagPrefix(th, "doTaskStatusHandle onComplete")));
            }
        }
        return true;
    }

    private boolean isAllChunkDownloadComplete() {
        if (this.downloadInfo.getChunkCount() <= 1) {
            return this.downloadInfo.getCurBytes() > 0 && this.downloadInfo.getCurBytes() == this.downloadInfo.getTotalBytes();
        }
        List<DownloadChunk> downloadChunk = this.downloadCache.getDownloadChunk(this.downloadInfo.getId());
        if (downloadChunk == null || downloadChunk.size() <= 1) {
            return false;
        }
        for (DownloadChunk downloadChunk2 : downloadChunk) {
            if (downloadChunk2 == null || !downloadChunk2.hasNoBytesDownload()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCompletedByteValid() {
        if (this.downloadInfo.isChunked()) {
            DownloadInfo downloadInfo = this.downloadInfo;
            downloadInfo.setTotalBytes(downloadInfo.getCurBytes());
        }
        Logger.i(TAG, "checkCompletedByteValid: downloadInfo.getCurBytes() = " + this.downloadInfo.getCurBytes() + ",  downloadInfo.getTotalBytes() = " + this.downloadInfo.getTotalBytes());
        if (this.downloadInfo.getCurBytes() > 0) {
            if (this.downloadInfo.isIgnoreDataVerify()) {
                return true;
            }
            if (this.downloadInfo.getTotalBytes() > 0 && this.downloadInfo.getCurBytes() == this.downloadInfo.getTotalBytes()) {
                return true;
            }
        }
        this.downloadInfo.setByteInvalidRetryStatus(ByteInvalidRetryStatus.BYTE_INVALID_RETRY_STATUS_RESTART);
        this.downloadInfo.reset();
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
        this.downloadCache.removeSegments(this.downloadInfo.getId());
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
        return false;
    }

    private void startRetryDelayAlarm() {
        this.runStatus = RunStatus.RUN_STATUS_NONE;
    }

    private long getDelayTime() {
        return this.retryDelayTimeCalculator.calculateRetryDelayTime(this.downloadInfo.getCurRetryTimeInTotal(), this.downloadInfo.getTotalRetryCount());
    }

    private void checkHasAnotherSameTask() throws RetryThrowable, BaseException {
        AbsDownloadEngine downloadEngine;
        int id = this.downloadInfo.getId();
        int downloadId = DownloadComponentManager.getDownloadId(this.downloadInfo);
        if (this.downloadInfo.isDownloaded() && !this.downloadInfo.isExpiredRedownload() && !this.needCheckIfModified) {
            throw new BaseException(1009, "file has downloaded");
        }
        DownloadInfo downloadInfo = this.downloadCache.getDownloadInfo(downloadId);
        if (downloadInfo == null || (downloadEngine = DownloadComponentManager.getDownloadEngine()) == null || downloadInfo.getId() == id || !downloadInfo.equalsTask(this.downloadInfo)) {
            return;
        }
        if (downloadEngine.isDownloading(downloadInfo.getId())) {
            this.downloadCache.removeDownloadTaskData(id);
            throw new BaseException(1025, "another same task is downloading");
        }
        List<DownloadChunk> downloadChunk = this.downloadCache.getDownloadChunk(downloadId);
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
        this.downloadCache.removeDownloadTaskData(downloadId);
        if (downloadInfo == null || !downloadInfo.isBreakpointAvailable()) {
            return;
        }
        this.downloadInfo.copyFromCacheData(downloadInfo, false);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        if (downloadChunk != null) {
            for (DownloadChunk downloadChunk2 : downloadChunk) {
                downloadChunk2.setId(id);
                this.downloadCache.addDownloadChunk(downloadChunk2);
            }
        }
        throw new RetryThrowable("retry task because id generator changed");
    }

    private void handleResponseWithMultiChunkFromResume(int i, List<DownloadChunk> list) throws BaseException {
        if (list.size() != i) {
            throw new BaseException(DownloadErrorCode.ERROR_CHUNK_NOT_EQUALS_CACHE, new IllegalArgumentException());
        }
        handleResponseWithMultiChunk(list, this.downloadInfo.getTotalBytes());
    }

    private void handleResponseMultiChunkFromBegin(long j, int i) throws BaseException {
        long j2 = j / ((long) i);
        int id = this.downloadInfo.getId();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        long j3 = 0;
        while (i2 < i) {
            DownloadChunk downloadChunkBuild = new DownloadChunk.Builder(id).chunkIndex(i2).startOffset(j3).oldOffset(j3).currentOffset(j3).endOffset(i2 == i + (-1) ? 0L : (j3 + j2) - 1).build();
            arrayList.add(downloadChunkBuild);
            this.downloadCache.addDownloadChunk(downloadChunkBuild);
            j3 += j2;
            i2++;
        }
        this.downloadInfo.setChunkCount(i);
        this.downloadCache.updateChunkCount(id, i);
        handleResponseWithMultiChunk(arrayList, j);
    }

    private void handleResponseWithMultiChunk(List<DownloadChunk> list, long j) throws BaseException {
        long endOffset;
        for (DownloadChunk downloadChunk : list) {
            if (downloadChunk != null) {
                if (downloadChunk.getEndOffset() == 0) {
                    endOffset = j - downloadChunk.getCurrentOffset();
                } else {
                    endOffset = (downloadChunk.getEndOffset() - downloadChunk.getCurrentOffset()) + 1;
                }
                if (endOffset > 0) {
                    downloadChunk.setContentLength(endOffset);
                    if (this.downloadInfo.isNeedReuseFirstConnection() && this.firstGetConnection != null && (!this.downloadInfo.isHeadConnectionAvailable() || this.firstHeadConnectionFailed)) {
                        if (downloadChunk.getChunkIndex() == 0) {
                            this.downloadChunkRunnableList.add(new DownloadChunkRunnable(downloadChunk, this.downloadTask, this.firstGetConnection, this));
                        } else if (downloadChunk.getChunkIndex() > 0) {
                            this.downloadChunkRunnableList.add(new DownloadChunkRunnable(downloadChunk, this.downloadTask, this));
                        }
                    } else {
                        this.downloadChunkRunnableList.add(new DownloadChunkRunnable(downloadChunk, this.downloadTask, this));
                    }
                }
            }
        }
        if (DownloadExpSwitchCode.isSwitchEnable(64)) {
            ArrayList arrayList = new ArrayList(this.downloadChunkRunnableList.size());
            for (DownloadChunkRunnable downloadChunkRunnable : this.downloadChunkRunnableList) {
                if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                    downloadChunkRunnable.cancel();
                } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
                    downloadChunkRunnable.pause();
                } else {
                    arrayList.add(downloadChunkRunnable);
                }
            }
            try {
                List<Future> listExecuteFutureTasks = DefaultDownloadEngine.executeFutureTasks(arrayList);
                for (Runnable unstartedTask = (Runnable) arrayList.remove(0); unstartedTask != null; unstartedTask = DefaultDownloadEngine.getUnstartedTask(listExecuteFutureTasks)) {
                    if (checkIsStoppedByUser()) {
                        return;
                    }
                    try {
                        unstartedTask.run();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (listExecuteFutureTasks == null || listExecuteFutureTasks.isEmpty()) {
                    return;
                }
                for (Future future : listExecuteFutureTasks) {
                    if (future != null && !future.isDone()) {
                        try {
                            future.get();
                        } catch (Throwable unused) {
                        }
                    }
                }
                return;
            } catch (Throwable unused2) {
                return;
            }
        }
        ArrayList arrayList2 = new ArrayList(this.downloadChunkRunnableList.size());
        for (DownloadChunkRunnable downloadChunkRunnable2 : this.downloadChunkRunnableList) {
            if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                downloadChunkRunnable2.cancel();
            } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
                downloadChunkRunnable2.pause();
            } else {
                arrayList2.add(Executors.callable(downloadChunkRunnable2));
            }
        }
        if (checkIsStoppedByUser()) {
            return;
        }
        try {
            DefaultDownloadEngine.invokeFutureTasks(arrayList2);
        } catch (InterruptedException e) {
            throw new BaseException(1020, e);
        }
    }

    private void handleResponseWithSingleChunk(DownloadChunk downloadChunk, String str, IDownloadHttpConnection iDownloadHttpConnection) throws BaseException {
        downloadChunk.setContentLength(this.downloadInfo.getTotalBytes() - downloadChunk.getCurrentOffset());
        this.downloadInfo.setChunkCount(1);
        this.downloadCache.updateChunkCount(this.downloadInfo.getId(), 1);
        this.firstHttpResponseHandler = new DownloadResponseHandler(this.downloadInfo, str, iDownloadHttpConnection, downloadChunk, this);
        handleFirstResponse();
    }

    private boolean isMultiChunkDownloadAvailable() {
        DownloadInfo downloadInfo = this.downloadInfo;
        if (downloadInfo == null || downloadInfo.isExpiredRedownload()) {
            return false;
        }
        return (!this.canResumeFromCache || this.downloadInfo.getChunkCount() > 1) && !this.downloadInfo.isChunkDowngradeRetryUsed() && this.acceptPartial && !this.isChunked;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:81|6|7|(9:9|(1:11)|12|(2:14|(4:16|(2:18|(1:20)(2:21|22))(1:23)|24|(3:26|(1:28)|49))(2:29|(1:31)(4:32|(1:34)(1:35)|36|37)))|84|50|51|72|73)(2:38|(4:40|(1:42)(1:43)|44|45)(2:46|(2:76|77)))|48|49|84|50|51|72|73) */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x016f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0171, code lost:
    
        com.ss.android.socialbase.downloader.logger.Logger.e(com.ss.android.socialbase.downloader.thread.DownloadRunnable.TAG, "checkSpaceOverflow: setLength1 e = " + r0 + ", mustSetLength = " + r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0193, code lost:
    
        if (r5 >= r24) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x019f, code lost:
    
        r7.setLength(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01a3, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01a5, code lost:
    
        com.ss.android.socialbase.downloader.logger.Logger.e(com.ss.android.socialbase.downloader.thread.DownloadRunnable.TAG, "checkSpaceOverflow: setLength2 ex = " + r0 + ", mustSetLength = " + r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01c1, code lost:
    
        if (r4 == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01c9, code lost:
    
        throw new com.ss.android.socialbase.downloader.exception.BaseException(com.ss.android.socialbase.downloader.constants.DownloadErrorCode.ERROR_OUTPUT_STREAM_SET_LENGTH_IO, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01ca, code lost:
    
        if (r4 != false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01db, code lost:
    
        throw new com.ss.android.socialbase.downloader.exception.BaseException(com.ss.android.socialbase.downloader.constants.DownloadErrorCode.ERROR_OUTPUT_STREAM_SET_LENGTH_IO, r0);
     */
    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void checkSpaceOverflow(long r24) throws com.ss.android.socialbase.downloader.exception.BaseException {
        /*
            Method dump skipped, instruction units count: 493
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadRunnable.checkSpaceOverflow(long):void");
    }

    private void checkSpaceOverflowInProgress() throws BaseException {
        long availableSpaceBytes;
        int iOptInt;
        try {
            availableSpaceBytes = DownloadUtils.getAvailableSpaceBytes(this.downloadInfo.getTempPath());
        } catch (BaseException unused) {
            availableSpaceBytes = 0;
        }
        Logger.i(TAG, "checkSpaceOverflowInProgress: available = " + DownloadUtils.byteToMb(availableSpaceBytes) + "MB");
        if (availableSpaceBytes > 0) {
            long totalBytes = this.downloadInfo.getTotalBytes() - this.downloadInfo.getCurBytes();
            if (availableSpaceBytes < totalBytes && (iOptInt = DownloadSetting.obtain(this.downloadInfo.getId()).optInt(DownloadSettingKeys.SPACE_FILL_MIN_KEEP_MB, 100)) > 0) {
                long j = availableSpaceBytes - (((long) iOptInt) * 1048576);
                Logger.i(TAG, "checkSpaceOverflowInProgress: minKeep  = " + iOptInt + "MB, canDownload = " + DownloadUtils.byteToMb(j) + "MB");
                if (j <= 0) {
                    this.curBytesNeedCheckSpaceOverFlow = 0L;
                    throw new DownloadOutOfSpaceException(availableSpaceBytes, totalBytes);
                }
                this.curBytesNeedCheckSpaceOverFlow = this.downloadInfo.getCurBytes() + j + 1048576;
                return;
            }
        }
        this.curBytesNeedCheckSpaceOverFlow = 0L;
    }

    private void checkWifiTaskValid() throws DownloadRetryNeedlessException {
        if (this.downloadInfo.isOnlyWifi() && !DownloadUtils.checkPermission(DownloadComponentManager.getAppContext(), "android.permission.ACCESS_NETWORK_STATE")) {
            throw new DownloadRetryNeedlessException(1019, String.format("download task need permission:%s", "android.permission.ACCESS_NETWORK_STATE"));
        }
        if (!this.downloadInfo.isDownloadWithWifiValid()) {
            throw new DownloadOnlyWifiException();
        }
        if (!this.downloadInfo.isPauseReserveWithWifiValid()) {
            throw new DownloadPauseReserveWifiException();
        }
    }

    private void checkSavePathValid() throws BaseException {
        if (TextUtils.isEmpty(this.downloadInfo.getSavePath())) {
            throw new BaseException(DownloadErrorCode.ERROR_SAVE_PATH_EMPTY, "download savePath can not be empty");
        }
        if (TextUtils.isEmpty(this.downloadInfo.getName())) {
            throw new BaseException(DownloadErrorCode.ERROR_FILE_NAME_EMPTY, "download name can not be empty");
        }
        File file = new File(this.downloadInfo.getSavePath());
        if (!file.exists()) {
            boolean zMkdirs = file.mkdirs();
            if (zMkdirs || file.exists()) {
                return;
            }
            int i = 0;
            if (DownloadSetting.obtain(this.downloadInfo.getId()).optInt(DownloadSettingKeys.OPT_MKDIR_FAILED, 0) == 1) {
                while (!zMkdirs) {
                    int i2 = i + 1;
                    if (i >= 3) {
                        break;
                    }
                    try {
                        Thread.sleep(10L);
                        zMkdirs = file.mkdirs();
                        i = i2;
                    } catch (InterruptedException unused) {
                    }
                }
                if (zMkdirs || file.exists()) {
                    return;
                }
                if (DownloadUtils.getAvailableSpaceBytes(this.downloadInfo.getSavePath()) < DownloadConstants.MK_DIR_MIN_SPACE) {
                    throw new BaseException(1006, "download savePath directory can not created:" + this.downloadInfo.getSavePath());
                }
                throw new BaseException(DownloadErrorCode.ERROR_SAVE_PATH_CREATE_FAILED, "download savePath directory can not created:" + this.downloadInfo.getSavePath());
            }
            throw new BaseException(DownloadErrorCode.ERROR_SAVE_PATH_CREATE_FAILED, "download savePath directory can not created:" + this.downloadInfo.getSavePath());
        }
        if (file.isDirectory()) {
            return;
        }
        if (DownloadSettingsUtils.isOptimizeSavePath(this.downloadInfo)) {
            file.delete();
            if (file.mkdirs() || file.exists()) {
                return;
            }
            throw new BaseException(DownloadErrorCode.ERROR_SAVE_PATH_NOT_DIRECTORY, "download savePath is not directory:path=" + this.downloadInfo.getSavePath());
        }
        throw new BaseException(DownloadErrorCode.ERROR_SAVE_PATH_NOT_DIRECTORY, "download savePath is not a directory:" + this.downloadInfo.getSavePath());
    }

    private boolean isResponseCodeError(int i, String str, String str2) {
        if (i == 412) {
            return true;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2) || !(this.isResponseFromBegin || this.acceptPartial)) {
            return (i == 201 || i == 416) && this.downloadInfo.getCurBytes() > 0;
        }
        return true;
    }

    private void handleResponseCodeError(String str, String str2) throws RetryThrowable {
        this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
        this.downloadCache.removeSegments(this.downloadInfo.getId());
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
        this.canResumeFromCache = false;
        this.downloadInfo.resetDataForEtagEndure(str);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        throw new RetryThrowable(str2);
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void handleFirstConnection(String str, IDownloadHeadHttpConnection iDownloadHeadHttpConnection, long j) throws RetryThrowable, BaseException {
        IDownloadHeadHttpConnection iDownloadHeadHttpConnection2;
        long contentRangeOfInstanceLength;
        if (iDownloadHeadHttpConnection == null) {
            return;
        }
        try {
            HttpResponse httpResponse = new HttpResponse(str, iDownloadHeadHttpConnection);
            int i = httpResponse.responseCode;
            String contentType = httpResponse.getContentType();
            if (TextUtils.isEmpty(this.downloadInfo.getMimeType()) && !TextUtils.isEmpty(contentType)) {
                this.downloadInfo.setMimeType(contentType);
            }
            boolean zAcceptPartial = httpResponse.acceptPartial();
            this.acceptPartial = zAcceptPartial;
            this.downloadInfo.setSupportPartial(zAcceptPartial);
            this.isResponseFromBegin = httpResponse.isResponseDataFromBegin();
            String str2 = this.downloadInfo.geteTag();
            String etag = httpResponse.getEtag();
            String lastModified = httpResponse.getLastModified();
            String cacheControl = httpResponse.getCacheControl();
            String str3 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("dcache=responseCode=");
            sb.append(i);
            sb.append(" last_modified=");
            sb.append(lastModified);
            sb.append(" CACHE_CONTROL=");
            sb.append(cacheControl);
            sb.append(" max-age=");
            String str4 = etag;
            sb.append(httpResponse.getMaxAge());
            sb.append(" isDeleteCacheIfCheckFailed=");
            sb.append(this.downloadInfo.isDeleteCacheIfCheckFailed());
            Logger.d(str3, sb.toString());
            Logger.d(TAG, "dcache=firstOffset=" + j + " cur=" + lastModified + " before=" + this.downloadInfo.getLastModified() + " cur=" + httpResponse.getTotalLength() + " before=" + this.downloadInfo.getTotalBytes());
            if (!TextUtils.isEmpty(cacheControl)) {
                this.downloadInfo.setCacheControl(cacheControl);
                if (httpResponse.getMaxAge() > 0) {
                    this.downloadInfo.setCacheExpiredTime(System.currentTimeMillis() + (httpResponse.getMaxAge() * 1000));
                }
            }
            if (this.downloadInfo.isExpiredRedownload() && this.needCheckIfModified && !TextUtils.isEmpty(this.existTargetFileName)) {
                boolean z = false;
                if (i == 304 || ((!TextUtils.isEmpty(this.downloadInfo.getLastModified()) || !this.downloadInfo.isDeleteCacheIfCheckFailed()) && TextUtils.equals(lastModified, this.downloadInfo.getLastModified()))) {
                    z = true;
                }
                if (z) {
                    Logger.d(TAG, "dcache=responseCode=" + i + " lastModified not changed, use local file  " + lastModified);
                    throw new DownloadFileExistException(this.existTargetFileName);
                }
                DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
            }
            if (j > 0 && this.downloadInfo.isExpiredRedownload() && !TextUtils.equals(lastModified, this.downloadInfo.getLastModified())) {
                Logger.d(TAG, "dcache cdn file change, so retry");
                handleResponseCodeError("", "cdn file changed");
            }
            if (!TextUtils.isEmpty(lastModified)) {
                this.downloadInfo.setLastModified(lastModified);
            }
            if (isResponseCodeError(i, str2, str4)) {
                iDownloadHeadHttpConnection2 = iDownloadHeadHttpConnection;
                if (iDownloadHeadHttpConnection2 instanceof IDownloadHttpConnection) {
                    if (!TextUtils.isEmpty(str2) && str2.equals(str4)) {
                        str4 = "";
                    }
                    handleResponseCodeError(str4, "eTag of server file changed");
                } else {
                    throw new DownloadHttpException(1002, i, "");
                }
            } else {
                iDownloadHeadHttpConnection2 = iDownloadHeadHttpConnection;
            }
            if (!this.acceptPartial && !this.isResponseFromBegin) {
                if (i == 403) {
                    throw new BaseException(DownloadErrorCode.ERROR_DOWNLOAD_FORBIDDEN, "response code error : 403");
                }
                throw new DownloadHttpException(1004, i, "response code error : " + i);
            }
            if (this.isResponseFromBegin && j > 0) {
                if (iDownloadHeadHttpConnection2 instanceof IDownloadHttpConnection) {
                    handleResponseCodeError("", "http head request not support");
                } else {
                    throw new BaseException(1004, "isResponseFromBegin but firstOffset > 0");
                }
            }
            long contentLength = httpResponse.getContentLength();
            if (!(iDownloadHeadHttpConnection2 instanceof IDownloadHttpConnection) && contentLength < 0 && DownloadSettingsUtils.isOptimizeHeadRequest(this.downloadInfo)) {
                throw new BaseException(1004, "");
            }
            String fileNameFromConnection = TextUtils.isEmpty(this.downloadInfo.getName()) ? DownloadUtils.getFileNameFromConnection(iDownloadHeadHttpConnection2, this.downloadInfo.getUrl()) : "";
            boolean zIsChunked = httpResponse.isChunked();
            this.isChunked = zIsChunked;
            if (!zIsChunked && contentLength == 0 && !(iDownloadHeadHttpConnection2 instanceof IDownloadHttpConnection)) {
                throw new BaseException(1004, "");
            }
            if (this.isChunked) {
                contentRangeOfInstanceLength = -1;
            } else {
                String respHeadFieldIgnoreCase = DownloadUtils.getRespHeadFieldIgnoreCase(iDownloadHeadHttpConnection2, DownloadUtils.CONTENT_RANGE);
                Logger.i(TAG, "firstConnection: contentRange = " + respHeadFieldIgnoreCase);
                if (!TextUtils.isEmpty(respHeadFieldIgnoreCase) && this.setting.optBugFix(DownloadSettingKeys.BugFix.FIX_GET_TOTAL_BYTES, true)) {
                    contentRangeOfInstanceLength = DownloadUtils.parseContentRangeOfInstanceLength(respHeadFieldIgnoreCase);
                    Logger.i(TAG, "firstConnection: 1 totalLength = " + contentRangeOfInstanceLength);
                } else {
                    contentRangeOfInstanceLength = j + contentLength;
                    Logger.e(TAG, "firstConnection: 2 totalLength = " + contentRangeOfInstanceLength + ", contentLength = " + contentLength);
                }
            }
            if (!TextUtils.isEmpty(this.downloadInfo.getTaskKey()) && this.downloadInfo.getTotalBytes() > 0 && contentRangeOfInstanceLength != this.downloadInfo.getTotalBytes()) {
                if (iDownloadHeadHttpConnection2 instanceof IDownloadHttpConnection) {
                    handleResponseCodeError("", "file totalLength changed");
                } else {
                    throw new DownloadHttpException(1002, i, "");
                }
            }
            if (checkIsStoppedByUser()) {
                return;
            }
            if (this.downloadInfo.getExpectFileLength() > 0 && DownloadSetting.obtain(this.downloadInfo.getId()).optInt(DownloadSettingKeys.FORCE_CHECK_FILE_LENGTH) == 1 && this.downloadInfo.getExpectFileLength() != contentRangeOfInstanceLength) {
                throw new BaseException(DownloadErrorCode.ERROR_CHECK_FILE_LENGTH_FAILED, "expectFileLength = " + this.downloadInfo.getExpectFileLength() + " , totalLength = " + contentRangeOfInstanceLength);
            }
            this.statusHandler.onFirstConnectionSuccessed(contentRangeOfInstanceLength, str4, fileNameFromConnection);
        } catch (BaseException e) {
            throw e;
        } catch (RetryThrowable e2) {
            throw e2;
        } catch (Throwable th) {
            DownloadUtils.parseException(th, "HandleFirstConnection");
        }
    }

    public boolean isAlive() {
        return this.isAlive.get();
    }

    public int getDownloadId() {
        DownloadInfo downloadInfo = this.downloadInfo;
        if (downloadInfo != null) {
            return downloadInfo.getId();
        }
        return 0;
    }

    public void prepareDownload() {
        this.prepareDownloadTime = System.currentTimeMillis();
        this.statusHandler.onPrepare();
    }

    private void checkTaskCanResume() {
        long curByte = DownloadUtils.getCurByte(this.downloadInfo);
        long curBytes = this.downloadInfo.getCurBytes();
        if (curByte != curBytes) {
            Logger.w(TAG, "checkTaskCanResume: offset = " + curByte + ", curBytes = " + curBytes);
        }
        this.downloadInfo.setCurBytes(curByte);
        boolean z = curByte > 0;
        this.canResumeFromCache = z;
        if (z || this.needCheckIfModified) {
            return;
        }
        Logger.i(TAG, "checkTaskCanResume: deleteAllDownloadFiles");
        this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
        this.downloadCache.removeSegments(this.downloadInfo.getId());
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public boolean onProgress(long j) throws BaseException {
        if (this.curBytesNeedCheckSpaceOverFlow > 0 && this.downloadInfo.getCurBytes() > this.curBytesNeedCheckSpaceOverFlow) {
            checkSpaceOverflowInProgress();
        }
        return this.statusHandler.onProgress(j);
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onCompleted(DownloadChunkRunnable downloadChunkRunnable) {
        if (this.isSingleChunk) {
            return;
        }
        synchronized (this) {
            this.downloadChunkRunnableList.remove(downloadChunkRunnable);
        }
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public boolean canRetry(BaseException baseException) {
        if (this.segmentDispatcher != null && DownloadUtils.isNetworkError(baseException) && this.retainRetryTimes.get() < this.downloadInfo.getRetryCount()) {
            return false;
        }
        if (DownloadUtils.isResponseCodeError(baseException)) {
            if (this.isSingleChunk && !this.isTriedFixRangeNotSatisfiable) {
                DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
                this.isTriedFixRangeNotSatisfiable = true;
            }
            return true;
        }
        AtomicInteger atomicInteger = this.retainRetryTimes;
        if ((atomicInteger == null || atomicInteger.get() <= 0) && !this.downloadInfo.hasNextBackupUrl()) {
            if (baseException == null) {
                return false;
            }
            if ((baseException.getErrorCode() != 1011 && (baseException.getCause() == null || !(baseException.getCause() instanceof SSLHandshakeException))) || !this.downloadInfo.canReplaceHttpForRetry()) {
                return false;
            }
        }
        return !(baseException instanceof DownloadRetryNeedlessException);
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onError(BaseException baseException) {
        Logger.d(TAG, "onError:" + baseException.getMessage());
        this.runStatus = RunStatus.RUN_STATUS_ERROR;
        this.errorException = baseException;
        cancelAllChunkRunnable();
    }

    private void clearCurrentDownloadData() {
        Logger.w(TAG, "clearCurrentDownloadData::" + Log.getStackTraceString(new Throwable()));
        try {
            this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
            this.downloadCache.removeSegments(this.downloadInfo.getId());
            DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
            this.canResumeFromCache = false;
            this.downloadInfo.resetDataForEtagEndure("");
            this.downloadCache.updateDownloadInfo(this.downloadInfo);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onChunkDowngradeRetry(BaseException baseException) {
        DownloadInfo downloadInfo = this.downloadInfo;
        if (downloadInfo != null) {
            downloadInfo.setChunkDowngradeRetryUsed(true);
        }
        onAllChunkRetryWithReset(baseException, false);
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onAllChunkRetryWithReset(BaseException baseException, boolean z) {
        Logger.d(TAG, "onAllChunkRetryWithReset");
        this.runStatus = RunStatus.RUN_STATUS_ALL_CHUNK_RETRY_WITH_RESET;
        this.errorException = baseException;
        cancelAllChunkRunnable();
        if (z ? handleRetryTime(baseException) : false) {
            return;
        }
        clearCurrentDownloadData();
    }

    private void cancelAllChunkRunnable() {
        try {
            for (DownloadChunkRunnable downloadChunkRunnable : (ArrayList) this.downloadChunkRunnableList.clone()) {
                if (downloadChunkRunnable != null) {
                    downloadChunkRunnable.cancel();
                }
            }
        } catch (Throwable th) {
            Logger.i(TAG, "cancelAllChunkRunnable: " + th.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleForbiddenCallback(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.downloadInfo.setForbiddenBackupUrls(list, this.runStatus == RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER);
        AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
        if (downloadEngine != null) {
            downloadEngine.restartAsyncWaitingTask(this.downloadInfo.getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDiskSpaceCallback() {
        AbsDownloadEngine downloadEngine;
        if (checkIsStoppedByUser() || (downloadEngine = DownloadComponentManager.getDownloadEngine()) == null) {
            return;
        }
        downloadEngine.restartAsyncWaitingTask(this.downloadInfo.getId());
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public RetryCheckStatus onSingleChunkRetry(DownloadChunk downloadChunk, BaseException baseException, long j) {
        if (isStoppedStatus()) {
            return RetryCheckStatus.RETURN;
        }
        if (baseException != null && (baseException.getErrorCode() == 1047 || DownloadUtils.isInsufficientSpaceError(baseException))) {
            return onRetry(baseException, j);
        }
        this.errorException = baseException;
        this.downloadInfo.increaseCurBytes(-j);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        if (handleRetryTime(baseException)) {
            return RetryCheckStatus.RETURN;
        }
        this.statusHandler.onSingleChunkRetry(downloadChunk, baseException, this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY);
        if (this.runStatus != RunStatus.RUN_STATUS_RETRY_DELAY && this.downloadInfo.isNeedRetryDelay()) {
            long delayTime = getDelayTime();
            if (delayTime > 0) {
                Logger.i(TAG, "onSingleChunkRetry with delay time " + delayTime);
                try {
                    Thread.sleep(delayTime);
                } catch (Throwable th) {
                    Logger.w(TAG, "onSingleChunkRetry:" + th.getMessage());
                }
            }
        }
        return RetryCheckStatus.CONTINUE;
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public RetryCheckStatus onRetry(BaseException baseException, long j) {
        long totalBytes;
        long avaliableSpaceBytes;
        boolean z;
        this.errorException = baseException;
        this.downloadInfo.increaseCurBytes(-j);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        if (isStoppedStatus()) {
            return RetryCheckStatus.RETURN;
        }
        if (baseException != null && baseException.getErrorCode() == 1047) {
            if (this.forbiddenHandler == null || this.downloadInfo.isForbiddenRetryed()) {
                if (handleRetryTime(baseException)) {
                    return RetryCheckStatus.RETURN;
                }
            } else {
                AbsDownloadForbiddenCallback absDownloadForbiddenCallback = new AbsDownloadForbiddenCallback() { // from class: com.ss.android.socialbase.downloader.thread.DownloadRunnable.1
                    @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadForbiddenCallback, com.ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback
                    public void onCallback(List<String> list) {
                        super.onCallback(list);
                        DownloadRunnable.this.handleForbiddenCallback(list);
                    }
                };
                boolean zOnForbidden = this.forbiddenHandler.onForbidden(absDownloadForbiddenCallback);
                this.downloadInfo.setForbiddenRetryed();
                if (zOnForbidden) {
                    if (!absDownloadForbiddenCallback.hasCallback()) {
                        cancelAllChunkRunnable();
                        this.statusHandler.handleWaitingAsyncHandler();
                        this.runStatus = RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER;
                        return RetryCheckStatus.RETURN;
                    }
                    z = true;
                }
            }
            z = false;
        } else if (DownloadUtils.isInsufficientSpaceError(baseException)) {
            if (this.diskSpaceHandler == null) {
                onError(baseException);
                return RetryCheckStatus.RETURN;
            }
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            IDownloadDiskSpaceCallback iDownloadDiskSpaceCallback = new IDownloadDiskSpaceCallback() { // from class: com.ss.android.socialbase.downloader.thread.DownloadRunnable.2
                @Override // com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback
                public void onDiskCleaned() {
                    synchronized (DownloadRunnable.this) {
                        atomicBoolean.set(true);
                        DownloadRunnable.this.handleDiskSpaceCallback();
                    }
                }
            };
            if (baseException instanceof DownloadOutOfSpaceException) {
                DownloadOutOfSpaceException downloadOutOfSpaceException = (DownloadOutOfSpaceException) baseException;
                avaliableSpaceBytes = downloadOutOfSpaceException.getAvaliableSpaceBytes();
                totalBytes = downloadOutOfSpaceException.getRequiredSpaceBytes();
            } else {
                totalBytes = this.downloadInfo.getTotalBytes();
                avaliableSpaceBytes = -1;
            }
            synchronized (this) {
                if (this.diskSpaceHandler.cleanUpDisk(avaliableSpaceBytes, totalBytes, iDownloadDiskSpaceCallback)) {
                    if (!DownloadSetting.obtain(this.downloadInfo.getId()).optBugFix(DownloadSettingKeys.BugFix.NOT_DELETE_WHEN_CLEAN_SPACE, false)) {
                        checkCompletedByteValid();
                    }
                    if (!atomicBoolean.get()) {
                        if (this.runStatus != RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER) {
                            this.runStatus = RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER;
                            cancelAllChunkRunnable();
                            this.statusHandler.handleWaitingAsyncHandler();
                        }
                        return RetryCheckStatus.RETURN;
                    }
                    if (handleRetryTime(baseException)) {
                        return RetryCheckStatus.RETURN;
                    }
                    z = true;
                } else {
                    if (this.runStatus == RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER) {
                        return RetryCheckStatus.RETURN;
                    }
                    onError(baseException);
                    return RetryCheckStatus.RETURN;
                }
            }
        } else {
            if (handleRetryTime(baseException)) {
                return RetryCheckStatus.RETURN;
            }
            z = false;
        }
        if (!z && checkNeedRetryDelay()) {
            cancelAllChunkRunnable();
        }
        this.statusHandler.onRetry(baseException, this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY);
        return this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY ? RetryCheckStatus.RETURN : RetryCheckStatus.CONTINUE;
    }

    private boolean handleRetryTime(BaseException baseException) {
        AtomicInteger atomicInteger = this.retainRetryTimes;
        boolean z = true;
        if (atomicInteger != null) {
            if (atomicInteger.get() <= 0 || (baseException != null && baseException.getErrorCode() == 1070)) {
                if (this.downloadInfo.trySwitchToNextBackupUrl()) {
                    this.retainRetryTimes.set(this.downloadInfo.getBackUpUrlRetryCount());
                    this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.get());
                } else if (baseException != null && ((baseException.getErrorCode() == 1011 || (baseException.getCause() != null && (baseException.getCause() instanceof SSLHandshakeException))) && this.downloadInfo.canReplaceHttpForRetry())) {
                    this.retainRetryTimes.set(this.downloadInfo.getRetryCount());
                    this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.get());
                    this.downloadInfo.setHttpsToHttpRetryUsed(true);
                } else {
                    onError(new BaseException(baseException.getErrorCode(), String.format("retry for exception, but current retry time : %s , retry Time %s all used, last error is %s", String.valueOf(this.retainRetryTimes), String.valueOf(this.downloadInfo.getRetryCount()), baseException.getErrorMessage())));
                    return true;
                }
                z = false;
            }
            if (this.runStatus != RunStatus.RUN_STATUS_RETRY_DELAY && z) {
                this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.decrementAndGet());
            }
            return false;
        }
        onError(new BaseException(DownloadErrorCode.ERROR_RETAIN_RETRY_TIME_IS_NULL, "retry for exception, but retain retry time is null, last error is :" + baseException.getErrorMessage()));
        return true;
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public synchronized DownloadChunk getUnCompletedSubChunk(int i) {
        DownloadChunk unCompletedSubChunk;
        if (this.downloadInfo.getChunkCount() < 2) {
            return null;
        }
        List<DownloadChunk> downloadChunk = this.downloadCache.getDownloadChunk(this.downloadInfo.getId());
        if (downloadChunk != null && !downloadChunk.isEmpty()) {
            for (int i2 = 0; i2 < downloadChunk.size(); i2++) {
                DownloadChunk downloadChunk2 = downloadChunk.get(i2);
                if (downloadChunk2 != null && (unCompletedSubChunk = getUnCompletedSubChunk(downloadChunk2, i)) != null) {
                    return unCompletedSubChunk;
                }
            }
            return null;
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void setHttpResponseStatus(IDownloadHeadHttpConnection iDownloadHeadHttpConnection) {
        boolean z;
        if (iDownloadHeadHttpConnection != null) {
            try {
                int responseCode = iDownloadHeadHttpConnection.getResponseCode();
                this.downloadInfo.setHttpStatusCode(responseCode);
                this.downloadInfo.setHttpStatusMessage(DownloadHttpUtils.httpCodeToMessage(responseCode));
                z = true;
            } catch (Throwable th) {
                th.printStackTrace();
                z = false;
            }
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        this.downloadInfo.setHttpStatusCode(-1);
        this.downloadInfo.setHttpStatusMessage("");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.ss.android.socialbase.downloader.model.DownloadChunk getUnCompletedSubChunk(com.ss.android.socialbase.downloader.model.DownloadChunk r9, int r10) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadRunnable.getUnCompletedSubChunk(com.ss.android.socialbase.downloader.model.DownloadChunk, int):com.ss.android.socialbase.downloader.model.DownloadChunk");
    }

    private IRetryDelayTimeCalculator getRetryDelayTimeCalculator(DownloadTask downloadTask) {
        IRetryDelayTimeCalculator retryDelayTimeCalculator = downloadTask.getRetryDelayTimeCalculator();
        if (retryDelayTimeCalculator != null) {
            return retryDelayTimeCalculator;
        }
        DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
        if (downloadInfo != null) {
            String retryDelayTimeArray = downloadInfo.getRetryDelayTimeArray();
            if (!TextUtils.isEmpty(retryDelayTimeArray)) {
                return new RetryDelayTimeParamCalculator(retryDelayTimeArray);
            }
        }
        return DownloadComponentManager.getRetryDelayTimeCalculator();
    }

    public Future getFuture() {
        return this.mFuture;
    }

    public void setFuture(Future future) {
        this.mFuture = future;
    }

    public void setThrottleNetSpeed(long j) {
        IDownloadHttpConnection iDownloadHttpConnection = this.firstGetConnection;
        if (iDownloadHttpConnection != null && (iDownloadHttpConnection instanceof AbsDownloadHttpConnection)) {
            try {
                ((AbsDownloadHttpConnection) iDownloadHttpConnection).setThrottleNetSpeedWhenRunning(j);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
