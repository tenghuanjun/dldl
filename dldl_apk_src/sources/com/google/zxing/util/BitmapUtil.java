package com.google.zxing.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class BitmapUtil {
    public static Bitmap decodeUri(Context context, Uri uri, int i, int i2) throws Throwable {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        readBitmapScale(context, uri, options);
        int i3 = 1;
        for (int i4 = 0; i4 < Integer.MAX_VALUE && ((options.outWidth / i3 > i && options.outWidth / i3 > ((double) i) * 1.4d) || (options.outHeight / i3 > i2 && options.outHeight / i3 > ((double) i2) * 1.4d)); i4++) {
            i3++;
        }
        options.inSampleSize = i3;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        try {
            return readBitmapData(context, uri, options);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static void readBitmapScale(Context context, Uri uri, BitmapFactory.Options options) throws Throwable {
        String str;
        StringBuilder sb;
        InputStream inputStreamOpenInputStream;
        if (uri == null) {
            return;
        }
        String scheme = uri.getScheme();
        if ("content".equals(scheme) || "file".equals(scheme)) {
            InputStream inputStream = null;
            try {
                try {
                    inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
                if (inputStreamOpenInputStream != null) {
                    try {
                        inputStreamOpenInputStream.close();
                        return;
                    } catch (IOException e2) {
                        e = e2;
                        str = "readBitmapScale";
                        sb = new StringBuilder();
                        sb.append("Unable to close content: ");
                        sb.append(uri);
                        Log.e(str, sb.toString(), e);
                        return;
                    }
                }
                return;
            } catch (Exception e3) {
                e = e3;
                inputStream = inputStreamOpenInputStream;
                Log.w("readBitmapScale", "Unable to open content: " + uri, e);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                        return;
                    } catch (IOException e4) {
                        e = e4;
                        str = "readBitmapScale";
                        sb = new StringBuilder();
                        sb.append("Unable to close content: ");
                        sb.append(uri);
                        Log.e(str, sb.toString(), e);
                        return;
                    }
                }
                return;
            } catch (Throwable th2) {
                th = th2;
                inputStream = inputStreamOpenInputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        Log.e("readBitmapScale", "Unable to close content: " + uri, e5);
                    }
                }
                throw th;
            }
        }
        if ("android.resource".equals(scheme)) {
            Log.e("readBitmapScale", "Unable to close content: " + uri);
            return;
        }
        Log.e("readBitmapScale", "Unable to close content: " + uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00b9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Bitmap readBitmapData(android.content.Context r4, android.net.Uri r5, android.graphics.BitmapFactory.Options r6) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.util.BitmapUtil.readBitmapData(android.content.Context, android.net.Uri, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }
}
