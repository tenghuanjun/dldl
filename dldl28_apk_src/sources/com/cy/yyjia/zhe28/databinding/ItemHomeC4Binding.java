package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CollectionBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeC4Binding extends ViewDataBinding {

    @Bindable
    protected CollectionBean mData;
    public final RecyclerView rv;

    public abstract void setData(CollectionBean data);

    protected ItemHomeC4Binding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
    }

    public CollectionBean getData() {
        return this.mData;
    }

    public static ItemHomeC4Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeC4Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeC4Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_c4, root, attachToRoot, component);
    }

    public static ItemHomeC4Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeC4Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeC4Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_c4, null, false, component);
    }

    public static ItemHomeC4Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeC4Binding bind(View view, Object component) {
        return (ItemHomeC4Binding) bind(component, view, R.layout.item_home_c4);
    }
}
