package com.sq.webview.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ViewUtil {
    public static View.OnClickListener getOnClickListener(View view) {
        if (!view.hasOnClickListeners()) {
            return null;
        }
        try {
            Method declaredMethod = Class.forName("android.view.View").getDeclaredMethod("getListenerInfo", new Class[0]);
            if (!declaredMethod.isAccessible()) {
                declaredMethod.setAccessible(true);
            }
            Field declaredField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener");
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            return (View.OnClickListener) declaredField.get(declaredMethod.invoke(view, new Object[0]));
        } catch (Exception e) {
            WebLogUtil.e("getOnClickListener error");
            WebLogUtil.e(e.getMessage());
            return null;
        }
    }

    public static int getScreenHeight(Context context) {
        if (context != null) {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        }
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        if (context != null) {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        }
        return Resources.getSystem().getDisplayMetrics().widthPixels;
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
