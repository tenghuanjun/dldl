package com.huya.berry.gamesdk.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ComnTextView extends TextView {
    public ComnTextView(Context context) {
        super(context);
    }

    public ComnTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ComnTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setComnText(CharSequence charSequence) {
        setText(charSequence);
    }
}
