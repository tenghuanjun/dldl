package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMLogBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGmLogBinding extends ViewDataBinding {

    @Bindable
    protected GMLogBean mData;

    public abstract void setData(GMLogBean data);

    protected ItemGmLogBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GMLogBean getData() {
        return this.mData;
    }

    public static ItemGmLogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmLogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGmLogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gm_log, root, attachToRoot, component);
    }

    public static ItemGmLogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmLogBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGmLogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gm_log, null, false, component);
    }

    public static ItemGmLogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmLogBinding bind(View view, Object component) {
        return (ItemGmLogBinding) bind(component, view, R.layout.item_gm_log);
    }
}
