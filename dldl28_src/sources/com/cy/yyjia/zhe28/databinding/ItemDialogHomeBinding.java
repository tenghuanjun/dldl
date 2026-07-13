package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.SlideBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDialogHomeBinding extends ViewDataBinding {

    @Bindable
    protected SlideBean mData;

    public abstract void setData(SlideBean data);

    protected ItemDialogHomeBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public SlideBean getData() {
        return this.mData;
    }

    public static ItemDialogHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDialogHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDialogHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_dialog_home, root, attachToRoot, component);
    }

    public static ItemDialogHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDialogHomeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDialogHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_dialog_home, null, false, component);
    }

    public static ItemDialogHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDialogHomeBinding bind(View view, Object component) {
        return (ItemDialogHomeBinding) bind(component, view, R.layout.item_dialog_home);
    }
}
