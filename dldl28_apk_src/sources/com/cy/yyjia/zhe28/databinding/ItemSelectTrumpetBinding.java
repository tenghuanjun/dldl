package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ReceiveStatusBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemSelectTrumpetBinding extends ViewDataBinding {

    @Bindable
    protected ReceiveStatusBean mData;
    public final TextView name;

    public abstract void setData(ReceiveStatusBean data);

    protected ItemSelectTrumpetBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView name) {
        super(_bindingComponent, _root, _localFieldCount);
        this.name = name;
    }

    public ReceiveStatusBean getData() {
        return this.mData;
    }

    public static ItemSelectTrumpetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSelectTrumpetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSelectTrumpetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_select_trumpet, root, attachToRoot, component);
    }

    public static ItemSelectTrumpetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSelectTrumpetBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSelectTrumpetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_select_trumpet, null, false, component);
    }

    public static ItemSelectTrumpetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSelectTrumpetBinding bind(View view, Object component) {
        return (ItemSelectTrumpetBinding) bind(component, view, R.layout.item_select_trumpet);
    }
}
