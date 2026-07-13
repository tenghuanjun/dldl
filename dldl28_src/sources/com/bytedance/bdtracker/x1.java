package com.bytedance.bdtracker;

import android.content.Context;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class x1 extends d1 {
    public final Context e;

    public x1(Context context) {
        super(true, false);
        this.e = context;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "SimCountry";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        TelephonyManager telephonyManager = (TelephonyManager) this.e.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        if (telephonyManager == null) {
            return true;
        }
        k1.a(jSONObject, "sim_region", telephonyManager.getSimCountryIso());
        return true;
    }
}
