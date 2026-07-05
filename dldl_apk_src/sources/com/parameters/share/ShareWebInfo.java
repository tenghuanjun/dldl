package com.parameters.share;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class ShareWebInfo implements IShareInfo {
    private String desc;
    private String pageUrl;
    private Bitmap thumbBmp;
    private String title;

    @Override // com.parameters.share.IShareInfo
    public int classify() {
        return 2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getPageUrl() {
        return this.pageUrl;
    }

    public void setPageUrl(String str) {
        this.pageUrl = str;
    }

    public Bitmap getThumbBmp() {
        return this.thumbBmp;
    }

    public void setThumbBmp(Bitmap bitmap) {
        this.thumbBmp = bitmap;
    }
}
