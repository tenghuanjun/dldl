package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ChampionshipBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityChampionshipBinding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected ChampionshipBean mData;
    public final RecyclerView rv;
    public final RecyclerView rvTask;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;

    public abstract void setData(ChampionshipBean data);

    protected ActivityChampionshipBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, RecyclerView rv, RecyclerView rvTask, TextView tv1, TextView tv2, TextView tv3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.rv = rv;
        this.rvTask = rvTask;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
    }

    public ChampionshipBean getData() {
        return this.mData;
    }

    public static ActivityChampionshipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChampionshipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityChampionshipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_championship, root, attachToRoot, component);
    }

    public static ActivityChampionshipBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChampionshipBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityChampionshipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_championship, null, false, component);
    }

    public static ActivityChampionshipBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChampionshipBinding bind(View view, Object component) {
        return (ActivityChampionshipBinding) bind(component, view, R.layout.activity_championship);
    }
}
