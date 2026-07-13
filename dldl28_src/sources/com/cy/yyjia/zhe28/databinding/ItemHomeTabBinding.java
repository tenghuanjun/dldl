package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TabBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeTabBinding extends ViewDataBinding {

    @Bindable
    protected TabBean mData;

    public abstract void setData(TabBean data);

    protected ItemHomeTabBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public TabBean getData() {
        return this.mData;
    }

    public static ItemHomeTabBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeTabBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeTabBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_tab, root, attachToRoot, component);
    }

    public static ItemHomeTabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeTabBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeTabBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_tab, null, false, component);
    }

    public static ItemHomeTabBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeTabBinding bind(View view, Object component) {
        return (ItemHomeTabBinding) bind(component, view, R.layout.item_home_tab);
    }
}
