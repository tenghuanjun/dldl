package com.duowan.ark.thread.pool;

import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ScheduledExecutorAdapter implements ScheduledExecutor {
    private static WorkThread mWork = new HandlerWorkFactory().newThread("ScheduledExecutorAdapter", 0);
    private ExecutorService mExecutor;

    public ScheduledExecutorAdapter(ExecutorService executorService) {
        if (executorService == null) {
            throw new NullPointerException("ScheduledThreadPoolExecutor may not be null");
        }
        this.mExecutor = executorService;
    }

    @Override // com.duowan.ark.thread.pool.ScheduledExecutor
    public void execute(final Runnable runnable, long j) {
        mWork.post(new Runnable() { // from class: com.duowan.ark.thread.pool.ScheduledExecutorAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                ScheduledExecutorAdapter.this.execute(runnable);
            }
        }, j);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.mExecutor.execute(runnable);
    }
}
