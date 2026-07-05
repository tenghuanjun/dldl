package com.bytedance.sdk.openadsdk.live.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.android.live.base.api.ILiveHostActionParam;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b implements ILiveHostActionParam {
    private Bridge a;

    public b(Bridge bridge) {
        this.a = bridge;
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostActionParam
    public void logEvent(boolean z, String str, String str2, Map<String, String> map) {
        Bridge bridge = this.a;
        if (bridge != null) {
            bridge.call(1, com.bykv.a.a.a.a.b.a().a(0, z).a(1, str).a(2, str2).a(3, map).b(), null);
        }
    }
}
