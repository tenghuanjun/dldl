package com.huya.berry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.security.MessageDigest;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GlideRoundTransform extends BitmapTransformation {
    private static final String ID = "com.huya.live.utils.image.GlideRoundTransform";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    public int mRadius;

    public GlideRoundTransform(Context context, int i) {
        this.mRadius = 10;
        this.mRadius = i;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return roundCrop(bitmapPool, bitmap);
    }

    public Bitmap roundCrop(BitmapPool bitmapPool, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = bitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
        if (bitmapCreateBitmap == null) {
            bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-16777216);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        int i = this.mRadius;
        canvas.drawRoundRect(rectF, i, i, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return ("com.huya.live.utils.image.GlideRoundTransform_" + this.mRadius).hashCode();
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return (obj instanceof GlideRoundTransform) && this.mRadius == ((GlideRoundTransform) obj).mRadius;
    }
}
