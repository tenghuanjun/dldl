package com.tencent.open.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f1088a = false;

    public static String a(Context context, String str, String str2) {
        if (context != null && str2 != null && str != null) {
            if ("com.tencent.mobileqq".equals(str2)) {
                return b(context, str, "com.tencent.mobileqq");
            }
            if (Constants.PACKAGE_TIM.equals(str2)) {
                return b(context, str, Constants.PACKAGE_TIM);
            }
        }
        return null;
    }

    private static String b(Context context, String str, String str2) {
        if (!f1088a) {
            return null;
        }
        try {
            Cursor cursorC = c(context, str, str2);
            if (cursorC == null) {
                SLog.e("openSDK_LOG.OpenApiProviderUtils", "queryTargetAppVersion null");
                return null;
            }
            if (cursorC.getCount() <= 0) {
                SLog.e("openSDK_LOG.OpenApiProviderUtils", "queryTargetAppVersion empty");
                return null;
            }
            cursorC.moveToFirst();
            String string = cursorC.getString(0);
            cursorC.close();
            SLog.i("openSDK_LOG.OpenApiProviderUtils", "AppVersion: " + string);
            return a(string) ? string : Constants.APP_VERSION_UNKNOWN;
        } catch (Exception e) {
            SLog.e("openSDK_LOG.OpenApiProviderUtils", "queryTargetAppVersion exception: ", e);
            return null;
        }
    }

    private static Cursor c(Context context, String str, String str2) {
        if (context == null) {
            return null;
        }
        try {
            return context.getContentResolver().query(Uri.parse("content://" + str2 + ".openapi.provider/query_app_version?appid=" + str + "&pkgName=" + context.getPackageName()), new String[0], null, null, null);
        } catch (Exception e) {
            SLog.e("openSDK_LOG.OpenApiProviderUtils", "query exception: ", e);
            return null;
        }
    }

    private static boolean a(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length < 3) {
            return false;
        }
        for (String str2 : strArrSplit) {
            try {
                Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        return true;
    }
}
