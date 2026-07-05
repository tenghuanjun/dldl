package com.sqwan.liveshow.huya.engine;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.huya.berry.client.HuyaBerry;
import com.huya.berry.client.HuyaBerryConfig;
import com.huya.berry.client.customui.CustomUICallback;
import com.huya.berry.client.customui.model.LiveInfo;
import com.huya.berry.client.customui.model.LiveListInfo;
import com.nbvideo.VideoInfo;
import com.snail.antifake.jni.EmulatorDetectUtil;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.liveshow.IHyLiveshowManager;
import com.sqwan.common.mod.liveshow.LiveshowEngine;
import com.sqwan.common.util.ApkInfoUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.LiveshowBaseManager;
import com.sqwan.liveshow.common.LiveShowParamsKey;
import com.sqwan.liveshow.error.LiveshowResult;
import com.sqwan.liveshow.huya.LiveRoomDataManager;
import com.sqwan.liveshow.huya.bean.ConfigBean;
import com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager;
import com.sqwan.liveshow.huya.request.bean.danmu.http.EnterRoomReqBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.EnterRoomRspBean;
import com.sqwan.liveshow.huya.skin.SkinHelper;
import com.sqwan.liveshow.huya.skin.manager.SkinManager;
import com.sqwan.liveshow.huya.trackaction.LiveshowTrackManager;
import com.sqwan.liveshow.huya.view.LiveshowFloatView;
import com.sqwan.liveshow.huya.view.PlayerUtils;
import com.sqwan.liveshow.huya.view.RoomListView;
import com.sqwan.msdk.api.SQResultListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowManager extends LiveshowBaseManager implements IHyLiveshowManager, ILiveshowControl {
    private ConfigBean configBean;
    private boolean hasCheckHyInited = false;
    private boolean isMinimize = true;
    public LiveInfoEx liveInfoEx;
    private LiveshowEventManager liveshowEventManager;
    private LiveshowFloatView liveshowFloatView;
    private LiveRoomRequestManager liveshowRequestManager;
    private HuyaBerryConfig mHuyaBerryConfig;
    private int platformID;
    private RoomListView roomListView;

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void onActive(JSONObject jSONObject) {
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void pauseChannel() {
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void resumeChannel() {
    }

    public static LiveshowManager getInstance() {
        return (LiveshowManager) LiveshowEngine.getInstance().getLiveshowManager();
    }

    private LiveshowManager() {
    }

    public boolean isMinimize() {
        return this.isMinimize;
    }

    public int getPlatformID() {
        return this.platformID;
    }

    public void setMinimize(boolean z) {
        this.isMinimize = z;
    }

    @Override // com.sqwan.base.BaseEnginHandler
    public void init(Context context) {
        super.init(context);
        if (this.liveshowEventManager == null) {
            this.liveshowEventManager = new LiveshowEventManager();
        }
        if (this.liveshowRequestManager == null) {
            this.liveshowRequestManager = new LiveRoomRequestManager();
        }
        SkinHelper.init(context);
    }

    @Override // com.sqwan.liveshow.LiveshowBaseManager
    public void resetData() {
        LogUtil.i(this.TAG, "resetData");
        this.liveInfoEx = null;
        this.platformID = 0;
        this.isMinimize = true;
        this.liveshowFloatView = null;
        this.joinRoomStatu = LiveshowBaseManager.JoinRoomStatu.unjoined;
    }

    private boolean initConfigs() {
        List<ConfigBean.ItemsBean> items;
        ConfigBean.ItemsBean next;
        ConfigBean configBean = this.configBean;
        if (configBean == null || (items = configBean.getItems()) == null) {
            return false;
        }
        Iterator<ConfigBean.ItemsBean> it = items.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next.getPlatform_id() == 1) {
                break;
            }
        }
        if (next == null) {
            return false;
        }
        try {
            return HyConfigManager.getInstance().init(next);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00b0  */
    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean init() {
        /*
            r6 = this;
            java.lang.String r0 = r6.TAG
            java.lang.String r1 = "init start"
            com.sqwan.common.util.LogUtil.i(r0, r1)
            boolean r0 = r6.hasCheckHyInited
            r1 = 0
            if (r0 != 0) goto Lb0
            com.huya.berry.client.HuyaBerry r0 = com.huya.berry.client.HuyaBerry.instance()
            com.sqwan.liveshow.huya.engine.LiveshowManager$1 r2 = new com.sqwan.liveshow.huya.engine.LiveshowManager$1
            r2.<init>()
            r0.setBerryEventDelegate(r2)
            boolean r0 = r6.initConfigs()
            if (r0 != 0) goto L26
            java.lang.String r0 = r6.TAG
            java.lang.String r2 = "initConfigs error"
            com.sqwan.common.util.LogUtil.e(r0, r2)
            return r1
        L26:
            com.sqwan.liveshow.huya.engine.HyConfigManager r0 = com.sqwan.liveshow.huya.engine.HyConfigManager.getInstance()
            com.sqwan.liveshow.huya.engine.bean.HyConfig r0 = r0.hyConfig
            java.lang.String r2 = r6.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "hyConfig:"
            r3.append(r4)
            java.lang.String r4 = r0.toString()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.sqwan.common.util.LogUtil.i(r2, r3)
            com.huya.berry.client.HuyaBerryConfig$Builder r2 = new com.huya.berry.client.HuyaBerryConfig$Builder
            r2.<init>()
            int r3 = r0.gameId
            com.huya.berry.client.HuyaBerryConfig$Builder r2 = r2.gameId(r3)
            java.lang.String r3 = r0.appId
            com.huya.berry.client.HuyaBerryConfig$Builder r2 = r2.appId(r3)
            java.lang.String r3 = r0.appKey
            com.huya.berry.client.HuyaBerryConfig$Builder r2 = r2.appKey(r3)
            com.huya.berry.client.HuyaBerryConfig$Builder r2 = r2.debugMode(r1)
            com.sqwan.liveshow.huya.engine.bean.HyConfig$HyUiConfig r0 = r0.hyUiConfig
            boolean r0 = r0.landscapeMode
            com.huya.berry.client.HuyaBerryConfig$Builder r0 = r2.landscapeMode(r0)
            com.huya.berry.client.HuyaBerryConfig r0 = r0.build()
            r6.mHuyaBerryConfig = r0
            android.app.Activity r0 = com.sqwan.base.L.getActivity()
            r6.initLiveshowActivity(r0)
            android.app.Activity r0 = r6.getLiveshowActivity()
            if (r0 == 0) goto Lb0
            java.lang.String r2 = r6.getUserId()     // Catch: java.lang.Exception -> Lab
            java.lang.String r3 = r6.TAG     // Catch: java.lang.Exception -> Lab
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lab
            r4.<init>()     // Catch: java.lang.Exception -> Lab
            java.lang.String r5 = "init userId "
            r4.append(r5)     // Catch: java.lang.Exception -> Lab
            r4.append(r2)     // Catch: java.lang.Exception -> Lab
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> Lab
            com.sqwan.common.util.LogUtil.i(r3, r4)     // Catch: java.lang.Exception -> Lab
            com.huya.berry.client.HuyaBerry r3 = com.huya.berry.client.HuyaBerry.instance()     // Catch: java.lang.Exception -> Lab
            r3.setGameAccountID(r2)     // Catch: java.lang.Exception -> Lab
            com.huya.berry.client.HuyaBerry r2 = com.huya.berry.client.HuyaBerry.instance()     // Catch: java.lang.Exception -> Lab
            android.app.Application r0 = r0.getApplication()     // Catch: java.lang.Exception -> Lab
            com.huya.berry.client.HuyaBerryConfig r3 = r6.mHuyaBerryConfig     // Catch: java.lang.Exception -> Lab
            r2.init(r0, r3)     // Catch: java.lang.Exception -> Lab
            goto Lb0
        Lab:
            r0 = move-exception
            r0.printStackTrace()
            goto Lb1
        Lb0:
            r1 = 1
        Lb1:
            java.lang.String r0 = r6.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "init end initResult "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            com.sqwan.common.util.LogUtil.i(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.liveshow.huya.engine.LiveshowManager.init():boolean");
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void initContext(Context context) {
        init(context);
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void uninit() {
        HuyaBerry.instance().uninit();
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public boolean isLiveShow() {
        return this.joinRoomStatu == LiveshowBaseManager.JoinRoomStatu.joined;
    }

    public void goLiveshowRoomList() {
        Activity activityCheckValid = checkValid();
        if (activityCheckValid != null) {
            if (this.roomListView == null) {
                this.roomListView = new RoomListView(activityCheckValid);
            }
            this.roomListView.show();
        }
    }

    private boolean isSupportAbi() {
        if (this.context == null) {
            return false;
        }
        String strChooseByX86andArm = ApkInfoUtil.chooseByX86andArm(this.context);
        LogUtil.i(this.TAG, "cpu_architect " + strChooseByX86andArm);
        return strChooseByX86andArm.contains("arm64-v8a") || (strChooseByX86andArm.contains("armeabi-v7a") && !EmulatorDetectUtil.isEmulator(this.context));
    }

    private boolean isSupportSdkVersion() {
        return Build.VERSION.SDK_INT >= 21;
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void joinLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        if (this.joinRoomListener == null) {
            this.joinRoomListener = sQResultListener;
        }
        if (!isSupportAbi()) {
            callbackInvokeFail(sQResultListener, LiveshowResult.error_cpu_supprot);
            LogUtil.e(this.TAG, "error_cpu_supprot");
            ToastUtil.showToast("当前手机系统不支持");
            return;
        }
        if (!isSupportSdkVersion()) {
            callbackInvokeFail(sQResultListener, LiveshowResult.error_sdkcode_support);
            LogUtil.e(this.TAG, "error_sdkcode_support");
            ToastUtil.showToast("当前手机系统版本过低");
            return;
        }
        if (!((IAccountMod) ModHelper.get(IAccountMod.class)).hasSubmitRole()) {
            LogUtil.e(this.TAG, "hasSubmitRole false");
            callbackInvokeFail(sQResultListener, LiveshowResult.error_inited_not_submitrole);
            return;
        }
        if (this.joinRoomStatu == LiveshowBaseManager.JoinRoomStatu.joining) {
            LogUtil.e(this.TAG, "JoinRoomStatu.joining");
            callbackInvokeFail(sQResultListener, LiveshowResult.error_inited_joining);
            return;
        }
        if (this.joinRoomStatu == LiveshowBaseManager.JoinRoomStatu.liveshowlist) {
            LogUtil.e(this.TAG, "JoinRoomStatu.liveshowlist");
            handleRepeatClickLiveshowIcon();
            return;
        }
        if (this.joinRoomStatu == LiveshowBaseManager.JoinRoomStatu.joined) {
            LogUtil.e(this.TAG, "JoinRoomStatu.joined");
            handleRepeatClickLiveshowIcon();
            return;
        }
        if (!this.hasCheckHyInited) {
            this.hasCheckHyInited = init();
        }
        if (this.hasCheckHyInited) {
            LiveshowTrackManager.getInstance().liveIconAction();
            this.joinRoomStatu = LiveshowBaseManager.JoinRoomStatu.joining;
            goLiveshowRoomList();
            this.joinRoomStatu = LiveshowBaseManager.JoinRoomStatu.liveshowlist;
            LiveshowDanmuManager.getInstance().joinLiveShow();
            return;
        }
        callbackInvokeFail(this.joinRoomListener, LiveshowResult.error_inited_sdkinit);
    }

    public void leaveLiveshowRoomWrapper(boolean z) {
        LogUtil.i(this.TAG, "leaveLiveshowRoomWrapper reset:" + z);
        if (!z) {
            LiveshowDanmuManager.getInstance().leaveRoom();
            Task.post(new Runnable() { // from class: com.sqwan.liveshow.huya.engine.LiveshowManager.2
                @Override // java.lang.Runnable
                public void run() {
                    if (LiveshowManager.this.liveshowFloatView != null) {
                        LiveshowManager.this.liveshowFloatView.release();
                        LiveshowManager.this.handleLeaveRoomSuccessCallback();
                    }
                    if (LiveshowManager.this.roomListView != null) {
                        LiveshowManager.this.roomListView.show();
                    }
                    LiveshowManager.this.uninit();
                    LiveshowManager.this.liveInfoEx = null;
                    LiveshowManager.this.platformID = 0;
                    LiveshowManager.this.isMinimize = true;
                    LiveshowManager.this.liveshowFloatView = null;
                    LiveshowManager.this.joinRoomStatu = LiveshowBaseManager.JoinRoomStatu.liveshowlist;
                }
            });
        } else {
            leaveLiveshowRoom(null, null);
        }
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void leaveLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        LiveshowDanmuManager.getInstance().leaveLiveShow();
        LogUtil.i(this.TAG, "leaveLiveshowRoom");
        if (sQResultListener != null) {
            this.leaveRoomListener = sQResultListener;
        }
        Task.post(new Runnable() { // from class: com.sqwan.liveshow.huya.engine.LiveshowManager.3
            @Override // java.lang.Runnable
            public void run() {
                if (LiveshowManager.this.liveshowFloatView != null) {
                    LiveshowManager.this.liveshowFloatView.release();
                    LiveshowManager.this.handleLeaveRoomSuccessCallback();
                }
                if (LiveshowManager.this.roomListView != null) {
                    LiveshowManager.this.roomListView.release();
                    LiveshowManager.this.roomListView = null;
                }
                LiveshowManager.this.uninit();
                LiveshowManager.this.resetData();
                LiveRoomDataManager.getInstance().clearPlatformData();
            }
        });
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void setLiveshowVoiceChangeCallback(SQResultListener sQResultListener) {
        this.voiceChangeCallback = sQResultListener;
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void setLiveshowDestroyCallback(SQResultListener sQResultListener) {
        this.destroyCallback = sQResultListener;
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void performLiveshowFeature(Map<String, String> map, SQResultListener sQResultListener) {
        performLiveshowFeature_switchVoice(map, sQResultListener);
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void onSubmitRole() {
        if (isSupportAbi() && isSupportSdkVersion()) {
            if (!this.hasCheckHyInited) {
                LiveRoomDataManager.getInstance().getConfigData(new SqHttpCallback.SimpleSqHttpCallback<ConfigBean>() { // from class: com.sqwan.liveshow.huya.engine.LiveshowManager.4
                    @Override // com.sq.tool.network.SqHttpCallback
                    public void onSuccess(ConfigBean configBean) {
                        LogUtil.i(LiveshowManager.this.TAG, "onSubmitRole onSuccess ConfigBean " + configBean);
                        if (configBean != null) {
                            LiveshowManager.this.configBean = configBean;
                        }
                    }
                });
                return;
            } else {
                LogUtil.i(this.TAG, "onSubmitRole hasCheckHyInited true");
                return;
            }
        }
        LogUtil.e(this.TAG, "onSubmitRole not isSupportAbi isSupportSdkVersion ");
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void initSkin(Context context) {
        try {
            SkinManager.getInstance().inject(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isLiveshow() {
        return this.joinRoomStatu == LiveshowBaseManager.JoinRoomStatu.joined;
    }

    public boolean isLiveshowList() {
        return this.joinRoomStatu == LiveshowBaseManager.JoinRoomStatu.liveshowlist;
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void resume() {
        if (isLiveshow()) {
            HuyaBerry.instance().startVideoPlay();
            channelChangeInvokeCallback(true);
        }
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void pause() {
        if (isLiveshow()) {
            HuyaBerry.instance().pauseVideoPlay();
            channelChangeInvokeCallback(false);
        }
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void switchVoice(boolean z) {
        if (isLiveshow()) {
            HuyaBerry.instance().switchVoice(z);
            channelChangeInvokeCallback(z);
        }
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void minimize() {
        RoomListView roomListView = this.roomListView;
        if (roomListView != null) {
            roomListView.hide();
        }
        this.isMinimize = true;
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void maximize() {
        this.isMinimize = false;
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void close() {
        if (this.liveInfoEx != null) {
            uninitActivityLifeCycle();
        }
        leaveLiveshowRoomWrapper(this.isMinimize);
    }

    public void watchLive(final int i, final LiveListInfo liveListInfo, final WatchLiveCallback watchLiveCallback) {
        getLiveInfoEx(liveListInfo, new GetLiveInfoExCallback() { // from class: com.sqwan.liveshow.huya.engine.LiveshowManager.5
            @Override // com.sqwan.liveshow.huya.engine.GetLiveInfoExCallback
            public void result(final LiveInfoEx liveInfoEx) {
                if (liveInfoEx != null) {
                    if (LiveshowManager.this.joinRoomStatu == LiveshowBaseManager.JoinRoomStatu.joined) {
                        return;
                    }
                    final String userId = LiveshowManager.this.getUserId();
                    LogUtil.i(LiveshowManager.this.TAG, "watchLive userId " + userId);
                    LogUtil.i(LiveshowManager.this.TAG, "watchLive anchor uid " + liveListInfo.uid);
                    long j = liveInfoEx.liveInfo.roomId;
                    LiveshowDanmuManager.getInstance().danmuRoomRequestManager.enterRoom(new EnterRoomReqBean(j + ""), new SqHttpCallback<EnterRoomRspBean>() { // from class: com.sqwan.liveshow.huya.engine.LiveshowManager.5.1
                        @Override // com.sdk.sq.net.SqRequestCallback
                        public void onResponseStateError(int i2, int i3, String str, String str2) {
                            ToastUtil.showToast("直播间信息加载失败");
                        }

                        @Override // com.sq.tool.network.SqHttpCallback
                        public void onSuccess(EnterRoomRspBean enterRoomRspBean) {
                            if (enterRoomRspBean.isForbid()) {
                                ToastUtil.showToast("禁止进入房间");
                                return;
                            }
                            HuyaBerry.instance().setGameAccountID(userId);
                            if (!LiveshowManager.this.hasCheckHyInited) {
                                if (watchLiveCallback != null) {
                                    watchLiveCallback.loading();
                                }
                                LiveshowManager.this.hasCheckHyInited = LiveshowManager.this.init();
                                if (!LiveshowManager.this.hasCheckHyInited) {
                                    LogUtil.e(LiveshowManager.this.TAG, "watchLive init error");
                                    ToastUtil.showToast("初始化配置失败");
                                    return;
                                }
                                LogUtil.i(LiveshowManager.this.TAG, "watchLive init success");
                            }
                            if (LiveshowManager.this.liveshowFloatView == null) {
                                LiveshowManager.this.liveshowFloatView = new LiveshowFloatView(LiveshowManager.this.getLiveshowActivity());
                                if (LiveshowDanmuManager.getInstance().onBehaviorListener != null) {
                                    LiveshowManager.this.liveshowFloatView.setOnBehaviorListener(LiveshowDanmuManager.getInstance().onBehaviorListener);
                                }
                            }
                            LiveshowManager.this.liveshowFloatView.showView();
                            if (LiveshowManager.this.roomListView != null) {
                                LiveshowManager.this.roomListView.hide();
                            }
                            if (watchLiveCallback != null) {
                                watchLiveCallback.loadFinish(LiveshowManager.this.hasCheckHyInited);
                            }
                            if (LiveshowManager.this.hasCheckHyInited) {
                                LiveshowManager.this.joinRoomStatu = LiveshowBaseManager.JoinRoomStatu.joined;
                                if (liveListInfo != null) {
                                    LiveshowManager.this.platformID = i;
                                    liveInfoEx.liveListInfo = liveListInfo;
                                    LiveshowManager.this.liveInfoEx = liveInfoEx;
                                    LiveshowManager.this.liveshowFloatView.play(liveInfoEx);
                                    LiveshowManager.this.callbackInvokeSuccess(LiveshowManager.this.joinRoomListener, null, LiveshowResult.success_joinRoom);
                                    LiveshowManager.this.channelChangeInvokeCallback(true);
                                    LiveshowManager.this.initActivityLifeCycle();
                                    LiveshowDanmuManager.getInstance().joinRoom();
                                }
                            }
                        }

                        @Override // com.sq.tool.network.SqHttpCallback
                        public void onFailure(int i2, String str, VolleyError volleyError) {
                            ToastUtil.showToast("直播间信息加载失败");
                        }
                    });
                    return;
                }
                ToastUtil.showToast("直播间信息加载失败");
            }
        });
    }

    public void getLiveInfoEx(LiveListInfo liveListInfo, final GetLiveInfoExCallback getLiveInfoExCallback) {
        HuyaBerry.instance().getLiveData(liveListInfo.uid, new CustomUICallback<LiveInfo>() { // from class: com.sqwan.liveshow.huya.engine.LiveshowManager.6
            @Override // com.huya.berry.client.customui.CustomUICallback
            public void onResultListCallback(int i, List list) {
            }

            @Override // com.huya.berry.client.customui.CustomUICallback
            public void onResultCallback(int i, LiveInfo liveInfo) {
                if (i != 0) {
                    LogUtil.e(LiveshowManager.this.TAG, "getdata error");
                    GetLiveInfoExCallback getLiveInfoExCallback2 = getLiveInfoExCallback;
                    if (getLiveInfoExCallback2 != null) {
                        getLiveInfoExCallback2.result(null);
                        return;
                    }
                    return;
                }
                if (liveInfo.roomId != 0) {
                    LogUtil.i(LiveshowManager.this.TAG, liveInfo.toString());
                    VideoInfo videoInfoMatchLine = PlayerUtils.matchLine(liveInfo, null);
                    if (videoInfoMatchLine == null) {
                        LogUtil.e(LiveshowManager.this.TAG, "not match");
                        GetLiveInfoExCallback getLiveInfoExCallback3 = getLiveInfoExCallback;
                        if (getLiveInfoExCallback3 != null) {
                            getLiveInfoExCallback3.result(null);
                            return;
                        }
                        return;
                    }
                    if (getLiveInfoExCallback != null) {
                        final LiveInfoEx liveInfoEx = new LiveInfoEx();
                        liveInfoEx.liveInfo = liveInfo;
                        liveInfoEx.videoInfo = videoInfoMatchLine;
                        liveInfoEx.disPlayNames = PlayerUtils.getLines(liveInfo);
                        Task.post(new Runnable() { // from class: com.sqwan.liveshow.huya.engine.LiveshowManager.6.1
                            @Override // java.lang.Runnable
                            public void run() {
                                getLiveInfoExCallback.result(liveInfoEx);
                            }
                        });
                        return;
                    }
                    return;
                }
                ToastUtil.showToast("主播已经离开房间");
                LogUtil.e(LiveshowManager.this.TAG, "主播已经离开房间");
            }
        });
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public String getRoomId() {
        LiveInfoEx liveInfoEx = this.liveInfoEx;
        if (liveInfoEx == null || liveInfoEx.liveInfo == null) {
            return "";
        }
        return this.liveInfoEx.liveInfo.roomId + "";
    }

    public LiveshowFloatView getLiveshowFloatView() {
        return this.liveshowFloatView;
    }

    public RoomListView getRoomListView() {
        return this.roomListView;
    }

    private void performLiveshowFeature_switchVoice(Map<String, String> map, SQResultListener sQResultListener) {
        if (map.containsKey(LiveShowParamsKey.switchVoice)) {
            boolean zEqualsIgnoreCase = map.get(LiveShowParamsKey.switchVoice).equalsIgnoreCase("true");
            if (isLiveshow()) {
                if (zEqualsIgnoreCase) {
                    onForeground();
                } else {
                    onBackground();
                }
                if (sQResultListener != null) {
                    sQResultListener.onSuccess(new Bundle());
                }
            }
        }
    }
}
