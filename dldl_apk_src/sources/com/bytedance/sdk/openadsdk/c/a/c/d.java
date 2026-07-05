package com.bytedance.sdk.openadsdk.c.a.c;

import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTCustomController;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class d {
    public static final ValueSet a(final TTCustomController tTCustomController) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a();
        if (tTCustomController == null) {
            return null;
        }
        bVarA.a(262101, new ValueSet.ValueGetter<Boolean>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.1
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean get() {
                return Boolean.valueOf(tTCustomController.isCanUseLocation());
            }
        });
        bVarA.a(262102, new ValueSet.ValueGetter<ValueSet>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.5
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public ValueSet get() {
                return c.a(tTCustomController.getTTLocation());
            }
        });
        bVarA.a(262103, new ValueSet.ValueGetter<Boolean>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.6
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean get() {
                return Boolean.valueOf(tTCustomController.alist());
            }
        });
        bVarA.a(262104, new ValueSet.ValueGetter<Boolean>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.7
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean get() {
                return Boolean.valueOf(tTCustomController.isCanUsePhoneState());
            }
        });
        bVarA.a(262105, new ValueSet.ValueGetter<String>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.8
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public String get() {
                return tTCustomController.getDevImei();
            }
        });
        bVarA.a(262106, new ValueSet.ValueGetter<Boolean>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.9
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean get() {
                return Boolean.valueOf(tTCustomController.isCanUseWifiState());
            }
        });
        bVarA.a(262107, new ValueSet.ValueGetter<String>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.10
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public String get() {
                return tTCustomController.getMacAddress();
            }
        });
        bVarA.a(262108, new ValueSet.ValueGetter<Boolean>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.11
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean get() {
                return Boolean.valueOf(tTCustomController.isCanUseWriteExternal());
            }
        });
        bVarA.a(262109, new ValueSet.ValueGetter<String>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.12
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public String get() {
                return tTCustomController.getDevOaid();
            }
        });
        bVarA.a(262110, new ValueSet.ValueGetter<Boolean>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.2
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean get() {
                return Boolean.valueOf(tTCustomController.isCanUseAndroidId());
            }
        });
        bVarA.a(262112, new ValueSet.ValueGetter<String>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.3
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public String get() {
                return tTCustomController.getAndroidId();
            }
        });
        bVarA.a(262111, new ValueSet.ValueGetter<Boolean>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.d.4
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean get() {
                return Boolean.valueOf(tTCustomController.isCanUsePermissionRecordAudio());
            }
        });
        return bVarA.b();
    }
}
