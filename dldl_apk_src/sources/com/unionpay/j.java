package com.unionpay;

import android.view.View;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class j implements View.OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    j(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.a.finish();
    }
}
