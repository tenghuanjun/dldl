package com.ss.android.socialbase.downloader.utils;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import com.ss.android.socialbase.downloader.constants.ListenerType;
import com.ss.android.socialbase.downloader.depend.AbsDownloadMonitorDepend;
import com.ss.android.socialbase.downloader.depend.IChunkCntAidlCalculator;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlInterceptor;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlListener;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlCallback;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadExtListener;
import com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback;
import com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback;
import com.ss.android.socialbase.downloader.depend.IDownloadForbiddenHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadInterceptor;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.depend.IDownloadMonitorDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback;
import com.ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.ss.android.socialbase.downloader.depend.IRetryDelayTimeAidlCalculator;
import com.ss.android.socialbase.downloader.depend.ProcessAidlCallback;
import com.ss.android.socialbase.downloader.depend.ProcessCallback;
import com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator;
import com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadAidlTask;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class IPCUtils {
    private static Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    public static DownloadAidlTask convertDownloadTaskToAidl(final DownloadTask downloadTask) {
        if (downloadTask == null) {
            return null;
        }
        return new DownloadAidlTask.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.1
            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public DownloadInfo getDownloadInfo() throws RemoteException {
                return downloadTask.getDownloadInfo();
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IChunkCntAidlCalculator getChunkStrategy() throws RemoteException {
                return IPCUtils.convertCntCalculatorToAidl(downloadTask.getChunkStrategy());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlListener getSingleDownloadListener(int i) throws RemoteException {
                return IPCUtils.convertListenerToAidl(downloadTask.getSingleDownloadListener(DownloadUtils.convertListenerType(i)), i != ListenerType.SUB.ordinal());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public int getDownloadListenerSize(int i) throws RemoteException {
                return downloadTask.getDownloadListenerSize(DownloadUtils.convertListenerType(i));
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlListener getDownloadListenerByIndex(int i, int i2) throws RemoteException {
                return IPCUtils.convertListenerToAidl(downloadTask.getDownloadListenerByIndex(DownloadUtils.convertListenerType(i), i2), i != ListenerType.SUB.ordinal());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadNotificationEventAidlListener getDownloadNotificationEventListener() throws RemoteException {
                return IPCUtils.convertDownloadNotificationEventListenerToAidl(downloadTask.getNotificationEventListener());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public INotificationClickAidlCallback getNotificationClickCallback() throws RemoteException {
                return IPCUtils.convertNotificationClickCallbackToAidl(downloadTask.getNotificationClickCallback());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlInterceptor getInterceptor() throws RemoteException {
                return IPCUtils.convertInterceptorToAidl(downloadTask.getInterceptor());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlDepend getDepend() throws RemoteException {
                return IPCUtils.convertDependToAidl(downloadTask.getDepend());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlMonitorDepend getMonitorDepend() throws RemoteException {
                return IPCUtils.convertMonitorDependToAidl(downloadTask.getMonitorDepend());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadForbiddenAidlHandler getForbiddenHandler() throws RemoteException {
                return IPCUtils.convertForbiddenHandlerToAidl(downloadTask.getForbiddenHandler());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IRetryDelayTimeAidlCalculator getRetryDelayTimeCalculator() throws RemoteException {
                return IPCUtils.convertRetryDelayTimeCalculatorToAidl(downloadTask.getRetryDelayTimeCalculator());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadDiskSpaceAidlHandler getDiskSpaceHandler() throws RemoteException {
                return IPCUtils.convertDiskSpaceHandlerToAidl(downloadTask.getDiskSpaceHandler());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlFileProvider getFileProvider() throws RemoteException {
                return IPCUtils.convertFileProviderToAidl(downloadTask.getFileUriProvider());
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public int getDownloadCompleteHandlerSize() throws RemoteException {
                return downloadTask.getDownloadCompleteHandlers().size();
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadCompleteAidlHandler getDownloadCompleteAidlHandlerByIndex(int i) throws RemoteException {
                return IPCUtils.convertDownloadCompleteHandlerToAidl(downloadTask.getDownloadCompleteHandlerByIndex(i));
            }
        };
    }

    public static IDownloadAidlListener convertListenerToAidl(final IDownloadListener iDownloadListener, final boolean z) {
        if (iDownloadListener == null) {
            return null;
        }
        return new IDownloadAidlListener.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public int getOriginHashCode() throws RemoteException {
                return iDownloadListener.hashCode();
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onPrepare(final DownloadInfo downloadInfo) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onPrepare(downloadInfo);
                        }
                    });
                } else {
                    iDownloadListener.onPrepare(downloadInfo);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onStart(final DownloadInfo downloadInfo) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onStart(downloadInfo);
                        }
                    });
                } else {
                    iDownloadListener.onStart(downloadInfo);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onProgress(final DownloadInfo downloadInfo) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.3
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onProgress(downloadInfo);
                        }
                    });
                } else {
                    iDownloadListener.onProgress(downloadInfo);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onPause(final DownloadInfo downloadInfo) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.4
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onPause(downloadInfo);
                        }
                    });
                } else {
                    iDownloadListener.onPause(downloadInfo);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onSuccessed(final DownloadInfo downloadInfo) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.5
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onSuccessed(downloadInfo);
                        }
                    });
                } else {
                    iDownloadListener.onSuccessed(downloadInfo);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onFailed(final DownloadInfo downloadInfo, final BaseException baseException) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.6
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onFailed(downloadInfo, baseException);
                        }
                    });
                } else {
                    iDownloadListener.onFailed(downloadInfo, baseException);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onCanceled(final DownloadInfo downloadInfo) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.7
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onCanceled(downloadInfo);
                        }
                    });
                } else {
                    iDownloadListener.onCanceled(downloadInfo);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onFirstStart(final DownloadInfo downloadInfo) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.8
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onFirstStart(downloadInfo);
                        }
                    });
                } else {
                    iDownloadListener.onFirstStart(downloadInfo);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onFirstSuccess(final DownloadInfo downloadInfo) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.9
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onFirstSuccess(downloadInfo);
                        }
                    });
                } else {
                    iDownloadListener.onFirstSuccess(downloadInfo);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onRetry(final DownloadInfo downloadInfo, final BaseException baseException) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.10
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onRetry(downloadInfo, baseException);
                        }
                    });
                } else {
                    iDownloadListener.onRetry(downloadInfo, baseException);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onRetryDelay(final DownloadInfo downloadInfo, final BaseException baseException) throws RemoteException {
                if (z) {
                    IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.11
                        @Override // java.lang.Runnable
                        public void run() {
                            iDownloadListener.onRetryDelay(downloadInfo, baseException);
                        }
                    });
                } else {
                    iDownloadListener.onRetryDelay(downloadInfo, baseException);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlListener
            public void onWaitingDownloadCompleteHandler(final DownloadInfo downloadInfo) throws RemoteException {
                IDownloadListener iDownloadListener2 = iDownloadListener;
                if (iDownloadListener2 instanceof IDownloadExtListener) {
                    if (z) {
                        IPCUtils.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.2.12
                            @Override // java.lang.Runnable
                            public void run() {
                                ((IDownloadExtListener) iDownloadListener).onWaitingDownloadCompleteHandler(downloadInfo);
                            }
                        });
                    } else {
                        ((IDownloadExtListener) iDownloadListener2).onWaitingDownloadCompleteHandler(downloadInfo);
                    }
                }
            }
        };
    }

    public static IDownloadNotificationEventAidlListener convertDownloadNotificationEventListenerToAidl(final IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        if (iDownloadNotificationEventListener == null) {
            return null;
        }
        return new IDownloadNotificationEventAidlListener.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.3
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener
            public void onNotificationEvent(int i, DownloadInfo downloadInfo, String str, String str2) throws RemoteException {
                iDownloadNotificationEventListener.onNotificationEvent(i, downloadInfo, str, str2);
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener
            public boolean interceptAfterNotificationSuccess(boolean z) throws RemoteException {
                return iDownloadNotificationEventListener.interceptAfterNotificationSuccess(z);
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener
            public String getNotifyProcessName() throws RemoteException {
                return iDownloadNotificationEventListener.getNotifyProcessName();
            }
        };
    }

    public static INotificationClickAidlCallback convertNotificationClickCallbackToAidl(final INotificationClickCallback iNotificationClickCallback) {
        if (iNotificationClickCallback == null) {
            return null;
        }
        return new INotificationClickAidlCallback.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.4
            @Override // com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback
            public boolean onClickWhenUnSuccess(DownloadInfo downloadInfo) throws RemoteException {
                return iNotificationClickCallback.onClickWhenUnSuccess(downloadInfo);
            }

            @Override // com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback
            public boolean onClickWhenSuccess(DownloadInfo downloadInfo) throws RemoteException {
                return iNotificationClickCallback.onClickWhenSuccess(downloadInfo);
            }

            @Override // com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback
            public boolean onClickWhenInstalled(DownloadInfo downloadInfo) throws RemoteException {
                return iNotificationClickCallback.onClickWhenInstalled(downloadInfo);
            }
        };
    }

    public static IRetryDelayTimeAidlCalculator convertRetryDelayTimeCalculatorToAidl(final IRetryDelayTimeCalculator iRetryDelayTimeCalculator) {
        if (iRetryDelayTimeCalculator == null) {
            return null;
        }
        return new IRetryDelayTimeAidlCalculator.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.5
            @Override // com.ss.android.socialbase.downloader.depend.IRetryDelayTimeAidlCalculator
            public long calculateRetryDelayTime(int i, int i2) throws RemoteException {
                return iRetryDelayTimeCalculator.calculateRetryDelayTime(i, i2);
            }
        };
    }

    public static IDownloadForbiddenAidlHandler convertForbiddenHandlerToAidl(final IDownloadForbiddenHandler iDownloadForbiddenHandler) {
        if (iDownloadForbiddenHandler == null) {
            return null;
        }
        return new IDownloadForbiddenAidlHandler.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.6
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler
            public boolean onForbidden(IDownloadForbiddenAidlCallback iDownloadForbiddenAidlCallback) throws RemoteException {
                return iDownloadForbiddenHandler.onForbidden(IPCUtils.convertForbiddenCallbackFromAidl(iDownloadForbiddenAidlCallback));
            }
        };
    }

    public static IDownloadAidlFileProvider convertFileProviderToAidl(final IDownloadFileUriProvider iDownloadFileUriProvider) {
        if (iDownloadFileUriProvider == null) {
            return null;
        }
        return new IDownloadAidlFileProvider.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.7
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider
            public Uri getUriForFile(String str, String str2) throws RemoteException {
                return iDownloadFileUriProvider.getUriForFile(str, str2);
            }
        };
    }

    public static IDownloadForbiddenCallback convertForbiddenCallbackFromAidl(final IDownloadForbiddenAidlCallback iDownloadForbiddenAidlCallback) {
        if (iDownloadForbiddenAidlCallback == null) {
            return null;
        }
        return new IDownloadForbiddenCallback() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.8
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback
            public void onCallback(List<String> list) {
                try {
                    iDownloadForbiddenAidlCallback.onCallback(list);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback
            public boolean hasCallback() {
                try {
                    return iDownloadForbiddenAidlCallback.hasCallback();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        };
    }

    public static IDownloadDiskSpaceAidlHandler convertDiskSpaceHandlerToAidl(final IDownloadDiskSpaceHandler iDownloadDiskSpaceHandler) {
        if (iDownloadDiskSpaceHandler == null) {
            return null;
        }
        return new IDownloadDiskSpaceAidlHandler.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.9
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlHandler
            public boolean cleanUpDisk(long j, long j2, IDownloadDiskSpaceAidlCallback iDownloadDiskSpaceAidlCallback) throws RemoteException {
                return iDownloadDiskSpaceHandler.cleanUpDisk(j, j2, IPCUtils.convertDiskSpaceCallbackFromAidl(iDownloadDiskSpaceAidlCallback));
            }
        };
    }

    public static IDownloadDiskSpaceCallback convertDiskSpaceCallbackFromAidl(final IDownloadDiskSpaceAidlCallback iDownloadDiskSpaceAidlCallback) {
        if (iDownloadDiskSpaceAidlCallback == null) {
            return null;
        }
        return new IDownloadDiskSpaceCallback() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.10
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback
            public void onDiskCleaned() {
                try {
                    iDownloadDiskSpaceAidlCallback.onDiskCleaned();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static IDownloadAidlDepend convertDependToAidl(final IDownloadDepend iDownloadDepend) {
        if (iDownloadDepend == null) {
            return null;
        }
        return new IDownloadAidlDepend.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.11
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlDepend
            public void monitorLogSend(DownloadInfo downloadInfo, BaseException baseException, int i) throws RemoteException {
                iDownloadDepend.monitorLogSend(downloadInfo, baseException, i);
            }
        };
    }

    public static IDownloadAidlMonitorDepend convertMonitorDependToAidl(final IDownloadMonitorDepend iDownloadMonitorDepend) {
        if (iDownloadMonitorDepend == null) {
            return null;
        }
        return new IDownloadAidlMonitorDepend.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.12
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend
            public void monitorLogSend(String str) throws RemoteException {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    iDownloadMonitorDepend.monitorLogSend(new JSONObject(str));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend
            public String getEventPage() throws RemoteException {
                return iDownloadMonitorDepend.getEventPage();
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend
            public int[] getAdditionalMonitorStatus() throws RemoteException {
                IDownloadMonitorDepend iDownloadMonitorDepend2 = iDownloadMonitorDepend;
                if (iDownloadMonitorDepend2 instanceof AbsDownloadMonitorDepend) {
                    return ((AbsDownloadMonitorDepend) iDownloadMonitorDepend2).getAdditionalMonitorStatus();
                }
                return null;
            }
        };
    }

    public static IChunkCntAidlCalculator convertCntCalculatorToAidl(final IChunkCntCalculator iChunkCntCalculator) {
        if (iChunkCntCalculator == null) {
            return null;
        }
        return new IChunkCntAidlCalculator.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.13
            @Override // com.ss.android.socialbase.downloader.depend.IChunkCntAidlCalculator
            public int calculateChunkCount(long j) throws RemoteException {
                return iChunkCntCalculator.calculateChunkCount(j);
            }
        };
    }

    public static IDownloadAidlInterceptor convertInterceptorToAidl(final IDownloadInterceptor iDownloadInterceptor) {
        if (iDownloadInterceptor == null) {
            return null;
        }
        return new IDownloadAidlInterceptor.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.14
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadAidlInterceptor
            public boolean intercept() throws RemoteException {
                return iDownloadInterceptor.intercepte();
            }
        };
    }

    public static DownloadTask convertDownloadTaskFromAidl(DownloadAidlTask downloadAidlTask) {
        if (downloadAidlTask == null) {
            return null;
        }
        try {
            DownloadTask downloadTask = new DownloadTask(downloadAidlTask.getDownloadInfo());
            downloadTask.chunkStategy(convertCntCalculatorFromAidl(downloadAidlTask.getChunkStrategy())).notificationEventListener(convertDownloadNotificationEventListenerFromAidl(downloadAidlTask.getDownloadNotificationEventListener())).interceptor(convertInterceptorFromAidl(downloadAidlTask.getInterceptor())).depend(convertDownloadDependFromAidl(downloadAidlTask.getDepend())).monitorDepend(convertDownloadMonitorDependFromAidl(downloadAidlTask.getMonitorDepend())).forbiddenHandler(convertForbiddenHandlerFromAidl(downloadAidlTask.getForbiddenHandler())).diskSpaceHandler(convertDiskSpaceHandlerFromAidl(downloadAidlTask.getDiskSpaceHandler())).fileUriProvider(convertFileProviderFromAidl(downloadAidlTask.getFileProvider())).notificationClickCallback(convertNotificationClickCallbackFromAidl(downloadAidlTask.getNotificationClickCallback())).retryDelayTimeCalculator(convertRetryDelayTimeCalculatorFromAidl(downloadAidlTask.getRetryDelayTimeCalculator()));
            IDownloadAidlListener singleDownloadListener = downloadAidlTask.getSingleDownloadListener(ListenerType.MAIN.ordinal());
            if (singleDownloadListener != null) {
                downloadTask.mainThreadListenerWithHashCode(singleDownloadListener.hashCode(), convertListenerFromAidl(singleDownloadListener));
            }
            IDownloadAidlListener singleDownloadListener2 = downloadAidlTask.getSingleDownloadListener(ListenerType.SUB.ordinal());
            if (singleDownloadListener2 != null) {
                downloadTask.subThreadListenerWithHashCode(singleDownloadListener2.hashCode(), convertListenerFromAidl(singleDownloadListener2));
            }
            IDownloadAidlListener singleDownloadListener3 = downloadAidlTask.getSingleDownloadListener(ListenerType.NOTIFICATION.ordinal());
            if (singleDownloadListener3 != null) {
                downloadTask.notificationListenerWithHashCode(singleDownloadListener3.hashCode(), convertListenerFromAidl(singleDownloadListener3));
            }
            setDownloadListeners(downloadTask, downloadAidlTask, ListenerType.MAIN);
            setDownloadListeners(downloadTask, downloadAidlTask, ListenerType.SUB);
            setDownloadListeners(downloadTask, downloadAidlTask, ListenerType.NOTIFICATION);
            setCompletedHandlers(downloadTask, downloadAidlTask);
            return downloadTask;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void setDownloadListeners(DownloadTask downloadTask, DownloadAidlTask downloadAidlTask, ListenerType listenerType) throws RemoteException {
        SparseArray<IDownloadListener> sparseArray = new SparseArray<>();
        for (int i = 0; i < downloadAidlTask.getDownloadListenerSize(listenerType.ordinal()); i++) {
            IDownloadAidlListener downloadListenerByIndex = downloadAidlTask.getDownloadListenerByIndex(listenerType.ordinal(), i);
            if (downloadListenerByIndex != null) {
                sparseArray.put(downloadListenerByIndex.getOriginHashCode(), convertListenerFromAidl(downloadListenerByIndex));
            }
        }
        downloadTask.setDownloadListeners(sparseArray, listenerType);
    }

    private static void setCompletedHandlers(DownloadTask downloadTask, DownloadAidlTask downloadAidlTask) throws RemoteException {
        for (int i = 0; i < downloadAidlTask.getDownloadCompleteHandlerSize(); i++) {
            IDownloadCompleteAidlHandler downloadCompleteAidlHandlerByIndex = downloadAidlTask.getDownloadCompleteAidlHandlerByIndex(i);
            if (downloadCompleteAidlHandlerByIndex != null) {
                downloadTask.addDownloadCompleteHandler(convertDownloadCompleteHandlerFromAidl(downloadCompleteAidlHandlerByIndex));
            }
        }
    }

    public static IDownloadNotificationEventListener convertDownloadNotificationEventListenerFromAidl(final IDownloadNotificationEventAidlListener iDownloadNotificationEventAidlListener) {
        if (iDownloadNotificationEventAidlListener == null) {
            return null;
        }
        return new IDownloadNotificationEventListener() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.15
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public void onNotificationEvent(int i, DownloadInfo downloadInfo, String str, String str2) {
                try {
                    iDownloadNotificationEventAidlListener.onNotificationEvent(i, downloadInfo, str, str2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public boolean interceptAfterNotificationSuccess(boolean z) {
                try {
                    return iDownloadNotificationEventAidlListener.interceptAfterNotificationSuccess(z);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public String getNotifyProcessName() {
                try {
                    return iDownloadNotificationEventAidlListener.getNotifyProcessName();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public static IDownloadCompleteHandler convertDownloadCompleteHandlerFromAidl(final IDownloadCompleteAidlHandler iDownloadCompleteAidlHandler) {
        if (iDownloadCompleteAidlHandler == null) {
            return null;
        }
        return new IDownloadCompleteHandler() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.16
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
            public void handle(DownloadInfo downloadInfo) throws BaseException {
                try {
                    iDownloadCompleteAidlHandler.handle(downloadInfo);
                } catch (RemoteException e) {
                    throw new BaseException(1008, e);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
            public boolean needHandle(DownloadInfo downloadInfo) {
                try {
                    return iDownloadCompleteAidlHandler.needHandle(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        };
    }

    public static IDownloadCompleteAidlHandler convertDownloadCompleteHandlerToAidl(final IDownloadCompleteHandler iDownloadCompleteHandler) {
        if (iDownloadCompleteHandler == null) {
            return null;
        }
        return new IDownloadCompleteAidlHandler.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.17
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler
            public void handle(DownloadInfo downloadInfo) throws RemoteException {
                try {
                    iDownloadCompleteHandler.handle(downloadInfo);
                } catch (BaseException e) {
                    throw new IllegalArgumentException(e);
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler
            public boolean needHandle(DownloadInfo downloadInfo) throws RemoteException {
                return iDownloadCompleteHandler.needHandle(downloadInfo);
            }
        };
    }

    public static INotificationClickCallback convertNotificationClickCallbackFromAidl(final INotificationClickAidlCallback iNotificationClickAidlCallback) {
        if (iNotificationClickAidlCallback == null) {
            return null;
        }
        return new INotificationClickCallback() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.18
            @Override // com.ss.android.socialbase.downloader.depend.INotificationClickCallback
            public boolean onClickWhenUnSuccess(DownloadInfo downloadInfo) {
                try {
                    return iNotificationClickAidlCallback.onClickWhenUnSuccess(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.INotificationClickCallback
            public boolean onClickWhenSuccess(DownloadInfo downloadInfo) {
                try {
                    return iNotificationClickAidlCallback.onClickWhenSuccess(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.INotificationClickCallback
            public boolean onClickWhenInstalled(DownloadInfo downloadInfo) {
                try {
                    return iNotificationClickAidlCallback.onClickWhenInstalled(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        };
    }

    public static IChunkCntCalculator convertCntCalculatorFromAidl(final IChunkCntAidlCalculator iChunkCntAidlCalculator) {
        if (iChunkCntAidlCalculator == null) {
            return null;
        }
        return new IChunkCntCalculator() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.19
            @Override // com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator
            public int calculateChunkCount(long j) {
                try {
                    return iChunkCntAidlCalculator.calculateChunkCount(j);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        };
    }

    public static IDownloadDepend convertDownloadDependFromAidl(final IDownloadAidlDepend iDownloadAidlDepend) {
        if (iDownloadAidlDepend == null) {
            return null;
        }
        return new IDownloadDepend() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.20
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadDepend
            public void monitorLogSend(DownloadInfo downloadInfo, BaseException baseException, int i) {
                if (downloadInfo == null) {
                    return;
                }
                try {
                    iDownloadAidlDepend.monitorLogSend(downloadInfo, baseException, i);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static IDownloadMonitorDepend convertDownloadMonitorDependFromAidl(final IDownloadAidlMonitorDepend iDownloadAidlMonitorDepend) {
        if (iDownloadAidlMonitorDepend == null) {
            return null;
        }
        return new AbsDownloadMonitorDepend() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.21
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadMonitorDepend
            public void monitorLogSend(JSONObject jSONObject) {
                if (jSONObject == null) {
                    return;
                }
                try {
                    iDownloadAidlMonitorDepend.monitorLogSend(jSONObject.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadMonitorDepend
            public String getEventPage() {
                try {
                    return iDownloadAidlMonitorDepend.getEventPage();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return "";
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadMonitorDepend
            public int[] getAdditionalMonitorStatus() {
                try {
                    return iDownloadAidlMonitorDepend.getAdditionalMonitorStatus();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public static IDownloadForbiddenHandler convertForbiddenHandlerFromAidl(final IDownloadForbiddenAidlHandler iDownloadForbiddenAidlHandler) {
        if (iDownloadForbiddenAidlHandler == null) {
            return null;
        }
        return new IDownloadForbiddenHandler() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.22
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenHandler
            public boolean onForbidden(IDownloadForbiddenCallback iDownloadForbiddenCallback) {
                try {
                    return iDownloadForbiddenAidlHandler.onForbidden(IPCUtils.convertForbiddenCallbackToAidl(iDownloadForbiddenCallback));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        };
    }

    public static IDownloadForbiddenAidlCallback convertForbiddenCallbackToAidl(final IDownloadForbiddenCallback iDownloadForbiddenCallback) {
        if (iDownloadForbiddenCallback == null) {
            return null;
        }
        return new IDownloadForbiddenAidlCallback.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.23
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback
            public void onCallback(List<String> list) {
                iDownloadForbiddenCallback.onCallback(list);
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback
            public boolean hasCallback() {
                return iDownloadForbiddenCallback.hasCallback();
            }
        };
    }

    public static ProcessAidlCallback convertProcessCallbackToAidl(final ProcessCallback processCallback) {
        if (processCallback == null) {
            return null;
        }
        return new ProcessAidlCallback.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.24
            @Override // com.ss.android.socialbase.downloader.depend.ProcessAidlCallback
            public void callback(int i, int i2) {
                processCallback.callback(i, i2);
            }
        };
    }

    public static ProcessCallback convertProcessAidlCallbackFromAidl(final ProcessAidlCallback processAidlCallback) {
        if (processAidlCallback == null) {
            return null;
        }
        return new ProcessCallback() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.25
            @Override // com.ss.android.socialbase.downloader.depend.ProcessCallback
            public void callback(int i, int i2) {
                try {
                    processAidlCallback.callback(i, i2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static IDownloadDiskSpaceHandler convertDiskSpaceHandlerFromAidl(final IDownloadDiskSpaceAidlHandler iDownloadDiskSpaceAidlHandler) {
        if (iDownloadDiskSpaceAidlHandler == null) {
            return null;
        }
        return new IDownloadDiskSpaceHandler() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.26
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler
            public boolean cleanUpDisk(long j, long j2, IDownloadDiskSpaceCallback iDownloadDiskSpaceCallback) {
                try {
                    return iDownloadDiskSpaceAidlHandler.cleanUpDisk(j, j2, IPCUtils.convertDiskSpaceCallbackToAidl(iDownloadDiskSpaceCallback));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        };
    }

    public static IDownloadDiskSpaceAidlCallback convertDiskSpaceCallbackToAidl(final IDownloadDiskSpaceCallback iDownloadDiskSpaceCallback) {
        if (iDownloadDiskSpaceCallback == null) {
            return null;
        }
        return new IDownloadDiskSpaceAidlCallback.Stub() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.27
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlCallback
            public void onDiskCleaned() throws RemoteException {
                iDownloadDiskSpaceCallback.onDiskCleaned();
            }
        };
    }

    public static IRetryDelayTimeCalculator convertRetryDelayTimeCalculatorFromAidl(final IRetryDelayTimeAidlCalculator iRetryDelayTimeAidlCalculator) {
        if (iRetryDelayTimeAidlCalculator == null) {
            return null;
        }
        return new IRetryDelayTimeCalculator() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.28
            @Override // com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator
            public long calculateRetryDelayTime(int i, int i2) {
                try {
                    return iRetryDelayTimeAidlCalculator.calculateRetryDelayTime(i, i2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return 0L;
                }
            }
        };
    }

    public static IDownloadInterceptor convertInterceptorFromAidl(final IDownloadAidlInterceptor iDownloadAidlInterceptor) {
        if (iDownloadAidlInterceptor == null) {
            return null;
        }
        return new IDownloadInterceptor() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.29
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadInterceptor
            public boolean intercepte() {
                try {
                    return iDownloadAidlInterceptor.intercept();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        };
    }

    public static IDownloadFileUriProvider convertFileProviderFromAidl(final IDownloadAidlFileProvider iDownloadAidlFileProvider) {
        if (iDownloadAidlFileProvider == null) {
            return null;
        }
        return new IDownloadFileUriProvider() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.30
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider
            public Uri getUriForFile(String str, String str2) {
                try {
                    return iDownloadAidlFileProvider.getUriForFile(str, str2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public static IDownloadListener convertListenerFromAidl(final IDownloadAidlListener iDownloadAidlListener) {
        if (iDownloadAidlListener == null) {
            return null;
        }
        return new IDownloadExtListener() { // from class: com.ss.android.socialbase.downloader.utils.IPCUtils.31
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadExtListener
            public void onWaitingDownloadCompleteHandler(DownloadInfo downloadInfo) {
                try {
                    iDownloadAidlListener.onWaitingDownloadCompleteHandler(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onPrepare(DownloadInfo downloadInfo) {
                try {
                    iDownloadAidlListener.onPrepare(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onStart(DownloadInfo downloadInfo) {
                try {
                    iDownloadAidlListener.onStart(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onProgress(DownloadInfo downloadInfo) {
                try {
                    iDownloadAidlListener.onProgress(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onPause(DownloadInfo downloadInfo) {
                try {
                    iDownloadAidlListener.onPause(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onSuccessed(DownloadInfo downloadInfo) {
                try {
                    iDownloadAidlListener.onSuccessed(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onFailed(DownloadInfo downloadInfo, BaseException baseException) {
                try {
                    iDownloadAidlListener.onFailed(downloadInfo, baseException);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onCanceled(DownloadInfo downloadInfo) {
                try {
                    iDownloadAidlListener.onCanceled(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onFirstStart(DownloadInfo downloadInfo) {
                try {
                    iDownloadAidlListener.onFirstStart(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onFirstSuccess(DownloadInfo downloadInfo) {
                try {
                    iDownloadAidlListener.onFirstSuccess(downloadInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onRetry(DownloadInfo downloadInfo, BaseException baseException) {
                try {
                    iDownloadAidlListener.onRetry(downloadInfo, baseException);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadListener
            public void onRetryDelay(DownloadInfo downloadInfo, BaseException baseException) {
                try {
                    iDownloadAidlListener.onRetryDelay(downloadInfo, baseException);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
