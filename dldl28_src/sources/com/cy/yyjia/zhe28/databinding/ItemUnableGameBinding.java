package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UnableGameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemUnableGameBinding extends ViewDataBinding {

    @Bindable
    protected UnableGameBean mData;

    public abstract void setData(UnableGameBean data);

    protected ItemUnableGameBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public UnableGameBean getData() {
        return this.mData;
    }

    public static ItemUnableGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemUnableGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemUnableGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_unable_game, root, attachToRoot, component);
    }

    public static ItemUnableGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemUnableGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemUnableGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_unable_game, null, false, component);
    }

    public static ItemUnableGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemUnableGameBinding bind(View view, Object component) {
        return (ItemUnableGameBinding) bind(component, view, R.layout.item_unable_game);
    }
}
