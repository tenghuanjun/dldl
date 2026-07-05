package com.huya.berry.gamesdk.utils;

import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import com.duowan.auk.util.L;
import com.sqwan.msdk.api.IMUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NotchUtil {
    public static final int FLAG_NOTCH_SUPPORT = 65536;
    public static final int NOTCH_IN_SCREEN_VIVO = 32;
    public static final int ROUNDED_IN_SCREEN_VIVO = 8;
    private static final String TAG = "NotchUtil";
    private static boolean isNotch;
    private static int notchSize;

    public static int getNotchSizeAtOppo(Context context) {
        return 0;
    }

    public static int getNotchSizeAtVivo(Context context) {
        return 0;
    }

    public static void setNotchSize(int i) {
        notchSize = i;
        isNotch = true;
    }

    public static boolean needCalNotchSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        return windowManager != null && isNotch && getDisplayRotation(windowManager) == 90;
    }

    public static int getDisplayRotation(WindowManager windowManager) {
        return CommonUtil.getDisplayRotation(windowManager);
    }

    public static int getNotchSize(Context context) {
        return notchSize;
    }

    public static boolean hasNotchInScreenAtHuawei(Context context) {
        try {
            try {
                Class<?> clsLoadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                return ((Boolean) clsLoadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(clsLoadClass, new Object[0])).booleanValue();
            } catch (Exception unused) {
                L.info(TAG, "huawei hasNotchInScreen Exception");
                return false;
            }
        } catch (Throwable unused2) {
            return false;
        }
    }

    public static int getNotchSizeAtHuawei(Context context) {
        int[] iArr = {0, 0};
        try {
            try {
                Class<?> clsLoadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                return ((int[]) clsLoadClass.getMethod("getNotchSize", new Class[0]).invoke(clsLoadClass, new Object[0]))[1];
            } catch (Exception unused) {
                L.info(TAG, "huawei getNotchSize Exception");
                return iArr[1];
            }
        } catch (Throwable unused2) {
            return iArr[1];
        }
    }

    public static void setFullScreenWindowLayoutInDisplayCutout(Window window) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        try {
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            cls.getMethod("addHwFlags", Integer.TYPE).invoke(cls.getConstructor(WindowManager.LayoutParams.class).newInstance(attributes), 65536);
        } catch (Exception unused) {
            L.info(TAG, "other Exception");
        }
    }

    public static boolean hasNotchInScreenAtOppo(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    public static boolean hasNotchInScreenAtVivo(Context context) {
        try {
            try {
                Class<?> clsLoadClass = context.getClassLoader().loadClass("com.util.FtFeature");
                return ((Boolean) clsLoadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(clsLoadClass, 32)).booleanValue();
            } catch (Exception unused) {
                L.info(TAG, "hasNotchInScreen Exception");
                return false;
            }
        } catch (Throwable unused2) {
            return false;
        }
    }

    public static boolean hasNotchInScreenAtXiaomi(Context context) {
        try {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                return ((Integer) cls.getMethod("getInt", String.class, Integer.TYPE).invoke(cls, "ro.miui.notch", 0)).intValue() == 1;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int getNotchSizeAtXiaomi(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", IMUrl.OS);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
