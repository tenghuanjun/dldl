package com.duowan.auk.http.downloader;

import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DownLoader {

    public interface DownLoaderListener {
        void onFailed(int i, File file);

        void onProgress(int i, int i2);

        void onSuccess(File file);
    }

    public static DownLoadTask downLoad(String str, File file, DownLoaderListener downLoaderListener) {
        DownLoadTask downLoadTask = new DownLoadTask(file, downLoaderListener);
        downLoadTask.executeOnExecutor(DownLoadTask.THREAD_POOL_EXECUTOR, str);
        return downLoadTask;
    }

    public static DownLoadTask downLoad(String str, File file, DownLoaderListener downLoaderListener, int i, int i2) {
        DownLoadTask downLoadTask = new DownLoadTask(file, downLoaderListener, i, i2);
        downLoadTask.executeOnExecutor(DownLoadTask.THREAD_POOL_EXECUTOR, str);
        return downLoadTask;
    }
}
