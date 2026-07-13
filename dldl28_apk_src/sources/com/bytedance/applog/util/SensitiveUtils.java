package com.bytedance.applog.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.autofill.HintConstants;
import com.bytedance.applog.ISensitiveInfoProvider;
import com.bytedance.applog.Level;
import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.bdtracker.e3;
import com.bytedance.bdtracker.g3;
import com.bytedance.bdtracker.n0;
import com.bytedance.bdtracker.v3;
import com.hjq.permissions.Permission;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SensitiveUtils {
    public static final String CHANNEL_APP_KEY = "UMENG_APPKEY";
    public static final String KEY_ALIYUN_UUID = "aliyun_uuid";
    public static final String KEY_BUILD_SERIAL = "build_serial";
    public static final String KEY_MAC = "mac_address";
    public static final String KEY_MC = "mc";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<String> f196a = Collections.singletonList("SensitiveUtils");

    public static class a implements v3.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f197a;

        public a(Context context) {
            this.f197a = context;
        }

        @Override // com.bytedance.bdtracker.v3.a
        public String a() {
            if (!SensitiveUtils.hasReadPhoneStatePermission(this.f197a)) {
                return null;
            }
            LoggerImpl.global().debug(SensitiveUtils.f196a, "[DeviceMeta&READ_PHONE_STATE] Try to get device id.", new Object[0]);
            try {
                TelephonyManager telephonyManager = (TelephonyManager) this.f197a.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
                return telephonyManager == null ? "" : telephonyManager.getDeviceId();
            } catch (Throwable th) {
                LoggerImpl.global().error(SensitiveUtils.f196a, "Get device id failed", th, new Object[0]);
                return null;
            }
        }
    }

    public static class b implements v3.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f198a;

        public b(Context context) {
            this.f198a = context;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00b4  */
        /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.String[]] */
        @Override // com.bytedance.bdtracker.v3.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.String a() {
            /*
                r8 = this;
                r0 = 0
                r1 = 0
                int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> La2
                r3 = 22
                if (r2 < r3) goto L63
                android.content.Context r2 = r8.f198a     // Catch: java.lang.Throwable -> La2
                boolean r2 = com.bytedance.applog.util.SensitiveUtils.hasReadPhoneStatePermission(r2)     // Catch: java.lang.Throwable -> La2
                if (r2 == 0) goto L61
                android.content.Context r2 = r8.f198a     // Catch: java.lang.Throwable -> La2
                android.telephony.SubscriptionManager r2 = com.bun.miitmdid.x$$ExternalSyntheticApiModelOutline0.m(r2)     // Catch: java.lang.Throwable -> La2
                com.bytedance.applog.log.IAppLogLogger r3 = com.bytedance.applog.log.LoggerImpl.global()     // Catch: java.lang.Throwable -> L51
                java.util.List<java.lang.String> r4 = com.bytedance.applog.util.SensitiveUtils.f196a     // Catch: java.lang.Throwable -> L51
                java.lang.String r5 = "[DeviceMeta&READ_PHONE_STATE] Try to get active subscription info list."
                java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L51
                r3.debug(r4, r5, r6)     // Catch: java.lang.Throwable -> L51
                java.util.List r2 = com.bun.miitmdid.x$$ExternalSyntheticApiModelOutline0.m6346m(r2)     // Catch: java.lang.Throwable -> L51
                if (r2 == 0) goto L50
                boolean r3 = r2.isEmpty()     // Catch: java.lang.Throwable -> L51
                if (r3 == 0) goto L30
                goto L50
            L30:
                int r3 = r2.size()     // Catch: java.lang.Throwable -> L51
                java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L51
                r4 = 0
            L37:
                int r5 = r2.size()     // Catch: java.lang.Throwable -> L4e
                if (r4 >= r5) goto Lb1
                java.lang.Object r5 = r2.get(r4)     // Catch: java.lang.Throwable -> L4e
                android.telephony.SubscriptionInfo r5 = com.bun.miitmdid.x$$ExternalSyntheticApiModelOutline0.m6342m(r5)     // Catch: java.lang.Throwable -> L4e
                java.lang.String r5 = com.bun.miitmdid.x$$ExternalSyntheticApiModelOutline0.m(r5)     // Catch: java.lang.Throwable -> L4e
                r3[r4] = r5     // Catch: java.lang.Throwable -> L4e
                int r4 = r4 + 1
                goto L37
            L4e:
                r2 = move-exception
                goto L53
            L50:
                return r1
            L51:
                r2 = move-exception
                r3 = r1
            L53:
                com.bytedance.applog.log.IAppLogLogger r4 = com.bytedance.applog.log.LoggerImpl.global()     // Catch: java.lang.Throwable -> La0
                java.util.List<java.lang.String> r5 = com.bytedance.applog.util.SensitiveUtils.f196a     // Catch: java.lang.Throwable -> La0
                java.lang.String r6 = "Get iccid failed"
                java.lang.Object[] r7 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> La0
                r4.error(r5, r6, r2, r7)     // Catch: java.lang.Throwable -> La0
                goto Lb1
            L61:
                r3 = r1
                goto Lb1
            L63:
                r2 = 1
                java.lang.String[] r3 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> La2
                android.content.Context r2 = r8.f198a     // Catch: java.lang.Throwable -> La0
                boolean r2 = com.bytedance.applog.util.SensitiveUtils.hasReadPhoneStatePermission(r2)     // Catch: java.lang.Throwable -> La0
                if (r2 == 0) goto Lb1
                com.bytedance.applog.log.IAppLogLogger r2 = com.bytedance.applog.log.LoggerImpl.global()     // Catch: java.lang.Throwable -> La0
                java.util.List<java.lang.String> r4 = com.bytedance.applog.util.SensitiveUtils.f196a     // Catch: java.lang.Throwable -> La0
                java.lang.String r5 = "[DeviceMeta&READ_PHONE_STATE] Try to get sim serial number."
                java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> La0
                r2.debug(r4, r5, r6)     // Catch: java.lang.Throwable -> La0
                android.content.Context r2 = r8.f198a     // Catch: java.lang.Throwable -> La0
                java.lang.String r4 = "phone"
                java.lang.Object r2 = r2.getSystemService(r4)     // Catch: java.lang.Throwable -> La0
                android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch: java.lang.Throwable -> La0
                if (r2 != 0) goto L8a
                java.lang.String r2 = ""
                goto L8e
            L8a:
                java.lang.String r2 = r2.getSimSerialNumber()     // Catch: java.lang.Throwable -> L91
            L8e:
                r3[r0] = r2     // Catch: java.lang.Throwable -> L91
                goto Lb1
            L91:
                r2 = move-exception
                com.bytedance.applog.log.IAppLogLogger r4 = com.bytedance.applog.log.LoggerImpl.global()     // Catch: java.lang.Throwable -> La0
                java.util.List<java.lang.String> r5 = com.bytedance.applog.util.SensitiveUtils.f196a     // Catch: java.lang.Throwable -> La0
                java.lang.String r6 = "Get sim serial number failed"
                java.lang.Object[] r7 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> La0
                r4.error(r5, r6, r2, r7)     // Catch: java.lang.Throwable -> La0
                goto Lb1
            La0:
                r2 = move-exception
                goto La4
            La2:
                r2 = move-exception
                r3 = r1
            La4:
                com.bytedance.applog.log.IAppLogLogger r4 = com.bytedance.applog.log.LoggerImpl.global()
                java.util.List<java.lang.String> r5 = com.bytedance.applog.util.SensitiveUtils.f196a
                java.lang.Object[] r0 = new java.lang.Object[r0]
                java.lang.String r6 = "Get accId failed"
                r4.error(r5, r6, r2, r0)
            Lb1:
                if (r3 != 0) goto Lb4
                goto Lba
            Lb4:
                java.lang.String r0 = ","
                java.lang.String r1 = android.text.TextUtils.join(r0, r3)
            Lba:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.util.SensitiveUtils.b.a():java.lang.String");
        }
    }

    public static class c implements v3.a {
        @Override // com.bytedance.bdtracker.v3.a
        public String a() throws SocketException {
            if (Build.VERSION.SDK_INT < 23) {
                return "";
            }
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if ("wlan0".equals(networkInterfaceNextElement.getName())) {
                    IAppLogLogger iAppLogLoggerGlobal = LoggerImpl.global();
                    List<String> list = SensitiveUtils.f196a;
                    StringBuilder sbA = com.bytedance.bdtracker.a.a("[DeviceMeta] Try to get hardware address from ");
                    sbA.append(networkInterfaceNextElement.getName());
                    sbA.append(".");
                    iAppLogLoggerGlobal.debug(list, sbA.toString(), new Object[0]);
                    byte[] hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                    if (hardwareAddress != null && hardwareAddress.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (byte b : hardwareAddress) {
                            sb.append(String.format("%02X:", Byte.valueOf(b)));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        return sb.toString();
                    }
                }
            }
            return "";
        }
    }

    public static class d implements v3.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f199a;

        public d(Context context) {
            this.f199a = context;
        }

        @Override // com.bytedance.bdtracker.v3.a
        public String a() {
            if (Build.VERSION.SDK_INT < 26 || !SensitiveUtils.hasReadPhoneStatePermission(this.f199a)) {
                return null;
            }
            LoggerImpl.global().debug(SensitiveUtils.f196a, "[DeviceMeta&READ_PHONE_STATE] Try to get imei and meid info", new Object[0]);
            int activeSubscriptionInfoCount = SubscriptionManager.from(this.f199a).getActiveSubscriptionInfoCount();
            JSONArray jSONArray = new JSONArray();
            TelephonyManager telephonyManager = (TelephonyManager) this.f199a.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
            for (int i = 0; i < activeSubscriptionInfoCount; i++) {
                String imei = "";
                try {
                    jSONArray.put(SensitiveUtils.a(telephonyManager == null ? "" : telephonyManager.getMeid(i), i, "meid"));
                } catch (Throwable unused) {
                }
                if (telephonyManager == null) {
                    jSONArray.put(SensitiveUtils.a(imei, i, "imei"));
                } else {
                    try {
                        imei = telephonyManager.getImei(i);
                        jSONArray.put(SensitiveUtils.a(imei, i, "imei"));
                    } catch (Throwable unused2) {
                    }
                }
            }
            return jSONArray.toString();
        }
    }

    public static class e implements v3.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f200a;

        public e(Context context) {
            this.f200a = context;
        }

        @Override // com.bytedance.bdtracker.v3.a
        public String a() {
            if (Build.VERSION.SDK_INT < 26 || this.f200a.getApplicationInfo().targetSdkVersion < 26 || !SensitiveUtils.hasReadPhoneStatePermission(this.f200a)) {
                return "";
            }
            LoggerImpl.global().debug(SensitiveUtils.f196a, "[DeviceMeta&READ_PHONE_STATE] Try to get build serial.", new Object[0]);
            return Build.getSerial();
        }
    }

    public static String a(Context context, int i) {
        Object objInvoke = null;
        if (!hasReadPhoneStatePermission(context)) {
            return null;
        }
        LoggerImpl.global().debug(f196a, "[READ_PHONE_STATE] Try to get device id.", new Object[0]);
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
            if (telephonyManager != null) {
                Method method = telephonyManager.getClass().getMethod("getDeviceId", a("getDeviceId"));
                if (i >= 0) {
                    objInvoke = method.invoke(telephonyManager, Integer.valueOf(i));
                }
            }
        } catch (Throwable th) {
            LoggerImpl.global().error(f196a, "Read phone info failed.", th, new Object[0]);
        }
        return (String) objInvoke;
    }

    public static JSONObject a(String str, int i, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("id", str);
            jSONObject.put("slot_index", i);
            jSONObject.put("type", str2);
        }
        return jSONObject;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
    
        com.bytedance.applog.log.LoggerImpl.global().debug(com.bytedance.applog.util.SensitiveUtils.f196a, "params length:" + r2.length, new java.lang.Object[0]);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Class[] a(java.lang.String r8) {
        /*
            r0 = 0
            r1 = 1
            r2 = 0
            java.lang.Class<android.telephony.TelephonyManager> r3 = android.telephony.TelephonyManager.class
            java.lang.reflect.Method[] r3 = r3.getDeclaredMethods()     // Catch: java.lang.Exception -> L41
            int r4 = r3.length     // Catch: java.lang.Exception -> L41
            r5 = 0
        Lb:
            if (r5 >= r4) goto L51
            r6 = r3[r5]     // Catch: java.lang.Exception -> L41
            java.lang.String r7 = r6.getName()     // Catch: java.lang.Exception -> L41
            boolean r7 = r8.equals(r7)     // Catch: java.lang.Exception -> L41
            if (r7 == 0) goto L3e
            java.lang.Class[] r2 = r6.getParameterTypes()     // Catch: java.lang.Exception -> L41
            int r6 = r2.length     // Catch: java.lang.Exception -> L41
            if (r6 < r1) goto L3e
            com.bytedance.applog.log.IAppLogLogger r3 = com.bytedance.applog.log.LoggerImpl.global()     // Catch: java.lang.Exception -> L41
            java.util.List<java.lang.String> r4 = com.bytedance.applog.util.SensitiveUtils.f196a     // Catch: java.lang.Exception -> L41
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L41
            r5.<init>()     // Catch: java.lang.Exception -> L41
            java.lang.String r6 = "params length:"
            r5.append(r6)     // Catch: java.lang.Exception -> L41
            int r6 = r2.length     // Catch: java.lang.Exception -> L41
            r5.append(r6)     // Catch: java.lang.Exception -> L41
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L41
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L41
            r3.debug(r4, r5, r6)     // Catch: java.lang.Exception -> L41
            goto L51
        L3e:
            int r5 = r5 + 1
            goto Lb
        L41:
            r3 = move-exception
            com.bytedance.applog.log.IAppLogLogger r4 = com.bytedance.applog.log.LoggerImpl.global()
            java.util.List<java.lang.String> r5 = com.bytedance.applog.util.SensitiveUtils.f196a
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r0] = r8
            java.lang.String r8 = "TelephonyManager reflect method:{} failed"
            r4.error(r5, r8, r3, r1)
        L51:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.util.SensitiveUtils.a(java.lang.String):java.lang.Class[]");
    }

    public static void addSensitiveParamsForUrlQuery(g3 g3Var, StringBuilder sb, JSONObject jSONObject) {
        e3.a(sb, KEY_BUILD_SERIAL, (String) g3Var.a(jSONObject, KEY_BUILD_SERIAL, (Object) null, (Class<Object>) String.class));
        JSONArray jSONArray = (JSONArray) g3Var.a(jSONObject, "sim_serial_number", (Object) null, (Class<Object>) JSONArray.class);
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        try {
            StringBuilder sb2 = new StringBuilder(((JSONObject) jSONArray.get(0)).optString("sim_serial_number"));
            for (int i = 1; i < jSONArray.length(); i++) {
                String strOptString = ((JSONObject) jSONArray.get(i)).optString("sim_serial_number");
                sb2.append(",");
                sb2.append(strOptString);
            }
            e3.a(sb, "sim_serial_number", sb2.toString());
        } catch (JSONException e2) {
            LoggerImpl.global().error(f196a, "failed to get sim_serial_number", e2, new Object[0]);
        }
    }

    public static void appendSensitiveParams(g3 g3Var, JSONObject jSONObject, Map<String, String> map, boolean z, Level level) {
        if (level == Level.L0) {
            if (z) {
                String str = (String) g3Var.a(jSONObject, KEY_MC, (Object) null, (Class<Object>) String.class);
                String str2 = (String) g3Var.a(jSONObject, "udid", (Object) null, (Class<Object>) String.class);
                if (!TextUtils.isEmpty(str)) {
                    map.put(KEY_MAC, str);
                }
                if (n0.a(str2)) {
                    map.put("uuid", str2);
                }
            }
            String str3 = (String) g3Var.a(jSONObject, KEY_ALIYUN_UUID, (Object) null, (Class<Object>) String.class);
            if (!TextUtils.isEmpty(str3)) {
                map.put(KEY_ALIYUN_UUID, str3);
            }
        }
        String str4 = (String) g3Var.a(jSONObject, KEY_BUILD_SERIAL, (Object) null, (Class<Object>) String.class);
        if (TextUtils.isEmpty(str4)) {
            return;
        }
        map.put(KEY_BUILD_SERIAL, str4);
    }

    public static String getDeviceId(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return v3.a(context).a("TelephonyManager.getDeviceId", new a(context));
        } catch (Throwable th) {
            LoggerImpl.global().error(f196a, "Get device id failed", th, new Object[0]);
            return null;
        }
    }

    public static String getMacAddress(ISensitiveInfoProvider iSensitiveInfoProvider, Context context) {
        return iSensitiveInfoProvider != null ? iSensitiveInfoProvider.getMac() : getMacAddressFromSystem(context);
    }

    public static String getMacAddressFromSystem(Context context) {
        String strA;
        try {
            strA = v3.a(context).a("NetworkInterface.getHardwareAddress", new c());
        } catch (Throwable th) {
            LoggerImpl.global().error(f196a, "Get HardwareAddress failed", th, new Object[0]);
            strA = null;
        }
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            return (wifiManager == null || wifiManager.getConnectionInfo() == null) ? strA : wifiManager.getConnectionInfo().getMacAddress();
        } catch (Throwable th2) {
            LoggerImpl.global().error(f196a, "Get MacAddress failed", th2, new Object[0]);
            return strA;
        }
    }

    public static JSONArray getMultiImeiFallback(Context context) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(a(a(context, 0), 0, "unknown"));
        jSONArray.put(a(a(context, 1), 1, "unknown"));
        return jSONArray;
    }

    public static JSONArray getMultiImeiFromSystem(Context context) {
        try {
            String strA = v3.a(context).a("TelephonyManager.getMultiImei", new d(context));
            return TextUtils.isEmpty(strA) ? new JSONArray() : new JSONArray(strA);
        } catch (Throwable th) {
            LoggerImpl.global().error(f196a, "Failed to get meid 0", th, new Object[0]);
            return null;
        }
    }

    public static String getSerialNumber(Context context) {
        String strA = null;
        if (context == null) {
            return null;
        }
        try {
            strA = v3.a(context).a("Build.getSerial", new e(context));
        } catch (Throwable th) {
            LoggerImpl.global().error(f196a, "Build getSerial failed.", th, new Object[0]);
        }
        if (TextUtils.isEmpty(strA) || TextUtils.equals(strA, "unknown")) {
            strA = Build.SERIAL;
        }
        return (TextUtils.isEmpty(strA) || TextUtils.equals(strA, "unknown")) ? "" : strA;
    }

    public static String[] getSimSerialNumbers(Context context) {
        if (context == null) {
            return new String[0];
        }
        try {
            String strA = v3.a(context).a("SubscriptionInfo.getIccid", new b(context));
            if (!TextUtils.isEmpty(strA)) {
                return strA.split(",");
            }
        } catch (Throwable th) {
            LoggerImpl.global().error(f196a, "getSimSerialNumbers failed", th, new Object[0]);
        }
        return new String[0];
    }

    public static boolean hasPermission(Context context, String str) {
        if (str == null) {
            return false;
        }
        try {
            return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
        } catch (Throwable th) {
            LoggerImpl.global().error(f196a, "check has permission failed.", th, new Object[0]);
            return false;
        }
    }

    public static boolean hasReadPhoneStatePermission(Context context) {
        return hasPermission(context, Build.VERSION.SDK_INT > 28 ? "android.permission.READ_PRECISE_PHONE_STATE" : Permission.READ_PHONE_STATE);
    }

    public static boolean validMultiImei(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return validMultiImei(new JSONArray(str));
            } catch (JSONException e2) {
                LoggerImpl.global().error(f196a, "JSON handle failed", e2, new Object[0]);
            }
        }
        return false;
    }

    public static boolean validMultiImei(JSONArray jSONArray) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(jSONObjectOptJSONObject.optString("id"))) {
                return true;
            }
        }
        return false;
    }
}
