package com.huya.berry.sdklive.liveTool;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SessionLinearLayout extends FrameLayout {
    private DispatchKeyEventListener mDispatchKeyEventListener;

    public interface DispatchKeyEventListener {
        boolean dispatchKeyEvent(KeyEvent keyEvent);
    }

    public SessionLinearLayout(Context context) {
        super(context);
    }

    public SessionLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SessionLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mDispatchKeyEventListener != null && keyEvent.getKeyCode() == 4) {
            return this.mDispatchKeyEventListener.dispatchKeyEvent(keyEvent);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public DispatchKeyEventListener getDispatchKeyEventListener() {
        return this.mDispatchKeyEventListener;
    }

    public void setDispatchKeyEventListener(DispatchKeyEventListener dispatchKeyEventListener) {
        this.mDispatchKeyEventListener = dispatchKeyEventListener;
    }
}
