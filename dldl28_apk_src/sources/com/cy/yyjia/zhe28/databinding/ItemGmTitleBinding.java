package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGmTitleBinding extends ViewDataBinding {
    public final ShapeTextView btn;

    @Bindable
    protected GMTitleBean mData;

    public abstract void setData(GMTitleBean data);

    protected ItemGmTitleBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
    }

    public GMTitleBean getData() {
        return this.mData;
    }

    public static ItemGmTitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmTitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGmTitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gm_title, root, attachToRoot, component);
    }

    public static ItemGmTitleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmTitleBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGmTitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_gm_title, null, false, component);
    }

    public static ItemGmTitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGmTitleBinding bind(View view, Object component) {
        return (ItemGmTitleBinding) bind(component, view, R.layout.item_gm_title);
    }
}
