package com.aliyun.aliyunface.network;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alipay.sdk.packet.e;
import com.alipay.sdk.sys.a;
import com.aliyun.aliyunface.api.ZIMResponseCode;
import com.aliyun.aliyunface.network.model.ZimResBase;
import com.aliyun.aliyunface.utils.MiscUtil;
import com.igexin.push.core.d.c;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class PopNetHelper {
    public static String HOST = null;
    private static final int MAX_TIME_OUT_CONNECT = 20;
    private static final int MAX_TIME_OUT_READ = 20;
    private static final int MAX_TIME_OUT_WRITE = 20;
    public static final int NET_EXCEPTION_CODE = -1;
    public static final int NET_RESPONSE_INVALID = -2;
    public static String appKey;
    public static String appSecret;

    public static native String getSignature(String str, String str2, String str3, byte[] bArr);

    public static native String sd9(byte[] bArr, String str);

    public static native String se9(byte[] bArr, String str);

    static {
        System.loadLibrary("aliyunaf");
        appKey = "ACSTQDkNtSMrZtwL";
        appSecret = "zXJ7QF79Oz";
        HOST = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isHttpServiceAvalible(int i, String str) {
        try {
            if (TextUtils.isEmpty(str) || str.contains("\"Code\":\"ServiceUnavailable\"")) {
                return false;
            }
            ZimResBase zimResBase = (ZimResBase) MiscUtil.json2Object(str, ZimResBase.class);
            if (zimResBase == null || zimResBase.Code < 500) {
                return true;
            }
            return 2003 == zimResBase.Code;
        } catch (Exception unused) {
            return true;
        }
    }

    public static void sendAsynRequest(final NetworkEnv networkEnv, final String str, final String str2, final Map<String, String> map, Object obj, final PopNetCallback popNetCallback) {
        sendAsynRequestInteli(networkEnv.safUrl, str, str2, map, obj, new PopNetCallback() { // from class: com.aliyun.aliyunface.network.PopNetHelper.1
            @Override // com.aliyun.aliyunface.network.PopNetCallback
            public void onSuccess(int i, String str3, Object obj2) {
                if (!PopNetHelper.isHttpServiceAvalible(i, str3)) {
                    PopNetHelper.sendAsynRequestInteli(networkEnv.safBackupUrl, str, str2, map, obj2, popNetCallback);
                    return;
                }
                PopNetCallback popNetCallback2 = popNetCallback;
                if (popNetCallback2 != null) {
                    popNetCallback2.onSuccess(i, str3, obj2);
                }
            }

            @Override // com.aliyun.aliyunface.network.PopNetCallback
            public void onError(int i, String str3, Object obj2) {
                PopNetHelper.sendAsynRequestInteli(networkEnv.safBackupUrl, str, str2, map, obj2, popNetCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendAsynRequestInteli(String str, String str2, String str3, Map<String, String> map, Object obj, PopNetCallback popNetCallback) {
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        map3.putAll(map);
        Log.e("Toyger", "url=>" + str + " action=>" + str2);
        map2.put("AccessKeyId", appKey);
        map2.put("Format", "JSON");
        map2.put("SignatureMethod", "HMAC-SHA1");
        map2.put("Timestamp", formatIso8601Date(new Date()));
        map2.put("SignatureVersion", "1.0");
        map2.put("SignatureNonce", UUID.randomUUID().toString());
        map2.put(e.e, str3);
        map2.put("Action", str2);
        map3.remove(RequestParameters.SIGNATURE);
        try {
            String str4 = (String) map3.get("CertifyId");
            if (!isNormalCertifyId(str4) && !isNormalAction(str2)) {
                map2.put("SecurityType", c.d);
                if (str4 != null && !TextUtils.isEmpty(str4)) {
                    map2.put("CertifyId", str4);
                    map3.remove("CertifyId");
                }
                String str5 = (String) map3.get("AppVersion");
                if (str5 != null && !TextUtils.isEmpty(str5)) {
                    map2.put("AppVersion", str5);
                    map3.remove("AppVersion");
                }
                String strSe9 = se9(MiscUtil.object2Json(map3).getBytes(), str4);
                if (strSe9 != null && !TextUtils.isEmpty(strSe9)) {
                    map2.put("RequestData", strSe9);
                }
            } else {
                for (Map.Entry entry : map3.entrySet()) {
                    String str6 = (String) entry.getKey();
                    String str7 = (String) entry.getValue();
                    if (!TextUtils.isEmpty(str6) && !TextUtils.isEmpty(str7)) {
                        map2.put(str6, str7);
                    }
                }
            }
        } catch (Exception unused) {
        }
        try {
            map2.put(RequestParameters.SIGNATURE, computeSignature(map2, "UTF-8"));
        } catch (Throwable unused2) {
            map2.put(RequestParameters.SIGNATURE, "null");
        }
        httpPost(str, map2, obj, popNetCallback);
    }

    public static boolean isNormalCertifyId(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return "n".equalsIgnoreCase(str.substring(str.length() - 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isNormalAction(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return !str.endsWith("Security");
    }

    private static void httpPost(String str, Map<String, String> map, final Object obj, final PopNetCallback popNetCallback) {
        OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Request.Builder builder2 = new Request.Builder();
        if (!TextUtils.isEmpty(HOST)) {
            builder2 = builder2.addHeader(com.alipay.sdk.cons.c.f, HOST);
        }
        Request requestBuild = builder2.url(str).post(builder.build()).build();
        final String str2 = map.get("CertifyId");
        final String str3 = map.get("Action");
        okHttpClientBuild.newCall(requestBuild).enqueue(new Callback() { // from class: com.aliyun.aliyunface.network.PopNetHelper.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                PopNetCallback popNetCallback2 = popNetCallback;
                if (popNetCallback2 != null) {
                    popNetCallback2.onError(-1, iOException.getMessage(), obj);
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject object;
                if (popNetCallback != null) {
                    ResponseBody responseBodyBody = response.body();
                    String strString = responseBodyBody != null ? responseBodyBody.string() : "";
                    if (strString.contains("InvalidTimeStamp.Expired")) {
                        strString = strString.replace("InvalidTimeStamp.Expired", String.valueOf(ZIMResponseCode.ZIM_RESPONSE_CLIENT_TIME_INVALID));
                    }
                    try {
                        if (!PopNetHelper.isNormalCertifyId(str2) && !PopNetHelper.isNormalAction(str3) && (object = JSONObject.parseObject(strString)) != null) {
                            String str4 = (String) object.get("ResultObject");
                            if (!TextUtils.isEmpty(str4)) {
                                String strSd9 = PopNetHelper.sd9(str4.getBytes(), str2);
                                if (!TextUtils.isEmpty(strSd9)) {
                                    object.put("ResultObject", (Object) JSONObject.parseObject(strSd9));
                                    strString = object.toJSONString();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    popNetCallback.onSuccess(response.code(), strString, obj);
                }
            }
        });
    }

    private static String formatIso8601Date(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return simpleDateFormat.format(date);
    }

    public static String computeSignature(Map<String, String> map, String str) throws Exception {
        String[] strArr = (String[]) map.keySet().toArray(new String[0]);
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str2 : strArr) {
            if (z) {
                z = false;
            } else {
                sb.append(a.b);
            }
            sb.append(encode(str2, str));
            sb.append("=");
            sb.append(encode(map.get(str2), str));
        }
        return getSignature(appKey, appSecret, a.b, ("POST" + a.b + encode("/", str) + a.b + encode(sb.toString(), str)).getBytes(str));
    }

    private static String encode(String str, String str2) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return URLEncoder.encode(str, str2).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }
}
