package com.mobile.auth.h;

import android.text.TextUtils;
import com.mobile.auth.k.h;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class c extends f {
    private a a;
    private String b;
    private String c;

    public static class a {
        private String E;
        private String a = "";
        private String b = "";
        private String c = "";
        private String d = "";
        private String e = "";
        private String f = "";
        private String g = "";
        private String h = "";
        private String i = "";
        private String j = "";
        private String k = "";
        private String l = "";
        private String m = "";
        private String n = "";
        private String o = "";
        private String p = "";
        private String q = "";
        private String r = "";
        private String s = "";
        private String t = "";
        private String u = "";
        private String v = "";
        private String w = "";
        private String x = "";
        private String y = "";
        private String z = "";
        private String A = "";
        private String B = "";
        private String C = "";
        private String D = null;

        private String w(String str) {
            return str == null ? "" : str;
        }

        public void a(String str) {
            this.D = str;
        }

        public void b(String str) {
            this.B = str;
        }

        public void c(String str) {
            this.C = str;
        }

        public void d(String str) {
            this.x = w(str);
        }

        public void e(String str) {
            this.a = w(str);
        }

        public void f(String str) {
            this.b = w(str);
        }

        public void g(String str) {
            this.c = w(str);
        }

        public void h(String str) {
            this.d = w(str);
        }

        public void i(String str) {
            this.e = w(str);
        }

        public void j(String str) {
            this.f = w(str);
        }

        public void k(String str) {
            this.h = w(str);
        }

        public void l(String str) {
            this.i = w(str);
        }

        public void m(String str) {
            String strW = w(str);
            try {
                this.j = URLEncoder.encode(strW, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                this.j = strW;
            }
        }

        public void n(String str) {
            String strW = w(str);
            try {
                this.k = URLEncoder.encode(strW, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                this.k = strW;
            }
        }

        public void o(String str) {
            this.l = w(str);
        }

        public void p(String str) {
            this.m = w(str);
        }

        public void q(String str) {
            this.o = w(str);
        }

        public void r(String str) {
            this.p = w(str);
        }

        public void s(String str) {
            this.z = w(str);
        }

        public void t(String str) {
            this.A = w(str);
        }

        public String toString() {
            String str = this.a + com.alipay.sdk.sys.a.b + this.b + com.alipay.sdk.sys.a.b + this.c + com.alipay.sdk.sys.a.b + this.d + com.alipay.sdk.sys.a.b + this.e + com.alipay.sdk.sys.a.b + this.f + com.alipay.sdk.sys.a.b + this.g + com.alipay.sdk.sys.a.b + this.h + com.alipay.sdk.sys.a.b + this.i + com.alipay.sdk.sys.a.b + this.j + com.alipay.sdk.sys.a.b + this.k + com.alipay.sdk.sys.a.b + this.l + com.alipay.sdk.sys.a.b + this.m + com.alipay.sdk.sys.a.b + "7.0" + com.alipay.sdk.sys.a.b + this.n + com.alipay.sdk.sys.a.b + this.o + com.alipay.sdk.sys.a.b + this.p + com.alipay.sdk.sys.a.b + this.q + com.alipay.sdk.sys.a.b + this.r + com.alipay.sdk.sys.a.b + this.s + com.alipay.sdk.sys.a.b + this.t + com.alipay.sdk.sys.a.b + this.u + com.alipay.sdk.sys.a.b + this.v + com.alipay.sdk.sys.a.b + this.w + com.alipay.sdk.sys.a.b + this.x + com.alipay.sdk.sys.a.b + this.y + com.alipay.sdk.sys.a.b + this.z + com.alipay.sdk.sys.a.b + this.A + com.alipay.sdk.sys.a.b + this.E + "&&" + this.B + com.alipay.sdk.sys.a.b + this.C;
            if (TextUtils.isEmpty(this.D)) {
                return str;
            }
            return str + com.alipay.sdk.sys.a.b + this.D;
        }

        public void u(String str) {
            this.E = w(str);
        }

        public String v(String str) {
            return h.a(this.b + this.c + this.d + this.e + this.f + this.g + this.h + this.i + this.j + this.k + this.l + this.m + this.o + this.p + str + this.q + this.r + this.s + this.t + this.u + this.v + this.w + this.x + this.y + this.z + this.A + this.B + this.C);
        }
    }

    @Override // com.mobile.auth.h.f
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("encrypted", this.c);
            jSONObject.put("reqdata", com.mobile.auth.k.a.a(this.b, this.a.toString()));
            com.mobile.auth.k.f.a("GETpre", this.a.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(String str) {
        this.b = str;
    }

    public a b() {
        return this.a;
    }

    public void b(String str) {
        this.c = str;
    }
}
