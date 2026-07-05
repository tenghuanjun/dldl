package com.unionpay;

import com.unionpay.utils.UPUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class w implements ae {
    final /* synthetic */ UPPayWapActivity a;

    w(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ae
    public final void a(String str, af afVar) {
        UPUtils.b(this.a, str);
        if (afVar != null) {
            afVar.a(UPPayWapActivity.b("0", "success", (String) null));
        }
    }
}
