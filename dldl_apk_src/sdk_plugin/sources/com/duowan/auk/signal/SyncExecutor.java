package com.duowan.auk.signal;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SyncExecutor implements Executor {
    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
