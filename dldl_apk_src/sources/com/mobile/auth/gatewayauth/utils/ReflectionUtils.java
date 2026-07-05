package com.mobile.auth.gatewayauth.utils;

import android.app.Activity;
import android.app.Application;
import com.ali.security.MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class ReflectionUtils {
    private static volatile Application sApplication;

    static {
        MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5.SLoad("pns-2.13.2.1-LogOnlineStandardCuumRelease_alijtca_plus");
        sApplication = null;
    }

    public static native Activity getActivity();

    public static native Application getApplication();
}
