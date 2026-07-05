package com.sq.sdk.tool.util;

import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqAppUtils {
    public static String getInstalledAppsInfo(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (PackageInfo packageInfo : installedPackages) {
                String string = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                sb.append("{\"pkgname\":\"" + packageInfo.packageName + "\",\"name\":\"" + string + "\"},");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("]");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void toUri(Context context, String str) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setComponent(getDefaultBrowserIntent(context));
        intent.setData(Uri.parse(str));
        context.startActivity(intent);
    }

    private static ComponentName getDefaultBrowserIntent(Context context) {
        boolean z;
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.addCategory("android.intent.category.DEFAULT");
        ComponentName componentName = null;
        intent.setDataAndType(Uri.parse("http://"), null);
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 32);
        int size = listQueryIntentActivities.size();
        ComponentName[] componentNameArr = new ComponentName[size];
        int i = 0;
        boolean z2 = false;
        while (i < size) {
            ActivityInfo activityInfo = listQueryIntentActivities.get(i).activityInfo;
            String str = activityInfo.packageName;
            String str2 = activityInfo.name;
            if (str.contains("com.UCMobile")) {
                componentName = new ComponentName(str, str2);
                z = true;
            } else {
                z = false;
            }
            componentNameArr[i] = new ComponentName(str, str2);
            i++;
            z2 = z;
        }
        return (size <= 0 || z2) ? componentName : componentNameArr[0];
    }

    public static boolean checkAppInstalled(Context context, String str) {
        try {
            Iterator<PackageInfo> it = context.getPackageManager().getInstalledPackages(0).iterator();
            while (it.hasNext()) {
                if (it.next().packageName.equalsIgnoreCase(str)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void startAppWithPackageName(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            SqLogUtil.i("Start app successfully, app: " + str);
            context.startActivity(launchIntentForPackage);
            return;
        }
        SqLogUtil.e("Start app error, app: " + str + ", app not installed!");
    }

    public static void call(Context context, String str) {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void email(Context context, String str, String str2, String str3) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:" + str));
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.TEXT", str3);
        context.startActivity(intent);
    }

    public static void copyToClipboard(Context context, String str) {
        ((ClipboardManager) context.getSystemService("clipboard")).setText(str);
    }

    public static boolean isAppInBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        boolean z = true;
        if (Build.VERSION.SDK_INT > 20) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return true;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100) {
                    for (String str : runningAppProcessInfo.pkgList) {
                        if (str.equals(context.getPackageName())) {
                            z = false;
                        }
                    }
                }
            }
            return z;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
        return runningTasks == null || runningTasks.size() <= 0 || !runningTasks.get(0).topActivity.getPackageName().equals(context.getPackageName());
    }

    public static boolean isScreenOn(Context context) {
        return ((PowerManager) context.getSystemService("power")).isScreenOn();
    }
}
