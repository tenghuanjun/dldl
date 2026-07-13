package com.bytedance.bdtracker;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.bytedance.applog.IPullAbTestConfigCallback;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.UriConfig;
import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.bytedance.applog.collector.Collector;
import com.bytedance.applog.log.EventBus;
import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.applog.migrate.MigrateDetectorActivity;
import com.bytedance.applog.util.UriConstants;
import com.bytedance.bdtracker.h0;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import com.volcengine.common.contant.CommonConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class c0 implements Handler.Callback, Comparator<j3> {
    public final z2 A;
    public final i B;
    public final c1 C;
    public final t2 E;
    public z b;
    public boolean c;
    public com.bytedance.bdtracker.d d;
    public i1 e;
    public b0 f;
    public volatile p3 h;
    public final k1 i;
    public volatile Handler j;
    public f0 k;
    public g0 l;
    public volatile y m;
    public UriConfig o;
    public final Handler p;
    public z1 q;
    public volatile boolean r;
    public a0 s;
    public volatile e0 t;
    public volatile boolean v;
    public volatile long w;
    public volatile s0 y;
    public volatile InitConfig.IpcDataChecker z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f223a = 10000;
    public final ArrayList<j3> g = new ArrayList<>(32);
    public final CopyOnWriteArrayList<a0> u = new CopyOnWriteArrayList<>();
    public final List<c> x = new ArrayList();
    public final h0 n = new h0(this);
    public final d0 D = new d0(this);

    public class a implements EventBus.DataFetcher {
        public a() {
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(CommonConstants.key_appId, c0.this.d.m);
                jSONObject.put("isMainProcess", c0.this.e.h());
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public class b implements g {
        public b() {
        }

        public void a(Map<String, String> map) {
            if (c0.this.i.e() == null || c0.this.i.e().opt("oaid") != null || map == null) {
                return;
            }
            c0.this.j.obtainMessage(17, map).sendToTarget();
        }
    }

    public abstract class c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public T f226a;

        public c(c0 c0Var, T t) {
            this.f226a = t;
        }
    }

    public class d extends c<String> {
        public d(String str) {
            super(c0.this, str);
        }
    }

    public c0(com.bytedance.bdtracker.d dVar, i1 i1Var, k1 k1Var, c1 c1Var) {
        int componentEnabledSetting;
        int i;
        this.d = dVar;
        this.e = i1Var;
        this.i = k1Var;
        this.C = c1Var;
        StringBuilder sbA = com.bytedance.bdtracker.a.a("bd_tracker_w:");
        sbA.append(dVar.m);
        HandlerThread handlerThread = new HandlerThread(sbA.toString());
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), this);
        this.p = handler;
        i iVar = new i(this);
        this.B = iVar;
        if (this.e.c.isDeferredALinkEnabled()) {
            this.d.addDataObserver(iVar);
        }
        ((c4) k1Var.h).b.a(handler);
        InitConfig initConfig = k1Var.c.c;
        if (initConfig == null || initConfig.isMigrateEnabled()) {
            Context context = k1Var.b;
            com.bytedance.bdtracker.d dVar2 = k1Var.i;
            String strA = com.bytedance.bdtracker.b.a(dVar2, "bdtracker_dr_migrate_detector");
            Context applicationContext = context.getApplicationContext();
            SharedPreferences sharedPreferencesA = v3.a(applicationContext, strA, 0);
            PackageManager packageManager = applicationContext.getPackageManager();
            ComponentName componentName = new ComponentName(context, (Class<?>) MigrateDetectorActivity.class);
            try {
                componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
                i = sharedPreferencesA.getInt("component_state", 0);
                IAppLogLogger iAppLogLogger = dVar2.D;
                StringBuilder sbA2 = com.bytedance.bdtracker.a.a("MigrateDetector#isMigrateInternal cs=");
                sbA2.append(y1.a(componentEnabledSetting));
                sbA2.append(" ss=");
                sbA2.append(y1.a(i));
                iAppLogLogger.debug(sbA2.toString(), new Object[0]);
            } catch (Exception unused) {
            }
            boolean z = componentEnabledSetting == 0 && i == 2;
            dVar2.D.debug("MigrateDetector#constructor migrate=" + z, new Object[0]);
            if (z) {
                i1 i1Var2 = k1Var.c;
                if (i1Var2 != null) {
                    i1Var2.f.edit().remove("google_aid").apply();
                }
                SharedPreferences sharedPreferences = k1Var.g;
                String strB = ((c4) k1Var.h).b();
                if (sharedPreferences != null) {
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putString("old_did", strB);
                    editorEdit.putBoolean("is_migrate", true);
                    editorEdit.apply();
                }
                ((c4) k1Var.h).a("openudid");
                ((c4) k1Var.h).a("clientudid");
                ((c4) k1Var.h).a("serial_number");
                ((c4) k1Var.h).a("sim_serial_number");
                ((c4) k1Var.h).a("udid");
                ((c4) k1Var.h).a("udid_list");
                ((c4) k1Var.h).a(MonitorConstants.KEY_DEVICE_ID);
                k1Var.b("clearMigrationInfo");
            }
            dVar2.D.debug("MigrateDetector#disableComponent", new Object[0]);
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
            sharedPreferencesA.edit().putInt("component_state", 2).apply();
        }
        if (k1Var.c.j()) {
            x4.f339a.b(k1Var.b).a();
        }
        this.A = new z2(this);
        if (this.e.c.isClearDidAndIid()) {
            this.i.b(this.e.c.getClearKey());
        }
        if (this.e.c.getIpcDataChecker() != null && !this.e.h()) {
            this.z = this.e.c.getIpcDataChecker();
        }
        if (this.e.i()) {
            this.q = new d2(this);
        }
        this.p.sendEmptyMessage(10);
        if (this.e.c.autoStart()) {
            this.r = true;
            this.p.sendEmptyMessage(1);
        }
        this.E = new t2(this);
    }

    public void a() {
        x4.a(new b());
    }

    public void a(Long l) {
        long jLongValue = 0;
        if (l != null && l.longValue() > 0) {
            jLongValue = l.longValue();
        }
        this.f223a = jLongValue;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0041 A[Catch: all -> 0x0050, TRY_LEAVE, TryCatch #0 {all -> 0x0050, blocks: (B:9:0x001f, B:15:0x003b, B:17:0x0041, B:19:0x0047, B:12:0x0030), top: B:24:0x001f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(org.json.JSONObject r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            java.lang.String r1 = "ssid"
            java.lang.String r2 = ""
            java.lang.String r3 = r8.optString(r1, r2)
            boolean r3 = com.bytedance.bdtracker.n0.d(r3)
            r4 = 1
            if (r3 == 0) goto L14
            return r4
        L14:
            com.bytedance.bdtracker.d r3 = r7.d
            com.bytedance.applog.log.IAppLogLogger r3 = r3.D
            java.lang.Object[] r5 = new java.lang.Object[r0]
            java.lang.String r6 = "Register to get ssid by temp header..."
            r3.debug(r6, r5)
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L50
            r3.<init>()     // Catch: java.lang.Throwable -> L50
            com.bytedance.bdtracker.n0.a(r3, r8)     // Catch: java.lang.Throwable -> L50
            com.bytedance.bdtracker.f0 r5 = r7.k     // Catch: java.lang.Throwable -> L50
            org.json.JSONObject r3 = r5.b(r3)     // Catch: java.lang.Throwable -> L50
            if (r3 != 0) goto L30
            goto L3a
        L30:
            java.lang.String r2 = r3.optString(r1, r2)     // Catch: java.lang.Throwable -> L50
            boolean r3 = com.bytedance.bdtracker.n0.c(r2)     // Catch: java.lang.Throwable -> L50
            if (r3 == 0) goto L3b
        L3a:
            r2 = 0
        L3b:
            boolean r3 = com.bytedance.bdtracker.n0.d(r2)     // Catch: java.lang.Throwable -> L50
            if (r3 == 0) goto L5c
            com.bytedance.bdtracker.d r3 = r7.d     // Catch: java.lang.Throwable -> L50
            com.bytedance.applog.log.IAppLogLogger r3 = r3.D     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = "Register to get ssid by header success."
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L50
            r3.debug(r5, r6)     // Catch: java.lang.Throwable -> L50
            r8.put(r1, r2)     // Catch: java.lang.Throwable -> L50
            return r4
        L50:
            r8 = move-exception
            com.bytedance.bdtracker.d r1 = r7.d
            com.bytedance.applog.log.IAppLogLogger r1 = r1.D
            java.lang.Object[] r2 = new java.lang.Object[r0]
            java.lang.String r3 = "JSON handle failed"
            r1.error(r3, r8, r2)
        L5c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.c0.a(org.json.JSONObject):boolean");
    }

    public boolean a(boolean z) {
        if ((!this.c || z) && this.j != null) {
            this.c = true;
            this.j.removeMessages(11);
            this.j.sendEmptyMessage(11);
        }
        return this.c;
    }

    public Context b() {
        return this.d.n;
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.A.a(jSONObject);
    }

    public p3 c() {
        if (this.h == null) {
            synchronized (this) {
                p3 p3Var = this.h;
                if (p3Var == null) {
                    p3Var = new p3(this, this.e.c.getDbName());
                }
                this.h = p3Var;
            }
        }
        return this.h;
    }

    public void c(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.A.b(jSONObject);
    }

    @Override // java.util.Comparator
    public int compare(j3 j3Var, j3 j3Var2) {
        long j = j3Var.c - j3Var2.c;
        if (j < 0) {
            return -1;
        }
        return j > 0 ? 1 : 0;
    }

    public String d() {
        h0 h0Var = this.n;
        if (h0Var != null) {
            return h0Var.e;
        }
        return null;
    }

    public void d(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.A.c(jSONObject);
    }

    public UriConfig e() {
        if (this.o == null) {
            UriConfig uriConfig = this.e.c.getUriConfig();
            this.o = uriConfig;
            if (uriConfig == null) {
                this.o = UriConstants.createUriConfig(0);
            }
        }
        return this.o;
    }

    public void e(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.A.d(jSONObject);
    }

    public void f(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.A.e(jSONObject);
    }

    public final boolean f() {
        return this.e.f.getBoolean("bav_ab_config", false) && this.e.c.isAbEnable() && !TextUtils.isEmpty(e().getAbUri());
    }

    public boolean g() {
        i1 i1Var = this.e;
        return i1Var.r == 1 && i1Var.c.isAutoTrackEnabled();
    }

    public void b(j3 j3Var) {
        if (this.t == null) {
            return;
        }
        if ((j3Var instanceof q3) || (((j3Var instanceof t3) && g()) || (j3Var instanceof m3) || (j3Var instanceof u3))) {
            JSONObject jSONObjectH = j3Var.h();
            if (j3Var instanceof t3) {
                if (!((t3) j3Var).k()) {
                    return;
                }
                JSONObject jSONObjectOptJSONObject = jSONObjectH.optJSONObject(MetricsSQLiteCacheKt.METRICS_PARAMS);
                if (jSONObjectOptJSONObject != null) {
                    try {
                        jSONObjectOptJSONObject.remove("duration");
                        jSONObjectH.put(MetricsSQLiteCacheKt.METRICS_PARAMS, jSONObjectOptJSONObject);
                    } catch (Throwable unused) {
                    }
                }
            }
            if ((j3Var instanceof m3) && !jSONObjectH.has(NotificationCompat.CATEGORY_EVENT)) {
                try {
                    jSONObjectH.put(NotificationCompat.CATEGORY_EVENT, jSONObjectH.optString(MonitorCommonConstants.KEY_LOG_TYPE, ((m3) j3Var).s));
                } catch (Throwable unused2) {
                }
            }
            this.d.k.a(jSONObjectH, this.t.g);
        }
    }

    public void a(String[] strArr, boolean z) {
        ArrayList<j3> arrayList;
        Handler handler;
        InitConfig initConfig;
        i1 i1Var = this.e;
        boolean zCheckIpcData = true;
        boolean z2 = (i1Var == null || (initConfig = i1Var.c) == null || initConfig.isTrackEventEnabled()) ? false : true;
        if (this.d.x || z2) {
            return;
        }
        synchronized (this.g) {
            arrayList = (ArrayList) this.g.clone();
            this.g.clear();
        }
        if (strArr != null) {
            arrayList.ensureCapacity(arrayList.size() + strArr.length);
            for (String str : strArr) {
                arrayList.add(j3.a(str));
            }
        }
        if (!arrayList.isEmpty()) {
            boolean zIsEventFilterEnable = this.e.c.isEventFilterEnable();
            s0 s0Var = this.y;
            s0 s0Var2 = this.d.w;
            if ((zIsEventFilterEnable && s0Var != null) || s0Var2 != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    j3 j3Var = (j3) it.next();
                    if (j3Var instanceof q3) {
                        q3 q3Var = (q3) j3Var;
                        String str2 = q3Var.u;
                        String strE = q3Var.e();
                        if ((s0Var2 != null && !s0Var2.a(str2, strE)) || (s0Var != null && !s0Var.a(str2, strE))) {
                            it.remove();
                        }
                    }
                }
            }
        }
        boolean zA = this.e.a(arrayList);
        if (arrayList.size() > 0) {
            if (!this.e.h()) {
                Intent intent = new Intent(this.d.n, (Class<?>) Collector.class);
                int size = arrayList.size();
                String[] strArr2 = new String[size];
                int length = 0;
                for (int i = 0; i < size; i++) {
                    String string = ((j3) arrayList.get(i)).g().toString();
                    strArr2[i] = string;
                    length += string.length();
                }
                if (length >= 307200 && this.z != null) {
                    try {
                        zCheckIpcData = this.z.checkIpcData(strArr2);
                    } catch (Throwable th) {
                        this.d.D.warn("check ipc data", th);
                    }
                }
                if (zCheckIpcData) {
                    intent.putExtra("K_DATA", strArr2);
                    this.d.n.sendBroadcast(intent);
                }
            } else if (zA || arrayList.size() > 100) {
                Collections.sort(arrayList, this);
                ArrayList<j3> arrayList2 = new ArrayList<>(arrayList.size());
                boolean z3 = false;
                boolean zA2 = false;
                boolean zA3 = false;
                for (j3 j3Var2 : arrayList) {
                    zA2 |= this.n.a(this.d, j3Var2, arrayList2);
                    if (j3Var2 instanceof t3) {
                        zA3 = h0.a(j3Var2);
                        z3 = true;
                    }
                    if (Looper.myLooper() != Looper.getMainLooper()) {
                        b(j3Var2);
                    } else if (this.j != null) {
                        this.j.obtainMessage(16, j3Var2).sendToTarget();
                    }
                    a1.a("event_process", j3Var2);
                }
                c().c.a(arrayList2);
                if (z3 && (handler = this.p) != null) {
                    if (zA3) {
                        handler.removeMessages(7);
                    } else {
                        handler.sendEmptyMessageDelayed(7, this.e.e());
                    }
                }
                if (zA2) {
                    a(this.l);
                }
                if (!this.c && this.n.i && this.j != null && this.e.c.isAutoActive()) {
                    a(false);
                }
            } else {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    a((j3) it2.next());
                }
            }
        }
        if (z && this.e.h()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (Math.abs(jCurrentTimeMillis - this.w) > 10000) {
                this.w = jCurrentTimeMillis;
                a(this.l);
            }
        }
    }

    public void a(j3 j3Var) {
        int size;
        if (j3Var.c == 0) {
            this.d.D.warn("Data ts is 0", new Object[0]);
        }
        synchronized (this.g) {
            size = this.g.size();
            this.g.add(j3Var);
        }
        boolean z = j3Var instanceof t3;
        if (size % 10 == 0 || z) {
            this.p.removeMessages(4);
            if (z || size != 0) {
                this.p.sendEmptyMessage(4);
            } else {
                this.p.sendEmptyMessageDelayed(4, 300L);
            }
        }
    }

    public final void a(String str) {
        JSONObject jSONObject = new JSONObject();
        n0.a(jSONObject, this.i.e());
        try {
            f0 f0Var = this.k;
            if (f0Var == null || !f0Var.a(jSONObject)) {
                return;
            }
            if (n0.d(str)) {
                this.e.f.edit().putInt("is_first_time_launch", 1).apply();
            }
            a(true);
        } catch (Throwable th) {
            this.d.D.error("Register new uuid:{} failed", th, str);
        }
    }

    public final void a(String str, String str2) {
        boolean zIsEmpty = TextUtils.isEmpty(this.i.l());
        this.i.b(str, str2);
        this.i.g("");
        this.i.d("$tr_web_ssid");
        InitConfig initConfig = this.e.c;
        if (initConfig != null && initConfig.isClearABCacheOnUserChange() && !zIsEmpty) {
            this.i.e(null);
        }
        this.v = true;
        if (this.j != null) {
            this.j.sendMessage(this.j.obtainMessage(12, str));
            return;
        }
        synchronized (this.x) {
            this.x.add(new d(str));
        }
    }

    public final void a(a0 a0Var) {
        if (this.j == null || a0Var == null || this.d.x) {
            return;
        }
        a0Var.b = true;
        if (Looper.myLooper() == this.j.getLooper()) {
            a0Var.a();
        } else {
            this.j.removeMessages(6);
            this.j.sendEmptyMessage(6);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.bytedance.bdtracker.h0$a] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v9, types: [com.bytedance.bdtracker.s0] */
    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Map<String, ?> all;
        Set set;
        Set set2;
        InitConfig initConfig;
        String str;
        String str2;
        ?? u0Var = 0;
        String[] strArr = null;
        u0Var = 0;
        u0Var = 0;
        switch (message.what) {
            case 1:
                this.d.D.info("AppLog is starting...", new Object[0]);
                i1 i1Var = this.e;
                i1Var.r = i1Var.f.getBoolean("bav_log_collect", i1Var.c.isAutoTrackEnabled()) ? 1 : 0;
                if (this.i.q()) {
                    if (this.e.h()) {
                        StringBuilder sbA = com.bytedance.bdtracker.a.a("bd_tracker_n:");
                        sbA.append(this.d.m);
                        HandlerThread handlerThread = new HandlerThread(sbA.toString());
                        handlerThread.start();
                        this.j = new Handler(handlerThread.getLooper(), this);
                        this.j.sendEmptyMessage(2);
                        if (this.g.size() > 0) {
                            this.p.removeMessages(4);
                            this.p.sendEmptyMessageDelayed(4, 1000L);
                        }
                        Application application = this.d.n;
                        l4.f289a = true;
                        w.f333a.submit(new m4(application));
                        this.d.D.info("AppLog started on main process.", new Object[0]);
                    } else {
                        this.d.D.info("AppLog started on secondary process.", new Object[0]);
                    }
                    a1.a("start_end", (EventBus.DataFetcher) new a());
                } else {
                    this.d.D.info("AppLog is not ready, will try start again after 1 second...", new Object[0]);
                    this.p.removeMessages(1);
                    this.p.sendEmptyMessageDelayed(1, 1000L);
                }
                return true;
            case 2:
                f0 f0Var = new f0(this);
                this.k = f0Var;
                this.u.add(f0Var);
                i1 i1Var2 = this.e;
                if (i1Var2 == null || (initConfig = i1Var2.c) == null || initConfig.isTrackEventEnabled()) {
                    g0 g0Var = new g0(this);
                    this.l = g0Var;
                    this.u.add(g0Var);
                }
                UriConfig uriConfigE = e();
                if (!TextUtils.isEmpty(uriConfigE.getSettingUri())) {
                    b0 b0Var = new b0(this);
                    this.f = b0Var;
                    this.u.add(b0Var);
                }
                if (!TextUtils.isEmpty(uriConfigE.getProfileUri())) {
                    Handler handler = this.A.b;
                    handler.sendMessage(handler.obtainMessage(106));
                }
                this.j.removeMessages(13);
                this.j.sendEmptyMessage(13);
                String strA = com.bytedance.bdtracker.b.a(this.d, "sp_filter_name");
                if (this.i.g.getInt("version_code", 0) != this.i.n() || !TextUtils.equals(this.e.f.getString("channel", ""), this.e.b())) {
                    f0 f0Var2 = this.k;
                    if (f0Var2 != null) {
                        f0Var2.b = true;
                    }
                    b0 b0Var2 = this.f;
                    if (b0Var2 != null) {
                        b0Var2.b = true;
                    }
                    if (this.e.c.isEventFilterEnable()) {
                        this.y = s0.a(this.d.n, strA, null);
                    }
                } else if (this.e.c.isEventFilterEnable()) {
                    try {
                        SharedPreferences sharedPreferencesA = v3.a(this.d.n, strA, 0);
                        HashSet hashSet = new HashSet();
                        HashMap map = new HashMap();
                        try {
                            all = sharedPreferencesA.getAll();
                        } catch (Throwable unused) {
                            all = null;
                        }
                        if (all != null && all.size() > 0) {
                            int i = 0;
                            for (Map.Entry<String, ?> entry : all.entrySet()) {
                                if (entry != null) {
                                    String key = entry.getKey();
                                    if ("is_block".equals(key)) {
                                        i = sharedPreferencesA.getInt("is_block", 0);
                                    } else if ("events".equals(key)) {
                                        try {
                                            set2 = (Set) entry.getValue();
                                        } catch (Throwable unused2) {
                                            set2 = null;
                                        }
                                        if (set2 != null && set2.size() > 0) {
                                            hashSet.addAll(set2);
                                        }
                                        break;
                                    } else if (!TextUtils.isEmpty(key)) {
                                        HashSet hashSet2 = new HashSet();
                                        try {
                                            set = (Set) entry.getValue();
                                        } catch (Throwable unused3) {
                                            set = null;
                                        }
                                        if (set != null && set.size() > 0) {
                                            hashSet2.addAll(set);
                                        }
                                        if (hashSet2.size() > 0) {
                                            map.put(key, hashSet2);
                                        }
                                        break;
                                    }
                                }
                            }
                            u0Var = i > 0 ? new u0(hashSet, map) : new t0(hashSet, map);
                        }
                        break;
                    } catch (Throwable unused4) {
                    }
                    this.y = u0Var;
                }
                this.j.removeMessages(6);
                this.j.sendEmptyMessage(6);
                z1 z1Var = this.q;
                if (z1Var != null) {
                    d2 d2Var = (d2) z1Var;
                    i1 i1Var3 = d2Var.c.e;
                    Intrinsics.checkExpressionValueIsNotNull(i1Var3, "mEngine.config");
                    if (i1Var3.i()) {
                        d2Var.b.a(new c2(d2Var));
                    }
                }
                return true;
            case 3:
            case 5:
            case 8:
            default:
                this.d.D.error("Unknown handler message type", new Object[0]);
                return true;
            case 4:
                a((String[]) message.obj, false);
                return true;
            case 6:
                this.j.removeMessages(6);
                long j = 5000;
                if (!this.d.x && (!this.e.c.isSilenceInBackground() || this.n.c())) {
                    long j2 = Long.MAX_VALUE;
                    for (a0 a0Var : this.u) {
                        if (!a0Var.d) {
                            long jA = a0Var.a();
                            if (jA < j2) {
                                j2 = jA;
                            }
                        }
                    }
                    long jCurrentTimeMillis = j2 - System.currentTimeMillis();
                    if (jCurrentTimeMillis <= 5000) {
                        j = jCurrentTimeMillis;
                    }
                }
                this.j.sendEmptyMessageDelayed(6, j);
                if (this.x.size() > 0) {
                    synchronized (this.x) {
                        for (c cVar : this.x) {
                            if (cVar != null) {
                                d dVar = (d) cVar;
                                c0.this.a((String) dVar.f226a);
                            }
                        }
                        this.x.clear();
                        break;
                    }
                }
                return true;
            case 7:
                synchronized (this.g) {
                    ArrayList<j3> arrayList = this.g;
                    if (h0.p == null) {
                        h0.p = new h0.b(u0Var);
                    }
                    h0.p.a(0L);
                    arrayList.add(h0.p);
                    break;
                }
                a((String[]) null, false);
                return true;
            case 9:
                a0 a0Var2 = this.s;
                if (!a0Var2.d) {
                    long jA2 = a0Var2.a();
                    if (!a0Var2.d) {
                        this.j.sendEmptyMessageDelayed(9, jA2 - System.currentTimeMillis());
                    }
                }
                return true;
            case 10:
                synchronized (this.g) {
                    this.C.a(this.g);
                    break;
                }
                c1 c1Var = this.C;
                int size = c1Var.b.size();
                if (size > 0) {
                    strArr = new String[size];
                    c1Var.b.toArray(strArr);
                    c1Var.b.clear();
                }
                a(strArr, false);
                return true;
            case 11:
                z zVar = this.b;
                if (zVar == null) {
                    z zVar2 = new z(this);
                    this.b = zVar2;
                    this.u.add(zVar2);
                } else {
                    zVar.d = false;
                }
                a(this.b);
                return true;
            case 12:
                Object obj = message.obj;
                a(obj != null ? obj.toString() : null);
                return true;
            case 13:
                if (f()) {
                    if (this.m == null) {
                        this.m = new y(this);
                    }
                    if (!this.u.contains(this.m)) {
                        this.u.add(this.m);
                    }
                    a(this.m);
                } else {
                    if (this.m != null) {
                        this.m.d = true;
                        this.u.remove(this.m);
                        this.m = null;
                    }
                    k1 k1Var = this.i;
                    k1Var.e(null);
                    k1Var.f("");
                    k1Var.a((JSONObject) null);
                }
                return true;
            case 14:
                a((String[]) null, true);
                return true;
            case 15:
                Object[] objArr = (Object[]) message.obj;
                boolean zBooleanValue = ((Boolean) objArr[0]).booleanValue();
                String str3 = (String) objArr[1];
                if (this.t != null) {
                    this.t.d = true;
                    this.u.remove(this.t);
                    this.t = null;
                }
                if (zBooleanValue) {
                    this.t = new e0(this, str3);
                    this.u.add(this.t);
                    this.j.removeMessages(6);
                    this.j.sendEmptyMessage(6);
                }
                return true;
            case 16:
                b((j3) message.obj);
                return true;
            case 17:
                Map map2 = (Map) message.obj;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("oaid", new JSONObject(map2));
                    String strC = this.i.c();
                    String strF = this.i.f();
                    jSONObject.put("bd_did", strC);
                    jSONObject.put("install_id", strF);
                    if (o4.c.b(new Object[0]).booleanValue()) {
                        str = "os";
                        str2 = "Harmony";
                    } else {
                        str = "os";
                        str2 = "Android";
                    }
                    jSONObject.put(str, str2);
                    jSONObject.put("aid", this.i.b());
                    this.d.D.debug("Report oaid success: {}", this.k.c(jSONObject));
                    break;
                } catch (Throwable th) {
                    this.d.D.error("Report oaid failed", th, new Object[0]);
                }
                return true;
            case 18:
                Object obj2 = message.obj;
                if (obj2 instanceof IPullAbTestConfigCallback) {
                    int i2 = message.arg1;
                    IPullAbTestConfigCallback iPullAbTestConfigCallback = (IPullAbTestConfigCallback) obj2;
                    if (f()) {
                        if (this.m == null) {
                            this.m = new y(this);
                        }
                        try {
                            JSONObject jSONObjectA = this.m.a(i2);
                            if (iPullAbTestConfigCallback != null) {
                                iPullAbTestConfigCallback.onRemoteConfig(jSONObjectA);
                            }
                        } catch (s2 unused5) {
                            if (iPullAbTestConfigCallback != null) {
                                iPullAbTestConfigCallback.onTimeoutError();
                            }
                        }
                    } else {
                        this.d.D.warn("ABTest is not enabled", new Object[0]);
                    }
                    break;
                } else {
                    a(this.m);
                }
                return true;
        }
    }
}
