package com.tencent.bugly.proguard;

import android.content.Context;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class v implements Runnable {
    private int a;
    private int b;
    private final Context c;
    private final int d;
    private final byte[] e;
    private final com.tencent.bugly.crashreport.common.info.a f;
    private final com.tencent.bugly.crashreport.common.strategy.a g;
    private final s h;
    private final u i;
    private final int j;
    private final t k;
    private final t l;
    private String m;
    private final String n;
    private final Map<String, String> o;
    private int p;
    private long q;
    private long r;
    private boolean s;
    private boolean t;

    public v(Context context, int i, int i2, byte[] bArr, String str, String str2, t tVar, boolean z, boolean z2) {
        this(context, i, i2, bArr, str, str2, tVar, z, 2, 30000, z2, null);
    }

    public v(Context context, int i, int i2, byte[] bArr, String str, String str2, t tVar, boolean z, int i3, int i4, boolean z2, Map<String, String> map) {
        this.a = 2;
        this.b = 30000;
        this.m = null;
        this.p = 0;
        this.q = 0L;
        this.r = 0L;
        this.s = true;
        this.t = false;
        this.c = context;
        this.f = com.tencent.bugly.crashreport.common.info.a.a(context);
        this.e = bArr;
        this.g = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.h = s.a(context);
        this.i = u.a();
        this.j = i;
        this.m = str;
        this.n = str2;
        this.k = tVar;
        this.l = null;
        this.s = z;
        this.d = i2;
        if (i3 > 0) {
            this.a = i3;
        }
        if (i4 > 0) {
            this.b = i4;
        }
        this.t = z2;
        this.o = map;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.tencent.bugly.proguard.aq r4, boolean r5, int r6, java.lang.String r7, int r8) {
        /*
            r3 = this;
            int r4 = r3.d
            r0 = 630(0x276, float:8.83E-43)
            if (r4 == r0) goto L1a
            r0 = 640(0x280, float:8.97E-43)
            if (r4 == r0) goto L17
            r0 = 830(0x33e, float:1.163E-42)
            if (r4 == r0) goto L1a
            r0 = 840(0x348, float:1.177E-42)
            if (r4 == r0) goto L17
            java.lang.String r4 = java.lang.String.valueOf(r4)
            goto L1c
        L17:
            java.lang.String r4 = "userinfo"
            goto L1c
        L1a:
            java.lang.String r4 = "crash"
        L1c:
            r0 = 1
            r1 = 0
            if (r5 == 0) goto L2a
            java.lang.Object[] r6 = new java.lang.Object[r0]
            r6[r1] = r4
            java.lang.String r4 = "[Upload] Success: %s"
            com.tencent.bugly.proguard.x.a(r4, r6)
            goto L47
        L2a:
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r2[r1] = r6
            r2[r0] = r4
            r4 = 2
            r2[r4] = r7
            java.lang.String r4 = "[Upload] Failed to upload(%d) %s: %s"
            com.tencent.bugly.proguard.x.e(r4, r2)
            boolean r4 = r3.s
            if (r4 == 0) goto L47
            com.tencent.bugly.proguard.u r4 = r3.i
            r6 = 0
            r4.a(r8, r6)
        L47:
            long r6 = r3.q
            long r0 = r3.r
            long r6 = r6 + r0
            r0 = 0
            int r4 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r4 <= 0) goto L67
            com.tencent.bugly.proguard.u r4 = r3.i
            boolean r6 = r3.t
            long r6 = r4.a(r6)
            long r0 = r3.q
            long r6 = r6 + r0
            long r0 = r3.r
            long r6 = r6 + r0
            com.tencent.bugly.proguard.u r4 = r3.i
            boolean r8 = r3.t
            r4.a(r6, r8)
        L67:
            com.tencent.bugly.proguard.t r4 = r3.k
            if (r4 == 0) goto L6e
            r4.a(r5)
        L6e:
            com.tencent.bugly.proguard.t r4 = r3.l
            if (r4 == 0) goto L75
            r4.a(r5)
        L75:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.a(com.tencent.bugly.proguard.aq, boolean, int, java.lang.String, int):void");
    }

    private static boolean a(aq aqVar, com.tencent.bugly.crashreport.common.info.a aVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        if (aqVar == null) {
            x.d("resp == null!", new Object[0]);
            return false;
        }
        if (aqVar.a != 0) {
            x.e("resp result error %d", Byte.valueOf(aqVar.a));
            return false;
        }
        try {
            if (!z.a(aqVar.d) && !com.tencent.bugly.crashreport.common.info.a.b().i().equals(aqVar.d)) {
                p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "gateway", aqVar.d.getBytes("UTF-8"), (o) null, true);
                aVar.d(aqVar.d);
            }
            if (!z.a(aqVar.f) && !com.tencent.bugly.crashreport.common.info.a.b().j().equals(aqVar.f)) {
                p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "device", aqVar.f.getBytes("UTF-8"), (o) null, true);
                aVar.e(aqVar.f);
            }
        } catch (Throwable th) {
            x.a(th);
        }
        aVar.i = aqVar.e;
        if (aqVar.b == 510) {
            if (aqVar.c == null) {
                x.e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(aqVar.b));
                return false;
            }
            as asVar = (as) a.a(aqVar.c, as.class);
            if (asVar == null) {
                x.e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(aqVar.b));
                return false;
            }
            aVar2.a(asVar);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:152:0x02a7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0240 A[Catch: all -> 0x047f, TryCatch #2 {all -> 0x047f, blocks: (B:3:0x0007, B:5:0x0019, B:8:0x0027, B:11:0x002c, B:13:0x0040, B:15:0x0072, B:17:0x0085, B:19:0x0089, B:21:0x008d, B:24:0x0093, B:26:0x009b, B:28:0x00a7, B:30:0x00cd, B:31:0x00d2, B:33:0x00d6, B:35:0x0109, B:37:0x0115, B:39:0x011b, B:41:0x0127, B:43:0x012f, B:45:0x013b, B:46:0x0151, B:49:0x0159, B:51:0x0170, B:52:0x017d, B:54:0x018f, B:55:0x0194, B:58:0x01c5, B:60:0x01da, B:64:0x01e6, B:67:0x01ed, B:70:0x01f5, B:82:0x0240, B:84:0x026c, B:85:0x0274, B:87:0x027a, B:88:0x029b, B:94:0x02d8, B:96:0x02e3, B:97:0x02f8, B:99:0x0346, B:104:0x0365, B:72:0x01ff, B:74:0x0205, B:75:0x020d, B:77:0x021b, B:78:0x0227, B:79:0x0234, B:106:0x038d, B:108:0x039f, B:110:0x03a2, B:111:0x03aa, B:113:0x03b0, B:114:0x03cb, B:116:0x03d7, B:118:0x03df, B:120:0x03eb, B:122:0x03f2, B:124:0x03fe, B:126:0x0406, B:128:0x0412, B:130:0x0416, B:131:0x041b, B:135:0x0430, B:137:0x0443, B:139:0x044f, B:134:0x042d, B:141:0x045b, B:143:0x0467, B:145:0x0473), top: B:156:0x0007 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 1162
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.run():void");
    }

    public final void a(long j) {
        this.p++;
        this.q += j;
    }

    public final void b(long j) {
        this.r += j;
    }

    private static String a(String str) {
        if (z.a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", str, UUID.randomUUID().toString());
        } catch (Throwable th) {
            x.a(th);
            return str;
        }
    }
}
