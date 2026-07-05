package com.unionpay.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.UPSEInfoResp;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class h implements Handler.Callback {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.a.i.removeMessages(4);
            com.unionpay.utils.j.c("uppay", "msg error");
            g.a(this.a, message.arg1, (String) message.obj);
            return false;
        }
        if (i == 4) {
            com.unionpay.utils.j.c("uppay", "timeout");
            g.a(this.a, message.arg1, UPSEInfoResp.ERROR_TIMEOUT);
            g.b(this.a);
            return false;
        }
        if (i != 4000) {
            return false;
        }
        this.a.i.removeMessages(4);
        g.a(this.a, (Bundle) message.obj);
        return false;
    }
}
