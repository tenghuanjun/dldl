package com.bytedance.sdk.openadsdk.c.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.DownloadStatusController;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class e implements DownloadStatusController {
    private final Bridge a;

    public e(Bridge bridge) {
        this.a = bridge == null ? com.bykv.a.a.a.a.b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.DownloadStatusController
    public void changeDownloadStatus() {
        this.a.call(222101, com.bykv.a.a.a.a.b.a(0).b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.DownloadStatusController
    public void cancelDownload() {
        this.a.call(222102, com.bykv.a.a.a.a.b.a(0).b(), Void.class);
    }
}
