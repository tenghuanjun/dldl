package com.sq.tool.network;

import com.sqnetwork.voly.VolleyLog;
import com.sqnetwork.voly.toolbox.FormBody;
import com.sqwan.common.util.MD5Util;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.msdk.config.ConfigManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SignV3Interceptor implements Interceptor {
    private static final String PARAM_NAME = "sign";
    private final String mKey;

    public SignV3Interceptor(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("App key can not be null!");
        }
        this.mKey = str;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBodyBody = request.body();
        if (requestBodyBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBodyBody;
            FormBody.Builder builder = new FormBody.Builder();
            HashMap map = new HashMap();
            for (int i = 0; i < formBody.size(); i++) {
                String strName = formBody.name(i);
                String strValue = formBody.value(i);
                if (!"sign".equals(strName)) {
                    map.put(strName, strValue);
                }
                builder.add(strName, strValue);
            }
            builder.add("sign", getSign(request, map));
            request = request.newBuilder().post(builder.build()).build();
        } else if ("get".equalsIgnoreCase(request.method())) {
            HttpUrl httpUrlUrl = request.url();
            HashMap map2 = new HashMap();
            for (String str : httpUrlUrl.queryParameterNames()) {
                String strQueryParameter = httpUrlUrl.queryParameter(str);
                if (strQueryParameter != null) {
                    map2.put(str, strQueryParameter);
                } else {
                    map2.put(str, "");
                }
            }
            if (!map2.isEmpty()) {
                request = request.newBuilder().url(httpUrlUrl.newBuilder().addQueryParameter("sign", getSign(request, map2)).build()).build();
            }
        }
        return chain.proceed(request);
    }

    private String getSign(Request request, Map<String, String> map) {
        StringBuilder sbBuildString = buildString(this.mKey, map);
        String strSign = sign(sbBuildString);
        if (VolleyLog.VERBOSE) {
            VolleyLog.i("[V3]%s\n签名原串: %s\n签名结果: %s", request.url(), sbBuildString, strSign);
        }
        return strSign;
    }

    private static StringBuilder buildString(String str, Map<String, String> map) {
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (String str2 : arrayList) {
            sb.append(str2);
            sb.append('=');
            sb.append(map.get(str2));
        }
        sb.append(str);
        return sb;
    }

    private static String sign(StringBuilder sb) {
        return MD5Util.Md5(sb.toString()).toLowerCase(Locale.US);
    }

    public static String sign(Map<String, String> map) {
        return sign(ConfigManager.getInstance(SQContextWrapper.getApplicationContext()).getAppKey(), map);
    }

    public static String sign(String str, Map<String, String> map) {
        if (map == null || str == null) {
            return "";
        }
        StringBuilder sbBuildString = buildString(str, map);
        String strSign = sign(sbBuildString);
        if (VolleyLog.VERBOSE) {
            VolleyLog.i("[V3]签名原串: %s\n签名结果: %s", sbBuildString, strSign);
        }
        return strSign;
    }
}
