package com.ss.android.downloadlib.addownload.compliance;

import com.ss.android.downloadlib.g.m;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class e {
    private boolean a;
    private a b;
    private int c;
    private int d;
    private int e = 15;
    private int f;
    private String g;
    private String h;
    private int i;
    private String j;
    private long k;
    private String l;
    private String m;
    private b n;
    private String o;

    public void a(boolean z) {
        this.a = z;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public void c(int i) {
        this.e = i;
    }

    public void d(int i) {
        this.f = i;
    }

    public void a(String str) {
        this.g = str;
    }

    public void b(String str) {
        this.h = str;
    }

    public int a() {
        return this.i;
    }

    public void e(int i) {
        this.i = i;
    }

    public void c(String str) {
        this.j = str;
    }

    public void a(long j) {
        this.k = j;
    }

    public void a(b bVar) {
        this.n = bVar;
    }

    public void d(String str) {
        this.o = str;
    }

    public void e(String str) {
        this.l = str;
    }

    public String b() {
        return this.m;
    }

    public void f(String str) {
        this.m = str;
    }

    public static class a {
        private String a;
        private String b;
        private long c;
        private long d;
        private String e;
        private String f;
        private List<C0093a> g;
        private String h;
        private String i;
        private String j;
        private String k;
        private String l;

        public void a(String str) {
            this.a = str;
        }

        public void b(String str) {
            this.b = str;
        }

        public void a(long j) {
            this.c = j;
        }

        public void b(long j) {
            this.d = j;
        }

        public void c(String str) {
            this.e = str;
        }

        public void d(String str) {
            this.f = str;
        }

        public void e(String str) {
            this.h = str;
        }

        public void a(List<C0093a> list) {
            this.g = list;
        }

        public void f(String str) {
            this.i = str;
        }

        public void g(String str) {
            this.j = str;
        }

        public void h(String str) {
            this.k = str;
        }

        public void i(String str) {
            this.l = str;
        }

        /* JADX INFO: renamed from: com.ss.android.downloadlib.addownload.compliance.e$a$a, reason: collision with other inner class name */
        public static class C0093a {
            private String a;
            private String b;

            public void a(String str) {
                this.a = str;
            }

            public void b(String str) {
                this.b = str;
            }
        }
    }

    public static class b {
        private int a;
        private String b;

        public void a(int i) {
            this.a = i;
        }

        public void a(String str) {
            this.b = str;
        }
    }

    public String toString() {
        return a(this);
    }

    public static String a(e eVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("show_auth", Integer.valueOf(eVar.a ? 1 : 0));
            jSONObject.putOpt("download_permit", Integer.valueOf(eVar.c));
            jSONObject.putOpt("appstore_permit", Integer.valueOf(eVar.d));
            jSONObject.putOpt("market_online_status", Integer.valueOf(eVar.e));
            jSONObject.putOpt("hijack_permit", Integer.valueOf(eVar.f));
            jSONObject.putOpt("package_name", eVar.g);
            jSONObject.putOpt("hijack_url", eVar.h);
            jSONObject.putOpt("code", Integer.valueOf(eVar.i));
            jSONObject.putOpt(com.igexin.push.core.b.Z, eVar.j);
            jSONObject.putOpt("request_duration", Long.valueOf(eVar.k));
            jSONObject.putOpt("auth_info", b(eVar.b));
            jSONObject.putOpt("status", b(eVar.n));
            jSONObject.putOpt("back_web_url", eVar.o);
            jSONObject.putOpt("hw_app_id", eVar.l);
            jSONObject.putOpt("deep_link", eVar.m);
        } catch (JSONException e) {
            com.ss.android.downloadlib.e.c.a().a(e, "ComplianceResult toJson");
        }
        return jSONObject.toString();
    }

    public static e g(String str) {
        e eVar = new e();
        try {
            JSONObject jSONObject = new JSONObject(str);
            a aVarA = a(jSONObject);
            b bVarB = b(jSONObject);
            eVar.a(aVarA);
            eVar.a(bVarB);
            eVar.a(jSONObject.optInt("show_auth", 0) == 1);
            eVar.a(jSONObject.optInt("download_permit"));
            eVar.b(jSONObject.optInt("appstore_permit"));
            eVar.c(jSONObject.optInt("market_online_status", 15));
            eVar.d(jSONObject.optInt("hijack_permit"));
            eVar.a(jSONObject.optString("package_name"));
            eVar.b(jSONObject.optString("hijack_url"));
            eVar.e(jSONObject.optInt("code"));
            eVar.c(jSONObject.optString(com.igexin.push.core.b.Z));
            eVar.a(jSONObject.optLong("request_duration", 0L));
            eVar.d(jSONObject.optString("back_web_url"));
            eVar.e(jSONObject.optString("hw_app_id"));
            eVar.f(jSONObject.optString("deep_link"));
        } catch (Exception e) {
            com.ss.android.downloadlib.e.c.a().a(e, "ComplianceResult fromJson");
        }
        return eVar;
    }

    private static a a(JSONObject jSONObject) {
        a aVar = new a();
        try {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("auth_info");
            if (jSONObjectOptJSONObject != null) {
                aVar.a(jSONObjectOptJSONObject.optString("app_name"));
                aVar.b(jSONObjectOptJSONObject.optString("version_name"));
                aVar.a(m.a(jSONObjectOptJSONObject, "update_time"));
                aVar.b(m.a(jSONObjectOptJSONObject, "size"));
                aVar.c(jSONObjectOptJSONObject.optString("developer_name"));
                aVar.d(jSONObjectOptJSONObject.optString("package_name"));
                JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("permissions");
                if (jSONArrayOptJSONArray != null) {
                    ArrayList arrayList = new ArrayList();
                    a(jSONArrayOptJSONArray, arrayList);
                    aVar.a(arrayList);
                }
                aVar.e(jSONObjectOptJSONObject.optString("permission_classify_url"));
                aVar.f(jSONObjectOptJSONObject.optString("policy_url"));
                aVar.g(jSONObjectOptJSONObject.optString("icon_url"));
                aVar.h(jSONObjectOptJSONObject.optString("download_url"));
                aVar.i(jSONObjectOptJSONObject.optString("desc_url"));
            }
        } catch (Exception e) {
            com.ss.android.downloadlib.e.c.a().a(e, "ComplianceResult getAuthInfo");
        }
        return aVar;
    }

    private static JSONObject b(a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (aVar != null) {
            jSONObject.putOpt("app_name", aVar.a);
            jSONObject.putOpt("version_name", aVar.b);
            jSONObject.putOpt("update_time", Long.valueOf(aVar.c));
            jSONObject.putOpt("size", Long.valueOf(aVar.d));
            jSONObject.putOpt("developer_name", aVar.e);
            jSONObject.putOpt("policy_url", aVar.i);
            jSONObject.putOpt("icon_url", aVar.j);
            jSONObject.putOpt("download_url", aVar.k);
            jSONObject.putOpt("permissions", c(aVar));
            jSONObject.putOpt("permission_classify_url", aVar.h);
            jSONObject.putOpt("desc_url", aVar.l);
        }
        return jSONObject;
    }

    private static b b(JSONObject jSONObject) {
        b bVar = new b();
        try {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("status");
            if (jSONObjectOptJSONObject != null) {
                bVar.a(jSONObjectOptJSONObject.optInt("status"));
                bVar.a(jSONObjectOptJSONObject.optString(com.igexin.push.core.b.Z));
            }
        } catch (Exception e) {
            com.ss.android.downloadlib.e.c.a().a(e, "ComplianceResult getStatus");
        }
        return bVar;
    }

    private static JSONObject b(b bVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bVar != null) {
            jSONObject.putOpt("status", Integer.valueOf(bVar.a));
            jSONObject.putOpt(com.igexin.push.core.b.Z, bVar.b);
        }
        return jSONObject;
    }

    private static void a(JSONArray jSONArray, List<a.C0093a> list) {
        if (jSONArray == null || list == null) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                a.C0093a c0093a = new a.C0093a();
                c0093a.a(jSONObjectOptJSONObject.optString("permission_name"));
                c0093a.b(jSONObjectOptJSONObject.optString("permission_desc"));
                list.add(c0093a);
            }
        }
    }

    private static JSONArray c(a aVar) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        List<a.C0093a> list = aVar.g;
        if (list != null && list.size() > 0) {
            for (a.C0093a c0093a : list) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.putOpt("permission_name", c0093a.a);
                jSONObject.putOpt("permission_desc", c0093a.b);
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }
}
