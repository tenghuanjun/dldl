package io.reactivex.rxjava3.internal.operators.flowable;

import androidx.compose.animation.core.ComplexDouble$;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class FlowableFlatMapMaybe<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final boolean delayErrors;
    final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
    final int maxConcurrency;

    public FlowableFlatMapMaybe(Flowable<T> source, Function<? super T, ? extends MaybeSource<? extends R>> mapper, boolean delayError, int maxConcurrency) {
        super(source);
        this.mapper = mapper;
        this.delayErrors = delayError;
        this.maxConcurrency = maxConcurrency;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super R> s) {
        this.source.subscribe((FlowableSubscriber) new FlatMapMaybeSubscriber(s, this.mapper, this.delayErrors, this.maxConcurrency));
    }

    static final class FlatMapMaybeSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 8600231336733376951L;
        volatile boolean cancelled;
        final boolean delayErrors;
        final Subscriber<? super R> downstream;
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        final int maxConcurrency;
        Subscription upstream;
        final AtomicLong requested = new AtomicLong();
        final CompositeDisposable set = new CompositeDisposable();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicInteger active = new AtomicInteger(1);
        final AtomicReference<SpscLinkedArrayQueue<R>> queue = new AtomicReference<>();

        FlatMapMaybeSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends MaybeSource<? extends R>> mapper, boolean delayErrors, int maxConcurrency) {
            this.downstream = actual;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
            this.maxConcurrency = maxConcurrency;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                int i = this.maxConcurrency;
                if (i == Integer.MAX_VALUE) {
                    s.request(Long.MAX_VALUE);
                } else {
                    s.request(i);
                }
            }
        }

        public void onNext(T t) {
            try {
                MaybeSource maybeSource = (MaybeSource) Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                this.active.getAndIncrement();
                InnerObserver innerObserver = new InnerObserver();
                if (this.cancelled || !this.set.add(innerObserver)) {
                    return;
                }
                maybeSource.subscribe(innerObserver);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.cancel();
                onError(th);
            }
        }

        public void onError(Throwable t) {
            this.active.decrementAndGet();
            if (this.errors.tryAddThrowableOrReport(t)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
            }
        }

        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            this.set.dispose();
            this.errors.tryTerminateAndReport();
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x006a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void innerSuccess(io.reactivex.rxjava3.internal.operators.flowable.FlowableFlatMapMaybe.FlatMapMaybeSubscriber<T, R>.InnerObserver r6, R r7) {
            /*
                r5 = this;
                io.reactivex.rxjava3.disposables.CompositeDisposable r0 = r5.set
                r0.delete(r6)
                int r6 = r5.get()
                if (r6 != 0) goto L6a
                r6 = 0
                r0 = 1
                boolean r1 = r5.compareAndSet(r6, r0)
                if (r1 == 0) goto L6a
                java.util.concurrent.atomic.AtomicInteger r1 = r5.active
                int r1 = r1.decrementAndGet()
                if (r1 != 0) goto L1c
                r6 = 1
            L1c:
                java.util.concurrent.atomic.AtomicLong r0 = r5.requested
                long r0 = r0.get()
                r2 = 0
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 == 0) goto L57
                org.reactivestreams.Subscriber<? super R> r0 = r5.downstream
                r0.onNext(r7)
                java.util.concurrent.atomic.AtomicReference<io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue<R>> r7 = r5.queue
                java.lang.Object r7 = r7.get()
                io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue r7 = (io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue) r7
                boolean r6 = checkTerminate(r6, r7)
                if (r6 == 0) goto L43
                io.reactivex.rxjava3.internal.util.AtomicThrowable r6 = r5.errors
                org.reactivestreams.Subscriber<? super R> r7 = r5.downstream
                r6.tryTerminateConsumer(r7)
                return
            L43:
                java.util.concurrent.atomic.AtomicLong r6 = r5.requested
                r0 = 1
                io.reactivex.rxjava3.internal.util.BackpressureHelper.produced(r6, r0)
                int r6 = r5.maxConcurrency
                r7 = 2147483647(0x7fffffff, float:NaN)
                if (r6 == r7) goto L60
                org.reactivestreams.Subscription r6 = r5.upstream
                r6.request(r0)
                goto L60
            L57:
                io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue r6 = r5.getOrCreateQueue()
                monitor-enter(r6)
                r6.offer(r7)     // Catch: java.lang.Throwable -> L67
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L67
            L60:
                int r6 = r5.decrementAndGet()
                if (r6 != 0) goto L7f
                return
            L67:
                r7 = move-exception
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L67
                throw r7
            L6a:
                io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue r6 = r5.getOrCreateQueue()
                monitor-enter(r6)
                r6.offer(r7)     // Catch: java.lang.Throwable -> L83
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L83
                java.util.concurrent.atomic.AtomicInteger r6 = r5.active
                r6.decrementAndGet()
                int r6 = r5.getAndIncrement()
                if (r6 == 0) goto L7f
                return
            L7f:
                r5.drainLoop()
                return
            L83:
                r7 = move-exception
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L83
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableFlatMapMaybe.FlatMapMaybeSubscriber.innerSuccess(io.reactivex.rxjava3.internal.operators.flowable.FlowableFlatMapMaybe$FlatMapMaybeSubscriber$InnerObserver, java.lang.Object):void");
        }

        SpscLinkedArrayQueue<R> getOrCreateQueue() {
            SpscLinkedArrayQueue<R> spscLinkedArrayQueue = this.queue.get();
            if (spscLinkedArrayQueue != null) {
                return spscLinkedArrayQueue;
            }
            SpscLinkedArrayQueue<R> spscLinkedArrayQueue2 = new SpscLinkedArrayQueue<>(Flowable.bufferSize());
            return ComplexDouble$.ExternalSyntheticBackport0.m(this.queue, (Object) null, spscLinkedArrayQueue2) ? spscLinkedArrayQueue2 : this.queue.get();
        }

        void innerError(FlatMapMaybeSubscriber<T, R>.InnerObserver inner, Throwable e) {
            this.set.delete(inner);
            if (this.errors.tryAddThrowableOrReport(e)) {
                if (!this.delayErrors) {
                    this.upstream.cancel();
                    this.set.dispose();
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    this.upstream.request(1L);
                }
                this.active.decrementAndGet();
                drain();
            }
        }

        void innerComplete(FlatMapMaybeSubscriber<T, R>.InnerObserver inner) {
            this.set.delete(inner);
            if (get() == 0) {
                if (compareAndSet(0, 1)) {
                    if (checkTerminate(this.active.decrementAndGet() == 0, this.queue.get())) {
                        this.errors.tryTerminateConsumer(this.downstream);
                        return;
                    }
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        this.upstream.request(1L);
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                    drainLoop();
                    return;
                }
            }
            this.active.decrementAndGet();
            if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.upstream.request(1L);
            }
            drain();
        }

        static boolean checkTerminate(boolean d, SpscLinkedArrayQueue<?> q) {
            return d && (q == null || q.isEmpty());
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void clear() {
            SpscLinkedArrayQueue<R> spscLinkedArrayQueue = this.queue.get();
            if (spscLinkedArrayQueue != null) {
                spscLinkedArrayQueue.clear();
            }
        }

        void drainLoop() {
            Subscriber<? super R> subscriber = this.downstream;
            AtomicInteger atomicInteger = this.active;
            AtomicReference<SpscLinkedArrayQueue<R>> atomicReference = this.queue;
            int iAddAndGet = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    if (j2 == j) {
                        break;
                    }
                    if (this.cancelled) {
                        clear();
                        return;
                    }
                    if (!this.delayErrors && this.errors.get() != null) {
                        clear();
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    }
                    boolean z = atomicInteger.get() == 0;
                    SpscLinkedArrayQueue<R> spscLinkedArrayQueue = atomicReference.get();
                    R rPoll = spscLinkedArrayQueue != null ? spscLinkedArrayQueue.poll() : null;
                    boolean z2 = rPoll == null;
                    if (z && z2) {
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    } else {
                        if (z2) {
                            break;
                        }
                        subscriber.onNext(rPoll);
                        j2++;
                    }
                }
                if (j2 == j) {
                    if (this.cancelled) {
                        clear();
                        return;
                    }
                    if (!this.delayErrors && this.errors.get() != null) {
                        clear();
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    }
                    boolean z3 = atomicInteger.get() == 0;
                    SpscLinkedArrayQueue<R> spscLinkedArrayQueue2 = atomicReference.get();
                    boolean z4 = spscLinkedArrayQueue2 == null || spscLinkedArrayQueue2.isEmpty();
                    if (z3 && z4) {
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    }
                }
                if (j2 != 0) {
                    BackpressureHelper.produced(this.requested, j2);
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        this.upstream.request(j2);
                    }
                }
                iAddAndGet = addAndGet(-iAddAndGet);
            } while (iAddAndGet != 0);
        }

        final class InnerObserver extends AtomicReference<Disposable> implements MaybeObserver<R>, Disposable {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this, d);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(R value) {
                FlatMapMaybeSubscriber.this.innerSuccess(this, value);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable e) {
                FlatMapMaybeSubscriber.this.innerError(this, e);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                FlatMapMaybeSubscriber.this.innerComplete(this);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
