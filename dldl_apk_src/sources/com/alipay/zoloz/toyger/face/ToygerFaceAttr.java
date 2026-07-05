package com.alipay.zoloz.toyger.face;

import android.graphics.RectF;
import com.alipay.zoloz.toyger.ToygerAttr;
import com.alipay.zoloz.toyger.algorithm.TGFaceAttr;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerFaceAttr extends TGFaceAttr implements ToygerAttr {
    public ToygerFaceAttr() {
    }

    public ToygerFaceAttr(TGFaceAttr tGFaceAttr) {
        super(tGFaceAttr);
    }

    public ToygerFaceAttr(ToygerFaceAttr toygerFaceAttr, boolean z) {
        float[] fArr;
        super(toygerFaceAttr);
        if (!z) {
            RectF rectF = toygerFaceAttr.faceRegion;
            this.faceRegion = new RectF(1.0f - rectF.right, rectF.top, 1.0f - rectF.left, rectF.bottom);
        }
        if (toygerFaceAttr == null || (fArr = toygerFaceAttr.keypts10) == null) {
            this.keypts10 = toygerFaceAttr != null ? toygerFaceAttr.keypts10 : new float[10];
            return;
        }
        float[] fArr2 = new float[10];
        if (z) {
            fArr2[0] = 1.0f - fArr[1];
            fArr2[1] = 1.0f - fArr[0];
            fArr2[2] = 1.0f - fArr[2];
            fArr2[3] = 1.0f - fArr[4];
            fArr2[4] = 1.0f - fArr[3];
            fArr2[5] = fArr[5];
            fArr2[6] = fArr[6];
            fArr2[7] = fArr[7];
            fArr2[8] = fArr[8];
            fArr2[9] = fArr[9];
        }
        this.keypts10 = fArr2;
    }

    public ToygerFaceAttr(boolean z, int i, boolean z2, boolean z3, RectF rectF, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, short s, float[] fArr, float f14, float f15, float f16, float f17, float f18) {
        super(z, i, z2, z3, rectF, f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, s, fArr, f14, f15, f16, f17, f18);
    }

    @Override // com.alipay.zoloz.toyger.ToygerAttr
    public float brightness() {
        return this.brightness;
    }

    @Override // com.alipay.zoloz.toyger.ToygerAttr
    public short distance() {
        return this.distance;
    }

    public boolean eyeBlink() {
        return this.eyeBlink;
    }

    @Override // com.alipay.zoloz.toyger.ToygerAttr
    public float gaussian() {
        return this.gaussian;
    }

    @Override // com.alipay.zoloz.toyger.ToygerAttr
    public boolean hasTarget() {
        return this.hasFace;
    }

    @Override // com.alipay.zoloz.toyger.ToygerAttr
    public float integrity() {
        return this.integrity;
    }

    public float leftEyeBlinkRatio() {
        return this.leftEyeBlinkRatio;
    }

    public float leftEyeBlinkRatioMax() {
        return this.leftEyeBlinkRatioMax;
    }

    public float leftEyeBlinkRatioMin() {
        return this.leftEyeBlinkRatioMin;
    }

    @Override // com.alipay.zoloz.toyger.ToygerAttr
    public float motion() {
        return this.motion;
    }

    public float pitch() {
        return this.pitch;
    }

    @Override // com.alipay.zoloz.toyger.ToygerAttr
    public float quality() {
        return this.quality;
    }

    @Override // com.alipay.zoloz.toyger.ToygerAttr
    public RectF region() {
        return this.faceRegion;
    }

    public float rightEyeBlinkRatio() {
        return this.rightEyeBlinkRatio;
    }

    public float rightEyeBlinkRatioMax() {
        return this.rightEyeBlinkRatioMax;
    }

    public float rightEyeBlinkRatioMin() {
        return this.rightEyeBlinkRatioMin;
    }

    public float yaw() {
        return this.yaw;
    }
}
