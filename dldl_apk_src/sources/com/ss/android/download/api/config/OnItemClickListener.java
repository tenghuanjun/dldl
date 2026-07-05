package com.ss.android.download.api.config;

import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface OnItemClickListener {
    void onItemClick(DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController);
}
