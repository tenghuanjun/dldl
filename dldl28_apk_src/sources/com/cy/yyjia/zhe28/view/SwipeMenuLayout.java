package com.cy.yyjia.zhe28.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes3.dex */
public class SwipeMenuLayout extends LinearLayout {
    private static final String TAG = "SwipeMenuLayout";
    private static SwipeMenuLayout mCacheView;
    private int animDuration;
    private boolean chokeIntercept;
    private boolean isClickMenuAndClose;
    private boolean isEnableLeftMenu;
    private boolean isEnableSwipe;
    private boolean isFingerTouch;
    private boolean isOpenChoke;
    private ValueAnimator mCloseAnim;
    private View mContentView;
    private final Context mContext;
    private ValueAnimator mExpandAnim;
    private float mFirstRawX;
    private float mLastRawX;
    private int mMenuWidth;
    private int mPointerId;
    private int mScaledMaximumFlingVelocity;
    private int mScaledTouchSlop;
    private SwipeMenuStateListener mSwipeMenuStateListener;
    private VelocityTracker mVelocityTracker;

    public interface SwipeMenuStateListener {
        void menuIsOpen(boolean isOpen);
    }

    public SwipeMenuLayout(Context context) {
        this(context, null);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mLastRawX = 0.0f;
        this.mFirstRawX = 0.0f;
        this.isFingerTouch = false;
        this.animDuration = 300;
        this.chokeIntercept = false;
        this.isOpenChoke = true;
        this.isEnableSwipe = true;
        this.isEnableLeftMenu = false;
        this.isClickMenuAndClose = false;
        this.mContext = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.SwipeMenuLayout, defStyleAttr, 0);
        this.isEnableSwipe = typedArrayObtainStyledAttributes.getBoolean(2, true);
        this.isEnableLeftMenu = typedArrayObtainStyledAttributes.getBoolean(1, false);
        this.isOpenChoke = typedArrayObtainStyledAttributes.getBoolean(3, true);
        this.isClickMenuAndClose = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        init();
    }

    private void init() {
        this.mScaledTouchSlop = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        this.mScaledMaximumFlingVelocity = ViewConfiguration.get(this.mContext).getScaledMaximumFlingVelocity();
        setClickable(true);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        this.mMenuWidth = 0;
        int childCount = getChildCount();
        int iMax = 0;
        int measuredWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                if (i == 0) {
                    layoutParams.width = getMeasuredWidth();
                    this.mContentView = childAt;
                }
                measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
                if (mode != 1073741824) {
                    iMax = Math.max(iMax, childAt.getMeasuredHeight());
                }
                if (i == 0) {
                    measuredWidth = childAt.getMeasuredWidth();
                } else {
                    this.mMenuWidth += childAt.getMeasuredWidth();
                }
            }
        }
        setMeasuredDimension(measuredWidth, Math.max(getMeasuredHeight(), iMax));
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int measuredWidth;
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i = 0;
        int measuredWidth2 = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                if (i2 == 0) {
                    childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                    measuredWidth = childAt.getMeasuredWidth() + paddingLeft;
                } else if (this.isEnableLeftMenu) {
                    childAt.layout(measuredWidth2 - childAt.getMeasuredWidth(), paddingTop, measuredWidth2, childAt.getMeasuredHeight() + paddingTop);
                    measuredWidth2 -= childAt.getMeasuredWidth();
                } else {
                    childAt.layout(i, paddingTop, childAt.getMeasuredWidth() + i, childAt.getMeasuredHeight() + paddingTop);
                    measuredWidth = childAt.getMeasuredWidth();
                }
                i += measuredWidth;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            this.mFirstRawX = ev.getRawX();
            getParent().requestDisallowInterceptTouchEvent(false);
            this.chokeIntercept = false;
            SwipeMenuLayout swipeMenuLayout = mCacheView;
            if (swipeMenuLayout != null) {
                if (swipeMenuLayout != this) {
                    swipeMenuLayout.closeMenuAnim();
                    this.chokeIntercept = this.isOpenChoke;
                }
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (action == 1 || action == 3) {
            this.isFingerTouch = false;
            if (Math.abs(getScrollX()) == Math.abs(this.mMenuWidth)) {
                if ((!this.isEnableLeftMenu || ev.getX() >= this.mMenuWidth) && (this.isEnableLeftMenu || ev.getX() <= getMeasuredWidth() - this.mMenuWidth)) {
                    return true;
                }
                if (this.isClickMenuAndClose) {
                    closeMenuAnim();
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.isEnableSwipe) {
            return super.onInterceptTouchEvent(ev);
        }
        int action = ev.getAction();
        if (action != 0) {
            if (action == 2 && Math.abs(ev.getRawX() - this.mFirstRawX) >= this.mScaledTouchSlop) {
                longClickable(false);
                return true;
            }
        } else {
            if (this.isFingerTouch) {
                return true;
            }
            this.isFingerTouch = true;
            this.mPointerId = ev.getPointerId(0);
            this.mLastRawX = ev.getRawX();
        }
        return super.onInterceptTouchEvent(ev);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0084  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.view.SwipeMenuLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void acquireVelocityTracker(final MotionEvent event) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(event);
    }

    private void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void expandMenuAnim() {
        longClickable(false);
        cleanAnim();
        mCacheView = this;
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(getScrollX(), this.isEnableLeftMenu ? -this.mMenuWidth : this.mMenuWidth);
        this.mExpandAnim = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.cy.yyjia.zhe28.view.SwipeMenuLayout.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                SwipeMenuLayout.this.scrollTo(((Integer) animation.getAnimatedValue()).intValue(), 0);
            }
        });
        this.mExpandAnim.addListener(new AnimatorListenerAdapter() { // from class: com.cy.yyjia.zhe28.view.SwipeMenuLayout.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (SwipeMenuLayout.this.mSwipeMenuStateListener != null) {
                    SwipeMenuLayout.this.mSwipeMenuStateListener.menuIsOpen(true);
                }
            }
        });
        this.mExpandAnim.setInterpolator(new OvershootInterpolator());
        this.mExpandAnim.setDuration(this.animDuration).start();
    }

    public void closeMenuAnim() {
        mCacheView = null;
        cleanAnim();
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(getScrollX(), 0);
        this.mCloseAnim = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.cy.yyjia.zhe28.view.SwipeMenuLayout.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                SwipeMenuLayout.this.scrollTo(((Integer) animation.getAnimatedValue()).intValue(), 0);
            }
        });
        this.mCloseAnim.addListener(new AnimatorListenerAdapter() { // from class: com.cy.yyjia.zhe28.view.SwipeMenuLayout.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                SwipeMenuLayout.this.longClickable(true);
                if (SwipeMenuLayout.this.mSwipeMenuStateListener != null) {
                    SwipeMenuLayout.this.mSwipeMenuStateListener.menuIsOpen(false);
                }
            }
        });
        this.mCloseAnim.setInterpolator(new AccelerateInterpolator());
        this.mCloseAnim.setDuration(this.animDuration).start();
    }

    private void cleanAnim() {
        ValueAnimator valueAnimator = this.mCloseAnim;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mCloseAnim.cancel();
        }
        ValueAnimator valueAnimator2 = this.mExpandAnim;
        if (valueAnimator2 == null || !valueAnimator2.isRunning()) {
            return;
        }
        this.mExpandAnim.cancel();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        if (getScrollX() != 0) {
            quickCloseMenu();
            mCacheView = null;
        }
        super.onDetachedFromWindow();
    }

    public void quickCloseMenu() {
        if (getScrollX() != 0) {
            cleanAnim();
            scrollTo(0, 0);
            mCacheView = null;
        }
    }

    public void quickExpandMenu() {
        if (getScrollX() == 0) {
            cleanAnim();
            scrollTo(this.isEnableLeftMenu ? -this.mMenuWidth : this.mMenuWidth, 0);
            mCacheView = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void longClickable(boolean enable) {
        setLongClickable(enable);
    }

    @Override // android.view.View
    public boolean performLongClick() {
        if (getScrollX() != 0) {
            return true;
        }
        return super.performLongClick();
    }

    public SwipeMenuLayout getCacheView() {
        return mCacheView;
    }

    public boolean isExpandMenu() {
        return Math.abs(getScaleX()) >= ((float) this.mMenuWidth);
    }

    public boolean isOpenChoke() {
        return this.isOpenChoke;
    }

    public SwipeMenuLayout setOpenChoke(boolean openChoke) {
        this.isOpenChoke = openChoke;
        return this;
    }

    public boolean isEnableSwipe() {
        return this.isEnableSwipe;
    }

    public SwipeMenuLayout setEnableSwipe(boolean enableSwipe) {
        this.isEnableSwipe = enableSwipe;
        return this;
    }

    public boolean isEnableLeftMenu() {
        return this.isEnableLeftMenu;
    }

    public SwipeMenuLayout setEnableLeftMenu(boolean enableLeftMenu) {
        this.isEnableLeftMenu = enableLeftMenu;
        return this;
    }

    public boolean isClickMenuAndClose() {
        return this.isClickMenuAndClose;
    }

    public SwipeMenuLayout setClickMenuAndClose(boolean clickMenuAndClose) {
        this.isClickMenuAndClose = clickMenuAndClose;
        return this;
    }

    public SwipeMenuLayout setSwipeMenuStateListener(SwipeMenuStateListener listener) {
        this.mSwipeMenuStateListener = listener;
        return this;
    }
}
