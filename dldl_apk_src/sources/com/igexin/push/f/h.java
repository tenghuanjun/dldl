package com.igexin.push.f;

import android.text.TextUtils;
import android.util.Base64;
import com.igexin.push.extension.mod.SecurityUtils;
import java.security.MessageDigest;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class h {
    public static String a = "";
    private static final String b = "com.igexin.push.f.h";
    private static boolean c = false;
    private static int d = 0;
    private static byte[] e = null;
    private static byte[] f = null;
    private static final int g = 200;

    static {
        try {
            if (SecurityUtils.loadSuccess) {
                e = SecurityUtils.e();
                f = SecurityUtils.a();
                c = (e == null || f == null || SecurityUtils.h(e) == null || SecurityUtils.d(f) == null || SecurityUtils.j() == null || j() == null) ? false : true;
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(b + "|load so error = " + th.toString(), new Object[0]);
            c = false;
            a = th.getMessage();
        }
        if (TextUtils.isEmpty(a)) {
            a = SecurityUtils.errorMsg;
        }
        if (c) {
            com.igexin.b.a.c.a.a(b + "|load so success ~~~~~~~", new Object[0]);
            return;
        }
        com.igexin.b.a.c.a.a(b + "|load so error ++++++++", new Object[0]);
        if (TextUtils.isEmpty(a)) {
            a = "value = null, normal error";
        }
    }

    public static String a(String str, byte[] bArr) throws Exception {
        byte[] bytes = str.getBytes();
        byte[] bArr2 = new byte[bytes.length + bArr.length];
        int iA = com.igexin.b.a.b.g.a(bytes, 0, bArr2, 0, bytes.length);
        if (bArr.length > 0) {
            com.igexin.b.a.b.g.a(bArr, 0, bArr2, iA, bArr.length);
        }
        return Base64.encodeToString(e(bArr2), 2);
    }

    public static boolean a() {
        return c;
    }

    public static byte[] a(com.igexin.push.c.c.a aVar, int i, int i2) {
        byte[] bArr = new byte[aVar.a + 11];
        com.igexin.b.a.b.g.a(i, bArr, 0);
        com.igexin.b.a.b.g.a(i2, bArr, 4);
        com.igexin.b.a.b.g.b((short) aVar.a, bArr, 8);
        bArr[10] = aVar.b;
        com.igexin.b.a.b.g.a(aVar.e, 0, bArr, 11, aVar.a);
        return e(bArr);
    }

    public static byte[] a(byte[] bArr) {
        return com.igexin.b.a.a.a.b(bArr, com.igexin.push.core.e.J);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        return SecurityUtils.b(f, bArr, bArr2);
    }

    public static byte[] b() {
        return SecurityUtils.h(e);
    }

    public static byte[] b(byte[] bArr) {
        return c(bArr);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return SecurityUtils.c(f, bArr, bArr2);
    }

    public static byte[] c() {
        return SecurityUtils.j();
    }

    public static byte[] c(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        return SecurityUtils.f(e, bArr, bArr2);
    }

    public static boolean d() {
        StringBuilder sb;
        String str;
        try {
            if (SecurityUtils.loadSuccess) {
                e = SecurityUtils.e();
                f = SecurityUtils.a();
                c = (e == null || f == null || SecurityUtils.h(e) == null || SecurityUtils.d(f) == null || SecurityUtils.j() == null || j() == null) ? false : true;
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(b + "|load so error = " + th.toString(), new Object[0]);
            c = false;
        }
        if (c) {
            sb = new StringBuilder();
            sb.append(b);
            str = "|load so success ~~~~~~~";
        } else {
            sb = new StringBuilder();
            sb.append(b);
            str = "|load so error ++++++++";
        }
        sb.append(str);
        com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
        return c;
    }

    private static byte[] d(byte[] bArr) {
        int length = bArr.length;
        if (length <= 214) {
            return SecurityUtils.i(bArr);
        }
        int i = length % 200 == 0 ? length / 200 : (length / 200) + 1;
        byte[] bArr2 = new byte[i * 256];
        int i2 = 0;
        int iA = 0;
        while (i2 < i) {
            int i3 = i2 < i + (-1) ? 200 : length - (i2 * 200);
            byte[] bArr3 = new byte[i3];
            com.igexin.b.a.b.g.a(bArr, i2 * 200, bArr3, 0, i3);
            byte[] bArrI = SecurityUtils.i(bArr3);
            iA += com.igexin.b.a.b.g.a(bArrI, 0, bArr2, iA, bArrI.length);
            i2++;
        }
        return bArr2;
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) {
        return SecurityUtils.g(e, bArr, bArr2);
    }

    public static int e() {
        int i = d;
        d = i + 1;
        return i;
    }

    private static byte[] e(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] e(byte[] bArr, byte[] bArr2) {
        return SecurityUtils.m(bArr, bArr2);
    }

    public static String f() {
        byte[] bArrD = SecurityUtils.d(f);
        byte[] bytes = p.b().getBytes();
        byte[] bArr = new byte[bytes.length + bArrD.length];
        com.igexin.b.a.b.g.a(bArrD, 0, bArr, com.igexin.b.a.b.g.a(bytes, 0, bArr, 0, bytes.length), bArrD.length);
        return Base64.encodeToString(bArr, 2);
    }

    private static byte[] f(byte[] bArr, byte[] bArr2) {
        return SecurityUtils.l(bArr, bArr2);
    }

    private static byte[] g() {
        return SecurityUtils.a();
    }

    private static byte[] h() {
        return SecurityUtils.e();
    }

    private static byte[] i() {
        return SecurityUtils.d(f);
    }

    private static String j() {
        byte[] bArrK = SecurityUtils.k();
        if (bArrK == null) {
            return null;
        }
        String str = new String(bArrK);
        com.igexin.b.a.c.a.a(b + "| so version is " + str, new Object[0]);
        return str;
    }
}
