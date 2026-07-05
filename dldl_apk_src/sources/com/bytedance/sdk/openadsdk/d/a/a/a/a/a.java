package com.bytedance.sdk.openadsdk.d.a.a.a.a;

import android.os.Bundle;
import com.bykv.a.a.a.a.b;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a implements TTRewardVideoAd.RewardAdPlayAgainController.Callback {
    private final Bridge a;

    public a(Bridge bridge) {
        this.a = bridge == null ? b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdPlayAgainController.Callback
    public void onConditionReturn(Bundle bundle) {
        b bVarA = b.a(1);
        bVarA.a(0, bundle);
        this.a.call(123101, bVarA.b(), Void.class);
    }
}
