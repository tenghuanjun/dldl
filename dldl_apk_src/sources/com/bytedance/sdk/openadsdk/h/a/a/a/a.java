package com.bytedance.sdk.openadsdk.h.a.a.a;

import com.bykv.a.a.a.a.b;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTDrawFeedAd;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a implements Bridge {
    private ValueSet a = b.a;
    private final TTDrawFeedAd.DrawVideoListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public a(TTDrawFeedAd.DrawVideoListener drawVideoListener) {
        this.b = drawVideoListener;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return this.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        TTDrawFeedAd.DrawVideoListener drawVideoListener = this.b;
        if (drawVideoListener == null) {
            return null;
        }
        switch (i) {
            case 171101:
                drawVideoListener.onClick();
                break;
            case 171102:
                drawVideoListener.onClickRetry();
                break;
        }
        a(i, valueSet, cls);
        return null;
    }
}
