package com.alipay.sdk.widget;

import android.content.DialogInterface;
import android.view.KeyEvent;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
final class f implements DialogInterface.OnKeyListener {
    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    f() {
    }
}
