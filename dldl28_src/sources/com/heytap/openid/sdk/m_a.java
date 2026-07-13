package com.heytap.openid.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import androidx.autofill.HintConstants;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;
import org.apache.commons.lang3.CharEncoding;

/* JADX INFO: loaded from: classes3.dex */
public class m_a {
    public static final ThreadPoolExecutor m_a = new ThreadPoolExecutor(0, 3, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(2048), new ThreadPoolExecutor.DiscardPolicy());
    public static String m_b = HintConstants.AUTOFILL_HINT_PHONE;

    public static int m_a(int i) {
        return (i > m_e.m_a || i <= 0) ? 10001 : 10000;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String m_a(android.content.Context r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.openid.sdk.m_a.m_a(android.content.Context, java.lang.String):java.lang.String");
    }

    public static String m_a(Context context, String str, String str2) {
        String str3;
        Signature[] signatureArr;
        String str4;
        try {
            signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e = e;
            str3 = "1011";
            m_h.m_a(str3, e);
            signatureArr = null;
        } catch (Exception e2) {
            e = e2;
            str3 = "1082";
            m_h.m_a(str3, e);
            signatureArr = null;
        }
        if (signatureArr == null) {
            return null;
        }
        for (Signature signature : signatureArr) {
            if ("SHA1".equals(str2)) {
                byte[] byteArray = signature.toByteArray();
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                    if (messageDigest == null) {
                        return null;
                    }
                    byte[] bArrDigest = messageDigest.digest(byteArray);
                    StringBuilder sb = new StringBuilder();
                    for (byte b : bArrDigest) {
                        sb.append(Integer.toHexString((b & UByte.MAX_VALUE) | 256).substring(1, 3));
                    }
                    return sb.toString();
                } catch (NoSuchAlgorithmException e3) {
                    e = e3;
                    str4 = "1012";
                    m_h.m_a(str4, e);
                    return null;
                } catch (Exception e4) {
                    e = e4;
                    str4 = "1083";
                    m_h.m_a(str4, e);
                    return null;
                }
            }
        }
        return null;
    }

    public static String m_a(String str) {
        try {
            String str2 = new String(Base64.decode(str, 0));
            m_h.m_a("4025: ".concat(str2));
            return str2;
        } catch (Exception e) {
            m_h.m_a("4025: " + e.toString());
            return null;
        }
    }

    public static void m_a(Context context) {
        String str;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.hasSystemFeature("android.hardware.type.watch")) {
            str = "watch";
        } else if (!packageManager.hasSystemFeature("android.software.leanback")) {
            return;
        } else {
            str = "tv";
        }
        m_b = str;
    }

    public static void m_a(Context context, Map<String, m_f> map) {
        String str;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("cache", 0);
            m_a(sharedPreferences, map, "GUID", "GUID_TIME", "GUID_IV");
            m_a(sharedPreferences, map, "APID", "APID_TIME", "APID_IV");
            m_a(sharedPreferences, map, "DUID", "DUID_TIME");
            m_a(sharedPreferences, map, "AUID", "AUID_TIME");
        } catch (IllegalStateException e) {
            e = e;
            str = "1020";
            m_h.m_a(str, e);
        } catch (Exception e2) {
            e = e2;
            str = "1064";
            m_h.m_a(str, e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void m_a(android.content.SharedPreferences.Editor r4, com.heytap.openid.sdk.m_f r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "U3RkSWRBcHBLZXk="
            java.lang.String r0 = m_a(r0)
            java.lang.String r1 = r5.m_a
            byte[] r1 = r1.getBytes()
            java.lang.String r2 = "AES/GCM/NoPadding"
            javax.crypto.Cipher r2 = javax.crypto.Cipher.getInstance(r2)     // Catch: java.lang.InstantiationError -> L34 java.lang.Exception -> L3c
            javax.crypto.SecretKey r0 = com.heytap.openid.sdk.m_g.m_b(r0)     // Catch: java.lang.InstantiationError -> L34 java.lang.Exception -> L3c
            if (r0 != 0) goto L19
            goto L42
        L19:
            r3 = 1
            r2.init(r3, r0)     // Catch: java.lang.InstantiationError -> L34 java.lang.Exception -> L3c
            byte[] r0 = r2.doFinal(r1)     // Catch: java.lang.InstantiationError -> L34 java.lang.Exception -> L3c
            android.util.Pair r1 = new android.util.Pair     // Catch: java.lang.InstantiationError -> L34 java.lang.Exception -> L3c
            r3 = 2
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r3)     // Catch: java.lang.InstantiationError -> L34 java.lang.Exception -> L3c
            byte[] r2 = r2.getIV()     // Catch: java.lang.InstantiationError -> L34 java.lang.Exception -> L3c
            java.lang.String r2 = android.util.Base64.encodeToString(r2, r3)     // Catch: java.lang.InstantiationError -> L34 java.lang.Exception -> L3c
            r1.<init>(r0, r2)     // Catch: java.lang.InstantiationError -> L34 java.lang.Exception -> L3c
            goto L43
        L34:
            java.lang.String r0 = "IDHelper"
            java.lang.String r1 = "1092"
            android.util.Log.e(r0, r1)
            goto L42
        L3c:
            r0 = move-exception
            java.lang.String r1 = "1018"
            com.heytap.openid.sdk.m_h.m_a(r1, r0)
        L42:
            r1 = 0
        L43:
            if (r1 == 0) goto L58
            java.lang.Object r0 = r1.first
            java.lang.String r0 = (java.lang.String) r0
            r4.putString(r6, r0)
            long r5 = r5.m_b
            r4.putLong(r7, r5)
            java.lang.Object r5 = r1.second
            java.lang.String r5 = (java.lang.String) r5
            r4.putString(r8, r5)
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.openid.sdk.m_a.m_a(android.content.SharedPreferences$Editor, com.heytap.openid.sdk.m_f, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static void m_a(SharedPreferences sharedPreferences, Map<String, m_f> map, String str, String str2) {
        if (map.containsKey(str)) {
            return;
        }
        String string = sharedPreferences.getString(str, null);
        long j = sharedPreferences.getLong(str2, 0L);
        if (string == null || j == 0) {
            return;
        }
        map.put(str, new m_f(string, j));
    }

    public static void m_a(SharedPreferences sharedPreferences, Map<String, m_f> map, String str, String str2, String str3) {
        String str4;
        if (map.containsKey(str)) {
            return;
        }
        String string = sharedPreferences.getString(str, null);
        long j = sharedPreferences.getLong(str2, 0L);
        String string2 = sharedPreferences.getString(str3, null);
        if (string == null || j == 0 || string2 == null) {
            return;
        }
        try {
            byte[] bArrM_a = m_g.m_a(m_a("U3RkSWRBcHBLZXk="), string, string2);
            if (bArrM_a != null) {
                map.put(str, new m_f(new String(bArrM_a, CharEncoding.ISO_8859_1), j));
            }
        } catch (UnsupportedEncodingException e) {
            e = e;
            str4 = "1065";
            m_h.m_a(str4, e);
        } catch (Exception e2) {
            e = e2;
            str4 = "1066";
            m_h.m_a(str4, e);
        }
    }

    public static long m_b(String str) {
        str.hashCode();
        str.hashCode();
        switch (str) {
            case "APID":
            case "GUID":
                return 259200000L;
            case "AUID":
                return 604800000L;
            case "DUID":
                return 86400000L;
            case "OUID":
            case "OUID_STATUS":
                return 7200000L;
            default:
                return 0L;
        }
    }
}
