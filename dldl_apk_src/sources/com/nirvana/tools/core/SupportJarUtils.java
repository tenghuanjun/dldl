package com.nirvana.tools.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class SupportJarUtils {
    public static int checkSelfPermission(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str);
    }

    public static int checkSelfPermission(Context context, String str, boolean z) {
        if (checkXSupportIsAvable()) {
            return checkSelfPermissionWithX(context, str);
        }
        if (checkV7SupportIsAvable()) {
            return checkSelfPermissionWithV7(context, str);
        }
        return 0;
    }

    private static int checkSelfPermissionWithV7(Context context, String str) {
        try {
            Object objInvoke = Class.forName("androidx.core.content.ContextCompat").getDeclaredMethod("checkSelfPermission", Context.class, String.class).invoke(null, context, str);
            if (objInvoke != null) {
                return Integer.parseInt(objInvoke.toString());
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int checkSelfPermissionWithX(Context context, String str) {
        try {
            Object objInvoke = Class.forName("androidx.core.content.ContextCompat").getDeclaredMethod("checkSelfPermission", Context.class, String.class).invoke(null, context, str);
            if (objInvoke != null) {
                return Integer.parseInt(objInvoke.toString());
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static boolean checkV7SupportIsAvable() {
        try {
            return (Class.forName("android.support.v4.app.ActivityCompat") == null || Class.forName("android.support.v4.app.ActivityOptionsCompat") == null || Class.forName("android.support.v4.content.ContextCompat") == null) ? false : true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static boolean checkXSupportIsAvable() {
        try {
            return (Class.forName("androidx.core.app.ActivityCompat") == null || Class.forName("androidx.core.app.ActivityOptionsCompat") == null || Class.forName("androidx.core.content.ContextCompat") == null) ? false : true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, String str, String str2) {
        ActivityCompat.startActivityForResult(activity, intent, i, (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? null : ActivityOptionsCompat.makeCustomAnimation(activity, AppUtils.getAnimResID(activity, str), AppUtils.getAnimResID(activity, str2)).toBundle());
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, String str, String str2, boolean z) {
        if (checkXSupportIsAvable()) {
            startActivityForResultWithX(activity, intent, i, str, str2);
        } else if (checkV7SupportIsAvable()) {
            startActivityForResultWithV7(activity, intent, i, str, str2);
        }
    }

    public static void startActivityForResultWithV7(Activity activity, Intent intent, int i, String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.support.v4.app.ActivityCompat");
            Class<?> cls2 = Class.forName("android.support.v4.app.ActivityOptionsCompat");
            cls.getDeclaredMethod("startActivityForResult", Activity.class, Intent.class, Integer.TYPE, Bundle.class).invoke(null, activity, intent, Integer.valueOf(i), (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? null : (Bundle) cls2.getDeclaredMethod("toBundle", new Class[0]).invoke((ActivityOptionsCompat) cls2.getDeclaredMethod("makeCustomAnimation", Context.class, Integer.TYPE, Integer.TYPE).invoke(null, activity, Integer.valueOf(AppUtils.getAnimResID(activity, str)), Integer.valueOf(AppUtils.getAnimResID(activity, str2))), new Object[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startActivityForResultWithX(Activity activity, Intent intent, int i, String str, String str2) {
        try {
            Class<?> cls = Class.forName("androidx.core.app.ActivityCompat");
            Class<?> cls2 = Class.forName("androidx.core.app.ActivityOptionsCompat");
            cls.getDeclaredMethod("startActivityForResult", Activity.class, Intent.class, Integer.TYPE, Bundle.class).invoke(null, activity, intent, Integer.valueOf(i), (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? null : (Bundle) cls2.getDeclaredMethod("toBundle", new Class[0]).invoke(cls2.getDeclaredMethod("makeCustomAnimation", Context.class, Integer.TYPE, Integer.TYPE).invoke(null, activity, Integer.valueOf(AppUtils.getAnimResID(activity, str)), Integer.valueOf(AppUtils.getAnimResID(activity, str2))), new Object[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
