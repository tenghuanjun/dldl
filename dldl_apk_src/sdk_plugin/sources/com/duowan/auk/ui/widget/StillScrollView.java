package com.duowan.auk.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class StillScrollView extends ScrollView {
    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public StillScrollView(Context context) {
        super(context);
    }

    public StillScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
