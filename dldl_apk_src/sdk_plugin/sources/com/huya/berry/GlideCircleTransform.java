package com.huya.berry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.security.MessageDigest;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GlideCircleTransform extends BitmapTransformation {
    private static final String ID = "com.huya.live.utils.image.GlideRoundTransform";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return -115076963;
    }

    public GlideCircleTransform(Context context) {
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return circleCrop(bitmapPool, bitmap);
    }

    public static Bitmap circleCrop(BitmapPool bitmapPool, Bitmap bitmap) {
        Rect rect;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            rect = new Rect((width - height) / 2, 0, (width + height) / 2, height);
            width = height;
        } else {
            rect = new Rect(0, (height - width) / 2, width, (height + width) / 2);
        }
        Bitmap bitmapCreateBitmap = bitmapPool.get(width, width, Bitmap.Config.ARGB_8888);
        if (bitmapCreateBitmap == null) {
            bitmapCreateBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        float f = width;
        RectF rectF = new RectF(0.0f, 0.0f, f, f);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-16777216);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        return bitmapCreateBitmap;
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof GlideCircleTransform) {
            return true;
        }
        return super.equals(obj);
    }
}
