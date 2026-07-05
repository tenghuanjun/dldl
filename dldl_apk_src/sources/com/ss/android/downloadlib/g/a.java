package com.ss.android.downloadlib.g;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return com.ss.android.a.c.a(new File(str));
    }

    public static int a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return 5;
        }
        return com.ss.android.a.c.a(str, new File(str2));
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = com.ss.android.downloadlib.addownload.k.a().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.sourceDir;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static void a() {
        if (com.ss.android.downloadlib.addownload.k.j().optInt("hook", 0) != 1) {
            return;
        }
        com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.g.a.1
            @Override // java.lang.Runnable
            public void run() {
                com.ss.android.socialbase.appdownloader.f.e.h();
                a.c();
            }
        }, com.igexin.push.config.c.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c() {
        Field declaredField;
        Class<?> cls;
        try {
            if (Build.VERSION.SDK_INT < 26) {
                declaredField = Class.forName("android.app.ActivityManagerNative").getDeclaredField("gDefault");
            } else {
                declaredField = Class.forName("android.app.ActivityManager").getDeclaredField("IActivityManagerSingleton");
            }
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            Field declaredField2 = Class.forName("android.util.Singleton").getDeclaredField("mInstance");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            if (obj2 == null || (cls = Class.forName("android.app.IActivityManager")) == null) {
                return;
            }
            declaredField2.set(obj, Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, new C0098a(obj2)));
        } catch (Throwable unused) {
        }
    }

    public static void a(Object[] objArr) {
        if (com.ss.android.downloadlib.addownload.k.j().optInt("hook", 0) == 1 && (objArr[1] instanceof String) && (objArr[2] instanceof Intent)) {
            Intent intent = (Intent) objArr[2];
            if ("android.intent.action.VIEW".equals(intent.getAction()) && DownloadConstants.MIME_APK.equals(intent.getType())) {
                if (com.ss.android.socialbase.appdownloader.f.e.d()) {
                    String strOptString = com.ss.android.downloadlib.addownload.k.j().optString("hook_vivo_arg", "com.android.settings");
                    if ("null".equals(strOptString)) {
                        return;
                    }
                    objArr[1] = strOptString;
                    return;
                }
                if (com.ss.android.socialbase.appdownloader.f.e.e()) {
                    String strOptString2 = com.ss.android.downloadlib.addownload.k.j().optString("hook_kllk_arg1", "com." + DownloadConstants.LOWER_OPPO + ".market");
                    if (!"null".equals(strOptString2)) {
                        objArr[1] = strOptString2;
                    }
                    String strOptString3 = com.ss.android.downloadlib.addownload.k.j().optString("hook_kllk_arg2", "com.android.browser");
                    String strOptString4 = com.ss.android.downloadlib.addownload.k.j().optString("hook_kllk_arg3", "m.store." + DownloadConstants.LOWER_OPPO + "mobile.com");
                    StringBuilder sb = new StringBuilder();
                    sb.append(DownloadConstants.LOWER_OPPO);
                    sb.append("_extra_pkg_name");
                    intent.putExtra(sb.toString(), strOptString3);
                    intent.putExtra("refererHost", strOptString4);
                    if (com.ss.android.downloadlib.addownload.k.j().optInt("hook_kllk_arg4", 0) == 1) {
                        Intent intent2 = new Intent();
                        intent2.putExtra(DownloadConstants.LOWER_OPPO + "_extra_pkg_name", strOptString3);
                        intent2.putExtra("refererHost", strOptString4);
                        intent.putExtra("android.intent.extra.INTENT", intent2);
                        return;
                    }
                    return;
                }
                if (com.ss.android.socialbase.appdownloader.f.e.a()) {
                    String strOptString5 = com.ss.android.downloadlib.addownload.k.j().optString("hook_huawei_arg1", "com.huawei.appmarket");
                    if (!"null".equals(strOptString5)) {
                        objArr[1] = strOptString5;
                    }
                    intent.putExtra("caller_package", com.ss.android.downloadlib.addownload.k.j().optString("hook_huawei_arg2", "com.huawei.appmarket"));
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.ss.android.downloadlib.g.a$a, reason: collision with other inner class name */
    private static class C0098a implements InvocationHandler {
        private Object a;

        private C0098a(Object obj) {
            this.a = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                if ("startActivity".contains(method.getName())) {
                    a.a(objArr);
                }
            } catch (Throwable unused) {
            }
            return method.invoke(this.a, objArr);
        }
    }
}
