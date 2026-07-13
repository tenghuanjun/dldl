package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogPicBinding extends ViewDataBinding {

    @Bindable
    protected String mData;

    public abstract void setData(String data);

    protected DialogPicBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public String getData() {
        return this.mData;
    }

    public static DialogPicBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPicBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogPicBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_pic, root, attachToRoot, component);
    }

    public static DialogPicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPicBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogPicBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_pic, null, false, component);
    }

    public static DialogPicBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPicBinding bind(View view, Object component) {
        return (DialogPicBinding) bind(component, view, R.layout.dialog_pic);
    }
}
