package com.ss.android.socialbase.downloader.impls;

import com.ss.android.socialbase.downloader.constants.DownloadStatus;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.thread.DownloadRunnable;
import com.ss.android.socialbase.downloader.thread.DownloadThreadPool;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DefaultDownloadEngine extends AbsDownloadEngine {
    private static final String TAG = "DefaultDownloadEngine";
    private static DownloadThreadPool downloadThreadPool;

    public static void invokeFutureTasks(List<Callable<Object>> list) throws InterruptedException {
        ExecutorService chunkDownloadThreadExecutorService = DownloadComponentManager.getChunkDownloadThreadExecutorService();
        if (chunkDownloadThreadExecutorService != null) {
            chunkDownloadThreadExecutorService.invokeAll(list);
        }
    }

    public static List<Future> executeFutureTasks(List<Runnable> list) {
        ExecutorService chunkDownloadThreadExecutorService = DownloadComponentManager.getChunkDownloadThreadExecutorService();
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Runnable> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(chunkDownloadThreadExecutorService.submit(it.next()));
        }
        return arrayList;
    }

    public DefaultDownloadEngine() {
        downloadThreadPool = new DownloadThreadPool();
    }

    public static Runnable getUnstartedTask(List<Future> list) {
        BlockingQueue<Runnable> queue;
        Runnable runnable;
        if (list != null && !list.isEmpty()) {
            try {
                ExecutorService chunkDownloadThreadExecutorService = DownloadComponentManager.getChunkDownloadThreadExecutorService();
                if ((chunkDownloadThreadExecutorService instanceof ThreadPoolExecutor) && (queue = ((ThreadPoolExecutor) chunkDownloadThreadExecutorService).getQueue()) != null && !queue.isEmpty()) {
                    Iterator<Future> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            runnable = null;
                            break;
                        }
                        Future next = it.next();
                        if ((next instanceof Runnable) && queue.remove(next)) {
                            runnable = (Runnable) next;
                            break;
                        }
                    }
                    if (runnable != null) {
                        list.remove(runnable);
                        return runnable;
                    }
                }
            } catch (Throwable th) {
                Logger.w(TAG, "getUnstartedTask() error: " + th.toString());
            }
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public boolean isDownloading(int i) {
        DownloadInfo downloadInfo;
        DownloadThreadPool downloadThreadPool2 = downloadThreadPool;
        if (downloadThreadPool2 == null || !downloadThreadPool2.containsTask(i) || (downloadInfo = getDownloadInfo(i)) == null) {
            return false;
        }
        if (DownloadStatus.isDownloading(downloadInfo.getStatus())) {
            return true;
        }
        doPause(i);
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public void removeDownloadRunnable(DownloadRunnable downloadRunnable) {
        DownloadThreadPool downloadThreadPool2 = downloadThreadPool;
        if (downloadThreadPool2 == null) {
            return;
        }
        downloadThreadPool2.removeUnAliveDownloadRunnable(downloadRunnable);
    }

    @Override // com.ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public void doDownload(int i, DownloadTask downloadTask) {
        if (downloadTask == null) {
            return;
        }
        Logger.d("DownloadTask", "start doDownload for task : " + i);
        downloadThreadPool.execute(new DownloadRunnable(downloadTask, this.mainHandler));
    }

    @Override // com.ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public void doPause(int i) {
        DownloadThreadPool downloadThreadPool2 = downloadThreadPool;
        if (downloadThreadPool2 == null) {
            return;
        }
        downloadThreadPool2.pause(i);
    }

    @Override // com.ss.android.socialbase.downloader.impls.AbsDownloadEngine
    protected DownloadRunnable doCancel(int i) {
        DownloadThreadPool downloadThreadPool2 = downloadThreadPool;
        if (downloadThreadPool2 == null) {
            return null;
        }
        return downloadThreadPool2.cancel(i);
    }

    @Override // com.ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public List<Integer> getAllAliveDownloadIds() {
        return downloadThreadPool.getAllAliveDownloadIds();
    }

    @Override // com.ss.android.socialbase.downloader.impls.AbsDownloadEngine
    public void doSetThrottleNetSpeed(int i, long j) {
        DownloadThreadPool downloadThreadPool2 = downloadThreadPool;
        if (downloadThreadPool2 == null) {
            return;
        }
        downloadThreadPool2.setThrottleNetSpeed(i, j);
    }
}
