package com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory;

import android.text.TextUtils;
import com.sq.websocket_engine.ARecInfMsg;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.request.bean.danmu.http.FetchImRspBean;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RoomRecInfMsg extends ARecInfMsg<List<FetchImRspBean.ImMsg>> {
    @Override // com.sq.websocket_engine.ARecInfMsg
    public List<FetchImRspBean.ImMsg> getInf() {
        return (List) this.inf;
    }

    @Override // com.sq.websocket_engine.ARecInfMsg
    public boolean filter() {
        return !checkRoomId(this.responseDataParse.body.ev, LiveshowManager.getInstance().getRoomId());
    }

    public boolean checkRoomId(String str, String str2) {
        return TextUtils.equals(str, String.format("lv.rm.%s", str2));
    }
}
