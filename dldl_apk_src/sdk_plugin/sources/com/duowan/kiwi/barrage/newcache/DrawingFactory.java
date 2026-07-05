package com.duowan.kiwi.barrage.newcache;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import com.duowan.ark.util.DensityUtil;
import com.duowan.kiwi.barrage.GunPowder;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageContext;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DrawingFactory<CONTENT> {
    private final BarrageBitmapManager mBitmapManager;
    private final BuildMachine<CONTENT> mBuildMachine;
    private final AbsDrawingCacheManager<CONTENT> mCacheManager;
    private final Canvas mCanvas = new Canvas();
    private final int mCharHeight;
    private final int mCharWidth;
    private final TextPaint mTextPaint;

    public interface BuildMachine<CONTENT> {
        AbsDrawingCache<CONTENT> createDrawingCache(Bitmap bitmap);
    }

    public DrawingFactory(TextPaint textPaint, int i, int i2, BuildMachine<CONTENT> buildMachine) {
        BarrageBitmapManager barrageBitmapManager = new BarrageBitmapManager();
        this.mBitmapManager = barrageBitmapManager;
        this.mTextPaint = textPaint;
        this.mCharHeight = i2;
        this.mCharWidth = i;
        this.mBuildMachine = buildMachine;
        this.mCacheManager = new SimpleDrawingCacheManager(barrageBitmapManager);
    }

    public AbsDrawingCache<CONTENT> draw(GunPowder gunPowder) {
        int characteristic = gunPowder.getCharacteristic();
        AbsDrawingCache<CONTENT> cache = this.mCacheManager.getCache(characteristic);
        if (cache != null) {
            return cache;
        }
        Bitmap bitmapCreateNewDrawing = createNewDrawing(gunPowder);
        AbsDrawingCache<CONTENT> absDrawingCacheCreateDrawingCache = this.mBuildMachine.createDrawingCache(bitmapCreateNewDrawing);
        this.mCacheManager.add2Cache(characteristic, absDrawingCacheCreateDrawingCache);
        if (!absDrawingCacheCreateDrawingCache.isHoldingBitmap()) {
            this.mBitmapManager.recycle(bitmapCreateNewDrawing);
        }
        return absDrawingCacheCreateDrawingCache;
    }

    private Bitmap createNewDrawing(GunPowder gunPowder) {
        return gunPowder.mDirection == 0 ? horizontalBullet(gunPowder) : verticalBullet(gunPowder);
    }

    private Bitmap verticalBullet(GunPowder gunPowder) {
        int i = this.mCharWidth;
        int length = this.mCharHeight * gunPowder.mPowder.length();
        if (length <= 0) {
            length = 1;
        }
        float f = (-this.mTextPaint.ascent()) + 0.5f;
        Bitmap bitmap = this.mBitmapManager.get(i, length);
        int i2 = 0;
        bitmap.eraseColor(0);
        this.mCanvas.setBitmap(bitmap);
        if (2 == gunPowder.mExplosive) {
            drawBorder(this.mCanvas, this.mTextPaint, i, length);
        }
        this.mTextPaint.setColor(gunPowder.mColor);
        while (i2 < gunPowder.mPowder.length()) {
            int i3 = i2 + 1;
            this.mCanvas.drawText(gunPowder.mPowder.substring(i2, i3), 0.0f, (i2 * this.mCharHeight) + f, this.mTextPaint);
            i2 = i3;
        }
        return bitmap;
    }

    private Bitmap horizontalBullet(GunPowder gunPowder) {
        int iMeasureText = ((int) this.mTextPaint.measureText(gunPowder.mPowder)) + 1;
        int iDip2px = iMeasureText > 0 ? iMeasureText : 1;
        if (2 == gunPowder.mExplosive) {
            iDip2px += DensityUtil.dip2px(BarrageContext.gContext, 3.5f);
        }
        int i = this.mCharHeight;
        Bitmap bitmap = this.mBitmapManager.get(iDip2px, i);
        bitmap.eraseColor(0);
        this.mCanvas.setBitmap(bitmap);
        if (2 == gunPowder.mExplosive) {
            drawBorder(this.mCanvas, this.mTextPaint, iDip2px, i);
        }
        this.mTextPaint.setColor(gunPowder.mColor);
        this.mCanvas.drawText(gunPowder.mPowder, 2.0f, (-this.mTextPaint.ascent()) + 2.5f, this.mTextPaint);
        return bitmap;
    }

    private void drawBorder(Canvas canvas, TextPaint textPaint, int i, int i2) {
        Paint.Style style = textPaint.getStyle();
        float strokeWidth = textPaint.getStrokeWidth();
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(DensityUtil.dip2px(BarrageContext.gContext, 2.0f));
        textPaint.setColor(BarrageConfig.sBorderColor);
        canvas.drawRect(0.0f, 0.0f, i, i2, textPaint);
        textPaint.setStyle(style);
        textPaint.setStrokeWidth(strokeWidth);
    }
}
