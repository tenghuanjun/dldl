package com.sqwan.msdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.utils.DisplayUtil;
import com.sy37sdk.utils.Util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BitmapUtils {
    public static Bitmap decodeResource(Context context, String str) {
        int idByName = Util.getIdByName(str, "drawable", context.getPackageName(), context);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), idByName, options);
        options.inSampleSize = calculateInSampleSize(options, DisplayUtil.getScreenWidth(context) / 2, DisplayUtil.getScreenHeight(context) / 2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), idByName, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        LogUtil.i("options:  width=" + i4 + "   height=" + i3);
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        LogUtil.i("计算得采样率：" + i5);
        return i5;
    }
}
