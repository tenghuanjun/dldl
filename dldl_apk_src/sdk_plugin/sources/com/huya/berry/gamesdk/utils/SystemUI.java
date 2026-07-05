package com.huya.berry.gamesdk.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.sqwan.msdk.api.IMUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SystemUI {
    public static final int NavigationBarBottom = 2;
    public static final int NavigationBarRight = 1;
    public static final int NavigationBarUnknown = 0;

    public static boolean hasNavigationBar(Context context) {
        if (context == null) {
            return true;
        }
        return (ViewConfiguration.get(context).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(4)) ? false : true;
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
        if (activity == null) {
            return 0;
        }
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
        return 0;
    }

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

    public static void hideNavigationBarLayout(View view) {
        if (apiLevelLowerThan16() || view == null || view.getRootView() == null || !hasNavigationBar(view.getContext())) {
            return;
        }
        view.getRootView().setSystemUiVisibility(512);
    }

    public static void hideNavigationBar(View view) {
        if (apiLevelLowerThan16() || view == null || view.getRootView() == null) {
            return;
        }
        view.getRootView().setSystemUiVisibility(Build.VERSION.SDK_INT >= 19 ? 2050 : 2);
    }

    public static void showSystemUIVisible(View view) {
        if (view == null || view.getRootView() == null) {
            return;
        }
        view.getRootView().setSystemUiVisibility(0);
    }

    public static int getScreenRealHeight(Activity activity) {
        if (activity == null) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return getScreenRealSizeAPI17(activity).y;
        }
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            return getRawSize(activity, "getRawHeight", "getScreenRealHeight");
        }
        return getScreenSizeAPILower(activity, true);
    }

    public static int getScreenRealWidth(Activity activity) {
        if (activity == null) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return getScreenRealSizeAPI17(activity).x;
        }
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            return getRawSize(activity, "getRawWidth", "getScreenRealWidth");
        }
        return getScreenSizeAPILower(activity, false);
    }

    public static boolean hideNavigationWithLowProfile() {
        return apiLevelLowerThan16();
    }

    private static Point getScreenRealSizeAPI17(Activity activity) {
        Point point = new Point();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        }
        return point;
    }

    private static int getRawSize(Activity activity, String str, String str2) {
        try {
            return ((Integer) Display.class.getMethod(str, new Class[0]).invoke(activity.getWindowManager().getDefaultDisplay(), new Object[0])).intValue();
        } catch (Exception e) {
            L.error("SystemUI", "SystemUI " + str2 + " Exception " + e.toString());
            return -1;
        }
    }

    private static int getScreenSizeAPILower(Activity activity, boolean z) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return z ? displayMetrics.heightPixels : displayMetrics.widthPixels;
    }

    private static boolean apiLevelLowerThan16() {
        return Build.VERSION.SDK_INT < 16;
    }

    public static void copyText(String str) {
        Application application = ArkValue.gContext;
        if (Build.VERSION.SDK_INT >= 11) {
            ((ClipboardManager) application.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("simple text", str));
        } else {
            ((android.text.ClipboardManager) application.getSystemService("clipboard")).setText(str);
        }
    }

    public static void showKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
    }

    public static void hideSoftInput(Activity activity, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (!inputMethodManager.isActive() || view == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static float density() {
        return ArkValue.gContext.getResources().getDisplayMetrics().density;
    }

    public static int dpToPx(float f) {
        return Math.round(f * density());
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Bitmap drawingCache = view.getDrawingCache(true);
        return drawingCache.copy(drawingCache.getConfig(), drawingCache.isMutable());
    }
}
