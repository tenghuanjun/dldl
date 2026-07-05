package com.huya.mtp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.huya.mtp.api.MTPApi;
import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BitmapUtils {
    private static final String TAG = "BitmapUtils";
    private static float hRadius = 3.0f;
    private static int iterations = 6;
    private static float vRadius = 3.0f;

    private static int clamp(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public static Bitmap highlight(Bitmap bitmap, float f) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{1.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 1.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 1.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap bytesToBitmap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static byte[] bitmapToBytes(Bitmap bitmap, boolean z) {
        return bitmap2Bytes(bitmap, Bitmap.CompressFormat.PNG, z);
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat compressFormat, boolean z) {
        if (bitmap == null) {
            MTPApi.LOGGER.error(TAG, "input bitmap is null");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        if (z) {
            bitmap.recycle();
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static Bitmap getCircle(Bitmap bitmap) {
        Rect rect;
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            rect = new Rect((width - height) / 2, 0, (width + height) / 2, height);
            width = height;
        } else {
            rect = new Rect(0, (height - width) / 2, width, (height + width) / 2);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        float f = width;
        RectF rectF = new RectF(0.0f, 0.0f, f, f);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-16777216);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap scale(Bitmap bitmap, int i, int i2) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap scaleAndGetCenter(Bitmap bitmap, int i, int i2) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = i;
        float f2 = width;
        float f3 = i2;
        float f4 = height;
        float fMax = Math.max(f / f2, f3 / f4);
        matrix.postScale(fMax, fMax);
        int i3 = (int) ((f2 - (f / fMax)) / 2.0f);
        int i4 = (int) ((f4 - (f3 / fMax)) / 2.0f);
        return Bitmap.createBitmap(bitmap, i3, i4, width - (i3 * 2), height - (i4 * 2), matrix, true);
    }

    public static Bitmap addReflection(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        int i = height / 8;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, (height * 7) / 8, width, i, matrix, false);
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width, i + height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        float f = height;
        float f2 = width;
        float f3 = height + 2;
        canvas.drawRect(0.0f, f, f2, f3, new Paint());
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, f3, (Paint) null);
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0f, bitmap.getHeight(), 0.0f, bitmapCreateBitmap2.getHeight() + 2, 1895825407, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, f, f2, bitmapCreateBitmap2.getHeight() + 2, paint);
        return bitmapCreateBitmap2;
    }

    public static Bitmap BoxBlurFilter(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < iterations; i2++) {
            blur(iArr, iArr2, width, height, hRadius);
            blur(iArr2, iArr, height, width, vRadius);
        }
        blurFractional(iArr, iArr2, width, height, hRadius);
        blurFractional(iArr2, iArr, height, width, vRadius);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }

    public static Bitmap BoxBlurFilterWithSourceBmp(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < iterations; i2++) {
            blur(iArr, iArr2, width, height, hRadius);
            blur(iArr2, iArr, height, width, vRadius);
        }
        blurFractional(iArr, iArr2, width, height, hRadius);
        blurFractional(iArr2, iArr, height, width, vRadius);
        bitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static void blur(int[] iArr, int[] iArr2, int i, int i2, float f) {
        int i3 = i - 1;
        int i4 = (int) f;
        int i5 = (i4 * 2) + 1;
        int i6 = i5 * 256;
        int[] iArr3 = new int[i6];
        int i7 = 0;
        for (int i8 = 0; i8 < i6; i8++) {
            iArr3[i8] = i8 / i5;
        }
        int i9 = 0;
        int i10 = 0;
        while (i9 < i2) {
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            for (int i15 = -i4; i15 <= i4; i15++) {
                int i16 = iArr[clamp(i15, i7, i3) + i10];
                i11 += (i16 >> 24) & 255;
                i12 += (i16 >> 16) & 255;
                i13 += (i16 >> 8) & 255;
                i14 += i16 & 255;
            }
            int i17 = i9;
            int i18 = 0;
            while (i18 < i) {
                iArr2[i17] = (iArr3[i11] << 24) | (iArr3[i12] << 16) | (iArr3[i13] << 8) | iArr3[i14];
                int i19 = i18 + i4 + 1;
                if (i19 > i3) {
                    i19 = i3;
                }
                int i20 = i18 - i4;
                if (i20 < 0) {
                    i20 = 0;
                }
                int i21 = iArr[i19 + i10];
                int i22 = iArr[i20 + i10];
                i11 += ((i21 >> 24) & 255) - ((i22 >> 24) & 255);
                i12 += ((i21 & 16711680) - (16711680 & i22)) >> 16;
                i13 += ((i21 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) - (65280 & i22)) >> 8;
                i14 += (i21 & 255) - (i22 & 255);
                i17 += i2;
                i18++;
                i3 = i3;
            }
            i10 += i;
            i9++;
            i7 = 0;
        }
    }

    private static void blurFractional(int[] iArr, int[] iArr2, int i, int i2, float f) {
        int i3;
        float f2 = f - ((int) f);
        float f3 = 1.0f / ((2.0f * f2) + 1.0f);
        char c = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            iArr2[i4] = iArr[c];
            int i6 = i4 + i2;
            int i7 = 1;
            int i8 = 1;
            while (true) {
                i3 = i - 1;
                if (i8 < i3) {
                    int i9 = i5 + i8;
                    int i10 = iArr[i9 - 1];
                    int i11 = iArr[i9];
                    int i12 = iArr[i9 + i7];
                    int i13 = (i11 >> 24) & 255;
                    int i14 = (i11 >> 8) & 255;
                    iArr2[i6] = (((int) ((((i11 >> 16) & 255) + ((int) ((((i10 >> 16) & 255) + ((i12 >> 16) & 255)) * f2))) * f3)) << 16) | (((int) ((i13 + ((int) ((((i10 >> 24) & 255) + ((i12 >> 24) & 255)) * f2))) * f3)) << 24) | (((int) ((i14 + ((int) ((((i10 >> 8) & 255) + ((i12 >> 8) & 255)) * f2))) * f3)) << 8) | ((int) (((i11 & 255) + ((int) (((i10 & 255) + (i12 & 255)) * f2))) * f3));
                    i6 += i2;
                    i8++;
                    i4 = i4;
                    i5 = i5;
                    i7 = 1;
                }
            }
            iArr2[i6] = iArr[i3];
            i5 += i;
            i4++;
            c = 0;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmapCreateBitmap;
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private static boolean isEmptyBitmap(Bitmap bitmap) {
        return bitmap == null || bitmap.getHeight() == 0 || bitmap.getWidth() == 0;
    }

    public static Bitmap RGB565toARGB888(Bitmap bitmap) {
        int[] iArr = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr, 0, bitmapCreateBitmap.getWidth(), 0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight());
        return bitmapCreateBitmap;
    }
}
