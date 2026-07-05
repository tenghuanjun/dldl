package com.ss.android.downloadlib.g;

import com.ss.android.downloadad.api.constant.AdBaseConstants;
import com.ss.android.socialbase.appdownloader.g;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class f {
    public static void a(DownloadInfo downloadInfo, JSONObject jSONObject) {
        try {
            c(downloadInfo, jSONObject);
            com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo);
            if (bVarA == null) {
                return;
            }
            jSONObject.put("is_update_download", bVarA.V() ? 1 : 2);
            a(bVarA, jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(DownloadInfo downloadInfo, JSONObject jSONObject) {
        com.ss.android.downloadad.api.a.b bVarA;
        if (jSONObject == null || (bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo)) == null) {
            return;
        }
        try {
            c(downloadInfo, jSONObject);
            jSONObject.putOpt("time_after_click", Long.valueOf(System.currentTimeMillis() - bVarA.T()));
            jSONObject.putOpt("click_download_size", Long.valueOf(bVarA.U()));
            jSONObject.putOpt("download_length", Long.valueOf(downloadInfo.getCurBytes()));
            jSONObject.putOpt("download_apk_size", Long.valueOf(downloadInfo.getTotalBytes()));
            bVarA.A();
            com.ss.android.downloadlib.addownload.b.i.a().a(bVarA);
            jSONObject.put("click_pause_times", bVarA.z());
            long totalBytes = downloadInfo.getTotalBytes();
            long curBytes = downloadInfo.getCurBytes();
            jSONObject.put("download_percent", (curBytes < 0 || totalBytes <= 0) ? 0.0d : curBytes / totalBytes);
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_STATUS, downloadInfo.getRealStatus());
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jH = bVarA.H();
            if (jH > 0) {
                jSONObject.put("time_from_start_download", jCurrentTimeMillis - jH);
            }
            long jB = bVarA.B();
            if (jB > 0) {
                jSONObject.put("time_from_download_resume", jCurrentTimeMillis - jB);
            }
            jSONObject.putOpt("fail_status", Integer.valueOf(bVarA.E()));
            jSONObject.putOpt("fail_msg", bVarA.F());
            jSONObject.put("download_failed_times", bVarA.x());
            jSONObject.put("can_show_notification", com.ss.android.socialbase.appdownloader.e.d.a() ? 1 : 2);
            jSONObject.put("first_speed_time", downloadInfo.getFirstSpeedTime());
            jSONObject.put("all_connect_time", downloadInfo.getAllConnectTime());
            jSONObject.put("download_prepare_time", downloadInfo.getDownloadPrepareTime());
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_TIME, downloadInfo.getRealDownloadTime() + downloadInfo.getAllConnectTime() + downloadInfo.getDownloadPrepareTime());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void c(DownloadInfo downloadInfo, JSONObject jSONObject) {
        if (downloadInfo != null) {
            try {
                jSONObject.putOpt(MonitorConstants.EXTRA_DOWNLOAD_TOTAL_BYTES, Long.valueOf(downloadInfo.getTotalBytes()));
                jSONObject.putOpt(MonitorConstants.EXTRA_DOWNLOAD_CUR_BYTES, Long.valueOf(downloadInfo.getCurBytes()));
                jSONObject.putOpt(MonitorConstants.EXTRA_DOWNLOAD_CHUNK_COUNT, Integer.valueOf(downloadInfo.getChunkCount()));
                jSONObject.putOpt("app_name", downloadInfo.getTitle());
                jSONObject.putOpt(MonitorConstants.EXTRA_DOWNLOAD_NETWORK_QUALITY, downloadInfo.getNetworkQuality());
                jSONObject.putOpt(MonitorConstants.EXTRA_DOWNLOAD_SAVE_PATH, downloadInfo.getSavePath());
                jSONObject.putOpt("file_name", downloadInfo.getName());
                jSONObject.putOpt(MonitorConstants.EXTRA_DOWNLOAD_STATUS, Integer.valueOf(downloadInfo.getRealStatus()));
                com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo.getId());
                if (bVarA != null) {
                    jSONObject.putOpt("click_download_time", Long.valueOf(bVarA.T()));
                    jSONObject.putOpt("click_download_size", Long.valueOf(bVarA.U()));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        int i = 1;
        jSONObject.putOpt("permission_notification", Integer.valueOf(com.ss.android.socialbase.appdownloader.e.d.a() ? 1 : 2));
        jSONObject.putOpt("network_available", Integer.valueOf(DownloadUtils.isNetworkConnected(com.ss.android.downloadlib.addownload.k.a()) ? 1 : 2));
        if (!DownloadUtils.isWifi(com.ss.android.downloadlib.addownload.k.a())) {
            i = 2;
        }
        jSONObject.putOpt("network_is_wifi", Integer.valueOf(i));
    }

    public static void a(com.ss.android.downloadad.api.a.b bVar, JSONObject jSONObject) {
        if (jSONObject == null || bVar == null) {
            return;
        }
        try {
            jSONObject.put("is_patch_apply_handled", bVar.X() ? 1 : 0);
            jSONObject.put("origin_mime_type", bVar.W());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, int i) {
        if (jSONObject == null) {
            return;
        }
        JSONArray jSONArrayOptJSONArray = DownloadSetting.obtain(i).optJSONArray("ah_report_config");
        if (jSONArrayOptJSONArray != null) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                try {
                    String string = jSONArrayOptJSONArray.getString(i2);
                    g.a aVarA = com.ss.android.socialbase.appdownloader.f.a.a(string);
                    if (aVarA != null) {
                        jSONObject.put(string.replaceAll("\\.", "_"), aVarA.f() + "_" + aVarA.g());
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        try {
            jSONObject.put("is_unknown_source_enabled", com.ss.android.socialbase.appdownloader.b.a(DownloadComponentManager.getAppContext()) ? 1 : 2);
        } catch (Throwable unused) {
        }
    }

    public static JSONObject a(JSONObject jSONObject, com.ss.android.downloadad.api.a.a aVar) {
        m.a(jSONObject, AdBaseConstants.MARKET_OPEN_INTENT_OPEN_URL, m.a(aVar.f(), "open_url_not_exist"));
        return jSONObject;
    }

    public static JSONObject b(JSONObject jSONObject, com.ss.android.downloadad.api.a.a aVar) {
        m.a(jSONObject, com.ss.android.socialbase.appdownloader.f.e.j().replaceAll("\\.", "_") + " versionCode", Integer.valueOf(m.b(com.ss.android.downloadlib.addownload.k.a(), com.ss.android.socialbase.appdownloader.f.e.j())));
        m.a(jSONObject, com.ss.android.socialbase.appdownloader.f.e.j().replaceAll("\\.", "_") + " versionName", m.c(com.ss.android.downloadlib.addownload.k.a(), com.ss.android.socialbase.appdownloader.f.e.j()));
        return jSONObject;
    }

    public static void a(JSONObject jSONObject) {
        try {
            jSONObject.putOpt("harmony_api_version", com.ss.android.socialbase.appdownloader.f.d.a());
            jSONObject.putOpt("harmony_release_type", com.ss.android.socialbase.appdownloader.f.d.c());
            jSONObject.putOpt("harmony_build_version", com.ss.android.socialbase.appdownloader.f.d.d());
            int i = 1;
            jSONObject.putOpt("pure_mode", Integer.valueOf(com.ss.android.socialbase.appdownloader.f.d.a(com.ss.android.downloadlib.addownload.k.a()) ? 1 : 2));
            jSONObject.putOpt("pure_mode_enable", Integer.valueOf(com.ss.android.socialbase.appdownloader.f.d.e() ? 1 : 2));
            jSONObject.putOpt("harmony_version", com.ss.android.socialbase.appdownloader.f.d.b());
            jSONObject.putOpt("pure_enhanced_mode", Integer.valueOf(com.ss.android.socialbase.appdownloader.f.d.b(com.ss.android.downloadlib.addownload.k.a()) ? 1 : 2));
            if (!com.ss.android.socialbase.appdownloader.f.d.f()) {
                i = 2;
            }
            jSONObject.putOpt("pure_enhanced_mode_enable", Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
