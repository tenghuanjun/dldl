package com.google.zxing.util;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class QrCodeGenerator {
    public static Bitmap getQrCodeImage(String str, int i, int i2) {
        if (str == null || str.length() == 0) {
            return null;
        }
        HashMap map = new HashMap(3);
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        map.put(EncodeHintType.MARGIN, 0);
        try {
            return bitMatrix2Bitmap(new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, map));
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bitmap bitMatrix2Bitmap(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] iArr = new int[width * height];
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                int i3 = -1;
                if (bitMatrix.get(i, i2)) {
                    i3 = -16777216;
                }
                iArr[(i2 * width) + i] = i3;
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }
}
