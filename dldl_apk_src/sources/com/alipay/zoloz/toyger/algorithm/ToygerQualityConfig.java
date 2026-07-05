package com.alipay.zoloz.toyger.algorithm;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerQualityConfig {
    public float blinkOpenness;
    public int depthCollectCount;
    public float depthMinQuality;
    public float eyeOcclusion;
    public float eyeOpenness;
    public float maxGaussian;
    public float maxMotion;
    public float maxPitch;
    public float maxYaw;
    public float max_iod;
    public float minBrightness;
    public float minFaceWidth;
    public float minIntegrity;
    public float minPitch;
    public float minQuality;
    public float minYaw;
    public float min_iod;
    public float stackTime;

    public ToygerQualityConfig() {
    }

    public ToygerQualityConfig(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, int i) {
        this.minBrightness = f;
        this.minFaceWidth = f2;
        this.minIntegrity = f3;
        this.maxPitch = f4;
        this.maxYaw = f5;
        this.maxGaussian = f6;
        this.maxMotion = f7;
        this.minQuality = f8;
        this.stackTime = f9;
        this.min_iod = f10;
        this.max_iod = f11;
        this.blinkOpenness = f12;
        this.eyeOpenness = f13;
        this.eyeOcclusion = f14;
        this.minPitch = f15;
        this.minYaw = f16;
        this.depthCollectCount = i;
        this.depthMinQuality = f17;
    }

    public String toString() {
        return "ToygerQualityConfig{minBrightness=" + this.minBrightness + ", minFaceWidth=" + this.minFaceWidth + ", minIntegrity=" + this.minIntegrity + ", maxPitch=" + this.maxPitch + ", maxYaw=" + this.maxYaw + ", maxGaussian=" + this.maxGaussian + ", maxMotion=" + this.maxMotion + ", minQuality=" + this.minQuality + ", stackTime=" + this.stackTime + ", min_iod=" + this.min_iod + ", max_iod=" + this.max_iod + ", blinkOpenness=" + this.blinkOpenness + ", eyeOpenness=" + this.eyeOpenness + ", eyeOcclusion=" + this.eyeOcclusion + ", minPitch=" + this.minPitch + ", minYaw=" + this.minYaw + ", depthMinQuality=" + this.depthMinQuality + ", depthCollectCount=" + this.depthCollectCount + '}';
    }
}
