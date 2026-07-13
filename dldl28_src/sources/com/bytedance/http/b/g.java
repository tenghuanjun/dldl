package com.bytedance.http.b;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f395a;
    private final byte[] b;
    private final boolean c;

    public g(int i, byte[] bArr, boolean z) {
        this.f395a = i;
        this.b = bArr;
        this.c = z;
    }

    public static int a(String str, long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException(str + " < 0");
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit == null");
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException(str + " too large.");
        }
        if (millis != 0 || j <= 0) {
            return (int) millis;
        }
        throw new IllegalArgumentException(str + " too small.");
    }

    public static int a(byte[] bArr) {
        return (bArr[3] & UByte.MAX_VALUE) | (bArr[0] << 24) | ((bArr[1] & UByte.MAX_VALUE) << 16) | ((bArr[2] & UByte.MAX_VALUE) << 8);
    }

    public static String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String string = stringWriter.toString();
        return (string == null || string.length() <= 1024) ? string : string.substring(1024);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread a(String str, boolean z, Runnable runnable) {
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(z);
        return thread;
    }

    public static ThreadFactory a(final String str, boolean z) {
        final boolean z2 = false;
        return new ThreadFactory() { // from class: com.bytedance.http.b.g$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return g.a(str, z2, runnable);
            }
        };
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean a(String str) {
        return Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$").matcher(str).matches();
    }

    public static String b(String str) {
        int iIndexOf;
        if (str == null || str.length() <= 0) {
            return "";
        }
        String lowerCase = str.toLowerCase();
        if (!lowerCase.startsWith("failed to connect to") || (iIndexOf = lowerCase.indexOf("/")) <= 0) {
            return "";
        }
        int i = iIndexOf + 1;
        for (int i2 = i; i2 < lowerCase.length(); i2++) {
            char cCharAt = lowerCase.charAt(i2);
            if (!Character.isDigit(cCharAt) && cCharAt != '.') {
                return lowerCase.substring(i, i2);
            }
        }
        return "";
    }

    public static String b(byte[] bArr) {
        try {
            if (System.getProperties().getProperty("java.vm.name").equals("Dalvik")) {
                return (String) Class.forName("android.util.Base64").getMethod("encodeToString", byte[].class, Integer.TYPE).invoke(null, bArr, 2);
            }
            Object objInvoke = Class.forName("java.util.Base64").getMethod("getEncoder", null).invoke(null, null);
            return (String) objInvoke.getClass().getMethod("encodeToString", byte[].class).invoke(objInvoke, bArr);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            throw new RuntimeException("Base64 class not found");
        }
    }

    public static boolean c(String str) {
        return (str == null || str.length() == 0) ? false : true;
    }

    public int a() {
        return this.f395a;
    }

    public byte[] b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }
}
