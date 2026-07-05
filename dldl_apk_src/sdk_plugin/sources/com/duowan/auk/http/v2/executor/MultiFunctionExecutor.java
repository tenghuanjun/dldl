package com.duowan.auk.http.v2.executor;

import com.duowan.auk.http.v2.HttpRequestDelegate;
import com.duowan.auk.http.v2.HttpResponseDelegate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MultiFunctionExecutor extends FunctionExecutor {
    private List<FunctionExecutor> mFunctionExecutors;

    public MultiFunctionExecutor(List<FunctionExecutor> list) {
        ArrayList arrayList = new ArrayList();
        this.mFunctionExecutors = arrayList;
        arrayList.addAll(list);
    }

    public MultiFunctionExecutor(FunctionExecutor... functionExecutorArr) {
        ArrayList arrayList = new ArrayList();
        this.mFunctionExecutors = arrayList;
        Collections.addAll(arrayList, functionExecutorArr);
    }

    public void addExecutor(FunctionExecutor functionExecutor) {
        this.mFunctionExecutors.add(functionExecutor);
    }

    public boolean hasExecutor(FunctionExecutor functionExecutor) {
        return this.mFunctionExecutors.contains(functionExecutor);
    }

    public void removeExecutor(FunctionExecutor functionExecutor) {
        this.mFunctionExecutors.remove(functionExecutor);
    }

    @Override // com.duowan.auk.http.v2.executor.FunctionExecutor
    public <Rsp> void execute(HttpRequestDelegate httpRequestDelegate, HttpResponseDelegate<Rsp> httpResponseDelegate) {
        new FunctionExecutorQueue(this.mFunctionExecutors, httpRequestDelegate, httpResponseDelegate).execute();
    }

    @Override // com.duowan.auk.http.v2.executor.FunctionExecutor
    public <Rsp> void cancel(HttpRequestDelegate httpRequestDelegate, HttpResponseDelegate<Rsp> httpResponseDelegate) {
        Iterator<FunctionExecutor> it = this.mFunctionExecutors.iterator();
        while (it.hasNext()) {
            it.next().cancel(httpRequestDelegate, httpResponseDelegate);
        }
    }
}
