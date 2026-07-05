package com.sqwan.liveshow.huya.danmu;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.sq.tool.network.SqHttpCallback;
import com.sq.websocket_engine.ARecInfMsg;
import com.sq.websocket_engine.ReqWrapperHandler;
import com.sq.websocket_engine.WebSocketEngine;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.base.L;
import com.sqwan.common.mod.CommonConfigs;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.TimeUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.request.DanmuImRequestManager;
import com.sqwan.liveshow.huya.request.DanmuRequestManager;
import com.sqwan.liveshow.huya.request.DanmuRoomRequestManager;
import com.sqwan.liveshow.huya.request.bean.danmu.http.EnterRoomReqBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.EnterRoomRspBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.FetchImRspBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.SendImReqBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.SendImRspBean;
import com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.AImMsgFactory;
import com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.ARecInfMsgDanmuFactory;
import com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.RoomRecInfMsg;
import com.sqwan.liveshow.huya.trackaction.LiveshowTrackManager;
import com.sqwan.liveshow.huya.view.LiveshowFloatView;
import com.sqwan.liveshow.huya.view.OnBehaviorListener;
import com.sqwan.liveshow.huya.view.OnBehaviorListenerAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowDanmuManager implements WebSocketEngine.WebSocketEngineCallback {
    private static final String TAG = "LiveshowDanmuManager";
    private static final LiveshowDanmuManager ourInstance = new LiveshowDanmuManager();
    public long firstJoinRoomTime;
    private Task taskSendImLimit = Task.create();
    private String inputTxtTemp = "";
    private final long taskSendImLimitDuration = 1;
    private final long taskSendImLimitCount = 3;
    private long taskSendImLimitCountDown = 3;
    private boolean isImListShow = true;
    private boolean isDanmuShow = true;
    private boolean hasFetchIm = false;
    private List<SendImLimitListener> sendImLimitListeners = new ArrayList();
    private ARecInfMsgDanmuFactory aRecInfMsgDanmuFactory = new ARecInfMsgDanmuFactory();
    public OnBehaviorListener onBehaviorListener = new OnBehaviorListenerAdapter() { // from class: com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager.4
        @Override // com.sqwan.liveshow.huya.view.OnBehaviorListenerAdapter, com.sqwan.liveshow.huya.view.OnBehaviorListener
        public void showChatList(boolean z) {
            LiveshowDanmuManager.this.setImListShow(z);
            LiveshowDanmuManager.this.uploadLiveShowSwitchAction(false, !z);
        }

        @Override // com.sqwan.liveshow.huya.view.OnBehaviorListenerAdapter, com.sqwan.liveshow.huya.view.OnBehaviorListener
        public void showDanmu(boolean z) {
            if (z) {
                ToastUtil.showToast("弹幕已开启");
            } else {
                ToastUtil.showToast("弹幕已关闭");
            }
            LiveshowDanmuManager.this.setDanmuShow(z);
        }
    };
    public DanmuRequestManager danmuRequestManager = new DanmuRequestManager();
    public DanmuImRequestManager danmuImRequestManager = new DanmuImRequestManager(L.getApplicationContext());
    public DanmuRoomRequestManager danmuRoomRequestManager = new DanmuRoomRequestManager(L.getApplicationContext());

    public interface SendImLimitListener {
        void onCountDown(boolean z, String str);
    }

    public DanmuRequestManager getDanmuRequestManager() {
        return this.danmuRequestManager;
    }

    public static LiveshowDanmuManager getInstance() {
        return ourInstance;
    }

    public String getInputTxtTemp() {
        return this.inputTxtTemp;
    }

    public void setInputTxtTemp(String str) {
        this.inputTxtTemp = str;
    }

    public boolean isDanmuShow() {
        return this.isDanmuShow;
    }

    public void setDanmuShow(boolean z) {
        this.isDanmuShow = z;
    }

    public boolean isImListShow() {
        return this.isImListShow;
    }

    public void setImListShow(boolean z) {
        this.isImListShow = z;
    }

    private LiveshowDanmuManager() {
    }

    @Override // com.sq.websocket_engine.WebSocketEngine.WebSocketEngineCallback
    public void onAuth() {
        handleRejoinRoom();
    }

    @Override // com.sq.websocket_engine.WebSocketEngine.WebSocketEngineCallback
    public void onReceiveInf(ARecInfMsg aRecInfMsg) {
        handleMsgInf(aRecInfMsg);
    }

    private boolean checkSelf(long j) {
        return TextUtils.equals(j + "", LiveshowManager.getInstance().getUserId());
    }

    private void handleMsgInf(ARecInfMsg aRecInfMsg) {
        if (aRecInfMsg instanceof RoomRecInfMsg) {
            if (!this.hasFetchIm) {
                LogUtil.i(TAG, "hasFetchIm return");
                return;
            }
            List<FetchImRspBean.ImMsg> inf = ((RoomRecInfMsg) aRecInfMsg).getInf();
            ArrayList arrayList = new ArrayList();
            for (FetchImRspBean.ImMsg imMsg : inf) {
                if (!TextUtils.isEmpty(imMsg.uid + "") && !checkSelf(imMsg.uid)) {
                    long j = getInstance().firstJoinRoomTime;
                    LogUtil.i(TAG, String.format("filter time outdate %s %s", TimeUtils.millis2String(j * 1000), TimeUtils.millis2String(imMsg.time * 1000)));
                    if (j <= imMsg.time) {
                        arrayList.add(imMsg);
                    }
                }
            }
            LiveshowMsgDispatcher.getInstance().addLiveshowMsgs(true, arrayList);
        }
    }

    public void regSendImLimitListener(SendImLimitListener sendImLimitListener) {
        this.sendImLimitListeners.add(sendImLimitListener);
    }

    public void unRegSendImLimitListener(SendImLimitListener sendImLimitListener) {
        this.sendImLimitListeners.remove(sendImLimitListener);
    }

    private void initRoomData() {
        this.isDanmuShow = true;
        this.isImListShow = true;
        this.hasFetchIm = false;
        this.firstJoinRoomTime = 0L;
    }

    public void joinRoom() {
        initRoomData();
        if (this.isImListShow) {
            LiveshowTrackManager.getInstance().trackHalfScreenOnAction();
        }
        LiveshowMsgDispatcher.getInstance().init();
        fetchIm();
        WebSocketEngine.getInstance().registerWebSocketEngineCallback(this);
    }

    public void joinLiveShow() {
        LogUtil.i(TAG, "joinLiveShow");
        WebSocketEngine.getInstance().addARecInfMsgBaseFactory(this.aRecInfMsgDanmuFactory);
    }

    public void leaveRoom() {
        LogUtil.i(TAG, "leaveRoom");
        if (!LiveshowManager.getInstance().isLiveshow()) {
            LogUtil.i(TAG, "leaveRoom return");
            return;
        }
        uploadLiveShowSwitchAction(true, this.isImListShow);
        initRoomData();
        LiveshowMsgDispatcher.getInstance().release();
        this.danmuRoomRequestManager.leaveRoom(null);
        this.danmuRequestManager.reqLeaveRoom(LiveshowManager.getInstance().getRoomId(), null);
        this.taskSendImLimit.stop();
        WebSocketEngine.getInstance().unregisterWebSocketEngineCallback(this);
    }

    public void leaveLiveShow() {
        LogUtil.i(TAG, "leaveLiveShow");
        if (!LiveshowManager.getInstance().isLiveshowList() && !LiveshowManager.getInstance().isLiveshow()) {
            LogUtil.i(TAG, "leaveLiveShow return");
        } else {
            leaveRoom();
            WebSocketEngine.getInstance().removeARecInfMsgBaseFactory(this.aRecInfMsgDanmuFactory);
        }
    }

    public void fetchIm() {
        LiveshowMsgDispatcher.getInstance().addLiveshowImMsg(false, true, new AImMsgFactory().buidNotification());
        getInstance().danmuImRequestManager.fetchIm(new SqHttpCallback<FetchImRspBean>() { // from class: com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(FetchImRspBean fetchImRspBean) {
                if (fetchImRspBean != null && fetchImRspBean.items != null && !fetchImRspBean.items.isEmpty()) {
                    LiveshowMsgDispatcher.getInstance().addLiveshowMsgs(false, fetchImRspBean.items);
                }
                onFinish();
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                onFinish();
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                onFinish();
            }

            private void onFinish() {
                LiveshowMsgDispatcher.getInstance().flush();
                LiveshowDanmuManager.this.hasFetchIm = true;
                LiveshowDanmuManager.this.firstJoinRoomTime = CommonConfigs.getInstance().getCurrentTime();
                LiveshowDanmuManager.getInstance().getDanmuRequestManager().reqJoinRoom(LiveshowManager.getInstance().getRoomId(), null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleImLimitTask() {
        this.taskSendImLimitCountDown = 4L;
        this.taskSendImLimit.repeat(1000L, new Task.TaskFunc() { // from class: com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager.2
            @Override // com.sqwan.common.util.task.Task.TaskFunc
            public Task.Result exec() {
                LiveshowDanmuManager.this.taskSendImLimitCountDown--;
                LogUtil.i(LiveshowDanmuManager.TAG, "handleImLimitTask taskSendImLimitCount:" + LiveshowDanmuManager.this.taskSendImLimitCountDown);
                boolean z = LiveshowDanmuManager.this.taskSendImLimitCountDown == 0;
                for (SendImLimitListener sendImLimitListener : LiveshowDanmuManager.this.sendImLimitListeners) {
                    if (sendImLimitListener != null) {
                        sendImLimitListener.onCountDown(z, LiveshowDanmuManager.this.taskSendImLimitCountDown + "");
                    }
                }
                if (z) {
                    return Task.Result.Stop;
                }
                return Task.Result.Next;
            }
        });
    }

    public void sendIm(String str) {
        final SendImReqBean sendImReqBean = new SendImReqBean(LiveshowManager.getInstance().getUsernick(), str);
        this.danmuImRequestManager.sendIm(sendImReqBean, new SqHttpCallback<SendImRspBean>() { // from class: com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager.3
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(SendImRspBean sendImRspBean) {
                if (!sendImRspBean.isForbid()) {
                    LiveshowMsgDispatcher.getInstance().addLiveshowMsg(true, true, new AImMsgFactory().buildSelfMsg(sendImReqBean));
                    LiveshowDanmuManager.this.handleImLimitTask();
                }
                LiveshowTrackManager.getInstance().bulletChatAction(sendImReqBean.msg.content, sendImRspBean.forbid + "", 1, LiveshowDanmuManager.getInstance().isImListShow);
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                ToastUtil.showToast(str2);
                LiveshowTrackManager.getInstance().bulletChatAction(sendImReqBean.msg.content, "", i2, LiveshowDanmuManager.getInstance().isImListShow);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                ToastUtil.showToast(str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadLiveShowSwitchAction(boolean z, boolean z2) {
        if (z2) {
            LiveshowTrackManager.getInstance().trackHalfScreenOffAction();
            if (z) {
                return;
            }
            LiveshowTrackManager.getInstance().trackFullScreenOnAction();
            return;
        }
        LiveshowTrackManager.getInstance().trackFullScreenOffAction();
        if (z) {
            return;
        }
        LiveshowTrackManager.getInstance().trackHalfScreenOnAction();
    }

    private void handleRejoinRoom() {
        if (LiveshowManager.getInstance().isLiveshow()) {
            String roomId = LiveshowManager.getInstance().getRoomId();
            if (TextUtils.isEmpty(roomId)) {
                return;
            }
            this.danmuRoomRequestManager.enterRoom(new EnterRoomReqBean(roomId), new SqHttpCallback.SimpleSqHttpCallback<EnterRoomRspBean>() { // from class: com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager.5
                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(EnterRoomRspBean enterRoomRspBean) {
                    LogUtil.i(LiveshowDanmuManager.TAG, "enterRoom result:" + enterRoomRspBean.isForbid());
                }
            });
            getInstance().getDanmuRequestManager().reqJoinRoom(LiveshowManager.getInstance().getRoomId(), new ReqWrapperHandler.FinishListener<Boolean>() { // from class: com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager.6
                @Override // com.sq.websocket_engine.ReqWrapperHandler.FinishListener
                public void on(Boolean bool) {
                    LogUtil.i(LiveshowDanmuManager.TAG, "reqJoinRoom result:" + bool);
                }
            });
        }
    }

    public void showSoftkeyboardPadding(int i) {
        View view;
        LiveshowFloatView liveshowFloatView = LiveshowManager.getInstance().getLiveshowFloatView();
        if (liveshowFloatView == null || (view = liveshowFloatView.liveshow_softkeyboard_padding) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
        ViewUtils.show(view);
    }

    public void hideSoftkeyboardPadding() {
        View view;
        LiveshowFloatView liveshowFloatView = LiveshowManager.getInstance().getLiveshowFloatView();
        if (liveshowFloatView == null || (view = liveshowFloatView.liveshow_softkeyboard_padding) == null) {
            return;
        }
        ViewUtils.gone(view);
    }
}
