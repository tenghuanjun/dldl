package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogGmItemsBinding extends ViewDataBinding {
    public final ImageView ivClose;

    @Bindable
    protected String mText;
    public final RecyclerView rv;

    public abstract void setText(String text);

    protected DialogGmItemsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivClose, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivClose = ivClose;
        this.rv = rv;
    }

    public String getText() {
        return this.mText;
    }

    public static DialogGmItemsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmItemsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogGmItemsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_gm_items, root, attachToRoot, component);
    }

    public static DialogGmItemsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmItemsBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogGmItemsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_gm_items, null, false, component);
    }

    public static DialogGmItemsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmItemsBinding bind(View view, Object component) {
        return (DialogGmItemsBinding) bind(component, view, R.layout.dialog_gm_items);
    }
}
