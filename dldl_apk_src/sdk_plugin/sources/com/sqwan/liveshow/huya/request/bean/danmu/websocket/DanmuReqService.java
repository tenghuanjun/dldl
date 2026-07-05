package com.sqwan.liveshow.huya.request.bean.danmu.websocket;

import com.sq.websocket_engine.ReqWrapperHandler;
import com.sq.websocket_engine.parse.ResponseDataParse;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DanmuReqService extends ReqWrapperHandler {
    public ReqWrapperHandler.FailedHolder<ResponseDataParse> reqJoinRoom(JoinRoomMsgReq joinRoomMsgReq, ReqWrapperHandler.SuccessListener<ResponseDataParse> successListener) {
        return reqImpl(joinRoomMsgReq, successListener);
    }

    public ReqWrapperHandler.FailedHolder<ResponseDataParse> reqleaveRoom(LeaveRoomMsgReq leaveRoomMsgReq, ReqWrapperHandler.SuccessListener<ResponseDataParse> successListener) {
        return reqImpl(leaveRoomMsgReq, successListener);
    }
}
