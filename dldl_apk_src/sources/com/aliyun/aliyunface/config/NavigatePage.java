package com.aliyun.aliyunface.config;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class NavigatePage {
    public static final String DEFAULT_URL = "https://render.alipay.com/p/f/fd-j8l9yjja/index.html";
    private boolean enable = false;
    private String url = DEFAULT_URL;

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }

    public String toString() {
        return "NavigatePage{enable=" + this.enable + ", url='" + this.url + "'}";
    }
}
