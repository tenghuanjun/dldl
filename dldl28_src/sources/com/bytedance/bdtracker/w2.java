package com.bytedance.bdtracker;

import android.os.Bundle;
import com.bytedance.applog.AppLog;
import com.volcengine.onekit.service.Analytics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class w2 implements Analytics {
    public void onEventV3(String str, Bundle bundle) {
        AppLog.onEventV3(str, bundle);
    }

    public void onEventV3(String str, JSONObject jSONObject) {
        AppLog.onEventV3(str, jSONObject);
    }
}
