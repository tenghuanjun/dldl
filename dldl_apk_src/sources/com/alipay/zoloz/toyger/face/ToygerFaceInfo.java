package com.alipay.zoloz.toyger.face;

import com.alipay.zoloz.toyger.ToygerBiometricInfo;
import com.alipay.zoloz.toyger.algorithm.TGFrame;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerFaceInfo extends ToygerBiometricInfo<ToygerFaceAttr> {
    public ToygerFaceInfo() {
    }

    public ToygerFaceInfo(TGFrame tGFrame, ToygerFaceAttr toygerFaceAttr) {
        this.frame = tGFrame;
        this.attr = toygerFaceAttr;
    }
}
