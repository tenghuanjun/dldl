package com.igexin.push.e;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e implements com.igexin.push.e.b.c {
    private static final String a = "SilentTimeTimerTask";
    private static e d;
    private long b = 0;
    private long c = 0;
    private boolean e = false;

    private e() {
    }

    public static e c() {
        if (d == null) {
            d = new e();
        }
        return d;
    }

    @Override // com.igexin.push.e.b.c
    public final void a() {
        d();
    }

    @Override // com.igexin.push.e.b.c
    public final void a(long j) {
        this.b = j;
    }

    @Override // com.igexin.push.e.b.c
    public final boolean b() {
        return System.currentTimeMillis() - this.b > this.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d() {
        /*
            r12 = this;
            r0 = 3600000(0x36ee80, double:1.7786363E-317)
            r12.c = r0
            long r0 = java.lang.System.currentTimeMillis()
            int r2 = com.igexin.push.config.d.b
            int r2 = com.igexin.push.config.d.b
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L85
            java.util.Calendar r2 = java.util.Calendar.getInstance()
            boolean r5 = com.igexin.push.f.c.a(r0)
            r6 = 5
            r7 = 13
            r8 = 12
            r9 = 11
            if (r5 == 0) goto L58
            boolean r5 = r12.e
            if (r5 != 0) goto L31
            r12.e = r3
            com.igexin.push.core.d r5 = com.igexin.push.core.d.a.a()
            com.igexin.push.d.a r5 = r5.i
            r5.b()
        L31:
            int r5 = com.igexin.push.config.d.a
            int r10 = com.igexin.push.config.d.b
            int r5 = r5 + r10
            r10 = 24
            if (r5 <= r10) goto L41
            int r5 = com.igexin.push.config.d.a
            int r11 = com.igexin.push.config.d.b
            int r5 = r5 + r11
            int r5 = r5 - r10
            goto L46
        L41:
            int r5 = com.igexin.push.config.d.a
            int r10 = com.igexin.push.config.d.b
            int r5 = r5 + r10
        L46:
            r2.set(r9, r5)
            r2.set(r8, r4)
            r2.set(r7, r4)
            long r4 = r2.getTimeInMillis()
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 >= 0) goto L7d
            goto L7a
        L58:
            boolean r5 = r12.e
            if (r5 == 0) goto L67
            r12.e = r4
            com.igexin.push.core.d r5 = com.igexin.push.core.d.a.a()
            com.igexin.push.d.a r5 = r5.i
            r5.a()
        L67:
            int r5 = com.igexin.push.config.d.a
            r2.set(r9, r5)
            r2.set(r8, r4)
            r2.set(r7, r4)
            long r4 = r2.getTimeInMillis()
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 >= 0) goto L7d
        L7a:
            r2.add(r6, r3)
        L7d:
            long r4 = r2.getTimeInMillis()
            long r4 = r4 - r0
            r12.c = r4
            goto L94
        L85:
            boolean r2 = r12.e
            if (r2 == 0) goto L94
            r12.e = r4
            com.igexin.push.core.d r2 = com.igexin.push.core.d.a.a()
            com.igexin.push.d.a r2 = r2.i
            r2.a()
        L94:
            long r4 = com.igexin.push.config.d.c
            long r6 = r12.c
            long r6 = r6 + r0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto Lb1
            long r4 = com.igexin.push.config.d.c
            long r4 = r4 - r0
            r12.c = r4
            boolean r0 = r12.e
            if (r0 != 0) goto Lb1
            r12.e = r3
            com.igexin.push.core.d r0 = com.igexin.push.core.d.a.a()
            com.igexin.push.d.a r0 = r0.i
            r0.b()
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.e.e.d():void");
    }
}
