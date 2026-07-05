package com.ss.android.socialbase.downloader.downloader;

import com.ss.android.socialbase.downloader.network.NetworkQuality;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IChunkAdjustCalculator {
    int calculateChunkCount(int i, NetworkQuality networkQuality);
}
