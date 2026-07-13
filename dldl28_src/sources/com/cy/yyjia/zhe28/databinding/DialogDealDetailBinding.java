package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogDealDetailBinding extends ViewDataBinding {
    public final ImageView ivBack;
    public final ImageView ivShare;

    @Bindable
    protected DealBean mData;

    public abstract void setData(DealBean data);

    protected DialogDealDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivBack, ImageView ivShare) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivBack = ivBack;
        this.ivShare = ivShare;
    }

    public DealBean getData() {
        return this.mData;
    }

    public static DialogDealDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDealDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_detail, root, attachToRoot, component);
    }

    public static DialogDealDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDealDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_detail, null, false, component);
    }

    public static DialogDealDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealDetailBinding bind(View view, Object component) {
        return (DialogDealDetailBinding) bind(component, view, R.layout.dialog_deal_detail);
    }
}
