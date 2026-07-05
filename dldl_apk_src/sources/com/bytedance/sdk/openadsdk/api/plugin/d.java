package com.bytedance.sdk.openadsdk.api.plugin;

import android.os.SystemClock;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class d {
    private String a;
    private long b;
    private long c;
    private Map<String, Long> d = new HashMap();

    private d(String str, long j) {
        this.a = str;
        this.b = j;
        this.c = j;
    }

    public static d a(String str) {
        return new d(str, SystemClock.elapsedRealtime());
    }

    public long a() {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.b;
        this.d.put(this.a, Long.valueOf(jElapsedRealtime));
        return jElapsedRealtime;
    }

    public long b(String str) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.c;
        this.c = SystemClock.elapsedRealtime();
        this.d.put(str, Long.valueOf(jElapsedRealtime));
        return jElapsedRealtime;
    }

    public long b() {
        return this.b;
    }

    public void a(JSONObject jSONObject, long j) {
        if (jSONObject == null) {
            return;
        }
        for (Map.Entry<String, Long> entry : this.d.entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();
            if (!TextUtils.isEmpty(key) && value.longValue() > j) {
                try {
                    jSONObject.put(key, value);
                } catch (JSONException unused) {
                }
            }
        }
    }
}
