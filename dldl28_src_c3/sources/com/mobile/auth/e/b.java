package com.mobile.auth.e;

import android.text.TextUtils;
import com.mobile.auth.BuildConfig;
import com.mobile.auth.e.a;
import com.mobile.auth.m.k;
import com.mobile.auth.m.n;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class b {
    private static b c;
    private com.mobile.auth.e.a a;
    private final com.mobile.auth.e.a b;
    private volatile boolean d = false;
    private a e;

    interface a {
        void a(com.mobile.auth.e.a aVar);
    }

    private b(boolean z) {
        com.mobile.auth.e.a aVarA = new a.C0179a().a();
        this.b = aVarA;
        if (z) {
            this.a = aVarA;
        } else {
            this.a = d();
        }
    }

    public static b a(boolean z) {
        if (c == null) {
            synchronized (b.class) {
                if (c == null) {
                    c = new b(z);
                }
            }
        }
        return c;
    }

    private String a(String str, String str2) {
        String str3;
        String[] strArrSplit = str.split("&");
        int length = strArrSplit.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str3 = "";
                break;
            }
            str3 = strArrSplit[i];
            if (str3.contains(str2)) {
                break;
            }
            i++;
        }
        return !TextUtils.isEmpty(str3) ? str3.substring(str3.lastIndexOf("=") + 1) : str3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        k.a aVarB = k.b("sso_config_xf");
        try {
            if (jSONObject.has("client_valid")) {
                aVarB.a("client_valid", System.currentTimeMillis() + (((long) Integer.parseInt(jSONObject.getString("client_valid"))) * 3600000));
            }
            if (jSONObject.has("Configlist")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("Configlist");
                if (jSONObject2.has("CHANGE_HOST")) {
                    String string = jSONObject2.getString("CHANGE_HOST");
                    if (string.contains("M007")) {
                        String strA = a(string, "M007");
                        if (!TextUtils.isEmpty(strA)) {
                            aVarB.a("logHost", strA);
                        }
                    }
                    if (string.contains("M008")) {
                        String strA2 = a(string, "M008");
                        if (!TextUtils.isEmpty(strA2)) {
                            aVarB.a("https_get_phone_scrip_host", strA2);
                        }
                    }
                    if (string.contains("M009")) {
                        String strA3 = a(string, "M009");
                        if (!TextUtils.isEmpty(strA3)) {
                            aVarB.a("config_host", strA3);
                        }
                    }
                } else {
                    aVarB.a("logHost");
                    aVarB.a("https_get_phone_scrip_host");
                    aVarB.a("config_host");
                }
                a(jSONObject2, "CLOSE_FRIEND_WAPKS", "0", aVarB);
                a(jSONObject2, "CLOSE_LOGS_VERSION", "0", aVarB);
                a(jSONObject2, "CLOSE_IPV4_LIST", "0", aVarB);
                a(jSONObject2, "CLOSE_IPV6_LIST", "0", aVarB);
                a(jSONObject2, "CLOSE_M008_SDKVERSION_LIST", "0", aVarB);
                a(jSONObject2, "CLOSE_M008_APPID_LIST", "0", aVarB);
                if (jSONObject2.has("LOGS_CONTROL")) {
                    String[] strArrSplit = jSONObject2.getString("LOGS_CONTROL").replace("h", "").split("&");
                    if (strArrSplit.length == 2 && !TextUtils.isEmpty(strArrSplit[0]) && !TextUtils.isEmpty(strArrSplit[1])) {
                        try {
                            int i = Integer.parseInt(strArrSplit[0]);
                            int i2 = Integer.parseInt(strArrSplit[1]);
                            aVarB.a("maxFailedLogTimes", i);
                            aVarB.a("pauseTime", i2);
                        } catch (Exception unused) {
                            com.mobile.auth.m.c.a("UmcConfigHandle", "解析日志上报限制时间次数异常");
                        }
                    }
                } else {
                    aVarB.a("maxFailedLogTimes");
                    aVarB.a("pauseTime");
                }
            }
            aVarB.b();
        } catch (Exception e) {
            com.mobile.auth.m.c.a("UmcConfigHandle", "配置项异常，配置失效");
            e.printStackTrace();
        }
    }

    private void a(JSONObject jSONObject, String str, String str2, k.a aVar) {
        if (!jSONObject.has(str)) {
            aVar.a(str);
            return;
        }
        String strOptString = jSONObject.optString(str, str2);
        if ("CLOSE_FRIEND_WAPKS".equals(str)) {
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            if (!strOptString.contains("CU") && !strOptString.contains("CT") && !strOptString.contains("CM")) {
                return;
            }
        } else if (!"0".equals(strOptString) && !"1".equals(strOptString)) {
            return;
        }
        aVar.a(str, jSONObject.optString(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.cmic.sso.sdk.a aVar) {
        if (this.d) {
            com.mobile.auth.m.c.a("UmcConfigHandle", "正在获取配置中...");
        } else {
            this.d = true;
            com.mobile.auth.k.a.a().a(false, aVar, new com.mobile.auth.k.d() { // from class: com.mobile.auth.e.b.1
                @Override // com.mobile.auth.k.d
                public void a(String str, String str2, JSONObject jSONObject) {
                    try {
                        if ("103000".equals(str)) {
                            b.this.a(jSONObject);
                            k.a("sdk_config_version", BuildConfig.CMCC_SDK_VERSION);
                            b bVar = b.this;
                            bVar.a = bVar.d();
                            if (b.this.e != null) {
                                b.this.e.a(b.this.a);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    b.this.d = false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.mobile.auth.e.a d() {
        return new a.C0179a().a(d.b(this.b.a())).c(d.a(this.b.c())).b(d.b(this.b.b())).d(d.c(this.b.d())).d(d.a(this.b.h())).e(d.b(this.b.i())).a(d.e(this.b.e())).b(d.d(this.b.f())).c(d.c(this.b.g())).f(d.f(this.b.j())).a(d.a(this.b.k())).b(d.b(this.b.l())).a();
    }

    com.mobile.auth.e.a a() {
        return this.b;
    }

    void a(final com.cmic.sso.sdk.a aVar) {
        if (d.a()) {
            n.a(new n.a() { // from class: com.mobile.auth.e.b.2
                @Override // com.mobile.auth.m.n.a
                protected void a() {
                    com.mobile.auth.m.c.b("UmcConfigHandle", "开始拉取配置..");
                    b.this.b(aVar);
                }
            });
        }
    }

    void a(a aVar) {
        this.e = aVar;
    }

    com.mobile.auth.e.a b() {
        return this.a;
    }

    void c() {
        k.a aVarB = k.b("sso_config_xf");
        aVarB.c();
        aVarB.b();
    }
}
