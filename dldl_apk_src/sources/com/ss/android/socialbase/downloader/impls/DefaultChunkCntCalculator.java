package com.ss.android.socialbase.downloader.impls;

import com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DefaultChunkCntCalculator implements IChunkCntCalculator {
    @Override // com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator
    public int calculateChunkCount(long j) {
        return 1;
    }
}
