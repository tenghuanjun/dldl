package com.volcengine.common.innerapi;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface ExecutorsService {
    void executeCpu(Runnable runnable);

    void executeIO(Runnable runnable);

    void executeMain(Runnable runnable);

    void executeMainDelay(Runnable runnable, long j);

    ExecutorService getCpuExecutor();

    ExecutorService getIOExecutor();

    Future<?> submitCpu(Runnable runnable);

    <T> Future<T> submitCpu(Callable<T> callable);

    Future<?> submitIO(Runnable runnable);

    <T> Future<T> submitIO(Callable<T> callable);
}
