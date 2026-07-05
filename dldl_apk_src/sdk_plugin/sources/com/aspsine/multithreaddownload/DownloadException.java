package com.aspsine.multithreaddownload;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DownloadException extends Exception {
    private int errorCode;
    private String errorMessage;

    public static final class DetailMessage {
        public static final String FILE_MD5_FAILED = "file md5 failed";
    }

    public DownloadException() {
    }

    public DownloadException(String str) {
        super(str);
        this.errorMessage = str;
    }

    public DownloadException(int i, String str) {
        this(str);
        this.errorCode = i;
    }

    public DownloadException(String str, Throwable th) {
        super(str, th);
        this.errorMessage = str;
    }

    public DownloadException(int i, String str, Throwable th) {
        this(str, th);
        this.errorCode = i;
    }

    public DownloadException(Throwable th) {
        super(th);
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }
}
