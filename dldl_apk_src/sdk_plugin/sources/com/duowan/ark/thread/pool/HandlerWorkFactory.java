package com.duowan.ark.thread.pool;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HandlerWorkFactory implements ThreadFactory {
    @Override // com.duowan.ark.thread.pool.ThreadFactory
    public WorkThread newThread(String str, int i) {
        HandlerWorkThread handlerWorkThread = new HandlerWorkThread(str, i);
        handlerWorkThread.start();
        return handlerWorkThread;
    }
}
