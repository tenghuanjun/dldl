package com.ss.android.download.api.config;

import android.content.Context;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface c {
    void a(Context context, DownloadModel downloadModel, DownloadController downloadController, DownloadEventConfig downloadEventConfig);

    void a(Context context, DownloadModel downloadModel, DownloadController downloadController, DownloadEventConfig downloadEventConfig, String str, String str2);
}
