package com.bytedance.bdtracker;

import android.os.Process;
import com.bytedance.applog.exception.AppCrashType;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.bdtracker.b;
import java.lang.Thread;
import java.util.Collections;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class k0 implements Thread.UncaughtExceptionHandler {
    public static volatile k0 b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Thread.UncaughtExceptionHandler f272a = Thread.getDefaultUncaughtExceptionHandler();

    public class a implements b.d {
        public a(k0 k0Var) {
        }

        @Override // com.bytedance.bdtracker.b.d
        public boolean a(d dVar) {
            return dVar.getInitConfig() != null && AppCrashType.hasJavaCrashType(dVar.getInitConfig().getTrackCrashType());
        }
    }

    public class b implements b.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.d f273a;
        public final /* synthetic */ q3 b;

        public b(k0 k0Var, b.d dVar, q3 q3Var) {
            this.f273a = dVar;
            this.b = q3Var;
        }

        @Override // com.bytedance.bdtracker.b.c
        public void a(d dVar) {
            if (this.f273a.a(dVar)) {
                dVar.receive(this.b);
                dVar.flush();
            }
        }
    }

    public k0() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static synchronized void a() {
        if (b == null) {
            b = new k0();
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        a aVar = new a(this);
        if (!com.bytedance.bdtracker.b.a(aVar)) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f272a;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
                return;
            }
            try {
                Process.killProcess(Process.myPid());
                System.exit(10);
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("$is_backstage", !v.b);
            jSONObject.put("$event_time", jCurrentTimeMillis);
            jSONObject.put("$crash_thread", thread.getName());
            jSONObject.put("$crash_process", n0.b());
            StringBuilder sb = new StringBuilder();
            for (Throwable cause = th; cause != null; cause = cause.getCause()) {
                sb.append(cause.toString());
                for (StackTraceElement stackTraceElement : cause.getStackTrace()) {
                    sb.append("\n\tat ");
                    sb.append(stackTraceElement);
                }
            }
            jSONObject.put("$detailed_stack", sb.toString());
        } catch (Throwable th2) {
            LoggerImpl.global().error(Collections.singletonList("ExceptionHandler"), "Collect crash params failed", th2, new Object[0]);
        }
        com.bytedance.bdtracker.b.a(new b(this, aVar, new q3("$crash", jSONObject)));
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.f272a;
        if (uncaughtExceptionHandler2 != null) {
            uncaughtExceptionHandler2.uncaughtException(thread, th);
            return;
        }
        try {
            Process.killProcess(Process.myPid());
            System.exit(10);
        } catch (Throwable unused2) {
        }
    }
}
