package com.huya.berry.sdklive.liveTool;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Property;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.ClickViewDelayHelper;
import com.huya.berry.gamesdk.utils.DigitUtil;
import com.huya.berry.gamesdk.utils.NotchUtil;
import com.huya.berry.gamesdk.utils.PreferenceUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.SystemUtil;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.huya.berry.gamesdk.utils.UIUtil;
import com.huya.berry.sdklive.liveTool.LiveAlertView;
import com.huya.berry.sdklive.liveTool.SessionLinearLayout;
import com.huya.berry.sdklive.liveTool.floating.FloatingActionButton;
import com.huya.berry.sdklive.liveTool.floating.FloatingActionMenu;
import com.huya.berry.sdklive.liveTool.floating.SubActionButton;
import com.sqwan.liveshow.huya.SqR;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveToolView implements IToolView {
    private static final int MODEL_EXPAND_DOWN = 2;
    private static final int MODEL_EXPAND_NO = 0;
    private static final int MODEL_EXPAND_UP = 1;
    private static final String TAG = "LiveToolView";
    private float lastX;
    private float lastY;
    private int mBackToX;
    private LiveToolCallBack mCallBack;
    private Context mContext;
    private boolean mHorToSide;
    private boolean mIsHalfShow;
    private ImageView mIvAaronLi;
    private ObjectAnimator mLoadingAnimator;
    private MessageToolView mMsgContainer;
    private WindowManager.LayoutParams mMsgLayoutParams;
    private int mOrienation;
    private MessagePreviewContainer mPreviewContainer;
    private View mPreviewGuide;
    private SurfaceTexture mSurface;
    private FrameLayout mTempLayout;
    private SessionLinearLayout mToolMenuContainer;
    private TextView mToolNewsNum;
    private TextView mToolUserNum;
    private WindowManager mWindowManager;
    private BroadcastReceiver receiver;
    private ImageView rightLowerButton;
    private FloatingActionMenu rightLowerMenu;
    private static final int MESSAGE_CONTAINER_PADDING_LEFT = (int) UIUtil.getDp(25.0f);
    private static final int MESSAGE_CONTAINER_PADDING_TOP = (int) UIUtil.getDp(40.0f);
    private static final int MESSAGE_CONTAINER_WIDTH = MessageToolView.NOW_VIEW_WIDTH - MESSAGE_CONTAINER_PADDING_LEFT;
    private static final int MESSAGE_CONTAINER_HEIGHT_EXPAND = MessageToolView.VIEW_HEIGHT_EXPAND - MESSAGE_CONTAINER_PADDING_TOP;
    private boolean mExpandAnimating = false;
    private List<LiveAlertView> mAlartList = new ArrayList();
    private boolean mIsStarted = false;
    private boolean mIsShow = false;
    private boolean mIsPause = true;
    private boolean mIsMicBan = false;
    private boolean mIsAarnLi = false;
    private int mMsgExpandModel = 0;
    private boolean mHalfHide = false;
    private boolean mToolMoving = false;
    private int[] rightLowerMenuCoord = new int[2];
    private boolean mHaveUpdatedCoord = false;
    private boolean mIsHalfClick = false;
    private int[] lastCoords = new int[2];
    private SessionLinearLayout.DispatchKeyEventListener mDispatchKeyEventListener = new SessionLinearLayout.DispatchKeyEventListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.7
        @Override // com.huya.berry.sdklive.liveTool.SessionLinearLayout.DispatchKeyEventListener
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() != 4) {
                return false;
            }
            LiveToolView.this.showStopLiveAlert();
            return true;
        }
    };

    public interface LiveToolCallBack {
        void backToLiveRoom();

        void onExpandMsgContainer();

        void onHalfHide(boolean z);

        void onMicBan(boolean z);

        void onPauseLive(boolean z);

        void onStopLive();

        void requestStartLive();
    }

    public LiveToolView(Context context, WindowManager windowManager, LiveToolCallBack liveToolCallBack) {
        this.mWindowManager = windowManager;
        this.mContext = context;
        this.mCallBack = liveToolCallBack;
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void createViews() {
        addMsgPreviewContainer();
        addMsgContainer();
        addToolMenuContainer();
        hideTool();
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void setIsStarted(boolean z) {
        this.mIsStarted = z;
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public LiveAlertView addLiveAlertView() {
        LiveAlertView liveAlertView = new LiveAlertView(this.mContext);
        WindowManager.LayoutParams defaultSystemWindowParams = SystemUtil.getDefaultSystemWindowParams(-1, -1);
        defaultSystemWindowParams.gravity = 17;
        this.mWindowManager.addView(liveAlertView, defaultSystemWindowParams);
        this.mAlartList.add(liveAlertView);
        return liveAlertView;
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void showTool(boolean z, boolean z2) {
        this.mIsShow = z;
        if (!z) {
            hideTool();
            return;
        }
        int[] iArr = new int[2];
        int[] iArr2 = this.lastCoords;
        if (iArr2 != null && iArr2.length > 0 && !z2) {
            SessionLinearLayout sessionLinearLayout = this.mToolMenuContainer;
            if (sessionLinearLayout != null) {
                sessionLinearLayout.setVisibility(0);
            }
            iArr = iArr2;
        } else {
            SessionLinearLayout sessionLinearLayout2 = this.mToolMenuContainer;
            if (sessionLinearLayout2 != null) {
                sessionLinearLayout2.setVisibility(0);
                this.rightLowerButton.getLocationOnScreen(iArr);
                int i = iArr[0];
                L.info(TAG, "notchSize:" + i);
                if (i != 0 && this.mOrienation == 2) {
                    NotchUtil.setNotchSize(i);
                }
            }
        }
        MessageToolView messageToolView = this.mMsgContainer;
        if (messageToolView != null) {
            messageToolView.setVisibility(0);
            this.mMsgContainer.onResume();
            iArr[0] = 0;
            onHalfHide(false);
            resetMsgContainer();
            SessionLinearLayout sessionLinearLayout3 = this.mToolMenuContainer;
            if (sessionLinearLayout3 != null) {
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) sessionLinearLayout3.getLayoutParams();
                layoutParams.x = iArr[0];
                layoutParams.y = iArr[1];
                this.mWindowManager.updateViewLayout(this.mToolMenuContainer, layoutParams);
            }
            Context context = this.mContext;
            if (context != null && NotchUtil.needCalNotchSize(context) && NotchUtil.getNotchSize(this.mContext) >= iArr[0]) {
                iArr[0] = iArr[0] + NotchUtil.getNotchSize(this.mContext);
                L.info(TAG, "after por to land:" + iArr[0]);
            }
            updateMsgContainer(iArr);
            if (this.mMsgExpandModel != 0) {
                this.mMsgContainer.setExpandMode(true);
            }
        }
    }

    private void hideTool() {
        if (this.mToolMenuContainer != null) {
            this.rightLowerButton.getLocationOnScreen(this.lastCoords);
            this.mToolMenuContainer.setVisibility(4);
        }
        MessageToolView messageToolView = this.mMsgContainer;
        if (messageToolView != null) {
            messageToolView.setVisibility(4);
            this.mMsgContainer.onPause();
        }
        closeMenu();
    }

    private void removeFloatViews() {
        SessionLinearLayout sessionLinearLayout = this.mToolMenuContainer;
        if (sessionLinearLayout != null) {
            sessionLinearLayout.setVisibility(8);
            this.mWindowManager.removeView(this.mToolMenuContainer);
            this.mToolMenuContainer = null;
            this.rightLowerButton = null;
        }
        hidePreviewGuide(false);
        removeAllLiveAlertView();
        removeToolMenuContainer();
        removeMsgContainer();
        removeMsgPreviewContainer();
        removePreviewGuide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAllLiveAlertView() {
        for (int i = 0; i < this.mAlartList.size(); i++) {
            this.mWindowManager.removeView(this.mAlartList.get(i));
        }
        this.mAlartList.clear();
    }

    public void removeLiveAlertView(LiveAlertView liveAlertView) {
        if (liveAlertView != null) {
            this.mWindowManager.removeView(liveAlertView);
            this.mAlartList.remove(liveAlertView);
        }
    }

    private void removeToolMenuContainer() {
        FloatingActionMenu floatingActionMenu = this.rightLowerMenu;
        if (floatingActionMenu != null) {
            floatingActionMenu.destroy();
            this.rightLowerMenu = null;
        }
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void updateNewsNum(int i) {
        if (i > 0) {
            if (this.mIsAarnLi) {
                this.mToolNewsNum.setText(i > 99 ? "99+" : String.valueOf(i));
                this.mToolNewsNum.setVisibility(0);
                return;
            } else {
                showMsgPreview();
                return;
            }
        }
        this.mToolNewsNum.setVisibility(8);
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void halfHideTool() {
        if (this.mToolMenuContainer == null || this.mToolMoving) {
            return;
        }
        onHalfHide(true);
    }

    private void onHalfHide(boolean z) {
        LiveToolCallBack liveToolCallBack = this.mCallBack;
        if (liveToolCallBack != null) {
            liveToolCallBack.onHalfHide(z);
        }
        if (this.mHalfHide == z) {
            return;
        }
        if (z) {
            Report.event(SdkReportConst.CLICK_SMALLWINDOW_WELT);
            halfHide();
        } else {
            Report.event(SdkReportConst.CLICK_SMALLWINDOW_NORMAL);
            halfShow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initToolState(View view) {
        if (LiveToolHelper.isLeftMost(view)) {
            onHalfHide(true);
        } else {
            onHalfHide(false);
        }
    }

    private void halfHide() {
        if (this.rightLowerMenu == null) {
            return;
        }
        this.mHalfHide = true;
        hideChatView();
        closeMenu();
        if (this.mMsgExpandModel != 0) {
            contractMsgContainer();
        }
        this.mToolUserNum.setVisibility(8);
        if (this.mIsAarnLi) {
            this.mIvAaronLi.setVisibility(0);
        } else {
            this.mIvAaronLi.setVisibility(8);
        }
        LiveToolHelper.halfHideTool(this.mToolMenuContainer, this.rightLowerButton, this.mWindowManager);
    }

    private void halfShow() {
        if (this.mHalfHide) {
            this.mHalfHide = false;
            resetMsgContainer();
        }
        if (this.mIvAaronLi.getVisibility() == 0) {
            this.mIvAaronLi.setVisibility(8);
        }
        LiveToolHelper.initToolButton(this.rightLowerButton);
        this.mToolUserNum.setVisibility(0);
    }

    private void addToolMenuContainer() {
        SessionLinearLayout sessionLinearLayout = (SessionLinearLayout) LayoutInflater.from(this.mContext).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_service_tool_menu_layout), (ViewGroup) null);
        this.mToolMenuContainer = sessionLinearLayout;
        this.mTempLayout = (FrameLayout) sessionLinearLayout.findViewById(ResourceUtil.getIdResIDByName(SqR.id.msg_container));
        contractTempLayout();
        WindowManager.LayoutParams defaultSystemWindowParams = FloatingActionButton.Builder.getDefaultSystemWindowParams(this.mContext);
        defaultSystemWindowParams.y = UIUtil.screenSize().y;
        this.mWindowManager.addView(this.mToolMenuContainer, defaultSystemWindowParams);
        L.info(TAG, "addToolMenuContainer addView");
        this.rightLowerButton = (ImageView) this.mToolMenuContainer.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tool_main_btn));
        this.mToolNewsNum = (TextView) this.mToolMenuContainer.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tool_news_num));
        this.mToolUserNum = (TextView) this.mToolMenuContainer.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tool_user_num));
        this.mIvAaronLi = (ImageView) this.mToolMenuContainer.findViewById(ResourceUtil.getIdResIDByName(SqR.id.iv_aaronli));
        this.rightLowerButton.setOnTouchListener(new View.OnTouchListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.1
            private float downX;
            private float downY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    LiveToolView.this.mToolMoving = true;
                    LiveToolView.this.lastX = motionEvent.getRawX();
                    LiveToolView.this.lastY = motionEvent.getRawY();
                    this.downX = LiveToolView.this.lastX;
                    this.downY = LiveToolView.this.lastY;
                } else if (action == 1) {
                    LiveToolView.this.mIsHalfShow = false;
                    float rawX = motionEvent.getRawX();
                    float rawY = motionEvent.getRawY();
                    if (Math.abs(rawX - this.downX) >= 15.0d || Math.abs(rawY - this.downY) >= 15.0d) {
                        LiveToolView.this.initToolState(view);
                    } else if (LiveToolView.this.mIsStarted) {
                        if (LiveToolView.this.mHalfHide) {
                            LiveToolView.this.judgeDoubleClick();
                        } else {
                            LiveToolView.this.expandMenu();
                        }
                    } else if (LiveToolView.this.mCallBack != null) {
                        LiveToolView.this.mCallBack.requestStartLive();
                    }
                    if (LiveToolView.this.mHorToSide && LiveToolView.this.mBackToX > 300 && LiveToolView.this.mIsShow) {
                        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) LiveToolView.this.mToolMenuContainer.getLayoutParams();
                        layoutParams.x = LiveToolView.this.mBackToX - LiveToolView.MESSAGE_CONTAINER_PADDING_LEFT;
                        LiveToolView.this.mWindowManager.updateViewLayout(LiveToolView.this.mToolMenuContainer, layoutParams);
                    }
                    LiveToolView.this.mToolMoving = false;
                } else if (action == 2) {
                    LiveToolView.this.moveToPos(motionEvent.getRawX(), motionEvent.getRawY());
                }
                return false;
            }
        });
        SubActionButton.Builder builder = new SubActionButton.Builder(this.mContext);
        ImageView imageView = new ImageView(this.mContext);
        imageView.setImageDrawable(this.mContext.getResources().getDrawable(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_stoplive_btn), null));
        SubActionButton subActionButtonBuild = builder.setContentView(imageView).build();
        ImageView imageView2 = new ImageView(this.mContext);
        imageView2.setImageDrawable(this.mContext.getResources().getDrawable(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_home_btn), null));
        SubActionButton subActionButtonBuild2 = builder.setContentView(imageView2).build();
        final ImageView imageView3 = new ImageView(this.mContext);
        imageView3.setImageDrawable(this.mContext.getResources().getDrawable(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_mic_btn), null));
        SubActionButton subActionButtonBuild3 = builder.setContentView(imageView3).build();
        int dp = (int) UIUtil.getDp(40.0f);
        int dp2 = (int) UIUtil.getDp(40.0f);
        if (!SdkProperties.hidePauseBtn.get().booleanValue()) {
            final ImageView imageView4 = new ImageView(this.mContext);
            imageView4.setImageDrawable(this.mContext.getResources().getDrawable(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_pauselive_btn), null));
            SubActionButton subActionButtonBuild4 = builder.setContentView(imageView4).build();
            this.rightLowerMenu = new FloatingActionMenu.Builder(this.mContext, true).attachTo(this.rightLowerButton).addSubActionView(subActionButtonBuild2, dp, dp2).addSubActionView(subActionButtonBuild3, dp, dp2).addSubActionView(subActionButtonBuild4, dp, dp2).addSubActionView(subActionButtonBuild, dp, dp2).build();
            subActionButtonBuild4.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LiveToolView.this.closeMenu();
                    if (ClickViewDelayHelper.enableClick()) {
                        if (LiveToolView.this.mIsPause) {
                            Report.event(SdkReportConst.CLICK_SMALLWINDOW_PAUSE);
                            imageView4.setImageDrawable(LiveToolView.this.mContext.getResources().getDrawable(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_continuelive_btn), null));
                        } else {
                            Report.event(SdkReportConst.CLICK_SMALLWINDOW_CONTINUE);
                            imageView4.setImageDrawable(LiveToolView.this.mContext.getResources().getDrawable(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_pauselive_btn), null));
                        }
                        if (LiveToolView.this.mCallBack != null) {
                            LiveToolView.this.mCallBack.onPauseLive(LiveToolView.this.mIsPause);
                        }
                        SdkProperties.isClickPauseLive.set(Boolean.valueOf(LiveToolView.this.mIsPause));
                        LiveToolView.this.mIsPause = !r4.mIsPause;
                    }
                }
            });
        } else {
            this.rightLowerMenu = new FloatingActionMenu.Builder(this.mContext, true).attachTo(this.rightLowerButton).addSubActionView(subActionButtonBuild2, dp, dp2).addSubActionView(subActionButtonBuild3, dp, dp2).addSubActionView(subActionButtonBuild, dp, dp2).build();
        }
        subActionButtonBuild.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Report.event(SdkReportConst.CLICK_SMALLWINDOW_STOP);
                LiveToolView.this.showStopLiveAlert();
            }
        });
        subActionButtonBuild2.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Report.event(SdkReportConst.CLICK_SMALLWINDOW_INFO);
                LiveToolView.this.closeMenu();
                if (ClickViewDelayHelper.enableClick() && LiveToolView.this.mCallBack != null) {
                    LiveToolView.this.mCallBack.backToLiveRoom();
                }
            }
        });
        subActionButtonBuild3.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveToolView.this.closeMenu();
                if (ClickViewDelayHelper.enableClick()) {
                    if (LiveToolView.this.mIsMicBan) {
                        imageView3.setImageDrawable(LiveToolView.this.mContext.getResources().getDrawable(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_mic_btn), null));
                    } else {
                        imageView3.setImageDrawable(LiveToolView.this.mContext.getResources().getDrawable(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_mic_ban_btn), null));
                    }
                    LiveToolView.this.mIsMicBan = !r4.mIsMicBan;
                    if (LiveToolView.this.mCallBack != null) {
                        LiveToolView.this.mCallBack.onMicBan(LiveToolView.this.mIsMicBan);
                    }
                }
            }
        });
        this.rightLowerMenu.getOverlayContainer().setOnTouchListener(new View.OnTouchListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0 || LiveToolView.this.rightLowerMenu == null) {
                    return false;
                }
                LiveToolView.this.closeMenu();
                return false;
            }
        });
        boolean isAarnli = PreferenceUtil.getIsAarnli();
        this.mIsAarnLi = isAarnli;
        Report.event(isAarnli ? SdkReportConst.STATUS_LIVE_LIVESTART_SMALLWINDOW_PREVIEWCLOSE : SdkReportConst.STATUS_LIVE_LIVESTART_SMALLWINDOW_PREVIEWOPEN);
        if (SdkProperties.sdkMode.get() == SdkProperties.SDKMode.CAPTURE_BY_SCREEN) {
            this.mToolMenuContainer.setDispatchKeyEventListener(this.mDispatchKeyEventListener);
            rigisterHomeKeyBroadcast();
        }
    }

    private void rigisterHomeKeyBroadcast() {
        if (this.receiver == null) {
            this.receiver = new BroadcastReceiver() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.8
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    String stringExtra;
                    if (intent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS") && (stringExtra = intent.getStringExtra("reason")) != null && stringExtra.equals("homekey")) {
                        Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.MAIN");
                        intent2.setFlags(268435456);
                        intent2.addCategory("android.intent.category.HOME");
                        LiveToolView.this.mContext.startActivity(intent2);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
            this.mContext.registerReceiver(this.receiver, intentFilter);
        }
    }

    private void removeHomeKeyBroadcast() {
        BroadcastReceiver broadcastReceiver = this.receiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
            this.receiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgeDoubleClick() {
        if (this.mIsHalfClick) {
            this.mIsHalfClick = false;
            toggleAaronLiModel();
        } else {
            this.mIsHalfClick = true;
            TaskExecutor.proxyHandler().postDelayed(new Runnable() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.9
                @Override // java.lang.Runnable
                public void run() {
                    LiveToolView.this.mIsHalfClick = false;
                }
            }, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveToPos(float f, float f2) {
        int[] iArrMoveContainer;
        float f3 = f - this.lastX;
        float f4 = f2 - this.lastY;
        if (f3 > 0.0f && this.mHorToSide && this.mBackToX > 300) {
            f3 = 0.0f;
        }
        if (f3 == 0.0f && f4 == 0.0f) {
            return;
        }
        if (this.mHalfHide) {
            if (Math.abs(f3) >= 15.0d || Math.abs(f4) >= 15.0d) {
                hideMsgPreview();
                hidePreviewGuide(true);
                onHalfHide(false);
                this.mIsHalfShow = true;
                return;
            }
            return;
        }
        int i = this.mMsgExpandModel;
        if (i == 0 || i == 2) {
            iArrMoveContainer = LiveToolHelper.moveContainer((int) f3, (int) f4, this.mToolMenuContainer, this.mWindowManager, MESSAGE_CONTAINER_WIDTH + NotchUtil.getNotchSize(this.mContext), 0);
        } else {
            iArrMoveContainer = LiveToolHelper.moveContainer((int) f3, (int) f4, this.mToolMenuContainer, this.mWindowManager, MESSAGE_CONTAINER_WIDTH + NotchUtil.getNotchSize(this.mContext), -MESSAGE_CONTAINER_HEIGHT_EXPAND);
        }
        int[] iArr = this.rightLowerMenuCoord;
        iArr[0] = iArrMoveContainer[0];
        iArr[1] = iArrMoveContainer[1];
        updateMsgContainer(iArrMoveContainer);
        isToSide((int) f3);
        this.lastX = f;
        this.lastY = f2;
        if (this.mIsHalfShow) {
            this.mHaveUpdatedCoord = true;
            FloatingActionMenu floatingActionMenu = this.rightLowerMenu;
            int[] iArr2 = this.rightLowerMenuCoord;
            floatingActionMenu.updatePosition(iArr2[0], iArr2[1], true);
            return;
        }
        this.mHaveUpdatedCoord = false;
    }

    private void isToSide(int i) {
        if (i < 0) {
            this.mHorToSide = false;
            return;
        }
        int[] iArr = new int[2];
        this.mMsgContainer.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        this.rightLowerButton.getLocationOnScreen(iArr2);
        if (Math.abs(iArr[0] - iArr2[0]) >= MESSAGE_CONTAINER_PADDING_LEFT || i <= 0 || !this.mIsShow) {
            return;
        }
        this.mBackToX = iArr[0];
        this.mHorToSide = true;
        L.info(TAG, "msg x toside" + this.mBackToX);
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.mToolMenuContainer.getLayoutParams();
        layoutParams.x = iArr[0] - MESSAGE_CONTAINER_PADDING_LEFT;
        this.mWindowManager.updateViewLayout(this.mToolMenuContainer, layoutParams);
    }

    private void updateMsgContainer(int[] iArr) {
        if (this.mIsShow) {
            this.mMsgLayoutParams.x = iArr[0] + MESSAGE_CONTAINER_PADDING_LEFT;
            if (this.mMsgExpandModel == 1) {
                this.mMsgLayoutParams.y = iArr[1] - MESSAGE_CONTAINER_HEIGHT_EXPAND;
            } else {
                this.mMsgLayoutParams.y = iArr[1];
            }
            this.mWindowManager.updateViewLayout(this.mMsgContainer, this.mMsgLayoutParams);
        }
    }

    private void toggleAaronLiModel() {
        boolean z = !this.mIsAarnLi;
        this.mIsAarnLi = z;
        PreferenceUtil.setIsAarnli(z);
        if (this.mIsAarnLi) {
            SdkProperties.hideMsgNumber.set(0);
            this.mIvAaronLi.setVisibility(0);
            this.mPreviewContainer.showPreview(false);
            Report.event(SdkReportConst.CLICK_SMALLWINDOW_CLOSEPREVIEW);
            return;
        }
        this.mIvAaronLi.setVisibility(8);
        if (this.mToolNewsNum.getVisibility() == 0) {
            showMsgPreview();
            this.mToolNewsNum.setVisibility(8);
        }
        Report.event(SdkReportConst.CLICK_SMALLWINDOW_OPENPREVIEW);
    }

    private void showMsgPreview() {
        if (this.mHalfHide) {
            L.info(TAG, "showMsgPreview");
            if (this.mPreviewContainer.getVisibility() == 8) {
                this.mPreviewContainer.setVisibility(0);
            }
            this.mWindowManager.updateViewLayout(this.mPreviewContainer, getPreviewContainerLp());
            this.mPreviewContainer.showPreview(true);
            showPreviewGuide();
        }
    }

    private void hideMsgPreview() {
        L.info(TAG, "hideMsgPreview");
        if (this.mPreviewContainer.getVisibility() == 0) {
            this.mPreviewContainer.setVisibility(8);
        }
    }

    private void addMsgPreviewContainer() {
        if (this.mPreviewContainer == null) {
            this.mPreviewContainer = new MessagePreviewContainer(this.mContext);
        }
        this.mWindowManager.addView(this.mPreviewContainer, SystemUtil.getDefaultSystemWindowParams(-2, -2, false));
        this.mPreviewContainer.setVisibility(8);
    }

    private void removeMsgPreviewContainer() {
        MessagePreviewContainer messagePreviewContainer = this.mPreviewContainer;
        if (messagePreviewContainer == null) {
            return;
        }
        this.mWindowManager.removeView(messagePreviewContainer);
    }

    private void hideChatView() {
        MessageToolView messageToolView = this.mMsgContainer;
        if (messageToolView == null || this.mToolMenuContainer == null) {
            return;
        }
        messageToolView.setVisibility(8);
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.mToolMenuContainer.getLayoutParams();
        Point pointAdjustToolMenuContainerPoint = LiveToolHelper.adjustToolMenuContainerPoint(layoutParams.x, layoutParams.y, this.mToolMenuContainer);
        layoutParams.x = pointAdjustToolMenuContainerPoint.x;
        layoutParams.y = pointAdjustToolMenuContainerPoint.y;
        layoutParams.width = -2;
        layoutParams.height = -2;
        this.mWindowManager.updateViewLayout(this.mToolMenuContainer, layoutParams);
    }

    private void showPreviewGuide() {
        if (this.mPreviewGuide == null && PreferenceUtil.getIsPreviewGuide()) {
            if (isOverHalf()) {
                this.mPreviewGuide = LayoutInflater.from(this.mContext).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_small_window_preview_guide_up), (ViewGroup) null);
            } else {
                this.mPreviewGuide = LayoutInflater.from(this.mContext).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_small_window_preview_guide_down), (ViewGroup) null);
            }
            if (((ViewGroup) this.mPreviewGuide).getChildAt(1) instanceof TextView) {
                SpannableString spannableString = new SpannableString("收起状态下，双击气泡可屏蔽弹出消息");
                spannableString.setSpan(new ForegroundColorSpan(-27136), 6, 8, 18);
                ((TextView) ((ViewGroup) this.mPreviewGuide).getChildAt(1)).setText(spannableString);
            }
            this.mWindowManager.addView(this.mPreviewGuide, getPreviewGuideLp());
            ((ImageView) this.mPreviewGuide.findViewById(ResourceUtil.getIdResIDByName("iv_close"))).setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LiveToolView.this.hidePreviewGuide(true);
                }
            });
            Report.event(SdkReportConst.CLICK_SMALLWINDOW_PREVIEWGUIDE);
        }
    }

    private WindowManager.LayoutParams getPreviewContainerLp() {
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.mPreviewContainer.getLayoutParams();
        layoutParams.x = 0;
        layoutParams.y = (int) (getStartY() + ((this.rightLowerButton.getHeight() - UIUtil.getDp(27.0f)) / 2.0f));
        return layoutParams;
    }

    private WindowManager.LayoutParams getPreviewGuideLp() {
        int height;
        int startY = getStartY();
        if (isOverHalf()) {
            height = startY - this.rightLowerButton.getHeight();
        } else {
            height = startY + ((this.rightLowerButton.getHeight() * 3) / 4);
        }
        WindowManager.LayoutParams defaultSystemWindowParams = SystemUtil.getDefaultSystemWindowParams((int) UIUtil.getDp(133.0f), (int) UIUtil.getDp(56.0f));
        defaultSystemWindowParams.x = (int) UIUtil.getDp(3.0f);
        defaultSystemWindowParams.y = height - 10;
        return defaultSystemWindowParams;
    }

    private int getStartY() {
        if (this.mHaveUpdatedCoord) {
            return this.rightLowerMenuCoord[1];
        }
        int[] iArr = new int[2];
        this.rightLowerButton.getLocationOnScreen(iArr);
        int height = UIUtil.screenSize().y - this.rightLowerButton.getHeight();
        if (iArr[1] > height) {
            iArr[1] = height;
        }
        return iArr[1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hidePreviewGuide(boolean z) {
        if (this.mPreviewGuide == null) {
            return;
        }
        removePreviewGuide();
        if (z) {
            PreferenceUtil.setIsPreviewGuide();
        }
        this.mPreviewGuide = null;
    }

    private void removePreviewGuide() {
        View view = this.mPreviewGuide;
        if (view == null) {
            return;
        }
        this.mWindowManager.removeView(view);
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void expandMenu() {
        boolean zIsOverHalf;
        int i;
        int i2;
        ImageView imageView = this.rightLowerButton;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        int i3 = this.mMsgExpandModel;
        if (i3 == 0) {
            zIsOverHalf = isOverHalf();
        } else {
            zIsOverHalf = i3 == 1;
        }
        if (zIsOverHalf) {
            i2 = 360;
            i = 270;
        } else {
            i = 90;
            i2 = 0;
        }
        this.rightLowerMenu.setAngle(i2, i);
        if (this.mIsShow) {
            if (this.mHaveUpdatedCoord) {
                this.rightLowerMenu.toggle();
                L.info(TAG, "expandMenu:   haveUpdate   " + this.rightLowerMenuCoord[0] + "," + this.rightLowerMenuCoord[1]);
                return;
            }
            int[] iArr = new int[2];
            this.rightLowerButton.getLocationOnScreen(iArr);
            this.rightLowerMenu.updatePosition(iArr[0], iArr[1], SdkProperties.specialOverlayType.get().booleanValue());
            this.rightLowerMenu.toggle();
            L.info(TAG, "expandMenu:" + iArr[0] + "," + iArr[1]);
        }
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void resetMsgContainer() {
        MessageToolView messageToolView = this.mMsgContainer;
        if (messageToolView == null) {
            return;
        }
        messageToolView.setVisibility(0);
        this.mMsgContainer.setExpandMode(false);
    }

    private void addMsgContainer() {
        this.mMsgContainer = new MessageToolView(this.mContext);
        int i = this.mContext.getResources().getConfiguration().orientation;
        this.mOrienation = i;
        this.mMsgContainer.setOrientation(i == 1);
        WindowManager.LayoutParams defaultSystemWindowParams = SystemUtil.getDefaultSystemWindowParams();
        this.mMsgLayoutParams = defaultSystemWindowParams;
        defaultSystemWindowParams.width = MessageToolView.NOW_VIEW_WIDTH;
        this.mMsgLayoutParams.height = MessageToolView.VIEW_HEIGHT_MINI;
        this.mWindowManager.addView(this.mMsgContainer, this.mMsgLayoutParams);
        ((ImageView) this.mMsgContainer.findViewById(ResourceUtil.getIdResIDByName(SqR.id.contract_btn))).setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveToolView.this.contractMsgContainer();
                Report.event(SdkReportConst.CLICK_SMALLWINDOW_CLOSE);
            }
        });
        ((ImageView) this.mMsgContainer.findViewById(ResourceUtil.getIdResIDByName(SqR.id.expand_btn))).setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveToolView.this.expandMsgContainer();
                if (LiveToolView.this.mCallBack != null) {
                    LiveToolView.this.mCallBack.onExpandMsgContainer();
                }
                Report.event(SdkReportConst.CLICK_SMALLWINDOW_OPEN);
            }
        });
        this.mMsgContainer.setVisibility(8);
        this.mMsgContainer.onResume();
    }

    private void removeMsgContainer() {
        MessageToolView messageToolView = this.mMsgContainer;
        if (messageToolView == null) {
            return;
        }
        this.mWindowManager.removeView(messageToolView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void contractMsgContainer() {
        contractMsgContainer(true);
    }

    private void contractMsgContainer(boolean z) {
        if (this.mExpandAnimating || this.rightLowerButton == null) {
            return;
        }
        this.mExpandAnimating = true;
        this.mMsgContainer.setExpandMode(false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(MessageToolView.NOW_VIEW_WIDTH, MessageToolView.VIEW_HEIGHT_MINI, 51);
        Rect msgContainerMarginsByToolMenu = LiveToolHelper.getMsgContainerMarginsByToolMenu(this.rightLowerButton, false);
        layoutParams.setMargins(msgContainerMarginsByToolMenu.left, msgContainerMarginsByToolMenu.top, msgContainerMarginsByToolMenu.right, msgContainerMarginsByToolMenu.bottom);
        WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) this.mMsgContainer.getLayoutParams();
        if (this.mMsgExpandModel == 1) {
            layoutParams2.y = (layoutParams2.y + MessageToolView.VIEW_HEIGHT_EXPAND) - MessageToolView.VIEW_HEIGHT_MINI;
        }
        layoutParams2.height = MessageToolView.VIEW_HEIGHT_MINI;
        this.mWindowManager.updateViewLayout(this.mMsgContainer, layoutParams2);
        this.mExpandAnimating = false;
        this.mMsgExpandModel = 0;
        contractTempLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void expandMsgContainer() {
        expandMsgContainer(true);
    }

    private synchronized void expandMsgContainer(boolean z) {
        if (this.mToolMenuContainer != null && this.rightLowerButton != null) {
            if (this.mExpandAnimating) {
                return;
            }
            int i = 1;
            this.mExpandAnimating = true;
            final WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.mMsgContainer.getLayoutParams();
            boolean zIsOverHalf = isOverHalf();
            this.mMsgContainer.setExpandMode(true);
            if (!zIsOverHalf) {
                i = 2;
            }
            this.mMsgExpandModel = i;
            if (zIsOverHalf) {
                layoutParams.y = (layoutParams.y + MessageToolView.VIEW_HEIGHT_MINI) - MessageToolView.VIEW_HEIGHT_EXPAND;
                this.mWindowManager.updateViewLayout(this.mMsgContainer, layoutParams);
                TaskExecutor.uiHandler().postDelayed(new Runnable() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.13
                    @Override // java.lang.Runnable
                    public void run() {
                        layoutParams.height = MessageToolView.VIEW_HEIGHT_EXPAND;
                        LiveToolView.this.mWindowManager.updateViewLayout(LiveToolView.this.mMsgContainer, layoutParams);
                        LiveToolView.this.mExpandAnimating = false;
                    }
                }, 25L);
            } else {
                layoutParams.height = MessageToolView.VIEW_HEIGHT_EXPAND;
                this.mWindowManager.updateViewLayout(this.mMsgContainer, layoutParams);
                this.mExpandAnimating = false;
            }
            if (this.mMsgExpandModel == 2) {
                expandTempLayout();
            }
        }
    }

    private boolean isOverHalf() {
        return getStartY() > UIUtil.screenSize().y / 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeMenu() {
        FloatingActionMenu floatingActionMenu = this.rightLowerMenu;
        if (floatingActionMenu == null || !floatingActionMenu.isOpen()) {
            return;
        }
        this.rightLowerMenu.toggle();
    }

    private void contractTempLayout() {
        ViewGroup.LayoutParams layoutParams = this.mTempLayout.getLayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        this.mTempLayout.setLayoutParams(layoutParams);
    }

    private void expandTempLayout() {
        ViewGroup.LayoutParams layoutParams = this.mTempLayout.getLayoutParams();
        layoutParams.width = -2;
        layoutParams.height = MessageToolView.VIEW_HEIGHT_EXPAND;
        this.mTempLayout.setLayoutParams(layoutParams);
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void onConfigurationChanged(Configuration configuration) {
        this.mOrienation = configuration.orientation;
        if (this.mWindowManager == null || this.mToolMenuContainer == null) {
            return;
        }
        if (configuration.orientation == 2 || configuration.orientation == 1) {
            closeMenu();
            if (configuration.orientation == 2) {
                showTool(true, false);
            } else {
                showTool(true, true);
            }
            int[] iArr = new int[2];
            int[] iArr2 = this.lastCoords;
            if (iArr2 != null && iArr2.length > 0) {
                SessionLinearLayout sessionLinearLayout = this.mToolMenuContainer;
                if (sessionLinearLayout != null) {
                    sessionLinearLayout.setVisibility(0);
                }
                iArr = iArr2;
            } else {
                SessionLinearLayout sessionLinearLayout2 = this.mToolMenuContainer;
                if (sessionLinearLayout2 != null) {
                    sessionLinearLayout2.setVisibility(0);
                    this.rightLowerButton.getLocationOnScreen(iArr);
                    int i = iArr[0];
                    L.info(TAG, "notchSize:" + i);
                    if (i != 0 && this.mOrienation == 2) {
                        NotchUtil.setNotchSize(i);
                    }
                }
            }
            iArr[0] = 0;
            this.rightLowerMenu.updatePosition(iArr[0], iArr[1], SdkProperties.specialOverlayType.get().booleanValue());
            if (!this.mIsStarted) {
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.mToolMenuContainer.getLayoutParams();
                layoutParams.x = (int) UIUtil.getDp(9.0f);
                layoutParams.y = (UIUtil.screenSize().y - ((int) UIUtil.getDp(9.0f))) - BitmapFactory.decodeResource(this.mContext.getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_start_live_normal)).getHeight();
                this.mWindowManager.updateViewLayout(this.mToolMenuContainer, layoutParams);
            }
            if (this.mHalfHide) {
                hideMsgPreview();
                hidePreviewGuide(false);
            }
            this.mHorToSide = false;
        }
        this.mOrienation = configuration.orientation;
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void showProgress(String str, boolean z) {
        closeMenu();
        MessageToolView messageToolView = this.mMsgContainer;
        if (messageToolView != null && messageToolView.getVisibility() == 0) {
            this.mMsgContainer.setVisibility(8);
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.mToolMenuContainer.getLayoutParams();
            Point pointAdjustToolMenuContainerPoint = LiveToolHelper.adjustToolMenuContainerPoint(layoutParams.x, layoutParams.y, this.mToolMenuContainer);
            layoutParams.x = pointAdjustToolMenuContainerPoint.x;
            layoutParams.y = pointAdjustToolMenuContainerPoint.y;
            layoutParams.width = -2;
            layoutParams.height = -2;
            this.mWindowManager.updateViewLayout(this.mToolMenuContainer, layoutParams);
        }
        ImageView imageView = this.rightLowerButton;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.rightLowerButton, (Property<ImageView, Float>) View.ROTATION_Y, 0.0f, 180.0f, 0.0f);
        this.mLoadingAnimator = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(2000L).setRepeatCount(-1);
        this.mLoadingAnimator.start();
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void dismissProgress() {
        ObjectAnimator objectAnimator = this.mLoadingAnimator;
        if (objectAnimator == null || this.rightLowerButton == null) {
            return;
        }
        objectAnimator.end();
        this.rightLowerButton.clearAnimation();
        this.mLoadingAnimator = null;
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void setOnlineUser(int i) {
        if (this.mMsgContainer == null) {
            return;
        }
        this.mToolUserNum.setText(DigitUtil.numFromat(i));
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void onDestroy() {
        this.mIsShow = false;
        removeHomeKeyBroadcast();
        this.mPreviewContainer.onDestroy();
        this.mMsgContainer.onStop();
        this.mMsgContainer.onDestroy();
        removeFloatViews();
        SurfaceTexture surfaceTexture = this.mSurface;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mSurface = null;
        }
        this.mContext = null;
        this.mCallBack = null;
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void showMsgContainer(boolean z) {
        MessageToolView messageToolView = this.mMsgContainer;
        if (messageToolView == null) {
            return;
        }
        messageToolView.setVisibility(z ? 0 : 4);
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void setMsgTip(boolean z, boolean z2, String str) {
        this.mMsgContainer.setTipText(z, z2, str);
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void setMicban(boolean z) {
        this.mMsgContainer.setMicBan(z);
    }

    @Override // com.huya.berry.sdklive.liveTool.IToolView
    public void showStopLiveAlert() {
        if (this.mAlartList.size() > 0) {
            return;
        }
        closeMenu();
        LiveAlertView liveAlertViewAddLiveAlertView = addLiveAlertView();
        liveAlertViewAddLiveAlertView.setTitle("是否停止直播");
        liveAlertViewAddLiveAlertView.setOnButtonClickListener(new LiveAlertView.OnButtonClickListener() { // from class: com.huya.berry.sdklive.liveTool.LiveToolView.14
            @Override // com.huya.berry.sdklive.liveTool.LiveAlertView.OnButtonClickListener
            public void onPositiveButtonClick(View view) {
                if (LiveToolView.this.mCallBack != null) {
                    LiveToolView.this.mCallBack.onStopLive();
                }
            }

            @Override // com.huya.berry.sdklive.liveTool.LiveAlertView.OnButtonClickListener
            public void onNegativeButtonClick(View view) {
                LiveToolView.this.removeAllLiveAlertView();
            }
        });
    }
}
