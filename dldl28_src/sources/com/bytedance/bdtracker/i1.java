package com.bytedance.bdtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.log.EventBus;
import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.lzy.okgo.model.Progress;
import com.volcengine.common.contant.CommonConstants;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import okhttp3.HttpUrl;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class i1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f262a;
    public final d b;
    public final InitConfig c;
    public final SharedPreferences d;
    public final SharedPreferences e;
    public final SharedPreferences f;
    public volatile JSONObject g;
    public volatile String h;
    public volatile JSONObject i;
    public final Set<String> j;
    public final Set<String> k;
    public int l;
    public int m;
    public long n;
    public int o;
    public long p;
    public boolean q;
    public int r;

    public class a implements EventBus.DataFetcher {
        public a() {
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            String str;
            String str2;
            String str3;
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put(CommonConstants.key_appId, i1.this.b.m);
                long j = i1.this.f.getLong("session_interval", 0L);
                String str4 = "--";
                if (j > 0) {
                    str = j + "ms";
                } else {
                    str = "--";
                }
                jSONObject2.put("后台会话时长", str);
                long j2 = i1.this.f.getLong("batch_event_interval", 0L);
                if (j2 > 0) {
                    str2 = j2 + "ms";
                } else {
                    str2 = "--";
                }
                jSONObject2.put("事件上报周期", str2);
                long j3 = i1.this.f.getLong("abtest_fetch_interval", 0L);
                if (j3 > 0) {
                    str3 = j3 + "ms";
                } else {
                    str3 = "--";
                }
                jSONObject2.put("AB实验更新周期", str3);
                jSONObject2.put("全埋点开关", i1.this.f.getBoolean("bav_log_collect", false));
                jSONObject2.put("AB实验开关", i1.this.f.getBoolean("bav_ab_config", false));
                jSONObject2.put("实时埋点事件", i1.this.f.getString("real_time_events", HttpUrl.PATH_SEGMENT_ENCODE_SET_URI));
                jSONObject2.put("禁止采集手机详情", i1.this.f.getBoolean("forbid_report_phone_detail_info", false));
                long j4 = i1.this.f.getLong("fetch_interval", 0L);
                if (j4 > 0) {
                    str4 = j4 + "ms";
                }
                jSONObject2.put("服务端配置更新周期", str4);
                jSONObject2.put("禁止内部监控开关", i1.this.f.getBoolean("applog_disable_monitor", false));
                jSONObject.put("config", jSONObject2);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public i1(d dVar, Context context, InitConfig initConfig) {
        HashSet hashSet = new HashSet();
        this.j = hashSet;
        HashSet hashSet2 = new HashSet();
        this.k = hashSet2;
        this.l = 0;
        this.m = 27;
        this.n = 0L;
        this.o = 0;
        this.p = 0L;
        this.q = false;
        this.r = 1;
        this.b = dVar;
        this.f262a = context;
        this.c = initConfig;
        SharedPreferences sharedPreferencesA = v3.a(context, initConfig.getSpName(), 0);
        this.f = sharedPreferencesA;
        this.d = v3.a(context, b.a(dVar, "header_custom"), 0);
        this.e = v3.a(context, b.a(dVar, "last_sp_session"), 0);
        Set<String> stringSet = sharedPreferencesA.getStringSet("block_events_v1", null);
        if (stringSet != null) {
            hashSet.addAll(stringSet);
        }
        Set<String> stringSet2 = sharedPreferencesA.getStringSet("block_events_v3", null);
        if (stringSet2 != null) {
            hashSet2.addAll(stringSet2);
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = this.g;
        if (jSONObject == null) {
            synchronized (this) {
                try {
                    jSONObject = new JSONObject(this.d.getString("ab_configure", ""));
                } catch (Throwable unused) {
                }
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                this.g = jSONObject;
            }
        }
        return jSONObject;
    }

    public final boolean a(long j) {
        return j >= 10000 && j <= MonitorCommonConstants.SECOND_STOP_INTERVAL;
    }

    public boolean a(List<j3> list) {
        if (list == null || list.size() == 0 || (this.j.isEmpty() && this.k.isEmpty())) {
            return true;
        }
        Iterator<j3> it = list.iterator();
        while (it.hasNext()) {
            j3 next = it.next();
            if (next instanceof q3) {
                if (this.k.contains(((q3) next).u)) {
                    it.remove();
                }
            } else if (next instanceof m3) {
                JSONObject jSONObjectH = next.h();
                StringBuilder sb = new StringBuilder();
                sb.append(jSONObjectH.optString(Progress.TAG));
                sb.append(!TextUtils.isEmpty(jSONObjectH.optString("label")) ? jSONObjectH.optString("label") : "");
                if (this.j.contains(sb.toString())) {
                    it.remove();
                }
            }
        }
        return true;
    }

    public String b() {
        String channel = this.c.getChannel();
        if (TextUtils.isEmpty(channel)) {
            channel = this.c.getTweakedChannel();
        }
        if (!TextUtils.isEmpty(channel)) {
            return channel;
        }
        try {
            return this.f262a.getPackageManager().getApplicationInfo(this.f262a.getPackageName(), 128).metaData.getString("UMENG_CHANNEL");
        } catch (Throwable th) {
            this.b.D.error(Collections.singletonList("ConfigManager"), "getChannel failed", th, new Object[0]);
            return channel;
        }
    }

    public String c() {
        String string = this.h;
        if (TextUtils.isEmpty(string)) {
            synchronized (this) {
                string = this.d.getString("external_ab_version", "");
                this.h = string;
            }
        }
        return string;
    }

    public String d() {
        return this.f.getString("channel", "");
    }

    public long e() {
        return this.f.getLong("session_interval", 30000L);
    }

    public String f() {
        StringBuilder sbA = com.bytedance.bdtracker.a.a("ssid_");
        sbA.append(this.c.getAid());
        return sbA.toString();
    }

    public String g() {
        return this.d.getString("user_unique_id", "");
    }

    public boolean h() {
        if (this.c.getProcess() == 0) {
            String strB = n0.b();
            if (TextUtils.isEmpty(strB)) {
                this.c.setProcess(0);
            } else {
                this.c.setProcess(strB.contains(":") ? 2 : 1);
            }
        }
        return this.c.getProcess() == 1;
    }

    public boolean i() {
        return this.f.getBoolean("monitor_enabled", this.c.isMonitorEnabled());
    }

    public boolean j() {
        InitConfig initConfig = this.c;
        return !((initConfig == null || initConfig.isOaidEnabled()) ? false : true);
    }

    public void k() {
        if (this.f.getLong("app_log_last_config_time", 0L) <= 0) {
            return;
        }
        a1.a("remote_settings", (EventBus.DataFetcher) new a());
    }

    public void a(JSONObject jSONObject) {
        int iOptInt = jSONObject.optInt("backoff_ratio", 0);
        this.l = iOptInt;
        if (iOptInt < 0 || iOptInt > 10000) {
            this.l = 0;
        }
        int i = this.l > 0 ? 1 : 27;
        int iOptInt2 = jSONObject.optInt("max_request_frequency", i);
        this.m = iOptInt2;
        if (iOptInt2 < 1 || iOptInt2 > 27) {
            this.m = i;
        }
        int i2 = this.l;
        if (i2 > 0 && this.n == 0) {
            this.n = System.currentTimeMillis();
            this.o = 1;
        } else if (i2 == 0) {
            this.n = 0L;
            this.o = 0;
        }
        this.p = jSONObject.optLong("batch_event_interval", 0L) * 1000;
        this.q = jSONObject.optInt("enter_background_not_send") == 1;
        IAppLogLogger iAppLogLogger = this.b.D;
        List<String> listSingletonList = Collections.singletonList("ConfigManager");
        StringBuilder sbA = com.bytedance.bdtracker.a.a("updateLogRespConfig mBackoffRatio: ");
        sbA.append(this.l);
        sbA.append(", mMaxRequestFrequency: ");
        sbA.append(this.m);
        sbA.append(", mBackoffWindowStartTime: ");
        sbA.append(this.n);
        sbA.append(", mBackoffWindowSendCount: ");
        sbA.append(this.o);
        sbA.append(", mEventIntervalFromLogResp: ");
        sbA.append(this.p);
        iAppLogLogger.debug(listSingletonList, sbA.toString(), new Object[0]);
    }
}
