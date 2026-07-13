package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipCouponIndexBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemVipCouponLevelBinding extends ViewDataBinding {

    @Bindable
    protected VipCouponIndexBean.Vip mData;

    public abstract void setData(VipCouponIndexBean.Vip data);

    protected ItemVipCouponLevelBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public VipCouponIndexBean.Vip getData() {
        return this.mData;
    }

    public static ItemVipCouponLevelBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipCouponLevelBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemVipCouponLevelBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_coupon_level, root, attachToRoot, component);
    }

    public static ItemVipCouponLevelBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipCouponLevelBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemVipCouponLevelBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_coupon_level, null, false, component);
    }

    public static ItemVipCouponLevelBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipCouponLevelBinding bind(View view, Object component) {
        return (ItemVipCouponLevelBinding) bind(component, view, R.layout.item_vip_coupon_level);
    }
}
