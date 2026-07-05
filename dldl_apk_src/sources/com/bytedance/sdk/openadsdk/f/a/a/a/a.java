package com.bytedance.sdk.openadsdk.f.a.a.a;

import com.bykv.a.a.a.a.b;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTAdDislike;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a implements Bridge {
    private ValueSet a = b.a;
    private final TTAdDislike.DislikeInteractionCallback b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public a(TTAdDislike.DislikeInteractionCallback dislikeInteractionCallback) {
        this.b = dislikeInteractionCallback;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return this.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        TTAdDislike.DislikeInteractionCallback dislikeInteractionCallback = this.b;
        if (dislikeInteractionCallback == null) {
            return null;
        }
        switch (i) {
            case 244101:
                dislikeInteractionCallback.onShow();
                break;
            case 244102:
                this.b.onSelected(valueSet.intValue(0), (String) valueSet.objectValue(1, String.class), valueSet.booleanValue(2));
                break;
            case 244103:
                dislikeInteractionCallback.onCancel();
                break;
        }
        a(i, valueSet, cls);
        return null;
    }
}
