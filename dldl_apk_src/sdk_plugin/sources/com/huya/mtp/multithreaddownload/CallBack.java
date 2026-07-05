package com.huya.mtp.multithreaddownload;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface CallBack {
    void onCompleted();

    void onConnected(long j, boolean z);

    void onConnecting();

    void onDownloadCanceled();

    void onDownloadPaused();

    void onFailed(DownloadException downloadException);

    void onProgress(long j, long j2, float f);

    void onStarted();
}
