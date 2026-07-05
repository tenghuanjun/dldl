package com.alipay.zoloz.toyger;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.zoloz.toyger.ToygerAttr;
import com.alipay.zoloz.toyger.algorithm.TGFrame;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public abstract class ToygerBiometricInfo<Attr extends ToygerAttr> {

    @JSONField(name = "attr")
    public Attr attr;

    @JSONField(name = "frame")
    public TGFrame frame;
}
