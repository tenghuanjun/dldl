package com.getui.gtc.base.http;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface Call {

    public interface Callback {
        void onFailure(Call call, Exception exc);

        void onResponse(Call call, Response response);
    }

    void cancel();

    void enqueue(Callback callback);

    Response execute() throws Exception;

    boolean isCanceled();

    boolean isExecuted();

    Request request();
}
