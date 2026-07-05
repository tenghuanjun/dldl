package com.huya.mtp.hyns.hysignal;

import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.api.NSDownloadApi;
import com.huya.mtp.multithreaddownload.CallBack;
import com.huya.mtp.multithreaddownload.DownloadException;
import com.huya.mtp.multithreaddownload.DownloadManager;
import com.huya.mtp.multithreaddownload.DownloadRequest;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyDownloadImpl implements NSDownloadApi {
    private DownloadManager mDownloadManager;

    public HyDownloadImpl() {
        DownloadManager downloadManager = DownloadManager.getInstance();
        this.mDownloadManager = downloadManager;
        downloadManager.init(MTPApi.CONTEXT.getApplicationContext());
    }

    @Override // com.huya.mtp.hyns.api.NSDownloadApi
    public void download(NSDownloadApi.NSDownloadRequest nSDownloadRequest, String str, final NSDownloadApi.NSDownloadCallBack nSDownloadCallBack) {
        this.mDownloadManager.download(new DownloadRequest.Builder().setUri(nSDownloadRequest.getUri()).setTitle(nSDownloadRequest.getTitle()).setScannable(nSDownloadRequest.isScannable()).setFolder(nSDownloadRequest.getFolder()).setFileMd5(nSDownloadRequest.getFileMd5()).setDescription(nSDownloadRequest.getDescription()).build(), str, new CallBack() { // from class: com.huya.mtp.hyns.hysignal.HyDownloadImpl.1
            @Override // com.huya.mtp.multithreaddownload.CallBack
            public void onStarted() {
                nSDownloadCallBack.onStarted();
            }

            @Override // com.huya.mtp.multithreaddownload.CallBack
            public void onConnecting() {
                nSDownloadCallBack.onConnecting();
            }

            @Override // com.huya.mtp.multithreaddownload.CallBack
            public void onConnected(long j, boolean z) {
                nSDownloadCallBack.onConnected(j, z);
            }

            @Override // com.huya.mtp.multithreaddownload.CallBack
            public void onProgress(long j, long j2, float f) {
                nSDownloadCallBack.onProgress(j, j2, f);
            }

            @Override // com.huya.mtp.multithreaddownload.CallBack
            public void onCompleted() {
                nSDownloadCallBack.onCompleted();
            }

            @Override // com.huya.mtp.multithreaddownload.CallBack
            public void onDownloadPaused() {
                nSDownloadCallBack.onDownloadPaused();
            }

            @Override // com.huya.mtp.multithreaddownload.CallBack
            public void onDownloadCanceled() {
                nSDownloadCallBack.onDownloadCanceled();
            }

            @Override // com.huya.mtp.multithreaddownload.CallBack
            public void onFailed(DownloadException downloadException) {
                nSDownloadCallBack.onFailed(downloadException);
            }
        });
    }

    @Override // com.huya.mtp.hyns.api.NSDownloadApi
    public void cancel(String str) {
        this.mDownloadManager.cancel(str);
    }

    @Override // com.huya.mtp.hyns.api.NSDownloadApi
    public boolean isDownloaderRunning(String str) {
        return this.mDownloadManager.isDownloaderRunning(str);
    }

    @Override // com.huya.mtp.hyns.api.NSDownloadApi
    public void setGlobalSpeedLimit(long j) {
        this.mDownloadManager.setGlobalSpeedLimit(j);
    }
}
