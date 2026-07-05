package com.sq.websocket_engine;

import android.text.TextUtils;
import com.sq.websocket_engine.ReqWrapperHandler;
import com.sq.websocket_engine.parse.ResponseDataParse;
import com.sqwan.common.mod.CommonConfigs;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseRequestManager {
    private BaseReqService baseReqService = new BaseReqService();

    public void reqAuth(final ReqWrapperHandler.FinishListener<BodyData> finishListener) {
        CommonConfigs.getInstance().setSession_id("");
        this.baseReqService.reqAuth(new AuthMsgReq(), new ReqWrapperHandler.SuccessListener<ResponseDataParse>() { // from class: com.sq.websocket_engine.BaseRequestManager.2
            @Override // com.sq.websocket_engine.ReqWrapperHandler.SuccessListener
            public void on(ResponseDataParse responseDataParse) {
                String str = responseDataParse.body.session_id;
                if (!TextUtils.isEmpty(str)) {
                    CommonConfigs.getInstance().setSession_id(str);
                }
                ReqWrapperHandler.FinishListener finishListener2 = finishListener;
                if (finishListener2 != null) {
                    finishListener2.on(responseDataParse.body);
                }
            }
        }).onFailed(new ReqWrapperHandler.FailedListener<ResponseDataParse>() { // from class: com.sq.websocket_engine.BaseRequestManager.1
            @Override // com.sq.websocket_engine.ReqWrapperHandler.FailedListener
            public void on(ResponseDataParse responseDataParse) {
                ReqWrapperHandler.FinishListener finishListener2 = finishListener;
                if (finishListener2 != null) {
                    finishListener2.on(null);
                }
            }
        });
    }

    public void reqHeartBeat(final ReqWrapperHandler.FinishListener<Boolean> finishListener) {
        this.baseReqService.reqHeartbeart(new HeartbeatMsgReq(), new ReqWrapperHandler.SuccessListener<ResponseDataParse>() { // from class: com.sq.websocket_engine.BaseRequestManager.4
            @Override // com.sq.websocket_engine.ReqWrapperHandler.SuccessListener
            public void on(ResponseDataParse responseDataParse) {
                ReqWrapperHandler.FinishListener finishListener2 = finishListener;
                if (finishListener2 != null) {
                    finishListener2.on(true);
                }
            }
        }).onFailed(new ReqWrapperHandler.FailedListener<ResponseDataParse>() { // from class: com.sq.websocket_engine.BaseRequestManager.3
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
