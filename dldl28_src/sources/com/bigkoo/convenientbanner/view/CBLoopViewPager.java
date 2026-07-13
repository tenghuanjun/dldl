package com.bigkoo.convenientbanner.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

/* JADX INFO: loaded from: classes2.dex */
public class CBLoopViewPager extends ViewPager {
    private static final float sens = 5.0f;
    private boolean canLoop;
    private boolean isCanScroll;
    private CBPageAdapter mAdapter;
    ViewPager.OnPageChangeListener mOuterPageChangeListener;
    private float newX;
    private float oldX;
    private OnItemClickListener onItemClickListener;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    public void setAdapter(PagerAdapter pagerAdapter, boolean z) {
        CBPageAdapter cBPageAdapter = (CBPageAdapter) pagerAdapter;
        this.mAdapter = cBPageAdapter;
        cBPageAdapter.setCanLoop(z);
        this.mAdapter.setViewPager(this);
        super.setAdapter(this.mAdapter);
        setCurrentItem(getFristItem(), false);
    }

    public int getFristItem() {
        if (this.canLoop) {
            return this.mAdapter.getRealCount();
        }
        return 0;
    }

    public int getLastItem() {
        return this.mAdapter.getRealCount() - 1;
    }

    public boolean isCanScroll() {
        return this.isCanScroll;
    }

    public void setCanScroll(boolean z) {
        this.isCanScroll = z;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.isCanScroll) {
            return false;
        }
        if (this.onItemClickListener != null) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.oldX = motionEvent.getX();
            } else if (action == 1) {
                float x = motionEvent.getX();
                this.newX = x;
                if (Math.abs(this.oldX - x) < sens) {
                    this.onItemClickListener.onItemClick(getRealItem());
                }
                this.oldX = 0.0f;
                this.newX = 0.0f;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isCanScroll) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public CBPageAdapter getAdapter() {
        return this.mAdapter;
    }

    public int getRealItem() {
        CBPageAdapter cBPageAdapter = this.mAdapter;
        if (cBPageAdapter != null) {
            return cBPageAdapter.toRealPosition(super.getCurrentItem());
        }
        return 0;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOuterPageChangeListener = onPageChangeListener;
    }

    public CBLoopViewPager(Context context) {
        super(context);
        this.isCanScroll = true;
        this.canLoop = true;
        this.oldX = 0.0f;
        this.newX = 0.0f;
        this.onPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.bigkoo.convenientbanner.view.CBLoopViewPager.1
            private float mPreviousPosition = -1.0f;

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                int realPosition = CBLoopViewPager.this.mAdapter.toRealPosition(i);
                float f = realPosition;
                if (this.mPreviousPosition != f) {
                    this.mPreviousPosition = f;
                    if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageSelected(realPosition);
                    }
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                    if (i != CBLoopViewPager.this.mAdapter.getRealCount() - 1) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, f, i2);
                    } else if (f > 0.5d) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(0, 0.0f, 0);
                    } else {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, 0.0f, 0);
                    }
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                    CBLoopViewPager.this.mOuterPageChangeListener.onPageScrollStateChanged(i);
                }
            }
        };
        init();
    }

    public CBLoopViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isCanScroll = true;
        this.canLoop = true;
        this.oldX = 0.0f;
        this.newX = 0.0f;
        this.onPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.bigkoo.convenientbanner.view.CBLoopViewPager.1
            private float mPreviousPosition = -1.0f;

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                int realPosition = CBLoopViewPager.this.mAdapter.toRealPosition(i);
                float f = realPosition;
                if (this.mPreviousPosition != f) {
                    this.mPreviousPosition = f;
                    if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageSelected(realPosition);
                    }
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                    if (i != CBLoopViewPager.this.mAdapter.getRealCount() - 1) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, f, i2);
                    } else if (f > 0.5d) {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(0, 0.0f, 0);
                    } else {
                        CBLoopViewPager.this.mOuterPageChangeListener.onPageScrolled(i, 0.0f, 0);
                    }
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                if (CBLoopViewPager.this.mOuterPageChangeListener != null) {
                    CBLoopViewPager.this.mOuterPageChangeListener.onPageScrollStateChanged(i);
                }
            }
        };
        init();
    }

    private void init() {
        super.setOnPageChangeListener(this.onPageChangeListener);
    }

    public boolean isCanLoop() {
        return this.canLoop;
    }

    public void setCanLoop(boolean z) {
        this.canLoop = z;
        if (!z) {
            setCurrentItem(getRealItem(), false);
        }
        CBPageAdapter cBPageAdapter = this.mAdapter;
        if (cBPageAdapter == null) {
            return;
        }
        cBPageAdapter.setCanLoop(z);
        this.mAdapter.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
