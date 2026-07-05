package com.duowan.ark.thread.pool;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ScheduledExecutor extends Executor {
    void execute(Runnable runnable, long j);
}
