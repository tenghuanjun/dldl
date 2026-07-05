package com.huya.mtp.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DidApiDelegate {
    private DidApi mApi;

    public DidApiDelegate() {
    }

    public DidApiDelegate(DidApi didApi) {
        this.mApi = didApi;
    }

    public void setDidVarApi(DidApi didApi) {
        this.mApi = didApi;
    }

    public String getOaid() {
        DidApi didApi = this.mApi;
        if (didApi != null) {
            return didApi.getOaid();
        }
        return null;
    }
}
