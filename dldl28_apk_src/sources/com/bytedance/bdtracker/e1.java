package com.bytedance.bdtracker;

import android.os.Build;
import com.bytedance.applog.InitConfig;
import com.tencent.connect.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class e1 extends d1 {
    public final i1 e;
    public final d f;

    public e1(d dVar, i1 i1Var) {
        super(true, false);
        this.f = dVar;
        this.e = i1Var;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "Build";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        InitConfig initConfig;
        jSONObject.put(Constants.PARAM_PLATFORM, "Android");
        jSONObject.put("sdk_lib", "Android");
        jSONObject.put("device_model", Build.MODEL);
        jSONObject.put("device_brand", Build.BRAND);
        jSONObject.put("device_manufacturer", Build.MANUFACTURER);
        jSONObject.put("cpu_abi", Build.CPU_ABI);
        jSONObject.put("sdk_target_version", 29);
        jSONObject.put("git_hash", "9e778e1");
        if (!o4.c.b(new Object[0]).booleanValue() || (initConfig = this.e.c) == null || !initConfig.isHarmonyEnabled()) {
            jSONObject.put("os", "Android");
            jSONObject.put("os_api", Build.VERSION.SDK_INT);
            jSONObject.put("os_version", Build.VERSION.RELEASE);
            return true;
        }
        jSONObject.put("os", "Harmony");
        try {
            jSONObject.put("os_api", q4.a("hw_sc.build.os.apiversion"));
            jSONObject.put("os_version", q4.a("hw_sc.build.platform.version"));
            return true;
        } catch (Throwable th) {
            this.f.D.error("loadHarmonyInfo failed", th, new Object[0]);
            return true;
        }
    }
}
