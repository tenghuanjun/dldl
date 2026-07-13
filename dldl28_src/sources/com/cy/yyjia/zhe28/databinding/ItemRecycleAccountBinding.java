package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.RecycleListBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemRecycleAccountBinding extends ViewDataBinding {

    @Bindable
    protected RecycleListBean.Account mData;

    @Bindable
    protected int mMode;

    public abstract void setData(RecycleListBean.Account data);

    public abstract void setMode(int mode);

    protected ItemRecycleAccountBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public RecycleListBean.Account getData() {
        return this.mData;
    }

    public int getMode() {
        return this.mMode;
    }

    public static ItemRecycleAccountBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRecycleAccountBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemRecycleAccountBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_recycle_account, root, attachToRoot, component);
    }

    public static ItemRecycleAccountBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRecycleAccountBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemRecycleAccountBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_recycle_account, null, false, component);
    }

    public static ItemRecycleAccountBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRecycleAccountBinding bind(View view, Object component) {
        return (ItemRecycleAccountBinding) bind(component, view, R.layout.item_recycle_account);
    }
}
