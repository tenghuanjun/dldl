package com.bytedance.bdtracker;

import android.text.TextUtils;
import com.bytedance.bdtracker.g2;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class j2 implements h2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f269a;

    public j2(Throwable throwable) {
        Intrinsics.checkParameterIsNotNull(throwable, "throwable");
        this.f269a = throwable;
    }

    @Override // com.bytedance.bdtracker.f2
    public List<String> a() {
        return TextUtils.isEmpty(this.f269a.getMessage()) ? n0.a() : CollectionsKt.listOf((Object[]) new String[]{"metrics_category", "metrics_name", "err_underlying_code"});
    }

    @Override // com.bytedance.bdtracker.g2
    public void a(JSONObject params) throws JSONException {
        Intrinsics.checkParameterIsNotNull(params, "params");
        StringWriter stringWriter = new StringWriter();
        this.f269a.printStackTrace(new PrintWriter(stringWriter));
        String message = this.f269a.getMessage();
        if (message == null) {
            message = "unknown";
        }
        params.put("err_underlying_code", message);
        params.put("err_message", stringWriter.toString());
    }

    @Override // com.bytedance.bdtracker.g2
    public String b() {
        return "db_exception";
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
        return 1;
    }
}
