package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CouponBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemSanbao648CouponBinding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected CouponBean mData;
    public final TextView tvMore;

    public abstract void setData(CouponBean data);

    protected ItemSanbao648CouponBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, TextView tvMore) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.tvMore = tvMore;
    }

    public CouponBean getData() {
        return this.mData;
    }

    public static ItemSanbao648CouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbao648CouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSanbao648CouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_648_coupon, root, attachToRoot, component);
    }

    public static ItemSanbao648CouponBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbao648CouponBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSanbao648CouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_648_coupon, null, false, component);
    }

    public static ItemSanbao648CouponBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbao648CouponBinding bind(View view, Object component) {
        return (ItemSanbao648CouponBinding) bind(component, view, R.layout.item_sanbao_648_coupon);
    }
}
