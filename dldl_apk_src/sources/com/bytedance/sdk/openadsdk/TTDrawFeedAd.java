package com.bytedance.sdk.openadsdk;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface TTDrawFeedAd extends TTFeedAd {

    public interface DrawVideoListener {
        void onClick();

        void onClickRetry();
    }

    void setCanInterruptVideoPlay(boolean z);

    void setDrawVideoListener(DrawVideoListener drawVideoListener);

    void setPauseIcon(Bitmap bitmap, int i);
}
