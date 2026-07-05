package com.aliyun.aliyunface.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import com.aliyun.aliyunface.camera.AndroidImpl;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ZipUtils {
    public static Bitmap byteBuffer2Bitmap(ByteBuffer byteBuffer, int i, int i2, int i3) {
        if (byteBuffer == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                new YuvImage(byteBuffer.array(), 17, i, i2, null).compressToJpeg(new Rect(0, 0, i, i2), 60, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                options.inSampleSize = AndroidImpl.calculateInSampleSize(options, options.outWidth / 2, options.outHeight / 2);
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                options.inPurgeable = true;
                options.inInputShareable = true;
                Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                if (bitmapDecodeByteArray != null) {
                    Matrix matrix = new Matrix();
                    matrix.setRotate(i3);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrix, false);
                    if (!bitmapDecodeByteArray.isRecycled()) {
                        bitmapDecodeByteArray.recycle();
                    }
                    return bitmapCreateBitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } finally {
            MiscUtil.safeClose(byteArrayOutputStream);
        }
    }

    public static void zipFiles(ArrayList<ByteBuffer> arrayList, File file, int i, int i2, int i3) throws Exception {
        Bitmap bitmapByteBuffer2Bitmap;
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            String str = "face_frame_" + i4 + ".jpeg";
            ByteBuffer byteBuffer = arrayList.get(i4);
            if (byteBuffer != null && (bitmapByteBuffer2Bitmap = byteBuffer2Bitmap(byteBuffer, i, i2, i3)) != null && !bitmapByteBuffer2Bitmap.isRecycled()) {
                zipFile(str, bitmapByteBuffer2Bitmap, zipOutputStream);
            }
        }
        zipOutputStream.close();
    }

    private static void zipFile(String str, Bitmap bitmap, ZipOutputStream zipOutputStream) throws Exception {
        byte[] bArrBitmap2Bytes = MiscUtil.bitmap2Bytes(bitmap);
        if (bArrBitmap2Bytes == null || bArrBitmap2Bytes.length <= 0) {
            return;
        }
        zipOutputStream.putNextEntry(new ZipEntry(str));
        zipOutputStream.write(bArrBitmap2Bytes, 0, bArrBitmap2Bytes.length);
        zipOutputStream.flush();
        zipOutputStream.closeEntry();
    }
}
