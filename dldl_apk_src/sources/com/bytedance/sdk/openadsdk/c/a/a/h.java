package com.bytedance.sdk.openadsdk.c.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.TTAdDislike;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class h implements TTAdDislike {
    private final Bridge a;

    public h(Bridge bridge) {
        this.a = bridge == null ? com.bykv.a.a.a.a.b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdDislike
    public void showDislikeDialog() {
        this.a.call(240101, com.bykv.a.a.a.a.b.a(0).b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdDislike
    public void setDislikeInteractionCallback(TTAdDislike.DislikeInteractionCallback dislikeInteractionCallback) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.f.a.a.a.a(dislikeInteractionCallback));
        this.a.call(240102, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdDislike
    public void setDislikeSource(String str) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, str);
        this.a.call(240103, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdDislike
    public void resetDislikeStatus() {
        this.a.call(240104, com.bykv.a.a.a.a.b.a(0).b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdDislike
    public boolean isShow() {
        return ((Boolean) this.a.call(240105, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }
}
