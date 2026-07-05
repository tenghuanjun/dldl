package com.sy37sdk.account.view.uifast.switcher;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PageSwitcher implements IPageSwitcher {
    private int mCurPage = 2;
    private ViewPager mViewPager;
    private IPageScrollListener pageScrollListener;

    public interface IPageScrollListener {
        void scroll(int i, int i2, Bundle bundle);
    }

    public PageSwitcher(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override // com.sy37sdk.account.view.uifast.switcher.IPageSwitcher
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
