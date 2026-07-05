package com.bytedance.sdk.openadsdk;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface TTClientBidding {
    void loss(Double d, String str, String str2);

    void setAdInteractionListener(TTAdInteractionListener tTAdInteractionListener);

    void setPrice(Double d);

    void win(Double d);
}
