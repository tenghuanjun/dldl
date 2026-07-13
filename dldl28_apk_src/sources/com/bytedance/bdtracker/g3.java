package com.bytedance.bdtracker;

import android.app.Application;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.bytedance.applog.IExtraParams;
import com.bytedance.applog.Level;
import com.bytedance.applog.util.SensitiveUtils;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes2.dex */
public final class g3 {
    public static final a[] c = {new a("aid", "aid", String.class), new a("google_aid", "google_aid", String.class), new a("carrier", "carrier", String.class), new a("mcc_mnc", "mcc_mnc", String.class), new a("sim_region", "sim_region", String.class), new a(MonitorConstants.KEY_DEVICE_ID, MonitorConstants.KEY_DEVICE_ID, String.class), new a("bd_did", "bd_did", String.class), new a("install_id", "iid", String.class), new a("clientudid", "clientudid", String.class), new a("app_name", "app_name", String.class), new a("app_version", "version_name", String.class), new a("version_code", "version_code", Integer.class), new a("manifest_version_code", "manifest_version_code", Integer.class), new a("update_version_code", "update_version_code", Integer.class), new a("sdk_version_code", "sdk_version_code", Integer.class)};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile IExtraParams f253a;
    public final d b;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f254a;
        public final String b;
        public final Class c;

