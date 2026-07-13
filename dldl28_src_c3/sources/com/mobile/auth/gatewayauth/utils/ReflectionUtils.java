package com.mobile.auth.gatewayauth.utils;

import android.app.Activity;
import android.app.Application;
import com.ali.security.MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ReflectionUtils {
    private static volatile Application sApplication;

    static {
        MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7.SLoad("pns-2.13.4-LogOnlineStandardCuumRelease_alijtca_plus");
        sApplication = null;
    }

    public static native Activity getActivity();

    public static native Application getApplication();
}
