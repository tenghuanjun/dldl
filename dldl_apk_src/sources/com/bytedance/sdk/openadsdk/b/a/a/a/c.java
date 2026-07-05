package com.bytedance.sdk.openadsdk.b.a.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.CSJSplashAd;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final CSJSplashAd.SplashClickEyeListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public c(CSJSplashAd.SplashClickEyeListener splashClickEyeListener) {
        this.b = splashClickEyeListener;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return this.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        CSJSplashAd.SplashClickEyeListener splashClickEyeListener = this.b;
        if (splashClickEyeListener == null) {
            return null;
        }
        switch (i) {
            case 113101:
                this.b.onSplashClickEyeReadyToShow(new com.bytedance.sdk.openadsdk.c.a.a.b((Bridge) valueSet.objectValue(0, Bridge.class)));
                break;
            case 113102:
                splashClickEyeListener.onSplashClickEyeClick();
                break;
            case 113103:
                splashClickEyeListener.onSplashClickEyeClose();
                break;
        }
        a(i, valueSet, cls);
        return null;
    }
}
