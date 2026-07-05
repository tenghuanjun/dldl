package com.sqwan.common.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.web.SY37web;
import com.sqwan.msdk.BaseSQwanCore;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import com.sqwan.msdk.config.MultiConfigManager;
import com.sqwan.msdk.config.MultiSdkManager;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AppUtils {
    public static String constructWebUrlParam(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (Uri.parse(str).getHost() != null) {
            SQAppConfig sQAppConfig = ConfigManager.getInstance(context).getSQAppConfig();
            String str2 = "?gid=" + sQAppConfig.getGameid() + "&pid=" + sQAppConfig.getPartner() + "&dev=" + DevLogic.getInstance(context).getValue() + "&token=" + ((IAccountMod) ModHelper.get(IAccountMod.class)).getToken() + "&sversion=" + VersionUtil.sdkVersion + "&gwversion=4.6.7&refer=" + sQAppConfig.getRefer() + "&scut=" + ConfigManager.getInstance(context).getLoginCode() + "&uid=" + ((IAccountMod) ModHelper.get(IAccountMod.class)).getUid() + "&uname=" + ((IAccountMod) ModHelper.get(IAccountMod.class)).getUname() + "&version=" + getVersionName(context) + "&policy=1&host_sdk_version=" + VersionUtil.getOriginalVersion();
            if (!TextUtils.isEmpty(MultiSdkManager.getInstance().getScut3())) {
                str2 = str2 + "&scut3=" + MultiSdkManager.getInstance().getScut3();
            }
            String strReplace = (str2 + "&dsid=" + (ConfigManager.getInstance(context).getRoleInfo() != null ? ConfigManager.getInstance(context).getRoleInfo().get(BaseSQwanCore.INFO_SERVERID) : "0") + "&os=1") + "&bindwx=" + (MultiConfigManager.getInstance().supportWx(context) ? 1 : 0);
            if (str.contains("?")) {
                strReplace = strReplace.replace("?", "&");
            }
            str = str + strReplace;
        }
        LogUtil.i("SQ weburl：" + str);
        return str;
    }

    public static void toSdkUrl(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("跳转到外部浏览器，url 为空");
        } else {
            toUri(context, constructWebUrlParam(context, str));
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

    public static void toSQWebUrl(Context context, String str, String str2) {
        toSQWebUrl(context, str, str2, false);
    }

    public static void toSQWebUrl(Context context, String str, String str2, boolean z) {
        toSQWebUrlNoAppend(context, constructWebUrlParam(context, str), str2, z);
    }

    public static void toSQWebUrlNoAppend(Context context, String str, String str2, boolean z) {
        if (str == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) SY37web.class);
        intent.setFlags(268435456);
        intent.putExtra("url", str);
        intent.putExtra("title", str2);
        intent.putExtra(SY37web.WEB_QUIT_ANIM, z);
        context.startActivity(intent);
    }

    public static void toSQWebUrlWithTitle(Context context, String str, String str2) {
        if (str == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) SY37web.class);
        intent.setFlags(268435456);
        intent.putExtra("url", constructWebUrlParam(context, str));
        intent.putExtra("title", str2);
        intent.putExtra("showTitle", true);
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

    public static void startAppFromPackage(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            if (packageInfo != null) {
                intent.setPackage(packageInfo.packageName);
            }
            ResolveInfo next = context.getPackageManager().queryIntentActivities(intent, 0).iterator().next();
            if (next != null) {
                String str2 = next.activityInfo.packageName;
                String str3 = next.activityInfo.name;
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setComponent(new ComponentName(str2, str3));
                intent2.setFlags(268435456);
                context.startActivity(intent2);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            LogUtil.e("需要启动的应用不存在！");
        }
    }

    public static String getLocaleLanguage() {
        Locale locale = Locale.getDefault();
        return locale.getLanguage() + "-" + locale.getCountry();
    }

    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static boolean isMobileNO(String str) {
        boolean z = str.length() == 11;
        if (z) {
            for (int i = 0; i < str.length(); i++) {
                try {
                    Integer.parseInt(str.charAt(i) + "");
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return z;
    }

    public static boolean saveView(Context context, View view, String str) {
        if (EnvironmentUtils.checkSdCardPermission(context)) {
            try {
                view.setBackgroundResource(SqResUtils.getIdByName("s_transparent", "color", context));
                Bitmap bitmapConvertViewToBitmap = convertViewToBitmap(view);
                if (bitmapConvertViewToBitmap == null) {
                    System.out.println("生成账密截图失败");
                    return false;
                }
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapConvertViewToBitmap);
                view.setDrawingCacheEnabled(false);
                view.destroyDrawingCache();
                String str2 = EnvironmentUtils.getCommonDirPath(context) + File.separator + "DCIM" + File.separator;
                File file = new File(str2);
                File file2 = new File(str2 + File.separator + str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (!file2.exists() && !file2.createNewFile()) {
                    return false;
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                LogUtil.w("账号已截图并保存\n" + str2);
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(Uri.fromFile(file2));
                context.sendBroadcast(intent);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("数据有误，账号截图保存失败：" + str);
                return false;
            }
        }
        System.out.println("没有存储卡权限，账号截图没法保存：" + str);
        return false;
    }

    public static Bitmap convertViewToBitmap(View view) {
        LogUtil.i("convert view to bitmap");
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    public static int getOrientation() {
        Activity activity = SQContextWrapper.getActivity();
        if (activity != null) {
            return activity.getResources().getConfiguration().orientation;
        }
        return 1;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static long getAppInstallTime(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long getAppUpdateTime(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0.0";
        }
    }
}
