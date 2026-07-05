package com.sq.tool.sqtools.detector;

import android.content.Context;
import android.text.TextUtils;
import com.sq.tool.sqtools.detector.log.LogTools;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DetectorHelper {
    public static String getMapInfo(String str, Map<String, String> map, Context context) {
        try {
            if (!map.isEmpty() && map.containsKey(str)) {
                if (TextUtils.isEmpty(map.get(str))) {
                    return getMethod(str, context);
                }
                return map.get(str);
            }
            return getMethod(str, context);
        } catch (Exception e) {
            e.printStackTrace();
            LogTools.sendLog("getMapInfo异常-->" + e);
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getMethod(java.lang.String r1, android.content.Context r2) {
        /*
            Method dump skipped, instruction units count: 740
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.tool.sqtools.detector.DetectorHelper.getMethod(java.lang.String, android.content.Context):java.lang.String");
    }
}
