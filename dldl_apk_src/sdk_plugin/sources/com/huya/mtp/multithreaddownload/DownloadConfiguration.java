package com.huya.mtp.multithreaddownload;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DownloadConfiguration {
    public static final int DEFAULT_MAX_THREAD_NUMBER = 10;
    public static final int DEFAULT_THREAD_NUMBER = 1;
    private int maxThreadNum = 10;
    private int threadNum = 1;

    public int getMaxThreadNum() {
        return this.maxThreadNum;
    }

    public void setMaxThreadNum(int i) {
        this.maxThreadNum = i;
    }

    public int getThreadNum() {
        return this.threadNum;
    }

    public void setThreadNum(int i) {
        this.threadNum = i;
    }
}
