package com.nirvana.tools.requestqueue;

import com.nirvana.tools.core.ExecutorManager;
import com.nirvana.tools.requestqueue.Response;
import com.nirvana.tools.requestqueue.strategy.CallbackStrategy;
import com.nirvana.tools.requestqueue.strategy.ThreadStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RunnableScheduledFuture;

/* JADX INFO: loaded from: classes3.dex */
final class RequestHandler<T extends Response> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    List<Callback<T>> f844a = new ArrayList();
    RequestHandler<T>.a b;
    Request<T> c;
    private DoneAction d;

    /* JADX INFO: renamed from: com.nirvana.tools.requestqueue.RequestHandler$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f848a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[CallbackStrategy.values().length];
            b = iArr;
            try {
                iArr[CallbackStrategy.LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[CallbackStrategy.COVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[ThreadStrategy.values().length];
            f848a = iArr2;
            try {
                iArr2[ThreadStrategy.THREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f848a[ThreadStrategy.THREAD_MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f848a[ThreadStrategy.SAME_WITH_CALLABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public interface DoneAction {
        void run(RequestHandler requestHandler);
    }

    class a implements Runnable {
        private Runnable d;
        private volatile boolean c = false;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        RunnableScheduledFuture<?> f849a = null;

        public a(Runnable runnable) {
            this.d = runnable;
        }

        public final synchronized void a() {
            if (this.d != null) {
                ExecutorManager.getInstance().removeFromMain(this.d);
            }
            if (this.f849a != null) {
                ExecutorManager.getInstance().removeFromThread(this.f849a);
            }
            this.c = true;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.c) {
                return;
            }
            try {
                T tCall = RequestHandler.this.c.getAction().call();
                if (this.c) {
                    return;
                }
                RequestHandler.this.a(tCall);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public RequestHandler(Request<T> request, DoneAction doneAction) {
        this.c = request;
        this.d = doneAction;
    }

    final void a() {
        if (this.b == null) {
            Runnable runnable = new Runnable() { // from class: com.nirvana.tools.requestqueue.RequestHandler.3
                @Override // java.lang.Runnable
                public final void run() {
                    RequestHandler.this.a(RequestHandler.this.c.getAction().onTimeout());
                }
            };
            this.b = new a(runnable);
            int i = AnonymousClass4.f848a[this.c.getThreadStrategy().ordinal()];
            if (i == 1) {
                ExecutorManager.getInstance().scheduleFuture(this.b);
                this.b.f849a = ExecutorManager.getInstance().scheduleFutureDelay(runnable, this.c.getTimeout());
            } else {
                if (i != 2) {
                    throw new IllegalArgumentException("Request Callable ThreadStrategy Illegal");
                }
                ExecutorManager.getInstance().postMain(this.b);
                ExecutorManager.getInstance().postMain(runnable, this.c.getTimeout());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0042 A[Catch: all -> 0x004d, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:11:0x003a, B:13:0x0042, B:8:0x0024, B:9:0x002f, B:10:0x0033), top: B:19:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final synchronized void a(com.nirvana.tools.requestqueue.Request<T> r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.nirvana.tools.requestqueue.Callback r0 = r6.getCallback()     // Catch: java.lang.Throwable -> L4d
            long r1 = r6.getTimeout()     // Catch: java.lang.Throwable -> L4d
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L4d
            long r1 = r1 + r3
            r0.setExpiredTime(r1)     // Catch: java.lang.Throwable -> L4d
            int[] r0 = com.nirvana.tools.requestqueue.RequestHandler.AnonymousClass4.b     // Catch: java.lang.Throwable -> L4d
            com.nirvana.tools.requestqueue.strategy.CallbackStrategy r1 = r6.getCallbackStrategy()     // Catch: java.lang.Throwable -> L4d
            int r1 = r1.ordinal()     // Catch: java.lang.Throwable -> L4d
            r0 = r0[r1]     // Catch: java.lang.Throwable -> L4d
            r1 = 1
            if (r0 == r1) goto L33
            r1 = 2
            if (r0 == r1) goto L24
            goto L3a
        L24:
            java.util.List<com.nirvana.tools.requestqueue.Callback<T extends com.nirvana.tools.requestqueue.Response>> r0 = r5.f844a     // Catch: java.lang.Throwable -> L4d
            r0.clear()     // Catch: java.lang.Throwable -> L4d
            java.util.List<com.nirvana.tools.requestqueue.Callback<T extends com.nirvana.tools.requestqueue.Response>> r0 = r5.f844a     // Catch: java.lang.Throwable -> L4d
            com.nirvana.tools.requestqueue.Callback r1 = r6.getCallback()     // Catch: java.lang.Throwable -> L4d
        L2f:
            r0.add(r1)     // Catch: java.lang.Throwable -> L4d
            goto L3a
        L33:
            java.util.List<com.nirvana.tools.requestqueue.Callback<T extends com.nirvana.tools.requestqueue.Response>> r0 = r5.f844a     // Catch: java.lang.Throwable -> L4d
            com.nirvana.tools.requestqueue.Callback r1 = r6.getCallback()     // Catch: java.lang.Throwable -> L4d
            goto L2f
        L3a:
            java.util.List<com.nirvana.tools.requestqueue.Callback<T extends com.nirvana.tools.requestqueue.Response>> r0 = r5.f844a     // Catch: java.lang.Throwable -> L4d
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L4d
            if (r0 == 0) goto L4b
            java.util.List<com.nirvana.tools.requestqueue.Callback<T extends com.nirvana.tools.requestqueue.Response>> r0 = r5.f844a     // Catch: java.lang.Throwable -> L4d
            com.nirvana.tools.requestqueue.Callback r6 = r6.getCallback()     // Catch: java.lang.Throwable -> L4d
            r0.add(r6)     // Catch: java.lang.Throwable -> L4d
        L4b:
            monitor-exit(r5)
            return
        L4d:
            r6 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L4d
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nirvana.tools.requestqueue.RequestHandler.a(com.nirvana.tools.requestqueue.Request):void");
    }

    final synchronized void a(final T t) {
        if (this.f844a.size() > 0) {
            ArrayList arrayList = new ArrayList(this.f844a.size());
            Iterator<Callback<T>> it = this.f844a.iterator();
            long j = 0;
            while (it.hasNext()) {
                final Callback<T> next = it.next();
                if (t.isTimeout()) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - next.getExpiredTime();
                    if (jCurrentTimeMillis > next.getThreshold()) {
                        if (j > jCurrentTimeMillis) {
                            j = jCurrentTimeMillis;
                        }
                    }
                }
                int i = AnonymousClass4.f848a[next.getThreadStrategy().ordinal()];
                if (i == 1) {
                    ExecutorManager.getInstance().scheduleFuture(new Runnable() { // from class: com.nirvana.tools.requestqueue.RequestHandler.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public final void run() {
                            next.onResult(t);
                        }
                    });
                } else if (i == 2) {
                    ExecutorManager.getInstance().postMain(new Runnable() { // from class: com.nirvana.tools.requestqueue.RequestHandler.2
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public final void run() {
                            next.onResult(t);
                        }
                    });
                } else if (i == 3) {
                    arrayList.add(next);
                }
                it.remove();
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((Callback) it2.next()).onResult(t);
            }
            arrayList.clear();
            if (this.f844a.isEmpty()) {
                DoneAction doneAction = this.d;
                if (doneAction != null) {
                    doneAction.run(this);
                }
            } else {
                Request<T> request = this.c;
                if (request != null) {
                    request.setTimeout(j);
                }
                a();
            }
        }
    }
}
