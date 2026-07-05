package com.duowan.live.common;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.duowan.auk.ui.toast.ToastCompat;
import com.sqwan.common.util.RomUtil;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PressionSettingUtil {
    public static void showPressionSetting(Activity activity) {
        String str = Build.MANUFACTURER;
        Log.i("MainActivity", Build.MODEL + "--------" + Build.MANUFACTURER);
        if ("HUAWEI".equals(str)) {
            goHuaWeiMainager(activity);
            return;
        }
        if ("vivo".equals(str)) {
            goVivoMainager(activity);
            return;
        }
        if (RomUtil.ROM_OPPO.equals(str)) {
            goOppoMainager(activity);
            return;
        }
        if ("Coolpad".equals(str)) {
            goCoolpadMainager(activity);
            return;
        }
        if ("Meizu".equals(str)) {
            goMeizuMainager(activity);
            return;
        }
        if ("Xiaomi".equals(str)) {
            goXiaoMiMainager(activity);
        } else if ("samsung".equals(str)) {
            goSangXinMainager(activity);
        } else {
            goIntentSetting(activity);
        }
    }

    private static void goHuaWeiMainager(Activity activity) {
        try {
            Intent intent = new Intent("demo.vincent.com.tiaozhuan");
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            activity.startActivity(intent);
        } catch (Exception e) {
            ToastCompat.makeText((Context) activity, (CharSequence) "跳转失败", 1).show();
            e.printStackTrace();
            goIntentSetting(activity);
        }
    }

    private static void goXiaoMiMainager(Activity activity) {
        try {
            Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            intent.putExtra("extra_pkgname", activity.getPackageName());
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            goIntentSetting(activity);
        }
    }

    public static void goMeizuMainager(Activity activity) {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.putExtra(BillingClientConstants.PACKAGE_NAME, "xiang.settingpression");
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            goIntentSetting(activity);
        }
    }

    private static void goSangXinMainager(Activity activity) {
        goIntentSetting(activity);
    }

    private static void goIntentSetting(Activity activity) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void goOppoMainager(Activity activity) {
        doStartApplicationWithPackageName(activity, "com.coloros.safecenter");
    }

    private static void goCoolpadMainager(Activity activity) {
        doStartApplicationWithPackageName(activity, "com.yulong.android.security:remote");
    }

    private static void goVivoMainager(Activity activity) {
        doStartApplicationWithPackageName(activity, "com.bairenkeji.icaller");
    }

    private static void doStartApplicationWithPackageName(Activity activity, String str) {
        PackageInfo packageInfo;
        ResolveInfo next = null;
        try {
            packageInfo = activity.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(packageInfo.packageName);
        List<ResolveInfo> listQueryIntentActivities = activity.getPackageManager().queryIntentActivities(intent, 0);
        Log.i("MainActivity", "resolveinfoList" + listQueryIntentActivities.size());
        for (int i = 0; i < listQueryIntentActivities.size(); i++) {
            Log.i("MainActivity", listQueryIntentActivities.get(i).activityInfo.packageName + listQueryIntentActivities.get(i).activityInfo.name);
        }
        try {
            next = listQueryIntentActivities.iterator().next();
        } catch (NoSuchElementException e2) {
            e2.printStackTrace();
        }
        if (next != null) {
            String str2 = next.activityInfo.packageName;
            String str3 = next.activityInfo.name;
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.setComponent(new ComponentName(str2, str3));
            try {
                activity.startActivity(intent2);
                return;
            } catch (Exception e3) {
                goIntentSetting(activity);
                e3.printStackTrace();
                return;
            }
        }
        goIntentSetting(activity);
    }
}
