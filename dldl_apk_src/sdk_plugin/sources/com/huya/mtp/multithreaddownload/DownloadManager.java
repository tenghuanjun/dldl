package com.huya.mtp.multithreaddownload;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.huya.mtp.multithreaddownload.architecture.DownloadStatusDelivery;
import com.huya.mtp.multithreaddownload.architecture.Downloader;
import com.huya.mtp.multithreaddownload.core.DownloadResponseImpl;
import com.huya.mtp.multithreaddownload.core.DownloadStatusDeliveryImpl;
import com.huya.mtp.multithreaddownload.core.DownloaderImpl;
import com.huya.mtp.multithreaddownload.db.DataBaseManager;
import com.huya.mtp.multithreaddownload.speedlimit.SpeedLimitManager;
import com.huya.mtp.multithreaddownload.util.L;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DownloadManager implements Downloader.OnDownloaderDestroyedListener {
    public static final String TAG = DownloadManager.class.getSimpleName();
    private static DownloadManager sDownloadManager = new DownloadManager();
    private DownloadConfiguration mConfig;
    private ExecutorService mDBExecutorService;
    private DataBaseManager mDBManager;
    private DownloadStatusDelivery mDelivery;
    private Map<String, Downloader> mDownloaderMap = new ConcurrentHashMap();
    private ExecutorService mExecutorService;
    private SpeedLimitManager mSpeedLimitManager;

    public static DownloadManager getInstance() {
        return sDownloadManager;
    }

    private DownloadManager() {
    }

    public synchronized void init(Context context) {
        init(context, new DownloadConfiguration());
    }

    public synchronized void init(Context context, DownloadConfiguration downloadConfiguration) {
        if (downloadConfiguration.getThreadNum() > downloadConfiguration.getMaxThreadNum()) {
            throw new IllegalArgumentException("thread num must < max thread num");
        }
        this.mConfig = downloadConfiguration;
        this.mDBManager = DataBaseManager.getInstance(context);
        this.mSpeedLimitManager = SpeedLimitManager.getInstance();
        this.mExecutorService = Executors.newFixedThreadPool(this.mConfig.getMaxThreadNum());
        this.mDBExecutorService = Executors.newFixedThreadPool(this.mConfig.getMaxThreadNum());
        this.mDelivery = new DownloadStatusDeliveryImpl(new Handler(Looper.getMainLooper()));
    }

    public boolean hasInited() {
        return this.mConfig != null;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.Downloader.OnDownloaderDestroyedListener
    public void onDestroyed(String str, Downloader downloader) {
        Log.i("Downloader", "onDestroyed key:" + str);
        if (this.mDownloaderMap.containsKey(str)) {
            Log.i("Downloader", "onDestroyed contain key");
            this.mDownloaderMap.remove(str);
        }
    }

    public synchronized void download(DownloadRequest downloadRequest, String str, CallBack callBack) {
        String strCreateKey = createKey(str);
        if (check(strCreateKey)) {
            deleteDBIfFileNotExist(downloadRequest, strCreateKey);
            DownloaderImpl downloaderImpl = new DownloaderImpl(downloadRequest, new DownloadResponseImpl(this.mDelivery, callBack), this.mExecutorService, this.mDBExecutorService, this.mDBManager, strCreateKey, this.mConfig, this);
            this.mDownloaderMap.put(strCreateKey, downloaderImpl);
            downloaderImpl.start();
        }
    }

    public synchronized void pause(String str) {
        Downloader downloader;
        String strCreateKey = createKey(str);
        if (this.mDownloaderMap.containsKey(strCreateKey) && (downloader = this.mDownloaderMap.get(strCreateKey)) != null && downloader.isRunning()) {
            downloader.pause();
        }
    }

    public synchronized void cancel(String str) {
        Downloader downloader;
        String strCreateKey = createKey(str);
        if (this.mDownloaderMap.containsKey(strCreateKey) && (downloader = this.mDownloaderMap.get(strCreateKey)) != null) {
            downloader.cancel();
        }
    }

    public synchronized void pauseAll() {
        for (Downloader downloader : this.mDownloaderMap.values()) {
            if (downloader != null && downloader.isRunning()) {
                downloader.pause();
            }
        }
    }

    public synchronized void cancelAll() {
        for (Downloader downloader : this.mDownloaderMap.values()) {
            if (downloader != null && downloader.isRunning()) {
                downloader.cancel();
            }
        }
    }

    public DownloadInfo getDownloadProgress(String str) {
        String strCreateKey = createKey(str);
        DataBaseManager dataBaseManager = this.mDBManager;
        if (dataBaseManager == null) {
            return null;
        }
        dataBaseManager.getThreadInfos(strCreateKey);
        return null;
    }

    public synchronized int getDownloadingTaskNumber() {
        return this.mDownloaderMap.size();
    }

    public synchronized boolean isDownloaderRunning(String str) {
        Downloader downloader;
        String strCreateKey = createKey(str);
        if (this.mDownloaderMap.containsKey(strCreateKey) && (downloader = this.mDownloaderMap.get(strCreateKey)) != null) {
            if (downloader.isRunning()) {
                return true;
            }
        }
        return false;
    }

    public boolean existTaskFromDB(String str) {
        String strCreateKey = createKey(str);
        DataBaseManager dataBaseManager = this.mDBManager;
        return dataBaseManager != null && dataBaseManager.existsTask(strCreateKey);
    }

    public void setGlobalSpeedLimit(long j) {
        this.mSpeedLimitManager.setGlobalSpeedLimit(j);
    }

    public void stopGlobalSpeedLimit() {
        this.mSpeedLimitManager.stopGlobalSpeedLimit();
    }

    public void setTaskSpeedLimit(String str, long j) {
        this.mSpeedLimitManager.setTaskSpeedLimit(str, j);
    }

    public void stopTaskSpeedLimit(String str) {
        this.mSpeedLimitManager.stopTaskSpeedLimit(str);
    }

    private void deleteDBIfFileNotExist(DownloadRequest downloadRequest, String str) {
        DataBaseManager dataBaseManager;
        if (downloadRequest == null || downloadRequest.getFolder() == null || TextUtils.isEmpty(downloadRequest.getTitle()) || new File(downloadRequest.getFolder(), downloadRequest.getTitle().toString()).exists() || (dataBaseManager = this.mDBManager) == null || !dataBaseManager.existsTask(str)) {
            return;
        }
        this.mDBManager.delete(str);
    }

    private synchronized boolean check(String str) {
        Downloader downloader;
        if (this.mDownloaderMap.containsKey(str) && (downloader = this.mDownloaderMap.get(str)) != null) {
            if (downloader.isRunning()) {
                L.w("Task has been started!");
                return false;
            }
            L.e(TAG, "Downloader instance with same tag has not been destroyed!");
        }
        return true;
    }

    private static String createKey(String str) {
        if (str == null) {
            throw new NullPointerException("Tag can't be null!");
        }
        return String.valueOf(str.hashCode());
    }
}
