package com.duowan.live.base.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;
import com.bumptech.glide.Registry;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BitmapUtil {
    public static final int DEFAULT_BITMAP_HEIGHT = 200;
    public static final int DEFAULT_BITMAP_WIDTH = 200;
    private static final String TAG = BitmapUtil.class.getSimpleName();

    public static byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap byteToBitmap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static String bitmapToString(Bitmap bitmap) {
        return Base64.encodeToString(bitmapToByte(bitmap), 0);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(bitmap);
    }

    public static Bitmap scaleImageTo(Bitmap bitmap, int i, int i2) {
        return scaleImage(bitmap, i / bitmap.getWidth(), i2 / bitmap.getHeight());
    }

    public static Bitmap scaleImage(Bitmap bitmap, float f, float f2) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap) {
        int height = bitmap.getHeight();
        int height2 = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(height2, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, height2, height);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0);
        float f = height2 / 2;
        canvas.drawCircle(f, height / 2, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap createBitmapThumbnail(Bitmap bitmap, boolean z, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i2 / width, i / height);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (z) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static boolean saveBitmap(Bitmap bitmap, Bitmap.CompressFormat compressFormat, File file) throws Throwable {
        FileOutputStream fileOutputStream;
        if (bitmap == null) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            bitmap.compress(compressFormat, 100, fileOutputStream);
            fileOutputStream.flush();
            try {
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return true;
        } catch (Exception e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean saveBitmap(Bitmap bitmap, Bitmap.CompressFormat compressFormat, String str) {
        return saveBitmap(bitmap, compressFormat, new File(str));
    }

    public static Intent buildImageGetIntent(Uri uri, int i, int i2, boolean z) {
        return buildImageGetIntent(uri, 1, 1, i, i2, z);
    }

    public static Intent buildImageGetIntent(Uri uri, int i, int i2, int i3, int i4, boolean z) {
        L.info(TAG, "Build.VERSION.SDK_INT : " + Build.VERSION.SDK_INT);
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction("android.intent.action.GET_CONTENT");
        } else {
            intent.setAction("android.intent.action.OPEN_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
        }
        intent.setType("image/*");
        intent.putExtra("output", uri);
        intent.putExtra("aspectX", i);
        intent.putExtra("aspectY", i2);
        intent.putExtra("outputX", i3);
        intent.putExtra("outputY", i4);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", z);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    public static Intent buildImageCropIntent(Uri uri, Uri uri2, int i, int i2, boolean z) {
        return buildImageCropIntent(uri, uri2, 1, 1, i, i2, z);
    }

    public static Intent buildImageCropIntent(Uri uri, Uri uri2, int i, int i2, int i3, int i4, boolean z) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("output", uri2);
        intent.putExtra("aspectX", i);
        intent.putExtra("aspectY", i2);
        intent.putExtra("outputX", i3);
        intent.putExtra("outputY", i4);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", z);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    public static Intent buildImageCaptureIntent(Uri uri) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uri);
        return intent;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int iMin;
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 > i2 || i4 > i) {
            iMin = (int) Math.min(i3 / i2, i4 / i);
        } else {
            iMin = 0;
        }
        return Math.max(1, iMin);
    }

    public static Bitmap getSmallBitmap(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap getSmallBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Matrix matrix = new Matrix();
        if (height > i2 || width > i) {
            float fMax = Math.max(i2 / height, i / width);
            matrix.setScale(fMax, fMax);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    public static byte[] bitmap2Byte(Bitmap bitmap) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bitmap == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static byte[] compressBitmapToBytes(String str, int i, int i2, int i3) {
        Bitmap smallBitmap = getSmallBitmap(str, i, i2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        smallBitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        smallBitmap.recycle();
        L.info(TAG, "Bitmap compressed success, size: " + byteArray.length);
        return byteArray;
    }

    public static byte[] compressBitmapSmallTo(String str, int i, int i2, int i3) {
        int i4 = 100;
        byte[] bArrCompressBitmapToBytes = compressBitmapToBytes(str, i, i2, 100);
        while (bArrCompressBitmapToBytes.length > i3 && i4 > 0) {
            i4 /= 2;
            bArrCompressBitmapToBytes = compressBitmapToBytes(str, i, i2, i4);
        }
        return bArrCompressBitmapToBytes;
    }

    public static byte[] compressBitmapQuikly(String str) {
        return compressBitmapToBytes(str, 200, 200, 50);
    }

    public static byte[] compressBitmapQuiklySmallTo(String str, int i) {
        return compressBitmapSmallTo(str, 200, 200, i);
    }

    public static Bitmap decodeUriAsBitmap(Uri uri) {
        try {
            return BitmapFactory.decodeStream(ArkValue.gContext.getContentResolver().openInputStream(uri));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:0|2|67|(7:65|3|81|4|5|79|6)|(4:7|(1:9)(1:84)|66|52)|10|73|11|15|66|52|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
    
        com.duowan.auk.util.L.error(com.duowan.live.base.utils.BitmapUtil.TAG, (java.lang.Throwable) r6);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v11, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.io.FileInputStream] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0036 -> B:66:0x007f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] File2byte(java.lang.String r6) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L50 java.io.FileNotFoundException -> L68
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L50 java.io.FileNotFoundException -> L68
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L50 java.io.FileNotFoundException -> L68
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L50 java.io.FileNotFoundException -> L68
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L48
            r1.<init>()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L48
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L3c java.io.FileNotFoundException -> L3e java.lang.Throwable -> L80
        L14:
            int r3 = r6.read(r2)     // Catch: java.io.IOException -> L3c java.io.FileNotFoundException -> L3e java.lang.Throwable -> L80
            r4 = -1
            if (r3 == r4) goto L20
            r4 = 0
            r1.write(r2, r4, r3)     // Catch: java.io.IOException -> L3c java.io.FileNotFoundException -> L3e java.lang.Throwable -> L80
            goto L14
        L20:
            r1.close()     // Catch: java.io.IOException -> L3c java.io.FileNotFoundException -> L3e java.lang.Throwable -> L80
            byte[] r0 = r1.toByteArray()     // Catch: java.io.IOException -> L3c java.io.FileNotFoundException -> L3e java.lang.Throwable -> L80
            r6.close()     // Catch: java.io.IOException -> L2b
            goto L31
        L2b:
            r6 = move-exception
            java.lang.String r2 = com.duowan.live.base.utils.BitmapUtil.TAG
            com.duowan.auk.util.L.error(r2, r6)
        L31:
            r1.close()     // Catch: java.io.IOException -> L35
            goto L7f
        L35:
            r6 = move-exception
            java.lang.String r1 = com.duowan.live.base.utils.BitmapUtil.TAG
            com.duowan.auk.util.L.error(r1, r6)
            goto L7f
        L3c:
            r2 = move-exception
            goto L53
        L3e:
            r2 = move-exception
            goto L6b
        L40:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L81
        L45:
            r2 = move-exception
            r1 = r0
            goto L53
        L48:
            r2 = move-exception
            r1 = r0
            goto L6b
        L4b:
            r6 = move-exception
            r1 = r0
            r0 = r6
            r6 = r1
            goto L81
        L50:
            r2 = move-exception
            r6 = r0
            r1 = r6
        L53:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L80
            if (r6 == 0) goto L62
            r6.close()     // Catch: java.io.IOException -> L5c
            goto L62
        L5c:
            r6 = move-exception
            java.lang.String r2 = com.duowan.live.base.utils.BitmapUtil.TAG
            com.duowan.auk.util.L.error(r2, r6)
        L62:
            if (r1 == 0) goto L7f
            r1.close()     // Catch: java.io.IOException -> L35
            goto L7f
        L68:
            r2 = move-exception
            r6 = r0
            r1 = r6
        L6b:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L80
            if (r6 == 0) goto L7a
            r6.close()     // Catch: java.io.IOException -> L74
            goto L7a
        L74:
            r6 = move-exception
            java.lang.String r2 = com.duowan.live.base.utils.BitmapUtil.TAG
            com.duowan.auk.util.L.error(r2, r6)
        L7a:
            if (r1 == 0) goto L7f
            r1.close()     // Catch: java.io.IOException -> L35
        L7f:
            return r0
        L80:
            r0 = move-exception
        L81:
            if (r6 == 0) goto L8d
            r6.close()     // Catch: java.io.IOException -> L87
            goto L8d
        L87:
            r6 = move-exception
            java.lang.String r2 = com.duowan.live.base.utils.BitmapUtil.TAG
            com.duowan.auk.util.L.error(r2, r6)
        L8d:
            if (r1 == 0) goto L99
            r1.close()     // Catch: java.io.IOException -> L93
            goto L99
        L93:
            r6 = move-exception
            java.lang.String r1 = com.duowan.live.base.utils.BitmapUtil.TAG
            com.duowan.auk.util.L.error(r1, r6)
        L99:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.live.base.utils.BitmapUtil.File2byte(java.lang.String):byte[]");
    }

    public static Bitmap compressImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        while (((double) byteArrayOutputStream.toByteArray().length) / 1024.0d > 1024.0d) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 10;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap getImage(java.lang.String r6) {
        /*
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 1
            r0.inJustDecodeBounds = r1
            android.graphics.BitmapFactory.decodeFile(r6, r0)
            r2 = 0
            r0.inJustDecodeBounds = r2
            int r2 = r0.outWidth
            int r3 = r0.outHeight
            if (r2 <= r3) goto L21
            float r4 = (float) r2
            r5 = 1139802112(0x43f00000, float:480.0)
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L21
            int r2 = r0.outWidth
            float r2 = (float) r2
            float r2 = r2 / r5
        L1f:
            int r2 = (int) r2
            goto L30
        L21:
            if (r2 >= r3) goto L2f
            float r2 = (float) r3
            r3 = 1145569280(0x44480000, float:800.0)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L2f
            int r2 = r0.outHeight
            float r2 = (float) r2
            float r2 = r2 / r3
            goto L1f
        L2f:
            r2 = 1
        L30:
            if (r2 > 0) goto L33
            goto L34
        L33:
            r1 = r2
        L34:
            r0.inSampleSize = r1
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeFile(r6, r0)
            android.graphics.Bitmap r6 = compressImage(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.live.base.utils.BitmapUtil.getImage(java.lang.String):android.graphics.Bitmap");
    }

    public static Bitmap getImage(Uri uri) throws FileNotFoundException {
        return getImage(uri, 960, 960);
    }

    public static Bitmap getImage(Uri uri, int i, int i2) throws FileNotFoundException {
        float f;
        float f2;
        int i3 = i > i2 ? i : i2;
        if (i > i2) {
            i = i2;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ArkValue.gContext.getContentResolver().openInputStream(uri), null, options);
        options.inJustDecodeBounds = false;
        int i4 = options.outWidth;
        int i5 = options.outHeight;
        if (i4 > i5) {
            f2 = i;
            f = i3;
        } else {
            float f3 = i3;
            f = i;
            f2 = f3;
        }
        float f4 = i4 / f;
        float f5 = i5 / f2;
        int iCeil = (int) Math.ceil(f4 > f5 ? f4 : f5);
        L.error(Registry.BUCKET_BITMAP, "getImage,beW " + f4 + ";beH " + f5 + ";be:" + iCeil);
        options.inSampleSize = iCeil;
        return BitmapFactory.decodeStream(ArkValue.gContext.getContentResolver().openInputStream(uri), null, options);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap comp(android.graphics.Bitmap r8) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r2 = 100
            r8.compress(r1, r2, r0)
            byte[] r1 = r0.toByteArray()
            int r1 = r1.length
            r2 = 1024(0x400, float:1.435E-42)
            int r1 = r1 / r2
            if (r1 <= r2) goto L20
            r0.reset()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r2 = 50
            r8.compress(r1, r2, r0)
        L20:
            java.io.ByteArrayInputStream r8 = new java.io.ByteArrayInputStream
            byte[] r1 = r0.toByteArray()
            r8.<init>(r1)
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            r2 = 1
            r1.inJustDecodeBounds = r2
            r3 = 0
            android.graphics.BitmapFactory.decodeStream(r8, r3, r1)
            r8 = 0
            r1.inJustDecodeBounds = r8
            int r8 = r1.outWidth
            int r4 = r1.outHeight
            r5 = 1145569280(0x44480000, float:800.0)
            r6 = 1139802112(0x43f00000, float:480.0)
            if (r8 <= r4) goto L4d
            float r7 = (float) r8
            int r7 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r7 <= 0) goto L4d
            int r8 = r1.outWidth
            float r8 = (float) r8
            float r8 = r8 / r6
        L4b:
            int r8 = (int) r8
            goto L5a
        L4d:
            if (r8 >= r4) goto L59
            float r8 = (float) r4
            int r8 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r8 <= 0) goto L59
            int r8 = r1.outHeight
            float r8 = (float) r8
            float r8 = r8 / r5
            goto L4b
        L59:
            r8 = 1
        L5a:
            if (r8 > 0) goto L5d
            goto L5e
        L5d:
            r2 = r8
        L5e:
            r1.inSampleSize = r2
            java.io.ByteArrayInputStream r8 = new java.io.ByteArrayInputStream
            byte[] r0 = r0.toByteArray()
            r8.<init>(r0)
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeStream(r8, r3, r1)
            android.graphics.Bitmap r8 = compressImage(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.live.base.utils.BitmapUtil.comp(android.graphics.Bitmap):android.graphics.Bitmap");
    }

    public static Bitmap sizeBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        float f = i / width;
        float f2 = i2 / height;
        if (f >= f2) {
            f = f2;
        }
        new Matrix().postScale(f, f);
        return Bitmap.createBitmap(bitmap, (width / 2) - (i / 2), (height / 2) - (i2 / 2), i, i2, (Matrix) null, false);
    }

    public static Bitmap blurBitmap(Bitmap bitmap, Context context) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript renderScriptCreate = RenderScript.create(context);
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmap);
        Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateBitmap);
        scriptIntrinsicBlurCreate.setRadius(25.0f);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
        allocationCreateFromBitmap2.copyTo(bitmapCreateBitmap);
        bitmap.recycle();
        renderScriptCreate.destroy();
        return bitmapCreateBitmap;
    }

    public static Bitmap reverseBitmap(Bitmap bitmap, int i) {
        float[] fArr = i != 0 ? i != 1 ? null : new float[]{1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f} : new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        if (fArr == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setValues(fArr);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
