package com.bytedance.bdtracker;

import android.text.TextUtils;
import com.bytedance.applog.IAppLogInstance;
import com.bytedance.applog.log.EventBus;
import com.volcengine.common.contant.CommonConstants;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class h0 {
    public static final AtomicLong o = new AtomicLong(1000);
    public static b p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f256a;
    public final c0 b;
    public t3 c;
    public t3 d;
    public String e;
    public volatile long f;
    public int g;
    public volatile boolean i;
    public long j;
    public int k;
    public String l;
    public volatile String m;
    public long h = -1;
    public volatile boolean n = false;

    public class a implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f257a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ long c;

        public a(d dVar, boolean z, long j) {
            this.f257a = dVar;
            this.b = z;
            this.c = j;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(CommonConstants.key_appId, this.f257a.m);
                jSONObject.put(CommonConstants.key_SessionId, h0.this.e);
                boolean z = true;
                jSONObject.put("isBackground", !this.b);
                if (this.c == -1) {
                    z = false;
                }
                jSONObject.put("newLaunch", z);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public static class b extends w3 {
        public /* synthetic */ b(a aVar) {
        }
    }

    public h0(c0 c0Var) {
        this.b = c0Var;
    }

    public static boolean a(j3 j3Var) {
        if (j3Var instanceof t3) {
            return ((t3) j3Var).k();
        }
        return false;
    }

    public String a() {
        return this.e;
    }

    public void a(IAppLogInstance iAppLogInstance, j3 j3Var) {
        JSONObject jSONObject;
        if (j3Var != null) {
            k1 k1Var = this.b.i;
            j3Var.m = iAppLogInstance.getAppId();
            j3Var.f = this.f256a;
            j3Var.g = k1Var.l();
            j3Var.h = k1Var.m();
            j3Var.i = k1Var.j();
            j3Var.e = this.e;
            j3Var.d = o.incrementAndGet();
            String strA = j3Var.j;
            String strA2 = k1Var.a();
            if (TextUtils.isEmpty(strA)) {
                strA = strA2;
            } else if (!TextUtils.isEmpty(strA2)) {
                Set<String> setC = k1Var.c(strA2);
                setC.addAll(k1Var.c(strA));
                strA = k1Var.a(setC);
            }
            j3Var.j = strA;
            j3Var.k = i4.b(this.b.b(), true).f267a;
            if (!(j3Var instanceof q3) || this.h <= 0 || !n0.a(((q3) j3Var).u, "$crash") || (jSONObject = j3Var.o) == null) {
                return;
            }
            try {
                jSONObject.put("$session_duration", System.currentTimeMillis() - this.h);
            } catch (Throwable unused) {
            }
        }
    }

    public String b() {
        return this.m;
    }

    public boolean c() {
        return this.i && this.j == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized android.os.Bundle a(long r6, long r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            long r0 = r5.f     // Catch: java.lang.Throwable -> L51
            com.bytedance.bdtracker.c0 r2 = r5.b     // Catch: java.lang.Throwable -> L51
            com.bytedance.bdtracker.i1 r2 = r2.e     // Catch: java.lang.Throwable -> L51
            com.bytedance.applog.InitConfig r2 = r2.c     // Catch: java.lang.Throwable -> L51
            boolean r2 = r2.isPlayEnable()     // Catch: java.lang.Throwable -> L51
            if (r2 == 0) goto L4e
            boolean r2 = r5.c()     // Catch: java.lang.Throwable -> L51
            if (r2 == 0) goto L4e
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L4e
            long r0 = r6 - r0
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 <= 0) goto L4e
            android.os.Bundle r8 = new android.os.Bundle     // Catch: java.lang.Throwable -> L51
            r8.<init>()     // Catch: java.lang.Throwable -> L51
            int r9 = r5.k     // Catch: java.lang.Throwable -> L51
            java.lang.String r2 = "session_no"
            r8.putInt(r2, r9)     // Catch: java.lang.Throwable -> L51
            int r9 = r5.g     // Catch: java.lang.Throwable -> L51
            int r9 = r9 + 1
            r5.g = r9     // Catch: java.lang.Throwable -> L51
            java.lang.String r2 = "send_times"
            r8.putInt(r2, r9)     // Catch: java.lang.Throwable -> L51
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            java.lang.String r9 = "current_duration"
            r8.putLong(r9, r0)     // Catch: java.lang.Throwable -> L51
            long r0 = r5.h     // Catch: java.lang.Throwable -> L51
            java.lang.String r9 = com.bytedance.bdtracker.j3.b(r0)     // Catch: java.lang.Throwable -> L51
            java.lang.String r0 = "session_start_time"
            r8.putString(r0, r9)     // Catch: java.lang.Throwable -> L51
            r5.f = r6     // Catch: java.lang.Throwable -> L51
            goto L4f
        L4e:
            r8 = 0
        L4f:
            monitor-exit(r5)
            return r8
        L51:
            r6 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L51
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.h0.a(long, long):android.os.Bundle");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0057, code lost:
    
        if (r16.h > (r18.c + 7200000)) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(com.bytedance.bdtracker.d r17, com.bytedance.bdtracker.j3 r18, java.util.ArrayList<com.bytedance.bdtracker.j3> r19) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.h0.a(com.bytedance.bdtracker.d, com.bytedance.bdtracker.j3, java.util.ArrayList):boolean");
    }

    public synchronized r3 a(d dVar, j3 j3Var, List<j3> list, boolean z) {
        r3 r3Var;
        long j = j3Var instanceof b ? -1L : j3Var.c;
        this.e = UUID.randomUUID().toString();
        a1.a("session_start", (EventBus.DataFetcher) new a(dVar, z, j));
        if (z && !this.b.v && TextUtils.isEmpty(this.m)) {
            this.m = this.e;
        }
        AtomicLong atomicLong = o;
        atomicLong.set(1000L);
        this.h = j;
        this.i = z;
        this.j = 0L;
        this.f = 0L;
        if (z) {
            Calendar calendar = Calendar.getInstance();
            StringBuilder sbA = com.bytedance.bdtracker.a.a("");
            sbA.append(calendar.get(1));
            sbA.append(calendar.get(2));
            sbA.append(calendar.get(5));
            String string = sbA.toString();
            i1 i1Var = this.b.e;
            if (TextUtils.isEmpty(this.l)) {
                this.l = i1Var.e.getString("session_last_day", "");
                this.k = i1Var.e.getInt("session_order", 0);
            }
            if (string.equals(this.l)) {
                this.k++;
            } else {
                this.l = string;
                this.k = 1;
            }
            i1Var.e.edit().putString("session_last_day", string).putInt("session_order", this.k).apply();
            this.g = 0;
            this.f = j3Var.c;
        }
        if (j != -1) {
            r3Var = new r3();
            r3Var.m = j3Var.m;
            r3Var.e = this.e;
            r3Var.u = !this.i;
            r3Var.d = atomicLong.incrementAndGet();
            r3Var.a(this.h);
            r3Var.t = this.b.i.o();
            r3Var.s = this.b.i.n();
            r3Var.f = this.f256a;
            r3Var.g = this.b.i.l();
            r3Var.h = this.b.i.m();
            r3Var.i = dVar.getSsid();
            r3Var.j = dVar.getAbSdkVersion();
            r3Var.w = z ? this.b.e.f.getInt("is_first_time_launch", 1) : 0;
            if (z && r3Var.w == 1) {
                this.b.e.f.edit().putInt("is_first_time_launch", 0).apply();
            }
            t3 t3VarA = v.a();
            if (t3VarA != null) {
                r3Var.y = t3VarA.u;
                r3Var.x = t3VarA.v;
            }
            if (this.i && this.n) {
                r3Var.z = this.n;
                this.n = false;
            }
            list.add(r3Var);
        } else {
            r3Var = null;
        }
        d dVar2 = this.b.d;
        if (dVar2.l <= 0) {
            dVar2.l = 6;
        }
        dVar.D.debug("Start new session:{} with background:{}", this.e, Boolean.valueOf(!this.i));
        return r3Var;
    }
}
