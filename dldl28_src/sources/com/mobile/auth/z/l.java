package com.mobile.auth.z;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f826a = null;

    public final void a(int i, int i2, String str, String str2, String str3) {
        try {
            t.c("typeTokenUaid=".concat(String.valueOf(i)));
            try {
                if (this.f826a == null) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("resultCode", i2);
                jSONObject.put("resultMsg", str);
                jSONObject.put("resultData", str2);
                jSONObject.put("seq", str3);
                this.f826a.onResult(jSONObject.toString());
                this.f826a = null;
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
