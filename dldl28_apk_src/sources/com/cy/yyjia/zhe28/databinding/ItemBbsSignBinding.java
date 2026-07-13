package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsSignBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBbsSignBinding extends ViewDataBinding {

    @Bindable
    protected BbsSignBean.Day mData;

    public abstract void setData(BbsSignBean.Day data);

    protected ItemBbsSignBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public BbsSignBean.Day getData() {
        return this.mData;
    }

    public static ItemBbsSignBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsSignBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBbsSignBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_sign, root, attachToRoot, component);
    }

    public static ItemBbsSignBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsSignBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBbsSignBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_sign, null, false, component);
    }

    public static ItemBbsSignBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsSignBinding bind(View view, Object component) {
        return (ItemBbsSignBinding) bind(component, view, R.layout.item_bbs_sign);
    }
}
