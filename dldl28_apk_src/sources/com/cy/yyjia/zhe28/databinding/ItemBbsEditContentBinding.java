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
public abstract class ItemBbsEditContentBinding extends ViewDataBinding {

    @Bindable
    protected BbsEditFastBean.Content mData;

    public abstract void setData(BbsEditFastBean.Content data);

    protected ItemBbsEditContentBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public BbsEditFastBean.Content getData() {
        return this.mData;
    }

    public static ItemBbsEditContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsEditContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBbsEditContentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_edit_content, root, attachToRoot, component);
    }

    public static ItemBbsEditContentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsEditContentBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBbsEditContentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_edit_content, null, false, component);
    }

    public static ItemBbsEditContentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsEditContentBinding bind(View view, Object component) {
        return (ItemBbsEditContentBinding) bind(component, view, R.layout.item_bbs_edit_content);
    }
}
