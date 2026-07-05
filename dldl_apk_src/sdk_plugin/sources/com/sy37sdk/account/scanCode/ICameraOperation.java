package com.sy37sdk.account.scanCode;

import android.util.Size;
import android.view.TextureView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface ICameraOperation {

    public interface FrameCallback {
        boolean onFrameAvailable(byte[] bArr, int i, int i2, Size size);
    }

    public interface OpenFailCallback {
        void onFail(String str);
    }

    void closeCamera();

    void openCamera(TextureView textureView, FrameCallback frameCallback, OpenFailCallback openFailCallback);
}
