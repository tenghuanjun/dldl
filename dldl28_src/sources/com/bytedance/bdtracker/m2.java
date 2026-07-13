package com.bytedance.bdtracker;

import com.bytedance.bdtracker.g2;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class m2 implements g2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f292a;
    public Integer b;
    public String c;
    public String d;
    public long e;

    @Override // com.bytedance.bdtracker.f2
    public List<String> a() {
        return this.b == null ? n0.a() : CollectionsKt.listOf((Object[]) new String[]{"metrics_category", "metrics_name", "err_underlying_code"});
    }

    public final void a(int i) {
        this.f292a = i;
    }

    @Override // com.bytedance.bdtracker.g2
    public void a(JSONObject params) throws JSONException {
        Intrinsics.checkParameterIsNotNull(params, "params");
        if (this.c != null) {
            params.put("err_code", 2003);
            params.put("err_message", this.c);
            params.put("err_underlying_code", this.b);
        }
        params.put("dim_success", this.f292a);
    }

    @Override // com.bytedance.bdtracker.g2
    public String b() {
        String strSubstring = this.d;
        if (strSubstring != null) {
            if (StringsKt.contains$default((CharSequence) strSubstring, (CharSequence) "?", false, 2, (Object) null)) {
                strSubstring = strSubstring.substring(0, StringsKt.indexOf$default((CharSequence) strSubstring, "?", 0, false, 6, (Object) null));
                Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            }
            if (strSubstring != null) {
                return strSubstring;
            }
        }
        return "";
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
        return "network_service";
    }

    @Override // com.bytedance.bdtracker.f2
    public List<Integer> f() {
        return CollectionsKt.listOf((Object[]) new Integer[]{0, 500, 1000, 1500, 2000, 2500, 5000});
    }

    @Override // com.bytedance.bdtracker.g2
    public Object g() {
        return Long.valueOf(this.e);
    }
}
