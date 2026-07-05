package com.sy.window;

import android.view.MotionEvent;
import android.view.View;
import com.sy.window.WindowX;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
final class ViewTouchWrapper implements View.OnTouchListener {
    private final WindowX.OnTouchListener mListener;
    private final WindowX<?> mWindow;

    ViewTouchWrapper(WindowX<?> windowX, WindowX.OnTouchListener onTouchListener) {
        this.mWindow = windowX;
        this.mListener = onTouchListener;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        WindowX.OnTouchListener onTouchListener = this.mListener;
        if (onTouchListener == null) {
            return false;
        }
        return onTouchListener.onTouch(this.mWindow, view, motionEvent);
    }
}
