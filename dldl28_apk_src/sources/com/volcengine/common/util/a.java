package com.volcengine.common.util;

import android.content.Context;
import android.hardware.SensorManager;
import android.text.TextUtils;
import android.util.Log;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.io.BufferedInputStream;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f1128a = "a";
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static String j;
    private static Boolean k;

    /* JADX INFO: renamed from: com.volcengine.common.util.a$a, reason: collision with other inner class name */
    static /* synthetic */ class C0446a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1129a;

        static {
            int[] iArr = new int[b.values().length];
            f1129a = iArr;
            try {
                iArr[b.RESULT_MAYBE_EMULATOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1129a[b.RESULT_EMULATOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private enum b {
        RESULT_EMULATOR,
        RESULT_MAYBE_EMULATOR,
        RESULT_UNKNOWN
    }

    private static b a() {
        if (TextUtils.isEmpty(h)) {
            String strB = b("gsm.version.baseband");
            h = strB;
            if (TextUtils.isEmpty(strB)) {
                return b.RESULT_MAYBE_EMULATOR;
            }
        }
        return h.contains("1.0.0.0") ? b.RESULT_EMULATOR : b.RESULT_UNKNOWN;
    }

    private static b a(Context context) {
        if (TextUtils.isEmpty(j)) {
            j = a("cat /proc/cpuinfo");
        }
        return TextUtils.isEmpty(j) ? b.RESULT_EMULATOR : j.contains("Hardware\t: placeholder") ? b.RESULT_MAYBE_EMULATOR : b.RESULT_UNKNOWN;
    }

    private static String a(BufferedInputStream bufferedInputStream) {
        int i2;
        if (bufferedInputStream == null) {
            return null;
        }
        byte[] bArr = new byte[512];
        StringBuilder sb = new StringBuilder();
        do {
            i2 = bufferedInputStream.read(bArr);
            if (i2 > 0) {
                sb.append(new String(bArr, 0, i2));
            }
        } while (i2 >= 512);
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.lang.String r8) {
        /*
            r0 = 0
            r1 = 1
            r2 = 0
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L51
            java.lang.String r4 = "sh"
            java.lang.Process r3 = r3.exec(r4)     // Catch: java.lang.Throwable -> L51
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L4f
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch: java.lang.Throwable -> L4f
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L4f
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L4c
            java.io.InputStream r6 = r3.getInputStream()     // Catch: java.lang.Throwable -> L4c
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L4c
            byte[] r8 = r8.getBytes()     // Catch: java.lang.Throwable -> L4a
            r4.write(r8)     // Catch: java.lang.Throwable -> L4a
            r8 = 10
            r4.write(r8)     // Catch: java.lang.Throwable -> L4a
            r4.flush()     // Catch: java.lang.Throwable -> L4a
            r4.close()     // Catch: java.lang.Throwable -> L4a
            r3.waitFor()     // Catch: java.lang.Throwable -> L4a
            java.lang.String r8 = a(r5)     // Catch: java.lang.Throwable -> L4a
            java.io.Closeable[] r2 = new java.io.Closeable[r1]
            r2[r0] = r4
            com.volcengine.j.h.a(r2)
            java.io.Closeable[] r1 = new java.io.Closeable[r1]
            r1[r0] = r5
            com.volcengine.j.h.a(r1)
            r3.destroy()
            return r8
        L4a:
            r8 = move-exception
            goto L55
        L4c:
            r8 = move-exception
            r5 = r2
            goto L55
        L4f:
            r8 = move-exception
            goto L53
        L51:
            r8 = move-exception
            r3 = r2
        L53:
            r4 = r2
            r5 = r4
        L55:
            java.lang.String r6 = com.volcengine.common.util.a.f1128a     // Catch: java.lang.Throwable -> L70
            java.lang.String r7 = "invoking the command failed..."
            android.util.Log.i(r6, r7, r8)     // Catch: java.lang.Throwable -> L70
            java.io.Closeable[] r8 = new java.io.Closeable[r1]
            r8[r0] = r4
            com.volcengine.j.h.a(r8)
            java.io.Closeable[] r8 = new java.io.Closeable[r1]
            r8[r0] = r5
            com.volcengine.j.h.a(r8)
            if (r3 == 0) goto L6f
            r3.destroy()
        L6f:
            return r2
        L70:
            r8 = move-exception
            java.io.Closeable[] r2 = new java.io.Closeable[r1]
            r2[r0] = r4
            com.volcengine.j.h.a(r2)
            java.io.Closeable[] r1 = new java.io.Closeable[r1]
            r1[r0] = r5
            com.volcengine.j.h.a(r1)
            if (r3 == 0) goto L84
            r3.destroy()
        L84:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.volcengine.common.util.a.a(java.lang.String):java.lang.String");
    }

    private static b b() {
        if (TextUtils.isEmpty(f)) {
            String strB = b("ro.product.board");
            if (strB == null) {
                return b.RESULT_MAYBE_EMULATOR;
            }
            f = strB.toLowerCase();
        }
        return (f.contains(PodInfo.GAME_TYPE_ANDROID) || f.contains("goldfish")) ? b.RESULT_EMULATOR : b.RESULT_UNKNOWN;
    }

    private static String b(String str) {
        try {
            Object objInvoke = Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
            if (objInvoke != null) {
                return (String) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            Log.e(f1128a, "failed to get system property[" + str + "]", th);
            return null;
        }
    }

    public static boolean b(Context context) {
        int i2;
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        b bVarE = e();
        Log.i(f1128a, "the hardware is " + b);
        int[] iArr = C0446a.f1129a;
        int i3 = iArr[bVarE.ordinal()];
        if (i3 == 1) {
            i2 = 1;
        } else {
            if (i3 == 2) {
                return true;
            }
            i2 = 0;
        }
        b bVarD = d();
        Log.i(f1128a, "the flavor is " + c);
        int i4 = iArr[bVarD.ordinal()];
        if (i4 == 1) {
            i2++;
        } else if (i4 == 2) {
            return true;
        }
        b bVarG = g();
        Log.i(f1128a, "the product model is " + d);
        int i5 = iArr[bVarG.ordinal()];
        if (i5 == 1) {
            i2++;
        } else if (i5 == 2) {
            return true;
        }
        b bVarF = f();
        Log.i(f1128a, "the product manufacturer is " + e);
        int i6 = iArr[bVarF.ordinal()];
        if (i6 == 1) {
            i2++;
        } else if (i6 == 2) {
            return true;
        }
        b bVarB = b();
        Log.i(f1128a, "the product board is " + f);
        int i7 = iArr[bVarB.ordinal()];
        if (i7 == 1) {
            i2++;
        } else if (i7 == 2) {
            return true;
        }
        b bVarH = h();
        Log.i(f1128a, "the board platform is " + g);
        int i8 = iArr[bVarH.ordinal()];
        if (i8 == 1) {
            i2++;
        } else if (i8 == 2) {
            return true;
        }
        b bVarA = a();
        Log.i(f1128a, "the base bound is " + h + "; the result is " + bVarA);
        int i9 = iArr[bVarA.ordinal()];
        if (i9 == 1) {
            i2 += 2;
        } else if (i9 == 2) {
            return true;
        }
        b bVarA2 = a(context);
        Log.i(f1128a, "the cpu info is " + j);
        int i10 = iArr[bVarA2.ordinal()];
        if (i10 == 1) {
            i2 += 3;
        } else if (i10 == 2) {
            return true;
        }
        if (d(context) <= 7) {
            i2++;
        }
        if (!h(context)) {
            i2++;
        }
        if (!g(context)) {
            i2++;
        }
        if (!f(context)) {
            i2++;
        }
        if (!e(context)) {
            i2++;
        }
        b bVarC = c();
        b bVar = b.RESULT_MAYBE_EMULATOR;
        if (bVarC == bVar) {
            i2++;
        }
        if (i() == bVar) {
            i2++;
        }
        return i2 > 3;
    }

    private static b c() {
        return a("cat /proc/self/cgroup") == null ? b.RESULT_MAYBE_EMULATOR : b.RESULT_UNKNOWN;
    }

    public static boolean c(Context context) {
        if (k == null) {
            k = Boolean.valueOf(b(context));
        }
        return k.booleanValue();
    }

    private static int d(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getSensorList(-1).size();
    }

    private static b d() {
        if (TextUtils.isEmpty(c)) {
            String strB = b("ro.build.flavor");
            if (strB == null) {
                return b.RESULT_MAYBE_EMULATOR;
            }
            c = strB.toLowerCase();
        }
        return (c.contains("vbox") || c.contains("sdk_gphone")) ? b.RESULT_EMULATOR : b.RESULT_UNKNOWN;
    }

    private static b e() {
        if (TextUtils.isEmpty(b)) {
            String strB = b("ro.hardware");
            if (strB == null) {
                return b.RESULT_MAYBE_EMULATOR;
            }
            b = strB.toLowerCase();
        }
        String str = b;
        str.hashCode();
        str.hashCode();
        switch (str) {
            case "cancro":
            case "vbox86":
            case "nox":
            case "ttvm":
            case "vbox":
            case "intel":
            case "android_x86":
                return b.RESULT_EMULATOR;
            default:
                return b.RESULT_UNKNOWN;
        }
    }

    private static boolean e(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(5) != null;
    }

    private static b f() {
        if (TextUtils.isEmpty(e)) {
            String strB = b("ro.product.manufacturer");
            if (strB == null) {
                return b.RESULT_MAYBE_EMULATOR;
            }
            e = strB.toLowerCase();
        }
        return (e.contains("genymotion") || e.equals("netease")) ? b.RESULT_EMULATOR : b.RESULT_UNKNOWN;
    }

    private static boolean f(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth");
    }

    private static b g() {
        if (TextUtils.isEmpty(d)) {
            String strB = b("ro.product.model");
            if (strB == null) {
                return b.RESULT_MAYBE_EMULATOR;
            }
            d = strB.toLowerCase();
        }
        return (d.contains("google_sdk") || d.contains("emulator") || d.contains("android sdk built for x86")) ? b.RESULT_EMULATOR : b.RESULT_UNKNOWN;
    }

    private static boolean g(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    private static b h() {
        if (TextUtils.isEmpty(g)) {
            String strB = b("ro.board.platform");
            if (strB == null) {
                return b.RESULT_MAYBE_EMULATOR;
            }
            g = strB.toLowerCase();
        }
        return g.contains(PodInfo.GAME_TYPE_ANDROID) ? b.RESULT_MAYBE_EMULATOR : b.RESULT_UNKNOWN;
    }

    private static boolean h(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    private static b i() {
        if (TextUtils.isEmpty(i)) {
            i = a("cat /sys/class/net/wlan0/address");
        }
        return TextUtils.isEmpty(i) ? b.RESULT_UNKNOWN : b.RESULT_MAYBE_EMULATOR;
    }
}
