package com.sq.webview.view;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface JSPageOperation {
    void close();

    void invalidateBack(boolean enable);

    void refresh();

    void refreshTitleBar(boolean visible);

    void refreshToolBar(boolean visible);
}
