package com.ss.android.socialbase.appdownloader.f;

import android.os.Build;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.g;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class e {
    public static String a = null;
    public static String b = "";
    public static String c = null;
    private static String d = "";
    private static String e;
    private static String f;
    private static String g;
    private static Boolean h;

    public static boolean a() {
        return a("EMUI") || a("MAGICUI");
    }

    public static boolean b() {
        return a("MAGICUI");
    }

    public static boolean c() {
        return a("MIUI");
    }

    public static boolean d() {
        return a("VIVO");
    }

    public static boolean e() {
        r();
        return a(a);
    }

    public static boolean f() {
        return a("FLYME");
    }

    public static boolean g() {
        return a("SAMSUNG");
    }

    public static String h() {
        if (e == null) {
            a("");
        }
        return e;
    }

    public static String i() {
        if (f == null) {
            a("");
        }
        return f;
    }

    public static String j() {
        if (c == null) {
            a("");
        }
        return c;
    }

    private static void r() {
        if (TextUtils.isEmpty(a)) {
            DownloadComponentManager.ensureOPPO();
            a = DownloadConstants.UPPER_OPPO;
            d = "ro.build.version." + DownloadConstants.LOWER_OPPO + "rom";
            b = "com." + DownloadConstants.LOWER_OPPO + ".market";
        }
    }

    public static boolean a(String str) {
        r();
        String str2 = e;
        if (str2 != null) {
            return str2.equals(str);
        }
        String strD = d("ro.miui.ui.version.name");
        f = strD;
        if (!TextUtils.isEmpty(strD)) {
            e = "MIUI";
            c = "com.xiaomi.market";
            g = f;
        } else {
            String strD2 = d("ro.build.version.emui");
            f = strD2;
            if (!TextUtils.isEmpty(strD2)) {
                String str3 = q() ? "MAGICUI" : "EMUI";
                e = str3;
                if (TextUtils.equals(str3, "MAGICUI")) {
                    c = "com.hihonor.appmarket";
                } else {
                    c = "com.huawei.appmarket";
                }
            } else {
                String strD3 = d("ro.build.version.magic");
                f = strD3;
                if (!TextUtils.isEmpty(strD3)) {
                    e = "MAGICUI";
                    c = "com.hihonor.appmarket";
                } else {
                    String strD4 = d(d);
                    f = strD4;
                    if (!TextUtils.isEmpty(strD4)) {
                        e = a;
                        if (g.a(b) > -1) {
                            c = b;
                        } else {
                            c = "com.heytap.market";
                        }
                    } else {
                        String strD5 = d("ro.vivo.os.version");
                        f = strD5;
                        if (!TextUtils.isEmpty(strD5)) {
                            e = "VIVO";
                            c = "com.bbk.appstore";
                        } else {
                            String strD6 = d("ro.smartisan.version");
                            f = strD6;
                            if (!TextUtils.isEmpty(strD6)) {
                                e = "SMARTISAN";
                                c = "com.smartisanos.appstore";
                            } else {
                                String strD7 = d("ro.gn.sv.version");
                                f = strD7;
                                if (!TextUtils.isEmpty(strD7)) {
                                    e = "QIONEE";
                                    c = "com.gionee.aora.market";
                                } else {
                                    String strD8 = d("ro.lenovo.lvp.version");
                                    f = strD8;
                                    if (!TextUtils.isEmpty(strD8)) {
                                        e = "LENOVO";
                                        c = "com.lenovo.leos.appstore";
                                    } else if (k().toUpperCase().contains("SAMSUNG")) {
                                        e = "SAMSUNG";
                                        c = "com.sec.android.app.samsungapps";
                                    } else if (k().toUpperCase().contains("ZTE")) {
                                        e = "ZTE";
                                        c = "zte.com.market";
                                    } else if (k().toUpperCase().contains("NUBIA")) {
                                        e = "NUBIA";
                                        c = "cn.nubia.neostore";
                                    } else if (l().toUpperCase().contains("FLYME")) {
                                        e = "FLYME";
                                        c = "com.meizu.mstore";
                                        f = l();
                                    } else if (k().toUpperCase().contains("ONEPLUS")) {
                                        e = "ONEPLUS";
                                        f = d("ro.rom.version");
                                        if (g.a(b) > -1) {
                                            c = b;
                                        } else {
                                            c = "com.heytap.market";
                                        }
                                    } else {
                                        e = k().toUpperCase();
                                        c = "";
                                        f = "";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return e.equals(str);
    }

    public static String b(String str) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
        } catch (Throwable unused) {
            bufferedReader = null;
        }
        try {
            String line = bufferedReader.readLine();
            bufferedReader.close();
            DownloadUtils.safeClose(bufferedReader);
            return line;
        } catch (Throwable unused2) {
            DownloadUtils.safeClose(bufferedReader);
            return null;
        }
    }

    public static String c(String str) throws Throwable {
        return (String) Class.forName("android.os.SystemProperties").getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class).invoke(null, str);
    }

    public static String d(String str) {
        if (DownloadSetting.getGlobalSettings().optBoolean(DownloadSettingKeys.ENABLE_REFLECT_SYSTEM_PROPERTIES, true)) {
            try {
                return c(str);
            } catch (Throwable th) {
                th.printStackTrace();
                return b(str);
            }
        }
        return b(str);
    }

    public static String k() {
        return Build.MANUFACTURER == null ? "" : Build.MANUFACTURER.trim();
    }

    public static String l() {
        return Build.DISPLAY == null ? "" : Build.DISPLAY.trim();
    }

    public static boolean m() {
        s();
        return "V10".equals(g);
    }

    public static boolean n() {
        s();
        return "V11".equals(g);
    }

    public static boolean o() {
        s();
        return "V12".equals(g);
    }

    private static void s() {
        if (g == null) {
            try {
                g = d("ro.miui.ui.version.name");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            String str = g;
            if (str == null) {
                str = "";
            }
            g = str;
        }
    }

    public static boolean p() {
        if (h == null) {
            h = Boolean.valueOf(d.g().equals("harmony"));
        }
        return h.booleanValue();
    }

    public static boolean q() {
        return (!TextUtils.isEmpty(Build.BRAND) && Build.BRAND.toLowerCase().startsWith("honor")) || (!TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith("honor"));
    }
}
