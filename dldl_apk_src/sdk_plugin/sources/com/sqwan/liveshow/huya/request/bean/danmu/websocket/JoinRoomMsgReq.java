package com.sqwan.liveshow.huya.request.bean.danmu.websocket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class JoinRoomMsgReq extends DanmuMsgBaseReq {
    public JoinRoomMsgReq(String str) {
        setOp(5);
        setEv(String.format("lv.rm.%s", str));
    }
}
