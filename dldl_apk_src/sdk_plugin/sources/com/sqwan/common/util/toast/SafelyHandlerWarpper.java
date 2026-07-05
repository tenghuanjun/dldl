package com.sqwan.common.util.toast;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SafelyHandlerWarpper extends Handler {
    private Handler impl;

    public SafelyHandlerWarpper(Handler handler) {
        this.impl = handler;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        try {
            super.dispatchMessage(message);
        } catch (Exception unused) {
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        this.impl.handleMessage(message);
    }
}
