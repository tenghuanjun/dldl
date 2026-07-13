package com.volcengine.j;

import android.text.TextUtils;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class j {
    public static String a(Map<String, Object> map) {
        return new JSONObject(map).toString();
    }

    public static JSONObject a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        JSONArray jSONArrayNames = jSONObject.names();
        JSONObject jSONObject2 = new JSONObject();
        int length = jSONArrayNames == null ? 0 : jSONArrayNames.length();
        for (int i = 0; i < length; i++) {
            String strOptString = jSONArrayNames.optString(i);
            if (!TextUtils.isEmpty(strOptString)) {
                try {
                    jSONObject2.put(strOptString, jSONObject.get(strOptString));
                } catch (JSONException unused) {
                }
            }
        }
        return jSONObject2;
    }

    public static JSONObject b(Map<String, Object> map) {
        return (map == null || map.size() < 1) ? new JSONObject() : new JSONObject(map);
    }
}
