package com.huya.berry.gamesdk.module;

import com.duowan.HUYA.BulletFormat;
import com.duowan.HUYA.ComponentDistributeReq;
import com.duowan.HUYA.ComponentDistributeRsp;
import com.duowan.HUYA.ContentFormat;
import com.duowan.HUYA.GetPresenterVeriInfoReq;
import com.duowan.HUYA.GetPresenterVeriInfoRsp;
import com.duowan.HUYA.LiveAnnouncementFetchReq;
import com.duowan.HUYA.LiveAnnouncementFetchRsp;
import com.duowan.HUYA.LiveAnnouncementSettingReq;
import com.duowan.HUYA.LiveAnnouncementSettingRsp;
import com.duowan.HUYA.SendMessageReq;
import com.duowan.HUYA.SendMessageRsp;
import com.duowan.HUYA.UserId;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.util.L;
import com.duowan.monitor.jce.Metric;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.live.common.api.BaseApi;
import com.huya.live.service.AbsService;
import com.huya.mtp.hyns.NS;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CommonService extends AbsService implements ICommonService {
    private static final String TAG = CommonService.class.getSimpleName();

    @Override // com.huya.live.service.AbsService
    public void onCreate() {
        ArkUtils.register(this);
    }

    @Override // com.huya.live.service.AbsService
    public void onStop() {
        ArkUtils.unregister(this);
    }

    @Override // com.huya.berry.gamesdk.module.ICommonService
    public Observable<LiveAnnouncementSettingRsp> setMyLiveAnnouncement(String str, UserId userId) {
        LiveAnnouncementSettingReq liveAnnouncementSettingReq = new LiveAnnouncementSettingReq();
        liveAnnouncementSettingReq.tId = userId;
        liveAnnouncementSettingReq.sLiveAnnouncement = str;
        return ((ICommonWup) NS.get(ICommonWup.class)).setMyLiveAnnouncement(liveAnnouncementSettingReq).doOnNext(new Consumer<LiveAnnouncementSettingRsp>() { // from class: com.huya.berry.gamesdk.module.CommonService.2
            @Override // io.reactivex.functions.Consumer
            public void accept(LiveAnnouncementSettingRsp liveAnnouncementSettingRsp) throws Exception {
                L.info(CommonService.TAG, "setMyLiveAnnouncement success");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.gamesdk.module.CommonService.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(CommonService.TAG, "setMyLiveAnnouncement error ");
            }
        });
    }

    @Override // com.huya.berry.gamesdk.module.ICommonService
    public Observable<LiveAnnouncementFetchRsp> getMyLiveAnnouncement(UserId userId) {
        return ((ICommonWup) NS.get(ICommonWup.class)).getMyLiveAnnouncement(new LiveAnnouncementFetchReq(userId, LoginApi.getUid())).doOnNext(new Consumer<LiveAnnouncementFetchRsp>() { // from class: com.huya.berry.gamesdk.module.CommonService.4
            @Override // io.reactivex.functions.Consumer
            public void accept(LiveAnnouncementFetchRsp liveAnnouncementFetchRsp) throws Exception {
                L.info(CommonService.TAG, "getMyLiveAnnouncement success");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.gamesdk.module.CommonService.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.info(CommonService.TAG, "getMyLiveAnnouncement fail");
            }
        });
    }

    @Override // com.huya.berry.gamesdk.module.ICommonService
    public Observable<ComponentDistributeRsp> getComponentDistribute(int i, UserId userId, String str) {
        return ((ICommonWup) NS.get(ICommonWup.class)).getComponentDistribute(new ComponentDistributeReq(6, str, userId, i)).doOnNext(new Consumer<ComponentDistributeRsp>() { // from class: com.huya.berry.gamesdk.module.CommonService.6
            @Override // io.reactivex.functions.Consumer
            public void accept(ComponentDistributeRsp componentDistributeRsp) throws Exception {
                L.info(CommonService.TAG, "ComponentDistributeRsp:" + componentDistributeRsp);
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.gamesdk.module.CommonService.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.info(CommonService.TAG, "resp.rsp.vComList:onError:");
            }
        });
    }

    @Override // com.huya.berry.gamesdk.module.ICommonService
    public Observable<SendMessageRsp> sendMessage(CommonEvent.SendPubText sendPubText) {
        sendPubText.mText.replace('\n', ' ');
        return ((ICommonWup) NS.get(ICommonWup.class)).sendMessage(constructMessage(sendPubText, -1, -1, 0)).doOnNext(new Consumer<SendMessageRsp>() { // from class: com.huya.berry.gamesdk.module.CommonService.8
            @Override // io.reactivex.functions.Consumer
            public void accept(SendMessageRsp sendMessageRsp) throws Exception {
                L.info(CommonService.TAG, "sendMessage: success" + sendMessageRsp);
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.gamesdk.module.CommonService.7
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.info(CommonService.TAG, "sendMessage fail");
            }
        });
    }

    private SendMessageReq constructMessage(CommonEvent.SendPubText sendPubText, int i, int i2, int i3) {
        String strReplace = sendPubText.mText.replace('\n', ' ');
        SendMessageReq sendMessageReq = new SendMessageReq();
        sendMessageReq.setSContent(strReplace);
        sendMessageReq.setTFormat(new ContentFormat(i, 4, 0, -1));
        sendMessageReq.setTBulletFormat(new BulletFormat(i2, 4, i3, 1, 0));
        sendMessageReq.setIShowMode(0);
        sendMessageReq.setLTid(sendPubText.channalId);
        sendMessageReq.setLSid(sendPubText.subId);
        sendMessageReq.setLPid(sendPubText.uid);
        sendMessageReq.setTUserId(sendPubText.userId);
        return sendMessageReq;
    }

    @Override // com.huya.berry.gamesdk.module.ICommonService
    public void metricReport(CommonEvent.WupMetricReport wupMetricReport) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (wupMetricReport.init > 0) {
            Metric metric = new Metric();
            metric.setSMetricName("berry.sdk.init");
            metric.setFValue(wupMetricReport.init);
            metric.setEUnit(15);
            metric.setITS(jCurrentTimeMillis);
            MonitorCenter.getInstance().request(metric);
        }
        if (wupMetricReport.login > 0) {
            Metric metric2 = new Metric();
            metric2.setSMetricName("berry.sdk.login");
            metric2.setFValue(wupMetricReport.login);
            metric2.setEUnit(15);
            metric2.setITS(jCurrentTimeMillis);
            MonitorCenter.getInstance().request(metric2);
        }
        if (wupMetricReport.startLive > 0) {
            Metric metric3 = new Metric();
            metric3.setSMetricName("berry.sdk.startLive");
            metric3.setFValue(wupMetricReport.startLive);
            metric3.setEUnit(15);
            metric3.setITS(jCurrentTimeMillis);
            MonitorCenter.getInstance().request(metric3);
        }
    }

    @Override // com.huya.berry.gamesdk.module.ICommonService
    public Observable<GetPresenterVeriInfoRsp> getPresenterVeriInfo() {
        return ((ICommonWup) NS.get(ICommonWup.class)).getPresenterVeriInfo(new GetPresenterVeriInfoReq(BaseApi.getUserId(), LoginProperties.uid.get().longValue())).doOnNext(new Consumer<GetPresenterVeriInfoRsp>() { // from class: com.huya.berry.gamesdk.module.CommonService.10
            @Override // io.reactivex.functions.Consumer
            public void accept(GetPresenterVeriInfoRsp getPresenterVeriInfoRsp) throws Exception {
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.gamesdk.module.CommonService.9
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
            }
        });
    }
}
