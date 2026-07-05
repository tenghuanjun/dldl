package com.nbvideo;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VideoInfo {
    public String disPlayName;
    private Map<String, String> headers;
    private String url;

    public VideoInfo(String str, Map<String, String> map) {
        this.url = str;
        this.headers = map;
    }

    public VideoInfo(String str, String str2, Map<String, String> map) {
        this(str2, map);
        this.disPlayName = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }
}
