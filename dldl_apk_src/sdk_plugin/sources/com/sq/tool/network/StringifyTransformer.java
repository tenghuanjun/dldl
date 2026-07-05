package com.sq.tool.network;

import com.sdk.sq.net.RequestBuilder;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class StringifyTransformer implements RequestBuilder.ParamsTransformer {
    StringifyTransformer() {
    }

    @Override // com.sdk.sq.net.RequestBuilder.ParamsTransformer
    public Map<String, Object> transform(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj == null) {
                linkedHashMap.put(str, "");
            } else if (obj instanceof String) {
                linkedHashMap.put(str, obj);
            } else {
                linkedHashMap.put(str, String.valueOf(obj));
            }
        }
        return linkedHashMap;
    }
}
