package com.huya.mtp.multithreaddownload.core;

import android.util.Log;
import com.huya.mtp.multithreaddownload.BufferedRandomAccessFile;
import com.huya.mtp.multithreaddownload.DownloadInfo;
import com.huya.mtp.multithreaddownload.architecture.DownloadTask;
import com.huya.mtp.multithreaddownload.db.DataBaseManager;
import com.huya.mtp.multithreaddownload.db.ThreadInfo;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MultiDownloadTask extends DownloadTaskImpl {
    private DataBaseManager mDBManager;

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected int getResponseCode() {
        return 206;
    }

    public MultiDownloadTask(DownloadInfo downloadInfo, ThreadInfo threadInfo, DataBaseManager dataBaseManager, ExecutorService executorService, DownloadTask.OnDownloadListener onDownloadListener) {
        super(downloadInfo, threadInfo, executorService, onDownloadListener);
        this.mDBManager = dataBaseManager;
    }

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected void insertIntoDB(ThreadInfo threadInfo) {
        if (this.mDBManager.existsThread(threadInfo.getTag(), threadInfo.getId())) {
            return;
        }
        this.mDBManager.insert(threadInfo);
    }

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected void updateDB(ThreadInfo threadInfo) {
        this.mDBManager.update(threadInfo.getTag(), threadInfo.getId(), threadInfo.getFinished());
    }

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected Map<String, String> getHttpHeaders(ThreadInfo threadInfo) {
        HashMap map = new HashMap();
        long start = threadInfo.getStart() + threadInfo.getFinished();
        long end = threadInfo.getEnd();
        map.put("Range", "bytes=" + start + "-" + end);
        Log.i("Downloader", "MultiDownloadTask getHttpHeaders Range bytes=" + start + "-" + end);
        return map;
    }

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected BufferedRandomAccessFile getFile(File file, String str, long j) throws IOException {
        if (!file.exists()) {
            file.mkdirs();
        }
        BufferedRandomAccessFile bufferedRandomAccessFile = new BufferedRandomAccessFile(new File(file, str), "rwd");
        bufferedRandomAccessFile.seek(j);
        Log.i("Downloader", "MultiDownloadTask BufferedRandomAccessFile seek offset:" + j);
        return bufferedRandomAccessFile;
    }

    @Override // com.huya.mtp.multithreaddownload.core.DownloadTaskImpl
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
