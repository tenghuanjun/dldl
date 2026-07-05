package com.sqinject.core;

import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class DebouncingOnClickListener implements View.OnClickListener {
    public abstract void doClick(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        doClick(view);
    }
}
