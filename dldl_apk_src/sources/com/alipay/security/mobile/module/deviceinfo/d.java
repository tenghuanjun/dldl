package com.alipay.security.mobile.module.deviceinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import com.aliyun.aliyunface.utils.MobileUtil;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class d {
    private static d a = new d();

    private d() {
    }

    public static d a() {
        return a;
    }

    private static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0055 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(android.content.Context r8) {
        /*
            r7 = this;
            r0 = 0
            java.lang.String r1 = android.os.Build.HARDWARE     // Catch: java.lang.Exception -> L66
            java.lang.String r2 = "goldfish"
            boolean r1 = r1.contains(r2)     // Catch: java.lang.Exception -> L66
            r2 = 1
            if (r1 != 0) goto L65
            java.lang.String r1 = android.os.Build.PRODUCT     // Catch: java.lang.Exception -> L66
            java.lang.String r3 = "sdk"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Exception -> L66
            if (r1 != 0) goto L65
            java.lang.String r1 = android.os.Build.FINGERPRINT     // Catch: java.lang.Exception -> L66
            java.lang.String r3 = "generic"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Exception -> L66
            if (r1 == 0) goto L21
            goto L65
        L21:
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r8.getSystemService(r1)     // Catch: java.lang.Exception -> L66
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1     // Catch: java.lang.Exception -> L66
            if (r1 == 0) goto L56
            java.lang.String r1 = r1.getDeviceId()     // Catch: java.lang.Exception -> L66
            if (r1 == 0) goto L52
            int r3 = r1.length()     // Catch: java.lang.Exception -> L66
            if (r3 != 0) goto L38
            goto L52
        L38:
            r4 = 0
        L39:
            if (r4 >= r3) goto L52
            char r5 = r1.charAt(r4)     // Catch: java.lang.Exception -> L66
            boolean r5 = java.lang.Character.isWhitespace(r5)     // Catch: java.lang.Exception -> L66
            if (r5 != 0) goto L4f
            char r5 = r1.charAt(r4)     // Catch: java.lang.Exception -> L66
            r6 = 48
            if (r5 == r6) goto L4f
            r1 = 0
            goto L53
        L4f:
            int r4 = r4 + 1
            goto L39
        L52:
            r1 = 1
        L53:
            if (r1 == 0) goto L56
            return r2
        L56:
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch: java.lang.Exception -> L66
            java.lang.String r1 = "android_id"
            java.lang.String r8 = android.provider.Settings.Secure.getString(r8, r1)     // Catch: java.lang.Exception -> L66
            boolean r8 = com.alipay.security.mobile.module.a.a.a(r8)     // Catch: java.lang.Exception -> L66
            return r8
        L65:
            return r2
        L66:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.deviceinfo.d.a(android.content.Context):boolean");
    }

    public String b() {
        return "android";
    }

    public String b(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (!connectivityManager.getActiveNetworkInfo().isConnected()) {
                return "";
            }
            String typeName = connectivityManager.getActiveNetworkInfo().getTypeName();
            return typeName == null ? false : typeName.equalsIgnoreCase(MobileUtil.NETWORK_WIFI) ? MobileUtil.NETWORK_WIFI : connectivityManager.getActiveNetworkInfo().getExtraInfo();
        } catch (Exception unused) {
            return "";
        }
    }

    public boolean c() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < 5; i++) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public String d() {
        return Build.BOARD;
    }

    public String e() {
        return Build.BRAND;
    }

    public String f() {
        return Build.DEVICE;
    }

    public String g() {
        return Build.DISPLAY;
    }

    public String h() {
        return Build.VERSION.INCREMENTAL;
    }

    public String i() {
        return Build.MANUFACTURER;
    }

    public String j() {
        return Build.MODEL;
    }

    public String k() {
        return Build.PRODUCT;
    }

    public String l() {
        return Build.VERSION.RELEASE;
    }

    public String m() {
        return Build.VERSION.SDK;
    }

    public String n() {
        return Build.TAGS;
    }

    public String o() {
        return a("ro.kernel.qemu", "0");
    }

    public String p() {
        return a("gsm.sim.state", "");
    }

    public String q() {
        return a("gsm.sim.state.2", "");
    }

    public String r() {
        return a("wifi.interface", "");
    }

    public String s() {
        return a("sys.usb.state", "");
    }
}
