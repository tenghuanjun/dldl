package com.social.sdk.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Environment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class BitmapUtils {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r4v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.graphics.Bitmap] */
    public static byte[] bitmap2Bytes(Bitmap bitmap) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ?? r3 = 0;
        if (bitmap != 0) {
            ?? IsRecycled = bitmap.isRecycled();
            try {
                if (IsRecycled == 0) {
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                            if (byteArrayOutputStream.toByteArray() == null) {
                                LogUtils.e("BitmapUtils", "bitmap2Bytes byteArrayOutputStream toByteArray=null");
                            }
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (bitmap != 0 && !bitmap.isRecycled()) {
                                bitmap.recycle();
                            }
                            return byteArray;
                        } catch (Exception e2) {
                            e = e2;
                            LogUtils.e("BitmapUtils", e.toString());
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (bitmap != 0 && !bitmap.isRecycled()) {
                                bitmap.recycle();
                            }
                            return null;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        byteArrayOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        if (r3 != 0) {
                            try {
                                r3.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        if (bitmap != 0 && !bitmap.isRecycled()) {
                            bitmap.recycle();
                            throw th;
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                r3 = IsRecycled;
            }
        }
        LogUtils.e("BitmapUtils", "bitmap2Bytes bitmap == null or bitmap.isRecycled()");
        return null;
    }

    public static byte[] compressBitmap(Bitmap bitmap, int i) throws Throwable {
        byte[] bArrBitmap2Bytes = bitmap2Bytes(bitmap);
        if (bArrBitmap2Bytes == null || bArrBitmap2Bytes.length <= i) {
            return bArrBitmap2Bytes;
        }
        LogUtils.i("原图大小：" + (bArrBitmap2Bytes.length / 1024) + ",需压缩到" + (i / 1024));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrBitmap2Bytes, 0, bArrBitmap2Bytes.length);
        boolean z = false;
        int i2 = 1;
        while (!z && i2 <= 10) {
            bitmapDecodeByteArray.compress(Bitmap.CompressFormat.JPEG, (int) (Math.pow(0.8d, i2) * 100.0d), byteArrayOutputStream);
            LogUtils.i("第" + i2 + "次压缩后的大小:" + byteArrayOutputStream.size());
            if (byteArrayOutputStream.size() < i) {
                z = true;
            } else {
                if (i2 < 10) {
                    byteArrayOutputStream.reset();
                }
                i2++;
            }
        }
        if (!bitmapDecodeByteArray.isRecycled()) {
            bitmapDecodeByteArray.recycle();
        }
        if (byteArrayOutputStream.size() > i) {
            LogUtils.e("BitmapUtils", "compressBitmap cannot compress to " + i + ", after compress size=" + byteArrayOutputStream.size());
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.graphics.Bitmap] */
    public static boolean saveBitmap(String str, Bitmap bitmap) throws Throwable {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        boolean z = false;
        try {
            file.createNewFile();
            ?? r1 = 0;
            r1 = 0;
            r1 = 0;
            try {
                try {
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                r1 = 100;
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                z = true;
                fileOutputStream.close();
            } catch (FileNotFoundException e3) {
                e = e3;
                r1 = fileOutputStream;
                e.printStackTrace();
                if (r1 != 0) {
                    r1.close();
                    r1 = r1;
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                r1 = fileOutputStream;
                if (r1 != 0) {
                    try {
                        r1.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
            return z;
        } catch (IOException e5) {
            e5.printStackTrace();
            return false;
        }
    }

    public static File qqSaveToAlbum(Context context, Bitmap bitmap, String str) {
        File externalStoragePublicDirectory;
        try {
            if (Build.VERSION.SDK_INT >= 29 && context.getApplicationInfo().targetSdkVersion >= 29) {
                externalStoragePublicDirectory = context.getExternalFilesDir(Environment.DIRECTORY_DCIM);
            } else {
                externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            }
            File file = new File(externalStoragePublicDirectory, str);
            if (!externalStoragePublicDirectory.exists()) {
                externalStoragePublicDirectory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (bitmap == null) {
                LogUtils.i("Bitmap 对象为空，无法保存图片文件");
            }
            if (bitmap != null) {
                bitmap.compress(getBitmapFormat(str), 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            MediaScannerConnection.scanFile(context.getApplicationContext(), new String[]{file.getPath()}, null, null);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.i("数据有误，保存图片失败：" + str);
            return null;
        }
    }

    private static Bitmap.CompressFormat getBitmapFormat(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.endsWith(".png")) {
            return Bitmap.CompressFormat.PNG;
        }
        if (lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg")) {
            return Bitmap.CompressFormat.JPEG;
        }
        if (lowerCase.endsWith(".webp")) {
            if (Build.VERSION.SDK_INT >= 30) {
                return Bitmap.CompressFormat.WEBP_LOSSLESS;
            }
            return Bitmap.CompressFormat.WEBP;
        }
        return Bitmap.CompressFormat.PNG;
    }
}
