package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.WithdrewBillBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemWithdrewRecordBinding extends ViewDataBinding {

    @Bindable
    protected WithdrewBillBean mData;

    public abstract void setData(WithdrewBillBean data);

    protected ItemWithdrewRecordBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public WithdrewBillBean getData() {
        return this.mData;
    }

    public static ItemWithdrewRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWithdrewRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWithdrewRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_withdrew_record, root, attachToRoot, component);
    }

    public static ItemWithdrewRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWithdrewRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemWithdrewRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_withdrew_record, null, false, component);
    }

    public static ItemWithdrewRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWithdrewRecordBinding bind(View view, Object component) {
        return (ItemWithdrewRecordBinding) bind(component, view, R.layout.item_withdrew_record);
    }
}
