package com.ss.android.socialbase.downloader.downloader;

import android.os.IBinder;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IDownloadServiceConnectionListener {
    void onServiceConnection(IBinder iBinder);

    void onServiceDisConnection();
}
