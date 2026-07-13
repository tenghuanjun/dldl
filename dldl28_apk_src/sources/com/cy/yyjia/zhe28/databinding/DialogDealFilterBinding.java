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
import com.cy.yyjia.zhe28.domain.DealParamBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogDealFilterBinding extends ViewDataBinding {
    public final TextView btn1;
    public final TextView btn2;
    public final ImageView ivClose;

    @Bindable
    protected DealParamBean mData;

    @Bindable
    protected View.OnClickListener mOnClick;
    public final RecyclerView rvType;

    public abstract void setData(DealParamBean data);

    public abstract void setOnClick(View.OnClickListener onClick);

    protected DialogDealFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn1, TextView btn2, ImageView ivClose, RecyclerView rvType) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.ivClose = ivClose;
        this.rvType = rvType;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public DealParamBean getData() {
        return this.mData;
    }

    public static DialogDealFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDealFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_filter, root, attachToRoot, component);
    }

    public static DialogDealFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDealFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_filter, null, false, component);
    }

    public static DialogDealFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealFilterBinding bind(View view, Object component) {
        return (DialogDealFilterBinding) bind(component, view, R.layout.dialog_deal_filter);
    }
}
