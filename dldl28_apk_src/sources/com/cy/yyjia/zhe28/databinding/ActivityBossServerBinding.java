package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BossServerBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityBossServerBinding extends ViewDataBinding {

    @Bindable
    protected BossServerBean mData;
    public final RecyclerView rv;

    public abstract void setData(BossServerBean data);

    protected ActivityBossServerBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
    }

    public BossServerBean getData() {
        return this.mData;
    }

    public static ActivityBossServerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBossServerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBossServerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_boss_server, root, attachToRoot, component);
    }

    public static ActivityBossServerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBossServerBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBossServerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_boss_server, null, false, component);
    }

    public static ActivityBossServerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBossServerBinding bind(View view, Object component) {
        return (ActivityBossServerBinding) bind(component, view, R.layout.activity_boss_server);
    }
}
