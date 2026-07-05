package com.sqwan.common.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CutoutUtil {
    public static final int VIVO_FILLET = 8;
    public static final int VIVO_NOTCH = 32;

    public static boolean hasNotchScreen(Activity activity) {
        return getInt("ro.miui.notch", activity) == 1 || hasNotchAtHuawei(activity) || hasNotchAtOPPO(activity) || hasNotchAtVivo(activity);
    }

    public static int getInt(String str, Activity activity) {
        if (!isXiaomi()) {
            return 0;
        }
        try {
            Class<?> clsLoadClass = activity.getClassLoader().loadClass("android.os.SystemProperties");
            return ((Integer) clsLoadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(clsLoadClass, new String(str), new Integer(0))).intValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return 0;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return 0;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return 0;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return 0;
        }
    }

    public static boolean hasNotchAtHuawei(Context context) {
        try {
            try {
                try {
                    Class<?> clsLoadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                    return ((Boolean) clsLoadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(clsLoadClass, new Object[0])).booleanValue();
                } catch (NoSuchMethodException unused) {
                    LogUtil.e("hasNotchAtHuawei NoSuchMethodException");
                    return false;
                }
            } catch (ClassNotFoundException unused2) {
                LogUtil.e("hasNotchAtHuawei ClassNotFoundException");
                return false;
            } catch (Exception unused3) {
                LogUtil.e("hasNotchAtHuawei Exception");
                return false;
            }
        } catch (Throwable unused4) {
            return false;
        }
    }

    public static boolean hasNotchAtVivo(Context context) {
        try {
            try {
                Class<?> clsLoadClass = context.getClassLoader().loadClass("android.util.FtFeature");
                return ((Boolean) clsLoadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(clsLoadClass, 32)).booleanValue();
            } catch (ClassNotFoundException unused) {
                LogUtil.e("hasNotchAtVivo ClassNotFoundException");
                return false;
            } catch (NoSuchMethodException unused2) {
                LogUtil.e("hasNotchAtVivo NoSuchMethodException");
                return false;
            } catch (Exception unused3) {
                LogUtil.e("hasNotchAtVivo Exception");
                return false;
            }
        } catch (Throwable unused4) {
            return false;
        }
    }

    public static boolean hasNotchAtOPPO(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    public static boolean isXiaomi() {
        return "Xiaomi".equals(Build.MANUFACTURER);
    }

    public static int getNavigationBarHeight(Context context) {
        if (NavigationUtils.hasNavigationBar(context)) {
            return NavigationUtils.getNavigationBarHeight(context);
        }
        LogUtil.e("ScreenOrientationHelper", "navigation bar is 0");
        return 0;
    }
}
