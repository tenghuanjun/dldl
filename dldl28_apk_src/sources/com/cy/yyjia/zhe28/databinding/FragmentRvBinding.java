package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentRvBinding extends ViewDataBinding {
    public final LinearLayout ll;
    public final RecyclerView rv;
    public final SmartRefreshLayout srl;

    protected FragmentRvBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout ll, RecyclerView rv, SmartRefreshLayout srl) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ll = ll;
        this.rv = rv;
        this.srl = srl;
    }

    public static FragmentRvBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRvBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentRvBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_rv, root, attachToRoot, component);
    }

    public static FragmentRvBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRvBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentRvBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_rv, null, false, component);
    }

    public static FragmentRvBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRvBinding bind(View view, Object component) {
        return (FragmentRvBinding) bind(component, view, R.layout.fragment_rv);
    }
}
