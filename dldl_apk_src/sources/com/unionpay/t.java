package com.unionpay;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class t implements ae {
    final /* synthetic */ UPPayWapActivity a;

    t(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ae
    public final void a(String str, af afVar) {
        String strA = UPPayAssistEx.a(this.a);
        if (afVar != null) {
            afVar.a(UPPayWapActivity.b("0", "success", strA));
        }
    }
}
