package com.igexin.push.core.e;

import android.content.Context;
import android.text.TextUtils;
import com.igexin.base.api.SharedPreferencesManager;
import java.util.Iterator;
import kotlin.jvm.internal.LongCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c {
    private static final String a = "MsgSPManager";
    private static final String b = "gx_msg_sp";
    private static final String c = "taskIdList";
    private static final String d = "gx_vendor_token";
    private static final String e = "tokeninfo";
    private static final String f = "usfdl";
    private static final Object h = new Object();
    private static final Object i = new Object();
    private SharedPreferencesManager g;

    public c(Context context) {
        if (context != null) {
            this.g = SharedPreferencesManager.get(b);
        }
    }

    private static void b(JSONObject jSONObject) {
        try {
            if (jSONObject.length() < 150) {
                return;
            }
            boolean z = false;
            long j = LongCompanionObject.MAX_VALUE;
            String str = null;
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                long j2 = jSONObject.getLong(next);
                if (j > j2) {
                    str = next;
                    j = j2;
                }
                if (j2 < System.currentTimeMillis() - 432000000) {
                    itKeys.remove();
                    z = true;
                }
            }
            if (z || str == null) {
                return;
            }
            jSONObject.remove(str);
        } catch (Throwable unused) {
        }
    }

    private void c(String str) {
        try {
            this.g.saveParam(d, str);
        } catch (Throwable unused) {
        }
    }

    private static void c(JSONObject jSONObject) {
        try {
            if (jSONObject.length() < 20) {
                return;
            }
            boolean z = false;
            long j = LongCompanionObject.MAX_VALUE;
            String str = null;
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                long j2 = Long.parseLong(jSONObject.getJSONObject(next).getString("timestamp"));
                if (j > j2) {
                    str = next;
                    j = j2;
                }
                if (j2 < System.currentTimeMillis() - 432000000) {
                    itKeys.remove();
                    z = true;
                }
            }
            if (z || str == null) {
                return;
            }
            jSONObject.remove(str);
        } catch (Throwable unused) {
        }
    }

    private JSONObject d() {
        try {
            String str = (String) this.g.getParam(c, "");
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return new JSONObject(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    private JSONObject e() {
        try {
            String str = (String) this.g.getParam(f, "");
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return new JSONObject(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    private String f() {
        try {
            return (String) this.g.getParam(d, null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final JSONObject a() {
        synchronized (h) {
            try {
                try {
                    String str = (String) this.g.getParam(f, "");
                    if (TextUtils.isEmpty(str)) {
                        return null;
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(itKeys.next());
                        if (!jSONObject2.has("timestamp") || Long.parseLong(jSONObject2.getString("timestamp")) < System.currentTimeMillis() - 432000000) {
                            itKeys.remove();
                        }
                    }
                    return jSONObject;
                } catch (Throwable unused) {
                    return null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void a(String str, JSONObject jSONObject) {
        if (this.g == null || TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (h) {
            try {
                JSONObject jSONObjectE = e();
                if (jSONObjectE == null) {
                    jSONObjectE = new JSONObject();
                }
                if (jSONObjectE.length() > 0) {
                    c(jSONObjectE);
                }
                jSONObjectE.put(str, jSONObject);
                this.g.getParam(f, jSONObjectE.toString());
            } catch (Throwable unused) {
            }
        }
    }

    public final void a(JSONObject jSONObject) {
        try {
            this.g.saveParam(e, jSONObject.toString());
        } catch (Exception unused) {
        }
    }

    public final boolean a(String str) {
        if (this.g != null && !TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObjectD = d();
                if (jSONObjectD != null && jSONObjectD.has(str)) {
                    com.igexin.b.a.c.a.a("sp task " + str + " already exists", new Object[0]);
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public final void b() {
        synchronized (h) {
            try {
                if (this.g != null) {
                    this.g.getParam(f, "");
                }
            } catch (Throwable unused) {
            }
        }
    }

    public final void b(String str) {
        if (this.g == null || TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (i) {
            try {
                JSONObject jSONObjectD = d();
                if (jSONObjectD == null) {
                    jSONObjectD = new JSONObject();
                }
                if (jSONObjectD.length() > 0) {
                    b(jSONObjectD);
                }
                jSONObjectD.put(str, System.currentTimeMillis());
                this.g.saveParam(c, jSONObjectD.toString());
            } catch (Throwable unused) {
            }
        }
    }

    public final JSONObject c() {
        try {
            String strValueOf = String.valueOf(this.g.getParam(e, ""));
            return strValueOf.isEmpty() ? new JSONObject() : new JSONObject(strValueOf);
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }
}
