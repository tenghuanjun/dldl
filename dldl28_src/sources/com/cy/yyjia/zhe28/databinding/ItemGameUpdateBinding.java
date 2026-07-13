package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameHistoryBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameUpdateBinding extends ViewDataBinding {

    @Bindable
    protected GameHistoryBean mData;

    public abstract void setData(GameHistoryBean data);

    protected ItemGameUpdateBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GameHistoryBean getData() {
        return this.mData;
    }

    public static ItemGameUpdateBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameUpdateBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameUpdateBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_update, root, attachToRoot, component);
    }

    public static ItemGameUpdateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameUpdateBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameUpdateBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_update, null, false, component);
    }

    public static ItemGameUpdateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameUpdateBinding bind(View view, Object component) {
        return (ItemGameUpdateBinding) bind(component, view, R.layout.item_game_update);
    }
}
