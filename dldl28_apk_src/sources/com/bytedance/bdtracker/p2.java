package com.bytedance.bdtracker;

import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;

/* JADX INFO: loaded from: classes2.dex */
public class p2 {
    public static final long[][] h = {new long[]{120000, 0, 12}, new long[]{120000, 5, 1}, new long[]{240000, 5, 1}, new long[]{480000, 4, 1}, new long[]{960000, 2, 1}};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f305a;
    public i1 b;
    public int c;
    public int d;
    public int e;
    public long f;
    public long g;

    public p2(String str, i1 i1Var) {
        this.b = i1Var;
        this.f305a = str;
        this.c = 0;
        if (System.currentTimeMillis() - i1Var.f.getLong(this.f305a + "downgrade_time", 0L) < 10800000) {
            this.c = this.b.f.getInt(this.f305a + "downgrade_index", 0);
            return;
        }
        this.b.f.edit().remove(this.f305a + "downgrade_time").remove(this.f305a + "downgrade_index").apply();
    }

    public final boolean a() {
        return this.b.c.isCongestionControlEnable();
    }

    public void b() {
        if (a()) {
            if (this.c >= h.length - 1) {
                this.e = 0;
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.c++;
            this.d = 1;
            this.e = 0;
            this.f = jCurrentTimeMillis;
            this.g = jCurrentTimeMillis;
            this.b.f.edit().putLong(this.f305a + "downgrade_time", jCurrentTimeMillis).putInt(this.f305a + "downgrade_index", this.c).apply();
        }
    }

    public void c() {
        if (a()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            int i = this.e;
            long j = i;
            long[][] jArr = h;
            int i2 = this.c;
            if (j < jArr[i2][1] && jCurrentTimeMillis - this.g <= MonitorCommonConstants.LAST_STOP_INTERVAL) {
                this.e = i + 1;
                return;
            }
            if (i2 > 0) {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                this.c--;
                this.d = 1;
                this.e = 1;
                this.f = jCurrentTimeMillis2;
                this.g = jCurrentTimeMillis2;
                this.b.f.edit().putLong(this.f305a + "downgrade_time", jCurrentTimeMillis2).putInt(this.f305a + "downgrade_index", this.c).apply();
            }
        }
    }
}
