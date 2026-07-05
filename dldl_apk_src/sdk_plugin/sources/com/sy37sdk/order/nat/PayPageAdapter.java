package com.sy37sdk.order.nat;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.sqwan.common.util.LogUtil;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayPageAdapter extends PagerAdapter {
    private static final String TAG = "AccountPageAdapter";
    private List<BaseNativePayPresenter> nativePayPresenters;

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public PayPageAdapter(List<BaseNativePayPresenter> list) {
        this.nativePayPresenters = list;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.nativePayPresenters.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(View view, int i, Object obj) {
        View view2 = this.nativePayPresenters.get(i).getView();
        LogUtil.i(TAG, "destroyItem: position=" + i + "  view:" + view2);
        ((ViewPager) view).removeView(view2);
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(View view, int i) {
        View view2 = this.nativePayPresenters.get(i).getView();
        LogUtil.i(TAG, "instantiateItem: v=" + view2 + "  pos=" + i);
        ((ViewPager) view).addView(view2);
        return view2;
    }
}
