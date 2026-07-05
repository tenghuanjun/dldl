package com.huya.berry.gamesdk.module;

import com.duowan.HUYA.ComponentDistributeRsp;
import com.duowan.HUYA.GetPresenterVeriInfoRsp;
import com.duowan.HUYA.LiveAnnouncementFetchRsp;
import com.duowan.HUYA.LiveAnnouncementSettingRsp;
import com.duowan.HUYA.SendMessageRsp;
import com.duowan.HUYA.UserId;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import io.reactivex.Observable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ICommonService {
    Observable<ComponentDistributeRsp> getComponentDistribute(int i, UserId userId, String str);

    Observable<LiveAnnouncementFetchRsp> getMyLiveAnnouncement(UserId userId);

    Observable<GetPresenterVeriInfoRsp> getPresenterVeriInfo();

    void metricReport(CommonEvent.WupMetricReport wupMetricReport);

    Observable<SendMessageRsp> sendMessage(CommonEvent.SendPubText sendPubText);

    Observable<LiveAnnouncementSettingRsp> setMyLiveAnnouncement(String str, UserId userId);
}
