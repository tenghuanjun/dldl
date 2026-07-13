package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameServiceBinding extends ViewDataBinding {

    @Bindable
    protected GameDetailBean.ServiceBean mData;

    public abstract void setData(GameDetailBean.ServiceBean data);

    protected ItemGameServiceBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GameDetailBean.ServiceBean getData() {
        return this.mData;
    }

    public static ItemGameServiceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameServiceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameServiceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_service, root, attachToRoot, component);
    }

    public static ItemGameServiceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameServiceBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameServiceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_service, null, false, component);
    }

    public static ItemGameServiceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameServiceBinding bind(View view, Object component) {
        return (ItemGameServiceBinding) bind(component, view, R.layout.item_game_service);
    }
}
