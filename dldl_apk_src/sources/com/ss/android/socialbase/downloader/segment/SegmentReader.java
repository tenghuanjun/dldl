package com.ss.android.socialbase.downloader.segment;

import android.text.TextUtils;
import android.util.Log;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.exception.RetryThrowable;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.model.HttpResponse;
import com.ss.android.socialbase.downloader.network.AbsDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadStenographer;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
class SegmentReader implements Runnable {
    private static final int SEGMENT_APPLY_RETRY_MAX_COUNT = 50;
    private static final int SWITCH_URL_MAX_COUNT = 30;
    private static final String TAG = "SegmentReader";
    private volatile boolean changeSegment;
    private volatile boolean closed;
    volatile long connectEndTime;
    volatile long connectStartTime;
    String curHostIp;
    String curHostRealIp;
    private int curRetryCount;
    volatile Segment curSegment;
    String curUrl;
    private final DownloadInfo downloadInfo;
    private volatile long endOffsetInConnection;
    private boolean exited;
    private boolean failed;
    private BaseException failedException;
    private Future future;
    private final ISegmentCallback host;
    private IDownloadHttpConnection httpConnection;
    private HttpResponse httpResponse;
    private boolean httpsToHttpRetryUsed;
    private long lastConnectStartTime;
    private final IBufferPool pool;
    private volatile long readBytes;
    volatile long readEndTime;
    volatile long readStartTime;
    private volatile boolean reconnect;
    private int retryCount;
    private int segmentApplyRetryTimes;
    private volatile long segmentNewEndOffset;
    private final DownloadSetting setting;
    private long startOffsetInConnection;
    private DownloadStenographer stenographer;
    private int switchUrlTimes;
    private Thread thread;
    private volatile boolean threadDirty;
    final int threadIndex;
    UrlRecord urlRecord;
    private final List<Segment> succeedSegments = new ArrayList();
    private volatile long curSegmentReadOffset = -1;

    SegmentReader(DownloadInfo downloadInfo, SegmentDispatcher segmentDispatcher, IBufferPool iBufferPool, UrlRecord urlRecord, int i) {
        this.downloadInfo = downloadInfo;
        this.host = segmentDispatcher;
        this.pool = iBufferPool;
        this.setting = DownloadSetting.obtain(downloadInfo.getId());
        this.urlRecord = urlRecord;
        this.threadIndex = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x007b, code lost:
    
        r6.curSegment = null;
        r2 = r6.host;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.segment.SegmentReader.run():void");
    }

