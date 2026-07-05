package com.sqwan.msdk;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MDevObservable extends ISQObservable<String, String> {
    @Override // com.sqwan.msdk.ISQObservable, java.lang.Runnable
    public void run() {
        handleSuccess("");
    }

    @Override // com.sqwan.msdk.ISQObservable
    public void handleSuccess(String str) {
        super.handleSuccess(str);
    }

    @Override // com.sqwan.msdk.ISQObservable
    public void handleFail(String str) {
        super.handleFail(str);
    }
}
