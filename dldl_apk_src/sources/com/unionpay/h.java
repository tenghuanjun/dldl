package com.unionpay;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class h implements Handler.Callback {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        IUnionCallback iUnionCallback;
        String str;
        String str2;
        int i = message.what;
        if (i != 1006) {
            if (i != 1007 || this.a.b || this.a.c == null) {
                return true;
            }
            g.c(this.a);
            if (message.getData() != null) {
                UPPayAssistEx.a(message.getData(), this.a.c);
                return true;
            }
            iUnionCallback = this.a.c;
            str = "03";
            str2 = "unknown error";
        } else {
            if (this.a.b || this.a.c == null) {
                return true;
            }
            g.c(this.a);
            iUnionCallback = this.a.c;
            str = UPPayAssistEx.SDK_TYPE;
            str2 = "network error";
        }
        iUnionCallback.onError(str, str2);
        return true;
    }
}
