package a.a.a.g;

import android.util.Log;

/* JADX INFO: compiled from: LogUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f135a = "KS_LOG";
    public static boolean b = false;
    public static boolean c = false;

    public static void a(String str, String str2) {
        if (b) {
            Log.d(String.format("[%s]", f135a), String.format("[%s]: ", str) + str2);
        }
    }

    public static void b(String str, String str2) {
        if (b) {
            Log.e(String.format("[%s]", f135a), String.format("[%s]: ", str) + str2);
        }
    }

    public static void c(String str, String str2) {
        if (c) {
            Log.e(String.format("[%s]", f135a), String.format("[%s]: ", str) + str2);
        }
    }

    public static void d(String str, String str2) {
        Log.d(String.format("[%s]", f135a), String.format("[%s]: ", str) + str2);
    }
}
