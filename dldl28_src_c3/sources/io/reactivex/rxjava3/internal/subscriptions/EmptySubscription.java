package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.internal.fuseable.QueueSubscription;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public enum EmptySubscription implements QueueSubscription<Object> {
    INSTANCE;

    public void cancel() {
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
    public void clear() {
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
    public boolean isEmpty() {
        return true;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
    public Object poll() {
        return null;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
    public int requestFusion(int mode) {
        return mode & 2;
    }

    public void request(long n) {
        SubscriptionHelper.validate(n);
    }

    @Override // java.lang.Enum
    public String toString() {
        return "EmptySubscription";
    }

    public static void error(Throwable e, Subscriber<?> s) {
        s.onSubscribe(INSTANCE);
        s.onError(e);
    }

    public static void complete(Subscriber<?> s) {
        s.onSubscribe(INSTANCE);
        s.onComplete();
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
    public boolean offer(Object value) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
    public boolean offer(Object v1, Object v2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
