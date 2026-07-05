package com.mobile.auth.k;

import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public abstract class g {

    public interface a {
    }

    public abstract String a();

    protected abstract String a(String str);

    public abstract JSONObject b();

    public String u(String str) {
        return com.mobile.auth.n.d.a(a(str)).toLowerCase();
    }
}
