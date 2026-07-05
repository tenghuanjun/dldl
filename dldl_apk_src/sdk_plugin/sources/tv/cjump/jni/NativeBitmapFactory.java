package tv.cjump.jni;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NativeBitmapFactory {
    static Field nativeIntField;
    static boolean nativeLibLoaded;
    static boolean notLoadAgain;

    private static native Bitmap createBitmap(int i, int i2, int i3, boolean z);

    private static native Bitmap createBitmap19(int i, int i2, int i3, boolean z);

    private static native boolean init();

    private static native boolean release();

    public static boolean isInNativeAlloc() {
        return Build.VERSION.SDK_INT < 11 || (nativeLibLoaded && nativeIntField != null);
    }

    public static void loadLibs() {
        if (notLoadAgain) {
            return;
        }
        if (!DeviceUtils.isRealARMArch() && !DeviceUtils.isRealX86Arch()) {
            notLoadAgain = true;
            nativeLibLoaded = false;
            return;
        }
        if (nativeLibLoaded) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 23) {
                System.loadLibrary("ndkbitmap");
                nativeLibLoaded = true;
            } else {
                notLoadAgain = true;
                nativeLibLoaded = false;
            }
        } catch (Error e) {
            e.printStackTrace();
            notLoadAgain = true;
            nativeLibLoaded = false;
        } catch (Exception e2) {
            e2.printStackTrace();
            notLoadAgain = true;
            nativeLibLoaded = false;
        }
        if (nativeLibLoaded) {
            if (!init()) {
                release();
                notLoadAgain = true;
                nativeLibLoaded = false;
            } else {
                initField();
                if (!testLib()) {
                    release();
                    notLoadAgain = true;
                    nativeLibLoaded = false;
                }
            }
        }
        Log.e("NativeBitmapFactory", "loaded" + nativeLibLoaded);
    }

    public static synchronized void releaseLibs() {
        boolean z = nativeLibLoaded;
        nativeIntField = null;
        nativeLibLoaded = false;
        if (z) {
            release();
        }
    }

    static void initField() {
        try {
            Field declaredField = Bitmap.Config.class.getDeclaredField("nativeInt");
            nativeIntField = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            nativeIntField = null;
            e.printStackTrace();
        }
    }

    private static boolean testLib() {
        if (nativeIntField == null) {
            return false;
        }
        Bitmap bitmapCreateNativeBitmap = null;
        try {
            try {
                bitmapCreateNativeBitmap = createNativeBitmap(2, 2, Bitmap.Config.ARGB_8888, true);
                boolean zIsPremultiplied = bitmapCreateNativeBitmap != null && bitmapCreateNativeBitmap.getWidth() == 2 && bitmapCreateNativeBitmap.getHeight() == 2;
                if (zIsPremultiplied) {
                    if (Build.VERSION.SDK_INT >= 17 && !bitmapCreateNativeBitmap.isPremultiplied()) {
                        bitmapCreateNativeBitmap.setPremultiplied(true);
                    }
                    Canvas canvas = new Canvas(bitmapCreateNativeBitmap);
                    Paint paint = new Paint();
                    paint.setColor(SupportMenu.CATEGORY_MASK);
                    paint.setTextSize(20.0f);
                    canvas.drawRect(0.0f, 0.0f, bitmapCreateNativeBitmap.getWidth(), bitmapCreateNativeBitmap.getHeight(), paint);
                    canvas.drawText("TestLib", 0.0f, 0.0f, paint);
                    if (Build.VERSION.SDK_INT >= 17) {
                        zIsPremultiplied = bitmapCreateNativeBitmap.isPremultiplied();
                    }
                }
                if (bitmapCreateNativeBitmap != null) {
                    bitmapCreateNativeBitmap.recycle();
                }
                return zIsPremultiplied;
            } catch (Error unused) {
                if (bitmapCreateNativeBitmap != null) {
                    bitmapCreateNativeBitmap.recycle();
                }
                return false;
            } catch (Exception e) {
                Log.e("NativeBitmapFactory", "exception:" + e.toString());
                if (bitmapCreateNativeBitmap != null) {
                    bitmapCreateNativeBitmap.recycle();
                }
                return false;
            }
        } catch (Throwable th) {
            if (bitmapCreateNativeBitmap != null) {
                bitmapCreateNativeBitmap.recycle();
            }
            throw th;
        }
    }

    public static int getNativeConfig(Bitmap.Config config) {
        try {
            if (nativeIntField == null) {
                return 0;
            }
            return nativeIntField.getInt(config);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Bitmap createBitmap(int i, int i2, Bitmap.Config config) {
        return createBitmap(i, i2, config, config.equals(Bitmap.Config.ARGB_4444) || config.equals(Bitmap.Config.ARGB_8888));
    }

    public static void recycle(Bitmap bitmap) {
        bitmap.recycle();
    }

    public static synchronized Bitmap createBitmap(int i, int i2, Bitmap.Config config, boolean z) {
        if (nativeLibLoaded && nativeIntField != null) {
            return createNativeBitmap(i, i2, config, z);
        }
        return Bitmap.createBitmap(i, i2, config);
    }

    private static Bitmap createNativeBitmap(int i, int i2, Bitmap.Config config, boolean z) {
        int nativeConfig = getNativeConfig(config);
        return Build.VERSION.SDK_INT == 19 ? createBitmap19(i, i2, nativeConfig, z) : createBitmap(i, i2, nativeConfig, z);
    }
}
