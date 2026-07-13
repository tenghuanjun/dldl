package com.bytedance.bdtracker;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f237a;
    public boolean b;
    public boolean c;
    public boolean d;

    public d1(boolean z, boolean z2) {
        this.b = z;
        this.c = z2;
        this.d = false;
    }

    public d1(boolean z, boolean z2, boolean z3) {
        this.b = z;
        this.c = z2;
        this.d = z3;
    }

    public abstract String a();

    public abstract boolean a(JSONObject jSONObject);
}
