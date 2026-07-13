package com.cy.yyjia.zhe28.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes3.dex */
public class ExpandableTextView extends LinearLayout implements View.OnClickListener {
    private static final int DEFAULT_ANIM_DURATION = 300;
    private static final float DEFAULT_CONTENT_TEXT_LINE_SPACING_MULTIPLIER = 1.0f;
    private static final int DEFAULT_CONTENT_TEXT_SIZE = 14;
    private static final int MAX_COLLAPSED_LINES = 4;
    private static final int STATE_TV_GRAVITY_CENTER = 1;
    private static final int STATE_TV_GRAVITY_LEFT = 0;
    private static final int STATE_TV_GRAVITY_RIGHT = 2;
    private boolean mAnimating;
    private int mAnimationDuration;
    private boolean mCollapsed;
    private int mCollapsedHeight;
    private SparseBooleanArray mCollapsedStatus;
    private String mCollapsedString;
    private float mContentLineSpacingMultiplier;
    private int mContentTextColor;
    private float mContentTextSize;
    private String mExpandString;
    private OnExpandStateChangeListener mListener;
    private int mMarginBetweenTxtAndBottom;
    private int mMaxCollapsedLines;
    private int mPosition;
    private boolean mRelayout;
    private Runnable mRunnable;
    private int mStateTextColor;
    protected TextView mStateTv;
    private int mStateTvGravity;
    private int mTextHeightWithMaxLines;
    protected TextView mTv;

    public interface OnExpandStateChangeListener {
        void onExpandStateChanged(TextView textView, boolean isExpanded);
    }

