package com.sq.webview.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StatusBarUtil {
    public static void hideSystemUI(Context context) {
        if (context instanceof Activity) {
            hideSystemUI(((Activity) context).getWindow());
        }
    }

    public static void hideSystemUI(Window window) {
        if (window == null) {
            return;
        }
        hideSystemUI(window.getDecorView());
    }

    public static void hideSystemUI(View view) {
        view.setSystemUiVisibility(7942);
    }

    public static void hideSystemKeyBoard(Context context, View v) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(((Integer) cls.getField("status_bar_height").get(cls.newInstance())).intValue());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
