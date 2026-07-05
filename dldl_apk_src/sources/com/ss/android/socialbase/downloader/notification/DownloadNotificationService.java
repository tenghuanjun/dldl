package com.ss.android.socialbase.downloader.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.SparseArray;
import com.igexin.push.core.b;
import com.igexin.sdk.PushConsts;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.NotificationConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.IDownloadProxy;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.thread.ThreadWithHandler;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadNotificationService extends Service {
    private static boolean sBugfixNotifyTooFast;
    private static volatile long sLastImportantNotifyTimestamp;
    private static volatile long sLastNotifyTimestamp;
    private ThreadWithHandler mNotifyThreadHandler;
    private final SparseArray<Notification> pendingImportantNotify = new SparseArray<>(2);
    private static final String TAG = DownloadNotificationService.class.getSimpleName();
    private static int sForegroundId = -1;
    private static int sIndependentProcessForegroundId = -1;
    private static boolean sAllowStartForeground = true;
    private static boolean sBugFixNonOngoing = false;
    private static final long NOTIFY_TIME_WINDOW = 900;
    private static long sNotifyTimeWindow = NOTIFY_TIME_WINDOW;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        createNotifyHandlerThread();
        DownloadComponentManager.setAppContext(this);
        DownloadSetting downloadSettingObtainGlobal = DownloadSetting.obtainGlobal();
        int iOptInt = downloadSettingObtainGlobal.optInt(DownloadSettingKeys.DOWNLOAD_SERVICE_FOREGROUND, 0);
        if ((iOptInt == 1 || iOptInt == 3) && sForegroundId == -1) {
            sForegroundId = 0;
        }
        if ((iOptInt == 2 || iOptInt == 3) && sIndependentProcessForegroundId == -1) {
            sIndependentProcessForegroundId = 0;
        }
        sBugFixNonOngoing = downloadSettingObtainGlobal.optBugFix(DownloadSettingKeys.BugFix.NON_GOING_NOTIFICATION_FOREGROUND, false);
        sBugfixNotifyTooFast = downloadSettingObtainGlobal.optBugFix(DownloadSettingKeys.BugFix.FIX_NOTIFY_TOO_FAST, false);
        long jOptLong = downloadSettingObtainGlobal.optLong(DownloadSettingKeys.NOTIFICATION_TIME_WINDOW, NOTIFY_TIME_WINDOW);
        sNotifyTimeWindow = jOptLong;
        if (jOptLong < 0 || jOptLong > 1200) {
            sNotifyTimeWindow = NOTIFY_TIME_WINDOW;
        }
    }

    private void createNotifyHandlerThread() {
        if (this.mNotifyThreadHandler == null) {
            ThreadWithHandler threadWithHandler = new ThreadWithHandler("DownloaderNotifyThread");
            this.mNotifyThreadHandler = threadWithHandler;
            threadWithHandler.start();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ThreadWithHandler threadWithHandler = this.mNotifyThreadHandler;
        if (threadWithHandler != null) {
            try {
                threadWithHandler.quit();
            } catch (Throwable unused) {
            }
            this.mNotifyThreadHandler = null;
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        handleIntent(intent);
        return 2;
    }

    private void handleIntent(final Intent intent) {
        ThreadWithHandler threadWithHandler;
        if (intent == null) {
            return;
        }
        final String action = intent.getAction();
        if (TextUtils.isEmpty(action) || (threadWithHandler = this.mNotifyThreadHandler) == null) {
            return;
        }
        threadWithHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.notification.DownloadNotificationService.1
            @Override // java.lang.Runnable
            public void run() {
                ConnectivityManager connectivityManager;
                NetworkInfo activeNetworkInfo;
                final NotificationManager notificationManager = (NotificationManager) DownloadNotificationService.this.getSystemService(b.l);
                final int intExtra = intent.getIntExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, 0);
                if (action.equals(NotificationConstants.ACTION_NOTIFICATION_NOTIFY)) {
                    final Notification notification = (Notification) intent.getParcelableExtra(NotificationConstants.EXTRA_NOTIFICATION);
                    int intExtra2 = intent.getIntExtra(NotificationConstants.EXTRA_NOTIFICATION_STATUS, 0);
                    if (intExtra == 0 || notification == null || notificationManager == null) {
                        return;
                    }
                    if (intExtra2 != 4) {
                        if (intExtra2 == -2 || intExtra2 == -3) {
                            if (DownloadNotificationService.sBugfixNotifyTooFast) {
                                DownloadNotificationService.this.doImportantNotify(notificationManager, intExtra, notification);
                                return;
                            } else {
                                if (DownloadNotificationService.this.mNotifyThreadHandler != null) {
                                    DownloadNotificationService.this.mNotifyThreadHandler.postDelayed(new Runnable() { // from class: com.ss.android.socialbase.downloader.notification.DownloadNotificationService.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            DownloadNotificationService.this.doNotify(notificationManager, intExtra, notification);
                                        }
                                    }, intExtra2 == -2 ? 50L : 200L);
                                    return;
                                }
                                return;
                            }
                        }
                        if (DownloadNotificationService.sBugfixNotifyTooFast) {
                            DownloadNotificationService.this.doImportantNotify(notificationManager, intExtra, notification);
                            return;
                        } else {
                            DownloadNotificationService.this.doNotify(notificationManager, intExtra, notification);
                            return;
                        }
                    }
                    if (Downloader.getInstance(DownloadComponentManager.getAppContext()).isDownloading(intExtra)) {
                        DownloadInfo downloadInfo = Downloader.getInstance(DownloadComponentManager.getAppContext()).getDownloadInfo(intExtra);
                        if (DownloadNotificationService.sBugfixNotifyTooFast) {
                            if (downloadInfo == null || !downloadInfo.canNotifyProgress() || System.currentTimeMillis() - DownloadNotificationService.sLastImportantNotifyTimestamp <= DownloadNotificationService.sNotifyTimeWindow) {
                                return;
                            }
                            DownloadNotificationService.this.doNotify(notificationManager, intExtra, notification);
                            downloadInfo.setLastNotifyProgressTime();
                            return;
                        }
                        if (downloadInfo == null || !downloadInfo.canNotifyProgress()) {
                            return;
                        }
                        DownloadNotificationService.this.doNotify(notificationManager, intExtra, notification);
                        downloadInfo.setLastNotifyProgressTime();
                        return;
                    }
                    return;
                }
                if (action.equals(NotificationConstants.ACTION_NOTIFICATION_CANCEL)) {
                    if (intExtra != 0) {
                        DownloadNotificationService.this.doCancel(notificationManager, intExtra);
                        return;
                    }
                    return;
                }
                if (action.equals(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE)) {
                    try {
                        if (DownloadUtils.checkPermission(DownloadNotificationService.this, "android.permission.ACCESS_NETWORK_STATE") && (connectivityManager = (ConnectivityManager) DownloadNotificationService.this.getApplicationContext().getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                            ArrayList arrayList = new ArrayList();
                            if (!TextUtils.isEmpty(DownloadConstants.MIME_APK)) {
                                arrayList.add(DownloadConstants.MIME_APK);
                            }
                            arrayList.add(DownloadConstants.MIME_PLG);
                            Context applicationContext = DownloadNotificationService.this.getApplicationContext();
                            if (applicationContext != null) {
                                Downloader.getInstance(applicationContext).restartAllFailedDownloadTasks(arrayList);
                                Downloader.getInstance(applicationContext).restartAllPauseReserveOnWifiDownloadTasks(arrayList);
                                return;
                            }
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                if (action.equals("android.intent.action.MEDIA_UNMOUNTED") || action.equals("android.intent.action.MEDIA_REMOVED") || action.equals("android.intent.action.MEDIA_BAD_REMOVAL") || action.equals("android.intent.action.MEDIA_EJECT")) {
                    try {
                        Downloader.getInstance(DownloadNotificationService.this).pauseAll();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doImportantNotify(final NotificationManager notificationManager, final int i, Notification notification) {
        synchronized (this.pendingImportantNotify) {
            int iIndexOfKey = this.pendingImportantNotify.indexOfKey(i);
            if (iIndexOfKey >= 0 && iIndexOfKey < this.pendingImportantNotify.size()) {
                this.pendingImportantNotify.setValueAt(iIndexOfKey, notification);
                return;
            }
            long jCurrentTimeMillis = sNotifyTimeWindow - (System.currentTimeMillis() - sLastNotifyTimestamp);
            if (jCurrentTimeMillis <= 0) {
                jCurrentTimeMillis = 0;
            }
            if (jCurrentTimeMillis > 20000) {
                jCurrentTimeMillis = 20000;
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis() + jCurrentTimeMillis;
            sLastImportantNotifyTimestamp = jCurrentTimeMillis2;
            sLastNotifyTimestamp = jCurrentTimeMillis2;
            if (jCurrentTimeMillis <= 0) {
                doNotify(notificationManager, i, notification);
            } else if (this.mNotifyThreadHandler != null) {
                synchronized (this.pendingImportantNotify) {
                    this.pendingImportantNotify.put(i, notification);
                }
                this.mNotifyThreadHandler.postDelayed(new Runnable() { // from class: com.ss.android.socialbase.downloader.notification.DownloadNotificationService.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DownloadNotificationService.this.performImportantNotify(notificationManager, i);
                    }
                }, jCurrentTimeMillis);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performImportantNotify(NotificationManager notificationManager, int i) {
        Notification notification;
        synchronized (this.pendingImportantNotify) {
            notification = this.pendingImportantNotify.get(i);
            this.pendingImportantNotify.remove(i);
        }
        if (notification != null) {
            doNotify(notificationManager, i, notification);
        }
    }

    private boolean needStartForeground(int i, Notification notification) {
        int i2;
        int i3;
        if (!sAllowStartForeground || (i2 = sForegroundId) == i || (i3 = sIndependentProcessForegroundId) == i) {
            return false;
        }
        if (i2 != 0 && i3 != 0) {
            return false;
        }
        if (sBugFixNonOngoing && (notification.flags & 2) == 0) {
            return false;
        }
        return Build.VERSION.SDK_INT < 26 || !TextUtils.isEmpty(notification.getChannelId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doNotify(NotificationManager notificationManager, int i, Notification notification) {
        if (needStartForeground(i, notification)) {
            try {
                boolean z = false;
                boolean z2 = DownloadProcessDispatcher.getInstance().getDownloadWithIndependentProcessStatus(i) == 1 && !DownloadUtils.isDownloaderProcess();
                if ((!z2 && sForegroundId == 0) || (z2 && sIndependentProcessForegroundId == 0)) {
                    z = true;
                }
                if (z) {
                    IDownloadProxy downloadHandler = DownloadProcessDispatcher.getInstance().getDownloadHandler(i);
                    if (downloadHandler.isServiceAlive() && !downloadHandler.isServiceForeground()) {
                        Logger.i(TAG, "doNotify, startForeground, ======== id = " + i + ", isIndependentProcess = " + z2);
                        if (z2) {
                            sIndependentProcessForegroundId = i;
                        } else {
                            sForegroundId = i;
                        }
                        downloadHandler.startForeground(i, notification);
                    } else {
                        Logger.i(TAG, "doNotify: canStartForeground = true, but proxy can not startForeground, isIndependentProcess = " + z2);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else if ((sForegroundId == i || sIndependentProcessForegroundId == i) && sBugFixNonOngoing && (notification.flags & 2) == 0) {
            doCancel(notificationManager, i);
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (sLastNotifyTimestamp < jCurrentTimeMillis) {
                sLastNotifyTimestamp = jCurrentTimeMillis;
            }
            notificationManager.notify(i, notification);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCancel(NotificationManager notificationManager, int i) {
        boolean z;
        AbsNotificationItem absNotificationItemValueAt;
        int id;
        if (sForegroundId == i || sIndependentProcessForegroundId == i) {
            boolean z2 = true;
            if (sForegroundId == i) {
                sForegroundId = 0;
                z = false;
            } else {
                sIndependentProcessForegroundId = 0;
                z = true;
            }
            try {
                IDownloadProxy downloadHandler = DownloadProcessDispatcher.getInstance().getDownloadHandler(i);
                if (!downloadHandler.isServiceForeground()) {
                    sAllowStartForeground = false;
                    Logger.w(TAG, "try to stopForeground when is not Foreground, id = " + i + ", isIndependentProcess = " + z);
                }
                Logger.i(TAG, "doCancel, ========== stopForeground id = " + i + ", isIndependentProcess = " + z);
                downloadHandler.stopForeground(false, true);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                notificationManager.cancel(i);
            } catch (Throwable unused) {
            }
            if (sAllowStartForeground) {
                try {
                    SparseArray<AbsNotificationItem> allNotificationItems = DownloadNotificationManager.getInstance().getAllNotificationItems();
                    if (allNotificationItems != null) {
                        for (int size = allNotificationItems.size() - 1; size >= 0; size--) {
                            absNotificationItemValueAt = allNotificationItems.valueAt(size);
                            if (absNotificationItemValueAt != null && (id = absNotificationItemValueAt.getId()) != i && id != sForegroundId && id != sIndependentProcessForegroundId && absNotificationItemValueAt.isOngoing()) {
                                if ((DownloadProcessDispatcher.getInstance().getDownloadWithIndependentProcessStatus(absNotificationItemValueAt.getId()) == 1 && !DownloadUtils.isDownloaderProcess()) == z) {
                                    break;
                                }
                            }
                        }
                        absNotificationItemValueAt = null;
                    } else {
                        absNotificationItemValueAt = null;
                    }
                    if (absNotificationItemValueAt != null) {
                        int id2 = absNotificationItemValueAt.getId();
                        try {
                            notificationManager.cancel(id2);
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                        if (Downloader.getInstance(this).getStatus(id2) != 1) {
                            z2 = false;
                        }
                        Logger.i(TAG, "doCancel, updateNotification id = " + id2);
                        absNotificationItemValueAt.updateNotification(null, z2);
                        return;
                    }
                    return;
                } catch (Throwable th3) {
                    th3.printStackTrace();
                    return;
                }
            }
            return;
        }
        try {
            notificationManager.cancel(i);
        } catch (Throwable unused2) {
        }
    }
}
