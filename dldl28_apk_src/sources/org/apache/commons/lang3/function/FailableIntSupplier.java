package org.apache.commons.lang3.function;

import java.lang.Throwable;

/* JADX INFO: loaded from: classes4.dex */
@FunctionalInterface
public interface FailableIntSupplier<E extends Throwable> {
    int getAsInt() throws Throwable;
}
