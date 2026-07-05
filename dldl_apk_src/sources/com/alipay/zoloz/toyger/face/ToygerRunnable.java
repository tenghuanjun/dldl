package com.alipay.zoloz.toyger.face;

import com.alipay.zoloz.toyger.ToygerAttr;
import com.alipay.zoloz.toyger.algorithm.TGDepthFrame;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.algorithm.Toyger;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerRunnable implements Runnable {
    private static final String TAG = "TOYGER_FLOW_RUNNABLE";
    public ToygerAttr attr;
    public TGDepthFrame depthFrame;
    public List<TGFrame> frames;

    public ToygerRunnable(List<TGFrame> list, TGDepthFrame tGDepthFrame, ToygerAttr toygerAttr) {
        this.frames = list;
        this.depthFrame = tGDepthFrame;
        this.attr = toygerAttr;
    }

    @Override // java.lang.Runnable
    public void run() {
        System.currentTimeMillis();
        Toyger.processImage(this.frames, this.depthFrame, this.attr);
    }
}
