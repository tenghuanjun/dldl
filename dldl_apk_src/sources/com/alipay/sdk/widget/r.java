package com.alipay.sdk.widget;

import android.view.View;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class r implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ q b;

    r(q qVar, View view) {
        this.b = qVar;
        this.a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.setEnabled(true);
    }
}
