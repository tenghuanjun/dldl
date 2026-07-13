package com.mobile.auth.m;

import android.text.TextUtils;
import java.security.SecureRandom;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f754a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = f754a;
            cArr[i] = cArr2[(b >>> 4) & 15];
            i += 2;
            cArr[i2] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public static void a(com.cmic.sso.sdk.a aVar, String str) {
        if (!TextUtils.isEmpty(aVar.b("interfaceType", ""))) {
            str = aVar.b("interfaceType") + ";" + str;
        }
        aVar.a("interfaceType", str);
    }

    public static boolean a(com.mobile.auth.e.a aVar) {
        return k.a("logCloseTime", 0L) + ((long) (aVar.l() * 3600000)) >= System.currentTimeMillis();
    }

    public static byte[] a() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    public static String b() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void b(com.cmic.sso.sdk.a aVar, String str) {
        if (!TextUtils.isEmpty(aVar.b("interfaceCode", ""))) {
            str = aVar.b("interfaceCode") + ";" + str;
        }
        aVar.a("interfaceCode", str);
    }

    public static String c() {
        return d().replace("-", "");
    }

    public static void c(com.cmic.sso.sdk.a aVar, String str) {
        if (!TextUtils.isEmpty(aVar.b("interfaceElasped", ""))) {
            str = aVar.b("interfaceElasped") + ";" + str;
        }
        aVar.a("interfaceElasped", str);
    }

    private static String d() {
        return UUID.randomUUID().toString();
    }
}
