package com.ss.android.socialbase.downloader.exception;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadTTNetException extends BaseException {
    public DownloadTTNetException(int i, String str) {
        super(i, str);
    }

    public DownloadTTNetException(int i, Throwable th) {
        super(i, th);
    }

    public DownloadTTNetException setRequestLog(String str) {
        setExtraInfo(str);
        return this;
    }

    public String getRequestLog() {
        return getExtraInfo();
    }
}
