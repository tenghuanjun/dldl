package com.ss.android.socialbase.downloader.notification;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.util.SparseArray;
import com.ss.android.socialbase.downloader.constants.NotificationConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadNotificationManager {
    private static final String KEY_NOTIFS_STRING = "notifs_string";
    private static volatile DownloadNotificationManager instance;
    private static final Object sLock = new Object();
    private final long PROGRESS_NOTIFY_DURATION = 1000;
    private final Map<Integer, Long> PROGRESS_NOTIFY_LAST_TIME_INFO = new HashMap();
    private final Set<String> notificationTagSet = new HashSet();
    private final SparseArray<AbsNotificationItem> notificationItemArray = new SparseArray<>();

    static boolean isCompleteVisibility(int i) {
        return i == 1 || i == 3;
    }

    private DownloadNotificationManager() {
    }

    public static DownloadNotificationManager getInstance() {
        if (instance == null) {
            synchronized (DownloadNotificationManager.class) {
                if (instance == null) {
                    instance = new DownloadNotificationManager();
                }
            }
        }
        return instance;
    }

    public void hideNotification(int i) {
        DownloadInfo downloadInfo = Downloader.getInstance(DownloadComponentManager.getAppContext()).getDownloadInfo(i);
        if (downloadInfo == null) {
            return;
        }
        updateNotificationState(downloadInfo);
        cancelCompleteNotification(downloadInfo);
    }

    void updateNotificationState(DownloadInfo downloadInfo) {
        IDownloadCache downloadCache = DownloadComponentManager.getDownloadCache();
        if (downloadCache != null && downloadInfo.isDownloadOverStatus()) {
            downloadInfo.setNotificationVisibility(3);
            try {
                downloadCache.updateDownloadInfo(downloadInfo);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }

    void cancelCompleteNotification(DownloadInfo downloadInfo) {
        if (isCompleteAndVisible(downloadInfo)) {
            cancelNotification(downloadInfo.getId());
        }
    }

    static boolean isCompleteAndVisible(DownloadInfo downloadInfo) {
        return downloadInfo.isDownloadOverStatus() && isCompleteVisibility(downloadInfo.getNotificationVisibility());
    }

    public void notifyByService(int i, int i2, Notification notification) {
        Context appContext = DownloadComponentManager.getAppContext();
        if (appContext == null || i == 0 || notification == null) {
            return;
        }
        if (i2 == 4) {
            synchronized (this.PROGRESS_NOTIFY_LAST_TIME_INFO) {
                Long l = this.PROGRESS_NOTIFY_LAST_TIME_INFO.get(Integer.valueOf(i));
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (l != null && Math.abs(jCurrentTimeMillis - l.longValue()) < 1000) {
                    return;
                } else {
                    this.PROGRESS_NOTIFY_LAST_TIME_INFO.put(Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis));
                }
            }
        }
        try {
            Intent intent = new Intent(appContext, (Class<?>) DownloadNotificationService.class);
            intent.setAction(NotificationConstants.ACTION_NOTIFICATION_NOTIFY);
            intent.putExtra(NotificationConstants.EXTRA_NOTIFICATION_STATUS, i2);
            intent.putExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, i);
            intent.putExtra(NotificationConstants.EXTRA_NOTIFICATION, notification);
            appContext.startService(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void cancel(int i) {
        Context appContext = DownloadComponentManager.getAppContext();
        if (appContext == null || i == 0) {
            return;
        }
        try {
            Intent intent = new Intent(appContext, (Class<?>) DownloadNotificationService.class);
            intent.setAction(NotificationConstants.ACTION_NOTIFICATION_CANCEL);
            intent.putExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, i);
            appContext.startService(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void addNotification(AbsNotificationItem absNotificationItem) {
        if (absNotificationItem == null) {
            return;
        }
        synchronized (this.notificationItemArray) {
            this.notificationItemArray.put(absNotificationItem.getId(), absNotificationItem);
        }
    }

    public AbsNotificationItem removeNotification(int i) {
        AbsNotificationItem absNotificationItem;
        if (i == 0) {
            return null;
        }
        synchronized (this.notificationItemArray) {
            absNotificationItem = this.notificationItemArray.get(i);
            if (absNotificationItem != null) {
                this.notificationItemArray.remove(i);
                Logger.d("removeNotificationId " + i);
            }
        }
        return absNotificationItem;
    }

    public AbsNotificationItem getNotificationItem(int i) {
        AbsNotificationItem absNotificationItem;
        if (i == 0) {
            return null;
        }
        synchronized (this.notificationItemArray) {
            absNotificationItem = this.notificationItemArray.get(i);
        }
        return absNotificationItem;
    }

    SparseArray<AbsNotificationItem> getAllNotificationItems() {
        SparseArray<AbsNotificationItem> sparseArray;
        synchronized (this.notificationItemArray) {
            sparseArray = this.notificationItemArray;
        }
        return sparseArray;
    }

    public void cancelNotification(int i) {
        removeNotification(i);
        if (i != 0) {
            getInstance().cancel(i);
        }
    }

    public void clearNotification() {
        SparseArray<AbsNotificationItem> sparseArrayClone;
        synchronized (this.notificationItemArray) {
            sparseArrayClone = this.notificationItemArray.clone();
            this.notificationItemArray.clear();
        }
        for (int i = 0; i < sparseArrayClone.size(); i++) {
            sparseArrayClone.get(sparseArrayClone.keyAt(i)).cancel();
        }
    }
}
