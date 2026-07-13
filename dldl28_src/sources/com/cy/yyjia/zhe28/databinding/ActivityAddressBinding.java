package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.AddressResult;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityAddressBinding extends ViewDataBinding {

    @Bindable
    protected AddressResult.DataBean mData;

    public abstract void setData(AddressResult.DataBean data);

    protected ActivityAddressBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public AddressResult.DataBean getData() {
        return this.mData;
    }

    public static ActivityAddressBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddressBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAddressBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_address, root, attachToRoot, component);
    }

    public static ActivityAddressBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddressBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAddressBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_address, null, false, component);
    }

    public static ActivityAddressBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddressBinding bind(View view, Object component) {
        return (ActivityAddressBinding) bind(component, view, R.layout.activity_address);
    }
}
