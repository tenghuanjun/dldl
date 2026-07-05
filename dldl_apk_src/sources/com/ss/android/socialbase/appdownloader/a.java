package com.ss.android.socialbase.appdownloader;

import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a {
    public String a;
    public int b = -1;
    public String c;
    public String d;
    public String e;

    public String a() {
        return b().toString();
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject);
        return jSONObject;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            jSONObject.put("ah_plan_type", this.a);
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, String.valueOf(this.b));
            jSONObject.put("error_msg", this.c);
            jSONObject.put("real_device_plan", this.d);
            jSONObject.put(DownloadSettingKeys.AhPlans.KEY_AH_DEVICE_PLANS, this.e);
        } catch (Throwable unused) {
        }
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        a aVar = new a();
        try {
            JSONObject jSONObject = new JSONObject(str);
            aVar.e = jSONObject.optString(DownloadSettingKeys.AhPlans.KEY_AH_DEVICE_PLANS, null);
            aVar.d = jSONObject.optString("real_device_plan", null);
            aVar.c = jSONObject.optString("error_msg", null);
            aVar.a = jSONObject.optString("ah_plan_type", null);
            String strOptString = jSONObject.optString(MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE);
            if (TextUtils.isEmpty(strOptString)) {
                aVar.b = -1;
            } else {
                aVar.b = Integer.parseInt(strOptString);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return aVar;
    }
}
