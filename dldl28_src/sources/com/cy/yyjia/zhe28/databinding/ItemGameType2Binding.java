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
public abstract class ItemGameType2Binding extends ViewDataBinding {

    @Bindable
    protected TypeBean mData;

    public abstract void setData(TypeBean data);

    protected ItemGameType2Binding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public TypeBean getData() {
        return this.mData;
    }

    public static ItemGameType2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameType2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameType2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_type2, root, attachToRoot, component);
    }

    public static ItemGameType2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameType2Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameType2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_type2, null, false, component);
    }

    public static ItemGameType2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameType2Binding bind(View view, Object component) {
        return (ItemGameType2Binding) bind(component, view, R.layout.item_game_type2);
    }
}
