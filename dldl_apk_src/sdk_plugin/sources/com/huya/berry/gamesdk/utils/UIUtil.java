package com.huya.berry.gamesdk.utils;

import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UIUtil {
    public static boolean enableFullSreen;
    public static Point mPortraitPoin = new Point();
    public static Point mLandscapePoin = new Point();

    public static void initPoint() {
        WindowManager windowManager = (WindowManager) ArkValue.gContext.getSystemService("window");
        Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getRealSize(point);
            if (point.x > point.y) {
                mLandscapePoin = point;
                mPortraitPoin.x = point.y;
                mPortraitPoin.y = point.x;
            } else {
                mPortraitPoin = point;
                mLandscapePoin.x = point.y;
                mLandscapePoin.y = point.x;
            }
            L.info("initPoint", "mPortraitPoin:" + mPortraitPoin + ";mLandscapePoin:" + mLandscapePoin);
        }
    }

    public static float getDp(float f) {
        return Resources.getSystem().getDisplayMetrics().density * f;
    }

    public static int px2sp(float f) {
        return (int) ((f / ArkValue.gContext.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(float f) {
        return (int) ((f * ArkValue.gContext.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static Point screenSize() {
        return getScreenRealSize();
    }

    public static Point normalScreenSize() {
        DisplayMetrics displayMetrics = ArkValue.gContext.getResources().getDisplayMetrics();
        return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static int getFullScreenLength(int i, int i2) {
        float f;
        int i3;
        enableFullSreen = false;
        Point pointScreenSize = screenSize();
        if (pointScreenSize.x > pointScreenSize.y) {
            f = pointScreenSize.x * 1.0f;
            i3 = pointScreenSize.y;
        } else {
            f = pointScreenSize.y * 1.0f;
            i3 = pointScreenSize.x;
        }
        float f2 = f / i3;
        float f3 = i2;
        float f4 = (i * 1.0f) / f3;
        L.info("ResolutionOptions", "screenRadio:" + f2 + ",resolutionRadio:" + f4);
        if (f2 - f4 < 0.2f) {
            return i;
        }
        enableFullSreen = true;
        int i4 = (int) (f3 * f2);
        int i5 = i4 % 16;
        return i5 < 8 ? i4 - i5 : (i4 - i5) + 16;
    }

    public static Point getPoint() {
        WindowManager windowManager = (WindowManager) ArkValue.gContext.getSystemService("window");
        Point point = new Point();
        Point point2 = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getRealSize(point);
            if (point.x <= point.y) {
                return point;
            }
            point2.x = point.y;
            point2.y = point.x;
        }
        return point2;
    }

    public static Point getScreenRealSize() {
        if (mPortraitPoin.x == 0) {
            initPoint();
        }
        if (ArkValue.gContext.getResources().getConfiguration().orientation == 2) {
            return mLandscapePoin;
        }
        return mPortraitPoin;
    }

    public static float density() {
        return ArkValue.gContext.getResources().getDisplayMetrics().density;
    }
}
