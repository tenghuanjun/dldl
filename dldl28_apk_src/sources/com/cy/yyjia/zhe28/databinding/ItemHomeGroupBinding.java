package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CollectionBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeGroupBinding extends ViewDataBinding {

    @Bindable
    protected CollectionBean mData;
    public final RecyclerView rvGroupGame;
    public final RecyclerView rvGroupUser;

    public abstract void setData(CollectionBean data);

    protected ItemHomeGroupBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rvGroupGame, RecyclerView rvGroupUser) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvGroupGame = rvGroupGame;
        this.rvGroupUser = rvGroupUser;
    }

    public CollectionBean getData() {
        return this.mData;
    }

    public static ItemHomeGroupBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGroupBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeGroupBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_group, root, attachToRoot, component);
    }

    public static ItemHomeGroupBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGroupBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeGroupBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_group, null, false, component);
    }

    public static ItemHomeGroupBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGroupBinding bind(View view, Object component) {
        return (ItemHomeGroupBinding) bind(component, view, R.layout.item_home_group);
    }
}
