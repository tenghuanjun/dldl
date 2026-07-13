package com.cy.yyjia.zhe28.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class BitmapUtil {
    private BitmapUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    static Bitmap getScaledBitmap(Context context, Uri imageUri, float maxWidth, float maxHeight, Bitmap.Config bitmapConfig) {
        String realPathFromURI = FileUtil.getRealPathFromURI(context, imageUri);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(realPathFromURI, options);
        Bitmap bitmapCreateBitmap = null;
        if (bitmapDecodeFile == null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(realPathFromURI);
                BitmapFactory.decodeStream(fileInputStream, null, options);
                fileInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        int attributeInt = options.outHeight;
        int attributeInt2 = options.outWidth;
        if (attributeInt == -1 || attributeInt2 == -1) {
            try {
                ExifInterface exifInterface = new ExifInterface(realPathFromURI);
                attributeInt = exifInterface.getAttributeInt("ImageLength", 1);
                attributeInt2 = exifInterface.getAttributeInt("ImageWidth", 1);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        if (attributeInt2 <= 0 || attributeInt <= 0) {
            Bitmap bitmapDecodeFile2 = BitmapFactory.decodeFile(realPathFromURI);
            if (bitmapDecodeFile2 == null) {
                return null;
            }
            attributeInt2 = bitmapDecodeFile2.getWidth();
            attributeInt = bitmapDecodeFile2.getHeight();
        }
        float f = attributeInt2;
        float f2 = attributeInt;
        float f3 = f / f2;
        float f4 = maxWidth / maxHeight;
        if (f2 > maxHeight || f > maxWidth) {
            if (f3 < f4) {
                attributeInt2 = (int) ((maxHeight / f2) * f);
                attributeInt = (int) maxHeight;
            } else {
                attributeInt = f3 > f4 ? (int) ((maxWidth / f) * f2) : (int) maxHeight;
                attributeInt2 = (int) maxWidth;
            }
        }
        options.inSampleSize = calculateInSampleSize(options, attributeInt2, attributeInt);
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16384];
        try {
            bitmapDecodeFile = BitmapFactory.decodeFile(realPathFromURI, options);
            if (bitmapDecodeFile == null) {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(realPathFromURI);
                    BitmapFactory.decodeStream(fileInputStream2, null, options);
                    fileInputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        } catch (OutOfMemoryError e5) {
            e5.printStackTrace();
        }
        if (attributeInt <= 0 || attributeInt2 <= 0) {
            return null;
        }
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(attributeInt2, attributeInt, bitmapConfig);
        } catch (OutOfMemoryError e6) {
            e6.printStackTrace();
        }
        float f5 = attributeInt2 / options.outWidth;
        float f6 = attributeInt / options.outHeight;
        Matrix matrix = new Matrix();
        matrix.setScale(f5, f6, 0.0f, 0.0f);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.setMatrix(matrix);
        canvas.drawBitmap(bitmapDecodeFile, 0.0f, 0.0f, new Paint(2));
        try {
            int attributeInt3 = new ExifInterface(realPathFromURI).getAttributeInt("Orientation", 0);
            Matrix matrix2 = new Matrix();
            if (attributeInt3 == 6) {
                matrix2.postRotate(90.0f);
            } else if (attributeInt3 == 3) {
                matrix2.postRotate(180.0f);
            } else if (attributeInt3 == 8) {
                matrix2.postRotate(270.0f);
            }
            return Bitmap.createBitmap(bitmapCreateBitmap, 0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), matrix2, true);
        } catch (IOException e7) {
            e7.printStackTrace();
            return bitmapCreateBitmap;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v7 */
    public static File compressImage(Context context, Uri uri, float f, float f2, Bitmap.CompressFormat compressFormat, Bitmap.Config config, int i, String str, String str2, String str3) throws Throwable {
        FileOutputStream fileOutputStream;
        Object obj;
        String strGenerateFilePath = generateFilePath(context, str, uri, compressFormat.name().toLowerCase(), str2, str3);
        ?? r2 = 0;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(strGenerateFilePath);
                    obj = uri;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (FileNotFoundException e) {
                e = e;
            }
            try {
                Bitmap scaledBitmap = getScaledBitmap(context, uri, f, f2, config);
                if (scaledBitmap != null) {
                    obj = compressFormat;
                    scaledBitmap.compress(compressFormat, i, fileOutputStream);
                }
                fileOutputStream.close();
                r2 = obj;
            } catch (FileNotFoundException e2) {
                e = e2;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                r2 = fileOutputStream2;
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                    r2 = fileOutputStream2;
                }
            } catch (Throwable th2) {
                th = th2;
                r2 = fileOutputStream;
                if (r2 != 0) {
                    try {
                        r2.close();
                    } catch (IOException unused) {
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
        }
        return new File(strGenerateFilePath);
    }

    public static String generateFilePath(Context context, String parentPath, Uri uri, String extension, String prefix, String fileName) {
        File file = new File(parentPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (TextUtils.isEmpty(prefix)) {
            prefix = "";
        }
        if (TextUtils.isEmpty(fileName)) {
            fileName = prefix + FileUtil.splitFileName(FileUtil.getFileName(context, uri))[0];
        }
        return file.getAbsolutePath() + File.separator + fileName + "." + extension;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int iRound;
        int i = options.outHeight;
        int i2 = options.outWidth;
        if (i > reqHeight || i2 > reqWidth) {
            iRound = Math.round(i / reqHeight);
            int iRound2 = Math.round(i2 / reqWidth);
            if (iRound >= iRound2) {
                iRound = iRound2;
            }
        } else {
            iRound = 1;
        }
        while ((i2 * i) / (iRound * iRound) > reqWidth * reqHeight * 2) {
            iRound++;
        }
        return iRound;
    }
}
