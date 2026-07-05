package com.sqwan.common.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.sqwan.msdk.api.IMUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class NavigationUtils {
    public static int getNavigationBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", IMUrl.OS);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean hasNavigationBar(Context context) {
        if (getNavigationBarHeight(context) == 0) {
            return false;
        }
        if (RomUtil.isEmui() && isHuaWeiHideNav(context)) {
            return false;
        }
        if (RomUtil.isMiui() && isMiuiFullScreen(context)) {
            return false;
        }
        if (RomUtil.isVivo() && isVivoFullScreen(context)) {
            return false;
        }
        return isHasNavigationBar(context);
    }

    private static boolean isHuaWeiHideNav(Context context) {
        int i;
        if (Build.VERSION.SDK_INT < 21) {
            i = Settings.System.getInt(context.getContentResolver(), "navigationbar_is_min", 0);
        } else {
            i = Settings.Global.getInt(context.getContentResolver(), "navigationbar_is_min", 0);
        }
        return i != 0;
    }

    private static boolean isMiuiFullScreen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "force_fsg_nav_bar", 0) != 0;
    }

    private static boolean isVivoFullScreen(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "navigation_gesture_on", 0) != 0;
    }

    public static boolean isHasNavigationBar(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return false;
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics2);
        int i3 = displayMetrics2.heightPixels;
        int i4 = displayMetrics2.widthPixels;
        Display defaultDisplay2 = windowManager.getDefaultDisplay();
        Point point = new Point();
        defaultDisplay2.getRealSize(new Point());
        defaultDisplay2.getSize(point);
        return i2 - i4 > 0 || i - i3 > 0;
    }
}
