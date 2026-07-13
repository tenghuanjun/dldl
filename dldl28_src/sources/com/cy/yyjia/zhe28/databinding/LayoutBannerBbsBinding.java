package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBannerBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LayoutBannerBbsBinding extends ViewDataBinding {

    @Bindable
    protected BbsBannerBean mData;

    public abstract void setData(BbsBannerBean data);

    protected LayoutBannerBbsBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public BbsBannerBean getData() {
        return this.mData;
    }

    public static LayoutBannerBbsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBannerBbsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutBannerBbsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_banner_bbs, root, attachToRoot, component);
    }

    public static LayoutBannerBbsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBannerBbsBinding inflate(LayoutInflater inflater, Object component) {
        return (LayoutBannerBbsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_banner_bbs, null, false, component);
    }

    public static LayoutBannerBbsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBannerBbsBinding bind(View view, Object component) {
        return (LayoutBannerBbsBinding) bind(component, view, R.layout.layout_banner_bbs);
    }
}
