package com.cy.yyjia.zhe28.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.Util;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class RecyclerViewScrollBar extends View {
    private static final int SCROLL_LOCATION_END = 3;
    private static final int SCROLL_LOCATION_MIDDLE = 2;
    private static final int SCROLL_LOCATION_START = 1;
    private static final String TAG = "RecyclerViewScrollBar";
    private int mHeight;
    private final Paint mPaint;
    private RecyclerView mRecyclerView;
    private final RecyclerView.OnScrollListener mScrollListener;
    private int mScrollLocation;
    private float mScrollScale;
    private int mThumbColor;
    private final RectF mThumbRectF;
    private float mThumbScale;
    private int mTrackColor;
    private final RectF mTrackRectF;
    private int mWidth;
    private OnTransformersScrollListener onTransformersScrollListener;
    private float radius;
    private boolean scrollBySelf;

    public interface OnTransformersScrollListener {
        void onScrollStateChanged(RecyclerView recyclerView, int newState);

        void onScrolled(RecyclerView recyclerView, int dx, int dy);
    }

    public RecyclerViewScrollBar(Context context) {
        this(context, null);
    }

    public RecyclerViewScrollBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerViewScrollBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mPaint = new Paint();
        this.mTrackRectF = new RectF();
        this.mThumbRectF = new RectF();
        this.mThumbScale = 0.0f;
        this.mScrollScale = 0.0f;
        this.mScrollLocation = 1;
        this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: com.cy.yyjia.zhe28.view.RecyclerViewScrollBar.1
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (RecyclerViewScrollBar.this.onTransformersScrollListener != null) {
                    RecyclerViewScrollBar.this.onTransformersScrollListener.onScrollStateChanged(recyclerView, newState);
                }
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                RecyclerViewScrollBar.this.computeScrollScale();
                if (RecyclerViewScrollBar.this.scrollBySelf && RecyclerViewScrollBar.this.mRecyclerView.getScrollState() == 0) {
                    onScrollStateChanged(recyclerView, 0);
                    RecyclerViewScrollBar.this.scrollBySelf = false;
                }
                if (RecyclerViewScrollBar.this.onTransformersScrollListener != null) {
                    RecyclerViewScrollBar.this.onTransformersScrollListener.onScrolled(recyclerView, dx, dy);
                }
            }
        };
        init(context, attrs);
    }

    public RecyclerViewScrollBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mPaint = new Paint();
        this.mTrackRectF = new RectF();
        this.mThumbRectF = new RectF();
        this.mThumbScale = 0.0f;
        this.mScrollScale = 0.0f;
        this.mScrollLocation = 1;
        this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: com.cy.yyjia.zhe28.view.RecyclerViewScrollBar.1
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (RecyclerViewScrollBar.this.onTransformersScrollListener != null) {
                    RecyclerViewScrollBar.this.onTransformersScrollListener.onScrollStateChanged(recyclerView, newState);
                }
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                RecyclerViewScrollBar.this.computeScrollScale();
                if (RecyclerViewScrollBar.this.scrollBySelf && RecyclerViewScrollBar.this.mRecyclerView.getScrollState() == 0) {
                    onScrollStateChanged(recyclerView, 0);
                    RecyclerViewScrollBar.this.scrollBySelf = false;
                }
                if (RecyclerViewScrollBar.this.onTransformersScrollListener != null) {
                    RecyclerViewScrollBar.this.onTransformersScrollListener.onScrolled(recyclerView, dx, dy);
                }
            }
        };
        init(context, attrs);
    }

    public void setScrollBySelf(boolean bySelf) {
        this.scrollBySelf = bySelf;
    }

    public void setOnTransformersScrollListener(OnTransformersScrollListener listener) {
        this.onTransformersScrollListener = listener;
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.RecyclerViewScrollBar);
        this.mTrackColor = getResources().getColor(typedArrayObtainStyledAttributes.getResourceId(2, 2131099705));
        this.mThumbColor = getResources().getColor(typedArrayObtainStyledAttributes.getResourceId(2, com.mobile.auth.R.color.colorPrimary));
        this.radius = typedArrayObtainStyledAttributes.getResourceId(0, Util.dpToPx(context, 10.0f));
        typedArrayObtainStyledAttributes.recycle();
        initPaint();
    }

    private void initPaint() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    public void attachRecyclerView(RecyclerView recyclerView) {
        if (this.mRecyclerView == recyclerView) {
            return;
        }
        this.mRecyclerView = recyclerView;
        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(this.mScrollListener);
            this.mRecyclerView.addOnScrollListener(this.mScrollListener);
            this.mRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.cy.yyjia.zhe28.view.RecyclerViewScrollBar.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    RecyclerViewScrollBar.this.mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                    RecyclerViewScrollBar.this.computeScrollScale();
                    return true;
                }
            });
        }
    }

    public RecyclerViewScrollBar setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public RecyclerViewScrollBar setTrackColor(int color) {
        this.mTrackColor = color;
        return this;
    }

    public RecyclerViewScrollBar setThumbColor(int color) {
        this.mThumbColor = color;
        return this;
    }

    public void applyChange() {
        postInvalidate();
    }

    public void computeScrollScale() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            return;
        }
        float fComputeHorizontalScrollExtent = recyclerView.computeHorizontalScrollExtent();
        float fComputeHorizontalScrollRange = this.mRecyclerView.computeHorizontalScrollRange();
        if (fComputeHorizontalScrollRange != 0.0f) {
            this.mThumbScale = fComputeHorizontalScrollExtent / fComputeHorizontalScrollRange;
        }
        float f = fComputeHorizontalScrollRange - fComputeHorizontalScrollExtent;
        float fComputeHorizontalScrollOffset = this.mRecyclerView.computeHorizontalScrollOffset();
        if (fComputeHorizontalScrollRange != 0.0f) {
            this.mScrollScale = fComputeHorizontalScrollOffset / fComputeHorizontalScrollRange;
        }
        if (fComputeHorizontalScrollOffset == 0.0f) {
            this.mScrollLocation = 1;
        } else if (f == fComputeHorizontalScrollOffset) {
            this.mScrollLocation = 3;
        } else {
            this.mScrollLocation = 2;
        }
        postInvalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        this.mHeight = View.MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTrack(canvas);
        drawThumb(canvas);
    }

    private void drawThumb(Canvas canvas) {
        initPaint();
        this.mPaint.setColor(this.mThumbColor);
        float f = this.mScrollScale;
        int i = this.mWidth;
        float f2 = f * i;
        float f3 = (i * this.mThumbScale) + f2;
        int i2 = this.mScrollLocation;
        if (i2 == 1) {
            this.mThumbRectF.set(0.0f, 0.0f, f3, this.mHeight);
        } else if (i2 == 2) {
            this.mThumbRectF.set(f2, 0.0f, f3, this.mHeight);
        } else if (i2 == 3) {
            this.mThumbRectF.set(f2, 0.0f, i, this.mHeight);
        }
        RectF rectF = this.mThumbRectF;
        float f4 = this.radius;
        canvas.drawRoundRect(rectF, f4, f4, this.mPaint);
    }

    private void drawTrack(Canvas canvas) {
        initPaint();
        this.mPaint.setColor(this.mTrackColor);
        this.mTrackRectF.set(0.0f, 0.0f, this.mWidth, this.mHeight);
        RectF rectF = this.mTrackRectF;
        float f = this.radius;
        canvas.drawRoundRect(rectF, f, f, this.mPaint);
    }
}
