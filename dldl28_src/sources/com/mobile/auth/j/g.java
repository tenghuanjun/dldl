package com.mobile.auth.j;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class g {
    public abstract String a();

    protected abstract String a(String str);

    public abstract JSONObject b();

    public String u(String str) {
        return com.mobile.auth.m.d.a(a(str)).toLowerCase();
    }
}
