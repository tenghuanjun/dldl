package com.ss.android.downloadlib.addownload.c;

import com.ss.android.downloadlib.g.k;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a implements IDownloadDiskSpaceHandler {
    private int a;

    public void a(int i) {
        this.a = i;
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler
    public boolean cleanUpDisk(long j, long j2, IDownloadDiskSpaceCallback iDownloadDiskSpaceCallback) throws Throwable {
        long j3;
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(this.a);
        if (!a(downloadSettingObtain)) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        d.a().c();
        long jB = m.b(0L);
        a();
        long jB2 = m.b(0L);
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        if (jB2 < j2) {
            long jB3 = b(downloadSettingObtain);
            if (jB3 > 0) {
                jB2 = m.b(0L);
            }
            j3 = jB3;
        } else {
            j3 = 0;
        }
        k.b("AppDownloadDiskSpaceHandler", "cleanUpDisk, byteRequired = " + j2 + ", byteAvailableAfter = " + jB2 + ", cleaned = " + (jB2 - jB), null);
        long j4 = jB2;
        a(jB, jB2, j2, jCurrentTimeMillis2, j3);
        if (j4 < j2) {
            return false;
        }
        if (iDownloadDiskSpaceCallback == null) {
            return true;
        }
        iDownloadDiskSpaceCallback.onDiskCleaned();
        return true;
    }

    private boolean a(DownloadSetting downloadSetting) {
        if (downloadSetting.optInt("clear_space_use_disk_handler", 0) != 1) {
            return false;
        }
        return System.currentTimeMillis() - d.a().b() >= downloadSetting.optLong("clear_space_min_time_interval", 600000L);
    }

    private void a() throws Throwable {
        com.ss.android.download.api.config.e eVarQ = com.ss.android.downloadlib.addownload.k.q();
        if (eVarQ != null) {
            eVarQ.a();
        }
        c.a();
        c.b();
    }

    private long b(DownloadSetting downloadSetting) {
        long jOptLong = downloadSetting.optLong("clear_space_sleep_time", 0L);
        if (jOptLong <= 0) {
            return 0L;
        }
        if (jOptLong > 5000) {
            jOptLong = 5000;
        }
        k.b("AppDownloadDiskSpaceHandler", "waiting for space clear, sleepTime = " + jOptLong, null);
        try {
            Thread.sleep(jOptLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        k.b("AppDownloadDiskSpaceHandler", "waiting end!", null);
        return jOptLong;
    }

    private void a(long j, long j2, long j3, long j4, long j5) {
        DownloadInfo downloadInfo = Downloader.getInstance(com.ss.android.downloadlib.addownload.k.a()).getDownloadInfo(this.a);
        if (downloadInfo == null) {
            return;
        }
        try {
            com.ss.android.downloadlib.a.a().a(downloadInfo, j, j2, j3, j4, j5, j2 > j3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
