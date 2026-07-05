package com.sy.window;

import android.view.View;
import com.sy.window.WindowX;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
final class ViewClickWrapper implements View.OnClickListener {
    private final WindowX.OnClickListener mListener;
    private final WindowX<?> mWindow;

    ViewClickWrapper(WindowX<?> windowX, WindowX.OnClickListener onClickListener) {
        this.mWindow = windowX;
        this.mListener = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WindowX.OnClickListener onClickListener = this.mListener;
        if (onClickListener == null) {
            return;
        }
        onClickListener.onClick(this.mWindow, view);
    }
}
