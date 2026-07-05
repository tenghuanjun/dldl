package com.huya.berry.gamesdk.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.duowan.auk.util.L;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ViewUtils {
    private ViewUtils() {
    }

    public static void postClickInterval(final View view) {
        view.setClickable(false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.huya.berry.gamesdk.utils.ViewUtils.1
            @Override // java.lang.Runnable
            public void run() {
                view.setClickable(true);
            }
        }, 1200L);
    }

    public static void postClickInterval(final View view, long j) {
        view.setClickable(false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.huya.berry.gamesdk.utils.ViewUtils.2
            @Override // java.lang.Runnable
            public void run() {
                view.setClickable(true);
            }
        }, j);
    }

    public static <T extends View> T findView(Activity activity, Integer num) {
        if (activity == null) {
            return null;
        }
        return (T) activity.findViewById(num.intValue());
    }

    public static <T extends View> T findView(View view, Integer num) {
        if (view == null) {
            return null;
        }
        return (T) view.findViewById(num.intValue());
    }

    public static <T extends View> T findViewAndClick(Activity activity, Integer num, View.OnClickListener onClickListener) {
        T t = (T) findView(activity, num);
        if (t != null) {
            t.setOnClickListener(onClickListener);
        }
        return t;
    }

    public static <T extends View> T findViewAndClick(View view, Integer num, View.OnClickListener onClickListener) {
        T t = (T) findView(view, num);
        t.setOnClickListener(onClickListener);
        return t;
    }

    public static <T extends TextView> T findTextViewAndSetText(View view, Integer num, String str) {
        View viewFindView = findView(view, num);
        if (!(viewFindView instanceof TextView)) {
            return null;
        }
        T t = (T) viewFindView;
        t.setText(str);
        return t;
    }

    public static <T extends Fragment> T findFragment(FragmentManager fragmentManager, Integer num) {
        if (fragmentManager == null) {
            return null;
        }
        return (T) fragmentManager.findFragmentById(num.intValue());
    }

    public static <T extends Fragment> T findFragment(FragmentManager fragmentManager, String str) {
        if (fragmentManager == null) {
            return null;
        }
        return (T) fragmentManager.findFragmentByTag(str);
    }

    public static void toggleSoftInput(Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(0, 2);
    }

    public static void hideSoftInput(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
    }

    public static void showSoftInput(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager.isActive(view)) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    public static void hideSoftInput(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        View currentFocus = activity.getCurrentFocus();
        if (!inputMethodManager.isActive() || currentFocus == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getDisplayWidth(Context context) {
        if (context == null) {
            return 0;
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Class[] clsArr = {Point.class};
        Point point = new Point();
        try {
            Display.class.getMethod("getSize", clsArr).invoke(defaultDisplay, point);
            return point.x;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            L.error("ViewUtils", "Exception:" + e);
            return defaultDisplay.getWidth();
        }
    }

    public static int getDisplayHeight(Context context) {
        if (context == null) {
            return 0;
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Class[] clsArr = {Point.class};
        Point point = new Point();
        try {
            Display.class.getMethod("getSize", clsArr).invoke(defaultDisplay, point);
            return point.y;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            L.error("ViewUtils", "Exception:" + e);
            return defaultDisplay.getHeight();
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        if (activity == null) {
            return 0;
        }
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i = rect.top;
        if (i != 0) {
            return i;
        }
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return activity.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            L.error("ViewUtils", "Exception:" + e);
            return i;
        }
    }
}
