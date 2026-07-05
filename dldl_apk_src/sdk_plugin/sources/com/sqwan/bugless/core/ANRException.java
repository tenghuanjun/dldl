package com.sqwan.bugless.core;

import android.os.Looper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class ANRException extends RuntimeException {
    ANRException() {
        super("The application is not responding, come and change the BUG!！！");
        setStackTrace(Looper.getMainLooper().getThread().getStackTrace());
    }
}
