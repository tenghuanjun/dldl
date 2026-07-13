package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDealPicBinding extends ViewDataBinding {
    public final ImageView iv;
    public final ImageView ivDelete;

    @Bindable
    protected String mData;

    public abstract void setData(String data);

    protected ItemDealPicBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv, ImageView ivDelete) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
        this.ivDelete = ivDelete;
    }

    public String getData() {
        return this.mData;
    }

    public static ItemDealPicBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealPicBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDealPicBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_pic, root, attachToRoot, component);
    }

    public static ItemDealPicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealPicBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDealPicBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_pic, null, false, component);
    }

    public static ItemDealPicBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealPicBinding bind(View view, Object component) {
        return (ItemDealPicBinding) bind(component, view, R.layout.item_deal_pic);
    }
}
