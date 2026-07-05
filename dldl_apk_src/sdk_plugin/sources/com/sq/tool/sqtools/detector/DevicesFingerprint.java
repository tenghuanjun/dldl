package com.sq.tool.sqtools.detector;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.sq.tool.sqtools.detector.log.LogTools;
import com.sq.tool.sqtools.net.DevicesHttpCallback;
import com.sq.tool.sqtools.net.DevicesHttpClient;
import com.sqwan.common.route.FunctionRouter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DevicesFingerprint {
    private static final String DEV_AES = "devices_aes";
    private static final String SQ_PREFS = "sq_prefs";
    public static String TOKEN = "";

    public static void getFingerprintToken(final Context context, Map<String, String> map) {
        if (TokenHelper.checkToken(context)) {
            return;
        }
        String strGZIPToAESAndBase64 = GZIPToAESAndBase64(jsonToGZIP(mapToJson(map)));
        setDeviceAES(context, strGZIPToAESAndBase64);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", TokenHelper.getToken(context));
            jSONObject.put("encrypt_str", strGZIPToAESAndBase64);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DevicesHttpClient.post("http://afflatus.37.com.cn/afflatus/get_token/android", String.valueOf(jSONObject), Detector.mDHttpClient, new DevicesHttpCallback() { // from class: com.sq.tool.sqtools.detector.DevicesFingerprint.1
            @Override // com.sq.tool.sqtools.net.DevicesHttpCallback
            public void onSuccess(String str) {
                try {
                    DevicesFingerprint.TOKEN = new JSONObject(str).optJSONObject(FunctionRouter.KEY_DATA).getString("token");
                    TokenHelper.setToken(context, DevicesFingerprint.TOKEN);
                    LogTools.sendLog("获取token成功,token");
                } catch (Exception unused) {
                    LogTools.sendLog("请求成功但是解析参数异常");
                }
            }

            @Override // com.sq.tool.sqtools.net.DevicesHttpCallback
            public void onFail(int i, String str) {
                LogTools.sendLog("获取token失败，code-->" + i + "; msg-->" + str);
            }
        });
    }

    public static JSONObject mapToJson(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                jSONObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                LogTools.sendLog("map转json失败");
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    public static byte[] jsonToGZIP(JSONObject jSONObject) {
        String strValueOf = String.valueOf(jSONObject);
        if (strValueOf.isEmpty()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(strValueOf.getBytes(StandardCharsets.UTF_8));
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void decompressForGzip(byte[] bArr) {
        try {
            new ByteArrayOutputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(bArr)), "UTF-8");
            char[] cArr = new char[1024];
            StringBuilder sb = new StringBuilder();
            while (true) {
                int i = inputStreamReader.read(cArr, 0, 1024);
                if (i <= 0) {
                    return;
                } else {
                    sb.append(cArr, 0, i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String GZIPToAESAndBase64(byte[] bArr) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec("17d2ff30df8d2042".getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec("17d2ff30df8d2042".getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            byte[] bArrDoFinal = cipher.doFinal(bArr);
            if (Build.VERSION.SDK_INT >= 26) {
                return Base64.getEncoder().encodeToString(bArrDoFinal);
            }
            return android.util.Base64.encodeToString(bArrDoFinal, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDeviceAES(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(DEV_AES, "");
    }

    public static void setDeviceAES(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(DEV_AES, str);
        editorEdit.apply();
    }

    public static String getDevToken(Context context) {
        String token = TokenHelper.getToken(context);
        if (!token.isEmpty() && TokenHelper.checkToken(context)) {
            return token;
        }
        if (!token.isEmpty() && TokenHelper.checkTokenExpired(context)) {
            return token;
        }
        String deviceAES = getDeviceAES(context);
        return !deviceAES.isEmpty() ? deviceAES : "";
    }
}
