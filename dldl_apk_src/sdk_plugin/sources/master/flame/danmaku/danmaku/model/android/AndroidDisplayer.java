package master.flame.danmaku.danmaku.model.android;

import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import com.alibaba.fastjson.asm.Opcodes;
import java.util.HashMap;
import java.util.Map;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.AlphaValue;
import master.flame.danmaku.danmaku.model.BaseDanmaku;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AndroidDisplayer extends AbsDisplayer<Canvas, Typeface> {
    public Canvas canvas;
    private int height;
    private float locationZ;
    private int width;
    private Camera camera = new Camera();
    private Matrix matrix = new Matrix();
    private final DisplayerConfig mDisplayConfig = new DisplayerConfig();
    private BaseCacheStuffer sStuffer = new SimpleTextCacheStuffer();
    private float density = 1.0f;
    private int densityDpi = Opcodes.IF_ICMPNE;
    private float scaledDensity = 1.0f;
    private int mSlopPixel = 0;
    private boolean mIsHardwareAccelerated = true;
    private int mMaximumBitmapWidth = 2048;
    private int mMaximumBitmapHeight = 2048;

    public static class DisplayerConfig {
        public static final int BORDER_WIDTH = 4;
        private Paint ALPHA_PAINT;
        private Paint BORDER_PAINT;
        public final TextPaint PAINT;
        public final TextPaint PAINT_DUPLICATE;
        private Paint UNDERLINE_PAINT;
        private boolean isTranslucent;
        private float sLastScaleTextSize;
        private final Map<Float, Float> sCachedScaleSize = new HashMap(10);
        public int UNDERLINE_HEIGHT = 4;
        private float SHADOW_RADIUS = 4.0f;
        private float STROKE_WIDTH = 3.5f;
        public float sProjectionOffsetX = 1.0f;
        public float sProjectionOffsetY = 1.0f;
        private int sProjectionAlpha = 204;
        public boolean CONFIG_HAS_SHADOW = false;
        private boolean HAS_SHADOW = false;
        public boolean CONFIG_HAS_STROKE = true;
        private boolean HAS_STROKE = true;
        public boolean CONFIG_HAS_PROJECTION = false;
        public boolean HAS_PROJECTION = false;
        public boolean CONFIG_ANTI_ALIAS = true;
        private boolean ANTI_ALIAS = true;
        private int transparency = AlphaValue.MAX;
        private float scaleTextSize = 1.0f;
        private boolean isTextScaled = false;
        private int margin = 0;
        private int allMarginTop = 0;

        public DisplayerConfig() {
            TextPaint textPaint = new TextPaint();
            this.PAINT = textPaint;
            textPaint.setStrokeWidth(this.STROKE_WIDTH);
            this.PAINT_DUPLICATE = new TextPaint(this.PAINT);
            this.ALPHA_PAINT = new Paint();
            Paint paint = new Paint();
            this.UNDERLINE_PAINT = paint;
            paint.setStrokeWidth(this.UNDERLINE_HEIGHT);
            this.UNDERLINE_PAINT.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.BORDER_PAINT = paint2;
            paint2.setStyle(Paint.Style.STROKE);
            this.BORDER_PAINT.setStrokeWidth(4.0f);
        }

        public void setTypeface(Typeface typeface) {
            this.PAINT.setTypeface(typeface);
        }

        public void setShadowRadius(float f) {
            this.SHADOW_RADIUS = f;
        }

        public void setStrokeWidth(float f) {
            this.PAINT.setStrokeWidth(f);
            this.STROKE_WIDTH = f;
        }

        public void setProjectionConfig(float f, float f2, int i) {
            if (this.sProjectionOffsetX == f && this.sProjectionOffsetY == f2 && this.sProjectionAlpha == i) {
                return;
            }
            if (f <= 1.0f) {
                f = 1.0f;
            }
            this.sProjectionOffsetX = f;
            if (f2 <= 1.0f) {
                f2 = 1.0f;
            }
            this.sProjectionOffsetY = f2;
            if (i < 0) {
                i = 0;
            } else if (i > 255) {
                i = 255;
            }
            this.sProjectionAlpha = i;
        }

        public void setFakeBoldText(boolean z) {
            this.PAINT.setFakeBoldText(z);
        }

        public void setTransparency(int i) {
            this.isTranslucent = i != AlphaValue.MAX;
            this.transparency = i;
        }

        public void setScaleTextSizeFactor(float f) {
            this.isTextScaled = f != 1.0f;
            this.scaleTextSize = f;
        }

        private void applyTextScaleConfig(BaseDanmaku baseDanmaku, Paint paint) {
            if (this.isTextScaled) {
                Float fValueOf = this.sCachedScaleSize.get(Float.valueOf(baseDanmaku.textSize));
                if (fValueOf == null || this.sLastScaleTextSize != this.scaleTextSize) {
                    this.sLastScaleTextSize = this.scaleTextSize;
                    fValueOf = Float.valueOf(baseDanmaku.textSize * this.scaleTextSize);
                    this.sCachedScaleSize.put(Float.valueOf(baseDanmaku.textSize), fValueOf);
                }
                paint.setTextSize(fValueOf.floatValue());
            }
        }

        public boolean hasStroke(BaseDanmaku baseDanmaku) {
            return (this.HAS_STROKE || this.HAS_PROJECTION) && this.STROKE_WIDTH > 0.0f && baseDanmaku.textShadowColor != 0;
        }

        public Paint getBorderPaint(BaseDanmaku baseDanmaku) {
            this.BORDER_PAINT.setColor(baseDanmaku.borderColor);
            return this.BORDER_PAINT;
        }

        public Paint getUnderlinePaint(BaseDanmaku baseDanmaku) {
            this.UNDERLINE_PAINT.setColor(baseDanmaku.underlineColor);
            return this.UNDERLINE_PAINT;
        }

        public TextPaint getPaint(BaseDanmaku baseDanmaku, boolean z) {
            TextPaint textPaint;
            if (z) {
                textPaint = this.PAINT;
            } else {
                textPaint = this.PAINT_DUPLICATE;
                textPaint.set(this.PAINT);
            }
            textPaint.setTextSize(baseDanmaku.textSize);
            applyTextScaleConfig(baseDanmaku, textPaint);
            if (!this.HAS_SHADOW || this.SHADOW_RADIUS <= 0.0f || baseDanmaku.textShadowColor == 0) {
                textPaint.clearShadowLayer();
            } else {
                textPaint.setShadowLayer(this.SHADOW_RADIUS, 0.0f, 0.0f, baseDanmaku.textShadowColor);
            }
            textPaint.setAntiAlias(this.ANTI_ALIAS);
            return textPaint;
        }

        public void applyPaintConfig(BaseDanmaku baseDanmaku, Paint paint, boolean z) {
            if (this.isTranslucent) {
                if (z) {
                    paint.setStyle(this.HAS_PROJECTION ? Paint.Style.FILL : Paint.Style.FILL_AND_STROKE);
                    paint.setColor(baseDanmaku.textShadowColor & ViewCompat.MEASURED_SIZE_MASK);
                    paint.setAlpha(this.HAS_PROJECTION ? (int) (this.sProjectionAlpha * (this.transparency / AlphaValue.MAX)) : this.transparency);
                } else {
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(baseDanmaku.textColor & ViewCompat.MEASURED_SIZE_MASK);
                    paint.setAlpha(this.transparency);
                }
            } else if (z) {
                paint.setStyle(this.HAS_PROJECTION ? Paint.Style.FILL : Paint.Style.FILL_AND_STROKE);
                paint.setColor(baseDanmaku.textShadowColor & ViewCompat.MEASURED_SIZE_MASK);
                paint.setAlpha(this.HAS_PROJECTION ? this.sProjectionAlpha : AlphaValue.MAX);
            } else {
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(baseDanmaku.textColor & ViewCompat.MEASURED_SIZE_MASK);
                paint.setAlpha(AlphaValue.MAX);
            }
            if (baseDanmaku.getType() == 7) {
                paint.setAlpha(baseDanmaku.getAlpha());
            }
        }

        public void clearTextHeightCache() {
            this.sCachedScaleSize.clear();
        }

        public float getStrokeWidth() {
            if (this.HAS_SHADOW && this.HAS_STROKE) {
                return Math.max(this.SHADOW_RADIUS, this.STROKE_WIDTH);
            }
            if (this.HAS_SHADOW) {
                return this.SHADOW_RADIUS;
            }
            if (this.HAS_STROKE) {
                return this.STROKE_WIDTH;
            }
            return 0.0f;
        }

        public void definePaintParams(boolean z) {
            this.HAS_STROKE = this.CONFIG_HAS_STROKE;
            this.HAS_SHADOW = this.CONFIG_HAS_SHADOW;
            this.HAS_PROJECTION = this.CONFIG_HAS_PROJECTION;
            this.ANTI_ALIAS = this.CONFIG_ANTI_ALIAS;
        }
    }

    private static final int getMaximumBitmapWidth(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 14) {
            return canvas.getMaximumBitmapWidth();
        }
        return canvas.getWidth();
    }

    private static final int getMaximumBitmapHeight(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 14) {
            return canvas.getMaximumBitmapHeight();
        }
        return canvas.getHeight();
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setTypeFace(Typeface typeface) {
        this.mDisplayConfig.setTypeface(typeface);
    }

    public void setShadowRadius(float f) {
        this.mDisplayConfig.setShadowRadius(f);
    }

    public void setPaintStorkeWidth(float f) {
        this.mDisplayConfig.setStrokeWidth(f);
    }

    public void setProjectionConfig(float f, float f2, int i) {
        this.mDisplayConfig.setProjectionConfig(f, f2, i);
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setFakeBoldText(boolean z) {
        this.mDisplayConfig.setFakeBoldText(z);
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setTransparency(int i) {
        this.mDisplayConfig.setTransparency(i);
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setScaleTextSizeFactor(float f) {
        this.mDisplayConfig.setScaleTextSizeFactor(f);
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setCacheStuffer(BaseCacheStuffer baseCacheStuffer) {
        if (baseCacheStuffer != this.sStuffer) {
            this.sStuffer = baseCacheStuffer;
        }
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public BaseCacheStuffer getCacheStuffer() {
        return this.sStuffer;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void setMargin(int i) {
        this.mDisplayConfig.margin = i;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getMargin() {
        return this.mDisplayConfig.margin;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void setAllMarginTop(int i) {
        this.mDisplayConfig.allMarginTop = i;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getAllMarginTop() {
        return this.mDisplayConfig.allMarginTop;
    }

    private void update(Canvas canvas) {
        this.canvas = canvas;
        if (canvas != null) {
            this.width = canvas.getWidth();
            this.height = canvas.getHeight();
            if (this.mIsHardwareAccelerated) {
                this.mMaximumBitmapWidth = getMaximumBitmapWidth(canvas);
                this.mMaximumBitmapHeight = getMaximumBitmapHeight(canvas);
            }
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getWidth() {
        return this.width;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getHeight() {
        return this.height;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public float getDensity() {
        return this.density;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getDensityDpi() {
        return this.densityDpi;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int draw(BaseDanmaku baseDanmaku) {
        Paint paint;
        boolean z;
        boolean z2;
        float top = baseDanmaku.getTop();
        float left = baseDanmaku.getLeft();
        if (this.canvas == null) {
            return 0;
        }
        Paint paint2 = null;
        int i = 1;
        if (baseDanmaku.getType() != 7) {
            paint = null;
            z = false;
        } else {
            if (baseDanmaku.getAlpha() == AlphaValue.TRANSPARENT) {
                return 0;
            }
            if (baseDanmaku.rotationZ == 0.0f && baseDanmaku.rotationY == 0.0f) {
                z2 = false;
            } else {
                saveCanvas(baseDanmaku, this.canvas, left, top);
                z2 = true;
            }
            if (baseDanmaku.getAlpha() != AlphaValue.MAX) {
                paint2 = this.mDisplayConfig.ALPHA_PAINT;
                paint2.setAlpha(baseDanmaku.getAlpha());
            }
            paint = paint2;
            z = z2;
        }
        if (paint != null && paint.getAlpha() == AlphaValue.TRANSPARENT) {
            return 0;
        }
        if (!this.sStuffer.drawCache(baseDanmaku, this.canvas, left, top, paint, this.mDisplayConfig.PAINT)) {
            if (paint != null) {
                this.mDisplayConfig.PAINT.setAlpha(paint.getAlpha());
                this.mDisplayConfig.PAINT_DUPLICATE.setAlpha(paint.getAlpha());
            } else {
                resetPaintAlpha(this.mDisplayConfig.PAINT);
            }
            drawDanmaku(baseDanmaku, this.canvas, left, top, false);
            i = 2;
        }
        if (z) {
            restoreCanvas(this.canvas);
        }
        return i;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void recycle(BaseDanmaku baseDanmaku) {
        BaseCacheStuffer baseCacheStuffer = this.sStuffer;
        if (baseCacheStuffer != null) {
            baseCacheStuffer.releaseResource(baseDanmaku);
        }
    }

    private void resetPaintAlpha(Paint paint) {
        if (paint.getAlpha() != AlphaValue.MAX) {
            paint.setAlpha(AlphaValue.MAX);
        }
    }

    private void restoreCanvas(Canvas canvas) {
        canvas.restore();
    }

    private int saveCanvas(BaseDanmaku baseDanmaku, Canvas canvas, float f, float f2) {
        this.camera.save();
        if (this.locationZ != 0.0f && Build.VERSION.SDK_INT >= 12) {
            this.camera.setLocation(0.0f, 0.0f, this.locationZ);
        }
        this.camera.rotateY(-baseDanmaku.rotationY);
        this.camera.rotateZ(-baseDanmaku.rotationZ);
        this.camera.getMatrix(this.matrix);
        this.matrix.preTranslate(-f, -f2);
        this.matrix.postTranslate(f, f2);
        this.camera.restore();
        int iSave = canvas.save();
        canvas.concat(this.matrix);
        return iSave;
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public synchronized void drawDanmaku(BaseDanmaku baseDanmaku, Canvas canvas, float f, float f2, boolean z) {
        if (this.sStuffer != null) {
            this.sStuffer.drawDanmaku(baseDanmaku, canvas, f, f2, z, this.mDisplayConfig);
        }
    }

    private synchronized TextPaint getPaint(BaseDanmaku baseDanmaku, boolean z) {
        return this.mDisplayConfig.getPaint(baseDanmaku, z);
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void prepare(BaseDanmaku baseDanmaku, boolean z) {
        BaseCacheStuffer baseCacheStuffer = this.sStuffer;
        if (baseCacheStuffer != null) {
            baseCacheStuffer.prepare(baseDanmaku, z);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void measure(BaseDanmaku baseDanmaku, boolean z) {
        TextPaint paint = getPaint(baseDanmaku, z);
        if (this.mDisplayConfig.HAS_STROKE) {
            this.mDisplayConfig.applyPaintConfig(baseDanmaku, paint, true);
        }
        calcPaintWH(baseDanmaku, paint, z);
        if (this.mDisplayConfig.HAS_STROKE) {
            this.mDisplayConfig.applyPaintConfig(baseDanmaku, paint, false);
        }
    }

    private void calcPaintWH(BaseDanmaku baseDanmaku, TextPaint textPaint, boolean z) {
        this.sStuffer.measure(baseDanmaku, textPaint, z);
        setDanmakuPaintWidthAndHeight(baseDanmaku, baseDanmaku.paintWidth, baseDanmaku.paintHeight);
    }

    private void setDanmakuPaintWidthAndHeight(BaseDanmaku baseDanmaku, float f, float f2) {
        float f3 = f + (baseDanmaku.padding * 2);
        float f4 = f2 + (baseDanmaku.padding * 2);
        if (baseDanmaku.borderColor != 0) {
            float f5 = 8;
            f3 += f5;
            f4 += f5;
        }
        baseDanmaku.paintWidth = f3 + getStrokeWidth();
        baseDanmaku.paintHeight = f4;
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void clearTextHeightCache() {
        this.sStuffer.clearCaches();
        this.mDisplayConfig.clearTextHeightCache();
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public float getScaledDensity() {
        return this.scaledDensity;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void resetSlopPixel(float f) {
        float fMax = Math.max(f, getWidth() / 682.0f) * 25.0f;
        this.mSlopPixel = (int) fMax;
        if (f > 1.0f) {
            this.mSlopPixel = (int) (fMax * f);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getSlopPixel() {
        return this.mSlopPixel;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void setDensities(float f, int i, float f2) {
        this.density = f;
        this.densityDpi = i;
        this.scaledDensity = f2;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
        this.locationZ = (float) (((double) (i / 2.0f)) / Math.tan(0.4799655442984406d));
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void setDanmakuStyle(int i, float[] fArr) {
        if (i != -1) {
            if (i == 0) {
                this.mDisplayConfig.CONFIG_HAS_SHADOW = false;
                this.mDisplayConfig.CONFIG_HAS_STROKE = false;
                this.mDisplayConfig.CONFIG_HAS_PROJECTION = false;
                return;
            } else {
                if (i == 1) {
                    this.mDisplayConfig.CONFIG_HAS_SHADOW = true;
                    this.mDisplayConfig.CONFIG_HAS_STROKE = false;
                    this.mDisplayConfig.CONFIG_HAS_PROJECTION = false;
                    setShadowRadius(fArr[0]);
                    return;
                }
                if (i != 2) {
                    if (i != 3) {
                        return;
                    }
                    this.mDisplayConfig.CONFIG_HAS_SHADOW = false;
                    this.mDisplayConfig.CONFIG_HAS_STROKE = false;
                    this.mDisplayConfig.CONFIG_HAS_PROJECTION = true;
                    setProjectionConfig(fArr[0], fArr[1], (int) fArr[2]);
                    return;
                }
            }
        }
        this.mDisplayConfig.CONFIG_HAS_SHADOW = false;
        this.mDisplayConfig.CONFIG_HAS_STROKE = true;
        this.mDisplayConfig.CONFIG_HAS_PROJECTION = false;
        setPaintStorkeWidth(fArr[0]);
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setExtraData(Canvas canvas) {
        update(canvas);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public Canvas getExtraData() {
        return this.canvas;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public float getStrokeWidth() {
        return this.mDisplayConfig.getStrokeWidth();
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void setHardwareAccelerated(boolean z) {
        this.mIsHardwareAccelerated = z;
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer, master.flame.danmaku.danmaku.model.IDisplayer
    public boolean isHardwareAccelerated() {
        return this.mIsHardwareAccelerated;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getMaximumCacheWidth() {
        return this.mMaximumBitmapWidth;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getMaximumCacheHeight() {
        return this.mMaximumBitmapHeight;
    }
}
