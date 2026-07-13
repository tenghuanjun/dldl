package com.google.android.material.shadow;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface ShadowViewDelegate {
    float getRadius();

    boolean isCompatPaddingEnabled();

    void setBackgroundDrawable(Drawable drawable);

    void setShadowPadding(int i, int i2, int i3, int i4);
}
