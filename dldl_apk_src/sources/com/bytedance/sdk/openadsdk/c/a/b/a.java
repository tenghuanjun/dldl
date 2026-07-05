package com.bytedance.sdk.openadsdk.c.a.b;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTAdInteractionListener;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final TTAdInteractionListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public a(TTAdInteractionListener tTAdInteractionListener) {
        this.b = tTAdInteractionListener;
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
        if (i == 100101) {
            this.b.onAdEvent(valueSet.intValue(0), (Map) valueSet.objectValue(1, Map.class));
        }
        a(i, valueSet, cls);
        return null;
    }
}
