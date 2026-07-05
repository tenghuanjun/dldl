package com.alipay.zoloz.toyger.algorithm;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerLivenessConfig {
    public float batLivenessThreshold;
    public String collection;
    public float dragonflyMax;
    public float dragonflyMin;
    public float eye_blink_threshold;
    public float eye_occlusion_threshold;
    public boolean fppPreProcess;
    public float geminiMax;
    public float geminiMin;
    public String livenessCombinations;

    public ToygerLivenessConfig() {
        this.batLivenessThreshold = 0.5f;
        this.dragonflyMin = 0.0f;
        this.dragonflyMax = 0.5f;
        this.geminiMin = 0.0f;
        this.geminiMax = 0.0f;
        this.eye_occlusion_threshold = 0.8f;
        this.eye_blink_threshold = 0.2f;
        this.fppPreProcess = false;
    }

    public ToygerLivenessConfig(String str, float f, float f2, float f3, float f4, float f5, boolean z) {
        this.batLivenessThreshold = 0.5f;
        this.dragonflyMin = 0.0f;
        this.dragonflyMax = 0.5f;
        this.geminiMin = 0.0f;
        this.geminiMax = 0.0f;
        this.eye_occlusion_threshold = 0.8f;
        this.eye_blink_threshold = 0.2f;
        this.fppPreProcess = false;
        this.livenessCombinations = str;
        this.batLivenessThreshold = f;
        this.dragonflyMin = f2;
        this.dragonflyMax = f3;
        this.geminiMin = f4;
        this.geminiMax = f5;
        this.fppPreProcess = z;
    }

    public String toString() {
        return "ToygerLivenessConfig{livenessCombinations='" + this.livenessCombinations + "', collection='" + this.collection + "', batLivenessThreshold=" + this.batLivenessThreshold + ", dragonflyMin=" + this.dragonflyMin + ", dragonflyMax=" + this.dragonflyMax + ", geminiMin=" + this.geminiMin + ", geminiMax=" + this.geminiMax + '}';
    }
}
