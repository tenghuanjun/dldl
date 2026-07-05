package com.sq.tool.logger;

import android.text.TextUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
final class Utils {
    static String logLevel(int value) {
        switch (value) {
            case 2:
                return "VERBOSE";
            case 3:
                return "DEBUG";
            case 4:
                return "INFO";
            case 5:
                return "WARN";
            case 6:
                return "ERROR";
            case 7:
                return "ASSERT";
            default:
                return "UNKNOWN";
        }
    }

    private Utils() {
    }

    static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    static boolean equals(CharSequence a, CharSequence b) {
        int length;
        if (a == b) {
            return true;
        }
        if (a == null || b == null || (length = a.length()) != b.length()) {
            return false;
        }
        if ((a instanceof String) && (b instanceof String)) {
            return a.equals(b);
        }
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        for (Throwable cause = tr; cause != null; cause = cause.getCause()) {
            if (cause instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        tr.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static String toString(Object object) {
        if (object == null) {
            return AbstractJsonLexerKt.NULL;
        }
        if (!object.getClass().isArray()) {
            return object.toString();
        }
        if (object instanceof boolean[]) {
            return Arrays.toString((boolean[]) object);
        }
        if (object instanceof byte[]) {
            return Arrays.toString((byte[]) object);
        }
        if (object instanceof char[]) {
            return Arrays.toString((char[]) object);
        }
        if (object instanceof short[]) {
            return Arrays.toString((short[]) object);
        }
        if (object instanceof int[]) {
            return Arrays.toString((int[]) object);
        }
        if (object instanceof long[]) {
            return Arrays.toString((long[]) object);
        }
        if (object instanceof float[]) {
            return Arrays.toString((float[]) object);
        }
        if (object instanceof double[]) {
            return Arrays.toString((double[]) object);
        }
        return object instanceof Object[] ? Arrays.deepToString((Object[]) object) : "Couldn't find a correct type for the object";
    }

    static <T> T checkNotNull(final T obj) {
        if (obj != null) {
            return obj;
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getProcessName() throws java.lang.Throwable {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 0
            r3 = 28
            if (r0 < r3) goto Ld
            java.lang.String r0 = android.app.Application.getProcessName()
            goto L31
        Ld:
            java.lang.String r0 = "android.app.ActivityThread"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            java.lang.String r3 = "currentProcessName"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r3, r4)     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            java.lang.Object r0 = r0.invoke(r2, r3)     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            goto L31
        L24:
            r0 = move-exception
            goto L2d
        L26:
            r0 = move-exception
            goto L2d
        L28:
            r0 = move-exception
            goto L2d
        L2a:
            r0 = move-exception
            goto L2d
        L2c:
            r0 = move-exception
        L2d:
            r0.printStackTrace()
            r0 = r2
        L31:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L38
            return r0
        L38:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            java.lang.String r3 = "/proc/self/cmdline"
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            r3 = 256(0x100, float:3.59E-43)
            byte[] r4 = new byte[r3]     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            r5 = 0
        L44:
            int r6 = r0.read()     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            if (r6 <= 0) goto L53
            if (r5 >= r3) goto L53
            int r7 = r5 + 1
            byte r6 = (byte) r6     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            r4[r5] = r6     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            r5 = r7
            goto L44
        L53:
            if (r5 <= 0) goto L65
            java.lang.String r3 = new java.lang.String     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            r3.<init>(r4, r1, r5, r6)     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            r0.close()     // Catch: java.io.IOException -> L60
            goto L64
        L60:
            r0 = move-exception
            r0.printStackTrace()
        L64:
            return r3
        L65:
            r0.close()     // Catch: java.io.IOException -> L78
            goto L7c
        L69:
            r1 = move-exception
            goto L6f
        L6b:
            r1 = move-exception
            goto L7f
        L6d:
            r1 = move-exception
            r0 = r2
        L6f:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L7d
            if (r0 == 0) goto L7c
            r0.close()     // Catch: java.io.IOException -> L78
            goto L7c
        L78:
            r0 = move-exception
            r0.printStackTrace()
        L7c:
            return r2
        L7d:
            r1 = move-exception
            r2 = r0
        L7f:
            if (r2 == 0) goto L89
            r2.close()     // Catch: java.io.IOException -> L85
            goto L89
        L85:
            r0 = move-exception
            r0.printStackTrace()
        L89:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.tool.logger.Utils.getProcessName():java.lang.String");
    }

    public static String wrapProcessName() throws Throwable {
        String processName = getProcessName();
        if (TextUtils.isEmpty(processName)) {
            return null;
        }
        return processName.replaceAll(":", "_");
    }
}
