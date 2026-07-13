package com.bigkoo.convenientbanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.ViewPager;
import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.CBPageChangeListener;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bigkoo.convenientbanner.view.CBLoopViewPager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ConvenientBanner<T> extends LinearLayout {
    private AdSwitchTask adSwitchTask;
    private long autoTurningTime;
    private boolean canLoop;
    private boolean canTurn;
    private ViewGroup loPageTurningPoint;
    private List<T> mDatas;
    private ArrayList<ImageView> mPointViews;
    private boolean manualPageable;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private CBPageAdapter pageAdapter;
    private CBPageChangeListener pageChangeListener;
    private int[] page_indicatorId;
    private ViewPagerScroller scroller;
    private boolean turning;
    private CBLoopViewPager viewPager;

    public enum PageIndicatorAlign {
        ALIGN_PARENT_LEFT,
        ALIGN_PARENT_RIGHT,
        CENTER_HORIZONTAL
    }

    public ConvenientBanner(Context context) {
        super(context);
        this.mPointViews = new ArrayList<>();
        this.canTurn = false;
        this.manualPageable = true;
        this.canLoop = true;
        init(context);
    }

    public ConvenientBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPointViews = new ArrayList<>();
        this.canTurn = false;
        this.manualPageable = true;
        this.canLoop = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConvenientBanner);
        this.canLoop = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConvenientBanner_canLoop, true);
        typedArrayObtainStyledAttributes.recycle();
        init(context);
    }

    public ConvenientBanner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPointViews = new ArrayList<>();
        this.canTurn = false;
        this.manualPageable = true;
        this.canLoop = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConvenientBanner);
        this.canLoop = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConvenientBanner_canLoop, true);
        typedArrayObtainStyledAttributes.recycle();
        init(context);
    }

    public ConvenientBanner(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mPointViews = new ArrayList<>();
        this.canTurn = false;
        this.manualPageable = true;
        this.canLoop = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConvenientBanner);
        this.canLoop = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ConvenientBanner_canLoop, true);
        typedArrayObtainStyledAttributes.recycle();
        init(context);
    }

    private void init(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.include_viewpager, (ViewGroup) this, true);
        this.viewPager = (CBLoopViewPager) viewInflate.findViewById(R.id.cbLoopViewPager);
        this.loPageTurningPoint = (ViewGroup) viewInflate.findViewById(R.id.loPageTurningPoint);
        initViewPagerScroll();
        this.adSwitchTask = new AdSwitchTask(this);
    }

    static class AdSwitchTask implements Runnable {
        private final WeakReference<ConvenientBanner> reference;

        AdSwitchTask(ConvenientBanner convenientBanner) {
            this.reference = new WeakReference<>(convenientBanner);
        }

        @Override // java.lang.Runnable
        public void run() {
            ConvenientBanner convenientBanner = this.reference.get();
            if (convenientBanner == null || convenientBanner.viewPager == null || !convenientBanner.turning) {
                return;
            }
            convenientBanner.viewPager.setCurrentItem(convenientBanner.viewPager.getCurrentItem() + 1);
            convenientBanner.postDelayed(convenientBanner.adSwitchTask, convenientBanner.autoTurningTime);
        }
    }

    public ConvenientBanner setPages(CBViewHolderCreator cBViewHolderCreator, List<T> list) {
        this.mDatas = list;
        CBPageAdapter cBPageAdapter = new CBPageAdapter(cBViewHolderCreator, this.mDatas);
        this.pageAdapter = cBPageAdapter;
        this.viewPager.setAdapter(cBPageAdapter, this.canLoop);
        int[] iArr = this.page_indicatorId;
        if (iArr != null) {
            setPageIndicator(iArr);
        }
        return this;
    }

    public void notifyDataSetChanged() {
        this.viewPager.getAdapter().notifyDataSetChanged();
        int[] iArr = this.page_indicatorId;
        if (iArr != null) {
            setPageIndicator(iArr);
        }
    }

    public ConvenientBanner setPointViewVisible(boolean z) {
        this.loPageTurningPoint.setVisibility(z ? 0 : 8);
        return this;
    }

    public ConvenientBanner setPageIndicator(int[] iArr) {
        this.loPageTurningPoint.removeAllViews();
        this.mPointViews.clear();
        this.page_indicatorId = iArr;
        if (this.mDatas == null) {
            return this;
        }
        for (int i = 0; i < this.mDatas.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setPadding(5, 0, 5, 0);
            if (this.mPointViews.isEmpty()) {
                imageView.setImageResource(iArr[1]);
            } else {
                imageView.setImageResource(iArr[0]);
            }
            this.mPointViews.add(imageView);
            this.loPageTurningPoint.addView(imageView);
        }
        CBPageChangeListener cBPageChangeListener = new CBPageChangeListener(this.mPointViews, iArr);
        this.pageChangeListener = cBPageChangeListener;
        this.viewPager.setOnPageChangeListener(cBPageChangeListener);
        this.pageChangeListener.onPageSelected(this.viewPager.getRealItem());
        ViewPager.OnPageChangeListener onPageChangeListener = this.onPageChangeListener;
        if (onPageChangeListener != null) {
            this.pageChangeListener.setOnPageChangeListener(onPageChangeListener);
        }
        return this;
    }

    public ConvenientBanner setPageIndicatorAlign(PageIndicatorAlign pageIndicatorAlign) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.loPageTurningPoint.getLayoutParams();
        layoutParams.addRule(9, pageIndicatorAlign == PageIndicatorAlign.ALIGN_PARENT_LEFT ? -1 : 0);
        layoutParams.addRule(11, pageIndicatorAlign == PageIndicatorAlign.ALIGN_PARENT_RIGHT ? -1 : 0);
        layoutParams.addRule(14, pageIndicatorAlign != PageIndicatorAlign.CENTER_HORIZONTAL ? 0 : -1);
        this.loPageTurningPoint.setLayoutParams(layoutParams);
        return this;
    }

    public boolean isTurning() {
        return this.turning;
    }

    public ConvenientBanner startTurning(long j) {
        if (this.turning) {
            stopTurning();
        }
        this.canTurn = true;
        this.autoTurningTime = j;
        this.turning = true;
        postDelayed(this.adSwitchTask, j);
        return this;
    }

    public void stopTurning() {
        this.turning = false;
        removeCallbacks(this.adSwitchTask);
    }

    public ConvenientBanner setPageTransformer(ViewPager.PageTransformer pageTransformer) {
        this.viewPager.setPageTransformer(true, pageTransformer);
        return this;
    }

    private void initViewPagerScroll() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            ViewPagerScroller viewPagerScroller = new ViewPagerScroller(this.viewPager.getContext());
            this.scroller = viewPagerScroller;
            declaredField.set(this.viewPager, viewPagerScroller);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
    }

    public boolean isManualPageable() {
        return this.viewPager.isCanScroll();
    }

    public void setManualPageable(boolean z) {
        this.viewPager.setCanScroll(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3 || action == 4) {
            if (this.canTurn) {
                startTurning(this.autoTurningTime);
            }
        } else if (action == 0 && this.canTurn) {
            stopTurning();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getCurrentItem() {
        CBLoopViewPager cBLoopViewPager = this.viewPager;
        if (cBLoopViewPager != null) {
            return cBLoopViewPager.getRealItem();
        }
        return -1;
    }

    public void setcurrentitem(int i) {
        CBLoopViewPager cBLoopViewPager = this.viewPager;
        if (cBLoopViewPager != null) {
            cBLoopViewPager.setCurrentItem(i);
        }
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return this.onPageChangeListener;
    }

    public ConvenientBanner setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
        CBPageChangeListener cBPageChangeListener = this.pageChangeListener;
        if (cBPageChangeListener != null) {
            cBPageChangeListener.setOnPageChangeListener(onPageChangeListener);
        } else {
            this.viewPager.setOnPageChangeListener(onPageChangeListener);
        }
        return this;
    }

    public boolean isCanLoop() {
        return this.viewPager.isCanLoop();
    }

    public ConvenientBanner setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener == null) {
            this.viewPager.setOnItemClickListener(null);
            return this;
        }
        this.viewPager.setOnItemClickListener(onItemClickListener);
        return this;
    }

    public void setScrollDuration(int i) {
        this.scroller.setScrollDuration(i);
    }

    public int getScrollDuration() {
        return this.scroller.getScrollDuration();
    }

    public CBLoopViewPager getViewPager() {
        return this.viewPager;
    }

    public void setCanLoop(boolean z) {
        this.canLoop = z;
        this.viewPager.setCanLoop(z);
    }
}
