package com.bytedance.bdtracker;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class j4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, Boolean> f271a = new ConcurrentHashMap();
    public static final Map<String, PackageInfo> b = new ConcurrentHashMap();

    public static PackageInfo a(Context context, String str, int i) {
        PackageInfo packageInfo;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        String str2 = i + ":" + context.hashCode() + "@" + str;
        Map<String, PackageInfo> map = b;
        synchronized (map) {
            if (!map.containsKey(str2)) {
                try {
                    map.put(str2, context.getPackageManager().getPackageInfo(str, i));
                } catch (Throwable unused) {
                }
            }
            packageInfo = b.get(str2);
        }
        return packageInfo;
    }

    public static boolean a(Context context, String str) {
        boolean zEquals;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        String str2 = context.hashCode() + "@" + str;
        Map<String, Boolean> map = f271a;
        synchronized (map) {
            if (map.containsKey(str2)) {
                zEquals = Boolean.TRUE.equals(f271a.get(str2));
            } else {
                try {
                    map.put(str2, Boolean.valueOf(context.getPackageManager().getPackageInfo(str, 16384) != null));
                } catch (Throwable unused) {
                    f271a.put(str2, false);
                }
                zEquals = Boolean.TRUE.equals(f271a.get(str2));
            }
        }
        return zEquals;
    }
}
