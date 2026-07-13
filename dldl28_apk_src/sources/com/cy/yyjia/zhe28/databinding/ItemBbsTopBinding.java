package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBbsTopBinding extends ViewDataBinding {

    @Bindable
    protected BbsBean mData;

    public abstract void setData(BbsBean data);

    protected ItemBbsTopBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public BbsBean getData() {
        return this.mData;
    }

    public static ItemBbsTopBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsTopBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBbsTopBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_top, root, attachToRoot, component);
    }

    public static ItemBbsTopBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsTopBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBbsTopBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_top, null, false, component);
    }

    public static ItemBbsTopBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsTopBinding bind(View view, Object component) {
        return (ItemBbsTopBinding) bind(component, view, R.layout.item_bbs_top);
    }
}
