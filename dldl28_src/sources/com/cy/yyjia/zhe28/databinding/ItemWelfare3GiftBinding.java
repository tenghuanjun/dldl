package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TypeBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemWelfare3GiftBinding extends ViewDataBinding {

    @Bindable
    protected TypeBean mData;

    public abstract void setData(TypeBean data);

    protected ItemWelfare3GiftBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public TypeBean getData() {
        return this.mData;
    }

    public static ItemWelfare3GiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3GiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWelfare3GiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare3_gift, root, attachToRoot, component);
    }

    public static ItemWelfare3GiftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3GiftBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemWelfare3GiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare3_gift, null, false, component);
    }

    public static ItemWelfare3GiftBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3GiftBinding bind(View view, Object component) {
        return (ItemWelfare3GiftBinding) bind(component, view, R.layout.item_welfare3_gift);
    }
}
