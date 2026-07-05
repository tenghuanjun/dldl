package com.huya.hysignal.core;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FakeCall implements Call {
    private com.huya.mtp.hyns.api.Request request;

    @Override // com.huya.hysignal.core.Call
    public void cancel() {
    }

    public FakeCall(com.huya.mtp.hyns.api.Request request) {
        this.request = request;
    }

    @Deprecated
    public FakeCall(Request request) {
        this.request = request.toNSRequest();
    }

    @Override // com.huya.hysignal.core.Call
    public com.huya.mtp.hyns.api.Request request() {
        return this.request;
    }

    @Override // com.huya.hysignal.core.Call
    public Response execute() throws Exception {
        return new Response(null, new HySignalError(10, -10));
    }

    @Override // com.huya.hysignal.core.Call
    public void enqueue(Callback callback) {
        callback.onResponse(null, new HySignalError(10, -10));
    }
}
