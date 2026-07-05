package com.huya.mtp.api;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ContextApiDelegate implements ContextApi {
    private ContextApi mContextApi;

    public void setContextApi(ContextApi contextApi) {
        this.mContextApi = contextApi;
    }

    @Override // com.huya.mtp.api.ContextApi
    public Application getApplication() {
        ContextApi contextApi = this.mContextApi;
        if (contextApi != null) {
            return contextApi.getApplication();
        }
        return null;
    }

    @Override // com.huya.mtp.api.ContextApi
    public Context getApplicationContext() {
        ContextApi contextApi = this.mContextApi;
        if (contextApi != null) {
            return contextApi.getApplicationContext();
        }
        return null;
    }
}
