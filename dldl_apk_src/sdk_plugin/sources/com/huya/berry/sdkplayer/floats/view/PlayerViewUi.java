package com.huya.berry.sdkplayer.floats.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.duowan.HUYA.GetRelationRsp;
import com.duowan.HUYA.ModRelationRsp;
import com.duowan.HUYA.ScreenType;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.login.api.ILoginService;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.widgets.LiveAlert;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.live.ISdkLiveService;
import com.huya.berry.sdkplayer.floats.PlayerPresenterImpl;
import com.huya.component.login.LoginProperties;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.huya.live.utils.image.ImageBind;
import com.sqwan.liveshow.huya.SqR;
import io.reactivex.android.schedulers.AndroidSchedulers;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PlayerViewUi implements IPlayerView {
    private static final String TAG = "PlayerViewUi";
    private boolean isShow = false;
    public Activity mActivity;
    private DanmuView mDanmuView;
    private ImageView mFloatingFullScreen;
    private ImageView mFloatingVoiceClose;
    private boolean mIsFullScreen;
    private ImageView mIvAvatar;
    private ImageView mIvBack;
    private ImageView mIvDanmu;
    private ImageView mIvSendMsg;
    private LinearLayout mLlAnchor;
    private LinearLayout mLlLeft;
    private LinearLayout mLlRight;
    private LinearLayout mLlSubscribe;
    private LinearLayout mLlTitle;
    protected PlayerPresenterImpl mPlayerPresenterImpl;
    private PlayerView mPlayerView;
    private TextView mTvTitle;
    private View mainView;
    private ViewGroup parentLayout;
    private TextView tvAnchorName;
    private TextView tvAudienceCount;
    private TextView tvHasSubscribe;
    private EditText tvInput;
    private TextView tvRate;
    private TextView tvSubscribe;

    protected void addEvents() {
    }

    protected void removeEvents() {
    }

    public PlayerViewUi(ViewGroup viewGroup) {
        this.parentLayout = viewGroup;
    }

    public void show() {
        if (this.isShow) {
            return;
        }
        L.info(TAG, "PlayerView show");
        this.isShow = true;
        if (this.parentLayout != null) {
            configView();
        }
        addEvents();
    }

    protected void configView() {
        View viewInflate = LayoutInflater.from(this.parentLayout.getContext()).inflate(ResourceUtil.getLayoutResIDByName(PlayerHelper.mScreenType == ScreenType.ST_Horizonal ? SqR.layout.hyberry_view_player_ui : SqR.layout.hyberry_view_player_ui_portait), this.parentLayout, false);
        this.mainView = viewInflate;
        this.parentLayout.addView(viewInflate);
        this.mLlLeft = (LinearLayout) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_left));
        this.mLlRight = (LinearLayout) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_right));
        this.mLlTitle = (LinearLayout) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_title));
        this.mLlAnchor = (LinearLayout) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_anchor));
        this.mIvBack = (ImageView) this.mLlTitle.findViewById(ResourceUtil.getIdResIDByName("iv_back"));
        this.mTvTitle = (TextView) this.mLlTitle.findViewById(ResourceUtil.getIdResIDByName("tv_title"));
        this.mIvAvatar = (ImageView) this.mLlAnchor.findViewById(ResourceUtil.getIdResIDByName("iv_avatar"));
        this.tvAnchorName = (TextView) this.mLlAnchor.findViewById(ResourceUtil.getIdResIDByName("tv_anchor_name"));
        this.tvAudienceCount = (TextView) this.mLlAnchor.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_audience_count));
        this.tvRate = (TextView) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_rate));
        this.tvInput = (EditText) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_input));
        this.mIvDanmu = (ImageView) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_danmu));
        this.mIvSendMsg = (ImageView) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_send_msg));
        this.mLlSubscribe = (LinearLayout) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_subscribe));
        this.tvSubscribe = (TextView) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_subscribe));
        this.tvHasSubscribe = (TextView) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_has_subscribe));
        this.mFloatingVoiceClose = (ImageView) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_voice_close));
        this.mFloatingFullScreen = (ImageView) this.mainView.findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_fullScreen));
        PlayerPresenterImpl playerPresenterImpl = new PlayerPresenterImpl(this);
        this.mPlayerPresenterImpl = playerPresenterImpl;
        playerPresenterImpl.onCreate();
        setInfo();
        this.parentLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PlayerViewUi.this.mPlayerPresenterImpl == null) {
                    return;
                }
                if (PlayerViewUi.this.mLlLeft.getVisibility() == 0) {
                    PlayerViewUi.this.mPlayerPresenterImpl.hideView();
                } else {
                    PlayerViewUi.this.mPlayerPresenterImpl.showView();
                }
            }
        });
        this.mIvDanmu.setSelected(!PlayerHelper.isOpenDanmu);
        this.mFloatingVoiceClose.setSelected(!PlayerHelper.isOpenMute);
        this.mIvDanmu.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PlayerViewUi.this.mPlayerPresenterImpl == null) {
                    return;
                }
                PlayerViewUi.this.mPlayerPresenterImpl.showView();
                PlayerViewUi.this.mPlayerPresenterImpl.switchDanmu();
            }
        });
        this.mIvSendMsg.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PlayerViewUi.this.mPlayerPresenterImpl == null || PlayerHelper.mActivity == null) {
                    return;
                }
                PlayerViewUi.this.mPlayerPresenterImpl.showView();
                FlotingEditFragment.getInstance(PlayerHelper.mActivity.getFragmentManager()).show(PlayerHelper.mActivity.getFragmentManager(), FlotingEditFragment.TAG);
            }
        });
        this.mFloatingFullScreen.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                L.info(PlayerViewUi.TAG, "enter onClick, fullscreen");
                ArkUtils.send(new CommonEvent.FullScreen());
                ArkUtils.send(new CommonEvent.FullScreenCloseFloating(true));
                Intent intent = new Intent(ArkValue.gContext, (Class<?>) (PlayerHelper.mScreenType == ScreenType.ST_Horizonal ? PlayerActivity.class : PortraitPlayerActivity.class));
                intent.addFlags(268435456);
                ArkValue.gContext.startActivity(intent);
                Report.event(SdkReportConst.LIVE_SMALLWINDOW_FULLSCREEN);
            }
        });
        this.mFloatingVoiceClose.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PlayerViewUi.this.mPlayerPresenterImpl == null) {
                    return;
                }
                PlayerViewUi.this.mPlayerPresenterImpl.showView();
                PlayerViewUi.this.mPlayerPresenterImpl.switchVoice();
            }
        });
        this.tvRate.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PlayerViewUi.this.mPlayerPresenterImpl == null) {
                    return;
                }
                PlayerViewUi.this.mPlayerPresenterImpl.showView();
                ArkUtils.send(new CommonEvent.OpenLine());
            }
        });
        this.mIvBack.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArkUtils.send(new CommonEvent.BackFloating());
            }
        });
        this.mLlTitle.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArkUtils.send(new CommonEvent.BackFloating());
            }
        });
        this.tvInput.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArkUtils.send(new CommonEvent.OpenFloatEdit(PlayerViewUi.this.tvInput));
            }
        });
        this.tvSubscribe.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayerViewUi.this.subscribe();
            }
        });
        this.tvHasSubscribe.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayerViewUi.this.deleteSubscribeAlert();
                Report.event(SdkReportConst.USER_CLICK_UNSUBSCRIBE_FULLSCREENLIVE, SdkReportConst.USER_CLICK_UNSUBSCRIBE_FULLSCREENLIVE_DES);
            }
        });
        this.mLlTitle.setVisibility(SdkProperties.showLiveInfo.get().booleanValue() ? 0 : 8);
        this.mLlAnchor.setVisibility(SdkProperties.showLiveInfo.get().booleanValue() ? 0 : 8);
        this.tvInput.setVisibility(SdkProperties.normalSendDanmu.get().booleanValue() ? 0 : 8);
        this.tvRate.setVisibility(SdkProperties.showQuality.get().booleanValue() ? 0 : 8);
        this.mIvSendMsg.setVisibility(SdkProperties.floatSendDanmu.get().booleanValue() ? 0 : 8);
        this.mFloatingFullScreen.setVisibility(SdkProperties.showFullScreen.get().booleanValue() ? 0 : 8);
        this.mIvDanmu.setVisibility(SdkProperties.showSwitchDanmu.get().booleanValue() ? 0 : 8);
        this.mFloatingVoiceClose.setVisibility(SdkProperties.showSwitchVoice.get().booleanValue() ? 0 : 8);
        this.mLlSubscribe.setVisibility(SdkProperties.showSubscribe.get().booleanValue() ? 0 : 8);
        if (LoginProperties.uid.get().longValue() != 0) {
            checkSubscribeStatus();
        }
    }

    private void checkSubscribeStatus() {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.getRelation(PlayerHelper.presenterUid).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<GetRelationRsp>() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.12
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetRelationRsp getRelationRsp) {
                    if (getRelationRsp.tItem.iRelation == 1) {
                        PlayerViewUi.this.tvSubscribe.setVisibility(8);
                        PlayerViewUi.this.tvHasSubscribe.setVisibility(0);
                    } else {
                        PlayerViewUi.this.tvSubscribe.setVisibility(0);
                        PlayerViewUi.this.tvHasSubscribe.setVisibility(8);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void subscribe() {
        if (LoginProperties.loginState.get() != LoginProperties.LoginState.LoggedIn || LoginProperties.uid.get().longValue() == 0) {
            ILoginService iLoginService = (ILoginService) ServiceCenter.instance().getService(ILoginService.class);
            if (iLoginService == null) {
                return;
            }
            Activity activity = this.mActivity;
            if (activity == null) {
                activity = PlayerHelper.mActivity;
            }
            iLoginService.login(activity);
            return;
        }
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.addSubscribe(PlayerHelper.presenterUid).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<ModRelationRsp>() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.13
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(ModRelationRsp modRelationRsp) {
                    if (modRelationRsp.iNewRelation == 1) {
                        PlayerViewUi.this.tvSubscribe.setVisibility(8);
                        PlayerViewUi.this.tvHasSubscribe.setVisibility(0);
                    } else {
                        PlayerViewUi.this.tvSubscribe.setVisibility(0);
                        PlayerViewUi.this.tvHasSubscribe.setVisibility(8);
                    }
                }
            });
            Report.event(SdkReportConst.USER_CLICK_SUBSCRIBE_FULLSCREENLIVE, SdkReportConst.USER_CLICK_SUBSCRIBE_FULLSCREENLIVE_DES);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteSubscribeAlert() {
        Activity activity = this.mActivity;
        if (activity == null) {
            activity = PlayerHelper.mActivity;
        }
        new LiveAlert.Builder(activity).message("是否确认取消订阅").positive("是").negative("否").onClickListener(new DialogInterface.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (i == -1) {
                        PlayerViewUi.this.deleteSubscribe();
                        jSONObject.put("status_", "confirm");
                    } else {
                        jSONObject.put("status_", "cancel");
                    }
                    dialogInterface.dismiss();
                    Report.event(SdkReportConst.USER_CLICK_UNSUBSCRIBE_CONFIRMWINDOW, SdkReportConst.USER_CLICK_UNSUBSCRIBE_CONFIRMWINDOW_DES, "", jSONObject.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteSubscribe() {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.delSubscribe(PlayerHelper.presenterUid).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<ModRelationRsp>() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerViewUi.15
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(ModRelationRsp modRelationRsp) {
                    if (modRelationRsp.iNewRelation == 1) {
                        PlayerViewUi.this.tvSubscribe.setVisibility(8);
                        PlayerViewUi.this.tvHasSubscribe.setVisibility(0);
                    } else {
                        PlayerViewUi.this.tvSubscribe.setVisibility(0);
                        PlayerViewUi.this.tvHasSubscribe.setVisibility(8);
                    }
                }
            });
        }
    }

    public void setInfo() {
        this.mTvTitle.setText(PlayerHelper.title);
        this.tvRate.setText(PlayerHelper.disPlayName);
        this.tvAnchorName.setText(PlayerHelper.liveAnchorName);
        this.tvAudienceCount.setText(PlayerHelper.audienceCount + "");
        ImageBind.displayCircle(ArkValue.gContext, this.mIvAvatar, PlayerHelper.liveAnchorAvatar, ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_unlogin_icon));
    }

    public void setDanmuView(DanmuView danmuView) {
        this.mDanmuView = danmuView;
    }

    public void setPlayerView(PlayerView playerView) {
        this.mPlayerView = playerView;
    }

    public LinearLayout getLlLeft() {
        return this.mLlLeft;
    }

    public PlayerPresenterImpl getPlayerPresenterImpl() {
        return this.mPlayerPresenterImpl;
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    @Override // com.huya.berry.sdkplayer.floats.view.IPlayerView
    public void showView() {
        LinearLayout linearLayout = this.mLlLeft;
        if (linearLayout == null) {
            return;
        }
        linearLayout.setVisibility(0);
        this.mLlRight.setVisibility(0);
        if (this.mIsFullScreen) {
            if (SdkProperties.showLiveInfo.get().booleanValue()) {
                this.mLlTitle.setVisibility(0);
                this.mLlAnchor.setVisibility(0);
            }
            if (SdkProperties.showSubscribe.get().booleanValue()) {
                this.mLlSubscribe.setVisibility(0);
            }
        }
    }

    @Override // com.huya.berry.sdkplayer.floats.view.IPlayerView
    public void hideView() {
        LinearLayout linearLayout = this.mLlLeft;
        if (linearLayout == null) {
            return;
        }
        linearLayout.setVisibility(8);
        this.mLlRight.setVisibility(8);
        if (this.mIsFullScreen) {
            this.mLlTitle.setVisibility(8);
            this.mLlAnchor.setVisibility(8);
            this.mLlSubscribe.setVisibility(8);
        }
    }

    public void setViewVisible(boolean z) {
        this.mIsFullScreen = z;
        if (z) {
            if (SdkProperties.showLiveInfo.get().booleanValue()) {
                this.mLlTitle.setVisibility(0);
                this.mLlAnchor.setVisibility(0);
            }
            if (SdkProperties.normalSendDanmu.get().booleanValue()) {
                this.tvInput.setVisibility(0);
            }
            if (SdkProperties.showQuality.get().booleanValue()) {
                this.tvRate.setVisibility(0);
            }
            if (SdkProperties.showSubscribe.get().booleanValue()) {
                this.mLlSubscribe.setVisibility(0);
            }
            this.mIvSendMsg.setVisibility(8);
            this.mFloatingFullScreen.setVisibility(8);
            return;
        }
        this.mLlTitle.setVisibility(8);
        this.mLlAnchor.setVisibility(8);
        this.tvInput.setVisibility(8);
        this.tvRate.setVisibility(8);
        this.mLlSubscribe.setVisibility(8);
        if (SdkProperties.floatSendDanmu.get().booleanValue()) {
            this.mIvSendMsg.setVisibility(0);
        }
        if (SdkProperties.showFullScreen.get().booleanValue()) {
            this.mFloatingFullScreen.setVisibility(0);
        }
    }

    @Override // com.huya.berry.sdkplayer.floats.view.IPlayerView
    public void switchVoice() {
        boolean zIsSelected = this.mFloatingVoiceClose.isSelected();
        L.info(TAG, "close click and voice: " + zIsSelected);
        if (zIsSelected) {
            ArkToast.show("声音开启");
            Report.event(SdkReportConst.LIVE_SMALLWINDOW_UMUTE);
        } else {
            ArkToast.show("声音关闭");
            Report.event(SdkReportConst.LIVE_SMALLWINDOW_MUTE);
        }
        PlayerHelper.isOpenMute = zIsSelected;
        this.mFloatingVoiceClose.setSelected(!zIsSelected);
        PlayerView playerView = this.mPlayerView;
        if (playerView != null) {
            playerView.setMuteAudio(zIsSelected);
        }
    }

    @Override // com.huya.berry.sdkplayer.floats.view.IPlayerView
    public void switchDanmu() {
        boolean zIsSelected = this.mIvDanmu.isSelected();
        L.info(TAG, "mIvDanmu click: " + zIsSelected);
        if (zIsSelected) {
            ArkToast.show("弹幕已开启");
            Report.event(SdkReportConst.LIVE_FULLSCREEN_OPENMSG);
        } else {
            ArkToast.show("已关闭弹幕");
            Report.event(SdkReportConst.LIVE_FULLSCREEN_CLOSEMSG);
        }
        PlayerHelper.isOpenDanmu = zIsSelected;
        this.mIvDanmu.setSelected(!zIsSelected);
        DanmuView danmuView = this.mDanmuView;
        if (danmuView != null) {
            danmuView.switchBarrage(zIsSelected);
        }
    }

    public void setDanmuSelected() {
        this.mIvDanmu.setSelected(!PlayerHelper.isOpenDanmu);
    }

    public void setVoiceSelected() {
        this.mFloatingVoiceClose.setSelected(!PlayerHelper.isOpenMute);
    }

    @Override // com.huya.berry.sdkplayer.floats.view.IPlayerView
    public void setTvRate() {
        this.tvRate.setText(PlayerHelper.disPlayName);
    }

    @Override // com.huya.berry.sdkplayer.floats.view.IPlayerView
    public void setTvAudienceCount() {
        this.tvAudienceCount.setText(PlayerHelper.audienceCount);
    }

    public void destroy() {
        L.info(TAG, "destroy");
        PlayerPresenterImpl playerPresenterImpl = this.mPlayerPresenterImpl;
        if (playerPresenterImpl != null) {
            playerPresenterImpl.onDestroy();
            this.mPlayerPresenterImpl = null;
        }
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
            ViewGroup viewGroup = this.parentLayout;
            if (viewGroup != null) {
                viewGroup.removeView(this.mainView);
            }
            destroy();
            this.mainView = null;
        }
    }

    public void onDestroy() {
        hide();
        this.parentLayout = null;
    }
}
