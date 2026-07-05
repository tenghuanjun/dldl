package com.sy.window;

import android.view.View;
import com.sy.window.WindowX;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
final class ViewLongClickWrapper implements View.OnLongClickListener {
    private final WindowX.OnLongClickListener mListener;
    private final WindowX<?> mWindow;

    ViewLongClickWrapper(WindowX<?> windowX, WindowX.OnLongClickListener onLongClickListener) {
        this.mWindow = windowX;
        this.mListener = onLongClickListener;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        WindowX.OnLongClickListener onLongClickListener = this.mListener;
        if (onLongClickListener == null) {
            return false;
        }
        return onLongClickListener.onLongClick(this.mWindow, view);
    }
}
