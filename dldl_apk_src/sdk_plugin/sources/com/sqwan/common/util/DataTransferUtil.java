package com.sqwan.common.util;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DataTransferUtil {
    public static Bundle convertMapToBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
        }
        return bundle;
    }

    public static Map<String, String> convertBundleToMap(Bundle bundle) {
        HashMap map = new HashMap();
        if (bundle != null && !bundle.isEmpty()) {
            for (String str : bundle.keySet()) {
                map.put(str, String.valueOf(bundle.get(str)));
            }
        }
        return map;
    }
}
