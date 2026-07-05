package com.huya.berry.sdkplayer.floats.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.kiwi.barrage.BarrageGLSurfaceViewForFloating;
import com.duowan.kiwi.base.smile.DefaultSmile;
import com.duowan.networkmars.hysignal.HySignalProxy;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.commonevent.Event_Biz;
import com.huya.berry.module.pubtext.ChatText;
import com.huya.mtp.hyns.api.NSRegisterApi;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DanmuView {
    private static final String TAG = "DanmuView";
    private boolean isShow = false;
    private BarrageGLSurfaceViewForFloating mGLFloatingBarrage;
    private long mPresenterUid;
    private boolean mReceiveDanmuData;
    private View mainView;
    private FrameLayout parentLayout;

    public DanmuView(FrameLayout frameLayout) {
        this.parentLayout = frameLayout;
    }

    public void show(long j) {
        if (this.isShow) {
            return;
        }
        this.mPresenterUid = j;
        L.info(TAG, "DanmuView show");
        this.isShow = true;
        if (this.parentLayout != null) {
            configView();
        }
        addEvents();
    }

    protected void configView() {
        View viewInflate = LayoutInflater.from(this.parentLayout.getContext()).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_view_danmu), (ViewGroup) this.parentLayout, false);
        this.mainView = viewInflate;
        this.parentLayout.addView(viewInflate, 0);
        this.mGLFloatingBarrage = (BarrageGLSurfaceViewForFloating) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.gl_floating_barrage));
        switchBarrage(true);
    }

    protected void addEvents() {
        ArkUtils.register(this);
        SdkProperties.isCustomReceiveDanmu.set(true);
        registerBroadcastByChannel();
        BarrageGLSurfaceViewForFloating barrageGLSurfaceViewForFloating = this.mGLFloatingBarrage;
        if (barrageGLSurfaceViewForFloating != null) {
            barrageGLSurfaceViewForFloating.register();
        }
    }

    private void registerBroadcastByChannel() {
        HySignalProxy.getInstance().registerLiveGroups(this.mPresenterUid, new NSRegisterApi.RegisterPushMsgListener() { // from class: com.huya.berry.sdkplayer.floats.view.DanmuView.1
            @Override // com.huya.mtp.hyns.api.NSRegisterApi.RegisterPushMsgListener
            public void onRegisterSucceed(NSRegisterApi.RegistResultInfo registResultInfo) {
                L.info(DanmuView.TAG, "Mars:onRegisterSucceed ...");
            }

            @Override // com.huya.mtp.hyns.api.NSRegisterApi.RegisterPushMsgListener
            public void onRegisterFailed(NSRegisterApi.RegistResultInfo registResultInfo) {
                L.error(DanmuView.TAG, "Mars:onRegisterFailed %d", Integer.valueOf(registResultInfo.getStatus()));
            }
        });
    }

    private void unregisterBroadcastByChannel() {
        HySignalProxy.getInstance().unRegisterLiveGroups(this.mPresenterUid);
    }

    protected void removeEvents() {
        ArkUtils.unregister(this);
        if (!this.mReceiveDanmuData) {
            SdkProperties.isCustomReceiveDanmu.set(false);
            unregisterBroadcastByChannel();
        }
        BarrageGLSurfaceViewForFloating barrageGLSurfaceViewForFloating = this.mGLFloatingBarrage;
        if (barrageGLSurfaceViewForFloating != null) {
            barrageGLSurfaceViewForFloating.openOrNot(false);
            this.mGLFloatingBarrage.unregister();
        }
    }

    public void setReceiveDanmuData(boolean z) {
        this.mReceiveDanmuData = z;
    }

    public void hide() {
        if (this.isShow) {
            L.info(TAG, "DanmuView hide");
            removeEvents();
            clear();
        }
    }

    private void clear() {
        if (this.isShow) {
            this.isShow = false;
            FrameLayout frameLayout = this.parentLayout;
            if (frameLayout != null) {
                frameLayout.removeAllViews();
            }
            BarrageGLSurfaceViewForFloating barrageGLSurfaceViewForFloating = this.mGLFloatingBarrage;
            if (barrageGLSurfaceViewForFloating != null) {
                barrageGLSurfaceViewForFloating.removeCallbacks(null);
                if (this.mGLFloatingBarrage.getParent() != null) {
                    ((RelativeLayout) this.mGLFloatingBarrage.getParent()).removeAllViews();
                }
                this.mGLFloatingBarrage = null;
            }
            this.mainView = null;
        }
    }

    public void onDestroy() {
        hide();
        this.parentLayout = null;
    }

    public void switchBarrage(boolean z) {
        BarrageGLSurfaceViewForFloating barrageGLSurfaceViewForFloating = this.mGLFloatingBarrage;
        if (barrageGLSurfaceViewForFloating != null) {
            barrageGLSurfaceViewForFloating.openOrNot(z);
        }
        BarrageGLSurfaceViewForFloating barrageGLSurfaceViewForFloating2 = this.mGLFloatingBarrage;
        if (barrageGLSurfaceViewForFloating2 != null) {
            if (z) {
                barrageGLSurfaceViewForFloating2.register();
            } else {
                barrageGLSurfaceViewForFloating2.unregister();
            }
        }
    }

    @IASlot(executorID = 1)
    public void onSendPublicText(Event_Biz.SendPublicText sendPublicText) {
        if (this.mGLFloatingBarrage != null) {
            if (DefaultSmile.hasSmile(sendPublicText.ct.text)) {
                this.mGLFloatingBarrage.addBarrageWithFace(sendPublicText.ct);
            } else {
                this.mGLFloatingBarrage.onPubText(sendPublicText.ct);
            }
        }
    }

    @IASlot(executorID = 1)
    public void onTextAboutToSendV2(Event_Biz.TextAboutToSendV2 textAboutToSendV2) {
        if (this.mGLFloatingBarrage != null) {
            if (DefaultSmile.hasSmile(textAboutToSendV2.text)) {
                ChatText chatText = new ChatText();
                chatText.uid = textAboutToSendV2.uid;
                chatText.nickname = textAboutToSendV2.nickname;
                chatText.text = textAboutToSendV2.text;
                chatText.avatar = textAboutToSendV2.avatar;
                chatText.barrageColor = textAboutToSendV2.barrageColor;
                this.mGLFloatingBarrage.addBarrageWithFace(chatText);
                return;
            }
            this.mGLFloatingBarrage.onTextAboutToSend(textAboutToSendV2);
        }
    }

    @IASlot(executorID = 1)
    public void onSwitchDanmu(CommonEvent.SwitchDanmu switchDanmu) {
        PlayerHelper.isOpenDanmu = switchDanmu.open;
        switchBarrage(switchDanmu.open);
    }
}
