package com.bytedance.sdk.openadsdk.m.a.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final TTRewardVideoAd.RewardAdPlayAgainController b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public b(TTRewardVideoAd.RewardAdPlayAgainController rewardAdPlayAgainController) {
        this.b = rewardAdPlayAgainController;
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
        if (i == 122101) {
            this.b.getPlayAgainCondition(valueSet.intValue(0), new com.bytedance.sdk.openadsdk.d.a.a.a.a.a((Bridge) valueSet.objectValue(1, Bridge.class)));
        }
        a(i, valueSet, cls);
        return null;
    }
}
