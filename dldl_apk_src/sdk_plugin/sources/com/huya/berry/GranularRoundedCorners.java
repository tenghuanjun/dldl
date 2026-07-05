package com.huya.berry;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GranularRoundedCorners extends BitmapTransformation {
    private static final Lock BITMAP_DRAWABLE_LOCK;
    private static final String ID = "com.huya.berry.GranularRoundedCorners";
    private static final byte[] ID_BYTES;
    private static final Set<String> MODELS_REQUIRING_BITMAP_LOCK;
    private final float bottomLeft;
    private final float bottomRight;
    private final float topLeft;
    private final float topRight;

    /* JADX INFO: Access modifiers changed from: private */
    interface DrawRoundedCornerFn {
        void drawRoundedCorners(Canvas canvas, Paint paint, RectF rectF);
    }

    static {
        HashSet hashSet = new HashSet(Arrays.asList("XT1085", "XT1092", "XT1093", "XT1094", "XT1095", "XT1096", "XT1097", "XT1098", "XT1031", "XT1028", "XT937C", "XT1032", "XT1008", "XT1033", "XT1035", "XT1034", "XT939G", "XT1039", "XT1040", "XT1042", "XT1045", "XT1063", "XT1064", "XT1068", "XT1069", "XT1072", "XT1077", "XT1078", "XT1079"));
        MODELS_REQUIRING_BITMAP_LOCK = hashSet;
        BITMAP_DRAWABLE_LOCK = hashSet.contains(Build.MODEL) ? new ReentrantLock() : new NoLock();
        ID_BYTES = ID.getBytes(CHARSET);
    }

    public GranularRoundedCorners(float f, float f2, float f3, float f4) {
        this.topLeft = f;
        this.topRight = f2;
        this.bottomRight = f3;
        this.bottomLeft = f4;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return roundedCorners(bitmapPool, bitmap, this.topLeft, this.topRight, this.bottomRight, this.bottomLeft);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (!(obj instanceof GranularRoundedCorners)) {
            return false;
        }
        GranularRoundedCorners granularRoundedCorners = (GranularRoundedCorners) obj;
        return this.topLeft == granularRoundedCorners.topLeft && this.topRight == granularRoundedCorners.topRight && this.bottomRight == granularRoundedCorners.bottomRight && this.bottomLeft == granularRoundedCorners.bottomLeft;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return Util.hashCode(this.bottomLeft, Util.hashCode(this.bottomRight, Util.hashCode(this.topRight, Util.hashCode(1793502229, Util.hashCode(this.topLeft)))));
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
        messageDigest.update(ByteBuffer.allocate(16).putFloat(this.topLeft).putFloat(this.topRight).putFloat(this.bottomRight).putFloat(this.bottomLeft).array());
    }

    public static Bitmap roundedCorners(BitmapPool bitmapPool, Bitmap bitmap, final float f, final float f2, final float f3, final float f4) {
        return roundedCorners(bitmapPool, bitmap, new DrawRoundedCornerFn() { // from class: com.huya.berry.-$$Lambda$GranularRoundedCorners$5KICmSmMKYN92fG0FKhj-FmqfFA
            @Override // com.huya.berry.GranularRoundedCorners.DrawRoundedCornerFn
            public final void drawRoundedCorners(Canvas canvas, Paint paint, RectF rectF) {
                GranularRoundedCorners.lambda$roundedCorners$0(f, f2, f3, f4, canvas, paint, rectF);
            }
        });
    }

    static /* synthetic */ void lambda$roundedCorners$0(float f, float f2, float f3, float f4, Canvas canvas, Paint paint, RectF rectF) {
        Path path = new Path();
        path.addRoundRect(rectF, new float[]{f, f, f2, f2, f3, f3, f4, f4}, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }

    private static Bitmap roundedCorners(BitmapPool bitmapPool, Bitmap bitmap, DrawRoundedCornerFn drawRoundedCornerFn) {
        Bitmap.Config alphaSafeConfig = getAlphaSafeConfig(bitmap);
        Bitmap alphaSafeBitmap = getAlphaSafeBitmap(bitmapPool, bitmap);
        Bitmap bitmap2 = bitmapPool.get(alphaSafeBitmap.getWidth(), alphaSafeBitmap.getHeight(), alphaSafeConfig);
        bitmap2.setHasAlpha(true);
        BitmapShader bitmapShader = new BitmapShader(alphaSafeBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        RectF rectF = new RectF(0.0f, 0.0f, bitmap2.getWidth(), bitmap2.getHeight());
        BITMAP_DRAWABLE_LOCK.lock();
        try {
            Canvas canvas = new Canvas(bitmap2);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            drawRoundedCornerFn.drawRoundedCorners(canvas, paint, rectF);
            clear(canvas);
            BITMAP_DRAWABLE_LOCK.unlock();
            if (!alphaSafeBitmap.equals(bitmap)) {
                bitmapPool.put(alphaSafeBitmap);
            }
            return bitmap2;
        } catch (Throwable th) {
            BITMAP_DRAWABLE_LOCK.unlock();
            throw th;
        }
    }

    private static void clear(Canvas canvas) {
        canvas.setBitmap(null);
    }

    private static Bitmap.Config getAlphaSafeConfig(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 26 && Bitmap.Config.RGBA_F16.equals(bitmap.getConfig())) {
            return Bitmap.Config.RGBA_F16;
        }
        return Bitmap.Config.ARGB_8888;
    }

    private static Bitmap getAlphaSafeBitmap(BitmapPool bitmapPool, Bitmap bitmap) {
        Bitmap.Config alphaSafeConfig = getAlphaSafeConfig(bitmap);
        if (alphaSafeConfig.equals(bitmap.getConfig())) {
            return bitmap;
        }
        Bitmap bitmap2 = bitmapPool.get(bitmap.getWidth(), bitmap.getHeight(), alphaSafeConfig);
        new Canvas(bitmap2).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return bitmap2;
    }

    private static final class NoLock implements Lock {
        @Override // java.util.concurrent.locks.Lock
        public void lock() {
        }

        @Override // java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock() {
            return true;
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
            return true;
        }

        @Override // java.util.concurrent.locks.Lock
        public void unlock() {
        }

        NoLock() {
        }

        @Override // java.util.concurrent.locks.Lock
        public Condition newCondition() {
            throw new UnsupportedOperationException("Should not be called");
        }
    }
}
