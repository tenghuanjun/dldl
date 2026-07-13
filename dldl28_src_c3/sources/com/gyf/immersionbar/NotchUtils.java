package com.gyf.immersionbar;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import androidx.core.util.HalfKt$;
import com.bun.miitmdid.x$;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class NotchUtils {
    private static final String NOTCH_HUA_WEI = "com.huawei.android.util.HwNotchSizeUtil";
    private static final String NOTCH_OPPO = "com.oppo.feature.screen.heteromorphism";
    private static final String NOTCH_VIVO = "android.util.FtFeature";
    private static final String NOTCH_XIAO_MI = "ro.miui.notch";
    private static final String SYSTEM_PROPERTIES = "android.os.SystemProperties";

    public static boolean hasNotchScreen(Activity activity) {
        return activity != null && (hasNotchAtXiaoMi(activity) || hasNotchAtHuaWei(activity) || hasNotchAtOPPO(activity) || hasNotchAtVIVO(activity) || hasNotchAtAndroidP(activity));
    }

    public static boolean hasNotchScreen(View view) {
        return view != null && (hasNotchAtXiaoMi(view.getContext()) || hasNotchAtHuaWei(view.getContext()) || hasNotchAtOPPO(view.getContext()) || hasNotchAtVIVO(view.getContext()) || hasNotchAtAndroidP(view));
    }

    private static boolean hasNotchAtAndroidP(View view) {
        return getDisplayCutout(view) != null;
    }

    private static boolean hasNotchAtAndroidP(Activity activity) {
        return getDisplayCutout(activity) != null;
    }

    private static DisplayCutout getDisplayCutout(Activity activity) {
        Window window;
        WindowInsets windowInsetsM;
        if (Build.VERSION.SDK_INT < 28 || activity == null || (window = activity.getWindow()) == null || (windowInsetsM = x$.ExternalSyntheticApiModelOutline0.m(window.getDecorView())) == null) {
            return null;
        }
        return HalfKt$.ExternalSyntheticApiModelOutline0.m(windowInsetsM);
    }

    private static DisplayCutout getDisplayCutout(View view) {
        WindowInsets windowInsetsM;
        if (Build.VERSION.SDK_INT < 28 || view == null || (windowInsetsM = x$.ExternalSyntheticApiModelOutline0.m(view)) == null) {
            return null;
        }
        return HalfKt$.ExternalSyntheticApiModelOutline0.m(windowInsetsM);
    }

    private static boolean hasNotchAtXiaoMi(Context context) {
        int iIntValue;
        if ("Xiaomi".equals(Build.MANUFACTURER)) {
            try {
                Class<?> clsLoadClass = context.getClassLoader().loadClass(SYSTEM_PROPERTIES);
                iIntValue = ((Integer) clsLoadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(clsLoadClass, NOTCH_XIAO_MI, 0)).intValue();
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                iIntValue = 0;
            }
        } else {
            iIntValue = 0;
        }
        return iIntValue == 1;
    }

    private static boolean hasNotchAtHuaWei(Context context) {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass(NOTCH_HUA_WEI);
            return ((Boolean) clsLoadClass.getMethod("hasNotchInScreen", null).invoke(clsLoadClass, null)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException | Exception unused) {
            return false;
        }
    }

    private static boolean hasNotchAtVIVO(Context context) {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass(NOTCH_VIVO);
            return ((Boolean) clsLoadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(clsLoadClass, 32)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException | Exception unused) {
            return false;
        }
    }

    private static boolean hasNotchAtOPPO(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature(NOTCH_OPPO);
        } catch (Exception unused) {
            return false;
        }
    }

    public static int getNotchHeight(Activity activity) {
        int statusBarHeight = ImmersionBar.getStatusBarHeight(activity);
        DisplayCutout displayCutout = getDisplayCutout(activity);
        if (Build.VERSION.SDK_INT >= 28 && displayCutout != null) {
            if (activity.getResources().getConfiguration().orientation == 1) {
                return x$.ExternalSyntheticApiModelOutline0.m(displayCutout);
            }
            if (x$.ExternalSyntheticApiModelOutline0.m$1(displayCutout) == 0) {
                return x$.ExternalSyntheticApiModelOutline0.m$2(displayCutout);
            }
            return x$.ExternalSyntheticApiModelOutline0.m$1(displayCutout);
        }
        int xiaoMiNotchHeight = hasNotchAtXiaoMi(activity) ? getXiaoMiNotchHeight(activity) : 0;
        if (hasNotchAtHuaWei(activity)) {
            xiaoMiNotchHeight = getHuaWeiNotchSize(activity)[1];
        }
        if (hasNotchAtVIVO(activity) && (xiaoMiNotchHeight = dp2px(activity, 32)) < statusBarHeight) {
            xiaoMiNotchHeight = statusBarHeight;
        }
        if (!hasNotchAtOPPO(activity)) {
            return xiaoMiNotchHeight;
        }
        if (80 < statusBarHeight) {
            return statusBarHeight;
        }
        return 80;
    }

    private static int getXiaoMiNotchHeight(Context context) {
        int identifier = context.getResources().getIdentifier("notch_height", "dimen", PodInfo.GAME_TYPE_ANDROID);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private static int[] getHuaWeiNotchSize(Context context) {
        int[] iArr = {0, 0};
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass(NOTCH_HUA_WEI);
            return (int[]) clsLoadClass.getMethod("getNotchSize", null).invoke(clsLoadClass, null);
        } catch (ClassNotFoundException | NoSuchMethodException | Exception unused) {
            return iArr;
        }
    }

    private static int dp2px(Context context, int i) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }
}
