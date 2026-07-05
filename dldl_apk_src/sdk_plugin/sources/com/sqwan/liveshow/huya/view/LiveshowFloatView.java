package com.sqwan.liveshow.huya.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.huya.berry.client.customui.model.LiveListInfo;
import com.sqwan.common.util.DensityUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.activity.ChatInputActivity;
import com.sqwan.liveshow.huya.danmu.DanMuController;
import com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager;
import com.sqwan.liveshow.huya.danmu.LiveshowMsgDispatcher;
import com.sqwan.liveshow.huya.danmu.adpter.ChooseAdapter;
import com.sqwan.liveshow.huya.danmu.view.ChattingRoomView;
import com.sqwan.liveshow.huya.danmu.view.ChooseView;
import com.sqwan.liveshow.huya.danmu.view.GuideView;
import com.sqwan.liveshow.huya.engine.ILiveshowControl;
import com.sqwan.liveshow.huya.engine.LiveInfoEx;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.request.bean.danmu.http.FetchImRspBean;
import com.sqwan.liveshow.huya.skin.SkinHelper;
import com.sy37sdk.account.floatview.DragViewLayout;
import master.flame.danmaku.ui.widget.DanmakuView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowFloatView extends DragViewLayout implements OnBehaviorListener, ILiveshowControl, LiveshowMsgDispatcher.ILiveshowMsgDispatch, LiveshowDanmuManager.SendImLimitListener {
    private ChattingRoomView chattingRoomView;
    private ChooseView chooseView;
    private DanMuController danMuController;
    private FrameLayout ftControlCorverView;
    private FrameLayout ftControlCorverViewFloat;
    private FrameLayout ftchooseView;
    public GuideView guideView;
    private int lastX;
    private int lastY;
    private LiveInfoEx liveInfoEx;
    public LiveshowFloatCoverView liveshowFloatCoverView;
    public View liveshow_softkeyboard_padding;
    private int minimizeHeight;
    private int minimizeWidth;
    private OnBehaviorListener onBehaviorListener;
    private PlayerView playerView;
    public DanmakuView sv_danmaku;
    private Task task;

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    public boolean isAuthStartAnim() {
        return false;
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    public boolean isCheckShowCompelete() {
        return false;
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    public boolean isPostView() {
        return false;
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase
    public boolean isScreenOnType() {
        return true;
    }

    @Override // com.sqwan.liveshow.huya.view.OnBehaviorListener
    public void resetLive() {
    }

    public LiveshowFloatView(Context context) {
        TextView tvInputMessage;
        super(context);
        this.task = Task.create();
        View.inflate(context, SqResUtils.getLayoutId(context, SqR.layout.sy37_layout_liveshowfloat), this);
        this.liveshowFloatCoverView = (LiveshowFloatCoverView) findViewById(SqResUtils.getId(getContext(), SqR.id.video_playerCover));
        this.chattingRoomView = (ChattingRoomView) findViewById(SqResUtils.getId(getContext(), SqR.id.chattingRoomView));
        this.chooseView = (ChooseView) findViewById(SqResUtils.getId(getContext(), SqR.id.chooseView));
        this.ftchooseView = (FrameLayout) findViewById(SqResUtils.getId(getContext(), SqR.id.ftchooseView));
        this.sv_danmaku = (DanmakuView) this.liveshowFloatCoverView.findViewById(SqResUtils.getId(getContext(), SqR.id.sv_danmaku));
        this.ftControlCorverView = (FrameLayout) findViewById(SqResUtils.getId(getContext(), SqR.id.ftControlCorverView));
        this.ftControlCorverViewFloat = (FrameLayout) this.liveshowFloatCoverView.findViewById(SqResUtils.getId(getContext(), SqR.id.ftControlCorverViewFloat));
        this.guideView = (GuideView) findViewById(SqResUtils.getId(getContext(), SqR.id.layout_guide_view));
        initChattingRoomView();
        initDanmakuView();
        PlayerView playerView = new PlayerView(this.mContext);
        this.playerView = playerView;
        this.liveshowFloatCoverView.addVideoFragmentContainer(this, playerView);
        this.liveshowFloatCoverView.setOnBehaviorListener(this);
        if (this.mContext.getResources().getConfiguration().orientation == 1) {
            this.minimizeWidth = 180;
            this.minimizeHeight = 120;
        } else {
            this.minimizeWidth = 256;
            this.minimizeHeight = 170;
        }
        this.mWidth = DensityUtil.dip2px(this.mContext, this.minimizeWidth);
        this.mHeight = DensityUtil.dip2px(this.mContext, this.minimizeHeight);
        this.chattingRoomView.getInputView().setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatView$rcbvOGkuVaP6klcgdQPsMXz9AfM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0$LiveshowFloatView(view);
            }
        });
        this.chooseView.setOnclickItemListener(new ChooseAdapter.ItemClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatView$NRoeOri0Er4XqMqE1PZCrwJkmFw
            @Override // com.sqwan.liveshow.huya.danmu.adpter.ChooseAdapter.ItemClickListener
            public final void ItemOnClick(int i) {
                this.f$0.lambda$new$1$LiveshowFloatView(i);
            }
        });
        this.liveshowFloatCoverView.getIv_definition().setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatView$uRSRB8Z1zClKeh6Zu9p9XqATyFs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$2$LiveshowFloatView(view);
            }
        });
        this.ftchooseView.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatView$fQey9FqcIAdxeRVID68MC9ylDqg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$3$LiveshowFloatView(view);
            }
        });
        DanMuController danMuController = new DanMuController(this.sv_danmaku);
        this.danMuController = danMuController;
        danMuController.init();
        LiveshowMsgDispatcher.getInstance().unRegister(this);
        LiveshowMsgDispatcher.getInstance().register(this);
        LiveshowDanmuManager.getInstance().unRegSendImLimitListener(this);
        LiveshowDanmuManager.getInstance().regSendImLimitListener(this);
        if (this.mContext.getResources().getConfiguration().orientation == 1 && (tvInputMessage = this.chattingRoomView.getInputView().getTvInputMessage()) != null) {
            tvInputMessage.setGravity(17);
        }
        post(new Runnable() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatView$XSHLjwXtKrAsHh4kRPbUoQfRrys
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$6$LiveshowFloatView();
            }
        });
    }

    public /* synthetic */ void lambda$new$0$LiveshowFloatView(View view) {
        goChatInputActivity();
    }

    public /* synthetic */ void lambda$new$1$LiveshowFloatView(int i) {
        ViewUtils.gone(this.ftchooseView);
        String str = this.liveInfoEx.disPlayNames.get(i);
        this.liveshowFloatCoverView.getIv_definition().setText(str);
        PlayerView playerView = this.playerView;
        if (playerView != null) {
            playerView.restart(str);
        }
    }

    public /* synthetic */ void lambda$new$2$LiveshowFloatView(View view) {
        ViewUtils.show(this.ftchooseView);
    }

    public /* synthetic */ void lambda$new$3$LiveshowFloatView(View view) {
        ViewUtils.gone(this.ftchooseView);
    }

    public /* synthetic */ void lambda$new$6$LiveshowFloatView() {
        this.guideView.resetGuideViewPosition(this.liveshowFloatCoverView.getIvLiveSwitchSound(), SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_liveshow_icon_sound), SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_liveshow_guide_small_sound), new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatView$-c_6fGeHbSRldCZ21GayaBnAk-o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$null$5$LiveshowFloatView(view);
            }
        });
        handleChatListChange(this.ftControlCorverView, LiveshowDanmuManager.getInstance().isImListShow());
        handleChatListChange(this.ftControlCorverViewFloat, LiveshowDanmuManager.getInstance().isImListShow());
    }

    public /* synthetic */ void lambda$null$5$LiveshowFloatView(View view) {
        this.guideView.resetGuideViewPosition(this.liveshowFloatCoverView.getmIvLiveSwitchWidth(), SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_liveshow_icon_narrow), SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_liveshow_guide_small_video), new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatView$k26pmmp3Hr_nnMdrLLN4WL-mkbw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$null$4$LiveshowFloatView(view2);
            }
        });
    }

    public /* synthetic */ void lambda$null$4$LiveshowFloatView(View view) {
        ViewUtils.gone(this.guideView);
        this.liveshowFloatCoverView.startAutoHide();
    }

    public void play(LiveInfoEx liveInfoEx) {
        this.liveInfoEx = liveInfoEx;
        LiveListInfo liveListInfo = liveInfoEx.liveListInfo;
        this.liveshowFloatCoverView.setAnchorInfo(liveListInfo.avatar, liveListInfo.nickName, liveListInfo.audienceCount);
        PlayerView playerView = this.playerView;
        if (playerView != null) {
            playerView.start(liveInfoEx);
        }
        String str = liveInfoEx.videoInfo.disPlayName;
        this.chooseView.setData(liveInfoEx.disPlayNames, str);
        this.liveshowFloatCoverView.getIv_definition().setText(str);
    }

    public void showView() {
        if (this.floatViewConfig != null) {
            this.floatLayoutParams.y = this.floatViewConfig.regionHeight - this.mHeight;
            this.lastX = this.floatLayoutParams.x;
            this.lastY = this.floatLayoutParams.y;
        }
        maximize();
        this.liveshowFloatCoverView.switchFullLiveContainer();
        addView();
        StatusBarUtil.hideSystemUI(this.mContext);
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void maximize() {
        if (LiveshowManager.getInstance().isMinimize()) {
            LiveshowManager.getInstance().maximize();
            chanageViewSize(false);
            this.canDispatchTouchEvent = false;
        }
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void close() {
        LiveshowManager.getInstance().close();
    }

    private void chanageViewSize(boolean z) {
        if (this.floatLayoutParams != null) {
            if (z) {
                this.floatLayoutParams.x = this.lastX;
                this.floatLayoutParams.y = this.lastY;
                this.floatLayoutParams.width = DensityUtil.dip2px(this.mContext, this.minimizeWidth);
                this.floatLayoutParams.height = DensityUtil.dip2px(this.mContext, this.minimizeHeight);
            } else {
                this.lastY = this.floatLayoutParams.y;
                this.lastX = this.floatLayoutParams.x;
                this.floatLayoutParams.x = this.floatViewConfig.marginLeft;
                this.floatLayoutParams.y = this.floatViewConfig.marginTop;
                this.floatLayoutParams.width = this.floatViewConfig.regionWidth;
                this.floatLayoutParams.height = this.floatViewConfig.regionHeight;
            }
            update();
        }
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void minimize() {
        if (LiveshowManager.getInstance().isMinimize()) {
            return;
        }
        LiveshowManager.getInstance().minimize();
        chanageViewSize(true);
        this.canDispatchTouchEvent = true;
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void resume() {
        LiveshowManager.getInstance().resume();
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void pause() {
        LiveshowManager.getInstance().pause();
    }

    @Override // com.sqwan.liveshow.huya.engine.ILiveshowControl
    public void switchVoice(boolean z) {
        LiveshowManager.getInstance().switchVoice(z);
    }

    @Override // com.sqwan.liveshow.huya.view.OnBehaviorListener
    public void fullScreen() {
        maximize();
        if (this.liveshowFloatCoverView.isImListShow()) {
            ViewUtils.show(this.chattingRoomView);
        }
        if (this.liveshowFloatCoverView.isDanmuShow()) {
            ViewUtils.show(this.sv_danmaku);
        }
    }

    @Override // com.sqwan.liveshow.huya.view.OnBehaviorListener
    public void smallScreen() {
        minimize();
        if (this.liveshowFloatCoverView.isImListShow()) {
            ViewUtils.gone(this.chattingRoomView);
        }
        if (isDanmuShow()) {
            ViewUtils.gone(this.sv_danmaku);
        }
    }

    @Override // com.sqwan.liveshow.huya.view.OnBehaviorListener
    public void levelLive() {
        close();
    }

    @Override // com.sqwan.liveshow.huya.view.OnBehaviorListener
    public void backLiveRoom() {
        RoomListView roomListView = LiveshowManager.getInstance().getRoomListView();
        if (roomListView != null) {
            roomListView.show();
        }
        LiveshowManager.getInstance().leaveLiveshowRoomWrapper(false);
    }

    @Override // com.sqwan.liveshow.huya.view.OnBehaviorListener
    public void clickInputChat() {
        goChatInputActivity();
    }

    @Override // com.sqwan.liveshow.huya.view.OnBehaviorListener
    public void showChatList(boolean z) {
        ViewGroup.LayoutParams layoutParams;
        handleChatListChange(this.ftControlCorverView, z);
        handleChatListChange(this.ftControlCorverViewFloat, z);
        OnBehaviorListener onBehaviorListener = this.onBehaviorListener;
        if (onBehaviorListener != null) {
            onBehaviorListener.showChatList(z);
        }
        if (this.mContext.getResources().getConfiguration().orientation != 1 || (layoutParams = this.liveshowFloatCoverView.getLayoutParams()) == null) {
            return;
        }
        if (z) {
            layoutParams.height = -2;
        } else {
            layoutParams.height = -1;
        }
        this.liveshowFloatCoverView.setLayoutParams(layoutParams);
    }

    @Override // com.sqwan.liveshow.huya.view.OnBehaviorListener
    public void showDanmu(boolean z) {
        if (z) {
            ViewUtils.show(this.sv_danmaku);
        } else {
            ViewUtils.gone(this.sv_danmaku);
        }
        OnBehaviorListener onBehaviorListener = this.onBehaviorListener;
        if (onBehaviorListener != null) {
            onBehaviorListener.showDanmu(z);
        }
    }

    @Override // com.sqwan.liveshow.huya.view.OnBehaviorListener
    public void clickDefinition() {
        ViewUtils.show(this.liveshowFloatCoverView.getIv_definition());
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase
    public void release() {
        super.release();
        LiveshowFloatCoverView liveshowFloatCoverView = this.liveshowFloatCoverView;
        if (liveshowFloatCoverView != null) {
            liveshowFloatCoverView.recycle();
        }
        PlayerView playerView = this.playerView;
        if (playerView != null) {
            playerView.release();
        }
        LiveshowMsgDispatcher.getInstance().unRegister(this);
        DanMuController danMuController = this.danMuController;
        if (danMuController != null) {
            danMuController.finish();
        }
        if (this.onBehaviorListener != null) {
            this.onBehaviorListener = null;
        }
        LiveshowDanmuManager.getInstance().unRegSendImLimitListener(this);
        this.task.stop();
    }

    public void goChatInputActivity() {
        Context context = getContext();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            Intent intent = new Intent(activity, (Class<?>) ChatInputActivity.class);
            intent.putExtra("Ex", true);
            activity.startActivity(intent);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        release();
    }

    @Override // com.sqwan.liveshow.huya.danmu.LiveshowMsgDispatcher.ILiveshowMsgDispatch
    public void dispatchIm(final FetchImRspBean.ImMsg imMsg) {
        Task.post(new Runnable() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatView$CtLtJ1fcRY8RBi2PZ9jEmqybuuQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dispatchIm$7$LiveshowFloatView(imMsg);
            }
        });
    }

    public /* synthetic */ void lambda$dispatchIm$7$LiveshowFloatView(FetchImRspBean.ImMsg imMsg) {
        if (imMsg == null || TextUtils.isEmpty(imMsg.msg.content)) {
            return;
        }
        LogUtil.i(this.TAG, "sendDanMuData:" + imMsg.toString());
        this.chattingRoomView.addChatMessage(imMsg);
    }

    @Override // com.sqwan.liveshow.huya.danmu.LiveshowMsgDispatcher.ILiveshowMsgDispatch
    public void dispatchDanmu(FetchImRspBean.ImMsg imMsg) {
        if (imMsg == null || TextUtils.isEmpty(imMsg.msg.content)) {
            return;
        }
        LogUtil.i(this.TAG, "sendDanMuData:" + imMsg.toString());
        this.danMuController.sendDanMuData(imMsg);
    }

    public boolean isDanmuShow() {
        return this.liveshowFloatCoverView.isDanmuShow();
    }

    private void initChattingRoomView() {
        if (LiveshowDanmuManager.getInstance().isImListShow()) {
            ViewUtils.show(this.chattingRoomView);
        } else {
            ViewUtils.gone(this.chattingRoomView);
        }
    }

    private void initDanmakuView() {
        if (LiveshowDanmuManager.getInstance().isDanmuShow()) {
            ViewUtils.show(this.sv_danmaku);
        } else {
            ViewUtils.gone(this.sv_danmaku);
        }
    }

    @Override // com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager.SendImLimitListener
    public void onCountDown(boolean z, String str) {
        this.chattingRoomView.getInputView().setEnabled(z);
        if (!z) {
            this.chattingRoomView.setCountDownViewText(String.format("%ss", str));
        } else {
            this.chattingRoomView.setCountDownViewText("");
        }
    }

    public void setOnBehaviorListener(OnBehaviorListener onBehaviorListener) {
        this.onBehaviorListener = onBehaviorListener;
    }

    private void handleChatListChange(View view, boolean z) {
        if (view == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (z) {
            if (this.mContext.getResources().getConfiguration().orientation == 1) {
                if (view != this.ftControlCorverViewFloat) {
                    marginLayoutParams.topMargin = this.liveshowFloatCoverView.getFlTopContainer().getHeight();
                }
            } else {
                marginLayoutParams.topMargin = this.liveshowFloatCoverView.getFlTopContainer().getHeight() - DensityUtil.dip2px(this.mContext, 5.0f);
            }
            ViewUtils.show(this.chattingRoomView);
            ViewUtils.hidden(this.liveshowFloatCoverView.getLl_input_and_countdownview());
            ViewGroup vBottomContainer = this.liveshowFloatCoverView.getVBottomContainer();
            if (vBottomContainer != null && this.mContext.getResources().getConfiguration().orientation == 1) {
                vBottomContainer.setPadding(0, 0, 0, 0);
            }
            view.setLayoutParams(marginLayoutParams);
            return;
        }
        marginLayoutParams.topMargin = 0;
        ViewUtils.gone(this.chattingRoomView);
        ViewUtils.show(this.liveshowFloatCoverView.getLl_input_and_countdownview());
        ViewGroup vBottomContainer2 = this.liveshowFloatCoverView.getVBottomContainer();
        if (this.mContext.getResources().getConfiguration().orientation == 1 && vBottomContainer2 != null) {
            vBottomContainer2.setPadding(0, DensityUtil.dip2px(this.mContext, 20.0f), 0, DensityUtil.dip2px(this.mContext, 20.0f));
        }
        view.setLayoutParams(marginLayoutParams);
    }

    public PlayerView getPlayerView() {
        return this.playerView;
    }
}
