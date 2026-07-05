package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class ab extends Thread {
    private static boolean a;
    private List<aa> b = Collections.synchronizedList(new ArrayList());
    private List<ac> c = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: compiled from: BUGLY */
    static class a {
        public static final ab a = new ab();
    }

    public static ab a(Context context) {
        return a.a;
    }

    public final void a() {
        a(new Handler(Looper.getMainLooper()), OAIDHelper.TIMEOUT);
    }

    public final void b() {
        a(new Handler(Looper.getMainLooper()));
    }

    private void a(Handler handler, long j) {
        if (handler == null) {
            x.e("addThread handler should not be null", new Object[0]);
            return;
        }
        String name = handler.getLooper().getThread().getName();
        for (int i = 0; i < this.b.size(); i++) {
            if (this.b.get(i).e().equals(handler.getLooper().getThread().getName())) {
                x.e("addThread fail ,this thread has been added in monitor queue", new Object[0]);
                return;
            }
        }
        this.b.add(new aa(handler, name, OAIDHelper.TIMEOUT));
    }

    private void a(Handler handler) {
        if (handler == null) {
            x.e("removeThread handler should not be null", new Object[0]);
            return;
        }
        for (int i = 0; i < this.b.size(); i++) {
            if (this.b.get(i).e().equals(handler.getLooper().getThread().getName())) {
                x.c("remove handler::%s", this.b.get(i));
                this.b.remove(i);
            }
        }
    }

    public final boolean c() {
        if (!isAlive()) {
            return false;
        }
        interrupt();
        a = true;
        return true;
    }

    public final boolean d() {
        if (isAlive()) {
            return false;
        }
        a = false;
        start();
        return true;
    }

    public final void a(ac acVar) {
        if (this.c.contains(acVar)) {
            x.e("addThreadMonitorListeners fail ,this threadMonitorListener has been added in monitor queue", new Object[0]);
        } else {
            this.c.add(acVar);
        }
    }

    public final void b(ac acVar) {
        this.c.remove(acVar);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        setName("Bugly-ThreadMonitor");
        while (!a) {
            for (int i = 0; i < this.b.size(); i++) {
                this.b.get(i).a();
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            for (long jUptimeMillis2 = 2000; jUptimeMillis2 > 0 && !isInterrupted(); jUptimeMillis2 = 2000 - (SystemClock.uptimeMillis() - jUptimeMillis)) {
                try {
                    sleep(jUptimeMillis2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int iMax = 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                iMax = Math.max(iMax, this.b.get(i2).c());
            }
            if (iMax != 0 && iMax != 1) {
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < this.b.size(); i3++) {
                    aa aaVar = this.b.get(i3);
                    if (aaVar.b()) {
                        arrayList.add(aaVar);
                        aaVar.a(LongCompanionObject.MAX_VALUE);
                        x.d("to avoid upload block state repeated. as thread is blocked ,it may not be monitor until thread is unblock or this state has not been dealed with.", new Object[0]);
                    }
                }
                boolean z = false;
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    aa aaVar2 = (aa) arrayList.get(i4);
                    Thread threadD = aaVar2.d();
                    for (int i5 = 0; i5 < this.c.size(); i5++) {
                        if (this.c.get(i5).a(threadD)) {
                            z = true;
                        }
                    }
                    if (!z && aaVar2.e().contains("main")) {
                        aaVar2.f();
                        x.d("although thread is blocked ,may not be anr error,so restore handler check wait time and restart check main thread", new Object[0]);
                    }
                }
            }
        }
    }
}
