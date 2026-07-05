package com.ss.android.socialbase.downloader.depend;

import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public abstract class AbsDownloadExtListener extends AbsDownloadListener implements IDownloadExtListener {
    private static final String TAG = AbsDownloadExtListener.class.getSimpleName();

    public void onWaitingDownloadCompleteHandler(DownloadInfo downloadInfo) {
        if (!Logger.debug() || downloadInfo == null) {
            return;
        }
        Logger.d(TAG, " onWaitingDownloadCompleteHandler -- " + downloadInfo.getName());
    }
}
