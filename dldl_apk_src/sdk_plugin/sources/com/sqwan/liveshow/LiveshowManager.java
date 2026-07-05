package com.sqwan.liveshow;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.dialog.CommonAlertDialog;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.liveshow.IAudioLiveshowManager;
import com.sqwan.common.mod.liveshow.LiveRadioEngine;
import com.sqwan.common.util.AESUtil;
import com.sqwan.common.util.ActivityLifeCycleUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.bean.LiveshowChannelInfo;
import com.sqwan.liveshow.bean.LiveshowConfig;
import com.sqwan.liveshow.common.IListenerManager;
import com.sqwan.liveshow.common.ILiveshowEventWrapper;
import com.sqwan.liveshow.common.ILiveshowViewEvent;
import com.sqwan.liveshow.common.LiveShowParamsKey;
import com.sqwan.liveshow.error.LiveshowResult;
import com.sqwan.liveshow.im.LiveshowImManager;
import com.sqwan.liveshow.request.LiveshowRequestManager;
import com.sqwan.liveshow.trackaction.LiveshowTrackManager;
import com.sqwan.liveshow.ui.LiveshowFloatView;
import com.sqwan.liveshow.ui.LiveshowRoomActivity;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.AccountCache;
import com.youme.voiceengine.MemberChange;
import com.youme.voiceengine.api;
import com.youme.voiceengine.mgr.YouMeManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowManager extends LiveshowBaseManager implements IAudioLiveshowManager, ILiveshowEventWrapper {
    private ActivityLifeCycleUtils.AppVisibilityCallback appVisibilityCallback;
    private long eTime;
    private long eTimeReally;
    public boolean isAnchorOnline;
    private LiveshowChannelInfo liveshowChannelInfo;
    private LiveshowFloatView liveshowFloatView;
    private LiveshowRequestManager liveshowRequestManager;
    private long sTime;
    private long sTimeReally;
    private int onlineCount = -1;
    private List<ILiveshowViewEvent> iLiveshowViewEvents = new ArrayList();
    private JoinRoomStatu joinRoomStatu = JoinRoomStatu.unjoined;
    private boolean hasInitNative = false;
    public LiveshowConfig liveshowConfig = new LiveshowConfig();
    public boolean isResume = true;
    public boolean isResumeLast = true;
    private LiveshowEventManager liveshowEventManager = new LiveshowEventManager();
    private IListenerManager listenerManager = new ListenerManager();

    enum JoinRoomStatu {
        joined,
        unjoined,
        joining
    }

    public interface MinimizeCallback {
        void callbcak(boolean z, boolean z2);
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void initSkin(Context context) {
    }

    @Override // com.sqwan.liveshow.common.ILiveshowEventWrapper
    public void onMemberChange(int i) {
    }

    @Override // com.sqwan.liveshow.common.ILiveshowEventWrapper
    public void onRepeatClickLiveshowIcon() {
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void performLiveshowFeature(Map<String, String> map, SQResultListener sQResultListener) {
    }

    public static LiveshowManager getInstance() {
        return (LiveshowManager) LiveRadioEngine.getInstance().getLiveshowManager();
    }

    private LiveshowManager() {
    }

    public int getOnlineCount() {
        int i = this.onlineCount;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public void setOnlineCount(int i) {
        this.onlineCount = i;
    }

    @Override // com.sqwan.base.BaseEnginHandler
    public void init(Context context) {
        super.init(context);
        this.liveshowRequestManager = new LiveshowRequestManager();
        LiveshowImManager.getInstance().init(context);
    }

    public void addiLiveshowViewEvent(ILiveshowViewEvent iLiveshowViewEvent) {
        this.iLiveshowViewEvents.add(iLiveshowViewEvent);
    }

    public void removeLiveshowViewEvent(ILiveshowViewEvent iLiveshowViewEvent) {
        this.iLiveshowViewEvents.remove(iLiveshowViewEvent);
    }

    @Override // com.sqwan.liveshow.LiveshowBaseManager
    public void resetData() {
        this.isAnchorOnline = false;
        this.onlineCount = -1;
        this.joinRoomStatu = JoinRoomStatu.unjoined;
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public boolean init() {
        resetData();
        Activity activityCheckValid = checkValid();
        if (activityCheckValid != null) {
            if (!this.hasInitNative) {
                api.SetCallback(this.liveshowEventManager);
                if (YouMeManager.Init(activityCheckValid)) {
                    LogUtil.i(this.TAG, "YouMeManager.Init");
                    this.hasInitNative = true;
                } else {
                    callbackInvokeFail(this.joinRoomListener, LiveshowResult.error_inited_youmemanager);
                    return false;
                }
            }
            if (!api.isInited()) {
                LogUtil.i(this.TAG, "api init");
                int iInit = api.init(this.liveshowConfig.appkey, this.liveshowConfig.appsecret, 0, "cn");
                LogUtil.i(this.TAG, "apiInitResult " + iInit);
                if (iInit != 0) {
                    callbackInvokeFail(this.joinRoomListener, LiveshowResult.error_inited_api);
                    return false;
                }
            }
        }
        if (this.appVisibilityCallback == null) {
            this.appVisibilityCallback = new ActivityLifeCycleUtils.AppVisibilityCallbackAdapter() { // from class: com.sqwan.liveshow.LiveshowManager.1
                @Override // com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallbackAdapter, com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallback
                public void onBackground() {
                    if (LiveshowManager.this.isResume) {
                        LiveshowManager.this.isResumeLast = true;
                        LiveshowManager.this.pauseChannel();
                    }
                }

                @Override // com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallbackAdapter, com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallback
                public void onForeground() {
                    if (LiveshowManager.this.isResumeLast) {
                        LiveshowManager.this.isResumeLast = false;
                        LiveshowManager.this.resumeChannel();
                    }
                }
            };
        }
        ActivityLifeCycleUtils.getInstance().registerActivityListener(this.appVisibilityCallback);
        register(this);
        this.isResume = true;
        return true;
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void initContext(Context context) {
        if (this.context == null) {
            init(context);
        }
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void uninit() {
        LogUtil.i(this.TAG, "uninit");
        if (api.isInited()) {
            api.unInit();
            LogUtil.i(this.TAG, "api uninit");
        }
        unregister(this);
        resetData();
        if (this.appVisibilityCallback != null) {
            ActivityLifeCycleUtils.getInstance().unRegisterActivityListener(this.appVisibilityCallback);
        }
    }

    private void leaveRoom() {
        releaseLiveShowView();
        if (this.hasInitNative) {
            if (isJoined()) {
                this.listenerManager.leaveRoom();
            } else {
                uninit();
                handleLeaveRoomSuccessCallback();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void leaveRoomActivty() {
        CopyOnWriteArrayList<Activity> copyOnWriteArrayList = ActivityLifeCycleUtils.getInstance().activities;
        if (copyOnWriteArrayList.size() > 0) {
            Activity activity = copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1);
            if (ActivityLifeCycleUtils.getInstance().equalActivity(activity, LiveshowRoomActivity.class)) {
                LogUtil.i(this.TAG, "leaveRoomActivty");
                activity.finish();
            }
        }
    }

    @Override // com.sqwan.liveshow.LiveshowBaseManager, com.sqwan.common.mod.liveshow.ILiveshowManager
    public String getUserId() {
        return AccountCache.getUserid(this.context);
    }

    public void resumeOrPauseChannel() {
        if (this.isResume) {
            pauseChannel();
        } else {
            resumeChannel();
        }
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void pauseChannel() {
        if (isJoined()) {
            turnOffAction();
            api.pauseChannel();
            channelChangeInvokeCallback(false);
        }
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void resumeChannel() {
        if (isJoined()) {
            turnOnAction();
            api.resumeChannel();
            channelChangeInvokeCallback(true);
        }
    }

    @Override // com.sqwan.liveshow.LiveshowBaseManager
    public boolean isJoined() {
        return this.joinRoomStatu == JoinRoomStatu.joined;
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public boolean isLiveShow() {
        return !TextUtils.isEmpty(getRoomId());
    }

    @Override // com.sqwan.liveshow.LiveshowBaseManager
    protected void handleRepeatClickLiveshowIcon() {
        LiveshowEventManager liveshowEventManager = this.liveshowEventManager;
        if (liveshowEventManager == null || liveshowEventManager.liveshowEventWrapperDispatcher == null) {
            return;
        }
        this.liveshowEventManager.liveshowEventWrapperDispatcher.dispatchRepeatClickLiveshowIcon();
    }

    private void handleMemberChange(int i) {
        LiveshowEventManager liveshowEventManager = this.liveshowEventManager;
        if (liveshowEventManager == null || liveshowEventManager.liveshowEventWrapperDispatcher == null) {
            return;
        }
        this.liveshowEventManager.liveshowEventWrapperDispatcher.dispatchMemberChange(i);
    }

    private void goChatRoom() {
        Intent intent = new Intent(checkValid(), (Class<?>) LiveshowRoomActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("Ex", true);
        checkValid().startActivity(intent);
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void joinLiveshowRoom(Map<String, String> map, final SQResultListener sQResultListener) {
        LogUtil.i(this.TAG, "joinLiveshowRoom");
        if (this.joinRoomListener == null) {
            this.joinRoomListener = sQResultListener;
        }
        if (!((IAccountMod) ModHelper.get(IAccountMod.class)).hasSubmitRole()) {
            LogUtil.e(this.TAG, "hasSubmitRole false");
            callbackInvokeFail(sQResultListener, LiveshowResult.error_inited_not_submitrole);
        } else if (this.joinRoomStatu == JoinRoomStatu.joining) {
            LogUtil.e(this.TAG, "JoinRoomStatu.joining");
            callbackInvokeFail(sQResultListener, LiveshowResult.error_inited_joining);
        } else if (this.joinRoomStatu == JoinRoomStatu.joined) {
            LogUtil.e(this.TAG, "JoinRoomStatu.joined");
            handleRepeatClickLiveshowIcon();
        } else {
            this.joinRoomStatu = JoinRoomStatu.joining;
            this.liveshowRequestManager.reqGetRadioChannels(new SqHttpCallback<LiveshowChannelInfo>() { // from class: com.sqwan.liveshow.LiveshowManager.2
                @Override // com.sdk.sq.net.SqRequestCallback
                public void onResponseStateError(int i, int i2, String str, String str2) {
                    LiveshowManager.this.callbackInvokeFail(sQResultListener, LiveshowResult.error_inited_reqeust);
                    LiveshowManager.this.joinRoomStatu = JoinRoomStatu.unjoined;
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(LiveshowChannelInfo liveshowChannelInfo) {
                    if (liveshowChannelInfo != null) {
                        LiveshowManager.this.liveshowChannelInfo = liveshowChannelInfo;
                    }
                    if (TextUtils.isEmpty(LiveshowManager.this.getRoomId())) {
                        Task.post(new Runnable() { // from class: com.sqwan.liveshow.LiveshowManager.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                LiveshowManager.this.showEmptyLiveshowTips();
                                LiveshowManager.this.callbackInvokeFail(sQResultListener, LiveshowResult.error_inited_room_empty);
                                LiveshowManager.this.joinRoomStatu = JoinRoomStatu.unjoined;
                            }
                        });
                        return;
                    }
                    if (LiveshowManager.this.init()) {
                        LiveshowManager.this.joinRoomStatu = JoinRoomStatu.joined;
                        Task.post(new Runnable() { // from class: com.sqwan.liveshow.LiveshowManager.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                LiveshowManager.this.showLiveShowView(false);
                            }
                        });
                    } else {
                        LiveshowManager.this.joinRoomStatu = JoinRoomStatu.unjoined;
                    }
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onFailure(int i, String str, VolleyError volleyError) {
                    LiveshowManager.this.callbackInvokeFail(sQResultListener, LiveshowResult.error_inited_reqeust);
                    LiveshowManager.this.joinRoomStatu = JoinRoomStatu.unjoined;
                }
            });
        }
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void leaveLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        if (sQResultListener != null) {
            this.leaveRoomListener = sQResultListener;
        }
        leaveRoom();
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
    public void onActive(JSONObject jSONObject) {
        LiveShowUrl.rlapi = jSONObject.optString("rlapi");
        LiveShowUrl.rlcapi = jSONObject.optString("rlcapi");
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public void onSubmitRole() {
        this.liveshowRequestManager.reqGetRadioConfig(new SqHttpCallback.SimpleSqHttpCallback<LiveshowConfig>() { // from class: com.sqwan.liveshow.LiveshowManager.3
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(LiveshowConfig liveshowConfig) {
                try {
                    LogUtil.i(LiveshowManager.this.TAG, "befault LiveshowConfig:" + liveshowConfig);
                    LiveshowManager.this.liveshowConfig.appsecret = new String(AESUtil.decrypt(liveshowConfig.appsecret));
                    LiveshowManager.this.liveshowConfig.appkey = new String(AESUtil.decrypt(liveshowConfig.appkey));
                    LogUtil.i(LiveshowManager.this.TAG, "after LiveshowConfig:" + LiveshowManager.this.liveshowConfig);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLiveShowView(boolean z) {
        if (this.liveshowFloatView == null) {
            LiveshowFloatView liveshowFloatView = new LiveshowFloatView(checkValid());
            this.liveshowFloatView = liveshowFloatView;
            liveshowFloatView.show();
            this.sTime = System.currentTimeMillis();
            if (z) {
                return;
            }
            for (ILiveshowViewEvent iLiveshowViewEvent : this.iLiveshowViewEvents) {
                if (iLiveshowViewEvent != null) {
                    iLiveshowViewEvent.onShowLiveShowView();
                }
            }
        }
    }

    private void releaseLiveShowView() {
        Task.post(new Runnable() { // from class: com.sqwan.liveshow.LiveshowManager.4
            @Override // java.lang.Runnable
            public void run() {
                LiveshowManager.this.leaveRoomActivty();
                LiveshowManager.this.eTime = System.currentTimeMillis();
                for (ILiveshowViewEvent iLiveshowViewEvent : LiveshowManager.this.iLiveshowViewEvents) {
                    if (iLiveshowViewEvent != null) {
                        iLiveshowViewEvent.onReleaseLiveShowView();
                    }
                }
                if (LiveshowManager.this.liveshowFloatView != null) {
                    LiveshowManager.this.liveshowFloatView.release();
                    LiveshowManager.this.liveshowFloatView = null;
                }
            }
        });
    }

    @Override // com.sqwan.liveshow.common.ILiveshowEventWrapper
    public void initCallback(boolean z) {
        if (z) {
            this.listenerManager.joinRoom(getUserId(), getRoomId());
        }
    }

    @Override // com.sqwan.liveshow.common.ILiveshowEventWrapper
    public void joinRoomCallback(boolean z) {
        if (z) {
            this.joinRoomStatu = JoinRoomStatu.joined;
            api.getChannelUserList(getRoomId(), -1, true);
            this.listenerManager.init();
            callbackInvokeSuccess(this.joinRoomListener, null, LiveshowResult.success_joinRoom);
            channelChangeInvokeCallback(true);
            turnOnAction();
            return;
        }
        this.joinRoomStatu = JoinRoomStatu.unjoined;
        callbackInvokeFail(this.joinRoomListener, LiveshowResult.error_joinRoom);
    }

    @Override // com.sqwan.liveshow.common.ILiveshowEventWrapper
    public void leaveRoomCallback(boolean z) {
        if (z) {
            handleLeaveRoomSuccessCallback();
        } else {
            callbackInvokeFail(this.leaveRoomListener, LiveshowResult.error_leaveRoom);
        }
        uninit();
        turnOffAction();
    }

    @Override // com.sqwan.liveshow.common.ILiveshowEventWrapper
    public void channelChange(boolean z) {
        this.isResume = z;
        channelChangeInvokeCallback(z);
    }

    @Override // com.sqwan.liveshow.LiveshowBaseManager
    public void channelChangeInvokeCallback(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(LiveShowParamsKey.isResume, z);
        callbackInvokeSuccess(this.voiceChangeCallback, bundle, LiveshowResult.success_voiceChange);
    }

    @Override // com.sqwan.liveshow.common.ILiveshowEventWrapper
    public void othersMicChange(boolean z, String str) {
        if (z) {
            updateChannelInfo(str);
        }
    }

    private void handleOnlineCount(String str, MemberChange[] memberChangeArr, boolean z) {
        if (this.onlineCount == -1) {
            this.onlineCount = memberChangeArr.length;
        } else {
            for (MemberChange memberChange : memberChangeArr) {
                if (memberChange.isJoin) {
                    this.onlineCount++;
                } else {
                    this.onlineCount--;
                }
            }
        }
        handleMemberChange(this.onlineCount);
    }

    @Override // com.sqwan.liveshow.common.ILiveshowEventWrapper
    public void onMemberChange(String str, MemberChange[] memberChangeArr, boolean z) {
        handleOnlineCount(str, memberChangeArr, z);
        if (TextUtils.equals(str, getRoomId())) {
            for (MemberChange memberChange : memberChangeArr) {
                if (TextUtils.equals(getAnchorId(), memberChange.userID)) {
                    this.isAnchorOnline = memberChange.isJoin;
                    LogUtil.i(this.TAG, "isAnchorOnline " + this.isAnchorOnline);
                    if (!memberChange.isJoin) {
                        for (ILiveshowViewEvent iLiveshowViewEvent : this.iLiveshowViewEvents) {
                            if (iLiveshowViewEvent != null) {
                                iLiveshowViewEvent.checkEmptyRoom();
                            }
                        }
                        return;
                    }
                }
            }
        }
    }

    public LiveshowChannelInfo.ChannelsBean getChannelInfo() {
        LiveshowChannelInfo liveshowChannelInfo = this.liveshowChannelInfo;
        if (liveshowChannelInfo == null || liveshowChannelInfo.getChannels().isEmpty()) {
            return null;
        }
        return this.liveshowChannelInfo.getChannels().get(0);
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowManager
    public String getRoomId() {
        LiveshowChannelInfo.ChannelsBean channelInfo = getChannelInfo();
        return channelInfo != null ? channelInfo.getCid() : "";
    }

    public void turnOnAction() {
        this.sTimeReally = SystemClock.uptimeMillis();
        LiveshowTrackManager.getInstance().turnOnAction(getRoomId(), getAnchorId());
    }

    public void turnOffAction() {
        if (this.isResume) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            this.eTimeReally = jUptimeMillis;
            long j = jUptimeMillis - this.sTimeReally;
            LiveshowTrackManager.getInstance().turnOffAction(getRoomId(), getAnchorId(), j + "");
        }
    }

    public String getAnchorId() {
        LiveshowChannelInfo.ChannelsBean channelInfo = getChannelInfo();
        return channelInfo != null ? channelInfo.getAnchor_id() : "";
    }

    public String getAnchorName() {
        LiveshowChannelInfo.ChannelsBean channelInfo = getChannelInfo();
        return channelInfo != null ? channelInfo.getAnchor_name() : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void close() {
        leaveRoom();
    }

    public void register(ILiveshowEventWrapper iLiveshowEventWrapper) {
        if (this.liveshowEventManager.liveshowEventWrapperDispatcher != null) {
            this.liveshowEventManager.liveshowEventWrapperDispatcher.register(iLiveshowEventWrapper);
        }
    }

    public void unregister(ILiveshowEventWrapper iLiveshowEventWrapper) {
        if (this.liveshowEventManager.liveshowEventWrapperDispatcher != null) {
            this.liveshowEventManager.liveshowEventWrapperDispatcher.unregister(iLiveshowEventWrapper);
        }
    }

    private void updateChannelInfo(String str) {
        LiveshowRequestManager liveshowRequestManager = this.liveshowRequestManager;
        if (liveshowRequestManager != null) {
            liveshowRequestManager.reqGetRadioChannel(getRoomId(), new SqHttpCallback.SimpleSqHttpCallback<LiveshowChannelInfo.ChannelsBean>() { // from class: com.sqwan.liveshow.LiveshowManager.5
                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(LiveshowChannelInfo.ChannelsBean channelsBean) {
                    String anchor_id = channelsBean.getAnchor_id();
                    if (anchor_id.isEmpty()) {
                        return;
                    }
                    if (!LiveshowManager.this.liveshowChannelInfo.getChannels().isEmpty()) {
                        LiveshowChannelInfo.ChannelsBean channelsBean2 = LiveshowManager.this.liveshowChannelInfo.getChannels().get(0);
                        channelsBean2.setAnchor_id(anchor_id);
                        channelsBean2.setAnchor_name(channelsBean.getAnchor_name());
                        channelsBean2.setAnchor_avatar(channelsBean.getAnchor_avatar());
                    }
                    for (ILiveshowViewEvent iLiveshowViewEvent : LiveshowManager.this.iLiveshowViewEvents) {
                        if (iLiveshowViewEvent != null) {
                            iLiveshowViewEvent.update(channelsBean);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmptyLiveshowTips() {
        Task.post(new Runnable() { // from class: com.sqwan.liveshow.LiveshowManager.6
            @Override // java.lang.Runnable
            public void run() {
                new CommonAlertDialog.Builder(LiveshowManager.this.checkValid()).setTitle("主播未开播").setMessage("主播走开啦，请稍后再来").setPositiveButton("确认", null).showEx();
            }
        });
    }

    public void close(Context context) {
        if (context instanceof Activity) {
            new CommonAlertDialog.Builder((Activity) context).setTitle("退出电台").setMessage("退出将不再收听电台音频").setPositiveButton("退出", new View.OnClickListener() { // from class: com.sqwan.liveshow.LiveshowManager.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LiveshowManager.getInstance().close();
                }
            }).setNegativeButton("取消", null).showEx();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(Activity activity) {
        activity.finish();
    }

    public void minimize(Context context, final MinimizeCallback minimizeCallback) {
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (!getInstance().isAnchorOnline) {
                finish(activity);
                return;
            }
            Dialog dialogShowEx = new CommonAlertDialog.Builder(activity).setTitle("退出电台").setMessage("退出将不再收听电台音频").setNegativeButton("退出", new View.OnClickListener() { // from class: com.sqwan.liveshow.LiveshowManager.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MinimizeCallback minimizeCallback2 = minimizeCallback;
                    if (minimizeCallback2 != null) {
                        minimizeCallback2.callbcak(false, true);
                    }
                    LiveshowManager.getInstance().close();
                    LiveshowManager.this.finish(activity);
                }
            }).setPositiveButton("最小化", new View.OnClickListener() { // from class: com.sqwan.liveshow.LiveshowManager.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MinimizeCallback minimizeCallback2 = minimizeCallback;
                    if (minimizeCallback2 != null) {
                        minimizeCallback2.callbcak(false, true);
                    }
                    LiveshowManager.this.finish(activity);
                }
            }).setmCanceledOnTouchOutside(true).setCancelable(true).showEx();
            if (minimizeCallback != null) {
                minimizeCallback.callbcak(true, false);
            }
            dialogShowEx.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sqwan.liveshow.LiveshowManager.10
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    MinimizeCallback minimizeCallback2;
                    if (activity.isFinishing() || (minimizeCallback2 = minimizeCallback) == null) {
                        return;
                    }
                    minimizeCallback2.callbcak(false, false);
                }
            });
        }
    }
}
