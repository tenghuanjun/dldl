package com.huya.berry.sdklive.living.messageboard.helper;

import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ClickListenerSpan extends ClickableSpan implements View.OnClickListener {
    private final View.OnClickListener mListener;

    public ClickListenerSpan(View.OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    @Override // android.text.style.ClickableSpan, android.view.View.OnClickListener
    public void onClick(View view) {
        this.mListener.onClick(view);
    }
}
