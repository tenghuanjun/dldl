package com.heytap.openid.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import com.sqwan.msdk.utils.AppSigning;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class m_a {
    public static final ThreadPoolExecutor m_a = new ThreadPoolExecutor(0, 3, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(2048), new ThreadPoolExecutor.DiscardPolicy());
    public static String m_b = "phone";

    public static int m_a(int i) {
        return (i > m_e.m_a || i <= 0) ? 10001 : 10000;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String m_a(android.content.Context r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 269
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
            if (AppSigning.SHA1.equals(str2)) {
                byte[] byteArray = signature.toByteArray();
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance(AppSigning.SHA1);
                    if (messageDigest == null) {
                        return null;
                    }
                    byte[] bArrDigest = messageDigest.digest(byteArray);
                    StringBuilder sb = new StringBuilder();
                    for (byte b : bArrDigest) {
                        sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
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
            m_h.m_a("4025: " + str2);
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
                map.put(str, new m_f(new String(bArrM_a, "ISO-8859-1"), j));
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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long m_b(java.lang.String r6) {
        /*
            r6.hashCode()
            int r0 = r6.hashCode()
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r0) {
                case 2015626: goto L47;
                case 2020431: goto L3c;
                case 2109804: goto L31;
                case 2199177: goto L26;
                case 2437505: goto L1b;
                case 572132464: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L52
        L10:
            java.lang.String r0 = "OUID_STATUS"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L19
            goto L52
        L19:
            r6 = 5
            goto L53
        L1b:
            java.lang.String r0 = "OUID"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L24
            goto L52
        L24:
            r6 = 4
            goto L53
        L26:
            java.lang.String r0 = "GUID"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L2f
            goto L52
        L2f:
            r6 = 3
            goto L53
        L31:
            java.lang.String r0 = "DUID"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L3a
            goto L52
        L3a:
            r6 = 2
            goto L53
        L3c:
            java.lang.String r0 = "AUID"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L45
            goto L52
        L45:
            r6 = 1
            goto L53
        L47:
            java.lang.String r0 = "APID"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L50
            goto L52
        L50:
            r6 = 0
            goto L53
        L52:
            r6 = -1
        L53:
            if (r6 == 0) goto L6e
            if (r6 == r5) goto L6a
            if (r6 == r4) goto L66
            if (r6 == r3) goto L6e
            if (r6 == r2) goto L62
            if (r6 == r1) goto L62
            r0 = 0
            return r0
        L62:
            r0 = 7200000(0x6ddd00, double:3.5572727E-317)
            return r0
        L66:
            r0 = 86400000(0x5265c00, double:4.2687272E-316)
            return r0
        L6a:
            r0 = 604800000(0x240c8400, double:2.988109026E-315)
            return r0
        L6e:
            r0 = 259200000(0xf731400, double:1.280618154E-315)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.openid.sdk.m_a.m_b(java.lang.String):long");
    }
}
