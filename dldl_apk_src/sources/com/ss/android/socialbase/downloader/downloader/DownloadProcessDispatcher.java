package com.ss.android.socialbase.downloader.downloader;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.ListenerType;
import com.ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener;
import com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.ss.android.socialbase.downloader.depend.IDownloaderProcessConnectedListener;
import com.ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.impls.DownloadHandleService;
import com.ss.android.socialbase.downloader.impls.DownloadProxy;
import com.ss.android.socialbase.downloader.model.DownloadChunk;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadProcessDispatcher {
    private static volatile DownloadProcessDispatcher instance;
    private volatile SparseArray<Boolean> independentMap = new SparseArray<>();
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private volatile List<IDownloaderProcessConnectedListener> processConnectedListeners = new ArrayList();

    public static DownloadProcessDispatcher getInstance() {
        if (instance == null) {
            synchronized (DownloadProcessDispatcher.class) {
                instance = new DownloadProcessDispatcher();
            }
        }
        return instance;
    }

    public void registerDownloaderProcessConnectedListener(IDownloaderProcessConnectedListener iDownloaderProcessConnectedListener) {
        if (iDownloaderProcessConnectedListener == null) {
            return;
        }
        if (DownloadUtils.isDownloaderProcess()) {
            iDownloaderProcessConnectedListener.onConnected();
            return;
        }
        if (DownloadProxy.get(true).isServiceAlive()) {
            iDownloaderProcessConnectedListener.onConnected();
        }
        synchronized (this.processConnectedListeners) {
            if (!this.processConnectedListeners.contains(iDownloaderProcessConnectedListener)) {
                this.processConnectedListeners.add(iDownloaderProcessConnectedListener);
            }
        }
    }

    public void unRegisterDownloaderProcessConnectedListener(IDownloaderProcessConnectedListener iDownloaderProcessConnectedListener) {
        if (iDownloaderProcessConnectedListener == null) {
            return;
        }
        synchronized (this.processConnectedListeners) {
            if (this.processConnectedListeners.contains(iDownloaderProcessConnectedListener)) {
                this.processConnectedListeners.remove(iDownloaderProcessConnectedListener);
            }
        }
    }

    public void dispatchDownloaderProcessConnectedEvent() {
        synchronized (this.processConnectedListeners) {
            for (IDownloaderProcessConnectedListener iDownloaderProcessConnectedListener : this.processConnectedListeners) {
                if (iDownloaderProcessConnectedListener != null) {
                    iDownloaderProcessConnectedListener.onConnected();
                }
            }
        }
    }

    public void setDownloadWithIndependentProcessStatus(int i, boolean z) {
        setDownloadIndependentProcessStatus(i, z);
        if (DownloadComponentManager.supportMultiProc() && !DownloadUtils.isDownloaderProcess() && DownloadProxy.get(true).isServiceAlive()) {
            DownloadProxy.get(true).setDownloadWithIndependentProcessStatus(i, z);
        }
        if (DownloadComponentManager.isDownloadInMultiProcess() || DownloadUtils.isDownloaderProcess() || DownloadUtils.isMainProcess()) {
            return;
        }
        try {
            Intent intent = new Intent(DownloadComponentManager.getAppContext(), (Class<?>) DownloadHandleService.class);
            intent.setAction(DownloadConstants.ACTION_DOWNLOAD_PROCESS_NOTIFY);
            intent.putExtra(DownloadConstants.EXTRA_DOWNLOAD_ID, i);
            DownloadComponentManager.getAppContext().startService(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void setDownloadIndependentProcessStatus(int i, boolean z) {
        this.independentMap.put(i, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public int getDownloadWithIndependentProcessStatus(int i) {
        if (!DownloadComponentManager.supportMultiProc()) {
            return -1;
        }
        if (!DownloadUtils.isDownloaderProcess() && DownloadProxy.get(true).isServiceAlive()) {
            return DownloadProxy.get(true).getDownloadWithIndependentProcessStatus(i);
        }
        return getDownloadWithIndependentProcessStatusInner(i);
    }

    public synchronized int getDownloadWithIndependentProcessStatusInner(int i) {
        if (this.independentMap.get(i) == null) {
            return -1;
        }
        return this.independentMap.get(i).booleanValue() ? 1 : 0;
    }

    public IDownloadProxy getDownloadHandler(int i) {
        return DownloadProxy.get(getDownloadWithIndependentProcessStatus(i) == 1 && !DownloadUtils.isDownloaderProcess());
    }

    private IDownloadProxy getDownloadHandler(DownloadTask downloadTask) {
        DownloadInfo downloadInfo;
        List<DownloadChunk> downloadChunk;
        if (downloadTask == null || (downloadInfo = downloadTask.getDownloadInfo()) == null) {
            return null;
        }
        boolean zIsNeedIndependentProcess = downloadInfo.isNeedIndependentProcess();
        if (DownloadUtils.isDownloaderProcess() || !DownloadUtils.isMainProcess()) {
            zIsNeedIndependentProcess = true;
        }
        int downloadWithIndependentProcessStatus = getDownloadWithIndependentProcessStatus(downloadInfo.getId());
        if (downloadWithIndependentProcessStatus >= 0 && downloadWithIndependentProcessStatus != zIsNeedIndependentProcess) {
            try {
                if (downloadWithIndependentProcessStatus == 1) {
                    if (DownloadUtils.isMainProcess()) {
                        DownloadProxy.get(true).pause(downloadInfo.getId());
                        DownloadInfo downloadInfo2 = DownloadProxy.get(true).getDownloadInfo(downloadInfo.getId());
                        if (downloadInfo2 != null) {
                            DownloadProxy.get(false).syncDownloadInfo(downloadInfo2);
                        }
                        if (downloadInfo2.getChunkCount() > 1 && (downloadChunk = DownloadProxy.get(true).getDownloadChunk(downloadInfo.getId())) != null) {
                            DownloadProxy.get(false).syncDownloadChunks(downloadInfo.getId(), DownloadUtils.parseHostChunkList(downloadChunk));
                        }
                    }
                } else if (DownloadUtils.isMainProcess()) {
                    DownloadProxy.get(false).pause(downloadInfo.getId());
                    List<DownloadChunk> downloadChunk2 = DownloadProxy.get(false).getDownloadChunk(downloadInfo.getId());
                    if (downloadChunk2 != null) {
                        DownloadProxy.get(true).syncDownloadChunks(downloadInfo.getId(), DownloadUtils.parseHostChunkList(downloadChunk2));
                    }
                } else {
                    downloadTask.setNeedDelayForCacheSync(true);
                    DownloadProxy.get(true).dispatchProcessCallback(1, downloadInfo.getId());
                }
            } catch (Throwable unused) {
            }
        }
        setDownloadWithIndependentProcessStatus(downloadInfo.getId(), zIsNeedIndependentProcess);
        return DownloadProxy.get(zIsNeedIndependentProcess);
    }

    public void pause(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return;
        }
        downloadHandler.pause(i);
    }

    public void cancel(int i, boolean z) {
        if (DownloadUtils.isMainProcess()) {
            if (DownloadExpSwitchCode.isSwitchEnable(8388608)) {
                IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
                if (iDownloadProxy != null) {
                    iDownloadProxy.cancel(i, z);
                }
                IDownloadProxy iDownloadProxy2 = DownloadProxy.get(false);
                if (iDownloadProxy2 != null) {
                    iDownloadProxy2.cancel(i, z);
                    return;
                }
                return;
            }
            IDownloadProxy iDownloadProxy3 = DownloadProxy.get(false);
            if (iDownloadProxy3 != null) {
                iDownloadProxy3.cancel(i, z);
            }
            IDownloadProxy iDownloadProxy4 = DownloadProxy.get(true);
            if (iDownloadProxy4 != null) {
                iDownloadProxy4.cancel(i, z);
                return;
            }
            return;
        }
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler != null) {
            downloadHandler.cancel(i, z);
        }
        DownloadProxy.get(true).dispatchProcessCallback(2, i);
    }

    public boolean canResume(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return false;
        }
        return downloadHandler.canResume(i);
    }

    public void resume(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return;
        }
        downloadHandler.resume(i);
    }

    public void restart(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return;
        }
        downloadHandler.restart(i);
    }

    public void pauseAll() {
        IDownloadProxy iDownloadProxy = DownloadProxy.get(false);
        if (iDownloadProxy != null) {
            iDownloadProxy.pauseAll();
        }
        IDownloadProxy iDownloadProxy2 = DownloadProxy.get(true);
        if (iDownloadProxy2 != null) {
            iDownloadProxy2.pauseAll();
        }
    }

    public long getCurBytes(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return 0L;
        }
        return downloadHandler.getCurBytes(i);
    }

    public int getStatus(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return 0;
        }
        return downloadHandler.getStatus(i);
    }

    public boolean isDownloading(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return false;
        }
        return downloadHandler.isDownloading(i);
    }

    public DownloadInfo getDownloadInfo(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return null;
        }
        return downloadHandler.getDownloadInfo(i);
    }

    public int getDownloadId(String str, String str2) {
        return DownloadComponentManager.getDownloadId(str, str2);
    }

    public DownloadInfo getDownloadInfo(String str, String str2) {
        int downloadId = getDownloadId(str, str2);
        IDownloadProxy downloadHandler = getDownloadHandler(downloadId);
        if (downloadHandler == null) {
            return null;
        }
        return downloadHandler.getDownloadInfo(downloadId);
    }

    public List<DownloadInfo> getDownloadInfoList(String str) {
        List<DownloadInfo> downloadInfoList = DownloadProxy.get(false).getDownloadInfoList(str);
        List<DownloadInfo> downloadInfoList2 = DownloadProxy.get(true).getDownloadInfoList(str);
        if (downloadInfoList == null && downloadInfoList2 == null) {
            return null;
        }
        if (downloadInfoList == null || downloadInfoList2 == null) {
            return downloadInfoList != null ? downloadInfoList : downloadInfoList2;
        }
        ArrayList arrayList = new ArrayList(downloadInfoList);
        arrayList.addAll(downloadInfoList2);
        return arrayList;
    }

    public IDownloadNotificationEventListener getDownloadNotificationEventListener(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return null;
        }
        return downloadHandler.getDownloadNotificationEventListener(i);
    }

    public INotificationClickCallback getNotificationClickCallback(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return null;
        }
        return downloadHandler.getNotificationClickCallback(i);
    }

    public void setDownloadNotificationEventListener(int i, IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return;
        }
        downloadHandler.setDownloadNotificationEventListener(i, iDownloadNotificationEventListener);
    }

    public List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) {
        SparseArray<DownloadInfo> sparseArray = new SparseArray<>();
        IDownloadProxy iDownloadProxy = DownloadProxy.get(false);
        List<DownloadInfo> failedDownloadInfosWithMimeType = iDownloadProxy != null ? iDownloadProxy.getFailedDownloadInfosWithMimeType(str) : null;
        IDownloadProxy iDownloadProxy2 = DownloadProxy.get(true);
        return handleDownloadInfos(failedDownloadInfosWithMimeType, iDownloadProxy2 != null ? iDownloadProxy2.getFailedDownloadInfosWithMimeType(str) : null, sparseArray);
    }

    private List<DownloadInfo> handleDownloadInfos(List<DownloadInfo> list, List<DownloadInfo> list2, SparseArray<DownloadInfo> sparseArray) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (DownloadInfo downloadInfo : list) {
                if (downloadInfo != null && sparseArray.get(downloadInfo.getId()) == null) {
                    sparseArray.put(downloadInfo.getId(), downloadInfo);
                }
            }
        }
        if (list2 != null) {
            for (DownloadInfo downloadInfo2 : list2) {
                if (downloadInfo2 != null && sparseArray.get(downloadInfo2.getId()) == null) {
                    sparseArray.put(downloadInfo2.getId(), downloadInfo2);
                }
            }
        }
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(sparseArray.get(sparseArray.keyAt(i)));
        }
        return arrayList;
    }

    public List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) {
        SparseArray<DownloadInfo> sparseArray = new SparseArray<>();
        IDownloadProxy iDownloadProxy = DownloadProxy.get(false);
        List<DownloadInfo> successedDownloadInfosWithMimeType = iDownloadProxy != null ? iDownloadProxy.getSuccessedDownloadInfosWithMimeType(str) : null;
        IDownloadProxy iDownloadProxy2 = DownloadProxy.get(true);
        return handleDownloadInfos(successedDownloadInfosWithMimeType, iDownloadProxy2 != null ? iDownloadProxy2.getSuccessedDownloadInfosWithMimeType(str) : null, sparseArray);
    }

    public void restartAllFailedDownloadTasks(List<String> list) {
        IDownloadProxy iDownloadProxy = DownloadProxy.get(false);
        if (iDownloadProxy != null) {
            iDownloadProxy.restartAllFailedDownloadTasks(list);
        }
        IDownloadProxy iDownloadProxy2 = DownloadProxy.get(true);
        if (iDownloadProxy2 != null) {
            iDownloadProxy2.restartAllFailedDownloadTasks(list);
        }
    }

    public void restartAllPauseReserveOnWifiDownloadTasks(List<String> list) {
        IDownloadProxy iDownloadProxy = DownloadProxy.get(false);
        if (iDownloadProxy != null) {
            iDownloadProxy.restartAllPauseReserveOnWifiDownloadTasks(list);
        }
        IDownloadProxy iDownloadProxy2 = DownloadProxy.get(true);
        if (iDownloadProxy2 != null) {
            iDownloadProxy2.restartAllPauseReserveOnWifiDownloadTasks(list);
        }
    }

    public void clearDownloadData(int i, boolean z) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return;
        }
        downloadHandler.clearDownloadData(i, z);
    }

    public void forceDownloadIngoreRecommendSize(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return;
        }
        downloadHandler.forceDownloadIngoreRecommendSize(i);
    }

    public void removeDownloadListener(int i, IDownloadListener iDownloadListener, ListenerType listenerType, boolean z) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return;
        }
        downloadHandler.removeDownloadListener(i, iDownloadListener == null ? 0 : iDownloadListener.hashCode(), iDownloadListener, listenerType, z);
    }

    public void addDownloadListener(int i, IDownloadListener iDownloadListener, ListenerType listenerType, boolean z) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return;
        }
        downloadHandler.addDownloadListener(i, iDownloadListener.hashCode(), iDownloadListener, listenerType, z);
    }

    public void addDownloadListener(int i, IDownloadListener iDownloadListener, ListenerType listenerType, boolean z, boolean z2) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return;
        }
        downloadHandler.addDownloadListener(i, iDownloadListener.hashCode(), iDownloadListener, listenerType, z, z2);
    }

    public boolean isDownloadSuccessAndFileNotExist(DownloadInfo downloadInfo) {
        IDownloadProxy downloadHandler;
        if (downloadInfo == null || (downloadHandler = getDownloadHandler(downloadInfo.getId())) == null) {
            return false;
        }
        return downloadHandler.isDownloadSuccessAndFileNotExist(downloadInfo);
    }

    public boolean isHttpServiceInit() {
        return DownloadComponentManager.isHttpServiceInit();
    }

    public List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) {
        SparseArray<DownloadInfo> sparseArray = new SparseArray<>();
        IDownloadProxy iDownloadProxy = DownloadProxy.get(false);
        List<DownloadInfo> unCompletedDownloadInfosWithMimeType = iDownloadProxy != null ? iDownloadProxy.getUnCompletedDownloadInfosWithMimeType(str) : null;
        IDownloadProxy iDownloadProxy2 = DownloadProxy.get(true);
        return handleDownloadInfos(unCompletedDownloadInfosWithMimeType, iDownloadProxy2 != null ? iDownloadProxy2.getUnCompletedDownloadInfosWithMimeType(str) : null, sparseArray);
    }

    public List<DownloadInfo> getDownloadingDownloadInfosWithMimeType(String str) {
        SparseArray<DownloadInfo> sparseArray = new SparseArray<>();
        IDownloadProxy iDownloadProxy = DownloadProxy.get(false);
        List<DownloadInfo> downloadingDownloadInfosWithMimeType = iDownloadProxy != null ? iDownloadProxy.getDownloadingDownloadInfosWithMimeType(str) : null;
        IDownloadProxy iDownloadProxy2 = DownloadProxy.get(true);
        return handleDownloadInfos(downloadingDownloadInfosWithMimeType, iDownloadProxy2 != null ? iDownloadProxy2.getDownloadingDownloadInfosWithMimeType(str) : null, sparseArray);
    }

    public List<DownloadInfo> getAllDownloadInfo() {
        SparseArray<DownloadInfo> sparseArray = new SparseArray<>();
        IDownloadProxy iDownloadProxy = DownloadProxy.get(false);
        List<DownloadInfo> allDownloadInfo = iDownloadProxy != null ? iDownloadProxy.getAllDownloadInfo() : null;
        IDownloadProxy iDownloadProxy2 = DownloadProxy.get(true);
        return handleDownloadInfos(allDownloadInfo, iDownloadProxy2 != null ? iDownloadProxy2.getAllDownloadInfo() : null, sparseArray);
    }

    public void tryDownload(final DownloadTask downloadTask) {
        final IDownloadProxy downloadHandler = getDownloadHandler(downloadTask);
        if (downloadHandler == null) {
            if (downloadTask != null) {
                DownloadMonitorHelper.monitorSendWithTaskMonitor(downloadTask.getMonitorDepend(), downloadTask.getDownloadInfo(), new BaseException(1003, "tryDownload but getDownloadHandler failed"), downloadTask.getDownloadInfo() != null ? downloadTask.getDownloadInfo().getStatus() : 0);
            }
        } else if (downloadTask.isNeedDelayForCacheSync()) {
            this.mainThreadHandler.postDelayed(new Runnable() { // from class: com.ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher.1
                @Override // java.lang.Runnable
                public void run() {
                    downloadHandler.tryDownload(downloadTask);
                }
            }, 500L);
        } else {
            downloadHandler.tryDownload(downloadTask);
        }
    }

    public boolean retryDelayStart(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return false;
        }
        return downloadHandler.retryDelayStart(i);
    }

    public void setLogLevel(int i) {
        IDownloadProxy iDownloadProxy = DownloadProxy.get(false);
        if (iDownloadProxy != null) {
            iDownloadProxy.setLogLevel(i);
        }
        IDownloadProxy iDownloadProxy2 = DownloadProxy.get(true);
        if (iDownloadProxy2 != null) {
            iDownloadProxy2.setLogLevel(i);
        }
    }

    public void recordTaskProcessIndependent(int i) {
        if (i == 0) {
            return;
        }
        setDownloadIndependentProcessStatus(i, Boolean.TRUE.booleanValue());
        IDownloadProxy iDownloadProxy = DownloadProxy.get(true);
        if (iDownloadProxy == null) {
            return;
        }
        iDownloadProxy.startService();
    }

    public void registerDownloadCacheSyncListener(IDownloadCacheSyncStatusListener iDownloadCacheSyncStatusListener) {
        DownloadComponentManager.registerDownloadCacheSyncListener(iDownloadCacheSyncStatusListener);
    }

    public void unRegisterDownloadCacheSyncListener(IDownloadCacheSyncStatusListener iDownloadCacheSyncStatusListener) {
        DownloadComponentManager.unRegisterDownloadCacheSyncListener(iDownloadCacheSyncStatusListener);
    }

    public boolean isDownloadCacheSyncSuccess() {
        IDownloadProxy iDownloadProxy = DownloadProxy.get(false);
        if (iDownloadProxy != null) {
            return iDownloadProxy.isDownloadCacheSyncSuccess();
        }
        return false;
    }

    public IDownloadFileUriProvider getDownloadFileUriProvider(int i) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return null;
        }
        return downloadHandler.getDownloadFileUriProvider(i);
    }

    public void setThrottleNetSpeed(int i, long j) {
        IDownloadProxy downloadHandler = getDownloadHandler(i);
        if (downloadHandler == null) {
            return;
        }
        downloadHandler.setThrottleNetSpeed(i, j);
    }
}
