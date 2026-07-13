package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DailyCouponBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDailyCouponBinding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected DailyCouponBean.Tier mData;

    public abstract void setData(DailyCouponBean.Tier data);

    protected ItemDailyCouponBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
    }

    public DailyCouponBean.Tier getData() {
        return this.mData;
    }

    public static ItemDailyCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDailyCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDailyCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_daily_coupon, root, attachToRoot, component);
    }

    public static ItemDailyCouponBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDailyCouponBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDailyCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_daily_coupon, null, false, component);
    }

    public static ItemDailyCouponBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDailyCouponBinding bind(View view, Object component) {
        return (ItemDailyCouponBinding) bind(component, view, R.layout.item_daily_coupon);
    }
}
