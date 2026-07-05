package com.ss.android.download.api.download.a;

import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface a {
    void a(DownloadModel downloadModel, DownloadController downloadController, DownloadEventConfig downloadEventConfig);

    void a(DownloadInfo downloadInfo);

    void a(DownloadInfo downloadInfo, BaseException baseException, String str);

    void a(DownloadInfo downloadInfo, String str);

    void b(DownloadInfo downloadInfo, String str);
}
