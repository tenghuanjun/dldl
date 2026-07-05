package com.sqwan.liveshow.huya.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.DisplayCutout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nbvideo.NBVideo;
import com.sqwan.common.util.DisplayUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager;
import com.sqwan.liveshow.huya.danmu.view.InputAndCountDownView;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.skin.SkinHelper;
import com.sqwan.liveshow.huya.skin.view.SkinImageView;
import com.sqwan.liveshow.huya.trackaction.LiveshowTrackManager;
import com.sy.window.WindowX;
import com.sy.window.draggable.SpringBackDraggable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowFloatCoverView extends FrameLayout implements OnMethodListener, LiveshowDanmuManager.SendImLimitListener {
    private static final int ANIMATOR_HIDE = 2;
    private static final int ANIMATOR_HIDING = 1;
    private static final int ANIMATOR_SHOW = 0;
    private static final int ANIMATOR_SHOWING = 3;
    private static final String ANIM_TRANSLATION_Y = "translationY";
    private static final long AUTO_HIDE_TOOLBAR_DURATION = 5000;
    private static final long TOOLBAR_ANIMATOR_DURATION = 500;
    private SkinImageView iv_chatlist;
    private SkinImageView iv_danmu;
    private TextView iv_definition;
    private View liveshowFloatView;
    private InputAndCountDownView ll_input_and_countdownview;
    private final Task mAnimatorTask;
    private final Task.TaskFunc mAutoHideAnimatorRunnable;
    private OnBehaviorListener mBehaviorListener;
    private RatioFrameLayout mFlLiveContainer;
    private View mFlRoot;
    private FrameLayout mFlTopContainer;
    private final AnimatorListenerAdapter mHideAnimatorListenerAdapter;
    private AnimatorSet mHideToolbarAnimatorSet;
    private ImageView mIvLiveAnchorPortrait;
    private SkinImageView mIvLiveBack;
    private ImageView mIvLiveEnlarge;
    private ImageView mIvLiveSwitchSound;
    private ImageView mIvLiveSwitchWidth;
    private ImageView mIvSmallLiveAnchorPortrait;
    private ImageView mIvSmallLiveClose;
    private SkinImageView mIvTopContainerBg;
    private ObjectAnimator mLiveLoadingAnimator;
    private LinearLayout mLlSmallTopContainer;
    private RequestOptions mRequestOptions;
    private final AnimatorListenerAdapter mShowAnimatorListenerAdapter;
    private AnimatorSet mShowToolbarAnimatorSet;
    private WindowX<?> mSoundWindow;
    private TextView mTvLiveAnchorNickname;
    private TextView mTvLiveAudienceCount;
    private TextView mTvSmallLiveAnchorNickname;
    private TextView mTvSmallLiveAudienceCount;
    private ViewGroup mVBottomContainer;
    private int statusAnimator;

    @Override // com.sqwan.liveshow.huya.view.OnMethodListener
    public void loading() {
    }

    public void showError() {
    }

    public void showSuccess() {
    }

    public ImageView getmIvLiveSwitchWidth() {
        return this.mIvLiveSwitchWidth;
    }

    public ImageView getIvLiveSwitchSound() {
        return this.mIvLiveSwitchSound;
    }

    public /* synthetic */ Task.Result lambda$new$0$LiveshowFloatCoverView() {
        hideToolbar();
        return null;
    }

    @Override // com.sqwan.liveshow.huya.view.OnMethodListener
    public void addVideoFragmentContainer(View view, View view2) {
        this.liveshowFloatView = view;
        this.mFlLiveContainer.removeAllViews();
        this.mFlLiveContainer.addView(view2, 0);
    }

    @Override // com.sqwan.liveshow.huya.view.OnMethodListener
    public void setAnchorInfo(String str, String str2, String str3) {
        initData(str, str2, str3);
    }

    @Override // com.sqwan.liveshow.huya.view.OnMethodListener
    public void setOnBehaviorListener(OnBehaviorListener onBehaviorListener) {
        this.mBehaviorListener = onBehaviorListener;
    }

    @Override // com.sqwan.liveshow.huya.view.OnMethodListener
    public void switchFullLiveContainer() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mFlLiveContainer.getLayoutParams();
        marginLayoutParams.topMargin = 0;
        marginLayoutParams.leftMargin = 0;
        marginLayoutParams.rightMargin = 0;
        marginLayoutParams.bottomMargin = 0;
        this.mFlLiveContainer.setLayoutParams(marginLayoutParams);
        this.mFlTopContainer.setVisibility(0);
        this.mVBottomContainer.setVisibility(0);
        this.mLlSmallTopContainer.setVisibility(8);
        this.mIvLiveEnlarge.setVisibility(8);
        OnBehaviorListener onBehaviorListener = this.mBehaviorListener;
        if (onBehaviorListener != null) {
            onBehaviorListener.fullScreen();
        }
        startAutoHide();
    }

    @Override // com.sqwan.liveshow.huya.view.OnMethodListener
    public void networkError() {
        showError();
    }

    @Override // com.sqwan.liveshow.huya.view.OnMethodListener
    public void loadSuccess() {
        showSuccess();
    }

    @Override // com.sqwan.liveshow.huya.view.OnMethodListener
    public void switchSmallLiveContainer() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mFlLiveContainer.getLayoutParams();
        if (getContext().getResources().getConfiguration().orientation == 1) {
            marginLayoutParams.topMargin = DisplayUtil.dip2px(getContext(), 20.0f);
            marginLayoutParams.leftMargin = DisplayUtil.dip2px(getContext(), 4.0f);
            marginLayoutParams.rightMargin = DisplayUtil.dip2px(getContext(), 4.0f);
            marginLayoutParams.bottomMargin = DisplayUtil.dip2px(getContext(), 4.0f);
        } else {
            marginLayoutParams.topMargin = DisplayUtil.dip2px(getContext(), 28.0f);
            marginLayoutParams.leftMargin = DisplayUtil.dip2px(getContext(), 8.0f);
            marginLayoutParams.rightMargin = DisplayUtil.dip2px(getContext(), 8.0f);
            marginLayoutParams.bottomMargin = DisplayUtil.dip2px(getContext(), 8.0f);
        }
        this.mFlLiveContainer.setLayoutParams(marginLayoutParams);
        this.mFlLiveContainer.setPadding(0, 0, 0, 0);
        this.mFlTopContainer.setVisibility(8);
        this.mVBottomContainer.setVisibility(8);
        this.mLlSmallTopContainer.setVisibility(0);
        this.mIvLiveEnlarge.setVisibility(0);
        OnBehaviorListener onBehaviorListener = this.mBehaviorListener;
        if (onBehaviorListener != null) {
            onBehaviorListener.smallScreen();
        }
    }

    public LiveshowFloatCoverView(Context context) {
        this(context, null);
    }

    public LiveshowFloatCoverView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveshowFloatCoverView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.statusAnimator = 0;
        this.mAutoHideAnimatorRunnable = new Task.TaskFunc() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$DPOhDPFEDHCYooqNMLK_23yl4Ew
            @Override // com.sqwan.common.util.task.Task.TaskFunc
            public final Task.Result exec() {
                return this.f$0.lambda$new$0$LiveshowFloatCoverView();
            }
        };
        this.mHideAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.sqwan.liveshow.huya.view.LiveshowFloatCoverView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                LiveshowFloatCoverView.this.statusAnimator = 0;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                LiveshowFloatCoverView.this.statusAnimator = 2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                LiveshowFloatCoverView.this.statusAnimator = 1;
            }
        };
        this.mShowAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.sqwan.liveshow.huya.view.LiveshowFloatCoverView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                LiveshowFloatCoverView.this.statusAnimator = 2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                LiveshowFloatCoverView.this.statusAnimator = 0;
                LiveshowFloatCoverView.this.resetAutoHide();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                LiveshowFloatCoverView.this.statusAnimator = 3;
            }
        };
        initView();
        this.mAnimatorTask = Task.create();
        LiveshowDanmuManager.getInstance().unRegSendImLimitListener(this);
        LiveshowDanmuManager.getInstance().regSendImLimitListener(this);
        post(new Runnable() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$FS11w51xeKyyqe19OPBsad325Qg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.startAutoHide();
            }
        });
    }

    private void initData(String str, String str2, String str3) {
        if (this.mRequestOptions == null) {
            this.mRequestOptions = RequestOptions.circleCropTransform().diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_liveshow_icon_default_portrait)).error(SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_liveshow_icon_default_portrait)).skipMemoryCache(true);
        }
        Glide.with(getContext()).load(str).apply(this.mRequestOptions).into(this.mIvLiveAnchorPortrait);
        this.mTvLiveAnchorNickname.setText(str2);
        this.mTvLiveAudienceCount.setText(str3);
        Glide.with(getContext()).load(str).apply(this.mRequestOptions).into(this.mIvSmallLiveAnchorPortrait);
        this.mTvSmallLiveAnchorNickname.setText(str2);
        this.mTvSmallLiveAudienceCount.setText(str3);
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(SqResUtils.getLayoutId(getContext(), SqR.layout.sy37_liveshow_float_cover_view), (ViewGroup) this, false);
        this.mFlRoot = viewInflate;
        addView(viewInflate, new LinearLayout.LayoutParams(-1, -1));
        this.mLlSmallTopContainer = (LinearLayout) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.ll_small_top_container));
        this.mIvLiveEnlarge = (ImageView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_sy37_liveshow_icon_enlarge));
        this.mFlTopContainer = (FrameLayout) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.fl_sy37_liveshow_room_top_layout));
        this.mIvTopContainerBg = (SkinImageView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_sy37_liveshow_room_top_bg));
        if (getResources().getConfiguration().orientation == 1) {
            this.mIvTopContainerBg.setImageDrawable(SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_liveshow_room_top_bg));
        } else {
            this.mIvTopContainerBg.setBackground(SqR.drawable.sy37_liveshow_room_top_bg_long, SqR.drawable.sy37_liveshow_room_top_bg);
        }
        this.mIvTopContainerBg.setSelected(!LiveshowDanmuManager.getInstance().isImListShow());
        ViewGroup viewGroup = (ViewGroup) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.fl_sy37_liveshow_room_top_content));
        if (getContext().getResources().getConfiguration().orientation == 1) {
            int safeInsetTop = getSafeInsetTop();
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = safeInsetTop;
                viewGroup.setLayoutParams(layoutParams);
            }
        }
        this.mIvLiveAnchorPortrait = (ImageView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_live_anchor_portrait));
        this.mTvLiveAnchorNickname = (TextView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.tv_live_anchor_nickname));
        this.mTvLiveAudienceCount = (TextView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.tv_live_audience_count));
        this.mIvSmallLiveAnchorPortrait = (ImageView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_small_live_anchor_portrait));
        this.mTvSmallLiveAnchorNickname = (TextView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.tv_small_live_anchor_nickname));
        this.mTvSmallLiveAudienceCount = (TextView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.tv_small_live_audience_count));
        this.mVBottomContainer = (ViewGroup) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.v_sy37_liveshow_bottom_bg));
        this.mIvLiveSwitchSound = (ImageView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_live_switch_sound));
        this.mIvLiveSwitchWidth = (ImageView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_live_switch_width));
        this.mFlLiveContainer = (RatioFrameLayout) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.fl_live_container));
        this.mIvSmallLiveClose = (ImageView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_sy37_liveshow_icon_small_close));
        this.mIvLiveBack = (SkinImageView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_sy37_liveshow_icon_top_back));
        this.ll_input_and_countdownview = (InputAndCountDownView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.ll_input_and_countdownview));
        this.iv_definition = (TextView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_definition));
        SkinImageView skinImageView = (SkinImageView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_chatlist));
        this.iv_chatlist = skinImageView;
        skinImageView.setBackground(SqR.drawable.sy37_liveshow_ic_imlist_open, SqR.drawable.sy37_liveshow_ic_imlist_close);
        this.iv_chatlist.setSelected(LiveshowDanmuManager.getInstance().isImListShow());
        SkinImageView skinImageView2 = (SkinImageView) this.mFlRoot.findViewById(SqResUtils.getId(getContext(), SqR.id.iv_danmu));
        this.iv_danmu = skinImageView2;
        skinImageView2.setBackground(SqR.drawable.sy37_liveshow_ic_danmu_open, SqR.drawable.sy37_liveshow_ic_danmu_close);
        this.iv_danmu.setSelected(LiveshowDanmuManager.getInstance().isDanmuShow());
        this.mIvLiveSwitchSound.setOnClickListener(new AnonymousClass3());
        this.mIvLiveSwitchWidth.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$W7-V2uvkYNd7YeodVEnJ4gFUSFo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$1$LiveshowFloatCoverView(view);
            }
        });
        this.mFlLiveContainer.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$0i40Pvsd4jv05vsWjwOS6f1S4VQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$2$LiveshowFloatCoverView(view);
            }
        });
        this.mIvLiveEnlarge.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$vFQVrjaglxn7dpKBBwA_PVU4SYo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$3$LiveshowFloatCoverView(view);
            }
        });
        this.mIvSmallLiveClose.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$UZAMUva_1kc7RNOpcnxtpgK6HIs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$4$LiveshowFloatCoverView(view);
            }
        });
        this.mIvLiveBack.setImageDrawable(SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_liveshow_icon_top_back));
        this.mIvLiveBack.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$8VIY598eH-FkXoxOevlKokKNmZI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$5$LiveshowFloatCoverView(view);
            }
        });
        this.ll_input_and_countdownview.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$7vCZfWMKqazoemgy49nkvGT_AsQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$6$LiveshowFloatCoverView(view);
            }
        });
        int colorValue = SkinHelper.getColorValue(getContext(), SqR.color.sy37_item_roast_view_tv_input_message_text_color, -1);
        int colorValue2 = SkinHelper.getColorValue(getContext(), SqR.color.sy37_item_roast_view_tv_countdown_text_color, -1);
        this.ll_input_and_countdownview.getTvInputMessage().setTextColor(colorValue);
        this.ll_input_and_countdownview.getTvCountDown().setTextColor(colorValue2);
        this.iv_chatlist.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$xq5i2p8wViqBOynJATzDVKfNeeY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$7$LiveshowFloatCoverView(view);
            }
        });
        this.iv_danmu.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$OFgCeEdFy22qQeThhz1sCkaTmIM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$8$LiveshowFloatCoverView(view);
            }
        });
        this.iv_definition.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$7RfsN2q709hZbiiiVDyVrY5tk9I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$9$LiveshowFloatCoverView(view);
            }
        });
    }

    /* JADX INFO: renamed from: com.sqwan.liveshow.huya.view.LiveshowFloatCoverView$3, reason: invalid class name */
    class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (LiveshowFloatCoverView.this.liveshowFloatView == null) {
                return;
            }
            Context context = LiveshowFloatCoverView.this.getContext();
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                WindowManager windowManager = activity.getWindowManager();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                int i = displayMetrics.widthPixels;
                int i2 = displayMetrics.heightPixels;
                if (LiveshowFloatCoverView.this.mSoundWindow != null && LiveshowFloatCoverView.this.mSoundWindow.isShowing()) {
                    LiveshowFloatCoverView.this.mSoundWindow.cancel();
                }
                LiveshowFloatCoverView.this.mSoundWindow = new WindowX(activity).setContentView(SqResUtils.getLayoutId(context, SqR.layout.sy37_layout_sound_float_window)).setGravity(8388661).setYOffset((i2 / 4) * 3).setDraggable(new SpringBackDraggable() { // from class: com.sqwan.liveshow.huya.view.LiveshowFloatCoverView.3.2
                    private View backgroundView;
                    private Drawable circleDrawable;
                    private Drawable leftSemicircleDrawable;
                    private Drawable rightSemicircleDrawable;

                    @Override // com.sy.window.draggable.BaseDraggable
                    public void updateWindowCoordinate(int i3, int i4) {
                        super.updateWindowCoordinate(i3, i4);
                        if (this.backgroundView == null) {
                            this.backgroundView = getWindowX().findViewById(SqResUtils.getId(LiveshowFloatCoverView.this.getContext(), SqR.id.bg_sound_float_record));
                        }
                        if (this.leftSemicircleDrawable == null) {
                            this.leftSemicircleDrawable = ContextCompat.getDrawable(LiveshowFloatCoverView.this.getContext(), SqResUtils.getDrawableId(LiveshowFloatCoverView.this.getContext(), SqR.drawable.sy37_liveshow_bg_sound_float_left_semicircle));
                        }
                        if (this.rightSemicircleDrawable == null) {
                            this.rightSemicircleDrawable = ContextCompat.getDrawable(LiveshowFloatCoverView.this.getContext(), SqResUtils.getDrawableId(LiveshowFloatCoverView.this.getContext(), SqR.drawable.sy37_liveshow_bg_sound_float_right_semicircle));
                        }
                        if (this.circleDrawable == null) {
                            this.circleDrawable = ContextCompat.getDrawable(LiveshowFloatCoverView.this.getContext(), SqResUtils.getDrawableId(LiveshowFloatCoverView.this.getContext(), SqR.drawable.sy37_liveshow_bg_sound_float_circle));
                        }
                        if (i3 == 0) {
                            this.backgroundView.setBackground(this.leftSemicircleDrawable);
                        } else if (i3 == getWindowWidth() - getDecorView().getWidth()) {
                            this.backgroundView.setBackground(this.rightSemicircleDrawable);
                        } else {
                            this.backgroundView.setBackground(this.circleDrawable);
                        }
                    }
                }).setOnClickListener(new WindowX.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$3$cT4Wkezk8PD5SigUjOpHNC2FvAw
                    @Override // com.sy.window.WindowX.OnClickListener
                    public final void onClick(WindowX windowX, View view2) {
                        windowX.cancel();
                    }
                }).setOnWindowLifecycle(new AnonymousClass1(i));
                LiveshowFloatCoverView.this.mSoundWindow.show();
            }
        }

        /* JADX INFO: renamed from: com.sqwan.liveshow.huya.view.LiveshowFloatCoverView$3$1, reason: invalid class name */
        class AnonymousClass1 implements WindowX.OnWindowLifecycle {
            private ObjectAnimator rotationAnimator;
            final /* synthetic */ int val$screenWidth;

            @Override // com.sy.window.WindowX.OnWindowLifecycle
            public /* synthetic */ void onWindowVisibilityChanged(WindowX<?> windowX, int i) {
                WindowX.OnWindowLifecycle.CC.$default$onWindowVisibilityChanged(this, windowX, i);
            }

            AnonymousClass1(int i) {
                this.val$screenWidth = i;
            }

            @Override // com.sy.window.WindowX.OnWindowLifecycle
            public void onWindowShow(WindowX<?> windowX) {
                if (LiveshowDanmuManager.getInstance().isImListShow()) {
                    LiveshowTrackManager.getInstance().trackHalfScreenOffAction();
                } else {
                    LiveshowTrackManager.getInstance().trackFullScreenOffAction();
                }
                LiveshowTrackManager.getInstance().trackListeningOnAction();
                if (LiveshowFloatCoverView.this.liveshowFloatView == null) {
                    return;
                }
                RoomListView roomListView = LiveshowManager.getInstance().getRoomListView();
                if (roomListView != null) {
                    roomListView.release();
                }
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
                valueAnimatorOfFloat.setDuration(200L);
                valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$3$1$03ZLVaO9dIU4R3h65pTafsbNPNQ
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.f$0.lambda$onWindowShow$0$LiveshowFloatCoverView$3$1(valueAnimator);
                    }
                });
                valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.sqwan.liveshow.huya.view.LiveshowFloatCoverView.3.1.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        LiveshowFloatCoverView.this.liveshowFloatView.setVisibility(8);
                        NBVideo nBVideo = (NBVideo) LiveshowFloatCoverView.this.liveshowFloatView.findViewById(SqResUtils.getId(LiveshowFloatCoverView.this.getContext(), SqR.id.playerview));
                        if (nBVideo == null) {
                            return;
                        }
                        nBVideo.setOnlySoundEnable(!nBVideo.isOnlySoundEnable());
                    }
                });
                valueAnimatorOfFloat.start();
                ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
                valueAnimatorOfFloat2.setDuration(250L);
                valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$3$1$jA_lPnV7Aaa0kWR96j4Y2H8znLc
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.f$0.lambda$onWindowShow$1$LiveshowFloatCoverView$3$1(valueAnimator);
                    }
                });
                valueAnimatorOfFloat2.start();
                ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(0.0f, this.val$screenWidth / 2.0f);
                valueAnimatorOfFloat3.setDuration(250L);
                valueAnimatorOfFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$3$1$DfmD1jUGkDvVno3iua5gvscPsiI
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.f$0.lambda$onWindowShow$2$LiveshowFloatCoverView$3$1(valueAnimator);
                    }
                });
                valueAnimatorOfFloat3.start();
                ValueAnimator valueAnimatorOfFloat4 = ValueAnimator.ofFloat(0.0f, windowX.getWindowParams().y / 2.0f);
                valueAnimatorOfFloat4.setDuration(250L);
                valueAnimatorOfFloat4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$3$1$zl9EsXbdQhNH3-FdaFdYJ-xvUzM
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.f$0.lambda$onWindowShow$3$LiveshowFloatCoverView$3$1(valueAnimator);
                    }
                });
                valueAnimatorOfFloat4.start();
                SkinImageView skinImageView = (SkinImageView) windowX.findViewById(SqResUtils.getId(LiveshowFloatCoverView.this.getContext(), SqR.id.iv_sound_float_record));
                if (skinImageView == null) {
                    return;
                }
                skinImageView.setImageDrawable(ContextCompat.getDrawable(LiveshowFloatCoverView.this.getContext(), SqResUtils.getDrawableId(LiveshowFloatCoverView.this.getContext(), SqR.drawable.sy37_liveshow_icon_record)));
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(skinImageView, "rotation", 0.0f, 360.0f);
                this.rotationAnimator = objectAnimatorOfFloat;
                objectAnimatorOfFloat.setDuration(5000L);
                this.rotationAnimator.setRepeatCount(-1);
                this.rotationAnimator.setInterpolator(new LinearInterpolator());
                this.rotationAnimator.start();
            }

            public /* synthetic */ void lambda$onWindowShow$0$LiveshowFloatCoverView$3$1(ValueAnimator valueAnimator) {
                LiveshowFloatCoverView.this.liveshowFloatView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }

            public /* synthetic */ void lambda$onWindowShow$1$LiveshowFloatCoverView$3$1(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LiveshowFloatCoverView.this.liveshowFloatView.setScaleX(fFloatValue);
                LiveshowFloatCoverView.this.liveshowFloatView.setScaleY(fFloatValue);
            }

            public /* synthetic */ void lambda$onWindowShow$2$LiveshowFloatCoverView$3$1(ValueAnimator valueAnimator) {
                LiveshowFloatCoverView.this.liveshowFloatView.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }

            public /* synthetic */ void lambda$onWindowShow$3$LiveshowFloatCoverView$3$1(ValueAnimator valueAnimator) {
                LiveshowFloatCoverView.this.liveshowFloatView.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }

            @Override // com.sy.window.WindowX.OnWindowLifecycle
            public void onWindowCancel(WindowX<?> windowX) {
                LiveshowTrackManager.getInstance().trackListeningOffAction();
                if (LiveshowDanmuManager.getInstance().isImListShow()) {
                    LiveshowTrackManager.getInstance().trackHalfScreenOnAction();
                } else {
                    LiveshowTrackManager.getInstance().trackFullScreenOnAction();
                }
                ObjectAnimator objectAnimator = this.rotationAnimator;
                if (objectAnimator != null) {
                    objectAnimator.cancel();
                }
                if (LiveshowFloatCoverView.this.liveshowFloatView == null) {
                    return;
                }
                LiveshowFloatCoverView.this.liveshowFloatView.setVisibility(0);
                RoomListView roomListView = LiveshowManager.getInstance().getRoomListView();
                if (roomListView != null) {
                    roomListView.addView();
                }
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                valueAnimatorOfFloat.setDuration(200L);
                valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$3$1$5ha2HMxrREwGUMguqM6vPlFXrV0
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.f$0.lambda$onWindowCancel$4$LiveshowFloatCoverView$3$1(valueAnimator);
                    }
                });
                valueAnimatorOfFloat.start();
                ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
                valueAnimatorOfFloat2.setDuration(250L);
                valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$3$1$cFlZKs4vEWwvTJKt-RDQUXlA1-c
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.f$0.lambda$onWindowCancel$5$LiveshowFloatCoverView$3$1(valueAnimator);
                    }
                });
                valueAnimatorOfFloat2.start();
                ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(LiveshowFloatCoverView.this.liveshowFloatView.getTranslationX(), 0.0f);
                valueAnimatorOfFloat3.setDuration(250L);
                valueAnimatorOfFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$3$1$_WtIHgUdrZDbapi0Q7t8oPPQOGw
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.f$0.lambda$onWindowCancel$6$LiveshowFloatCoverView$3$1(valueAnimator);
                    }
                });
                valueAnimatorOfFloat3.start();
                ValueAnimator valueAnimatorOfFloat4 = ValueAnimator.ofFloat(LiveshowFloatCoverView.this.liveshowFloatView.getTranslationY(), 0.0f);
                valueAnimatorOfFloat4.setDuration(250L);
                valueAnimatorOfFloat4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$LiveshowFloatCoverView$3$1$fIyXEKGDTCkxuMswqWiKLJcw1Zc
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.f$0.lambda$onWindowCancel$7$LiveshowFloatCoverView$3$1(valueAnimator);
                    }
                });
                valueAnimatorOfFloat4.start();
                NBVideo nBVideo = (NBVideo) LiveshowFloatCoverView.this.liveshowFloatView.findViewById(SqResUtils.getId(LiveshowFloatCoverView.this.getContext(), SqR.id.playerview));
                if (nBVideo != null) {
                    nBVideo.setOnlySoundEnable(false);
                }
            }

            public /* synthetic */ void lambda$onWindowCancel$4$LiveshowFloatCoverView$3$1(ValueAnimator valueAnimator) {
                LiveshowFloatCoverView.this.liveshowFloatView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }

            public /* synthetic */ void lambda$onWindowCancel$5$LiveshowFloatCoverView$3$1(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LiveshowFloatCoverView.this.liveshowFloatView.setScaleX(fFloatValue);
                LiveshowFloatCoverView.this.liveshowFloatView.setScaleY(fFloatValue);
            }

            public /* synthetic */ void lambda$onWindowCancel$6$LiveshowFloatCoverView$3$1(ValueAnimator valueAnimator) {
                LiveshowFloatCoverView.this.liveshowFloatView.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }

            public /* synthetic */ void lambda$onWindowCancel$7$LiveshowFloatCoverView$3$1(ValueAnimator valueAnimator) {
                LiveshowFloatCoverView.this.liveshowFloatView.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }

            @Override // com.sy.window.WindowX.OnWindowLifecycle
            public void onWindowRecycler(WindowX<?> windowX) {
                this.rotationAnimator = null;
            }
        }
    }

    public /* synthetic */ void lambda$initView$1$LiveshowFloatCoverView(View view) {
        switchSmallLiveContainer();
    }

    public /* synthetic */ void lambda$initView$2$LiveshowFloatCoverView(View view) {
        int i = this.statusAnimator;
        if (i == 0 || i == 3) {
            endAnimatorAndHideToolbar();
        } else {
            showToolbar();
        }
    }

    public /* synthetic */ void lambda$initView$3$LiveshowFloatCoverView(View view) {
        switchFullLiveContainer();
    }

    public /* synthetic */ void lambda$initView$4$LiveshowFloatCoverView(View view) {
        OnBehaviorListener onBehaviorListener = this.mBehaviorListener;
        if (onBehaviorListener != null) {
            onBehaviorListener.levelLive();
        }
    }

    public /* synthetic */ void lambda$initView$5$LiveshowFloatCoverView(View view) {
        if (!this.iv_chatlist.isSelected()) {
            this.iv_chatlist.performClick();
            return;
        }
        OnBehaviorListener onBehaviorListener = this.mBehaviorListener;
        if (onBehaviorListener != null) {
            onBehaviorListener.backLiveRoom();
        }
    }

    public /* synthetic */ void lambda$initView$6$LiveshowFloatCoverView(View view) {
        OnBehaviorListener onBehaviorListener = this.mBehaviorListener;
        if (onBehaviorListener != null) {
            onBehaviorListener.clickInputChat();
        }
    }

    public /* synthetic */ void lambda$initView$7$LiveshowFloatCoverView(View view) {
        this.iv_chatlist.setSelected(!r5.isSelected());
        if (this.iv_chatlist.isSelected()) {
            ViewUtils.hidden(this.ll_input_and_countdownview);
            if (getContext().getResources().getConfiguration().orientation == 1) {
                this.mFlLiveContainer.setSizeRatio(16.0f, 9.0f);
                ViewGroup.LayoutParams layoutParams = this.mFlLiveContainer.getLayoutParams();
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    layoutParams.height = -2;
                    ((RelativeLayout.LayoutParams) layoutParams).addRule(3, this.mFlTopContainer.getId());
                    this.mFlLiveContainer.setLayoutParams(layoutParams);
                }
                ViewGroup viewGroup = (ViewGroup) this.mFlTopContainer.getParent();
                viewGroup.removeView(this.mFlTopContainer);
                viewGroup.addView(this.mFlTopContainer, 0);
                this.mIvTopContainerBg.setImageDrawable(SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_liveshow_room_top_bg));
                this.mIvLiveBack.setImageDrawable(SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_liveshow_icon_top_back));
            }
        } else {
            endAutoHideAndShowToolbar();
            ViewUtils.show(this.ll_input_and_countdownview);
            if (getContext().getResources().getConfiguration().orientation == 1) {
                this.mFlLiveContainer.setSizeRatio(0.0f, 0.0f);
                ViewGroup.LayoutParams layoutParams2 = this.mFlLiveContainer.getLayoutParams();
                if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
                    layoutParams2.height = -1;
                    ((RelativeLayout.LayoutParams) layoutParams2).removeRule(3);
                    this.mFlLiveContainer.setLayoutParams(layoutParams2);
                }
                ViewGroup viewGroup2 = (ViewGroup) this.mFlTopContainer.getParent();
                viewGroup2.removeView(this.mFlTopContainer);
                viewGroup2.addView(this.mFlTopContainer, viewGroup2.getChildCount() - 1);
                this.mIvTopContainerBg.setImageDrawable(null);
                this.mIvLiveBack.setImageDrawable(SkinHelper.getDrawable(getContext(), SqR.drawable.sy37_live_show_full_screen_back_icon));
            }
        }
        this.mIvTopContainerBg.setSelected(!this.iv_chatlist.isSelected());
        if (this.mBehaviorListener != null) {
            startAutoHide();
            this.mBehaviorListener.showChatList(this.iv_chatlist.isSelected());
        }
    }

    public /* synthetic */ void lambda$initView$8$LiveshowFloatCoverView(View view) {
        this.iv_danmu.setSelected(!r2.isSelected());
        OnBehaviorListener onBehaviorListener = this.mBehaviorListener;
        if (onBehaviorListener != null) {
            onBehaviorListener.showDanmu(this.iv_danmu.isSelected());
        }
    }

    public /* synthetic */ void lambda$initView$9$LiveshowFloatCoverView(View view) {
        OnBehaviorListener onBehaviorListener = this.mBehaviorListener;
        if (onBehaviorListener != null) {
            onBehaviorListener.clickDefinition();
        }
    }

    public void startAutoHide() {
        this.mAnimatorTask.oneShot(5000L, this.mAutoHideAnimatorRunnable);
    }

    private void endAutoHideAndShowToolbar() {
        this.mAnimatorTask.stop();
        showToolbar();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAutoHide() {
        this.mAnimatorTask.stop();
        this.mAnimatorTask.oneShot(5000L, this.mAutoHideAnimatorRunnable);
    }

    private void endAnimatorAndHideToolbar() {
        this.mAnimatorTask.stop();
        hideToolbar();
    }

    private void hideToolbar() {
        AnimatorSet animatorSet;
        int i = this.statusAnimator;
        if (i == 1 || i == 2) {
            return;
        }
        if (i == 3 && (animatorSet = this.mShowToolbarAnimatorSet) != null && animatorSet.isRunning()) {
            this.mShowToolbarAnimatorSet.cancel();
        }
        hideToolbarAnimator();
    }

    private void hideToolbarAnimator() {
        if (this.mFlTopContainer.getVisibility() == 0 || this.mVBottomContainer.getVisibility() == 0) {
            this.mHideToolbarAnimatorSet = new AnimatorSet();
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mFlTopContainer, ANIM_TRANSLATION_Y, (-r0.getHeight()) + 1);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.mVBottomContainer, ANIM_TRANSLATION_Y, r2.getHeight() - 1);
            if (isImListShow()) {
                if (!isShowGuide()) {
                    this.mHideToolbarAnimatorSet.playTogether(objectAnimatorOfFloat2);
                }
            } else {
                this.mHideToolbarAnimatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
            }
            this.mHideToolbarAnimatorSet.addListener(this.mHideAnimatorListenerAdapter);
            this.mHideToolbarAnimatorSet.setDuration(500L);
            if (this.mHideToolbarAnimatorSet.isRunning()) {
                return;
            }
            this.mHideToolbarAnimatorSet.start();
        }
    }

    private void showToolbar() {
        AnimatorSet animatorSet;
        int i = this.statusAnimator;
        if (i == 0 || i == 3) {
            resetAutoHide();
            return;
        }
        if (i == 1 && (animatorSet = this.mHideToolbarAnimatorSet) != null && animatorSet.isRunning()) {
            this.mHideToolbarAnimatorSet.cancel();
        }
        showToolbarAnimator();
    }

    private void showToolbarAnimator() {
        if (this.mFlTopContainer.getVisibility() == 0 || this.mVBottomContainer.getVisibility() == 0) {
            this.mShowToolbarAnimatorSet = new AnimatorSet();
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mFlTopContainer, ANIM_TRANSLATION_Y, 0.0f);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.mVBottomContainer, ANIM_TRANSLATION_Y, 0.0f);
            if (isImListShow()) {
                this.mShowToolbarAnimatorSet.playTogether(objectAnimatorOfFloat2);
            } else {
                this.mShowToolbarAnimatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
            }
            this.mShowToolbarAnimatorSet.addListener(this.mShowAnimatorListenerAdapter);
            this.mShowToolbarAnimatorSet.setDuration(500L);
            if (this.mShowToolbarAnimatorSet.isRunning()) {
                return;
            }
            this.mShowToolbarAnimatorSet.start();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recycle();
    }

    public void recycle() {
        Task task = this.mAnimatorTask;
        if (task != null && task.isRunning()) {
            this.mAnimatorTask.stop();
        }
        AnimatorSet animatorSet = this.mHideToolbarAnimatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.mHideToolbarAnimatorSet.cancel();
        }
        AnimatorSet animatorSet2 = this.mShowToolbarAnimatorSet;
        if (animatorSet2 != null) {
            animatorSet2.removeAllListeners();
            this.mShowToolbarAnimatorSet.cancel();
        }
        ObjectAnimator objectAnimator = this.mLiveLoadingAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.mLiveLoadingAnimator = null;
        }
        WindowX<?> windowX = this.mSoundWindow;
        if (windowX != null) {
            windowX.recycle();
            this.mSoundWindow = null;
        }
        LiveshowDanmuManager.getInstance().unRegSendImLimitListener(this);
    }

    public boolean isImListShow() {
        return this.iv_chatlist.isSelected();
    }

    public boolean isDanmuShow() {
        return this.iv_danmu.isSelected();
    }

    public TextView getIv_definition() {
        return this.iv_definition;
    }

    @Override // com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager.SendImLimitListener
    public void onCountDown(boolean z, String str) {
        this.ll_input_and_countdownview.setEnabled(z);
        if (!z) {
            this.ll_input_and_countdownview.getTvCountDown().setText(String.format("%ss", str));
        } else {
            this.ll_input_and_countdownview.getTvCountDown().setText("");
        }
    }

    public InputAndCountDownView getLl_input_and_countdownview() {
        return this.ll_input_and_countdownview;
    }

    public FrameLayout getFlTopContainer() {
        return this.mFlTopContainer;
    }

    public ViewGroup getVBottomContainer() {
        return this.mVBottomContainer;
    }

    private boolean isShowGuide() {
        View view = this.liveshowFloatView;
        return view != null && (view instanceof LiveshowFloatView) && ViewUtils.isShow(((LiveshowFloatView) view).guideView);
    }

    public int getSafeInsetTop() {
        Window window;
        View decorView;
        WindowInsets rootWindowInsets;
        DisplayCutout displayCutout;
        if (Build.VERSION.SDK_INT < 28) {
            return 0;
        }
        Context context = getContext();
        if (!(context instanceof Activity) || (window = ((Activity) context).getWindow()) == null || (decorView = window.getDecorView()) == null || (rootWindowInsets = decorView.getRootWindowInsets()) == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null) {
            return 0;
        }
        return displayCutout.getSafeInsetTop();
    }
}
