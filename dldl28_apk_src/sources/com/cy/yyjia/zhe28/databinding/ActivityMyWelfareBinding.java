package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.google.android.material.tabs.TabLayout;
import com.hjq.shape.view.ShapeTextView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityMyWelfareBinding extends ViewDataBinding {
    public final LinearLayout llCoupon;

    @Bindable
    protected int mPosition;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final SmartRefreshLayout srl;
    public final TabLayout tab;
    public final ShapeTextView tv1;
    public final ShapeTextView tv2;
    public final ShapeTextView tv3;

    public abstract void setPosition(int position);

    protected ActivityMyWelfareBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llCoupon, Navigation navigation, RecyclerView rv, SmartRefreshLayout srl, TabLayout tab, ShapeTextView tv1, ShapeTextView tv2, ShapeTextView tv3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llCoupon = llCoupon;
        this.navigation = navigation;
        this.rv = rv;
        this.srl = srl;
        this.tab = tab;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public static ActivityMyWelfareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMyWelfareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMyWelfareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_my_welfare, root, attachToRoot, component);
    }

    public static ActivityMyWelfareBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMyWelfareBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMyWelfareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_my_welfare, null, false, component);
    }

    public static ActivityMyWelfareBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMyWelfareBinding bind(View view, Object component) {
        return (ActivityMyWelfareBinding) bind(component, view, R.layout.activity_my_welfare);
    }
}
