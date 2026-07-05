package com.unicom.online.account.kernel;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class c {
    private static boolean a = true;
    private static int b = 2;
    private static long c;
    private static int d;
    private static StringBuilder e = new StringBuilder();
    private static StringBuilder f = new StringBuilder();
    private static StringBuilder g = new StringBuilder();

    public static String a(int i) {
        StringBuilder sb;
        if (i == 0) {
            sb = f;
        } else if (1 == i) {
            sb = e;
        } else {
            if (2 != i) {
                return "no info";
            }
            sb = g;
        }
        return sb.toString();
    }

    public static void a() {
        d = 0;
        f.setLength(0);
        f.append("\n\n********************\n\n\n\n   com debug info   \n\n\n\n********************\n\n");
        e.setLength(0);
        e.append("\n\n********************\n\n\n\n   all debug info   \n\n\n\n********************\n\n");
        g.append("\n\n********************\n\n\n\n   result  info   \n\n\n\n********************\n\n");
    }

    public static void a(int i, String str) {
        StringBuilder sb = new StringBuilder("【");
        int i2 = d;
        d = i2 + 1;
        sb.append(i2);
        sb.append("】\n时间戳:");
        sb.append(System.currentTimeMillis());
        sb.append("\n时间差:");
        sb.append(System.currentTimeMillis() - c);
        sb.append("\n数据:\n");
        sb.append(str);
        sb.append("\n\n");
        b(i, sb.toString());
        c = System.currentTimeMillis();
    }

    public static void a(String str) {
        a(1, "\n" + str + "\n");
    }

    public static void a(boolean z) {
        a = z;
    }

    private static void b(int i, String str) {
        if (i == 0) {
            f.append(str);
        }
        if (2 == i) {
            g.append(str);
        }
        e.append(str);
    }

    public static void b(String str) {
        if (a) {
            Log.d("UniAccount", ab.a() + " " + str);
            a(0, str);
        }
    }

    public static void c(String str) {
        if (a) {
            Log.e("UniAccount", ab.a() + " " + str);
            a(0, str);
        }
    }

    public static void d(String str) {
        Log.e("UniAccount", ab.a() + " " + str);
        a(0, str);
    }
}
