package com.bytedance.bdtracker;

import com.bytedance.bdtracker.g2;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class i2 implements h2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f264a;

    public i2(int i) {
        this.f264a = i;
    }

    @Override // com.bytedance.bdtracker.f2
    public List<String> a() {
        return n0.a();
    }

    @Override // com.bytedance.bdtracker.g2
    public String b() {
        return "data_storage_count";
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
        return "data_statistics";
    }

    @Override // com.bytedance.bdtracker.f2
    public List<Number> f() {
        return g2.a.b(this);
    }

    @Override // com.bytedance.bdtracker.g2
    public Object g() {
        return Integer.valueOf(this.f264a);
    }

    @Override // com.bytedance.bdtracker.g2
    public void a(JSONObject params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(params, "params");
        g2.a.a(params);
    }
}
