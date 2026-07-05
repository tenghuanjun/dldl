package com.ss.android.downloadlib.addownload;

import android.os.Looper;
import android.os.Message;
import com.ss.android.downloadlib.g.n;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.android.socialbase.downloader.utils.SystemUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a implements n.a {
    private static final String a = a.class.getSimpleName();
    private static a b;
    private com.ss.android.downloadlib.g.n c = new com.ss.android.downloadlib.g.n(Looper.getMainLooper(), this);
    private long d;

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    private a() {
    }

    public void a(DownloadInfo downloadInfo, long j, long j2, String str, String str2, String str3, String str4) {
        com.ss.android.downloadlib.addownload.b.a aVar = new com.ss.android.downloadlib.addownload.b.a(downloadInfo.getId(), j, j2, str, str2, str3, str4);
        if (DownloadSetting.obtain(downloadInfo.getId()).optInt("back_miui_silent_install", 1) == 0 && ((com.ss.android.socialbase.appdownloader.f.e.m() || com.ss.android.socialbase.appdownloader.f.e.n()) && SystemUtils.checkServiceExists(k.a(), "com.miui.securitycore", "com.miui.enterprise.service.EntInstallService"))) {
            if (DownloadUtils.getBoolean(downloadInfo.getTempCacheData().get("extra_silent_install_succeed"), false)) {
                Message messageObtainMessage = this.c.obtainMessage(200, aVar);
                messageObtainMessage.arg1 = 2;
                this.c.sendMessageDelayed(messageObtainMessage, r1.optInt("check_silent_install_interval", 60000));
                return;
            }
            com.ss.android.downloadad.api.a.b bVarD = com.ss.android.downloadlib.addownload.b.f.a().d(aVar.b);
            JSONObject jSONObject = new JSONObject();
            int i = -1;
            try {
                jSONObject.put("ttdownloader_type", "miui_silent_install");
                jSONObject.put("ttdownloader_message", "miui_silent_install_failed: has not started service");
                i = 5;
            } catch (Exception unused) {
            }
            k.g().a(null, new BaseException(i, jSONObject.toString()), i);
            com.ss.android.downloadlib.d.a.a().a("embeded_ad", "ah_result", jSONObject, bVarD);
        }
        if (com.ss.android.downloadlib.g.e.c()) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.d;
            long jD = com.ss.android.downloadlib.g.e.d();
            if (jCurrentTimeMillis < com.ss.android.downloadlib.g.e.e()) {
                long jE = com.ss.android.downloadlib.g.e.e() - jCurrentTimeMillis;
                jD += jE;
                this.d = System.currentTimeMillis() + jE;
            } else {
                this.d = System.currentTimeMillis();
            }
            com.ss.android.downloadlib.g.n nVar = this.c;
            nVar.sendMessageDelayed(nVar.obtainMessage(200, aVar), jD);
        }
    }

    private void a(com.ss.android.downloadlib.addownload.b.a aVar, int i) {
        if (k.m() == null || k.m().a() || aVar == null) {
            return;
        }
        if (2 == i) {
            com.ss.android.downloadad.api.a.b bVarD = com.ss.android.downloadlib.addownload.b.f.a().d(aVar.b);
            JSONObject jSONObject = new JSONObject();
            int i2 = -1;
            try {
                jSONObject.put("ttdownloader_type", "miui_silent_install");
                if (com.ss.android.downloadlib.g.m.e(k.a(), aVar.d)) {
                    jSONObject.put("ttdownloader_message", "miui_silent_install_succeed");
                    i2 = 4;
                } else {
                    jSONObject.put("ttdownloader_message", "miui_silent_install_failed: has started service");
                    i2 = 5;
                }
            } catch (Exception unused) {
            }
            k.g().a(null, new BaseException(i2, jSONObject.toString()), i2);
            com.ss.android.downloadlib.d.a.a().a("embeded_ad", "ah_result", jSONObject, bVarD);
        }
        if (com.ss.android.downloadlib.g.m.e(k.a(), aVar.d)) {
            com.ss.android.downloadlib.d.a.a().a("delayinstall_installed", aVar.b);
            return;
        }
        if (!com.ss.android.downloadlib.g.m.a(aVar.g)) {
            com.ss.android.downloadlib.d.a.a().a("delayinstall_file_lost", aVar.b);
        } else if (com.ss.android.downloadlib.addownload.a.a.a().a(aVar.d)) {
            com.ss.android.downloadlib.d.a.a().a("delayinstall_conflict_with_back_dialog", aVar.b);
        } else {
            com.ss.android.downloadlib.d.a.a().a("delayinstall_install_start", aVar.b);
            com.ss.android.socialbase.appdownloader.d.a(k.a(), (int) aVar.a);
        }
    }

    @Override // com.ss.android.downloadlib.g.n.a
    public void a(Message message) {
        if (message.what != 200) {
            return;
        }
        a((com.ss.android.downloadlib.addownload.b.a) message.obj, message.arg1);
    }
}
