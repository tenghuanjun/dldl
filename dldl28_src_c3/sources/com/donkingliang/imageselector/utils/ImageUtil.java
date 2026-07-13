package com.donkingliang.imageselector.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import com.by.sjlr.cq28.wxapi.WXEntryActivity$;
import com.volcengine.cloudphone.base.VeVideoFrame;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ImageUtil {
    public static String getImageCacheDir(Context context) {
        File externalCacheDir;
        if (!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
            externalCacheDir = null;
        } else if (VersionUtils.isAndroidQ()) {
            externalCacheDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        } else {
            externalCacheDir = context.getExternalCacheDir();
        }
        if (externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        return externalCacheDir.getPath() + File.separator + "image_select";
    }

    public static String saveImage(Bitmap bitmap, String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str3 = str + File.separator + str2 + ".jpg";
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(str3);
            } catch (Throwable th) {
                th = th;
            }
        } catch (FileNotFoundException e) {
            e = e;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream);
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return str3;
        } catch (FileNotFoundException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                    return "";
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return "";
                }
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float fMin = Math.min(i / width, i2 / height);
        Matrix matrix = new Matrix();
        matrix.postScale(fMin, fMin);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap decodeSampledBitmapFromFile(Context context, String str, int i, int i2) {
        ExifInterface exifInterface;
        Bitmap bitmapDecodeFile;
        Uri imageContentUri = UriUtils.getImageContentUri(context, str);
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(imageContentUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptorOpenFileDescriptor.getFileDescriptor();
            if (VersionUtils.isAndroidQ()) {
                WXEntryActivity$.ExternalSyntheticApiModelOutline0.m$2();
                exifInterface = WXEntryActivity$.ExternalSyntheticApiModelOutline0.m(fileDescriptor);
            } else {
                exifInterface = new ExifInterface(str);
            }
            int attributeInt = exifInterface.getAttributeInt("Orientation", 0);
            int i3 = attributeInt != 3 ? attributeInt != 6 ? attributeInt != 8 ? 0 : VeVideoFrame.VideoRotation.VIDEO_ROTATION_270 : 90 : 180;
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                if (VersionUtils.isAndroidQ()) {
                    BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                } else {
                    BitmapFactory.decodeFile(str, options);
                }
                options.inSampleSize = calculateInSampleSize(options, i, i2);
                options.inJustDecodeBounds = false;
                if (VersionUtils.isAndroidQ()) {
                    bitmapDecodeFile = getBitmapFromUri(context, imageContentUri, options);
                } else {
                    bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
                }
                parcelFileDescriptorOpenFileDescriptor.close();
                if (i3 == 0) {
                    return bitmapDecodeFile;
                }
                Bitmap bitmapRotateImageView = rotateImageView(bitmapDecodeFile, i3);
                bitmapDecodeFile.recycle();
                return bitmapRotateImageView;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (OutOfMemoryError unused) {
                Log.e("eee", "内存泄露！");
                return null;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        return getBitmapFromUri(context, uri, null);
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri, BitmapFactory.Options options) {
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            Bitmap bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor(), null, options);
            parcelFileDescriptorOpenFileDescriptor.close();
            return bitmapDecodeFileDescriptor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap rotateImageView(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        if (i3 <= i || i4 <= i2) {
            return 1;
        }
        return Math.max(Math.round(i3 / i), Math.round(i4 / i2));
    }

    public static boolean isEffective(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return options.outWidth > 0 && options.outHeight > 0;
    }

    public static boolean isEffective(Context context, Uri uri) {
        try {
            FileDescriptor fileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r").getFileDescriptor();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
            if (options.outWidth > 0) {
                return options.outHeight > 0;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isCutImage(Context context, String str) {
        return isCutImage(getImageCacheDir(context), str);
    }

    public static boolean isCutImage(String str, String str2) {
        if (StringUtils.isEmptyString(str2)) {
            return false;
        }
        return str2.startsWith(str);
    }

    public static void savePicture(final Context context, final Uri uri, final long j) {
        new Thread(new Runnable() { // from class: com.donkingliang.imageselector.utils.ImageUtil.1
            @Override // java.lang.Runnable
            public void run() {
                if (ImageUtil.isNeedSavePicture(context, j)) {
                    context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", uri));
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isNeedSavePicture(Context context, long j) {
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendQueryParameter("limit", "1").build(), new String[]{"date_added", "_id", "_size"}, "_size>0", null, "_id DESC");
        if (cursorQuery == null || cursorQuery.getCount() <= 0 || !cursorQuery.moveToFirst()) {
            return true;
        }
        long j2 = cursorQuery.getLong(cursorQuery.getColumnIndex("date_added"));
        cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
        if (String.valueOf(j2).length() < 13) {
            j2 *= 1000;
        }
        cursorQuery.close();
        return j2 + 1000 < j;
    }
}
