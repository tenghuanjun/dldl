package com.unionpay.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.unionpay.tsmservice.mini.ITsmCallback;
import com.unionpay.tsmservice.mini.result.QueryVendorPayStatusResult;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public final class a extends ITsmCallback.Stub {
    private int a = TTAdConstant.INIT_LOCAL_FAIL_CODE;
    private Handler b;

    public a(Handler handler) {
        this.b = handler;
    }

    @Override // com.unionpay.tsmservice.mini.ITsmCallback
    public final void onError(String str, String str2) {
        com.unionpay.utils.j.c("uppay", "errorCode:" + str + ", errorDesc:" + str2);
        Handler handler = this.b;
        handler.sendMessage(Message.obtain(handler, 1, this.a, 0, str + str2));
    }

    @Override // com.unionpay.tsmservice.mini.ITsmCallback
    public final void onResult(Bundle bundle) {
        if (this.a != 4000) {
            return;
        }
        com.unionpay.utils.j.c("uppay-spay", "query vendor pay status callback");
        bundle.setClassLoader(QueryVendorPayStatusResult.class.getClassLoader());
        Bundle queryVendorPayStatusResult = ((QueryVendorPayStatusResult) bundle.get("result")).getQueryVendorPayStatusResult();
        Handler handler = this.b;
        handler.sendMessage(Message.obtain(handler, TTAdConstant.INIT_LOCAL_FAIL_CODE, queryVendorPayStatusResult));
    }
}
