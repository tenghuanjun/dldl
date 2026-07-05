package com.parameters.performfeatureconfig;

import com.sqwan.msdk.api.SQResultListener;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class PerformFeature {
    public static Map<String, PerformFeature> map = new HashMap();
    public Object data;
    public SQResultListener sqResultListener;
    public String type;

    public PerformFeature(String str, Object obj, SQResultListener sQResultListener) {
        this.type = str;
        this.data = obj;
        this.sqResultListener = sQResultListener;
    }
}
