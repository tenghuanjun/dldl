package com.mobile.auth.gatewayauth.utils.security;

import com.ali.security.MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7;

/* JADX INFO: loaded from: classes3.dex */
public class CheckRoot {
    private static String LOG_TAG;

    static {
        MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7.SLoad("pns-2.13.4-LogOnlineStandardCuumRelease_alijtca_plus");
        LOG_TAG = "CheckRoot";
    }

    private static native boolean checkDeviceDebuggable();

    private static native boolean checkRootPathSU();

    private static native boolean checkSuperuserApk();

    public static native String isDeviceRooted();
}
