package com.huya.berry.module.live;

import android.os.Build;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.duowan.HUYA.BeginLiveNotice;
import com.duowan.HUYA.BeginLiveReq;
import com.duowan.HUYA.BeginLiveRsp;
import com.duowan.HUYA.ChangeLiveInfoReq;
import com.duowan.HUYA.ChangeLiveInfoRsp;
import com.duowan.HUYA.EClientTemplateType;
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
import com.duowan.HUYA.MultiStreamInfo;
import com.duowan.HUYA.ScreenType;
import com.duowan.HUYA.StreamInfo;
import com.duowan.HUYA.StreamSettingNotice;
import com.duowan.HUYA.SubScribeListUserRecItemRsp;
import com.duowan.HUYA.SubscribeToListReq;
import com.duowan.HUYA.SwitchStatusRsp;
import com.duowan.HUYA.SwitchToGameTemplateReq;
import com.duowan.HUYA.TransMsg;
import com.duowan.HUYA.TransMsgToViewerReq;
import com.duowan.HUYA.TransMsgToViewerRsp;
import com.duowan.HUYA.UserEventReq;
import com.duowan.HUYA.UserEventRsp;
import com.duowan.HUYA.UserHeartBeatReq;
import com.duowan.HUYA.UserHeartBeatRsp;
import com.duowan.HUYA.UserId;
import com.duowan.HUYA.UserRecListReq;
import com.duowan.HUYA.UserRecListRsp;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.http.v2.wup.WupError;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.Report;
import com.duowan.live.one.module.uploadLog.FeedBackInterface;
import com.duowan.networkmars.hysignal.HySignalProxy;
import com.duowan.taf.jce.JceStruct;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.AppStatusReportUtil;
import com.huya.berry.gamesdk.utils.AppUtils;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.EasyTimer;
import com.huya.berry.gamesdk.utils.PreferenceUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.Player.SMObject;
import com.huya.berry.module.data.LiveListTagInfo;
import com.huya.berry.module.help.LiveHelper;
import com.huya.berry.module.live.LiveInterface;
import com.huya.berry.module.props.PropsMgr;
import com.huya.berry.module.props.prop.PropItem;
import com.huya.berry.module.props.prop.PropStruct;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.user.UserProperties;
import com.huya.component.user.api.UserApi;
import com.huya.live.service.AbsService;
import com.huya.live.utils.heartbeat.BaseHeartBeat;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.hyns.api.NSRegisterApi;
import com.huya.mtp.utils.Base64;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sqwan.bugless.util.FileUtil;
import com.sqwan.liveshow.huya.SqR;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SdkLiveService extends AbsService implements ISdkLiveService {
    private static final String TAG = "LiveStream";
    private static final String UTF_8 = "UTF-8";
    private long iGameId;
    private boolean isWatch;
    private long lPresenterUid;
    private long mLiveTime;
    private PresenterHeartBeat mPresenterHearBeat;
    private EasyTimer mUserHeartBeatTimer;
    private byte[] vContext;
    private int mRegisterChanneTime = 0;
    private boolean mIsRegisterChannel = false;
    private ArrayList<PropItem> mComnPropList = new ArrayList<>();
    private boolean mTypeMirrorLoaded = false;
    private boolean mTypeHuyaLoaded = false;
    private String md5 = "'";

    private String nickName() {
        if (TextUtils.isEmpty(UserProperties.nickName.get())) {
            return ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_default_nick_name));
        }
        return UserProperties.nickName.get();
    }

    private String liveDesc() {
        if (TextUtils.isEmpty(SdkProperties.liveTitle.get())) {
            return ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_default_live_desc));
        }
        return SdkProperties.liveTitle.get();
    }

    @Override // com.huya.live.service.AbsService
    public void onCreate() {
        ArkUtils.register(this);
        this.mComnPropList.clear();
        this.mTypeHuyaLoaded = false;
        this.mTypeMirrorLoaded = false;
        getMobilePropsList(EClientTemplateType.TPL_MIRROR.value() | EClientTemplateType.TPL_HUYAAPP.value(), 0L, 0L, 0L, 0);
    }

    @Override // com.huya.live.service.AbsService
    public void onStop() {
        ArkUtils.unregister(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startUsertHeartBeatTimer() {
        if (this.mUserHeartBeatTimer != null) {
            stopUserHeartBeatTimer();
        } else {
            TaskExecutor.uiHandler().post(new Runnable() { // from class: com.huya.berry.module.live.SdkLiveService.1
                @Override // java.lang.Runnable
                public void run() {
                    L.info(SdkLiveService.TAG, "UserHeartBeat start");
                    SdkLiveService.this.mUserHeartBeatTimer = new EasyTimer();
                    SdkLiveService.this.mUserHeartBeatTimer.resetAndStart(60000, new Runnable() { // from class: com.huya.berry.module.live.SdkLiveService.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SdkLiveService.this.userHeartBeat();
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopUserHeartBeatTimer() {
        if (this.mUserHeartBeatTimer != null) {
            L.info(TAG, "UserHeartBeat stop");
            this.mUserHeartBeatTimer.stop();
            this.mUserHeartBeatTimer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRegisterChannelHeartBeat() {
        if (this.mIsRegisterChannel || this.mRegisterChanneTime >= 3) {
            return;
        }
        L.error(TAG, "Mars:进频道重连...");
        this.mRegisterChanneTime++;
        registerBroadcastByChannel();
    }

    private Map<String, String> getReportParams() {
        HashMap map = new HashMap();
        map.put("DeviceName", Build.MODEL);
        map.put("Network", AppStatusReportUtil.getNetworkConnectionName());
        map.put("Baseband", Build.VERSION.INCREMENTAL);
        map.put("TimeZone", TimeZone.getDefault().getID());
        map.put("SystemVersion", Build.VERSION.RELEASE);
        map.put("AppVersion", AppUtils.getVersion());
        map.put("HUYA_MAIXU", "2");
        map.put("HuyaAudioACQEnable", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopHeartBeat() {
        PresenterHeartBeat presenterHeartBeat = this.mPresenterHearBeat;
        if (presenterHeartBeat != null) {
            presenterHeartBeat.stopHeartBeat();
        }
    }

    private void sendFeedback() {
        this.mLiveTime = System.currentTimeMillis() - this.mLiveTime;
        ArkUtils.send(new FeedBackInterface.AddFeedBack("结束开播自动反馈", String.format(Locale.CHINA, "%s[sid:%d|subid:%d|resolution:%d|land:%b|net:%s|time:%d|v:%s]", UserProperties.nickName.get(), LoginProperties.uid.get(), LoginProperties.uid.get(), SdkProperties.resolution.get(), Boolean.valueOf(CommonUtil.isScreenLandScape()), AppStatusReportUtil.getNetworkConnectionName(), Long.valueOf(this.mLiveTime / 1000), WupHelper.getVersion())));
    }

    private void registerBroadcastByChannel() {
        HySignalProxy.getInstance().registerLiveGroups(LoginApi.getUid(), new NSRegisterApi.RegisterPushMsgListener() { // from class: com.huya.berry.module.live.SdkLiveService.2
            @Override // com.huya.mtp.hyns.api.NSRegisterApi.RegisterPushMsgListener
            public void onRegisterSucceed(NSRegisterApi.RegistResultInfo registResultInfo) {
                L.info(SdkLiveService.TAG, "Mars:onRegisterSucceed ...");
                SdkLiveService.this.mIsRegisterChannel = true;
            }

            @Override // com.huya.mtp.hyns.api.NSRegisterApi.RegisterPushMsgListener
            public void onRegisterFailed(NSRegisterApi.RegistResultInfo registResultInfo) {
                L.error(SdkLiveService.TAG, "Mars:onRegisterFailed %d", Integer.valueOf(registResultInfo.getStatus()));
                SdkLiveService.this.mIsRegisterChannel = false;
            }
        });
    }

    private void unregisterBroadcastByChannel() {
        HySignalProxy.getInstance().unRegisterLiveGroups(LoginApi.getUid());
        this.mIsRegisterChannel = false;
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<UserHeartBeatRsp> userHeartBeat() {
        UserHeartBeatReq userHeartBeatReq = new UserHeartBeatReq();
        userHeartBeatReq.tId = LiveHelper.getUserId();
        userHeartBeatReq.lPid = this.isWatch ? PlayerHelper.presenterUid : LoginApi.getUid();
        userHeartBeatReq.lSid = LoginProperties.uid.get().longValue();
        userHeartBeatReq.lTid = LoginProperties.uid.get().longValue();
        userHeartBeatReq.bWatchVideo = this.isWatch;
        return ((ILiveWup) NS.get(ILiveWup.class)).userHeartBeat(userHeartBeatReq).doOnNext(new Consumer<UserHeartBeatRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.4
            @Override // io.reactivex.functions.Consumer
            public void accept(UserHeartBeatRsp userHeartBeatRsp) throws Exception {
                L.info(SdkLiveService.TAG, "UserHeartBeat success");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.info(SdkLiveService.TAG, "UserHeartBeat fail");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<UserEventRsp> userEvent(long j, final int i) {
        UserEventReq userEventReq = new UserEventReq();
        userEventReq.tId = LiveHelper.getUserId();
        userEventReq.lPid = j;
        userEventReq.lSid = LoginProperties.uid.get().longValue();
        userEventReq.lTid = LoginProperties.uid.get().longValue();
        userEventReq.eOp = i;
        userEventReq.sChan = WupHelper.getSHuYaUA();
        userEventReq.eSource = 2;
        userEventReq.bWatchVideo = true;
        userEventReq.bAnonymous = LoginProperties.uid.get().longValue() <= 0;
        return ((ILiveWup) NS.get(ILiveWup.class)).userEvent(userEventReq).doOnNext(new Consumer<UserEventRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.6
            @Override // io.reactivex.functions.Consumer
            public void accept(UserEventRsp userEventRsp) throws Exception {
                L.info(SdkLiveService.TAG, "userEvent success");
                if (i == 1) {
                    SdkLiveService.this.isWatch = true;
                    SdkLiveService.this.startUsertHeartBeatTimer();
                } else {
                    SdkLiveService.this.stopUserHeartBeatTimer();
                }
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.info(SdkLiveService.TAG, "userEvent fail");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<BeginLiveRsp> beginLive(LiveInterface.StartLive startLive) {
        this.mLiveTime = System.currentTimeMillis();
        registerBroadcastByChannel();
        BeginLiveReq beginLiveReq = new BeginLiveReq();
        UserId userId = LiveHelper.getUserId();
        userId.setLUid(LoginApi.getUid());
        userId.setSToken(LoginApi.getDefaultToken().getToken());
        userId.setITokenType(LoginApi.getDefaultToken().getTokenType());
        beginLiveReq.setTId(userId);
        beginLiveReq.setSNickName(nickName());
        beginLiveReq.setSLiveDesc(liveDesc());
        beginLiveReq.setLTopSid(LoginProperties.uid.get().longValue());
        beginLiveReq.setLSubSid(LoginProperties.uid.get().longValue());
        beginLiveReq.setIGameId(startLive.gameId);
        beginLiveReq.setINewGameId(startLive.gameId);
        beginLiveReq.setIResolution(Math.max(startLive.width, startLive.height));
        beginLiveReq.setIBandWidth(startLive.bitrateKbps);
        beginLiveReq.setIBitRate(startLive.bitrateKbps);
        beginLiveReq.setIFrameRate(startLive.frameRate);
        beginLiveReq.setISourceType(6);
        beginLiveReq.setICodecType(0);
        beginLiveReq.setIScreenType(SdkProperties.isLandscape.get().booleanValue() ? 1 : 0);
        beginLiveReq.setIPopupFlags(1);
        beginLiveReq.setIIsCdnSupport(1);
        beginLiveReq.setMMiscInfo(getReportParams());
        L.info(TAG, "BeginLive req=" + beginLiveReq);
        return ((ILiveWup) NS.get(ILiveWup.class)).beginLive(beginLiveReq).doOnNext(new Consumer<BeginLiveRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.8
            @Override // io.reactivex.functions.Consumer
            public void accept(BeginLiveRsp beginLiveRsp) throws Exception {
                L.info(SdkLiveService.TAG, "BeginLive success");
                SdkProperties.liveId.set(Long.valueOf(beginLiveRsp.getLLiveId()));
                PreferenceUtil.setLiveId(beginLiveRsp.getLLiveId());
                SdkProperties.multiStreamFlag.set(Long.valueOf(beginLiveRsp.getLMultiStreamFlag()));
                SdkLiveService.this.mPresenterHearBeat = new PresenterHeartBeat(LiveHelper.getUserId(), beginLiveRsp.getIHeartbeatInterval(), new BaseHeartBeat.HeartBeatListener() { // from class: com.huya.berry.module.live.SdkLiveService.8.1
                    @Override // com.huya.live.utils.heartbeat.BaseHeartBeat.HeartBeatListener
                    public void onHeartBeatError(VolleyError volleyError) {
                        if (volleyError instanceof WupError) {
                            int i = ((WupError) volleyError).mCode;
                        }
                    }

                    @Override // com.huya.live.utils.heartbeat.BaseHeartBeat.HeartBeatListener
                    public void afterHeartBeat() {
                        SdkLiveService.this.onRegisterChannelHeartBeat();
                    }
                });
                SdkLiveService.this.mPresenterHearBeat.startHeartBeat();
                SdkLiveService.this.isWatch = false;
                SdkLiveService.this.startUsertHeartBeatTimer();
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.7
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "BeginLive error");
                SdkLiveService.this.stopHeartBeat();
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public void endLive() {
        sendFeedback();
        SdkProperties.isLiving.set(false);
        stopUserHeartBeatTimer();
        unregisterBroadcastByChannel();
        EndLiveReq endLiveReq = new EndLiveReq();
        endLiveReq.setTId(LiveHelper.getUserId());
        endLiveReq.setIReason(0);
        endLiveReq.setLLiveId(SdkProperties.liveId.get().longValue());
        new EndLiveFunction(endLiveReq) { // from class: com.huya.berry.module.live.SdkLiveService.9
            @Override // com.huya.berry.module.live.EndLiveFunction, com.duowan.auk.http.v2.Function, com.duowan.auk.http.v2.ResponseListener
            public void onError(VolleyError volleyError) {
                super.onError(volleyError);
                SdkLiveService.this.stopHeartBeat();
                L.error("endLive", (Throwable) volleyError);
            }

            @Override // com.huya.berry.module.live.EndLiveFunction, com.duowan.auk.http.v2.Function, com.duowan.auk.http.v2.ResponseListener
            public void onResponse(JceStruct jceStruct, boolean z) {
                super.onResponse(jceStruct, z);
                ArkValue.gMainHandler.post(new Runnable() { // from class: com.huya.berry.module.live.SdkLiveService.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SdkLiveService.this.stopHeartBeat();
                    }
                });
            }
        }.execute();
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<GetConfigRsp> getConfig(Map<String, String> map) {
        GetConfigReq getConfigReq = new GetConfigReq();
        getConfigReq.tId = LiveHelper.getUserId();
        getConfigReq.mpVariable = map;
        return ((ILiveWup) NS.get(ILiveWup.class)).getConfig(getConfigReq).doOnNext(new Consumer<GetConfigRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.11
            @Override // io.reactivex.functions.Consumer
            public void accept(GetConfigRsp getConfigRsp) throws Exception {
                if (getConfigRsp == null) {
                    L.info(SdkLiveService.TAG, "getConfig response == null");
                }
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.10
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "getConfig error");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<GetLiveSummaryRsp> getLiveSummary(long j, boolean z) {
        GetLiveSummaryReq getLiveSummaryReq = new GetLiveSummaryReq();
        getLiveSummaryReq.tId = LiveHelper.getUserId();
        getLiveSummaryReq.lLiveId = j;
        return ((ILiveWup) NS.get(ILiveWup.class)).getLiveSummary(getLiveSummaryReq).doOnNext(new Consumer<GetLiveSummaryRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.13
            @Override // io.reactivex.functions.Consumer
            public void accept(GetLiveSummaryRsp getLiveSummaryRsp) throws Exception {
                L.info(SdkLiveService.TAG, "getLiveSummary, resp=" + getLiveSummaryRsp.toString());
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.12
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "--------getLiveSummary error");
                Report.event(SdkReportConst.STATUS_ERROR_NOENDDATA_PRE + SdkProperties.gameId.get());
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<TransMsgToViewerRsp> transMsgToViewer(String str, int i) {
        return ((ILiveWup) NS.get(ILiveWup.class)).transMsgToViewer(new TransMsgToViewerReq(LiveHelper.getUserId(), new TransMsg(str, i, null), LoginProperties.uid.get().longValue(), LoginProperties.uid.get().longValue())).doOnNext(new Consumer<TransMsgToViewerRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.15
            @Override // io.reactivex.functions.Consumer
            public void accept(TransMsgToViewerRsp transMsgToViewerRsp) throws Exception {
                if (transMsgToViewerRsp == null) {
                    L.error(SdkLiveService.TAG, "transMsgToViewer response is null");
                    return;
                }
                L.info(SdkLiveService.TAG, "transMsgToViewer, resp=" + transMsgToViewerRsp.toString());
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.14
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "--------transMsgToViewer error");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<SwitchStatusRsp> switchToGameTemplate() {
        return ((ILiveWup) NS.get(ILiveWup.class)).switchToGameTemplate(new SwitchToGameTemplateReq(LiveHelper.getUserId(), LoginProperties.uid.get().longValue(), LoginProperties.uid.get().longValue())).doOnNext(new Consumer<SwitchStatusRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.17
            @Override // io.reactivex.functions.Consumer
            public void accept(SwitchStatusRsp switchStatusRsp) throws Exception {
                if (switchStatusRsp == null) {
                    L.error(SdkLiveService.TAG, "--------switchToGameTemplate response == null");
                    return;
                }
                L.info(SdkLiveService.TAG, "response iRetCode:" + switchStatusRsp.iRetCode + ",sRetDesc:" + switchStatusRsp.sRetDesc);
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.16
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "--------switchToGameTemplate error");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<GetLivingInfoRsp> getLivingInfo(long j, long j2, long j3, long j4) {
        UserId userId = new UserId();
        userId.setLUid(LoginApi.getUid());
        userId.setSGuid(UserApi.getGUID());
        userId.setSHuYaUA(WupHelper.getSHuYaUA());
        userId.setSToken("");
        GetLivingInfoReq getLivingInfoReq = new GetLivingInfoReq();
        getLivingInfoReq.setTId(userId);
        if (j4 > 0) {
            getLivingInfoReq.setIRoomId(j4);
        } else {
            getLivingInfoReq.setLTopSid(j);
            getLivingInfoReq.setLSubSid(j2);
            getLivingInfoReq.setLPresenterUid(j3);
        }
        return ((ILiveWup) NS.get(ILiveWup.class)).getLivingInfo(getLivingInfoReq).doOnNext(new Consumer<GetLivingInfoRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.19
            @Override // io.reactivex.functions.Consumer
            public void accept(GetLivingInfoRsp getLivingInfoRsp) throws Exception {
                L.error(SdkLiveService.TAG, "GetLivingInfo success ");
                SdkLiveService.this.parseGetLivingInfoRsp(getLivingInfoRsp);
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.18
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "GetLivingInfo error ");
            }
        });
    }

    public static String decodeData(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new String(Base64.decode(str.getBytes("UTF-8")), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            L.error(TAG, str);
            return null;
        }
    }

    private String getSAntiCode(StreamInfo streamInfo, String str) {
        String[] strArrSplit = str.split("&");
        String str2 = "";
        String str3 = str2;
        String str4 = str3;
        for (int i = 0; i < strArrSplit.length; i++) {
            if (strArrSplit[i].indexOf("fm") != -1) {
                str2 = strArrSplit[i].split(SimpleComparison.EQUAL_TO_OPERATION)[1];
            } else if (strArrSplit[i].indexOf("wsSecret") != -1) {
                String str5 = strArrSplit[i].split(SimpleComparison.EQUAL_TO_OPERATION)[1];
            } else if (strArrSplit[i].indexOf("wsTime") != -1) {
                str3 = strArrSplit[i].split(SimpleComparison.EQUAL_TO_OPERATION)[1];
            } else {
                str4 = str4 + "&" + strArrSplit[i];
            }
        }
        String[] strArrSplit2 = decodeData(URLDecoder.decode(str2)).split("_");
        String str6 = System.currentTimeMillis() + "" + (((int) Math.random()) * 10000);
        return "wsSecret=" + AppUtils.md5(strArrSplit2[0] + "_" + LoginProperties.uid.get() + "_" + streamInfo.getSStreamName() + "_" + str6 + "_" + str3) + "&wsTime=" + str3 + "&u=" + LoginProperties.uid.get() + "&seqid=" + str6 + str4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseGetLivingInfoRsp(GetLivingInfoRsp getLivingInfoRsp) {
        boolean z;
        String str;
        Vector<SMObject.BitRateInfo> vector;
        Iterator<StreamInfo> it;
        StreamSettingNotice tStreamSettingNotice = getLivingInfoRsp.getTStreamSettingNotice();
        BeginLiveNotice tNotice = getLivingInfoRsp.getTNotice();
        PlayerHelper.roomId = tNotice.iRoomId;
        int i = tNotice.iRoomId;
        String str2 = TAG;
        if (i <= 0) {
            L.info(TAG, getLivingInfoRsp.toString());
            return;
        }
        ScreenType screenType = ScreenType.ST_Horizonal;
        if ((tNotice.iScreenType == 0 && (tNotice.iSourceType == 6 || tNotice.iSourceType == 2)) || tNotice.iSourceType == 11) {
            screenType = ScreenType.ST_Vertical;
        }
        PlayerHelper.mScreenType = screenType;
        PlayerHelper.gameName = tNotice.sGameName;
        PlayerHelper.title = tNotice.sLiveDesc;
        PlayerHelper.liveAnchorName = tNotice.sNick;
        PlayerHelper.liveAnchorAvatar = tNotice.sAvatarUrl;
        StringBuilder sb = new StringBuilder();
        sb.append(tNotice.lAttendeeCount);
        String str3 = "";
        sb.append("");
        PlayerHelper.audienceCount = sb.toString();
        PlayerHelper.presenterUid = tNotice.lPresenterUid;
        SMObject.StreamInfoList streamInfoList = new SMObject.StreamInfoList();
        streamInfoList.streamInfoList = new HashMap();
        PlayerHelper.singleStreamInfo = new SMObject.SingleStreamInfo();
        PlayerHelper.singleStreamInfo.singleInfo = new HashMap();
        PlayerHelper.singleStreamInfo.uid = tNotice.getLPresenterUid();
        Vector<SMObject.BitRateInfo> vector2 = new Vector<>();
        int i2 = tStreamSettingNotice.iBitRate;
        PlayerHelper.defaultBitRate = i2;
        loop0: while (true) {
            boolean z2 = false;
            for (MultiStreamInfo multiStreamInfo : tNotice.getVMultiStreamInfo()) {
                Iterator<SMObject.BitRateInfo> it2 = vector2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    SMObject.BitRateInfo next = it2.next();
                    if (next != null && next.disPlayName.equals(multiStreamInfo.sDisplayName)) {
                        z2 = true;
                        break;
                    }
                }
                if (z2) {
                    break;
                }
                SMObject.BitRateInfo bitRateInfo = new SMObject.BitRateInfo();
                bitRateInfo.disPlayName = multiStreamInfo.sDisplayName;
                bitRateInfo.H264BitRate = multiStreamInfo.iBitRate;
                bitRateInfo.H265BitRate = multiStreamInfo.iHEVCBitRate;
                if (bitRateInfo.H264BitRate == 0 && bitRateInfo.H265BitRate == -1) {
                    str3 = bitRateInfo.disPlayName;
                }
                if (bitRateInfo.H264BitRate > i2) {
                    i2 = bitRateInfo.H264BitRate;
                    str3 = bitRateInfo.disPlayName;
                }
                if (bitRateInfo.H265BitRate > i2) {
                    i2 = bitRateInfo.H265BitRate;
                    str3 = bitRateInfo.disPlayName;
                }
                if (bitRateInfo.H265BitRate == -1 && bitRateInfo.H264BitRate == 0) {
                    bitRateInfo.H264BitRate = i2;
                }
                vector2.add(bitRateInfo);
            }
        }
        Iterator<StreamInfo> it3 = tNotice.getVStreamInfo().iterator();
        boolean z3 = false;
        while (it3.hasNext()) {
            StreamInfo next2 = it3.next();
            SMObject.LineInfo lineInfo = new SMObject.LineInfo();
            lineInfo.bitRateInfoList = vector2;
            lineInfo.streamTypeList = new Vector<>();
            int iLineIndex = next2.getILineIndex();
            if (iLineIndex == 4) {
                lineInfo.streamTypeList.add(3);
                str = str2;
                vector = vector2;
                it = it3;
            } else {
                if (next2.getSFlvUrl().length() > 0) {
                    it = it3;
                    lineInfo.streamTypeList.add(1);
                    Map<Integer, String> map = PlayerHelper.FlvUrl;
                    Integer numValueOf = Integer.valueOf(iLineIndex);
                    str = str2;
                    StringBuilder sb2 = new StringBuilder();
                    vector = vector2;
                    sb2.append(next2.getSFlvUrl());
                    sb2.append("/");
                    sb2.append(next2.getSStreamName());
                    sb2.append(FileUtil.FILE_EXTENSION_SEPARATOR);
                    sb2.append(next2.getSFlvUrlSuffix());
                    sb2.append("?");
                    sb2.append(getSAntiCode(next2, next2.getSFlvAntiCode()));
                    map.put(numValueOf, sb2.toString());
                } else {
                    str = str2;
                    vector = vector2;
                    it = it3;
                }
                if (next2.getSHlsUrl().length() > 0) {
                    lineInfo.streamTypeList.add(5);
                    PlayerHelper.HlsUrl.put(Integer.valueOf(iLineIndex), next2.getSHlsUrl() + "/" + next2.getSStreamName() + FileUtil.FILE_EXTENSION_SEPARATOR + next2.getSHlsUrlSuffix() + "?" + getSAntiCode(next2, next2.getSHlsAntiCode()));
                }
                if (next2.getSP2pUrl().length() > 0) {
                    lineInfo.streamTypeList.add(2);
                    PlayerHelper.P2PUrl.put(Integer.valueOf(iLineIndex), next2.getSP2pUrl() + "/" + next2.getSStreamName() + FileUtil.FILE_EXTENSION_SEPARATOR + next2.getSP2pUrlSuffix() + "?uid=" + PlayerHelper.presenterUid + "&uuid=0&" + next2.getSP2pAntiCode());
                }
            }
            PlayerHelper.streamNames.put(Integer.valueOf(iLineIndex), next2.sStreamName);
            if (PlayerHelper.line == 0) {
                PlayerHelper.line = iLineIndex;
                PlayerHelper.streamName = next2.sStreamName;
            }
            if (PlayerHelper.line == iLineIndex) {
                z3 = true;
            }
            PlayerHelper.singleStreamInfo.singleInfo.put(Integer.valueOf(iLineIndex), lineInfo);
            streamInfoList.streamInfoList.put(next2.getSStreamName(), PlayerHelper.singleStreamInfo);
            it3 = it;
            str2 = str;
            vector2 = vector;
        }
        String str4 = str2;
        Vector<SMObject.BitRateInfo> vector3 = vector2;
        if (z3 || tNotice.getVStreamInfo() == null || tNotice.getVStreamInfo().size() <= 0) {
            z = false;
        } else {
            z = false;
            StreamInfo streamInfo = tNotice.getVStreamInfo().get(0);
            PlayerHelper.line = streamInfo.iLineIndex;
            PlayerHelper.streamName = streamInfo.sStreamName;
        }
        if (PlayerHelper.bitRate == 0) {
            PlayerHelper.bitRate = 2500;
            PlayerHelper.disPlayName = "超清";
        }
        if (i2 > 0 && i2 <= 2500) {
            PlayerHelper.bitRate = i2;
            PlayerHelper.disPlayName = str3;
        }
        boolean z4 = false;
        int i3 = 0;
        while (i3 < vector3.size()) {
            Vector<SMObject.BitRateInfo> vector4 = vector3;
            SMObject.BitRateInfo bitRateInfo2 = vector4.get(i3);
            if (bitRateInfo2.disPlayName.equals(PlayerHelper.disPlayName)) {
                PlayerHelper.bitRate = bitRateInfo2.H264BitRate > 0 ? bitRateInfo2.H264BitRate : bitRateInfo2.H265BitRate;
                z = true;
            }
            if (bitRateInfo2.disPlayName.equals("超清")) {
                z4 = true;
            }
            i3++;
            vector3 = vector4;
        }
        if (!z && i2 > 0) {
            if (z4) {
                PlayerHelper.bitRate = 2500;
                PlayerHelper.disPlayName = "超清";
            } else {
                PlayerHelper.bitRate = i2;
                PlayerHelper.disPlayName = str3;
            }
        }
        L.info(str4, getLivingInfoRsp.toString());
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<ChangeLiveInfoRsp> changeLiveInfo(final String str) {
        ChangeLiveInfoReq changeLiveInfoReq = new ChangeLiveInfoReq();
        changeLiveInfoReq.tId = LiveHelper.getUserId();
        changeLiveInfoReq.sLiveDesc = str;
        return ((ILiveWup) NS.get(ILiveWup.class)).changeLiveInfo(changeLiveInfoReq).doOnNext(new Consumer<ChangeLiveInfoRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.21
            @Override // io.reactivex.functions.Consumer
            public void accept(ChangeLiveInfoRsp changeLiveInfoRsp) throws Exception {
                L.info(SdkLiveService.TAG, "changeLiveInfo success");
                SdkProperties.liveTitle.set(str);
                PreferenceUtil.setLiveTitle(str);
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.20
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "changeLiveInfo error ");
                if (th instanceof WupError) {
                    L.error(SdkLiveService.TAG, "changeLiveInfo error " + ((ChangeLiveInfoRsp) ((WupError) th).mResponse).sMessage);
                }
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<GetMobilePropsListRsp> getMobilePropsList(final int i, final long j, long j2, long j3, final int i2) {
        GetMobilePropsListReq getMobilePropsListReq = new GetMobilePropsListReq();
        getMobilePropsListReq.setTUserId(LiveHelper.getUserId());
        getMobilePropsListReq.setITemplateType(i);
        getMobilePropsListReq.setIAppId(1);
        getMobilePropsListReq.setSVersion(WupHelper.getVersion());
        getMobilePropsListReq.setLPresenterUid(j);
        getMobilePropsListReq.setLSid(j2);
        getMobilePropsListReq.setLSubSid(j3);
        getMobilePropsListReq.setIGameId(i2);
        getMobilePropsListReq.setSMd5("");
        return ((ILiveWup) NS.get(ILiveWup.class)).getMobilePropsList(getMobilePropsListReq).doOnNext(new Consumer<GetMobilePropsListRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.23
            @Override // io.reactivex.functions.Consumer
            public void accept(GetMobilePropsListRsp getMobilePropsListRsp) throws Exception {
                boolean z;
                if (getMobilePropsListRsp != null && getMobilePropsListRsp.getVPropsItemList() != null) {
                    if (!getMobilePropsListRsp.getSMd5().equals(SdkLiveService.this.md5) || j != SdkLiveService.this.lPresenterUid || SdkLiveService.this.iGameId != i2) {
                        SdkLiveService.this.md5 = getMobilePropsListRsp.getSMd5();
                        long j4 = j;
                        if (j4 == 0 || i2 == 0) {
                            z = false;
                        } else {
                            SdkLiveService.this.lPresenterUid = j4;
                            SdkLiveService.this.iGameId = i2;
                            z = true;
                        }
                        List<PropItem> mobileProps = PropStruct.parseMobileProps(SdkLiveService.this.mComnPropList, getMobilePropsListRsp.getVPropsItemList(), z);
                        if ((i & 2) == 2) {
                            SdkLiveService.this.mTypeHuyaLoaded = true;
                        }
                        if ((i & 1) == 1) {
                            SdkLiveService.this.mTypeMirrorLoaded = true;
                        }
                        if (z) {
                            SdkLiveService.this.tryResetProps(mobileProps, j, i2);
                            return;
                        }
                        SdkLiveService.this.mComnPropList.addAll(mobileProps);
                        L.info(SdkLiveService.TAG, "GetMobilePropsList success==propList size:%d", Integer.valueOf(mobileProps.size()));
                        SdkLiveService sdkLiveService = SdkLiveService.this;
                        sdkLiveService.tryResetProps(sdkLiveService.mComnPropList, j, i2);
                        return;
                    }
                    L.info(SdkLiveService.TAG, "GetMobilePropsList same end...");
                    return;
                }
                L.error(SdkLiveService.TAG, "wup prop struct null");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.22
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                if (th instanceof WupError) {
                    L.error(SdkLiveService.TAG, "getMobilePropsList error " + ((ChangeLiveInfoRsp) ((WupError) th).mResponse).sMessage);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryResetProps(List<PropItem> list, long j, int i) {
        boolean z;
        if (this.mTypeMirrorLoaded && this.mTypeHuyaLoaded) {
            if (j == 0 || i == 0) {
                PropsMgr.instance().setComnPropLoaded(true);
                z = true;
            } else {
                z = false;
            }
            List<PropItem> arrayList = new ArrayList<>();
            HashSet hashSet = new HashSet();
            hashSet.addAll(list);
            arrayList.addAll(hashSet);
            updateProps(arrayList, z, j, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProps(List<PropItem> list, boolean z, long j, int i) {
        if (list.isEmpty()) {
            L.warn(TAG, "parse props empty");
            return;
        }
        if (z) {
            Collections.sort(list);
            PropsMgr.instance().clearActiveProps();
        }
        Iterator<PropItem> it = list.iterator();
        while (it.hasNext()) {
            PropsMgr.instance().addActiveProp(it.next(), j, i);
        }
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<GetMobilePropsItemRsp> getMobilePropsItem(int i) {
        GetMobilePropsItemReq getMobilePropsItemReq = new GetMobilePropsItemReq();
        getMobilePropsItemReq.setTUserId(LiveHelper.getUserId());
        getMobilePropsItemReq.setLPropId(i);
        return ((ILiveWup) NS.get(ILiveWup.class)).getMobilePropsItem(getMobilePropsItemReq).doOnNext(new Consumer<GetMobilePropsItemRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.25
            @Override // io.reactivex.functions.Consumer
            public void accept(GetMobilePropsItemRsp getMobilePropsItemRsp) throws Exception {
                L.info(SdkLiveService.TAG, "getMobilePropsItem success");
                if (getMobilePropsItemRsp != null && getMobilePropsItemRsp.getTPropsItem() != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(getMobilePropsItemRsp.getTPropsItem());
                    SdkLiveService.this.updateProps(PropStruct.parseMobileProps(arrayList), false, 0L, 0);
                    return;
                }
                L.error(SdkLiveService.TAG, "wup prop struct null");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.24
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "getMobilePropsItem error ");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<ModRelationRsp> addSubscribe(long j) {
        ModRelationReq modRelationReq = new ModRelationReq();
        modRelationReq.setTId(LiveHelper.getUserId());
        modRelationReq.setLUid(j);
        modRelationReq.setIOp(1);
        modRelationReq.setSSource("Live");
        return ((ILiveWup) NS.get(ILiveWup.class)).addSubscribe(modRelationReq).doOnNext(new Consumer<ModRelationRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.27
            @Override // io.reactivex.functions.Consumer
            public void accept(ModRelationRsp modRelationRsp) throws Exception {
                L.info(SdkLiveService.TAG, "addSubscribe success");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.26
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "addSubscribe error ");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<ModRelationRsp> delSubscribe(long j) {
        ModRelationReq modRelationReq = new ModRelationReq();
        modRelationReq.setTId(LiveHelper.getUserId());
        modRelationReq.setLUid(j);
        modRelationReq.setIOp(2);
        modRelationReq.setSSource("Live");
        return ((ILiveWup) NS.get(ILiveWup.class)).delSubscribe(modRelationReq).doOnNext(new Consumer<ModRelationRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.29
            @Override // io.reactivex.functions.Consumer
            public void accept(ModRelationRsp modRelationRsp) throws Exception {
                L.info(SdkLiveService.TAG, "delSubscribe success");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.28
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "delSubscribe error ");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<GetRelationRsp> getRelation(long j) {
        GetRelationReq getRelationReq = new GetRelationReq();
        getRelationReq.tId = LiveHelper.getUserId();
        getRelationReq.lUid = j;
        return ((ILiveWup) NS.get(ILiveWup.class)).getRelation(getRelationReq).doOnNext(new Consumer<GetRelationRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.31
            @Override // io.reactivex.functions.Consumer
            public void accept(GetRelationRsp getRelationRsp) throws Exception {
                L.info(SdkLiveService.TAG, "getRelation success");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.30
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "getRelation error ");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<SubScribeListUserRecItemRsp> getUserSubscribeLiveList() {
        SubscribeToListReq subscribeToListReq = new SubscribeToListReq();
        subscribeToListReq.tId = LiveHelper.getUserId();
        return ((ILiveWup) NS.get(ILiveWup.class)).getUserSubscribeLiveList(subscribeToListReq).doOnNext(new Consumer<SubScribeListUserRecItemRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.33
            @Override // io.reactivex.functions.Consumer
            public void accept(SubScribeListUserRecItemRsp subScribeListUserRecItemRsp) throws Exception {
                L.info(SdkLiveService.TAG, "getUserSubscribeLiveList success");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.32
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.error(SdkLiveService.TAG, "getUserSubscribeLiveList error ");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<GetActiveEventInfoRsp> getAnthorRecruitInfo(UserId userId, int i) {
        GetActiveEventInfoReq getActiveEventInfoReq = new GetActiveEventInfoReq();
        getActiveEventInfoReq.tId = userId;
        getActiveEventInfoReq.lUid = LoginApi.getUid();
        getActiveEventInfoReq.iType = -7;
        getActiveEventInfoReq.iOrderType = 0;
        getActiveEventInfoReq.iCurGameID = i;
        return ((ILiveWup) NS.get(ILiveWup.class)).getAnthorRecruitInfo(getActiveEventInfoReq).doOnNext(new Consumer<GetActiveEventInfoRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.35
            @Override // io.reactivex.functions.Consumer
            public void accept(GetActiveEventInfoRsp getActiveEventInfoRsp) throws Exception {
                L.error(SdkLiveService.TAG, "getAnthorRecruitInfo success");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.34
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.info(SdkLiveService.TAG, "getAnthorRecruitInfo fail");
            }
        });
    }

    @Override // com.huya.berry.module.live.ISdkLiveService
    public Observable<UserRecListRsp> getRecListByGame(boolean z, int i, String str) {
        UserRecListReq userRecListReq = new UserRecListReq();
        userRecListReq.tId = LiveHelper.getSpecialUserId();
        userRecListReq.vContext = z ? null : this.vContext;
        userRecListReq.iGameId = i;
        userRecListReq.iContentType = 1;
        if (!TextUtils.isEmpty(str)) {
            userRecListReq.sFilterTagId = str;
        }
        return ((ILiveWup) NS.get(ILiveWup.class)).getRecListByGame(userRecListReq).doOnNext(new Consumer<UserRecListRsp>() { // from class: com.huya.berry.module.live.SdkLiveService.37
            @Override // io.reactivex.functions.Consumer
            public void accept(UserRecListRsp userRecListRsp) throws Exception {
                L.info(SdkLiveService.TAG, "getRecListByGame success");
                if (userRecListRsp == null || userRecListRsp.vItems == null) {
                    return;
                }
                ArrayList<LiveListTagInfo> arrayList = new ArrayList<>();
                for (int i2 = 0; i2 < userRecListRsp.vChildFilterTags.size(); i2++) {
                    LiveListTagInfo liveListTagInfo = new LiveListTagInfo();
                    liveListTagInfo.id = userRecListRsp.vChildFilterTags.get(i2).sId;
                    liveListTagInfo.name = userRecListRsp.vChildFilterTags.get(i2).sName;
                    liveListTagInfo.parentTagId = userRecListRsp.vChildFilterTags.get(i2).sParentTagId;
                    arrayList.add(liveListTagInfo);
                }
                LiveHelper.list = arrayList;
                SdkLiveService.this.vContext = userRecListRsp.vContext;
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.berry.module.live.SdkLiveService.36
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.info(SdkLiveService.TAG, "getRecListByGame fail");
            }
        });
    }
}
