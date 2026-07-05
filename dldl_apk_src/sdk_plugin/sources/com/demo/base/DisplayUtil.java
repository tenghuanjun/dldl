package com.demo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.sqwan.msdk.api.IMUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DisplayUtil {
    public static int dip2px(float f, float f2) {
        return (int) ((f * f2) + 0.5f);
    }

    public static int px2dip(float f, float f2) {
        return (int) ((f / f2) + 0.5f);
    }

    public static int px2sp(float f, float f2) {
        return (int) ((f / f2) + 0.5f);
    }

    public static int sp2px(float f, float f2) {
        return (int) ((f * f2) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void setViewMeasurement(View view, int i, int i2, double d) {
        if (i < i2) {
            i = i2;
        }
        int i3 = (int) (((double) i) * d);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = i3;
        layoutParams.height = i3;
        layoutParams.setMargins(0, (i3 / 2) + i3, i3, 0);
    }

    public static int getScreenWidth(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    public static int getScreenLong(Context context) {
        return Math.max(getScreenWidth(context), getScreenHeight(context));
    }

    public static int getScreenShort(Context context) {
        return Math.min(getScreenWidth(context), getScreenHeight(context));
    }

    public static boolean isLandscape(Context context) {
        return getScreenWidth(context) > getScreenHeight(context);
    }

    public static boolean isCutoutScreen(Activity activity) {
        if (activity == null) {
            return false;
        }
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return activity.getWindow().getDecorView().getRootWindowInsets().getDisplayCutout() != null;
        }
        String str = Build.MANUFACTURER;
        if (!TextUtils.isEmpty(str)) {
            if (str.equalsIgnoreCase("HUAWEI")) {
                return hwCutout(activity);
            }
            if (str.equalsIgnoreCase("xiaomi")) {
                return xiaomiCutout(activity);
            }
            if (str.equalsIgnoreCase("oppo")) {
                return oppoCutout(activity);
            }
            if (str.equalsIgnoreCase("vivo")) {
                return vivoCutout(activity);
            }
        }
        return false;
    }

    public static int getStatusBarHeight(Activity activity) {
        int identifier;
        if (activity == null || (identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", IMUrl.OS)) <= 0) {
            return 0;
        }
        return activity.getResources().getDimensionPixelSize(identifier);
    }

    public static boolean hwCutout(Activity activity) {
        try {
            Class<?> clsLoadClass = activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((Boolean) clsLoadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(clsLoadClass, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean oppoCutout(Activity activity) {
        return activity.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    public static boolean vivoCutout(Activity activity) {
        try {
            Class<?> cls = Class.forName("android.util.FtFeature");
            return ((Boolean) cls.getMethod("isFeatureSupport", Integer.TYPE).invoke(cls, 32)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xiaomiCutout(Activity activity) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Integer) cls.getMethod("getInt", String.class, Integer.TYPE).invoke(cls, "ro.miui.notch", 0)).intValue() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void setViewSize(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = dip2px(view.getContext(), i);
        layoutParams.height = dip2px(view.getContext(), i2);
        view.setLayoutParams(layoutParams);
    }
}
