package com.donkingliang.consecutivescroller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.lzy.okgo.model.Priority;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ConsecutiveScrollerLayout extends ViewGroup implements ScrollingView, NestedScrollingParent2, NestedScrollingChild2 {
    private static final int MAX_CYCLE_COUNT = 1000;
    private static final int SCROLL_HORIZONTAL = 2;
    private static final int SCROLL_NONE = 0;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final int SCROLL_VERTICAL = 1;
    static final Interpolator sQuinticInterpolator = new Interpolator() { // from class: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float t) {
            float f = t - 1.0f;
            return (f * f * f * f * f) + 1.0f;
        }
    };
    private int SCROLL_ORIENTATION;
    private String TAG;
    protected Runnable animationRunnable;
    private boolean disableChildHorizontalScroll;
    private boolean isBrake;
    private boolean isIntercept;
    private boolean isPermanent;
    private boolean isTouchNotTriggerScrollStick;
    private int mActivePointerId;
    private int mAdjust;
    private int mAdjustHeightOffset;
    private VelocityTracker mAdjustVelocityTracker;
    private int mAdjustYVelocity;
    private boolean mAutoAdjustHeightAtBottomView;
    private NestedScrollingChildHelper mChildHelper;
    private View mCurrentStickyView;
    private final List<View> mCurrentStickyViews;
    protected int mCurrentVelocity;
    private int mCycleCount;
    private final int[] mDownLocation;
    protected float mDragRate;
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    private int mEventX;
    private int mEventY;
    private HashMap<Integer, Float> mFixedYMap;
    protected Handler mHandler;
    private int mLastScrollerY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    private int mOldScrollY;
    private OnPermanentStickyChangeListener mOnPermanentStickyChangeListener;
    protected OnScrollChangeListener mOnScrollChangeListener;
    private OnStickyChangeListener mOnStickyChangeListener;
    private NestedScrollingParentHelper mParentHelper;
    protected int mReboundDuration;
    protected Interpolator mReboundInterpolator;
    protected int mScreenHeightPixels;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;
    int mScrollRange;
    private int mScrollState;
    private int mScrollToIndex;
    private int mScrollToIndexWithOffset;
    private View mScrollToTopView;
    private OverScroller mScroller;
    private int mSecondScrollY;
    private int mSmoothScrollOffset;
    private int mStickyOffset;
    private final List<View> mTempStickyViews;
    private int mTouchSlop;
    private int mTouchY;
    private boolean mTouching;
    private VelocityTracker mVelocityTracker;
    private final List<View> mViews;
    private int overDragMaxDistanceOfBottom;
    private int overDragMaxDistanceOfTop;
    private boolean overDragMode;
    protected ValueAnimator reboundAnimator;

    public interface OnPermanentStickyChangeListener {
        void onStickyChange(List<View> mCurrentStickyViews);
    }

    public interface OnScrollChangeListener {
        void onScrollChange(View v, int scrollY, int oldScrollY, int scrollState);
    }

    public interface OnStickyChangeListener {
        void onStickyChange(View oldStickyView, View newStickyView);
    }

    @Override // android.view.View
    @Deprecated
    public void setOnScrollChangeListener(View.OnScrollChangeListener l) {
    }

    public ConsecutiveScrollerLayout(Context context) {
        this(context, null);
    }

    public ConsecutiveScrollerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConsecutiveScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDragRate = 0.5f;
        this.mReboundDuration = 300;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mFixedYMap = new HashMap<>();
        this.mDownLocation = new int[2];
        this.mTouching = false;
        this.SCROLL_ORIENTATION = 0;
        this.mActivePointerId = -1;
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mScrollToIndex = -1;
        this.mSmoothScrollOffset = 0;
        this.mScrollToIndexWithOffset = 0;
        this.mCycleCount = 0;
        this.mAdjustHeightOffset = 0;
        this.mStickyOffset = 0;
        this.mCurrentStickyViews = new ArrayList();
        this.mTempStickyViews = new ArrayList();
        this.mOldScrollY = 0;
        this.mViews = new ArrayList();
        this.mNestedYOffset = 0;
        this.mScrollState = 0;
        this.isTouchNotTriggerScrollStick = false;
        this.isIntercept = false;
        this.isBrake = false;
        this.TAG = getClass().getName();
        TypedArray typedArrayObtainStyledAttributes = null;
        try {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ConsecutiveScrollerLayout);
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ConsecutiveScrollerLayout_overDragMode)) {
                boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConsecutiveScrollerLayout_overDragMode, false);
                this.overDragMode = z;
                if (z) {
                    int iDp2px = Util.dp2px(180.0f);
                    this.overDragMaxDistanceOfTop = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.ConsecutiveScrollerLayout_overDragMaxDistanceOfTop, iDp2px);
                    this.overDragMaxDistanceOfBottom = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.ConsecutiveScrollerLayout_overDragMaxDistanceOfBottom, iDp2px);
                }
            }
            this.isPermanent = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConsecutiveScrollerLayout_isPermanent, false);
            this.disableChildHorizontalScroll = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConsecutiveScrollerLayout_disableChildHorizontalScroll, false);
            this.mStickyOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.ConsecutiveScrollerLayout_stickyOffset, 0);
            this.mAutoAdjustHeightAtBottomView = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConsecutiveScrollerLayout_autoAdjustHeightAtBottomView, false);
            this.mAdjustHeightOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.ConsecutiveScrollerLayout_adjustHeightOffset, 0);
            this.mScroller = new OverScroller(getContext(), sQuinticInterpolator);
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
            this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
            this.mTouchSlop = ViewConfiguration.getTouchSlop();
            setWillNotDraw(false);
            setVerticalScrollBarEnabled(true);
            this.mParentHelper = new NestedScrollingParentHelper(this);
            this.mChildHelper = new NestedScrollingChildHelper(this);
            setNestedScrollingEnabled(true);
            setChildrenDrawingOrderEnabled(true);
            setMotionEventSplittingEnabled(false);
            this.mReboundInterpolator = new OverScrollInterpolator(OverScrollInterpolator.INTERPOLATOR_VISCOUS_FLUID);
        } finally {
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        List<View> scrolledViews;
        if (params instanceof LayoutParams) {
            LayoutParamsUtils.invalidTopAndBottomMargin((LayoutParams) params);
        }
        super.addView(child, index, params);
        if (ScrollUtils.isConsecutiveScrollerChild(child)) {
            View scrollChild = ScrollUtils.getScrollChild(child);
            disableChildScroll(scrollChild);
            if ((scrollChild instanceof IConsecutiveScroller) && (scrolledViews = ((IConsecutiveScroller) scrollChild).getScrolledViews()) != null && !scrolledViews.isEmpty()) {
                int size = scrolledViews.size();
                for (int i = 0; i < size; i++) {
                    disableChildScroll(scrolledViews.get(i));
                }
            }
        }
        if (child instanceof ViewGroup) {
            ((ViewGroup) child).setClipToPadding(false);
        }
    }

    private void disableChildScroll(View child) {
        child.setVerticalScrollBarEnabled(false);
        child.setHorizontalScrollBarEnabled(false);
        child.setOverScrollMode(2);
        ViewCompat.setNestedScrollingEnabled(child, false);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        resetScrollToTopView();
        List<View> nonGoneChildren = getNonGoneChildren();
        int size = nonGoneChildren.size();
        int iMax = 0;
        int measuredHeight = 0;
        for (int i = 0; i < size; i++) {
            View view = nonGoneChildren.get(i);
            measureChildWithMargins(view, widthMeasureSpec, 0, heightMeasureSpec, getAdjustHeightForChild(view));
            iMax = Math.max(iMax, getContentWidth(view));
            measuredHeight += view.getMeasuredHeight();
        }
        setMeasuredDimension(measureSize(widthMeasureSpec, iMax + getPaddingLeft() + getPaddingRight()), measureSize(heightMeasureSpec, measuredHeight + getPaddingTop() + getPaddingBottom()));
    }

    private int getAdjustHeightForChild(View child) {
        if (this.mAutoAdjustHeightAtBottomView && child == getChildAt(getChildCount() - 1)) {
            return getAdjustHeight();
        }
        return 0;
    }

    private int getAdjustHeight() {
        List<View> stickyChildren = getStickyChildren();
        int measuredHeight = this.mAdjustHeightOffset;
        int size = stickyChildren.size();
        if (this.isPermanent) {
            for (int i = 0; i < size; i++) {
                View view = stickyChildren.get(i);
                if (!isSink(view)) {
                    measuredHeight += view.getMeasuredHeight();
                }
            }
            return measuredHeight;
        }
        for (int i2 = size - 1; i2 >= 0; i2--) {
            View view2 = stickyChildren.get(i2);
            if (!isSink(view2)) {
                return measuredHeight + view2.getMeasuredHeight();
            }
        }
        return measuredHeight;
    }

    private int getContentWidth(View child) {
        int measuredWidth = child.getMeasuredWidth();
        LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
        return measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    private int measureSize(int measureSpec, int size) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size2 = View.MeasureSpec.getSize(measureSpec);
        if (mode == 1073741824) {
            size = size2;
        } else if (mode == Integer.MIN_VALUE) {
            size = Math.min(size, size2);
        }
        return resolveSizeAndState(Math.max(size, getSuggestedMinimumWidth()), measureSpec, 0);
    }

    @Override // android.view.ViewGroup
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        LayoutParamsUtils.invalidTopAndBottomMargin((LayoutParams) child.getLayoutParams());
        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        this.mScreenHeightPixels = getResources().getDisplayMetrics().heightPixels;
        this.mScrollRange = 0;
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int measuredWidth = getMeasuredWidth();
        List<View> nonGoneChildren = getNonGoneChildren();
        int size = nonGoneChildren.size();
        int i = 0;
        while (i < size) {
            View view = nonGoneChildren.get(i);
            int measuredHeight = view.getMeasuredHeight() + paddingTop;
            int childLeft = getChildLeft(view, measuredWidth, paddingLeft, paddingRight);
            view.layout(childLeft, paddingTop, view.getMeasuredWidth() + childLeft, measuredHeight);
            this.mScrollRange += view.getHeight();
            i++;
            paddingTop = measuredHeight;
        }
        int measuredHeight2 = this.mScrollRange - ((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        this.mScrollRange = measuredHeight2;
        if (measuredHeight2 < 0) {
            this.mScrollRange = 0;
        }
        checkLayoutChange(changed, false);
        sortViews();
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
    }

    private void sortViews() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (!isStickyView(childAt) || isSink(childAt)) {
                arrayList.add(childAt);
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt2 = getChildAt(i2);
            if (isStickyView(childAt2) && !isSink(childAt2)) {
                arrayList.add(childAt2);
            }
        }
        this.mViews.clear();
        this.mViews.addAll(arrayList);
    }

    /* JADX INFO: renamed from: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$donkingliang$consecutivescroller$ConsecutiveScrollerLayout$LayoutParams$Align;

        static {
            int[] iArr = new int[LayoutParams.Align.values().length];
            $SwitchMap$com$donkingliang$consecutivescroller$ConsecutiveScrollerLayout$LayoutParams$Align = iArr;
            try {
                iArr[LayoutParams.Align.RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$donkingliang$consecutivescroller$ConsecutiveScrollerLayout$LayoutParams$Align[LayoutParams.Align.CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$donkingliang$consecutivescroller$ConsecutiveScrollerLayout$LayoutParams$Align[LayoutParams.Align.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private int getChildLeft(View child, int parentWidth, int paddingLeft, int paddingRight) {
        LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
        int i = AnonymousClass5.$SwitchMap$com$donkingliang$consecutivescroller$ConsecutiveScrollerLayout$LayoutParams$Align[layoutParams.align.ordinal()];
        if (i == 1) {
            return ((parentWidth - child.getMeasuredWidth()) - paddingRight) - layoutParams.rightMargin;
        }
        if (i == 2) {
            return layoutParams.leftMargin + paddingLeft + ((((((parentWidth - child.getMeasuredWidth()) - paddingLeft) - layoutParams.leftMargin) - paddingRight) - layoutParams.rightMargin) / 2);
        }
        return paddingLeft + layoutParams.leftMargin;
    }

    private void resetScrollToTopView() {
        View viewFindFirstVisibleView = findFirstVisibleView();
        this.mScrollToTopView = viewFindFirstVisibleView;
        if (viewFindFirstVisibleView != null) {
            this.mAdjust = getScrollY() - this.mScrollToTopView.getTop();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x01f3  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instruction units count: 784
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x001f  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getActionMasked()
            if (r0 == 0) goto L2c
            r1 = 1
            if (r0 == r1) goto L1f
            r2 = 2
            if (r0 == r2) goto L10
            r2 = 3
            if (r0 == r2) goto L1f
            goto L34
        L10:
            int r0 = r3.SCROLL_ORIENTATION
            if (r0 == r2) goto L34
            boolean r0 = r3.isIntercept
            if (r0 != 0) goto L1e
            boolean r0 = r3.isIntercept(r4)
            if (r0 == 0) goto L34
        L1e:
            return r1
        L1f:
            r0 = 0
            r3.stopNestedScroll(r0)
            boolean r0 = r3.isBrake
            if (r0 == 0) goto L34
            int r0 = r3.SCROLL_ORIENTATION
            if (r0 != 0) goto L34
            return r1
        L2c:
            r3.initOrResetVelocityTracker()
            android.view.VelocityTracker r0 = r3.mVelocityTracker
            r0.addMovement(r4)
        L34:
            boolean r4 = super.onInterceptTouchEvent(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:86:0x01b2  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r15) {
        /*
            Method dump skipped, instruction units count: 447
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean canScrollVertically() {
        return (isScrollTop() && isScrollBottom() && !this.overDragMode) ? false : true;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int childCount, int drawingPosition) {
        int iIndexOfChild;
        return (this.mViews.size() <= drawingPosition || (iIndexOfChild = indexOfChild(this.mViews.get(drawingPosition))) == -1) ? super.getChildDrawingOrder(childCount, drawingPosition) : iIndexOfChild;
    }

    int getDrawingPosition(View child) {
        return this.mViews.indexOf(child);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int paddingLeft;
        int paddingTop;
        super.draw(canvas);
        if (this.mOldScrollY != getScrollY()) {
            this.mOldScrollY = getScrollY();
            resetSticky();
        }
        if (this.mEdgeGlowTop != null) {
            int scrollY = getScrollY();
            int paddingLeft2 = 0;
            if (!this.mEdgeGlowTop.isFinished()) {
                int iSave = canvas.save();
                int width = getWidth();
                int height = getHeight();
                if (getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    paddingLeft = getPaddingLeft();
                } else {
                    paddingLeft = 0;
                }
                if (getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    paddingTop = getPaddingTop() + scrollY;
                } else {
                    paddingTop = scrollY;
                }
                canvas.translate(paddingLeft, paddingTop);
                this.mEdgeGlowTop.setSize(width, height);
                if (this.mEdgeGlowTop.draw(canvas)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                canvas.restoreToCount(iSave);
            }
            if (this.mEdgeGlowBottom.isFinished()) {
                return;
            }
            int iSave2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int paddingBottom = scrollY + height2;
            if (getClipToPadding()) {
                width2 -= getPaddingLeft() + getPaddingRight();
                paddingLeft2 = getPaddingLeft();
            }
            if (getClipToPadding()) {
                height2 -= getPaddingTop() + getPaddingBottom();
                paddingBottom -= getPaddingBottom();
            }
            canvas.translate(paddingLeft2 - width2, paddingBottom);
            canvas.rotate(180.0f, width2, 0.0f);
            this.mEdgeGlowBottom.setSize(width2, height2);
            if (this.mEdgeGlowBottom.draw(canvas)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            canvas.restoreToCount(iSave2);
        }
    }

    private int getScrollRange() {
        if (getChildCount() > 0) {
            return Math.max(0, computeVerticalScrollRange() - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
        }
        return 0;
    }

    private void fling(int velocityY) {
        if (Math.abs(velocityY) > this.mMinimumVelocity) {
            float f = velocityY;
            if (dispatchNestedPreFling(0.0f, f)) {
                return;
            }
            dispatchNestedFling(0.0f, f, (velocityY < 0 && !isScrollTop()) || (velocityY > 0 && !isScrollBottom()));
            this.mScroller.fling(0, this.mSecondScrollY, 1, velocityY, Priority.BG_LOW, Priority.BG_LOW, Priority.BG_LOW, Integer.MAX_VALUE);
            startNestedScroll(2, 1);
            setScrollState(2);
            this.mLastScrollerY = this.mSecondScrollY;
            invalidate();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        int i;
        if (this.mScrollToIndex != -1 && (i = this.mSmoothScrollOffset) != 0) {
            if (i > 0 && i < 200) {
                this.mSmoothScrollOffset = i + 5;
            }
            int i2 = this.mSmoothScrollOffset;
            if (i2 < 0 && i2 > -200) {
                this.mSmoothScrollOffset = i2 - 5;
            }
            dispatchScroll(this.mSmoothScrollOffset);
            this.mCycleCount++;
            invalidate();
            return;
        }
        if (this.mScroller.computeScrollOffset()) {
            int currY = this.mScroller.getCurrY();
            int i3 = currY - this.mLastScrollerY;
            this.mLastScrollerY = currY;
            int[] iArr = this.mScrollConsumed;
            iArr[1] = 0;
            dispatchNestedPreScroll(0, i3, iArr, null, 1);
            int i4 = i3 - this.mScrollConsumed[1];
            int i5 = this.mSecondScrollY;
            dispatchScroll(i4);
            int i6 = this.mSecondScrollY - i5;
            int i7 = i4 - i6;
            if ((i7 < 0 && isScrollTop()) || (i7 > 0 && isScrollBottom())) {
                dispatchNestedScroll(0, i6, 0, i7, this.mScrollOffset, 1);
                i7 += this.mScrollOffset[1];
            }
            if ((i7 < 0 && isScrollTop()) || (i7 > 0 && isScrollBottom())) {
                if (this.overDragMode) {
                    animSpinnerBounce(this.mScroller.getFinalY() > 0 ? this.mScroller.getCurrVelocity() : -this.mScroller.getCurrVelocity());
                    this.mScroller.forceFinished(true);
                } else {
                    int overScrollMode = getOverScrollMode();
                    if (overScrollMode == 0 || (overScrollMode == 1 && getScrollRange() > 0)) {
                        ensureGlows();
                        if (i7 < 0) {
                            if (this.mEdgeGlowTop.isFinished()) {
                                this.mEdgeGlowTop.onAbsorb((int) this.mScroller.getCurrVelocity());
                            }
                        } else if (this.mEdgeGlowBottom.isFinished()) {
                            this.mEdgeGlowBottom.onAbsorb((int) this.mScroller.getCurrVelocity());
                        }
                    }
                    stopScroll();
                }
            }
            invalidate();
        }
        if (this.mScrollState == 2 && this.mScroller.isFinished()) {
            stopNestedScroll(1);
            checkTargetsScroll(false, false);
            setScrollState(0);
        }
    }

    protected boolean interceptAnimatorByAction(int action) {
        if (action == 0) {
            ValueAnimator valueAnimator = this.reboundAnimator;
            if (valueAnimator != null) {
                valueAnimator.setDuration(0L);
                this.reboundAnimator.cancel();
                this.reboundAnimator = null;
            }
            this.animationRunnable = null;
        }
        return this.reboundAnimator != null;
    }

    protected class BounceRunnable implements Runnable {
        int mSmoothDistance;
        float mVelocity;
        int mFrame = 0;
        int mFrameDelay = 10;
        float mOffset = 0.0f;
        long mLastTime = AnimationUtils.currentAnimationTimeMillis();

        BounceRunnable(float velocity, int smoothDistance) {
            this.mVelocity = velocity;
            this.mSmoothDistance = smoothDistance;
            ConsecutiveScrollerLayout.this.mHandler.postDelayed(this, this.mFrameDelay);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ConsecutiveScrollerLayout.this.animationRunnable == this) {
                double d = this.mVelocity;
                this.mFrame = this.mFrame + 1;
                this.mVelocity = (float) (d * Math.pow(0.8500000238418579d, r2 * 2));
                long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float f = this.mVelocity * (((jCurrentAnimationTimeMillis - this.mLastTime) * 1.0f) / 1000.0f);
                if (Math.abs(f) >= 1.0f) {
                    this.mLastTime = jCurrentAnimationTimeMillis;
                    this.mOffset += f;
                    int scrollY = ConsecutiveScrollerLayout.this.getScrollY();
                    ConsecutiveScrollerLayout.this.moveSpinnerInfinitely(this.mOffset);
                    if (scrollY != ConsecutiveScrollerLayout.this.mSecondScrollY) {
                        ConsecutiveScrollerLayout consecutiveScrollerLayout = ConsecutiveScrollerLayout.this;
                        consecutiveScrollerLayout.scrollChange(consecutiveScrollerLayout.mSecondScrollY, scrollY);
                    }
                    ConsecutiveScrollerLayout.this.mHandler.postDelayed(this, this.mFrameDelay);
                    return;
                }
                ConsecutiveScrollerLayout.this.animationRunnable = null;
                int scrollY2 = ConsecutiveScrollerLayout.this.getScrollY();
                int iMin = Math.min(Math.max((int) Util.px2dp(Math.abs(scrollY2 - this.mSmoothDistance)), 30), 100) * 10;
                ConsecutiveScrollerLayout consecutiveScrollerLayout2 = ConsecutiveScrollerLayout.this;
                consecutiveScrollerLayout2.animSpinner(scrollY2, this.mSmoothDistance, 0, consecutiveScrollerLayout2.mReboundInterpolator, iMin);
            }
        }
    }

    protected void moveSpinnerInfinitely(float spinner) {
        double dMin;
        double dMax = Math.max(this.mScreenHeightPixels / 2, getHeight());
        if (spinner > 0.0f) {
            double dMax2 = Math.max(0.0f, this.mDragRate * spinner);
            double d = -dMax2;
            if (dMax == 0.0d) {
                dMax = 1.0d;
            }
            dMin = Math.min(d * (1.0d - Math.pow(100.0d, d / dMax)), dMax2);
        } else {
            double d2 = -Math.min(0.0f, this.mDragRate * spinner);
            double d3 = -d2;
            if (dMax == 0.0d) {
                dMax = 1.0d;
            }
            dMin = -Math.min(d * (1.0d - Math.pow(100.0d, d3 / dMax)), d2);
        }
        int i = (int) dMin;
        if (Math.abs(spinner) >= 1.0f && i == 0) {
            i = (int) spinner;
        }
        int scrollY = getScrollY() + i;
        this.mSecondScrollY += i;
        scrollSelf(scrollY);
    }

    protected ValueAnimator animSpinner(int startSpinner, int endSpinner, int startDelay, Interpolator interpolator, int duration) {
        if (startSpinner == endSpinner) {
            return null;
        }
        ValueAnimator valueAnimator = this.reboundAnimator;
        if (valueAnimator != null) {
            valueAnimator.setDuration(0L);
            this.reboundAnimator.cancel();
            this.reboundAnimator = null;
        }
        this.animationRunnable = null;
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(startSpinner, endSpinner);
        this.reboundAnimator = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(duration);
        this.reboundAnimator.setInterpolator(interpolator);
        this.reboundAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (animation == null || animation.getDuration() != 0) {
                    ConsecutiveScrollerLayout.this.reboundAnimator = null;
                    ConsecutiveScrollerLayout.this.checkTargetsScroll(false, false);
                }
            }
        });
        this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.m27xfaf03e34(valueAnimator2);
            }
        });
        this.reboundAnimator.setStartDelay(startDelay);
        this.reboundAnimator.start();
        return this.reboundAnimator;
    }

    /* JADX INFO: renamed from: lambda$animSpinner$0$com-donkingliang-consecutivescroller-ConsecutiveScrollerLayout, reason: not valid java name */
    /* synthetic */ void m27xfaf03e34(ValueAnimator animation) {
        int iIntValue = ((Integer) animation.getAnimatedValue()).intValue();
        int iComputeVerticalScrollOffset = computeVerticalScrollOffset();
        scrollSelf(iIntValue);
        int iComputeVerticalScrollOffset2 = computeVerticalScrollOffset();
        this.mSecondScrollY = iComputeVerticalScrollOffset2;
        if (iComputeVerticalScrollOffset != iComputeVerticalScrollOffset2) {
            scrollChange(iComputeVerticalScrollOffset2, iComputeVerticalScrollOffset);
        }
    }

    protected void animSpinnerBounce(final float velocity) {
        if (this.reboundAnimator == null) {
            if (velocity < 0.0f && this.overDragMaxDistanceOfTop > 0) {
                this.animationRunnable = new BounceRunnable(velocity, 0);
            } else {
                if (velocity <= 0.0f || this.overDragMaxDistanceOfBottom <= 0) {
                    return;
                }
                this.animationRunnable = new BounceRunnable(velocity, this.mScrollRange);
            }
        }
    }

    protected void overSpinner() {
        int scrollY = getScrollY();
        if (scrollY < 0) {
            if (this.reboundAnimator == null) {
                animSpinner(scrollY, 0, 0, this.mReboundInterpolator, this.mReboundDuration);
            }
        } else {
            int i = this.mScrollRange;
            if (scrollY <= i || this.reboundAnimator != null) {
                return;
            }
            animSpinner(scrollY, i, 0, this.mReboundInterpolator, this.mReboundDuration);
        }
    }

    private void endDrag() {
        EdgeEffect edgeEffect = this.mEdgeGlowTop;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.mEdgeGlowBottom.onRelease();
        }
    }

    private void ensureGlows() {
        if (getOverScrollMode() != 2) {
            if (this.mEdgeGlowTop == null) {
                Context context = getContext();
                this.mEdgeGlowTop = new EdgeEffect(context);
                this.mEdgeGlowBottom = new EdgeEffect(context);
                return;
            }
            return;
        }
        this.mEdgeGlowTop = null;
        this.mEdgeGlowBottom = null;
    }

    private void dispatchScroll(int offset) {
        if (offset > 0) {
            scrollUp(offset);
        } else if (offset < 0) {
            scrollDown(offset);
        }
    }

    private void scrollUp(int offset) {
        int viewsScrollOffset;
        int top;
        View bottomView;
        int iMin;
        int iComputeVerticalScrollOffset = computeVerticalScrollOffset();
        do {
            int i = this.mScrollToIndex;
            int iAbs = 0;
            if (i != -1) {
                View childAt = getChildAt(i);
                top = (childAt.getTop() - this.mScrollToIndexWithOffset) - getAdjustHeightForChild(childAt);
                viewsScrollOffset = this.mScrollToIndexWithOffset < 0 ? getViewsScrollOffset(this.mScrollToIndex) : 0;
                if (this.mCycleCount >= 1000 || getScrollY() + getPaddingTop() + viewsScrollOffset >= top || isScrollBottom()) {
                    this.mScrollToIndex = -1;
                    this.mSmoothScrollOffset = 0;
                    this.mScrollToIndexWithOffset = 0;
                    this.mCycleCount = 0;
                    setScrollState(0);
                    break;
                }
            } else {
                viewsScrollOffset = 0;
                top = 0;
            }
            int scrollY = getScrollY();
            if (!isScrollBottom() && scrollY >= 0) {
                if (getScrollY() < this.mScrollRange) {
                    bottomView = findFirstVisibleView();
                } else {
                    bottomView = getBottomView();
                }
                if (bottomView != null) {
                    awakenScrollBars();
                    int scrollBottomOffset = ScrollUtils.getScrollBottomOffset(bottomView);
                    if (scrollBottomOffset > 0) {
                        iMin = Math.min(offset, scrollBottomOffset);
                        if (this.mScrollToIndex != -1) {
                            iMin = Math.min(iMin, top - ((getScrollY() + getPaddingTop()) + viewsScrollOffset));
                        }
                        scrollChild(bottomView, iMin);
                    } else {
                        int iMin2 = Math.min(offset, (bottomView.getBottom() - getPaddingTop()) - getScrollY());
                        int iMin3 = this.mScrollToIndex != -1 ? Math.min(iMin2, top - ((getScrollY() + getPaddingTop()) + viewsScrollOffset)) : iMin2;
                        scrollSelf(scrollY + iMin3);
                        iMin = iMin3;
                    }
                    this.mSecondScrollY += iMin;
                    offset -= iMin;
                    iAbs = iMin;
                }
            } else if (this.mTouching) {
                if (scrollY < 0 && offset > Math.abs(scrollY)) {
                    iAbs = offset - Math.abs(scrollY);
                    offset -= iAbs;
                    moveSpinnerInfinitely(iAbs);
                } else {
                    dispatchNestedScroll(0, 0, 0, offset, this.mScrollOffset, 0);
                    if (this.mScrollOffset[1] == 0 && this.overDragMode && this.overDragMaxDistanceOfBottom >= 0) {
                        moveSpinnerInfinitely(offset);
                    }
                    offset = 0;
                }
            } else if (!this.mScroller.isFinished() && this.mScroller.getFinalY() > 0 && scrollY < 0) {
                if (this.reboundAnimator != null) {
                    interceptAnimatorByAction(0);
                }
                if (offset > Math.abs(scrollY)) {
                    int iAbs2 = offset - Math.abs(scrollY);
                    iAbs = offset - iAbs2;
                    offset = iAbs2;
                }
                this.mSecondScrollY += offset;
                scrollSelf(scrollY + offset);
                int i2 = iAbs;
                iAbs = offset;
                offset = i2;
            } else if (scrollY < 0) {
                this.mScroller.forceFinished(true);
            }
            if (iAbs <= 0) {
                break;
            }
        } while (offset > 0);
        int iComputeVerticalScrollOffset2 = computeVerticalScrollOffset();
        if (iComputeVerticalScrollOffset != iComputeVerticalScrollOffset2) {
            scrollChange(iComputeVerticalScrollOffset2, iComputeVerticalScrollOffset);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0123  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void scrollDown(int r12) {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.scrollDown(int):void");
    }

    @Override // android.view.View
    public void scrollBy(int x, int y) {
        scrollTo(0, this.mSecondScrollY + y);
    }

    @Override // android.view.View
    public void scrollTo(int x, int y) {
        dispatchScroll(y - this.mSecondScrollY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollChange(int scrollY, int oldScrollY) {
        OnScrollChangeListener onScrollChangeListener = this.mOnScrollChangeListener;
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChange(this, scrollY, oldScrollY, this.mScrollState);
        }
    }

    private void stickyChange(View oldStickyView, View newStickyView) {
        OnStickyChangeListener onStickyChangeListener = this.mOnStickyChangeListener;
        if (onStickyChangeListener != null) {
            onStickyChangeListener.onStickyChange(oldStickyView, newStickyView);
        }
    }

    private void permanentStickyChange(List<View> mCurrentStickyViews) {
        OnPermanentStickyChangeListener onPermanentStickyChangeListener = this.mOnPermanentStickyChangeListener;
        if (onPermanentStickyChangeListener != null) {
            onPermanentStickyChangeListener.onStickyChange(mCurrentStickyViews);
        }
    }

    private void scrollSelf(int y) {
        if (y < 0 && Math.abs(y) > Math.abs(this.overDragMaxDistanceOfTop)) {
            int i = this.overDragMaxDistanceOfTop;
            y = i <= 0 ? 0 : -i;
        } else {
            int i2 = this.mScrollRange;
            if (y > i2 && y > i2 + Math.abs(this.overDragMaxDistanceOfBottom)) {
                int i3 = this.overDragMaxDistanceOfBottom;
                y = i3 <= 0 ? this.mScrollRange : this.mScrollRange + i3;
            }
        }
        super.scrollTo(0, y);
    }

    private void scrollChild(View child, int y) {
        RecyclerView scrolledView = ScrollUtils.getScrolledView(child);
        if (scrolledView instanceof AbsListView) {
            ((AbsListView) scrolledView).scrollListBy(y);
            return;
        }
        boolean zStartInterceptRequestLayout = scrolledView instanceof RecyclerView ? ScrollUtils.startInterceptRequestLayout(scrolledView) : false;
        scrolledView.scrollBy(0, y);
        if (zStartInterceptRequestLayout) {
            final RecyclerView recyclerView = scrolledView;
            recyclerView.postDelayed(new Runnable() { // from class: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.3
                @Override // java.lang.Runnable
                public void run() {
                    ScrollUtils.stopInterceptRequestLayout(recyclerView);
                }
            }, 0L);
        }
    }

    public void checkLayoutChange() {
        postDelayed(new Runnable() { // from class: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.4
            @Override // java.lang.Runnable
            public void run() {
                ConsecutiveScrollerLayout.this.checkLayoutChange(false, true);
            }
        }, 20L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLayoutChange(boolean changed, boolean isForce) {
        int i = this.mSecondScrollY;
        View view = this.mScrollToTopView;
        if (view != null && changed) {
            if (indexOfChild(view) != -1) {
                scrollSelf(this.mScrollToTopView.getTop() + this.mAdjust);
            }
        } else {
            scrollSelf(getScrollY());
        }
        checkTargetsScroll(true, isForce);
        if (i != this.mSecondScrollY && this.mScrollToTopView != findFirstVisibleView()) {
            scrollTo(0, i);
        }
        this.mScrollToTopView = null;
        this.mAdjust = 0;
        resetChildren();
        resetSticky();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void checkTargetsScroll(boolean isLayoutChange, boolean isForce) {
        int iComputeVerticalScrollOffset;
        if (isForce || (!this.mTouching && this.mScroller.isFinished() && this.mScrollToIndex == -1)) {
            int iComputeVerticalScrollOffset2 = computeVerticalScrollOffset();
            View viewFindFirstVisibleView = findFirstVisibleView();
            if (viewFindFirstVisibleView == null) {
                return;
            }
            int iIndexOfChild = indexOfChild(viewFindFirstVisibleView);
            if (isLayoutChange) {
                while (true) {
                    int scrollBottomOffset = ScrollUtils.getScrollBottomOffset(viewFindFirstVisibleView);
                    int top = viewFindFirstVisibleView.getTop() - getScrollY();
                    if (scrollBottomOffset <= 0 || top >= 0) {
                        break;
                    }
                    int iMin = Math.min(scrollBottomOffset, -top);
                    scrollSelf(getScrollY() - iMin);
                    scrollChild(viewFindFirstVisibleView, iMin);
                }
            }
            for (int i = 0; i < iIndexOfChild; i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() != 8 && ScrollUtils.isConsecutiveScrollerChild(childAt)) {
                    View scrollChild = ScrollUtils.getScrollChild(childAt);
                    if (scrollChild instanceof IConsecutiveScroller) {
                        List<View> scrolledViews = ((IConsecutiveScroller) scrollChild).getScrolledViews();
                        if (scrolledViews != null && !scrolledViews.isEmpty()) {
                            int size = scrolledViews.size();
                            for (int i2 = 0; i2 < size; i2++) {
                                scrollChildContentToBottom(scrolledViews.get(i2));
                            }
                        }
                    } else {
                        scrollChildContentToBottom(scrollChild);
                    }
                }
            }
            while (true) {
                iIndexOfChild++;
                if (iIndexOfChild >= getChildCount()) {
                    break;
                }
                View childAt2 = getChildAt(iIndexOfChild);
                if (childAt2.getVisibility() != 8 && ScrollUtils.isConsecutiveScrollerChild(childAt2) && (iIndexOfChild != getChildCount() - 1 || childAt2.getHeight() >= getHeight() || getScrollY() < this.mScrollRange)) {
                    View scrollChild2 = ScrollUtils.getScrollChild(childAt2);
                    if (scrollChild2 instanceof IConsecutiveScroller) {
                        List<View> scrolledViews2 = ((IConsecutiveScroller) scrollChild2).getScrolledViews();
                        if (scrolledViews2 != null && !scrolledViews2.isEmpty()) {
                            int size2 = scrolledViews2.size();
                            for (int i3 = 0; i3 < size2; i3++) {
                                scrollChildContentToTop(scrolledViews2.get(i3));
                            }
                        }
                    } else {
                        scrollChildContentToTop(scrollChild2);
                    }
                }
            }
            computeOwnScrollOffset();
            if (isLayoutChange && iComputeVerticalScrollOffset2 != (iComputeVerticalScrollOffset = computeVerticalScrollOffset())) {
                scrollChange(iComputeVerticalScrollOffset, iComputeVerticalScrollOffset2);
            }
            resetSticky();
        }
    }

    void scrollChildContentToTop(View target) {
        int iComputeVerticalScrollOffset;
        do {
            int scrollTopOffset = ScrollUtils.getScrollTopOffset(target);
            if (scrollTopOffset < 0) {
                int iComputeVerticalScrollOffset2 = ScrollUtils.computeVerticalScrollOffset(target);
                scrollChild(target, scrollTopOffset);
                iComputeVerticalScrollOffset = iComputeVerticalScrollOffset2 - ScrollUtils.computeVerticalScrollOffset(target);
            } else {
                iComputeVerticalScrollOffset = 0;
            }
        } while (iComputeVerticalScrollOffset != 0);
    }

    void scrollChildContentToBottom(View target) {
        int iComputeVerticalScrollOffset;
        do {
            int scrollBottomOffset = ScrollUtils.getScrollBottomOffset(target);
            if (scrollBottomOffset > 0) {
                int iComputeVerticalScrollOffset2 = ScrollUtils.computeVerticalScrollOffset(target);
                scrollChild(target, scrollBottomOffset);
                iComputeVerticalScrollOffset = iComputeVerticalScrollOffset2 - ScrollUtils.computeVerticalScrollOffset(target);
            } else {
                iComputeVerticalScrollOffset = 0;
            }
        } while (iComputeVerticalScrollOffset != 0);
    }

    private void computeOwnScrollOffset() {
        this.mSecondScrollY = computeVerticalScrollOffset();
    }

    private void initOrResetVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void initOrResetAdjustVelocityTracker() {
        VelocityTracker velocityTracker = this.mAdjustVelocityTracker;
        if (velocityTracker == null) {
            this.mAdjustVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void initAdjustVelocityTrackerIfNotExists() {
        if (this.mAdjustVelocityTracker == null) {
            this.mAdjustVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleAdjustVelocityTracker() {
        VelocityTracker velocityTracker = this.mAdjustVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mAdjustVelocityTracker = null;
        }
    }

    public void stopScroll() {
        if (this.mScroller.isFinished()) {
            return;
        }
        this.mScroller.abortAnimation();
        stopNestedScroll(1);
        if (this.mScrollToIndex == -1) {
            setScrollState(0);
        }
    }

    private View getBottomView() {
        List<View> effectiveChildren = getEffectiveChildren();
        if (effectiveChildren.isEmpty()) {
            return null;
        }
        return effectiveChildren.get(effectiveChildren.size() - 1);
    }

    private List<View> getNonGoneChildren() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                arrayList.add(childAt);
            }
        }
        return arrayList;
    }

    private List<View> getEffectiveChildren() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8 && childAt.getHeight() > 0) {
                arrayList.add(childAt);
            }
        }
        return arrayList;
    }

    private List<View> getStickyChildren() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8 && isStickyView(childAt)) {
                arrayList.add(childAt);
            }
        }
        return arrayList;
    }

    public boolean isStickyView(View child) {
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            return ((LayoutParams) layoutParams).isSticky;
        }
        return false;
    }

    public boolean isSink(View stickyView) {
        ViewGroup.LayoutParams layoutParams = stickyView.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            return ((LayoutParams) layoutParams).isSink;
        }
        return false;
    }

    private void resetChildren() {
        Iterator<View> it = getNonGoneChildren().iterator();
        while (it.hasNext()) {
            it.next().setTranslationY(0.0f);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0056, code lost:
    
        if (r3 == r1) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0058, code lost:
    
        r4 = r0.get(r3 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0061, code lost:
    
        r0 = r4;
        r4 = r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void resetSticky() {
        /*
            r8 = this;
            java.util.List r0 = r8.getStickyChildren()
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L91
            int r1 = r0.size()
            r2 = 0
            r3 = 0
        L10:
            if (r3 >= r1) goto L1f
            java.lang.Object r4 = r0.get(r3)
            android.view.View r4 = (android.view.View) r4
            r5 = 0
            r4.setTranslationY(r5)
            int r3 = r3 + 1
            goto L10
        L1f:
            boolean r3 = r8.isPermanent
            if (r3 == 0) goto L2b
            r8.clearCurrentStickyView()
            r8.permanentStickyChild(r0)
            goto L97
        L2b:
            r8.clearCurrentStickyViews()
            int r1 = r1 + (-1)
            r3 = r1
        L31:
            r4 = 0
            if (r3 < 0) goto L67
            java.lang.Object r5 = r0.get(r3)
            android.view.View r5 = (android.view.View) r5
            int r6 = r8.getScrollY()
            if (r6 >= 0) goto L4c
            int r7 = r5.getTop()
            int r7 = r7 + r6
            int r6 = r8.getStickyY()
            if (r7 > r6) goto L4c
            goto L56
        L4c:
            int r6 = r5.getTop()
            int r7 = r8.getStickyY()
            if (r6 > r7) goto L64
        L56:
            if (r3 == r1) goto L61
            int r3 = r3 + 1
            java.lang.Object r0 = r0.get(r3)
            r4 = r0
            android.view.View r4 = (android.view.View) r4
        L61:
            r0 = r4
            r4 = r5
            goto L68
        L64:
            int r3 = r3 + (-1)
            goto L31
        L67:
            r0 = r4
        L68:
            android.view.View r1 = r8.mCurrentStickyView
            if (r4 == 0) goto L89
            if (r0 == 0) goto L86
            boolean r3 = r8.isSink(r4)
            if (r3 != 0) goto L86
            int r3 = r4.getHeight()
            int r0 = r0.getTop()
            int r5 = r8.getStickyY()
            int r0 = r0 - r5
            int r3 = r3 - r0
            int r2 = java.lang.Math.max(r2, r3)
        L86:
            r8.stickyChild(r4, r2)
        L89:
            if (r1 == r4) goto L97
            r8.mCurrentStickyView = r4
            r8.stickyChange(r1, r4)
            goto L97
        L91:
            r8.clearCurrentStickyView()
            r8.clearCurrentStickyViews()
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.resetSticky():void");
    }

    private void clearCurrentStickyView() {
        View view = this.mCurrentStickyView;
        if (view != null) {
            this.mCurrentStickyView = null;
            stickyChange(view, null);
        }
    }

    private void clearCurrentStickyViews() {
        if (this.mCurrentStickyViews.isEmpty()) {
            return;
        }
        this.mCurrentStickyViews.clear();
        permanentStickyChange(this.mCurrentStickyViews);
    }

    private void stickyChild(View child, int offset) {
        child.setY(getStickyY() - offset);
        child.setClickable(true);
    }

    private int getStickyY() {
        return getScrollY() + getPaddingTop() + this.mStickyOffset;
    }

    private void permanentStickyChild(List<View> children) {
        this.mTempStickyViews.clear();
        for (int i = 0; i < children.size(); i++) {
            View view = children.get(i);
            if (view.getTop() <= getStickyY() + getPermanentHeight(children, i)) {
                view.setY(getStickyY() + r2);
                view.setClickable(true);
                this.mTempStickyViews.add(view);
            }
        }
        if (isListEqual()) {
            return;
        }
        this.mCurrentStickyViews.clear();
        this.mCurrentStickyViews.addAll(this.mTempStickyViews);
        this.mTempStickyViews.clear();
        permanentStickyChange(this.mCurrentStickyViews);
    }

    private int getPermanentHeight(List<View> children, int currentPosition) {
        int measuredHeight = 0;
        for (int i = 0; i < currentPosition; i++) {
            View view = children.get(i);
            if (!isSink(view)) {
                measuredHeight += view.getMeasuredHeight();
            }
        }
        return measuredHeight;
    }

    private boolean isListEqual() {
        if (this.mTempStickyViews.size() != this.mCurrentStickyViews.size()) {
            return false;
        }
        int size = this.mTempStickyViews.size();
        for (int i = 0; i < size; i++) {
            if (this.mTempStickyViews.get(i) != this.mCurrentStickyViews.get(i)) {
                return false;
            }
        }
        return true;
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    void setScrollState(int state) {
        if (state == this.mScrollState) {
            return;
        }
        this.mScrollState = state;
        int iComputeVerticalScrollOffset = computeVerticalScrollOffset();
        scrollChange(iComputeVerticalScrollOffset, iComputeVerticalScrollOffset);
    }

    public int getOwnScrollY() {
        return computeVerticalScrollOffset();
    }

    public View findFirstVisibleView() {
        int scrollY = getScrollY() + getPaddingTop();
        List<View> effectiveChildren = getEffectiveChildren();
        int size = effectiveChildren.size();
        for (int i = 0; i < size; i++) {
            View view = effectiveChildren.get(i);
            if (view.getTop() <= scrollY && view.getBottom() > scrollY) {
                return view;
            }
        }
        return null;
    }

    public View findLastVisibleView() {
        int height = (getHeight() - getPaddingBottom()) + getScrollY();
        List<View> effectiveChildren = getEffectiveChildren();
        int size = effectiveChildren.size();
        for (int i = 0; i < size; i++) {
            View view = effectiveChildren.get(i);
            if (view.getTop() < height && view.getBottom() >= height) {
                return view;
            }
        }
        return null;
    }

    public boolean isScrollTop() {
        List<View> effectiveChildren = getEffectiveChildren();
        int size = effectiveChildren.size();
        if (size <= 0) {
            return true;
        }
        boolean z = getScrollY() <= 0 && !ScrollUtils.canScrollVertically(effectiveChildren.get(0), -1);
        if (z) {
            for (int i = size - 1; i >= 0; i--) {
                View view = effectiveChildren.get(i);
                if (ScrollUtils.isConsecutiveScrollerChild(view) && ScrollUtils.canScrollVertically(view, -1)) {
                    return false;
                }
            }
        }
        return z;
    }

    public boolean isScrollBottom() {
        List<View> effectiveChildren = getEffectiveChildren();
        int size = effectiveChildren.size();
        if (size <= 0) {
            return true;
        }
        boolean z = getScrollY() >= this.mScrollRange && !ScrollUtils.canScrollVertically(effectiveChildren.get(effectiveChildren.size() - 1), 1);
        if (z) {
            for (int i = size - 1; i >= 0; i--) {
                View view = effectiveChildren.get(i);
                if (ScrollUtils.isConsecutiveScrollerChild(view) && ScrollUtils.canScrollVertically(view, 1)) {
                    return false;
                }
            }
        }
        return z;
    }

    @Override // android.view.View
    public boolean canScrollVertically(int direction) {
        if (direction > 0) {
            return !isScrollBottom();
        }
        return !isScrollTop();
    }

    public void setOnVerticalScrollChangeListener(OnScrollChangeListener l) {
        this.mOnScrollChangeListener = l;
    }

    public OnScrollChangeListener getOnVerticalScrollChangeListener() {
        return this.mOnScrollChangeListener;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        List<View> nonGoneChildren = getNonGoneChildren();
        int size = nonGoneChildren.size();
        int iComputeVerticalScrollRange = 0;
        for (int i = 0; i < size; i++) {
            View view = nonGoneChildren.get(i);
            if (ScrollUtils.isConsecutiveScrollerChild(view) && ScrollUtils.canScrollVertically(view)) {
                View scrolledView = ScrollUtils.getScrolledView(view);
                iComputeVerticalScrollRange += ScrollUtils.computeVerticalScrollRange(scrolledView) + scrolledView.getPaddingTop() + scrolledView.getPaddingBottom();
            } else {
                int height = view.getHeight();
                iComputeVerticalScrollRange += height;
            }
        }
        return iComputeVerticalScrollRange;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        int scrollY = getScrollY();
        List<View> nonGoneChildren = getNonGoneChildren();
        int size = nonGoneChildren.size();
        for (int i = 0; i < size; i++) {
            View view = nonGoneChildren.get(i);
            if (ScrollUtils.isConsecutiveScrollerChild(view)) {
                scrollY += ScrollUtils.computeVerticalScrollOffset(view);
            }
        }
        return scrollY;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private View getTouchTarget(int touchX, int touchY) {
        for (View view : getNonGoneChildren()) {
            if (ScrollUtils.isTouchPointInView(view, touchX, touchY)) {
                return view;
            }
        }
        return null;
    }

    private boolean isIntercept(MotionEvent ev) {
        int iFindPointerIndex = ev.findPointerIndex(this.mActivePointerId);
        if (iFindPointerIndex < 0 || iFindPointerIndex >= ev.getPointerCount()) {
            return true;
        }
        return isIntercept(ScrollUtils.getRawX(this, ev, iFindPointerIndex), ScrollUtils.getRawY(this, ev, iFindPointerIndex));
    }

    private boolean isIntercept(int touchX, int touchY) {
        View touchTarget = getTouchTarget(touchX, touchY);
        if (touchTarget != null) {
            return ScrollUtils.isConsecutiveScrollerChild(touchTarget);
        }
        return false;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public Align align;
        public boolean isConsecutive;
        public boolean isNestedScroll;
        public boolean isSink;
        public boolean isSticky;
        public boolean isTriggerScroll;
        public int scrollChild;

        public enum Align {
            LEFT(1),
            RIGHT(2),
            CENTER(3);

            int value;

            Align(int value) {
                this.value = value;
            }

            static Align get(int value) {
                if (value == 1) {
                    return LEFT;
                }
                if (value == 2) {
                    return RIGHT;
                }
                if (value == 3) {
                    return CENTER;
                }
                return LEFT;
            }
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.isConsecutive = true;
            this.isNestedScroll = true;
            this.isSticky = false;
            this.isTriggerScroll = false;
            this.isSink = false;
            this.scrollChild = -1;
            this.align = Align.LEFT;
            TypedArray typedArrayObtainStyledAttributes = null;
            try {
                try {
                    typedArrayObtainStyledAttributes = c.obtainStyledAttributes(attrs, R.styleable.ConsecutiveScrollerLayout_Layout);
                    this.isConsecutive = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConsecutiveScrollerLayout_Layout_layout_isConsecutive, true);
                    this.isNestedScroll = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConsecutiveScrollerLayout_Layout_layout_isNestedScroll, true);
                    this.isSticky = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConsecutiveScrollerLayout_Layout_layout_isSticky, false);
                    this.isTriggerScroll = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConsecutiveScrollerLayout_Layout_layout_isTriggerScroll, false);
                    this.isSink = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConsecutiveScrollerLayout_Layout_layout_isSink, false);
                    this.align = Align.get(typedArrayObtainStyledAttributes.getInt(R.styleable.ConsecutiveScrollerLayout_Layout_layout_align, 1));
                    this.scrollChild = typedArrayObtainStyledAttributes.getResourceId(R.styleable.ConsecutiveScrollerLayout_Layout_layout_scrollChild, -1);
                    if (typedArrayObtainStyledAttributes == null) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (typedArrayObtainStyledAttributes == null) {
                        return;
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            } catch (Throwable th) {
                if (typedArrayObtainStyledAttributes != null) {
                    typedArrayObtainStyledAttributes.recycle();
                }
                throw th;
            }
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.isConsecutive = true;
            this.isNestedScroll = true;
            this.isSticky = false;
            this.isTriggerScroll = false;
            this.isSink = false;
            this.scrollChild = -1;
            this.align = Align.LEFT;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.isConsecutive = true;
            this.isNestedScroll = true;
            this.isSticky = false;
            this.isTriggerScroll = false;
            this.isSink = false;
            this.scrollChild = -1;
            this.align = Align.LEFT;
        }
    }

    public void scrollToChild(View view) {
        scrollToChildWithOffset(view, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void scrollToChildWithOffset(android.view.View r7, int r8) {
        /*
            r6 = this;
            int r0 = r6.indexOfChild(r7)
            r1 = -1
            if (r0 == r1) goto L77
            int r2 = r7.getTop()
            int r2 = r2 - r8
            int r3 = r6.getAdjustHeightForChild(r7)
            int r2 = r2 - r3
            r3 = 1
            if (r8 < 0) goto L33
            int r4 = r6.getScrollY()
            int r5 = r6.getPaddingTop()
            int r4 = r4 + r5
            if (r4 <= r2) goto L20
            goto L43
        L20:
            int r4 = r6.getScrollY()
            int r5 = r6.getPaddingTop()
            int r4 = r4 + r5
            if (r4 >= r2) goto L2c
            goto L51
        L2c:
            boolean r7 = com.donkingliang.consecutivescroller.ScrollUtils.canScrollVertically(r7, r1)
            if (r7 == 0) goto L53
            goto L43
        L33:
            int r7 = r6.getViewsScrollOffset(r0)
            int r4 = r6.getScrollY()
            int r5 = r6.getPaddingTop()
            int r4 = r4 + r5
            int r4 = r4 + r7
            if (r4 <= r2) goto L45
        L43:
            r7 = -1
            goto L54
        L45:
            int r4 = r6.getScrollY()
            int r5 = r6.getPaddingTop()
            int r4 = r4 + r5
            int r4 = r4 + r7
            if (r4 >= r2) goto L53
        L51:
            r7 = 1
            goto L54
        L53:
            r7 = 0
        L54:
            if (r7 == 0) goto L77
            r6.mScrollToIndex = r0
            r6.stopScroll()
            r6.mScrollToIndexWithOffset = r8
            r8 = 2
            r6.setScrollState(r8)
        L61:
            if (r7 >= 0) goto L69
            r8 = -200(0xffffffffffffff38, float:NaN)
            r6.dispatchScroll(r8)
            goto L6e
        L69:
            r8 = 200(0xc8, float:2.8E-43)
            r6.dispatchScroll(r8)
        L6e:
            int r8 = r6.mCycleCount
            int r8 = r8 + r3
            r6.mCycleCount = r8
            int r8 = r6.mScrollToIndex
            if (r8 != r1) goto L61
        L77:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.scrollToChildWithOffset(android.view.View, int):void");
    }

    public void smoothScrollToChild(View view) {
        smoothScrollToChildWithOffset(view, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void smoothScrollToChildWithOffset(android.view.View r7, int r8) {
        /*
            r6 = this;
            int r0 = r6.indexOfChild(r7)
            r1 = -1
            if (r0 == r1) goto L6e
            int r2 = r7.getTop()
            int r2 = r2 - r8
            int r3 = r6.getAdjustHeightForChild(r7)
            int r2 = r2 - r3
            r3 = 1
            if (r8 < 0) goto L33
            int r4 = r6.getScrollY()
            int r5 = r6.getPaddingTop()
            int r4 = r4 + r5
            if (r4 <= r2) goto L20
            goto L53
        L20:
            int r4 = r6.getScrollY()
            int r5 = r6.getPaddingTop()
            int r4 = r4 + r5
            if (r4 >= r2) goto L2c
            goto L50
        L2c:
            boolean r7 = com.donkingliang.consecutivescroller.ScrollUtils.canScrollVertically(r7, r1)
            if (r7 == 0) goto L52
            goto L53
        L33:
            int r7 = r6.getViewsScrollOffset(r0)
            int r4 = r6.getScrollY()
            int r5 = r6.getPaddingTop()
            int r4 = r4 + r5
            int r4 = r4 + r7
            if (r4 <= r2) goto L44
            goto L53
        L44:
            int r1 = r6.getScrollY()
            int r4 = r6.getPaddingTop()
            int r1 = r1 + r4
            int r1 = r1 + r7
            if (r1 >= r2) goto L52
        L50:
            r1 = 1
            goto L53
        L52:
            r1 = 0
        L53:
            if (r1 == 0) goto L6e
            r6.mScrollToIndex = r0
            r6.stopScroll()
            r6.mScrollToIndexWithOffset = r8
            r7 = 2
            r6.setScrollState(r7)
            if (r1 >= 0) goto L67
            r7 = -50
            r6.mSmoothScrollOffset = r7
            goto L6b
        L67:
            r7 = 50
            r6.mSmoothScrollOffset = r7
        L6b:
            r6.invalidate()
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout.smoothScrollToChildWithOffset(android.view.View, int):void");
    }

    private int getViewsScrollOffset(int index) {
        int childCount = getChildCount();
        int iComputeVerticalScrollOffset = 0;
        while (index < childCount) {
            View childAt = getChildAt(index);
            if (childAt.getVisibility() != 8 && ScrollUtils.isConsecutiveScrollerChild(childAt)) {
                iComputeVerticalScrollOffset += ScrollUtils.computeVerticalScrollOffset(childAt);
            }
            index++;
        }
        return iComputeVerticalScrollOffset;
    }

    public boolean isAutoAdjustHeightAtBottomView() {
        return this.mAutoAdjustHeightAtBottomView;
    }

    public void setAutoAdjustHeightAtBottomView(boolean autoAdjustHeightAtBottomView) {
        if (this.mAutoAdjustHeightAtBottomView != autoAdjustHeightAtBottomView) {
            this.mAutoAdjustHeightAtBottomView = autoAdjustHeightAtBottomView;
            requestLayout();
        }
    }

    public int getAdjustHeightOffset() {
        return this.mAdjustHeightOffset;
    }

    public void setAdjustHeightOffset(int adjustHeightOffset) {
        if (this.mAdjustHeightOffset != adjustHeightOffset) {
            this.mAdjustHeightOffset = adjustHeightOffset;
            requestLayout();
        }
    }

    public void setPermanent(boolean isPermanent) {
        if (this.isPermanent != isPermanent) {
            this.isPermanent = isPermanent;
            if (this.mAutoAdjustHeightAtBottomView) {
                requestLayout();
            } else {
                resetSticky();
            }
        }
    }

    public boolean isPermanent() {
        return this.isPermanent;
    }

    public boolean isDisableChildHorizontalScroll() {
        return this.disableChildHorizontalScroll;
    }

    public void setDisableChildHorizontalScroll(boolean disableChildHorizontalScroll) {
        this.disableChildHorizontalScroll = disableChildHorizontalScroll;
    }

    public void setStickyOffset(int offset) {
        if (this.mStickyOffset != offset) {
            this.mStickyOffset = offset;
            resetSticky();
        }
    }

    public int getStickyOffset() {
        return this.mStickyOffset;
    }

    public View getCurrentStickyView() {
        return this.mCurrentStickyView;
    }

    public List<View> getCurrentStickyViews() {
        return this.mCurrentStickyViews;
    }

    public boolean theChildIsStick(View child) {
        boolean z = this.isPermanent;
        return (!z && this.mCurrentStickyView == child) || (z && this.mCurrentStickyViews.contains(child));
    }

    public OnStickyChangeListener getOnStickyChangeListener() {
        return this.mOnStickyChangeListener;
    }

    public void setOnStickyChangeListener(OnStickyChangeListener l) {
        this.mOnStickyChangeListener = l;
    }

    public OnPermanentStickyChangeListener getOnPermanentStickyChangeListener() {
        return this.mOnPermanentStickyChangeListener;
    }

    public void setOnPermanentStickyChangeListener(OnPermanentStickyChangeListener l) {
        this.mOnPermanentStickyChangeListener = l;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean enabled) {
        this.mChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return this.mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return this.mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    public boolean startNestedScroll(int axes, int type) {
        return this.mChildHelper.startNestedScroll(axes, type);
    }

    public void stopNestedScroll(int type) {
        this.mChildHelper.stopNestedScroll(type);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public boolean hasNestedScrollingParent(int type) {
        return this.mChildHelper.hasNestedScrollingParent(type);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return this.mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
        return this.mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, 0);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
        return this.mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    public boolean onStartNestedScroll(View child, View target, int axes, int type) {
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        return (layoutParams instanceof LayoutParams ? ((LayoutParams) layoutParams).isNestedScroll : false) && (axes & 2) != 0;
    }

    public void onNestedScrollAccepted(View child, View target, int axes, int type) {
        this.mParentHelper.onNestedScrollAccepted(child, target, axes, type);
        checkTargetsScroll(false, false);
        startNestedScroll(2, type);
        interceptAnimatorByAction(0);
    }

    public void onStopNestedScroll(View target, int type) {
        this.mParentHelper.onStopNestedScroll(target, type);
        stopNestedScroll(type);
        overSpinner();
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        onNestedScrollInternal(dyUnconsumed, type);
    }

    private void onNestedScrollInternal(int dyUnconsumed, int type) {
        int i = this.mSecondScrollY;
        dispatchScroll(dyUnconsumed);
        int i2 = this.mSecondScrollY - i;
        this.mChildHelper.dispatchNestedScroll(0, i2, 0, dyUnconsumed - i2, (int[]) null, type);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return onStartNestedScroll(child, target, nestedScrollAxes, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        onNestedScrollAccepted(child, target, nestedScrollAxes, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View target) {
        onStopNestedScroll(target, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        onNestedScrollInternal(dyUnconsumed, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        onNestedPreScroll(target, dx, dy, consumed, 0);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed, int type) {
        dispatchNestedPreScroll(dx, dy, consumed, null, type);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        if (consumed) {
            return false;
        }
        dispatchNestedFling(0.0f, velocityY, true);
        fling((int) velocityY);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return dispatchNestedPreFling(velocityX, velocityY);
    }

    public boolean isEnableOverDragMode() {
        return this.overDragMode || this.overDragMaxDistanceOfTop > 0 || this.overDragMaxDistanceOfBottom > 0;
    }

    public void enableOverDragMode(boolean enable) {
        this.overDragMode = enable;
        if (enable) {
            int iDp2px = Util.dp2px(180.0f);
            if (this.overDragMaxDistanceOfTop <= 0) {
                this.overDragMaxDistanceOfTop = iDp2px;
            }
            if (this.overDragMaxDistanceOfBottom <= 0) {
                this.overDragMaxDistanceOfBottom = iDp2px;
                return;
            }
            return;
        }
        this.overDragMaxDistanceOfTop = 0;
        this.overDragMaxDistanceOfBottom = 0;
    }

    public void enableOverDragMode(boolean enable, int topDistance, int bottomDistance) {
        this.overDragMode = enable;
        if (enable) {
            this.overDragMaxDistanceOfTop = topDistance;
            this.overDragMaxDistanceOfBottom = bottomDistance;
        } else {
            this.overDragMaxDistanceOfTop = 0;
            this.overDragMaxDistanceOfBottom = 0;
        }
    }

    public void setOverDragMaxDistanceOfTop(int distance) {
        if (!isEnableOverDragMode()) {
            enableOverDragMode(true, distance, this.overDragMaxDistanceOfBottom);
        } else {
            this.overDragMaxDistanceOfTop = distance;
        }
    }

    public void setOverDragMaxDistanceOfBottom(int distance) {
        if (!isEnableOverDragMode()) {
            enableOverDragMode(true, this.overDragMaxDistanceOfTop, distance);
        } else {
            this.overDragMaxDistanceOfBottom = distance;
        }
    }

    public void setOverDragRate(float rate) {
        this.mDragRate = rate;
    }
}
