package com.ss.android.downloadlib.d;

import android.os.Build;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.model.c;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.addownload.b.e;
import com.ss.android.downloadlib.addownload.b.f;
import com.ss.android.downloadlib.addownload.b.i;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.appdownloader.e.d;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a {

    /* JADX INFO: renamed from: com.ss.android.downloadlib.d.a$a, reason: collision with other inner class name */
    private static class C0097a {
        private static a a = new a();
    }

    public static a a() {
        return C0097a.a;
    }

    private a() {
    }

    public void a(long j, int i) {
        e eVarE = f.a().e(j);
        if (eVarE.x()) {
            com.ss.android.downloadlib.e.c.a().a("sendClickEvent ModelBox notValid");
            return;
        }
        if (eVarE.c.isEnableClickEvent()) {
            int i2 = 1;
            DownloadEventConfig downloadEventConfig = eVarE.c;
            String clickItemTag = i == 1 ? downloadEventConfig.getClickItemTag() : downloadEventConfig.getClickButtonTag();
            String strA = m.a(eVarE.c.getClickLabel(), "click");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("download_click_type", Integer.valueOf(i));
                jSONObject.putOpt("permission_notification", Integer.valueOf(d.a() ? 1 : 2));
                if (!DownloadUtils.isNetworkConnected(k.a())) {
                    i2 = 2;
                }
                jSONObject.putOpt("network_available", Integer.valueOf(i2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            a(clickItemTag, strA, jSONObject, eVarE);
            if (!"click".equals(strA) || eVarE.b == null) {
                return;
            }
            c.a().a(j, eVarE.b.getLogExtra());
        }
    }

    public void a(long j, int i, DownloadInfo downloadInfo) {
        e eVarE = f.a().e(j);
        if (eVarE.x()) {
            com.ss.android.downloadlib.e.c.a().a("sendEvent ModelBox notValid");
            return;
        }
        String strA = null;
        JSONObject jSONObject = new JSONObject();
        m.a(jSONObject, "download_scene", Integer.valueOf(eVarE.t()));
        if (i == 1) {
            strA = m.a(eVarE.c.getStorageDenyLabel(), "storage_deny");
        } else if (i == 2) {
            strA = m.a(eVarE.c.getClickStartLabel(), "click_start");
            com.ss.android.downloadlib.g.f.a(downloadInfo, jSONObject);
        } else if (i == 3) {
            strA = m.a(eVarE.c.getClickPauseLabel(), "click_pause");
            com.ss.android.downloadlib.g.f.b(downloadInfo, jSONObject);
        } else if (i == 4) {
            strA = m.a(eVarE.c.getClickContinueLabel(), "click_continue");
            com.ss.android.downloadlib.g.f.c(downloadInfo, jSONObject);
        } else if (i == 5) {
            if (downloadInfo != null) {
                try {
                    com.ss.android.downloadlib.g.f.a(jSONObject, downloadInfo.getId());
                    com.ss.android.downloadlib.a.b(jSONObject, downloadInfo);
                } catch (Throwable unused) {
                }
            }
            strA = m.a(eVarE.c.getClickInstallLabel(), "click_install");
        }
        a(null, strA, jSONObject, 0L, 1, eVarE);
    }

    public void b(long j, int i) {
        a(j, i, (DownloadInfo) null);
    }

    public void a(String str, int i, e eVar) {
        a(null, str, null, i, 0, eVar);
    }

    public void a(long j, boolean z, int i) {
        e eVarE = f.a().e(j);
        if (eVarE.x()) {
            com.ss.android.downloadlib.e.c.a().a("sendQuickAppEvent ModelBox notValid");
            return;
        }
        if (eVarE.b.getQuickAppModel() == null) {
            return;
        }
        if (eVarE.b instanceof AdDownloadModel) {
            ((AdDownloadModel) eVarE.b).setFunnelType(3);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("download_click_type", Integer.valueOf(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        b(z ? "deeplink_quickapp_success" : "deeplink_quickapp_failed", jSONObject, eVarE);
    }

    public void a(long j, BaseException baseException) {
        e eVarE = f.a().e(j);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(MonitorConstants.EXTRA_DOWNLOAD_TIME, 0);
            if (baseException != null) {
                jSONObject.putOpt("fail_status", Integer.valueOf(baseException.getErrorCode()));
                jSONObject.putOpt("fail_msg", baseException.getErrorMessage());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        b("download_failed", jSONObject, eVarE);
    }

    public void a(DownloadInfo downloadInfo) {
        com.ss.android.downloadad.api.a.b bVarA = f.a().a(downloadInfo);
        if (bVarA == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            com.ss.android.downloadlib.g.f.c(downloadInfo, jSONObject);
            bVarA.a(System.currentTimeMillis());
            a(bVarA.j(), "download_resume", jSONObject, bVarA);
            i.a().a(bVarA);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(JSONObject jSONObject, com.ss.android.downloadad.api.a.b bVar) {
        a(bVar.j(), "install_finish", jSONObject, bVar);
    }

    public void a(DownloadInfo downloadInfo, BaseException baseException) {
        com.ss.android.downloadad.api.a.b bVarA;
        if (downloadInfo == null || (bVarA = f.a().a(downloadInfo)) == null || bVarA.c.get()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            com.ss.android.downloadlib.a.a(jSONObject, downloadInfo);
            jSONObject.putOpt("fail_status", Integer.valueOf(bVarA.E()));
            jSONObject.putOpt("fail_msg", bVarA.F());
            jSONObject.put("download_failed_times", bVarA.x());
            if (downloadInfo.getTotalBytes() > 0) {
                jSONObject.put("download_percent", downloadInfo.getCurBytes() / downloadInfo.getTotalBytes());
            }
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_STATUS, downloadInfo.getRealStatus());
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (bVarA.H() > 0) {
                jSONObject.put("time_from_start_download", jCurrentTimeMillis - bVarA.H());
            }
            if (bVarA.B() > 0) {
                jSONObject.put("time_from_download_resume", jCurrentTimeMillis - bVarA.B());
            }
            int i = 1;
            jSONObject.put("is_update_download", bVarA.V() ? 1 : 2);
            jSONObject.put("can_show_notification", d.a() ? 1 : 2);
            if (!bVarA.d.get()) {
                i = 2;
            }
            jSONObject.put("has_send_download_failed_finally", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(bVarA.j(), "download_cancel", jSONObject, bVarA);
    }

    public void b(DownloadInfo downloadInfo, BaseException baseException) {
        if (downloadInfo == null) {
            return;
        }
        com.ss.android.downloadad.api.a.b bVarA = f.a().a(downloadInfo);
        if (bVarA == null) {
            com.ss.android.downloadlib.e.c.a().a("sendDownloadFailedEvent nativeModel null");
            return;
        }
        if (bVarA.c.get()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            com.ss.android.downloadlib.g.f.c(downloadInfo, jSONObject);
            com.ss.android.downloadlib.a.a(jSONObject, downloadInfo);
            if (baseException != null) {
                jSONObject.putOpt("fail_status", Integer.valueOf(baseException.getErrorCode()));
                jSONObject.putOpt("fail_msg", baseException.getErrorMessage());
                bVarA.d(baseException.getErrorCode());
                bVarA.a(baseException.getErrorMessage());
            }
            bVarA.y();
            jSONObject.put("download_failed_times", bVarA.x());
            if (downloadInfo.getTotalBytes() > 0) {
                jSONObject.put("download_percent", downloadInfo.getCurBytes() / downloadInfo.getTotalBytes());
            }
            int i = 1;
            jSONObject.put("has_send_download_failed_finally", bVarA.d.get() ? 1 : 2);
            com.ss.android.downloadlib.g.f.a(bVarA, jSONObject);
            if (!bVarA.V()) {
                i = 2;
            }
            jSONObject.put("is_update_download", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(bVarA.j(), "download_failed", jSONObject, bVarA);
        i.a().a(bVarA);
    }

    public void a(String str, com.ss.android.downloadad.api.a.a aVar) {
        a(str, (JSONObject) null, aVar);
    }

    public void a(String str, JSONObject jSONObject, long j) {
        com.ss.android.downloadad.api.a.a aVarD = f.a().d(j);
        if (aVarD != null) {
            a(str, jSONObject, aVarD);
            return;
        }
        e eVarE = f.a().e(j);
        if (eVarE.x()) {
            com.ss.android.downloadlib.e.c.a().a("sendUnityEvent ModelBox notValid");
        } else {
            a(str, jSONObject, eVarE);
        }
    }

    public void a(String str, JSONObject jSONObject, com.ss.android.downloadad.api.a.a aVar) {
        JSONObject jSONObject2 = new JSONObject();
        m.a(jSONObject2, "unity_label", str);
        a("embeded_ad", "ttdownloader_unity", m.a(jSONObject, jSONObject2), aVar);
    }

    public void a(String str, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        b(str, new e(downloadModel.getId(), downloadModel, downloadEventConfig, downloadController));
    }

    public void a(String str, long j) {
        com.ss.android.downloadad.api.a.b bVarD = f.a().d(j);
        if (bVarD != null) {
            b(str, bVarD);
        } else {
            b(str, f.a().e(j));
        }
    }

    public void b(String str, com.ss.android.downloadad.api.a.a aVar) {
        a((String) null, str, aVar);
    }

    public void b(String str, JSONObject jSONObject, com.ss.android.downloadad.api.a.a aVar) {
        a((String) null, str, jSONObject, aVar);
    }

    public void a(String str, String str2, com.ss.android.downloadad.api.a.a aVar) {
        a(str, str2, (JSONObject) null, aVar);
    }

    public void a(String str, String str2, JSONObject jSONObject, com.ss.android.downloadad.api.a.a aVar) {
        a(str, str2, jSONObject, 0L, 0, aVar);
    }

    private void a(String str, String str2, JSONObject jSONObject, long j, int i, com.ss.android.downloadad.api.a.a aVar) {
        if (aVar == null) {
            com.ss.android.downloadlib.e.c.a().a("onEvent data null");
            return;
        }
        if ((aVar instanceof e) && ((e) aVar).x()) {
            com.ss.android.downloadlib.e.c.a().a("onEvent ModelBox notValid");
            return;
        }
        try {
            c.a aVarC = new c.a().a(m.a(str, aVar.j(), "embeded_ad")).b(str2).b(aVar.c()).a(aVar.b()).c(aVar.d());
            if (j <= 0) {
                j = aVar.l();
            }
            c.a aVarA = aVarC.b(j).d(aVar.i()).a(aVar.n()).a(m.a(a(aVar), jSONObject)).b(aVar.k()).a(aVar.o());
            if (i <= 0) {
                i = 2;
            }
            a(aVarA.a(i).a(aVar.m()).a());
        } catch (Exception e) {
            com.ss.android.downloadlib.e.c.a().a(e, "onEvent");
        }
    }

    private JSONObject a(com.ss.android.downloadad.api.a.a aVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            m.a(aVar.g(), jSONObject);
            m.a(aVar.p(), jSONObject);
            jSONObject.putOpt("download_url", aVar.a());
            jSONObject.putOpt("package_name", aVar.e());
            jSONObject.putOpt("android_int", Integer.valueOf(Build.VERSION.SDK_INT));
            jSONObject.putOpt("rom_name", com.ss.android.socialbase.appdownloader.f.e.h());
            jSONObject.putOpt("rom_version", com.ss.android.socialbase.appdownloader.f.e.i());
            jSONObject.putOpt("ttdownloader", 1);
            jSONObject.putOpt("funnel_type", Integer.valueOf(aVar.h()));
            if (aVar.h() == 2) {
                com.ss.android.downloadlib.g.f.b(jSONObject, aVar);
            }
            if (com.ss.android.socialbase.appdownloader.f.e.p()) {
                com.ss.android.downloadlib.g.f.a(jSONObject);
            }
        } catch (Exception e) {
            k.u().a(e, "getBaseJson");
        }
        return jSONObject;
    }

    private void a(com.ss.android.download.api.model.c cVar) {
        if (k.b() == null) {
            return;
        }
        if (cVar.m()) {
            k.b().a(cVar);
        } else {
            k.b().b(cVar);
        }
    }
}
