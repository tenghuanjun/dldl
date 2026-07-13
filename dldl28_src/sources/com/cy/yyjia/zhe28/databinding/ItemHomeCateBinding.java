package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.HomeBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeCateBinding extends ViewDataBinding {

    @Bindable
    protected HomeBean.CateBlock mData;

    public abstract void setData(HomeBean.CateBlock data);

    protected ItemHomeCateBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public HomeBean.CateBlock getData() {
        return this.mData;
    }

    public static ItemHomeCateBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeCateBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeCateBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_cate, root, attachToRoot, component);
    }

    public static ItemHomeCateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeCateBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeCateBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_cate, null, false, component);
    }

    public static ItemHomeCateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeCateBinding bind(View view, Object component) {
        return (ItemHomeCateBinding) bind(component, view, R.layout.item_home_cate);
    }
}
