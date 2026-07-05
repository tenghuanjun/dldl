package com.huya.berry.forcelive.api;

import android.app.Activity;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IForceLiveService {
    IForceLiveStream getForceLiveStream(Activity activity);

    void uninit();
}
