package com.ss.android.socialbase.downloader.exception;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadHttpException extends BaseException {
    private final int httpStatusCode;

    public DownloadHttpException(int i, int i2, String str) {
        super(i, str);
        this.httpStatusCode = i2;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
