package com.huya.berry.module.live;

import com.duowan.HUYA.BeginLiveRsp;
import com.duowan.HUYA.ChangeLiveInfoRsp;
import com.duowan.HUYA.GetActiveEventInfoRsp;
import com.duowan.HUYA.GetConfigRsp;
import com.duowan.HUYA.GetLiveSummaryRsp;
import com.duowan.HUYA.GetLivingInfoRsp;
import com.duowan.HUYA.GetMobilePropsItemRsp;
import com.duowan.HUYA.GetMobilePropsListRsp;
import com.duowan.HUYA.GetRelationRsp;
import com.duowan.HUYA.ModRelationRsp;
import com.duowan.HUYA.SubScribeListUserRecItemRsp;
import com.duowan.HUYA.SwitchStatusRsp;
import com.duowan.HUYA.TransMsgToViewerRsp;
import com.duowan.HUYA.UserEventRsp;
import com.duowan.HUYA.UserHeartBeatRsp;
import com.duowan.HUYA.UserId;
import com.duowan.HUYA.UserRecListRsp;
import com.huya.berry.module.live.LiveInterface;
import io.reactivex.Observable;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ISdkLiveService {
    Observable<ModRelationRsp> addSubscribe(long j);

    Observable<BeginLiveRsp> beginLive(LiveInterface.StartLive startLive);

    Observable<ChangeLiveInfoRsp> changeLiveInfo(String str);

    Observable<ModRelationRsp> delSubscribe(long j);

    void endLive();

    Observable<GetActiveEventInfoRsp> getAnthorRecruitInfo(UserId userId, int i);

    Observable<GetConfigRsp> getConfig(Map<String, String> map);

    Observable<GetLiveSummaryRsp> getLiveSummary(long j, boolean z);

    Observable<GetLivingInfoRsp> getLivingInfo(long j, long j2, long j3, long j4);

    Observable<GetMobilePropsItemRsp> getMobilePropsItem(int i);

    Observable<GetMobilePropsListRsp> getMobilePropsList(int i, long j, long j2, long j3, int i2);

    Observable<UserRecListRsp> getRecListByGame(boolean z, int i, String str);

    Observable<GetRelationRsp> getRelation(long j);

    Observable<SubScribeListUserRecItemRsp> getUserSubscribeLiveList();

    Observable<SwitchStatusRsp> switchToGameTemplate();

    Observable<TransMsgToViewerRsp> transMsgToViewer(String str, int i);

    Observable<UserEventRsp> userEvent(long j, int i);

    Observable<UserHeartBeatRsp> userHeartBeat();
}
