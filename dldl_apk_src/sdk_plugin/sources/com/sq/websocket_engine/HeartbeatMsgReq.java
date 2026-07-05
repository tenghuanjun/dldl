package com.sq.websocket_engine;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HeartbeatMsgReq extends MsgBaseReq {
    public HeartbeatMsgReq() {
        setOp(3);
        setEv("lc.hb.up");
    }
}
