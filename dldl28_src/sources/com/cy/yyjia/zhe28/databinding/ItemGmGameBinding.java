package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMGameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGmGameBinding extends ViewDataBinding {

    @Bindable
    protected GMGameBean mData;

    public abstract void setData(GMGameBean data);

    protected ItemGmGameBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GMGameBean getData() {
        return this.mData;
    }

    public static ItemGmGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGmGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gm_game, root, attachToRoot, component);
    }

    public static ItemGmGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGmGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gm_game, null, false, component);
    }

    public static ItemGmGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmGameBinding bind(View view, Object component) {
        return (ItemGmGameBinding) bind(component, view, R.layout.item_gm_game);
    }
}
