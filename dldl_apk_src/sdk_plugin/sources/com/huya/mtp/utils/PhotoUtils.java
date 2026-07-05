package com.huya.mtp.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.LruCache;
import android.widget.ImageView;
import com.huya.mtp.api.MTPApi;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PhotoUtils {
    private static final int IMAGE_HIGH = 1200;
    private static final int IMAGE_WIDTH = 1600;
    private static LruCache<String, Bitmap> mCache;

    public static File createImageFile(String str) throws IOException {
        File externalStoragePublicDirectory;
        if (StringUtils.isNullOrEmpty(str) || !new File(str).isDirectory()) {
            externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        } else {
            externalStoragePublicDirectory = new File(str);
        }
        FileUtils.ensureDirExists(externalStoragePublicDirectory.getAbsolutePath());
        return File.createTempFile("JPEG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_", ".jpg", externalStoragePublicDirectory);
    }

    public static void addPhotoToGallery(Context context, String str) {
        if (StringUtils.isNullOrEmpty(str)) {
            return;
        }
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        context.sendBroadcast(intent);
    }

    public static String compressUsingCacheDir(Context context, String str, boolean z) throws IOException {
        String absolutePath;
        if (str == null) {
            return null;
        }
        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDir == null) {
            absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            absolutePath = externalFilesDir.getAbsolutePath();
        }
        if (z) {
            return compressAndAdjustOrientation(str, createImageFile(absolutePath).getAbsolutePath());
        }
        return compress(str, createImageFile(absolutePath).getAbsolutePath());
    }

    public static String compress(String str, String str2) throws IOException {
        Bitmap bitmapDecodeFile = decodeFile(str);
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        bitmapDecodeFile.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return str2;
    }

    public static String compressAndAdjustOrientation(String str, String str2) throws IOException {
        int cameraPhotoOrientation = getCameraPhotoOrientation(str);
        Bitmap bitmapDecodeFile = decodeFile(str);
        if (cameraPhotoOrientation != 0) {
            bitmapDecodeFile = rotaingImageView(cameraPhotoOrientation, bitmapDecodeFile);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        bitmapDecodeFile.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return str2;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    private static Bitmap decodeFile(String str) throws IOException {
        int i;
        File file = new File(str);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        FileInputStream fileInputStream = new FileInputStream(file);
        BitmapFactory.decodeStream(fileInputStream, null, options);
        fileInputStream.close();
        if (options.outWidth > options.outHeight && options.outWidth > IMAGE_WIDTH) {
            i = options.outWidth / IMAGE_WIDTH;
        } else if (options.outWidth < options.outHeight && options.outHeight > IMAGE_HIGH) {
            i = options.outHeight / IMAGE_HIGH;
        } else {
            i = (options.outWidth != options.outHeight || options.outHeight <= IMAGE_WIDTH) ? 1 : options.outHeight / IMAGE_WIDTH;
        }
        int i2 = i > 0 ? i : 1;
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = i2;
        FileInputStream fileInputStream2 = new FileInputStream(file);
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream2, null, options2);
        fileInputStream2.close();
        return bitmapDecodeStream;
    }

    public static void photoBind(ImageView imageView, String str, int i, int i2) {
        imageView.setImageBitmap(getThumbnailUtils(str, i, i2));
    }

    public static void photoBind(ImageView imageView, String str) {
        imageView.setImageBitmap(getThumbnailUtils(str, imageView.getLayoutParams().width, imageView.getLayoutParams().height));
    }

    public static Bitmap getThumbnailUtils(String str, int i, int i2) {
        if (mCache == null) {
            mCache = new LruCache<>(((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 8);
        }
        String str2 = str + "size" + String.valueOf(i) + ":" + String.valueOf(i2);
        if (mCache.get(str2) == null) {
            int cameraPhotoOrientation = getCameraPhotoOrientation(str);
            Bitmap bitmapExtractThumbnail = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(str), i, i2);
            if (bitmapExtractThumbnail == null) {
                return null;
            }
            mCache.put(str2, cameraPhotoOrientation == 0 ? bitmapExtractThumbnail : rotaingImageView(cameraPhotoOrientation, bitmapExtractThumbnail));
            return bitmapExtractThumbnail;
        }
        return mCache.get(str2);
    }

    private static void readFromFile(ImageView imageView, String str) {
        readFromFile(imageView, str, imageView.getLayoutParams().width, imageView.getLayoutParams().height);
    }

    private static void readFromFile(ImageView imageView, String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int iMin = Math.min(options.outWidth / i, options.outHeight / i2);
        options.inJustDecodeBounds = false;
        options.inSampleSize = iMin;
        options.inPurgeable = true;
        imageView.setImageBitmap(BitmapFactory.decodeFile(str, options));
    }

    public static Bitmap rotaingImageView(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static int getCameraPhotoOrientation(String str) {
        try {
            int attributeInt = new ExifInterface(new File(str).getAbsolutePath()).getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt != 6) {
                return attributeInt != 8 ? 0 : 270;
            }
            return 90;
        } catch (Exception e) {
            MTPApi.LOGGER.error("PhotoUtils error : ", e);
            return 0;
        }
    }
}
