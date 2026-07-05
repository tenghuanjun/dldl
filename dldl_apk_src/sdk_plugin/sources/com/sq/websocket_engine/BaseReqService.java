package com.sq.websocket_engine;

import com.sq.websocket_engine.ReqWrapperHandler;
import com.sq.websocket_engine.parse.ResponseDataParse;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseReqService extends ReqWrapperHandler {
    public ReqWrapperHandler.FailedHolder<ResponseDataParse> reqAuth(AuthMsgReq authMsgReq, ReqWrapperHandler.SuccessListener<ResponseDataParse> successListener) {
        return reqImpl(authMsgReq, successListener);
    }

    public ReqWrapperHandler.FailedHolder<ResponseDataParse> reqHeartbeart(HeartbeatMsgReq heartbeatMsgReq, ReqWrapperHandler.SuccessListener<ResponseDataParse> successListener) {
        return reqImpl(heartbeatMsgReq, successListener);
    }
}
