package com.donkingliang.consecutivescroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ConsecutiveViewPager2 extends FrameLayout implements IConsecutiveScroller {
    private static final int TAG_KEY = -123;
    private int mAdjustHeight;
    protected RecyclerView mRecyclerView;
    protected ViewPager2 mViewPager2;

    public ConsecutiveViewPager2(Context context) {
        super(context);
        initialize(context);
    }

    public ConsecutiveViewPager2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public ConsecutiveViewPager2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        ViewPager2 viewPager2 = new ViewPager2(context);
        this.mViewPager2 = viewPager2;
        addView((View) viewPager2, -1, -1);
        this.mRecyclerView = this.mViewPager2.getChildAt(0);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isConsecutiveParentAndBottom() && this.mAdjustHeight > 0) {
            super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(getDefaultSize(0, heightMeasureSpec) - this.mAdjustHeight, View.MeasureSpec.getMode(heightMeasureSpec)));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private boolean isConsecutiveParentAndBottom() {
        ViewParent parent = getParent();
        if (!(parent instanceof ConsecutiveScrollerLayout)) {
            return false;
        }
        ConsecutiveScrollerLayout consecutiveScrollerLayout = (ConsecutiveScrollerLayout) parent;
        return consecutiveScrollerLayout.indexOfChild(this) == consecutiveScrollerLayout.getChildCount() - 1;
    }

    public int getAdjustHeight() {
        return this.mAdjustHeight;
    }

    public void setAdjustHeight(int adjustHeight) {
        if (this.mAdjustHeight != adjustHeight) {
            this.mAdjustHeight = adjustHeight;
            requestLayout();
        }
    }

    @Override // com.donkingliang.consecutivescroller.IConsecutiveScroller
    public View getCurrentScrollerView() {
        View viewFindScrolledItemView;
        int currentItem = getCurrentItem();
        RecyclerView.Adapter adapter = this.mRecyclerView.getAdapter();
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (adapter == null || layoutManager == null || currentItem < 0 || currentItem >= adapter.getItemCount()) {
            viewFindScrolledItemView = null;
        } else {
            viewFindScrolledItemView = findScrolledItemView(layoutManager.findViewByPosition(currentItem));
            if (viewFindScrolledItemView != null) {
                setAttachListener(viewFindScrolledItemView);
            }
        }
        return viewFindScrolledItemView == null ? this.mRecyclerView : viewFindScrolledItemView;
    }

    private void setAttachListener(View scrollerView) {
        if (scrollerView.getTag(TAG_KEY) != null) {
            AttachListener attachListener = (AttachListener) scrollerView.getTag(TAG_KEY);
            if (attachListener.reference.get() == null) {
                scrollerView.removeOnAttachStateChangeListener(attachListener);
                scrollerView.setTag(TAG_KEY, null);
            }
        }
        if (scrollerView.getTag(TAG_KEY) == null) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if ((layoutParams instanceof ConsecutiveScrollerLayout.LayoutParams) && ((ConsecutiveScrollerLayout.LayoutParams) layoutParams).isConsecutive) {
                View.OnAttachStateChangeListener attachListener2 = new AttachListener(this, scrollerView);
                scrollerView.addOnAttachStateChangeListener(attachListener2);
                scrollerView.setTag(TAG_KEY, attachListener2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollChildContent(View v) {
        View viewFindFirstVisibleView;
        if (v == null || !(getParent() instanceof ConsecutiveScrollerLayout)) {
            return;
        }
        ConsecutiveScrollerLayout consecutiveScrollerLayout = (ConsecutiveScrollerLayout) getParent();
        int iIndexOfChild = consecutiveScrollerLayout.indexOfChild(this);
        if ((iIndexOfChild != consecutiveScrollerLayout.getChildCount() - 1 || getHeight() >= consecutiveScrollerLayout.getHeight() || consecutiveScrollerLayout.getScrollY() < consecutiveScrollerLayout.mScrollRange) && (viewFindFirstVisibleView = consecutiveScrollerLayout.findFirstVisibleView()) != null) {
            int iIndexOfChild2 = consecutiveScrollerLayout.indexOfChild(viewFindFirstVisibleView);
            if (iIndexOfChild < iIndexOfChild2) {
                consecutiveScrollerLayout.scrollChildContentToBottom(v);
            } else if (iIndexOfChild > iIndexOfChild2) {
                consecutiveScrollerLayout.scrollChildContentToTop(v);
            }
        }
    }

    @Override // com.donkingliang.consecutivescroller.IConsecutiveScroller
    public List<View> getScrolledViews() {
        ArrayList arrayList = new ArrayList();
        int childCount = this.mRecyclerView.getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                arrayList.add(findScrolledItemView(this.mRecyclerView.getChildAt(i)));
            }
        }
        return arrayList;
    }

    protected View findScrolledItemView(View view) {
        if (!(this.mRecyclerView.getAdapter() instanceof FragmentStateAdapter) || !(view instanceof FrameLayout)) {
            return view;
        }
        FrameLayout frameLayout = (FrameLayout) view;
        return frameLayout.getChildCount() > 0 ? frameLayout.getChildAt(0) : view;
    }

    public ViewPager2 getViewPager2() {
        return this.mViewPager2;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.mViewPager2.setAdapter(adapter);
    }

    public RecyclerView.Adapter getAdapter() {
        return this.mViewPager2.getAdapter();
    }

    public void setOrientation(int orientation) {
        this.mViewPager2.setOrientation(orientation);
    }

    public int getOrientation() {
        return this.mViewPager2.getOrientation();
    }

    public void setCurrentItem(int item) {
        this.mViewPager2.setCurrentItem(item);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        this.mViewPager2.setCurrentItem(item, smoothScroll);
    }

    public int getCurrentItem() {
        return this.mViewPager2.getCurrentItem();
    }

    public void setOffscreenPageLimit(int limit) {
        this.mViewPager2.setOffscreenPageLimit(limit);
    }

    public int getOffscreenPageLimit() {
        return this.mViewPager2.getOffscreenPageLimit();
    }

    public void registerOnPageChangeCallback(ViewPager2.OnPageChangeCallback callback) {
        this.mViewPager2.registerOnPageChangeCallback(callback);
    }

    public void unregisterOnPageChangeCallback(ViewPager2.OnPageChangeCallback callback) {
        this.mViewPager2.unregisterOnPageChangeCallback(callback);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int direction) {
        return this.mViewPager2.canScrollHorizontally(direction);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int direction) {
        return this.mViewPager2.canScrollVertically(direction);
    }

    private static class AttachListener implements View.OnAttachStateChangeListener {
        WeakReference<ConsecutiveViewPager2> reference;
        View view;

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View v) {
        }

        public AttachListener(ConsecutiveViewPager2 parent, View view) {
            this.reference = new WeakReference<>(parent);
            this.view = view;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View v) {
            if (this.reference.get() != null) {
                this.reference.get().scrollChildContent(this.view);
            }
        }
    }
}
