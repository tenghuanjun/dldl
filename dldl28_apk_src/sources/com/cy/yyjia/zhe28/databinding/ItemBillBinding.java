package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.RecordBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBillBinding extends ViewDataBinding {

    @Bindable
    protected RecordBean mData;

    public abstract void setData(RecordBean data);

    protected ItemBillBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public RecordBean getData() {
        return this.mData;
    }

    public static ItemBillBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBillBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBillBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bill, root, attachToRoot, component);
    }

    public static ItemBillBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBillBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBillBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bill, null, false, component);
    }

    public static ItemBillBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBillBinding bind(View view, Object component) {
        return (ItemBillBinding) bind(component, view, R.layout.item_bill);
    }
}
