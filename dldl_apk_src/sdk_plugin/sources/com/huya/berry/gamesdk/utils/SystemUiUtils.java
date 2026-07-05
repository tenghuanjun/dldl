package com.huya.berry.gamesdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.sqwan.msdk.api.IMUrl;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SystemUiUtils {
    public static final int NavigationBarBottom = 2;
    public static final int NavigationBarRight = 1;
    public static final int NavigationBarUnknown = 0;
    private static final String TAG = "SystemUiUtils";
    private static final float sDefaultRatio = 1.8f;
    private static int sDisplayHeight;
    private static int sDisplayWidth;

    public static boolean hasNavigationBar(Context context) {
        if (context == null) {
            return true;
        }
        return (ViewConfiguration.get(context).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(3)) ? false : true;
    }

    @Deprecated
    public static boolean hasNavigationBar(Activity activity) {
        if (activity == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 17) {
            return (ViewConfiguration.get(activity).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(3)) ? false : true;
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        Point point2 = new Point();
        defaultDisplay.getSize(point);
        defaultDisplay.getRealSize(point2);
        return isFull(activity) ? point2.x != point.x : point2.y != point.y;
    }

    public static int getStatusBarHeight() {
        Resources resources = ArkValue.gContext.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", IMUrl.OS);
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return -1;
    }

    public static int getNavigationBarHeight() {
        Resources resources = ArkValue.gContext.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", IMUrl.OS);
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return -1;
    }

    public static int getNavigationBarLandscapeHeight() {
        Resources resources = ArkValue.gContext.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height_landscape", "dimen", IMUrl.OS);
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return -1;
    }

    public static int getNavigationBarLandscapeWidth() {
        Resources resources = ArkValue.gContext.getResources();
        int identifier = resources.getIdentifier("navigation_bar_width", "dimen", IMUrl.OS);
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return -1;
    }

    public static int getNavigationBarLocal(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int screenRealWidth = getScreenRealWidth(activity);
            int screenRealHeight = getScreenRealHeight(activity);
            if (-1 != screenRealHeight && -1 != screenRealWidth) {
                if (screenRealHeight == rect.bottom) {
                    return 1;
                }
                if (screenRealWidth == rect.right) {
                    return 2;
                }
            }
        }
        return 0;
    }

    @Deprecated
    public static void setNavigationBarVisible(View view, boolean z) {
        if (view == null || view.getRootView() == null || !hasNavigationBar(view.getContext())) {
            return;
        }
        if (apiLevelLowerThan16()) {
            view.getRootView().setSystemUiVisibility(!z ? 1 : 0);
            return;
        }
        int i = 512;
        if (!z) {
            i = 514;
            if (Build.VERSION.SDK_INT >= 19) {
                i = 2562;
            }
        }
        view.getRootView().setSystemUiVisibility(i);
    }

    public static int getScreenRealHeight(Activity activity) {
        return SystemUI.getScreenRealHeight(activity);
    }

    public static int getScreenRealWidth(Activity activity) {
        return SystemUI.getScreenRealWidth(activity);
    }

    public static boolean hideNavigationWithLowProfile() {
        return apiLevelLowerThan16();
    }

    public static boolean apiLevelLowerThan16() {
        return Build.VERSION.SDK_INT < 16;
    }

    public static int getBottomKeyboardHeight(Activity activity) {
        int i = getAccurateScreenDpi(activity)[1];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return i - displayMetrics.heightPixels;
    }

    public static int[] getAccurateScreenDpi(Activity activity) {
        int[] iArr = new int[2];
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            iArr[0] = displayMetrics.widthPixels;
            iArr[1] = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iArr;
    }

    public static int getCurrentMetricsDisplayWidth() {
        WindowManager windowManager = (WindowManager) ArkValue.gContext.getSystemService("window");
        if (windowManager == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager.getDefaultDisplay() == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getCurrentMetricsDisplayHeight() {
        WindowManager windowManager = (WindowManager) ArkValue.gContext.getSystemService("window");
        if (windowManager == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager.getDefaultDisplay() == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private static void getDisplay() {
        WindowManager windowManager;
        if ((sDisplayWidth <= 0 || sDisplayHeight <= 0) && (windowManager = (WindowManager) ArkValue.gContext.getSystemService("window")) != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (windowManager.getDefaultDisplay() == null) {
                return;
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            sDisplayWidth = displayMetrics.widthPixels;
            sDisplayHeight = displayMetrics.heightPixels;
        }
    }

    public static int getDisplayWidth() {
        getDisplay();
        int i = sDisplayWidth;
        int i2 = sDisplayHeight;
        return i < i2 ? i : i2;
    }

    public static int getDisplayHeight() {
        getDisplay();
        int i = sDisplayWidth;
        int i2 = sDisplayHeight;
        return i < i2 ? i2 : i;
    }

    public static boolean isFullScreenDevice() {
        float displayHeight = getDisplayHeight() / getDisplayWidth();
        L.info(TAG, "current scale is " + displayHeight);
        return displayHeight > sDefaultRatio;
    }

    public static boolean isFull(Context context) {
        Activity activity = AppUtils.getActivity(context);
        return SystemUI.getScreenRealWidth(activity) > SystemUI.getScreenRealHeight(activity);
    }

    public static int[] getExactNavigationSize(Activity activity) {
        int navigationBarLandscapeHeight;
        int[] iArr = {0, 0};
        if (!hasNavigationBar(activity) || hideNavigationWithLowProfile()) {
            return iArr;
        }
        int navigationBarLocal = getNavigationBarLocal(activity);
        if (1 == navigationBarLocal) {
            int navigationBarLandscapeWidth = getNavigationBarLandscapeWidth();
            if (-1 != navigationBarLandscapeWidth) {
                iArr[0] = navigationBarLandscapeWidth;
            }
        } else if (2 == navigationBarLocal && -1 != (navigationBarLandscapeHeight = getNavigationBarLandscapeHeight())) {
            iArr[1] = navigationBarLandscapeHeight;
        }
        return iArr;
    }

    public static String getSystemProperty(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (ClassNotFoundException e) {
            L.debug(TAG, (Throwable) e);
            return null;
        } catch (IllegalAccessException e2) {
            L.debug(TAG, (Throwable) e2);
            return null;
        } catch (IllegalArgumentException e3) {
            L.debug(TAG, (Throwable) e3);
            return null;
        } catch (NoSuchMethodException e4) {
            L.debug(TAG, (Throwable) e4);
            return null;
        } catch (InvocationTargetException e5) {
            L.debug(TAG, (Throwable) e5);
            return null;
        }
    }

    public static int getScreenMaxSize(Activity activity) {
        return Math.max(getScreenRealHeight(activity), getScreenRealWidth(activity));
    }

    public static int getScreenMinSize(Activity activity) {
        return Math.min(getScreenRealHeight(activity), getScreenRealWidth(activity));
    }

    public static void hideNavigationBarLayout(View view) {
        if (apiLevelLowerThan16() || view == null || view.getRootView() == null || !hasNavigationBar(view.getContext())) {
            return;
        }
        view.getRootView().setSystemUiVisibility(512);
    }

    public static boolean navigationBarExist() {
        Display defaultDisplay = ((WindowManager) ArkValue.gContext.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
        }
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics2);
        return i2 - displayMetrics2.widthPixels > 0 || i - displayMetrics2.heightPixels > 0;
    }

    public static boolean checkDeviceHasNavigationBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", IMUrl.OS);
        boolean z = false;
        boolean z2 = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
            if (!"1".equals(str)) {
                z = "0".equals(str) ? true : z2;
            }
            return z;
        } catch (Exception e) {
            L.debug(TAG, "checkDeviceHasNavigationBar:  Exception: " + e);
            return z2;
        }
    }

    public static void setStatusBarVisible(Window window, boolean z) {
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (z) {
                attributes.flags &= -1025;
            } else {
                attributes.flags |= 1024;
            }
            window.setAttributes(attributes);
        }
    }
}
