package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BossServerBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBossServer2Binding extends ViewDataBinding {

    @Bindable
    protected BossServerBean.Privilege mData;

    public abstract void setData(BossServerBean.Privilege data);

    protected ItemBossServer2Binding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public BossServerBean.Privilege getData() {
        return this.mData;
    }

    public static ItemBossServer2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBossServer2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBossServer2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_boss_server2, root, attachToRoot, component);
    }

    public static ItemBossServer2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBossServer2Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemBossServer2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_boss_server2, null, false, component);
    }

    public static ItemBossServer2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBossServer2Binding bind(View view, Object component) {
        return (ItemBossServer2Binding) bind(component, view, R.layout.item_boss_server2);
    }
}
