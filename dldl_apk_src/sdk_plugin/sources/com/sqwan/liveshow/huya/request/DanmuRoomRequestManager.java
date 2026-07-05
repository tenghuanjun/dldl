package com.sqwan.liveshow.huya.request;

import android.content.Context;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.mod.CommonConfigs;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.request.bean.danmu.http.EnterRoomReqBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.EnterRoomRspBean;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DanmuRoomRequestManager extends HyLiveshowBaseRequestManager {
    public DanmuRoomRequestManager(Context context) {
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
        map.put("sversion", VersionUtil.sdkVersion);
        map.put("version", AppUtils.getVersionName(this.mContext));
        map.put("gwversion", "4.6.7");
        map.put("time", "" + getCurrentTime());
        map.put("rid", LiveshowManager.getInstance().getRoomId());
        map.put("uid", LiveshowManager.getInstance().getUserId());
        map.put("uname", LiveshowManager.getInstance().getUserName());
        map.put("nickname", CommonConfigs.getInstance().getBaseUserInfo().roleName);
        return map;
    }

    public void enterRoom(EnterRoomReqBean enterRoomReqBean, SqHttpCallback<EnterRoomRspBean> sqHttpCallback) {
        post(SqRequest.of(LiveRoomUrl.LIVE_ROOM_ENTER).signV3().params(generateBaseParams(enterRoomReqBean.toMap())), sqHttpCallback, EnterRoomRspBean.class);
    }

    public void leaveRoom(SqHttpCallback<Void> sqHttpCallback) {
        post(SqRequest.of(LiveRoomUrl.LIVE_ROOM_LEAVE).signV3().params(generateBaseParams()), sqHttpCallback, Void.class);
    }
}
