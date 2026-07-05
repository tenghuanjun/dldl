package com.alipay.sdk.widget;

import android.content.DialogInterface;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class o implements DialogInterface.OnClickListener {
    final /* synthetic */ n a;

    o(n nVar) {
        this.a = nVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.b.w = true;
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, com.alipay.sdk.app.statistic.c.s, "2");
        this.a.a.proceed();
        dialogInterface.dismiss();
    }
}
