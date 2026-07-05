package com.unicom.online.account.kernel;

import com.unionpay.tsmservice.mini.data.Constant;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class x {
    public m a = null;

    public final void a(int i, String str) {
        a(i, str, "", "");
    }

    public final void a(int i, String str, String str2, String str3) {
        try {
            if (this.a == null) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constant.KEY_RESULT_CODE, i);
            jSONObject.put("resultMsg", str);
            jSONObject.put("resultData", str2);
            jSONObject.put("seq", str3);
            this.a.onResult(jSONObject.toString());
            this.a = null;
            if (i < 0) {
                new StringBuilder().append(i);
                b.a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
