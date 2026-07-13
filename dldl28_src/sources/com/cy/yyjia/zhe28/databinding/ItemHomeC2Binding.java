package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeC2Binding extends ViewDataBinding {
    public final RecyclerView rv;

    protected ItemHomeC2Binding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
    }

    public static ItemHomeC2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeC2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeC2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_c2, root, attachToRoot, component);
    }

    public static ItemHomeC2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeC2Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeC2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_c2, null, false, component);
    }

    public static ItemHomeC2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeC2Binding bind(View view, Object component) {
        return (ItemHomeC2Binding) bind(component, view, R.layout.item_home_c2);
    }
}
