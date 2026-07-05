package com.bytedance.pangle.download;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface IZeusDownloadListener {
    void onFailed(Throwable th, int i, String str);

    void onProgress(long j, long j2);

    void onStart();

    void onSuccess(String str, String str2, long j);
}
