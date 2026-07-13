package com.bytedance.bdtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.applog.log.EventBus;
import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import com.volcengine.common.contant.CommonConstants;
import io.reactivex.rxjava3.annotations.SchedulerSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class k1 {
    public static final String[] l = {"channel", "package", "app_version"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile boolean f274a;
    public final Context b;
    public final i1 c;
    public volatile JSONObject d;
    public boolean e;
    public final SharedPreferences g;
    public final g4 h;
    public final d i;

    @Deprecated
    public boolean k;
    public final ArrayList<d1> f = new ArrayList<>(32);
    public int j = 0;

    public class a implements EventBus.DataFetcher {
        public a() {
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(CommonConstants.key_appId, k1.this.i.m);
                jSONObject.put("did", k1.this.d.optString(MonitorConstants.KEY_DEVICE_ID, ""));
                jSONObject.put("bdDid", k1.this.c());
                jSONObject.put("ssid", k1.this.j());
                jSONObject.put("installId", k1.this.f());
                jSONObject.put("uuid", k1.this.l());
                jSONObject.put("uuidType", k1.this.m());
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public k1(d dVar, Context context, i1 i1Var) {
        this.k = false;
        this.i = dVar;
        this.b = context;
        this.c = i1Var;
        SharedPreferences sharedPreferences = i1Var.f;
        this.g = sharedPreferences;
        this.d = new JSONObject();
        this.h = dVar.d.a(dVar, context, i1Var);
        this.k = sharedPreferences.getBoolean("forbid_report_phone_detail_info", false);
        boolean z = i1Var.f.getBoolean("is_first_app_launch", true);
        String userUniqueId = i1Var.c.getUserUniqueId();
        String userUniqueIdType = i1Var.c.getUserUniqueIdType();
        if (n0.d(userUniqueId) && z) {
            h(userUniqueId);
        }
        if (n0.d(userUniqueIdType) && z) {
            i(userUniqueIdType);
        }
        if (z) {
            i1Var.f.edit().putBoolean("is_first_app_launch", false).apply();
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        jSONObject.put(str, str2);
    }

    public final String a(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public final synchronized void a(String str) {
        String strOptString = this.d.optString("ab_sdk_version");
        if (!TextUtils.isEmpty(strOptString)) {
            for (String str2 : strOptString.split(",")) {
                if (!TextUtils.isEmpty(str2) && str2.equals(str)) {
                    this.i.D.debug(Collections.singletonList("DeviceManager"), "addExposedVid ready added: " + strOptString, new Object[0]);
                    return;
                }
            }
            str = strOptString + "," + str;
        }
        e(str);
        a(str, this.c.c());
    }

    public void b(String str) {
        g4 g4Var = this.h;
        if (g4Var instanceof c4) {
            c4 c4Var = (c4) g4Var;
            Context context = this.b;
            c4Var.e.D.debug(c4Var.g, "DeviceParamsProvider#clearDidAndIid clearKey=" + str + " sDeviceId=" + c4.l, new Object[0]);
            if (!TextUtils.isEmpty(str)) {
                c4.l = null;
                String str2 = "clear_key_prefix" + str;
                SharedPreferences sharedPreferencesA = v3.a(context, c4Var.f.c.getSpName(), 0);
                if (sharedPreferencesA.getBoolean(str2, false)) {
                    c4Var.e.D.debug(c4Var.g, "clearKey:{} is already cleared", str);
                } else {
                    SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                    editorEdit.putBoolean(str2, true);
                    if (sharedPreferencesA.contains(MonitorConstants.KEY_DEVICE_ID)) {
                        editorEdit.remove(MonitorConstants.KEY_DEVICE_ID);
                    }
                    if (sharedPreferencesA.contains("install_id")) {
                        editorEdit.remove("install_id");
                    }
                    editorEdit.apply();
                    c4Var.b.a(MonitorConstants.KEY_DEVICE_ID);
                    c4Var.e.D.debug(c4Var.g, "clearKey:{} installId and deviceId finish", str);
                }
            }
        }
        this.c.f.edit().remove("device_token").commit();
    }

    public String c() {
        return this.d.optString("bd_did", "");
    }

    public final Set<String> c(String str) {
        String[] strArrSplit;
        HashSet hashSet = new HashSet();
        if (!TextUtils.isEmpty(str) && (strArrSplit = str.split(",")) != null && strArrSplit.length > 0) {
            for (String str2 : strArrSplit) {
                if (!TextUtils.isEmpty(str2)) {
                    hashSet.add(str2);
                }
            }
        }
        return hashSet;
    }

    public final JSONObject d() {
        if (this.f274a) {
            return this.d.optJSONObject(SchedulerSupport.CUSTOM);
        }
        i1 i1Var = this.c;
        if (i1Var != null) {
            try {
                return new JSONObject(i1Var.d.getString("header_custom_info", null));
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public void d(String str) {
        JSONObject jSONObjectD;
        if (TextUtils.isEmpty(str) || (jSONObjectD = d()) == null || !jSONObjectD.has(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        n0.a(jSONObject, jSONObjectD);
        jSONObject.remove(str);
        b(jSONObject);
    }

    public JSONObject e() {
        if (this.f274a) {
            return this.d;
        }
        return null;
    }

    public String f() {
        return this.d.optString("install_id", "");
    }

    public int g() {
        return this.g.getInt("version_code", 0);
    }

    public boolean g(String str) {
        if (!a("ssid", (Object) str)) {
            return false;
        }
        this.g.edit().putString(this.c.f(), str).apply();
        return true;
    }

    public String h() {
        return this.d.optString("openudid", "");
    }

    public int i() {
        String strOptString = this.d.optString(MonitorConstants.KEY_DEVICE_ID, "");
        String strOptString2 = this.d.optString("install_id", "");
        String strOptString3 = this.d.optString("bd_did", "");
        if ((n0.a(strOptString) || n0.a(strOptString3)) && n0.a(strOptString2)) {
            return this.g.getInt("version_code", 0) == this.d.optInt("version_code", -1) ? 1 : 2;
        }
        return 0;
    }

    public String j() {
        return this.d.optString("ssid", "");
    }

    public String k() {
        return this.d.optString("udid", "");
    }

    public String l() {
        if (this.f274a) {
            return this.d.optString("user_unique_id", "");
        }
        i1 i1Var = this.c;
        return i1Var != null ? i1Var.g() : "";
    }

    public String m() {
        return this.d.optString("user_unique_id_type", this.c.d.getString("user_unique_id_type", null));
    }

    public int n() {
        int iOptInt = this.f274a ? this.d.optInt("version_code", -1) : -1;
        for (int i = 0; i < 3 && iOptInt == -1; i++) {
            q();
            iOptInt = this.f274a ? this.d.optInt("version_code", -1) : -1;
        }
        return iOptInt;
    }

    public String o() {
        String strOptString = this.f274a ? this.d.optString("app_version", null) : null;
        for (int i = 0; i < 3 && strOptString == null; i++) {
            q();
            strOptString = this.f274a ? this.d.optString("app_version", null) : null;
        }
        return strOptString;
    }

    public boolean p() {
        return this.e;
    }

    public synchronized boolean q() {
        v0 v0Var;
        if (this.f.size() == 0) {
            this.f.add(new e1(this.i, this.c));
            this.f.add(new h1(this.i, this.b, this.c));
            this.f.add(new o1(this.i, this.b));
            this.f.add(new p1(this.b));
            this.f.add(new u1(this.b, this.c, this, this.i.getInitConfig() != null ? this.i.getInitConfig().getSensitiveInfoProvider() : null));
            this.f.add(new q1(this.b));
            this.f.add(new s1(this.i, this.b, this.c));
            this.f.add(new t1(this.c));
            this.f.add(new v1(this.b, this.c, this));
            this.f.add(new w1(this.i, this.b));
            this.f.add(new x1(this.b));
            this.f.add(new m1(this.b, this.c, this));
            this.f.add(new r1(this.b, this.c));
            this.f.add(new g1(this.c));
            this.f.add(new b1(this.b));
            this.f.add(new f1(this.i));
        }
        JSONObject jSONObject = this.d;
        JSONObject jSONObject2 = new JSONObject();
        n0.a(jSONObject2, jSONObject);
        boolean z = true;
        int i = 0;
        int i2 = 0;
        for (d1 d1Var : this.f) {
            if (!d1Var.f237a || d1Var.c || (!this.c.h() && d1Var.d)) {
                try {
                    try {
                        d1Var.f237a = d1Var.a(jSONObject2);
                    } catch (SecurityException e) {
                        if (!d1Var.b) {
                            i++;
                            IAppLogLogger iAppLogLogger = this.i.D;
                            List<String> listSingletonList = Collections.singletonList("DeviceManager");
                            StringBuilder sbA = com.bytedance.bdtracker.a.a("loadHeader mCountPermission: ");
                            sbA.append(this.j);
                            iAppLogLogger.warn(listSingletonList, sbA.toString(), e);
                            if (!d1Var.f237a && this.j > 10) {
                                d1Var.f237a = true;
                            }
                        }
                    }
                } catch (JSONException e2) {
                    this.i.D.error("loader load error", e2, new Object[0]);
                }
                if (!d1Var.f237a && !d1Var.b) {
                    i2++;
                }
            }
            this.i.D.debug(Collections.singletonList("DeviceManager"), "Loader:{} is ready:{}", d1Var.a(), Boolean.valueOf(d1Var.f237a));
            z &= d1Var.f237a || d1Var.b;
        }
        if (z) {
            for (String str : l) {
                boolean zIsEmpty = TextUtils.isEmpty(jSONObject2.optString(str));
                z &= !zIsEmpty;
                if (zIsEmpty) {
                    this.i.D.warn(Collections.singletonList("DeviceManager"), "Key " + str + " is empty!", new Object[0]);
                }
            }
        }
        JSONObject jSONObject3 = this.d;
        this.d = jSONObject2;
        a1.a("set_header", (EventBus.DataFetcher) new l1(this, jSONObject2));
        Iterator<String> itKeys = jSONObject3.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            a(next, jSONObject3.opt(next));
        }
        this.f274a = z;
        this.i.D.debug(Collections.singletonList("DeviceManager"), "Loader header ready:{}, permission count:{}, header:{}", Boolean.valueOf(this.f274a), Integer.valueOf(this.j), this.d);
        if (i > 0 && i == i2) {
            this.j++;
            if (i() != 0) {
                this.j += 10;
            }
        }
        if (this.f274a && (v0Var = this.i.y) != null) {
            v0Var.onIdLoaded(c(), f(), j());
        }
        if (n0.d(j())) {
            a1.a("local_did_load", (EventBus.DataFetcher) new a());
        }
        return this.f274a;
    }

    public boolean r() {
        return !this.k;
    }

    public void e(String str) {
        if (a("ab_sdk_version", (Object) str)) {
            com.bytedance.bdtracker.a.a(this.c.d, "ab_sdk_version", str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001d A[Catch: all -> 0x0085, TryCatch #0 {, blocks: (B:5:0x0004, B:6:0x000f, B:8:0x001d, B:10:0x0028, B:11:0x002c, B:13:0x0032, B:15:0x003a, B:17:0x0042, B:20:0x0051, B:21:0x0063, B:23:0x0080), top: B:29:0x0004, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void c(org.json.JSONObject r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            r0 = 0
            if (r11 != 0) goto Lf
            com.bytedance.bdtracker.d r1 = r10.i     // Catch: java.lang.Throwable -> L85
            com.bytedance.applog.log.IAppLogLogger r1 = r1.D     // Catch: java.lang.Throwable -> L85
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L85
            java.lang.String r3 = "null abconfig"
            r1.warn(r3, r2)     // Catch: java.lang.Throwable -> L85
        Lf:
            org.json.JSONObject r1 = r10.d     // Catch: java.lang.Throwable -> L85
            java.lang.String r2 = "ab_sdk_version"
            java.lang.String r1 = r1.optString(r2)     // Catch: java.lang.Throwable -> L85
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L85
            if (r2 != 0) goto L83
            java.util.Set r2 = r10.c(r1)     // Catch: java.lang.Throwable -> L85
            java.util.HashSet r3 = new java.util.HashSet     // Catch: java.lang.Throwable -> L85
            r3.<init>()     // Catch: java.lang.Throwable -> L85
            if (r11 == 0) goto L63
            java.util.Iterator r4 = r11.keys()     // Catch: java.lang.Throwable -> L85
        L2c:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> L85
            if (r5 == 0) goto L63
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> L85
            boolean r6 = r5 instanceof java.lang.String     // Catch: java.lang.Throwable -> L85
            if (r6 == 0) goto L2c
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Throwable -> L85
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L85
            if (r6 != 0) goto L2c
            org.json.JSONObject r5 = r11.getJSONObject(r5)     // Catch: org.json.JSONException -> L50 java.lang.Throwable -> L85
            java.lang.String r6 = "vid"
            java.lang.String r5 = r5.optString(r6)     // Catch: org.json.JSONException -> L50 java.lang.Throwable -> L85
            r3.add(r5)     // Catch: org.json.JSONException -> L50 java.lang.Throwable -> L85
            goto L2c
        L50:
            r5 = move-exception
            com.bytedance.bdtracker.d r6 = r10.i     // Catch: java.lang.Throwable -> L85
            com.bytedance.applog.log.IAppLogLogger r6 = r6.D     // Catch: java.lang.Throwable -> L85
            java.lang.String r7 = "DeviceManager"
            java.util.List r7 = java.util.Collections.singletonList(r7)     // Catch: java.lang.Throwable -> L85
            java.lang.Object[] r8 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L85
            java.lang.String r9 = "JSON handle failed"
            r6.error(r7, r9, r5, r8)     // Catch: java.lang.Throwable -> L85
            goto L2c
        L63:
            com.bytedance.bdtracker.i1 r11 = r10.c     // Catch: java.lang.Throwable -> L85
            java.lang.String r11 = r11.c()     // Catch: java.lang.Throwable -> L85
            java.util.Set r0 = r10.c(r11)     // Catch: java.lang.Throwable -> L85
            r3.addAll(r0)     // Catch: java.lang.Throwable -> L85
            r2.retainAll(r3)     // Catch: java.lang.Throwable -> L85
            java.lang.String r0 = r10.a(r2)     // Catch: java.lang.Throwable -> L85
            r10.e(r0)     // Catch: java.lang.Throwable -> L85
            boolean r1 = android.text.TextUtils.equals(r1, r0)     // Catch: java.lang.Throwable -> L85
            if (r1 != 0) goto L83
            r10.a(r0, r11)     // Catch: java.lang.Throwable -> L85
        L83:
            monitor-exit(r10)
            return
        L85:
            r11 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L85
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.k1.c(org.json.JSONObject):void");
    }

    public synchronized void f(String str) {
        Set<String> setC = c(this.c.c());
        Set<String> setC2 = c(this.d.optString("ab_sdk_version"));
        setC2.removeAll(setC);
        setC2.addAll(c(str));
        i1 i1Var = this.c;
        i1Var.b.D.debug(Collections.singletonList("ConfigManager"), "setExternalAbVersion:{}", str);
        com.bytedance.bdtracker.a.a(i1Var.d, "external_ab_version", str);
        i1Var.h = null;
        e(a(setC2));
    }

    public boolean h(String str) {
        if (!a("user_unique_id", (Object) str)) {
            return false;
        }
        this.c.d.edit().putString("user_unique_id", n0.a((Object) str)).apply();
        return true;
    }

    public String a() {
        if (this.f274a) {
            return this.d.optString("ab_sdk_version", "");
        }
        i1 i1Var = this.c;
        return i1Var != null ? i1Var.d.getString("ab_sdk_version", "") : "";
    }

    public <T> T a(String str, T t, Class<T> cls) {
        return (T) this.i.j.a(this.d, str, t, cls);
    }

    public void i(String str) {
        if (a("user_unique_id_type", (Object) str)) {
            com.bytedance.bdtracker.a.a(this.c.d, "user_unique_id_type", str);
        }
    }

    public final void a(String str, String str2) {
        if (this.c.f.getBoolean("bav_ab_config", false) && this.c.c.isAbEnable()) {
            Set<String> setC = c(str);
            setC.removeAll(c(str2));
            v0 v0Var = this.i.y;
            if (v0Var != null) {
                v0Var.onAbVidsChange(a(setC), str2);
            }
        }
    }

    public String b() {
        return this.c.c.getAid();
    }

    public boolean b(String str, String str2) {
        if (!a("user_unique_id", (Object) str)) {
            return false;
        }
        this.c.d.edit().putString("user_unique_id", n0.a((Object) str)).apply();
        i(str2);
        return true;
    }

    public final void b(JSONObject jSONObject) {
        if (a(SchedulerSupport.CUSTOM, jSONObject)) {
            this.c.d.edit().putString("header_custom_info", jSONObject != null ? jSONObject.toString() : "").apply();
        }
    }

    public synchronized boolean a(JSONObject jSONObject, String str, String str2, String str3, String str4, String str5, String str6) {
        boolean z;
        this.i.D.debug(Collections.singletonList("DeviceManager"), "saveRegisterInfo -> uuid:" + str + ", did:" + str2 + ", iid:" + str3 + ", ssid:" + str4 + ", did:" + str5 + ", cd:" + str6 + ", response:{}", jSONObject);
        if (!n0.a(l(), str)) {
            this.i.D.debug(1, "saveRegisterInfo interrupted for uuid is changed", new Object[0]);
            return true;
        }
        this.e = jSONObject.optInt("new_user", 0) > 0;
        String strOptString = jSONObject.optString("device_token", "");
        boolean zA = n0.a(str2);
        boolean zA2 = n0.a(str3);
        boolean zA3 = n0.a(str5);
        boolean zA4 = n0.a(str6);
        try {
            boolean zA5 = n0.a(str4);
            int i = this.g.getInt("version_code", 0);
            int iOptInt = this.d.optInt("version_code", 0);
            SharedPreferences.Editor editorEdit = this.g.edit();
            if (i != iOptInt) {
                editorEdit.putInt("version_code", iOptInt);
            }
            String string = this.g.getString("channel", "");
            String strOptString2 = this.d.optString("channel", "");
            if (!TextUtils.equals(string, strOptString2)) {
                editorEdit.putString("channel", strOptString2);
            }
            editorEdit.putString("device_token", strOptString);
            if ((zA || (zA3 && zA4)) && zA2) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                editorEdit.putLong("register_time", jCurrentTimeMillis);
                a("register_time", Long.valueOf(jCurrentTimeMillis));
            } else if (!zA && (!zA3 || !zA4)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(CommonConstants.KEY_RESPONSE, jSONObject);
                this.i.onEventV3("tt_fetch_did_error", jSONObject2, 0);
            }
            String strB = ((c4) this.h).b();
            String string2 = this.g.getString("bd_did", null);
            this.i.D.debug(Collections.singletonList("DeviceManager"), "device: od=" + strB + " nd=" + str2 + " ck=" + zA, new Object[0]);
            if (zA) {
                if (str2.equals(this.d.optString(MonitorConstants.KEY_DEVICE_ID))) {
                    z = false;
                } else {
                    JSONObject jSONObject3 = this.d;
                    JSONObject jSONObject4 = new JSONObject();
                    n0.a(jSONObject4, jSONObject3);
                    jSONObject4.put(MonitorConstants.KEY_DEVICE_ID, str2);
                    this.d = jSONObject4;
                    a1.a("set_header", (EventBus.DataFetcher) new l1(this, jSONObject4));
                    ((c4) this.h).b(str2);
                    z = true;
                }
                if (!str2.equals(strB)) {
                    z = true;
                }
            } else {
                z = false;
            }
            if (zA3 && a("bd_did", (Object) str5)) {
                editorEdit.putString("bd_did", str5);
                z = true;
            }
            String strOptString3 = this.d.optString("install_id", "");
            if (zA2 && a("install_id", (Object) str3)) {
                editorEdit.putString("install_id", str3);
                z = true;
            }
            String strOptString4 = this.d.optString("ssid", "");
            boolean z2 = (zA5 && g(str4)) ? true : z;
            if (this.i.y != null) {
                this.i.y.onRemoteIdGet(z2, string2, str5, strOptString3, str3, strOptString4, str4);
            }
            editorEdit.apply();
        } catch (Throwable th) {
            this.i.D.error(Collections.singletonList("DeviceManager"), "JSON handle failed", th, new Object[0]);
        }
        return (zA || (zA3 && zA4)) && zA2;
    }

    public void a(JSONObject jSONObject) {
        i1 i1Var = this.c;
        i1Var.b.D.debug(Collections.singletonList("ConfigManager"), "setAbConfig:{}", jSONObject);
        com.bytedance.bdtracker.a.a(i1Var.d, "ab_configure", jSONObject == null ? "" : jSONObject.toString());
        i1Var.g = null;
        a1.a("set_abconfig", (EventBus.DataFetcher) new j1(i1Var, jSONObject));
        c(jSONObject);
    }

    public void a(HashMap<String, Object> map) {
        JSONObject jSONObject;
        if (map == null || map.isEmpty()) {
            jSONObject = null;
        } else {
            jSONObject = new JSONObject();
            JSONObject jSONObjectD = d();
            if (jSONObjectD != null) {
                n0.a(jSONObject, jSONObjectD);
            }
            try {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (!TextUtils.isEmpty(entry.getKey())) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                }
            } catch (Throwable th) {
                this.i.D.error(Collections.singletonList("DeviceManager"), "Set custom header failed", th, new Object[0]);
            }
        }
        b(jSONObject);
    }

    public final boolean a(String str, Object obj) {
        Object objOpt = this.d.opt(str);
        if (n0.b(obj, objOpt)) {
            return false;
        }
        synchronized (this) {
            try {
                JSONObject jSONObject = this.d;
                JSONObject jSONObject2 = new JSONObject();
                n0.a(jSONObject2, jSONObject);
                jSONObject2.put(str, obj);
                this.d = jSONObject2;
                a1.a("set_header", (EventBus.DataFetcher) new l1(this, jSONObject2));
            } catch (JSONException e) {
                this.i.D.error(Collections.singletonList("DeviceManager"), "Update header:{} to value:{} failed", e, str, obj);
            }
        }
        this.i.D.debug(Collections.singletonList("DeviceManager"), "Update header:{} from old:{} to new value:{}", str, objOpt, obj);
        return true;
    }
}
