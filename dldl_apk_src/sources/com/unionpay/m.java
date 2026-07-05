package com.unionpay;

import android.content.Intent;
import android.os.Bundle;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class m implements ae {
    final /* synthetic */ UPPayWapActivity a;

    m(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ae
    public final void a(String str, af afVar) {
        String str2;
        String str3 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            try {
                str2 = (String) jSONObject.get("url");
            } catch (Exception e) {
                e = e;
                str2 = "";
            }
            try {
                str3 = (String) jSONObject.get("title");
            } catch (Exception e2) {
                e = e2;
                if (afVar != null) {
                    afVar.a(UPPayWapActivity.b("1", e.getMessage(), (String) null));
                }
            }
            Bundle bundle = new Bundle();
            bundle.putString("waptype", "new_page");
            bundle.putString("magic_data", "949A1CC");
            bundle.putString("wapurl", str2);
            bundle.putString("waptitle", str3);
            bundle.putString("actionType", this.a.g);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(this.a, UPPayWapActivity.class);
            this.a.startActivity(intent);
            if (afVar != null) {
                afVar.a(UPPayWapActivity.b("0", "success", (String) null));
            }
        } catch (Exception e3) {
            if (afVar != null) {
                afVar.a(UPPayWapActivity.b("1", e3.getMessage(), (String) null));
            }
        }
    }
}
