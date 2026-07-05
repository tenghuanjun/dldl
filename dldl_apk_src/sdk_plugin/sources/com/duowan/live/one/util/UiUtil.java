package com.duowan.live.one.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.WindowManager;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UiUtil {
    public static Point mPortraitPoin = new Point();
    public static Point mLandscapePoin = new Point();
    private static int mFullBarTop = -1;

    public static int bound(int i, int i2, int i3) {
        return i2 < i ? i : i2 > i3 ? i3 : i2;
    }

    static {
        initPoint();
    }

    private static void initPoint() {
        WindowManager windowManager = (WindowManager) ArkValue.gContext.getSystemService("window");
        Point point = new Point();
        if (windowManager != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            defaultDisplay.getRealSize(point);
            if (point.x > point.y) {
                mLandscapePoin = point;
                mPortraitPoin.x = point.y;
                mPortraitPoin.y = point.x;
            } else {
                mPortraitPoin = point;
                mLandscapePoin.x = point.y;
                mLandscapePoin.y = point.x;
            }
            Point point2 = new Point();
            defaultDisplay.getSize(point2);
            L.info("initPoint", "mPortraitPoin:" + mPortraitPoin + ";mLandscapePoin:" + mLandscapePoin + ",mFullBarTop:" + (point.y - point2.y));
        }
    }

    public static Point getPortraitPoin() {
        return mPortraitPoin;
    }

    public static int getFullBarTop() {
        int i = mFullBarTop;
        if (i != -1) {
            return i;
        }
        WindowManager windowManager = (WindowManager) ArkValue.gContext.getSystemService("window");
        Point point = new Point();
        Point point2 = new Point();
        if (windowManager != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            defaultDisplay.getRealSize(point);
            defaultDisplay.getSize(point2);
            mFullBarTop = point.y - point2.y;
            L.info("mFullBarTop:" + mFullBarTop);
        }
        return mFullBarTop;
    }

    public static Bitmap clipBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i3 = i * height;
        int i4 = width * i2;
        if (i3 == i4) {
            return bitmap;
        }
        if (i3 / i4 > 0) {
            height = i4 / i;
        } else {
            width = i3 / i2;
        }
        return Bitmap.createBitmap(bitmap, (bitmap.getWidth() - width) / 2, (bitmap.getHeight() - height) / 2, width, height);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Point screenSize() {
        return getScreenRealSize();
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

    public static int statusBarHeight() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return ArkValue.gContext.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 38;
        }
    }

    public static int dpToPx(float f) {
        return Math.round(f * density());
    }
}
