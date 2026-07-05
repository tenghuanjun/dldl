package com.duowan.ark.bind;

import android.widget.TextView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TextViewBinder extends ViewBinder<TextView, CharSequence> {
    @Override // com.duowan.ark.bind.ViewBinder
    public boolean bindView(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        return true;
    }
}
