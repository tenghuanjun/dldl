package com.unionpay;

import android.view.View;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class q implements View.OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    q(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        UPPayWapActivity.d(this.a);
    }
}
