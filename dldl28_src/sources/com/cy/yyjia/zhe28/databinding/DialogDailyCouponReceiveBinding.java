package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogDailyCouponReceiveBinding extends ViewDataBinding {
    public final ImageView iv;
    public final ImageView ivClose;

    @Bindable
    protected String mData;

    @Bindable
    protected String mImg;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f447tv;

    public abstract void setData(String data);

    public abstract void setImg(String img);

    protected DialogDailyCouponReceiveBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv, ImageView ivClose, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
        this.ivClose = ivClose;
        this.f447tv = tv2;
    }

    public String getData() {
        return this.mData;
    }

    public String getImg() {
        return this.mImg;
    }

    public static DialogDailyCouponReceiveBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDailyCouponReceiveBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDailyCouponReceiveBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_daily_coupon_receive, root, attachToRoot, component);
    }

    public static DialogDailyCouponReceiveBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDailyCouponReceiveBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDailyCouponReceiveBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_daily_coupon_receive, null, false, component);
    }

    public static DialogDailyCouponReceiveBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDailyCouponReceiveBinding bind(View view, Object component) {
        return (DialogDailyCouponReceiveBinding) bind(component, view, R.layout.dialog_daily_coupon_receive);
    }
}
