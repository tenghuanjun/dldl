package com.bytedance.sdk.openadsdk.c.a.c;

import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.LocationProvider;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c {
    public static final ValueSet a(LocationProvider locationProvider) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a();
        if (locationProvider == null) {
            return null;
        }
        bVarA.a(262001, locationProvider.getLatitude());
        bVarA.a(262002, locationProvider.getLongitude());
        return bVarA.b();
    }
}
