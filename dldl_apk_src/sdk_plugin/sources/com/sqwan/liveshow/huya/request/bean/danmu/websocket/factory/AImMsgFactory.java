package com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory;

import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.request.bean.danmu.http.FetchImRspBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.SendImReqBean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AImMsgFactory {
    public static int imType_normal = 1;
    public static int imType_notification;

    public AImMsg convert(FetchImRspBean.ImMsg imMsg) {
        AImMsg imNormal;
        if (imMsg.msg.type == imType_notification) {
            imNormal = new ImNotification();
        } else {
            imNormal = new ImNormal();
        }
        imNormal.init(imMsg);
        return imNormal;
    }

    public FetchImRspBean.ImMsg buidNotification() {
        FetchImRspBean.ImMsg imMsg = new FetchImRspBean.ImMsg();
        SendImReqBean.ImMsg imMsg2 = new SendImReqBean.ImMsg();
        imMsg2.type = imType_notification;
        imMsg2.nickname = "系统消息";
        imMsg2.content = "请各位用户文明发言，禁止传播任何违法违规、暴力血腥、低俗色情等不良信息；请勿轻信各类代练代抽、购买礼包码、游戏币等广告信息或私下交易信息，以免上当受骗。";
        imMsg.msg = imMsg2;
        return imMsg;
    }

    public FetchImRspBean.ImMsg buildSelfMsg(SendImReqBean sendImReqBean) {
        FetchImRspBean.ImMsg imMsg = new FetchImRspBean.ImMsg();
        imMsg.msg = sendImReqBean.msg;
        imMsg.uname = sendImReqBean.msg.nickname;
        try {
            imMsg.uid = Long.parseLong(LiveshowManager.getInstance().getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imMsg;
    }
}
