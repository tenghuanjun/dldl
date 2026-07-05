package com.unionpay.b;

import android.os.Bundle;
import android.os.Message;
import com.huawei.nfc.sdk.service.HwOpenPayTask;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class f implements HwOpenPayTask.IHwPayResultCallBack {
    final /* synthetic */ b a;

    f(b bVar) {
        this.a = bVar;
    }

    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwPayResultCallBack
    public final void onError(String str, String str2) {
        com.unionpay.utils.j.c("uppay", "queryHwPayStatus onError, errorCode:" + str + " errorMsg:" + str2);
        if (this.a.j != null) {
            Message messageObtainMessage = this.a.j.obtainMessage(4002);
            Bundle bundle = new Bundle();
            bundle.putString("errorCode", str);
            bundle.putString("errorDesc", str2);
            messageObtainMessage.obj = bundle;
            this.a.j.sendMessage(messageObtainMessage);
        }
    }

    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwPayResultCallBack
    public final void onResult(Bundle bundle) {
        com.unionpay.utils.j.c("uppay", "queryHwPayStatus onResult");
        if (this.a.j != null) {
            Message messageObtainMessage = this.a.j.obtainMessage(4001);
            messageObtainMessage.obj = bundle;
            this.a.j.sendMessage(messageObtainMessage);
        }
    }
}
