package com.sqwan.liveshow.huya.request.bean.danmu.websocket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LeaveRoomMsgReq extends DanmuMsgBaseReq {
    public LeaveRoomMsgReq(String str) {
        setOp(7);
        setEv(String.format("lv.rm.%s", str));
    }
}
