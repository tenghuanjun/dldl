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
public abstract class ItemPicSelectBinding extends ViewDataBinding {
    public final ImageView iv;
    public final ImageView ivDelete;

    @Bindable
    protected String mData;

    public abstract void setData(String data);

    protected ItemPicSelectBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv, ImageView ivDelete) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
        this.ivDelete = ivDelete;
    }

    public String getData() {
        return this.mData;
    }

    public static ItemPicSelectBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPicSelectBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemPicSelectBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_pic_select, root, attachToRoot, component);
    }

    public static ItemPicSelectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPicSelectBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemPicSelectBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_pic_select, null, false, component);
    }

    public static ItemPicSelectBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPicSelectBinding bind(View view, Object component) {
        return (ItemPicSelectBinding) bind(component, view, R.layout.item_pic_select);
    }
}
