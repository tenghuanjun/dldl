package com.sy37sdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class BitmapUtils {
    public static Bitmap decodeResource(Context context, String str) {
        int idByNameHostFirst = Util.getIdByNameHostFirst(str, "drawable", context.getPackageName(), context);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), idByNameHostFirst, options);
        options.inSampleSize = calculateInSampleSize(options, DisplayUtil.getScreenWidth(context) / 2, DisplayUtil.getScreenHeight(context) / 2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), idByNameHostFirst, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }
}
