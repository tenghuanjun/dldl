package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CollectionBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomePicBinding extends ViewDataBinding {

    @Bindable
    protected CollectionBean mData;

    public abstract void setData(CollectionBean data);

    protected ItemHomePicBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public CollectionBean getData() {
        return this.mData;
    }

    public static ItemHomePicBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomePicBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomePicBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_pic, root, attachToRoot, component);
    }

    public static ItemHomePicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomePicBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomePicBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_pic, null, false, component);
    }

    public static ItemHomePicBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomePicBinding bind(View view, Object component) {
        return (ItemHomePicBinding) bind(component, view, R.layout.item_home_pic);
    }
}
