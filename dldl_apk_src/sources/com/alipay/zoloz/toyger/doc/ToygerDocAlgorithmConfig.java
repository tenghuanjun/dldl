package com.alipay.zoloz.toyger.doc;

import android.graphics.Rect;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.zoloz.toyger.ToygerBaseService;
import com.aliyun.aliyunface.api.ZIMFacade;
import toygerservice.b;
import toygerservice.k;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerDocAlgorithmConfig extends b {

    @JSONField(name = "frameRect")
    public Rect frameRect;

    @JSONField(name = ToygerBaseService.KEY_ROTATE_TIMES)
    public int rotateTimes;

    @JSONField(name = "algoType")
    public int algoType = 1;

    @JSONField(name = "exposure")
    public int exposure = 20;

    @JSONField(name = "blur")
    public int blur = 80;

    @JSONField(name = "card_detect_score")
    public int card_detect_score = 100;

    public static String ToygerDocAlgorithmConfigUpdate(String str) {
        if (str.equals(ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE)) {
            k.b.a = true;
        }
        return str + ",ToygerDocAlgorithmConfigUpdate!";
    }

    public static ToygerDocAlgorithmConfig from(String str) {
        return (ToygerDocAlgorithmConfig) JSON.parseObject(str, ToygerDocAlgorithmConfig.class);
    }

    public String toString() {
        return "ToygerDocAlgorithmConfig{algoType=" + this.algoType + ", rect=" + this.frameRect.toString() + ", rotateTimes=" + this.rotateTimes + ", exposure=" + this.exposure + ", blur=" + this.blur + ", card_detect_score=" + this.card_detect_score + '}';
    }
}
