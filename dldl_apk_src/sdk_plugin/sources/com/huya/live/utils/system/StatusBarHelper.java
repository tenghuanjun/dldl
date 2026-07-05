package com.huya.live.utils.system;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import com.sqwan.msdk.api.IMUrl;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StatusBarHelper {
    private StatusBarHelper() {
        throw new IllegalArgumentException("you can not init me！！！！！");
    }

    public static void statusBarIconWhiteBlackColor(AppCompatActivity appCompatActivity, boolean z) {
        if (z) {
            if (Build.VERSION.SDK_INT >= 23) {
                appCompatActivity.getWindow().getDecorView().setSystemUiVisibility(4096);
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            appCompatActivity.getWindow().getDecorView().setSystemUiVisibility(8192);
        }
    }

    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean z) {
        Class<?> cls = activity.getWindow().getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            Window window = activity.getWindow();
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean z) {
        if (activity != null) {
            try {
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i2 | i : (~i) & i2);
                activity.getWindow().setAttributes(attributes);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static void setStatusBarTranslucent(Activity activity, boolean z) {
        if (activity == null) {
            return;
        }
        if (z) {
            activity.getWindow().addFlags(67108864);
        } else {
            activity.getWindow().clearFlags(67108864);
        }
    }

    public static int getStatusBarHeight(Context context) {
        int identifier;
        if (context != null && (identifier = context.getResources().getIdentifier("status_bar_height", "dimen", IMUrl.OS)) > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources;
        int identifier;
        if (!hasNavBar(context) || (identifier = (resources = context.getResources()).getIdentifier("navigation_bar_height", "dimen", IMUrl.OS)) <= 0) {
            return 0;
        }
        return resources.getDimensionPixelSize(identifier);
    }

    private static boolean hasNavBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", IMUrl.OS);
        if (identifier != 0) {
            boolean z = resources.getBoolean(identifier);
            String navBarOverride = getNavBarOverride();
            if ("1".equals(navBarOverride)) {
                return false;
            }
            if ("0".equals(navBarOverride)) {
                return true;
            }
            return z;
        }
        return !ViewConfiguration.get(context).hasPermanentMenuKey();
    }

    private static String getNavBarOverride() {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(null, "qemu.hw.mainkeys");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void hideNavigationBar(Activity activity) {
        if (activity == null) {
            return;
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(Build.VERSION.SDK_INT >= 19 ? 2050 : 2);
    }
}
