package a.a.a.c;

import android.util.Log;

/* JADX INFO: compiled from: DetectLogger.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static String a(String str) {
        return "Detect_" + str;
    }

    public static void b(String str, String str2) {
        if (c(str, str2)) {
            return;
        }
        a.a.a.g.b.b(a(str), str2);
    }

    public static boolean c(String str, String str2) {
        if (!a.a.a.b.d.a(a.a.a.b.d.f70a, 8L)) {
            return false;
        }
        Log.d(a(str), str2);
        return true;
    }

    public static void d(String str, String str2) {
        if (c(str, str2)) {
            return;
        }
        a.a.a.g.b.d(a(str), str2);
    }

    public static void a(String str, String str2) {
        if (c(str, str2)) {
            return;
        }
        a.a.a.g.b.a(a(str), str2);
    }
}
