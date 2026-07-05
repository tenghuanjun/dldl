package com.huya.mtp.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WeakRefHandler extends Handler {
    private WeakReference<Handler.Callback> mReference;

    public WeakRefHandler(Handler.Callback callback) {
        this(Looper.getMainLooper(), callback);
    }

    public WeakRefHandler(Looper looper, Handler.Callback callback) {
        super(looper);
        this.mReference = new WeakReference<>(callback);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Handler.Callback callback = this.mReference.get();
        if (callback != null) {
            callback.handleMessage(message);
        }
    }
}
