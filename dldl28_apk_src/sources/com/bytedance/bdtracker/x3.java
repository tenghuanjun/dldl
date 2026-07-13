package com.bytedance.bdtracker;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.bdtracker.i4;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class x3 extends j3 {
    public static final JSONObject s;

    static {
        JSONObject jSONObject = new JSONObject();
        s = jSONObject;
        try {
            jSONObject.put("_staging_flag", 1);
            jSONObject.put("params_for_special", "applog_trace");
        } catch (Throwable th) {
            LoggerImpl.global().error(4, Collections.singletonList("trace"), "JSON handle failed", th, new Object[0]);
        }
    }

    @Override // com.bytedance.bdtracker.j3
    public String f() {
        return "trace";
    }

    @Override // com.bytedance.bdtracker.j3
    public JSONObject i() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.c);
        jSONObject.put("tea_event_index", this.d);
        jSONObject.put(MonitorCommonConstants.KEY_SESSION_ID, this.e);
        long j = this.f;
        if (j > 0) {
            jSONObject.put("user_id", j);
        }
        jSONObject.put("user_unique_id", TextUtils.isEmpty(this.g) ? JSONObject.NULL : this.g);
        if (!TextUtils.isEmpty(this.h)) {
            jSONObject.put("$user_unique_id_type", this.h);
        }
        if (!TextUtils.isEmpty(this.i)) {
            jSONObject.put("ssid", this.i);
        }
        jSONObject.put(NotificationCompat.CATEGORY_EVENT, "rangersapplog_trace");
        a(jSONObject, s);
        int i = this.k;
        if (i != i4.a.UNKNOWN.f267a) {
            jSONObject.put("nt", i);
        }
        jSONObject.put("datetime", this.n);
        return jSONObject;
    }
}
