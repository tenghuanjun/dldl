package com.bytedance.bdtracker;

import android.util.Log;
import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.applog.log.LoggerImpl;

/* JADX INFO: loaded from: classes2.dex */
public class y4 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f341a;
    public final String b;
    public final String c = Log.getStackTraceString(new RuntimeException("origin stacktrace"));

    public y4(Runnable runnable, String str) {
        this.f341a = runnable;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f341a.run();
        } catch (Throwable th) {
            IAppLogLogger iAppLogLoggerGlobal = LoggerImpl.global();
            StringBuilder sbA = a.a("Thread:");
            sbA.append(this.b);
            sbA.append(" exception\n");
            sbA.append(this.c);
            iAppLogLoggerGlobal.error(1, sbA.toString(), th, new Object[0]);
        }
    }
}