    private static boolean isPostLolipop() {
        return true;
    }

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCollapsed = true;
        this.mRunnable = new Runnable() { // from class: com.cy.yyjia.zhe28.view.ExpandableTextView.1
            @Override // java.lang.Runnable
            public void run() {
                ExpandableTextView expandableTextView = ExpandableTextView.this;
                expandableTextView.mMarginBetweenTxtAndBottom = expandableTextView.getHeight() - ExpandableTextView.this.mTv.getHeight();
            }
        };
        init(context, attrs);
    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCollapsed = true;
        this.mRunnable = new Runnable() { // from class: com.cy.yyjia.zhe28.view.ExpandableTextView.1
            @Override // java.lang.Runnable
            public void run() {
                ExpandableTextView expandableTextView = ExpandableTextView.this;
                expandableTextView.mMarginBetweenTxtAndBottom = expandableTextView.getHeight() - ExpandableTextView.this.mTv.getHeight();
            }
        };
        init(context, attrs);
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int orientation) {
        if (orientation == 0) {
            throw new IllegalArgumentException("ExpandableTextView only supports Vertical Orientation.");
        }
        super.setOrientation(orientation);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ExpandCollapseAnimation expandCollapseAnimation;
        if (this.mStateTv.getVisibility() != 0) {
            return;
        }
        boolean z = this.mCollapsed;
        this.mCollapsed = !z;
        this.mStateTv.setText(!z ? this.mExpandString : this.mCollapsedString);
        SparseBooleanArray sparseBooleanArray = this.mCollapsedStatus;
        if (sparseBooleanArray != null) {
            sparseBooleanArray.put(this.mPosition, this.mCollapsed);
        }
        this.mAnimating = true;
        if (this.mCollapsed) {
            expandCollapseAnimation = new ExpandCollapseAnimation(this, getHeight(), this.mCollapsedHeight);
        } else {
            expandCollapseAnimation = new ExpandCollapseAnimation(this, getHeight(), (getHeight() + this.mTextHeightWithMaxLines) - this.mTv.getHeight());
        }
        expandCollapseAnimation.setFillAfter(true);
        expandCollapseAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.cy.yyjia.zhe28.view.ExpandableTextView.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                ExpandableTextView.this.clearAnimation();
                ExpandableTextView.this.mAnimating = false;
                if (ExpandableTextView.this.mListener != null) {
                    ExpandableTextView.this.mListener.onExpandStateChanged(ExpandableTextView.this.mTv, !ExpandableTextView.this.mCollapsed);
                }
            }
        });
        clearAnimation();
        startAnimation(expandCollapseAnimation);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.mAnimating;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        findViews();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!this.mRelayout || getVisibility() == 8) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        this.mRelayout = false;
        this.mStateTv.setVisibility(8);
        this.mTv.setMaxLines(Integer.MAX_VALUE);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mTv.getLineCount() <= this.mMaxCollapsedLines) {
            return;
        }
        this.mTextHeightWithMaxLines = getRealTextViewHeight(this.mTv);
        if (this.mCollapsed) {
            this.mTv.setMaxLines(this.mMaxCollapsedLines);
        }
        this.mStateTv.setVisibility(0);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mCollapsed) {
            this.mTv.post(this.mRunnable);
            this.mCollapsedHeight = getMeasuredHeight();
        }
    }

    public void setOnExpandStateChangeListener(OnExpandStateChangeListener listener) {
        this.mListener = listener;
    }

    public void setExpandableTextContent(CharSequence text) {
        this.mRelayout = true;
        this.mTv.setText(text);
        setVisibility(TextUtils.isEmpty(text) ? 8 : 0);
    }

    public void setText(CharSequence text, SparseBooleanArray collapsedStatus, int position) {
        this.mCollapsedStatus = collapsedStatus;
        this.mPosition = position;
        boolean z = collapsedStatus.get(position, true);
        clearAnimation();
        this.mCollapsed = z;
        this.mStateTv.setText(z ? this.mExpandString : this.mCollapsedString);
        setExpandableTextContent(text);
        getLayoutParams().height = -2;
        requestLayout();
    }

    public CharSequence getText() {
        TextView textView = this.mTv;
        if (textView == null) {
            return "";
        }
        return textView.getText();
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.wancms_layout_expand_text, (ViewGroup) this, true);
        setOrientation(1);
        setVisibility(8);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        this.mMaxCollapsedLines = typedArrayObtainStyledAttributes.getInt(2, 4);
        this.mAnimationDuration = 300;
        this.mContentTextSize = typedArrayObtainStyledAttributes.getDimension(3, context.getResources().getDimensionPixelSize(R.dimen.dp_14));
        this.mContentLineSpacingMultiplier = 1.0f;
        this.mContentTextColor = typedArrayObtainStyledAttributes.getColor(0, context.getResources().getColor(R.color.color_text_1));
        this.mStateTvGravity = 2;
        this.mStateTextColor = context.getResources().getColor(R.color.colorPrimary);
        typedArrayObtainStyledAttributes.recycle();
        if (this.mExpandString == null) {
            this.mExpandString = "展开";
        }
        if (this.mCollapsedString == null) {
            this.mCollapsedString = "收起";
        }
    }

    private void findViews() {
        TextView textView = (TextView) findViewById(R.id.expandable_text);
        this.mTv = textView;
        textView.setTextColor(this.mContentTextColor);
        this.mTv.setTextSize(0, this.mContentTextSize);
        this.mTv.setLineSpacing(0.0f, this.mContentLineSpacingMultiplier);
        this.mStateTv = (TextView) findViewById(R.id.expand_collapse);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int i = this.mStateTvGravity;
        if (i == 0) {
            layoutParams.gravity = GravityCompat.START;
        } else if (i == 1) {
            layoutParams.gravity = 1;
        } else if (i == 2) {
            layoutParams.gravity = GravityCompat.END;
        }
        this.mStateTv.setLayoutParams(layoutParams);
        this.mStateTv.setText(this.mCollapsed ? this.mExpandString : this.mCollapsedString);
        this.mStateTv.setTextColor(this.mStateTextColor);
        this.mStateTv.setCompoundDrawablePadding(10);
        this.mStateTv.setOnClickListener(this);
    }

    private static Drawable getDrawable(Context context, int resId) {
        Resources resources = context.getResources();
        if (isPostLolipop()) {
            return resources.getDrawable(resId, context.getTheme());
        }
        return resources.getDrawable(resId);
    }

    private static int getRealTextViewHeight(TextView textView) {
        return textView.getLayout().getLineTop(textView.getLineCount()) + textView.getCompoundPaddingTop() + textView.getCompoundPaddingBottom();
    }

    class ExpandCollapseAnimation extends Animation {
        private final int mEndHeight;
        private final int mStartHeight;
        private final View mTargetView;

        @Override // android.view.animation.Animation
        public boolean willChangeBounds() {
            return true;
        }

        public ExpandCollapseAnimation(View view, int startHeight, int endHeight) {
            this.mTargetView = view;
            this.mStartHeight = startHeight;
            this.mEndHeight = endHeight;
            setDuration(ExpandableTextView.this.mAnimationDuration);
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int i = this.mEndHeight;
            int i2 = (int) (((i - r0) * interpolatedTime) + this.mStartHeight);
            ExpandableTextView.this.mTv.setMaxHeight(i2 - ExpandableTextView.this.mMarginBetweenTxtAndBottom);
            this.mTargetView.getLayoutParams().height = i2;
            this.mTargetView.requestLayout();
        }

        @Override // android.view.animation.Animation
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }
    }
}
