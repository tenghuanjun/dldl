package com.sqwan.liveshow.huya.request;

import com.sq.websocket_engine.ReqWrapperHandler;
import com.sq.websocket_engine.parse.ResponseDataParse;
import com.sqwan.liveshow.huya.request.bean.danmu.websocket.DanmuReqService;
import com.sqwan.liveshow.huya.request.bean.danmu.websocket.JoinRoomMsgReq;
import com.sqwan.liveshow.huya.request.bean.danmu.websocket.LeaveRoomMsgReq;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DanmuRequestManager {
    private DanmuReqService danmuReqService = new DanmuReqService();

    public void reqJoinRoom(String str, final ReqWrapperHandler.FinishListener<Boolean> finishListener) {
        this.danmuReqService.reqJoinRoom(new JoinRoomMsgReq(str), new ReqWrapperHandler.SuccessListener<ResponseDataParse>() { // from class: com.sqwan.liveshow.huya.request.DanmuRequestManager.2
            @Override // com.sq.websocket_engine.ReqWrapperHandler.SuccessListener
            public void on(ResponseDataParse responseDataParse) {
                ReqWrapperHandler.FinishListener finishListener2 = finishListener;
                if (finishListener2 != null) {
                    finishListener2.on(true);
                }
            }
        }).onFailed(new ReqWrapperHandler.FailedListener<ResponseDataParse>() { // from class: com.sqwan.liveshow.huya.request.DanmuRequestManager.1
            @Override // com.sq.websocket_engine.ReqWrapperHandler.FailedListener
            public void on(ResponseDataParse responseDataParse) {
                ReqWrapperHandler.FinishListener finishListener2 = finishListener;
                if (finishListener2 != null) {
                    finishListener2.on(false);
                }
            }
        });
    }

    public void reqLeaveRoom(String str, final ReqWrapperHandler.FinishListener<Boolean> finishListener) {
        this.danmuReqService.reqleaveRoom(new LeaveRoomMsgReq(str), new ReqWrapperHandler.SuccessListener<ResponseDataParse>() { // from class: com.sqwan.liveshow.huya.request.DanmuRequestManager.4
            @Override // com.sq.websocket_engine.ReqWrapperHandler.SuccessListener
            public void on(ResponseDataParse responseDataParse) {
                ReqWrapperHandler.FinishListener finishListener2 = finishListener;
                if (finishListener2 != null) {
                    finishListener2.on(true);
                }
            }
        }).onFailed(new ReqWrapperHandler.FailedListener<ResponseDataParse>() { // from class: com.sqwan.liveshow.huya.request.DanmuRequestManager.3
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
