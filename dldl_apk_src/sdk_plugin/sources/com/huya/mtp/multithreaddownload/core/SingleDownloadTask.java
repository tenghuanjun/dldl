package com.huya.mtp.multithreaddownload.core;

import com.huya.mtp.multithreaddownload.BufferedRandomAccessFile;
import com.huya.mtp.multithreaddownload.DownloadInfo;
import com.huya.mtp.multithreaddownload.architecture.DownloadTask;
import com.huya.mtp.multithreaddownload.db.ThreadInfo;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SingleDownloadTask extends DownloadTaskImpl {
    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected Map<String, String> getHttpHeaders(ThreadInfo threadInfo) {
        return null;
    }

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected int getResponseCode() {
        return 200;
    }

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected void insertIntoDB(ThreadInfo threadInfo) {
    }

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected void updateDB(ThreadInfo threadInfo) {
    }

    public SingleDownloadTask(DownloadInfo downloadInfo, ThreadInfo threadInfo, ExecutorService executorService, DownloadTask.OnDownloadListener onDownloadListener) {
        super(downloadInfo, threadInfo, executorService, onDownloadListener);
    }

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected BufferedRandomAccessFile getFile(File file, String str, long j) throws IOException {
        if (!file.exists()) {
            file.mkdirs();
        }
        BufferedRandomAccessFile bufferedRandomAccessFile = new BufferedRandomAccessFile(new File(file, str), "rwd");
        bufferedRandomAccessFile.seek(0L);
        return bufferedRandomAccessFile;
    }

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
