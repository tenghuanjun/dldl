package com.ss.android.socialbase.downloader.depend;

import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IDownloadForbiddenCallback {
    boolean hasCallback();

    void onCallback(List<String> list);
}
