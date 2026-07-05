package com.sdk.sq.net;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Deprecated
public class ParamsUtils {
    public static Map<String, String> checkNullParams(Map<String, String> originParams) {
        if (originParams != null && !originParams.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = originParams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> next = it.next();
                if (next.getKey() == null) {
                    it.remove();
                } else if (next.getValue() == null) {
                    next.setValue("");
                }
            }
        }
        return originParams;
    }
}
