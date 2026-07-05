package com.bytedance.sdk.openadsdk.api.plugin.c;

import android.os.Build;
import android.text.TextUtils;
import com.bytedance.JProtect;
import java.security.SecureRandom;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b {
    @JProtect
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String strA = a();
        String strA2 = a(strA, 32);
        String strB = b();
        String strA3 = null;
        if (strA2 != null && strB != null) {
            strA3 = a.a(str, strB, strA2);
        }
        return 3 + strA + strB + strA3;
    }

    @JProtect
    public static JSONObject a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null) {
            return jSONObject2;
        }
        try {
            try {
                String strA = a(jSONObject.toString());
                if (!TextUtils.isEmpty(strA)) {
                    jSONObject2.put(com.igexin.push.core.b.Z, strA);
                    jSONObject2.put("cypher", 3);
                } else {
                    jSONObject2.put(com.igexin.push.core.b.Z, jSONObject.toString());
                    jSONObject2.put("cypher", 0);
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            jSONObject2.put(com.igexin.push.core.b.Z, jSONObject.toString());
            jSONObject2.put("cypher", 0);
        }
        return jSONObject2;
    }

    public static String a() {
        String strA = a(16);
        if (strA == null || strA.length() != 32) {
            return null;
        }
        return strA;
    }

    public static String a(String str, int i) {
        if (str == null || str.length() != i) {
            return null;
        }
        int i2 = i / 2;
        return str.substring(i2, i) + str.substring(0, i2);
    }

    public static String b() {
        String strA = a(8);
        if (strA == null || strA.length() != 16) {
            return null;
        }
        return strA;
    }

    public static String a(int i) {
        try {
            byte[] bArr = new byte[i];
            c().nextBytes(bArr);
            return c.a(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    private static SecureRandom c() {
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                return SecureRandom.getInstanceStrong();
            } catch (Throwable unused) {
                return new SecureRandom();
            }
        }
        return new SecureRandom();
    }
}
