package com.bytedance.bdtracker;

import android.content.Context;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.util.HardwareUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class m1 extends d1 {
    public final Context e;
    public final k1 f;
    public final i1 g;

    public m1(Context context, i1 i1Var, k1 k1Var) {
        super(false, false);
        this.e = context;
        this.f = k1Var;
        this.g = i1Var;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "DeviceParams";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        InitConfig initConfig = this.g.c;
        if (initConfig == null || initConfig.isOperatorInfoEnabled()) {
            String operatorName = HardwareUtils.getOperatorName(this.e);
            if (n0.d(operatorName)) {
                k1.a(jSONObject, "carrier", operatorName);
            }
            String operatorMccMnc = HardwareUtils.getOperatorMccMnc(this.e);
            if (n0.d(operatorMccMnc)) {
                k1.a(jSONObject, "mcc_mnc", operatorMccMnc);
            }
        }
        k1.a(jSONObject, "clientudid", ((c4) this.f.h).a());
        k1.a(jSONObject, "openudid", ((c4) this.f.h).c());
        return true;
    }
}
