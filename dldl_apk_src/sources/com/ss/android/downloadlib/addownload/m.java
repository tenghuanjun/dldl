package com.ss.android.downloadlib.addownload;

import com.ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class m implements IReserveWifiStatusListener {
    @Override // com.ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener
    public void onStatusChanged(DownloadInfo downloadInfo, int i, int i2) {
        com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("reserve_wifi_source", Integer.valueOf(i2));
            jSONObject.putOpt("reserve_wifi_status", Integer.valueOf(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().a("pause_reserve_wifi", jSONObject, bVarA);
    }
}
