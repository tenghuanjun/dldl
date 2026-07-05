package com.huya.mtp.multithreaddownload.architecture;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Downloader {

    public interface OnDownloaderDestroyedListener {
        void onDestroyed(String str, Downloader downloader);
    }

    void cancel();

    boolean isRunning();

    void onDestroy();

    void pause();

    void start();
}
