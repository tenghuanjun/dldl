package com.unionpay.b;

import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.mi.mini.UPTsmAddonMini;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class i implements UPTsmAddonMini.UPTsmConnectionListener {
    final /* synthetic */ g a;

    i(g gVar) {
        this.a = gVar;
    }

    @Override // com.unionpay.tsmservice.mi.mini.UPTsmAddonMini.UPTsmConnectionListener
    public final void onTsmConnected() {
        com.unionpay.utils.j.c("uppay", "mi TsmService connected.");
        this.a.b();
    }

    @Override // com.unionpay.tsmservice.mi.mini.UPTsmAddonMini.UPTsmConnectionListener
    public final void onTsmDisconnected() {
        com.unionpay.utils.j.c("uppay", "mi TsmService disconnected.");
        g gVar = this.a;
        gVar.a(gVar.d, this.a.e, UPSEInfoResp.ERROR_NONE, "Tsm service disconnect");
    }
}
