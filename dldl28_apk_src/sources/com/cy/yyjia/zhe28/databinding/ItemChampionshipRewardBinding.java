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
public abstract class ItemChampionshipRewardBinding extends ViewDataBinding {

    @Bindable
    protected ChampionshipBean.Prize mData;

    public abstract void setData(ChampionshipBean.Prize data);

    protected ItemChampionshipRewardBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public ChampionshipBean.Prize getData() {
        return this.mData;
    }

    public static ItemChampionshipRewardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemChampionshipRewardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemChampionshipRewardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_championship_reward, root, attachToRoot, component);
    }

    public static ItemChampionshipRewardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemChampionshipRewardBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemChampionshipRewardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_championship_reward, null, false, component);
    }

    public static ItemChampionshipRewardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemChampionshipRewardBinding bind(View view, Object component) {
        return (ItemChampionshipRewardBinding) bind(component, view, R.layout.item_championship_reward);
    }
}
