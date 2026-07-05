package com.sqwan.common.request;

import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sqwan.base.L;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.MD5Util;
import com.sqwan.msdk.config.ConfigManager;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SignUtils {
    public static String sign(Map<String, Object> map) {
        TreeMap treeMap = new TreeMap(map);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : treeMap.entrySet()) {
            sb.append((String) entry.getKey());
            sb.append(SimpleComparison.EQUAL_TO_OPERATION);
            sb.append(entry.getValue());
        }
        sb.append(ConfigManager.getInstance(L.getApplicationContext()).getAppKey());
        LogUtil.i("common sgin: signStr :" + ((Object) sb));
        return MD5Util.Md5(sb.toString()).toLowerCase();
    }
}
