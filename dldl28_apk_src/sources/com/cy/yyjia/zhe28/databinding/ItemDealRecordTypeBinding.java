package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.FilterBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDealRecordTypeBinding extends ViewDataBinding {

    @Bindable
    protected FilterBean mData;

    public abstract void setData(FilterBean data);

    protected ItemDealRecordTypeBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public FilterBean getData() {
        return this.mData;
    }

    public static ItemDealRecordTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealRecordTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDealRecordTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_record_type, root, attachToRoot, component);
    }

    public static ItemDealRecordTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealRecordTypeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDealRecordTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_record_type, null, false, component);
    }

    public static ItemDealRecordTypeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealRecordTypeBinding bind(View view, Object component) {
        return (ItemDealRecordTypeBinding) bind(component, view, R.layout.item_deal_record_type);
    }
}
