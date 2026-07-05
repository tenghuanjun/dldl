package com.sq.sdk.tool.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Process;
import android.text.TextUtils;
import com.sqwan.msdk.api.IMUrl;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AppUtils {
    public static boolean isAppExist(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return false;
        }
    }

    public static int getAppVersionCode(Context context, String str) {
        if (context == null || str == null) {
            return -1;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            SqLogUtil.e("Error :" + str + " is not exist.");
            return -1;
        } catch (Exception unused2) {
            return -1;
        }
    }

    public static String getAppVersionName(Context context) {
        return context == null ? "" : getAppVersionName(context, context.getPackageName());
    }

    public static String getAppVersionName(Context context, String str) {
        if (context == null || str == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            SqLogUtil.e("Error :" + str + " is not exist.");
            return "";
        } catch (Exception unused2) {
            return "";
        }
    }

    public static String getAppLabel(Context context, String str) {
        if (str == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return packageManager.getApplicationLabel(applicationInfo).toString();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getPackageName(Intent intent) {
        ComponentName component;
        if (intent == null || (component = intent.getComponent()) == null) {
            return null;
        }
        return component.getPackageName();
    }

    public static List<ResolveInfo> getLauncherApps(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        return packageManager.queryIntentActivities(intent, 0);
    }

    public static List<PackageInfo> getLauncherableApps(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : installedPackages) {
            if (packageManager.getLaunchIntentForPackage(packageInfo.applicationInfo.packageName) != null && !packageName.equals(packageInfo.packageName)) {
                arrayList.add(packageInfo);
            }
        }
        return arrayList;
    }

    public static void killProcess() {
        killProcess(Process.myPid());
    }

    public static void killProcess(int i) {
        Process.killProcess(i);
    }

    public static String getSelfTopActivityName(Context context) {
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks.size() > 0) {
                return runningTasks.get(0).topActivity.getClassName();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getDefaultLauncher(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveInfoResolveActivity.activityInfo == null || resolveInfoResolveActivity.activityInfo.packageName.equals(IMUrl.OS)) {
            return null;
        }
        return resolveInfoResolveActivity.activityInfo.packageName;
    }

    public static PackageInfo getAppPackageInfo(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public static ApplicationInfo getApplicationInfo(Context context, String str) {
        if (str == null) {
            return null;
        }
        try {
            return context.getPackageManager().getApplicationInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
