package com.sy37sdk.account.floatview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ViewUtils;
import com.sy37sdk.account.floatview.FloatViewUtils;
import com.sy37sdk.account.floatview.FloatWindow;
import com.sy37sdk.account.floatview.SystemUiCheckUtils;
import java.util.ArrayList;
import java.util.List;
import notchtools.geek.com.notchtools.NotchTools;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DragViewLayout extends CheckSystemUiViewBase implements SystemUiCheckUtils.onSystemUiChangedCallback {
    public FloatWindow.DragBottom2DeleteCallback DragBottom2DeleteCallback;
    private Activity activity;
    private boolean authStartAnim;
    public boolean canDispatchTouchEvent;
    private boolean checkRecordPos;
    private boolean checkShowCompelete;
    private boolean checkStayEdge;
    public FloatWindow.ClickListener clickListener;
    protected float deletedY;
    public FloatViewUtils.FloatViewConfig floatViewConfig;
    private boolean isAnimating;
    protected boolean isDrag;
    public boolean isLeft;
    public int mHeight;
    private FloatWindow.OnDragCallBack mOnDragCallBack;
    protected Runnable mStayEdgeRunnable;
    public int mWidth;
    private boolean reachDeleteArea;
    protected ValueAnimator valueAnimator;
    private List<View> views;
    private float xInRaw;
    private float xInRawLast;
    private float yInRaw;
    private float yInRawLast;

    public boolean isPostView() {
        return true;
    }

    public boolean isAuthStartAnim() {
        return this.authStartAnim;
    }

    public boolean isCheckShowCompelete() {
        return this.checkShowCompelete;
    }

    public boolean isCheckStayEdge() {
        return this.checkStayEdge;
    }

    public boolean isCheckRecordPos() {
        return this.checkRecordPos;
    }

    public DragViewLayout(Context context) {
        this(context, null);
    }

    public DragViewLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DragViewLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.checkStayEdge = true;
        this.checkRecordPos = true;
        this.checkShowCompelete = true;
        this.authStartAnim = true;
        this.isLeft = true;
        this.isDrag = false;
        this.isAnimating = false;
        this.deletedY = 0.0f;
        this.reachDeleteArea = false;
        this.canDispatchTouchEvent = true;
        this.mStayEdgeRunnable = new Runnable() { // from class: com.sy37sdk.account.floatview.DragViewLayout.4
            @Override // java.lang.Runnable
            public void run() {
                if (DragViewLayout.this.isAnimating || DragViewLayout.this.isDrag) {
                    return;
                }
                if (DragViewLayout.this.isNotchAffected()) {
                    DragViewLayout.this.floatLayoutParams.y = (-DragViewLayout.this.mHeight) / 2;
                } else if (DragViewLayout.this.isLeft) {
                    DragViewLayout.this.floatLayoutParams.x = (-DragViewLayout.this.mWidth) / 2;
                } else {
                    DragViewLayout.this.floatLayoutParams.x = DragViewLayout.this.floatViewConfig.regionWidth - (DragViewLayout.this.mWidth / 2);
                }
                DragViewLayout.this.updateViewLayout();
            }
        };
        this.views = new ArrayList();
        SystemUiCheckUtils.getInstance().addListener(this);
        initView();
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
        if (isPostView()) {
            post(new Runnable() { // from class: com.sy37sdk.account.floatview.DragViewLayout.1
                @Override // java.lang.Runnable
                public void run() {
                    DragViewLayout dragViewLayout = DragViewLayout.this;
                    dragViewLayout.mWidth = dragViewLayout.getWidth();
                    DragViewLayout dragViewLayout2 = DragViewLayout.this;
                    dragViewLayout2.mHeight = dragViewLayout2.getHeight();
                }
            });
        }
    }

    private boolean checkDragBottom2Delete(boolean z, boolean z2) {
        if (this.DragBottom2DeleteCallback != null && z2) {
            if (z) {
                if (reachDeleteArea()) {
                    this.DragBottom2DeleteCallback.onDragBottom2Delete(false, true, true);
                } else {
                    this.DragBottom2DeleteCallback.onDragBottom2Delete(false, true, false);
                }
            } else {
                if (reachDeleteArea()) {
                    canAnimate();
                    this.DragBottom2DeleteCallback.onDragBottom2Delete(true, false, true);
                    return true;
                }
                this.DragBottom2DeleteCallback.onDragBottom2Delete(false, false, true);
            }
        }
        return false;
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        FloatWindow.OnDragCallBack onDragCallBack;
        if (!this.canDispatchTouchEvent) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        this.yInRaw = motionEvent.getRawY();
        float rawX = motionEvent.getRawX();
        this.xInRaw = rawX;
        if (action == 0) {
            removeCallbacks(this.mStayEdgeRunnable);
            this.isDrag = false;
        } else if (action != 1) {
            if (action == 2) {
                float f = this.yInRaw - this.yInRawLast;
                float f2 = rawX - this.xInRawLast;
                if (Math.abs(f2) > this.mTouchSlop || Math.abs(f) > this.mTouchSlop) {
                    if (!this.isDrag && (onDragCallBack = this.mOnDragCallBack) != null) {
                        onDragCallBack.onStartDrag();
                    }
                    this.isDrag = true;
                }
                this.floatLayoutParams.x = (int) (r2.x + f2);
                this.floatLayoutParams.y = (int) (r1.y + f);
                if (this.isDrag) {
                    updateFloatPosition();
                }
                if (checkDragBottom2Delete(true, this.isDrag)) {
                    return true;
                }
            }
        } else {
            if (checkDragBottom2Delete(false, this.isDrag)) {
                return true;
            }
            if (!this.isDrag) {
                onClick((int) this.xInRaw, (int) this.yInRaw);
            }
            if (this.isDrag || !isStayEdge()) {
                if (isAuthStartAnim()) {
                    startAnim();
                }
                this.isDrag = false;
            }
        }
        this.yInRawLast = this.yInRaw;
        this.xInRawLast = this.xInRaw;
        return super.dispatchTouchEvent(motionEvent);
    }

    public void handleClick(int i, int i2) {
        LogUtil.i(this.TAG, "handleClick");
        FloatWindow.ClickListener clickListener = this.clickListener;
        if (clickListener != null) {
            clickListener.onClick(null, i, i2);
        }
    }

    private void onClick(int i, int i2) {
        LogUtil.i(this.TAG, "onClick");
        if (isCheckShowCompelete()) {
            if (isShowComplete()) {
                handleClick(i, i2);
                return;
            } else {
                showComplete();
                return;
            }
        }
        handleClick(i, i2);
    }

    private boolean reachDeleteArea() {
        boolean z = this.reachDeleteArea;
        this.reachDeleteArea = ((float) (this.floatLayoutParams.y + this.mHeight)) > this.deletedY && reachDeleteAreaX();
        return this.reachDeleteArea;
    }

    protected int getRegionnWidth() {
        return this.floatViewConfig.regionWidth;
    }

    protected int getRegionHeight() {
        return this.floatViewConfig.regionHeight;
    }

    private boolean reachDeleteAreaX() {
        int regionnWidth = getRegionnWidth() / 3;
        boolean z = this.floatLayoutParams.x >= regionnWidth && this.floatLayoutParams.x <= getRegionnWidth() - regionnWidth;
        LogUtil.d(this.TAG, "reachDeleteAreaX:" + z);
        return z;
    }

    public void updateFloatPosition() {
        int regionnWidth = this.floatLayoutParams.x;
        int regionHeight = this.floatLayoutParams.y;
        if (regionHeight < 0) {
            regionHeight = 0;
        }
        if (regionHeight > getRegionHeight() - this.mHeight) {
            regionHeight = getRegionHeight() - this.mHeight;
        }
        if (regionnWidth < 0) {
            regionnWidth = 0;
        }
        if (regionnWidth > getRegionnWidth() - this.mWidth) {
            regionnWidth = getRegionnWidth() - this.mWidth;
        }
        this.floatLayoutParams.x = regionnWidth;
        this.floatLayoutParams.y = regionHeight;
        updateViewLayout();
    }

    private boolean isLeft() {
        return this.floatLayoutParams.x + (this.mWidth / 2) < getRegionnWidth() / 2;
    }

    protected boolean isNotchAffected() {
        return this.activity != null && NotchTools.getFullScreenTools().isNotchScreen(this.activity.getWindow()) && getResources().getConfiguration().orientation == 2;
    }

    protected void handleOnAnimationUpdate(ValueAnimator valueAnimator) {
        if (isNotchAffected()) {
            this.floatLayoutParams.y = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        } else {
            this.floatLayoutParams.x = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        }
    }

    protected void handleStartAnimResultPos() {
        this.isLeft = isLeft();
        if (isNotchAffected()) {
            this.valueAnimator = ValueAnimator.ofInt(this.floatLayoutParams.y, 0);
        } else if (this.isLeft) {
            this.valueAnimator = ValueAnimator.ofInt(this.floatLayoutParams.x, 0);
        } else {
            this.valueAnimator = ValueAnimator.ofInt(this.floatLayoutParams.x, getRegionnWidth() - this.mWidth);
        }
    }

    private void startAnim() {
        LogUtil.i("startAnim");
        this.isAnimating = true;
        handleStartAnimResultPos();
        this.valueAnimator.setDuration(10L);
        this.valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sy37sdk.account.floatview.DragViewLayout.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                DragViewLayout.this.handleOnAnimationUpdate(valueAnimator);
                DragViewLayout.this.updateViewLayout();
            }
        });
        this.valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.sy37sdk.account.floatview.DragViewLayout.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                DragViewLayout.this.isAnimating = false;
                LogUtil.i("onAnimationEnd");
                DragViewLayout.this.stayEdge();
                if (DragViewLayout.this.mOnDragCallBack != null) {
                    DragViewLayout.this.mOnDragCallBack.onStayEdge();
                }
                if (DragViewLayout.this.reachDeleteArea || !DragViewLayout.this.checkRecordPos) {
                    return;
                }
                Context context = DragViewLayout.this.mContext;
                DragViewLayout dragViewLayout = DragViewLayout.this;
                FloatViewUtils.setFloatViewPos(context, dragViewLayout, dragViewLayout.floatLayoutParams.x, DragViewLayout.this.floatLayoutParams.y, ScreenOrientationHelper.currentType);
            }
        });
        this.valueAnimator.start();
    }

    public void show(boolean z) {
        checkFloatViewPosition();
        if (z) {
            ViewUtils.hidden(this);
        }
        addView();
    }

    public boolean isAnim() {
        return this.isAnimating;
    }

    public void setOnDragCallBack(FloatWindow.OnDragCallBack onDragCallBack) {
        this.mOnDragCallBack = onDragCallBack;
    }

    private boolean isShowComplete() {
        int i = this.floatViewConfig.regionWidth - this.mWidth;
        int i2 = this.floatLayoutParams.x;
        int i3 = this.floatLayoutParams.y;
        if (isNotchAffected() || !(i2 == 0 || i2 == i)) {
            return isNotchAffected() && i3 == 0;
        }
        return true;
    }

    private boolean isStayEdge() {
        int i = this.floatViewConfig.regionWidth - this.mWidth;
        int i2 = this.floatLayoutParams.x;
        if (i2 > this.mTouchSlop + 0 && i2 < i - this.mTouchSlop) {
            LogUtil.i("isStayEdge false");
            return false;
        }
        if (i2 < 0 - this.mTouchSlop || i2 > i + this.mTouchSlop) {
            LogUtil.i("isStayEdge false");
            return false;
        }
        LogUtil.i("isStayEdge true");
        return true;
    }

    public void showComplete() {
        LogUtil.i(this.TAG, "showComplete");
        if (isNotchAffected()) {
            this.floatLayoutParams.y = 0;
        } else if (this.isLeft) {
            this.floatLayoutParams.x = 0;
        } else {
            this.floatLayoutParams.x = this.floatViewConfig.regionWidth - this.mWidth;
        }
        updateViewLayout();
    }

    private void verifyLeft() {
        if (this.floatLayoutParams.x < this.floatViewConfig.regionWidth / 2) {
            this.isLeft = true;
        } else {
            this.isLeft = false;
        }
    }

    public void stayEdge() {
        if (isCheckStayEdge()) {
            LogUtil.i("stayEdge");
            postDelayed(this.mStayEdgeRunnable, 3000L);
        }
    }

    private void resetData() {
        this.isLeft = true;
        this.isDrag = false;
        this.isAnimating = false;
        this.floatViewConfig = null;
    }

    private void initView() {
        resetData();
        this.floatViewConfig = new FloatViewUtils.FloatViewConfig(this.mContext);
        LogUtil.i(this.TAG, this.floatViewConfig.toString());
    }

    private void checkFloatViewPosition() {
        FloatViewUtils.FloatViewPosConfig floatViewPositionConfig = FloatViewUtils.getFloatViewPositionConfig(getContext(), this);
        if (floatViewPositionConfig.x != -1 && floatViewPositionConfig.y != -1 && floatViewPositionConfig.orientationType == ScreenOrientationHelper.currentType) {
            this.floatLayoutParams.x = floatViewPositionConfig.x;
            this.floatLayoutParams.y = floatViewPositionConfig.y;
        } else {
            this.floatLayoutParams.x = 0;
            this.floatLayoutParams.y = (int) (((double) getRegionHeight()) * 0.4d);
        }
        verifyLeft();
    }

    protected void resetView(boolean z) {
        initView();
        if (z) {
            this.floatLayoutParams.gravity = 51;
            this.floatLayoutParams.x = 0;
            this.floatLayoutParams.y = (int) (((double) getRegionHeight()) * 0.4d);
        } else {
            checkFloatViewPosition();
        }
        updateFloatPosition();
        stayEdge();
    }

    protected void updateViewLayout() {
        update();
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase, com.sy37sdk.account.floatview.SystemUiCheckUtils.onSystemUiChangedCallback
    public void onSystemUiChanged(FloatViewUtils.FloatViewConfig floatViewConfig) {
        this.floatViewConfig = floatViewConfig;
    }

    protected void setFilterDragClickListener(View view, FloatWindow.ClickListener clickListener) {
        if (this.clickListener == null) {
            this.clickListener = new FloatWindow.ClickListener() { // from class: com.sy37sdk.account.floatview.DragViewLayout.5
                @Override // com.sy37sdk.account.floatview.FloatWindow.ClickListener
                public void onShowComplete() {
                }

                @Override // com.sy37sdk.account.floatview.FloatWindow.ClickListener
                public void onClick(View view2, int i, int i2) {
                    boolean[] zArr = new boolean[DragViewLayout.this.views.size()];
                    for (int i3 = 0; i3 < DragViewLayout.this.views.size(); i3++) {
                        zArr[i3] = ((View) DragViewLayout.this.views.get(i3)).getVisibility() == 0;
                    }
                    for (int i4 = 0; i4 < DragViewLayout.this.views.size(); i4++) {
                        View view3 = (View) DragViewLayout.this.views.get(i4);
                        if (FloatViewUtils.isTouchPointInView(view3, i, i2)) {
                            FloatWindow.ClickListener clickListener2 = (FloatWindow.ClickListener) view3.getTag();
                            if (zArr[i4]) {
                                clickListener2.onClick(view3, i, i2);
                            }
                        }
                    }
                }
            };
        }
        if (this.views.contains(view)) {
            return;
        }
        view.setTag(clickListener);
        this.views.add(view);
    }
}
