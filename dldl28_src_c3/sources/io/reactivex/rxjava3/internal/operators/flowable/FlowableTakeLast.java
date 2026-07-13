package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class FlowableTakeLast<T> extends AbstractFlowableWithUpstream<T, T> {
    final int count;

    public FlowableTakeLast(Flowable<T> source, int count) {
        super(source);
        this.count = count;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe((FlowableSubscriber) new TakeLastSubscriber(s, this.count));
    }

    static final class TakeLastSubscriber<T> extends ArrayDeque<T> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 7240042530241604978L;
        volatile boolean cancelled;
        final int count;
        volatile boolean done;
        final Subscriber<? super T> downstream;
        Subscription upstream;
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger wip = new AtomicInteger();

        TakeLastSubscriber(Subscriber<? super T> actual, int count) {
            this.downstream = actual;
            this.count = count;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (this.count == size()) {
                poll();
            }
            offer(t);
        }

        public void onError(Throwable t) {
            this.downstream.onError(t);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                Subscriber<? super T> subscriber = this.downstream;
                long jProduced = this.requested.get();
                while (!this.cancelled) {
                    if (this.done) {
                        long j = 0;
                        while (j != jProduced) {
                            if (this.cancelled) {
                                return;
                            }
                            T tPoll = poll();
                            if (tPoll == null) {
                                subscriber.onComplete();
                                return;
                            } else {
                                subscriber.onNext(tPoll);
                                j++;
                            }
                        }
                        if (isEmpty()) {
                            subscriber.onComplete();
                            return;
                        } else if (j != 0) {
                            jProduced = BackpressureHelper.produced(this.requested, j);
                        }
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
}
