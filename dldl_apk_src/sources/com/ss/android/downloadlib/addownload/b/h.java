package com.ss.android.downloadlib.addownload.b;

import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class h {
    private static volatile h a;

    private h() {
    }

    public static h a() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new h();
                }
            }
        }
        return a;
    }

    public void a(int i, int i2, com.ss.android.downloadad.api.a.b bVar) {
        if (bVar == null) {
            return;
        }
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(bVar.s());
        if (downloadSettingObtain.optInt("report_api_hijack", 0) == 0) {
            return;
        }
        int i3 = i2 - i;
        if (i <= 0 || i3 <= downloadSettingObtain.optInt("check_api_hijack_version_code_diff", TTAdConstant.SHOW_POLL_TIME_SPLASH_DEFAULT)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version_code_diff", i3);
            jSONObject.put("installed_version_code", i2);
            jSONObject.put("hijack_type", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().b("api_hijack", jSONObject, bVar);
    }
}
