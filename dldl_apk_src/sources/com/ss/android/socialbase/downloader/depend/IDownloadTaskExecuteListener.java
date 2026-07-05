package com.ss.android.socialbase.downloader.depend;

import com.ss.android.socialbase.downloader.model.DownloadTask;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IDownloadTaskExecuteListener {
    void onFinish(DownloadTask downloadTask, int i);

    void onStart(DownloadTask downloadTask, int i);
}
