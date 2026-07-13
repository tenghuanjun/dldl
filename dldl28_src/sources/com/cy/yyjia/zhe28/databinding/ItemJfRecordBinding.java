package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CreditResult;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemJfRecordBinding extends ViewDataBinding {

    @Bindable
    protected CreditResult mData;

    public abstract void setData(CreditResult data);

    protected ItemJfRecordBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public CreditResult getData() {
        return this.mData;
    }

    public static ItemJfRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemJfRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemJfRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_jf_record, root, attachToRoot, component);
    }

    public static ItemJfRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemJfRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemJfRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_jf_record, null, false, component);
    }

    public static ItemJfRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemJfRecordBinding bind(View view, Object component) {
        return (ItemJfRecordBinding) bind(component, view, R.layout.item_jf_record);
    }
}
