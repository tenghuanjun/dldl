package com.sqwan.common.util;

import android.R;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.sqwan.msdk.api.IMUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AndroidBug5497Workaround {
    private final Activity activity;
    private ViewGroup.LayoutParams frameLayoutParams;
    private View mChildOfContent;
    private int statusBarHeight;
    private int usableHeightPrevious;

    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

    private AndroidBug5497Workaround(final Activity activity) {
        this.activity = activity;
        if (checkDeviceHasNavigationBar(activity)) {
            this.statusBarHeight = activity.getResources().getDimensionPixelSize(activity.getResources().getIdentifier("status_bar_height", "dimen", IMUrl.OS));
        }
        View childAt = ((FrameLayout) activity.findViewById(R.id.content)).getChildAt(0);
        this.mChildOfContent = childAt;
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.sqwan.common.util.AndroidBug5497Workaround.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                AndroidBug5497Workaround.this.possiblyResizeChildOfContent(AndroidBug5497Workaround.checkDeviceHasNavigationBar(activity));
            }
        });
        this.frameLayoutParams = this.mChildOfContent.getLayoutParams();
    }

    public static void assistActivity(Activity activity, View view) {
        new AndroidBug5497Workaround(activity, view);
    }

    private AndroidBug5497Workaround(final Activity activity, View view) {
        this.activity = activity;
        this.mChildOfContent = view;
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.sqwan.common.util.AndroidBug5497Workaround.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                AndroidBug5497Workaround.this.possiblyResizeChildOfContent(AndroidBug5497Workaround.checkDeviceHasNavigationBar(activity));
            }
        });
        this.frameLayoutParams = this.mChildOfContent.getLayoutParams();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void possiblyResizeChildOfContent(boolean z) {
        int height;
        int iComputeUsableHeight = computeUsableHeight(z);
        if (iComputeUsableHeight != this.usableHeightPrevious) {
            if (z) {
                height = this.mChildOfContent.getHeight();
            } else {
                height = this.mChildOfContent.getRootView().getHeight();
                if (Build.VERSION.SDK_INT < 19) {
                    Rect rect = new Rect();
                    this.activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                    height -= rect.top;
                }
            }
            int i = height - iComputeUsableHeight;
            if (i > height / 4) {
                if (Build.VERSION.SDK_INT >= 19 && z) {
                    this.frameLayoutParams.height = (height - i) + this.statusBarHeight;
                } else {
                    this.frameLayoutParams.height = height - i;
                }
            } else if (z) {
                this.frameLayoutParams.height = this.statusBarHeight + iComputeUsableHeight;
            } else {
                this.frameLayoutParams.height = height;
            }
            this.mChildOfContent.requestLayout();
            this.usableHeightPrevious = iComputeUsableHeight;
        }
    }

    private int computeUsableHeight(boolean z) {
        int i;
        int i2;
        if (z) {
            Rect rect = new Rect();
            this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
            if (rect.top < this.statusBarHeight) {
                i = rect.bottom;
                i2 = this.statusBarHeight;
            } else {
                return rect.bottom - rect.top;
            }
        } else {
            Rect rect2 = new Rect();
            this.activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect2);
            int iMax = Math.max(rect2.top, 0);
            Rect rect3 = new Rect();
            this.mChildOfContent.getWindowVisibleDisplayFrame(rect3);
            if (Build.VERSION.SDK_INT >= 19) {
                return (rect3.bottom - rect3.top) + iMax;
            }
            i = rect3.bottom;
            i2 = rect3.top;
        }
        return i - i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean checkDeviceHasNavigationBar(Activity activity) {
        Resources resources = activity.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", IMUrl.OS);
        boolean zHasNavBar = false;
        boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
            if (!"1".equals(str)) {
                zHasNavBar = "0".equals(str) ? true : hasNavBar(activity);
            }
            return zHasNavBar;
        } catch (Exception unused) {
            return z;
        }
    }

    private static boolean hasNavBar(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 19) {
            defaultDisplay.getRealMetrics(displayMetrics);
        }
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics2);
        return i2 - displayMetrics2.widthPixels > 0 || i - displayMetrics2.heightPixels > 0;
    }
}
