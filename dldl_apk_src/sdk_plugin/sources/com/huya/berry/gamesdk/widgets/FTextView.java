package com.huya.berry.gamesdk.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FTextView extends TextView {
    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public FTextView(Context context) {
        super(context);
        init();
    }

    public FTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public FTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
    }
}
