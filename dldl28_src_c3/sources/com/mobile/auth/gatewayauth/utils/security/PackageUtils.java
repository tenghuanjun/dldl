package com.mobile.auth.gatewayauth.utils.security;

import android.content.Context;
import com.ali.security.MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class PackageUtils {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static final char[] e;

    static {
        MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7.SLoad("pns-2.13.4-LogOnlineStandardCuumRelease_alijtca_plus");
        a = null;
        b = null;
        c = null;
        d = null;
        e = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    public static native synchronized String getPackageName(Context context);

    public static native synchronized String getSign(Context context);

    public static native synchronized String getVersionName(Context context);

    public static native String hexdigest(byte[] bArr);

    private static native void setupAppInfo(Context context);
}
