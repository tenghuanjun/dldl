package com.bytedance.bdtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.applog.ISensitiveInfoProvider;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.util.SensitiveUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class u1 extends d1 {
    public final ISensitiveInfoProvider e;
    public final Context f;
    public final i1 g;
    public final k1 h;

    public u1(Context context, i1 i1Var, k1 k1Var, ISensitiveInfoProvider iSensitiveInfoProvider) {
        super(true, false);
        this.e = iSensitiveInfoProvider;
        this.f = context;
        this.g = i1Var;
        this.h = k1Var;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "SensitiveLoader";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        String[] strArrE;
        jSONObject.put(SensitiveUtils.KEY_BUILD_SERIAL, SensitiveUtils.getSerialNumber(this.f));
        k1.a(jSONObject, SensitiveUtils.KEY_ALIYUN_UUID, this.g.c.getAliyunUdid());
        if (this.g.c.isMacEnable()) {
            String macAddress = SensitiveUtils.getMacAddress(this.e, this.f);
            SharedPreferences sharedPreferences = this.g.f;
            String string = sharedPreferences.getString(SensitiveUtils.KEY_MAC, null);
            if (!TextUtils.isEmpty(macAddress)) {
                if (!TextUtils.equals(string, macAddress)) {
                    a.a(sharedPreferences, SensitiveUtils.KEY_MAC, macAddress);
                }
                jSONObject.put(SensitiveUtils.KEY_MC, macAddress);
            } else if (!TextUtils.isEmpty(string)) {
                jSONObject.put(SensitiveUtils.KEY_MC, string);
            }
        }
        k1.a(jSONObject, "udid", ((c4) this.h.h).f());
        JSONArray jSONArrayG = ((c4) this.h.h).g();
        if (SensitiveUtils.validMultiImei(jSONArrayG)) {
            jSONObject.put("udid_list", jSONArrayG);
        }
        k1.a(jSONObject, "serial_number", ((c4) this.h.h).d());
        InitConfig initConfig = this.g.c;
        if (initConfig == null || !initConfig.isIccIdEnabled() || !this.h.r() || (strArrE = ((c4) this.h.h).e()) == null) {
            return true;
        }
        JSONArray jSONArray = new JSONArray();
        for (String str : strArrE) {
            jSONArray.put(new JSONObject().put("sim_serial_number", str));
        }
        jSONObject.put("sim_serial_number", jSONArray);
        return true;
    }
}
