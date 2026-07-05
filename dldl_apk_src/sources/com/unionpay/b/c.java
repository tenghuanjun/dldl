package com.unionpay.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.unionpay.UPSEInfoResp;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class c implements Handler.Callback {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        String str;
        int iIntValue;
        int i = message.what;
        if (i != 1) {
            if (i != 4) {
                switch (i) {
                    case TTAdConstant.INIT_LOCAL_FAIL_CODE /* 4000 */:
                        this.a.j.removeMessages(4);
                        b.a(this.a, (Bundle) message.obj);
                        break;
                    case 4001:
                        this.a.j.removeMessages(4003);
                        if (message.obj instanceof Bundle) {
                            b.b(this.a, (Bundle) message.obj);
                        }
                        break;
                    case 4002:
                        this.a.j.removeMessages(4003);
                        if (message.obj instanceof Bundle) {
                            b.c(this.a, (Bundle) message.obj);
                        }
                        break;
                    case 4003:
                        str = "queryHwPayStatus timeout";
                        break;
                    case 4004:
                        this.a.j.removeMessages(4005);
                        try {
                            iIntValue = ((Integer) message.obj).intValue();
                        } catch (Exception unused) {
                            iIntValue = 0;
                        }
                        if (!this.a.g) {
                            if (iIntValue != 1) {
                                this.a.c();
                            } else {
                                b.e(this.a);
                            }
                        }
                        break;
                    case 4005:
                        com.unionpay.utils.j.c("uppay", "QUERY_VENDOR_CAPACITY_TIMEOUT");
                        b.g(this.a);
                        this.a.c();
                        break;
                }
            } else {
                str = "timeout";
            }
            com.unionpay.utils.j.c("uppay", str);
            b bVar = this.a;
            bVar.a(bVar.d, bVar.e, UPSEInfoResp.ERROR_TIMEOUT, "timeout");
            b.c(this.a);
        } else {
            this.a.j.removeMessages(4);
            com.unionpay.utils.j.c("uppay", "msg error");
            b.a(this.a, message.arg1, (String) message.obj);
        }
        return false;
    }
}
