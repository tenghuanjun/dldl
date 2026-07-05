package com.rxjava.rxlife;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.parallel.ParallelFlowable;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ParallelFlowableLife<T> {
    private boolean onMain;
    private Scope scope;
    private ParallelFlowable<T> upStream;

    ParallelFlowableLife(ParallelFlowable<T> parallelFlowable, Scope scope, boolean z) {
        this.upStream = parallelFlowable;
        this.scope = scope;
        this.onMain = z;
    }

    public void subscribe(Subscriber<? super T>[] subscriberArr) {
        if (validate(subscriberArr)) {
            int length = subscriberArr.length;
            Subscriber<? super T>[] subscriberArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                Subscriber<? super T> subscriber = subscriberArr[i];
                if (subscriber instanceof ConditionalSubscriber) {
                    subscriberArr2[i] = new LifeConditionalSubscriber((ConditionalSubscriber) subscriber, this.scope);
                } else {
                    subscriberArr2[i] = new LifeSubscriber(subscriber, this.scope);
                }
            }
            ParallelFlowable<T> parallelFlowableRunOn = this.upStream;
            if (this.onMain) {
                parallelFlowableRunOn = parallelFlowableRunOn.runOn(AndroidSchedulers.mainThread());
            }
            parallelFlowableRunOn.subscribe(subscriberArr2);
        }
    }

    private int parallelism() {
        return this.upStream.parallelism();
    }

    private boolean validate(Subscriber<?>[] subscriberArr) {
        int iParallelism = parallelism();
        if (subscriberArr.length == iParallelism) {
            return true;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("parallelism = " + iParallelism + ", subscribers = " + subscriberArr.length);
        for (Subscriber<?> subscriber : subscriberArr) {
            EmptySubscription.error(illegalArgumentException, subscriber);
        }
        return false;
    }
}
