package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeVerticalBinding extends ViewDataBinding {

    @Bindable
    protected GameBean mData;

    public abstract void setData(GameBean data);

    protected ItemHomeVerticalBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemHomeVerticalBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeVerticalBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeVerticalBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_vertical, root, attachToRoot, component);
    }

    public static ItemHomeVerticalBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeVerticalBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeVerticalBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_vertical, null, false, component);
    }

    public static ItemHomeVerticalBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeVerticalBinding bind(View view, Object component) {
        return (ItemHomeVerticalBinding) bind(component, view, R.layout.item_home_vertical);
    }
}
