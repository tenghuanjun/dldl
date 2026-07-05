package com.sy37sdk.account.floatview.request;

import com.sq.websocket_engine.ReqWrapperHandler;
import com.sq.websocket_engine.parse.ResponseDataParse;
import com.sy37sdk.account.floatview.request.websocket.FloatReqService;
import com.sy37sdk.account.floatview.request.websocket.FloatWindowRedDotMsgPidGidReq;
import com.sy37sdk.account.floatview.request.websocket.FloatWindowRedDotMsgPlatformReq;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatRequestManager {
    private final FloatReqService floatReqService = new FloatReqService();

    public void requestFloatWindowRedDotMsgPlatform(final ReqWrapperHandler.FinishListener<Boolean> finishListener) {
        this.floatReqService.requestFloatWindowRedDotPlatformMsg(new FloatWindowRedDotMsgPlatformReq(), new ReqWrapperHandler.SuccessListener<ResponseDataParse>() { // from class: com.sy37sdk.account.floatview.request.FloatRequestManager.2
            @Override // com.sq.websocket_engine.ReqWrapperHandler.SuccessListener
            public void on(ResponseDataParse responseDataParse) {
                ReqWrapperHandler.FinishListener finishListener2 = finishListener;
                if (finishListener2 != null) {
                    finishListener2.on(true);
                }
            }
        }).onFailed(new ReqWrapperHandler.FailedListener<ResponseDataParse>() { // from class: com.sy37sdk.account.floatview.request.FloatRequestManager.1
            @Override // com.sq.websocket_engine.ReqWrapperHandler.FailedListener
            public void on(ResponseDataParse responseDataParse) {
                ReqWrapperHandler.FinishListener finishListener2 = finishListener;
                if (finishListener2 != null) {
                    finishListener2.on(false);
                }
            }
        });
    }

    public void requestFloatWindowRedDotMsgPidGid(final ReqWrapperHandler.FinishListener<Boolean> finishListener) {
        this.floatReqService.requestFloatWindowRedDotPidGidMsg(new FloatWindowRedDotMsgPidGidReq(), new ReqWrapperHandler.SuccessListener<ResponseDataParse>() { // from class: com.sy37sdk.account.floatview.request.FloatRequestManager.4
            @Override // com.sq.websocket_engine.ReqWrapperHandler.SuccessListener
            public void on(ResponseDataParse responseDataParse) {
                ReqWrapperHandler.FinishListener finishListener2 = finishListener;
                if (finishListener2 != null) {
                    finishListener2.on(true);
                }
            }
        }).onFailed(new ReqWrapperHandler.FailedListener<ResponseDataParse>() { // from class: com.sy37sdk.account.floatview.request.FloatRequestManager.3
            @Override // com.sq.websocket_engine.ReqWrapperHandler.FailedListener
            public void on(ResponseDataParse responseDataParse) {
                ReqWrapperHandler.FinishListener finishListener2 = finishListener;
                if (finishListener2 != null) {
                    finishListener2.on(false);
                }
            }
        });
    }
}
