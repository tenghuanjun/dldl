package com.aspsine.multithreaddownload.architecture;

import com.aspsine.multithreaddownload.DownloadException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface DownloadResponse {
    void onConnectCanceled();

    void onConnectFailed(DownloadException downloadException);

    void onConnectPaused();

    void onConnected(long j, long j2, boolean z);

    void onConnecting();

    void onDownloadCanceled();

    void onDownloadCompleted();

    void onDownloadFailed(DownloadException downloadException);

    void onDownloadPaused();

    void onDownloadProgress(long j, long j2, float f);

    void onStarted();
}
