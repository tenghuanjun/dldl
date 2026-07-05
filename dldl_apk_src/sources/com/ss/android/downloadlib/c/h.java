package com.ss.android.downloadlib.c;

import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class h {

    private static class a {
        private static h a = new h();
    }

    public static h a() {
        return a.a;
    }

    private h() {
    }

    public void a(com.ss.android.downloadad.api.a.b bVar) {
        b(bVar, 5L);
    }

    public void b(com.ss.android.downloadad.api.a.b bVar) {
        if (bVar == null) {
            return;
        }
        b(bVar, DownloadSetting.obtain(bVar.s()).optInt("noti_continue_delay_secs", 5));
    }

    private void b(final com.ss.android.downloadad.api.a.b bVar, long j) {
        final int iS = bVar.s();
        if (DownloadSetting.obtain(iS).optInt("notification_opt_2") != 1) {
            return;
        }
        a(iS);
        com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.c.h.1
            @Override // java.lang.Runnable
            public void run() {
                DownloadInfo downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(iS);
                JSONObject jSONObject = new JSONObject();
                m.a(jSONObject, "ttdownloader_type", (Object) 1);
                com.ss.android.downloadlib.g.f.c(downloadInfo, jSONObject);
                if (downloadInfo != null && -2 == downloadInfo.getRealStatus() && !downloadInfo.isPauseReserveOnWifi()) {
                    h.this.a(iS, bVar, jSONObject);
                } else {
                    m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, (Object) 1001);
                }
                com.ss.android.downloadlib.d.a.a().b("download_notification_try_show", jSONObject, bVar);
            }
        }, j * 1000);
    }

    public void c(com.ss.android.downloadad.api.a.b bVar) {
        c(bVar, 5L);
    }

    public void d(com.ss.android.downloadad.api.a.b bVar) {
        c(bVar, DownloadSetting.obtain(bVar.s()).optInt("noti_install_delay_secs", 5));
    }

    private void c(final com.ss.android.downloadad.api.a.b bVar, long j) {
        final int iS = bVar.s();
        if (DownloadSetting.obtain(iS).optInt("notification_opt_2") != 1) {
            return;
        }
        a(iS);
        com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.c.h.2
            @Override // java.lang.Runnable
            public void run() {
                DownloadInfo downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(iS);
                JSONObject jSONObject = new JSONObject();
                m.a(jSONObject, "ttdownloader_type", (Object) 2);
                com.ss.android.downloadlib.g.f.c(downloadInfo, jSONObject);
                if (!m.b(bVar)) {
                    h.this.a(iS, bVar, jSONObject);
                } else {
                    m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, (Object) 1002);
                }
                com.ss.android.downloadlib.d.a.a().b("download_notification_try_show", jSONObject, bVar);
            }
        }, j * 1000);
    }

    public void e(com.ss.android.downloadad.api.a.b bVar) {
        a(bVar, 5L);
    }

    public void f(com.ss.android.downloadad.api.a.b bVar) {
        a(bVar, DownloadSetting.obtain(bVar.s()).optInt("noti_open_delay_secs", 5));
    }

    public void a(final com.ss.android.downloadad.api.a.b bVar, long j) {
        final int iS = bVar.s();
        if (DownloadSetting.obtain(iS).optInt("notification_opt_2") != 1) {
            return;
        }
        a(iS);
        com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.c.h.3
            @Override // java.lang.Runnable
            public void run() {
                DownloadInfo downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(iS);
                JSONObject jSONObject = new JSONObject();
                m.a(jSONObject, "ttdownloader_type", (Object) 3);
                com.ss.android.downloadlib.g.f.c(downloadInfo, jSONObject);
                if (!m.c(bVar.e())) {
                    h.this.a(iS, bVar, jSONObject);
                } else {
                    m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, (Object) 1003);
                }
                com.ss.android.downloadlib.d.a.a().b("download_notification_try_show", jSONObject, bVar);
            }
        }, j * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, com.ss.android.downloadad.api.a.b bVar, JSONObject jSONObject) {
        if (!com.ss.android.socialbase.appdownloader.e.d.a()) {
            m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, (Object) 1004);
            return;
        }
        DownloadInfo downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(i);
        if (downloadInfo == null) {
            m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, (Object) 1005);
            return;
        }
        if (DownloadNotificationManager.getInstance().getNotificationItem(i) != null) {
            DownloadNotificationManager.getInstance().cancelNotification(i);
        }
        com.ss.android.socialbase.appdownloader.e.a aVar = new com.ss.android.socialbase.appdownloader.e.a(k.a(), i, downloadInfo.getTitle(), downloadInfo.getSavePath(), downloadInfo.getName(), downloadInfo.getExtra());
        aVar.setCurBytes(downloadInfo.getCurBytes());
        aVar.setTotalBytes(downloadInfo.getTotalBytes());
        aVar.refreshStatus(downloadInfo.getStatus(), null, false, false);
        DownloadNotificationManager.getInstance().addNotification(aVar);
        aVar.updateNotification(null, false);
        com.ss.android.downloadlib.d.a.a().b("download_notification_show", jSONObject, bVar);
    }

    public void a(int i) {
        DownloadInfo downloadInfo;
        if (com.ss.android.socialbase.appdownloader.e.c.a().a(i) != null || (downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(i)) == null) {
            return;
        }
        com.ss.android.socialbase.appdownloader.e.c.a().a(i, downloadInfo.getIconUrl());
    }
}