        public a(String str, String str2, Class cls) {
            this.f254a = str;
            this.b = str2;
            this.c = cls;
        }
    }

    public g3(d dVar) {
        this.b = dVar;
    }

    public String a(JSONObject jSONObject, String str, boolean z, Level level) {
        if (this.b.n == null || TextUtils.isEmpty(str)) {
            return str;
        }
        Uri uri = Uri.parse(str);
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        Uri.Builder builderBuildUpon = uri.buildUpon();
        HashMap map = new HashMap();
        a(jSONObject, z, map, level);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!queryParameterNames.contains(key) && !TextUtils.isEmpty(value)) {
                builderBuildUpon.appendQueryParameter(key, entry.getValue());
            }
        }
        return builderBuildUpon.build().toString();
    }

    public void a(JSONObject jSONObject, boolean z, Map<String, String> map, Level level) {
        HashMap<String, String> extraParams;
        Application application = this.b.n;
        if (application == null || map == null || level == null) {
            return;
        }
        map.put("_rticket", String.valueOf(System.currentTimeMillis()));
        map.put("device_platform", PodInfo.GAME_TYPE_ANDROID);
        if (z) {
            map.put("ssmix", "a");
        }
        if (TextUtils.isEmpty(r4.f319a)) {
            DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
            int i = displayMetrics == null ? 0 : displayMetrics.widthPixels;
            DisplayMetrics displayMetrics2 = application.getResources().getDisplayMetrics();
            int i2 = displayMetrics2 == null ? 0 : displayMetrics2.heightPixels;
            if (i > 0 && i2 > 0) {
                r4.f319a = i + "*" + i2;
            }
        }
        String str = r4.f319a;
        if (!TextUtils.isEmpty(str)) {
            map.put("resolution", str);
        }
        if (r4.b == -1) {
            r4.b = application.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        }
        int i3 = r4.b;
        if (i3 > 0) {
            map.put("dpi", String.valueOf(i3));
        }
        map.put("device_type", Build.MODEL);
        map.put("device_brand", Build.BRAND);
        map.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, application.getResources().getConfiguration().locale.getLanguage());
        map.put("os_api", String.valueOf(Build.VERSION.SDK_INT));
        String strSubstring = Build.VERSION.RELEASE;
        if (strSubstring != null && strSubstring.length() > 10) {
            strSubstring = strSubstring.substring(0, 10);
        }
        map.put("os_version", strSubstring);
        String strA = i4.a(application, false);
        if (!TextUtils.isEmpty(strA)) {
            map.put("ac", strA);
        }
        int i4 = 0;
        while (true) {
            a[] aVarArr = c;
            extraParams = null;
            if (i4 >= aVarArr.length) {
                break;
            }
            a aVar = aVarArr[i4];
            Object objA = a(jSONObject, aVar.f254a, (Object) null, (Class<Object>) aVar.c);
            if (objA != null) {
                map.put(aVar.b, objA.toString());
            }
            i4++;
        }
        String str2 = (String) a(jSONObject, "tweaked_channel", "", (Class<String>) String.class);
        if (TextUtils.isEmpty(str2)) {
            str2 = (String) a(jSONObject, "channel", "", (Class<String>) String.class);
        }
        if (!TextUtils.isEmpty(str2)) {
            map.put("channel", str2);
        }
        String str3 = (String) a(jSONObject, "cdid", (Object) null, (Class<Object>) String.class);
        if (!TextUtils.isEmpty(str3)) {
            map.put("cdid", str3);
        }
        SensitiveUtils.appendSensitiveParams(this, jSONObject, map, l4.a(application), level);
        if (level == Level.L0) {
            String str4 = (String) a(jSONObject, "openudid", (Object) null, (Class<Object>) String.class);
            if (!TextUtils.isEmpty(str4)) {
                map.put("openudid", str4);
            }
        }
        this.b.getAppContext();
        try {
            if (this.f253a != null) {
                extraParams = this.f253a.getExtraParams(level);
            }
            if (extraParams == null || extraParams.isEmpty()) {
                return;
            }
            for (Map.Entry<String, String> entry : extraParams.entrySet()) {
                if (entry != null) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value) && !map.containsKey(key)) {
                        map.put(key, value);
                    }
                }
            }
        } catch (Throwable th) {
            this.b.D.error(11, "Add extra params failed.", th, new Object[0]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String[] a(com.bytedance.bdtracker.c0 r7, org.json.JSONObject r8, int r9) {
        /*
            r6 = this;
            com.bytedance.applog.UriConfig r7 = r7.e()
            r0 = 0
            r1 = 1
            if (r9 == 0) goto L21
            if (r9 == r1) goto Ld
            java.lang.String[] r7 = new java.lang.String[r0]
            goto L25
        Ld:
            java.lang.String r9 = r7.getBusinessUri()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L21
            java.lang.String[] r9 = new java.lang.String[r1]
            java.lang.String r7 = r7.getBusinessUri()
            r9[r0] = r7
            r7 = r9
            goto L25
        L21:
            java.lang.String[] r7 = r7.getSendUris()
        L25:
            int r9 = r7.length
            java.lang.String[] r2 = new java.lang.String[r9]
            com.bytedance.bdtracker.d r3 = r6.b
            boolean r3 = r3.E
        L2c:
            if (r0 >= r9) goto L5e
            r4 = r7[r0]
            r2[r0] = r4
            if (r3 == 0) goto L49
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = r2[r0]
            r4.append(r5)
            java.lang.String r5 = "?tt_data=a"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2[r0] = r4
        L49:
            r4 = r2[r0]
            com.bytedance.applog.Level r5 = com.bytedance.applog.Level.L1
            java.lang.String r4 = r6.a(r8, r4, r1, r5)
            r2[r0] = r4
            java.lang.String[] r5 = com.bytedance.bdtracker.e4.c
            java.lang.String r4 = com.bytedance.bdtracker.e3.a(r4, r5)
            r2[r0] = r4
            int r0 = r0 + 1
            goto L2c
        L5e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.g3.a(com.bytedance.bdtracker.c0, org.json.JSONObject, int):java.lang.String[]");
    }

    public <T> T a(JSONObject jSONObject, String str, T t, Class<T> cls) {
        T tCast = null;
        if (jSONObject == null) {
            d dVar = this.b;
            if (dVar.a()) {
                return null;
            }
            return (T) dVar.p.a(str, t, cls);
        }
        Object objOpt = jSONObject.opt(str);
        if (objOpt != null && cls != null) {
            try {
                tCast = cls.cast(objOpt);
            } catch (Throwable th) {
                this.b.D.error(11, "Cast type failed.", th, new Object[0]);
            }
        }
        return tCast == null ? t : tCast;
    }
}
