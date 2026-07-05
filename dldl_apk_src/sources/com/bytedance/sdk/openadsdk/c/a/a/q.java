package com.bytedance.sdk.openadsdk.c.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.TTWidgetManager;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class q extends TTWidgetManager {
    private final Bridge a;

    public q(Bridge bridge) {
        this.a = bridge == null ? com.bykv.a.a.a.a.b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.TTWidgetManager
    public boolean updateWidgetWithType(int i, JSONObject jSONObject) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(2);
        bVarA.a(0, i);
        bVarA.a(1, jSONObject);
        return ((Boolean) this.a.call(264001, bVarA.b(), Boolean.TYPE)).booleanValue();
    }
}
