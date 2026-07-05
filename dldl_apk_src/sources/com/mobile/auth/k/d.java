package com.mobile.auth.k;

import android.net.Network;
import com.mobile.auth.k.z;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d extends g {
    private final String a;
    private final String b;
    private final String c;
    private String d = "authz";
    private String e;

    class a implements z.b {
        a() {
        }

        @Override // com.mobile.auth.k.z.b
        public void a(Network network) {
            d.a(d.this, network);
            f.b("HttpUtils", "onAvailable");
        }
    }

    public interface b {
        void a(String str, String str2);

        void a(String str, String str2, String str3);
    }

    public d(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    @Override // com.mobile.auth.k.g
    public String a() {
        return this.a;
    }

    @Override // com.mobile.auth.k.g
    protected String a(String str) {
        return null;
    }

    @Override // com.mobile.auth.k.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ver", this.b);
            jSONObject.put(com.alipay.sdk.packet.e.k, this.c);
            jSONObject.put("userCapaid", this.e);
            jSONObject.put("funcType", this.d);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.e = str;
    }
}
