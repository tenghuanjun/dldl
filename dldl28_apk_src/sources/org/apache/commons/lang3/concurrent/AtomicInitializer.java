package org.apache.commons.lang3.concurrent;

import androidx.compose.animation.core.ComplexDouble$$ExternalSyntheticBackport0;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AtomicInitializer<T> implements ConcurrentInitializer<T> {
    private final AtomicReference<T> reference = new AtomicReference<>();

    protected abstract T initialize() throws ConcurrentException;

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public T get() throws ConcurrentException {
        T t = this.reference.get();
        if (t != null) {
            return t;
        }
        T tInitialize = initialize();
        return !ComplexDouble$$ExternalSyntheticBackport0.m(this.reference, null, tInitialize) ? this.reference.get() : tInitialize;
    }
}
