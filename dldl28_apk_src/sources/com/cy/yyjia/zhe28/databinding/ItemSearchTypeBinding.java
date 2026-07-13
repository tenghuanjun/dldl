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
public abstract class ItemSearchTypeBinding extends ViewDataBinding {

    @Bindable
    protected TypeBean mData;

    public abstract void setData(TypeBean data);

    protected ItemSearchTypeBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public TypeBean getData() {
        return this.mData;
    }

    public static ItemSearchTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSearchTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_search_type, root, attachToRoot, component);
    }

    public static ItemSearchTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchTypeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSearchTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_search_type, null, false, component);
    }

    public static ItemSearchTypeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchTypeBinding bind(View view, Object component) {
        return (ItemSearchTypeBinding) bind(component, view, R.layout.item_search_type);
    }
}
