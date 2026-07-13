package com.hjq.shape.builder;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.hjq.shape.config.IShapeDrawableStyleable;
import com.hjq.shape.drawable.ShapeDrawable;
import com.hjq.shape.drawable.ShapeGradientOrientation;
import com.hjq.shape.other.ExtendStateListDrawable;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import com.volcengine.cloudphone.base.VeVideoFrame;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class ShapeDrawableBuilder {
    private static final int NO_COLOR = 0;
    private float mBottomLeftRadius;
    private float mBottomRightRadius;
    private int mHeight;
    private int mLineGravity;
    private float mRingInnerRadiusRatio;
    private int mRingInnerRadiusSize;
    private float mRingThicknessRatio;
    private int mRingThicknessSize;
    private int mShadowColor;
    private int mShadowOffsetX;
    private int mShadowOffsetY;
    private int mShadowSize;
    private Integer mSolidCheckedColor;
    private int mSolidColor;
    private Integer mSolidDisabledColor;
    private Integer mSolidFocusedColor;
    private float mSolidGradientCenterX;
    private float mSolidGradientCenterY;
    private int[] mSolidGradientColors;
    private ShapeGradientOrientation mSolidGradientOrientation;
    private int mSolidGradientRadius;
    private int mSolidGradientType;
    private Integer mSolidPressedColor;
    private Integer mSolidSelectedColor;
    private Integer mStrokeCheckedColor;
    private int mStrokeColor;
    private int mStrokeDashGap;
    private int mStrokeDashSize;
    private Integer mStrokeDisabledColor;
    private Integer mStrokeFocusedColor;
    private int[] mStrokeGradientColors;
    private ShapeGradientOrientation mStrokeGradientOrientation;
    private Integer mStrokePressedColor;
    private Integer mStrokeSelectedColor;
    private int mStrokeSize;
    private float mTopLeftRadius;
    private float mTopRightRadius;
    private int mType;
    private final View mView;
    private int mWidth;

    private int getDefaultGradientOrientation() {
        return 10;
    }

    public ShapeDrawableBuilder(View view, TypedArray typedArray, IShapeDrawableStyleable iShapeDrawableStyleable) {
        this.mView = view;
        this.mType = typedArray.getInt(iShapeDrawableStyleable.getShapeTypeStyleable(), 0);
        this.mWidth = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getShapeWidthStyleable(), -1);
        this.mHeight = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getShapeHeightStyleable(), -1);
        this.mSolidColor = typedArray.getColor(iShapeDrawableStyleable.getSolidColorStyleable(), 0);
        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidPressedColorStyleable())) {
            this.mSolidPressedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getSolidPressedColorStyleable(), 0));
        }
        if (iShapeDrawableStyleable.getSolidCheckedColorStyleable() > 0 && typedArray.hasValue(iShapeDrawableStyleable.getSolidCheckedColorStyleable())) {
            this.mSolidCheckedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getSolidCheckedColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidDisabledColorStyleable())) {
            this.mSolidDisabledColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getSolidDisabledColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidFocusedColorStyleable())) {
            this.mSolidFocusedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getSolidFocusedColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidSelectedColorStyleable())) {
            this.mSolidSelectedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getSolidSelectedColorStyleable(), 0));
        }
        int layoutDirection = getLayoutDirection(view);
        int dimensionPixelSize = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusStyleable(), 0);
        float f = dimensionPixelSize;
        this.mBottomRightRadius = f;
        this.mBottomLeftRadius = f;
        this.mTopRightRadius = f;
        this.mTopLeftRadius = f;
        if (typedArray.hasValue(iShapeDrawableStyleable.getRadiusInTopStartStyleable())) {
            if (layoutDirection == 1) {
                this.mTopRightRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInTopStartStyleable(), dimensionPixelSize);
            } else {
                this.mTopLeftRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInTopStartStyleable(), dimensionPixelSize);
            }
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getRadiusInTopEndStyleable())) {
            if (layoutDirection == 1) {
                this.mTopLeftRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInTopEndStyleable(), dimensionPixelSize);
            } else {
                this.mTopRightRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInTopEndStyleable(), dimensionPixelSize);
            }
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getRadiusInBottomStartStyleable())) {
            if (layoutDirection == 1) {
                this.mBottomRightRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInBottomStartStyleable(), dimensionPixelSize);
            } else {
                this.mBottomLeftRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInBottomStartStyleable(), dimensionPixelSize);
            }
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getRadiusInBottomEndStyleable())) {
            if (layoutDirection == 1) {
                this.mBottomLeftRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInBottomEndStyleable(), dimensionPixelSize);
            } else {
                this.mBottomRightRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInBottomEndStyleable(), dimensionPixelSize);
            }
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getRadiusInTopLeftStyleable())) {
            this.mTopLeftRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInTopLeftStyleable(), dimensionPixelSize);
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getRadiusInTopRightStyleable())) {
            this.mTopRightRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInTopRightStyleable(), dimensionPixelSize);
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getRadiusInBottomLeftStyleable())) {
            this.mBottomLeftRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInBottomLeftStyleable(), dimensionPixelSize);
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getRadiusInBottomRightStyleable())) {
            this.mBottomRightRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusInBottomRightStyleable(), dimensionPixelSize);
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidGradientStartColorStyleable()) && typedArray.hasValue(iShapeDrawableStyleable.getSolidGradientEndColorStyleable())) {
            if (typedArray.hasValue(iShapeDrawableStyleable.getSolidGradientCenterColorStyleable())) {
                this.mSolidGradientColors = new int[]{typedArray.getColor(iShapeDrawableStyleable.getSolidGradientStartColorStyleable(), 0), typedArray.getColor(iShapeDrawableStyleable.getSolidGradientCenterColorStyleable(), 0), typedArray.getColor(iShapeDrawableStyleable.getSolidGradientEndColorStyleable(), 0)};
            } else {
                this.mSolidGradientColors = new int[]{typedArray.getColor(iShapeDrawableStyleable.getSolidGradientStartColorStyleable(), 0), typedArray.getColor(iShapeDrawableStyleable.getSolidGradientEndColorStyleable(), 0)};
            }
        }
        this.mSolidGradientOrientation = transformGradientOrientation(typedArray.getInt(iShapeDrawableStyleable.getSolidGradientOrientationStyleable(), getDefaultGradientOrientation()));
        this.mSolidGradientType = typedArray.getInt(iShapeDrawableStyleable.getSolidGradientTypeStyleable(), 0);
        this.mSolidGradientCenterX = typedArray.getFloat(iShapeDrawableStyleable.getSolidGradientCenterXStyleable(), 0.5f);
        this.mSolidGradientCenterY = typedArray.getFloat(iShapeDrawableStyleable.getSolidGradientCenterYStyleable(), 0.5f);
        this.mSolidGradientRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getSolidGradientRadiusStyleable(), dimensionPixelSize);
        this.mStrokeColor = typedArray.getColor(iShapeDrawableStyleable.getStrokeColorStyleable(), 0);
        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokePressedColorStyleable())) {
            this.mStrokePressedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getStrokePressedColorStyleable(), 0));
        }
        if (iShapeDrawableStyleable.getStrokeCheckedColorStyleable() > 0 && typedArray.hasValue(iShapeDrawableStyleable.getStrokeCheckedColorStyleable())) {
            this.mStrokeCheckedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getStrokeCheckedColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeDisabledColorStyleable())) {
            this.mStrokeDisabledColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getStrokeDisabledColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeFocusedColorStyleable())) {
            this.mStrokeFocusedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getStrokeFocusedColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeSelectedColorStyleable())) {
            this.mStrokeSelectedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getStrokeSelectedColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeGradientStartColorStyleable()) && typedArray.hasValue(iShapeDrawableStyleable.getStrokeGradientEndColorStyleable())) {
            if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeGradientCenterColorStyleable())) {
                this.mStrokeGradientColors = new int[]{typedArray.getColor(iShapeDrawableStyleable.getStrokeGradientStartColorStyleable(), 0), typedArray.getColor(iShapeDrawableStyleable.getStrokeGradientCenterColorStyleable(), 0), typedArray.getColor(iShapeDrawableStyleable.getStrokeGradientEndColorStyleable(), 0)};
            } else {
                this.mStrokeGradientColors = new int[]{typedArray.getColor(iShapeDrawableStyleable.getStrokeGradientStartColorStyleable(), 0), typedArray.getColor(iShapeDrawableStyleable.getStrokeGradientEndColorStyleable(), 0)};
            }
        }
        this.mStrokeGradientOrientation = transformGradientOrientation(typedArray.getInt(iShapeDrawableStyleable.getStrokeGradientOrientationStyleable(), getDefaultGradientOrientation()));
        this.mStrokeSize = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getStrokeSizeStyleable(), 0);
        this.mStrokeDashSize = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getStrokeDashSizeStyleable(), 0);
        this.mStrokeDashGap = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getStrokeDashGapStyleable(), 0);
        this.mShadowSize = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getShadowSizeStyleable(), 0);
        this.mShadowColor = typedArray.getColor(iShapeDrawableStyleable.getShadowColorStyleable(), 268435456);
        this.mShadowOffsetX = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getShadowOffsetXStyleable(), 0);
        this.mShadowOffsetY = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getShadowOffsetYStyleable(), 0);
        this.mRingInnerRadiusSize = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getRingInnerRadiusSizeStyleable(), -1);
        this.mRingInnerRadiusRatio = typedArray.getFloat(iShapeDrawableStyleable.getRingInnerRadiusRatioStyleable(), 3.0f);
        this.mRingThicknessSize = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getRingThicknessSizeStyleable(), -1);
        this.mRingThicknessRatio = typedArray.getFloat(iShapeDrawableStyleable.getRingThicknessRatioStyleable(), 9.0f);
        this.mLineGravity = typedArray.getInt(iShapeDrawableStyleable.getLineGravityStyleable(), 17);
    }

    public ShapeDrawableBuilder setType(int i) {
        this.mType = i;
        return this;
    }

    public int getType() {
        return this.mType;
    }

    public ShapeDrawableBuilder setWidth(int i) {
        this.mWidth = i;
        return this;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public ShapeDrawableBuilder setHeight(int i) {
        this.mHeight = i;
        return this;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public ShapeDrawableBuilder setRadius(float f) {
        return setRadius(f, f, f, f);
    }

    public ShapeDrawableBuilder setRadius(float f, float f2, float f3, float f4) {
        this.mTopLeftRadius = f;
        this.mTopRightRadius = f2;
        this.mBottomLeftRadius = f3;
        this.mBottomRightRadius = f4;
        return this;
    }

    public ShapeDrawableBuilder setRadiusRelative(float f, float f2, float f3, float f4) {
        if (this.mView.getLayoutDirection() == 1) {
            this.mTopLeftRadius = f2;
            this.mTopRightRadius = f;
            this.mBottomLeftRadius = f4;
            this.mBottomRightRadius = f3;
        } else {
            this.mTopLeftRadius = f;
            this.mTopRightRadius = f2;
            this.mBottomLeftRadius = f3;
            this.mBottomRightRadius = f4;
        }
        return this;
    }

    public ShapeDrawableBuilder setTopLeftRadius(float f) {
        this.mTopLeftRadius = f;
        return this;
    }

    public float getTopLeftRadius() {
        return this.mTopLeftRadius;
    }

    public ShapeDrawableBuilder setTopRightRadius(float f) {
        this.mTopRightRadius = f;
        return this;
    }

    public float getTopRightRadius() {
        return this.mTopRightRadius;
    }

    public ShapeDrawableBuilder setBottomLeftRadius(float f) {
        this.mBottomLeftRadius = f;
        return this;
    }

    public float getBottomLeftRadius() {
        return this.mBottomLeftRadius;
    }

    public ShapeDrawableBuilder setBottomRightRadius(float f) {
        this.mBottomRightRadius = f;
        return this;
    }

    public float getBottomRightRadius() {
        return this.mBottomRightRadius;
    }

    public ShapeDrawableBuilder setSolidColor(int i) {
        this.mSolidColor = i;
        clearSolidGradientColors();
        return this;
    }

    public int getSolidColor() {
        return this.mSolidColor;
    }

    public ShapeDrawableBuilder setSolidPressedColor(Integer num) {
        this.mSolidPressedColor = num;
        return this;
    }

    public Integer getSolidPressedColor() {
        return this.mSolidPressedColor;
    }

    public ShapeDrawableBuilder setSolidCheckedColor(Integer num) {
        this.mSolidCheckedColor = num;
        return this;
    }

    public Integer getSolidCheckedColor() {
        return this.mSolidCheckedColor;
    }

    public ShapeDrawableBuilder setSolidDisabledColor(Integer num) {
        this.mSolidDisabledColor = num;
        return this;
    }

    public Integer getSolidDisabledColor() {
        return this.mSolidDisabledColor;
    }

    public ShapeDrawableBuilder setSolidFocusedColor(Integer num) {
        this.mSolidFocusedColor = num;
        return this;
    }

    public Integer getSolidFocusedColor() {
        return this.mSolidFocusedColor;
    }

    public ShapeDrawableBuilder setSolidSelectedColor(Integer num) {
        this.mSolidSelectedColor = num;
        return this;
    }

    public Integer getSolidSelectedColor() {
        return this.mSolidSelectedColor;
    }

    public ShapeDrawableBuilder setSolidGradientColors(int i, int i2) {
        return setSolidGradientColors(new int[]{i, i2});
    }

    public ShapeDrawableBuilder setSolidGradientColors(int i, int i2, int i3) {
        return setSolidGradientColors(new int[]{i, i2, i3});
    }

    public ShapeDrawableBuilder setSolidGradientColors(int[] iArr) {
        this.mSolidGradientColors = iArr;
        return this;
    }

    public int[] getSolidGradientColors() {
        return this.mSolidGradientColors;
    }

    public boolean isSolidGradientColorsEnable() {
        int[] iArr = this.mSolidGradientColors;
        return iArr != null && iArr.length > 0;
    }

    public void clearSolidGradientColors() {
        this.mSolidGradientColors = null;
    }

    public ShapeDrawableBuilder setSolidGradientOrientation(ShapeGradientOrientation shapeGradientOrientation) {
        this.mSolidGradientOrientation = shapeGradientOrientation;
        return this;
    }

    public ShapeGradientOrientation getSolidGradientOrientation() {
        return this.mSolidGradientOrientation;
    }

    public ShapeDrawableBuilder setSolidGradientType(int i) {
        this.mSolidGradientType = i;
        return this;
    }

    public int getSolidGradientType() {
        return this.mSolidGradientType;
    }

    public ShapeDrawableBuilder setSolidGradientCenterX(float f) {
        this.mSolidGradientCenterX = f;
        return this;
    }

    public float getSolidGradientCenterX() {
        return this.mSolidGradientCenterX;
    }

    public ShapeDrawableBuilder setSolidGradientCenterY(float f) {
        this.mSolidGradientCenterY = f;
        return this;
    }

    public float getSolidGradientCenterY() {
        return this.mSolidGradientCenterY;
    }

    public ShapeDrawableBuilder setSolidGradientRadius(int i) {
        this.mSolidGradientRadius = i;
        return this;
    }

    public int getSolidGradientRadius() {
        return this.mSolidGradientRadius;
    }

    public ShapeDrawableBuilder setStrokeColor(int i) {
        this.mStrokeColor = i;
        clearStrokeGradientColors();
        return this;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public ShapeDrawableBuilder setStrokePressedColor(Integer num) {
        this.mStrokePressedColor = num;
        return this;
    }

    public Integer getStrokePressedColor() {
        return this.mStrokePressedColor;
    }

    public ShapeDrawableBuilder setStrokeCheckedColor(Integer num) {
        this.mStrokeCheckedColor = num;
        return this;
    }

    public Integer getStrokeCheckedColor() {
        return this.mStrokeCheckedColor;
    }

    public ShapeDrawableBuilder setStrokeDisabledColor(Integer num) {
        this.mStrokeDisabledColor = num;
        return this;
    }

    public Integer getStrokeDisabledColor() {
        return this.mStrokeDisabledColor;
    }

    public ShapeDrawableBuilder setStrokeFocusedColor(Integer num) {
        this.mStrokeFocusedColor = num;
        return this;
    }

    public Integer getStrokeFocusedColor() {
        return this.mStrokeFocusedColor;
    }

    public ShapeDrawableBuilder setStrokeSelectedColor(Integer num) {
        this.mStrokeSelectedColor = num;
        return this;
    }

    public Integer getStrokeSelectedColor() {
        return this.mStrokeSelectedColor;
    }

    public ShapeDrawableBuilder setStrokeGradientColors(int i, int i2) {
        return setStrokeGradientColors(new int[]{i, i2});
    }

    public ShapeDrawableBuilder setStrokeGradientColors(int i, int i2, int i3) {
        return setStrokeGradientColors(new int[]{i, i2, i3});
    }

    public ShapeDrawableBuilder setStrokeGradientColors(int[] iArr) {
        this.mStrokeGradientColors = iArr;
        return this;
    }

    public int[] getStrokeGradientColors() {
        return this.mStrokeGradientColors;
    }

    public boolean isStrokeGradientColorsEnable() {
        int[] iArr = this.mStrokeGradientColors;
        return iArr != null && iArr.length > 0;
    }

    public void clearStrokeGradientColors() {
        this.mStrokeGradientColors = null;
    }

    public ShapeDrawableBuilder setStrokeGradientOrientation(ShapeGradientOrientation shapeGradientOrientation) {
        this.mStrokeGradientOrientation = shapeGradientOrientation;
        return this;
    }

    public ShapeGradientOrientation getStrokeGradientOrientation() {
        return this.mStrokeGradientOrientation;
    }

    public ShapeDrawableBuilder setStrokeSize(int i) {
        this.mStrokeSize = i;
        return this;
    }

    public int getStrokeSize() {
        return this.mStrokeSize;
    }

    public ShapeDrawableBuilder setStrokeDashSize(int i) {
        this.mStrokeDashSize = i;
        return this;
    }

    public int getStrokeDashSize() {
        return this.mStrokeDashSize;
    }

    public ShapeDrawableBuilder setStrokeDashGap(int i) {
        this.mStrokeDashGap = i;
        return this;
    }

    public int getStrokeDashGap() {
        return this.mStrokeDashGap;
    }

    public boolean isStrokeDashLineEnable() {
        return this.mStrokeDashGap > 0;
    }

    public ShapeDrawableBuilder setRingInnerRadiusSize(int i) {
        this.mRingInnerRadiusSize = i;
        return this;
    }

    public int getRingInnerRadiusSize() {
        return this.mRingInnerRadiusSize;
    }

    public ShapeDrawableBuilder setRingInnerRadiusRatio(float f) {
        this.mRingInnerRadiusRatio = f;
        return this;
    }

    public float getRingInnerRadiusRatio() {
        return this.mRingInnerRadiusRatio;
    }

    public ShapeDrawableBuilder setRingThicknessSize(int i) {
        this.mRingThicknessSize = i;
        return this;
    }

    public int getRingThicknessSize() {
        return this.mRingThicknessSize;
    }

    public ShapeDrawableBuilder setRingThicknessRatio(float f) {
        this.mRingThicknessRatio = f;
        return this;
    }

    public float getRingThicknessRatio() {
        return this.mRingThicknessRatio;
    }

    public boolean isShadowEnable() {
        return this.mShadowSize > 0;
    }

    public ShapeDrawableBuilder setShadowSize(int i) {
        this.mShadowSize = i;
        return this;
    }

    public int getShadowSize() {
        return this.mShadowSize;
    }

    public ShapeDrawableBuilder setShadowColor(int i) {
        this.mShadowColor = i;
        return this;
    }

    public int getShadowColor() {
        return this.mShadowColor;
    }

    public ShapeDrawableBuilder setShadowOffsetX(int i) {
        this.mShadowOffsetX = i;
        return this;
    }

    public int getShadowOffsetX() {
        return this.mShadowOffsetX;
    }

    public ShapeDrawableBuilder setShadowOffsetY(int i) {
        this.mShadowOffsetY = i;
        return this;
    }

    public int getShadowOffsetY() {
        return this.mShadowOffsetY;
    }

    public int getLineGravity() {
        return this.mLineGravity;
    }

    public ShapeDrawableBuilder setLineGravity(int i) {
        this.mLineGravity = i;
        return this;
    }

    public Drawable buildBackgroundDrawable() {
        ShapeDrawable shapeDrawableConvertShapeDrawable;
        boolean z = (this.mSolidPressedColor == null && this.mSolidCheckedColor == null && this.mSolidDisabledColor == null && this.mSolidFocusedColor == null && this.mSolidSelectedColor == null) ? false : true;
        boolean z2 = (this.mStrokePressedColor == null && this.mStrokeCheckedColor == null && this.mStrokeDisabledColor == null && this.mStrokeFocusedColor == null && this.mStrokeSelectedColor == null) ? false : true;
        if (!isSolidGradientColorsEnable() && !isStrokeGradientColorsEnable() && this.mSolidColor == 0 && !z && this.mStrokeColor == 0 && !z2) {
            return null;
        }
        Drawable background = this.mView.getBackground();
        if (background instanceof ExtendStateListDrawable) {
            shapeDrawableConvertShapeDrawable = convertShapeDrawable(((ExtendStateListDrawable) background).getDefaultDrawable());
        } else {
            shapeDrawableConvertShapeDrawable = convertShapeDrawable(background);
        }
        refreshShapeDrawable(shapeDrawableConvertShapeDrawable, null, null);
        if (!z && !z2) {
            return shapeDrawableConvertShapeDrawable;
        }
        ExtendStateListDrawable extendStateListDrawable = new ExtendStateListDrawable();
        if (this.mSolidPressedColor != null || this.mStrokePressedColor != null) {
            ShapeDrawable shapeDrawableConvertShapeDrawable2 = convertShapeDrawable(extendStateListDrawable.getPressedDrawable());
            refreshShapeDrawable(shapeDrawableConvertShapeDrawable2, this.mSolidPressedColor, this.mStrokePressedColor);
            extendStateListDrawable.setPressedDrawable(shapeDrawableConvertShapeDrawable2);
        }
        if (this.mSolidCheckedColor != null || this.mStrokeCheckedColor != null) {
            ShapeDrawable shapeDrawableConvertShapeDrawable3 = convertShapeDrawable(extendStateListDrawable.getCheckDrawable());
            refreshShapeDrawable(shapeDrawableConvertShapeDrawable3, this.mSolidCheckedColor, this.mStrokeCheckedColor);
            extendStateListDrawable.setCheckDrawable(shapeDrawableConvertShapeDrawable3);
        }
        if (this.mSolidDisabledColor != null || this.mStrokeDisabledColor != null) {
            ShapeDrawable shapeDrawableConvertShapeDrawable4 = convertShapeDrawable(extendStateListDrawable.getDisabledDrawable());
            refreshShapeDrawable(shapeDrawableConvertShapeDrawable4, this.mSolidDisabledColor, this.mStrokeDisabledColor);
            extendStateListDrawable.setDisabledDrawable(shapeDrawableConvertShapeDrawable4);
        }
        if (this.mSolidFocusedColor != null || this.mStrokeFocusedColor != null) {
            ShapeDrawable shapeDrawableConvertShapeDrawable5 = convertShapeDrawable(extendStateListDrawable.getFocusedDrawable());
            refreshShapeDrawable(shapeDrawableConvertShapeDrawable5, this.mSolidFocusedColor, this.mStrokeFocusedColor);
            extendStateListDrawable.setFocusedDrawable(shapeDrawableConvertShapeDrawable5);
        }
        if (this.mSolidSelectedColor != null || this.mStrokeSelectedColor != null) {
            ShapeDrawable shapeDrawableConvertShapeDrawable6 = convertShapeDrawable(extendStateListDrawable.getSelectDrawable());
            refreshShapeDrawable(shapeDrawableConvertShapeDrawable6, this.mSolidSelectedColor, this.mStrokeSelectedColor);
            extendStateListDrawable.setSelectDrawable(shapeDrawableConvertShapeDrawable6);
        }
        extendStateListDrawable.setDefaultDrawable(shapeDrawableConvertShapeDrawable);
        return extendStateListDrawable;
    }

    public void refreshShapeDrawable(ShapeDrawable shapeDrawable, Integer num, Integer num2) {
        shapeDrawable.setType(this.mType).setWidth(this.mWidth).setHeight(this.mHeight).setRadius(this.mTopLeftRadius, this.mTopRightRadius, this.mBottomLeftRadius, this.mBottomRightRadius);
        shapeDrawable.setSolidGradientType(this.mSolidGradientType).setSolidGradientOrientation(this.mSolidGradientOrientation).setSolidGradientRadius(this.mSolidGradientRadius).setSolidGradientCenterX(this.mSolidGradientCenterX).setSolidGradientCenterY(this.mSolidGradientCenterY);
        shapeDrawable.setStrokeGradientOrientation(this.mStrokeGradientOrientation).setStrokeSize(this.mStrokeSize).setStrokeDashSize(this.mStrokeDashSize).setStrokeDashGap(this.mStrokeDashGap);
        shapeDrawable.setShadowSize(this.mShadowSize).setShadowColor(this.mShadowColor).setShadowOffsetX(this.mShadowOffsetX).setShadowOffsetY(this.mShadowOffsetY);
        float f = this.mRingInnerRadiusRatio;
        if (f > 0.0f) {
            shapeDrawable.setRingInnerRadiusRatio(f);
        } else {
            int i = this.mRingInnerRadiusSize;
            if (i > -1) {
                shapeDrawable.setRingInnerRadiusSize(i);
            }
        }
        float f2 = this.mRingThicknessRatio;
        if (f2 > 0.0f) {
            shapeDrawable.setRingThicknessRatio(f2);
        } else {
            int i2 = this.mRingThicknessSize;
            if (i2 > -1) {
                shapeDrawable.setRingThicknessSize(i2);
            }
        }
        shapeDrawable.setLineGravity(this.mLineGravity);
        if (num != null) {
            shapeDrawable.setSolidColor(num.intValue());
        } else if (isSolidGradientColorsEnable()) {
            shapeDrawable.setSolidColor(this.mSolidGradientColors);
        } else {
            shapeDrawable.setSolidColor(this.mSolidColor);
        }
        if (num2 != null) {
            shapeDrawable.setStrokeColor(num2.intValue());
        } else if (isStrokeGradientColorsEnable()) {
            shapeDrawable.setStrokeColor(this.mStrokeGradientColors);
        } else {
            shapeDrawable.setStrokeColor(this.mStrokeColor);
        }
    }

    public ShapeDrawable convertShapeDrawable(Drawable drawable) {
        if (drawable instanceof ShapeDrawable) {
            return (ShapeDrawable) drawable;
        }
        return new ShapeDrawable();
    }

    public void intoBackground() {
        Drawable drawableBuildBackgroundDrawable = buildBackgroundDrawable();
        if (isStrokeDashLineEnable() || isShadowEnable()) {
            this.mView.setLayerType(1, null);
        }
        if (drawableBuildBackgroundDrawable != null) {
            this.mView.setBackground(drawableBuildBackgroundDrawable);
        }
    }

    public void clearBackground() {
        this.mSolidColor = 0;
        this.mSolidGradientColors = null;
        this.mSolidPressedColor = null;
        this.mSolidCheckedColor = null;
        this.mSolidDisabledColor = null;
        this.mSolidFocusedColor = null;
        this.mSolidSelectedColor = null;
        this.mStrokeColor = 0;
        this.mStrokeGradientColors = null;
        this.mStrokePressedColor = null;
        this.mStrokeCheckedColor = null;
        this.mStrokeDisabledColor = null;
        this.mStrokeFocusedColor = null;
        this.mStrokeSelectedColor = null;
        this.mView.setBackground(null);
    }

    private static int getLayoutDirection(View view) {
        Context context = view.getContext();
        Resources resources = context != null ? context.getResources() : null;
        Configuration configuration = resources != null ? resources.getConfiguration() : null;
        if (configuration != null) {
            return configuration.getLayoutDirection();
        }
        return 0;
    }

    private ShapeGradientOrientation transformGradientOrientation(int i) {
        switch (i) {
            case 0:
                return ShapeGradientOrientation.LEFT_TO_RIGHT;
            case 45:
                return ShapeGradientOrientation.BOTTOM_LEFT_TO_TOP_RIGHT;
            case 90:
                return ShapeGradientOrientation.BOTTOM_TO_TOP;
            case KeyBoardKey.KeyboardKeyF24 /* 135 */:
                return ShapeGradientOrientation.BOTTOM_RIGHT_TO_TOP_LEFT;
            case 180:
                return ShapeGradientOrientation.RIGHT_TO_LEFT;
            case KeyBoardKey.KeyboardKeyOemAx /* 225 */:
                return ShapeGradientOrientation.TOP_RIGHT_TO_BOTTOM_LEFT;
            case VeVideoFrame.VideoRotation.VIDEO_ROTATION_270 /* 270 */:
                return ShapeGradientOrientation.TOP_TO_BOTTOM;
            case 315:
                return ShapeGradientOrientation.TOP_LEFT_TO_BOTTOM_RIGHT;
            case 450:
                return ShapeGradientOrientation.BOTTOM_START_TO_TOP_END;
            case 1350:
                return ShapeGradientOrientation.BOTTOM_END_TO_TOP_START;
            case 1800:
                return ShapeGradientOrientation.END_TO_START;
            case 2250:
                return ShapeGradientOrientation.TOP_END_TO_BOTTOM_START;
            case 3150:
                return ShapeGradientOrientation.TOP_START_TO_BOTTOM_END;
            default:
                return ShapeGradientOrientation.START_TO_END;
        }
    }
}
