package com.mobile.auth.ab;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class b {
    public static String a(String str, String str2) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            try {
                boolean z = true;
                for (Map.Entry<?, ?> entry : a(new JSONObject(str).toString()).entrySet()) {
                    stringBuffer.append(z ? "" : str2);
                    stringBuffer.append((String) entry.getKey());
                    stringBuffer.append("=");
                    stringBuffer.append(entry.getValue() != null ? entry.getValue() : "");
                    z = false;
                }
            } catch (Exception e) {
                d.a(e);
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    private static TreeMap<?, ?> a(String str) {
        try {
            try {
                JSONObject jSONObject = new JSONObject(str);
                TreeMap<?, ?> treeMap = new TreeMap<>();
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    treeMap.put(next, jSONObject.getString(next));
                }
                return treeMap;
            } catch (Exception e) {
                d.a(e);
                return null;
            }
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }
}
