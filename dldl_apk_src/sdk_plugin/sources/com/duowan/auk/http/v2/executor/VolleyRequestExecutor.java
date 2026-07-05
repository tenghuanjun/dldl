package com.duowan.auk.http.v2.executor;

import com.duowan.auk.http.v2.HttpRequestDelegate;
import com.duowan.auk.http.v2.HttpResponseDelegate;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class VolleyRequestExecutor extends FunctionExecutor {
    @Override // com.duowan.auk.http.v2.executor.FunctionExecutor
    public <Rsp> void execute(HttpRequestDelegate httpRequestDelegate, HttpResponseDelegate<Rsp> httpResponseDelegate) {
        AccurateTimeOutHttpRequest.getInstance(httpRequestDelegate, httpResponseDelegate).execute();
    }

    @Override // com.duowan.auk.http.v2.executor.FunctionExecutor
    public <Rsp> void cancel(HttpRequestDelegate httpRequestDelegate, HttpResponseDelegate<Rsp> httpResponseDelegate) {
        AccurateTimeOutHttpRequest.getInstance(httpRequestDelegate, httpResponseDelegate).cancel();
    }
}
