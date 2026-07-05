package com.sq.tool.network;

import com.sqnetwork.voly.VolleyLog;
import com.sqwan.common.util.MD5Util;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.msdk.config.ConfigManager;
import java.util.Locale;
import java.util.Map;
import okhttp3.Interceptor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SignV1Interceptor implements Interceptor {
    private static final String PARAM_NAME = "sign";
    private final String mKey;

    public SignV1Interceptor(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("App key can not be null!");
        }
        this.mKey = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0065  */
    @Override // okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r18) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.tool.network.SignV1Interceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    public static String sign(Map<String, String> map) {
        return sign(ConfigManager.getInstance(SQContextWrapper.getApplicationContext()).getAppKey(), map);
    }

    public static String sign(String str, Map<String, String> map) {
        if (map == null || str == null) {
            return "";
        }
        String str2 = map.get("pid") + map.get("gid") + map.get("refer") + map.get("version") + map.get("time") + str;
        String lowerCase = MD5Util.Md5(str2).toLowerCase(Locale.US);
        if (VolleyLog.VERBOSE) {
            VolleyLog.i("[V1]签名原串: %s\n签名结果: %s", str2, lowerCase);
        }
        return lowerCase;
    }
}
