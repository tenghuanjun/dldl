package com.mobile.auth.gatewayauth.utils.security;

import com.ali.security.MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class CheckRoot {
    private static String LOG_TAG;

    static {
        MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5.SLoad("pns-2.13.2.1-LogOnlineStandardCuumRelease_alijtca_plus");
        LOG_TAG = "CheckRoot";
    }

    private static native boolean checkDeviceDebuggable();

    private static native boolean checkRootPathSU();

    private static native boolean checkSuperuserApk();

    public static native String isDeviceRooted();
}
