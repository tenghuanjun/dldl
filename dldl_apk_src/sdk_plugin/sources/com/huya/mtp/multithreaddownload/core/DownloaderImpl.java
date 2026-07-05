package com.huya.mtp.multithreaddownload.core;

import android.text.TextUtils;
import android.util.Log;
import com.huya.mtp.multithreaddownload.DownloadConfiguration;
import com.huya.mtp.multithreaddownload.DownloadException;
import com.huya.mtp.multithreaddownload.DownloadInfo;
import com.huya.mtp.multithreaddownload.DownloadRequest;
import com.huya.mtp.multithreaddownload.architecture.ConnectTask;
import com.huya.mtp.multithreaddownload.architecture.DownloadResponse;
import com.huya.mtp.multithreaddownload.architecture.DownloadTask;
import com.huya.mtp.multithreaddownload.architecture.Downloader;
import com.huya.mtp.multithreaddownload.db.DataBaseManager;
import com.huya.mtp.multithreaddownload.db.ThreadInfo;
import com.huya.mtp.multithreaddownload.util.FileUtils;
import com.huya.mtp.multithreaddownload.util.StringUtils;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DownloaderImpl implements Downloader, ConnectTask.OnConnectListener, DownloadTask.OnDownloadListener {
    private DownloadConfiguration mConfig;
    private ConnectTask mConnectTask;
    private ExecutorService mDBExecutor;
    private DataBaseManager mDBManager;
    private DownloadInfo mDownloadInfo;
    private List<DownloadTask> mDownloadTasks;
    private ExecutorService mExecutor;
    private Downloader.OnDownloaderDestroyedListener mListener;
    private String mRedirectUri;
    private DownloadRequest mRequest;
    private DownloadResponse mResponse;
    private int mStatus;
    private String mTag;

    public DownloaderImpl(DownloadRequest downloadRequest, DownloadResponse downloadResponse, ExecutorService executorService, ExecutorService executorService2, DataBaseManager dataBaseManager, String str, DownloadConfiguration downloadConfiguration, Downloader.OnDownloaderDestroyedListener onDownloaderDestroyedListener) {
        this.mRequest = downloadRequest;
        this.mResponse = downloadResponse;
        this.mExecutor = executorService;
        this.mDBExecutor = executorService2;
        this.mDBManager = dataBaseManager;
        this.mTag = str;
        this.mConfig = downloadConfiguration;
        this.mListener = onDownloaderDestroyedListener;
        init();
    }

    private void init() {
        this.mDownloadInfo = new DownloadInfo(this.mRequest.getTitle().toString(), this.mRequest.getUri(), this.mRequest.getFileMd5(), this.mRequest.getFolder());
        this.mDownloadTasks = new LinkedList();
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.Downloader
    public boolean isRunning() {
        int i = this.mStatus;
        return i == 101 || i == 102 || i == 103 || i == 104;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.Downloader
    public void start() {
        this.mStatus = 101;
        this.mResponse.onStarted();
        connect();
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.Downloader
    public void pause() {
        ConnectTask connectTask = this.mConnectTask;
        if (connectTask != null) {
            connectTask.pause();
        }
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        while (it.hasNext()) {
            it.next().pause();
        }
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.Downloader
    public void cancel() {
        ConnectTask connectTask = this.mConnectTask;
        if (connectTask != null) {
            connectTask.cancel();
        }
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.Downloader
    public void onDestroy() {
        Log.i("Downloader", "DownloaderImpl onDestroy");
        this.mListener.onDestroyed(this.mTag, this);
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask.OnConnectListener
    public void onConnecting() {
        Log.i("Downloader", "DownloaderImpl onConnecting");
        this.mStatus = 102;
        this.mResponse.onConnecting();
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask.OnConnectListener
    public void onConnectRedirect(String str) {
        Log.i("Downloader", "DownloaderImpl onConnectRedirect");
        this.mRedirectUri = str;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask.OnConnectListener
    public void onConnected(long j, long j2, boolean z) {
        Log.i("Downloader", "DownloaderImpl onConnected");
        this.mStatus = 103;
        this.mResponse.onConnected(j, j2, z);
        this.mDownloadInfo.setAcceptRanges(z);
        this.mDownloadInfo.setLength(j2);
        download(j2, z);
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask.OnConnectListener
    public void onConnectFailed(DownloadException downloadException) {
        Log.i("Downloader", "DownloaderImpl onConnectFailed");
        onDestroy();
        this.mStatus = 108;
        this.mResponse.onConnectFailed(downloadException);
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask.OnConnectListener
    public void onConnectCanceled() {
        Log.i("Downloader", "DownloaderImpl onConnectCanceled");
        onDestroy();
        this.mStatus = 107;
        this.mResponse.onConnectCanceled();
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask.OnConnectListener
    public void onConnectPaused() {
        Log.i("Downloader", "DownloaderImpl onConnectPaused");
        onDestroy();
        this.mStatus = 106;
        this.mResponse.onConnectPaused();
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask.OnDownloadListener
    public void onDownloadConnecting() {
        Log.i("Downloader", "DownloaderImpl onDownloadConnecting");
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask.OnDownloadListener
    public void onDownloadProgress(long j, long j2) {
        this.mStatus = 104;
        this.mResponse.onDownloadProgress(j, j2, (j * 100.0f) / j2);
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask.OnDownloadListener
    public void onDownloadCompleted() {
        Log.i("Downloader", "DownloaderImpl onDownloadCompleted");
        if (isAllComplete()) {
            Log.i("Downloader", "DownloaderImpl onDownloadCompleted all complete");
            deleteFromDB();
            onDestroy();
            if (checkFileMd5()) {
                this.mStatus = 105;
                this.mResponse.onDownloadCompleted();
            } else {
                onDownloadFailed(new DownloadException(108, "file md5 failed"));
            }
        }
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask.OnDownloadListener
    public void onDownloadPaused() {
        Log.i("Downloader", "DownloaderImpl onDownloadPaused");
        if (isAllPaused()) {
            Log.i("Downloader", "DownloaderImpl onDownloadPaused all pause");
            onDestroy();
            this.mStatus = 106;
            this.mResponse.onDownloadPaused();
        }
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask.OnDownloadListener
    public void onDownloadCanceled() {
        Log.i("Downloader", "DownloaderImpl onDownloadCanceled");
        if (isAllCanceled()) {
            Log.i("Downloader", "DownloaderImpl onDownloadPaused all canceled");
            deleteFromDB();
            onDestroy();
            this.mStatus = 107;
            this.mResponse.onDownloadCanceled();
        }
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask.OnDownloadListener
    public void onDownloadFailed(DownloadException downloadException) {
        Log.i("Downloader", "DownloaderImpl onDownloadFailed:" + downloadException.getErrorMessage());
        if (isAllFailed()) {
            Log.i("Downloader", "DownloaderImpl onDownloadFailed all failed");
            onDestroy();
            this.mStatus = 108;
            this.mResponse.onDownloadFailed(downloadException);
        }
    }

    private boolean checkFileMd5() {
        return TextUtils.isEmpty(this.mDownloadInfo.getFileMd5()) || isValidFile(new File(this.mDownloadInfo.getDir(), this.mDownloadInfo.getName()));
    }

    private boolean isValidFile(File file) {
        return file != null && file.exists() && StringUtils.equal(this.mDownloadInfo.getFileMd5(), FileUtils.fileMd5(file), false);
    }

    private void connect() {
        ConnectTaskImpl connectTaskImpl = new ConnectTaskImpl(this.mRequest.getUri(), this);
        this.mConnectTask = connectTaskImpl;
        this.mExecutor.execute(connectTaskImpl);
    }

    private void download(long j, boolean z) {
        initDownloadTasks(j, z);
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        while (it.hasNext()) {
            this.mExecutor.execute(it.next());
        }
    }

    private void initDownloadTasks(long j, boolean z) {
        this.mDownloadTasks.clear();
        if (z) {
            List<ThreadInfo> multiThreadInfos = getMultiThreadInfos(j);
            int finished = 0;
            Iterator<ThreadInfo> it = multiThreadInfos.iterator();
            while (it.hasNext()) {
                finished = (int) (((long) finished) + it.next().getFinished());
            }
            this.mDownloadInfo.setFinished(finished);
            Iterator<ThreadInfo> it2 = multiThreadInfos.iterator();
            while (it2.hasNext()) {
                this.mDownloadTasks.add(new MultiDownloadTask(this.mDownloadInfo, it2.next(), this.mDBManager, this.mDBExecutor, this));
            }
            return;
        }
        this.mDownloadTasks.add(new SingleDownloadTask(this.mDownloadInfo, getSingleThreadInfo(), this.mDBExecutor, this));
    }

    private List<ThreadInfo> getMultiThreadInfos(long j) {
        ThreadInfo threadInfo;
        List<ThreadInfo> threadInfos = this.mDBManager.getThreadInfos(this.mTag);
        if (threadInfos.isEmpty()) {
            int threadNum = this.mConfig.getThreadNum();
            int i = 0;
            while (i < threadNum) {
                long j2 = j / ((long) threadNum);
                long j3 = j2 * ((long) i);
                long j4 = i == threadNum + (-1) ? j : (j2 + j3) - 1;
                String str = this.mRedirectUri;
                if (str == null || str.isEmpty()) {
                    threadInfo = new ThreadInfo(i, this.mTag, this.mRequest.getUri(), j3, j4, 0L);
                } else {
                    threadInfo = new ThreadInfo(i, this.mTag, this.mRedirectUri, j3, j4, 0L);
                }
                threadInfos.add(threadInfo);
                i++;
            }
        }
        return threadInfos;
    }

    private ThreadInfo getSingleThreadInfo() {
        String str = this.mRedirectUri;
        if (str == null || str.isEmpty()) {
            return new ThreadInfo(0, this.mTag, this.mRequest.getUri(), 0L);
        }
        return new ThreadInfo(0, this.mTag, this.mRedirectUri, 0L);
    }

    private boolean isAllComplete() {
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        while (it.hasNext()) {
            if (!it.next().isComplete()) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllFailed() {
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        while (it.hasNext()) {
            if (it.next().isDownloading()) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllPaused() {
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        while (it.hasNext()) {
            if (it.next().isDownloading()) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllCanceled() {
        Iterator<DownloadTask> it = this.mDownloadTasks.iterator();
        while (it.hasNext()) {
            if (it.next().isDownloading()) {
                return false;
            }
        }
        return true;
    }

    private void deleteFromDB() {
        this.mDBManager.delete(this.mTag);
    }
}
