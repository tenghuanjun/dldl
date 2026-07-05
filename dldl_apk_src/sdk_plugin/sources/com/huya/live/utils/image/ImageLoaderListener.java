package com.huya.live.utils.image;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ImageLoaderListener {
    void onFail();

    void onLoadSuccess(Bitmap bitmap);
}
