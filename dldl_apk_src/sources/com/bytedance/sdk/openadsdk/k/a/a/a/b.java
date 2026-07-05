package com.bytedance.sdk.openadsdk.k.a.a.a;

import android.view.View;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTNativeAd;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final TTNativeAd.ExpressRenderListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public b(TTNativeAd.ExpressRenderListener expressRenderListener) {
        this.b = expressRenderListener;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return this.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        if (this.b == null) {
            return null;
        }
        if (i == 142101) {
            this.b.onRenderSuccess((View) valueSet.objectValue(0, View.class), valueSet.floatValue(1), valueSet.floatValue(2), valueSet.booleanValue(3));
        }
        a(i, valueSet, cls);
        return null;
    }
}
