package com.sq.tools.encrypt;

import android.content.Context;
import android.text.TextUtils;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sq.tools.Logger;
import com.sq.tools.utils.DecodeUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class EncryptApi {
    private EncryptApi() {
    }

    public static byte[] desEncrypt(Context context, byte[] bArr) {
        return desEncrypt(desKey(context), bArr);
    }

    public static byte[] desDecrypt(Context context, byte[] bArr) {
        return desDecrypt(desKey(context), bArr);
    }

    public static byte[] desEncrypt(String str, byte[] bArr) {
        return DES.encrypt(bArr, str);
    }

    public static byte[] desDecrypt(String str, byte[] bArr) {
        return DES.decrypt(bArr, str);
    }

    public static String sign(Context context, JSONObject jSONObject) {
        return sign(signKey(context), jSONObject);
    }

    public static String sign(String str, JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList = new ArrayList();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            arrayList.add(itKeys.next());
        }
        Collections.sort(arrayList);
        for (String str2 : arrayList) {
            try {
                if (!TextUtils.isEmpty(jSONObject.getString(str2))) {
                    sb.append(str2);
                    sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                    sb.append(jSONObject.getString(str2));
                }
            } catch (JSONException e) {
                Logger.warning("Sign json object has Exception", e);
            }
        }
        return sign(str, sb.toString());
    }

    public static String sign(Context context, String str) {
        return sign(signKey(context), str);
    }

    public static String sign(String str, String str2) {
        return DecodeUtils.MD5(str2 + str).toLowerCase();
    }

    protected static String desKey(Context context) {
        return readKey(context, "httpdns/dk");
    }

    protected static String signKey(Context context) {
        return readKey(context, "sq_games/sk");
    }

    private static String readKey(Context context, String str) {
        try {
            String[] list = context.getAssets().list(str);
            if (list == null || list.length == 0) {
                Logger.error("fail, there is no files in path %s", str);
                return "";
            }
            int length = list.length;
            char[] cArr = new char[length];
            for (String str2 : list) {
                if (!TextUtils.isEmpty(str2) && str2.contains("#")) {
                    try {
                        int i = Integer.parseInt(str2.split("#")[0]);
                        if (i < length) {
                            StringBuilder sb = new StringBuilder();
                            for (int iIndexOf = str2.indexOf(35) + 1; iIndexOf < str2.length() && Character.isDigit(str2.charAt(iIndexOf)); iIndexOf++) {
                                sb.append(str2.charAt(iIndexOf));
                            }
                            cArr[i] = (char) Integer.parseInt(sb.toString());
                        }
                    } catch (NumberFormatException e) {
                        Logger.error("Read Key parse int exception", e);
                    }
                }
            }
            return new String(cArr).trim();
        } catch (IOException e2) {
            Logger.error("Read Key exception since list failed", e2);
            return "";
        }
    }
}
