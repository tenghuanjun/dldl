package com.sy37sdk.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.sqwan.bugless.util.DateUtil;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.common.web.SY37web;
import com.sqwan.msdk.BaseSQwanCore;
import com.sqwan.msdk.config.MultiSdkManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Deprecated
public class AppUtils {
    private static final SimpleDateFormat dateFormater = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE);

    public static PackageInfo getPackageInfo(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
            packageInfo = null;
        }
        return packageInfo == null ? new PackageInfo() : packageInfo;
    }

    public static Drawable getPackIcon(Context context, String str) {
        return getPackageInfo(context, str).applicationInfo.loadIcon(context.getPackageManager());
    }

    public static boolean checkPackInstalled(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static boolean isWorked(Context context, String str) {
        ArrayList arrayList = (ArrayList) ((ActivityManager) context.getSystemService("activity")).getRunningServices(45);
        for (int i = 0; i < arrayList.size(); i++) {
            if (((ActivityManager.RunningServiceInfo) arrayList.get(i)).service.getClassName().toString().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String toDate(long j) {
        return dateFormater.format(Long.valueOf(j * 1000));
    }

    public static void startAppFromPackage(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
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

    public static void toUri(Context context, String str) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setComponent(getDefaultBrowserIntent(context));
        intent.setData(Uri.parse(str));
        context.startActivity(intent);
    }

    @Deprecated
    public static void toSQWebUrl(Context context, String str, String str2) {
        if (str == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) SY37web.class);
        intent.setFlags(268435456);
        intent.putExtra("url", constructWebUrlParam(context, str));
        intent.putExtra("title", str2);
        context.startActivity(intent);
    }

    @Deprecated
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

    @Deprecated
    public static void toSdkUrl(Context context, String str) {
        if (str == null) {
            return;
        }
        toUri(context, constructWebUrlParam(context, str));
    }

    public static String constructWebUrlParam(Context context, String str) {
        if (Uri.parse(str).getHost() != null) {
            String str2 = "?gid=" + Util.getGameID(context) + "&pid=" + Util.getPaternerID(context) + "&dev=" + DevLogic.getInstance(context).getValue() + "&token=" + Util.getToken(context) + "&sversion=" + VersionUtil.sdkVersion + "&refer=" + Util.getRefer(context) + "&scut=" + Util.getCodeOfLogin(context) + "&host_sdk_version=" + VersionUtil.getOriginalVersion();
            if (!TextUtils.isEmpty(MultiSdkManager.getInstance().getScut3())) {
                str2 = str2 + "&scut3=" + MultiSdkManager.getInstance().getScut3();
            }
            String strReplace = str2 + "&dsid=" + (Util.getRoleInfos() != null ? Util.getRoleInfos().get(BaseSQwanCore.INFO_SERVERID) : "0") + "&os=1";
            if (str.contains("?")) {
                strReplace = strReplace.replace("?", "&");
            }
            str = str + strReplace;
        }
        LogUtil.i("SQ weburl：" + str);
        return str;
    }

    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(((Integer) cls.getField("status_bar_height").get(cls.newInstance())).intValue());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
