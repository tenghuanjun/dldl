package com.sqwan.bugless.net;

import com.sqwan.bugless.util.LogUtil;
import com.sqwan.common.constants.SqConstants;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SignHelper {
    public static JSONObject sign(JSONObject json, String secret) throws IOException {
        LogUtil.d("签名前：" + json);
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest((json.toString() + secret).getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append("0");
                }
                sb.append(hexString);
            }
            try {
                json.put(SqConstants.SIGN, sb.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LogUtil.d("签名后：" + json);
            return json;
        } catch (GeneralSecurityException e2) {
            throw new IOException(e2);
        }
    }
}
