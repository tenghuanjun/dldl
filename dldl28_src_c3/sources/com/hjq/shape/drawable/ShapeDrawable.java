package com.hjq.shape.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import com.bun.miitmdid.x$;
import com.google.android.flexbox.FlexItem;
import kotlin.KotlinVersion;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ShapeDrawable extends Drawable {
    private int mAlpha;
    private ColorFilter mColorFilter;
    private boolean mDither;
    private Paint mLayerPaint;
    private int mLayoutDirection;
    private boolean mMutated;
    private Rect mPadding;
    private final Path mPath;
    private boolean mPathDirty;
    private final RectF mRect;
    private boolean mRectDirty;
    private Path mRingPath;
    private Paint mShadowPaint;
    private final Path mShadowPath;
    private final RectF mShadowRect;
    private ShapeState mShapeState;
    private final Paint mSolidPaint;
    private final Paint mStrokePaint;

    public ShapeDrawable() {
        this(new ShapeState());
    }

    public ShapeDrawable(ShapeState shapeState) {
        this.mSolidPaint = new Paint(1);
        Paint paint = new Paint(1);
        this.mStrokePaint = paint;
        this.mAlpha = KotlinVersion.MAX_COMPONENT_VALUE;
        this.mPath = new Path();
        this.mRect = new RectF();
        this.mShadowRect = new RectF();
        this.mShadowPath = new Path();
        this.mPathDirty = true;
        this.mShapeState = shapeState;
        initializeWithState(shapeState);
        this.mRectDirty = true;
        this.mMutated = false;
        paint.setStyle(Paint.Style.STROKE);
    }

    public ShapeState getShapeState() {
        return this.mShapeState;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.mPadding;
        if (rect2 != null) {
            rect.set(rect2);
            return true;
        }
        return super.getPadding(rect);
    }

    public ShapeDrawable setPadding(int i, int i2, int i3, int i4) {
        return setPadding(new Rect(i, i2, i3, i4));
    }

    public ShapeDrawable setPadding(Rect rect) {
        this.mPadding = rect;
        this.mPathDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setType(int i) {
        this.mRingPath = null;
        this.mShapeState.setType(i);
        this.mPathDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setWidth(int i) {
        this.mShapeState.width = i;
        this.mPathDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setHeight(int i) {
        this.mShapeState.height = i;
        this.mPathDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setRadius(float f) {
        this.mShapeState.setCornerRadius(f);
        this.mPathDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setRadius(float f, float f2, float f3, float f4) {
        if (f == f2 && f == f3 && f == f4) {
            return setRadius(f);
        }
        this.mShapeState.setCornerRadii(new float[]{f, f, f2, f2, f4, f4, f3, f3});
        this.mPathDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setSolidColor(int i, int i2) {
        return setSolidColor(i, i2);
    }

    public ShapeDrawable setSolidColor(int i, int i2, int i3) {
        return setSolidColor(i, i2, i3);
    }

    public ShapeDrawable setSolidColor(int... iArr) {
        this.mShapeState.setSolidColor(iArr);
        if (iArr == null) {
            this.mSolidPaint.setColor(0);
        } else if (iArr.length == 1) {
            this.mSolidPaint.setColor(iArr[0]);
            this.mSolidPaint.setMaskFilter(null);
        }
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setSolidGradientType(int i) {
        this.mShapeState.setSolidGradientType(i);
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setSolidGradientOrientation(ShapeGradientOrientation shapeGradientOrientation) {
        this.mShapeState.solidGradientOrientation = shapeGradientOrientation;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setSolidGradientCenterX(float f) {
        this.mShapeState.solidCenterX = f;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setSolidGradientCenterY(float f) {
        this.mShapeState.solidCenterY = f;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setSolidGradientRadius(float f) {
        this.mShapeState.gradientRadius = f;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setStrokeColor(int i, int i2) {
        return setStrokeColor(i, i2);
    }

    public ShapeDrawable setStrokeColor(int i, int i2, int i3) {
        return setStrokeColor(i, i2, i3);
    }

    public ShapeDrawable setStrokeColor(int... iArr) {
        this.mShapeState.setStrokeColor(iArr);
        if (iArr == null) {
            this.mStrokePaint.setColor(0);
        } else if (iArr.length == 1) {
            this.mStrokePaint.setColor(iArr[0]);
            this.mStrokePaint.setMaskFilter(null);
        }
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setStrokeGradientOrientation(ShapeGradientOrientation shapeGradientOrientation) {
        this.mShapeState.strokeGradientOrientation = shapeGradientOrientation;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setStrokeSize(int i) {
        this.mShapeState.setStrokeSize(i);
        this.mStrokePaint.setStrokeWidth(i);
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setStrokeDashSize(float f) {
        this.mShapeState.strokeDashSize = f;
        this.mStrokePaint.setPathEffect(f > 0.0f ? new DashPathEffect(new float[]{f, this.mShapeState.strokeDashGap}, 0.0f) : null);
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setStrokeDashGap(float f) {
        this.mShapeState.strokeDashGap = f;
        this.mStrokePaint.setPathEffect(this.mShapeState.strokeDashSize > 0.0f ? new DashPathEffect(new float[]{this.mShapeState.strokeDashSize, f}, 0.0f) : null);
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setUseLevel(boolean z) {
        this.mShapeState.useLevel = z;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setShadowColor(int i) {
        this.mShapeState.shadowColor = i;
        this.mPathDirty = true;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setShadowSize(int i) {
        this.mShapeState.shadowSize = i;
        this.mPathDirty = true;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setShadowOffsetX(int i) {
        this.mShapeState.shadowOffsetX = i;
        this.mPathDirty = true;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setShadowOffsetY(int i) {
        this.mShapeState.shadowOffsetY = i;
        this.mPathDirty = true;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setRingInnerRadiusSize(int i) {
        this.mShapeState.ringInnerRadiusSize = i;
        this.mShapeState.ringInnerRadiusRatio = 0.0f;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setRingInnerRadiusRatio(float f) {
        this.mShapeState.ringInnerRadiusRatio = f;
        this.mShapeState.ringInnerRadiusSize = -1;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setRingThicknessSize(int i) {
        this.mShapeState.ringThicknessSize = i;
        this.mShapeState.ringThicknessRatio = 0.0f;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setRingThicknessRatio(float f) {
        this.mShapeState.ringThicknessRatio = f;
        this.mShapeState.ringThicknessSize = -1;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setLineGravity(int i) {
        this.mShapeState.lineGravity = i;
        this.mRectDirty = true;
        invalidateSelf();
        return this;
    }

    public void intoBackground(View view) {
        if (this.mShapeState.strokeDashGap > 0.0f || this.mShapeState.shadowSize > 0) {
            view.setLayerType(1, null);
        }
        view.setBackground(this);
        int layoutDirection = view.getLayoutDirection();
        if (Build.VERSION.SDK_INT >= 23) {
            x$.ExternalSyntheticApiModelOutline0.m(this, layoutDirection);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x01dc  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r20) {
        /*
            Method dump skipped, instruction units count: 727
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hjq.shape.drawable.ShapeDrawable.draw(android.graphics.Canvas):void");
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        this.mLayoutDirection = i;
        return this.mShapeState.shapeType == 2;
    }

    private int modulateAlpha(int i) {
        int i2 = this.mAlpha;
        return (i * (i2 + (i2 >> 7))) >> 8;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mShapeState.changingConfigurations;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        if (z != this.mDither) {
            this.mDither = z;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.mColorFilter) {
            this.mColorFilter = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mShapeState.opaque ? -1 : -3;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mRingPath = null;
        this.mPathDirty = true;
        this.mRectDirty = true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        super.onLevelChange(i);
        this.mRectDirty = true;
        this.mPathDirty = true;
        invalidateSelf();
        return true;
    }

    private Path buildRing(ShapeState shapeState) {
        float fWidth;
        float fWidth2;
        if (this.mRingPath != null && (!shapeState.useLevelForShape || !this.mPathDirty)) {
            return this.mRingPath;
        }
        this.mPathDirty = false;
        float level = shapeState.useLevelForShape ? (getLevel() * 360.0f) / 10000.0f : 360.0f;
        RectF rectF = new RectF(this.mRect);
        float fWidth3 = rectF.width() / 2.0f;
        float fHeight = rectF.height() / 2.0f;
        if (shapeState.ringThicknessSize != -1) {
            fWidth = shapeState.ringThicknessSize;
        } else {
            float f = shapeState.ringThicknessRatio;
            if (f <= 0.0f) {
                f = 9.0f;
            }
            fWidth = rectF.width() / f;
        }
        if (shapeState.ringInnerRadiusSize != -1) {
            fWidth2 = shapeState.ringInnerRadiusSize;
        } else {
            float f2 = shapeState.ringInnerRadiusRatio;
            if (f2 <= 0.0f) {
                f2 = 3.0f;
            }
            fWidth2 = rectF.width() / f2;
        }
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(fWidth3 - fWidth2, fHeight - fWidth2);
        RectF rectF3 = new RectF(rectF2);
        float f3 = -fWidth;
        rectF3.inset(f3, f3);
        Path path = this.mRingPath;
        if (path == null) {
            this.mRingPath = new Path();
        } else {
            path.reset();
        }
        Path path2 = this.mRingPath;
        if (level < 360.0f && level > -360.0f) {
            path2.setFillType(Path.FillType.EVEN_ODD);
            float f4 = fWidth3 + fWidth2;
            path2.moveTo(f4, fHeight);
            path2.lineTo(f4 + fWidth, fHeight);
            path2.arcTo(rectF3, 0.0f, level, false);
            path2.arcTo(rectF2, level, -level, false);
            path2.close();
        } else {
            path2.addOval(rectF3, Path.Direction.CW);
            path2.addOval(rectF2, Path.Direction.CCW);
        }
        return path2;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x018a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean ensureValidRect() {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hjq.shape.drawable.ShapeDrawable.ensureValidRect():boolean");
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mShapeState.width;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mShapeState.height;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        this.mShapeState.changingConfigurations = getChangingConfigurations();
        return this.mShapeState;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            ShapeState shapeState = new ShapeState(this.mShapeState);
            this.mShapeState = shapeState;
            initializeWithState(shapeState);
            this.mMutated = true;
        }
        return this;
    }

    private void initializeWithState(ShapeState shapeState) {
        if (shapeState.hasSolidColor) {
            this.mSolidPaint.setColor(shapeState.solidColor);
        } else if (shapeState.solidColors == null) {
            this.mSolidPaint.setColor(0);
        } else {
            this.mSolidPaint.setColor(-16777216);
        }
        this.mPadding = shapeState.padding;
        if (shapeState.strokeSize >= 0) {
            if (shapeState.hasStrokeColor) {
                setStrokeColor(shapeState.strokeColor);
            } else {
                setStrokeColor(shapeState.strokeColors);
            }
            setStrokeSize(shapeState.strokeSize);
            setStrokeDashSize(shapeState.strokeDashSize);
            setStrokeDashGap(shapeState.strokeDashGap);
        }
    }

    public static int[] reverseArray(int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            iArr2[i] = iArr[(length - 1) - i];
        }
        return iArr2;
    }

    static void saveCanvasLayer(Canvas canvas, float f, float f2, float f3, float f4, Paint paint) {
        canvas.saveLayer(f, f2, f3, f4, paint);
    }

    /* JADX INFO: renamed from: com.hjq.shape.drawable.ShapeDrawable$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation;

        static {
            int[] iArr = new int[ShapeGradientOrientation.values().length];
            $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation = iArr;
            try {
                iArr[ShapeGradientOrientation.START_TO_END.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.END_TO_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.TOP_START_TO_BOTTOM_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.TOP_END_TO_BOTTOM_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.BOTTOM_START_TO_TOP_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.BOTTOM_END_TO_TOP_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.TOP_TO_BOTTOM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.TOP_RIGHT_TO_BOTTOM_LEFT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.RIGHT_TO_LEFT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.BOTTOM_RIGHT_TO_TOP_LEFT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.BOTTOM_TO_TOP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.BOTTOM_LEFT_TO_TOP_RIGHT.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.LEFT_TO_RIGHT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[ShapeGradientOrientation.TOP_LEFT_TO_BOTTOM_RIGHT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    static float[] computeLinearGradientCoordinate(int i, RectF rectF, float f, ShapeGradientOrientation shapeGradientOrientation) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        switch (AnonymousClass1.$SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation[shapeGradientOrientation.ordinal()]) {
            case 1:
                return computeLinearGradientCoordinate(i, rectF, f, i == 1 ? ShapeGradientOrientation.RIGHT_TO_LEFT : ShapeGradientOrientation.LEFT_TO_RIGHT);
            case 2:
                return computeLinearGradientCoordinate(i, rectF, f, i == 1 ? ShapeGradientOrientation.LEFT_TO_RIGHT : ShapeGradientOrientation.RIGHT_TO_LEFT);
            case 3:
                return computeLinearGradientCoordinate(i, rectF, f, i == 1 ? ShapeGradientOrientation.TOP_RIGHT_TO_BOTTOM_LEFT : ShapeGradientOrientation.TOP_LEFT_TO_BOTTOM_RIGHT);
            case 4:
                return computeLinearGradientCoordinate(i, rectF, f, i == 1 ? ShapeGradientOrientation.TOP_LEFT_TO_BOTTOM_RIGHT : ShapeGradientOrientation.TOP_RIGHT_TO_BOTTOM_LEFT);
            case 5:
                return computeLinearGradientCoordinate(i, rectF, f, i == 1 ? ShapeGradientOrientation.BOTTOM_RIGHT_TO_TOP_LEFT : ShapeGradientOrientation.BOTTOM_LEFT_TO_TOP_RIGHT);
            case 6:
                return computeLinearGradientCoordinate(i, rectF, f, i == 1 ? ShapeGradientOrientation.BOTTOM_LEFT_TO_TOP_RIGHT : ShapeGradientOrientation.BOTTOM_RIGHT_TO_TOP_LEFT);
            case 7:
                f2 = rectF.left;
                f3 = rectF.top;
                f4 = rectF.bottom;
                f8 = f4 * f;
                f9 = f2;
                return new float[]{f2, f3, f9, f8};
            case 8:
                f2 = rectF.right;
                f3 = rectF.top;
                f5 = rectF.left * f;
                f6 = rectF.bottom;
                f8 = f6 * f;
                f9 = f5;
                return new float[]{f2, f3, f9, f8};
            case 9:
                f2 = rectF.right;
                f3 = rectF.top;
                f7 = rectF.left;
                f9 = f * f7;
                f8 = f3;
                return new float[]{f2, f3, f9, f8};
            case 10:
                f2 = rectF.right;
                f3 = rectF.bottom;
                f5 = rectF.left * f;
                f6 = rectF.top;
                f8 = f6 * f;
                f9 = f5;
                return new float[]{f2, f3, f9, f8};
            case 11:
                f2 = rectF.left;
                f3 = rectF.bottom;
                f4 = rectF.top;
                f8 = f4 * f;
                f9 = f2;
                return new float[]{f2, f3, f9, f8};
            case 12:
                f2 = rectF.left;
                f3 = rectF.bottom;
                f5 = rectF.right * f;
                f6 = rectF.top;
                f8 = f6 * f;
                f9 = f5;
                return new float[]{f2, f3, f9, f8};
            case 13:
                f2 = rectF.left;
                f3 = rectF.top;
                f7 = rectF.right;
                f9 = f * f7;
                f8 = f3;
                return new float[]{f2, f3, f9, f8};
            default:
                f2 = rectF.left;
                f3 = rectF.top;
                f5 = rectF.right * f;
                f6 = rectF.bottom;
                f8 = f6 * f;
                f9 = f5;
                return new float[]{f2, f3, f9, f8};
        }
    }

    public static int setColorAlphaComponent(int i, int i2) {
        if (i2 < 0 || i2 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i & FlexItem.MAX_SIZE) | (i2 << 24);
    }
}
