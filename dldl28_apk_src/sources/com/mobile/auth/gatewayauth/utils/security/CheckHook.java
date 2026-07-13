package com.mobile.auth.gatewayauth.utils.security;

import com.ali.security.MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7;

/* JADX INFO: loaded from: classes3.dex */
public class CheckHook {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f717a;
    private static int b;

    static {
        MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7.SLoad("pns-2.13.4-LogOnlineStandardCuumRelease_alijtca_plus");
        f717a = -1;
        b = -1;
    }

    public static native synchronized boolean isHookByJar();

    public static native synchronized boolean isHookByStack();
}
