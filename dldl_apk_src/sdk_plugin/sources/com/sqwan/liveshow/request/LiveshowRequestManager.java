package com.sqwan.liveshow.request;

import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.liveshow.LiveShowUrl;
import com.sqwan.liveshow.bean.LiveshowChannelInfo;
import com.sqwan.liveshow.bean.LiveshowConfig;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowRequestManager {
    public void reqGetRadioChannels(SqHttpCallback<LiveshowChannelInfo> sqHttpCallback) {
        SqRequest.of(LiveShowUrl.rlapi).signV3().addParamsTransformer(new LiveShowParams()).post(sqHttpCallback, LiveshowChannelInfo.class);
    }

    public void reqGetRadioChannel(String str, SqHttpCallback<LiveshowChannelInfo.ChannelsBean> sqHttpCallback) {
        SqRequest.of(LiveShowUrl.rlcapi).signV3().addParam("cid", str).addParamsTransformer(new LiveShowParams()).post(sqHttpCallback, LiveshowChannelInfo.ChannelsBean.class);
    }

    public void reqGetRadioConfig(SqHttpCallback<LiveshowConfig> sqHttpCallback) {
        SqRequest.of(LiveShowUrl.radio_config).signV3().addParamsTransformer(new LiveShowParams()).get(sqHttpCallback, LiveshowConfig.class);
    }
}
