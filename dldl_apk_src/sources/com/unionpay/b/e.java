package com.unionpay.b;

import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.mini.UPTsmAddonMini;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class e implements UPTsmAddonMini.UPTsmConnectionListener {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    @Override // com.unionpay.tsmservice.mini.UPTsmAddonMini.UPTsmConnectionListener
    public final void onTsmConnected() {
        com.unionpay.utils.j.c("uppay", "TsmService connected.");
        this.a.b();
    }

    @Override // com.unionpay.tsmservice.mini.UPTsmAddonMini.UPTsmConnectionListener
    public final void onTsmDisconnected() {
        com.unionpay.utils.j.c("uppay", "TsmService disconnected.");
        b bVar = this.a;
        bVar.a(bVar.d, this.a.e, UPSEInfoResp.ERROR_NONE, "Tsm service disconnect");
    }
}
