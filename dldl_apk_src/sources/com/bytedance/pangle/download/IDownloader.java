package com.bytedance.pangle.download;

import android.content.Context;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface IDownloader {
    void downloader(Context context, String str, String str2, boolean z, String str3, int i, String str4, List<String> list, IZeusDownloadListener iZeusDownloadListener, IZeusDownloadInterceptor iZeusDownloadInterceptor);
}
