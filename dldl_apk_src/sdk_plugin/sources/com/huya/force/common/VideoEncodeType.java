package com.huya.force.common;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum VideoEncodeType {
    kH264,
    kH265;

    public String mineType() {
        return this == kH264 ? "video/avc" : this == kH265 ? "video/hevc" : "";
    }
}
