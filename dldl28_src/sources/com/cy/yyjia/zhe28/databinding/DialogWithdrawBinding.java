package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.InviteInfoBean;
import com.hjq.shape.layout.ShapeFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogWithdrawBinding extends ViewDataBinding {
    public final AppCompatImageView ivClose;
    public final ShapeFrameLayout llFlb;
    public final ShapeFrameLayout llPtb;
    public final ShapeFrameLayout llZfb;

    @Bindable
    protected InviteInfoBean mData;

    public abstract void setData(InviteInfoBean data);

    protected DialogWithdrawBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatImageView ivClose, ShapeFrameLayout llFlb, ShapeFrameLayout llPtb, ShapeFrameLayout llZfb) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivClose = ivClose;
        this.llFlb = llFlb;
        this.llPtb = llPtb;
        this.llZfb = llZfb;
    }

    public InviteInfoBean getData() {
        return this.mData;
    }

    public static DialogWithdrawBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWithdrawBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogWithdrawBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_withdraw, root, attachToRoot, component);
    }

    public static DialogWithdrawBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWithdrawBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogWithdrawBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_withdraw, null, false, component);
    }

    public static DialogWithdrawBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWithdrawBinding bind(View view, Object component) {
        return (DialogWithdrawBinding) bind(component, view, R.layout.dialog_withdraw);
    }
}
