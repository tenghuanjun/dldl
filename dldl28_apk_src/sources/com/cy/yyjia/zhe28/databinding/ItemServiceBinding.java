package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ServiceTypeBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemServiceBinding extends ViewDataBinding {

    @Bindable
    protected ServiceTypeBean mData;
    public final RecyclerView rv;

    public abstract void setData(ServiceTypeBean data);

    protected ItemServiceBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
    }

    public ServiceTypeBean getData() {
        return this.mData;
    }

    public static ItemServiceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemServiceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemServiceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_service, root, attachToRoot, component);
    }

    public static ItemServiceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemServiceBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemServiceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_service, null, false, component);
    }

    public static ItemServiceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemServiceBinding bind(View view, Object component) {
        return (ItemServiceBinding) bind(component, view, R.layout.item_service);
    }
}
