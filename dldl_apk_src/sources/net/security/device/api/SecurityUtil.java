package net.security.device.api;

import android.text.TextUtils;
import android.util.Base64;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alipay.sdk.packet.e;
import com.alipay.sdk.sys.a;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class SecurityUtil {
    private static final int MAX_TIME_OUT_CONNECT = 20;
    private static final int MAX_TIME_OUT_READ = 20;
    private static final int MAX_TIME_OUT_WRITE = 20;
    public static final int NET_EXCEPTION_CODE = -1;
    public static Field artMethodField;

    public static String sendPopRequest(String str, Map<String, String> map, Map<String, String> map2) {
        HashMap map3 = new HashMap();
        map3.put("TimestampKeep", formatIso8601Date(new Date()));
        map3.put("SignatureNonce", UUID.randomUUID().toString());
        if (map2 != null) {
            map2.remove(RequestParameters.SIGNATURE);
            for (Map.Entry<String, String> entry : map2.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    map3.put(key, value);
                }
            }
            String str2 = map2.get(e.e);
            if (str2 != null && !str2.isEmpty() && str2.equals("2020-04-25")) {
                map3.put("Timestamp", formatIso8601Date(new Date()));
            }
        }
        try {
            map3.put(RequestParameters.SIGNATURE, computeSignature(map3, "UTF-8"));
        } catch (Throwable unused) {
            map3.put(RequestParameters.SIGNATURE, "null");
        }
        return httpPost(str, map, map3);
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
        return SecurityDevice.gs("POST" + a.b + encode("/", str) + a.b + encode(sb.toString(), str));
    }

    private static String encode(String str, String str2) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return URLEncoder.encode(str, str2).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    private static String httpPost(String str, Map<String, String> map, Map<String, String> map2) {
        OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Request.Builder builder2 = new Request.Builder();
        for (Map.Entry<String, String> entry2 : map.entrySet()) {
            builder2 = builder2.addHeader(entry2.getKey(), entry2.getValue());
        }
        try {
            Response responseExecute = okHttpClientBuild.newCall(builder2.url(str).post(builder.build()).build()).execute();
            ResponseBody responseBodyBody = responseExecute.body();
            return responseExecute.code() + "#" + Base64.encodeToString((responseBodyBody != null ? responseBodyBody.string() : "").getBytes(), 2);
        } catch (Exception e) {
            return "-1#" + Base64.encodeToString(e.getMessage().getBytes(), 2);
        }
    }

    public static boolean isExeFileExist(String str) {
        try {
            Process processExec = Runtime.getRuntime().exec(str);
            if (processExec == null) {
                return false;
            }
            processExec.destroy();
            processExec.waitFor();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x00c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int downloadFile(java.lang.String r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.security.device.api.SecurityUtil.downloadFile(java.lang.String, java.lang.String):int");
    }

    public static String generateSessionId(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String string = UUID.randomUUID().toString();
        if (string.isEmpty()) {
            return "";
        }
        String str2 = String.format("%s-%s-%d-%s", str, "h", Long.valueOf(System.currentTimeMillis()), string.replace("-", ""));
        String strSubstring = str2.substring(0, str2.length() - 4);
        int iAbs = Math.abs((strSubstring + "7849478494").hashCode());
        String strValueOf = String.valueOf(iAbs);
        if (strValueOf.length() > 4) {
            strValueOf = strValueOf.substring(strValueOf.length() - 4);
        } else if (strValueOf.length() < 4) {
            strValueOf = String.format("%4d", Integer.valueOf(iAbs));
        }
        return strSubstring + strValueOf;
    }

    public static void utilInit() {
        try {
            artMethodField = getField(Method.class, "artMethod");
        } catch (Exception unused) {
        }
    }

    public static Field getField(Class cls, String str) {
        while (cls != null && cls != Object.class) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (Exception unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static long getArtMethod(Member member) {
        Field field = artMethodField;
        if (field == null) {
            return 0L;
        }
        try {
            return ((Long) field.get(member)).longValue();
        } catch (IllegalAccessException unused) {
            return 0L;
        }
    }
}
