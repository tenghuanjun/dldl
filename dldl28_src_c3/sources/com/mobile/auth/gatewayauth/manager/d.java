package com.mobile.auth.gatewayauth.manager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mobile.auth.BuildConfig;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.PnsReporter;
import com.mobile.auth.gatewayauth.model.MonitorStruct;
import com.mobile.auth.gatewayauth.model.UStruct;
import com.mobile.auth.gatewayauth.utils.AESUtils;
import com.mobile.auth.gatewayauth.utils.security.PackageUtils;
import com.nirvana.tools.logger.storage.LoggerIdManager;
import com.tencent.open.SocialOperation;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class d {
    public static String a;
    public static String b;
    public static String c;
    private Context d;
    private String h;
    private String i;
    private String j;
    private Map<String, String> o;
    private Map<String, String> p;
    private Map<String, String> q;
    private Map<String, String> r;
    private com.mobile.auth.p.a s;
    private final String e = "c78623c22e2f6513";
    private String f = UUID.randomUUID().toString();
    private boolean g = true;
    private String k = "";
    private String l = "";
    private com.mobile.auth.o.a m = null;
    private LoggerIdManager n = null;

    public d(Context context) {
        this.d = context.getApplicationContext();
        o();
    }

    private String a(Context context) {
        try {
            try {
                Object objInvoke = Class.forName("com.nirvana.tools.logger.utils.LocalDeviceUtil").getDeclaredMethod("getUmaaId", Context.class).invoke(null, context);
                return objInvoke != null ? objInvoke.toString() : "";
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private String a(JSONObject jSONObject, String str, String str2, String str3, String str4) {
        try {
            try {
                jSONObject.put("c", new JSONObject(a(this.d, str2, str4)));
                jSONObject.put("action", str);
                jSONObject.put("apiLevel", str3);
                jSONObject.put("osType", "Android");
                Map<String, String> map = this.r;
                if (map != null && !map.isEmpty()) {
                    for (String str5 : this.r.keySet()) {
                        jSONObject.put(str5, this.r.get(str5));
                    }
                }
            } catch (JSONException e) {
                this.s.e("AssembleMonitorInfoError!", Log.getStackTraceString(e));
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private String b(Context context) {
        try {
            try {
                Object objInvoke = Class.forName("com.nirvana.tools.logger.utils.LocalDeviceUtil").getDeclaredMethod("getDeviceId", Context.class).invoke(null, context);
                return objInvoke != null ? objInvoke.toString() : "";
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private void o() {
        try {
            this.s = com.mobile.auth.p.a.a(this.d);
            this.m = new com.mobile.auth.o.a(a(), this);
            r();
            this.n = new LoggerIdManager(this.d);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    private String p() {
        try {
            return q() ? a(this.d) : "";
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private boolean q() {
        try {
            return Class.forName("com.nirvana.tools.logger.utils.LocalDeviceUtil") != null;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }

    private boolean r() {
        try {
            return Class.forName("com.nirvana.tools.logger.storage.LoggerIdManager") != null;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }

    public com.mobile.auth.p.a a() {
        try {
            return this.s;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public String a(MonitorStruct monitorStruct) {
        try {
            return a(monitorStruct.getVendorKey(), monitorStruct.getAction(), new UStruct(monitorStruct), monitorStruct.getApiLevel(), monitorStruct.getPhoneNumber());
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public String a(String str, String str2, UStruct uStruct, String str3) {
        try {
            return a(str, str2, uStruct, str3, "");
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public String a(String str, String str2, UStruct uStruct, String str3, String str4) {
        String strEncrypt;
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(this.k)) {
                uStruct.setEt(this.k);
            }
            try {
                JSONObject json = uStruct.toJson();
                Map<String, String> map = this.o;
                if (map != null && !map.isEmpty()) {
                    for (String str5 : this.o.keySet()) {
                        json.put(str5, this.o.get(str5));
                    }
                }
                jSONObject.put("u", json);
                jSONObject.put("u", uStruct.toJson());
                if (TextUtils.isEmpty(str4)) {
                    Map<String, String> mapB = b(uStruct.getPrivateIp());
                    Map<String, String> map2 = this.q;
                    if (map2 != null && !map2.isEmpty()) {
                        mapB.putAll(this.q);
                    }
                    strEncrypt = AESUtils.encrypt(new JSONObject(mapB).toString(), "c78623c22e2f6513");
                } else {
                    HashMap map3 = new HashMap();
                    map3.put("phoneNumber", str4);
                    map3.putAll(b(uStruct.getPrivateIp()));
                    Map<String, String> map4 = this.q;
                    if (map4 != null && !map4.isEmpty()) {
                        map3.putAll(this.q);
                    }
                    strEncrypt = AESUtils.encrypt(new JSONObject(map3).toString(), "c78623c22e2f6513");
                }
                jSONObject.put("s", strEncrypt);
            } catch (Exception e) {
                this.s.e("BuildMonitorError!", Log.getStackTraceString(e));
            }
            return a(jSONObject, str2, str, str3, uStruct.getNetworkType());
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public Map<String, Object> a(Context context, String str, String str2) {
        String str3;
        Map<String, String> map;
        try {
            HashMap map2 = new HashMap();
            map2.put("createTime", Long.valueOf(System.currentTimeMillis()));
            map2.put("osVersion", com.mobile.auth.gatewayauth.utils.f.b());
            map2.put("deviceName", com.mobile.auth.gatewayauth.utils.f.c());
            map2.put("deviceBrand", com.mobile.auth.gatewayauth.utils.f.a());
            map2.put("packageName", PackageUtils.getPackageName(context));
            map2.put("appVersion", PackageUtils.getVersionName(context));
            map2.put(SocialOperation.GAME_SIGNATURE, PackageUtils.getSign(context));
            if (Constant.VENDOR_CUCC.equals(str)) {
                map2.put("vendorKey", Constant.VENDOR_CUXZ);
            } else {
                map2.put("vendorKey", str);
            }
            map2.put("sdkVersion", BuildConfig.VERSION_NAME);
            map2.put("networkType", str2);
            map2.put("monitorVersion", "2.1");
            map2.put("utdid", l());
            map2.put("um_aaid", m());
            map2.put("uniqueId", k());
            map2.put("traceId", this.f);
            map2.put("archiveName", BuildConfig.FLAVOR);
            if (!Constant.VENDOR_CMCC.equals(str)) {
                if (!Constant.VENDOR_CUCC.equals(str)) {
                    if (Constant.VENDOR_CTCC.equals(str)) {
                        str3 = BuildConfig.CTCC_SDK_VERSION;
                    } else if (!Constant.VENDOR_CUXZ.equals(str)) {
                        str3 = "";
                    }
                }
                map2.put("carrierSdkVersion", BuildConfig.CUZX_SDK_VERSION);
                map = this.p;
                if (map != null && !map.isEmpty()) {
                    map2.putAll(this.p);
                }
                return map2;
            }
            str3 = BuildConfig.CMCC_SDK_VERSION;
            map2.put("carrierSdkVersion", str3);
            map = this.p;
            if (map != null) {
                map2.putAll(this.p);
            }
            return map2;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public void a(f fVar) {
        try {
            this.m.a(fVar);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(String str) {
        try {
            this.l = str;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public PnsReporter b() {
        try {
            return this.m;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public String b(String str, String str2, UStruct uStruct, String str3) {
        String strEncrypt;
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(this.k)) {
                uStruct.setEt(this.k);
            }
            try {
                JSONObject json = uStruct.toJson();
                Map<String, String> map = this.o;
                if (map != null && !map.isEmpty()) {
                    for (String str4 : this.o.keySet()) {
                        json.put(str4, this.o.get(str4));
                    }
                }
                jSONObject.put("u", json);
                Map<String, String> map2 = this.q;
                if (map2 == null || map2.isEmpty()) {
                    strEncrypt = "";
                } else {
                    Map<? extends String, ? extends String> map3 = this.q;
                    map3.putAll(map3);
                    strEncrypt = AESUtils.encrypt(new JSONObject(this.q).toString(), "c78623c22e2f6513");
                }
                jSONObject.put("s", strEncrypt);
            } catch (Exception e) {
                this.s.e("BuildMonitorNoSError!", Log.getStackTraceString(e));
            }
            return a(jSONObject, str2, str, str3, uStruct.getNetworkType());
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public Map<String, String> b(String str) {
        try {
            HashMap map = new HashMap();
            if (TextUtils.isEmpty(str)) {
                map.put("innerIP", str);
            }
            map.put("sceneCode", this.l);
            return map;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public synchronized String c() {
        try {
            if (TextUtils.isEmpty(this.h)) {
                return f();
            }
            return this.h;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public void c(String str) {
        if (str != null) {
            try {
                this.k = str;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }
    }

    public synchronized String d() {
        try {
            if (TextUtils.isEmpty(this.i)) {
                return g();
            }
            return this.i;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public synchronized String e() {
        try {
            if (TextUtils.isEmpty(this.j)) {
                return j();
            }
            return this.j;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public synchronized String f() {
        String string;
        try {
            string = UUID.randomUUID().toString();
            this.h = string;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
        return string;
    }

    public synchronized String g() {
        String string;
        try {
            string = UUID.randomUUID().toString();
            this.i = string;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
        return string;
    }

    public synchronized void h() {
        try {
            this.h = null;
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }

    public synchronized void i() {
        try {
            this.i = null;
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }

    public synchronized String j() {
        String string;
        try {
            string = UUID.randomUUID().toString();
            this.j = string;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
        return string;
    }

    public String k() {
        LoggerIdManager loggerIdManager;
        try {
            if (a == null && (loggerIdManager = this.n) != null) {
                a = loggerIdManager.getUniqueId();
            }
            return a;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public String l() {
        try {
            if (!this.g) {
                return null;
            }
            if (b == null && q()) {
                b = b(this.d);
            }
            return b;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public String m() {
        try {
            if (c == null) {
                c = p();
            }
            return c;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public void n() {
        try {
            this.g = false;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
