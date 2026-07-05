package com.huya.live.channelinfo.impl.wup;

import com.duowan.HUYA.ChangeLiveInfoReq;
import com.duowan.HUYA.ChangeLiveInfoRsp;
import com.duowan.HUYA.CreateAddrReq;
import com.duowan.HUYA.CreateAddrRsp;
import com.duowan.HUYA.GetMobileGameListReq;
import com.duowan.HUYA.GetMobileGameListRsp;
import com.duowan.HUYA.GetMobileLivingGameInfoReq;
import com.duowan.HUYA.GetMobileLivingGameInfoRsp;
import com.duowan.HUYA.GetPresenterSignInviteReq;
import com.duowan.HUYA.GetPresenterSignInviteRsp;
import com.duowan.HUYA.OpenRtmpAddrReq;
import com.duowan.HUYA.OpenRtmpAddrRsp;
import com.duowan.HUYA.OpenRtmpEndLiveReq;
import com.duowan.HUYA.PresenterNotifyPopupReq;
import com.duowan.HUYA.PresenterNotifyPopupRsp;
import com.duowan.HUYA.QueryAddrReq;
import com.duowan.HUYA.QueryAddrRsp;
import com.duowan.HUYA.ZhuShouLiveingGameListReq;
import com.duowan.HUYA.ZhuShouLiveingGameListRsp;
import com.huya.live.channelinfo.impl.IChannelInfoConstants;
import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.wup.WupFunc;
import com.huya.mtp.hyns.wup.WupProtocol;
import io.reactivex.Observable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(WupProtocol.class)
public interface IChannelInfoWup {
    @WupFunc(servant = "liveui", value = "changeLiveInfo")
    Observable<ChangeLiveInfoRsp> changeLiveInfo(ChangeLiveInfoReq changeLiveInfoReq);

    @WupFunc(servant = "liveui", value = IChannelInfoConstants.FuncName.CREATE_ADDR)
    Observable<CreateAddrRsp> createAddr(CreateAddrReq createAddrReq);

    @WupFunc(servant = "presenterui", value = IChannelInfoConstants.FuncName.GET_MOBILE_LIVING_GAME_INFO)
    Observable<GetMobileLivingGameInfoRsp> getChannelTypeList(GetMobileLivingGameInfoReq getMobileLivingGameInfoReq);

    @WupFunc(servant = "liveui", value = IChannelInfoConstants.FuncName.GET_GAME_LIVEING_GAME_LIST)
    Observable<GetMobileGameListRsp> getGameLiveIngGameList(GetMobileGameListReq getMobileGameListReq);

    @WupFunc(servant = "liveui", value = IChannelInfoConstants.FuncName.GET_OPEN_RTMP_ADDR)
    Observable<OpenRtmpAddrRsp> getOpenRtmpAddr(OpenRtmpAddrReq openRtmpAddrReq);

    @WupFunc(servant = "presenterui", value = IChannelInfoConstants.FuncName.GET_PRESENTER_SIGN_INVITE)
    Observable<GetPresenterSignInviteRsp> getPresenterSignInvite(GetPresenterSignInviteReq getPresenterSignInviteReq);

    @WupFunc(servant = "mobileui", value = IChannelInfoConstants.FuncName.GET_ZHU_SHOU_LIVEING_GAME_LIST)
    Observable<ZhuShouLiveingGameListRsp> getZhuShouLiveingGameList(ZhuShouLiveingGameListReq zhuShouLiveingGameListReq);

    @WupFunc(servant = "liveui", value = IChannelInfoConstants.FuncName.OPEN_RTMP_END_LIVE)
    Observable<Object> openRtmpEndLive(OpenRtmpEndLiveReq openRtmpEndLiveReq);

    @WupFunc(servant = "liveui", value = IChannelInfoConstants.FuncName.QUERY_POPUP_DATA)
    Observable<PresenterNotifyPopupRsp> queryPopupData(PresenterNotifyPopupReq presenterNotifyPopupReq);

    @WupFunc(servant = "liveui", value = IChannelInfoConstants.FuncName.QUERY_ADDR)
    Observable<QueryAddrRsp> queryRtmpAddr(QueryAddrReq queryAddrReq);
}
