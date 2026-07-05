package com.taptap.sdk.openlog.utils;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: JsonUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/openlog/utils/JsonUtils;", "", "()V", "stringify", "", "map", "", "tap-openlog_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class JsonUtils {
    public static final JsonUtils INSTANCE = new JsonUtils();

    private JsonUtils() {
    }

    public final String stringify(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        String string = new JSONObject(map).toString();
        Intrinsics.checkNotNullExpressionValue(string, "JSONObject(map).toString()");
        return string;
    }
}
