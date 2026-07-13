package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBlockBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemBbsCateBinding extends ViewDataBinding {

    @Bindable
    protected BbsBlockBean.ListBean mData;

    public abstract void setData(BbsBlockBean.ListBean data);

    protected ItemBbsCateBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public BbsBlockBean.ListBean getData() {
        return this.mData;
    }

    public static ItemBbsCateBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsCateBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemBbsCateBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_cate, root, attachToRoot, component);
    }

    public static ItemBbsCateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsCateBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemBbsCateBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_bbs_cate, null, false, component);
    }

    public static ItemBbsCateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemBbsCateBinding bind(View view, Object component) {
        return (ItemBbsCateBinding) bind(component, view, R.layout.item_bbs_cate);
    }
}
