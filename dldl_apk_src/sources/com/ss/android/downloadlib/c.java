package com.ss.android.downloadlib;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.downloadlib.g.k;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class c implements com.ss.android.socialbase.appdownloader.c.h {
    private static String a = c.class.getSimpleName();
    private Handler b = new Handler(Looper.getMainLooper());

    @Override // com.ss.android.socialbase.appdownloader.c.h
    public void a(DownloadInfo downloadInfo, BaseException baseException, int i) {
        final DownloadModel downloadModelA;
        if (downloadInfo == null) {
            return;
        }
        if (i == -1 && baseException != null) {
            JSONObject jSONObject = new JSONObject();
            com.ss.android.downloadlib.g.f.c(downloadInfo, jSONObject);
            a.a(jSONObject, downloadInfo);
            k.a("download_failed", jSONObject.toString());
        }
        com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo);
        if (bVarA == null) {
            return;
        }
        try {
            if (i != -1) {
                if (i == -3) {
                    a.a(downloadInfo, bVarA);
                    return;
                }
                if (i == 2001) {
                    a.a().a(downloadInfo, bVarA, 2001);
                    return;
                } else {
                    if (i == 11) {
                        a.a().a(downloadInfo, bVarA, 2000);
                        if (bVarA.S()) {
                            return;
                        }
                        a(downloadInfo, bVarA);
                        return;
                    }
                    return;
                }
            }
            BaseException baseException2 = null;
            if (baseException != null) {
                if (DownloadSetting.obtain(downloadInfo.getId()).optInt("toast_without_network", 0) == 1 && baseException.getErrorCode() == 1049) {
                    this.b.post(new Runnable() { // from class: com.ss.android.downloadlib.c.1
                        @Override // java.lang.Runnable
                        public void run() {
                            com.ss.android.downloadlib.addownload.k.d().a(5, com.ss.android.downloadlib.addownload.k.a(), null, "无网络，请检查网络设置", null, 0);
                        }
                    });
                }
                if (DownloadUtils.isInsufficientSpaceError(baseException)) {
                    if (com.ss.android.downloadlib.addownload.k.n() != null) {
                        com.ss.android.downloadlib.addownload.k.n().a(bVarA.b());
                    }
                    com.ss.android.downloadlib.d.a.a().a("download_failed_for_space", bVarA);
                    if (!bVarA.Q()) {
                        com.ss.android.downloadlib.d.a.a().a("download_can_restart", bVarA);
                        a(downloadInfo);
                    }
                    if ((com.ss.android.downloadlib.addownload.k.n() == null || !com.ss.android.downloadlib.addownload.k.n().d()) && (downloadModelA = com.ss.android.downloadlib.addownload.b.f.a().a(bVarA.b())) != null && downloadModelA.isShowToast()) {
                        final DownloadSetting downloadSettingObtain = DownloadSetting.obtain(downloadInfo.getId());
                        if (downloadSettingObtain.optInt("show_no_enough_space_toast", 0) == 1) {
                            this.b.post(new Runnable() { // from class: com.ss.android.downloadlib.c.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    com.ss.android.downloadlib.addownload.k.d().a(2, com.ss.android.downloadlib.addownload.k.a(), downloadModelA, downloadSettingObtain.optString("no_enough_space_toast_text", "您的存储空间不足，请清理后再试"), null, 0);
                                }
                            });
                        }
                    }
                }
                baseException2 = new BaseException(baseException.getErrorCode(), m.a(baseException.getMessage(), com.ss.android.downloadlib.addownload.k.j().optInt(DownloadSettingKeys.KEY_EXCEPTION_MSG_LENGTH, TTAdConstant.SHOW_POLL_TIME_SPLASH_DEFAULT)));
            }
            com.ss.android.downloadlib.d.a.a().b(downloadInfo, baseException2);
            g.a().a(downloadInfo, baseException, "");
        } catch (Exception e) {
            com.ss.android.downloadlib.addownload.k.u().a(e, "onAppDownloadMonitorSend");
        }
    }

    private void a(final DownloadInfo downloadInfo, final com.ss.android.downloadad.api.a.b bVar) {
        final long jA = m.a(Environment.getDataDirectory(), -1L);
        long jMin = Math.min(524288000L, m.a(Environment.getDataDirectory()) / 10);
        final long totalBytes = downloadInfo.getTotalBytes();
        final double d = (totalBytes * 2.5d) + jMin;
        if (jA > -1 && totalBytes > -1) {
            double d2 = jA;
            if (d2 < d && d - d2 > com.ss.android.downloadlib.addownload.d.b()) {
                com.ss.android.downloadlib.addownload.d.a(downloadInfo.getId());
            }
        }
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.downloadlib.c.3
            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                if (!m.b(bVar)) {
                    long j = jA;
                    if (j <= -1 || totalBytes <= -1 || j >= d) {
                        return;
                    }
                    com.ss.android.downloadlib.d.a.a().a("clean_space_install", com.ss.android.downloadlib.addownload.d.a("install_no_enough_space"), bVar);
                    if (com.ss.android.downloadlib.addownload.d.a(downloadInfo, ((long) d) - jA)) {
                        AppStatusManager.getInstance().unregisterAppSwitchListener(this);
                        bVar.g(true);
                        return;
                    }
                    return;
                }
                AppStatusManager.getInstance().unregisterAppSwitchListener(this);
            }
        });
    }

    private void a(DownloadInfo downloadInfo) {
        if (com.ss.android.downloadlib.g.e.f(downloadInfo.getId())) {
            d.a().b(new com.ss.android.downloadlib.addownload.c.b(downloadInfo));
        }
    }
}
