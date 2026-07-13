package org.apache.commons.lang3.function;

import java.lang.Throwable;

/* JADX INFO: loaded from: classes4.dex */
@FunctionalInterface
public interface FailableRunnable<E extends Throwable> {
    void run() throws Throwable;
}
