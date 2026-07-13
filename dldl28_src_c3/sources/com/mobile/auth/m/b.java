package com.mobile.auth.m;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.text.TextUtils;
import android.util.Base64;
import com.bun.miitmdid.x$;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Calendar;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class b {
    private static byte[] a;

    static String a(Context context, String str) {
        a();
        byte[] bArrB = b(context);
        if (bArrB != null) {
            return a.a(bArrB, str, a);
        }
        a();
        return null;
    }

    public static void a() {
        k.a("AES_KEY");
    }

    private static boolean a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
                x$.ExternalSyntheticApiModelOutline0.m();
                keyGenerator.init(x$.ExternalSyntheticApiModelOutline0.m(x$.ExternalSyntheticApiModelOutline0.m(x$.ExternalSyntheticApiModelOutline0.m(x$.ExternalSyntheticApiModelOutline0.m$2(x$.ExternalSyntheticApiModelOutline0.m$1(x$.ExternalSyntheticApiModelOutline0.m(x$.ExternalSyntheticApiModelOutline0.m("CMCC_SDK_V1", 3), new String[]{"SHA-256", "SHA-512"}), new String[]{"CBC"}), new String[]{"PKCS7Padding"}), false), 256)));
                Thread.sleep(1000L);
                keyGenerator.generateKey();
                return true;
            }
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 30);
            KeyPairGeneratorSpec keyPairGeneratorSpecBuild = new KeyPairGeneratorSpec.Builder(context).setAlias("CMCC_SDK_V1").setSubject(new X500Principal("CN=CMCC_SDK_V1")).setSerialNumber(BigInteger.TEN).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            keyPairGenerator.initialize(keyPairGeneratorSpecBuild);
            Thread.sleep(1000L);
            keyPairGenerator.generateKeyPair();
            return true;
        } catch (Exception e) {
            c.a("KeystoreUtil", e.getMessage());
            return false;
        }
    }

    public static boolean a(Context context, boolean z) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (keyStore.getKey("CMCC_SDK_V1", null) != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (z) {
            return a(context);
        }
        return false;
    }

    private static String b() {
        return k.b("AES_KEY", "");
    }

    static String b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] bArrB = b(context);
        if (bArrB != null) {
            return a.b(bArrB, str, a);
        }
        a();
        return null;
    }

    private static synchronized byte[] b(Context context) {
        Cipher cipher;
        String str;
        String str2;
        byte[] bArrDoFinal;
        Cipher cipher2;
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (!a(context, false)) {
                return null;
            }
            String strB = b();
            if (TextUtils.isEmpty(strB)) {
                bArrDoFinal = q.a();
                a = q.a();
                Key key = keyStore.getKey("CMCC_SDK_V1", null);
                if (key instanceof SecretKey) {
                    c.b("KeystoreUtil", "随机生成aes秘钥");
                    cipher2 = Cipher.getInstance("AES/CBC/PKCS7Padding");
                    cipher2.init(1, key, new IvParameterSpec(a));
                } else {
                    if (!(key instanceof PrivateKey)) {
                        return null;
                    }
                    PublicKey publicKey = keyStore.getCertificate("CMCC_SDK_V1").getPublicKey();
                    cipher2 = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
                    c.b("KeystoreUtil", "生成rsa密");
                    cipher2.init(1, publicKey);
                }
                String strEncodeToString = Base64.encodeToString(cipher2.doFinal(bArrDoFinal), 0);
                String strEncodeToString2 = Base64.encodeToString(a, 0);
                HashMap map = new HashMap();
                map.put("AES_IV", strEncodeToString2);
                map.put("AES_KEY", strEncodeToString);
                k.a(map);
            } else {
                a = Base64.decode(c(), 0);
                byte[] bArrDecode = Base64.decode(strB, 0);
                Key key2 = keyStore.getKey("CMCC_SDK_V1", null);
                if (key2 == null) {
                    return null;
                }
                if (key2 instanceof SecretKey) {
                    cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                    cipher.init(2, key2, new IvParameterSpec(a));
                    str = "KeystoreUtil";
                    str2 = "使用aes";
                } else {
                    if (!(key2 instanceof PrivateKey)) {
                        return null;
                    }
                    cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
                    cipher.init(2, key2);
                    str = "KeystoreUtil";
                    str2 = "使用rsa";
                }
                c.b(str, str2);
                bArrDoFinal = cipher.doFinal(bArrDecode);
                StringBuilder sb = new StringBuilder("是否解密出秘钥：");
                sb.append(!TextUtils.isEmpty(Base64.encodeToString(bArrDoFinal, 0)));
                c.b("KeystoreUtil", sb.toString());
            }
            return bArrDoFinal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String c() {
        return k.b("AES_IV", "");
    }
}
