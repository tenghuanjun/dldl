package com.alipay.zoloz.toyger.algorithm;

import android.graphics.Rect;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerCameraConfig {
    public float[] color2depthExtrin;
    public float[] colorIntrin;
    public float[] depthIntrin;
    public boolean isAligned;
    public boolean isMirror;
    public Rect roiRect;

    public ToygerCameraConfig() {
        this.colorIntrin = new float[]{-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        this.depthIntrin = new float[]{-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        this.color2depthExtrin = new float[]{-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        this.roiRect = new Rect(0, 0, 0, 0);
        this.isAligned = true;
        this.isMirror = false;
    }

    public ToygerCameraConfig(float[] fArr, float[] fArr2, float[] fArr3, Rect rect, boolean z, boolean z2) {
        this.colorIntrin = new float[]{-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        this.depthIntrin = new float[]{-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        this.color2depthExtrin = new float[]{-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f};
        this.roiRect = new Rect(0, 0, 0, 0);
        this.isAligned = true;
        this.isMirror = false;
        this.colorIntrin = fArr;
        this.depthIntrin = fArr2;
        this.color2depthExtrin = fArr3;
        this.roiRect = rect;
        this.isAligned = z;
        this.isMirror = z2;
    }

    public String toString() {
        return "ToygerCameraConfig{colorIntrin=" + Arrays.toString(this.colorIntrin) + ", depthIntrin=" + Arrays.toString(this.depthIntrin) + ", color2depthExtrin=" + Arrays.toString(this.color2depthExtrin) + ", roiRect=" + this.roiRect + ", isAligned=" + this.isAligned + '}';
    }
}
