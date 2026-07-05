package com.ss.android.socialbase.downloader.thread;

import android.util.SparseArray;
import com.ss.android.socialbase.downloader.constants.DbJsonConstants;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadThreadPool {
    private static ExecutorService executorService = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory("Download_OP_Thread"));
    private int clearTimes = 0;
    private volatile SparseArray<DownloadRunnable> downloadRunnablePool = new SparseArray<>();

    public static void executeOP(Runnable runnable) {
        executorService.execute(runnable);
    }

    public void execute(DownloadRunnable downloadRunnable) {
        downloadRunnable.prepareDownload();
        synchronized (DownloadThreadPool.class) {
            if (this.clearTimes >= 500) {
                clearRunnableNotAlive();
                this.clearTimes = 0;
            } else {
                this.clearTimes++;
            }
            this.downloadRunnablePool.put(downloadRunnable.getDownloadId(), downloadRunnable);
        }
        DownloadTask downloadTask = downloadRunnable.getDownloadTask();
        try {
            ExecutorService mixDefaultThreadExecutor = DownloadComponentManager.getMixDefaultThreadExecutor();
            if (downloadTask != null && downloadTask.getDownloadInfo() != null) {
                if (DownloadConstants.MIME_PLG.equals(downloadTask.getDownloadInfo().getMimeType()) && DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.DIVIDE_PLUGIN, 1) == 1) {
                    downloadTask.getDownloadInfo().safePutToDBJsonData(DbJsonConstants.DBJSON_KEY_EXECUTOR, 3);
                }
                int executorGroup = downloadTask.getDownloadInfo().getExecutorGroup();
                if (executorGroup == 3) {
                    mixDefaultThreadExecutor = DownloadComponentManager.getMixFrequentThreadExecutor();
                } else if (executorGroup == 4) {
                    mixDefaultThreadExecutor = DownloadComponentManager.getMixApkThreadExecutor();
                }
            }
            if (mixDefaultThreadExecutor != null) {
                if (DownloadSetting.obtain(downloadRunnable.getDownloadId()).optBugFix(DownloadSettingKeys.BugFix.BUGFIX_PAUSE_WITH_INTERRUPT, false)) {
                    downloadRunnable.setFuture(mixDefaultThreadExecutor.submit(downloadRunnable));
                    return;
                } else {
                    mixDefaultThreadExecutor.execute(downloadRunnable);
                    return;
                }
            }
            DownloadMonitorHelper.monitorSendWithTaskMonitor(downloadTask.getMonitorDepend(), downloadTask.getDownloadInfo(), new BaseException(1003, "execute failed cpu thread executor service is null"), downloadTask.getDownloadInfo() != null ? downloadTask.getDownloadInfo().getStatus() : 0);
        } catch (Exception e) {
            if (downloadTask != null) {
                DownloadMonitorHelper.monitorSendWithTaskMonitor(downloadTask.getMonitorDepend(), downloadTask.getDownloadInfo(), new BaseException(1003, DownloadUtils.getErrorMsgWithTagPrefix(e, "DownloadThreadPoolExecute")), downloadTask.getDownloadInfo() != null ? downloadTask.getDownloadInfo().getStatus() : 0);
            }
            e.printStackTrace();
        } catch (OutOfMemoryError e2) {
            if (downloadTask != null) {
                DownloadMonitorHelper.monitorSendWithTaskMonitor(downloadTask.getMonitorDepend(), downloadTask.getDownloadInfo(), new BaseException(1003, "execute OOM"), downloadTask.getDownloadInfo() != null ? downloadTask.getDownloadInfo().getStatus() : 0);
            }
            e2.printStackTrace();
        }
    }

    private void clearRunnableNotAlive() {
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.downloadRunnablePool.size(); i++) {
                int iKeyAt = this.downloadRunnablePool.keyAt(i);
                if (!this.downloadRunnablePool.get(iKeyAt).isAlive()) {
                    arrayList.add(Integer.valueOf(iKeyAt));
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                try {
                    Integer num = (Integer) arrayList.get(i2);
                    if (num != null) {
                        this.downloadRunnablePool.remove(num.intValue());
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public void removeUnAliveDownloadRunnable(DownloadRunnable downloadRunnable) {
        if (downloadRunnable == null) {
            return;
        }
        synchronized (DownloadThreadPool.class) {
            try {
                if (DownloadExpSwitchCode.isSwitchEnable(524288)) {
                    int iIndexOfValue = this.downloadRunnablePool.indexOfValue(downloadRunnable);
                    if (iIndexOfValue >= 0) {
                        this.downloadRunnablePool.removeAt(iIndexOfValue);
                    }
                } else {
                    this.downloadRunnablePool.remove(downloadRunnable.getDownloadId());
                }
            } finally {
            }
        }
    }

    public boolean containsTask(int i) {
        synchronized (DownloadThreadPool.class) {
            boolean z = false;
            if (this.downloadRunnablePool != null && this.downloadRunnablePool.size() > 0) {
                DownloadRunnable downloadRunnable = this.downloadRunnablePool.get(i);
                if (downloadRunnable != null && downloadRunnable.isAlive()) {
                    z = true;
                }
                return z;
            }
            return false;
        }
    }

    public DownloadRunnable cancel(int i) {
        synchronized (DownloadThreadPool.class) {
            clearRunnableNotAlive();
            DownloadRunnable downloadRunnable = this.downloadRunnablePool.get(i);
            if (downloadRunnable == null) {
                return null;
            }
            downloadRunnable.cancel();
            removeFromThreadPool(downloadRunnable);
            this.downloadRunnablePool.remove(i);
            return downloadRunnable;
        }
    }

    public void pause(int i) {
        synchronized (DownloadThreadPool.class) {
            clearRunnableNotAlive();
            DownloadRunnable downloadRunnable = this.downloadRunnablePool.get(i);
            if (downloadRunnable != null) {
                downloadRunnable.pause();
                removeFromThreadPool(downloadRunnable);
                this.downloadRunnablePool.remove(i);
            }
        }
    }

    private void removeFromThreadPool(DownloadRunnable downloadRunnable) {
        Future future;
        if (downloadRunnable == null) {
            return;
        }
        try {
            ExecutorService mixDefaultThreadExecutor = DownloadComponentManager.getMixDefaultThreadExecutor();
            DownloadTask downloadTask = downloadRunnable.getDownloadTask();
            if (downloadTask != null && downloadTask.getDownloadInfo() != null) {
                int executorGroup = downloadTask.getDownloadInfo().getExecutorGroup();
                if (executorGroup == 3) {
                    mixDefaultThreadExecutor = DownloadComponentManager.getMixFrequentThreadExecutor();
                } else if (executorGroup == 4) {
                    mixDefaultThreadExecutor = DownloadComponentManager.getMixApkThreadExecutor();
                }
            }
            if (mixDefaultThreadExecutor == null || !(mixDefaultThreadExecutor instanceof ThreadPoolExecutor)) {
                return;
            }
            ((ThreadPoolExecutor) mixDefaultThreadExecutor).remove(downloadRunnable);
            if (!DownloadSetting.obtain(downloadRunnable.getDownloadId()).optBugFix(DownloadSettingKeys.BugFix.BUGFIX_PAUSE_WITH_INTERRUPT, false) || (future = downloadRunnable.getFuture()) == null) {
                return;
            }
            future.cancel(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getAllAliveDownloadIds() {
        ArrayList arrayList;
        synchronized (DownloadThreadPool.class) {
            clearRunnableNotAlive();
            arrayList = new ArrayList();
            for (int i = 0; i < this.downloadRunnablePool.size(); i++) {
                DownloadRunnable downloadRunnable = this.downloadRunnablePool.get(this.downloadRunnablePool.keyAt(i));
                if (downloadRunnable != null) {
                    arrayList.add(Integer.valueOf(downloadRunnable.getDownloadId()));
                }
            }
        }
        return arrayList;
    }

    public void setThrottleNetSpeed(int i, long j) {
        DownloadRunnable downloadRunnable = this.downloadRunnablePool.get(i);
        if (downloadRunnable != null) {
            downloadRunnable.setThrottleNetSpeed(j);
        }
    }
}
