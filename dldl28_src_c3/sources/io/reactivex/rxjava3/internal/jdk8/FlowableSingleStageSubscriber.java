package io.reactivex.rxjava3.internal.jdk8;

import java.util.NoSuchElementException;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class FlowableSingleStageSubscriber<T> extends FlowableStageSubscriber<T> {
    final T defaultItem;
    final boolean hasDefault;

    public FlowableSingleStageSubscriber(boolean hasDefault, T defaultItem) {
        this.hasDefault = hasDefault;
        this.defaultItem = defaultItem;
    }

    public void onNext(T t) {
        if (this.value != null) {
            this.value = null;
            completeExceptionally(new IllegalArgumentException("Sequence contains more than one element!"));
        } else {
            this.value = t;
        }
    }

    public void onComplete() {
        if (isDone()) {
            return;
        }
        T t = this.value;
        clear();
        if (t != null) {
            complete(t);
        } else if (this.hasDefault) {
            complete(this.defaultItem);
        } else {
            completeExceptionally(new NoSuchElementException());
        }
    }

    @Override // io.reactivex.rxjava3.internal.jdk8.FlowableStageSubscriber
    protected void afterSubscribe(Subscription s) {
        s.request(2L);
    }
}
