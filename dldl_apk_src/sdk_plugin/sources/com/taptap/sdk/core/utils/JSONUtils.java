package com.taptap.sdk.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: JSONUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004*\u00020\u0005H\u0002J\u0018\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007*\u00020\t¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/core/utils/JSONUtils;", "", "()V", "toList", "", "Lorg/json/JSONArray;", "toMap", "", "", "Lorg/json/JSONObject;", "tap-core_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class JSONUtils {
    public static final JSONUtils INSTANCE = new JSONUtils();

    private JSONUtils() {
    }

    public final Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        Object list;
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            HashMap map2 = map;
            Intrinsics.checkNotNullExpressionValue(key, "key");
            Object obj = jSONObject.get(key);
            if (obj == null ? true : Intrinsics.areEqual(obj, JSONObject.NULL)) {
                list = null;
            } else if (obj instanceof JSONObject) {
                list = toMap((JSONObject) obj);
            } else if (obj instanceof JSONArray) {
                list = toList((JSONArray) obj);
            } else {
                list = jSONObject.get(key);
            }
            map2.put(key, list);
        }
        return map;
    }

    private final List<Object> toList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object obj = jSONArray.get(i);
            if (obj == null ? true : Intrinsics.areEqual(obj, JSONObject.NULL)) {
                arrayList.add(null);
            } else if (obj instanceof JSONArray) {
                arrayList.add(toList((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                arrayList.add(toMap((JSONObject) obj));
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
