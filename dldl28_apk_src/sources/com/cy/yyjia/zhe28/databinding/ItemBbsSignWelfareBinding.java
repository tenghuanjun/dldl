package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsSignBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBbsSignWelfareBinding extends ViewDataBinding {

    @Bindable
    protected BbsSignBean.Welfare mData;

    public abstract void setData(BbsSignBean.Welfare data);

    protected ItemBbsSignWelfareBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public BbsSignBean.Welfare getData() {
        return this.mData;
    }

    public static ItemBbsSignWelfareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsSignWelfareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBbsSignWelfareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_sign_welfare, root, attachToRoot, component);
    }

    public static ItemBbsSignWelfareBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsSignWelfareBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBbsSignWelfareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_sign_welfare, null, false, component);
    }

    public static ItemBbsSignWelfareBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsSignWelfareBinding bind(View view, Object component) {
        return (ItemBbsSignWelfareBinding) bind(component, view, R.layout.item_bbs_sign_welfare);
    }
}
