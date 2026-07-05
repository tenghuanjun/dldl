package com.igexin.push.f;

import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class p {
    private static final char[] a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz`~!@#$%^&*()-_=+[{}];:'/?.>,<".toCharArray();

    public static String a() {
        StringBuilder sb = new StringBuilder(16);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 16; i++) {
            char[] cArr = a;
            sb.append(cArr[random.nextInt(cArr.length)]);
        }
        return sb.toString();
    }

    public static String b() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    private static long c() {
        return ((long) (new Random().nextInt(6) + 2)) * 60000;
    }
}
