package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LayoutDealAllBinding extends ViewDataBinding {
    public final ImageView btnAll;
    public final ImageView btnDl;

    protected LayoutDealAllBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView btnAll, ImageView btnDl) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnAll = btnAll;
        this.btnDl = btnDl;
    }

    public static LayoutDealAllBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDealAllBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutDealAllBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_deal_all, root, attachToRoot, component);
    }

    public static LayoutDealAllBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDealAllBinding inflate(LayoutInflater inflater, Object component) {
        return (LayoutDealAllBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_deal_all, null, false, component);
    }

    public static LayoutDealAllBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDealAllBinding bind(View view, Object component) {
        return (LayoutDealAllBinding) bind(component, view, R.layout.layout_deal_all);
    }
}
