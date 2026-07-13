package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipCouponIndexBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityVipCouponBinding extends ViewDataBinding {
    public final TextView btn;
    public final ImageView ivRule;

    @Bindable
    protected VipCouponIndexBean mData;

    @Bindable
    protected int mLevel;
    public final RecyclerView rv;

    public abstract void setData(VipCouponIndexBean data);

    public abstract void setLevel(int level);

    protected ActivityVipCouponBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, ImageView ivRule, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.ivRule = ivRule;
        this.rv = rv;
    }

    public int getLevel() {
        return this.mLevel;
    }

    public VipCouponIndexBean getData() {
        return this.mData;
    }

    public static ActivityVipCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVipCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityVipCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_vip_coupon, root, attachToRoot, component);
    }

    public static ActivityVipCouponBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVipCouponBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityVipCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_vip_coupon, null, false, component);
    }

    public static ActivityVipCouponBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVipCouponBinding bind(View view, Object component) {
        return (ActivityVipCouponBinding) bind(component, view, R.layout.activity_vip_coupon);
    }
}
