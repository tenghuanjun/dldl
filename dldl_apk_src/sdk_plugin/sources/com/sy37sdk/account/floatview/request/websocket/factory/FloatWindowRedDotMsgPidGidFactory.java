package com.sy37sdk.account.floatview.request.websocket.factory;

import com.sqwan.common.mod.CommonConfigs;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatWindowRedDotMsgPidGidFactory extends FloatWindowRedDotMsgBaseFactory {
    @Override // com.sq.websocket_engine.ARecInfMsgBaseFactory
    public String getTargetAppid() {
        return String.format("%s_%s", CommonConfigs.getInstance().getSqAppConfig().getPartner(), CommonConfigs.getInstance().getSqAppConfig().getGameid());
    }
}
