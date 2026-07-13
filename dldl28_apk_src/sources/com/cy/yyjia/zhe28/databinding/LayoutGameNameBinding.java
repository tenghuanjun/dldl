package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LayoutGameNameBinding extends ViewDataBinding {

    @Bindable
    protected GameBean mData;

    @Bindable
    protected boolean mSearch;

    public abstract void setData(GameBean data);

    public abstract void setSearch(boolean search);

    protected LayoutGameNameBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GameBean getData() {
        return this.mData;
    }

    public boolean getSearch() {
        return this.mSearch;
    }

    public static LayoutGameNameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameNameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutGameNameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_game_name, root, attachToRoot, component);
    }

    public static LayoutGameNameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameNameBinding inflate(LayoutInflater inflater, Object component) {
        return (LayoutGameNameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_game_name, null, false, component);
    }

    public static LayoutGameNameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameNameBinding bind(View view, Object component) {
        return (LayoutGameNameBinding) bind(component, view, R.layout.layout_game_name);
    }
}
