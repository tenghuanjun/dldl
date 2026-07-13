package com.mobile.auth.gatewayauth.utils;

import android.content.Context;
import com.ali.security.MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.nirvana.tools.core.CryptUtil;
import javax.crypto.KeyGenerator;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class EncryptUtils {
    public static final String IV_PARAMETER_SPEC = "0000000000000000";

    static {
        MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7.SLoad("pns-2.13.4-LogOnlineStandardCuumRelease_alijtca_plus");
    }

    private static native boolean checkMonitorUse();

    public static native String encrpytAESKey(String str);

    public static native String encrpytAESKey(String str, String str2);

    private static native String encrypt(String str, String str2);

    public static native String encryptInfoForCertifyId(Context context, String str, String str2, String str3, String str4, String str5);

    public static native String encryptToken(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9);

    public static String generateAesKey() {
        try {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(128);
                return CryptUtil.Base64.encode(keyGenerator.generateKey().getEncoded());
            } catch (Exception e) {
                i.a(e);
                return "";
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private static native String getSecret1();

    private static native String getSecret2();

    private static native String getSecret3();

    private static native String getSecret4();

    public static native String getSecret5();

    public static native String getSecret6();

    public static native String noEncryptTinfo(Context context, String str, String str2);
}
