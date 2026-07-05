package com.huya.berry.module.live;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import com.duowan.auk.util.L;
import com.sqwan.common.util.RomUtil;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PressionSettingUtil {
    public static void showPressionSetting(Activity activity) throws OpenFalseException {
        String str = Build.MANUFACTURER;
        L.info("MainActivity", Build.MODEL + "--------" + Build.MANUFACTURER);
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

    private static void goHuaWeiMainager(Activity activity) throws OpenFalseException {
        try {
            Intent intent = new Intent("demo.vincent.com.tiaozhuan");
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            goIntentSetting(activity);
        }
    }

    private static void goXiaoMiMainager(Activity activity) throws OpenFalseException {
        try {
            Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            intent.putExtra("extra_pkgname", activity.getPackageName());
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            goIntentSetting(activity);
        }
    }

    public static void goMeizuMainager(Activity activity) throws OpenFalseException {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.putExtra(BillingClientConstants.PACKAGE_NAME, activity.getPackageName());
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            goIntentSetting(activity);
        }
    }

    private static void goSangXinMainager(Activity activity) throws OpenFalseException {
        goIntentSetting(activity);
    }

    private static void goIntentSetting(Activity activity) throws OpenFalseException {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            throw new OpenFalseException(e.toString());
        }
    }

    private static void goOppoMainager(Activity activity) throws OpenFalseException {
        if (!doStartApplicationWithPackageName(activity, "com.coloros.safecenter")) {
            throw new OpenFalseException("");
        }
    }

    private static void goCoolpadMainager(Activity activity) throws OpenFalseException {
        doStartApplicationWithPackageName(activity, "com.yulong.android.security:remote");
    }

    private static void goVivoMainager(Activity activity) throws OpenFalseException {
        try {
            doStartApplicationWithPackageName(activity, "com.bairenkeji.icaller");
        } catch (OpenFalseException unused) {
            doStartApplicationWithPackageName(activity, "com.iqoo.secure");
        }
    }

    private static boolean doStartApplicationWithPackageName(Activity activity, String str) throws OpenFalseException {
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                throw new OpenFalseException("");
            }
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(packageInfo.packageName);
            List<ResolveInfo> listQueryIntentActivities = activity.getPackageManager().queryIntentActivities(intent, 0);
            if (!listQueryIntentActivities.iterator().hasNext()) {
                throw new OpenFalseException("");
            }
            ResolveInfo next = listQueryIntentActivities.iterator().next();
            if (next == null) {
                return true;
            }
            String str2 = next.activityInfo.packageName;
            String str3 = next.activityInfo.name;
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.setComponent(new ComponentName(str2, str3));
            intent2.setFlags(335544320);
            try {
                activity.getApplication().startActivity(intent2);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                goIntentSetting(activity);
                return true;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            L.error(e2.toString());
            throw new OpenFalseException(e2.toString());
        }
    }

    public static class OpenFalseException extends Exception {
        public OpenFalseException(String str) {
            super(str);
        }
    }
}
