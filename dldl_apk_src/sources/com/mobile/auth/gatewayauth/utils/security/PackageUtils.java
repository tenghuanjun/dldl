package com.mobile.auth.gatewayauth.utils.security;

import android.content.Context;
import com.ali.security.MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class PackageUtils {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static final char[] e;

    static {
        MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5.SLoad("pns-2.13.2.1-LogOnlineStandardCuumRelease_alijtca_plus");
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
