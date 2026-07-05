package com.sqwan.liveshow.huya.request;

import android.content.Context;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.liveshow.huya.bean.ConfigBean;
import com.sqwan.liveshow.huya.bean.LiveMenuBean;
import com.sqwan.liveshow.huya.bean.RecommenedAnchorBean;
import com.sqwan.liveshow.request.LiveShowParams;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveRoomRequestManager extends HyLiveshowBaseRequestManager {
    public LiveRoomRequestManager(Context context) {
        super(context);
    }

    public void ReqGetLiveMenuData(SqHttpCallback<LiveMenuBean> sqHttpCallback) {
        get(SqRequest.of(LiveRoomUrl.LIVE_MENU_URL).signV3().addParamsTransformer(new LiveShowParams()), sqHttpCallback, LiveMenuBean.class);
    }

    public void ReqGetRecommendedAnchorData(SqHttpCallback<RecommenedAnchorBean> sqHttpCallback) {
        get(SqRequest.of(LiveRoomUrl.LIVE_RECOMMENDED_ANCHOR).signV3().addParamsTransformer(new LiveShowParams()), sqHttpCallback, RecommenedAnchorBean.class);
    }

    public void ReqGetConfigData(SqHttpCallback<ConfigBean> sqHttpCallback) {
        get(SqRequest.of(LiveRoomUrl.LIVE_CONFIG).signV3().addParamsTransformer(new LiveShowParams()), sqHttpCallback, ConfigBean.class);
    }
}
