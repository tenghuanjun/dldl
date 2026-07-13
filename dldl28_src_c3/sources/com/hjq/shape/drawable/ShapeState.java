package com.hjq.shape.drawable;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import kotlin.KotlinVersion;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ShapeState extends Drawable.ConstantState {
    public int changingConfigurations;
    public float gradientRadius;
    public boolean hasSolidColor;
    public boolean hasStrokeColor;
    public int height;
    public int lineGravity;
    public boolean opaque;
    public Rect padding;
    public float[] positions;
    public float radius;
    public float[] radiusArray;
    public float ringInnerRadiusRatio;
    public int ringInnerRadiusSize;
    public float ringThicknessRatio;
    public int ringThicknessSize;
    public int shadowColor;
    public int shadowOffsetX;
    public int shadowOffsetY;
    public int shadowSize;
    public int shapeType;
    public float solidCenterX;
    public float solidCenterY;
    public int solidColor;
    public int[] solidColors;
    public ShapeGradientOrientation solidGradientOrientation;
    public int solidGradientType;
    public int strokeColor;
    public int[] strokeColors;
    public float strokeDashGap;
    public float strokeDashSize;
    public ShapeGradientOrientation strokeGradientOrientation;
    public int strokeSize;
    public int[] tempSolidColors;
    public float[] tempSolidPositions;
    public boolean useLevel;
    public boolean useLevelForShape;
    public int width;

    private static boolean isOpaque(int i) {
        return ((i >> 24) & KotlinVersion.MAX_COMPONENT_VALUE) == 255;
    }

    public ShapeState() {
        this.shapeType = 0;
        this.solidGradientType = 0;
        this.solidGradientOrientation = ShapeGradientOrientation.TOP_TO_BOTTOM;
        this.strokeSize = -1;
        this.strokeGradientOrientation = ShapeGradientOrientation.TOP_TO_BOTTOM;
        this.width = -1;
        this.height = -1;
        this.ringInnerRadiusSize = -1;
        this.ringThicknessSize = -1;
        this.solidCenterX = 0.5f;
        this.solidCenterY = 0.5f;
        this.gradientRadius = 0.5f;
        this.lineGravity = 17;
    }

    public ShapeState(ShapeState shapeState) {
        this.shapeType = 0;
        this.solidGradientType = 0;
        this.solidGradientOrientation = ShapeGradientOrientation.TOP_TO_BOTTOM;
        this.strokeSize = -1;
        this.strokeGradientOrientation = ShapeGradientOrientation.TOP_TO_BOTTOM;
        this.width = -1;
        this.height = -1;
        this.ringInnerRadiusSize = -1;
        this.ringThicknessSize = -1;
        this.solidCenterX = 0.5f;
        this.solidCenterY = 0.5f;
        this.gradientRadius = 0.5f;
        this.lineGravity = 17;
        this.changingConfigurations = shapeState.changingConfigurations;
        this.shapeType = shapeState.shapeType;
        this.solidGradientType = shapeState.solidGradientType;
        this.solidGradientOrientation = shapeState.solidGradientOrientation;
        int[] iArr = shapeState.solidColors;
        if (iArr != null) {
            this.solidColors = (int[]) iArr.clone();
        }
        int[] iArr2 = shapeState.strokeColors;
        if (iArr2 != null) {
            this.strokeColors = (int[]) iArr2.clone();
        }
        float[] fArr = shapeState.positions;
        if (fArr != null) {
            this.positions = (float[]) fArr.clone();
        }
        this.hasSolidColor = shapeState.hasSolidColor;
        this.hasStrokeColor = shapeState.hasStrokeColor;
        this.solidColor = shapeState.solidColor;
        this.strokeSize = shapeState.strokeSize;
        this.strokeGradientOrientation = shapeState.strokeGradientOrientation;
        this.strokeColor = shapeState.strokeColor;
        this.strokeDashSize = shapeState.strokeDashSize;
        this.strokeDashGap = shapeState.strokeDashGap;
        this.radius = shapeState.radius;
        float[] fArr2 = shapeState.radiusArray;
        if (fArr2 != null) {
            this.radiusArray = (float[]) fArr2.clone();
        }
        if (shapeState.padding != null) {
            this.padding = new Rect(shapeState.padding);
        }
        this.width = shapeState.width;
        this.height = shapeState.height;
        this.ringInnerRadiusRatio = shapeState.ringInnerRadiusRatio;
        this.ringThicknessRatio = shapeState.ringThicknessRatio;
        this.ringInnerRadiusSize = shapeState.ringInnerRadiusSize;
        this.ringThicknessSize = shapeState.ringThicknessSize;
        this.solidCenterX = shapeState.solidCenterX;
        this.solidCenterY = shapeState.solidCenterY;
        this.gradientRadius = shapeState.gradientRadius;
        this.useLevel = shapeState.useLevel;
        this.useLevelForShape = shapeState.useLevelForShape;
        this.opaque = shapeState.opaque;
        this.shadowSize = shapeState.shadowSize;
        this.shadowColor = shapeState.shadowColor;
        this.shadowOffsetX = shapeState.shadowOffsetX;
        this.shadowOffsetY = shapeState.shadowOffsetY;
        this.lineGravity = shapeState.lineGravity;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        return new ShapeDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        return new ShapeDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        return this.changingConfigurations;
    }

    public void setType(int i) {
        this.shapeType = i;
        computeOpacity();
    }

    public void setSolidGradientType(int i) {
        this.solidGradientType = i;
    }

    public void setSolidColor(int... iArr) {
        if (iArr == null) {
            this.solidColor = 0;
            this.hasSolidColor = true;
            computeOpacity();
            return;
        }
        if (iArr.length == 1) {
            this.hasSolidColor = true;
            this.solidColor = iArr[0];
            this.solidColors = null;
        } else {
            this.hasSolidColor = false;
            this.solidColor = 0;
            this.solidColors = iArr;
        }
        computeOpacity();
    }

    public void setSolidColor(int i) {
        this.hasSolidColor = true;
        this.solidColor = i;
        this.solidColors = null;
        computeOpacity();
    }

    private void computeOpacity() {
        if (this.shapeType != 0) {
            this.opaque = false;
            return;
        }
        if (this.radius > 0.0f || this.radiusArray != null) {
            this.opaque = false;
            return;
        }
        if (this.shadowSize > 0) {
            this.opaque = false;
            return;
        }
        if (this.strokeSize > 0 && !isOpaque(this.strokeColor)) {
            this.opaque = false;
            return;
        }
        if (this.hasSolidColor) {
            this.opaque = isOpaque(this.solidColor);
            return;
        }
        int[] iArr = this.solidColors;
        if (iArr != null) {
            for (int i : iArr) {
                if (!isOpaque(i)) {
                    this.opaque = false;
                    return;
                }
            }
        }
        if (this.hasStrokeColor) {
            this.opaque = isOpaque(this.strokeColor);
            return;
        }
        int[] iArr2 = this.strokeColors;
        if (iArr2 != null) {
            for (int i2 : iArr2) {
                if (!isOpaque(i2)) {
                    this.opaque = false;
                    return;
                }
            }
        }
        this.opaque = true;
    }

    public void setStrokeSize(int i) {
        this.strokeSize = i;
        computeOpacity();
    }

    public void setStrokeColor(int... iArr) {
        if (iArr == null) {
            this.strokeColor = 0;
            this.hasStrokeColor = true;
            computeOpacity();
            return;
        }
        if (iArr.length == 1) {
            this.hasStrokeColor = true;
            this.strokeColor = iArr[0];
            this.strokeColors = null;
        } else {
            this.hasStrokeColor = false;
            this.strokeColor = 0;
            this.strokeColors = iArr;
        }
        computeOpacity();
    }

    public void setCornerRadius(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.radius = f;
        this.radiusArray = null;
    }

    public void setCornerRadii(float[] fArr) {
        this.radiusArray = fArr;
        if (fArr == null) {
            this.radius = 0.0f;
        }
    }
}
