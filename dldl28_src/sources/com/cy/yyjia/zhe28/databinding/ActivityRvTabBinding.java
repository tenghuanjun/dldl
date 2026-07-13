package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityRvTabBinding extends ViewDataBinding {
    public final Navigation navigation;
    public final RecyclerView rv;
    public final SmartRefreshLayout srl;
    public final TabLayout tab;

    protected ActivityRvTabBinding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, RecyclerView rv, SmartRefreshLayout srl, TabLayout tab) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.rv = rv;
        this.srl = srl;
        this.tab = tab;
    }

    public static ActivityRvTabBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRvTabBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityRvTabBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_rv_tab, root, attachToRoot, component);
    }

    public static ActivityRvTabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRvTabBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityRvTabBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_rv_tab, null, false, component);
    }

    public static ActivityRvTabBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRvTabBinding bind(View view, Object component) {
        return (ActivityRvTabBinding) bind(component, view, R.layout.activity_rv_tab);
    }
}
