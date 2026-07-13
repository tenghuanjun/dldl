package com.bytedance.bdtracker;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class e0 extends a0 {
    public final String g;
    public int h;

    public e0(c0 c0Var, String str) {
        super(c0Var);
        this.h = 0;
        this.g = str;
    }

    @Override // com.bytedance.bdtracker.a0
    public boolean c() {
        int i = this.f.k.a((JSONObject) null, this.g) ? 0 : this.h + 1;
        this.h = i;
        if (i > 3) {
            this.f.setRangersEventVerifyEnable(false, this.g);
        }
        return true;
    }

    @Override // com.bytedance.bdtracker.a0
    public String d() {
        return "RangersEventVerify";
    }

    @Override // com.bytedance.bdtracker.a0
    public long[] e() {
        return new long[]{1000};
    }

    @Override // com.bytedance.bdtracker.a0
    public boolean f() {
        return true;
    }

    @Override // com.bytedance.bdtracker.a0
    public long g() {
        return 1000L;
    }
}
