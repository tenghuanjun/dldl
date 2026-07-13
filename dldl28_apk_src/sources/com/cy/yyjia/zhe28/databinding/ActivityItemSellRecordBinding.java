package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityItemSellRecordBinding extends ViewDataBinding {
    public final TextView btn;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final SmartRefreshLayout srl;
    public final TabLayout tab;

    protected ActivityItemSellRecordBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, Navigation navigation, RecyclerView rv, SmartRefreshLayout srl, TabLayout tab) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.navigation = navigation;
        this.rv = rv;
        this.srl = srl;
        this.tab = tab;
    }

    public static ActivityItemSellRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemSellRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityItemSellRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_item_sell_record, root, attachToRoot, component);
    }

    public static ActivityItemSellRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemSellRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityItemSellRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_item_sell_record, null, false, component);
    }

    public static ActivityItemSellRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityItemSellRecordBinding bind(View view, Object component) {
        return (ActivityItemSellRecordBinding) bind(component, view, R.layout.activity_item_sell_record);
    }
}
