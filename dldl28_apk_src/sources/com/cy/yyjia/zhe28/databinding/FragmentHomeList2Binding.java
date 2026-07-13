package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentHomeList2Binding extends ViewDataBinding {
    public final LinearLayout ll;

    @Bindable
    protected View.OnClickListener mOnClick;
    public final RecyclerView rv;
    public final SmartRefreshLayout srl;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f459tv;

    public abstract void setOnClick(View.OnClickListener onClick);

    protected FragmentHomeList2Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout ll, RecyclerView rv, SmartRefreshLayout srl, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ll = ll;
        this.rv = rv;
        this.srl = srl;
        this.f459tv = tv2;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public static FragmentHomeList2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeList2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHomeList2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_list2, root, attachToRoot, component);
    }

    public static FragmentHomeList2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeList2Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHomeList2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_list2, null, false, component);
    }

    public static FragmentHomeList2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeList2Binding bind(View view, Object component) {
        return (FragmentHomeList2Binding) bind(component, view, R.layout.fragment_home_list2);
    }
}
