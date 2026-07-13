package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemTrumpet2Binding extends ViewDataBinding {

    @Bindable
    protected DealBean mData;

    @Bindable
    protected boolean mManager;

    public abstract void setData(DealBean data);

    public abstract void setManager(boolean manager);

    protected ItemTrumpet2Binding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public boolean getManager() {
        return this.mManager;
    }

    public DealBean getData() {
        return this.mData;
    }

    public static ItemTrumpet2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTrumpet2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemTrumpet2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_trumpet2, root, attachToRoot, component);
    }

    public static ItemTrumpet2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTrumpet2Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemTrumpet2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_trumpet2, null, false, component);
    }

    public static ItemTrumpet2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTrumpet2Binding bind(View view, Object component) {
        return (ItemTrumpet2Binding) bind(component, view, R.layout.item_trumpet2);
    }
}
