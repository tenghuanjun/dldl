package com.alipay.sdk.app;

import android.content.DialogInterface;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class e implements DialogInterface.OnClickListener {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.b.cancel();
        this.a.c.b = false;
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, com.alipay.sdk.app.statistic.c.t, "1");
        j.a(j.c());
        this.a.a.finish();
    }
}
