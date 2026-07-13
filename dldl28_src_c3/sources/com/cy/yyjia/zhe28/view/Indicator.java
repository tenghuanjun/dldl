package com.cy.yyjia.zhe28.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class Indicator extends LinearLayout {
    private int INDICATOR_NORMAL_RES;
    private int INDICATOR_SELECTED_RES;
    private int LAST_SELECT_INDEX;
    private int indicatorMargin;
    private Context mContext;
    private int size;

    public Indicator(Context context) {
        super(context);
        this.LAST_SELECT_INDEX = 0;
        this.size = 0;
        init(context, null);
    }

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.LAST_SELECT_INDEX = 0;
        this.size = 0;
        init(context, attrs);
    }

    public Indicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.LAST_SELECT_INDEX = 0;
        this.size = 0;
        init(context, attrs);
    }

    public Indicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.LAST_SELECT_INDEX = 0;
        this.size = 0;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.Indicator);
        this.INDICATOR_SELECTED_RES = typedArrayObtainStyledAttributes.getResourceId(3, 2131624512);
        this.INDICATOR_NORMAL_RES = typedArrayObtainStyledAttributes.getResourceId(2, 2131624511);
        this.indicatorMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mContext.getResources().getDimensionPixelSize(2131165332));
        setIndicatorLength(typedArrayObtainStyledAttributes.getInteger(0, 0));
        typedArrayObtainStyledAttributes.recycle();
        setPadding(getResources().getDimensionPixelSize(2131165333), getResources().getDimensionPixelSize(2131165338), getResources().getDimensionPixelSize(2131165333), getResources().getDimensionPixelSize(2131165338));
    }

    public void setSelectedIndicator(int INDICATOR_SELECTED_RES) {
        this.INDICATOR_SELECTED_RES = INDICATOR_SELECTED_RES;
    }

    public void setNormalIndicator(int INDICATOR_NORMAL_RES) {
        this.INDICATOR_NORMAL_RES = INDICATOR_NORMAL_RES;
    }

    public void setIndicatorMargin(int indicatorMargin) {
        this.indicatorMargin = indicatorMargin;
    }

    public void setIndicatorLength(int size) {
        this.size = size;
        if (getChildCount() != 0) {
            removeAllViewsInLayout();
        }
        for (int i = 0; i < size; i++) {
            addIndicator();
        }
        if (getChildCount() != 0) {
            select(0);
        }
    }

    public void select(int index) {
        ((ImageView) getChildAt(this.LAST_SELECT_INDEX)).setImageResource(this.INDICATOR_NORMAL_RES);
        ((ImageView) getChildAt(index)).setImageResource(this.INDICATOR_SELECTED_RES);
        this.LAST_SELECT_INDEX = index;
    }

    private void addIndicator() {
        ImageView imageView = new ImageView(this.mContext);
        imageView.setImageResource(this.INDICATOR_NORMAL_RES);
        addView(imageView);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        int i = this.indicatorMargin;
        layoutParams.setMargins(i, i, i, i);
    }

    public void attachToRecyclerView(final RecyclerView rv) {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.cy.yyjia.zhe28.view.Indicator.1
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int iFindFirstCompletelyVisibleItemPosition = rv.getLayoutManager().findFirstCompletelyVisibleItemPosition();
                if (iFindFirstCompletelyVisibleItemPosition < 0 || iFindFirstCompletelyVisibleItemPosition >= Indicator.this.size) {
                    return;
                }
                Indicator.this.select(iFindFirstCompletelyVisibleItemPosition);
            }
        });
    }
}
