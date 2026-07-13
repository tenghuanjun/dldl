package com.bytedance.dns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Logger f349a = Logger.getLogger("DnsResolver");
    private static boolean b = true;

    private static String a() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date()) + "\t";
    }

    public static void a(String str) {
        if (b) {
            f349a.log(Level.INFO, a() + StringUtils.SPACE + str);
        }
    }

    public static void a(boolean z) {
        b = z;
    }

    public static void b(String str) {
        if (b) {
            f349a.log(Level.WARNING, a() + StringUtils.SPACE + str);
        }
    }

    public static void c(String str) {
        if (b) {
            f349a.log(Level.SEVERE, a() + StringUtils.SPACE + str);
        }
    }
}
