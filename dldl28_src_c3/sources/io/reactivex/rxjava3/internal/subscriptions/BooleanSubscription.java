package io.reactivex.rxjava3.internal.subscriptions;

import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class BooleanSubscription extends AtomicBoolean implements Subscription {
    private static final long serialVersionUID = -8127758972444290902L;

    public void request(long n) {
        SubscriptionHelper.validate(n);
    }

    public void cancel() {
        lazySet(true);
    }

    public boolean isCancelled() {
        return get();
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    public String toString() {
        return "BooleanSubscription(cancelled=" + get() + ")";
    }
}
