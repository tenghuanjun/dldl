package com.igexin.b.a.d;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import com.igexin.push.c.c.o;
import com.igexin.push.f.n;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class g extends BroadcastReceiver implements Comparator<f> {
    public static final long D = TimeUnit.SECONDS.toMillis(2);
    protected static final String E = "AlarmTaskSchedule.";
    protected static final String F = "AlarmTaskScheduleBak.";
    protected static final String G = "AlarmNioTaskSchedule.";
    public static final String h = "TaskService";
    public static final String i = "com.igexin.b.a.d.g";
    static final byte j = -1;
    static final byte k = 0;
    static final byte l = 1;
    static final byte m = 2;
    static final byte n = -128;
    static final byte o = 7;
    public String A;
    volatile long B;
    public volatile boolean C;
    public PowerManager u;
    public AlarmManager v;
    public Intent w;
    public PendingIntent x;
    public Intent y;
    public PendingIntent z;
    final ReentrantLock t = new ReentrantLock();
    public boolean H = false;
    final HashMap<Long, com.igexin.b.a.d.a.c> q = new HashMap<>(7);
    public final e<f> s = new e<>(this, this);
    final d r = new d();
    public final b p = new b();

    final class a {
        volatile int g;
        final ReentrantLock c = new ReentrantLock();
        final BlockingQueue<f> a = new SynchronousQueue();
        final HashMap<Integer, RunnableC0056a> b = new HashMap<>();
        volatile long e = TimeUnit.SECONDS.toNanos(60);
        volatile int f = 0;
        ThreadFactory d = new b();
        volatile int h = Integer.MAX_VALUE;

        /* JADX INFO: renamed from: com.igexin.b.a.d.g$a$a, reason: collision with other inner class name */
        final class RunnableC0056a implements Runnable {
            final BlockingQueue<f> a = new LinkedBlockingQueue();
            f b;
            f c;
            volatile int d;
            volatile boolean e;

            public RunnableC0056a(f fVar) {
                this.b = fVar;
            }

            private void a() {
                this.a.clear();
                this.c = null;
            }

            private void a(f fVar) {
                if (this.d == 0) {
                    this.d = fVar.C;
                }
                f fVar2 = fVar;
                boolean z = true;
                while (z) {
                    try {
                        try {
                            fVar2.b_();
                            fVar2.n();
                            if (!fVar2.v) {
                                fVar2.d_();
                            }
                            boolean z2 = fVar2.m;
                            boolean z3 = fVar2.p;
                            long j = fVar2.w;
                        } catch (Exception e) {
                            com.igexin.b.a.c.a.a(g.h + e.toString(), new Object[0]);
                            fVar2.v = true;
                            fVar2.E = e;
                            fVar2.o();
                            fVar2.k();
                            g.this.a((Object) fVar2);
                            g.this.e();
                            if (!fVar2.v) {
                                fVar2.d_();
                            }
                            boolean z4 = fVar2.m;
                            boolean z5 = fVar2.p;
                            long j2 = fVar2.w;
                            if (fVar2.m || !fVar2.p || fVar2.w == 0) {
                            }
                        }
                    } finally {
                    }
                    if (fVar2.m || !fVar2.p || fVar2.w == 0) {
                        fVar2 = null;
                        z = false;
                    }
                }
            }

            private f b() {
                while (this.d != 0) {
                    try {
                        f fVarPoll = this.a.poll(a.this.e, TimeUnit.NANOSECONDS);
                        if (fVarPoll != null) {
                            return fVarPoll;
                        }
                        if (this.a.isEmpty()) {
                            ReentrantLock reentrantLock = a.this.c;
                            reentrantLock.lock();
                            try {
                                if (this.a.isEmpty()) {
                                    a.this.b.remove(Integer.valueOf(this.d));
                                    this.d = 0;
                                    return null;
                                }
                            } finally {
                                reentrantLock.unlock();
                            }
                        } else {
                            continue;
                        }
                    } catch (InterruptedException unused) {
                    }
                }
                return null;
            }

            @Override // java.lang.Runnable
            public final void run() {
                boolean zA = true;
                while (zA) {
                    try {
                        try {
                            f fVarB = this.b;
                            this.b = null;
                            while (true) {
                                if (fVarB == null) {
                                    fVarB = b();
                                    if (fVarB == null && (fVarB = a.this.b()) == null) {
                                        zA = a.this.a(this);
                                        if (!zA) {
                                        }
                                    }
                                }
                                this.c = null;
                                if (this.d == 0) {
                                    this.d = fVarB.C;
                                }
                                f fVar = fVarB;
                                boolean z = true;
                                while (z) {
                                    try {
                                        try {
                                            fVar.b_();
                                            fVar.n();
                                            if (!fVar.v) {
                                                fVar.d_();
                                            }
                                            boolean z2 = fVar.m;
                                            boolean z3 = fVar.p;
                                            long j = fVar.w;
                                        } catch (Exception e) {
                                            com.igexin.b.a.c.a.a(g.h + e.toString(), new Object[0]);
                                            fVar.v = true;
                                            fVar.E = e;
                                            fVar.o();
                                            fVar.k();
                                            g.this.a((Object) fVar);
                                            g.this.e();
                                            if (!fVar.v) {
                                                fVar.d_();
                                            }
                                            boolean z4 = fVar.m;
                                            boolean z5 = fVar.p;
                                            long j2 = fVar.w;
                                            if (fVar.m || !fVar.p || fVar.w == 0) {
                                            }
                                        }
                                    } catch (Throwable th) {
                                        if (!fVar.v) {
                                            fVar.d_();
                                        }
                                        boolean z6 = fVar.m;
                                        boolean z7 = fVar.p;
                                        long j3 = fVar.w;
                                        if (fVar.m || !fVar.p || fVar.w == 0) {
                                            throw th;
                                        }
                                    }
                                    if (fVar.m || !fVar.p || fVar.w == 0) {
                                        fVar = null;
                                        z = false;
                                    }
                                }
                                this.c = fVarB;
                                fVarB = null;
                            }
                            throw th;
                        } catch (Exception e2) {
                            com.igexin.b.a.c.a.a("TaskService|Worker|run()|error" + e2.toString(), new Object[0]);
                            zA = a.this.a(this);
                            if (!zA) {
                                a();
                            }
                        }
                    } catch (Throwable th2) {
                        if (!a.this.a(this)) {
                            a();
                        }
                        throw th2;
                    }
                }
            }
        }

        final class b implements ThreadFactory {
            final AtomicInteger a = new AtomicInteger(0);

            public b() {
            }

            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return new Thread(runnable, "TS-pool-" + this.a.incrementAndGet());
            }
        }

        public a() {
        }

        private void c(f fVar) {
            if (fVar == null) {
                throw new NullPointerException();
            }
            if (fVar.C != 0) {
                ReentrantLock reentrantLock = this.c;
                reentrantLock.lock();
                try {
                    RunnableC0056a runnableC0056a = this.b.get(Integer.valueOf(fVar.C));
                    if (runnableC0056a != null) {
                        runnableC0056a.a.offer(fVar);
                        return;
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }
            if (this.g >= this.f || !a(fVar)) {
                if (!this.a.offer(fVar)) {
                    b(fVar);
                } else if (this.g == 0) {
                    a();
                }
            }
        }

        private void d(f fVar) {
            if (this.g >= this.f || !a(fVar)) {
                if (!this.a.offer(fVar)) {
                    if (!b(fVar)) {
                    }
                } else if (this.g == 0) {
                    a();
                }
            }
        }

        private Thread e(f fVar) {
            RunnableC0056a runnableC0056a = new RunnableC0056a(fVar);
            if (fVar != null && fVar.C != 0) {
                this.b.put(Integer.valueOf(fVar.C), runnableC0056a);
            }
            Thread threadNewThread = this.d.newThread(runnableC0056a);
            if (threadNewThread != null) {
                this.g++;
            }
            return threadNewThread;
        }

        final void a() {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            try {
                Thread threadE = null;
                if (this.g < Math.max(this.f, 1) && !this.a.isEmpty()) {
                    threadE = e(null);
                }
                if (threadE != null) {
                    threadE.start();
                }
            } finally {
                reentrantLock.unlock();
            }
        }

        final boolean a(f fVar) {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            try {
                Thread threadE = this.g < this.f ? e(fVar) : null;
                if (threadE == null) {
                    return false;
                }
                threadE.start();
                return true;
            } finally {
                reentrantLock.unlock();
            }
        }

        final boolean a(RunnableC0056a runnableC0056a) {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            try {
                int i = this.g - 1;
                this.g = i;
                if (i == 0 && !this.a.isEmpty()) {
                    Thread threadE = e(null);
                    if (threadE != null) {
                        threadE.start();
                    }
                } else if (!runnableC0056a.a.isEmpty()) {
                    return true;
                }
                this.b.remove(Integer.valueOf(runnableC0056a.d));
                reentrantLock.unlock();
                return false;
            } finally {
                reentrantLock.unlock();
            }
        }

        final f b() {
            f fVarPoll;
            while (true) {
                try {
                    fVarPoll = this.g > this.f ? this.a.poll(this.e, TimeUnit.NANOSECONDS) : this.a.take();
                } catch (InterruptedException unused) {
                }
                if (fVarPoll != null) {
                    return fVarPoll;
                }
                if (this.a.isEmpty()) {
                    return null;
                }
            }
        }

        final boolean b(f fVar) {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            try {
                Thread threadE = this.g < this.h ? e(fVar) : null;
                if (threadE == null) {
                    return false;
                }
                threadE.start();
                return true;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public final class b extends Thread {
        volatile boolean a = true;
        long b;
        long c;
        a d;

        public b() {
            setName("TS-processor");
        }

        private static void a() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:29:0x0073, code lost:
        
            if (r3.g >= r3.f) goto L104;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0079, code lost:
        
            if (r3.a(r4) != false) goto L110;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0081, code lost:
        
            if (r3.a.offer(r4) == false) goto L106;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0085, code lost:
        
            if (r3.g != 0) goto L112;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0087, code lost:
        
            r3.a();
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x008c, code lost:
        
            r3.b(r4);
         */
        /* JADX WARN: Removed duplicated region for block: B:117:0x013c A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:70:0x012d  */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 394
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.igexin.b.a.d.g.b.run():void");
        }
    }

    protected g() {
        f.H = this;
    }

    private static int a(f fVar, f fVar2) {
        if (fVar.w < fVar2.w) {
            return -1;
        }
        if (fVar.w > fVar2.w) {
            return 1;
        }
        if (fVar.D > fVar2.D) {
            return -1;
        }
        if (fVar.D < fVar2.D) {
            return 1;
        }
        if (fVar.x < fVar2.x) {
            return -1;
        }
        if (fVar.x > fVar2.x) {
            return 1;
        }
        return fVar.hashCode() - fVar2.hashCode();
    }

    private void a() {
        try {
            if (this.x != null) {
                this.v.cancel(this.x);
            }
        } catch (Throwable unused) {
        }
    }

    private void a(int i2, TimeUnit timeUnit) {
        this.p.b = TimeUnit.MILLISECONDS.convert(i2, timeUnit);
    }

    private void a(Context context) {
        if (this.H) {
            return;
        }
        if (!n.l()) {
            this.u = (PowerManager) context.getSystemService("power");
            this.C = true;
            this.v = (AlarmManager) context.getSystemService("alarm");
            context.registerReceiver(this, new IntentFilter(E + context.getPackageName()));
            context.registerReceiver(this, new IntentFilter(F + context.getPackageName()));
            context.registerReceiver(this, new IntentFilter("android.intent.action.SCREEN_OFF"));
            context.registerReceiver(this, new IntentFilter("android.intent.action.SCREEN_ON"));
            this.A = G + context.getPackageName();
            context.registerReceiver(this, new IntentFilter(this.A));
            this.w = new Intent(E + context.getPackageName());
            this.x = PendingIntent.getBroadcast(context, hashCode(), this.w, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
            hashCode();
            this.y = new Intent(this.A);
            this.z = PendingIntent.getBroadcast(context, hashCode() + 2, this.y, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
            hashCode();
        }
        this.p.start();
        try {
            Thread.yield();
        } catch (Throwable unused) {
        }
        this.H = true;
    }

    private static boolean a(com.igexin.b.a.d.a.e eVar, com.igexin.b.a.d.a.c cVar) {
        int iC = eVar.c();
        if (iC <= Integer.MIN_VALUE || iC >= 0) {
            if (iC < 0 || iC >= Integer.MAX_VALUE) {
                return false;
            }
            return cVar.a(eVar);
        }
        f fVar = (f) eVar;
        boolean zA = fVar.v ? false : cVar.a(eVar);
        if (zA) {
            fVar.d_();
        }
        return zA;
    }

    private boolean a(f fVar) {
        e<f> eVar = this.s;
        return eVar != null && eVar.c(fVar);
    }

    private boolean a(f fVar, boolean z, int i2, long j2, byte b2, Object obj, com.igexin.b.a.d.a.d dVar, int i3, com.igexin.b.a.d.a.g gVar) {
        if (fVar == null) {
            throw new NullPointerException();
        }
        fVar.A = i2;
        fVar.a((int) b2);
        fVar.F = obj;
        fVar.O = dVar;
        fVar.a(j2, TimeUnit.MILLISECONDS);
        fVar.a(i3, gVar);
        return a(fVar, z);
    }

    private boolean a(Class cls) {
        e<f> eVar = this.s;
        return eVar != null && eVar.a(cls);
    }

    private boolean b() {
        e<f> eVar = this.s;
        if (eVar == null) {
            return false;
        }
        eVar.c.clear();
        return true;
    }

    @TargetApi(19)
    public final void a(long j2) {
        if (this.C) {
            com.igexin.b.a.c.a.a("setalarm|" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j2)), new Object[0]);
            if (j2 < 0) {
                j2 = System.currentTimeMillis() + D;
            }
            try {
                if (this.x != null) {
                    if (Build.VERSION.SDK_INT < 19) {
                        this.v.set(0, j2, this.x);
                        return;
                    }
                    try {
                        this.v.setExact(0, j2, this.x);
                    } catch (Throwable unused) {
                        this.v.set(0, j2, this.x);
                    }
                }
            } catch (Throwable th) {
                com.igexin.b.a.c.a.a(h + th.toString(), new Object[0]);
            }
        }
    }

    public final boolean a(com.igexin.b.a.d.a.c cVar) {
        ReentrantLock reentrantLock = this.t;
        if (reentrantLock.tryLock()) {
            try {
                if (this.q.containsKey(Long.valueOf(cVar.h()))) {
                    return false;
                }
                this.q.put(Long.valueOf(cVar.h()), cVar);
                reentrantLock.unlock();
                return true;
            } catch (Throwable th) {
                com.igexin.b.a.c.a.a("TaskService|" + th.toString(), new Object[0]);
                return false;
            } finally {
                reentrantLock.unlock();
            }
            reentrantLock.unlock();
        }
        return false;
    }

    public final boolean a(f fVar, boolean z) {
        if (fVar == null) {
            throw new NullPointerException();
        }
        int iIncrementAndGet = 0;
        if (fVar.q || fVar.m) {
            return false;
        }
        fVar.getClass().getName();
        e<f> eVar = this.s;
        if ((fVar instanceof com.igexin.b.a.b.f) && (((com.igexin.b.a.b.f) fVar).d instanceof o)) {
            if (z) {
                iIncrementAndGet = Integer.MAX_VALUE;
            }
        } else if (z) {
            iIncrementAndGet = eVar.d.incrementAndGet();
        }
        fVar.D = iIncrementAndGet;
        return eVar.a(fVar);
    }

    public final boolean a(f fVar, boolean z, boolean z2) {
        if (fVar == null) {
            throw new NullPointerException();
        }
        if (fVar.n) {
            return false;
        }
        if (!z || z2) {
            return a(fVar, z2 && z);
        }
        fVar.d();
        try {
            try {
                fVar.b_();
                fVar.n();
                if (!fVar.v) {
                    fVar.d_();
                }
                return true;
            } catch (Exception e) {
                fVar.v = true;
                fVar.E = e;
                fVar.k();
                fVar.o();
                a((Object) fVar);
                e();
                if (!fVar.v) {
                    fVar.d_();
                }
                return false;
            }
        } catch (Throwable th) {
            if (!fVar.v) {
                fVar.d_();
            }
            throw th;
        }
    }

    public final boolean a(Object obj) {
        if (obj == null) {
            return false;
        }
        obj.getClass().getName();
        obj.hashCode();
        try {
            if (obj instanceof com.igexin.push.c.c.n) {
                obj.getClass().getName();
                obj.hashCode();
            }
        } catch (Exception unused) {
        }
        obj.getClass().getName();
        obj.hashCode();
        com.igexin.b.a.c.a.a("TaskService|responseQueue ++ task = " + obj.getClass().getName() + "@" + obj.hashCode(), new Object[0]);
        if (!(obj instanceof com.igexin.b.a.d.a.e)) {
            throw new ClassCastException("response Obj is not a TaskResult ");
        }
        com.igexin.b.a.d.a.e eVar = (com.igexin.b.a.d.a.e) obj;
        if (eVar.i()) {
            return false;
        }
        eVar.a(false);
        if ((obj instanceof com.igexin.push.c.b.a) || (obj instanceof com.igexin.push.c.b.b)) {
            this.r.a();
            com.igexin.b.a.c.a.a("TaskService|change to primaryQueue", new Object[0]);
        }
        this.r.a(eVar);
        return true;
    }

    @TargetApi(19)
    public final void b(long j2) {
        if (n.l()) {
            return;
        }
        com.igexin.b.a.c.a.a("setnioalarm|" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j2)), new Object[0]);
        if (j2 < 0) {
            j2 = System.currentTimeMillis() + D;
        }
        try {
            if (Build.VERSION.SDK_INT < 19) {
                this.v.set(0, j2, this.z);
                return;
            }
            try {
                this.v.setExact(0, j2, this.z);
            } catch (Exception unused) {
                this.v.set(0, j2, this.z);
            }
        } catch (Throwable unused2) {
        }
    }

    @Override // java.util.Comparator
    public /* synthetic */ int compare(f fVar, f fVar2) {
        f fVar3 = fVar;
        f fVar4 = fVar2;
        if (fVar3.w < fVar4.w) {
            return -1;
        }
        if (fVar3.w > fVar4.w) {
            return 1;
        }
        if (fVar3.D > fVar4.D) {
            return -1;
        }
        if (fVar3.D < fVar4.D) {
            return 1;
        }
        if (fVar3.x < fVar4.x) {
            return -1;
        }
        if (fVar3.x > fVar4.x) {
            return 1;
        }
        return fVar3.hashCode() - fVar4.hashCode();
    }

    public final void d() {
        try {
            if (this.z != null) {
                this.v.cancel(this.z);
            }
        } catch (Throwable unused) {
        }
    }

    protected final void e() {
        b bVar = this.p;
        if (bVar == null || bVar.isInterrupted()) {
            return;
        }
        this.p.interrupt();
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00a5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0000 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final void f() throws java.lang.Throwable {
        /*
            r8 = this;
        L0:
            com.igexin.b.a.d.d r0 = r8.r
            boolean r0 = r0.c()
            if (r0 != 0) goto Lc7
            com.igexin.b.a.d.d r0 = r8.r
            com.igexin.b.a.d.a.e r0 = r0.d()
            if (r0 != 0) goto L11
            return
        L11:
            r1 = 1
            r0.a(r1)
            java.util.concurrent.locks.ReentrantLock r1 = r8.t
            r1.lock()
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 0
            java.util.HashMap<java.lang.Long, com.igexin.b.a.d.a.c> r4 = r8.q     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            boolean r4 = r4.isEmpty()     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            if (r4 != 0) goto L65
            long r4 = r0.j()     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 == 0) goto L45
            java.util.HashMap<java.lang.Long, com.igexin.b.a.d.a.c> r6 = r8.q     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            java.lang.Object r4 = r6.get(r4)     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            com.igexin.b.a.d.a.c r4 = (com.igexin.b.a.d.a.c) r4     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            if (r4 == 0) goto L42
            boolean r4 = a(r0, r4)     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            goto L43
        L42:
            r4 = 0
        L43:
            r5 = r4
            goto L66
        L45:
            java.util.HashMap<java.lang.Long, com.igexin.b.a.d.a.c> r4 = r8.q     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            java.util.Collection r4 = r4.values()     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            java.util.Iterator r4 = r4.iterator()     // Catch: java.lang.Throwable -> L7a java.lang.Throwable -> L7d
            r5 = 0
        L50:
            boolean r6 = r4.hasNext()     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> Lb3
            if (r6 == 0) goto L66
            java.lang.Object r6 = r4.next()     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> Lb3
            com.igexin.b.a.d.a.c r6 = (com.igexin.b.a.d.a.c) r6     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> Lb3
            boolean r5 = a(r0, r6)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> Lb3
            if (r5 == 0) goto L50
            goto L66
        L63:
            r4 = move-exception
            goto L7f
        L65:
            r5 = 0
        L66:
            if (r5 != 0) goto L76
            int r4 = r0.c()
            if (r4 <= r2) goto L76
            if (r4 >= 0) goto L76
        L70:
            r2 = r0
            com.igexin.b.a.d.f r2 = (com.igexin.b.a.d.f) r2
            r2.d_()
        L76:
            r1.unlock()
            goto La1
        L7a:
            r4 = move-exception
            r5 = 0
            goto Lb4
        L7d:
            r4 = move-exception
            r5 = 0
        L7f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r7 = "TaskService|"
            r6.<init>(r7)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> Lb3
            r6.append(r4)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r4 = r6.toString()     // Catch: java.lang.Throwable -> Lb3
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lb3
            com.igexin.b.a.c.a.a(r4, r6)     // Catch: java.lang.Throwable -> Lb3
            if (r5 != 0) goto L76
            int r4 = r0.c()
            if (r4 <= r2) goto L76
            if (r4 >= 0) goto L76
            goto L70
        La1:
            boolean r0 = r0 instanceof com.igexin.push.c.c.k
            if (r0 == 0) goto L0
            com.igexin.b.a.d.d r0 = r8.r
            r0.b()
            java.lang.String r0 = "TaskService|queue -> secondRespQueue"
            java.lang.Object[] r1 = new java.lang.Object[r3]
            com.igexin.b.a.c.a.a(r0, r1)
            goto L0
        Lb3:
            r4 = move-exception
        Lb4:
            if (r5 != 0) goto Lc3
            int r3 = r0.c()
            if (r3 <= r2) goto Lc3
            if (r3 >= 0) goto Lc3
            com.igexin.b.a.d.f r0 = (com.igexin.b.a.d.f) r0
            r0.d_()
        Lc3:
            r1.unlock()
            throw r4
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.b.a.d.g.f():void");
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            this.C = true;
            com.igexin.b.a.c.a.a("screenoff", new Object[0]);
            if (this.s.g.get() > 0) {
                a(this.s.g.get());
                return;
            }
            return;
        }
        if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
            this.C = false;
            com.igexin.b.a.c.a.a("screenon", new Object[0]);
            return;
        }
        if (intent.getAction().startsWith(E) || intent.getAction().startsWith(F)) {
            Calendar.getInstance().getTime().toLocaleString();
            com.igexin.b.a.c.a.a("receivealarm|" + this.C, new Object[0]);
            e();
            return;
        }
        if (this.A.equals(intent.getAction())) {
            Calendar calendar = Calendar.getInstance();
            Log.i(i, "CPU ON + NioAlarmReceiver:-> cTime; " + calendar.getTime().toLocaleString());
            com.igexin.b.a.c.a.a("receive nioalarm", new Object[0]);
            try {
                com.igexin.b.a.c.a.a("TaskService|alarm time out #######", new Object[0]);
                com.igexin.b.a.b.a.a.d.a().e();
            } catch (Exception unused) {
            }
        }
    }
}
