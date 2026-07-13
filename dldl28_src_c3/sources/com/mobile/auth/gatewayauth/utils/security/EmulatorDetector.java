package com.mobile.auth.gatewayauth.utils.security;

import android.content.Context;
import com.ali.security.MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class EmulatorDetector {
    private static final String TAG = "EmulatorDetector";
    private static int rating;

    static {
        MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7.SLoad("pns-2.13.4-LogOnlineStandardCuumRelease_alijtca_plus");
        rating = -1;
    }

    private static final native String getProp(Context context, String str);

    public static native boolean isEmulator(Context context);

    private static native boolean isEmulatorAbsoluly(Context context);

    private static final native boolean mayOnEmulatorViaQEMU(Context context);
}
