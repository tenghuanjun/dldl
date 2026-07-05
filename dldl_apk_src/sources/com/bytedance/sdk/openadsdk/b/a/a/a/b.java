package com.bytedance.sdk.openadsdk.b.a.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.CSJSplashAd;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final CSJSplashAd.SplashCardListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public b(CSJSplashAd.SplashCardListener splashCardListener) {
        this.b = splashCardListener;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return this.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        CSJSplashAd.SplashCardListener splashCardListener = this.b;
        if (splashCardListener == null) {
            return null;
        }
        switch (i) {
            case 112102:
                splashCardListener.onSplashCardClick();
                break;
            case 112103:
                splashCardListener.onSplashCardClose();
                break;
            case 121201:
                this.b.onSplashCardReadyToShow(new com.bytedance.sdk.openadsdk.c.a.a.b((Bridge) valueSet.objectValue(0, Bridge.class)));
                break;
        }
        a(i, valueSet, cls);
        return null;
    }
}