    private boolean download(Segment segment) throws BaseException {
        initParams();
        while (true) {
            try {
                doConnect(segment);
                loopAndRead(segment);
                return true;
            } catch (SegmentApplyException e) {
                this.failedException = e;
                throw e;
            } catch (Throwable th) {
                try {
                    Logger.e(TAG, "download: e = " + th + ", threadIndex = " + this.threadIndex + ", reconnect = " + this.reconnect + ", closed = " + this.closed);
                    if (this.closed) {
                        return false;
                    }
                    if (this.reconnect) {
                        this.reconnect = false;
                        try {
                            Thread.interrupted();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                        if (this.changeSegment) {
                            this.changeSegment = false;
                            throw new SegmentApplyException(5, "download");
                        }
                    } else {
                        th.printStackTrace();
                        BaseException e2 = null;
                        if (th instanceof BaseException) {
                            e2 = th;
                        } else {
                            try {
                                DownloadUtils.parseException(th, "download");
                            } catch (BaseException e3) {
                                e2 = e3;
                            }
                        }
                        if (e2 == null || !handleFailedAndCheckRetry(segment, e2)) {
                            return false;
                        }
                    }
                } finally {
                    releaseDownload();
                }
            }
        }
        return false;
    }

    private void releaseDownload() {
        this.lastConnectStartTime = this.connectStartTime;
        this.connectStartTime = -1L;
        this.connectEndTime = -1L;
        this.readStartTime = -1L;
        this.readEndTime = -1L;
        closeConnection();
    }

    private void closeConnection() {
        IDownloadHttpConnection iDownloadHttpConnection = this.httpConnection;
        if (iDownloadHttpConnection != null) {
            try {
                Logger.i(TAG, "closeConnection: thread = " + this.threadIndex);
                iDownloadHttpConnection.end();
                iDownloadHttpConnection.cancel();
            } catch (Throwable unused) {
            }
        }
    }

    private void initParams() {
        this.httpsToHttpRetryUsed = false;
        resetRetryTimes();
    }

    private void resetRetryTimes() {
        this.retryCount = this.urlRecord.isMainUrl ? this.downloadInfo.getRetryCount() : this.downloadInfo.getBackUpUrlRetryCount();
        this.curRetryCount = 0;
    }

    boolean switchUrlRecord(UrlRecord urlRecord) {
        int i = this.switchUrlTimes;
        if (i >= 30) {
            return false;
        }
        this.switchUrlTimes = i + 1;
        UrlRecord urlRecord2 = this.urlRecord;
        if (urlRecord2 != null) {
            urlRecord2.recordUnUse(this);
        }
        urlRecord.recordUse(this);
        this.urlRecord = urlRecord;
        resetRetryTimes();
        return true;
    }

    int getCurRetryCount() {
        return this.curRetryCount;
    }

    int getRetryCount() {
        return this.retryCount;
    }

    private boolean handleFailedAndCheckRetry(Segment segment, BaseException baseException) {
        Logger.e(TAG, "handleDownloadFailed:  e = " + baseException + ", curRetryCount = " + this.curRetryCount + ", retryCount = " + this.retryCount);
        this.failedException = baseException;
        this.urlRecord.recordFailed();
        this.host.onSegmentRetry(this, this.urlRecord, segment, baseException, this.curRetryCount, this.retryCount);
        int i = this.curRetryCount;
        if (i < this.retryCount) {
            this.curRetryCount = i + 1;
            return true;
        }
        if (checkCanUseHttpsToHttpRetry(baseException)) {
            return true;
        }
        this.host.onSegmentFailed(this, this.urlRecord, segment, baseException);
        return false;
    }

    private boolean checkCanUseHttpsToHttpRetry(BaseException baseException) {
        if (!DownloadUtils.isHttpsError(baseException)) {
            return false;
        }
        String str = this.urlRecord.url;
        if (TextUtils.isEmpty(str) || !str.startsWith("https") || !this.downloadInfo.isNeedHttpsToHttpRetry() || this.httpsToHttpRetryUsed) {
            return false;
        }
        this.httpsToHttpRetryUsed = true;
        resetRetryTimes();
        return true;
    }

    private void doConnect(Segment segment) throws RetryThrowable, BaseException {
        createConnection(segment);
        this.host.onSegmentConnected(this, segment, this.urlRecord, this.httpResponse);
        this.urlRecord.recordSucceed();
    }

    private void createConnection(Segment segment) throws BaseException {
        String strReplaceFirst;
        String str;
        IDownloadHttpConnection iDownloadHttpConnectionDownloadWithConnection;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.connectEndTime = 0L;
                this.connectStartTime = jCurrentTimeMillis;
                this.startOffsetInConnection = segment.getCurrentOffsetRead();
                this.endOffsetInConnection = segment.getEndOffset();
                if (this.endOffsetInConnection > 0 && this.startOffsetInConnection > this.endOffsetInConnection) {
                    throw new SegmentApplyException(6, "createConn, " + segment);
                }
                this.stenographer = new DownloadStenographer();
                List<HttpHeader> listAddRangeHeader = DownloadUtils.addRangeHeader(this.downloadInfo.getExtraHeaders(), this.downloadInfo.geteTag(), this.startOffsetInConnection, this.endOffsetInConnection);
                listAddRangeHeader.add(new HttpHeader("Segment-Index", String.valueOf(segment.getIndex())));
                listAddRangeHeader.add(new HttpHeader("Thread-Index", String.valueOf(this.threadIndex)));
                DownloadUtils.addThrottleNetSpeed(listAddRangeHeader, this.downloadInfo);
                DownloadUtils.addTTNetProtectTimeout(listAddRangeHeader, this.downloadInfo);
                strReplaceFirst = this.urlRecord.url;
                if (this.httpsToHttpRetryUsed && !TextUtils.isEmpty(strReplaceFirst) && strReplaceFirst.startsWith("https")) {
                    strReplaceFirst = strReplaceFirst.replaceFirst("https", "http");
                }
                str = this.urlRecord.ip;
                Logger.i(TAG, "createConnectionBegin: url = " + strReplaceFirst + ", ip = " + str + ", segment = " + segment + ", threadIndex = " + this.threadIndex);
                this.curUrl = strReplaceFirst;
                this.curHostIp = str;
                iDownloadHttpConnectionDownloadWithConnection = DownloadComponentManager.downloadWithConnection(this.downloadInfo.isNeedDefaultHttpServiceBackUp(), this.downloadInfo.getMaxBytes(), strReplaceFirst, str, listAddRangeHeader, 0, jCurrentTimeMillis - this.lastConnectStartTime > 3000 && this.setting.optInt(DownloadSettingKeys.MONITOR_DOWNLOAD_CONNECT) > 0, this.downloadInfo);
            } finally {
                this.connectEndTime = System.currentTimeMillis();
            }
        } catch (BaseException e) {
            throw e;
        } catch (Throwable th) {
            DownloadUtils.parseException(th, "createConn");
        }
        if (iDownloadHttpConnectionDownloadWithConnection == null) {
            throw new BaseException(DownloadErrorCode.ERROR_NO_CONNECTION, new IOException("download can't continue, chunk connection is null"));
        }
        this.httpConnection = iDownloadHttpConnectionDownloadWithConnection;
        this.httpResponse = new HttpResponse(strReplaceFirst, iDownloadHttpConnectionDownloadWithConnection);
        if (this.closed) {
            throw new StreamClosedException("createConn");
        }
        if (iDownloadHttpConnectionDownloadWithConnection instanceof AbsDownloadHttpConnection) {
            this.curHostRealIp = ((AbsDownloadHttpConnection) iDownloadHttpConnectionDownloadWithConnection).getHostIp();
        }
        Log.i(TAG, "createConnectionSuccess: url = " + strReplaceFirst + ", ip = " + str + ", hostRealIp = " + this.curHostRealIp + ", threadIndex = " + this.threadIndex);
    }

    /* JADX WARN: Removed duplicated region for block: B:211:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0179 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0180 A[Catch: all -> 0x0274, BaseException -> 0x0279, TryCatch #22 {BaseException -> 0x0279, all -> 0x0274, blocks: (B:86:0x016e, B:88:0x0179, B:89:0x0180, B:91:0x0186, B:93:0x018c, B:96:0x0195, B:97:0x0199), top: B:255:0x016e }] */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:77:0x015c
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void loopAndRead(com.ss.android.socialbase.downloader.segment.Segment r32) throws com.ss.android.socialbase.downloader.exception.BaseException {
        /*
            Method dump skipped, instruction units count: 998
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.segment.SegmentReader.loopAndRead(com.ss.android.socialbase.downloader.segment.Segment):void");
    }

    private Buffer probeFirstBuffer(IBufferPool iBufferPool, InputStream inputStream) throws Throwable {
        int i;
        Buffer bufferObtain = iBufferPool.obtain();
        try {
            i = inputStream.read(bufferObtain.data);
        } catch (Throwable th) {
            th = th;
            i = -1;
        }
        try {
            if (i == -1) {
                throw new BaseException(DownloadErrorCode.ERROR_PROBE_FIRST_BUFFER, "probe");
            }
            bufferObtain.size = i;
            if (i == -1) {
                iBufferPool.recycle(bufferObtain);
            }
            return bufferObtain;
        } catch (Throwable th2) {
            th = th2;
            if (i == -1) {
                iBufferPool.recycle(bufferObtain);
            }
            throw th;
        }
    }

    private long refreshSegmentEndOffset() {
        long j = this.segmentNewEndOffset;
        this.segmentNewEndOffset = 0L;
        return j <= 0 ? LongCompanionObject.MAX_VALUE : j;
    }

    public boolean adjustSegmentEndOffset(long j) {
        long j2 = this.endOffsetInConnection;
        if (j <= 0 && j2 > 0) {
            return false;
        }
        if (j > j2 && j2 > 0) {
            return false;
        }
        this.segmentNewEndOffset = j;
        this.threadDirty = true;
        return true;
    }

    void updateReadBytes() {
        UrlRecord urlRecord = this.urlRecord;
        try {
            synchronized (this.host) {
                long readingBytes = getReadingBytes();
                if (readingBytes > 0) {
                    this.readBytes += readingBytes;
                    urlRecord.increaseDownloadBytes(readingBytes);
                }
                this.curSegmentReadOffset = -1L;
            }
        } catch (Throwable unused) {
        }
    }

    public long getReadBytes() {
        long readingBytes;
        synchronized (this.host) {
            readingBytes = this.readBytes + getReadingBytes();
        }
        return readingBytes;
    }

    public long getReadingBytes() {
        synchronized (this.host) {
            long j = this.curSegmentReadOffset;
            long j2 = this.startOffsetInConnection;
            if (j2 < 0 || j <= j2) {
                return 0L;
            }
            return j - j2;
        }
    }

    public long getCurSegmentReadOffset() {
        return this.curSegmentReadOffset;
    }

    public void close() {
        Logger.i(TAG, "close: threadIndex = " + this.threadIndex);
        synchronized (this) {
            this.closed = true;
            this.threadDirty = true;
        }
        closeConnection();
        Future future = this.future;
        if (future != null) {
            this.future = null;
            try {
                future.cancel(true);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void reconnect() {
        reconnect(false);
    }

    public void reconnect(boolean z) {
        Logger.i(TAG, "reconnect: threadIndex = " + this.threadIndex);
        synchronized (this) {
            this.changeSegment = z;
            this.reconnect = true;
            this.threadDirty = true;
        }
        closeConnection();
        Thread thread = this.thread;
        if (thread != null) {
            try {
                Log.i(TAG, "reconnect: t.interrupt threadIndex = " + this.threadIndex);
                thread.interrupt();
            } catch (Throwable unused) {
            }
        }
    }

    void setFuture(Future future) {
        this.future = future;
    }

    void setFailed(boolean z) {
        this.failed = z;
    }

    boolean isFailed() {
        return this.failed;
    }

    public boolean isExited() {
        return this.exited;
    }

    public void setExited(boolean z) {
        this.exited = z;
    }

    BaseException getFailedException() {
        return this.failedException;
    }

    void markProgress(long j) {
        long j2 = this.curSegmentReadOffset;
        DownloadStenographer downloadStenographer = this.stenographer;
        if (j2 < 0 || downloadStenographer == null) {
            return;
        }
        Log.i(TAG, "markProgress: curSegmentReadOffset = " + j2 + ", threadIndex = " + this.threadIndex);
        downloadStenographer.markProgress(j2, j);
    }

    long getRecentDownloadSpeed(long j, long j2) {
        DownloadStenographer downloadStenographer = this.stenographer;
        if (downloadStenographer == null) {
            return -1L;
        }
        return downloadStenographer.getRecentDownloadSpeed(j, j2);
    }

    long getCurSegmentDownloadSpeed(long j) {
        long j2 = this.readStartTime;
        if (j2 <= 0) {
            return -1L;
        }
        long j3 = j - j2;
        if (j3 <= 0) {
            return -1L;
        }
        long j4 = this.curSegmentReadOffset;
        long j5 = this.startOffsetInConnection;
        if (j5 < 0 || j4 < j5) {
            return 0L;
        }
        return (j4 - j5) / j3;
    }

    long getStartOffsetInConnection() {
        return this.startOffsetInConnection;
    }
}
