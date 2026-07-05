package com.sq.sdk.tool.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GoogleMarketUtils {

    public enum MarketOpenResult {
        success_googleplay,
        success_browser,
        fail
    }

    public static boolean isMarketExist(Context context) {
        return AppUtils.isAppExist(context, "com.android.vending");
    }

    public static void goToMarketMyApp(Context context) {
        gotoMarket(context, getAppDetailUrl(context, context.getPackageName()), true);
    }

    public static String getAppDetailUrl(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        if (isMarketExist(context)) {
            return "market://details?id=" + str;
        }
        return "https://play.google.com/store/apps/details?id=" + str;
    }

    public static MarketOpenResult gotoMarket(Context context, String str, boolean z) {
        if (context == null || TextUtils.isEmpty(str)) {
            return MarketOpenResult.fail;
        }
        if (isMarketExist(context)) {
            try {
                if (!str.startsWith("market://details?id=")) {
                    if (str.startsWith("http://play.google.com/store/apps/details") || str.startsWith("https://play.google.com/store/apps/details")) {
                        str = str.substring(str.indexOf("id=") + 3);
                    } else {
                        if (!str.startsWith("http://")) {
                            if (str.startsWith("https://")) {
                            }
                        }
                        return gotoBrowser(context, str) ? MarketOpenResult.success_browser : MarketOpenResult.fail;
                    }
                    str = "market://details?id=" + str;
                }
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.setPackage("com.android.vending");
                if (context instanceof Activity) {
                    intent.addFlags(524288);
                    intent.addFlags(1073741824);
                } else {
                    intent.addFlags(268435456);
                    intent.addFlags(32768);
                }
                context.startActivity(intent);
                return MarketOpenResult.success_googleplay;
            } catch (Exception unused) {
                gotoBrowser(context, str);
            }
        } else if (z) {
            return gotoBrowser(context, str) ? MarketOpenResult.success_browser : MarketOpenResult.fail;
        }
        return MarketOpenResult.fail;
    }

    private static boolean gotoBrowser(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            if (!str.startsWith("http://play.google.com/store/apps/details") && !str.startsWith("https://play.google.com/store/apps/details") && (str.startsWith("market://details?id=") || (!str.startsWith("http://") && !str.startsWith("https://")))) {
                if (str.startsWith("market://details?id=")) {
                    str = str.replace("market://details?id=", "?id=");
                }
                str = "https://play.google.com/store/apps/details" + str;
            }
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
                if (listQueryIntentActivities != null && listQueryIntentActivities.size() > 0) {
                    ActivityInfo activityInfo = listQueryIntentActivities.get(0) != null ? listQueryIntentActivities.get(0).activityInfo : null;
                    String str2 = activityInfo != null ? activityInfo.packageName : null;
                    String str3 = activityInfo != null ? activityInfo.name : null;
                    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                        intent.setClassName(str2, str3);
                    }
                }
                if (context instanceof Activity) {
                    intent.addFlags(1073741824);
                } else {
                    intent.addFlags(268435456);
                    intent.addFlags(32768);
                }
                context.startActivity(intent);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
