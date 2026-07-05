package com.parameters.share;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class ShareImageInfo implements IShareInfo {
    private Bitmap bitmap;

    @Override // com.parameters.share.IShareInfo
    public int classify() {
        return 1;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
