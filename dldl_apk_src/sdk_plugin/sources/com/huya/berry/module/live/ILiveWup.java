package com.huya.berry.module.live;

import com.duowan.HUYA.BeginLiveReq;
import com.duowan.HUYA.BeginLiveRsp;
import com.duowan.HUYA.ChangeLiveInfoReq;
import com.duowan.HUYA.ChangeLiveInfoRsp;
import com.duowan.HUYA.EndLiveReq;
import com.duowan.HUYA.GetActiveEventInfoReq;
import com.duowan.HUYA.GetActiveEventInfoRsp;
import com.duowan.HUYA.GetConfigReq;
import com.duowan.HUYA.GetConfigRsp;
import com.duowan.HUYA.GetLiveSummaryReq;
import com.duowan.HUYA.GetLiveSummaryRsp;
import com.duowan.HUYA.GetLivingInfoReq;
import com.duowan.HUYA.GetLivingInfoRsp;
import com.duowan.HUYA.GetMobilePropsItemReq;
import com.duowan.HUYA.GetMobilePropsItemRsp;
import com.duowan.HUYA.GetMobilePropsListReq;
import com.duowan.HUYA.GetMobilePropsListRsp;
import com.duowan.HUYA.GetRelationReq;
import com.duowan.HUYA.GetRelationRsp;
import com.duowan.HUYA.ModRelationReq;
import com.duowan.HUYA.ModRelationRsp;
import com.duowan.HUYA.PresentHeartBeatReq;
import com.duowan.HUYA.SubScribeListUserRecItemRsp;
import com.duowan.HUYA.SubscribeToListReq;
import com.duowan.HUYA.SwitchStatusRsp;
import com.duowan.HUYA.SwitchToGameTemplateReq;
import com.duowan.HUYA.TransMsgToViewerReq;
import com.duowan.HUYA.TransMsgToViewerRsp;
import com.duowan.HUYA.UserEventReq;
import com.duowan.HUYA.UserEventRsp;
import com.duowan.HUYA.UserHeartBeatReq;
import com.duowan.HUYA.UserHeartBeatRsp;
import com.duowan.HUYA.UserRecListReq;
import com.duowan.HUYA.UserRecListRsp;
import com.duowan.taf.jce.JceStruct;
import com.huya.berry.module.live.ILiveConstants;
import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.wup.WupFunc;
import com.huya.mtp.hyns.wup.WupProtocol;
import io.reactivex.Observable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
@NSApi(WupProtocol.class)
public interface ILiveWup {
    @WupFunc(servant = "huyauserui", value = ILiveConstants.FuncName.ADD_SUBSCRIBE)
    Observable<ModRelationRsp> addSubscribe(ModRelationReq modRelationReq);

    @WupFunc(servant = "liveui", value = ILiveConstants.FuncName.BEGIN_LIVE)
    Observable<BeginLiveRsp> beginLive(BeginLiveReq beginLiveReq);

    @WupFunc(servant = "liveui", value = "changeLiveInfo")
    Observable<ChangeLiveInfoRsp> changeLiveInfo(ChangeLiveInfoReq changeLiveInfoReq);

    @WupFunc(servant = "huyauserui", value = ILiveConstants.FuncName.DEL_SUBSCRIBE)
    Observable<ModRelationRsp> delSubscribe(ModRelationReq modRelationReq);

    @WupFunc(servant = "liveui", value = "endLive")
    Observable<JceStruct> endLive(EndLiveReq endLiveReq);

    @WupFunc(servant = "liveui", value = ILiveConstants.FuncName.GET_ACTIVE_EVENT_INFO)
    Observable<GetActiveEventInfoRsp> getAnthorRecruitInfo(GetActiveEventInfoReq getActiveEventInfoReq);

    @WupFunc(servant = "mobileui", value = ILiveConstants.FuncName.GETCONFIG)
    Observable<GetConfigRsp> getConfig(GetConfigReq getConfigReq);

    @WupFunc(servant = ILiveConstants.ServiceName.COMMUI, value = ILiveConstants.FuncName.GET_LIVE_SUMMARY)
    Observable<GetLiveSummaryRsp> getLiveSummary(GetLiveSummaryReq getLiveSummaryReq);

    @WupFunc(servant = "liveui", value = ILiveConstants.FuncName.GET_LIVEING_INFO)
    Observable<GetLivingInfoRsp> getLivingInfo(GetLivingInfoReq getLivingInfoReq);

    @WupFunc(servant = ILiveConstants.ServiceName.PROPS_SERVER_NAME, value = ILiveConstants.FuncName.GET_MOBILE_PROPS_ITEM)
    Observable<GetMobilePropsItemRsp> getMobilePropsItem(GetMobilePropsItemReq getMobilePropsItemReq);

    @WupFunc(servant = ILiveConstants.ServiceName.PROPS_SERVER_NAME, value = ILiveConstants.FuncName.GET_MOBILE_PROPS_LIST)
    Observable<GetMobilePropsListRsp> getMobilePropsList(GetMobilePropsListReq getMobilePropsListReq);

    @WupFunc(servant = "mobileui", value = ILiveConstants.FuncName.GET_REC_LIST_BY_GAME)
    Observable<UserRecListRsp> getRecListByGame(UserRecListReq userRecListReq);

    @WupFunc(servant = "huyauserui", value = ILiveConstants.FuncName.GET_RELATION)
    Observable<GetRelationRsp> getRelation(GetRelationReq getRelationReq);

    @WupFunc(servant = "huyauserui", value = ILiveConstants.FuncName.GET_USER_SUBSCRIBE_LIVELIST)
    Observable<SubScribeListUserRecItemRsp> getUserSubscribeLiveList(SubscribeToListReq subscribeToListReq);

    @WupFunc(servant = ILiveConstants.ServiceName.ON_LIVE_SERVER_NAME, value = ILiveConstants.FuncName.PRESENTER_HEART_BEAT)
    Observable<JceStruct> heartBeat(PresentHeartBeatReq presentHeartBeatReq);

    @WupFunc(servant = "presenterui", value = ILiveConstants.FuncName.SWITCH_TO_GAME_TEMPLATE)
    Observable<SwitchStatusRsp> switchToGameTemplate(SwitchToGameTemplateReq switchToGameTemplateReq);

    @WupFunc(servant = "liveui", value = ILiveConstants.FuncName.TRANS_MSG_TO_VIEWER)
    Observable<TransMsgToViewerRsp> transMsgToViewer(TransMsgToViewerReq transMsgToViewerReq);

    @WupFunc(servant = ILiveConstants.ServiceName.ON_LIVE_SERVER_NAME, value = ILiveConstants.FuncName.ON_USER_EVENT)
    Observable<UserEventRsp> userEvent(UserEventReq userEventReq);

    @WupFunc(servant = ILiveConstants.ServiceName.ON_LIVE_SERVER_NAME, value = ILiveConstants.FuncName.ON_USER_HEART_BEAT)
    Observable<UserHeartBeatRsp> userHeartBeat(UserHeartBeatReq userHeartBeatReq);
}
