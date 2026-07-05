package com.sqwan.common.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import java.io.File;
import java.io.OutputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ImageUtils {
    public static Uri save(Context context, Bitmap bitmap, String str) {
        File file;
        Uri uriFromFile;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (Build.VERSION.SDK_INT >= 29) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("_display_name", str);
                contentValues.put("relative_path", Environment.DIRECTORY_DCIM + File.separator + str);
                String mimeType = getMimeType(str);
                if (mimeType != null) {
                    contentValues.put("mime_type", mimeType);
                }
                uriFromFile = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                file = null;
            } else {
                if (Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
                    LogUtil.i("没有存储卡权限，图片没法保存：" + str);
                    return null;
                }
                File file2 = new File(EnvironmentUtils.getCommonDirPath(context), Environment.DIRECTORY_DCIM);
                file = new File(file2, str);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    uriFromFile = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
                } else {
                    uriFromFile = Uri.fromFile(file);
                }
            }
            OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriFromFile);
            if (outputStreamOpenOutputStream == null) {
                LogUtil.i("输出流为空，无法保存图片文件");
            }
            if (bitmap == null) {
                LogUtil.i("Bitmap 对象为空，无法保存图片文件");
            }
            if (outputStreamOpenOutputStream != null && bitmap != null) {
                bitmap.compress(getBitmapFormat(str), 100, outputStreamOpenOutputStream);
                outputStreamOpenOutputStream.flush();
                outputStreamOpenOutputStream.close();
            }
            ContentValues contentValues2 = new ContentValues();
            if (Build.VERSION.SDK_INT >= 29) {
                contentValues2.put("is_pending", (Integer) 0);
                contentResolver.update(uriFromFile, contentValues2, null, null);
            } else {
                MediaScannerConnection.scanFile(context.getApplicationContext(), new String[]{file.getPath()}, null, null);
            }
            return uriFromFile;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i("数据有误，保存图片失败：" + str);
            return null;
        }
    }

    private static String getMimeType(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.endsWith(".png")) {
            return "image/png";
        }
        if (lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg")) {
            return "image/jpeg";
        }
        if (lowerCase.endsWith(".webp")) {
            return "image/webp";
        }
        if (lowerCase.endsWith(".gif")) {
            return "image/gif";
        }
        return null;
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
