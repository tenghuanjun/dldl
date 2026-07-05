package com.sy37sdk.order.nat;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.sqwan.common.mvp.IPageSwitcher;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayPageSwitcher implements IPageSwitcher {
    public static final int PAGE_COUPON = 1;
    public static final int PAGE_LOBAR = 2;
    public static final int PAGE_TRADE = 0;
    private int mCurPage = 0;
    private ViewPager mViewPager;
    private IPageScrollListener pageScrollListener;

    public interface IPageScrollListener {
        void scroll(int i, int i2, Bundle bundle);
    }

    public PayPageSwitcher(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override // com.sqwan.common.mvp.IPageSwitcher
    public void onSwitch(int i) {
        onSwitch(i, null);
    }

    public void onSwitch(int i, Bundle bundle) {
        IPageScrollListener iPageScrollListener = this.pageScrollListener;
        if (iPageScrollListener != null) {
            iPageScrollListener.scroll(this.mCurPage, i, bundle);
        }
        this.mCurPage = i;
        this.mViewPager.setCurrentItem(i);
    }

    public void setPageScrollListener(IPageScrollListener iPageScrollListener) {
        this.pageScrollListener = iPageScrollListener;
    }
}
