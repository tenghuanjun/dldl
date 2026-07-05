package com.ss.android.downloadlib.addownload.d;

import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class j implements i {
    @Override // com.ss.android.downloadlib.addownload.d.i
    public boolean a(com.ss.android.downloadad.api.a.b bVar, int i, h hVar) {
        if (bVar == null || !b(bVar.s())) {
            return false;
        }
        if (System.currentTimeMillis() - bVar.T() > a(bVar.s())) {
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("pause_optimise_type", "mistake_click");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().a("pause_optimise", jSONObject, bVar);
        return true;
    }

    private long a(int i) {
        return DownloadSetting.obtain(i).optInt("pause_optimise_mistake_click_interval", 300);
    }

    private boolean b(int i) {
        return DownloadSetting.obtain(i).optInt("pause_optimise_mistake_click_interval_switch", 0) == 1;
    }
}
