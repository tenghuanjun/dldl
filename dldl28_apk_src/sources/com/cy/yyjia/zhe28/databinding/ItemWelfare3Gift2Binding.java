package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TypeBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemWelfare3Gift2Binding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected TypeBean mData;

    public abstract void setData(TypeBean data);

    protected ItemWelfare3Gift2Binding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
    }

    public TypeBean getData() {
        return this.mData;
    }

    public static ItemWelfare3Gift2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3Gift2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWelfare3Gift2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare3_gift2, root, attachToRoot, component);
    }

    public static ItemWelfare3Gift2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3Gift2Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemWelfare3Gift2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare3_gift2, null, false, component);
    }

    public static ItemWelfare3Gift2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfare3Gift2Binding bind(View view, Object component) {
        return (ItemWelfare3Gift2Binding) bind(component, view, R.layout.item_welfare3_gift2);
    }
}
