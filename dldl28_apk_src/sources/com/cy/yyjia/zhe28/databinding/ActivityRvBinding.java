package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityRvBinding extends ViewDataBinding {
    public final LinearLayout ll;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final SmartRefreshLayout srl;

    protected ActivityRvBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout ll, Navigation navigation, RecyclerView rv, SmartRefreshLayout srl) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ll = ll;
        this.navigation = navigation;
        this.rv = rv;
        this.srl = srl;
    }

    public static ActivityRvBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRvBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityRvBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_rv, root, attachToRoot, component);
    }

    public static ActivityRvBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRvBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityRvBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_rv, null, false, component);
    }

    public static ActivityRvBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRvBinding bind(View view, Object component) {
        return (ActivityRvBinding) bind(component, view, R.layout.activity_rv);
    }
}
