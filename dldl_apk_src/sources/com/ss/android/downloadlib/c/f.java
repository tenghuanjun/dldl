package com.ss.android.downloadlib.c;

import android.content.Context;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class f implements com.ss.android.socialbase.appdownloader.c.d {
    private Context a;

    public f(Context context) {
        this.a = context.getApplicationContext();
    }

    @Override // com.ss.android.socialbase.appdownloader.c.d
    public void a(Context context, String str) {
        com.ss.android.downloadlib.a.a().a(str);
    }

    @Override // com.ss.android.socialbase.appdownloader.c.d
    public void a(int i, int i2, String str, int i3, long j) {
        DownloadInfo downloadInfo;
        com.ss.android.downloadad.api.a.b bVarA;
        Context context = this.a;
        if (context == null || (downloadInfo = Downloader.getInstance(context).getDownloadInfo(i)) == null || downloadInfo.getStatus() == 0 || (bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo)) == null) {
            return;
        }
        if (i2 == 1) {
            com.ss.android.downloadlib.a.a(downloadInfo, bVarA);
            if ("application/vnd.android.package-archive".equals(downloadInfo.getMimeType())) {
                com.ss.android.downloadlib.addownload.a.a().a(downloadInfo, bVarA.b(), bVarA.l(), bVarA.e(), downloadInfo.getTitle(), bVarA.d(), downloadInfo.getTargetFilePath());
                return;
            }
            return;
        }
        if (i2 == 3) {
            com.ss.android.downloadlib.d.a.a().a("download_notification", "download_notification_install", com.ss.android.downloadlib.a.b(new JSONObject(), downloadInfo), bVarA);
            return;
        }
        if (i2 == 5) {
            com.ss.android.downloadlib.d.a.a().a("download_notification", "download_notification_pause", bVarA);
        } else if (i2 == 6) {
            com.ss.android.downloadlib.d.a.a().a("download_notification", "download_notification_continue", bVarA);
        } else {
            if (i2 != 7) {
                return;
            }
            com.ss.android.downloadlib.d.a.a().a("download_notification", "download_notification_click", bVarA);
        }
    }

    @Override // com.ss.android.socialbase.appdownloader.c.d
    public boolean a(int i, boolean z) {
        if (k.p() != null) {
            return k.p().a(z);
        }
        return false;
    }

    @Override // com.ss.android.socialbase.appdownloader.c.d
    public void a(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return;
        }
        com.ss.android.downloadlib.g.a().a(downloadInfo);
        if (DownloadSetting.obtain(downloadInfo.getId()).optInt("report_download_cancel", 1) == 1) {
            com.ss.android.downloadlib.d.a.a().a(downloadInfo, new BaseException(1012, ""));
        } else {
            com.ss.android.downloadlib.d.a.a().b(downloadInfo, new BaseException(1012, ""));
        }
    }

    @Override // com.ss.android.socialbase.appdownloader.c.d
    public void a(int i, int i2, String str, String str2, String str3) {
        DownloadInfo downloadInfo;
        Context context = this.a;
        if (context == null || (downloadInfo = Downloader.getInstance(context).getDownloadInfo(i)) == null || downloadInfo.getStatus() != -3) {
            return;
        }
        downloadInfo.setPackageName(str2);
        com.ss.android.downloadlib.addownload.b.a().a(this.a, downloadInfo);
    }

    @Override // com.ss.android.socialbase.appdownloader.c.d
    public boolean a() {
        return com.ss.android.downloadlib.addownload.b.a().b();
    }
}
