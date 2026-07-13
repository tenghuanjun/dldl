package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ChampionshipBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemChampionshipTaskBinding extends ViewDataBinding {

    @Bindable
    protected ChampionshipBean.Task mData;

    public abstract void setData(ChampionshipBean.Task data);

    protected ItemChampionshipTaskBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public ChampionshipBean.Task getData() {
        return this.mData;
    }

    public static ItemChampionshipTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemChampionshipTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemChampionshipTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_championship_task, root, attachToRoot, component);
    }

    public static ItemChampionshipTaskBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemChampionshipTaskBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemChampionshipTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_championship_task, null, false, component);
    }

    public static ItemChampionshipTaskBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemChampionshipTaskBinding bind(View view, Object component) {
        return (ItemChampionshipTaskBinding) bind(component, view, R.layout.item_championship_task);
    }
}
