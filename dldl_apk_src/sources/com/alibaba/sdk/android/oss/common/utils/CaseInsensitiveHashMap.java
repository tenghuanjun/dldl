package com.alibaba.sdk.android.oss.common.utils;

import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class CaseInsensitiveHashMap<k, v> extends HashMap<k, v> {
    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public v get(Object obj) {
        if (obj != null && !containsKey(obj) && (obj instanceof String)) {
            String lowerCase = ((String) obj).toLowerCase();
            if (containsKey(lowerCase)) {
                return get(lowerCase);
            }
            return null;
        }
        return (v) super.get(obj);
    }
}
