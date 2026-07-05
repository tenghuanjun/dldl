package com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory;

import android.text.TextUtils;
import com.sq.websocket_engine.ARecInfMsg;
import com.sq.websocket_engine.ARecInfMsgBaseFactory;
import com.sqwan.liveshow.huya.engine.LiveshowManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ARecInfMsgDanmuFactory extends ARecInfMsgBaseFactory {
    @Override // com.sq.websocket_engine.ARecInfMsgBaseFactory
    public String getTargetAppid() {
        return "danmu";
    }

    @Override // com.sq.websocket_engine.ARecInfMsgBaseFactory
    public ARecInfMsg convert(String str) {
        if (TextUtils.equals(str, getRoomMsgEv())) {
            return new RoomRecInfMsg();
        }
        return null;
    }

    private String getRoomMsgEv() {
        return String.format("lv.rm.%s", LiveshowManager.getInstance().getRoomId());
    }
}
