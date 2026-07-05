package com.alipay.zoloz.toyger.algorithm;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class TGFaceAttr {
    public float brightness;
    public float confidence;
    public float deepLiveness;
    public float depthQuality;
    public short distance;
    public boolean eyeBlink;
    public boolean eyeOpen;
    public int faceId;
    public RectF faceRegion;
    public float gaussian;
    public boolean hasFace;
    public float integrity;
    public float iodRatio;
    public float[] keypts10;
    public float leftEyeBlinkRatio;
    public float leftEyeBlinkRatioMax;
    public float leftEyeBlinkRatioMin;
    public float motion;
    public float pitch;
    public float quality;
    public float rightEyeBlinkRatio;
    public float rightEyeBlinkRatioMax;
    public float rightEyeBlinkRatioMin;
    public float roll;
    public float yaw;

    public TGFaceAttr() {
        this.faceId = -1;
        this.depthQuality = -1.0f;
        this.faceRegion = new RectF();
    }

    public TGFaceAttr(TGFaceAttr tGFaceAttr) {
        this.faceId = -1;
        this.depthQuality = -1.0f;
        if (tGFaceAttr != null) {
            this.hasFace = tGFaceAttr.hasFace;
            this.faceId = tGFaceAttr.faceId;
            this.eyeOpen = tGFaceAttr.eyeOpen;
            this.eyeBlink = tGFaceAttr.eyeBlink;
            this.faceRegion = new RectF(tGFaceAttr.faceRegion);
            this.quality = tGFaceAttr.quality;
            this.yaw = tGFaceAttr.yaw;
            this.pitch = tGFaceAttr.pitch;
            this.gaussian = tGFaceAttr.gaussian;
            this.motion = tGFaceAttr.motion;
            this.brightness = tGFaceAttr.brightness;
            this.integrity = tGFaceAttr.integrity;
            this.leftEyeBlinkRatio = tGFaceAttr.leftEyeBlinkRatio;
            this.leftEyeBlinkRatioMin = tGFaceAttr.leftEyeBlinkRatioMin;
            this.leftEyeBlinkRatioMax = tGFaceAttr.leftEyeBlinkRatioMax;
            this.rightEyeBlinkRatio = tGFaceAttr.rightEyeBlinkRatio;
            this.rightEyeBlinkRatioMin = tGFaceAttr.rightEyeBlinkRatioMin;
            this.rightEyeBlinkRatioMax = tGFaceAttr.rightEyeBlinkRatioMax;
            this.distance = tGFaceAttr.distance;
            this.keypts10 = tGFaceAttr.keypts10;
            this.confidence = tGFaceAttr.confidence;
            this.roll = tGFaceAttr.roll;
            this.iodRatio = tGFaceAttr.iodRatio;
            this.deepLiveness = tGFaceAttr.deepLiveness;
            this.depthQuality = tGFaceAttr.depthQuality;
        }
    }

    public TGFaceAttr(boolean z, int i, boolean z2, boolean z3, RectF rectF, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, short s, float[] fArr, float f14, float f15, float f16, float f17, float f18) {
        this.faceId = -1;
        this.depthQuality = -1.0f;
        this.hasFace = z;
        this.eyeOpen = z3;
        this.faceId = i;
        this.eyeBlink = z2;
        this.faceRegion = rectF;
        this.quality = f;
        this.yaw = f2;
        this.pitch = f3;
        this.gaussian = f4;
        this.motion = f5;
        this.brightness = f6;
        this.integrity = f7;
        this.leftEyeBlinkRatio = f8;
        this.leftEyeBlinkRatioMin = f9;
        this.leftEyeBlinkRatioMax = f10;
        this.rightEyeBlinkRatio = f11;
        this.rightEyeBlinkRatioMin = f12;
        this.rightEyeBlinkRatioMax = f13;
        this.distance = s;
        this.keypts10 = fArr;
        this.confidence = f14;
        this.roll = f15;
        this.iodRatio = f16;
        this.deepLiveness = f17;
        this.depthQuality = f18;
    }

    public String toString() {
        return "TGFaceAttr{hasFace=" + this.hasFace + ", faceId=" + this.faceId + ", eyeBlink=" + this.eyeBlink + ", eyeOpen=" + this.eyeOpen + ", faceRegion=" + this.faceRegion + ", quality=" + this.quality + ", yaw=" + this.yaw + ", pitch=" + this.pitch + ", gaussian=" + this.gaussian + ", motion=" + this.motion + ", brightness=" + this.brightness + ", integrity=" + this.integrity + ", leftEyeBlinkRatio=" + this.leftEyeBlinkRatio + ", leftEyeBlinkRatioMin=" + this.leftEyeBlinkRatioMin + ", leftEyeBlinkRatioMax=" + this.leftEyeBlinkRatioMax + ", rightEyeBlinkRatio=" + this.rightEyeBlinkRatio + ", rightEyeBlinkRatioMin=" + this.rightEyeBlinkRatioMin + ", rightEyeBlinkRatioMax=" + this.rightEyeBlinkRatioMax + ", distance=" + ((int) this.distance) + ", keypts10=" + Arrays.toString(this.keypts10) + ", confidence=" + this.confidence + ", roll=" + this.roll + ", iodRatio=" + this.iodRatio + ", deepLiveness=" + this.deepLiveness + ", depthQuality=" + this.depthQuality + '}';
    }
}
