package com.huya.mtp.utils;

import android.app.Activity;
import android.content.Intent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StartActivityUtil {
    public static boolean miuiSecurityCenterDetailVer5(Activity activity) {
        try {
            Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent.setFlags(268435456);
            intent.setClassName("com.android.settings", "com.miui.securitycenter.permission.AppPermissionsEditor");
            intent.putExtra("extra_package_uid", activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).applicationInfo.uid);
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean miuiSecurityCenterAutoStartVer6(Activity activity) {
        try {
            Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent.setFlags(268435456);
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
