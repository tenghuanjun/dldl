package com.huya.berry.gamesdk.module;

import com.duowan.HUYA.ComponentDistributeReq;
import com.duowan.HUYA.ComponentDistributeRsp;
import com.duowan.HUYA.GamePartyHeartbeatReq;
import com.duowan.HUYA.GamePartyHeartbeatRsp;
import com.duowan.HUYA.GetPresenterVeriInfoReq;
import com.duowan.HUYA.GetPresenterVeriInfoRsp;
import com.duowan.HUYA.LiveAnnouncementFetchReq;
import com.duowan.HUYA.LiveAnnouncementFetchRsp;
import com.duowan.HUYA.LiveAnnouncementSettingReq;
import com.duowan.HUYA.LiveAnnouncementSettingRsp;
import com.duowan.HUYA.SendMessageReq;
import com.duowan.HUYA.SendMessageRsp;
import com.duowan.HUYA.SetLiveAttributeReq;
import com.duowan.monitor.jce.MetricSet;
import com.duowan.taf.jce.JceStruct;
import com.huya.berry.gamesdk.module.ICommonConstants;
import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.wup.WupFunc;
import com.huya.mtp.hyns.wup.WupProtocol;
import io.reactivex.Observable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
@NSApi(WupProtocol.class)
public interface ICommonWup {
    @WupFunc(servant = "presenterui", value = ICommonConstants.FuncName.GAME_PARTY_HEARTBEAT)
    Observable<GamePartyHeartbeatRsp> gamePartyHeartbeat(GamePartyHeartbeatReq gamePartyHeartbeatReq);

    @WupFunc(servant = "liveui", value = ICommonConstants.FuncName.GET_COM_LIST)
    Observable<ComponentDistributeRsp> getComponentDistribute(ComponentDistributeReq componentDistributeReq);

    @WupFunc(servant = "liveui", value = ICommonConstants.FuncName.GET_MY_LIST_ANNOUNCEMENT)
    Observable<LiveAnnouncementFetchRsp> getMyLiveAnnouncement(LiveAnnouncementFetchReq liveAnnouncementFetchReq);

    @WupFunc(servant = "liveui", value = ICommonConstants.FuncName.PRESENTER_VERIFY_INFO)
    Observable<GetPresenterVeriInfoRsp> getPresenterVeriInfo(GetPresenterVeriInfoReq getPresenterVeriInfoReq);

    @WupFunc(servant = ICommonConstants.ServiceName.METRIC, value = ICommonConstants.FuncName.METRIC_REPORT)
    Observable<JceStruct> metricReport(MetricSet metricSet);

    @WupFunc(servant = "liveui", value = ICommonConstants.FuncName.SEND_MESSAGE)
    Observable<SendMessageRsp> sendMessage(SendMessageReq sendMessageReq);

    @WupFunc(servant = "wupui", value = ICommonConstants.FuncName.SET_LIVE_TAG)
    Observable<JceStruct> setLiveAttribute(SetLiveAttributeReq setLiveAttributeReq);

    @WupFunc(servant = "liveui", value = ICommonConstants.FuncName.SET_MY_LIST_ANNOUNCEMENT)
    Observable<LiveAnnouncementSettingRsp> setMyLiveAnnouncement(LiveAnnouncementSettingReq liveAnnouncementSettingReq);
}
