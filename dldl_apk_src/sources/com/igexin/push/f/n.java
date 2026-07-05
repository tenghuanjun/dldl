package com.igexin.push.f;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.getui.gtc.dim.AllowSysCall;
import com.getui.gtc.dim.DimManager;
import com.getui.gtc.dim.DimRequest;
import com.igexin.push.core.e.e.AnonymousClass22;
import com.igexin.push.f.g;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class n {
    public static final String a = "PhoneInfoUtils";
    private static String b;

    public static int a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.targetSdkVersion;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static String a(String str, String str2) {
        String str3 = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return str3;
                }
                str3 = str3 + line;
            }
        } catch (Exception unused) {
            return str2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<android.content.pm.PackageInfo> a() {
        /*
            r0 = 0
            com.getui.gtc.dim.DimManager r2 = com.getui.gtc.dim.DimManager.getInstance()     // Catch: java.lang.Exception -> L2a
            com.getui.gtc.dim.DimRequest$Builder r3 = new com.getui.gtc.dim.DimRequest$Builder     // Catch: java.lang.Exception -> L2a
            r3.<init>()     // Catch: java.lang.Exception -> L2a
            java.lang.String r4 = com.igexin.push.f.g.a.I     // Catch: java.lang.Exception -> L2a
            com.getui.gtc.dim.DimRequest$Builder r3 = r3.key(r4)     // Catch: java.lang.Exception -> L2a
            com.getui.gtc.dim.DimRequest$Builder r3 = r3.ramCacheValidTime(r0)     // Catch: java.lang.Exception -> L2a
            com.getui.gtc.dim.DimRequest$Builder r3 = r3.storageCacheValidTime(r0)     // Catch: java.lang.Exception -> L2a
            com.getui.gtc.dim.AllowSysCall r4 = com.getui.gtc.dim.AllowSysCall.ALL_ALLOW     // Catch: java.lang.Exception -> L2a
            com.getui.gtc.dim.DimRequest$Builder r3 = r3.allowSysCall(r4)     // Catch: java.lang.Exception -> L2a
            com.getui.gtc.dim.DimRequest r3 = r3.build()     // Catch: java.lang.Exception -> L2a
            java.lang.Object r2 = r2.get(r3)     // Catch: java.lang.Exception -> L2a
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Exception -> L2a
            goto L2b
        L2a:
            r2 = 0
        L2b:
            r3 = 0
            if (r2 == 0) goto L3d
            int r4 = r2.size()
            if (r4 != 0) goto L35
            goto L3d
        L35:
            java.lang.String r0 = "PhoneInfoUtilshes permission als from api by gl."
            java.lang.Object[] r1 = new java.lang.Object[r3]
            com.igexin.b.a.c.a.a(r0, r1)
            goto Laa
        L3d:
            int r4 = com.igexin.push.config.d.V     // Catch: java.lang.Exception -> Laa
            if (r4 == 0) goto La7
            r5 = 2
            if (r4 == r5) goto L7e
            r5 = 4
            if (r4 == r5) goto L4f
            java.lang.String r0 = "PhoneInfoUtilsfetch als error config"
        L49:
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> Laa
            com.igexin.b.a.c.a.a(r0, r1)     // Catch: java.lang.Exception -> Laa
            goto Laa
        L4f:
            java.lang.String r4 = "PhoneInfoUtilsfetch als by sd."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> Laa
            com.igexin.b.a.c.a.a(r4, r3)     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimManager r3 = com.getui.gtc.dim.DimManager.getInstance()     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest$Builder r4 = new com.getui.gtc.dim.DimRequest$Builder     // Catch: java.lang.Exception -> Laa
            r4.<init>()     // Catch: java.lang.Exception -> Laa
            java.lang.String r5 = com.igexin.push.f.g.a.J     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest$Builder r4 = r4.key(r5)     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest$Builder r4 = r4.ramCacheValidTime(r0)     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest$Builder r0 = r4.storageCacheValidTime(r0)     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.AllowSysCall r1 = com.getui.gtc.dim.AllowSysCall.ALL_ALLOW     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest$Builder r0 = r0.allowSysCall(r1)     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest r0 = r0.build()     // Catch: java.lang.Exception -> Laa
        L77:
            java.lang.Object r0 = r3.get(r0)     // Catch: java.lang.Exception -> Laa
            java.util.List r0 = (java.util.List) r0     // Catch: java.lang.Exception -> Laa
            goto Lab
        L7e:
            java.lang.String r4 = "PhoneInfoUtilsfetch als from api by q."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> Laa
            com.igexin.b.a.c.a.a(r4, r3)     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimManager r3 = com.getui.gtc.dim.DimManager.getInstance()     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest$Builder r4 = new com.getui.gtc.dim.DimRequest$Builder     // Catch: java.lang.Exception -> Laa
            r4.<init>()     // Catch: java.lang.Exception -> Laa
            java.lang.String r5 = com.igexin.push.f.g.a.H     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest$Builder r4 = r4.key(r5)     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest$Builder r4 = r4.ramCacheValidTime(r0)     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest$Builder r0 = r4.storageCacheValidTime(r0)     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.AllowSysCall r1 = com.getui.gtc.dim.AllowSysCall.ALL_ALLOW     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest$Builder r0 = r0.allowSysCall(r1)     // Catch: java.lang.Exception -> Laa
            com.getui.gtc.dim.DimRequest r0 = r0.build()     // Catch: java.lang.Exception -> Laa
            goto L77
        La7:
            java.lang.String r0 = "PhoneInfoUtilsfetch forbid to get apls"
            goto L49
        Laa:
            r0 = r2
        Lab:
            if (r0 != 0) goto Lb1
            java.util.List r0 = java.util.Collections.emptyList()
        Lb1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.f.n.a():java.util.List");
    }

    public static Pair<String, String> b() {
        try {
            WifiInfo wifiInfo = (WifiInfo) DimManager.getInstance().get(g.a.D);
            return Pair.create(wifiInfo.getSSID(), wifiInfo.getBSSID());
        } catch (Exception unused) {
            return null;
        }
    }

    public static String c() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(g.a.j).useExpiredCacheForReserve(true).build());
        } catch (Exception unused) {
            return null;
        }
    }

    public static String d() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(g.a.f).useExpiredCacheForReserve(true).build());
        } catch (Exception unused) {
            return null;
        }
    }

    public static String e() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(g.a.b).useExpiredCacheForReserve(true).build());
        } catch (Exception unused) {
            return null;
        }
    }

    public static String f() {
        try {
            return (String) DimManager.getInstance().get(g.a.e);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String g() {
        try {
            return (String) DimManager.getInstance().get(g.a.q);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String h() {
        try {
            return (String) DimManager.getInstance().get(g.a.r);
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<ScanResult> i() {
        try {
            return (List) DimManager.getInstance().get(g.a.E);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String j() {
        String str = "";
        try {
            str = Build.VERSION.SDK_INT < 21 ? Build.CPU_ABI : Build.SUPPORTED_ABIS[0];
        } catch (Throwable unused) {
        }
        return str;
    }

    public static String k() {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        try {
            String str = Build.BRAND;
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            String lowerCase = str.toLowerCase();
            HashMap map = new HashMap();
            map.put("huawei", "ro.build.version.emui");
            map.put("blackshark", "ro.build.version.incremental");
            map.put("redmi", "ro.build.version.incremental");
            map.put("xiaomi", "ro.build.version.incremental");
            map.put("samsang", "ro.build.version.incremental");
            map.put("vivo", "ro.vivo.os.version");
            map.put("oppo", "ro.build.version.opporom");
            map.put("meizu", "ro.build.display.id");
            map.put("lenovo", "ro.build.version.incremental");
            map.put("smartisan", "ro.modversion");
            map.put("htc", "ro.build.sense.version");
            map.put("oneplus", "ro.rom.version");
            map.put("yunos", "ro.cta.yunos.version");
            map.put("360", "ro.build.uiversion");
            map.put("nubia", "ro.build.rom.internal.id");
            if (!map.containsKey(lowerCase)) {
                return "";
            }
            String strA = a((String) map.get(lowerCase), "");
            b = strA;
            return strA;
        } catch (Throwable unused) {
            return "";
        }
    }

    public static boolean l() {
        try {
            return Arrays.asList(com.igexin.push.config.d.K.toUpperCase().split(com.igexin.push.core.b.aj)).contains(Build.BRAND.toUpperCase());
        } catch (Exception e) {
            com.igexin.b.a.c.a.a("PhoneInfoUtils|delAlarm " + com.igexin.push.config.d.K + " err " + e.toString(), new Object[0]);
            return false;
        }
    }

    public static String m() {
        String str;
        try {
            str = (String) DimManager.getInstance().get(new DimRequest.Builder().key(g.a.l).ramCacheValidTime(0L).storageCacheValidTime(0L).allowSysCall(AllowSysCall.ALL_ALLOW).build());
        } catch (Exception unused) {
            str = null;
        }
        if (!TextUtils.isEmpty(str) && !str.equals(com.igexin.push.core.e.e)) {
            com.igexin.push.core.e.e eVarA = com.igexin.push.core.e.e.a();
            com.igexin.push.core.e.e = str;
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) eVarA.new AnonymousClass22(str), false, true);
        }
        return str;
    }

    private static String n() {
        return Build.BRAND;
    }

    private static String o() {
        return Build.MODEL;
    }

    private static boolean p() {
        return Build.VERSION.SDK_INT > 28;
    }
}
