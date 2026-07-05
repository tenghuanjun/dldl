package com.sq.webview.view;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IWebViewLoading {
    void onProgress(Context context, int progress);

    void startLoading(Context context);

    void stopLoading(Context context);
}
