package com.bytedance.sdk.openadsdk.g.a.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.c.a.a.p;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class f implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final TTAdNative.RewardVideoAdListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public f(TTAdNative.RewardVideoAdListener rewardVideoAdListener) {
        this.b = rewardVideoAdListener;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return this.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        TTAdNative.RewardVideoAdListener rewardVideoAdListener = this.b;
        if (rewardVideoAdListener == null) {
            return null;
        }
        switch (i) {
            case 124101:
                this.b.onError(valueSet.intValue(0), (String) valueSet.objectValue(1, String.class));
                break;
            case 124102:
                this.b.onRewardVideoAdLoad(new p((Bridge) valueSet.objectValue(0, Bridge.class)));
                break;
            case 124103:
                this.b.onRewardVideoCached(new p((Bridge) valueSet.objectValue(0, Bridge.class)));
                break;
            case 124104:
                rewardVideoAdListener.onRewardVideoCached();
                break;
        }
        a(i, valueSet, cls);
        return null;
    }
}
