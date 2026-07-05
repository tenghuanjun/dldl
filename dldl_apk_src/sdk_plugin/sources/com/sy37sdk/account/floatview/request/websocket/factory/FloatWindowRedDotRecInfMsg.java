package com.sy37sdk.account.floatview.request.websocket.factory;

import com.sq.websocket_engine.ARecInfMsg;
import com.sy37sdk.account.floatview.request.bean.FetchFloatWindowRedDotRspBean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatWindowRedDotRecInfMsg extends ARecInfMsg<FetchFloatWindowRedDotRspBean> {
    @Override // com.sq.websocket_engine.ARecInfMsg
    public boolean filter() {
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sq.websocket_engine.ARecInfMsg
    public FetchFloatWindowRedDotRspBean getInf() {
        return (FetchFloatWindowRedDotRspBean) this.inf;
    }
}
