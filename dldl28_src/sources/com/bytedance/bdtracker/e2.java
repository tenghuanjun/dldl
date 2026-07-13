package com.bytedance.bdtracker;

import android.text.TextUtils;
import com.bytedance.bdtracker.g2;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class e2 implements o2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f244a = 1;
    public String b;
    public long c;

    public e2(String str, long j) {
        this.b = str;
        this.c = j;
    }

    @Override // com.bytedance.bdtracker.f2
    public List<String> a() {
        return TextUtils.isEmpty(this.b) ? n0.a() : CollectionsKt.listOf((Object[]) new String[]{"metrics_category", "metrics_name", "api_name"});
    }

    @Override // com.bytedance.bdtracker.g2
    public void a(JSONObject params) throws JSONException {
        Intrinsics.checkParameterIsNotNull(params, "params");
        params.put("api_name", this.b);
        params.put("api_time", this.c);
    }

    @Override // com.bytedance.bdtracker.g2
    public String b() {
        return "api_usage";
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
        return this.f244a;
    }
}
