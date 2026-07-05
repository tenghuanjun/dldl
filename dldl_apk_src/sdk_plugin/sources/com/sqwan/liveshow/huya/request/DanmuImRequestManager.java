package com.sqwan.liveshow.huya.request;

import android.content.Context;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.request.bean.danmu.http.FetchImReqBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.FetchImRspBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.SendImReqBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.SendImRspBean;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DanmuImRequestManager extends HyLiveshowBaseRequestManager {
    public DanmuImRequestManager(Context context) {
        super(context);
    }

    protected Map<String, String> generateBaseParams(Map<String, String> map) {
        Map<String, String> mapGenerateBaseParams = generateBaseParams();
        if (map != null) {
            mapGenerateBaseParams.putAll(map);
        }
        return mapGenerateBaseParams;
    }

    protected Map<String, String> generateBaseParams() {
        HashMap map = new HashMap();
        SQAppConfig sQAppConfig = ConfigManager.getInstance(this.mContext).getSQAppConfig();
        map.put("pid", sQAppConfig.getPartner());
        map.put("gid", sQAppConfig.getGameid());
        map.put("refer", sQAppConfig.getRefer());
        map.put("dev", DevLogic.getInstance(this.mContext).getValue());
        map.put("time", "" + getCurrentTime());
        map.put("rid", LiveshowManager.getInstance().getRoomId());
        map.put("uid", LiveshowManager.getInstance().getUserId());
        map.put("uname", LiveshowManager.getInstance().getUserName());
        return map;
    }

    public void fetchIm(SqHttpCallback<FetchImRspBean> sqHttpCallback) {
        get(SqRequest.of(LiveRoomUrl.LIVE_ROOM_IM_FETCH).signV3().params(generateBaseParams(new FetchImReqBean().toMap())), sqHttpCallback, FetchImRspBean.class);
    }

    public void sendIm(SendImReqBean sendImReqBean, SqHttpCallback<SendImRspBean> sqHttpCallback) {
        post(SqRequest.of(LiveRoomUrl.LIVE_ROOM_IM_SEND).signV3().params(generateBaseParams(sendImReqBean.toMap())), sqHttpCallback, SendImRspBean.class);
    }
}
