package com.aspsine.multithreaddownload.core;

import com.aspsine.multithreaddownload.CallBack;
import com.aspsine.multithreaddownload.DownloadException;
import com.aspsine.multithreaddownload.architecture.DownloadResponse;
import com.aspsine.multithreaddownload.architecture.DownloadStatus;
import com.aspsine.multithreaddownload.architecture.DownloadStatusDelivery;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DownloadResponseImpl implements DownloadResponse {
    private DownloadStatusDelivery mDelivery;
    private DownloadStatus mDownloadStatus;

    public DownloadResponseImpl(DownloadStatusDelivery downloadStatusDelivery, CallBack callBack) {
        this.mDelivery = downloadStatusDelivery;
        DownloadStatus downloadStatus = new DownloadStatus();
        this.mDownloadStatus = downloadStatus;
        downloadStatus.setCallBack(callBack);
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onStarted() {
        this.mDownloadStatus.setStatus(101);
        this.mDownloadStatus.getCallBack().onStarted();
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onConnecting() {
        this.mDownloadStatus.setStatus(102);
        this.mDelivery.post(this.mDownloadStatus.copy());
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onConnected(long j, long j2, boolean z) {
        this.mDownloadStatus.setTime(j);
        this.mDownloadStatus.setAcceptRanges(z);
        this.mDownloadStatus.setStatus(103);
        this.mDelivery.post(this.mDownloadStatus.copy());
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onConnectFailed(DownloadException downloadException) {
        this.mDownloadStatus.setException(downloadException);
        this.mDownloadStatus.setStatus(108);
        this.mDelivery.post(this.mDownloadStatus.copy());
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onConnectPaused() {
        this.mDownloadStatus.setStatus(106);
        this.mDelivery.post(this.mDownloadStatus.copy());
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onConnectCanceled() {
        this.mDownloadStatus.setStatus(107);
        this.mDelivery.post(this.mDownloadStatus.copy());
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onDownloadProgress(long j, long j2, float f) {
        this.mDownloadStatus.setFinished(j);
        this.mDownloadStatus.setLength(j2);
        this.mDownloadStatus.setPercent(f);
        this.mDownloadStatus.setStatus(104);
        this.mDelivery.post(this.mDownloadStatus.copy());
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onDownloadCompleted() {
        this.mDownloadStatus.setStatus(105);
        this.mDelivery.post(this.mDownloadStatus.copy());
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onDownloadPaused() {
        this.mDownloadStatus.setStatus(106);
        this.mDelivery.post(this.mDownloadStatus.copy());
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onDownloadCanceled() {
        this.mDownloadStatus.setStatus(107);
        this.mDelivery.post(this.mDownloadStatus.copy());
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadResponse
    public void onDownloadFailed(DownloadException downloadException) {
        this.mDownloadStatus.setException(downloadException);
        this.mDownloadStatus.setStatus(108);
        this.mDelivery.post(this.mDownloadStatus.copy());
    }
}
