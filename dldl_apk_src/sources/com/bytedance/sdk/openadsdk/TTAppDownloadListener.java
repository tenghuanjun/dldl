package com.bytedance.sdk.openadsdk;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface TTAppDownloadListener {
    void onDownloadActive(long j, long j2, String str, String str2);

    void onDownloadFailed(long j, long j2, String str, String str2);

    void onDownloadFinished(long j, String str, String str2);

    void onDownloadPaused(long j, long j2, String str, String str2);

    void onIdle();

    void onInstalled(String str, String str2);
}
