package com.bytedance.http.b;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Logger f390a = Logger.getLogger("QuickHttp3");
    private static boolean b = false;

    private static String a() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date()) + "\t";
    }

    public static void a(String str) {
        if (b) {
            f390a.log(Level.INFO, a() + str);
        }
    }

    public static void a(boolean z) {
        b = z;
    }

    public static void b(String str) {
        if (b) {
            f390a.log(Level.WARNING, a() + str);
        }
    }

    public static void c(String str) {
        if (b) {
            f390a.log(Level.SEVERE, a() + str);
        }
    }
}
