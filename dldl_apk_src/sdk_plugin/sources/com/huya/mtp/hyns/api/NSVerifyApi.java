package com.huya.mtp.hyns.api;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.protocol.NSVerifyProtocol;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(NSVerifyProtocol.class)
public interface NSVerifyApi {

    public interface VerifyBizListener {
        void onResult(boolean z, String str);
    }

    public interface VerifyBizListenerV2 {
        void onResult(boolean z, int i, String str);
    }

    void unVerifyTokenIfNeed(VerifyBizListener verifyBizListener);

    void verifyTokenIfNeed(VerifyBizListener verifyBizListener);

    void verifyTokenV2(VerifyBizListenerV2 verifyBizListenerV2);
}
