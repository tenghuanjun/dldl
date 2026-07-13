package com.shuyu.gsyvideoplayer.model;

/* JADX INFO: loaded from: classes3.dex */
public class GSYVideoModel {
    private String mTitle;
    private String mUrl;

    public GSYVideoModel(String str, String str2) {
        this.mUrl = str;
        this.mTitle = str2;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }
}
