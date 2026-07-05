package com.ss.android.download.api.download;

import com.ss.android.download.api.model.DownloadShortInfo;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface DownloadStatusChangeListener {
    void onDownloadActive(DownloadShortInfo downloadShortInfo, int i);

    void onDownloadFailed(DownloadShortInfo downloadShortInfo);

    void onDownloadFinished(DownloadShortInfo downloadShortInfo);

    void onDownloadPaused(DownloadShortInfo downloadShortInfo, int i);

    void onDownloadStart(DownloadModel downloadModel, DownloadController downloadController);

    void onIdle();

    void onInstalled(DownloadShortInfo downloadShortInfo);
}
