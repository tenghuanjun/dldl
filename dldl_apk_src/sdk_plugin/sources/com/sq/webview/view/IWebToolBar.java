package com.sq.webview.view;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IWebToolBar {
    View getBackView();

    View getForwardView();

    View getRefreshView();

    void hide(Context context);

    boolean isShow();

    void show(Context context);
}
