package com.bytedance.bdtracker;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import com.bytedance.applog.log.LoggerImpl;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes2.dex */
public final class d5<SERVICE, RESULT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CountDownLatch f241a = new CountDownLatch(1);
    public final Intent b;
    public final b<SERVICE, RESULT> c;
    public final Context d;

    public class a implements ServiceConnection {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final CountDownLatch f242a;
        public final b<SERVICE, RESULT> b;
        public SERVICE c;

        public a(d5 d5Var, CountDownLatch countDownLatch, b<SERVICE, RESULT> bVar) {
            this.f242a = countDownLatch;
            this.b = bVar;
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LoggerImpl.global().debug(1, "ServiceBlockBinder#onServiceConnected " + componentName, new Object[0]);
            try {
                this.c = this.b.a(iBinder);
                try {
                    this.f242a.countDown();
                } catch (Exception e) {
                    LoggerImpl.global().error(1, "count down failed", e, new Object[0]);
                }
            } catch (Throwable th) {
                try {
                    LoggerImpl.global().error(1, "ServiceBlockBinder#onServiceConnected", th, new Object[0]);
                    try {
                        this.f242a.countDown();
                    } catch (Exception e2) {
                        LoggerImpl.global().error(1, "count down failed", e2, new Object[0]);
                    }
                } catch (Throwable th2) {
                    try {
                        this.f242a.countDown();
                    } catch (Exception e3) {
                        LoggerImpl.global().error(1, "count down failed", e3, new Object[0]);
                    }
                    throw th2;
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LoggerImpl.global().debug(1, "ServiceBlockBinder#onServiceDisconnected" + componentName, new Object[0]);
            try {
                this.f242a.countDown();
            } catch (Exception e) {
                LoggerImpl.global().error(1, "countDown failed", e, new Object[0]);
            }
        }
    }

    public interface b<T, RESULT> {
        T a(IBinder iBinder);

        RESULT a(T t);
    }

    public d5(Context context, Intent intent, b<SERVICE, RESULT> bVar) {
        this.d = context;
        this.b = intent;
        this.c = bVar;
    }

    public RESULT a() {
        Throwable th;
        d5<SERVICE, RESULT>.a aVar;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            LoggerImpl.global().warn(1, "can't run in ui thread", new Object[0]);
            return null;
        }
        try {
            aVar = new a(this, this.f241a, this.c);
            this.d.bindService(this.b, aVar, 1);
            this.f241a.await();
            try {
                return this.c.a(aVar.c);
            } catch (Throwable th2) {
                th = th2;
                try {
                    LoggerImpl.global().error(1, "blockFetchResult failed", th, new Object[0]);
                    return null;
                } finally {
                    a(aVar);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            aVar = null;
        }
    }

    public final void a(d5<SERVICE, RESULT>.a aVar) {
        if (aVar != null) {
            try {
                this.d.unbindService(aVar);
            } catch (Throwable th) {
                LoggerImpl.global().error(1, "Release connection failed", th, new Object[0]);
            }
        }
    }
}
