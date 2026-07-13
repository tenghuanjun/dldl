package com.bytedance.bdtracker;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.applog.util.SensitiveUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class b1 extends d1 {
    public final Context e;

    public b1(Context context) {
        super(true, false);
        this.e = context;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "AppKey";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) {
        try {
            Bundle bundle = this.e.getPackageManager().getApplicationInfo(this.e.getPackageName(), 128).metaData;
            if (bundle == null || TextUtils.isEmpty(SensitiveUtils.CHANNEL_APP_KEY)) {
                return true;
            }
            jSONObject.put("appkey", bundle.getString(SensitiveUtils.CHANNEL_APP_KEY));
            return true;
        } catch (Throwable th) {
            LoggerImpl.global().error("Load app key failed.", th, new Object[0]);
            return true;
        }
    }
}
