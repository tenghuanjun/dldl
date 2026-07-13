package com.bytedance.bdtracker;

import android.os.SystemClock;
import com.bytedance.applog.log.IAppLogLogger;

/* JADX INFO: loaded from: classes2.dex */
public class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final IAppLogLogger f261a;
    public final String b;
    public long c = -1;
    public long d = 0;

    public i0(IAppLogLogger iAppLogLogger, String str) {
        this.f261a = iAppLogLogger;
        this.b = str;
    }

    public void a(long j) {
        if (j <= 0 || this.c <= 0) {
            return;
        }
        IAppLogLogger iAppLogLogger = this.f261a;
        if (iAppLogLogger != null) {
            iAppLogLogger.debug(4, "[DurationEvent:{}] Pause at:{}", this.b, Long.valueOf(j));
        }
        long j2 = this.d;
        if (j <= this.c) {
            j = SystemClock.elapsedRealtime();
        }
        this.d = (j - this.c) + j2;
        this.c = -1L;
    }

    public void b(long j) {
        if (j <= 0 || this.c >= 0) {
            return;
        }
        c(j);
        IAppLogLogger iAppLogLogger = this.f261a;
        if (iAppLogLogger != null) {
            iAppLogLogger.debug(4, "[DurationEvent:{}] Resume at:{}", this.b, Long.valueOf(j));
        }
    }

    public void c(long j) {
        this.c = j;
        IAppLogLogger iAppLogLogger = this.f261a;
        if (iAppLogLogger != null) {
            iAppLogLogger.debug(4, "[DurationEvent:{}] Start at:{}", this.b, Long.valueOf(j));
        }
    }
}
