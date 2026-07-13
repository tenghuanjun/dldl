package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CouponBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemMyCouponBinding extends ViewDataBinding {
    public final ImageView ivMore;
    public final LinearLayout ll;

    @Bindable
    protected CouponBean mData;

    @Bindable
    protected int mType;

    public abstract void setData(CouponBean data);

    public abstract void setType(int type);

    protected ItemMyCouponBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMore, LinearLayout ll) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMore = ivMore;
        this.ll = ll;
    }

    public int getType() {
        return this.mType;
    }

    public CouponBean getData() {
        return this.mData;
    }

    public static ItemMyCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyCouponBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemMyCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_my_coupon, root, attachToRoot, component);
    }

    public static ItemMyCouponBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyCouponBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemMyCouponBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_my_coupon, null, false, component);
    }

    public static ItemMyCouponBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMyCouponBinding bind(View view, Object component) {
        return (ItemMyCouponBinding) bind(component, view, R.layout.item_my_coupon);
    }
}
