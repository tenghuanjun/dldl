package com.bytedance.bdtracker;

import android.net.Uri;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.network.INetworkClient;
import com.bytedance.framwork.core.sdklib.net.NetConst;
import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f303a;

    public p(d appLogInstance) {
        Intrinsics.checkParameterIsNotNull(appLogInstance, "appLogInstance");
        this.f303a = appLogInstance;
    }

    public final String a(String str, JSONObject jSONObject) {
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            String strOptString = jSONObject.optString(next);
            if (strOptString != null && strOptString.length() != 0) {
                builderBuildUpon.appendQueryParameter(next, jSONObject.optString(next));
            }
        }
        return builderBuildUpon.build().toString();
    }

    public final HashMap<String, String> a() {
        Map<String, String> httpHeaders;
        HashMap<String, String> map = new HashMap<>(2);
        InitConfig initConfig = this.f303a.getInitConfig();
        if (initConfig != null && (httpHeaders = initConfig.getHttpHeaders()) != null && !httpHeaders.isEmpty()) {
            map.putAll(httpHeaders);
        }
        map.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE, this.f303a.E ? "application/octet-stream;tt-data=a" : NetConst.CONTENT_TYPE);
        return map;
    }

    public final l<j> a(String uri, k queryParam) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        Intrinsics.checkParameterIsNotNull(queryParam, "queryParam");
        try {
            INetworkClient netClient = this.f303a.getNetClient();
            e3 e3Var = this.f303a.k;
            Intrinsics.checkExpressionValueIsNotNull(e3Var, "appLogInstance.api");
            byte[] bArrExecute = netClient.execute((byte) 0, e3Var.c.a(a(uri, queryParam.a())), null, a(), (byte) 0, true, 60000);
            Intrinsics.checkExpressionValueIsNotNull(bArrExecute, "appLogInstance.netClient…TIMEOUT\n                )");
            return l.b.a(new String(bArrExecute, Charsets.UTF_8), j.class);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final l<m> a(String uri, n request, k queryParam) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(queryParam, "queryParam");
        try {
            INetworkClient netClient = this.f303a.getNetClient();
            e3 e3Var = this.f303a.k;
            Intrinsics.checkExpressionValueIsNotNull(e3Var, "appLogInstance.api");
            byte[] bArrExecute = netClient.execute((byte) 1, e3Var.c.a(a(uri, queryParam.a())), request.a(), a(), (byte) 0, true, 60000);
            Intrinsics.checkExpressionValueIsNotNull(bArrExecute, "appLogInstance.netClient…OUT\n                    )");
            return l.b.a(new String(bArrExecute, Charsets.UTF_8), m.class);
        } catch (Throwable unused) {
            return null;
        }
    }
}
