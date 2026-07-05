package com.sqwan.afinal.download;

import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface DownloadListener {
    void onFailure(Throwable th, int i, String str);

    void onSuccess(File file);

    void onUpdate(long j, long j2);
}
