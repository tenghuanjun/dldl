package com.ss.android.downloadlib;

import android.text.TextUtils;
import com.ss.android.download.api.config.j;
import com.ss.android.download.api.config.l;
import com.ss.android.download.api.config.q;
import com.ss.android.download.api.config.u;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.socialbase.appdownloader.DownloadHandlerService;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.DownloaderBuilder;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
class e implements com.ss.android.download.api.a {
    e() {
    }

    @Override // com.ss.android.download.api.a
    public com.ss.android.download.api.a a(com.ss.android.download.api.config.i iVar) {
        k.a(iVar);
        return this;
    }

    @Override // com.ss.android.download.api.a
    public com.ss.android.download.api.a a(com.ss.android.download.api.config.g gVar) {
        k.a(gVar);
        return this;
    }

    @Override // com.ss.android.download.api.a
    public com.ss.android.download.api.a a(l lVar) {
        k.a(lVar);
        return this;
    }

    @Override // com.ss.android.download.api.a
    public com.ss.android.download.api.a a(com.ss.android.download.api.config.h hVar) {
        k.a(hVar);
        return this;
    }

    @Override // com.ss.android.download.api.a
    public com.ss.android.download.api.a a(j jVar) {
        k.a(jVar);
        return this;
    }

    @Override // com.ss.android.download.api.a
    public com.ss.android.download.api.a a(com.ss.android.download.api.model.a aVar) {
        k.a(aVar);
        return this;
    }

    @Override // com.ss.android.download.api.a
    public com.ss.android.download.api.a a(String str) {
        k.a(str);
        return this;
    }

    @Override // com.ss.android.download.api.a
    public com.ss.android.download.api.a a(final com.ss.android.download.api.config.b bVar) {
        k.a(bVar);
        AppStatusManager.getInstance().setInnerAppStatusChangeCaller(new AppStatusManager.InnerAppStatusChangeCaller() { // from class: com.ss.android.downloadlib.e.1
            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.InnerAppStatusChangeCaller
            public boolean isAppInBackground() {
                return bVar.a();
            }
        });
        return this;
    }

    @Override // com.ss.android.download.api.a
    public com.ss.android.download.api.a a(DownloaderBuilder downloaderBuilder) {
        if (downloaderBuilder.getNotificationClickCallback() == null) {
            downloaderBuilder.notificationClickCallback(new INotificationClickCallback() { // from class: com.ss.android.downloadlib.e.2
                @Override // com.ss.android.socialbase.downloader.depend.INotificationClickCallback
                public boolean onClickWhenSuccess(DownloadInfo downloadInfo) {
                    return false;
                }

                @Override // com.ss.android.socialbase.downloader.depend.INotificationClickCallback
                public boolean onClickWhenUnSuccess(DownloadInfo downloadInfo) {
                    DownloadSetting downloadSettingObtain = DownloadSetting.obtain(downloadInfo.getId());
                    if (downloadSettingObtain.optInt("notification_opt_2") == 1) {
                        if (downloadInfo.getStatus() == -2) {
                            DownloadHandlerService.a(k.a(), downloadInfo, com.ss.android.socialbase.appdownloader.d.j().b(), Downloader.getInstance(k.a()).getDownloadNotificationEventListener(downloadInfo.getId()));
                        }
                        return true;
                    }
                    boolean zA = a(downloadInfo);
                    if (downloadSettingObtain.optInt("disable_delete_dialog", 0) == 1) {
                        return true;
                    }
                    return zA;
                }

                private boolean a(DownloadInfo downloadInfo) {
                    String strA;
                    u uVarT = k.t();
                    if (uVarT == null) {
                        return false;
                    }
                    com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo);
                    if (bVarA != null && bVarA.c()) {
                        strA = DownloadSetting.obtain(downloadInfo.getId()).optString("ad_notification_jump_url", null);
                    } else {
                        strA = com.ss.android.downloadlib.addownload.i.a(downloadInfo);
                    }
                    if (TextUtils.isEmpty(strA)) {
                        return false;
                    }
                    return uVarT.a(k.a(), strA);
                }

                @Override // com.ss.android.socialbase.downloader.depend.INotificationClickCallback
                public boolean onClickWhenInstalled(DownloadInfo downloadInfo) {
                    if (downloadInfo == null) {
                        return false;
                    }
                    com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo);
                    if (bVarA != null) {
                        com.ss.android.downloadlib.b.a.a(bVarA);
                    } else {
                        com.ss.android.downloadlib.g.i.b(k.a(), downloadInfo.getPackageName());
                    }
                    DownloadNotificationManager.getInstance().cancelNotification(downloadInfo.getId());
                    return true;
                }
            });
        }
        downloaderBuilder.addDownloadCompleteHandler(new com.ss.android.downloadlib.c.c());
        Downloader.initOrCover(downloaderBuilder, true);
        return this;
    }

    @Override // com.ss.android.download.api.a
    public com.ss.android.download.api.a a(q qVar) {
        k.a(qVar);
        return this;
    }

    @Override // com.ss.android.download.api.a
    public void a() {
        if (!k.x()) {
            com.ss.android.downloadlib.e.c.a().a("ttdownloader init error");
        }
        k.a(com.ss.android.downloadlib.e.c.a());
        try {
            com.ss.android.socialbase.appdownloader.d.j().b(k.w());
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ss.android.socialbase.appdownloader.d.j().a(a.a());
        d.a().b(new Runnable() { // from class: com.ss.android.downloadlib.e.3
            @Override // java.lang.Runnable
            public void run() {
                com.ss.android.socialbase.appdownloader.f.e.a("");
                if (com.ss.android.socialbase.appdownloader.f.e.o()) {
                    DownloadComponentManager.setNotAutoRebootService(true);
                }
                if (DownloadSetting.obtainGlobal().optInt("disable_security_init", 1) == 1) {
                    com.ss.android.socialbase.appdownloader.f.f.a(k.a());
                }
            }
        });
    }
}
