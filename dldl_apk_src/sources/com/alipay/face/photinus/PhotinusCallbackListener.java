package com.alipay.face.photinus;

import android.net.Uri;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface PhotinusCallbackListener {
    void onDisplayRGB(int i);

    void onEncoderErrorReport(String str);

    void onFilesReady(Uri uri, Uri uri2);

    void onHasEnoughFrames();

    void onLockCameraParameterRequest();

    void onTakePhotoErrorReport(String str);
}
