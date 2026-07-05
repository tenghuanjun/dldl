package com.sqwan.msdk.api;

import android.content.Context;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.util.SQContextWrapper;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
class MInitParams extends CommonParamsV1 {
    MInitParams() {
    }

    @Override // com.sqwan.msdk.api.CommonParamsV1, com.sdk.sq.net.RequestBuilder.ParamsTransformer
    public Map<String, Object> transform(Map<String, Object> map) {
        Map<String, Object> mapTransform = super.transform(map);
        if (mapTransform == null) {
            mapTransform = new HashMap<>();
        }
        Context applicationContext = SQContextWrapper.getApplicationContext();
        mapTransform.put("dev", MultiSDKUtils.getDevID(applicationContext) + "");
        mapTransform.put("dev2", DevLogic.getInstance(applicationContext).getValue());
        return mapTransform;
    }
}
