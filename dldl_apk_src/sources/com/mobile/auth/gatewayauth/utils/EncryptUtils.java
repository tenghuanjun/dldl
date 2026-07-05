package com.mobile.auth.gatewayauth.utils;

import android.content.Context;
import com.ali.security.MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class EncryptUtils {
    public static final String IV_PARAMETER_SPEC = "0000000000000000";

    static {
        MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5.SLoad("pns-2.13.2.1-LogOnlineStandardCuumRelease_alijtca_plus");
    }

    public static native String encrpytAESKey(String str);

    public static native String encrpytAESKey(String str, String str2);

    private static native String encrypt(String str, String str2);

    public static native String encryptInfoForCertifyId(Context context, String str, String str2, String str3, String str4, String str5);

    public static native String encryptToken(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9);

    public static native String generateAesKey();

    private static native String getSecret1();

    private static native String getSecret2();

    private static native String getSecret3();

    private static native String getSecret4();

    public static native String getSecret5();

    public static native String getSecret6();

    public static native String noEncryptTinfo(Context context, String str, String str2);
}
