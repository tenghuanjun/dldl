package com.social.sdk.share.media;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ShareWebMedia implements IShareMedia {
    private String mDescription;
    private Bitmap mThumb;
    private String mTitle;
    private String mWebPageUrl;

    @Override // com.social.sdk.share.media.IShareMedia
    public int category() {
        return 1;
    }

    public String getWebPageUrl() {
        return this.mWebPageUrl;
    }

    public void setWebPageUrl(String str) {
        this.mWebPageUrl = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public Bitmap getThumb() {
        return this.mThumb;
    }

    public void setThumb(Bitmap bitmap) {
        this.mThumb = bitmap;
    }
}
