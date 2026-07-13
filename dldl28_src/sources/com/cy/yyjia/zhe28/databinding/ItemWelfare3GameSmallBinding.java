package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.WelfareGameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemWelfare3GameSmallBinding extends ViewDataBinding {

    @Bindable
    protected WelfareGameBean mData;

    public abstract void setData(WelfareGameBean data);

    protected ItemWelfare3GameSmallBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public WelfareGameBean getData() {
        return this.mData;
    }

    public static ItemWelfare3GameSmallBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3GameSmallBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWelfare3GameSmallBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare3_game_small, root, attachToRoot, component);
    }

    public static ItemWelfare3GameSmallBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3GameSmallBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemWelfare3GameSmallBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare3_game_small, null, false, component);
    }

    public static ItemWelfare3GameSmallBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3GameSmallBinding bind(View view, Object component) {
        return (ItemWelfare3GameSmallBinding) bind(component, view, R.layout.item_welfare3_game_small);
    }
}
