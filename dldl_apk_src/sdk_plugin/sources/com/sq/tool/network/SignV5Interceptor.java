package com.sq.tool.network;

import com.sqnetwork.voly.VolleyLog;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.util.MD5Util;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.msdk.config.ConfigManager;
import java.util.Locale;
import java.util.Map;
import okhttp3.Interceptor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SignV5Interceptor implements Interceptor {
    private static final String PARAM_NAME = "sign";
    private final String mKey;

    public SignV5Interceptor(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("App key can not be null!");
        }
        this.mKey = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0071  */
    @Override // okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r19) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.tool.network.SignV5Interceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    public static String sign(Map<String, String> map) {
        return sign(ConfigManager.getInstance(SQContextWrapper.getApplicationContext()).getAppKey(), map);
    }

    public static String sign(String str, Map<String, String> map) {
        if (map == null || str == null) {
            return "";
        }
        String str2 = map.get("pid");
        String str3 = map.get("gid");
        String str4 = map.get("refer");
        String str5 = map.get("version");
        String str6 = map.get("time");
        String str7 = map.get(SqConstants.TRANS_INFO);
        String str8 = str2 + str3 + str4 + str5 + str6 + (str7 != null ? str7 : "") + str;
        String lowerCase = MD5Util.Md5(str8).toLowerCase(Locale.US);
        if (VolleyLog.VERBOSE) {
            VolleyLog.i("[V5]签名原串: %s\n签名结果: %s", str8, lowerCase);
        }
        return lowerCase;
    }
}
