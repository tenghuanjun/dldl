package com.bytedance.sdk.openadsdk.c.a.c;

import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.AdConfig;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a {
    public static final ValueSet a(final AdConfig adConfig) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a();
        if (adConfig == null) {
            return null;
        }
        bVarA.a(261001, adConfig.getAppId());
        bVarA.a(261002, adConfig.getAppName());
        bVarA.a(261003, adConfig.isPaid());
        bVarA.a(261004, adConfig.getKeywords());
        bVarA.a(261005, adConfig.getData());
        bVarA.a(261006, adConfig.getTitleBarTheme());
        bVarA.a(261007, adConfig.isAllowShowNotify());
        bVarA.a(261008, adConfig.isDebug());
        bVarA.a(261009, adConfig.getDirectDownloadNetworkType());
        bVarA.a(261011, adConfig.isSupportMultiProcess());
        bVarA.a(261012, adConfig.getCustomController() != null ? d.a(adConfig.getCustomController()) : null);
        bVarA.a(261013, new ValueSet.ValueGetter<Integer>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.a.1
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer get() {
                return Integer.valueOf(adConfig.getPluginUpdateConfig());
            }
        });
        bVarA.a(261014, new ValueSet.ValueGetter<Integer>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.a.2
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer get() {
                return Integer.valueOf(adConfig.getAgeGroup());
            }
        });
        bVarA.a(261015, new ValueSet.ValueGetter<Integer>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.a.3
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer get() {
                return Integer.valueOf(adConfig.getThemeStatus());
            }
        });
        bVarA.a(261018, new ValueSet.ValueGetter<Map>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.a.4
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Map get() {
                return adConfig.getInitExtra();
            }
        });
        return bVarA.b();
    }
}
