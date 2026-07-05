package com.huya.berry.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.widget.FrameLayout;
import com.alibaba.fastjson.JSONObject;
import com.duowan.HUYA.CornerMark;
import com.duowan.HUYA.GetLivingInfoRsp;
import com.duowan.HUYA.GetRelationRsp;
import com.duowan.HUYA.ModRelationRsp;
import com.duowan.HUYA.ScreenType;
import com.duowan.HUYA.SendMessageRsp;
import com.duowan.HUYA.UserEventRsp;
import com.duowan.HUYA.UserRecItem;
import com.duowan.HUYA.UserRecListRsp;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.asignal.SignalCenter;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.login.api.ILoginService;
import com.duowan.live.one.module.report.Report;
import com.duowan.live.one.util.NetworkUtil;
import com.duowan.networkmars.hysignal.HySignalProxy;
import com.huya.berry.client.customui.CustomUICallback;
import com.huya.berry.client.customui.model.BitRateInfo;
import com.huya.berry.client.customui.model.LiveInfo;
import com.huya.berry.client.customui.model.LiveListInfo;
import com.huya.berry.client.customui.model.SubscribeInfo;
import com.huya.berry.endlive.api.ISdkPlayerService;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.ICommonService;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.commonevent.Event_Biz;
import com.huya.berry.module.help.LiveHelper;
import com.huya.berry.module.live.ISdkLiveService;
import com.huya.berry.sdkplayer.floats.FloatingVideoMgr;
import com.huya.berry.sdkplayer.floats.view.PlayerActivity;
import com.huya.berry.sdkplayer.floats.view.PortraitPlayerActivity;
import com.huya.component.login.LoginProperties;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.huya.mtp.hyns.api.NSRegisterApi;
import com.huya.statistics.LiveCommonFieldProvider;
import com.huya.statistics.LiveStaticsicsSdk;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sqwan.bugless.core.ParamsManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HuyaBerryData implements IHuyaBerryData {
    private static final int MAX_DANMU_LEN = 30;
    public static final String TAG = "HuyaBerryData";
    private boolean isCallBackDanmu;
    private String mDanmuMsg;
    private boolean mDanmuViewShow;
    private boolean mIsWatchLive;
    private CustomUICallback mSendDanmuCallback;
    private long mSendDanmuChannalId;
    private long mSendDanmuSubId;
    private long mSendDanmuUid;

    public HuyaBerryData() {
        SignalCenter.register(this);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void showDanmuView(FrameLayout frameLayout, long j) {
        ISdkPlayerService iSdkPlayerService = (ISdkPlayerService) ServiceCenter.instance().getService(ISdkPlayerService.class);
        if (iSdkPlayerService != null) {
            this.mDanmuViewShow = iSdkPlayerService.showDanmuView(frameLayout, j);
        }
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void hideDanmuView() {
        ISdkPlayerService iSdkPlayerService = (ISdkPlayerService) ServiceCenter.instance().getService(ISdkPlayerService.class);
        if (iSdkPlayerService != null) {
            this.mDanmuViewShow = iSdkPlayerService.hideDanmuView(this.isCallBackDanmu);
        }
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void setReceiveDanmuData(boolean z, long j) {
        if (this.isCallBackDanmu == z) {
            return;
        }
        if (SdkProperties.isPLayerFloating.get().booleanValue() && z) {
            return;
        }
        this.isCallBackDanmu = z;
        if (z) {
            SdkProperties.isCustomReceiveDanmu.set(true);
            registerBroadcastByChannel(j);
        } else {
            if (this.mDanmuViewShow) {
                return;
            }
            SdkProperties.isCustomReceiveDanmu.set(false);
            unregisterBroadcastByChannel(j);
        }
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void sendDanmu(long j, long j2, long j3, Activity activity, String str, CustomUICallback customUICallback) {
        this.mSendDanmuChannalId = j;
        this.mSendDanmuSubId = j2;
        this.mSendDanmuUid = j3;
        this.mDanmuMsg = str;
        this.mSendDanmuCallback = customUICallback;
        if (LoginProperties.uid.get().longValue() <= 0) {
            ArkToast.show("发言需要先登录哦");
            ILoginService iLoginService = (ILoginService) ServiceCenter.instance().getService(ILoginService.class);
            if (iLoginService != null) {
                iLoginService.login(activity);
                return;
            }
            return;
        }
        sendDanmu();
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void onGetLiveListData(boolean z, CustomUICallback customUICallback) {
        getRecListByGame("", z, customUICallback, false);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void getTagListData(CustomUICallback customUICallback) {
        getRecListByGame("", true, customUICallback, true);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void getLiveListDataByTag(String str, boolean z, CustomUICallback customUICallback) {
        getRecListByGame(str, z, customUICallback, false);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void onGetLiveData(long j, CustomUICallback customUICallback) {
        getLiveInfoByGame(0L, 0L, j, 0L, customUICallback);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void getLiveDataByRoomId(long j, CustomUICallback customUICallback) {
        this.mIsWatchLive = false;
        getLiveInfoByGame(0L, 0L, 0L, j, customUICallback);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void watchLive(long j, Activity activity, CustomUICallback customUICallback) {
        PlayerHelper.mActivity = activity;
        this.mIsWatchLive = true;
        getLiveInfoByGame(0L, 0L, 0L, j, customUICallback);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void watchLiveByUid(long j, Activity activity, CustomUICallback customUICallback) {
        PlayerHelper.mActivity = activity;
        this.mIsWatchLive = true;
        getLiveInfoByGame(0L, 0L, j, 0L, customUICallback);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void fullScreen() {
        ArkUtils.send(new CommonEvent.FullScreenCloseFloating(true));
        Intent intent = new Intent(ArkValue.gContext, (Class<?>) (PlayerHelper.mScreenType == ScreenType.ST_Horizonal ? PlayerActivity.class : PortraitPlayerActivity.class));
        intent.addFlags(268435456);
        ArkValue.gContext.startActivity(intent);
        Report.event(SdkReportConst.LIVE_SMALLWINDOW_FULLSCREEN);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void fullScreen(long j) {
        PlayerHelper.presenterUid = j;
        Intent intent = new Intent(ArkValue.gContext, (Class<?>) (SdkProperties.isLandscape.get().booleanValue() ? PlayerActivity.class : PortraitPlayerActivity.class));
        intent.addFlags(268435456);
        ArkValue.gContext.startActivity(intent);
        Report.event(SdkReportConst.LIVE_SMALLWINDOW_FULLSCREEN);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void smallWindowPlay(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
            PlayerHelper.mActivity = activity;
            ArkUtils.send(new CommonEvent.SmallWindowPlay());
            FloatingVideoMgr.getInstance().start(PlayerHelper.mScreenType);
            FloatingVideoMgr.getInstance().createPlayer();
        }
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void pauseVideoPlay() {
        ArkUtils.send(new CommonEvent.PauseOrPlay(false));
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void startVideoPlay() {
        ArkUtils.send(new CommonEvent.PauseOrPlay(true));
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void switchDanmu(boolean z) {
        ArkUtils.send(new CommonEvent.SwitchDanmu(z));
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void switchVoice(boolean z) {
        ArkUtils.send(new CommonEvent.SwitchVoice(z));
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void closeFloat() {
        ArkUtils.send(new CommonEvent.FullScreenCloseFloating(false));
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void customUIOpenQuality(Activity activity, CustomUICallback customUICallback) {
        if (PlayerHelper.roomId <= 0) {
            customUICallback.onResultCallback(1, null);
            return;
        }
        ISdkPlayerService iSdkPlayerService = (ISdkPlayerService) ServiceCenter.instance().getService(ISdkPlayerService.class);
        if (iSdkPlayerService != null) {
            iSdkPlayerService.openLine(activity);
            customUICallback.onResultCallback(0, null);
        }
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void customUIOpenSendDanmu(Activity activity, CustomUICallback customUICallback) {
        if (PlayerHelper.roomId <= 0) {
            customUICallback.onResultCallback(1, null);
            return;
        }
        ISdkPlayerService iSdkPlayerService = (ISdkPlayerService) ServiceCenter.instance().getService(ISdkPlayerService.class);
        if (iSdkPlayerService != null) {
            iSdkPlayerService.openFloatEdit(activity);
            customUICallback.onResultCallback(0, null);
        }
    }

    private void getRecListByGame(String str, boolean z, final CustomUICallback customUICallback, final boolean z2) {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.getRecListByGame(z, SdkProperties.gameId.get().intValue(), str).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<UserRecListRsp>() { // from class: com.huya.berry.client.HuyaBerryData.1
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(UserRecListRsp userRecListRsp) {
                    if (!z2) {
                        HuyaBerryData.this.getLiveList(customUICallback, userRecListRsp);
                        return;
                    }
                    CustomUICallback customUICallback2 = customUICallback;
                    if (customUICallback2 != null) {
                        customUICallback2.onResultListCallback(0, LiveHelper.list);
                    }
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    CustomUICallback customUICallback2 = customUICallback;
                    if (customUICallback2 != null) {
                        customUICallback2.onResultListCallback(1, null);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLiveList(CustomUICallback customUICallback, UserRecListRsp userRecListRsp) {
        ArrayList arrayList = new ArrayList();
        if (userRecListRsp.vItems != null) {
            for (int i = 0; i < userRecListRsp.vItems.size(); i++) {
                UserRecItem userRecItem = userRecListRsp.vItems.get(i);
                LiveListInfo liveListInfo = new LiveListInfo();
                liveListInfo.nickName = userRecItem.sNickName;
                liveListInfo.avatar = userRecItem.sAvatar;
                liveListInfo.title = userRecItem.sTitle;
                liveListInfo.gameId = SdkProperties.gameId.get().intValue();
                String strReplace = userRecItem.sCoverUrl;
                if (strReplace.indexOf(IDataSource.SCHEME_HTTPS_TAG) == -1) {
                    strReplace = strReplace.replace(IDataSource.SCHEME_HTTP_TAG, IDataSource.SCHEME_HTTPS_TAG);
                }
                liveListInfo.coverUrl = strReplace;
                Iterator<CornerMark> it = userRecItem.vCornerMarks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CornerMark next = it.next();
                    if (next.iPos == 4) {
                        liveListInfo.audienceCount = next.sText;
                        break;
                    }
                }
                String[] strArrSplit = userRecItem.sAction.split("&");
                if (strArrSplit.length == 0) {
                    L.info(TAG, "item.sAction split == 0");
                } else {
                    for (String str : strArrSplit) {
                        if (str.contains("liveuid")) {
                            liveListInfo.uid = Long.valueOf(str.substring(str.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str.length())).longValue();
                        }
                        if (str.contains("channelid")) {
                            liveListInfo.channelId = Long.valueOf(str.substring(str.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str.length())).longValue();
                        }
                        if (str.contains("subid")) {
                            liveListInfo.subId = Long.valueOf(str.substring(str.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1, str.length())).longValue();
                        }
                    }
                    arrayList.add(liveListInfo);
                }
            }
        }
        if (customUICallback != null) {
            customUICallback.onResultListCallback(0, arrayList);
        }
    }

    private void getLiveInfoByGame(long j, long j2, long j3, long j4, final CustomUICallback customUICallback) {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.getLivingInfo(j, j2, j3, j4).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<GetLivingInfoRsp>() { // from class: com.huya.berry.client.HuyaBerryData.2
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetLivingInfoRsp getLivingInfoRsp) {
                    LiveInfo liveInfo = new LiveInfo();
                    if (PlayerHelper.roomId <= 0) {
                        customUICallback.onResultCallback(1, liveInfo);
                        return;
                    }
                    liveInfo.gameName = PlayerHelper.gameName;
                    liveInfo.title = PlayerHelper.title;
                    liveInfo.avatar = PlayerHelper.liveAnchorAvatar;
                    liveInfo.nickName = PlayerHelper.liveAnchorName;
                    liveInfo.audienceCount = PlayerHelper.audienceCount;
                    liveInfo.uid = PlayerHelper.presenterUid;
                    liveInfo.roomId = PlayerHelper.roomId;
                    if (SdkProperties.maxPlayBitrate.get().intValue() > 0 && PlayerHelper.bitRate > SdkProperties.maxPlayBitrate.get().intValue()) {
                        Vector<BitRateInfo> bitRateList = liveInfo.getBitRateList(PlayerHelper.line);
                        if (bitRateList.size() > 0) {
                            PlayerHelper.bitRate = bitRateList.get(0).bitRate;
                        }
                    }
                    liveInfo.landType = PlayerHelper.mScreenType == ScreenType.ST_Horizonal ? 0 : 1;
                    customUICallback.onResultCallback(0, liveInfo);
                    if (HuyaBerryData.this.mIsWatchLive) {
                        HuyaBerryData.this.mIsWatchLive = false;
                        ISdkPlayerService iSdkPlayerService = (ISdkPlayerService) ServiceCenter.instance().getService(ISdkPlayerService.class);
                        if (iSdkPlayerService != null) {
                            iSdkPlayerService.watchLive();
                            return;
                        }
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("Gameid", (Object) SdkProperties.gameId.get());
                        jSONObject.put("Network", (Object) (NetworkUtil.isWifiActive(ArkValue.gContext) ? "wifi" : "4G"));
                        jSONObject.put("Roomid", (Object) Long.valueOf(PlayerHelper.roomId));
                        jSONObject.put("Ifapi", (Object) true);
                        Report.event(SdkReportConst.LIVE_STATUS_WATCHLIVE, SdkReportConst.LIVE_STATUS_WATCHLIVE_DES, "", jSONObject.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void sendMessage() {
        ICommonService iCommonService = (ICommonService) ServiceCenter.instance().getService(ICommonService.class);
        if (iCommonService != null) {
            iCommonService.sendMessage(new CommonEvent.SendPubText(this.mDanmuMsg, this.mSendDanmuUid, this.mSendDanmuChannalId, this.mSendDanmuSubId, LiveHelper.getUserId())).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<SendMessageRsp>() { // from class: com.huya.berry.client.HuyaBerryData.3
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(SendMessageRsp sendMessageRsp) {
                    if (HuyaBerryData.this.mSendDanmuCallback != null) {
                        HuyaBerryData.this.mSendDanmuCallback.onResultCallback(0, null);
                    }
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    if (HuyaBerryData.this.mSendDanmuCallback != null) {
                        HuyaBerryData.this.mSendDanmuCallback.onResultCallback(1, null);
                    }
                }
            });
        }
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void changeGameId(int i) {
        getRecListByGame("", true, null, false);
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void joinChannel(final long j, int i) {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.userEvent(j, i).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<UserEventRsp>() { // from class: com.huya.berry.client.HuyaBerryData.4
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(UserEventRsp userEventRsp) {
                }
            });
            if (i == 1) {
                LiveStaticsicsSdk.setLiveCommonFieldProvider(new LiveCommonFieldProvider() { // from class: com.huya.berry.client.HuyaBerryData.5
                    @Override // com.huya.statistics.LiveCommonFieldProvider
                    public Map<String, String> getLiveCommonField() {
                        HashMap map = new HashMap();
                        map.put("ayyuid", j + "");
                        map.put(ParamsManager.CONFIG_NODE_GAMEID, SdkProperties.gameId.get() + "");
                        return map;
                    }
                });
                LiveStaticsicsSdk.chnStartUp();
            } else {
                LiveStaticsicsSdk.chnEndUp();
            }
        }
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void subscribe(long j, final CustomUICallback customUICallback) {
        if (customUICallback == null) {
            return;
        }
        final SubscribeInfo subscribeInfo = new SubscribeInfo();
        if (LoginProperties.loginState.get() != LoginProperties.LoginState.LoggedIn || LoginProperties.uid.get().longValue() == 0) {
            subscribeInfo.isLogin = false;
            subscribeInfo.msg = "需要登陆才可以订阅主播";
            customUICallback.onResultCallback(1, subscribeInfo);
        } else {
            ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
            if (iSdkLiveService != null) {
                iSdkLiveService.addSubscribe(j).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<ModRelationRsp>() { // from class: com.huya.berry.client.HuyaBerryData.6
                    @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                    public void onNext(ModRelationRsp modRelationRsp) {
                        subscribeInfo.isLogin = true;
                        if (modRelationRsp.iNewRelation == 1) {
                            subscribeInfo.isSubscribe = true;
                            subscribeInfo.msg = "订阅主播成功";
                            customUICallback.onResultCallback(0, subscribeInfo);
                        } else {
                            subscribeInfo.isSubscribe = false;
                            subscribeInfo.msg = "订阅主播失败";
                            customUICallback.onResultCallback(1, subscribeInfo);
                        }
                    }

                    @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                    public void onError(Throwable th) {
                        subscribeInfo.isLogin = true;
                        subscribeInfo.isSubscribe = false;
                        subscribeInfo.msg = "订阅主播失败";
                        customUICallback.onResultCallback(1, subscribeInfo);
                    }
                });
            }
        }
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void unSubscribe(long j, final CustomUICallback customUICallback) {
        if (customUICallback == null) {
            return;
        }
        final SubscribeInfo subscribeInfo = new SubscribeInfo();
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.delSubscribe(j).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<ModRelationRsp>() { // from class: com.huya.berry.client.HuyaBerryData.7
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(ModRelationRsp modRelationRsp) {
                    subscribeInfo.isLogin = true;
                    if (modRelationRsp.iNewRelation == 1) {
                        subscribeInfo.isSubscribe = true;
                        subscribeInfo.msg = "取消订阅主播失败";
                        customUICallback.onResultCallback(1, subscribeInfo);
                    } else {
                        subscribeInfo.isSubscribe = false;
                        subscribeInfo.msg = "取消订阅主播成功";
                        customUICallback.onResultCallback(0, subscribeInfo);
                    }
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    subscribeInfo.isLogin = true;
                    subscribeInfo.isSubscribe = true;
                    subscribeInfo.msg = "取消订阅主播失败";
                    customUICallback.onResultCallback(1, subscribeInfo);
                }
            });
        }
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void querySubscribeStatus(long j, final CustomUICallback customUICallback) {
        if (customUICallback == null) {
            return;
        }
        final SubscribeInfo subscribeInfo = new SubscribeInfo();
        if (LoginProperties.loginState.get() != LoginProperties.LoginState.LoggedIn || LoginProperties.uid.get().longValue() == 0) {
            subscribeInfo.isLogin = false;
            subscribeInfo.msg = "需要登陆才可以查询订阅主播状态";
            customUICallback.onResultCallback(1, subscribeInfo);
        } else {
            ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
            if (iSdkLiveService != null) {
                iSdkLiveService.getRelation(j).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<GetRelationRsp>() { // from class: com.huya.berry.client.HuyaBerryData.8
                    @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                    public void onNext(GetRelationRsp getRelationRsp) {
                        subscribeInfo.isLogin = true;
                        if (getRelationRsp.tItem.iRelation == 1) {
                            subscribeInfo.isSubscribe = true;
                        } else {
                            subscribeInfo.isSubscribe = false;
                        }
                        customUICallback.onResultCallback(0, subscribeInfo);
                    }

                    @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                    public void onError(Throwable th) {
                        subscribeInfo.isLogin = true;
                        subscribeInfo.isSubscribe = false;
                        customUICallback.onResultCallback(1, subscribeInfo);
                    }
                });
            }
        }
    }

    @Override // com.huya.berry.client.IHuyaBerryData
    public void uninit() {
        SignalCenter.unregister(this);
    }

    private void sendDanmu() {
        if (this.mDanmuMsg.length() > 30) {
            ArkToast.show("发言字符长度须小于30");
        } else {
            sendMessage();
        }
    }

    private void registerBroadcastByChannel(long j) {
        HySignalProxy.getInstance().registerLiveGroups(j, new NSRegisterApi.RegisterPushMsgListener() { // from class: com.huya.berry.client.HuyaBerryData.9
            @Override // com.huya.mtp.hyns.api.NSRegisterApi.RegisterPushMsgListener
            public void onRegisterSucceed(NSRegisterApi.RegistResultInfo registResultInfo) {
                L.info(HuyaBerryData.TAG, "Mars:onRegisterSucceed ...");
            }

            @Override // com.huya.mtp.hyns.api.NSRegisterApi.RegisterPushMsgListener
            public void onRegisterFailed(NSRegisterApi.RegistResultInfo registResultInfo) {
                L.error(HuyaBerryData.TAG, "Mars:onRegisterFailed %d", Integer.valueOf(registResultInfo.getStatus()));
            }
        });
    }

    private void unregisterBroadcastByChannel(long j) {
        HySignalProxy.getInstance().unRegisterLiveGroups(j);
    }

    @IASlot(executorID = 1)
    public void onSendPublicText(Event_Biz.SendPublicText sendPublicText) {
        if (this.isCallBackDanmu) {
            HuyaBerryCallback.getInstance().callbackReceiveDanmu(true, sendPublicText.ct.nickname, sendPublicText.ct.text);
        }
    }

    @IASlot(executorID = 1)
    public void onTextAboutToSendV2(Event_Biz.TextAboutToSendV2 textAboutToSendV2) {
        if (this.isCallBackDanmu) {
            HuyaBerryCallback.getInstance().callbackReceiveDanmu(true, textAboutToSendV2.nickname, textAboutToSendV2.text);
        }
    }

    @IASlot(executorID = 1)
    public void onNormalPlay(CommonEvent.NormalPlay normalPlay) {
        if (normalPlay.fullScreen) {
            HuyaBerryCallback.getInstance().callbackExitFullScreen(true);
        }
    }

    @IASlot(executorID = 1)
    public void onFullScreen(CommonEvent.FullScreen fullScreen) {
        HuyaBerryCallback.getInstance().callbackFullScreen(true);
    }

    @IASlot(executorID = 1)
    public void onShowFloating(CommonEvent.ShowFloating showFloating) {
        HuyaBerryCallback.getInstance().callbackShowFloating(true);
    }
}
