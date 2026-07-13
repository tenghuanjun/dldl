package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMItemBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGmItemBinding extends ViewDataBinding {

    @Bindable
    protected GMItemBean mData;

    public abstract void setData(GMItemBean data);

    protected ItemGmItemBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GMItemBean getData() {
        return this.mData;
    }

    public static ItemGmItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGmItemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gm_item, root, attachToRoot, component);
    }

    public static ItemGmItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmItemBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGmItemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gm_item, null, false, component);
    }

    public static ItemGmItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmItemBinding bind(View view, Object component) {
        return (ItemGmItemBinding) bind(component, view, R.layout.item_gm_item);
    }
}
