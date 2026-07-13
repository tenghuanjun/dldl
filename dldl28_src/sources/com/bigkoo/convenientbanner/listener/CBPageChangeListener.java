package com.bigkoo.convenientbanner.listener;

import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class CBPageChangeListener implements ViewPager.OnPageChangeListener {
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private int[] page_indicatorId;
    private ArrayList<ImageView> pointViews;

    public CBPageChangeListener(ArrayList<ImageView> arrayList, int[] iArr) {
        this.pointViews = arrayList;
        this.page_indicatorId = iArr;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.onPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.onPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        for (int i2 = 0; i2 < this.pointViews.size(); i2++) {
            this.pointViews.get(i).setImageResource(this.page_indicatorId[1]);
            if (i != i2) {
                this.pointViews.get(i2).setImageResource(this.page_indicatorId[0]);
            }
        }
        ViewPager.OnPageChangeListener onPageChangeListener = this.onPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }
}
