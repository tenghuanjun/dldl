package com.nirvana.tools.logger.uaid;

import com.alipay.deviceid.DeviceTokenClient;
import com.alipay.sdk.sys.a;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class UaidUtils {
    public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMddhhmmssSSS");

    public static String composeCmccGetTokenParams(String str, String str2) throws JSONException {
        String string;
        String str3 = TIMESTAMP_FORMAT.format(new Date());
        String string2 = UUID.randomUUID().toString();
        String string3 = UUID.randomUUID().toString();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append("3");
            stringBuffer.append(string3);
            stringBuffer.append(str3);
            stringBuffer.append(string2);
            stringBuffer.append("1.0");
            stringBuffer.append(str2);
            string = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            string = null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", "1.0");
        jSONObject.put("timestamp", str3);
        jSONObject.put("appId", str);
        jSONObject.put("businessType", "3");
        jSONObject.put(DeviceTokenClient.INARGS_FACE_TRACEID, string2);
        jSONObject.put("msgId", string3);
        jSONObject.put("sign", string);
        return jSONObject.toString();
    }

    public static String composeCuccAuthAddrParams(String str) {
        return "appid=" + str;
    }

    public static String composeCuccAuthCodeParams(String str, String str2) {
        return "appid=" + str + a.b + "private_ip=" + str2;
    }
}
