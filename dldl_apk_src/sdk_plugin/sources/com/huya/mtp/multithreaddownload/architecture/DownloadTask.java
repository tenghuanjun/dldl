package com.huya.mtp.multithreaddownload.architecture;

import com.huya.mtp.multithreaddownload.DownloadException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface DownloadTask extends Runnable {

    public interface OnDownloadListener {
        void onDownloadCanceled();

        void onDownloadCompleted();

        void onDownloadConnecting();

        void onDownloadFailed(DownloadException downloadException);

        void onDownloadPaused();

        void onDownloadProgress(long j, long j2);
    }

    void cancel();

    boolean isCanceled();

    boolean isComplete();

    boolean isDownloading();

    boolean isFailed();

    boolean isPaused();

    void pause();

    @Override // java.lang.Runnable
    void run();
}
