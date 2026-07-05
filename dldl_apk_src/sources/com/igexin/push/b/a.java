package com.igexin.push.b;

import android.text.TextUtils;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.d;
import com.igexin.push.core.e.e.AnonymousClass21;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a {
    static final String a = com.igexin.push.b.b.a + a.class.getName();
    private static final int q = 10;
    int b;
    protected int g;
    protected volatile long h;
    protected volatile long i;
    boolean j;
    private int l;
    private int m;
    private d n;
    private final List<b> o = new ArrayList();
    final List<d> c = new ArrayList();
    final Object d = new Object();
    private final Object p = new Object();
    public volatile EnumC0058a e = EnumC0058a.NORMAL;
    private int r = 0;
    public AtomicBoolean f = new AtomicBoolean(false);
    final Comparator<d> k = new Comparator<d>() { // from class: com.igexin.push.b.a.1
        private static int a(d dVar, d dVar2) {
            return (int) (dVar.c() - dVar2.c());
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(d dVar, d dVar2) {
            return (int) (dVar.c() - dVar2.c());
        }
    };

    /* JADX INFO: renamed from: com.igexin.push.b.a$a, reason: collision with other inner class name */
    protected enum EnumC0058a {
        NORMAL(0),
        BACKUP(1),
        TRY_NORMAL(2);

        int d;

        EnumC0058a(int i) {
            this.d = -1;
            this.d = i;
        }

        private int a() {
            return this.d;
        }

        public static EnumC0058a a(int i) {
            for (EnumC0058a enumC0058a : values()) {
                if (enumC0058a.d == i) {
                    return enumC0058a;
                }
            }
            return null;
        }
    }

    public static final class b {
        public String a;
        public long b;

        public final b a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return this;
            }
            try {
                this.a = jSONObject.getString("address");
                this.b = jSONObject.getLong("outdateTime");
            } catch (Exception unused) {
            }
            return this;
        }

        public final JSONObject a() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("address", this.a);
                jSONObject.put("outdateTime", this.b);
                return jSONObject;
            } catch (Exception unused) {
                return null;
            }
        }

        public final String toString() {
            return "ServerAddress{address='" + this.a + "', outdateTime=" + this.b + '}';
        }
    }

    private String a(boolean z) {
        try {
            synchronized (this.p) {
                String str = this.j ? com.igexin.push.core.e.at : com.igexin.push.core.e.au;
                if (this.o.isEmpty() && TextUtils.isEmpty(str)) {
                    com.igexin.b.a.c.a.a(a + "cm list size = 0", new Object[0]);
                    this.m = 0;
                    this.l = 0;
                    return null;
                }
                if (this.o.isEmpty() && !TextUtils.isEmpty(str)) {
                    a(str);
                }
                com.igexin.b.a.c.a.a(a + "cm try = " + this.m + " times", new Object[0]);
                if (this.m >= this.o.size() * 1) {
                    com.igexin.b.a.c.a.a(a + "cm invalid", new Object[0]);
                    this.m = 0;
                    this.l = 0;
                    this.o.clear();
                    return null;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                Iterator<b> it = this.o.iterator();
                while (it.hasNext()) {
                    b next = it.next();
                    if (next.b < jCurrentTimeMillis) {
                        com.igexin.b.a.c.a.a(a + "|add[" + next.a + "] outDate", new Object[0]);
                        it.remove();
                    }
                }
                h();
                if (this.o.isEmpty()) {
                    return null;
                }
                if (z) {
                    this.m++;
                }
                this.l = this.l >= this.o.size() ? 0 : this.l;
                String str2 = this.o.get(this.l).a;
                this.l++;
                return str2;
            }
        } catch (Exception e) {
            com.igexin.b.a.c.a.a(a + "|" + e.toString(), new Object[0]);
            return null;
        }
    }

    private void a(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                this.o.add(new b().a(jSONArray.getJSONObject(i)));
            }
            com.igexin.b.a.c.a.a(a + "|get cm from cache, isWifi = " + this.j + ", lastCmList = " + str, new Object[0]);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(a + "|" + th.toString(), new Object[0]);
        }
    }

    private String b(boolean z) {
        String strA;
        synchronized (this.d) {
            this.b = this.b >= this.c.size() ? 0 : this.b;
            this.n = this.c.get(this.b);
            strA = this.n.a(z);
        }
        return strA;
    }

    private void c(boolean z) {
        this.j = z;
    }

    private List<b> g() {
        return this.o;
    }

    private void h() {
        JSONArray jSONArray = new JSONArray();
        Iterator<b> it = this.o.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().a());
        }
        com.igexin.push.core.e.e eVarA = com.igexin.push.core.e.e.a();
        String string = jSONArray.length() == 0 ? "null" : jSONArray.toString();
        boolean z = !this.j;
        if (string != null) {
            String str = string.equals("null") ? null : string;
            if (z && !TextUtils.equals(com.igexin.push.core.e.au, string)) {
                com.igexin.push.core.e.au = str;
            } else if (z || TextUtils.equals(com.igexin.push.core.e.at, string)) {
                return;
            } else {
                com.igexin.push.core.e.at = str;
            }
            com.igexin.b.a.c.a.a(com.igexin.push.core.e.e.a + "|saveLastRedirectCmList isMobile = " + z + ", lastRedirectCmList = " + string, new Object[0]);
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) eVarA.new AnonymousClass21(z, string), false, true);
        }
    }

    private void i() {
        synchronized (this.d) {
            this.b = 0;
            Collections.sort(this.c, this.k);
        }
    }

    private void j() {
        EnumC0058a enumC0058a = this.e;
        com.igexin.b.a.c.a.a(a + "|detect success, current type = " + this.e, new Object[0]);
        if (this.e == EnumC0058a.BACKUP) {
            a(EnumC0058a.TRY_NORMAL);
            com.igexin.push.core.d unused = d.a.a;
            com.igexin.push.d.a.a(true);
        }
    }

    private void k() {
        EnumC0058a enumC0058a = this.e;
        com.igexin.b.a.c.a.a(a + "|before disconnect, type = " + this.e, new Object[0]);
        switch (this.e) {
            case NORMAL:
                if (System.currentTimeMillis() - this.i > 86400000 && this.g > com.igexin.push.config.d.x) {
                    a(EnumC0058a.BACKUP);
                    break;
                }
                break;
            case BACKUP:
                if (System.currentTimeMillis() - this.h > com.igexin.push.config.d.v) {
                    a(EnumC0058a.TRY_NORMAL);
                }
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0085 A[Catch: all -> 0x00ba, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0022, B:7:0x0026, B:8:0x002a, B:9:0x0033, B:23:0x00ab, B:11:0x0037, B:13:0x003b, B:14:0x003e, B:16:0x0047, B:17:0x004d, B:18:0x0074, B:19:0x0078, B:21:0x0085, B:22:0x008a), top: B:29:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final synchronized void a(com.igexin.push.b.a.EnumC0058a r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lba
            r0.<init>()     // Catch: java.lang.Throwable -> Lba
            java.lang.String r1 = com.igexin.push.b.a.a     // Catch: java.lang.Throwable -> Lba
            r0.append(r1)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r1 = "|set domain type = "
            r0.append(r1)     // Catch: java.lang.Throwable -> Lba
            r0.append(r5)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lba
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lba
            com.igexin.b.a.c.a.a(r0, r2)     // Catch: java.lang.Throwable -> Lba
            boolean r0 = com.igexin.push.config.d.f     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto Lb8
            com.igexin.push.b.a$a r0 = r4.e     // Catch: java.lang.Throwable -> Lba
            if (r0 == r5) goto L2a
            r0 = 0
            r4.a(r0)     // Catch: java.lang.Throwable -> Lba
        L2a:
            int[] r0 = com.igexin.push.b.a.AnonymousClass2.a     // Catch: java.lang.Throwable -> Lba
            int r2 = r5.ordinal()     // Catch: java.lang.Throwable -> Lba
            r0 = r0[r2]     // Catch: java.lang.Throwable -> Lba
            r2 = 1
            switch(r0) {
                case 1: goto L78;
                case 2: goto L3e;
                case 3: goto L37;
                default: goto L36;
            }     // Catch: java.lang.Throwable -> Lba
        L36:
            goto Lab
        L37:
            com.igexin.push.b.a$a r0 = r4.e     // Catch: java.lang.Throwable -> Lba
            if (r0 == r5) goto L78
            r4.r = r1     // Catch: java.lang.Throwable -> Lba
            goto L78
        L3e:
            java.util.concurrent.atomic.AtomicBoolean r0 = r4.f     // Catch: java.lang.Throwable -> Lba
            r0.set(r2)     // Catch: java.lang.Throwable -> Lba
            com.igexin.push.b.a$a r0 = r4.e     // Catch: java.lang.Throwable -> Lba
            if (r0 == r5) goto L4d
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lba
            r4.h = r2     // Catch: java.lang.Throwable -> Lba
        L4d:
            java.lang.String[] r0 = com.igexin.push.config.SDKUrlConfig.XFR_ADDRESS_BAK     // Catch: java.lang.Throwable -> Lba
            r0 = r0[r1]     // Catch: java.lang.Throwable -> Lba
            com.igexin.push.config.SDKUrlConfig.setConnectAddress(r0)     // Catch: java.lang.Throwable -> Lba
            com.igexin.push.config.SDKUrlConfig.getConnectAddress()     // Catch: java.lang.Throwable -> Lba
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lba
            r0.<init>()     // Catch: java.lang.Throwable -> Lba
            java.lang.String r2 = com.igexin.push.b.a.a     // Catch: java.lang.Throwable -> Lba
            r0.append(r2)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r2 = "|set domain type backup cm = "
            r0.append(r2)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r2 = com.igexin.push.config.SDKUrlConfig.getConnectAddress()     // Catch: java.lang.Throwable -> Lba
            r0.append(r2)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lba
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lba
        L74:
            com.igexin.b.a.c.a.a(r0, r1)     // Catch: java.lang.Throwable -> Lba
            goto Lab
        L78:
            r4.b = r1     // Catch: java.lang.Throwable -> Lba
            java.lang.String r0 = r4.b(r2)     // Catch: java.lang.Throwable -> Lba
            com.igexin.push.config.SDKUrlConfig.setConnectAddress(r0)     // Catch: java.lang.Throwable -> Lba
            com.igexin.push.b.a$a r0 = com.igexin.push.b.a.EnumC0058a.NORMAL     // Catch: java.lang.Throwable -> Lba
            if (r5 != r0) goto L8a
            java.util.concurrent.atomic.AtomicBoolean r0 = r4.f     // Catch: java.lang.Throwable -> Lba
            r0.set(r1)     // Catch: java.lang.Throwable -> Lba
        L8a:
            com.igexin.push.config.SDKUrlConfig.getConnectAddress()     // Catch: java.lang.Throwable -> Lba
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lba
            r0.<init>()     // Catch: java.lang.Throwable -> Lba
            java.lang.String r2 = com.igexin.push.b.a.a     // Catch: java.lang.Throwable -> Lba
            r0.append(r2)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r2 = "|set domain type normal cm = "
            r0.append(r2)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r2 = com.igexin.push.config.SDKUrlConfig.getConnectAddress()     // Catch: java.lang.Throwable -> Lba
            r0.append(r2)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lba
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lba
            goto L74
        Lab:
            r4.e = r5     // Catch: java.lang.Throwable -> Lba
            com.igexin.push.b.c r5 = com.igexin.push.b.c.a()     // Catch: java.lang.Throwable -> Lba
            com.igexin.push.b.h r5 = r5.f()     // Catch: java.lang.Throwable -> Lba
            r5.n()     // Catch: java.lang.Throwable -> Lba
        Lb8:
            monitor-exit(r4)
            return
        Lba:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.b.a.a(com.igexin.push.b.a$a):void");
    }

    public final void a(List<b> list) {
        synchronized (this.p) {
            this.l = 0;
            this.m = 0;
            this.o.clear();
            if (list != null) {
                this.o.addAll(list);
                com.igexin.b.a.c.a.a(a + "|set cm list: " + list.toString(), new Object[0]);
            }
            h();
        }
    }

    public final boolean a() {
        boolean z;
        String strA;
        try {
            com.igexin.push.core.d unused = d.a.a;
            z = true;
            boolean z2 = !com.igexin.push.d.a.d();
            strA = a(z2);
            com.igexin.b.a.c.a.a(a + "|get from cm = " + strA, new Object[0]);
            if (strA == null) {
                if (com.igexin.push.config.d.f && this.e == EnumC0058a.BACKUP) {
                    this.b = this.b >= SDKUrlConfig.XFR_ADDRESS_BAK.length ? 0 : this.b;
                    strA = SDKUrlConfig.XFR_ADDRESS_BAK[this.b];
                    this.b++;
                } else {
                    if (this.n != null && !this.n.d()) {
                        this.b++;
                    }
                    strA = b(z2);
                }
                z = false;
            }
        } catch (Exception e) {
            e = e;
            z = false;
        }
        try {
            if (!SDKUrlConfig.getConnectAddress().equals(strA)) {
                SDKUrlConfig.getConnectAddress();
                com.igexin.b.a.c.a.a(a + "|address changed : form [" + SDKUrlConfig.getConnectAddress() + "] to [" + strA + "]", new Object[0]);
            }
            SDKUrlConfig.setConnectAddress(strA);
        } catch (Exception e2) {
            e = e2;
            com.igexin.b.a.c.a.a(a + "|switch address|" + e.toString(), new Object[0]);
        }
        return z;
    }

    public final synchronized void b() {
        this.m = 0;
        if (this.n != null) {
            this.n.e();
        }
    }

    public final void b(List<d> list) {
        synchronized (this.d) {
            this.c.clear();
            this.c.addAll(list);
            Collections.sort(this.c, this.k);
        }
    }

    public final synchronized void c() {
        this.g++;
        com.igexin.b.a.c.a.a(a + "|loginFailedlCnt = " + this.g, new Object[0]);
    }

    public final void d() {
        if (AnonymousClass2.a[this.e.ordinal()] == 2 && System.currentTimeMillis() - this.h > com.igexin.push.config.d.v) {
            a(EnumC0058a.TRY_NORMAL);
        }
    }

    public final void e() {
        if (this.e != EnumC0058a.BACKUP) {
            this.g = 0;
        }
        switch (this.e) {
            case NORMAL:
                this.i = System.currentTimeMillis();
                c.a().f().n();
                this.f.set(false);
                break;
            case TRY_NORMAL:
                a(EnumC0058a.NORMAL);
                this.f.set(false);
                break;
        }
    }

    public final void f() {
        EnumC0058a enumC0058a;
        EnumC0058a enumC0058a2 = this.e;
        com.igexin.b.a.c.a.a(a + "|before disconnect, type = " + this.e, new Object[0]);
        switch (this.e) {
            case NORMAL:
                if (System.currentTimeMillis() - this.i > 86400000 && this.g > com.igexin.push.config.d.x) {
                    enumC0058a = EnumC0058a.BACKUP;
                    a(enumC0058a);
                }
                break;
            case BACKUP:
                if (System.currentTimeMillis() - this.h > com.igexin.push.config.d.v) {
                    enumC0058a = EnumC0058a.TRY_NORMAL;
                    a(enumC0058a);
                }
                break;
        }
        if (com.igexin.push.core.e.r && this.e != EnumC0058a.BACKUP) {
            this.i = System.currentTimeMillis();
            c.a().f().n();
        }
        switch (this.e) {
            case TRY_NORMAL:
                int i = this.r + 1;
                this.r = i;
                if (i >= 10) {
                    this.g = 0;
                    this.h = System.currentTimeMillis();
                    a(EnumC0058a.BACKUP);
                }
                break;
        }
    }
}
