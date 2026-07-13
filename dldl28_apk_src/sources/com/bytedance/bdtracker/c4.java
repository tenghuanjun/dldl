package com.bytedance.bdtracker;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.util.HardwareUtils;
import com.bytedance.applog.util.SensitiveUtils;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class c4 implements g4 {
    public static String h;
    public static String i;
    public static String j;
    public static JSONArray k;
    public static volatile String l;
    public static String[] m;
    public static String n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f230a;
    public k3 b;
    public final i3 c;
    public final String d;
    public final d e;
    public final i1 f;
    public final List<String> g = Collections.singletonList("DeviceParamsProvider");

    public c4(d dVar, Context context, i1 i1Var, i3 i3Var) {
        this.e = dVar;
        this.f = i1Var;
        this.d = i1Var.c.getLocalTest() ? "_local" : "";
        Context applicationContext = context.getApplicationContext();
        this.f230a = applicationContext;
        b4 b4Var = new b4();
        this.c = i3Var;
        v3 v3Var = new v3(applicationContext, "snssdk_openudid", i1Var.c.getSpName());
        this.b = v3Var;
        v3Var.f277a = i3Var;
        if (!i1Var.c.getAnonymous()) {
            new Thread(new a4(b4Var)).start();
        }
        a(i1Var.c.getAccount());
    }

    public void a(Account account) {
        i3 i3Var = this.c;
        if (i3Var != null) {
            i3Var.a(account);
        }
    }

    public void a(String str) {
        this.b.a(str);
        this.e.D.debug(this.g, "DeviceParamsProvider#clear clearKey=" + str + " sDeviceId=" + l, new Object[0]);
    }

    public String b() {
        if (!TextUtils.isEmpty(l)) {
            return l;
        }
        l = this.b.c("", "");
        return l;
    }

    public void b(String str) {
        if (!n0.a(str) || n0.a(str, l)) {
            return;
        }
        l = this.b.c(str, l);
    }

    public String c() {
        if (!TextUtils.isEmpty(h)) {
            return h;
        }
        InitConfig initConfig = this.f.c;
        String secureAndroidId = (initConfig == null || initConfig.isAndroidIdEnabled()) ? HardwareUtils.getSecureAndroidId(this.f230a) : "";
        try {
            if (!n0.e(secureAndroidId) || "9774d56d682e549c".equals(secureAndroidId)) {
                SharedPreferences sharedPreferencesA = v3.a(this.f230a, "snssdk_openudid", 0);
                String string = sharedPreferencesA.getString("openudid", null);
                if (n0.e(string)) {
                    this.c.d(string, null);
                    secureAndroidId = string;
                } else {
                    String string2 = new BigInteger(80, new SecureRandom()).toString(16);
                    if (string2.charAt(0) == '-') {
                        string2 = string2.substring(1);
                    }
                    int length = 13 - string2.length();
                    if (length > 0) {
                        StringBuilder sb = new StringBuilder();
                        while (length > 0) {
                            sb.append('F');
                            length--;
                        }
                        sb.append(string2);
                        string2 = sb.toString();
                    }
                    SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                    editorEdit.putString("openudid", string2);
                    editorEdit.apply();
                    secureAndroidId = string2;
                }
            } else {
                secureAndroidId = this.b.d(null, secureAndroidId);
            }
        } catch (Throwable th) {
            this.e.D.error(this.g, "getOpenUdid failed", th, new Object[0]);
        }
        if (!TextUtils.isEmpty(secureAndroidId)) {
            StringBuilder sbA = a.a(secureAndroidId);
            sbA.append(this.d);
            secureAndroidId = sbA.toString();
        }
        if (!TextUtils.isEmpty(secureAndroidId)) {
            h = secureAndroidId;
        }
        return secureAndroidId;
    }

    public String d() {
        if (!TextUtils.isEmpty(n)) {
            return n;
        }
        try {
            String strE = this.b.e(null, SensitiveUtils.getSerialNumber(this.f230a));
            if (!TextUtils.isEmpty(strE)) {
                strE = strE + this.d;
            }
            n = strE;
            return strE;
        } catch (Throwable th) {
            this.e.D.error(this.g, "getSerialNumber failed", th, new Object[0]);
            return null;
        }
    }

    public String[] e() {
        String[] strArr = m;
        if (strArr != null && strArr.length > 0) {
            return strArr;
        }
        try {
            String[] strArrA = this.b.a((String[]) null, SensitiveUtils.getSimSerialNumbers(this.f230a));
            if (strArrA == null) {
                strArrA = new String[0];
            }
            for (int i2 = 0; i2 < strArrA.length; i2++) {
                strArrA[i2] = strArrA[i2] + this.d;
            }
            m = strArrA;
            return strArrA;
        } catch (Throwable th) {
            this.e.D.error(this.g, "getSimSerialNumbers failed", th, new Object[0]);
            return null;
        }
    }

    public String f() {
        if (!TextUtils.isEmpty(j)) {
            return j;
        }
        try {
            String strF = this.b.f(null, this.f.c.isImeiEnable() ? SensitiveUtils.getDeviceId(this.f230a) : this.f.c.getAppImei());
            if (!TextUtils.isEmpty(strF)) {
                strF = strF + this.d;
            }
            j = strF;
            return strF;
        } catch (Throwable th) {
            this.e.D.error(this.g, "getUdId failed", th, new Object[0]);
            return null;
        }
    }

    public JSONArray g() {
        JSONArray jSONArray = k;
        if (jSONArray != null) {
            return jSONArray;
        }
        try {
            if (!this.f.c.isImeiEnable()) {
                return new JSONArray();
            }
            JSONArray multiImeiFromSystem = SensitiveUtils.getMultiImeiFromSystem(this.f230a);
            if (multiImeiFromSystem == null) {
                multiImeiFromSystem = SensitiveUtils.getMultiImeiFallback(this.f230a);
            }
            JSONArray jSONArray2 = new JSONArray(this.b.g(null, multiImeiFromSystem.toString()));
            if (!TextUtils.isEmpty(this.d)) {
                String str = this.d;
                if (jSONArray2.length() != 0) {
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        JSONObject jSONObjectOptJSONObject = jSONArray2.optJSONObject(i2);
                        if (jSONObjectOptJSONObject != null) {
                            String strOptString = jSONObjectOptJSONObject.optString("id");
                            if (!TextUtils.isEmpty(strOptString)) {
                                jSONObjectOptJSONObject.remove("id");
                                jSONObjectOptJSONObject.put("id", strOptString + str);
                            }
                        }
                    }
                }
            }
            k = jSONArray2;
            return jSONArray2;
        } catch (Throwable th) {
            this.e.D.error(this.g, "getUdIdList failed", th, new Object[0]);
            return null;
        }
    }

    public String a() {
        if (!TextUtils.isEmpty(i)) {
            return i;
        }
        try {
            SharedPreferences sharedPreferencesA = v3.a(this.f230a, "snssdk_openudid", 0);
            String string = sharedPreferencesA.getString("clientudid", null);
            if (n0.e(string)) {
                this.c.b(string, null);
            } else {
                string = UUID.randomUUID().toString();
                SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                editorEdit.putString("clientudid", string);
                editorEdit.apply();
            }
            if (!TextUtils.isEmpty(string)) {
                string = string + this.d;
            }
            i = string;
            return string;
        } catch (Throwable th) {
            this.e.D.error(this.g, "getClientUDID failed", th, new Object[0]);
            return "";
        }
    }
}
