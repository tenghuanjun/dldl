package com.huya.mtp.multithreaddownload.architecture;

import com.huya.mtp.multithreaddownload.DownloadException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ConnectTask extends Runnable {

    public interface OnConnectListener {
        void onConnectCanceled();

        void onConnectFailed(DownloadException downloadException);

        void onConnectPaused();

        void onConnectRedirect(String str);

        void onConnected(long j, long j2, boolean z);

        void onConnecting();
    }

    void cancel();

    boolean isCanceled();

    boolean isConnected();

    boolean isConnecting();

    boolean isFailed();

    boolean isPaused();

    void pause();

    @Override // java.lang.Runnable
    void run();
}
