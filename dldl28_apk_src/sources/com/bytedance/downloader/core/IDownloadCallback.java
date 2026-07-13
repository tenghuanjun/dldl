package com.bytedance.downloader.core;

/* JADX INFO: loaded from: classes2.dex */
public interface IDownloadCallback {
    void onDownloadCancelled(DownloadResponse downloadResponse);

    void onDownloadCompleted(DownloadResponse downloadResponse);

    void onDownloadFailed(DownloadResponse downloadResponse, int i, String str);

    void onDownloadPrepared(DownloadResponse downloadResponse);

    void onDownloadProgress(DownloadResponse downloadResponse, int i);

    void onDownloadRemoved(DownloadResponse downloadResponse);

    void onDownloadSpeed(DownloadResponse downloadResponse, long j);

    void onDownloadStarted(DownloadResponse downloadResponse);

    void onDownloadUpdated(DownloadResponse downloadResponse);

    void onDownloadWarning(DownloadResponse downloadResponse, int i, String str);
}
