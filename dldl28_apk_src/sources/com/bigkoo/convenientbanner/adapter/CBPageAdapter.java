package com.bigkoo.convenientbanner.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.bigkoo.convenientbanner.R;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.view.CBLoopViewPager;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CBPageAdapter<T> extends PagerAdapter {
    protected CBViewHolderCreator holderCreator;
    protected List<T> mDatas;
    private CBLoopViewPager viewPager;
    private boolean canLoop = true;
    private final int MULTIPLE_COUNT = 300;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int toRealPosition(int i) {
        int realCount = getRealCount();
        if (realCount == 0) {
            return 0;
        }
        return i % realCount;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.canLoop ? getRealCount() * 300 : getRealCount();
    }

    public int getRealCount() {
        List<T> list = this.mDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = getView(toRealPosition(i), null, viewGroup);
        viewGroup.addView(view);
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        int currentItem = this.viewPager.getCurrentItem();
        if (currentItem == 0) {
            currentItem = this.viewPager.getFristItem();
        } else if (currentItem == getCount() - 1) {
            currentItem = this.viewPager.getLastItem();
        }
        try {
            this.viewPager.setCurrentItem(currentItem, false);
        } catch (IllegalStateException unused) {
        }
    }

    public void setCanLoop(boolean z) {
        this.canLoop = z;
    }

    public void setViewPager(CBLoopViewPager cBLoopViewPager) {
        this.viewPager = cBLoopViewPager;
    }

    public CBPageAdapter(CBViewHolderCreator cBViewHolderCreator, List<T> list) {
        this.holderCreator = cBViewHolderCreator;
        this.mDatas = list;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewCreateView;
        Holder holder;
        if (view == null) {
            holder = (Holder) this.holderCreator.createHolder();
            viewCreateView = holder.createView(viewGroup.getContext());
            viewCreateView.setTag(R.id.cb_item_tag, holder);
        } else {
            viewCreateView = view;
            holder = (Holder) view.getTag(R.id.cb_item_tag);
        }
        List<T> list = this.mDatas;
        if (list != null && !list.isEmpty()) {
            holder.UpdateUI(viewGroup.getContext(), i, this.mDatas.get(i));
        }
        return viewCreateView;
    }
}
