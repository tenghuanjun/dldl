package com.bytedance.sdk.openadsdk.i.a.a.a;

import com.bykv.a.a.a.a.b;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.TTFeedAd;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a implements TTFeedAd.CustomizeVideo {
    private final Bridge a;

    public a(Bridge bridge) {
        this.a = bridge == null ? b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.TTFeedAd.CustomizeVideo
    public String getVideoUrl() {
        return (String) this.a.call(162101, b.a(0).b(), String.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTFeedAd.CustomizeVideo
    public void reportVideoStart() {
        this.a.call(162102, b.a(0).b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTFeedAd.CustomizeVideo
    public void reportVideoPause(long j) {
        b bVarA = b.a(1);
        bVarA.a(0, j);
        this.a.call(162103, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTFeedAd.CustomizeVideo
    public void reportVideoContinue(long j) {
        b bVarA = b.a(1);
        bVarA.a(0, j);
        this.a.call(162104, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTFeedAd.CustomizeVideo
    public void reportVideoFinish() {
        this.a.call(162105, b.a(0).b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTFeedAd.CustomizeVideo
    public void reportVideoBreak(long j) {
        b bVarA = b.a(1);
        bVarA.a(0, j);
        this.a.call(162106, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTFeedAd.CustomizeVideo
    public void reportVideoAutoStart() {
        this.a.call(162107, b.a(0).b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTFeedAd.CustomizeVideo
    public void reportVideoStartError(int i, int i2) {
        b bVarA = b.a(2);
        bVarA.a(0, i);
        bVarA.a(1, i2);
        this.a.call(162108, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTFeedAd.CustomizeVideo
    public void reportVideoError(long j, int i, int i2) {
        b bVarA = b.a(3);
        bVarA.a(0, j);
        bVarA.a(1, i);
        bVarA.a(2, i2);
        this.a.call(162109, bVarA.b(), Void.class);
    }
}
