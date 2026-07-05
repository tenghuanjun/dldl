package com.aspsine.multithreaddownload.core;

import android.os.Handler;
import android.util.Log;
import com.aspsine.multithreaddownload.CallBack;
import com.aspsine.multithreaddownload.DownloadException;
import com.aspsine.multithreaddownload.architecture.DownloadStatus;
import com.aspsine.multithreaddownload.architecture.DownloadStatusDelivery;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DownloadStatusDeliveryImpl implements DownloadStatusDelivery {
    private Handler mHandler;

    public DownloadStatusDeliveryImpl(Handler handler) {
        this.mHandler = handler;
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadStatusDelivery
    public void post(DownloadStatus downloadStatus) {
        this.mHandler.post(new DownloadStatusDeliveryRunnable(downloadStatus));
    }

    private static class DownloadStatusDeliveryRunnable implements Runnable {
        private final CallBack mCallBack;
        private final DownloadStatus mDownloadStatus;

        public DownloadStatusDeliveryRunnable(DownloadStatus downloadStatus) {
            this.mDownloadStatus = downloadStatus;
            this.mCallBack = downloadStatus.getCallBack();
        }

        @Override // java.lang.Runnable
        public void run() {
            switch (this.mDownloadStatus.getStatus()) {
                case 102:
                    Log.i("Downloader", "DownloadStatusDeliveryImpl callBack onConnecting");
                    this.mCallBack.onConnecting();
                    break;
                case 103:
                    Log.i("Downloader", "DownloadStatusDeliveryImpl callBack onConnected");
                    this.mCallBack.onConnected(this.mDownloadStatus.getLength(), this.mDownloadStatus.isAcceptRanges());
                    break;
                case 104:
                    this.mCallBack.onProgress(this.mDownloadStatus.getFinished(), this.mDownloadStatus.getLength(), this.mDownloadStatus.getPercent());
                    break;
                case 105:
                    Log.i("Downloader", "DownloadStatusDeliveryImpl callBack onCompleted");
                    this.mCallBack.onCompleted();
                    break;
                case 106:
                    Log.i("Downloader", "DownloadStatusDeliveryImpl callBack onDownloadPaused");
                    this.mCallBack.onDownloadPaused();
                    break;
                case 107:
                    Log.i("Downloader", "DownloadStatusDeliveryImpl callBack onDownloadCanceled");
                    this.mCallBack.onDownloadCanceled();
                    break;
                case 108:
                    Log.i("Downloader", "DownloadStatusDeliveryImpl callBack onFailed");
                    this.mCallBack.onFailed((DownloadException) this.mDownloadStatus.getException());
                    break;
            }
        }
    }
}
