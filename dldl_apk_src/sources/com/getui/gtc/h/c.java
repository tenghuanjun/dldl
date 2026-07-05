package com.getui.gtc.h;

import android.text.TextUtils;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.http.MediaType;
import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.RequestBody;
import com.getui.gtc.base.http.crypt.PtRASCryptoInterceptor;
import com.getui.gtc.base.util.ScheduleQueue;
import com.getui.gtc.dim.DimManager;
import com.getui.gtc.dim.DimRequest;
import com.getui.gtc.i.d.a;
import com.getui.gtc.server.ServerManager;
import com.parameters.performfeatureconfig.PerformFeatureKey;
import java.io.IOException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c {

    public interface a {
        void a(String str);
    }

    static String a(String str) throws Exception {
        while (true) {
            String server = ServerManager.getServer("gtc.as");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("version", "1.0");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject.put(com.alipay.sdk.packet.e.k, jSONObject2);
                if (!TextUtils.isEmpty(str)) {
                    jSONObject2.put("gtcid", str);
                }
                com.getui.gtc.i.d.a unused = a.C0048a.a;
                String str2 = (String) DimManager.getInstance().get(new DimRequest.Builder().key("dim-2-1-5-1").useExpiredCacheForReserve(true).build());
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject2.put(PerformFeatureKey.KEY_OAID, str2);
                }
                jSONObject2.put("gtAppid", com.getui.gtc.c.b.a);
                jSONObject2.put("pkgName", GtcProvider.context().getPackageName());
                jSONObject2.put("os", "android");
                com.getui.gtc.i.d.a unused2 = a.C0048a.a;
                String str3 = (String) DimManager.getInstance().get(new DimRequest.Builder().key("dim-2-1-8-1").useExpiredCacheForReserve(true).build());
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject2.put("androidAid", str3);
                }
                com.getui.gtc.i.d.a unused3 = a.C0048a.a;
                String str4 = (String) DimManager.getInstance().get(new DimRequest.Builder().key("dim-2-1-10-1").useExpiredCacheForReserve(true).build());
                if (!TextUtils.isEmpty(str4)) {
                    jSONObject2.put("phoneModel", str4);
                }
                JSONObject jSONObject3 = new JSONObject(d.a.newCall(new Request.Builder().url(String.format("%s/cidserver/getcid", server)).method("POST").body(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jSONObject.toString())).cryptInterceptor(new PtRASCryptoInterceptor(com.getui.gtc.c.b.i, com.getui.gtc.c.b.h)).tag("register gtcid").build()).execute().body().string());
                int i = jSONObject3.getInt("errno");
                String string = jSONObject3.getString("errmsg");
                if (i == 0) {
                    return jSONObject3.getJSONObject(com.alipay.sdk.packet.e.k).getString("gtcid");
                }
                throw new Exception(string);
            } catch (Throwable th) {
                if (!(th instanceof IOException) || !ServerManager.switchServer("gtc.as", server)) {
                    throw th;
                }
                com.getui.gtc.i.c.a.c("register gtcId failed with server: " + server + ", try again with: " + ServerManager.getServer("gtc.as"));
            }
        }
    }

    public static void a(final String str, final a aVar) {
        ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.h.c.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    String strA = c.a(str);
                    if (aVar != null) {
                        aVar.a(strA);
                    }
                } catch (Throwable th) {
                    com.getui.gtc.i.c.a.c("register gtcId failed: " + th.getMessage());
                }
            }
        });
    }
}
