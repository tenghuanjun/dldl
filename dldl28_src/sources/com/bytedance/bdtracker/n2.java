package com.bytedance.bdtracker;

import com.bytedance.bdtracker.g2;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class n2 implements o2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f296a;

    public n2(long j) {
        this.f296a = j;
    }

    @Override // com.bytedance.bdtracker.f2
    public List<String> a() {
        return n0.a();
    }

    @Override // com.bytedance.bdtracker.g2
    public void a(JSONObject params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        n0.a((o2) this, params);
    }

    @Override // com.bytedance.bdtracker.g2
    public String b() {
        return "sdk_init";
    }

    @Override // com.bytedance.bdtracker.f2
    public int c() {
        return 7;
    }

    @Override // com.bytedance.bdtracker.g2
    public JSONObject d() {
        return g2.a.a(this);
    }

    @Override // com.bytedance.bdtracker.g2
    public String e() {
        return "sdk_usage";
    }

    @Override // com.bytedance.bdtracker.f2
    public List<Number> f() {
        return n0.a((o2) this);
    }

    @Override // com.bytedance.bdtracker.g2
    public Object g() {
        return Long.valueOf(h());
    }

    public long h() {
        return this.f296a;
    }
}
