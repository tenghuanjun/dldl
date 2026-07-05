package com.sqwan.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ViewUtils {
    public static View inflate(Activity activity, int i) {
        return activity.getLayoutInflater().inflate(i, (ViewGroup) null);
    }

    public static View inflate(Context context, int i) {
        return ((Activity) context).getLayoutInflater().inflate(i, (ViewGroup) null);
    }

    public static void showToast(Context context, String str) {
        try {
            ToastUtil.showToast(context, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void hideSystemView(Window window) {
        if (window == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 19) {
            View decorView = window.getDecorView();
            if (decorView != null) {
                decorView.setSystemUiVisibility(8);
                return;
            }
            return;
        }
        View decorView2 = window.getDecorView();
        if (decorView2 != null) {
            decorView2.setSystemUiVisibility(1280);
        }
        window.clearFlags(67108864);
    }

    public static boolean show(View view) {
        if (view == null) {
            return false;
        }
        if (view.getVisibility() == 0) {
            return true;
        }
        view.setVisibility(0);
        return true;
    }

    public static boolean hidden(View view) {
        if (view == null) {
            return false;
        }
        if (view.getVisibility() == 4) {
            return true;
        }
        view.setVisibility(4);
        return true;
    }

    public static boolean gone(View view) {
        if (view == null) {
            return false;
        }
        if (view.getVisibility() == 8) {
            return true;
        }
        view.setVisibility(8);
        return true;
    }

    public static boolean isHidden(View view) {
        return view == null || view.getVisibility() == 4;
    }

    public static boolean isGone(View view) {
        return view == null || view.getVisibility() == 8;
    }

    public static boolean isShow(View view) {
        return view == null || view.getVisibility() == 0;
    }

    public static Activity getActivity(View view) {
        if (view == null) {
            return null;
        }
        Context context = view.getContext();
        while (!(context instanceof Activity)) {
            if (!(context instanceof ContextWrapper) || (context = ((ContextWrapper) context).getBaseContext()) == null) {
                return null;
            }
        }
        return (Activity) context;
    }
}
