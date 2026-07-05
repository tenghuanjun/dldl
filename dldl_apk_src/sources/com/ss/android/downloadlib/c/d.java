package com.ss.android.downloadlib.c;

import com.ss.android.downloadlib.addownload.b.i;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.constants.SpJsonConstants;
import com.ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class d implements com.ss.android.socialbase.appdownloader.c.g, IDownloadCacheSyncStatusListener {
    @Override // com.ss.android.socialbase.appdownloader.c.g
    public void a(List<DownloadInfo> list) {
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener
    public void onStart() {
    }

    @Override // com.ss.android.socialbase.appdownloader.c.g
    public void a(DownloadInfo downloadInfo, boolean z) {
        if (downloadInfo == null) {
            return;
        }
        a(downloadInfo, downloadInfo.getRealStatus(), z);
    }

    public void a(DownloadInfo downloadInfo, int i, boolean z) {
        com.ss.android.downloadlib.addownload.b.f.a().b();
        com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo);
        if (bVarA == null) {
            return;
        }
        try {
            if (z) {
                bVarA.c(downloadInfo.getFailedResumeCount());
            } else if (bVarA.D() == -1) {
                return;
            } else {
                bVarA.c(-1);
            }
            i.a().a(bVarA);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ID, downloadInfo.getId());
            jSONObject.put("name", downloadInfo.getName());
            jSONObject.put("url", downloadInfo.getUrl());
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_TIME, downloadInfo.getDownloadTime());
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_STATUS, i);
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_CUR_BYTES, downloadInfo.getCurBytes());
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_TOTAL_BYTES, downloadInfo.getTotalBytes());
            int i2 = 1;
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ONLY_WIFI, downloadInfo.isOnlyWifi() ? 1 : 0);
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_CHUNK_COUNT, downloadInfo.getChunkCount());
            if (!z) {
                i2 = 2;
            }
            jSONObject.put("launch_resumed", i2);
            jSONObject.put("failed_resume_count", downloadInfo.getFailedResumeCount());
            com.ss.android.downloadlib.d.a.a().a("embeded_ad", "download_uncompleted", jSONObject, bVarA);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener
    public void onSuccess() {
        com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.c.d.1
            @Override // java.lang.Runnable
            public void run() {
                DownloadInfo downloadInfo;
                int spIntVal;
                com.ss.android.downloadlib.addownload.b.f.a().b();
                for (com.ss.android.downloadad.api.a.b bVar : com.ss.android.downloadlib.addownload.b.f.a().c().values()) {
                    int iS = bVar.s();
                    if (iS != 0) {
                        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(iS);
                        if (downloadSettingObtain.optInt("notification_opt_2") == 1 && (downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(iS)) != null) {
                            if (m.b(bVar) && !m.c(bVar.e())) {
                                int spIntVal2 = downloadInfo.getSpIntVal(SpJsonConstants.RESTART_NOTIFY_OPEN_APP_COUNT);
                                if (spIntVal2 < downloadSettingObtain.optInt("noti_open_restart_times", 1)) {
                                    h.a().e(bVar);
                                    downloadInfo.setSpValue(SpJsonConstants.RESTART_NOTIFY_OPEN_APP_COUNT, String.valueOf(spIntVal2 + 1));
                                }
                            } else if (downloadInfo.getRealStatus() == -2) {
                                int spIntVal3 = downloadInfo.getSpIntVal(SpJsonConstants.RESTART_NOTIFY_CONTINUE_COUNT);
                                if (spIntVal3 < downloadSettingObtain.optInt("noti_continue_restart_times", 1)) {
                                    h.a().a(bVar);
                                    downloadInfo.setSpValue(SpJsonConstants.RESTART_NOTIFY_CONTINUE_COUNT, String.valueOf(spIntVal3 + 1));
                                }
                            } else if (downloadInfo.getRealStatus() == -3 && DownloadUtils.isFileDownloaded(downloadInfo) && !m.b(bVar) && (spIntVal = downloadInfo.getSpIntVal(SpJsonConstants.RESTART_NOTIFY_INSTALL_COUNT)) < downloadSettingObtain.optInt("noti_install_restart_times", 1)) {
                                h.a().c(bVar);
                                downloadInfo.setSpValue(SpJsonConstants.RESTART_NOTIFY_INSTALL_COUNT, String.valueOf(spIntVal + 1));
                            }
                        }
                    }
                }
            }
        }, 5000L);
    }
}
