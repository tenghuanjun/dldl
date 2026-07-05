package com.huya.berry.gamesdk.widgets;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MarqueeTextView extends ComnTextView {
    private static final int FIRST_SCROLL_DELAY_DEFAULT = 500;
    public static final int SCROLL_FOREVER = 100;
    public static final int SCROLL_ONCE = 101;
    private static final float SCROLL_SPEED = 0.1f;
    private static final String TAG = MarqueeTextView.class.getSimpleName();
    private int mAllDuration;
    private int mDistance;
    private int mDuration;
    private boolean mFirst;
    private int mFirstScrollDelay;
    private boolean mPaused;
    private Runnable mRun;
    private int mScrollMode;
    private Scroller mScroller;
    private float mSpeed;
    private float mStopInput;
    private int mStopScrollDelay;
    private int mViewWidth;
    private int mXPaused;

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mXPaused = 0;
        this.mPaused = true;
        this.mFirst = true;
        this.mScrollMode = 101;
        this.mFirstScrollDelay = FIRST_SCROLL_DELAY_DEFAULT;
        this.mStopScrollDelay = 0;
        this.mDuration = 0;
        this.mAllDuration = 0;
        this.mStopInput = 1.0f;
        this.mSpeed = SCROLL_SPEED;
        this.mRun = new Runnable() { // from class: com.huya.berry.gamesdk.widgets.MarqueeTextView.1
            @Override // java.lang.Runnable
            public void run() {
                MarqueeTextView.this.mScroller.startScroll(MarqueeTextView.this.mXPaused, 0, MarqueeTextView.this.mDistance, 0, MarqueeTextView.this.mDuration);
                MarqueeTextView.this.invalidate();
                MarqueeTextView.this.mPaused = false;
            }
        };
        this.mViewWidth = 0;
        initView(context, attributeSet, i);
    }

    private void initView(Context context, AttributeSet attributeSet, int i) {
        this.mScrollMode = 100;
        this.mFirstScrollDelay = FIRST_SCROLL_DELAY_DEFAULT;
        this.mStopScrollDelay = FIRST_SCROLL_DELAY_DEFAULT;
        this.mSpeed = SCROLL_SPEED;
        setLines(1);
        setMaxLines(1);
        setSingleLine();
        setEllipsize(null);
        setHorizontallyScrolling(true);
    }

    public void setScrollConfig(int i, int i2, int i3, int i4) {
        this.mScrollMode = i;
        this.mFirstScrollDelay = i2;
        this.mStopScrollDelay = i3;
        this.mSpeed = i4;
    }

    @Override // com.huya.berry.gamesdk.widgets.ComnTextView
    public void setComnText(CharSequence charSequence) {
        setText(charSequence);
        this.mXPaused = 0;
        removeCallbacks(this.mRun);
        this.mPaused = true;
        this.mFirst = true;
        resumeScroll();
    }

    public int getScrollDuration() {
        return this.mAllDuration;
    }

    public void startScroll() {
        this.mXPaused = 0;
        this.mPaused = true;
        this.mFirst = true;
        resumeScroll();
    }

    public void resumeScroll() {
        int width;
        Scroller scroller = this.mScroller;
        if (scroller == null) {
            Scroller scroller2 = new Scroller(getContext(), new LinearInterpolator() { // from class: com.huya.berry.gamesdk.widgets.MarqueeTextView.2
                @Override // android.view.animation.LinearInterpolator, android.animation.TimeInterpolator
                public float getInterpolation(float f) {
                    if (f > MarqueeTextView.this.mStopInput) {
                        return 1.0f;
                    }
                    return f / MarqueeTextView.this.mStopInput;
                }
            });
            this.mScroller = scroller2;
            setScroller(scroller2);
        } else if (scroller.getCurrX() != 0) {
            this.mScroller.startScroll(0, 0, 0, 0);
        }
        int iCalculateScrollingLen = calculateScrollingLen();
        if (this.mViewWidth == 0) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams.width == -2) {
                width = getMaxWidth();
                if (width == 0) {
                    width = getWidth();
                }
            } else if (layoutParams.width > 0) {
                width = layoutParams.width;
            } else {
                width = getWidth();
            }
            this.mViewWidth = (width - getPaddingStart()) - getPaddingEnd();
        }
        if (this.mViewWidth >= iCalculateScrollingLen) {
            this.mDuration = 0;
            return;
        }
        int i = iCalculateScrollingLen - this.mXPaused;
        this.mDistance = i;
        int iIntValue = Double.valueOf((((double) i) * 1.0d) / ((double) this.mSpeed)).intValue() + this.mStopScrollDelay;
        this.mDuration = iIntValue;
        this.mStopInput = ((iIntValue - r2) * 1.0f) / iIntValue;
        int i2 = this.mFirstScrollDelay;
        this.mAllDuration = iIntValue + i2;
        if (this.mFirst) {
            postDelayed(this.mRun, i2);
            return;
        }
        this.mScroller.startScroll(this.mXPaused, 0, this.mDistance, 0, iIntValue);
        invalidate();
        this.mPaused = false;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (getLayoutParams().width == -2) {
            this.mViewWidth = (getMaxWidth() - getPaddingStart()) - getPaddingEnd();
        } else {
            this.mViewWidth = (i - getPaddingStart()) - getPaddingEnd();
        }
    }

    public void pauseScroll() {
        Scroller scroller = this.mScroller;
        if (scroller == null || this.mPaused) {
            return;
        }
        this.mPaused = true;
        this.mXPaused = scroller.getCurrX();
        this.mScroller.abortAnimation();
    }

    public void stopScroll() {
        if (this.mScroller == null) {
            return;
        }
        this.mPaused = true;
    }

    private int calculateScrollingLen() {
        return (int) Layout.getDesiredWidth(getText(), getPaint());
    }

    @Override // android.widget.TextView, android.view.View
    public void computeScroll() {
        super.computeScroll();
        Scroller scroller = this.mScroller;
        if (scroller == null || !scroller.isFinished() || this.mPaused) {
            return;
        }
        if (this.mScrollMode == 101) {
            stopScroll();
            return;
        }
        this.mPaused = true;
        this.mXPaused = 0;
        this.mFirst = false;
        resumeScroll();
    }

    public void setScrollSpeed(int i) {
        if (i == 0) {
            return;
        }
        this.mSpeed = 1.0f / i;
    }

    public void setScrollMode(int i) {
        this.mScrollMode = i;
    }

    public int getScrollMode() {
        return this.mScrollMode;
    }

    public void setScrollFirstDelay(int i) {
        this.mFirstScrollDelay = i;
    }

    public int getScrollFirstDelay() {
        return this.mFirstScrollDelay;
    }

    public boolean isPaused() {
        return this.mPaused;
    }
}
