package com.sy37sdk.account.floatview.request.websocket;

import com.sq.websocket_engine.ReqWrapperHandler;
import com.sq.websocket_engine.parse.ResponseDataParse;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatReqService extends ReqWrapperHandler {
    public ReqWrapperHandler.FailedHolder<ResponseDataParse> requestFloatWindowRedDotPlatformMsg(FloatWindowRedDotMsgPlatformReq floatWindowRedDotMsgPlatformReq, ReqWrapperHandler.SuccessListener<ResponseDataParse> successListener) {
        return reqImpl(floatWindowRedDotMsgPlatformReq, successListener);
    }

    public ReqWrapperHandler.FailedHolder<ResponseDataParse> requestFloatWindowRedDotPidGidMsg(FloatWindowRedDotMsgPidGidReq floatWindowRedDotMsgPidGidReq, ReqWrapperHandler.SuccessListener<ResponseDataParse> successListener) {
        return reqImpl(floatWindowRedDotMsgPidGidReq, successListener);
    }
}
