package com.demo.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ScreenCaptureUtils {
    private static final String DEVICE_NAME = "/dev/graphics/fb0";
    private static String TAG = ScreenCaptureUtils.class.getSimpleName();

    public static Bitmap captureScreen(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        drawingCache.setHasAlpha(false);
        drawingCache.prepareToDraw();
        return drawingCache;
    }

    public static Bitmap acquireScreenshot(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        int pixelFormat = defaultDisplay.getPixelFormat();
        PixelFormat pixelFormat2 = new PixelFormat();
        PixelFormat.getPixelFormatInfo(pixelFormat, pixelFormat2);
        int i3 = pixelFormat2.bytesPerPixel;
        int i4 = i * i2;
        int i5 = i4 * i3;
        byte[] bArr = new byte[i5];
        try {
            InputStream asRoot = readAsRoot();
            new DataInputStream(asRoot).readFully(bArr);
            asRoot.close();
            int[] iArr = new int[i4];
            for (int i6 = 0; i6 < i5; i6 += i3) {
                iArr[i6 / i3] = (bArr[i6] & 255) | ((bArr[i6 + 1] & 255) << 8) | ((bArr[i6 + 2] & 255) << 16) | (-16777216);
            }
            return Bitmap.createBitmap(iArr, i2, i, Bitmap.Config.ARGB_8888);
        } catch (Exception e) {
            Log.d(TAG, "#### 读取屏幕截图失败");
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream readAsRoot() throws Exception {
        File file = new File(DEVICE_NAME);
        Process processExec = Runtime.getRuntime().exec(ShellAdbUtils.COMMAND_SU);
        processExec.getOutputStream().write(("cat " + file.getAbsolutePath() + ShellAdbUtils.COMMAND_LINE_END).getBytes());
        return processExec.getInputStream();
    }

    public static Bitmap captureScreen(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        Log.d(TAG, "width = " + width + ", height = " + height);
        view.layout(0, 0, width, height);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(-1);
        view.draw(canvas);
        return bitmapCreateBitmap;
    }
}
