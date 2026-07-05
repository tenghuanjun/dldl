package com.unionpay;

import android.content.DialogInterface;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class s implements DialogInterface.OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    s(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.d.dismiss();
    }
}
