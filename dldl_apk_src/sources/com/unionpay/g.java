package com.unionpay;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.unionpay.utils.UPUtils;
import java.util.ArrayList;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public final class g {
    private IUnionCallback c;
    private Handler a = null;
    private volatile boolean b = false;
    private final Handler.Callback d = new h(this);

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList a(Context context, String str, String str2) {
        ArrayList arrayListB = null;
        try {
            String strA = com.unionpay.utils.b.a(str, str2);
            if (!TextUtils.isEmpty(strA)) {
                if (this.c != null && !this.b) {
                    arrayListB = com.unionpay.utils.b.b(context, strA);
                }
                UPUtils.a(context, str, "direct_configs");
                UPUtils.a(context, str2, "direct_mode");
            }
        } catch (Exception unused) {
        }
        return arrayListB;
    }

    static /* synthetic */ boolean c(g gVar) {
        gVar.b = true;
        return true;
    }

    protected final void a(Context context, String str, String str2, IUnionCallback iUnionCallback, boolean z) {
        String strD = UPUtils.d(com.unionpay.utils.b.a(str));
        this.b = z;
        this.c = iUnionCallback;
        if (this.a == null) {
            this.a = new Handler(this.d);
        }
        if (!TextUtils.isEmpty(strD)) {
            Executors.newSingleThreadExecutor().execute(new i(this, strD, str2, context, str, iUnionCallback));
        } else {
            if (z || iUnionCallback == null) {
                return;
            }
            iUnionCallback.onError("03", "unknown error");
            this.b = true;
        }
    }
}
