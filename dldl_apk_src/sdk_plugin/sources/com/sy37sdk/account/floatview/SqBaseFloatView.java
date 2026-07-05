package com.sy37sdk.account.floatview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sq.tools.utils.ScreenUtils;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.DensityUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sy.window.WindowX;
import com.sy.window.draggable.BaseDraggable;
import com.sy37sdk.account.floatview.BottomDeleteView;
import com.sy37sdk.account.floatview.FloatViewDraggable;
import com.sy37sdk.account.floatview.FloatViewUtils;
import com.sy37sdk.account.floatview.FloatWindow;
import com.sy37sdk.account.floatview.redpacket.RedPacketInfo;
import com.sy37sdk.account.floatview.redpacket.RedPacketManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SqBaseFloatView extends RelativeLayout {
    private static final float BUBBLE_SCALEX = 0.6f;
    private static final float BUBBLE_SCALEY = 0.6f;
    private DismissListener dismissListener;
    private final Activity mActivity;
    private boolean mAnimatingFlag;
    private Animator mAnimator;
    public BottomDeleteView mBottomDeleteView;
    private int mCurrentBubbleViewId;
    private float mDeletedStartY;
    private final FloatWindow.DragBottom2DeleteCallback mDragBottom2DeleteCallback;
    private final FloatViewDraggable mDraggable;
    private boolean mDraggingFlag;
    private Path mFloatPath;
    private Region mFloatRegion;
    private Path mHidePath;
    private Region mHideRegion;
    private final String mIconUrl;
    private final View mMenuLayout;
    private int mNormalViewId;
    private View.OnClickListener mNormalViewOnClickListener;
    private FloatWindow.OnDragCallBack mOnDragCallBack;
    private PopupWindow mPopupWindow;
    private ImageView mRedImageView;
    private int mScreenHeight;
    private Region mScreenRegion;
    private int mScreenWidth;
    protected Runnable mStayEdgeRunnable;
    private MenuConfig mWarnMenuConfig;
    private final View mWindowDecorView;
    private final WindowX<?> mWindowX;
    private MenuListener menuListener;
    private View.OnClickListener onClickListener;

    public interface DismissListener {
        void onDismiss();
    }

    public interface MenuListener {
        void onMenuShow();
    }

    public void handleMenuConfigClick(MenuConfig menuConfig) {
    }

    public SqBaseFloatView(Activity activity, String str, View view, RedPacketInfo redPacketInfo, FloatWindow.DragBottom2DeleteCallback dragBottom2DeleteCallback) {
        super(activity);
        this.mCurrentBubbleViewId = -1;
        this.mNormalViewId = -1;
        this.mStayEdgeRunnable = new Runnable() { // from class: com.sy37sdk.account.floatview.SqBaseFloatView.7
            @Override // java.lang.Runnable
            public void run() {
                if (SqBaseFloatView.this.mAnimatingFlag || SqBaseFloatView.this.mDraggingFlag) {
                    return;
                }
                if (SqBaseFloatView.this.mPopupWindow == null || !SqBaseFloatView.this.mPopupWindow.isShowing()) {
                    View decorView = SqBaseFloatView.this.mWindowX.getDecorView();
                    if (SqBaseFloatView.this.isLeftShow()) {
                        if (SqBaseFloatView.this.isTopShow()) {
                            SqBaseFloatView.this.hideHalfView(decorView, 48);
                            return;
                        } else {
                            SqBaseFloatView.this.hideHalfView(decorView, 3);
                            return;
                        }
                    }
                    if (SqBaseFloatView.this.isTopShow()) {
                        SqBaseFloatView.this.hideHalfView(decorView, 48);
                    } else {
                        SqBaseFloatView.this.hideHalfView(decorView, 5);
                    }
                }
            }
        };
        this.mIconUrl = str;
        this.mActivity = activity;
        this.mDragBottom2DeleteCallback = dragBottom2DeleteCallback;
        this.mMenuLayout = view;
        setClickable(true);
        if (redPacketInfo != null) {
            addRedPackView(activity, redPacketInfo);
        } else {
            addNormalView(activity, view);
        }
        this.mWindowDecorView = this.mActivity.getWindow().getDecorView();
        FloatViewDraggable floatViewDraggable = new FloatViewDraggable();
        this.mDraggable = floatViewDraggable;
        floatViewDraggable.setAllowMoveToScreenNotch(false);
        this.mDraggable.setDraggingCallback(new BaseDraggable.DraggingCallback() { // from class: com.sy37sdk.account.floatview.SqBaseFloatView.1
            @Override // com.sy.window.draggable.BaseDraggable.DraggingCallback
            public void onStartDragging(WindowX<?> windowX) {
                SqBaseFloatView.this.mDraggingFlag = true;
                if (SqBaseFloatView.this.mOnDragCallBack != null) {
                    SqBaseFloatView.this.mOnDragCallBack.onStartDrag();
                }
                View decorView = SqBaseFloatView.this.mWindowX.getDecorView();
                if (!SqBaseFloatView.this.isFullShowView(decorView)) {
                    SqBaseFloatView.this.showFullView(decorView);
                }
                if (SqBaseFloatView.this.mDragBottom2DeleteCallback == null) {
                    return;
                }
                SqBaseFloatView.this.mDragBottom2DeleteCallback.onDragBottom2Delete(false, true, false);
            }

            @Override // com.sy.window.draggable.BaseDraggable.DraggingCallback
            public void onExecuteDragging(WindowX<?> windowX) {
                if (SqBaseFloatView.this.mDragBottom2DeleteCallback == null) {
                    return;
                }
                SqBaseFloatView.this.mDragBottom2DeleteCallback.onDragBottom2Delete(false, true, SqBaseFloatView.this.reachDeleteArea(windowX));
            }

            @Override // com.sy.window.draggable.BaseDraggable.DraggingCallback
            public void onStopDragging(WindowX<?> windowX) {
                SqBaseFloatView.this.mDraggingFlag = false;
                SqBaseFloatView.this.updateBubbleView();
                SqBaseFloatView.this.saveWindowCoordinate();
                if (SqBaseFloatView.this.mDragBottom2DeleteCallback == null) {
                    return;
                }
                SqBaseFloatView.this.mDragBottom2DeleteCallback.onDragBottom2Delete(SqBaseFloatView.this.reachDeleteArea(windowX), false, false);
            }
        });
        this.mDraggable.setSpringBackAnimCallback(new FloatViewDraggable.SpringBackAnimCallback() { // from class: com.sy37sdk.account.floatview.SqBaseFloatView.2
            @Override // com.sy37sdk.account.floatview.FloatViewDraggable.SpringBackAnimCallback
            public void onSpringBackAnimationStart(WindowX<?> windowX, Animator animator) {
                SqBaseFloatView.this.mAnimatingFlag = true;
                SqBaseFloatView.this.mAnimator = animator;
            }

            @Override // com.sy37sdk.account.floatview.FloatViewDraggable.SpringBackAnimCallback
            public void onSpringBackAnimationEnd(WindowX<?> windowX, Animator animator) {
                SqBaseFloatView.this.mAnimatingFlag = false;
                SqBaseFloatView.this.mAnimator = null;
                SqBaseFloatView.this.saveWindowCoordinate();
                SqBaseFloatView.this.postStayEdgeRunnable();
                if (SqBaseFloatView.this.mOnDragCallBack != null) {
                    SqBaseFloatView.this.mOnDragCallBack.onStayEdge();
                }
            }
        });
        WindowX<?> onWindowLifecycle = new WindowX(this.mActivity).setAnimStyle(0).setContentView(this).setDraggable(this.mDraggable, false).setOnWindowLifecycle(new WindowX.OnWindowLifecycle() { // from class: com.sy37sdk.account.floatview.SqBaseFloatView.3
            @Override // com.sy.window.WindowX.OnWindowLifecycle
            public /* synthetic */ void onWindowCancel(WindowX<?> windowX) {
                WindowX.OnWindowLifecycle.CC.$default$onWindowCancel(this, windowX);
            }

            @Override // com.sy.window.WindowX.OnWindowLifecycle
            public /* synthetic */ void onWindowRecycler(WindowX<?> windowX) {
                WindowX.OnWindowLifecycle.CC.$default$onWindowRecycler(this, windowX);
            }

            @Override // com.sy.window.WindowX.OnWindowLifecycle
            public /* synthetic */ void onWindowVisibilityChanged(WindowX<?> windowX, int i) {
                WindowX.OnWindowLifecycle.CC.$default$onWindowVisibilityChanged(this, windowX, i);
            }

            @Override // com.sy.window.WindowX.OnWindowLifecycle
            public void onWindowShow(WindowX<?> windowX) {
                SqBaseFloatView.this.postStayEdgeRunnable();
            }
        });
        this.mWindowX = onWindowLifecycle;
        onWindowLifecycle.show();
        int screenWidth = DisplayUtil.getScreenWidth(getContext());
        int screenHeight = DisplayUtil.getScreenHeight(getContext());
        this.mHideRegion = new Region();
        this.mFloatRegion = new Region();
        this.mFloatPath = new Path();
        if (this.mScreenRegion == null) {
            this.mScreenRegion = new Region(0, 0, screenWidth, screenHeight);
        }
        BottomDeleteView bottomDeleteView = new BottomDeleteView(activity);
        this.mBottomDeleteView = bottomDeleteView;
        bottomDeleteView.attach(new BottomDeleteView.MoniterHeightListener() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$v7p2R5hrzKOQJDIkPWqdwxW5CaE
            @Override // com.sy37sdk.account.floatview.BottomDeleteView.MoniterHeightListener
            public final void onChange(int i) {
                this.f$0.lambda$new$0$SqBaseFloatView(i);
            }
        });
        recoverWindowCoordinate();
    }

    public /* synthetic */ void lambda$new$0$SqBaseFloatView(int i) {
        this.mDeletedStartY = i;
        this.mScreenWidth = ScreenUtils.getScreenWidth(getContext());
        this.mScreenHeight = (int) (this.mDeletedStartY + this.mBottomDeleteView.getHeight());
        this.mScreenRegion = new Region(0, 0, this.mScreenWidth, this.mScreenHeight);
        Path deleteIrregularPath = getDeleteIrregularPath();
        this.mHidePath = deleteIrregularPath;
        this.mHideRegion.setPath(deleteIrregularPath, this.mScreenRegion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBubbleView() {
        if (((ImageView) findViewById(this.mNormalViewId)) == null) {
            LogUtil.i("normalView is null, updateBubbleView return");
            return;
        }
        removeView(findViewById(this.mCurrentBubbleViewId));
        TextView textViewCreateBubbleView = createBubbleView();
        if (textViewCreateBubbleView != null) {
            updateBubblePosition(textViewCreateBubbleView);
            textViewCreateBubbleView.setVisibility(0);
            if (isNeedShake()) {
                post(new Runnable() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$Qp2244yznJR33C1vNE7CIgriEXE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.setRotePivot();
                    }
                });
            }
        }
    }

    private void updateBubblePosition(final TextView textView) {
        if (textView != null && findViewById(this.mCurrentBubbleViewId) == null) {
            ImageView imageView = (ImageView) findViewById(this.mNormalViewId);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams2.removeRule(9);
            layoutParams2.removeRule(10);
            layoutParams2.removeRule(1);
            layoutParams2.removeRule(3);
            layoutParams2.setMargins(0, 0, 0, 0);
            if (isTopShow()) {
                LogUtil.d("showRemindAnim isTopShow");
                if (isLeftShow()) {
                    LogUtil.d("showRemindAnim isTopShow isLeftShow");
                    textView.setBackgroundResource(SqResUtils.getDrawableId(this.mActivity, "background_bubble_bottom_right"));
                    layoutParams2.addRule(10);
                    layoutParams.addRule(1, imageView.getId());
                    layoutParams.setMargins(-dip2px(15), -dip2px(15), 0, dip2px(15));
                } else {
                    LogUtil.d("showRemindAnim isTopShow isRightShow");
                    textView.setBackgroundResource(SqResUtils.getDrawableId(this.mActivity, "background_bubble_bottom_left"));
                    layoutParams2.addRule(10);
                    layoutParams2.addRule(1, textView.getId());
                    layoutParams.addRule(9);
                    layoutParams.setMargins(0, -dip2px(15), -dip2px(15), 0);
                }
                layoutParams.addRule(3, imageView.getId());
            } else {
                if (isLeftShow()) {
                    LogUtil.d("showRemindAnim isLeftShow");
                    textView.setBackgroundResource(SqResUtils.getDrawableId(this.mActivity, "background_bubble_right"));
                    layoutParams.addRule(10);
                    layoutParams.addRule(1, imageView.getId());
                    layoutParams2.addRule(9);
                    layoutParams.setMargins(-dip2px(15), dip2px(15), 0, -dip2px(15));
                } else {
                    LogUtil.d("showRemindAnim isRightShow");
                    textView.setBackgroundResource(SqResUtils.getDrawableId(this.mActivity, "background_bubble_left"));
                    layoutParams.addRule(10);
                    layoutParams.addRule(9);
                    layoutParams2.addRule(1, textView.getId());
                    layoutParams.setMargins(0, dip2px(15), -dip2px(15), -dip2px(15));
                }
                layoutParams2.addRule(3, textView.getId());
            }
            addView(textView, layoutParams);
            textView.post(new Runnable() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$pjRXTz8uwj3p7TNmgLZgp1DLFyo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateBubblePosition$1$SqBaseFloatView(textView);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateBubblePosition$1$SqBaseFloatView(TextView textView) {
        setPivot(textView);
        textView.setScaleX(0.6f);
        textView.setScaleY(0.6f);
    }

    private void setPivot(View view) {
        if (isTopShow()) {
            if (isLeftShow()) {
                view.setPivotX(0.0f);
                view.setPivotY(0.0f);
                return;
            } else {
                view.setPivotX(view.getWidth());
                view.setPivotY(0.0f);
                return;
            }
        }
        if (isLeftShow()) {
            view.setPivotX(0.0f);
            view.setPivotY(view.getHeight());
        } else {
            view.setPivotX(view.getWidth());
            view.setPivotY(view.getHeight());
        }
    }

    private boolean isNeedShake() {
        MenuConfig menuConfig = this.mWarnMenuConfig;
        if (menuConfig != null) {
            return menuConfig.warningType.contains("3");
        }
        return false;
    }

    private boolean isNeedTextWarn() {
        MenuConfig menuConfig = this.mWarnMenuConfig;
        if (menuConfig != null) {
            return menuConfig.warningType.contains("2");
        }
        return false;
    }

    private TextView createBubbleView() {
        MenuConfig menuConfig = this.mWarnMenuConfig;
        if (menuConfig == null || TextUtils.isEmpty(menuConfig.warningMsg) || !isNeedTextWarn()) {
            return null;
        }
        TextView textView = new TextView(this.mActivity);
        int iGenerateViewId = View.generateViewId();
        this.mCurrentBubbleViewId = iGenerateViewId;
        textView.setId(iGenerateViewId);
        textView.setText(this.mWarnMenuConfig.warningMsg);
        textView.setLines(1);
        textView.setPadding(20, 0, 20, 0);
        textView.setVisibility(4);
        textView.setTextSize(1, dip2px(6));
        textView.setGravity(17);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        return textView;
    }

    public MenuConfig getShowingConfig() {
        return this.mWarnMenuConfig;
    }

    public void setShowingConfig(MenuConfig menuConfig) {
        this.mWarnMenuConfig = menuConfig;
    }

    public void removeBubble() {
        removeView(findViewById(this.mCurrentBubbleViewId));
        post(new Runnable() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$FHbO57xBPSacEulvNMt62w8ILiI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$removeBubble$2$SqBaseFloatView();
            }
        });
    }

    public /* synthetic */ void lambda$removeBubble$2$SqBaseFloatView() {
        updatePopupWindow(false, this.mActivity, this.mMenuLayout);
    }

    public void showRemindAnim(MenuConfig menuConfig) {
        this.mWarnMenuConfig = menuConfig;
        if (this.mWindowX == null) {
            LogUtil.e("mWindowX is null, showRemindAnim return");
            return;
        }
        if (((ImageView) findViewById(this.mNormalViewId)) == null) {
            LogUtil.i("normalView is null, showRemindAnim return");
            return;
        }
        removeView(findViewById(this.mCurrentBubbleViewId));
        final TextView textViewCreateBubbleView = createBubbleView();
        if (textViewCreateBubbleView != null) {
            postStayEdgeRunnable();
            this.mWindowX.getDecorView().setClipBounds(null);
            updateBubblePosition(textViewCreateBubbleView);
            post(new Runnable() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$RWSlAGOeNhj1O6XY-YZORr-qSr8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$showRemindAnim$3$SqBaseFloatView(textViewCreateBubbleView);
                }
            });
        }
    }

    public void checkShake(MenuConfig menuConfig) {
        this.mWarnMenuConfig = menuConfig;
        if (isNeedShake()) {
            post(new Runnable() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$ylK7igDUHQvcWgwBkDmso38ARC0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$checkShake$4$SqBaseFloatView();
                }
            });
        }
    }

    public /* synthetic */ void lambda$checkShake$4$SqBaseFloatView() {
        rotateAnim(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: scaleAnim, reason: merged with bridge method [inline-methods] */
    public void lambda$showRemindAnim$3$SqBaseFloatView(final View view) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(view, "scaleX", 0.0f, 0.6f).setDuration(600L);
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(view, "scaleY", 0.0f, 0.6f).setDuration(600L);
        setPivot(view);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, duration2);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.sy37sdk.account.floatview.SqBaseFloatView.4
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                view.setVisibility(0);
            }
        });
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRotePivot() {
        ImageView imageView = (ImageView) findViewById(this.mNormalViewId);
        if (imageView == null) {
            return;
        }
        imageView.getLocationInWindow(new int[2]);
        setPivotX(r1[0] + (imageView.getWidth() / 2.0f));
        setPivotY(r1[1] + (imageView.getHeight() / 2.0f));
    }

    private void rotateAnim(final View view) {
        setRotePivot();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "rotation", -8.0f, 8.0f);
        objectAnimatorOfFloat.setDuration(500L);
        objectAnimatorOfFloat.setRepeatMode(2);
        objectAnimatorOfFloat.setRepeatCount(6);
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.sy37sdk.account.floatview.SqBaseFloatView.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                view.setRotation(0.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                view.setRotation(0.0f);
            }
        });
        objectAnimatorOfFloat.start();
    }

    private int dip2px(int i) {
        return DensityUtil.dip2px(this.mActivity, i);
    }

    protected boolean isNotchAffected() {
        Rect safeInsetRect = this.mDraggable.getSafeInsetRect();
        if (safeInsetRect == null) {
            return false;
        }
        return safeInsetRect.left > 0 || safeInsetRect.right > 0 || safeInsetRect.top > 0 || safeInsetRect.bottom > 0;
    }

    public void showRedDot(final boolean z) {
        if (this.mRedImageView == null) {
            return;
        }
        post(new Runnable() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$rECVkAMU-vNnlRuEVJ314NfJb08
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$showRedDot$5$SqBaseFloatView(z);
            }
        });
    }

    public /* synthetic */ void lambda$showRedDot$5$SqBaseFloatView(boolean z) {
        this.mRedImageView.setVisibility(z ? 0 : 8);
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setDismissListener(DismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public void setMenuListener(MenuListener menuListener) {
        this.menuListener = menuListener;
    }

    private void addNormalView(final Activity activity, final View view) {
        final ImageView imageView = new ImageView(activity);
        int iGenerateViewId = View.generateViewId();
        this.mNormalViewId = iGenerateViewId;
        imageView.setId(iGenerateViewId);
        if (!TextUtils.isEmpty(this.mIconUrl)) {
            new AsyncImageLoader(activity).loadDrawable(this.mIconUrl, imageView, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$kxE5HcoIqxr7A0kjKEcesmJAEeI
                @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                public final void imageLoaded(Bitmap bitmap, ImageView imageView2, String str) {
                    SqBaseFloatView.lambda$addNormalView$6(imageView, activity, bitmap, imageView2, str);
                }
            });
        }
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$1gIDYxkN8yyEnvlksOoND8JppIs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$addNormalView$7$SqBaseFloatView(view, activity, view2);
            }
        };
        this.mNormalViewOnClickListener = onClickListener;
        imageView.setOnClickListener(onClickListener);
        addView(imageView, new RelativeLayout.LayoutParams(DensityUtil.dip2px(activity, 45.0f), DensityUtil.dip2px(activity, 45.0f)));
        ImageView imageView2 = new ImageView(activity);
        this.mRedImageView = imageView2;
        imageView2.setImageResource(SqResUtils.getIdByName("sysq_bg_red_dot", "drawable", activity));
    }

    static /* synthetic */ void lambda$addNormalView$6(ImageView imageView, Activity activity, Bitmap bitmap, ImageView imageView2, String str) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(SqResUtils.getDrawableId(activity, "sy37_wm_img_move"));
        }
    }

    public /* synthetic */ void lambda$addNormalView$7$SqBaseFloatView(View view, Activity activity, View view2) {
        View decorView = this.mWindowX.getDecorView();
        if (!isFullShowView(decorView)) {
            showFullView(decorView);
            postStayEdgeRunnable();
            return;
        }
        View.OnClickListener onClickListener = this.onClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
        if (this.mAnimatingFlag || this.mDraggingFlag) {
            return;
        }
        if (view == null) {
            LogUtil.i("menuLayout == null");
            return;
        }
        if (this.mPopupWindow == null) {
            view.setSystemUiVisibility(5894);
            PopupWindow popupWindow = new PopupWindow(view, -2, -2, true);
            this.mPopupWindow = popupWindow;
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.sy37sdk.account.floatview.-$$Lambda$j1XUKnkXtzjGYbGNgn7S-MJVT2A
                @Override // android.widget.PopupWindow.OnDismissListener
                public final void onDismiss() {
                    this.f$0.postStayEdgeRunnable();
                }
            });
            this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        }
        if (this.mPopupWindow.isShowing()) {
            return;
        }
        MenuListener menuListener = this.menuListener;
        if (menuListener != null) {
            menuListener.onMenuShow();
        }
        updatePopupWindow(true, activity, view);
    }

    public void expandMenu() {
        if (this.mNormalViewOnClickListener != null) {
            if (isFullShowView(this.mWindowX.getDecorView())) {
                this.mNormalViewOnClickListener.onClick(this);
            } else {
                this.mNormalViewOnClickListener.onClick(this);
                this.mNormalViewOnClickListener.onClick(this);
            }
        }
    }

    private void addRedPackView(final Context context, final RedPacketInfo redPacketInfo) {
        GifMovieView gifMovieView = new GifMovieView(context);
        gifMovieView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$JHGysk66WaQsHf2jJxbJFFXwuU8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$addRedPackView$8$SqBaseFloatView(context, redPacketInfo, view);
            }
        });
        gifMovieView.setMovieFilePath(redPacketInfo.imgLocalPath);
        addView(gifMovieView, new RelativeLayout.LayoutParams(-2, -2));
    }

    public /* synthetic */ void lambda$addRedPackView$8$SqBaseFloatView(Context context, RedPacketInfo redPacketInfo, View view) {
        View decorView = this.mWindowX.getDecorView();
        if (!isFullShowView(decorView)) {
            showFullView(decorView);
            postStayEdgeRunnable();
        } else {
            postStayEdgeRunnable();
            RedPacketManager.getInstance().showTargetUrl(context, redPacketInfo);
        }
    }

    private int getBubbleWidth() {
        View viewFindViewById = findViewById(this.mCurrentBubbleViewId);
        if (viewFindViewById == null) {
            return 0;
        }
        if (isLeftShow()) {
            return viewFindViewById.getWidth() + dip2px(15);
        }
        return viewFindViewById.getWidth() - dip2px(15);
    }

    private void updatePopupWindow(boolean z, Activity activity, View view) {
        Window window;
        int bubbleWidth;
        int i;
        if (activity == null || activity.isFinishing() || activity.isDestroyed() || (window = activity.getWindow()) == null) {
            return;
        }
        View decorView = window.getDecorView();
        int i2 = 51;
        if (isNotchAffected()) {
            int viewWidth = this.mWindowX.getWindowParams().x + this.mWindowX.getViewWidth() + 10;
            if (viewWidth > this.mWindowDecorView.getWidth() / 2) {
                int width = view.getWidth();
                if (width == 0) {
                    view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                    width = view.getMeasuredWidth();
                }
                viewWidth = this.mWindowX.getWindowParams().x - width;
            }
            if (isLeftShow()) {
                bubbleWidth = viewWidth - getBubbleWidth();
            } else {
                bubbleWidth = viewWidth + getBubbleWidth();
            }
            i = this.mWindowX.getWindowParams().y;
        } else if (isLeftShow()) {
            bubbleWidth = this.mWindowX.getContentView().getWidth() - getBubbleWidth();
            i = this.mWindowX.getWindowParams().y;
        } else {
            i2 = 53;
            bubbleWidth = getBubbleWidth() + this.mWindowX.getContentView().getWidth();
            i = this.mWindowX.getWindowParams().y;
        }
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow != null) {
            if (z) {
                popupWindow.showAtLocation(decorView, i2, bubbleWidth, i);
            } else {
                popupWindow.update(bubbleWidth, i, popupWindow.getWidth(), this.mPopupWindow.getHeight());
            }
        }
    }

    public void setWindowVisibility(int i) {
        this.mWindowX.setVisibility(i);
    }

    @Override // android.view.View
    public int getWindowVisibility() {
        return this.mWindowX.getVisibility();
    }

    public void dismiss() {
        DismissListener dismissListener = this.dismissListener;
        if (dismissListener != null) {
            dismissListener.onDismiss();
        }
        removeCallbacks(this.mStayEdgeRunnable);
        this.mWindowX.cancel();
        dismissMenu();
        BottomDeleteView bottomDeleteView = this.mBottomDeleteView;
        if (bottomDeleteView != null) {
            bottomDeleteView.removeBottomDeleteView();
        }
    }

    public void show(boolean z) {
        recoverWindowCoordinate();
        if (z) {
            this.mWindowX.setVisibility(4);
        }
        this.mWindowX.show();
    }

    public void dismissMenu() {
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow == null) {
            return;
        }
        popupWindow.dismiss();
    }

    public void refreshRedDot() {
        if (this.mOnDragCallBack == null) {
            this.mOnDragCallBack = new FloatWindow.OnDragCallBack() { // from class: com.sy37sdk.account.floatview.SqBaseFloatView.6
                @Override // com.sy37sdk.account.floatview.FloatWindow.OnDragCallBack
                public void onStartDrag() {
                }

                @Override // com.sy37sdk.account.floatview.FloatWindow.OnDragCallBack
                public void onStayEdge() {
                    SqBaseFloatView.this.refreshRedDot();
                }
            };
        }
        post(new Runnable() { // from class: com.sy37sdk.account.floatview.-$$Lambda$SqBaseFloatView$5w-zHT6IGUzsdrWd7owjsyPkQvg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshRedDot$9$SqBaseFloatView();
            }
        });
    }

    public /* synthetic */ void lambda$refreshRedDot$9$SqBaseFloatView() {
        RelativeLayout.LayoutParams layoutParams;
        ImageView imageView = this.mRedImageView;
        if (imageView == null) {
            return;
        }
        if (imageView.getParent() == null) {
            addView(this.mRedImageView);
        }
        int iDip2px = DisplayUtil.dip2px(getContext(), 13.0f);
        if (this.mRedImageView.getParent() == null) {
            layoutParams = new RelativeLayout.LayoutParams(iDip2px, iDip2px);
        } else {
            layoutParams = (RelativeLayout.LayoutParams) this.mRedImageView.getLayoutParams();
        }
        int iDip2px2 = DisplayUtil.dip2px(getContext(), 4.0f);
        if (isLeftShow()) {
            layoutParams.addRule(9);
            layoutParams.setMargins((getRight() - this.mRedImageView.getDrawable().getIntrinsicWidth()) - iDip2px2, iDip2px2, 0, 0);
            this.mRedImageView.setLayoutParams(layoutParams);
        } else {
            layoutParams.addRule(9);
            layoutParams.setMargins(iDip2px2, iDip2px2, 0, 0);
            this.mRedImageView.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLeftShow() {
        return ((float) this.mWindowX.getWindowParams().x) + (((float) this.mWindowX.getViewWidth()) / 2.0f) < ((float) DisplayUtil.getScreenWidth(getContext())) / 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTopShow() {
        WindowManager.LayoutParams windowParams = this.mWindowX.getWindowParams();
        return isLeftShow() ? windowParams.x > windowParams.y : (DisplayUtil.getScreenWidth(getContext()) - windowParams.x) - getWidth() > windowParams.y;
    }

    public void resetView(boolean z) {
        dismissMenu();
        cancelAnim();
        removeCallbacks(this.mStayEdgeRunnable);
        if (z) {
            resetWindowCoordinate();
        } else {
            recoverWindowCoordinate();
        }
        showFullView(this.mWindowX.getDecorView());
        this.mWindowX.update();
        postStayEdgeRunnable();
        updateBottomDeleteView();
    }

    private void cancelAnim() {
        Animator animator = this.mAnimator;
        if (animator == null) {
            return;
        }
        animator.end();
        this.mAnimator.removeAllListeners();
    }

    private void updateBottomDeleteView() {
        this.mBottomDeleteView.updateView();
    }

    public void postStayEdgeRunnable() {
        removeCallbacks(this.mStayEdgeRunnable);
        postDelayed(this.mStayEdgeRunnable, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFullView(View view) {
        Rect safeInsetRect = this.mDraggable.getSafeInsetRect();
        if (safeInsetRect != null && safeInsetRect.left > 0 && !isTopShow()) {
            this.mDraggable.updateLocation(this.mWindowX.getWindowParams().x + (this.mWindowX.getViewWidth() / 2.0f), this.mWindowX.getWindowParams().y, false);
        }
        if (view == null) {
            return;
        }
        view.getWidth();
        view.getHeight();
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        view.setClipBounds(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFullShowView(View view) {
        Rect safeInsetRect = this.mDraggable.getSafeInsetRect();
        if (safeInsetRect != null && safeInsetRect.left > 0 && !isTopShow() && this.mWindowX.getWindowParams().x < safeInsetRect.left) {
            return false;
        }
        if (view == null) {
            return true;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        Rect clipBounds = view.getClipBounds();
        if (view.getTranslationX() != 0.0f && view.getTranslationY() != 0.0f) {
            return false;
        }
        if (clipBounds == null) {
            return true;
        }
        return clipBounds.left == 0 && clipBounds.top == 0 && clipBounds.right == width && clipBounds.bottom == height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideHalfView(View view, int i) {
        if (view == null) {
            return;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        Rect rect = new Rect();
        if (i == 3) {
            Rect safeInsetRect = this.mDraggable.getSafeInsetRect();
            if (safeInsetRect != null && safeInsetRect.left > 0) {
                this.mDraggable.updateLocation(this.mWindowX.getWindowParams().x - (this.mWindowX.getViewWidth() / 2.0f), this.mWindowX.getWindowParams().y, true);
                return;
            }
            rect.set(getNormalView() != null ? getNormalView().getWidth() / 2 : width / 2, 0, width, height);
            view.setTranslationX(-r8);
            view.setTranslationY(0.0f);
            view.setClipBounds(rect);
            return;
        }
        if (i == 5) {
            int width2 = getNormalView() != null ? getNormalView().getWidth() / 2 : width / 2;
            rect.set(0, 0, width - width2, height);
            view.setTranslationX(width2);
            view.setTranslationY(0.0f);
            view.setClipBounds(rect);
            return;
        }
        if (i != 48) {
            return;
        }
        rect.set(0, getNormalView() != null ? getNormalView().getHeight() / 2 : height / 2, width, height);
        view.setTranslationX(0.0f);
        view.setTranslationY(-r8);
        view.setClipBounds(rect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reachDeleteArea(WindowX<?> windowX) {
        int viewWidth = windowX.getViewWidth() / 2;
        int i = windowX.getWindowParams().x + viewWidth;
        int i2 = windowX.getWindowParams().y + viewWidth;
        if (this.mHidePath == null) {
            Path deleteIrregularPath = getDeleteIrregularPath();
            this.mHidePath = deleteIrregularPath;
            this.mHideRegion.setPath(deleteIrregularPath, this.mScreenRegion);
        }
        this.mFloatPath.reset();
        this.mFloatPath.addCircle(i, i2, viewWidth, Path.Direction.CW);
        this.mFloatRegion.setPath(this.mFloatPath, this.mScreenRegion);
        return this.mFloatRegion.op(this.mHideRegion, Region.Op.INTERSECT);
    }

    private Path getDeleteIrregularPath() {
        int i = this.mScreenWidth;
        int i2 = this.mScreenHeight;
        int height = this.mBottomDeleteView.getHeight();
        int bottomDeleteWidth = FloatViewUtils.getBottomDeleteWidth(getContext());
        int i3 = i > i2 ? 0 : height / 2;
        float f = (i - bottomDeleteWidth) / 2.0f;
        float f2 = i - f;
        Path path = new Path();
        float f3 = i2;
        path.moveTo(f, f3);
        path.lineTo(f2, f3);
        float f4 = height;
        float f5 = i3;
        float f6 = i2 - height;
        path.lineTo((f2 - f4) + f5, f6);
        path.lineTo((f4 + f) - f5, f6);
        path.lineTo(f, f3);
        path.close();
        return path;
    }

    private void recoverWindowCoordinate() {
        FloatViewUtils.FloatViewPosConfig floatViewPositionConfig = FloatViewUtils.getFloatViewPositionConfig(getContext(), this);
        if (floatViewPositionConfig.x != -1 && floatViewPositionConfig.y != -1) {
            this.mWindowX.setGravity(51);
            this.mWindowX.setXOffset(floatViewPositionConfig.x);
            this.mWindowX.setYOffset(floatViewPositionConfig.y);
            return;
        }
        resetWindowCoordinate();
    }

    private View getNormalView() {
        return findViewById(this.mNormalViewId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveWindowCoordinate() {
        if (!reachDeleteArea(this.mWindowX) && this.mWindowX.getVisibility() == 0) {
            FloatViewUtils.setFloatViewPos(getContext(), this, this.mWindowX.getWindowParams().x, this.mWindowX.getWindowParams().y, ScreenOrientationHelper.currentType);
        }
    }

    private void resetWindowCoordinate() {
        Window window = this.mActivity.getWindow();
        if (window == null) {
            return;
        }
        this.mWindowX.setGravity(51);
        Rect safeInsetRect = BaseDraggable.getSafeInsetRect(window);
        if (safeInsetRect != null) {
            this.mWindowX.setXOffset(safeInsetRect.left);
        } else {
            this.mWindowX.setXOffset(0);
        }
        this.mWindowX.setYOffset((int) (((double) DisplayUtil.getScreenHeight(getContext())) * 0.4d));
    }

    public static class Builder {
        private final Activity mActivity;
        private FloatWindow.DragBottom2DeleteCallback mDragBottom2DeleteCallback;
        private String mIconUrl;
        private MenuConfig mMenuConfig;
        private View mMenuLayout;
        private RedPacketInfo mRedPacketInfo;

        public Builder(Activity activity) {
            this.mActivity = activity;
        }

        public Builder setFloatIconUrl(String str) {
            this.mIconUrl = str;
            return this;
        }

        public Builder setMenuLayout(View view) {
            this.mMenuLayout = view;
            return this;
        }

        public Builder setRedPacketInfo(RedPacketInfo redPacketInfo) {
            this.mRedPacketInfo = redPacketInfo;
            return this;
        }

        public Builder setOnDragBottom2DeleteCallback(FloatWindow.DragBottom2DeleteCallback dragBottom2DeleteCallback) {
            this.mDragBottom2DeleteCallback = dragBottom2DeleteCallback;
            return this;
        }

        public SqBaseFloatView build() {
            return new SqBaseFloatView(this.mActivity, this.mIconUrl, this.mMenuLayout, this.mRedPacketInfo, this.mDragBottom2DeleteCallback);
        }
    }

    public int[] getRealLocation() {
        int[] iArr = new int[2];
        View viewFindViewById = findViewById(this.mNormalViewId);
        if (viewFindViewById != null) {
            viewFindViewById.getLocationOnScreen(iArr);
        } else {
            getLocationOnScreen(iArr);
        }
        return iArr;
    }

    public int getRealWidth() {
        View viewFindViewById = findViewById(this.mNormalViewId);
        if (viewFindViewById != null) {
            return viewFindViewById.getWidth();
        }
        return getWidth();
    }

    public int getRealHeight() {
        View viewFindViewById = findViewById(this.mNormalViewId);
        if (viewFindViewById != null) {
            return viewFindViewById.getHeight();
        }
        return getHeight();
    }
}
