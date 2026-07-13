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
import com.cy.yyjia.zhe28.domain.DailyCouponBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityDailyCouponBinding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected DailyCouponBean mData;
    public final RecyclerView rv;
    public final TextView tvRule;

    public abstract void setData(DailyCouponBean data);

    protected ActivityDailyCouponBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, RecyclerView rv, TextView tvRule) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.rv = rv;
        this.tvRule = tvRule;
    }

    public DailyCouponBean getData() {
        return this.mData;
    }

    public static ActivityDailyCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDailyCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDailyCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_daily_coupon, root, attachToRoot, component);
    }

    public static ActivityDailyCouponBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDailyCouponBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDailyCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_daily_coupon, null, false, component);
    }

    public static ActivityDailyCouponBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDailyCouponBinding bind(View view, Object component) {
        return (ActivityDailyCouponBinding) bind(component, view, R.layout.activity_daily_coupon);
    }
}
