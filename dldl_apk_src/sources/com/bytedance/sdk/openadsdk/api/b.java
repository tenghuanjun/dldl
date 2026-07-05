package com.bytedance.sdk.openadsdk.api;

import com.bykv.vk.openvk.api.proto.EventListener;
import com.bykv.vk.openvk.api.proto.Result;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b {
    protected EventListener a;

    protected void a(int i, Result result) {
        if (a()) {
            return;
        }
        this.a.onEvent(i, result);
    }

    protected void a(int i) {
        a(i, null);
    }

    protected boolean a() {
        return this.a == null;
    }
}
