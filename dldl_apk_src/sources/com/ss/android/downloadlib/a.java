package com.ss.android.downloadlib;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Pair;
import com.aliyun.aliyunface.api.ZIMResponseCode;
import com.ss.android.downloadlib.addownload.b.d;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.addownload.l;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.appdownloader.b;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.constants.DownloadStatus;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.depend.IOpenInstallerListener;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.network.NetTrafficManager;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a implements com.ss.android.downloadad.api.a, b.c, AppStatusManager.AppStatusChangeListener, IOpenInstallerListener {
    private static String a = a.class.getSimpleName();
    private static volatile a d;
    private long b;
    private b c;

    private a() {
        com.ss.android.socialbase.appdownloader.b.a(this);
        AppStatusManager.getInstance().registerAppSwitchListener(this);
    }

    public static a a() {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a();
                }
            }
        }
        return d;
    }

    public static synchronized void a(DownloadInfo downloadInfo, com.ss.android.downloadad.api.a.b bVar) {
        if (downloadInfo == null) {
            com.ss.android.downloadlib.e.c.a().a("onDownloadFinish info null");
            return;
        }
        if (bVar == null) {
            com.ss.android.downloadlib.e.c.a().a("onDownloadFinish nativeModel null");
            return;
        }
        if (bVar.G() != 1) {
            return;
        }
        com.ss.android.downloadlib.c.h.a().d(bVar);
        String strC = c(downloadInfo, bVar);
        com.ss.android.downloadlib.addownload.b.f.a().b(downloadInfo.getUrl(), strC);
        Map<Long, com.ss.android.downloadad.api.a.b> mapA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo.getUrl(), strC);
        bVar.f(System.currentTimeMillis());
        bVar.e(2);
        bVar.b(strC);
        mapA.put(Long.valueOf(bVar.b()), bVar);
        com.ss.android.downloadlib.addownload.b.i.a().a(mapA.values());
        a(bVar);
        g.a().a(downloadInfo, strC);
        if ("application/vnd.android.package-archive".equals(downloadInfo.getMimeType())) {
            if (com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVar).optInt("enable_app_install_receiver", 1) != 1 && k.j().optInt("enable_app_install_receiver", 1) != 1) {
                l.a().a(bVar);
            }
            a().b(downloadInfo, bVar);
            if (bVar.L()) {
                com.ss.android.downloadlib.addownload.a.a.a().a(downloadInfo.getId(), bVar.b(), bVar.l(), strC, downloadInfo.getTitle(), bVar.d(), downloadInfo.getTargetFilePath());
            }
            com.ss.android.downloadlib.addownload.e.a.a(downloadInfo, bVar.b(), bVar.d(), strC);
        }
    }

    public synchronized void a(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (m.b()) {
            throw new RuntimeException("handleAppInstalled in main thread.");
        }
        final com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(str);
        if (bVarA == null) {
            com.ss.android.downloadlib.addownload.b.d.a().a(str);
            return;
        }
        com.ss.android.downloadlib.addownload.e eVarA = g.a().a(bVarA.a());
        if (eVarA != null) {
            eVarA.f();
        }
        if (bVarA.c.get()) {
            return;
        }
        if (DownloadSetting.obtain(bVarA.s()).optInt("notification_opt_2") == 1) {
            DownloadNotificationManager.getInstance().cancelNotification(bVarA.s());
        }
        new com.ss.android.downloadlib.b.b().a(bVarA, new com.ss.android.downloadlib.b.g() { // from class: com.ss.android.downloadlib.a.1
            @Override // com.ss.android.downloadlib.b.g
            public void a(boolean z) {
                Logger.d(a.a, "appBackForeground->" + z);
                if (z) {
                    if (!(com.ss.android.downloadlib.b.f.c(bVarA) ? com.ss.android.downloadlib.b.a.a(str, bVarA) : false) && com.ss.android.downloadlib.b.f.d(bVarA) && bVarA.K() == 4) {
                        com.ss.android.downloadlib.addownload.a.a.a().a(bVarA);
                        return;
                    }
                    return;
                }
                if (com.ss.android.downloadlib.b.a.a(str, bVarA) || bVarA.K() != 4) {
                    return;
                }
                com.ss.android.downloadlib.addownload.a.a.a().a(bVarA);
            }
        }, com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVarA).optInt("try_applink_delay_after_installed", 0));
        com.ss.android.downloadlib.c.h.a().f(bVarA);
        a(str, bVarA);
        com.ss.android.downloadlib.addownload.a.a.a().b(str);
        DownloadInfo downloadInfoA = a((List<DownloadInfo>) Downloader.getInstance(k.a()).getSuccessedDownloadInfosWithMimeType("application/vnd.android.package-archive"), str);
        if (downloadInfoA != null) {
            if (DownloadSetting.obtain(downloadInfoA.getId()).optInt(DownloadSettingKeys.NO_HIDE_NOTIFICATION) != 1) {
                DownloadNotificationManager.getInstance().hideNotification(downloadInfoA.getId());
            }
            g.a().b(downloadInfoA, str);
            com.ss.android.downloadlib.addownload.c.d.a(downloadInfoA);
        } else {
            g.a().b(null, str);
        }
    }

    void a(DownloadInfo downloadInfo, com.ss.android.downloadad.api.a.b bVar, int i) {
        long jMax;
        if (downloadInfo == null || bVar == null) {
            return;
        }
        b();
        long jCurrentTimeMillis = System.currentTimeMillis();
        bVar.b(jCurrentTimeMillis);
        bVar.g(m.a(Environment.getDataDirectory(), -1L));
        if (i != 2000) {
            jMax = 2000;
        } else {
            long jOptLong = DownloadSetting.obtain(downloadInfo.getId()).optLong("check_install_failed_delay_time", com.igexin.push.config.c.l);
            if (jOptLong < 0) {
                return;
            } else {
                jMax = Math.max(jOptLong, com.igexin.push.config.c.k);
            }
        }
        long j = jMax;
        b bVar2 = new b(bVar.b(), downloadInfo.getId(), jCurrentTimeMillis, i);
        d.a().a(bVar2, j);
        this.c = bVar2;
        com.ss.android.downloadlib.addownload.b.i.a().a(bVar);
    }

    private static class b implements Runnable {
        private long a;
        private int b;
        private long c;
        private int d;
        private long e;

        private b(long j, int i, long j2, int i2) {
            this.a = j;
            this.b = i;
            this.c = j2;
            this.d = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            this.e = System.currentTimeMillis();
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (a()) {
                    a.a().a(this.a, this.b);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        /* JADX WARN: Can't wrap try/catch for region: R(21:12|(1:19)(20:16|(0)|21|52|22|23|54|24|(1:26)|27|(1:29)(1:30)|31|(1:34)|35|(1:37)(1:38)|39|(1:41)|42|49|50)|20|21|52|22|23|54|24|(0)|27|(0)(0)|31|(1:34)|35|(0)(0)|39|(0)|42|49|50) */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00ee, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00f0, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00f1, code lost:
        
            r3 = r6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00f3, code lost:
        
            r0.printStackTrace();
         */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00ad A[Catch: Exception -> 0x00ee, TryCatch #1 {Exception -> 0x00ee, blocks: (B:24:0x008c, B:26:0x00ad, B:31:0x00bf, B:34:0x00cc, B:39:0x00da, B:42:0x00ea), top: B:54:0x008c }] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00bc  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00be  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00d7  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00d9  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00e9  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        boolean a() {
            /*
                Method dump skipped, instruction units count: 257
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.a.b.a():boolean");
        }

        private int a(boolean z, com.ss.android.downloadad.api.a.b bVar, DownloadInfo downloadInfo, boolean z2, JSONObject jSONObject) {
            DownloadSetting downloadSettingObtain = DownloadSetting.obtain(downloadInfo.getId());
            int i = 1;
            if (downloadSettingObtain.optInt("install_failed_check_ttmd5", 1) == 1) {
                int iCheckMd5Status = downloadInfo.checkMd5Status();
                try {
                    jSONObject.put("ttmd5_status", iCheckMd5Status);
                } catch (Throwable unused) {
                }
                if (!DownloadUtils.isMd5Valid(iCheckMd5Status)) {
                    return 2005;
                }
            }
            int i2 = this.d;
            if (i2 != 2000) {
                return i2;
            }
            if (downloadSettingObtain.optInt("install_failed_check_signature", 1) == 1 && m.e(k.a(), bVar.e())) {
                if (!m.a(m.i(k.a(), downloadInfo.getTargetFilePath()), m.h(k.a(), bVar.e()))) {
                    return ZIMResponseCode.ZIM_RESPONSE_FAIL;
                }
            }
            if (!z) {
                return ZIMResponseCode.ZIM_RESPONSE_NETWORK_FAIL;
            }
            long j = this.e;
            long j2 = this.c;
            if (j <= j2) {
                return 2000;
            }
            try {
                jSONObject.put("install_time", j - j2);
                if (bVar.C() <= this.c) {
                    i = 0;
                }
                jSONObject.put("install_again", i);
            } catch (Throwable unused2) {
            }
            if (z2) {
                return 2004;
            }
            return ZIMResponseCode.ZIM_RESPONSE_CLIENT_TIME_INVALID;
        }
    }

    public void a(final long j, int i) {
        long jOptLong = DownloadSetting.obtain(i).optLong("check_install_finish_hijack_delay_time", 900000L);
        if (jOptLong < 0) {
            return;
        }
        d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.a.2
            @Override // java.lang.Runnable
            public void run() {
                a.a().a(j);
            }
        }, Math.max(jOptLong, com.alipay.security.mobile.module.deviceinfo.e.a));
    }

    public void a(long j) {
        d.a aVarA;
        int iIntValue;
        try {
            com.ss.android.downloadad.api.a.b bVarD = com.ss.android.downloadlib.addownload.b.f.a().d(j);
            if (bVarD != null && !m.b(bVarD) && !bVarD.c.get()) {
                Pair<d.a, Integer> pairB = com.ss.android.downloadlib.addownload.b.d.a().b(bVarD);
                if (pairB != null) {
                    aVarA = (d.a) pairB.first;
                    iIntValue = ((Integer) pairB.second).intValue();
                } else {
                    aVarA = com.ss.android.downloadlib.addownload.b.d.a().a(bVarD);
                    iIntValue = -1;
                }
                if (aVarA == null) {
                    return;
                }
                com.ss.android.downloadlib.addownload.b.d.a().b(aVarA.a);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("installed_app_name", aVarA.d);
                jSONObject.put("installed_pkg_name", aVarA.a);
                if (iIntValue != -1) {
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, iIntValue);
                    com.ss.android.downloadlib.g.f.a(jSONObject, bVarD.s());
                    com.ss.android.downloadlib.d.a.a().b("install_finish_hijack", jSONObject, bVarD);
                    return;
                }
                com.ss.android.downloadlib.d.a.a().b("install_finish_may_hijack", jSONObject, bVarD);
            }
        } catch (Throwable th) {
            com.ss.android.downloadlib.e.c.a().a(th, "trySendInstallFinishHijack");
        }
    }

    public void a(String str, com.ss.android.downloadad.api.a.b bVar) {
        if (bVar != null && m.b(bVar) && bVar.c.compareAndSet(false, true)) {
            com.ss.android.downloadlib.d.a.a().a(bVar.j(), "install_finish", a(bVar, str, bVar.K() != 4 ? 3 : 4), bVar);
            com.ss.android.downloadlib.addownload.b.i.a().a(bVar);
        }
    }

    private static DownloadInfo a(List<DownloadInfo> list, String str) {
        if (list != null && !list.isEmpty() && !TextUtils.isEmpty(str)) {
            for (DownloadInfo downloadInfo : list) {
                if (downloadInfo != null) {
                    if (str.equals(downloadInfo.getPackageName())) {
                        return downloadInfo;
                    }
                    if (m.a(k.a(), downloadInfo.getTargetFilePath(), str)) {
                        return downloadInfo;
                    }
                }
            }
        }
        return null;
    }

    public static JSONObject a(JSONObject jSONObject, DownloadInfo downloadInfo) {
        if (jSONObject != null && downloadInfo != null) {
            int i = 1;
            if (DownloadSetting.obtain(downloadInfo.getId()).optInt("download_event_opt", 1) == 0) {
                return jSONObject;
            }
            try {
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ID, downloadInfo.getId());
                jSONObject.put("name", downloadInfo.getName());
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_CUR_BYTES, downloadInfo.getCurBytes());
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_TOTAL_BYTES, downloadInfo.getTotalBytes());
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_NETWORK_QUALITY, downloadInfo.getNetworkQuality());
                jSONObject.put(MonitorConstants.EXTRA_CUR_NETWORK_QUALITY, NetTrafficManager.getInstance().getCurrentNetworkQuality().name());
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ONLY_WIFI, downloadInfo.isOnlyWifi() ? 1 : 0);
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_NEED_HTTPS_DEGRADE, downloadInfo.isNeedHttpsToHttpRetry() ? 1 : 0);
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_HTTPS_DEGRADE_RETRY_USED, downloadInfo.isHttpsToHttpRetryUsed() ? 1 : 0);
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_CHUNK_COUNT, downloadInfo.getChunkCount());
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_RETRY_COUNT, downloadInfo.getRetryCount());
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_CUR_RETRY_TIME, downloadInfo.getCurRetryTime());
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_NEED_RETRY_DELAY, downloadInfo.isNeedRetryDelay() ? 1 : 0);
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_BACKUP_URL_USED, downloadInfo.isBackUpUrlUsed() ? 1 : 0);
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_HEAD_CONNECTION_ERROR_MSG, downloadInfo.getHeadConnectionException() != null ? downloadInfo.getHeadConnectionException() : "");
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_NEED_INDEPENDENT_PROCESS, downloadInfo.isNeedIndependentProcess() ? 1 : 0);
                jSONObject.put(MonitorConstants.EXTRA_TOTAL_RETRY_COUNT, downloadInfo.getTotalRetryCount());
                jSONObject.put(MonitorConstants.EXTRA_CUR_RETRY_TIME_IN_TOTAL, downloadInfo.getCurRetryTimeInTotal());
                jSONObject.put(MonitorConstants.EXTRA_REAL_DOWNLOAD_TIME, downloadInfo.getRealDownloadTime());
                jSONObject.put("first_speed_time", downloadInfo.getFirstSpeedTime());
                jSONObject.put("all_connect_time", downloadInfo.getAllConnectTime());
                jSONObject.put("download_prepare_time", downloadInfo.getDownloadPrepareTime());
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_TIME, downloadInfo.getRealDownloadTime() + downloadInfo.getAllConnectTime() + downloadInfo.getDownloadPrepareTime());
                jSONObject.put(MonitorConstants.EXTRA_CHUNK_DOWNGRADE_UESD, downloadInfo.isChunkDowngradeRetryUsed() ? 1 : 0);
                jSONObject.put(MonitorConstants.EXTRA_NEED_CHUNK_DOWNGRADE_RETRY, downloadInfo.isNeedChunkDowngradeRetry() ? 1 : 0);
                jSONObject.put("failed_resume_count", downloadInfo.getFailedResumeCount());
                jSONObject.put(MonitorConstants.EXTRA_PRECONNECT_LEVEL, downloadInfo.getPreconnectLevel());
                jSONObject.put("md5", downloadInfo.getMd5());
                jSONObject.put("expect_file_length", downloadInfo.getExpectFileLength());
                jSONObject.put("retry_schedule_count", downloadInfo.getRetryScheduleCount());
                jSONObject.put("rw_concurrent", downloadInfo.isRwConcurrent() ? 1 : 0);
                double curBytes = downloadInfo.getCurBytes() / 1048576.0d;
                double realDownloadTime = downloadInfo.getRealDownloadTime() / 1000.0d;
                if (curBytes > 0.0d && realDownloadTime > 0.0d) {
                    double d2 = curBytes / realDownloadTime;
                    try {
                        jSONObject.put(MonitorConstants.DOWNLOAD_SPEED, d2);
                    } catch (Exception unused) {
                    }
                    Logger.d(a, "download speed : " + d2 + "MB/s");
                }
                try {
                    jSONObject.put("is_download_service_foreground", Downloader.getInstance(k.a()).isDownloadServiceForeground(downloadInfo.getId()) ? 1 : 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (downloadInfo.getBackUpUrls() != null) {
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_BACKUP_URL_COUNT, downloadInfo.getBackUpUrls().size());
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_CUR_BACKUP_URL_INDEX, downloadInfo.getCurBackUpUrlIndex());
                }
                jSONObject.put("clear_space_restart_times", com.ss.android.downloadlib.addownload.c.d.a().b(downloadInfo.getUrl()));
                jSONObject.put("mime_type", downloadInfo.getMimeType());
                if (!DownloadUtils.isNetworkConnected(k.a())) {
                    i = 2;
                }
                jSONObject.put("network_available", i);
                jSONObject.put(MonitorConstants.STATUS_CODE, downloadInfo.getHttpStatusCode());
                b(jSONObject, downloadInfo);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return jSONObject;
    }

    public static JSONObject b(JSONObject jSONObject, DownloadInfo downloadInfo) {
        if (jSONObject == null || downloadInfo == null || DownloadSetting.obtain(downloadInfo.getId()).optInt("download_event_opt", 1) == 0) {
            return jSONObject;
        }
        try {
            long jB = m.b(0L);
            double d2 = jB;
            jSONObject.put("available_space", d2 / 1048576.0d);
            long totalBytes = downloadInfo.getTotalBytes();
            double d3 = totalBytes;
            jSONObject.put("apk_size", d3 / 1048576.0d);
            if (jB > 0 && totalBytes > 0) {
                jSONObject.put("available_space_ratio", d2 / d3);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public void b(DownloadInfo downloadInfo, final com.ss.android.downloadad.api.a.b bVar) {
        if (downloadInfo == null || bVar == null || DownloadSetting.obtain(downloadInfo.getId()).optInt("install_finish_check_ttmd5", 1) == 0) {
            return;
        }
        final String targetFilePath = downloadInfo.getTargetFilePath();
        if (TextUtils.isEmpty(targetFilePath)) {
            return;
        }
        d.a().b(new Runnable() { // from class: com.ss.android.downloadlib.a.3
            @Override // java.lang.Runnable
            public void run() {
                String strA = com.ss.android.downloadlib.g.a.a(targetFilePath);
                if (TextUtils.isEmpty(strA)) {
                    return;
                }
                k.a().getSharedPreferences("sp_ttdownloader_md5", 0).edit().putString(String.valueOf(bVar.b()), strA).apply();
            }
        });
    }

    private static void a(com.ss.android.downloadad.api.a.b bVar) {
        if (bVar == null) {
            return;
        }
        String strP = TextUtils.isEmpty(bVar.P()) ? "" : bVar.P();
        DownloadInfo downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(bVar.s());
        bVar.l("");
        com.ss.android.downloadlib.addownload.b.i.a().a(bVar);
        JSONObject jSONObjectA = a(new JSONObject(), downloadInfo);
        int i = 1;
        try {
            jSONObjectA.putOpt("finish_reason", strP);
            jSONObjectA.putOpt("finish_from_reserve_wifi", Integer.valueOf(downloadInfo.isDownloadFromReserveWifi() ? 1 : 0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo);
        com.ss.android.downloadlib.g.f.a(jSONObjectA, downloadInfo.getId());
        try {
            jSONObjectA.put("download_failed_times", bVarA.x());
            jSONObjectA.put("can_show_notification", com.ss.android.socialbase.appdownloader.e.d.a() ? 1 : 2);
            if (downloadInfo.getExpectFileLength() > 0 && downloadInfo.getTotalBytes() > 0) {
                jSONObjectA.put("file_length_gap", downloadInfo.getExpectFileLength() - downloadInfo.getTotalBytes());
            }
            jSONObjectA.put("ttmd5_status", downloadInfo.getTTMd5CheckStatus());
            jSONObjectA.put("has_send_download_failed_finally", bVarA.d.get() ? 1 : 2);
            if (!bVarA.V()) {
                i = 2;
            }
            jSONObjectA.put("is_update_download", i);
            com.ss.android.downloadlib.g.f.a(bVarA, jSONObjectA);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().b("download_finish", jSONObjectA, bVar);
    }

    private int a(com.ss.android.downloadad.api.a.b bVar, DownloadInfo downloadInfo, String str, JSONObject jSONObject) {
        int iB = com.ss.android.socialbase.appdownloader.c.b(k.a(), downloadInfo);
        int iB2 = m.b(k.a(), str);
        if (iB > 0 && iB2 > 0 && iB != iB2) {
            return iB2 > iB ? 3011 : 3010;
        }
        if (DownloadSetting.obtain(bVar.s()).optInt("install_finish_check_ttmd5", 1) != 1) {
            return ZIMResponseCode.ZIM_RESPONSE_RETRY;
        }
        String string = k.a().getSharedPreferences("sp_ttdownloader_md5", 0).getString(String.valueOf(bVar.b()), null);
        if (TextUtils.isEmpty(string) && downloadInfo != null) {
            string = com.ss.android.downloadlib.g.a.a(downloadInfo.getTargetFilePath());
        }
        int iA = com.ss.android.downloadlib.g.a.a(string, com.ss.android.downloadlib.g.a.b(str));
        try {
            jSONObject.put("ttmd5_status", iA);
        } catch (Throwable unused) {
        }
        if (iA == 0) {
            return 3000;
        }
        return iA == 1 ? ZIMResponseCode.ZIM_RESPONSE_CONTINUE : ZIMResponseCode.ZIM_RESPONSE_RETRY;
    }

    public static String c(DownloadInfo downloadInfo, com.ss.android.downloadad.api.a.b bVar) {
        File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
        String str = null;
        if (file.exists()) {
            try {
                PackageInfo packageArchiveInfo = k.a().getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), com.ss.android.socialbase.appdownloader.c.a());
                if (packageArchiveInfo != null) {
                    str = packageArchiveInfo.packageName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(str) && !str.equals(downloadInfo.getPackageName())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("real_package_name", str);
                jSONObject.put("input_package_name", downloadInfo.getPackageName());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            com.ss.android.downloadlib.d.a.a().a("embeded_ad", "package_name_error", jSONObject, bVar);
            return str;
        }
        return downloadInfo.getPackageName();
    }

    @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppForeground() {
        Logger.d(a, "onAppForeground()");
        b();
        a(5);
    }

    @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppBackground() {
        Logger.d(a, "onAppBackground()");
        a(6);
    }

    synchronized void b() {
        b bVar = this.c;
        if (bVar != null) {
            bVar.b();
            this.c = null;
        }
    }

    @Override // com.ss.android.downloadad.api.a
    public void a(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.b < com.igexin.push.config.c.l) {
            return;
        }
        d.a().a(new RunnableC0088a(i), this.b > 0 ? 2000L : 8000L);
        this.b = jCurrentTimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ConcurrentHashMap<Long, com.ss.android.downloadad.api.a.b> concurrentHashMap, int i) {
        ArrayList arrayList = new ArrayList();
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (com.ss.android.downloadad.api.a.b bVar : concurrentHashMap.values()) {
            if (bVar.c.get()) {
                if (jCurrentTimeMillis - bVar.H() >= DownloadSetting.obtain(bVar.s()).optInt("start_event_expire_hours", 168) * 60 * 60 * 1000) {
                    arrayList.add(Long.valueOf(bVar.b()));
                }
            } else if (bVar.G() == 1) {
                if (b(bVar) <= 0 && jCurrentTimeMillis - bVar.H() >= DownloadSetting.obtain(bVar.s()).optInt("start_event_expire_hours", 168) * 60 * 60 * 1000) {
                    arrayList.add(Long.valueOf(bVar.b()));
                }
            } else if (bVar.G() == 2) {
                if (!bVar.Y()) {
                    if (m.b(bVar)) {
                        if (bVar.K() == 4) {
                            i = bVar.K();
                        }
                        bVar.j(false);
                        com.ss.android.downloadlib.d.a.a().a(a(bVar, bVar.e(), i), bVar);
                        arrayList.add(Long.valueOf(bVar.b()));
                        com.ss.android.downloadlib.addownload.c.d.a(bVar);
                    } else if (bVar.Y() && bVar.K() == 4 && i == 1 && !m.b(bVar)) {
                        com.ss.android.downloadlib.g.l.a().a(a, "trySendAndRefreshAdEvent", "命中兜底逻辑,尝试对广播监听执行冷启兜底逻辑");
                        h.a().a(bVar);
                    } else if (jCurrentTimeMillis - bVar.H() >= DownloadSetting.obtain(bVar.s()).optInt("finish_event_expire_hours", 168) * 60 * 60 * 1000) {
                        arrayList.add(Long.valueOf(bVar.b()));
                    } else if (TextUtils.isEmpty(bVar.e())) {
                        arrayList.add(Long.valueOf(bVar.b()));
                    }
                }
            } else {
                arrayList.add(Long.valueOf(bVar.b()));
            }
        }
        com.ss.android.downloadlib.addownload.b.f.a().a(arrayList);
    }

    @Override // com.ss.android.socialbase.appdownloader.b.c
    public void a(DownloadInfo downloadInfo, com.ss.android.socialbase.appdownloader.a aVar) {
        JSONObject jSONObjectB;
        if (downloadInfo == null || aVar == null) {
            return;
        }
        JSONArray jSONArrayOptJSONArray = DownloadSetting.obtain(downloadInfo.getId()).optJSONArray("ah_report_config");
        if (aVar.b != 0) {
            downloadInfo.getTempCacheData().remove("intent");
        }
        if (jSONArrayOptJSONArray == null || (jSONObjectB = b(downloadInfo, aVar)) == null) {
            return;
        }
        downloadInfo.getTempCacheData().put("ah_ext_json", jSONObjectB);
    }

    @Override // com.ss.android.socialbase.downloader.depend.IOpenInstallerListener
    public void onOpenInstaller(final DownloadInfo downloadInfo, String str) {
        if (downloadInfo == null) {
            com.ss.android.downloadlib.e.c.a().a("info is null");
        } else if ((DownloadSetting.obtain(downloadInfo).optInt("check_applink_mode") & 2) != 0) {
            final JSONObject jSONObject = (JSONObject) downloadInfo.getTempCacheData().get("ah_ext_json");
            com.ss.android.downloadlib.b.e.a().b(new com.ss.android.downloadlib.b.d() { // from class: com.ss.android.downloadlib.a.4
                @Override // com.ss.android.downloadlib.b.d
                public void a(boolean z) {
                    if (!z) {
                        Intent intent = (Intent) downloadInfo.getTempCacheData().get("intent");
                        if (intent != null) {
                            downloadInfo.getTempCacheData().remove("intent");
                            com.ss.android.socialbase.appdownloader.c.a(k.a(), intent);
                            m.a(jSONObject, "backup", (Object) 1);
                        } else {
                            m.a(jSONObject, "backup", (Object) 2);
                        }
                    }
                    com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo);
                    if (bVarA != null) {
                        com.ss.android.downloadlib.d.a.a().a(z ? "installer_delay_success" : "installer_delay_failed", jSONObject, bVarA);
                    } else {
                        com.ss.android.downloadlib.e.c.a().b("ah nativeModel=null");
                    }
                    if (z) {
                        k.v().a(k.a(), null, null, null, null, 1);
                    }
                }
            });
        }
    }

    private int b(com.ss.android.downloadad.api.a.b bVar) {
        int realStatus;
        double dOptDouble = DownloadSetting.obtain(bVar.s()).optDouble("download_failed_finally_hours", 48.0d);
        if (dOptDouble <= 0.0d) {
            return -1;
        }
        if (System.currentTimeMillis() - bVar.H() < dOptDouble * 60.0d * 60.0d * 1000.0d) {
            return 1;
        }
        if (bVar.d.get()) {
            return 0;
        }
        DownloadInfo downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(bVar.s());
        if (downloadInfo == null || (realStatus = downloadInfo.getRealStatus()) == -3 || realStatus == -4) {
            return -1;
        }
        if (!DownloadStatus.isDownloading(realStatus) && bVar.d.compareAndSet(false, true)) {
            try {
                JSONObject jSONObject = new JSONObject();
                a(jSONObject, downloadInfo);
                jSONObject.putOpt(MonitorConstants.EXTRA_DOWNLOAD_STATUS, Integer.valueOf(realStatus));
                jSONObject.putOpt("fail_status", Integer.valueOf(bVar.E()));
                jSONObject.putOpt("fail_msg", bVar.F());
                jSONObject.put("download_failed_times", bVar.x());
                if (downloadInfo.getTotalBytes() > 0) {
                    jSONObject.put("download_percent", downloadInfo.getCurBytes() / downloadInfo.getTotalBytes());
                }
                jSONObject.put("is_update_download", bVar.V() ? 1 : 2);
                com.ss.android.downloadlib.d.a.a().a(bVar.j(), "download_failed_finally", jSONObject, bVar);
                com.ss.android.downloadlib.addownload.b.i.a().a(bVar);
                return 0;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return 1;
    }

    private JSONObject b(DownloadInfo downloadInfo, com.ss.android.socialbase.appdownloader.a aVar) {
        com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo);
        if (bVarA == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        aVar.a(jSONObject);
        try {
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ID, downloadInfo.getId());
            jSONObject.put("name", downloadInfo.getName());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        com.ss.android.downloadlib.g.f.a(jSONObject, downloadInfo.getId());
        com.ss.android.downloadlib.d.a.a().a("embeded_ad", "ah_result", jSONObject, bVarA);
        return jSONObject;
    }

    /* JADX INFO: renamed from: com.ss.android.downloadlib.a$a, reason: collision with other inner class name */
    private class RunnableC0088a implements Runnable {
        private final int b;

        public RunnableC0088a(int i) {
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                com.ss.android.downloadlib.addownload.b.f.a().b();
                ConcurrentHashMap<Long, com.ss.android.downloadad.api.a.b> concurrentHashMapC = com.ss.android.downloadlib.addownload.b.f.a().c();
                if (concurrentHashMapC == null || concurrentHashMapC.isEmpty()) {
                    return;
                }
                a.this.a(concurrentHashMapC, this.b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private JSONObject a(com.ss.android.downloadad.api.a.b bVar, String str, int i) {
        com.ss.android.socialbase.appdownloader.a aVarA;
        JSONObject jSONObject = new JSONObject();
        try {
            DownloadInfo downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(bVar.s());
            jSONObject.putOpt("scene", Integer.valueOf(i));
            com.ss.android.downloadlib.g.f.a(jSONObject, bVar.s());
            com.ss.android.downloadlib.g.f.a(bVar, jSONObject);
            jSONObject.put("is_update_download", bVar.V() ? 1 : 2);
            jSONObject.put("install_after_back_app", bVar.ab() ? 1 : 2);
            jSONObject.putOpt("clean_space_install_params", bVar.R() ? "1" : "2");
            if (downloadInfo != null) {
                a(jSONObject, downloadInfo);
                try {
                    jSONObject.put("uninstall_resume_count", downloadInfo.getUninstallResumeCount());
                    if (bVar.C() > 0) {
                        long jCurrentTimeMillis = System.currentTimeMillis() - bVar.C();
                        jSONObject.put("install_time", jCurrentTimeMillis);
                        if (jCurrentTimeMillis > DownloadSetting.obtain(downloadInfo.getId()).optLong("check_install_finish_expired_duration", 86400000L)) {
                            jSONObject.put("install_expired", 1);
                        } else {
                            jSONObject.put("install_expired", 0);
                        }
                    }
                } catch (Throwable unused) {
                }
                String string = DownloadUtils.getString(downloadInfo.getTempCacheData().get("ah_attempt"), null);
                if (!TextUtils.isEmpty(string) && (aVarA = com.ss.android.socialbase.appdownloader.a.a(string)) != null) {
                    aVarA.a(jSONObject);
                }
            }
            int iA = a(bVar, downloadInfo, str, jSONObject);
            jSONObject.put("fail_status", iA);
            if (iA == 3000) {
                jSONObject.put("hijack", 2);
            } else if (iA == 3001) {
                jSONObject.put("hijack", 0);
            } else {
                jSONObject.put("hijack", 1);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public void a(DownloadInfo downloadInfo, long j, long j2, long j3, long j4, long j5, boolean z) {
        com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo);
        if (bVarA == null) {
            com.ss.android.downloadlib.e.c.a().a("trySendClearSpaceEvent nativeModel null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("space_before", Double.valueOf(j / 1048576.0d));
            jSONObject.putOpt("space_cleaned", Double.valueOf((j2 - j) / 1048576.0d));
            jSONObject.putOpt("clean_up_time_cost", Long.valueOf(j4));
            jSONObject.putOpt("is_download_restarted", Integer.valueOf(z ? 1 : 0));
            jSONObject.putOpt("byte_required", Long.valueOf(j3));
            jSONObject.putOpt("byte_required_after", Double.valueOf((j3 - j2) / 1048576.0d));
            jSONObject.putOpt("clear_sleep_time", Long.valueOf(j5));
            com.ss.android.downloadlib.g.f.c(downloadInfo, jSONObject);
            com.ss.android.downloadlib.d.a.a().a("cleanup", jSONObject, bVarA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
