package com.duowan.kiwi.barrage.render.draw;

import android.graphics.Paint;
import android.text.TextPaint;
import com.duowan.kiwi.barrage.GunPowder;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.newcache.AbsDrawingCache;
import com.duowan.kiwi.barrage.newcache.DrawingFactory;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BulletBuilder<CONTENT> {
    private static int mCharHeight;
    private final DrawingFactory<CONTENT> mFireworkFactory;
    private TextPaint mTextPaint = new TextPaint();
    private int mCharWidth = 0;
    private int[] mCharWidthEnglish = new int[WorkQueueKt.MASK];

    public BulletBuilder(int i, int i2, DrawingFactory.BuildMachine<CONTENT> buildMachine) {
        initPainter(i, i2);
        this.mFireworkFactory = new DrawingFactory<>(this.mTextPaint, this.mCharWidth, mCharHeight, buildMachine);
    }

    private void initPainter(int i, int i2) {
        float f = i;
        this.mTextPaint.setTextSize(f);
        this.mTextPaint.setColor(BarrageConfig.DefaultColor);
        this.mTextPaint.setTextAlign(Paint.Align.LEFT);
        if (BarrageConfig.sEnbaleStroke) {
            this.mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            this.mTextPaint.setStrokeWidth(1.0f);
        }
        if (BarrageConfig.sEnableBold) {
            this.mTextPaint.setFakeBoldText(true);
        }
        if (BarrageConfig.sEnableShadow) {
            this.mTextPaint.setShadowLayer(i2, BarrageConfig.ShadowDX, BarrageConfig.ShadowDY, BarrageConfig.ShadowColor);
        }
        this.mTextPaint.setAntiAlias(true);
        int iMeasureText = (int) this.mTextPaint.measureText("中");
        this.mCharWidth = iMeasureText;
        if (iMeasureText <= 0) {
            BarrageLog.error("barrage_shell_builder", "char width %d error,set to %d ", Integer.valueOf(iMeasureText), Integer.valueOf(i));
            this.mCharWidth = i;
        }
        for (int i3 = 0; i3 <= 126; i3++) {
            int iMeasureText2 = (int) this.mTextPaint.measureText(String.valueOf((char) i3));
            int[] iArr = this.mCharWidthEnglish;
            if (iMeasureText2 <= 0) {
                iMeasureText2 = (int) (0.875f * f);
            }
            iArr[i3] = iMeasureText2;
        }
        initBarrageHeight(this.mTextPaint);
    }

    public static int getDefaultBarrageHeight() {
        int i = mCharHeight;
        if (i > 0) {
            return i;
        }
        Paint paint = new Paint(1);
        paint.setTextSize(BarrageConfig.sBaseLandscapeSize);
        initBarrageHeight(paint);
        return mCharHeight;
    }

    private static void initBarrageHeight(Paint paint) {
        int iDescent = (int) ((-paint.ascent()) + 0.5f + paint.descent() + 0.5f + BarrageConfig.LANDSCAPE_LINE_SPACE_INNER);
        mCharHeight = iDescent;
        if (iDescent <= 0) {
            int i = (int) ((BarrageConfig.sBaseLandscapeSize * 1.1f) + 1.0f);
            BarrageLog.error("barrage_shell_builder", "char height %d error,set to %d ", Integer.valueOf(mCharHeight), Integer.valueOf(i));
            mCharHeight = i;
        }
        BarrageLog.info("barrage_shell_builder", "mCharHeight = %d", Integer.valueOf(mCharHeight));
    }

    public Bullet<CONTENT> gunPowderToBullet(GunPowder gunPowder) {
        Bullet<CONTENT> bullet = new Bullet<>(gunPowder.mUid, gunPowder.mPowder, gunPowder.mNickName, gunPowder.mExplosive);
        ((Bullet) bullet).mDuration = gunPowder.mDuration;
        ((Bullet) bullet).mBeginTime = gunPowder.mBeginTime;
        if (gunPowder.mCacheObject != null) {
            bullet.setCacheObject(gunPowder.mCacheObject);
            return bullet;
        }
        AbsDrawingCache<CONTENT> absDrawingCacheDraw = this.mFireworkFactory.draw(gunPowder);
        if (absDrawingCacheDraw == null) {
            return null;
        }
        absDrawingCacheDraw.increaseReferenceCount();
        bullet.setCacheObject(absDrawingCacheDraw);
        return bullet;
    }

    public int[] getCharSize() {
        return new int[]{this.mCharWidth, mCharHeight};
    }

    public static class Bullet<CONTENT> {
        private float mBeginTime;
        private AbsDrawingCache<CONTENT> mCachePixels;
        private float mDuration;
        public int mExplosive;
        private String mNickName;
        private String mText;
        private long mUid;

        public Bullet(long j, String str, String str2, int i) {
            this.mUid = 0L;
            this.mUid = j;
            this.mText = str;
            this.mNickName = str2;
            this.mExplosive = i;
        }

        public AbsDrawingCache<CONTENT> getCacheObject() {
            return this.mCachePixels;
        }

        public int getPixelsWidth() {
            return this.mCachePixels.getWidth();
        }

        public int getPixelsHeight() {
            return this.mCachePixels.getHeight();
        }

        public long getUid() {
            return this.mUid;
        }

        public String getNickName() {
            return this.mNickName;
        }

        public String getText() {
            return this.mText;
        }

        public boolean hasPixels() {
            if (this.mCachePixels != null) {
                return true;
            }
            BarrageLog.error(BarrageConfig.TAG, "mCachePixels is null");
            return false;
        }

        public void setCacheObject(AbsDrawingCache absDrawingCache) {
            if (absDrawingCache == null || absDrawingCache.getContent() == null) {
                return;
            }
            this.mCachePixels = absDrawingCache;
        }

        public float getBeginTime() {
            return this.mBeginTime;
        }

        public float getDuration() {
            return this.mDuration;
        }
    }
}
