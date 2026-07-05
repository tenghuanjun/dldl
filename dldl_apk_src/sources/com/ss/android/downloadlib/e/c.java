package com.ss.android.downloadlib.e;

import android.text.TextUtils;
import android.util.Log;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.appdownloader.f.f;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class c implements com.ss.android.download.api.b.a {

    private static class a {
        private static c a = new c();
    }

    public static c a() {
        return a.a;
    }

    @Override // com.ss.android.download.api.b.a
    public void a(Throwable th, String str) {
        a(true, th, str);
    }

    public void a(boolean z, Throwable th, String str) {
        if (b()) {
            return;
        }
        if (th == null) {
            th = new Throwable();
        }
        if (z) {
            b(th);
        }
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str)) {
            str = th.getMessage();
        }
        m.a(jSONObject, "msg", str);
        m.a(jSONObject, "stack", Log.getStackTraceString(th));
        k.i().a("service_ttdownloader", 1, jSONObject);
    }

    public void a(String str) {
        a(true, str);
    }

    public void a(boolean z, String str) {
        if (b()) {
            return;
        }
        if (z) {
            b(new RuntimeException(str));
        }
        JSONObject jSONObject = new JSONObject();
        m.a(jSONObject, "msg", str);
        m.a(jSONObject, "stack", a(new Throwable()));
        k.i().a("service_ttdownloader", 2, jSONObject);
    }

    public void b(String str) {
        b(true, str);
    }

    public void b(boolean z, String str) {
        if (b()) {
            return;
        }
        if (z) {
            b(new RuntimeException(str));
        }
        JSONObject jSONObject = new JSONObject();
        m.a(jSONObject, "msg", str);
        m.a(jSONObject, "stack", a(new Throwable()));
        k.i().a("service_ttdownloader", 3, jSONObject);
    }

    private void b(Throwable th) {
        if (f.b(k.a())) {
            throw new com.ss.android.downloadlib.e.a(th);
        }
    }

    public static String a(Throwable th) {
        try {
            return Log.getStackTraceString(th);
        } catch (Exception unused) {
            return null;
        }
    }

    private boolean b() {
        return k.j().optInt("enable_monitor", 1) != 1;
    }
}
