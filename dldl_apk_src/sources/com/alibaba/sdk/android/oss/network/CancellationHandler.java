package com.alibaba.sdk.android.oss.network;

import okhttp3.Call;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class CancellationHandler {
    private volatile Call call;
    private volatile boolean isCancelled;

    public void cancel() {
        if (this.call != null) {
            this.call.cancel();
        }
        this.isCancelled = true;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCall(Call call) {
        this.call = call;
    }
}
