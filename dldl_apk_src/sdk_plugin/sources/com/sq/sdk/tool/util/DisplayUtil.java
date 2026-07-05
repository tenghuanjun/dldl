package com.sq.sdk.tool.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.taptap.sdk.db.constant.Common;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DisplayUtil {
    public static int dip2px(float f, float f2) {
        return (int) ((f * f2) + 0.5f);
    }

    public static int px2dip(float f, float f2) {
        return (int) ((f / f2) + 0.5f);
    }

    public static int px2sp(float f, float f2) {
        return (int) ((f / f2) + 0.5f);
    }

    public static int sp2px(float f, float f2) {
        return (int) ((f * f2) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static HashMap<String, Object> getViewLength(Context context, int i, int i2, int i3, int i4, String str) {
        int i5;
        HashMap<String, Object> map = new HashMap<>();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i6 = displayMetrics.widthPixels;
        int i7 = displayMetrics.heightPixels;
        if (i7 < i6) {
            i5 = (i6 / i) * (i - i2);
            i7 = (i7 / i3) * (i3 - i4);
        } else {
            int i8 = i6 / 10;
            int i9 = i7 / 10;
            if ("SQNoticeDialog".equals(str)) {
                i5 = i8 * 9;
                i7 = i9 * 4;
            } else if ("PayWebDialog".equals(str)) {
                i5 = i8 * 8;
                i7 = i9 * 7;
            } else {
                i5 = i8 * 8;
            }
        }
        map.put(Common.Predefined.SUB_WIDTH, Integer.valueOf(i5));
        map.put("height", Integer.valueOf(i7));
        return map;
    }

    public static void setViewMeasurement(View view, int i, int i2, double d) {
        if (i < i2) {
            i = i2;
        }
        int i3 = (int) (((double) i) * d);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = i3;
        layoutParams.height = i3;
        layoutParams.setMargins(0, (i3 / 2) + i3, i3, 0);
    }

    public static int getScreenWidth(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }
}
