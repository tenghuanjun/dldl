package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogVipCouponBuyBinding extends ViewDataBinding {
    public final ShapeTextView btn;
    public final ImageView ivJia;
    public final ImageView ivJian;

    @Bindable
    protected int mMax;

    @Bindable
    protected String mText;

    public abstract void setMax(int max);

    public abstract void setText(String text);

    protected DialogVipCouponBuyBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, ImageView ivJia, ImageView ivJian) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.ivJia = ivJia;
        this.ivJian = ivJian;
    }

    public String getText() {
        return this.mText;
    }

    public int getMax() {
        return this.mMax;
    }

    public static DialogVipCouponBuyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVipCouponBuyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogVipCouponBuyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_vip_coupon_buy, root, attachToRoot, component);
    }

    public static DialogVipCouponBuyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVipCouponBuyBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogVipCouponBuyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_vip_coupon_buy, null, false, component);
    }

    public static DialogVipCouponBuyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVipCouponBuyBinding bind(View view, Object component) {
        return (DialogVipCouponBuyBinding) bind(component, view, R.layout.dialog_vip_coupon_buy);
    }
}
