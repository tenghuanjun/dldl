package com.bytedance.sdk.openadsdk.c.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.LocationProvider;
import com.bytedance.sdk.openadsdk.TTCustomController;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class i extends TTCustomController {
    private final Bridge a;

    public i(Bridge bridge) {
        this.a = bridge == null ? com.bykv.a.a.a.a.b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public boolean isCanUseLocation() {
        return ((Boolean) this.a.call(262101, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public LocationProvider getTTLocation() {
        return (LocationProvider) this.a.call(262102, com.bykv.a.a.a.a.b.a(0).b(), LocationProvider.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public boolean alist() {
        return ((Boolean) this.a.call(262103, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public boolean isCanUsePhoneState() {
        return ((Boolean) this.a.call(262104, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public String getDevImei() {
        return (String) this.a.call(262105, com.bykv.a.a.a.a.b.a(0).b(), String.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public boolean isCanUseWifiState() {
        return ((Boolean) this.a.call(262106, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public String getMacAddress() {
        return (String) this.a.call(262107, com.bykv.a.a.a.a.b.a(0).b(), String.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public boolean isCanUseWriteExternal() {
        return ((Boolean) this.a.call(262108, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public String getDevOaid() {
        return (String) this.a.call(262109, com.bykv.a.a.a.a.b.a(0).b(), String.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public boolean isCanUseAndroidId() {
        return ((Boolean) this.a.call(262110, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public String getAndroidId() {
        return (String) this.a.call(262112, com.bykv.a.a.a.a.b.a(0).b(), String.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTCustomController
    public boolean isCanUsePermissionRecordAudio() {
        return ((Boolean) this.a.call(262111, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }
}
