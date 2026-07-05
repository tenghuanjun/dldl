package com.igexin.push.f;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.mobile.auth.gatewayauth.Constant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d {
    private static final String a = "ro.miui.ui.version.name";
    private static final String b = "ro.miui.ui.version.code";
    private static volatile Boolean c;
    private static PackageInfo d;

    public static Pair<ServiceInfo, Class> a(Context context, Class cls) {
        boolean z;
        try {
            if (d == null) {
                d = context.getPackageManager().getPackageInfo(context.getPackageName(), 4);
            }
            ServiceInfo[] serviceInfoArr = d.services;
            if (serviceInfoArr != null && serviceInfoArr.length > 0) {
                int length = serviceInfoArr.length;
                for (int i = 0; i < length; i++) {
                    ServiceInfo serviceInfo = serviceInfoArr[i];
                    try {
                        Class<?> cls2 = Class.forName(serviceInfo.name);
                        if (cls2 != cls) {
                            Class<?> superclass = cls2;
                            for (int i2 = 5; superclass != null && i2 > 0; i2--) {
                                if (superclass == cls) {
                                    z = true;
                                    break;
                                }
                                if (superclass.getSuperclass() == null) {
                                    break;
                                }
                                superclass = superclass.getSuperclass();
                            }
                            z = false;
                            if (z) {
                                return Pair.create(serviceInfo, cls2);
                            }
                            continue;
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.c.a().a(" findGtImplClassInManifest error = " + th.toString());
        }
        return Pair.create(null, null);
    }

    private static String a(Context context) {
        try {
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()), 0);
            return (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) ? "" : listQueryIntentActivities.get(0).activityInfo.name;
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String a(String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ".concat(String.valueOf(str))).getInputStream()), 1024);
            try {
                String line = bufferedReader.readLine();
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                }
                return line;
            } catch (Exception unused2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean a() {
        try {
            if (c != null) {
                return c.booleanValue();
            }
            Boolean boolValueOf = Boolean.valueOf((!"Xiaomi".equalsIgnoreCase(com.igexin.push.core.e.D) && TextUtils.isEmpty(a(a)) && TextUtils.isEmpty(a(b))) ? false : true);
            c = boolValueOf;
            return boolValueOf.booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean a(int i) {
        try {
            if (com.igexin.push.core.e.i == null) {
                return false;
            }
            Intent intent = new Intent();
            intent.setAction("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            intent.putExtra("packageName", com.igexin.push.core.e.i.getPackageName());
            Intent launchIntentForPackage = com.igexin.push.core.e.i.getPackageManager().getLaunchIntentForPackage(com.igexin.push.core.e.i.getPackageName());
            if (launchIntentForPackage == null || launchIntentForPackage.getComponent() == null) {
                return false;
            }
            intent.putExtra("className", launchIntentForPackage.getComponent().getClassName());
            intent.putExtra("notificationNum", i);
            com.igexin.push.core.e.i.sendBroadcast(intent);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static synchronized boolean a(int i, boolean z) {
        if (com.igexin.push.core.e.i == null) {
            return false;
        }
        String str = com.igexin.push.core.e.D;
        if ("huawei".equalsIgnoreCase(str) || "honor".equalsIgnoreCase(str)) {
            int iIntValue = ((Integer) o.b(com.igexin.push.core.e.i, o.f, 0)).intValue();
            if (!z) {
                i += iIntValue;
            }
            o.a(com.igexin.push.core.e.i, o.f, Integer.valueOf(i));
            Bundle bundle = new Bundle();
            bundle.putString("package", com.igexin.push.core.e.d);
            bundle.putString("class", a(com.igexin.push.core.e.i));
            bundle.putInt("badgenumber", i);
            Uri uri = Uri.parse("content://com.huawei.android.launcher.settings/badge/");
            Uri uri2 = Uri.parse("content://com.hihonor.android.launcher.settings/badge/");
            if (TextUtils.isEmpty(com.igexin.push.core.e.i.getContentResolver().getType(uri))) {
                uri = uri2;
            }
            com.igexin.push.core.e.i.getContentResolver().call(uri, "change_badge", (String) null, bundle);
            return true;
        }
        return false;
    }

    public static boolean a(String... strArr) {
        for (int i = 0; i < 5; i++) {
            if (TextUtils.isEmpty(strArr[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(int i) {
        try {
            Intent intent = new Intent("com.oppo.unsettledevent");
            intent.putExtra("packageName", com.igexin.push.core.e.i.getPackageName());
            intent.putExtra(Constant.LOGIN_ACTIVITY_NUMBER, i);
            intent.putExtra("upgradeNumber", i);
            List<ResolveInfo> listQueryBroadcastReceivers = com.igexin.push.core.e.i.getPackageManager().queryBroadcastReceivers(intent, 0);
            if (listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() > 0) {
                com.igexin.push.core.e.i.sendBroadcast(intent);
                return true;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("app_badge_count", i);
            com.igexin.push.core.e.i.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String) null, bundle);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
