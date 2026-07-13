package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentDealRecordBinding extends ViewDataBinding {
    public final TextView btn;
    public final RecyclerView rv;
    public final SmartRefreshLayout srl;
    public final TabLayout tab;

    protected FragmentDealRecordBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, RecyclerView rv, SmartRefreshLayout srl, TabLayout tab) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.rv = rv;
        this.srl = srl;
        this.tab = tab;
    }

    public static FragmentDealRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentDealRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_deal_record, root, attachToRoot, component);
    }

    public static FragmentDealRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentDealRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_deal_record, null, false, component);
    }

    public static FragmentDealRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDealRecordBinding bind(View view, Object component) {
        return (FragmentDealRecordBinding) bind(component, view, R.layout.fragment_deal_record);
    }
}
