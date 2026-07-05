package com.sqwan.common.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class WindowManagerUtil {
    public static void handleNotch(Context context, boolean z) {
        if (context instanceof Activity) {
            Window window = ((Activity) context).getWindow();
            window.getDecorView().setSystemUiVisibility(1028);
            if (Build.VERSION.SDK_INT >= 28) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                if (z) {
                    attributes.layoutInDisplayCutoutMode = 1;
                }
                window.setAttributes(attributes);
            }
        }
    }

    public static void handleHideSystemUI(Context context) {
        if (context instanceof Activity) {
            final Window window = ((Activity) context).getWindow();
            StatusBarUtil.hideSystemUI(window);
            window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.sqwan.common.util.WindowManagerUtil.1
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    StatusBarUtil.hideSystemUI(window);
                }
            });
        }
    }
}
