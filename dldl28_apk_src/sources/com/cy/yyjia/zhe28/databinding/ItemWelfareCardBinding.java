package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.WelfareBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemWelfareCardBinding extends ViewDataBinding {

    @Bindable
    protected WelfareBean.Card mData;

    public abstract void setData(WelfareBean.Card data);

    protected ItemWelfareCardBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public WelfareBean.Card getData() {
        return this.mData;
    }

    public static ItemWelfareCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWelfareCardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare_card, root, attachToRoot, component);
    }

    public static ItemWelfareCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareCardBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemWelfareCardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare_card, null, false, component);
    }

    public static ItemWelfareCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareCardBinding bind(View view, Object component) {
        return (ItemWelfareCardBinding) bind(component, view, R.layout.item_welfare_card);
    }
}
