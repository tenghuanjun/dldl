package com.getui.gtc.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.getui.gtc.BuildConfig;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.e.c;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d implements b {
    private String a;
    private boolean b = true;
    private long c = 43200000;

    private static String a() {
        StringBuilder sb = new StringBuilder();
        sb.append(BuildConfig.VERSION_NAME);
        sb.append(com.igexin.push.core.b.aj);
        try {
            Class<?> cls = Class.forName("com.igexin.sdk.PushManager");
            Method declaredMethod = cls.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            Method declaredMethod2 = cls.getDeclaredMethod("getVersion", Context.class);
            declaredMethod2.setAccessible(true);
            sb.append("GT-".concat(String.valueOf((String) declaredMethod2.invoke(objInvoke, GtcProvider.context()))));
            sb.append(com.igexin.push.core.b.aj);
        } catch (Throwable unused) {
        }
        try {
            Class<?> cls2 = Class.forName("com.getui.gis.sdk.GInsightManager");
            Method declaredMethod3 = cls2.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod3.setAccessible(true);
            Object objInvoke2 = declaredMethod3.invoke(null, new Object[0]);
            Method declaredMethod4 = cls2.getDeclaredMethod("version", new Class[0]);
            declaredMethod4.setAccessible(true);
            sb.append((String) declaredMethod4.invoke(objInvoke2, new Object[0]));
            sb.append(com.igexin.push.core.b.aj);
        } catch (Throwable unused2) {
        }
        try {
            Class<?> cls3 = Class.forName("com.getui.gs.sdk.GsManager");
            Method declaredMethod5 = cls3.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod5.setAccessible(true);
            Object objInvoke3 = declaredMethod5.invoke(null, new Object[0]);
            Method declaredMethod6 = cls3.getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod6.setAccessible(true);
            sb.append((String) declaredMethod6.invoke(objInvoke3, new Object[0]));
            sb.append(com.igexin.push.core.b.aj);
        } catch (Throwable unused3) {
        }
        try {
            Class<?> cls4 = Class.forName("com.g.gysdk.GYManager");
            Method declaredMethod7 = cls4.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod7.setAccessible(true);
            Object objInvoke4 = declaredMethod7.invoke(null, new Object[0]);
            Method declaredMethod8 = cls4.getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod8.setAccessible(true);
            sb.append((String) declaredMethod8.invoke(objInvoke4, new Object[0]));
            sb.append(com.igexin.push.core.b.aj);
        } catch (Throwable unused4) {
        }
        try {
            Class<?> cls5 = Class.forName("com.getui.ctid.CTIDManager");
            Method declaredMethod9 = cls5.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod9.setAccessible(true);
            Object objInvoke5 = declaredMethod9.invoke(null, new Object[0]);
            Method declaredMethod10 = cls5.getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod10.setAccessible(true);
            sb.append((String) declaredMethod10.invoke(objInvoke5, new Object[0]));
            sb.append(com.igexin.push.core.b.aj);
        } catch (Throwable unused5) {
        }
        try {
            Class<?> cls6 = Class.forName("com.getui.bxsdk.BXManager");
            Method declaredMethod11 = cls6.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod11.setAccessible(true);
            Object objInvoke6 = declaredMethod11.invoke(null, new Object[0]);
            Method declaredMethod12 = cls6.getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod12.setAccessible(true);
            sb.append((String) declaredMethod12.invoke(objInvoke6, new Object[0]));
            sb.append(com.igexin.push.core.b.aj);
        } catch (Throwable unused6) {
        }
        try {
            Method declaredMethod13 = Class.forName("com.getui.iop.IopManager").getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod13.setAccessible(true);
            sb.append((String) declaredMethod13.invoke(null, new Object[0]));
            sb.append(com.igexin.push.core.b.aj);
        } catch (Throwable unused7) {
        }
        String string = sb.toString();
        return string.endsWith(com.igexin.push.core.b.aj) ? string.substring(0, string.length() - 1) : string;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map<String, String> mapA = com.getui.gtc.f.b.a(null);
        if (mapA != null && mapA.size() > 0) {
            try {
                if (mapA.containsKey("sdk.gtc.type302.enable")) {
                    this.b = Boolean.parseBoolean(mapA.get("sdk.gtc.type302.enable"));
                }
            } catch (Exception e) {
                com.getui.gtc.i.c.a.a(e);
            }
            try {
                if (mapA.containsKey("sdk.gtc.type302.interval")) {
                    this.c = Long.parseLong(mapA.get("sdk.gtc.type302.interval")) * 1000;
                }
            } catch (Exception e2) {
                com.getui.gtc.i.c.a.a(e2);
            }
        }
        if (!this.b) {
            com.getui.gtc.i.c.a.b("type 302 is not enabled");
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String str = "";
        String strValueOf = "";
        try {
            PackageInfo packageInfo = GtcProvider.context().getPackageManager().getPackageInfo(GtcProvider.context().getPackageName(), 0);
            str = packageInfo.versionName;
            strValueOf = String.valueOf(packageInfo.versionCode);
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.a(th);
        }
        this.a = a.a(simpleDateFormat.format(new Date())) + "|" + a.a(com.getui.gtc.c.b.d) + "|" + a.a(com.getui.gtc.c.b.a) + "|" + a.a(GtcProvider.context().getPackageName()) + "|" + a.a(str) + "|" + a.a(strValueOf) + "|android|" + a.a(a());
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - c.a.a.a.j < this.c) {
                return;
            }
            com.getui.gtc.h.a.a(this.a, 302);
            com.getui.gtc.e.d dVar = c.a.a.a;
            if (dVar.a(15, jCurrentTimeMillis)) {
                dVar.j = jCurrentTimeMillis;
            }
        } catch (Exception e3) {
            com.getui.gtc.i.c.a.c("type 302 report error: " + e3.toString());
        }
    }
}
