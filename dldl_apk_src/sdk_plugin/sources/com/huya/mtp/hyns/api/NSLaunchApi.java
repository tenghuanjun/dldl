package com.huya.mtp.hyns.api;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.protocol.NSLaunchProtocol;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(NSLaunchProtocol.class)
public interface NSLaunchApi {

    public interface NSGuidListener {
        void onGuid(String str);
    }

    void addGuidListener(NSGuidListener nSGuidListener);

    String getClientIp();

    String getGuid();

    void removeGuidListener(NSGuidListener nSGuidListener);
}
