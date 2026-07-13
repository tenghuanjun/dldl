package com.bytedance.downloader.core;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Logger f357a = Logger.getLogger("VeDownload");
    private static boolean b = false;

    private static String a() {
        try {
            return Thread.currentThread().getStackTrace().length >= 5 ? String.format(Locale.getDefault(), "%s.%s - %d", Thread.currentThread().getStackTrace()[4].getClassName(), Thread.currentThread().getStackTrace()[4].getMethodName(), Integer.valueOf(Thread.currentThread().getStackTrace()[4].getLineNumber())) : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void a(Exception exc) {
        if (b) {
            String str = String.format("%s %s %s", "VeDownload", a(), exc.getMessage());
            if (b) {
                f357a.log(Level.SEVERE, str);
            }
        }
    }

    public static void a(String str) {
        if (b) {
            String str2 = String.format("%s %s %s", "VeDownload", a(), str);
            if (b) {
                f357a.log(Level.INFO, str2);
            }
        }
    }

    public static void a(boolean z) {
        b = z;
    }

    public static void b(String str) {
        if (b) {
            String str2 = String.format("%s %s %s", "VeDownload", a(), str);
            if (b) {
                f357a.log(Level.WARNING, str2);
            }
        }
    }

    public static void c(String str) {
        if (b) {
            String str2 = String.format("%s %s %s", "VeDownload", a(), str);
            if (b) {
                f357a.log(Level.SEVERE, str2);
            }
        }
    }
}
