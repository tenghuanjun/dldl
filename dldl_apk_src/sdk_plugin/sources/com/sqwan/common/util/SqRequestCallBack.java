package com.sqwan.common.util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SqRequestCallBack implements BaseRequestCallBack {
    @Override // com.sqwan.common.util.BaseRequestCallBack
    @Deprecated
    public void onRequestError(String str) {
    }

    @Override // com.sqwan.common.util.BaseRequestCallBack
    public void onRequestSuccess(String str) {
    }

    @Override // com.sqwan.common.util.BaseRequestCallBack
    public void onRequestError(int i, String str) {
        onRequestError(str);
    }
}
