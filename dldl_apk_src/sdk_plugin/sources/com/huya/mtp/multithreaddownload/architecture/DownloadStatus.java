package com.huya.mtp.multithreaddownload.architecture;

import com.huya.mtp.multithreaddownload.CallBack;
import com.huya.mtp.multithreaddownload.DownloadException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DownloadStatus {
    public static final int STATUS_CANCELED = 107;
    public static final int STATUS_COMPLETED = 105;
    public static final int STATUS_CONNECTED = 103;
    public static final int STATUS_CONNECTING = 102;
    public static final int STATUS_FAILED = 108;
    public static final int STATUS_INVALID = -1;
    public static final int STATUS_PAUSED = 106;
    public static final int STATUS_PROGRESS = 104;
    public static final int STATUS_STARTED = 101;
    private boolean acceptRanges;
    private CallBack callBack;
    private DownloadException exception;
    private long finished;
    private long length;
    private float percent;
    private int status;
    private long time;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long j) {
        this.length = j;
    }

    public long getFinished() {
        return this.finished;
    }

    public void setFinished(long j) {
        this.finished = j;
    }

    public float getPercent() {
        return this.percent;
    }

    public void setPercent(float f) {
        this.percent = f;
    }

    public boolean isAcceptRanges() {
        return this.acceptRanges;
    }

    public void setAcceptRanges(boolean z) {
        this.acceptRanges = z;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(DownloadException downloadException) {
        this.exception = downloadException;
    }

    public CallBack getCallBack() {
        return this.callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public DownloadStatus copy() {
        DownloadStatus downloadStatus = new DownloadStatus();
        downloadStatus.setStatus(this.status);
        downloadStatus.setTime(this.time);
        downloadStatus.setLength(this.length);
        downloadStatus.setFinished(this.finished);
        downloadStatus.setPercent(this.percent);
        downloadStatus.setAcceptRanges(this.acceptRanges);
        downloadStatus.setException(this.exception);
        downloadStatus.setCallBack(this.callBack);
        return downloadStatus;
    }
}
