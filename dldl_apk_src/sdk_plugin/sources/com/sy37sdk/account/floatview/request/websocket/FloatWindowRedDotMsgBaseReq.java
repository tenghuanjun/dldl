package com.sy37sdk.account.floatview.request.websocket;

import com.sq.websocket_engine.MsgBaseReq;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class FloatWindowRedDotMsgBaseReq extends MsgBaseReq {
    public abstract String getRealAppId();

    public FloatWindowRedDotMsgBaseReq() {
        setAppid(getRealAppId());
        setEv("fl.rd.nt");
        setOp(5);
    }
}
