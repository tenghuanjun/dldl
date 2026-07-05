package com.bytedance.sdk.openadsdk.i.a.a.b;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTFeedAd;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final TTFeedAd.VideoRewardListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public b(TTFeedAd.VideoRewardListener videoRewardListener) {
        this.b = videoRewardListener;
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
        if (i == 163101) {
            this.b.onFeedRewardCountDown(valueSet.intValue(0));
        }
        a(i, valueSet, cls);
        return null;
    }
}
