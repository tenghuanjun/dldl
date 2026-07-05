package com.alipay.sdk.sys;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.alipay.sdk.util.c;
import com.alipay.sdk.util.n;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class a {
    public static final String a = "\"&";
    public static final String b = "&";
    public static final String c = "bizcontext=\"";
    public static final String d = "bizcontext=";
    public static final String e = "\"";
    public static final String f = "appkey";
    public static final String g = "ty";
    public static final String h = "sv";
    public static final String i = "an";
    public static final String j = "setting";
    public static final String k = "av";
    public static final String l = "sdk_start_time";
    public static final String m = "UTF-8";
    private String n;
    private String o;
    private Context p;

    public a(Context context) {
        this.n = "";
        this.o = "";
        this.p = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.n = packageInfo.versionName;
            this.o = packageInfo.packageName;
            this.p = context.getApplicationContext();
        } catch (Exception unused) {
        }
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if (b(str)) {
            return c(str);
        }
        return d(str);
    }

    private boolean b(String str) {
        return !str.contains(a);
    }

    private String c(String str) {
        try {
            String strA = a(str, b, d);
            if (TextUtils.isEmpty(strA)) {
                str = str + b + b(d, "");
            } else {
                int iIndexOf = str.indexOf(strA);
                str = str.substring(0, iIndexOf) + a(strA, d, "", true) + str.substring(iIndexOf + strA.length());
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    private String d(String str) {
        try {
            String strA = a(str, a, c);
            if (TextUtils.isEmpty(strA)) {
                return str + b + b(c, "\"");
            }
            if (!strA.endsWith("\"")) {
                strA = strA + "\"";
            }
            int iIndexOf = str.indexOf(strA);
            return str.substring(0, iIndexOf) + a(strA, c, "\"", false) + str.substring(iIndexOf + strA.length());
        } catch (Throwable unused) {
            return str;
        }
    }

    private String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split(str2);
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            if (!TextUtils.isEmpty(strArrSplit[i2]) && strArrSplit[i2].startsWith(str3)) {
                return strArrSplit[i2];
            }
        }
        return null;
    }

    private String b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        return str + a("", "") + str2;
    }

    public String a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", com.alipay.sdk.cons.a.d);
            jSONObject.put(g, "and_lite");
            jSONObject.put(h, "h.a.3.6.5");
            if (!this.o.contains(j) || !n.b(this.p)) {
                jSONObject.put(i, this.o);
            }
            jSONObject.put(k, this.n);
            jSONObject.put(l, System.currentTimeMillis());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            c.a(th);
            return "";
        }
    }

    private String a(String str, String str2, String str3, boolean z) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject;
        String strSubstring = str.substring(str2.length());
        boolean z2 = false;
        String strSubstring2 = strSubstring.substring(0, strSubstring.length() - str3.length());
        if (strSubstring2.length() >= 2 && strSubstring2.startsWith("\"") && strSubstring2.endsWith("\"")) {
            jSONObject = new JSONObject(strSubstring2.substring(1, strSubstring2.length() - 1));
            z2 = true;
        } else {
            jSONObject = new JSONObject(strSubstring2);
        }
        if (!jSONObject.has("appkey")) {
            jSONObject.put("appkey", com.alipay.sdk.cons.a.d);
        }
        if (!jSONObject.has(g)) {
            jSONObject.put(g, "and_lite");
        }
        if (!jSONObject.has(h)) {
            jSONObject.put(h, "h.a.3.6.5");
        }
        if (!jSONObject.has(i) && (!this.o.contains(j) || !n.b(this.p))) {
            jSONObject.put(i, this.o);
        }
        if (!jSONObject.has(k)) {
            jSONObject.put(k, this.n);
        }
        if (!jSONObject.has(l)) {
            jSONObject.put(l, System.currentTimeMillis());
        }
        String string = jSONObject.toString();
        if (z2) {
            string = "\"" + string + "\"";
        }
        return str2 + string + str3;
    }
}
