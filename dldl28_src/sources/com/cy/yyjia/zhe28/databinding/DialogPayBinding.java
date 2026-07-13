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
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogPayBinding extends ViewDataBinding {
    public final ImageView ivClose;

    @Bindable
    protected int mAlipay;

    @Bindable
    protected String mData;
    public final ShapeTextView tvPay;
    public final TextView tvPrice;
    public final TextView tvPtb;
    public final TextView tvTag1;
    public final TextView tvWx;
    public final TextView tvZfb;

    public abstract void setAlipay(int alipay);

    public abstract void setData(String data);

    protected DialogPayBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivClose, ShapeTextView tvPay, TextView tvPrice, TextView tvPtb, TextView tvTag1, TextView tvWx, TextView tvZfb) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivClose = ivClose;
        this.tvPay = tvPay;
        this.tvPrice = tvPrice;
        this.tvPtb = tvPtb;
        this.tvTag1 = tvTag1;
        this.tvWx = tvWx;
        this.tvZfb = tvZfb;
    }

    public String getData() {
        return this.mData;
    }

    public int getAlipay() {
        return this.mAlipay;
    }

    public static DialogPayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogPayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_pay, root, attachToRoot, component);
    }

    public static DialogPayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPayBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogPayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_pay, null, false, component);
    }

    public static DialogPayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPayBinding bind(View view, Object component) {
        return (DialogPayBinding) bind(component, view, R.layout.dialog_pay);
    }
}
