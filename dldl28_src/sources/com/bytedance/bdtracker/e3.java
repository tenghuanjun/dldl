package com.bytedance.bdtracker;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.convert.BuildConfig;
import com.bytedance.applog.log.EventBus;
import com.bytedance.framwork.core.sdklib.net.NetConst;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.connect.common.Constants;
import com.volcengine.common.contant.CommonConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class e3 {
    public static JSONObject d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f245a = "https://databyterangers.com.cn";
    public final d b;
    public final e4 c;

    public class a implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ JSONObject f246a;

        public a(JSONObject jSONObject) {
            this.f246a = jSONObject;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            n0.b(this.f246a, jSONObject);
            try {
                jSONObject.put(CommonConstants.key_appId, e3.this.b.m);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public class b implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f247a;
        public final /* synthetic */ int b;
        public final /* synthetic */ byte[] c;
        public final /* synthetic */ String d;
        public final /* synthetic */ long e;
        public final /* synthetic */ JSONObject f;

        public b(String str, int i, byte[] bArr, String str2, long j, JSONObject jSONObject) {
            this.f247a = str;
            this.b = i;
            this.c = bArr;
            this.d = str2;
            this.e = j;
            this.f = jSONObject;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(CommonConstants.key_appId, e3.this.b.m);
                jSONObject.put("nid", this.f247a);
                jSONObject.put("statusCode", this.b);
                jSONObject.put("responseByte", this.c);
                jSONObject.put("responseString", this.d);
                jSONObject.put("time", this.e);
                jSONObject.put("header", this.f);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public e3(d dVar) {
        this.b = dVar;
        this.c = new e4(dVar);
    }

    public static String a(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Uri uri = Uri.parse(str);
        HashMap map = new HashMap(strArr.length);
        for (String str2 : strArr) {
            String queryParameter = uri.getQueryParameter(str2);
            if (!TextUtils.isEmpty(queryParameter)) {
                map.put(str2, queryParameter);
            }
        }
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.clearQuery();
        for (String str3 : map.keySet()) {
            builderBuildUpon.appendQueryParameter(str3, (String) map.get(str3));
        }
        return builderBuildUpon.build().toString();
    }

    public static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("aid", str);
        jSONObject.put("os", "Android");
        jSONObject.put("os_version", String.valueOf(Build.VERSION.SDK_INT));
        jSONObject.put(Constants.JumpUrlConstants.URL_KEY_SDK_VERSION, BuildConfig.VERSION_NAME);
        jSONObject.put("app_version", str2);
        return jSONObject;
    }

    public static void a(StringBuilder sb, String str, String str2) {
        if (sb == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        sb.append(sb.toString().indexOf(63) < 0 ? "?" : "&");
        sb.append(str);
        sb.append("=");
        sb.append(Uri.encode(str2));
    }

    public static JSONObject b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("magic_tag", "ss_app_log");
        jSONObject2.put("header", jSONObject);
        jSONObject2.put("_gen_time", System.currentTimeMillis());
        return jSONObject2;
    }

    public final HashMap<String, String> a() {
        Map<String, String> httpHeaders;
        HashMap<String, String> map = new HashMap<>(2);
        InitConfig initConfig = this.b.getInitConfig();
        if (initConfig != null && (httpHeaders = initConfig.getHttpHeaders()) != null && !httpHeaders.isEmpty()) {
            map.putAll(httpHeaders);
        }
        map.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE, this.b.E ? "application/octet-stream;tt-data=a" : NetConst.CONTENT_TYPE);
        return map;
    }

    public final void a(String str, int i, byte[] bArr, String str2, long j, JSONObject jSONObject) {
        a1.a("do_request_end", (EventBus.DataFetcher) new b(str, i, bArr, str2, j, jSONObject));
    }

    public final void a(JSONObject jSONObject) {
        try {
            long jOptLong = jSONObject.optLong("server_time");
            if (jOptLong > 0) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("server_time", jOptLong);
                jSONObject2.put("local_time", System.currentTimeMillis() / 1000);
                d = jSONObject2;
                a1.a("server_time_sync", (EventBus.DataFetcher) new a(jSONObject2));
            }
        } catch (Exception unused) {
        }
    }

    public JSONObject b(String str, JSONObject jSONObject) {
        String str2;
        this.b.D.debug(11, "Start to report oaid to uri:{} with request:{}...", str, jSONObject);
        try {
            str2 = new String(this.b.getNetClient().execute((byte) 1, this.c.a(str), jSONObject, a(), (byte) 0, true, 60000));
        } catch (Exception e) {
            e = e;
            str2 = null;
        }
        try {
            this.b.D.debug(11, "reportOaid success: {}", str2);
        } catch (Exception e2) {
            e = e2;
            this.b.D.error(11, "reportOaid error", e, new Object[0]);
        }
        return a(str2);
    }

    public final JSONObject a(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            a(jSONObject);
            return jSONObject;
        } catch (Throwable th) {
            this.b.D.error(11, "JSON handle failed", th, new Object[0]);
            return null;
        }
    }

    public JSONObject a(String str, JSONObject jSONObject) {
        String str2;
        this.b.D.debug(11, "Start to register to uri:{} with request:{}...", str, jSONObject);
        try {
            str2 = new String(this.b.getNetClient().execute((byte) 1, this.c.a(str), jSONObject, a(), (byte) 0, true, 60000));
            try {
                this.b.D.debug(11, "request register success: {}", str2);
            } catch (Throwable th) {
                th = th;
                this.b.D.error(11, "request register error", th, new Object[0]);
            }
        } catch (Throwable th2) {
            th = th2;
            str2 = null;
        }
        return a(str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(java.lang.String[] r22, org.json.JSONObject r23, com.bytedance.bdtracker.i1 r24) {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.e3.a(java.lang.String[], org.json.JSONObject, com.bytedance.bdtracker.i1):int");
    }

    public boolean a(JSONObject jSONObject, String str) {
        this.b.D.debug(11, "Start to send event:{} with cookie:{} to et...", jSONObject, str);
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("header", this.b.getHeader());
            if (jSONObject != null) {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
                jSONObject2.put("event_v3", jSONArray);
            }
        } catch (Throwable th) {
            this.b.D.error(11, "JSON handle failed", th, new Object[0]);
        }
        HashMap<String, String> mapA = a();
        mapA.put(HttpHeaders.HEAD_KEY_COOKIE, str);
        try {
            String str2 = new String(this.b.getNetClient().execute((byte) 1, this.f245a + "/simulator/mobile/log", jSONObject2, mapA, (byte) 0, true, 60000));
            if (!new JSONObject(str2).getJSONObject("data").optBoolean("keep", false)) {
                this.b.setRangersEventVerifyEnable(false, str);
            }
            this.b.D.debug(11, "Send event to et with response:{}", str2);
            return true;
        } catch (Throwable th2) {
            this.b.D.error(11, "Post to event verify failed", th2, new Object[0]);
            return false;
        }
    }
}
