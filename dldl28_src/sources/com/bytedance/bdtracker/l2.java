package com.bytedance.bdtracker;

import com.bytedance.bdtracker.g2;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class l2 implements o2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f288a;

    public l2(long j) {
        this.f288a = j;
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
        return "db_delay_interval";
    }

    @Override // com.bytedance.bdtracker.f2
    public int c() {
        return 23;
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
    public List<Integer> f() {
        return CollectionsKt.listOf((Object[]) new Integer[]{0, 1000, 10000, 60000, 300000, 1200000, 3600000, 21600000});
    }

    @Override // com.bytedance.bdtracker.g2
    public Object g() {
        return Long.valueOf(h());
    }

    public long h() {
        return this.f288a;
    }
}
