package com.igexin.push.core.a.b;

import com.igexin.push.b.a;
import com.igexin.push.config.SDKUrlConfig;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class g extends a {
    private static final String a = com.igexin.push.config.c.a + "_RedirectServerAction";

    private static void a(String str, JSONArray jSONArray) {
        try {
            com.igexin.b.a.c.a.a(a + "|start fetch idc config, url : " + str, new Object[0]);
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.e.a.d(new com.igexin.push.core.h.c(str, jSONArray)), false, true);
        } catch (Exception e) {
            com.igexin.b.a.c.a.a(a + e.toString(), new Object[0]);
        }
    }

    private static void a(JSONObject jSONObject) {
        long jOptLong = jSONObject.optLong("delay");
        if (jOptLong >= 0) {
            com.igexin.push.core.e.L = jOptLong;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("address_list");
        com.igexin.b.a.c.a.a("redirect|" + jOptLong + "|" + jSONArrayOptJSONArray.toString(), new Object[0]);
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            String strOptString = jSONArrayOptJSONArray.optString(i);
            int iIndexOf = strOptString.indexOf(44);
            if (iIndexOf > 0) {
                String strSubstring = strOptString.substring(0, iIndexOf);
                String strSubstring2 = strOptString.substring(iIndexOf + 1);
                long jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    long j = Long.parseLong(strSubstring2);
                    a.b bVar = new a.b();
                    bVar.a = "socket://".concat(String.valueOf(strSubstring));
                    bVar.b = jCurrentTimeMillis + (j * 1000);
                    arrayList.add(bVar);
                } catch (NumberFormatException unused) {
                }
            }
        }
        com.igexin.push.b.c.a().d().a(arrayList);
    }

    public static String[] a(JSONArray jSONArray) {
        String[] strArr = null;
        try {
            strArr = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                strArr[i] = "https://" + jSONArray.getString(i);
            }
        } catch (Exception e) {
            com.igexin.b.a.c.a.a(a + "|parseIDCConfigURL exception" + e.toString(), new Object[0]);
        }
        return strArr;
    }

    private static void b(JSONObject jSONObject) {
        if (jSONObject.has("loc") && jSONObject.has("conf")) {
            try {
                SDKUrlConfig.setLocation(jSONObject.getString("loc"));
                String str = com.igexin.push.core.e.c;
                com.igexin.b.a.c.a.a(a + " set group id : " + com.igexin.push.core.e.c, new Object[0]);
                JSONArray jSONArray = jSONObject.getJSONArray("conf");
                String[] strArrA = a(jSONArray);
                if (strArrA == null || strArrA.length <= 1) {
                    return;
                }
                String[] idcConfigUrl = SDKUrlConfig.getIdcConfigUrl();
                if (idcConfigUrl != null && (idcConfigUrl.length <= 1 || strArrA[1].equals(idcConfigUrl[1]))) {
                    com.igexin.b.a.c.a.a(a + "|current idc config url == new idc config url, return", new Object[0]);
                    return;
                }
                if (com.igexin.push.core.e.ao == 0) {
                    a(strArrA[1], jSONArray);
                    return;
                }
                if (System.currentTimeMillis() - com.igexin.push.core.e.ao > 7200000) {
                    a(strArrA[1], jSONArray);
                    return;
                }
                com.igexin.b.a.c.a.a(a + "|get idc cfg last time less than 2 hours return", new Object[0]);
            } catch (Exception e) {
                com.igexin.b.a.c.a.a(a + e.toString(), new Object[0]);
            }
        }
    }

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        String str;
        Object[] objArr;
        com.igexin.b.a.c.a.a(a + "|redirect server resp data : " + jSONObject, new Object[0]);
        try {
            a(jSONObject);
            com.igexin.b.a.b.a.a.d.a().f();
            if (jSONObject.has("loc") && jSONObject.has("conf")) {
                try {
                    SDKUrlConfig.setLocation(jSONObject.getString("loc"));
                    String str2 = com.igexin.push.core.e.c;
                    com.igexin.b.a.c.a.a(a + " set group id : " + com.igexin.push.core.e.c, new Object[0]);
                    JSONArray jSONArray = jSONObject.getJSONArray("conf");
                    String[] strArrA = a(jSONArray);
                    if (strArrA != null && strArrA.length > 1) {
                        String[] idcConfigUrl = SDKUrlConfig.getIdcConfigUrl();
                        if (idcConfigUrl != null && (idcConfigUrl.length <= 1 || strArrA[1].equals(idcConfigUrl[1]))) {
                            str = a + "|current idc config url == new idc config url, return";
                            objArr = new Object[0];
                        } else if (com.igexin.push.core.e.ao != 0 && System.currentTimeMillis() - com.igexin.push.core.e.ao <= 7200000) {
                            str = a + "|get idc cfg last time less than 2 hours return";
                            objArr = new Object[0];
                        } else {
                            String str3 = strArrA[1];
                            a(str3, jSONArray);
                        }
                        com.igexin.b.a.c.a.a(str, objArr);
                    }
                } catch (Exception e) {
                    com.igexin.b.a.c.a.a(a + e.toString(), new Object[0]);
                }
            }
            if (com.igexin.push.f.h.a()) {
                com.igexin.b.a.c.a.a(a + "|redirect reInit so ~~~~~", new Object[0]);
                com.igexin.push.f.h.d();
            }
        } catch (Exception e2) {
            com.igexin.b.a.c.a.a(a + e2.toString(), new Object[0]);
        }
        return true;
    }
}
