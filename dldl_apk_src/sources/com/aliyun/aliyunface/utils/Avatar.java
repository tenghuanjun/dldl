package com.aliyun.aliyunface.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.alipay.zoloz.toyger.ToygerLog;
import com.alipay.zoloz.toyger.face.FaceBlobManager;
import com.alipay.zoloz.toyger.face.ToygerFaceAttr;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class Avatar {
    public static Bitmap genAvatar(Bitmap bitmap, ToygerFaceAttr toygerFaceAttr) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect rectConvertFaceRegion = FaceBlobManager.convertFaceRegion(toygerFaceAttr.faceRegion, width, height, 0, false);
        ToygerLog.d("avatar", "bitmapWidth=" + width + ", bitmapHeight=" + height + ", attr.faceRegion=" + toygerFaceAttr.faceRegion + ", rect=" + rectConvertFaceRegion);
        int i = rectConvertFaceRegion.left;
        int i2 = rectConvertFaceRegion.top;
        int i3 = rectConvertFaceRegion.right;
        int i4 = rectConvertFaceRegion.bottom;
        int i5 = (i3 - i) / 2;
        int iMax = Math.max(0, i - i5);
        int i6 = (i4 - i2) / 2;
        int iMax2 = Math.max(0, i2 - i6);
        int iMin = Math.min(width, i3 + i5);
        int iMin2 = Math.min(height, i4 + i6);
        int i7 = iMin - iMax;
        int i8 = iMin2 - iMax2;
        ToygerLog.d("avatar", "left=" + iMax + ", top=" + iMax2 + ", right=" + iMin + ", bottom=" + iMin2 + ", width=" + i7 + ", height=" + i8);
        if (i7 > i8) {
            i7 = i8;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, iMax, iMax2, i7, i7);
        Bitmap bitmapBlur = blur(bitmapCreateBitmap, 1, 6.0f);
        bitmapCreateBitmap.recycle();
        return bitmapBlur;
    }

    public static Bitmap blur(Bitmap bitmap, int i, float f) {
        ToygerLog.d("blur()...radius:" + i + " scale:" + f);
        if (f <= 0.0f) {
            f = 1.0f;
        }
        Bitmap bitmapCreateBitmap = null;
        try {
            bitmapCreateBitmap = Bitmap.createBitmap((int) (bitmap.getWidth() / f), (int) (bitmap.getHeight() / f), Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            float f2 = 1.0f / f;
            canvas.scale(f2, f2);
            Paint paint = new Paint();
            paint.setFlags(2);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            return doBlur(bitmapCreateBitmap, i, true);
        } catch (OutOfMemoryError e) {
            ToygerLog.e(e);
            return bitmapCreateBitmap;
        }
    }

    public static Bitmap doBlur(Bitmap bitmap, int i, boolean z) {
        int[] iArr;
        int i2 = i;
        ToygerLog.d("radius:" + i2);
        Bitmap bitmapCopy = z ? bitmap : bitmap.copy(bitmap.getConfig(), true);
        if (i2 < 1) {
            return null;
        }
        int width = bitmapCopy.getWidth();
        int height = bitmapCopy.getHeight();
        int i3 = width * height;
        int[] iArr2 = new int[i3];
        bitmapCopy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i4 = width - 1;
        int i5 = height - 1;
        int i6 = i2 + i2 + 1;
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i3];
        int[] iArr5 = new int[i3];
        int[] iArr6 = new int[Math.max(width, height)];
        int i7 = (i6 + 1) >> 1;
        ToygerLog.d("radius:" + i2 + " div:" + i6 + " divsum:" + i7);
        int i8 = i7 * i7;
        int i9 = i8 * 256;
        int[] iArr7 = new int[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            iArr7[i10] = i10 / i8;
        }
        int[][] iArr8 = (int[][]) Array.newInstance((Class<?>) int.class, i6, 3);
        int i11 = i2 + 1;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i12 < height) {
            Bitmap bitmap2 = bitmapCopy;
            int i15 = height;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = -i2;
            int i25 = 0;
            while (i24 <= i2) {
                int i26 = i5;
                int[] iArr9 = iArr6;
                int i27 = iArr2[i13 + Math.min(i4, Math.max(i24, 0))];
                int[] iArr10 = iArr8[i24 + i2];
                iArr10[0] = (i27 & 16711680) >> 16;
                iArr10[1] = (i27 & 65280) >> 8;
                iArr10[2] = i27 & 255;
                int iAbs = i11 - Math.abs(i24);
                i25 += iArr10[0] * iAbs;
                i16 += iArr10[1] * iAbs;
                i17 += iArr10[2] * iAbs;
                if (i24 > 0) {
                    i21 += iArr10[0];
                    i22 += iArr10[1];
                    i23 += iArr10[2];
                } else {
                    i18 += iArr10[0];
                    i19 += iArr10[1];
                    i20 += iArr10[2];
                }
                i24++;
                i5 = i26;
                iArr6 = iArr9;
            }
            int i28 = i5;
            int[] iArr11 = iArr6;
            int i29 = i2;
            int i30 = i25;
            int i31 = 0;
            while (i31 < width) {
                iArr3[i13] = iArr7[i30];
                iArr4[i13] = iArr7[i16];
                iArr5[i13] = iArr7[i17];
                int i32 = i30 - i18;
                int i33 = i16 - i19;
                int i34 = i17 - i20;
                int[] iArr12 = iArr8[((i29 - i2) + i6) % i6];
                int i35 = i18 - iArr12[0];
                int i36 = i19 - iArr12[1];
                int i37 = i20 - iArr12[2];
                if (i12 == 0) {
                    iArr = iArr7;
                    iArr11[i31] = Math.min(i31 + i2 + 1, i4);
                } else {
                    iArr = iArr7;
                }
                int i38 = iArr2[i14 + iArr11[i31]];
                iArr12[0] = (i38 & 16711680) >> 16;
                iArr12[1] = (i38 & 65280) >> 8;
                iArr12[2] = i38 & 255;
                int i39 = i21 + iArr12[0];
                int i40 = i22 + iArr12[1];
                int i41 = i23 + iArr12[2];
                i30 = i32 + i39;
                i16 = i33 + i40;
                i17 = i34 + i41;
                i29 = (i29 + 1) % i6;
                int[] iArr13 = iArr8[i29 % i6];
                i18 = i35 + iArr13[0];
                i19 = i36 + iArr13[1];
                i20 = i37 + iArr13[2];
                i21 = i39 - iArr13[0];
                i22 = i40 - iArr13[1];
                i23 = i41 - iArr13[2];
                i13++;
                i31++;
                iArr7 = iArr;
            }
            i14 += width;
            i12++;
            bitmapCopy = bitmap2;
            height = i15;
            i5 = i28;
            iArr6 = iArr11;
        }
        Bitmap bitmap3 = bitmapCopy;
        int i42 = i5;
        int[] iArr14 = iArr6;
        int i43 = height;
        int[] iArr15 = iArr7;
        int i44 = 0;
        while (i44 < width) {
            int i45 = -i2;
            int i46 = i6;
            int[] iArr16 = iArr2;
            int i47 = 0;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = i45;
            int i55 = i45 * width;
            int i56 = 0;
            int i57 = 0;
            while (i54 <= i2) {
                int i58 = width;
                int iMax = Math.max(0, i55) + i44;
                int[] iArr17 = iArr8[i54 + i2];
                iArr17[0] = iArr3[iMax];
                iArr17[1] = iArr4[iMax];
                iArr17[2] = iArr5[iMax];
                int iAbs2 = i11 - Math.abs(i54);
                i56 += iArr3[iMax] * iAbs2;
                i57 += iArr4[iMax] * iAbs2;
                i47 += iArr5[iMax] * iAbs2;
                if (i54 > 0) {
                    i51 += iArr17[0];
                    i52 += iArr17[1];
                    i53 += iArr17[2];
                } else {
                    i48 += iArr17[0];
                    i49 += iArr17[1];
                    i50 += iArr17[2];
                }
                int i59 = i42;
                if (i54 < i59) {
                    i55 += i58;
                }
                i54++;
                i42 = i59;
                width = i58;
            }
            int i60 = width;
            int i61 = i42;
            int i62 = i2;
            int i63 = i44;
            int i64 = i47;
            int i65 = i43;
            int i66 = i57;
            int i67 = i56;
            int i68 = 0;
            while (i68 < i65) {
                iArr16[i63] = (iArr16[i63] & (-16777216)) | (iArr15[i67] << 16) | (iArr15[i66] << 8) | iArr15[i64];
                int i69 = i67 - i48;
                int i70 = i66 - i49;
                int i71 = i64 - i50;
                int[] iArr18 = iArr8[((i62 - i2) + i46) % i46];
                int i72 = i48 - iArr18[0];
                int i73 = i49 - iArr18[1];
                int i74 = i50 - iArr18[2];
                if (i44 == 0) {
                    iArr14[i68] = Math.min(i68 + i11, i61) * i60;
                }
                int i75 = iArr14[i68] + i44;
                iArr18[0] = iArr3[i75];
                iArr18[1] = iArr4[i75];
                iArr18[2] = iArr5[i75];
                int i76 = i51 + iArr18[0];
                int i77 = i52 + iArr18[1];
                int i78 = i53 + iArr18[2];
                i67 = i69 + i76;
                i66 = i70 + i77;
                i64 = i71 + i78;
                i62 = (i62 + 1) % i46;
                int[] iArr19 = iArr8[i62];
                i48 = i72 + iArr19[0];
                i49 = i73 + iArr19[1];
                i50 = i74 + iArr19[2];
                i51 = i76 - iArr19[0];
                i52 = i77 - iArr19[1];
                i53 = i78 - iArr19[2];
                i63 += i60;
                i68++;
                i2 = i;
            }
            i44++;
            i2 = i;
            i42 = i61;
            i43 = i65;
            i6 = i46;
            iArr2 = iArr16;
            width = i60;
        }
        int i79 = width;
        bitmap3.setPixels(iArr2, 0, i79, 0, 0, i79, i43);
        return bitmap3;
    }
}
