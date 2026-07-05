package com.social.sdk.share.media;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ShareImageMedia implements IShareMedia {
    private Bitmap mImage;

    @Override // com.social.sdk.share.media.IShareMedia
    public int category() {
        return 0;
    }

    public Bitmap getImage() {
        return this.mImage;
    }

    public void setImage(Bitmap bitmap) {
        this.mImage = bitmap;
    }
}
