package com.mobile.auth.z;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class l {
    public d a = null;

    public final void a(int i, int i2, String str, String str2, String str3) {
        try {
            t.c("typeTokenUaid=".concat(String.valueOf(i)));
            try {
                if (this.a == null) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("resultCode", i2);
                jSONObject.put("resultMsg", str);
                jSONObject.put("resultData", str2);
                jSONObject.put("seq", str3);
                this.a.onResult(jSONObject.toString());
                this.a = null;
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
        ExceptionProcessor.processException(th);
    }
}
