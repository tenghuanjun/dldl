package com.bytedance.applog.aggregation;

import com.tencent.connect.common.Constants;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001\u001a\f\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0006\u001a\f\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\u0006¨\u0006\b"}, d2 = {"copy", "Lorg/json/JSONObject;", "copyFrom", Constants.FROM, "toJSONArray", "Lorg/json/JSONArray;", "", "toJSONObject", "aggregation_release"}, k = 2, mv = {1, 1, 16})
public final class UtilsKt {
    public static final JSONObject copy(JSONObject copy) {
        Intrinsics.checkParameterIsNotNull(copy, "$this$copy");
        JSONObject jSONObject = new JSONObject();
        try {
            Iterator<String> itKeys = copy.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject.put(next, copy.opt(next));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public static final JSONObject copyFrom(JSONObject copyFrom, JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(copyFrom, "$this$copyFrom");
        if (jSONObject != null) {
            try {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    copyFrom.put(next, jSONObject.opt(next));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return copyFrom;
    }

    public static final JSONObject toJSONObject(String toJSONObject) {
        Intrinsics.checkParameterIsNotNull(toJSONObject, "$this$toJSONObject");
        try {
            return new JSONObject(toJSONObject);
        } catch (Exception unused) {
            return null;
        }
    }

    public static final JSONArray toJSONArray(String toJSONArray) {
        Intrinsics.checkParameterIsNotNull(toJSONArray, "$this$toJSONArray");
        try {
            return new JSONArray(toJSONArray);
        } catch (Exception unused) {
            return null;
        }
    }
}
