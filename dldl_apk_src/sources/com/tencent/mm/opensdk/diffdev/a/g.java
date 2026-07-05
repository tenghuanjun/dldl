package com.tencent.mm.opensdk.diffdev.a;

import com.bytedance.sdk.openadsdk.TTAdConstant;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public enum g {
    UUID_EXPIRED(TTAdConstant.DEEPLINK_UNAVAILABLE_CODE),
    UUID_CANCELED(TTAdConstant.DEEPLINK_FALLBACK_TYPE_ERROR_CODE),
    UUID_SCANED(TTAdConstant.SDK_NOT_SUPPORT_LIVE_MATE_CODE),
    UUID_CONFIRM(TTAdConstant.LANDING_PAGE_TYPE_CODE),
    UUID_KEEP_CONNECT(TTAdConstant.INTERACTION_TYPE_CODE),
    UUID_ERROR(TTAdConstant.SHOW_POLL_TIME_SPLASH_DEFAULT);

    private int code;

    g(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "UUIDStatusCode:" + this.code;
    }
}
