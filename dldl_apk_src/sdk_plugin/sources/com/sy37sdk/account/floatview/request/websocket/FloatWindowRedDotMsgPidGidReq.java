package com.sy37sdk.account.floatview.request.websocket;

import com.sqwan.common.mod.CommonConfigs;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatWindowRedDotMsgPidGidReq extends FloatWindowRedDotMsgBaseReq {
    @Override // com.sy37sdk.account.floatview.request.websocket.FloatWindowRedDotMsgBaseReq
    public String getRealAppId() {
        return String.format("%s_%s", CommonConfigs.getInstance().getSqAppConfig().getPartner(), CommonConfigs.getInstance().getSqAppConfig().getGameid());
    }
}
