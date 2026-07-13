package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameToolBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemWelfare31Binding extends ViewDataBinding {

    @Bindable
    protected GameToolBean mData;

    public abstract void setData(GameToolBean data);

    protected ItemWelfare31Binding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GameToolBean getData() {
        return this.mData;
    }

    public static ItemWelfare31Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare31Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWelfare31Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare3_1, root, attachToRoot, component);
    }

    public static ItemWelfare31Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare31Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemWelfare31Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare3_1, null, false, component);
    }

    public static ItemWelfare31Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare31Binding bind(View view, Object component) {
        return (ItemWelfare31Binding) bind(component, view, R.layout.item_welfare3_1);
    }
}
