package com.bytedance.sdk.openadsdk.c.a.a;

import android.app.Activity;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdInteractionListener;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class p implements TTRewardVideoAd {
    private final Bridge a;

    public p(Bridge bridge) {
        this.a = bridge == null ? com.bykv.a.a.a.a.b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.TTClientBidding
    public void win(Double d) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, d);
        this.a.call(210101, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTClientBidding
    public void loss(Double d, String str, String str2) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(3);
        bVarA.a(0, d);
        bVarA.a(1, str);
        bVarA.a(2, str2);
        this.a.call(210102, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTClientBidding
    public void setPrice(Double d) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, d);
        this.a.call(210103, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTClientBidding
    public void setAdInteractionListener(TTAdInteractionListener tTAdInteractionListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.c.a.b.a(tTAdInteractionListener));
        this.a.call(210104, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public void setRewardAdInteractionListener(TTRewardVideoAd.RewardAdInteractionListener rewardAdInteractionListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.m.a.a.a.a(rewardAdInteractionListener));
        this.a.call(120101, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public void setRewardPlayAgainInteractionListener(TTRewardVideoAd.RewardAdInteractionListener rewardAdInteractionListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.m.a.a.a.a(rewardAdInteractionListener));
        this.a.call(120102, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public void setRewardPlayAgainController(TTRewardVideoAd.RewardAdPlayAgainController rewardAdPlayAgainController) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.m.a.a.a.b(rewardAdPlayAgainController));
        this.a.call(120103, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public void setDownloadListener(TTAppDownloadListener tTAppDownloadListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.c.a.b.b(tTAppDownloadListener));
        this.a.call(120104, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public void showRewardVideoAd(Activity activity) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, activity);
        this.a.call(120105, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public void showRewardVideoAd(Activity activity, TTAdConstant.RitScenes ritScenes, String str) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(3);
        bVarA.a(0, activity);
        bVarA.a(1, ritScenes);
        bVarA.a(2, str);
        this.a.call(120106, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public void setShowDownLoadBar(boolean z) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, z);
        this.a.call(120107, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public int getInteractionType() {
        return this.a.values().intValue(120001);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public Map<String, Object> getMediaExtraInfo() {
        return (Map) this.a.values().objectValue(120002, Map.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public int getRewardVideoAdType() {
        return this.a.values().intValue(120003);
    }

    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd
    public long getExpirationTimestamp() {
        return this.a.values().longValue(120004);
    }
}
