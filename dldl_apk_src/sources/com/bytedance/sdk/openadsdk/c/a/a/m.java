package com.bytedance.sdk.openadsdk.c.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.TTImage;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class m extends TTImage {
    private final Bridge a;

    public m(Bridge bridge) {
        this.a = bridge == null ? com.bykv.a.a.a.a.b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.TTImage
    public int getHeight() {
        return this.a.values().intValue(230002);
    }

    @Override // com.bytedance.sdk.openadsdk.TTImage
    public int getWidth() {
        return this.a.values().intValue(230001);
    }

    @Override // com.bytedance.sdk.openadsdk.TTImage
    public String getImageUrl() {
        return (String) this.a.values().objectValue(230003, String.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTImage
    public double getDuration() {
        return this.a.values().doubleValue(230004);
    }

    @Override // com.bytedance.sdk.openadsdk.TTImage
    public boolean isValid() {
        return this.a.values().booleanValue(230005);
    }
}
