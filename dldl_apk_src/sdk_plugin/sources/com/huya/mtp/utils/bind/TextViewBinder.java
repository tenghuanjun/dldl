package com.huya.mtp.utils.bind;

import android.widget.TextView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TextViewBinder extends ViewBinder<TextView, CharSequence> {
    @Override // com.huya.mtp.utils.bind.ViewBinder
    public boolean bindView(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        return true;
    }
}
