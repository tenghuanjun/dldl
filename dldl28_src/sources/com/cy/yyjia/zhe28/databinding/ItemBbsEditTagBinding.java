package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsEditFastBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBbsEditTagBinding extends ViewDataBinding {

    @Bindable
    protected BbsEditFastBean mData;

    public abstract void setData(BbsEditFastBean data);

    protected ItemBbsEditTagBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public BbsEditFastBean getData() {
        return this.mData;
    }

    public static ItemBbsEditTagBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsEditTagBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBbsEditTagBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_edit_tag, root, attachToRoot, component);
    }

    public static ItemBbsEditTagBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsEditTagBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBbsEditTagBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_edit_tag, null, false, component);
    }

    public static ItemBbsEditTagBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsEditTagBinding bind(View view, Object component) {
        return (ItemBbsEditTagBinding) bind(component, view, R.layout.item_bbs_edit_tag);
    }
}
