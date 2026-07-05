package com.unionpay.b;

import android.os.Bundle;
import android.os.Message;
import com.huawei.nfc.sdk.service.HwOpenPayTask;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class d implements HwOpenPayTask.IHwResultCallBack {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwResultCallBack
    public final void onResult(int i, Bundle bundle) {
        com.unionpay.utils.j.c("uppay", "supportCapacity result:" + i);
        if (this.a.j != null) {
            Message messageObtainMessage = this.a.j.obtainMessage(4004);
            messageObtainMessage.obj = Integer.valueOf(i);
            this.a.j.sendMessage(messageObtainMessage);
        }
    }
}
