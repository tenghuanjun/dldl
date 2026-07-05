package com.duowan.auk.http.v2.executor;

import com.duowan.auk.http.v2.HttpRequestDelegate;
import com.duowan.auk.http.v2.HttpResponseDelegate;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class FunctionExecutor implements Comparable<FunctionExecutor> {
    private volatile int mPriority;

    public abstract <Rsp> void cancel(HttpRequestDelegate httpRequestDelegate, HttpResponseDelegate<Rsp> httpResponseDelegate);

    public abstract <Rsp> void execute(HttpRequestDelegate httpRequestDelegate, HttpResponseDelegate<Rsp> httpResponseDelegate);

    public int getPriority() {
        return this.mPriority;
    }

    public void setPriority(int i) {
        this.mPriority = i;
    }

    @Override // java.lang.Comparable
    public synchronized int compareTo(FunctionExecutor functionExecutor) {
        if (functionExecutor == null) {
            return 0;
        }
        return functionExecutor.getPriority() - getPriority();
    }
}
