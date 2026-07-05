package com.sq.webview.util;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AppUtils {
    public static boolean isAppExist(Context context, String packageName) {
        if (context != null && !TextUtils.isEmpty(packageName)) {
            try {
                context.getPackageManager().getPackageInfo(packageName, 0);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static int getAppVersionCode(Context context, String packageName) {
        if (context == null || packageName == null) {
            return -1;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (Exception unused) {
            WebLogUtil.e("Error :" + packageName + " is not exist.");
            return -1;
        }
    }

    public static String getAppVersionName(Context context) {
        return getAppVersionName(context, context.getPackageName());
    }

    public static String getAppVersionName(Context context, String packageName) {
        if (context == null || packageName == null) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo != null ? packageInfo.versionName : "";
        } catch (Exception unused) {
            WebLogUtil.e("Error :" + packageName + " is not exist.");
            return "";
        }
    }

    public static String getAppLabel(Context context, String packageName) {
        if (context != null && packageName != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
                if (applicationInfo != null) {
                    return packageManager.getApplicationLabel(applicationInfo).toString();
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static void killProcess() {
        Process.killProcess(Process.myPid());
    }

    public static void startAppWithPackageName(Context ctx, String packageName) {
        Intent launchIntentForPackage = ctx.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            WebLogUtil.i("Start app successfully, app: " + packageName);
            ctx.startActivity(launchIntentForPackage);
            return;
        }
        WebLogUtil.e("Start app error, app: " + packageName + ", app not installed!");
    }

    public static void call(Context context, String phoneNumber) {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phoneNumber));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void email(Context context, String sendTo, String title, String content) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:" + sendTo));
        intent.putExtra("android.intent.extra.SUBJECT", title);
        intent.putExtra("android.intent.extra.TEXT", content);
        context.startActivity(intent);
    }

    public static void copyToClipboard(Context context, String text) {
        ((ClipboardManager) context.getSystemService("clipboard")).setText(text);
    }
}
